package com.vix.common.id.storer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vix.common.id.SequenceStorer;
import com.vix.common.id.exception.StoreSequenceException;
import com.vix.common.properties.PropertiesFactory;
import com.vix.common.properties.PropertiesFile;
import com.vix.common.properties.PropertiesHelper;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-21
 */
public class DBSequenceStorer implements SequenceStorer {

	private javax.sql.DataSource dataSource;
	private String tableName;
	private String idColumnName;
	private String valueColumnName;
	static PropertiesHelper vix = PropertiesFactory.getPropertiesHelper(PropertiesFile.VIX_APPLICATION);

	static String mysqlurl = vix.getValue("jdbc.url");
	static String mysqlusername = vix.getValue("jdbc.username");
	static String mysqlpassword = vix.getValue("jdbc.password");
	private Connection conn;

	public Connection getMysqlConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mysqlurl, mysqlusername, mysqlpassword);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long load(String sequenceID) throws StoreSequenceException {
		try {
			conn = this.getMysqlConnection();
			conn.setAutoCommit(false);
			String selectSql = "select * from SYS_ENCODINGRULESTABLEINTHEMIDDLE where sequenceid='" + sequenceID + "'";
			PreparedStatement ps = conn.prepareStatement(selectSql);
			ResultSet rs = ps.executeQuery(selectSql);
			while (rs.next()) {
				return rs.getLong("INITVALUE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public void updateMaxValueByFieldName(long sequence, String sequenceID) throws StoreSequenceException {
		try {
			conn = this.getMysqlConnection();
			conn.setAutoCommit(false);
			String selectSql = "select COUNT(*) as count from SYS_ENCODINGRULESTABLEINTHEMIDDLE where sequenceid='" + sequenceID + "'";
			PreparedStatement ps = conn.prepareStatement(selectSql);
			ResultSet rs = ps.executeQuery(selectSql);
			PreparedStatement prest = null;
			while (rs.next()) {
				if (rs.getInt("count") != 0) {
					StringBuffer updateSql = new StringBuffer();
					updateSql.append("update SYS_ENCODINGRULESTABLEINTHEMIDDLE set initvalue=").append(sequence);
					updateSql.append(" where sequenceid='").append(sequenceID).append("'");
					prest = conn.prepareStatement(updateSql.toString(), ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					prest.executeUpdate();
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public javax.sql.DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(javax.sql.DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIdColumnName() {
		return idColumnName;
	}

	public void setIdColumnName(String idColumnName) {
		this.idColumnName = idColumnName;
	}

	public String getValueColumnName() {
		return valueColumnName;
	}

	public void setValueColumnName(String valueColumnName) {
		this.valueColumnName = valueColumnName;
	}

}
