package com.team.hash.hashproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DBConstant.URL, DBConstant.ID, DBConstant.PASSWORD);
		return connection;
	}

	// 디비 붙어있는지 확인
	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection(DBConstant.URL, DBConstant.ID, DBConstant.PASSWORD);
		System.out.println(connection);
	}
}