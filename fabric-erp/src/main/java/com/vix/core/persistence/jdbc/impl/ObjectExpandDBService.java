package com.vix.core.persistence.jdbc.impl;

import com.vix.core.constant.DatabaseConstant;
import com.vix.core.persistence.jdbc.IJdbcDao;
import com.vix.core.persistence.jdbc.IObjectExpandDBService;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;

public class ObjectExpandDBService implements IObjectExpandDBService{
	private static final long serialVersionUID = -7524355023279202816L;

	@Override
	public boolean createTable(String dbType,ObjectExpand objectExpand) throws Exception {
		
		if(null != dbType && DatabaseConstant.ORACLE.equals(dbType)){
			IJdbcDao ijd = new JdbcOracleDao();
			StringBuilder sqlBuilder = new StringBuilder("create table ");
			sqlBuilder.append(objectExpand.getExpandTableName());
			sqlBuilder.append("(\"ID\" number(19,0) not null,");
			for(ObjectExpandField ef : objectExpand.getObjectExpandFields()){
				sqlBuilder.append("\"");
				sqlBuilder.append(ef.getFieldName());
				sqlBuilder.append("\" ");
				if("date".equals(ef.getFieldType())){
					sqlBuilder.append("timestamp(6),");
				}else{
					sqlBuilder.append("varchar2(");
					sqlBuilder.append(ef.getLength());
					sqlBuilder.append(") ");
					if("1".equals(ef.getIsRequired())){
						sqlBuilder.append("not null,");
					}else{
						sqlBuilder.append("null,");
					}
				}
			}
			sqlBuilder.append("primary key (\"ID\"))");
			return ijd.createExpandTable(sqlBuilder.toString());
		}
		if(null != dbType && DatabaseConstant.MYSQL.equals(dbType)){
			IJdbcDao ijd = new JdbcMysqlDao();
			StringBuilder sqlBuilder = new StringBuilder("create table ");
			sqlBuilder.append(objectExpand.getExpandTableName());
			sqlBuilder.append("(ID varchar(255) NOT NULL ,");
			for(ObjectExpandField ef : objectExpand.getObjectExpandFields()){
				sqlBuilder.append(ef.getFieldName());
				sqlBuilder.append(" ");
				if("date".equals(ef.getFieldType())){
					sqlBuilder.append("DATETIME ,");
				}else{
					sqlBuilder.append("varchar(");
					sqlBuilder.append(ef.getLength());
					sqlBuilder.append(") ");
					if("1".equals(ef.getIsRequired())){
						sqlBuilder.append("not null,");
					}else{
						sqlBuilder.append("null,");
					}
				}
			}
			sqlBuilder.append("PRIMARY KEY (ID))");
			return ijd.createExpandTable(sqlBuilder.toString());
		}
		if(null != dbType && DatabaseConstant.SQLSERVER.equals(dbType)){
			IJdbcDao ijd = new JdbcSqlServerDao();
			StringBuilder sqlBuilder = new StringBuilder("create table ");
			sqlBuilder.append(objectExpand.getExpandTableName());
			sqlBuilder.append("(\"ID\" number(19,0) not null,");
			for(ObjectExpandField ef : objectExpand.getObjectExpandFields()){
				sqlBuilder.append("\"");
				sqlBuilder.append(ef.getFieldName());
				sqlBuilder.append("\" ");
				if("date".equals(ef.getFieldType())){
					sqlBuilder.append("timestamp(6),");
				}else{
					sqlBuilder.append("varchar2(");
					sqlBuilder.append(ef.getLength());
					sqlBuilder.append(") ");
					if("1".equals(ef.getIsRequired())){
						sqlBuilder.append("not null,");
					}else{
						sqlBuilder.append("null,");
					}
				}
			}
			sqlBuilder.append("primary key (\"ID\"))");
			return ijd.createExpandTable(sqlBuilder.toString());
		}
		return false;
	}
	
