package com.vix.core.persistence.jdbc.impl;

import java.util.Date;

import com.vix.core.constant.DatabaseConstant;
import com.vix.core.persistence.jdbc.IJdbcDao;
import com.vix.core.persistence.jdbc.ISpecificationDBService;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.mdm.item.entity.ItemCatalog;

public class SpecificationDBService implements ISpecificationDBService{
	
	private static final long serialVersionUID = -7524355023279202816L;

	@Override
	public boolean createTable(String dbType,ItemCatalog itemCatalog,String tableName) throws Exception {
		if(null != dbType && DatabaseConstant.ORACLE.equals(dbType)){
			IJdbcDao ijd = new JdbcOracleDao();
			if(!checkTableExist(DatabaseConstant.ORACLE,tableName)){
				String objectExpandTable = itemCatalog.getExpandTableName().toUpperCase();
				StringBuilder sqlBuilder = new StringBuilder("create table ");
				sqlBuilder.append(tableName);
				sqlBuilder.append(" (\"ID\" number(19,0) not null,");
				for(Specification s : itemCatalog.getSpecifications()){
					if(null != s && null != s.getCode() && !"".equals(s.getCode())){
						sqlBuilder.append("\"");
						sqlBuilder.append(s.getCode().toUpperCase());
						sqlBuilder.append("\" ");
						sqlBuilder.append("varchar2(50) null,");
						sqlBuilder.append("\"");
						sqlBuilder.append(s.getCode().toUpperCase()+"_CODE");
						sqlBuilder.append("\" ");
						sqlBuilder.append("varchar2(50) null,");
					}
				}
				sqlBuilder.append("\"OBJECTEXPAND_CODE\" number(19,0),");
				sqlBuilder.append("SKU varchar(100) null,");
				sqlBuilder.append("\"OBJECTEXPAND_COUNT\" number(19,0),");
				sqlBuilder.append("TENANTID varchar(50) null,");
				sqlBuilder.append(objectExpandTable);
				sqlBuilder.append("_ID  number(19,0),");
				sqlBuilder.append("primary key (\"ID\"),");
				sqlBuilder.append("constraint \"fk"+new Date().getTime()+"\" foreign key (\"PRODUCT_ID\") references \"EC_PRODUCT\" (\"ID\") enable)");
				return ijd.createExpandTable(sqlBuilder.toString());
			}
		}
		if(null != dbType && DatabaseConstant.MYSQL.equals(dbType)){
			IJdbcDao ijd = new JdbcMysqlDao();
			if(!checkTableExist(DatabaseConstant.MYSQL,tableName)){
				StringBuilder sqlBuilder = new StringBuilder("create table ");
				sqlBuilder.append(tableName);
				sqlBuilder.append("(ID varchar(255) NOT NULL ,");
				for(Specification s : itemCatalog.getSpecifications()){
					if(null != s && null != s.getCode() && !"".equals(s.getCode())){
						sqlBuilder.append(s.getCode().toUpperCase());
						sqlBuilder.append(" varchar(50) null,");
						sqlBuilder.append(s.getCode().toUpperCase()+"_CODE");
						sqlBuilder.append(" varchar(50) null,");
					}
				}
				sqlBuilder.append("SKU varchar(100) null,");
				sqlBuilder.append("INVENTORY_COUNT DOUBLE(11,2) null,");
				sqlBuilder.append("OBJECT_ID varchar(255) null,");
				sqlBuilder.append("OBJECT_TYPE varchar(50) null,");
				sqlBuilder.append("SPEC_CODE varchar(150) null,");
				sqlBuilder.append("MDM_ITEMCATALOG_ID varchar(255),");
				sqlBuilder.append("TENANTID varchar(100) null,");
				sqlBuilder.append("primary key (ID),");
				sqlBuilder.append("CONSTRAINT `fk"+new Date().getTime()+"` FOREIGN KEY (`");
				sqlBuilder.append("MDM_ITEMCATALOG_ID`) REFERENCES `MDM_ITEMCATALOG` ");
				sqlBuilder.append(" (`id`))");
				return ijd.createExpandTable(sqlBuilder.toString());
			}
		}
		if(null != dbType && DatabaseConstant.SQLSERVER.equals(dbType)){
			IJdbcDao ijd = new JdbcSqlServerDao();
			if(!checkTableExist(DatabaseConstant.SQLSERVER,tableName)){
				String objectExpandTable = itemCatalog.getExpandTableName().toUpperCase();
				StringBuilder sqlBuilder = new StringBuilder("create table ");
				sqlBuilder.append(tableName);
				sqlBuilder.append(" (\"ID\" number(19,0) not null,");
				for(Specification s : itemCatalog.getSpecifications()){
					if(null != s && null != s.getCode() && !"".equals(s.getCode())){
						sqlBuilder.append("\"");
						sqlBuilder.append(s.getCode().toUpperCase()+"_CODE");
						sqlBuilder.append("\" ");
						sqlBuilder.append("varchar2(50) null,");
						sqlBuilder.append("\"");
						sqlBuilder.append(s.getCode().toUpperCase());
						sqlBuilder.append("\" ");
						sqlBuilder.append("varchar2(50) null,");
					}
				}
				sqlBuilder.append("\"OBJECTEXPAND_CODE\" number(19,0),");
				sqlBuilder.append("SKU varchar2(100) null,");
				sqlBuilder.append("\"OBJECTEXPAND_COUNT\" number(19,0),");
				sqlBuilder.append(objectExpandTable);
				sqlBuilder.append("_ID  number(19,0),");
				sqlBuilder.append("primary key (\"ID\"),");
				sqlBuilder.append("constraint \"fk"+new Date().getTime()+"\" foreign key (\"PRODUCT_ID\") references \"EC_PRODUCT\" (\"ID\") enable)");
				return ijd.createExpandTable(sqlBuilder.toString());
			}
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
			return ijd.checkColumnExist(tableName,columnName.toUpperCase());
		}
		if(null != dbType && DatabaseConstant.MYSQL.equals(dbType)){
			IJdbcDao ijd = new JdbcMysqlDao();
			return ijd.checkColumnExist(tableName,columnName.toUpperCase());
		}
		if(null != dbType && DatabaseConstant.SQLSERVER.equals(dbType)){
			IJdbcDao ijd = new JdbcSqlServerDao();
			return ijd.checkColumnExist(tableName,columnName.toUpperCase());
		}
		return false;
	}
	
