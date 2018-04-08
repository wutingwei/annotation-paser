package com.cesec.smartcity.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>文件名称: com.cesec.smartcity.jdbc</p >
 * <p>版权所有: 版权所有(C)2018-2018</p >
 * <p>公    司: 中电智绘数据技术有限公司</p >
 * <p>内容摘要: com.cesec.smartcity.jdbc</p >
 * <p>完成日期: 2018/4/8</p >
 *
 * @author 武庭伟
 * @version 1.0
 */
public class MyConnection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String URL="jdbc:mysql://192.168.200.10:3306/cityapp?useUnicode=true&amp;characterEncoding=utf-8";
        String USER="root";
        String PASSWORD="123456";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
        return conn;
    }

    public static void executeSQL(String sql){
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            int res = pstmt.executeUpdate();
            System.out.println(res);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
