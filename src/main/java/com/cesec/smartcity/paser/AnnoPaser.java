package com.cesec.smartcity.paser;




import com.cesec.smartcity.annotation.COLUMNTYPE;
import com.cesec.smartcity.annotation.ColumnDef;
import com.cesec.smartcity.annotation.TableDef;
import com.cesec.smartcity.entity.Column;
import com.cesec.smartcity.entity.Table;
import com.cesec.smartcity.exception.AnnotationNoFoundException;
import com.cesec.smartcity.model.News;
import com.cesec.smartcity.util.StringUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>文件名称: com.cesec.smartcity.paser</p >
 * <p>版权所有: 版权所有(C)2018-2018</p >
 * <p>公    司: 中电智绘数据技术有限公司</p >
 * <p>内容摘要: com.cesec.smartcity.paser</p >
 * <p>完成日期: 2018/4/3</p >
 *
 * @author 武庭伟
 * @version 1.0
 */
public class AnnoPaser {

    private static Table table = new Table();
    private static List<Column> columns = new ArrayList<>();

    public static void annoPaser(Class clazz) throws AnnotationNoFoundException {

        //解析类上面的注解
        if(clazz.isAnnotationPresent(TableDef.class)){
            TableDef tableDef = (TableDef) clazz.getAnnotation(TableDef.class);

            //表名
            String tableName = tableDef.value();
            if(StringUtil.isEmpty(tableName)){
                tableName = clazz.getName();
            }
            table.setTableName(analysisName(tableName,"_"));

            table.setCharset(tableDef.charset());

            table.setComment(tableDef.comment());

            table.setDbEngine(tableDef.dbengine());

            table.setDeleteExist(tableDef.deleteExist());

        }else {
            throw new AnnotationNoFoundException("未找到对应的注解");
        }

        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields){
            if(field.isAnnotationPresent(ColumnDef.class)){

                Column column = new Column();
                columns.add(column);

                ColumnDef columnDef = field.getAnnotation(ColumnDef.class);
                String colName = columnDef.name();
                if(StringUtil.isEmpty(colName)){
                    colName = field.getName();
                }
                column.setName(analysisName(colName,"_"));

                column.setComment(columnDef.comment());

                column.setLength(columnDef.length());

                column.setNullable(columnDef.nullable());

                column.setPk(columnDef.pk());

                column.setPoint(columnDef.point());

                column.setType(columnDef.type());

                column.setValue(columnDef.value());

                column.setAutoIncrement(columnDef.autoIncrement());
            }

        }

