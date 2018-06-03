package com.vix.crm.analyse.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.crm.analyse.dao.IRfmAnalyseDao;
import com.vix.crm.analyse.service.IRfmAnalyseService;
import com.vix.crm.analyse.wraper.RfmWraper;

@Service("rfmAnalyseService")
public class RfmAnalyseServiceImpl extends BaseHibernateServiceImpl implements IRfmAnalyseService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IRfmAnalyseDao rfmAnalyseDao;

	@Override
	public List<List<RfmWraper>> findRfmByDate(String year,String tenlantId) throws Exception {
		Long totalCount = 0l;
		List<List<RfmWraper>> list = new ArrayList<List<RfmWraper>>();
		StringBuilder sqlBuilder = new StringBuilder("SELECT count(TEMP.id) from (");
		sqlBuilder.append("select ca.id,ca.name,count(so.id) as orderCount,so.billDate from crm_customeraccount ca LEFT JOIN sale_salesorder so on ca.ID = so.customerAccount_id ");
		sqlBuilder.append("where ca.ISTEMP != '1' and ca.ISDELETED != '1' and ca.TENANTID = '"+tenlantId+"' and so.ISTEMP != '1' and so.ISDELETED != '1' and so.TENANTID = 'T00002' group by ca.ID");
		sqlBuilder.append(") temp ");
		//时间条件
		StringBuilder oneRBuilder = new StringBuilder(" where temp.billDate > '");
		oneRBuilder.append(year);
		oneRBuilder.append("-01-01 00:00:01' and  temp.billDate <='");
		oneRBuilder.append(year);
		oneRBuilder.append("-01-31 23:59:59'");
		StringBuilder twoRBuilder = new StringBuilder(" where temp.billDate > '");
		twoRBuilder.append(year);
		twoRBuilder.append("-02-01 00:00:01' and  temp.billDate <= '");
		twoRBuilder.append(year);
		twoRBuilder.append("-03-31 23:59:59'");
		StringBuilder threeRBuilder = new StringBuilder(" where temp.billDate > '");
		threeRBuilder.append(year);
		threeRBuilder.append("-03-01 00:00:01' and  temp.billDate <= '");
		threeRBuilder.append(year);
		threeRBuilder.append("-06-30 23:59:59'");
		StringBuilder fourRBuilder = new StringBuilder(" where temp.billDate > '");
		fourRBuilder.append(year);
		fourRBuilder.append("-07-01 00:00:01' and  temp.billDate <= '");
		fourRBuilder.append(year);
		fourRBuilder.append("-12-31 23:59:59'");
		StringBuilder fiveRBuilder = new StringBuilder(" where temp.billDate > '");
		Long beforeYear = Long.parseLong(year);
		fiveRBuilder.append("2000-01-01 00:00:01' and  temp.billDate <= '");
		fiveRBuilder.append(beforeYear-1);
		fiveRBuilder.append("-12-31 23:59:59'");
		StringBuilder sixRBuilder = new StringBuilder(" where temp.billDate > '");
		sixRBuilder.append("2000-01-01 00:00:01' and  temp.billDate <= '");
		sixRBuilder.append(year);
		sixRBuilder.append("-12-31 23:59:59'");
		totalCount =  rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + sixRBuilder.toString() + " and temp.orderCount <= 5 ");
		for(int i=1;i<=6;i++){
			List<RfmWraper> l = new ArrayList<RfmWraper>();
			if(i== 1){
				for(int j=1;j<=6;j++){
					RfmWraper rw = new RfmWraper();
					long count = 0l;
					if(j<6){
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + oneRBuilder.toString() + " and temp.orderCount = " + j);
					}else{
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + oneRBuilder.toString() + " and temp.orderCount <= 5 ");
					}
					rw.setCustomerAccountCount(count);
					if(totalCount > 0){
						rw.setCustomerAccountPersent((count + 0d) / totalCount);
					}
					l.add(rw);
				}
			}
			if(i== 2){
				for(int j=1;j<=6;j++){
					RfmWraper rw = new RfmWraper();
					long count = 0l;
					if(j<6){
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + twoRBuilder.toString() + " and temp.orderCount = " + j);
					}else{
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + twoRBuilder.toString() + " and temp.orderCount <= 5 ");
					}
					rw.setCustomerAccountCount(count);
					if(totalCount > 0){
						rw.setCustomerAccountPersent((count + 0d) / totalCount);
					}
					l.add(rw);
				}
			}
			if(i== 3){
				for(int j=1;j<=6;j++){
					RfmWraper rw = new RfmWraper();
					long count = 0l;
					if(j<6){
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + threeRBuilder.toString() + " and temp.orderCount = " + j);
					}else{
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + threeRBuilder.toString() + " and temp.orderCount <= 5 ");
					}
					rw.setCustomerAccountCount(count);
					if(totalCount > 0){
						rw.setCustomerAccountPersent((count + 0d) / totalCount);
					}
					l.add(rw);
				}
			}
			if(i== 4){
				for(int j=1;j<=6;j++){
					RfmWraper rw = new RfmWraper();
					long count = 0l;
					if(j<6){
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + fourRBuilder.toString() + " and temp.orderCount = " + j);
					}else{
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + fourRBuilder.toString() + " and temp.orderCount <= 5 ");
					}
					rw.setCustomerAccountCount(count);
					if(totalCount > 0){
						rw.setCustomerAccountPersent((count + 0d) / totalCount);
					}
					l.add(rw);
				}
			}
			if(i== 5){
				for(int j=1;j<=6;j++){
					RfmWraper rw = new RfmWraper();
					long count = 0l;
					if(j<6){
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + fiveRBuilder.toString() + " and temp.orderCount = " + j);
					}else{
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + fiveRBuilder.toString() + " and temp.orderCount <= 5 ");
					}
					rw.setCustomerAccountCount(count);
					if(totalCount > 0){
						rw.setCustomerAccountPersent((count + 0d) / totalCount);
					}
					l.add(rw);
				}
			}
			if(i== 6){
				for(int j=1;j<=6;j++){
					RfmWraper rw = new RfmWraper();
					long count = 0l;
					if(j<6){
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + sixRBuilder.toString() + " and temp.orderCount = " + j);
					}else{
						count = rfmAnalyseDao.findOrderCountBySql(sqlBuilder.toString() + sixRBuilder.toString() + " and temp.orderCount <= 5 ");
					}
					rw.setCustomerAccountCount(count);
					if(totalCount > 0){
						rw.setCustomerAccountPersent((count + 0d) / totalCount);
					}
					l.add(rw);
				}
			}
			list.add(l);
		}
		return list;
	}
}