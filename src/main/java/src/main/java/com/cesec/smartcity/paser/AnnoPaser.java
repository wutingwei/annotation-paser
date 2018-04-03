package src.main.java.com.cesec.smartcity.paser;


import src.main.java.com.cesec.smartcity.annotation.TableDef;
import src.main.java.com.cesec.smartcity.entity.Table;
import src.main.java.com.cesec.smartcity.util.StringUtil;

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

    private Table table = new Table();

    public static void annoPaser(Class clazz){

        //解析类上面的注解
        if(clazz.isAnnotationPresent(TableDef.class)){
            TableDef tableDef = (TableDef) clazz.getAnnotation(TableDef.class);
            String tableName = tableDef.value();
            if(StringUtil.isEmpty(tableName)){
                tableName = clazz.getName();
            }

        }else {

        }

        Field[] fields = clazz.getDeclaredFields();

//        for(Field f : fields){
//            System.out.println(f.getName());
//        }

        System.out.println(clazz.isAnnotationPresent(TableDef.class));



    }


    private String analysisName(String name,String sepator){
        if(StringUtil.isEmpty(sepator) || name.length() <= 1){
            return name.toLowerCase();
        }else{
            String tmpName = name;
            List<Integer> posList = new ArrayList<>();
            for(int i = 1; i < name.length()-1; i++){
                char c = name.charAt(i);
                //当前字母是大写
                if(Character.isUpperCase(c)){
                    posList.add(i);
                }
            }
            Collections.reverse(posList);

            char[] chars = name.toCharArray();


            return tmpName;
        }
    }
}