        if(columns.isEmpty()){
            throw new AnnotationNoFoundException("未找到对应的字段!");
        }

    }

    /**
     * 创建表
     */
    public static String generactorSQL(Class clazz) throws AnnotationNoFoundException {

        annoPaser(clazz);

        StringBuffer sb = new StringBuffer();
        if(table.isDeleteExist()){
            sb.append("drop table if exists ");
            sb.append(table.getTableName());
            sb.append(";");
        }

        sb.append("create table ");
        sb.append(table.getTableName());
        sb.append("(\n");
        for(int i = 0; i < columns.size(); i++){
            Column c = columns.get(i);
            sb.append(c.getName());
            sb.append(" ");
            sb.append(c.getType());
            if(c.getType().equals(COLUMNTYPE.VARCHAR)){ //VARCHAR类型
                sb.append("(");
                if(c.getLength() <= 0){
                    sb.append(20);
                }else{
                    sb.append(c.getLength());
                }
                sb.append(") ");

                if(StringUtil.isEmpty(c.getValue())){
                    sb.append("default ");
                    sb.append("' '");
                }else {
                    sb.append("default ");
                    sb.append("'");
                    sb.append(c.getValue());
                    sb.append("'");
                }
            }else if(c.getType().equals(COLUMNTYPE.DECIMAL)){ //DECIMAL类型
                sb.append("(");
                if(c.getLength() <= 0){
                    sb.append(20);
                }else{
                    sb.append(c.getLength());
                }
                sb.append(",");
                sb.append(c.getPoint());
                sb.append(") ");
                if(StringUtil.isEmpty(c.getValue())){
                    sb.append("default ");
                    sb.append("(20,6)");
                }else{
                    sb.append("default ");
                    sb.append(c.getValue());
                }
            }else if(c.getType().equals(COLUMNTYPE.INT)){  //INT类型
                if(c.getLength() >0){
                    sb.append("(");
                    sb.append(c.getLength());
                    sb.append(") ");
                }else {
                    sb.append(" ");
                }
                if(!c.isPk()) {
                    if (StringUtil.isEmpty(c.getValue())) {
                        sb.append("default ");
                        sb.append(0);
                    } else {
                        sb.append("default ");
                        sb.append(c.getValue());
                    }
                }
            }else {
                sb.append(" ");
                if(!StringUtil.isEmpty(c.getValue())){
                    sb.append("default ");
                    sb.append(c.getValue());
                }
            }
            if(c.isPk()){
                sb.append(" primary key not null ");
                if(c.isAutoIncrement()){
                    sb.append(" auto_increment");
                }
            }else{
                if(!c.isNullable()){
                    sb.append(" not null ");
                }
            }
            if(!StringUtil.isEmpty(c.getComment())){
                sb.append(" comment '");
                sb.append(c.getComment());
                sb.append("'");
            }
            if(i == columns.size() -1 ){
                sb.append("\n");
            }else {
                sb.append(",\n");
            }
        }

        sb.append(")ENGINE=");
        sb.append(table.getDbEngine());
        sb.append(" default charset=");
        sb.append(table.getCharset());
        sb.append(";\n");

        if(!StringUtil.isEmpty(table.getComment())){
            sb.append("alter table");
            sb.append(table.getTableName());
            sb.append(" comment ");
            sb.append("'");
            sb.append(table.getComment());
            sb.append("';");
        }

        System.out.println("generactorSQL："+sb.toString());
        return sb.toString();
    }

    public static List<String> generactorSQL(Class[] clazzs) throws AnnotationNoFoundException {

        List<String> sqls = new ArrayList<>();

        for(Class clz : clazzs){
            sqls.add(generactorSQL(clz));
        }
        return sqls;
    }


    /**
     *
     * @param name 表或字段的名字
     * @param sepator 分隔符
     * @return
     */
    private static String analysisName(String name,String sepator){
        if(StringUtil.isEmpty(sepator) || name.length() <= 1){
            return name.toLowerCase();
        }else{
            List<Integer> posList = new ArrayList<>();
            for(int i = 1; i < name.length(); i++){
                char c = name.charAt(i);
                //当前字母是大写
                if(Character.isUpperCase(c)){
                    posList.add(i);
                }
            }
            Collections.reverse(posList);

            List<String> tmpList = new ArrayList<>();
            for(char c : name.toCharArray()){
                tmpList.add(String.valueOf(c));

            }

            for(int j : posList){
                tmpList.add(j,sepator);
            }

            StringBuilder tmpName = new StringBuilder();
            for (String aTmpList : tmpList) {
                tmpName.append(aTmpList.toLowerCase());
            }

            return tmpName.toString();
        }
    }

    public static void main(String[] args) throws AnnotationNoFoundException {
//        AnnoPaser ap = new AnnoPaser();
//        AnnoPaser.annoPaser(News.class);
//        table.getTableName();
//        columns.get(0).getName();
//        System.out.println("success");

        System.out.println(AnnoPaser.generactorSQL(News.class));
//        System.out.println(ap.analysisName("NewsCommentTestA","_"));
//        char[] c = new char[]{'c','d','e','f'};
//        String[] s = new String[]{"a","b","v","c"};
//        List a = Arrays.asList(c);
//
//        a.forEach(e -> System.out.println(e.toString()));
    }

}