	@Override
	public <T> boolean createForeignKey(String dbType,String mainTable,String subTable) throws Exception{
		if(null != dbType && DatabaseConstant.ORACLE.equals(dbType)){
			IJdbcDao ijd = new JdbcOracleDao();
			return ijd.createForeignKey(mainTable, subTable);
		}
		if(null != dbType && DatabaseConstant.MYSQL.equals(dbType)){
			IJdbcDao ijd = new JdbcMysqlDao();
			return ijd.createForeignKey(mainTable, subTable);
		}
		if(null != dbType && DatabaseConstant.SQLSERVER.equals(dbType)){
			IJdbcDao ijd = new JdbcSqlServerDao();
			return ijd.createForeignKey(mainTable, subTable);
		}
		return false;
	}
	
	/** 检查表是否存在 */
	@Override
	public boolean checkTableExist(String dbType,String tableName) throws Exception{
		if(null != dbType && DatabaseConstant.ORACLE.equals(dbType)){
			IJdbcDao ijd = new JdbcOracleDao();
			return ijd.checkTableExist(tableName);
		}
		if(null != dbType && DatabaseConstant.MYSQL.equals(dbType)){
			IJdbcDao ijd = new JdbcMysqlDao();
			return ijd.checkTableExist(tableName);
		}
		if(null != dbType && DatabaseConstant.SQLSERVER.equals(dbType)){
			IJdbcDao ijd = new JdbcSqlServerDao();
			return ijd.checkTableExist(tableName);
		}
		return false;
	}
	
	/** 检查表中列是否存在 */
	@Override
	public boolean checkColumnExist(String dbType,String tableName,String columnName) throws Exception{
		if(null != dbType && DatabaseConstant.ORACLE.equals(dbType)){
			IJdbcDao ijd = new JdbcOracleDao();
			return ijd.checkColumnExist(tableName,columnName);
		}
		if(null != dbType && DatabaseConstant.MYSQL.equals(dbType)){
			IJdbcDao ijd = new JdbcMysqlDao();
			return ijd.checkColumnExist(tableName,columnName);
		}
		if(null != dbType && DatabaseConstant.SQLSERVER.equals(dbType)){
			IJdbcDao ijd = new JdbcSqlServerDao();
			return ijd.checkColumnExist(tableName,columnName);
		}
		return false;
	}
	
	/** 删除扩展表  */
	@Override
	public boolean dropExpandTable(String dbType,String tableName) throws Exception{
		if(null != dbType && DatabaseConstant.ORACLE.equals(dbType)){
			IJdbcDao ijd = new JdbcOracleDao();
			return ijd.dropExpandTable(tableName);
		}
		if(null != dbType && DatabaseConstant.MYSQL.equals(dbType)){
			IJdbcDao ijd = new JdbcMysqlDao();
			return ijd.dropExpandTable(tableName);
		}
		if(null != dbType && DatabaseConstant.SQLSERVER.equals(dbType)){
			IJdbcDao ijd = new JdbcSqlServerDao();
			return ijd.dropExpandTable(tableName);
		}
		return false;
	}
	
