package src.main.java.com.cesec.smartcity.paser;


import src.main.java.com.cesec.smartcity.annotation.ColumnDef;
import src.main.java.com.cesec.smartcity.annotation.TableDef;
import src.main.java.com.cesec.smartcity.entity.Column;
import src.main.java.com.cesec.smartcity.entity.Table;
import src.main.java.com.cesec.smartcity.exception.AnnotationNoFoundException;
import src.main.java.com.cesec.smartcity.model.News;
import src.main.java.com.cesec.smartcity.util.StringUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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
                column.setName(colName);

                column.setComment(columnDef.comment());

                column.setLength(columnDef.length());

                column.setNullable(columnDef.nullable());

                column.setPk(columnDef.pk());

                column.setPoint(columnDef.point());

                column.setType(columnDef.type().toString());

                column.setValue(columnDef.value());
            }

        }

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
        AnnoPaser.annoPaser(News.class);
        table.getTableName();
        columns.get(0).getName();
        System.out.println("success");
//        System.out.println(ap.analysisName("NewsCommentTestA","_"));
//        char[] c = new char[]{'c','d','e','f'};
//        String[] s = new String[]{"a","b","v","c"};
//        List a = Arrays.asList(c);
//
//        a.forEach(e -> System.out.println(e.toString()));
    }
}
