package com.cesec.smartcity.main;

import com.cesec.smartcity.exception.AnnotationNoFoundException;
import com.cesec.smartcity.jdbc.MyConnection;
import com.cesec.smartcity.model.News;
import com.cesec.smartcity.paser.AnnoPaser;

/**
 * <p>文件名称: com.cesec.smartcity.main</p >
 * <p>版权所有: 版权所有(C)2018-2018</p >
 * <p>公    司: 中电智绘数据技术有限公司</p >
 * <p>内容摘要: com.cesec.smartcity.main</p >
 * <p>完成日期: 2018/4/8</p >
 *
 * @author 武庭伟
 * @version 1.0
 */
public class Main {

    public static void main(String[] args){
        try {
            String sql = AnnoPaser.generactorSQL(News.class);
            MyConnection.executeSQL(sql);
        } catch (AnnotationNoFoundException e) {
            e.printStackTrace();
        }
    }
}