	/** 添加扩展表字段  */
	@Override
	public boolean addExpandTableField(String dbType,ObjectExpandField objectExpand) throws Exception{
		if(null != dbType && DatabaseConstant.ORACLE.equals(dbType)){
			IJdbcDao ijd = new JdbcOracleDao();
			StringBuilder addColumn = new StringBuilder("alter table ");
 			addColumn.append(objectExpand.getExpandTableName());
			addColumn.append(" add (");
			addColumn.append("\"");
			addColumn.append(objectExpand.getFieldName());
			addColumn.append("\" ");
			if("date".equals(objectExpand.getFieldType())){
				addColumn.append("timestamp (6) ");
				if("1".equals(objectExpand.getIsRequired())){
					addColumn.append(" not null");
				}else{
					addColumn.append(" null");
				}
			}else{
				addColumn.append(" varchar2(");
				addColumn.append(objectExpand.getLength());
				addColumn.append(") ");
				if("1".equals(objectExpand.getIsRequired())){
					addColumn.append(" not null");
				}else{
					addColumn.append(" null");
				}
			} 
			addColumn.append(")");
			return ijd.addExpandTableField(addColumn.toString());
		}
		if(null != dbType && DatabaseConstant.MYSQL.equals(dbType)){
			IJdbcDao ijd = new JdbcMysqlDao();
			StringBuilder addColumn = new StringBuilder("alter table ");
 			addColumn.append(objectExpand.getExpandTableName());
			addColumn.append(" add (");
			addColumn.append(objectExpand.getFieldName());
			addColumn.append(" ");
			if("date".equals(objectExpand.getFieldType())){
				addColumn.append("DATETIME ");
				if("1".equals(objectExpand.getIsRequired())){
					addColumn.append(" not null");
				}else{
					addColumn.append(" null");
				}
			}else{
				addColumn.append(" varchar(");
				addColumn.append(objectExpand.getLength());
				addColumn.append(") ");
				if("1".equals(objectExpand.getIsRequired())){
					addColumn.append(" not null");
				}else{
					addColumn.append(" null");
				}
			} 
			addColumn.append(")");
			return ijd.addExpandTableField(addColumn.toString());
		}
		if(null != dbType && DatabaseConstant.SQLSERVER.equals(dbType)){
			IJdbcDao ijd = new JdbcSqlServerDao();
			StringBuilder addColumn = new StringBuilder("alter table ");
 			addColumn.append(objectExpand.getExpandTableName());
			addColumn.append(" add (");
			addColumn.append("\"");
			addColumn.append(objectExpand.getFieldName());
			addColumn.append("\" ");
			if("date".equals(objectExpand.getFieldType())){
				addColumn.append("timestamp (6) ");
				if("1".equals(objectExpand.getIsRequired())){
					addColumn.append(" not null");
				}else{
					addColumn.append(" null");
				}
			}else{
				addColumn.append(" varchar2(");
				addColumn.append(objectExpand.getLength());
				addColumn.append(") ");
				if("1".equals(objectExpand.getIsRequired())){
					addColumn.append(" not null");
				}else{
					addColumn.append(" null");
				}
			} 
			addColumn.append(")");
			return ijd.addExpandTableField(addColumn.toString());
		}
		return false;
	}
	
	/** 删除扩展表字段  */
	@Override
	public boolean deleteExpandTableField(String dbType,ObjectExpandField objectExpand) throws Exception{
		if(null != dbType && DatabaseConstant.ORACLE.equals(dbType)){
			IJdbcDao ijd = new JdbcOracleDao();
			StringBuilder dropColumn = new StringBuilder("alter table ");
 			dropColumn.append(objectExpand.getExpandTableName());
			dropColumn.append(" drop column ");
			dropColumn.append("\"");
			dropColumn.append(objectExpand.getFieldName());
			dropColumn.append("\" ");
			return ijd.deleteExpandTableField(dropColumn.toString());
		}
		if(null != dbType && DatabaseConstant.MYSQL.equals(dbType)){
			IJdbcDao ijd = new JdbcMysqlDao();
			StringBuilder dropColumn = new StringBuilder("alter table ");
 			dropColumn.append(objectExpand.getExpandTableName());
			dropColumn.append(" drop column ");
			dropColumn.append(objectExpand.getFieldName());
			dropColumn.append(" ");
			return ijd.deleteExpandTableField(dropColumn.toString());
		}
		if(null != dbType && DatabaseConstant.SQLSERVER.equals(dbType)){
			IJdbcDao ijd = new JdbcSqlServerDao();
			StringBuilder dropColumn = new StringBuilder("alter table ");
 			dropColumn.append(objectExpand.getExpandTableName());
			dropColumn.append(" drop column ");
			dropColumn.append("\"");
			dropColumn.append(objectExpand.getFieldName());
			dropColumn.append("\" ");
			return ijd.deleteExpandTableField(dropColumn.toString());
		}
		return false;
	}
}
