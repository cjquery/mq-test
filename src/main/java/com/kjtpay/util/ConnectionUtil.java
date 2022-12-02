package com.kjtpay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    static Logger logger = LoggerFactory.getLogger(ConnectionUtil.class.getSimpleName());

    private static Connection pbsbosConnection = null;
    private static Connection fosConnection = null;
    private static Connection tssConnection = null;
    private static String driver = "oracle.jdbc.OracleDriver";    //驱动标识符
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getPbsbosConnection() {
        if (pbsbosConnection != null) {
            return pbsbosConnection;
        }
        String basis_url = "jdbc:oracle:thin:@192.168.180.66:1521:yoyo"; //链接字符串
        String basis_user = "pbsdbuser";         //数据库的用户名
        String basis_password = "pbsdbuser";     //数据库的密码

        try {
            return pbsbosConnection = DriverManager.getConnection(basis_url, basis_user, basis_password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getFosConnection() {
        if (fosConnection != null) {
            return fosConnection;
        }
        String basis_url = "jdbc:oracle:thin:@192.168.180.66:1521:yoyo"; //链接字符串
        String basis_user = "fosuser";         //数据库的用户名
        String basis_password = "fosuser";     //数据库的密码

        try {
            return fosConnection = DriverManager.getConnection(basis_url, basis_user, basis_password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getTssConnection() {
        if (tssConnection != null) {
            return tssConnection;
        }
        String basis_url = "jdbc:oracle:thin:@192.168.180.66:1521:yoyo"; //链接字符串
        String basis_user = "tssuser";         //数据库的用户名
        String basis_password = "tssuser";     //数据库的密码

        try {
            return tssConnection = DriverManager.getConnection(basis_url, basis_user, basis_password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	public static void main(String[] args) {
		Connection pbsbosConnection = getPbsbosConnection();
		if(pbsbosConnection!=null){
			System.out.println("1111");
		}
	}

}