	/** 删除规格扩展表  */
	@Override
	public boolean dropTable(String dbType,String tableName) throws Exception{
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
	
	/** 添加商品扩展表字段  */
	@Override
	public boolean addTableField(String dbType,String tableName,String columnName) throws Exception{
		if(checkColumnExist(dbType,tableName,columnName)){
			return false;
		}
		if(null != dbType && DatabaseConstant.ORACLE.equals(dbType)){
			IJdbcDao ijd = new JdbcOracleDao();
			StringBuilder addColumn = new StringBuilder("alter table ");
 			addColumn.append(tableName);
			addColumn.append(" add ");
			addColumn.append("\"");
			addColumn.append(columnName.toUpperCase());
			addColumn.append("\" ");
			addColumn.append(" varchar2(50) null ");
			return ijd.addExpandTableField(addColumn.toString());
		}
		if(null != dbType && DatabaseConstant.MYSQL.equals(dbType)){
			IJdbcDao ijd = new JdbcMysqlDao();
			StringBuilder addColumn = new StringBuilder("alter table ");
 			addColumn.append(tableName);
 			addColumn.append(" add ");
			addColumn.append(columnName.toUpperCase());
			addColumn.append(" varchar(50) null ");
			return ijd.addExpandTableField(addColumn.toString());
		}
		if(null != dbType && DatabaseConstant.SQLSERVER.equals(dbType)){
			IJdbcDao ijd = new JdbcSqlServerDao();
			StringBuilder addColumn = new StringBuilder("alter table ");
			addColumn.append(tableName);
			addColumn.append(" add (");
			addColumn.append("\"");
			addColumn.append(columnName.toUpperCase());
			addColumn.append("\" ");
			addColumn.append(" varchar(50) null ");
			return ijd.addExpandTableField(addColumn.toString());
		}
		return false;
	}
	
	/** 删除商品扩展表字段  */
	@Override
	public boolean deleteTableField(String dbType,String tableName,String columnName) throws Exception{
		if(!checkColumnExist(dbType,tableName,columnName)){
			return false;
		}
		if(null != dbType && DatabaseConstant.ORACLE.equals(dbType)){
			IJdbcDao ijd = new JdbcOracleDao();
			StringBuilder dropColumn = new StringBuilder("alter table ");
 			dropColumn.append(tableName);
			dropColumn.append(" drop column ");
			dropColumn.append("\"");
			dropColumn.append(columnName);
			dropColumn.append("\" ");
			return ijd.deleteExpandTableField(dropColumn.toString());
		}
		if(null != dbType && DatabaseConstant.MYSQL.equals(dbType)){
			IJdbcDao ijd = new JdbcMysqlDao();
			StringBuilder dropColumn = new StringBuilder("alter table ");
 			dropColumn.append(tableName);
			dropColumn.append(" drop column ");
			dropColumn.append(columnName);
			dropColumn.append(" ");
			return ijd.deleteExpandTableField(dropColumn.toString());
		}
		if(null != dbType && DatabaseConstant.SQLSERVER.equals(dbType)){
			IJdbcDao ijd = new JdbcSqlServerDao();
			StringBuilder dropColumn = new StringBuilder("alter table ");
 			dropColumn.append(tableName);
			dropColumn.append(" drop column ");
			dropColumn.append("\"");
			dropColumn.append(columnName);
			dropColumn.append("\" ");
			return ijd.deleteExpandTableField(dropColumn.toString());
		}
		return false;
	}
}
