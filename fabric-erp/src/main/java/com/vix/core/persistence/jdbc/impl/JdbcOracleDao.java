package com.vix.core.persistence.jdbc.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vix.core.persistence.jdbc.IJdbcDao;
import com.vix.core.utils.PropertyConfigLoader;
 
/** 通用DAO */
public class JdbcOracleDao implements IJdbcDao{
 
	private static final long serialVersionUID = 1L;

	protected static Logger logger = LoggerFactory.getLogger(JdbcOracleDao.class);
 
	/** 数据库连接 */
	private Connection con = null;
	private void initConnection(){
	    try {
	        //加载数据库驱动
	        Class.forName(PropertyConfigLoader.dbDriver);
	        //设置访问属性
	        Properties prop = new Properties();
	        prop.setProperty("user",PropertyConfigLoader.userName);
	        prop.setProperty("password",PropertyConfigLoader.userPwd);
	        //打开数据库连接并连接到指定的URL
	        con = DriverManager.getConnection(PropertyConfigLoader.dbUrl, prop);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public boolean createExpandTable(String createTableString) throws Exception {
		boolean executeResult = true;
		PreparedStatement sm = null;
		try {
			if(null == con || con.isClosed()){
				initConnection();
			}
            sm = con.prepareStatement(createTableString);
            sm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            executeResult = false;
            if(null != sm && !sm.isClosed()){
            	sm.close();
            	sm = null;
            }
            if(null != con && !con.isClosed()){
            	con.close();
            }
        }finally{
        	try{
        		con.close();
        		con = null;
        	}catch(Exception ex){
        		ex.printStackTrace();
        		if(null != sm && !sm.isClosed()){
        			sm.close();
        			sm = null;
        		}
        		if(null != con && !con.isClosed()){
        			con.close();
        			con = null;
        		}
        	}
        }
		return executeResult;
	}
	
	@Override
	public boolean createForeignKey(String mainTable,String subTable) throws Exception{
		/** 添加外键列 */
		StringBuilder addColumn = new StringBuilder("alter table ");
		addColumn.append(subTable);
		addColumn.append(" add (");
		addColumn.append("\"");
		addColumn.append(mainTable+"_ID");
		addColumn.append("\" number(19,0) not null)");
		addExpandTableField(addColumn.toString());
		/** 添加外键 */
		StringBuilder sqlBuilder = new StringBuilder("alter table \"");
		sqlBuilder.append(subTable);
		sqlBuilder.append("\" add constraint \"fk_");
		sqlBuilder.append(new Date().getTime());
		sqlBuilder.append("\"  FOREIGN KEY (\"");
		sqlBuilder.append(mainTable);
		sqlBuilder.append("_id\") REFERENCES \"");
		sqlBuilder.append(mainTable);
		sqlBuilder.append("\" (\"id\")");
		boolean executeResult = true;
		PreparedStatement sm = null;
		try {
			if(null == con || con.isClosed()){
				initConnection();
			}
            sm = con.prepareStatement(sqlBuilder.toString());
            sm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            executeResult = false;
            if(null != sm && !sm.isClosed()){
            	sm.close();
            	sm = null;
            }
            if(null != con && !con.isClosed()){
            	con.close();
            }
        }finally{
        	try{
        		con.close();
        		con = null;
        	}catch(Exception ex){
        		ex.printStackTrace();
        		if(null != sm && !sm.isClosed()){
        			sm.close();
        			sm = null;
        		}
        		if(null != con && !con.isClosed()){
        			con.close();
        			con = null;
        		}
        	}
        }
		return executeResult;
	}
	
	@Override
	public boolean checkTableExist(String tableName) throws Exception{
		try{
			if(null == con || con.isClosed()){
				initConnection();
			}
			DatabaseMetaData meta = con.getMetaData();
	        ResultSet rsTables = meta.getTables(PropertyConfigLoader.userName,null, tableName,new String[] { "TABLE" });
	        while(rsTables.next()){
	        	if(rsTables.getString("TABLE_NAME").equals(tableName)){
	        		rsTables.close();
	       			return true;
	        	}
	        }
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
        return false;
	}
	
	/** 修改商品扩展表列名  */
	@Override
	public boolean changeTableColumnName(String tableName,String oldColumnName,String newColumnName) throws Exception{
		boolean executeResult = false;
		PreparedStatement sm = null;
		try {
			if(null == con || con.isClosed()){
				initConnection();
			}
			StringBuilder renameColumn = new StringBuilder("alter table ");
			renameColumn.append(tableName);
			renameColumn.append(" rename column ");
			renameColumn.append(oldColumnName);
			renameColumn.append(" to ");
			renameColumn.append(newColumnName);
			sm = con.prepareStatement(renameColumn.toString());
			sm.execute();
			sm.close();
			sm = null;
			executeResult = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            executeResult = false;
            if(null != sm && !sm.isClosed()){
            	sm.close();
            	sm = null;
            }
            if(null != con && !con.isClosed()){
            	con.close();
            }
        }finally{
        	try{
        		con.close();
        		con = null;
        	}catch(Exception ex){
        		ex.printStackTrace();
        		if(null != sm && !sm.isClosed()){
        			sm.close();
        			sm = null;
        		}
        		if(null != con && !con.isClosed()){
        			con.close();
        			con = null;
        		}
        	}
		}
		return executeResult;
	}
	
	
	/** 删除商品扩展表  */
	@Override
	public boolean dropExpandTable(String tableName) throws Exception{
		boolean executeResult = false;
		PreparedStatement sm = null;
		try {
			if(null == con || con.isClosed()){
				initConnection();
			}
			StringBuilder dropTable = new StringBuilder("drop table ");
			dropTable.append(tableName);
			sm = con.prepareStatement(dropTable.toString());
			sm.execute();
			sm.close();
			sm = null;
			executeResult = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            executeResult = false;
            if(null != sm && !sm.isClosed()){
            	sm.close();
            	sm = null;
            }
            if(null != con && !con.isClosed()){
            	con.close();
            }
        }finally{
        	try{
        		con.close();
        		con = null;
        	}catch(Exception ex){
        		ex.printStackTrace();
        		if(null != sm && !sm.isClosed()){
        			sm.close();
        			sm = null;
        		}
        		if(null != con && !con.isClosed()){
        			con.close();
        			con = null;
        		}
        	}
        }
		return executeResult;
	}
	
	/** 添加商品扩展表字段  */
	@Override
	public boolean addExpandTableField(String addColumn) throws Exception{
		boolean executeResult = false;
		PreparedStatement sm = null;
		try {
			if(null == con || con.isClosed()){
				initConnection();
			}
			sm = con.prepareStatement(addColumn);
			sm.execute();
			sm.close();
			sm = null;
			executeResult = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            executeResult = false;
            if(null != sm && !sm.isClosed()){
            	sm.close();
            	sm = null;
            }
            if(null != con && !con.isClosed()){
            	con.close();
            }
        }finally{
        	try{
        		con.close();
        		con = null;
        	}catch(Exception ex){
        		ex.printStackTrace();
        		if(null != sm && !sm.isClosed()){
        			sm.close();
        			sm = null;
        		}
        		if(null != con && !con.isClosed()){
        			con.close();
        			con = null;
        		}
        	}
		}
		return executeResult;
	}
	
	@Override
	public boolean checkColumnExist(String tableName,String column){
		try{
			if(null == con || con.isClosed()){
				initConnection();
			}
			DatabaseMetaData meta = con.getMetaData();
			ResultSet columnRs = meta.getColumns(PropertyConfigLoader.userName, null,tableName, null);
			while(columnRs.next()){
				String columnName = columnRs.getString("COLUMN_NAME");
				if(columnName.equals(column)){
					return true;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	/** 删除商品扩展表字段  */
	@Override
	public boolean deleteExpandTableField(String dropColumn) throws Exception{
		boolean executeResult = false;
		PreparedStatement sm = null;
		try {
			if(null == con || con.isClosed()){
				initConnection();
			}
			sm = con.prepareStatement(dropColumn);
			sm.execute();
			sm.close();
			sm = null;
			executeResult = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            executeResult = false;
            if(null != sm && !sm.isClosed()){
            	sm.close();
            	sm = null;
            }
            if(null != con && !con.isClosed()){
            	con.close();
            }
        }finally{
        	try{
        		con.close();
        		con = null;
        	}catch(Exception ex){
        		ex.printStackTrace();
        		if(null != sm && !sm.isClosed()){
        			sm.close();
        			sm = null;
        		}
        		if(null != con && !con.isClosed()){
        			con.close();
        			con = null;
        		}
        	}
		}
		return executeResult;
	}
}