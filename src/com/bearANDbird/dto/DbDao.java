package com.bearANDbird.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bearANDbird.onstartup.MyDataSource;



public class DbDao {
	private Connection conn;
	private String driver;
	private String url;
	private String username;
	private String passwd;
	public DbDao(){
		
	}
	public DbDao(String driver, String url
			, String username, String passwd){
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.passwd = passwd;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	private Connection getConnection() throws Exception{
		if(conn == null){
			conn = MyDataSource.getDataSource().getConnection();
		}
		return conn;
	}
	public boolean insert(String sql,Object[]args) throws Exception{
		PreparedStatement prepStatement = getConnection().prepareStatement(sql);
		for(int i = 0; i < args.length; i++){
			prepStatement.setObject(i + 1, args[i]);
		}
		if(prepStatement.executeUpdate() != 1){
			return false;
		}
		return true;
	}
	public ResultSet query(String sql,Object[]args)throws Exception{
		PreparedStatement prepStatement = getConnection().prepareStatement(sql);
		for(int i = 0; i < args.length; i++){
			prepStatement.setObject(i + 1, args[i]);
		}
		return prepStatement.executeQuery();
	}
	public void modify(String sql,Object[]args)throws Exception{
		PreparedStatement prepStatement = getConnection().prepareStatement(sql);
		for(int i = 0; i < args.length; i++){
			prepStatement.setObject(i + 1, args[i]);
		}
		prepStatement.executeUpdate();
		prepStatement.close();
	}
	public void closeConn() throws Exception{
		if(conn != null && !conn.isClosed()){
			conn.close();
		}
	}
}
