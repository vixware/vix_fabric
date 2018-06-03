package com.vix.oa.personaloffice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.vix.common.properties.util.MapBean;
import com.vix.common.properties.util.MapBeanInt;
import com.vix.common.properties.util.MyTool;
import com.vix.common.properties.util.TableBeanE;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.chain.action.RequireGoodsOrderVo;
import com.vix.nvix.common.base.dao.IVixntBaseDao;
import com.vix.nvix.common.base.hql.AttendanceHqlProvider;
import com.vix.oa.personaloffice.dao.IQueryDataDao;
import com.vix.oa.personaloffice.service.IQueryDataService;


@Service("queryDataService")
public class QueryDataServiceImpl extends BaseHibernateServiceImpl implements IQueryDataService {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IVixntBaseDao vixntBaseDao;
	@Resource(name = "attendanceHqlProvider")
	private AttendanceHqlProvider attendanceHqlProvider;
	@Autowired
	private IQueryDataDao queryDataDao;   //IQueryDataDao
	
	@Override    /** ‘工作台’相关数据块   查询返回相关json   **/  
	public Map<String, Object> findnvixContentJsonA(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		
		if((hsMap.get("controlSQL")+"").toString().equals("NTtotalBusinessIncome")){// controlSQL:'NTtotalBusinessIncome'   工作台>总营业收入       numRt：返回数字  用 NT
			StringBuilder hql = new StringBuilder();
			hql.append(" SELECT IFNULL(sum(payAmount),0),'1' FROM ");
			hql.append(" DRP_REQUIREGOODSORDER ");//查询自 订单，payTime付款时间  payAmount 实付金额  
			hql.append(" WHERE ");
			@SuppressWarnings("unchecked")  
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append("     payTime >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" and isTemp != \'1\'  and type = '1' ");//#交易类型(POS会员消费1;门店消费2;)
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		else if((hsMap.get("controlSQL")+"").toString().equals("NTtotalRecharge")){// {controlSQL:'NTtotalRecharge'},//查询‘总储值收入’ numRt：返回数字  用 NT 代表，  NTtotalRecharge 
			StringBuilder hql = new StringBuilder();
		hql.append(" SELECT "); 
		hql.append(" IFNULL(sum(payMoney),0),'1' ");
		hql.append(" FROM ");
		hql.append(" CRM_RECHARGERECORD ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     payDate >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and payDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" and (isTemp!='1') ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		else if((hsMap.get("controlSQL")+"").toString().equals("NTnoPaymentOrder")){//{controlSQL:'NTnoPaymentOrder'},//numRt返回数字  用 NT代表NTnoPaymentOrder未支付订单 
			StringBuilder hql = new StringBuilder();
		hql.append(" SELECT "); 
		hql.append(" count(*),'1' ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER ");//  #CREATETIME创建时间，payTime付款时间
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		hql.append(" isTemp != \'1\' ");  
		hql.append(" and payTime is null "); 
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		else if((hsMap.get("controlSQL")+"").toString().equals("NTtotalDiscountMoney")){//{controlSQL:'NTtotalDiscountMoney'},// numRt：返回数字  用 NT 代表，  NTtotalDiscountMoney总折扣金额
			StringBuilder hql = new StringBuilder();
		hql.append(" SELECT "); 
		hql.append(" IFNULL(sum(many.discountFee),0),'1' ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDERITEM many, DRP_REQUIREGOODSORDER toone ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     toone.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and toone.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
	hql.append(" toone.isTemp != \'1\' ");
		hql.append(" and many.REQUIREGOODSORDER_ID = toone.id "); 
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		else if((hsMap.get("controlSQL")+"").toString().equals("NTregisterMemberNum")){//registerMemberNum 今日注册会员人数{controlSQL:'NTregisterMemberNum'}
			StringBuilder hql = new StringBuilder();
		hql.append(" SELECT "); 
		hql.append(" count(*),'1' ");
		hql.append(" FROM ");
		hql.append(" CRM_CUSTOMERACCOUNT ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		else if((hsMap.get("controlSQL")+"").toString().equals("NTtodayOrdersNumBlock")){// NTtodayOrdersNumBlock 今日订单数目，付款或者不付款都查询  Block数据块显示
			StringBuilder hql = new StringBuilder();
			hql.append(" SELECT "); 
			hql.append(" count(*),'1' ");
			hql.append(" FROM ");
			hql.append(" DRP_REQUIREGOODSORDER ");//查询自 订单，payTime付款时间  payAmount 实付金额
			hql.append(" WHERE ");
			@SuppressWarnings("unchecked")     
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append("     CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}
			hql.append(" and isTemp !=\'1\' ");
			hql.append(" and type = '1' ");//#交易类型(POS会员消费1;门店消费2;) 
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		else if((hsMap.get("controlSQL")+"").toString().equals("customerLossRiskEAllCustomer")){//customerLossRiskEAllCustomer | 查询全部客户（会员）人数
			StringBuilder hql = new StringBuilder();
			hql.append(" SELECT "); 
			hql.append(" count(*),'1' ");
			hql.append(" FROM ");
			hql.append(" CRM_CUSTOMERACCOUNT where 1=1 ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}	
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	
	@Override    /** ‘工作台’相关数据块   查询返回相关json B  **/  
	public Map<String, Object> findnvixContentJsonB(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		if((hsMap.get("controlSQL")+"").toString().equals("NTtodayConsumptionMoney")){//{controlSQL:'NTtodayConsumptionMoney'},//numRt：返回数字  用 NT代表，NTtodayConsumptionMoney 今日‘总消费金额’
			StringBuffer hql = new StringBuffer();
		hql.append(" SELECT "); 
		hql.append(" IFNULL(sum(payAmount),0),'1' ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER ");//查询自 订单，payTime付款时间  payAmount 实付金额  
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")  
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" and isTemp != \'1\' ");
		hql.append(" and type = '1' ");//#交易类型(POS会员消费1;门店消费2;) 
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		else if((hsMap.get("controlSQL")+"").toString().equals("NTtodayAddCardNum")){//todayAddCardNum 今日新增会员卡
			StringBuilder hql = new StringBuilder();
		hql.append(" SELECT  count(*),'1' FROM CRM_CUSTOMERACCOUNTCILP many "); 
		hql.append(" inner join CRM_CUSTOMERACCOUNT toone on many.customerAccount_id = toone.id ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");
		}
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append(" and many.CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1)))); 
		hql.append(" and ( many.ISTEMP != '1') ");//会员卡办卡要考虑‘不卡问题’，当CRM_CUSTOMERACCOUNTCILP的ISTEMP ！=‘1’时为正常的卡
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		else if((hsMap.get("controlSQL")+"").toString().equals("NTtodayIntoStoreCustomers")){
			StringBuilder hql = new StringBuilder();
		hql.append(" SELECT "); //{controlSQL:'NTtodayIntoStoreCustomers'},//NTtodayIntoStoreCustomers 今日进店客户，(充值的+下订单) (合并两个子查询结果，然后统一去重会员ID)
		hql.append(" count(DISTINCT(aa)),'1' ");
		hql.append(" FROM ");
		hql.append(" (  ");
		hql.append(" SELECT DISTINCT(tooneBB.id) as aa ");
		hql.append(" FROM CRM_CUSTOMERACCOUNT tooneBB ");
		hql.append(" inner JOIN  DRP_REQUIREGOODSORDER tooneAA on tooneAA.customerAccount_id = tooneBB.id and tooneAA.isTemp != \'1\' ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tooneAA.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and tooneAA.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and tooneAA.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append(" and tooneAA.CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));//CREATETIME 订单创建日期
		hql.append(" and tooneAA.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		hql.append(" and tooneAA.type = '1' ");//#交易类型(POS会员消费1;门店消费2;) 
		hql.append(" union all ");
		hql.append(" SELECT ");
		hql.append(" DISTINCT(tooneB.id) as aa ");
		hql.append(" FROM ");
		hql.append(" CRM_CUSTOMERACCOUNT tooneB ");
		hql.append(" inner JOIN  CRM_CUSTOMERACCOUNTCILP tooneA on tooneA.customerAccount_id = tooneB.id and ( tooneA.ISTEMP != '1') ");
		hql.append(" inner JOIN  CRM_RECHARGERECORD many on many.customerAccountClip_id = tooneA.id ");
		hql.append(" and (many.isTemp!='1') ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" and many.payDate >= "+ MyTool.StringUseToSql(object.get(0)));//payDate 充值日期
		hql.append(" and many.payDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		hql.append(" )C ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	@Override    /** 查询 工作台  '今日销售情况'   **/  
	public Map<String, Object> findnvixContentJsonC(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		ArrayList<String>  stringXaxis = new ArrayList<String>();
		ArrayList<String>  numberYaxis = new ArrayList<String>();
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("JVtodaySalesMoneyTop")){//jsonKeyValueForView：为视图view返回Json键值对; 用 JV 代表;  查询  '今日客流量排行' 客流量  todayPassengerFlowTop
			StringBuffer hql = new StringBuffer();//JVtodaySalesMoneyTop 先查今日‘销售金额Top’
		hql.append(" select A.* from ( ");
		hql.append(" SELECT ");
		hql.append(" DATE_FORMAT(payTime, "+MyTool.getValueToStrByHashMapKey(hsMap,"myDATE_FORMAT")+") AS ersiShi,IFNULL(sum(payAmount),0) as qian ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		 hql.append(" and isTemp !=\'1\' ");
		hql.append(" GROUP BY  ersiShi ");
		hql.append(" ORDER BY ersiShi asc ");
		hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					stringXaxis.add(ttList.get(x)[0].toString());
					numberYaxis.add(ttList.get(x)[1].toString());
				}
			}
		} 
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("JVtodaySalesOrdernumTop")){//查询相关销售订单数
			StringBuffer hql = new StringBuffer();
			numberYaxis.add("0");
		hql.append(" select A.* from ( ");
		hql.append(" SELECT ");
		hql.append(" DATE_FORMAT(payTime, "+MyTool.getValueToStrByHashMapKey(hsMap,"myDATE_FORMAT")+") AS ersiShi,count(*) as sl ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		 hql.append(" and isTemp !=\'1\' ");
		hql.append(" GROUP BY  ersiShi ");
		hql.append(" )A where A.ersiShi = ("+MyTool.getValueToStrByHashMapKey(hsMap,"twoTimesQuery")+")  ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					numberYaxis.set(0, ttList.get(x)[1].toString());
				}
			}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("JVtodaySalesMoneyTopSdwv")){//按小时查询今日销售金额|JVtodaySalesMoneyTopSdwv|Sdwv是4个随机字母
			StringBuffer hql = new StringBuffer();//JVtodaySalesMoneyTop 先查今日‘销售金额Top’
		hql.append(" SELECT ");
		hql.append(" DATE_FORMAT(payTime, "+MyTool.getValueToStrByHashMapKey(hsMap,"myDATE_FORMAT")+") AS ersiShi,IFNULL(sum(payAmount),0) as qian ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" and isTemp !=\'1\' ");
		hql.append(" and type = '1' ");//#交易类型(POS会员消费1;门店消费2;) 
		hql.append(" GROUP BY  ersiShi ");
		hql.append(" ORDER BY ersiShi asc ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
					 
			ArrayList<MapBean> hour24Arr = MyTool.getMapBeanZeroArrByDateStrArrForH();
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<hour24Arr.size();x++){
					String hkey = hour24Arr.get(x).getKey();
					for(int y=0;y<ttList.size();y++){
						String tkey = ttList.get(y)[0].toString();
						if(hkey.equals(tkey)){
							hour24Arr.set(x, new MapBean(hkey,ttList.get(y)[1].toString()));
						}
					}
				}
			}
			for(int x=0;x<hour24Arr.size();x++){
				stringXaxis.add(hour24Arr.get(x).getKey()+"点");
				numberYaxis.add(hour24Arr.get(x).getValue());
			}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("JVtodaySalesOrdernumTopSdwv")){//查询相关销售订单数|JVtodaySalesOrdernumTopSdwv|Sdwv是4个随机字母
			StringBuffer hql = new StringBuffer();
		hql.append(" SELECT ");
		hql.append(" DATE_FORMAT(payTime, "+MyTool.getValueToStrByHashMapKey(hsMap,"myDATE_FORMAT")+") AS ersiShi,count(*) as sl ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" and type = '1' ");//#交易类型(POS会员消费1;门店消费2;) 
	    hql.append(" and isTemp !=\'1\' ");
		hql.append(" GROUP BY  ersiShi ");
		hql.append(" ORDER BY ersiShi asc ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
					 
			ArrayList<MapBean> hour24Arr = MyTool.getMapBeanZeroArrByDateStrArrForH();
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<hour24Arr.size();x++){
					String hkey = hour24Arr.get(x).getKey();
					for(int y=0;y<ttList.size();y++){
						String tkey = ttList.get(y)[0].toString();
						if(hkey.equals(tkey)){
							hour24Arr.set(x, new MapBean(hkey,ttList.get(y)[1].toString()));
						}
					}
				}
			}
			for(int x=0;x<hour24Arr.size();x++){
				stringXaxis.add(hour24Arr.get(x).getKey()+"点");
				numberYaxis.add(hour24Arr.get(x).getValue());
			}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("WorkbenchPassengerFlowSylb")){//WorkbenchPassengerFlowSylb|工作台页面客流量
			StringBuffer hql = new StringBuffer();
		hql.append(" SELECT ");
		hql.append(" DATE_FORMAT(payTime, "+MyTool.getValueToStrByHashMapKey(hsMap,"myDATE_FORMAT")+") as M ,count(*) as sl ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" and isTemp !=\'1\' ");
		hql.append(" and customerAccount_id is not null ");       
		hql.append(" GROUP BY M ORDER BY M asc");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
					 
			ArrayList<MapBean> hour24Arr = MyTool.getMapBeanZeroArrByDateStrArrForH();
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<hour24Arr.size();x++){
					String hkey = hour24Arr.get(x).getKey();
					for(int y=0;y<ttList.size();y++){
						String tkey = ttList.get(y)[0].toString();
						if(hkey.equals(tkey)){
							hour24Arr.set(x, new MapBean(hkey,ttList.get(y)[1].toString()));
						}
					}
				}
			}
			for(int x=0;x<hour24Arr.size();x++){
				stringXaxis.add(hour24Arr.get(x).getKey()+"点");
				numberYaxis.add(hour24Arr.get(x).getValue());
			}
		}
		hsMapReturn.put("stringXaxis", stringXaxis);
		hsMapReturn.put("numberYaxis", numberYaxis);
		return hsMapReturn;
	}
	@Override    /** 工作台 :客户消费排行TOP10 返回html  **/  
	public Map<String, Object> findnvixContentHtmlA(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		ArrayList<TableBeanE>  tBeanEArr = new ArrayList<TableBeanE>();
		TableBeanE  tBeanE = new TableBeanE();
		StringBuffer hql = new StringBuffer();
		hql.append(" select A.kname,A.kahao,A.SFje from "); 
		hql.append(" ( ");
		hql.append(" SELECT ");
		hql.append(" IFNULL(sum(many.payAmount),0) AS SFje, ");
		hql.append(" toone. NAME as kname , ");
		hql.append(" IFNULL(toone.clipNumber,'无卡') as kahao ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER many ");
		hql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone ON many.customerAccount_id = toone.id and toone. NAME is not null and toone. NAME <> '' "); 
		hql.append(" and toone. clipNumber is not null and toone. clipNumber <> '' "); 
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     many.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
    hql.append(" and many.isTemp !=\'1\' "); 
    hql.append(" and many.type = '1' ");//#交易类型(POS会员消费1;门店消费2;) 
		hql.append(" GROUP BY ");
		hql.append(" toone.id ");
		hql.append(" ORDER BY ");
		hql.append(" SFje DESC ");
		hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					tBeanE.setColA(ttList.get(x)[0].toString());//姓名
					tBeanE.setColB(ttList.get(x)[1].toString());//卡号
					tBeanE.setColC(MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(ttList.get(x)[2].toString()))+"");
					tBeanEArr.add(tBeanE);
					tBeanE = new TableBeanE();
				}
			}
		hsMapReturn.put("tBeanEArr", tBeanEArr);	
		return hsMapReturn;
	}
	@Override    /** 工作台 :商品销量排行 TOP10 返回html  **/  
	public Map<String, Object> findnvixContentHtmlB(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		ArrayList<TableBeanE>  tBeanEArr = new ArrayList<TableBeanE>();
		Double yesterdayJe = 0.0;
		TableBeanE  tBeanE = new TableBeanE();
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("one")){
		StringBuffer hql = new StringBuffer();
		hql.append(" select A.spName,A.spSL,A.spJE from "); 
		hql.append(" ( ");
		hql.append(" SELECT ");
		hql.append(" many.title AS spName "); //#购买商品名称
		hql.append(" ,IFNULL(sum(many.num),0) AS spSL ");  //#购买商品数量     [RequireGoodsOrderItem num   才是记录的订单商品数量]
		hql.append(" ,IFNULL(sum(many.netTotal),0) AS spJE ");  //#金额(净单价*数量)  
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDERITEM many ");   
		hql.append(" INNER JOIN DRP_REQUIREGOODSORDER toone ON many.REQUIREGOODSORDER_ID = toone.id and toone.isTemp !=\'1\' and toone.type = '1' "); 
		if(hsMap.containsKey("tenantId")){
			hql.append(" and toone.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and toone.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append(" and toone.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and toone.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		hql.append(" where many.title  is not null and many.title <> '' ");
		hql.append(" GROUP BY ");
		hql.append(" spName ");
		hql.append(" ORDER BY ");
		hql.append(" spJE DESC ");
		hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					tBeanE.setColA(ttList.get(x)[0].toString());
					tBeanE.setColB(MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(ttList.get(x)[1].toString()))+"");
					tBeanE.setColC(MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(ttList.get(x)[2].toString()))+"");
					tBeanEArr.add(tBeanE);
					tBeanE = new TableBeanE();
				}
			}
		}
		
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("two")){//查询较昨天的 环比值
			StringBuffer hql = new StringBuffer();
			hql.append(" select A.spJE,'1'  from( ");
			hql.append(" SELECT ");
			hql.append(" many.title AS spName "); //#购买商品名称
			hql.append(" ,IFNULL(sum(many.netTotal),0) AS spJE ");  //#金额(净单价*数量)  
			hql.append(" FROM ");
			hql.append(" DRP_REQUIREGOODSORDERITEM many ");   
			hql.append(" INNER JOIN DRP_REQUIREGOODSORDER toone ON many.REQUIREGOODSORDER_ID = toone.id and toone.isTemp !=\'1\' and toone.type = '1' ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and toone.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and toone.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and toone.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and toone.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" where many.title  is not null and many.title <> '' ");
			hql.append(" GROUP BY ");
			hql.append(" spName ");
			hql.append(" ) A where A.spName="+MyTool.getValueToStrByHashMapKey(hsMap,"spName")+" ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
						yesterdayJe = MyTool.roundingTwoDecimalTwo(ttList.get(0)[0].toString());
				}
			}
		hsMapReturn.put("yesterdayJe", yesterdayJe);
		hsMapReturn.put("tBeanEArr", tBeanEArr);	
		return hsMapReturn;
	}
	@Override    /**  会员消费分析  '数据块'返回jsonA   **/  
	public Map<String, Object> findConsumptionAnalysisJsonA(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT  IFNULL(sum(payAmount),0),'1' FROM DRP_REQUIREGOODSORDER WHERE "); //查询自 订单，payTime付款时间  payAmount 实付金额
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" and isTemp !=\'1\' and type = '1' ");
		hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"conditionSQL")+" ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	@Override    /**   会员消费分析  '视图A'返回json 会员消费方式分析   **/  
	public Map<String, Object> findConsumptionAnalysisViewA(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		ArrayList<String> valueArr = new ArrayList<String>();
		StringBuilder hql = new StringBuilder();//查询自 订单，payTime付款时间  payAmount 实付金额
		hql.append(" SELECT DATE_FORMAT(payTime, '%Y-%m-%d' ) as M,IFNULL(sum(payAmount),0) FROM DRP_REQUIREGOODSORDER WHERE "); 
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     payTime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
		hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" and  isTemp !=\'1\' and type = '1' ");
		hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"conditionSQL")+" ");
		hql.append(" GROUP BY M ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			ArrayList<MapBean> dayArr = MyTool.getMapBeanZeroArray_ByDateStringArray(objTimeArr);
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<dayArr.size();x++){
					String hkey = dayArr.get(x).getKey();
					for(int y=0;y<ttList.size();y++){
						String tkey = ttList.get(y)[0].toString();
						if(hkey.equals(tkey)){
							dayArr.set(x, new MapBean(hkey,ttList.get(y)[1].toString()));
						}
					}
				}
			}
			for(int x=0;x<dayArr.size();x++){
				valueArr.add(dayArr.get(x).getValue());
			}
		hsMapReturn.put("valueArr", valueArr);
		return hsMapReturn;
	}
	@Override    /**   会员消费排行视图 '会员消费分析' **/  
	public Map<String, Object> findConsumptionAnalysisViewB(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder();
		hql.append(" select A.kname,A.SFje from "); 
		hql.append(" ( ");
		hql.append(" SELECT IFNULL(sum(many.payAmount),0) AS SFje, ");
		hql.append(" toone. NAME as kname  ");
		hql.append(" FROM DRP_REQUIREGOODSORDER many ");
		hql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone ON many.customerAccount_id = toone.id and toone. NAME is not null and toone. NAME <> '' ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     many.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		hql.append(" and  many.isTemp !=\'1\' and many.type = '1'  ");
		hql.append(" GROUP BY toone.id  ORDER BY SFje DESC ");
		hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			ArrayList<String> nameArr = new ArrayList<String>();
			ArrayList<String> valueArr = new ArrayList<String>();
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					nameArr.add(ttList.get(x)[0].toString());
					valueArr.add(ttList.get(x)[1].toString());
				}
			}
			 Integer topNum = Integer.parseInt(MyTool.getValueToStrByHashMapKey(hsMap,"topNum"));
			 if( nameArr.size() < topNum ){
				 for(int x=0;x<topNum;x++){
					 nameArr.add("");
					 valueArr.add("0");
					 if(nameArr.size() == topNum){
						 break;
					 }
				 }
			 }
			 for (int start = 0, end = nameArr.size() - 1; start < end; start++, end--) {
				 String temp = nameArr.get(end);
				 nameArr.set(end, nameArr.get(start));
				 nameArr.set(start, temp);
			 }
			 for (int start = 0, end = valueArr.size() - 1; start < end; start++, end--) {
				 String temp = valueArr.get(end);
				 valueArr.set(end, valueArr.get(start));
				 valueArr.set(start, temp);
			 }
			String jsonStrReturn = "{\"a\":"+1+"}";
			Gson gosn = new Gson();
			String strA ="{\"nameArr\":"+gosn.toJson(nameArr)+"}";
			String strB ="{\"valueArr\":"+ valueArr.toString() +"}";
			jsonStrReturn = MyTool.mergeJsonStringTwo(strA,strB);
		hsMapReturn.put("jsonStrReturn", jsonStrReturn);	
		return hsMapReturn;
	}

	@Override
	public List<RequireGoodsOrderVo> getCustomerExpense(Map<String, Object> params) throws Exception {
		List<RequireGoodsOrderVo> storeItems = new ArrayList<RequireGoodsOrderVo>();
		StringBuilder hql = attendanceHqlProvider.findCustomerExpense(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length == 2) {
					RequireGoodsOrderVo requireGoodsOrderVo = new RequireGoodsOrderVo();
					if (obj[0] != null) {
						requireGoodsOrderVo.setItemName(String.valueOf(obj[0]));
					}
					if (obj[1] != null) {
						requireGoodsOrderVo.setAmount(Double.valueOf(String.valueOf(obj[1])));
					}
					storeItems.add(requireGoodsOrderVo);
				}
			}
		}	
		return storeItems;
	}
	@Override
	public List<RequireGoodsOrderVo> getCustomerAllExpense(Map<String, Object> params) throws Exception {
		List<RequireGoodsOrderVo> storeItems = new ArrayList<RequireGoodsOrderVo>();
		StringBuilder hql = attendanceHqlProvider.findCustomerAllExpense(params);
		List<Object[]> objectList = vixntBaseDao.findObjectList(hql.toString());
		if (null != objectList && objectList.size() > 0) {
			for (Object[] obj : objectList) {
				if (obj.length == 2) {
					RequireGoodsOrderVo requireGoodsOrderVo = new RequireGoodsOrderVo();
					if (obj[0] != null) {
						requireGoodsOrderVo.setItemName(String.valueOf(obj[0]));
					}
					if (obj[1] != null) {
						requireGoodsOrderVo.setAmount(Double.valueOf(String.valueOf(obj[1])));
					}
					storeItems.add(requireGoodsOrderVo);
				}
			}
		}	
		return storeItems;
	}
	@Override    /** 会员画像分析>顾客消费次数分析视图  */ 
	public Map<String, Object> findCategoryAnalysisViewA(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder();//#查询会员购买次数的sql， Agoumaicishu就是次数，jigeren就是相应的几个人
		hql.append(" select A.goumaicishu as Agoumaicishu ,count(*) as jigeren  from ( "); 
		hql.append(" SELECT count(*) as goumaicishu  FROM DRP_REQUIREGOODSORDER many ");
		hql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone ON many.customerAccount_id = toone.id ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");
		}
		hql.append(" AND toone. NAME IS NOT NULL  AND toone. NAME <> '' WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     many.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		hql.append(" and many.isTemp !=\'1\' and many.type='1' ");
		hql.append(" GROUP BY many.customerAccount_id ");
		hql.append(" )A  GROUP BY Agoumaicishu  ORDER BY Agoumaicishu asc ");
		
		ArrayList<MapBeanInt> mBeanArr = new ArrayList<MapBeanInt>();
		for(int x=0;x<10;x++){
			mBeanArr.add(new MapBeanInt(((x+1)+"次"),0));
		}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<mBeanArr.size();x++){
					String cishuA = mBeanArr.get(x).getKey();
					for(int y=0;y<ttList.size();y++){
						String cishuB = ttList.get(y)[0].toString();//购买次数
						String renshu = ttList.get(y)[1].toString();//相应的几个人
						if((cishuB+"次").equals(cishuA)){
							mBeanArr.set(x,new MapBeanInt((cishuB+"次"),Integer.parseInt(renshu)));
						}
					
					}
				}
			}
			int renshuA = 0;
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					Integer cishu = Integer.parseInt(ttList.get(x)[0].toString());//购买次数
					Integer renshu = Integer.parseInt(ttList.get(x)[1].toString());//相应的几个人
					if(cishu >=11 && cishu <=20){
						renshuA +=renshu;
					}
				}
			}
			mBeanArr.add(new MapBeanInt("11次-20次",renshuA));
			////////////////
			int renshuB = 0;
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					Integer cishu = Integer.parseInt(ttList.get(x)[0].toString());//购买次数
					Integer renshu = Integer.parseInt(ttList.get(x)[1].toString());//相应的几个人
					if(cishu >=21 && cishu <=50){
						renshuB +=renshu;
					}
				}
			}
			mBeanArr.add(new MapBeanInt("21次-50次",renshuB));
			////////////////////
			int renshuC = 0;
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					Integer cishu = Integer.parseInt(ttList.get(x)[0].toString());//购买次数
					Integer renshu = Integer.parseInt(ttList.get(x)[1].toString());//相应的几个人
					if(cishu >=51 && cishu <=100){
						renshuC += renshu;
					}
				}
			}
			mBeanArr.add(new MapBeanInt("51次-100次",renshuC));
			////////////////////////////
			int renshuD = 0;
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					Integer cishu = Integer.parseInt(ttList.get(x)[0].toString());//购买次数
					Integer renshu = Integer.parseInt(ttList.get(x)[1].toString());//相应的几个人
					if(cishu >=101 ){
						renshuD += renshu;
					}
				}
			}
			mBeanArr.add(new MapBeanInt("101次及以上",renshuD));
			
			ArrayList<String> nameArr = new ArrayList<String>();
			ArrayList<String> valueArr = new ArrayList<String>();
			for(int x=0;x<mBeanArr.size();x++){
				nameArr.add(mBeanArr.get(x).getKey());
				valueArr.add(mBeanArr.get(x).getValue()+"");
			}
			
			String jsonStrReturn = "{\"a\":"+1+"}";
			Gson gosn = new Gson();
			String strA ="{\"nameArr\":"+ gosn.toJson(nameArr) +"}";
			String strB ="{\"valueArr\":"+ valueArr.toString() +"}";
			jsonStrReturn = MyTool.mergeJsonStringTwo(strA,strB);
		hsMapReturn.put("jsonStrReturn", jsonStrReturn);	
		return hsMapReturn;
	}
	@Override    /** 会员画像分析>会员消费次数排行  */ 
	public Map<String, Object> findCategoryAnalysisViewB(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder();
		hql.append(" select A.kname,A.goumaicishu from ( "); 
		hql.append("SELECT toone. NAME AS kname,count(*) as goumaicishu  FROM DRP_REQUIREGOODSORDER many ");
		hql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone ON many.customerAccount_id = toone.id and toone. NAME is not null and toone. NAME <> '' ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");
		}
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     many.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		hql.append(" and many.isTemp !=\'1\' and many.type='1' ");
		hql.append(" GROUP BY  many.customerAccount_id  ORDER BY  goumaicishu DESC ");
		hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");

			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			ArrayList<String> nameArr = new ArrayList<String>();
			ArrayList<String> valueArr = new ArrayList<String>();
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					nameArr.add(ttList.get(x)[0].toString());
					valueArr.add(ttList.get(x)[1].toString());
				}
			}
			 Integer topNum = Integer.parseInt(MyTool.getValueToStrByHashMapKey(hsMap,"topNum"));
			 if( nameArr.size() < topNum ){
				 for(int x=0;x<topNum;x++){
					 nameArr.add("");
					 valueArr.add("0");
					 if(nameArr.size() == topNum){
						 break;
					 }
				 }
			 }
			 for (int start = 0, end = nameArr.size() - 1; start < end; start++, end--) {
				 String temp = nameArr.get(end);
				 nameArr.set(end, nameArr.get(start));
				 nameArr.set(start, temp);
			 }
			 for (int start = 0, end = valueArr.size() - 1; start < end; start++, end--) {
				 String temp = valueArr.get(end);
				 valueArr.set(end, valueArr.get(start));
				 valueArr.set(start, temp);
			 }
			String jsonStrReturn = "{\"a\":"+1+"}";
			Gson gosn = new Gson();
			String strA ="{\"nameArr\":"+gosn.toJson(nameArr)+"}";
			String strB ="{\"valueArr\":"+ valueArr.toString() +"}";
			jsonStrReturn = MyTool.mergeJsonStringTwo(strA,strB);
		hsMapReturn.put("jsonStrReturn", jsonStrReturn);	
		return hsMapReturn;
	}
	@Override    /** 门店库存报表>最近30日商品入库数量Top10  */ 
	public Map<String, Object> findStockAnalysisViewASabc(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuffer hql = new StringBuffer();
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("goodsStorageNumSabe")){
			hql = new StringBuffer();
			hql.append(" select A.spName,A.spSL from "); //goodsStorageNumSabe 商品入库数量
			hql.append(" ( ");
			hql.append(" SELECT ");
			hql.append(" many.itemname as spName, ");
			hql.append(" IFNULL(sum(many.quantity),0) as spSL ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ");
			hql.append(" ON ");
			hql.append(" many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");   
			hql.append(" toone.istemp != '1' ");
			hql.append(" ) ");// select istemp from inv_stockrecordlines #Unknown column 'istemp' in 'field list'
			hql.append(" AND toone.flag = '1' ");
			hql.append(" AND many.itemname  is not null and  many.itemname <> '' ");//切记，判断商品名称不为NULL，也不是空字符串
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and many.CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" GROUP BY spName ");
			hql.append(" ORDER BY spSL DESC ");
			hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("goodsOutStorageNumSame")){
			hql = new StringBuffer();
			hql.append(" select A.spName,A.spSL from ");//goodsOutStorageNumSame商品出库数量
			hql.append(" ( ");
			hql.append(" SELECT ");
			hql.append(" many.itemname as spName, ");
			hql.append(" IFNULL(sum(many.quantity),0) as spSL ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ");
			hql.append(" ON ");
			hql.append(" many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");   
			hql.append(" toone.istemp != '1' ");
			hql.append(" ) ");// select istemp from inv_stockrecordlines #Unknown column 'istemp' in 'field list'
			hql.append(" AND toone.flag = '2' ");//flag 出入库标志 1:入库，     2:出库
			hql.append(" AND many.itemname  is not null and  many.itemname <> '' ");//切记，判断商品名称不为NULL，也不是空字符串
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and many.CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" GROUP BY spName ");
			hql.append(" ORDER BY spSL DESC ");
			hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("goodsStorageMoneySrbe")){
			hql = new StringBuffer();
			hql.append(" select A.spName,A.spSL from "); //goodsStorageMoneySrbe 商品入库金额 Money
			hql.append(" ( ");
			hql.append(" SELECT ");
			hql.append(" many.itemname as spName, ");
			hql.append(" IFNULL(sum(many.price),0) as spSL ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ");
			hql.append(" ON ");
			hql.append(" many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");
			hql.append(" toone.istemp != '1' ");
			hql.append(" ) ");
			hql.append(" AND toone.flag = '1' ");//flag 是不是入库，和出库标记
			hql.append(" AND many.itemname  is not null and  many.itemname <> '' ");//切记，判断商品名称不为NULL，也不是空字符串
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and many.CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" GROUP BY spName ");
			hql.append(" ORDER BY spSL DESC ");
			hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
					hsMap.put("moneyTo-Two-Decimal-", "moneyTo-Two-Decimal-");//因为是钱，所有要保留2位小数
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("goodsOutStorageMoneySrbb")){
			hql = new StringBuffer();
			hql.append(" select A.spName,A.spSL from ");//goodsOutStorageMoneySrbb 商品出库金额
			hql.append(" ( ");
			hql.append(" SELECT ");
			hql.append(" many.itemname as spName, ");
			hql.append(" IFNULL(sum(many.price),0) as spSL ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ");
			hql.append(" ON ");
			hql.append(" many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");
			hql.append(" toone.istemp != '1' ");
			hql.append(" ) ");
			hql.append(" AND toone.flag = '2' ");//flag 是不是入库，和出库标记
			hql.append(" AND many.itemname  is not null and  many.itemname <> '' ");//切记，判断商品名称不为NULL，也不是空字符串
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and many.CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" GROUP BY spName ");
			hql.append(" ORDER BY spSL DESC ");
			hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
					hsMap.put("moneyTo-Two-Decimal-", "moneyTo-Two-Decimal-");//因为是钱，所有要保留2位小数
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("goodsSaleNumSaveh")){
			hql = new StringBuffer(); //goodsSaleNumSaveh商品销售数量
			hql.append(" select A.spName,A.spSL from "); 
			hql.append(" ( ");
			hql.append(" SELECT ");
			hql.append(" many.title AS spName "); //#购买商品名称
			hql.append(" ,IFNULL(sum(many.num),0) AS spSL ");  //#购买商品数量  	
			hql.append(" FROM DRP_REQUIREGOODSORDERITEM many ");   
			hql.append(" INNER JOIN DRP_REQUIREGOODSORDER toone ON many.REQUIREGOODSORDER_ID = toone.id and toone.isTemp !=\'1\' and toone.type ='1' ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and toone.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and toone.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");
			}
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and toone.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and toone.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" where many.title  is not null and many.title <> '' ");
			hql.append(" GROUP BY ");
			hql.append(" spName ");
			hql.append(" ORDER BY ");
			hql.append(" spSL DESC ");
			hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("goodsSaleMoneySkvkh")){
			hql = new StringBuffer();//goodsSaleMoneySkvkh商品销售金额
			hql.append(" select A.spName,A.spSL from "); 
			hql.append(" ( ");
			hql.append(" SELECT many.title AS spName "); //#购买商品名称
			hql.append(" ,IFNULL(sum(many.netTotal),0) AS spSL FROM "); 
			hql.append(" DRP_REQUIREGOODSORDERITEM many ");   //many.netTotal #金额(净单价*数量)// many.amount 购买商品数量 
			hql.append(" INNER JOIN DRP_REQUIREGOODSORDER toone ON many.REQUIREGOODSORDER_ID = toone.id and toone.isTemp !=\'1\' and toone.type ='1' ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and toone.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and toone.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");
			}
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and toone.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and toone.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" where many.title  is not null and many.title <> '' ");
			hql.append(" GROUP BY ");
			hql.append(" spName ");
			hql.append(" ORDER BY ");
			hql.append(" spSL DESC ");
			hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
					hsMap.put("moneyTo-Two-Decimal-", "moneyTo-Two-Decimal-");//因为是钱，所有要保留2位小数
		}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			ArrayList<String> nameArr = new ArrayList<String>();
			ArrayList<String> valueArr = new ArrayList<String>();
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					nameArr.add(ttList.get(x)[0].toString());
					valueArr.add(ttList.get(x)[1].toString());
				}
			}
			 Integer topNum = Integer.parseInt(MyTool.getValueToStrByHashMapKey(hsMap,"topNum"));
			 if( nameArr.size() < topNum ){
				 for(int x=0;x<topNum;x++){
					 nameArr.add("");
					 valueArr.add("0");
					 if(nameArr.size() == topNum){
						 break;
					 }
				 }
			 }
			 for (int start = 0, end = nameArr.size() - 1; start < end; start++, end--) {
				 String temp = nameArr.get(end);
				 nameArr.set(end, nameArr.get(start));
				 nameArr.set(start, temp);
			 }
			 for (int start = 0, end = valueArr.size() - 1; start < end; start++, end--) {
				 String temp = valueArr.get(end);
				 valueArr.set(end, valueArr.get(start));
				 valueArr.set(start, temp);
			 }
			String jsonStrReturn = "{\"a\":"+1+"}";
			Gson gosn = new Gson();
			if(hsMap.containsKey("moneyTo-Two-Decimal-")){//因为是钱，所有要保留2位小数
				for(int x=0;x<valueArr.size();x++){
					String valueNum = valueArr.get(x);
					Double numA = MyTool.roundingTwoDecimal(Double.parseDouble(valueNum));
					String numB = MyTool.formatObjToNumStr(numA);
					valueArr.set(x, numB);
				}
			}
					
			String strA ="{\"nameArr\":"+gosn.toJson(nameArr)+"}";
			String strB ="{\"valueArr\":"+ valueArr.toString() +"}";
			jsonStrReturn = MyTool.mergeJsonStringTwo(strA,strB);
		hsMapReturn.put("jsonStrReturn", jsonStrReturn);	
		return hsMapReturn;
	}
	@Override    /**   会员消费排行 '视图C'返回json 最近30日商品销量排行Top10 **/  
	public Map<String, Object> findConsumptionAnalysisViewC(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder();
		hql.append(" select A.spName,A.spSL from "); 
		hql.append(" ( ");
		hql.append(" SELECT ");
		hql.append(" many.title AS spName "); //#购买商品名称
		hql.append(" ,IFNULL(sum(many.num),0) AS spSL ");  //#购买商品数量  	
		hql.append(" FROM DRP_REQUIREGOODSORDERITEM many ");  
		hql.append(" INNER JOIN DRP_REQUIREGOODSORDER toone ON many.REQUIREGOODSORDER_ID = toone.id and toone.isTemp !=\'1\'  and toone.type = '1' ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and toone.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and toone.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append(" and toone.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and toone.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		hql.append(" where many.title  is not null and many.title <> '' ");
		hql.append(" GROUP BY spName ORDER BY spSL DESC ");
		hql.append(" )A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			ArrayList<String> nameArr = new ArrayList<String>();
			ArrayList<String> valueArr = new ArrayList<String>();
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					nameArr.add(ttList.get(x)[0].toString());
					valueArr.add(ttList.get(x)[1].toString());
				}
			}
			 Integer topNum = Integer.parseInt(MyTool.getValueToStrByHashMapKey(hsMap,"topNum"));
			 if( nameArr.size() < topNum ){
				 for(int x=0;x<topNum;x++){
					 nameArr.add("");
					 valueArr.add("0");
					 if(nameArr.size() == topNum){
						 break;
					 }
				 }
			 }
			 for (int start = 0, end = nameArr.size() - 1; start < end; start++, end--) {
				 String temp = nameArr.get(end);
				 nameArr.set(end, nameArr.get(start));
				 nameArr.set(start, temp);
			 }
			 for (int start = 0, end = valueArr.size() - 1; start < end; start++, end--) {
				 String temp = valueArr.get(end);
				 valueArr.set(end, valueArr.get(start));
				 valueArr.set(start, temp);
			 }
			String jsonStrReturn = "{\"a\":"+1+"}";
			Gson gosn = new Gson();
			String strA ="{\"nameArr\":"+gosn.toJson(nameArr)+"}";
			String strB ="{\"valueArr\":"+ valueArr.toString() +"}";
			jsonStrReturn = MyTool.mergeJsonStringTwo(strA,strB);
		hsMapReturn.put("jsonStrReturn", jsonStrReturn);	
		return hsMapReturn;
	}
@Override    /**   会员量分析 > 新增会员量视图    **/  
	public Map<String, Object> findPurchasingBehaviorActionViewA(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		ArrayList<String> valueArr = new ArrayList<String>();
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT DATE_FORMAT(CREATETIME, '%Y-%m-%d' ) as M,count(*) FROM CRM_CUSTOMERACCOUNT WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
		hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");
		}
		hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"conditionSQL")+" ");
		hql.append(" GROUP BY M ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			ArrayList<MapBean> dayArr = MyTool.getMapBeanZeroArray_ByDateStringArray(objTimeArr);
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<dayArr.size();x++){
					String hkey = dayArr.get(x).getKey();
					for(int y=0;y<ttList.size();y++){
						String tkey = ttList.get(y)[0].toString();
						if(hkey.equals(tkey)){
							dayArr.set(x, new MapBean(hkey,ttList.get(y)[1].toString()));
						}
					}
				}
			}
			for(int x=0;x<dayArr.size();x++){
				valueArr.add(dayArr.get(x).getValue());
			}
		hsMapReturn.put("valueArr", valueArr);
		return hsMapReturn;
	}
	@Override    /**   会员量分析 > 会员总量视图    **/  
	public Map<String, Object> findPurchasingBehaviorActionViewB(Map<String, Object> hsMap) throws Exception {
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		
		StringBuffer joinSql = new StringBuffer();
		for (int x = 0; x < objTimeArr.size(); x++) {
			if (x == 0) {
				joinSql.append(" SELECT " + MyTool.StringUseToSql(objTimeArr.get(x)) + " as click_date");
			}
			if (x != 0) {
				joinSql.append(" union all SELECT " + MyTool.StringUseToSql(objTimeArr.get(x)) + " as click_date");
			}
		}
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		ArrayList<String> valueArr = new ArrayList<String>();
		StringBuffer hql = new StringBuffer();
		hql.append(" select h.riqi as datetemp, ");
		hql.append(" ( select count(*) FROM CRM_CUSTOMERACCOUNT WHERE 1=1 ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");
		}
		hql.append(" and CREATETIME < date_add( datetemp , INTERVAL 1 DAY)"); 
		hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"conditionSQL")+" ");
		hql.append("  ) c from ( select b.click_date as riqi from ( ");
		hql.append(joinSql.toString());
		hql.append(" )b)h ");
		
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			ArrayList<MapBean> dayArr = MyTool.getMapBeanZeroArray_ByDateStringArray(objTimeArr);
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<dayArr.size();x++){
					String hkey = dayArr.get(x).getKey();
					for(int y=0;y<ttList.size();y++){
						String tkey = ttList.get(y)[0].toString();
						if(hkey.equals(tkey)){
							dayArr.set(x, new MapBean(hkey,ttList.get(y)[1].toString()));
						}
					}
				}
			}
			for(int x=0;x<dayArr.size();x++){
				valueArr.add(dayArr.get(x).getValue());
			}
		hsMapReturn.put("valueArr", valueArr);
		return hsMapReturn;
	}
	@Override    /**  客户消费明细CustomerConsumptionDetails   A  **/  
	public Map<String, Object> findCustomerConsumptionDetailsA(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		String stringResult ="";
		String stringResultB ="";
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("selfExtendStringField1")){
		StringBuffer hql = new StringBuffer();// 客户消费明细(消费总金额) {controlSQL:'selfExtendStringField1'}/** 自定义扩展字段1 */
		hql.append(" SELECT "); 
		hql.append(" IFNULL(sum(payAmount),0) AS XFje,'1' "); 
		hql.append(" from "); 
		hql.append(" DRP_REQUIREGOODSORDER "); 
		hql.append(" where payTime is not null ");   
		hql.append(" and customerAccount_id ="+MyTool.StringUseToSql(MyTool.getValueToStrByHashMapKey(hsMap,"customerID"))+" ");
		if(hsMap.containsKey("startTime")){
		hql.append(" and payTime >= "+ MyTool.StringUseToSql(MyTool.getValueToStrByHashMapKey(hsMap,"startTime")));
		hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(MyTool.getValueToStrByHashMapKey(hsMap,"endTime"))));
		}
		hql.append(" and isTemp !=\'1\' and type='1' ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}	
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("selfExtendStringField2")){
			StringBuffer hql = new StringBuffer();// 客户消费明细(消费次数) {controlSQL:'selfExtendStringField2'}/** 自定义扩展字段2 */
			hql.append(" SELECT "); 
			hql.append(" count(*) as goumaicishu,'1' "); 
			hql.append(" from "); 
			hql.append(" DRP_REQUIREGOODSORDER ");
			hql.append(" where payTime is not null and isTemp !=\'1\' and type='1' ");   
			hql.append(" and customerAccount_id ="+MyTool.StringUseToSql(MyTool.getValueToStrByHashMapKey(hsMap,"customerID"))+" ");
			if(hsMap.containsKey("startTime")){
				hql.append(" and payTime >= "+ MyTool.StringUseToSql(MyTool.getValueToStrByHashMapKey(hsMap,"startTime")));
				hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(MyTool.getValueToStrByHashMapKey(hsMap,"endTime"))));
			}
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("selfExtendStringField4")){
			StringBuffer hql = new StringBuffer();// 客户消费明细(最近消费时间) {controlSQL:'selfExtendStringField4'}/** 自定义扩展字段4 */
			hql.append(" select  DATE_FORMAT(A.firstPayTime, '%Y-%m-%d %H:%i:%s' ),IFNULL(A.XFje,0) from ( "); 
			hql.append(" SELECT "); 
			hql.append(" payTime as firstPayTime, payAmount as XFje "); 
			hql.append(" from "); 
			hql.append(" DRP_REQUIREGOODSORDER ");  
			hql.append(" where payTime is not null and isTemp !=\'1\' and type='1' ");   
			hql.append(" and customerAccount_id ="+MyTool.StringUseToSql(MyTool.getValueToStrByHashMapKey(hsMap,"customerID"))+" ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}
			hql.append(" ORDER BY payTime desc)A LIMIT 0,1 ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					stringResult =  ttList.get(0)[0].toString();
					 totalDouble =  Double.parseDouble(ttList.get(0)[1].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("mobilePhoneANDname")){
			StringBuffer hql = new StringBuffer();//mobilePhoneANDname 查‘移动电话’和‘名字’
			hql.append(" SELECT "); 
			hql.append(" mobilePhone , NAME "); 
			hql.append(" from "); 
			hql.append(" CRM_CUSTOMERACCOUNT "); 
			hql.append(" where ");   
			hql.append(" id ="+MyTool.StringUseToSql(MyTool.getValueToStrByHashMapKey(hsMap,"customerID"))+" ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					if(ttList.get(0) != null ){
						if(ttList.get(0)[0] != null){
							stringResult  = ttList.get(0)[0].toString();
						}
						if(ttList.get(0)[1] != null){
							stringResultB = ttList.get(0)[1].toString();
						}
					}
				}
		}		
		hsMapReturn.put("totalDouble", totalDouble);
		hsMapReturn.put("stringResult", stringResult);
		hsMapReturn.put("stringResultB", stringResultB);
		return hsMapReturn;
	}
	@Override    /** 获得’客户分析 ’分析页面的 会员总数+会员卡总数+会员卡总积分+会员卡平均积分   **/  
	public Map<String, Object> findFsycfaaMemberAnalysisJsonA(Map<String, Object> hsMap) throws Exception { 
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;//findFsycfaaMemberAnalysisJsonA 中 ‘Fsycfaa’ 为随机7个英文字母
		
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("MemberTotal")){//MemberTotal 会员总数
			StringBuilder hql = new StringBuilder();
			hql.append(" SELECT "); 
			hql.append(" count(*),'1' ");  
			hql.append(" FROM ");
			hql.append(" CRM_CUSTOMERACCOUNT where 1=1 ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("MemberCardTotal")){//MemberCardTotal 会员卡总数
			StringBuilder hql = new StringBuilder();
			hql.append(" SELECT count(*),'1' FROM  CRM_CUSTOMERACCOUNTCILP many ");
			hql.append(" inner join CRM_CUSTOMERACCOUNT toone on many.customerAccount_id = toone.id and ( many.ISTEMP != '1') and (many.isReport != 'Y') ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
			}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("MemberPointTotal")){//MemberPointTotal 会员总积分
			StringBuffer hql = new StringBuffer();
		hql.append(" SELECT "); 
		hql.append(" IFNULL(sum(point),0),'1' ");  
		hql.append(" FROM ");
		hql.append(" CRM_CUSTOMERACCOUNT ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("MemberTotalMoneySabghj")){ 
			StringBuffer hql = new StringBuffer();//MemberTotalMoneySabghj 会员消费总金额  
			hql.append(" SELECT IFNULL(sum(payAmount),0),'1' FROM DRP_REQUIREGOODSORDER WHERE ");//查询自 订单，payTime付款时间  payAmount 实付金额
			ArrayList<String> timeobj = new ArrayList<String>();
			timeobj.add(MyTool.getTodayBy_yyyyMMdd());
			hql.append("  payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeobj.get(timeobj.size()-1))));
			hql.append(" and isTemp !=\'1\' and type = '1'  ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());  
			}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("MemberStillMoneySfgh")){ 
			StringBuffer hql = new StringBuffer();// MemberStillMoneySfgh 会员卡总余额
			hql.append(" SELECT "); 
			hql.append(" IFNULL(sum(many.money),0),'1' "); 
			hql.append(" FROM ");
			hql.append(" CRM_CUSTOMERACCOUNTCILP many");
			hql.append(" inner join CRM_CUSTOMERACCOUNT toone on many.customerAccount_id = toone.id and ( many.ISTEMP != '1') ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());  
			}
		}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	
	@Override    /** 获得’客户分析 ’分析页面的 饼图数据   **/  
	public Map<String, Object> findFsycfaaMemberAnalysisJsonB(Map<String, Object> hsMap) throws Exception { 
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		String jsonReturnStr = "{\"occupyA\":"+0+"}";
		ArrayList<String> numberArr = new ArrayList<String>();
		ArrayList<String> stringArr = new ArrayList<String>();
		
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("PieViewCardType")){
			StringBuilder hql = new StringBuilder();
			//查询： 客户分析>会员卡类型分布  | PieView饼图视图|CardType会员卡类型|PieViewCardType
			hql.append(" select count(*) as sl,case toone.type  when '1' then '余额卡' when '2' then '次卡'  else '其他卡' end from CRM_CUSTOMERACCOUNTCILP many ");
			hql.append(" inner JOIN CRM_CARDENTITY toone on many.card_id = toone.id and (many.ISTEMP != '1')  and (many.isReport != 'Y') ");
			hql.append(" inner join CRM_CUSTOMERACCOUNT tooneB on many.customerAccount_id = tooneB.id ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and tooneB.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");
			}
			hql.append(" GROUP BY toone.type ");
			hql.append(" ORDER BY sl desc ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  for(int x=0;x<ttList.size();x++){
						String number = ttList.get(x)[0].toString();//数目
						String string = ttList.get(x)[1].toString();//名称
						numberArr.add(number);
						stringArr.add(string);
					}
			}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("PieViewMemberLevel")){
			StringBuilder hql = new StringBuilder();
			//查询： 客户分析>会员等级分布 | PieView饼图视图| |PieViewMemberLevel
			hql.append(" select count(*) as sl,toone.name from CRM_CUSTOMERACCOUNT many ");
			hql.append(" inner JOIN CRM_M_MEMBERLEVEL toone on many.memberLevel_id = toone.id and toone. NAME is not null and toone. NAME <> '' ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}
			hql.append(" GROUP BY many.memberLevel_id ");
			hql.append(" ORDER BY sl desc ");
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  for(int x=0;x<ttList.size();x++){
						String number = ttList.get(x)[0].toString();//数目
						String string = ttList.get(x)[1].toString();//名称
						numberArr.add(number);
						stringArr.add(string);
					}
			}
		}
		Gson gson = new Gson();
		jsonReturnStr ="{\"numberResult\":"+numberArr.toString()+",\"stringResult\":"+gson.toJson(stringArr)+"}";
		hsMapReturn.put("jsonReturnStr", jsonReturnStr);
		return hsMapReturn;
	}
	@Override   /**获得’客户分析 ’客流量视图  **/  
	public Map<String, Object> findFsycfaaMemberAnalysisJsonC(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		ArrayList<String> valueArr = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		StringBuffer hql = new StringBuffer();
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("memberOrderPassengers")){//memberOrderPassengers | 会员付款订单人次
			hql.append(" SELECT DATE_FORMAT(payTime, "+MyTool.getValueToStrByHashMapKey(hsMap,"myDATE_FORMAT")+") as M ,count(*) as sl ");
			hql.append(" FROM DRP_REQUIREGOODSORDER WHERE ");
			hql.append("     payTime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" and isTemp !=\'1\' and type = '1' ");
			hql.append(" and customerAccount_id is not null "); 
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}
			hql.append(" GROUP BY M ");
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("orderNumberSojn")){//orderNumberSojn | 每日订单数
			hql.append(" SELECT DATE_FORMAT(payTime, "+MyTool.getValueToStrByHashMapKey(hsMap,"myDATE_FORMAT")+") as M ,count(*) as sl ");
			hql.append(" FROM DRP_REQUIREGOODSORDER WHERE ");
			hql.append("     payTime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}
			hql.append(" and isTemp !=\'1\' and type = '1' and customerAccount_id is not null GROUP BY M ");
		}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			ArrayList<MapBean> dayArr = MyTool.getMapBeanZeroArray_ByDateStringArray(objTimeArr);
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<dayArr.size();x++){
					String hkey = dayArr.get(x).getKey();
					for(int y=0;y<ttList.size();y++){
						String tkey = ttList.get(y)[0].toString();
						if(hkey.equals(tkey)){
							dayArr.set(x, new MapBean(hkey,ttList.get(y)[1].toString()));
						}
					}
				}
			}
			for(int x=0;x<dayArr.size();x++){
				valueArr.add(dayArr.get(x).getValue());
			}
			
		hsMapReturn.put("valueArr", valueArr);
		return hsMapReturn;
	}
	@Override   /**获得’会员消费分析 ’>客单价视图(每日订单总金额/对应客户人数)  **/  
	public Map<String, Object> findFsyqfraConsumptionAnalysisViewD(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		ArrayList<String> valueArr = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		
		StringBuffer hql = new StringBuffer();
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("memberOrderTotalMoney")){//memberOrderTotalMoney | 会员付款订单总金额
			hql.append(" SELECT ");
			hql.append(" DATE_FORMAT(payTime, "+MyTool.getValueToStrByHashMapKey(hsMap,"myDATE_FORMAT")+") as M ,IFNULL(sum(payAmount),0) as qian ");
			hql.append(" FROM DRP_REQUIREGOODSORDER WHERE ");
			hql.append("     payTime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}
			hql.append(" and isTemp !=\'1\' and type = '1'  GROUP BY M ");
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("orderMemberIdDistinct")){//orderMemberIdDistinct | 会员付款订单对应会员人数，去重，Distinct
			hql.append(" SELECT DATE_FORMAT(payTime, "+MyTool.getValueToStrByHashMapKey(hsMap,"myDATE_FORMAT")+") as M ,count(DISTINCT(customerAccount_id)) as renshu ");
			hql.append(" FROM DRP_REQUIREGOODSORDER WHERE ");
			hql.append("     payTime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}
			hql.append(" and isTemp !=\'1\' and type = '1'  and customerAccount_id is not null  GROUP BY M ");
		}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			ArrayList<MapBean> dayArr = MyTool.getMapBeanZeroArray_ByDateStringArray(objTimeArr);
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<dayArr.size();x++){
					String hkey = dayArr.get(x).getKey();
					for(int y=0;y<ttList.size();y++){
						String tkey = ttList.get(y)[0].toString();
						if(hkey.equals(tkey)){
							dayArr.set(x, new MapBean(hkey,ttList.get(y)[1].toString()));
						}
					}
				}
			}
			for(int x=0;x<dayArr.size();x++){
				valueArr.add(dayArr.get(x).getValue());
			}
			
		hsMapReturn.put("valueArr", valueArr);
		return hsMapReturn;
	}
	@Override   /** 会员画像分析> 顾客流失风险A主力客户 |查询主力客户    */
	public Map<String, Object> findFsycftaMainCustomer(Map<String, Object> hsMap) throws Exception { 
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		
		StringBuffer hql = new StringBuffer();//#查讯最近1个月消费次数在3次及其以上的主力顾客有多少人
		hql.append(" select count(*),'1'  from "); 
		hql.append(" ( ");
		hql.append(" SELECT ");
		hql.append(" many.customerAccount_id as HYid, ");
		hql.append(" count(*) as goumaicishu  ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER many ");
		hql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone ON many.customerAccount_id = toone.id ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     many.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		hql.append(" and  many.isTemp !=\'1\' and  many.type ='1' ");
		hql.append(" GROUP BY ");
		hql.append(" many.customerAccount_id ");
		hql.append(" )A where A.goumaicishu >="+MyTool.getValueToStrByHashMapKey(hsMap,"frequency")+" ");
		 
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	@Override /** 会员画像分析> 顾客流失风险  | B 新客户 | frequency 频率，次数|新顾客：只消费1次的顾客*/
	public Map<String, Object> findFsmcftaNewCustomer(Map<String, Object> hsMap) throws Exception { 
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		
		StringBuffer hql = new StringBuffer();
		hql.append(" select count(*),'1'  from "); 
		hql.append(" ( ");
		hql.append(" SELECT ");
		hql.append(" many.customerAccount_id as HYid, ");
		hql.append(" count(*) as goumaicishu  ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER many ");
		hql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone ON many.customerAccount_id = toone.id ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" WHERE ");
		hql.append("     many.payTime is not null ");
		hql.append(" and many.isTemp !=\'1\' and  many.type ='1' ");
		hql.append(" GROUP BY ");
		hql.append(" many.customerAccount_id ");
		hql.append(" )A where A.goumaicishu ="+MyTool.getValueToStrByHashMapKey(hsMap,"frequency")+" ");
		 
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	@Override /** 会员画像分析> 顾客流失风险  | C 已经流失顾客 | frequency 频率，次数 AlreadyLost*/
	public Map<String, Object> findFsmcftaAlreadyLostCustomer(Map<String, Object> hsMap) throws Exception { 
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
									 
		StringBuffer hql = new StringBuffer();
		hql.append(" select count(*),'1' from CRM_CUSTOMERACCOUNT WHERE id not in  ");   
		hql.append(" ( ");
		hql.append(" SELECT ");
		hql.append(" many.customerAccount_id as HYid ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER many ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
		hql.append("     many.payTime >= "+ MyTool.StringUseToSql(object.get(0)));
		hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}	
		hql.append(" and many.isTemp !=\'1\'  and  many.type ='1'  ");
		hql.append(" and many.customerAccount_id is not null ");
		hql.append(" GROUP BY ");
		hql.append(" many.customerAccount_id ");
		hql.append(" ) ");
		hql.append(" and CREATETIME is not null and CREATETIME < "+ MyTool.StringUseToSql(object.get(0)));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	@Override /** 会员画像分析> 顾客流失风险  | D 将要流失顾客 | frequency 频率，次数WillLoss 将要流失*/
	public Map<String, Object> findFsmcftaWillLossCustomer(Map<String, Object> hsMap) throws Exception { 
		//例如： 将流失顾客：最近1个月未到店消费，而之前的最近上个月来过店消费的顾客
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
							 
		StringBuffer hql = new StringBuffer();
		hql.append(" select id from CRM_CUSTOMERACCOUNT WHERE id not in  "); 
		hql.append(" ( ");
		hql.append(" SELECT ");
		hql.append(" many.customerAccount_id as HYid ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER many ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> timeArrStart = (ArrayList<String>)hsMap.get("timeArrStart");          
		hql.append("     many.payTime >= "+ MyTool.StringUseToSql(timeArrStart.get(0)));
		hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrStart.get(timeArrStart.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}	
		hql.append(" and many.customerAccount_id is not null ");
		hql.append("  and   many.isTemp !=\'1\'  and  many.type ='1' ");
		hql.append(" GROUP BY ");
		hql.append(" many.customerAccount_id ");
		hql.append(" ) ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
								 
		StringBuffer hqlTwo = new StringBuffer();
		hqlTwo.append(" SELECT ");
		hqlTwo.append(" many.customerAccount_id as HYidTwo ");
		hqlTwo.append(" FROM ");
		hqlTwo.append(" DRP_REQUIREGOODSORDER many ");
		hqlTwo.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> timeArrEnd = (ArrayList<String>)hsMap.get("timeArrEnd");          
		hqlTwo.append("     many.payTime >= "+ MyTool.StringUseToSql(timeArrEnd.get(0)));
		hqlTwo.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrEnd.get(timeArrEnd.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hqlTwo.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hqlTwo.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hqlTwo.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hqlTwo.append(" and 1=2 ");  
		}	
		hqlTwo.append(" and many.customerAccount_id is not null ");
		hqlTwo.append("  and   many.isTemp !=\'1\'  and  many.type ='1' ");
		hqlTwo.append(" GROUP BY ");
		hqlTwo.append(" many.customerAccount_id ");
								 
		StringBuffer hqlThree = new StringBuffer();
		String hqlA = "("+hql.toString()+")";
		String hqlB = "("+hqlTwo.toString()+")";
		hqlThree.append(" select count(*),'1' from CRM_CUSTOMERACCOUNT WHERE id in "+hqlA);
		hqlThree.append(" and id in "+hqlB); 
		 
			List<Object[]> ttList = queryDataDao.findAllBySql(hqlThree.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	@Override /** 会员画像分析> highValueMember 高价值客户分析 |A 忠诚客户   |Loyal 忠诚  **/
	public Map<String, Object> findFvmqftaLoyalCustomer(Map<String, Object> hsMap) throws Exception { 
		//例如： 忠诚客户：最近1个月消费3次及其以上并且最近上个月也消费3次及其以上的客户
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		
		StringBuffer hql = new StringBuffer();
		hql.append(" select A.HYid from "); 
		hql.append(" ( ");
		hql.append(" SELECT ");
		hql.append(" many.customerAccount_id as HYid, ");
		hql.append(" count(*) as goumaicishu  ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER many ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> timeArrStart = (ArrayList<String>)hsMap.get("timeArrStart");          
		hql.append("     many.payTime >= "+ MyTool.StringUseToSql(timeArrStart.get(0)));
		hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrStart.get(timeArrStart.size()-1))));
		hql.append(" and many.isTemp !=\'1\' and many.type='1' ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" and many.customerAccount_id is not null ");
		hql.append(" GROUP BY ");
		hql.append(" many.customerAccount_id ");
		hql.append(" )A where A.goumaicishu >="+MyTool.getValueToStrByHashMapKey(hsMap,"frequency")+" ");
								 
		StringBuffer hqlTwo = new StringBuffer();
		hqlTwo.append(" select B.HYidTwo from "); 
		hqlTwo.append(" ( ");
		hqlTwo.append(" SELECT ");
		hqlTwo.append(" many.customerAccount_id as HYidTwo, ");
		hqlTwo.append(" count(*) as goumaicishuB  ");
		hqlTwo.append(" FROM ");
		hqlTwo.append(" DRP_REQUIREGOODSORDER many ");
		hqlTwo.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> timeArrEnd = (ArrayList<String>)hsMap.get("timeArrEnd");          
		hqlTwo.append("     many.payTime >= "+ MyTool.StringUseToSql(timeArrEnd.get(0)));
		hqlTwo.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrEnd.get(timeArrEnd.size()-1))));
		hqlTwo.append(" and many.customerAccount_id is not null ");
		hqlTwo.append(" and  many.isTemp !=\'1\'  and many.type='1'  ");
		if(hsMap.containsKey("tenantId")){
			hqlTwo.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hqlTwo.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hqlTwo.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hqlTwo.append(" and 1=2 ");  
		}
		hqlTwo.append(" GROUP BY ");
		hqlTwo.append(" many.customerAccount_id ");
		hqlTwo.append(" )B where B.goumaicishuB >="+MyTool.getValueToStrByHashMapKey(hsMap,"frequency")+" ");
								 
		StringBuffer hqlThree = new StringBuffer();
		String hqlA = "("+hql.toString()+")";
		String hqlB = "("+hqlTwo.toString()+")";
		hqlThree.append(" select count(*),'1' from CRM_CUSTOMERACCOUNT WHERE id in "+hqlA);
		hqlThree.append(" and id in "+hqlB); 

			List<Object[]> ttList = queryDataDao.findAllBySql(hqlThree.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	@Override /** 会员画像分析> highValueMember 高价值客户分析 |B 瞌睡客户   |Doze 瞌睡  **/
	public Map<String, Object> findFvmqftaDozeCustomer(Map<String, Object> hsMap) throws Exception { 
		//例如： 忠诚客户：最近1个月消费3次及其以上并且最近上个月也消费3次及其以上的客户
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		
		StringBuffer hql = new StringBuffer();
		hql.append(" select A.HYid from "); 
		hql.append(" ( ");
		hql.append(" SELECT ");
		hql.append(" many.customerAccount_id as HYid, ");
		hql.append(" count(*) as goumaicishu  ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER many ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> timeArrStart = (ArrayList<String>)hsMap.get("timeArrStart");          
		hql.append("     many.payTime >= "+ MyTool.StringUseToSql(timeArrStart.get(0)));
		hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrStart.get(timeArrStart.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" and many.isTemp !=\'1\'  and many.type='1'   ");
		hql.append(" and many.customerAccount_id is not null ");
		hql.append(" GROUP BY ");
		hql.append(" many.customerAccount_id ");
		hql.append(" )A where A.goumaicishu >0 and A.goumaicishu <"+MyTool.getValueToStrByHashMapKey(hsMap,"frequency")+" ");
								 
		StringBuffer hqlTwo = new StringBuffer();
		hqlTwo.append(" select B.HYidTwo from "); 
		hqlTwo.append(" ( ");
		hqlTwo.append(" SELECT ");
		hqlTwo.append(" many.customerAccount_id as HYidTwo, ");
		hqlTwo.append(" count(*) as goumaicishuB  ");
		hqlTwo.append(" FROM ");
		hqlTwo.append(" DRP_REQUIREGOODSORDER many ");
		hqlTwo.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> timeArrEnd = (ArrayList<String>)hsMap.get("timeArrEnd");          
		hqlTwo.append("     many.payTime >= "+ MyTool.StringUseToSql(timeArrEnd.get(0)));
		hqlTwo.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrEnd.get(timeArrEnd.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hqlTwo.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hqlTwo.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hqlTwo.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hqlTwo.append(" and 1=2 ");  
		}
		hqlTwo.append(" and many.isTemp !=\'1\'  and many.type='1'   ");
		hqlTwo.append(" and many.customerAccount_id is not null ");
		hqlTwo.append(" GROUP BY ");
		hqlTwo.append(" many.customerAccount_id ");
		hqlTwo.append(" )B where B.goumaicishuB >="+MyTool.getValueToStrByHashMapKey(hsMap,"frequency")+" ");
								 
		StringBuffer hqlThree = new StringBuffer();
		String hqlA = "("+hql.toString()+")";
		String hqlB = "("+hqlTwo.toString()+")";
		hqlThree.append(" select count(*),'1' from CRM_CUSTOMERACCOUNT WHERE id in "+hqlA);
		hqlThree.append(" and id in "+hqlB); 

			List<Object[]> ttList = queryDataDao.findAllBySql(hqlThree.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	@Override /** 会员画像分析> highValueMember 高价值客户分析 |C 半睡客户   |HalfAsleep半睡  **/
	public Map<String, Object> findFvmmftaHalfAsleepCustomer(Map<String, Object> hsMap) throws Exception { 
		//例如： 忠诚客户：最近1个月消费3次及其以上并且最近上个月也消费3次及其以上的客户
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		
		StringBuffer hql = new StringBuffer();
		hql.append(" select count(A.HYid),'1' from "); 
		hql.append(" ( ");
		hql.append(" SELECT ");
		hql.append(" many.customerAccount_id as HYid, ");
		hql.append(" count(*) as goumaicishu  ");
		hql.append(" FROM ");
		hql.append(" DRP_REQUIREGOODSORDER many ");
		hql.append(" WHERE ");
		@SuppressWarnings("unchecked")
		ArrayList<String> timeArrStart = (ArrayList<String>)hsMap.get("timeArrStart");          
		hql.append("     many.payTime >= "+ MyTool.StringUseToSql(timeArrStart.get(0)));
		hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrStart.get(timeArrStart.size()-1))));
		if(hsMap.containsKey("tenantId")){
			hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}else{
			hql.append(" and 1=2 ");  
		}
		hql.append(" and many.isTemp !=\'1\'  and many.type='1'  ");
		hql.append(" and many.customerAccount_id is not null ");
		hql.append(" GROUP BY ");
		hql.append(" many.customerAccount_id ");
		hql.append(" )A where A.goumaicishu >0 and A.goumaicishu <"+MyTool.getValueToStrByHashMapKey(hsMap,"frequency")+" ");
		
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	@Override    /**  客户会员相关数据或其他...Souli是个随机数  **/  
	public Map<String, Object> findCustomerDataSouliA(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("selfExtendStringField1")){
		StringBuffer hql = new StringBuffer();// selfExtendStringField1   查询会员开店到现在消费总金额  
		hql.append(" SELECT "); 
		hql.append(" IFNULL(sum(payAmount),0) AS XFje,'1' "); 
		hql.append(" from "); 
		hql.append(" DRP_REQUIREGOODSORDER "); 
		hql.append(" where payTime is not null and isTemp !=\'1\'  and type = '1' ");  
		hql.append(" and customerAccount_id ="+MyTool.StringUseToSql(MyTool.getValueToStrByHashMapKey(hsMap,"customerID"))+" ");
		if(hsMap.containsKey("tenantId")){
			hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
		}
		if(hsMap.containsKey("companyInnerCode")){
			hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
		}
		if(hsMap.containsKey("channelDistributorId")){
			hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
		}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			if(ttList !=null && ttList.size()>0){
				  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
			}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("remainderMoneySufngtfk")){
			StringBuffer hql = new StringBuffer();// remainderMoneySufngtfk   查询会员卡总余额
			hql.append(" SELECT "); 
			hql.append(" IFNULL(sum(many.money),0),'1' ");
			hql.append(" FROM ");
			hql.append(" CRM_CUSTOMERACCOUNTCILP many "); 
			hql.append(" inner join CRM_CUSTOMERACCOUNT toone on many.customerAccount_id = toone.id and ( many.ISTEMP != '1') ");
			hql.append(" and toone.id ="+MyTool.StringUseToSql(MyTool.getValueToStrByHashMapKey(hsMap,"customerID"))+" ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
			}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("selfExtendStringField1Money")){
			StringBuffer hql = new StringBuffer();// selfExtendStringField1Money  查询当前时间消费总金额 
			hql.append(" SELECT IFNULL(sum(payAmount),0) AS XFje,'1' "); 
			hql.append(" from DRP_REQUIREGOODSORDER "); 
			hql.append(" where payTime is not null and isTemp !=\'1\' and type = '1' ");   
			hql.append(" and customerAccount_id ="+MyTool.StringUseToSql(MyTool.getValueToStrByHashMapKey(hsMap,"customerID"))+" ");
			if(hsMap.containsKey("startTimeMoney") && hsMap.containsKey("endTimeMoney")){
			hql.append(" and payTime >= "+ MyTool.StringUseToSql(MyTool.getValueToStrByHashMapKey(hsMap,"startTimeMoney")));
			hql.append(" and payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(MyTool.getValueToStrByHashMapKey(hsMap,"endTimeMoney"))));
			} 
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}		
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("rechargeTotalSuuqo")){
			StringBuffer hql = new StringBuffer();// rechargeTotalSuuqo  查询当前时间充值总金额
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.payMoney),0) AS CZje,'1' ");
			hql.append(" FROM ");
			hql.append(" CRM_CUSTOMERACCOUNT tooneB ");
			hql.append(" inner JOIN  CRM_CUSTOMERACCOUNTCILP tooneA on tooneA.customerAccount_id = tooneB.id and ( tooneA.ISTEMP != '1') ");
			hql.append(" inner JOIN  CRM_RECHARGERECORD many on many.customerAccountClip_id = tooneA.id and  (many.isTemp!='1') ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}	
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArrRecharge");     
			hql.append(" and many.payDate >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and many.payDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" and many.payMoney is not null ");
			hql.append(" and tooneA.customerAccount_id ="+MyTool.StringUseToSql(MyTool.getValueToStrByHashMapKey(hsMap,"customerID"))+" ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("NewAddSnkli")){
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
			StringBuffer hql = new StringBuffer();//NewAddSnkli查询新增会员的相关数量
			hql.append(" SELECT  count(*) AS sl,'1' from  CRM_CUSTOMERACCOUNT  where CREATETIME is not null "); 
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}	
			hql.append(" and CREATETIME >= "+ MyTool.StringUseToSql(timeArr.get(0)));
			hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1)))); 
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("OrderNumSnkki")){
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
			StringBuffer hql = new StringBuffer();//OrderNumSnkki查询‘本月订单数’|订单数目，付款或者不付款都查询
			hql.append(" SELECT  count(*) AS sl,'1' from DRP_REQUIREGOODSORDER where CREATETIME is not null and isTemp !=\'1\'  and type = '1' ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");  
			}	
			hql.append(" and CREATETIME >= "+ MyTool.StringUseToSql(timeArr.get(0)));
			hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1)))); 
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("BirthdayMemberSbkli")){
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
			StringBuffer hql = new StringBuffer();//BirthdayMemberSbkli查询‘过生日的会员’的相关数量
			hql.append(" SELECT "); 
			hql.append(" count(*) AS sl,'1' "); 
			hql.append(" from "); 
			hql.append(" CRM_CUSTOMERACCOUNT ");
			hql.append(" where birthday is not null "); 
			if(timeArr != null && timeArr.size()>0  ){
				String startTime = MyTool.queryBirthdayBYyyyyMMddTwo(timeArr.get(0));
				String endTime = MyTool.queryBirthdayBYyyyyMMddTwo(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1)));//先加一天，在取出‘11-08’月日
				hql.append(" and DATE_FORMAT(birthday,'%m-%d') >= "+ MyTool.StringUseToSql(startTime));
				hql.append(" and DATE_FORMAT(birthday,'%m-%d') <  "+ MyTool.StringUseToSql(endTime));
			} 
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("MoneyMemberSnuli")){
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
			StringBuffer fullSql = new StringBuffer();//MoneyMemberSnuli 查询'会员消费'的相关数量
			if(timeStr.equals("ThisMonthOT")){
				fullSql.append(" SELECT IFNULL(sum(many.payAmount),0) AS XFje,count(DISTINCT(many.customerAccount_id)) AS renshu  from DRP_REQUIREGOODSORDER many ");
			    fullSql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone on many.customerAccount_id = toone.id and many.type = '1' ");
			    if(hsMap.containsKey("tenantId")){
			    	fullSql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
				}
				if(hsMap.containsKey("companyInnerCode")){
					fullSql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
				}
				if(hsMap.containsKey("channelDistributorId")){
					fullSql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
				}else{
					fullSql.append(" and 1=2 ");  
				}	
				fullSql.append(" where many.customerAccount_id is not null and many.isTemp !=\'1\' ");
				fullSql.append(" and many.payTime is not null ");
				if(timeArr != null && timeArr.size()>0  ){
					String startTime = timeArr.get(0);
					String endTime = MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1));
					fullSql.append(" and many.payTime >= "+ MyTool.StringUseToSql(startTime));
					fullSql.append(" and many.payTime <  "+ MyTool.StringUseToSql(endTime));
				}
			}else{
				fullSql.append(" SELECT IFNULL(sum(many.payAmount),0) AS XFje,'1' from DRP_REQUIREGOODSORDER many ");
			    fullSql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone on many.customerAccount_id = toone.id  and many.type = '1' ");
			    if(hsMap.containsKey("tenantId")){
			    	fullSql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
				}
				if(hsMap.containsKey("companyInnerCode")){
					fullSql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
				}
				if(hsMap.containsKey("channelDistributorId")){
					fullSql.append(" and many.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
				}else{
					fullSql.append(" and 1=2 ");  
				}
				fullSql.append(" where many.customerAccount_id is not null and many.isTemp !=\'1\'  ");
				fullSql.append(" and many.payTime is not null ");
				if(timeArr != null && timeArr.size()>0  ){
					String startTime = timeArr.get(0);
					String endTime = MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1));
					fullSql.append(" and many.payTime >= "+ MyTool.StringUseToSql(startTime));
					fullSql.append(" and many.payTime <  "+ MyTool.StringUseToSql(endTime));
				}
			}
			Double totalDoublePeopleNum = 0.0;//人数
				List<Object[]> ttList = queryDataDao.findAllBySql(fullSql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
					  totalDoublePeopleNum =  Double.parseDouble(ttList.get(0)[1].toString());
				}
				hsMapReturn.put("totalDoublePeopleNum", totalDoublePeopleNum);
		}
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("CardTermMemberSbtli")){
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
			StringBuffer fullSql = new StringBuffer();//CardTermMemberSbtli查询‘会员卡过期会员’数量
			fullSql.append(" SELECT count(DISTINCT(many.customerAccount_id)) AS renshu,'1'  from CRM_CUSTOMERACCOUNTCILP many ");
			fullSql.append(" inner join CRM_CUSTOMERACCOUNT toone on many.customerAccount_id = toone.id ");
			if(hsMap.containsKey("tenantId")){
				fullSql.append(" and many.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				fullSql.append(" and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				fullSql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				fullSql.append(" and 1=2 ");
			}
			fullSql.append(" where many.customerAccount_id is not null and ( many.ISTEMP != '1')  ");
			fullSql.append(" and many.expiryDate is not null ");
			if(timeArr != null && timeArr.size()>0  ){
				String startTime = timeArr.get(0);
				String endTime = MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1));
				fullSql.append(" and many.expiryDate >= "+ MyTool.StringUseToSql(startTime));
				fullSql.append(" and many.expiryDate <  "+ MyTool.StringUseToSql(endTime));
			}
				List<Object[]> ttList = queryDataDao.findAllBySql(fullSql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	@Override    /**  查询sql的返回一个数值  **/  
	public Map<String, Object> findGoodsDataSujrop(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("warehouseGoodsINSvyhg")){
			StringBuffer hql = new StringBuffer();//warehouseGoodsINSvyhg 今日入库商品总数(种类)
			hql.append(" SELECT ");
			hql.append(" COUNT(DISTINCT(many.itemcode)) ,'1' ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ");
			hql.append(" ON ");
			hql.append(" many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");   
			hql.append(" toone.istemp != '1' ");
			hql.append(" ) ");
			hql.append(" AND toone.flag = '1' ");//1 入库
			hql.append(" AND many.itemcode  is not null and  many.itemcode <> '' and many.quantity > 0 ");//切记，判断商品名称不为NULL，也不是空字符串
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> objTimeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);         
			hql.append(" and many.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("warehouseGoodsOUTSzyhg")){
			StringBuffer hql = new StringBuffer();//warehouseGoodsOUTSzyhg 今日出库商品总数(种类)
			hql.append(" SELECT ");
			hql.append(" COUNT(DISTINCT(many.itemcode)) ,'1' ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ");
			hql.append(" ON ");
			hql.append(" many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");   
			hql.append(" toone.istemp != '1' ");
			hql.append(" ) ");
			hql.append(" AND toone.flag = '2' ");//flag 2 出库
			hql.append(" AND many.itemcode  is not null and  many.itemcode <> ''  and many.quantity > 0 ");//切记，判断商品名称不为NULL，也不是空字符串
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> objTimeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);         
			hql.append(" and many.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("nowStockGoodsNumSzphg")){
			/*StringBuffer hql = new StringBuffer();// nowStockGoodsNumSzphg 现存商品量(多少种)
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			ArrayList<String> objTimeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);         
			//#查询 ‘到现在时，库存数量有多少种的sql语句，（进库量-出库量=余数，余数必须大于0）’
			//#说明因为是left join,所以  (A.AspSL-IFNULL(C.CSL,0))中，前面A.AspSL已经判断不会为null，而C.CSL是右表会出现null
			hql.append(" select  count(Q.yushu),'1' from ( ");
			hql.append(" select (A.AspSL-IFNULL(C.CSL,0)) as yushu from (  ");
			hql.append(" SELECT ");
			hql.append(" many.itemcode AS AspName, ");
			hql.append(" sum(many.quantity) AS AspSL ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");
			hql.append(" toone.istemp != '1' ");
			hql.append(" OR toone.istemp IS NULL ");
			hql.append(" ) ");
			hql.append(" AND toone.flag = '1' ");
			hql.append(" AND many.itemcode IS NOT NULL ");
			hql.append(" AND many.itemcode <> '' ");
			hql.append(" and many.quantity > 0 ");
			hql.append(" AND many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" GROUP BY ");
			hql.append(" AspName ");
			hql.append(" )A LEFT JOIN ");
			hql.append(" (select B.BspName as CNAME,B.BspSL AS CSL FROM( ");
			hql.append(" SELECT ");
			hql.append(" many.itemcode AS BspName, ");
			hql.append(" sum(many.quantity) AS BspSL ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");
			hql.append(" toone.istemp != '1' ");
			hql.append(" OR toone.istemp IS NULL ");
			hql.append(" ) ");
			hql.append(" AND toone.flag = '2' ");
			hql.append(" AND many.itemcode IS NOT NULL ");
			hql.append(" AND many.itemcode <> '' ");
			hql.append(" and many.quantity > 0 ");
			hql.append(" AND many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" GROUP BY ");
			hql.append(" BspName)B)C  ");
			hql.append(" on ");
			hql.append("  C.CNAME = A.AspName )Q  where  Q.yushu  > 0 ");*/
			
			StringBuffer hql = new StringBuffer();// nowStockGoodsNumSzphg 现存商品量(多少种)
			//查询 本月过期现存商品(多少种) 不用从入库表和出库表中查询，应该从‘存货档案清单(现存量汇总表)’ inv_inventory 查询即可  InventoryCurrentStock
			hql.append(" select count(DISTINCT(itemcode)),'1'  from inv_inventory "); 
			hql.append(" where avaquantity > 0  ");
			hql.append(" and itemcode is not null  and itemcode <> '' ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");
			}
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("invalidGoodsNumSzhhg")){
			/*StringBuffer hql = new StringBuffer();//invalidGoodsNumSzhhg 本月过期现存商品(多少种)
			String timeStrA = MyTool.getValueToStrByHashMapKey(hsMap,"timeStrA");
			ArrayList<String> timeArrA = MyTool.getTimeArrByHtmlParameterString(timeStrA); 
				String timeStrB = MyTool.getValueToStrByHashMapKey(hsMap,"timeStrB");
				ArrayList<String> timeArrB = MyTool.getTimeArrByHtmlParameterString(timeStrB); 
			hql.append(" select  count(Q.yushu),'1' from ( ");
			hql.append(" select (A.AspSL-IFNULL(C.CSL,0)) as yushu from (  ");
			hql.append(" SELECT ");
			hql.append(" many.itemcode AS AspName, ");
			hql.append(" sum(many.quantity) AS AspSL ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");
			hql.append(" toone.istemp != '1' ");
			hql.append(" OR toone.istemp IS NULL ");
			hql.append(" ) ");
			hql.append(" AND toone.flag = '1' ");
			hql.append(" AND many.itemcode IS NOT NULL ");
			hql.append(" AND many.itemcode <> '' ");
			hql.append(" and many.quantity > 0 ");
			hql.append(" AND many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrA.get(timeArrA.size()-1))));
			hql.append(" and many.massunitEndTime >= "+ MyTool.StringUseToSql(timeArrB.get(0)));
			hql.append(" and many.massunitEndTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrB.get(timeArrB.size()-1))));
			hql.append(" GROUP BY ");
			hql.append(" AspName ");
			hql.append(" )A LEFT JOIN ");
			hql.append(" (select B.BspName as CNAME,B.BspSL AS CSL FROM( ");
			hql.append(" SELECT ");
			hql.append(" many.itemcode AS BspName, ");
			hql.append(" sum(many.quantity) AS BspSL ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");
			hql.append(" toone.istemp != '1' ");
			hql.append(" OR toone.istemp IS NULL ");
			hql.append(" ) ");
			hql.append(" AND toone.flag = '2' ");
			hql.append(" AND many.itemcode IS NOT NULL ");
			hql.append(" AND many.itemcode <> '' ");
			hql.append(" and many.quantity > 0 ");
			hql.append(" AND many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrA.get(timeArrA.size()-1))));
			hql.append(" and many.massunitEndTime >= "+ MyTool.StringUseToSql(timeArrB.get(0)));
			hql.append(" and many.massunitEndTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrB.get(timeArrB.size()-1))));
			hql.append(" GROUP BY ");
			hql.append(" BspName)B)C  ");
			hql.append(" on ");
			hql.append("  C.CNAME = A.AspName )Q  where  Q.yushu  > 0 ");*/
			
			//查询 本月过期现存商品(多少种) 不用从入库表和出库表中查询，应该从‘存货档案清单(现存量汇总表)’ inv_inventory 查询即可  InventoryCurrentStock
			String timeStrB = MyTool.getValueToStrByHashMapKey(hsMap,"timeStrB");
			List<String> timeArrB = MyTool.getTimeArrByHtmlParameterString(timeStrB); 
			StringBuffer hql = new StringBuffer();
			hql.append(" select count(DISTINCT(itemcode)),'1'  from inv_inventory "); 
			hql.append(" where avaquantity > 0   and itemcode is not null  and itemcode <> ''  ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");
			}
			hql.append(" and massunitEndTime >= "+ MyTool.StringUseToSql(timeArrB.get(0)));
			hql.append(" and massunitEndTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrB.get(timeArrB.size()-1))));
			
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("notEnoughGoodsNumSzpxg") || 
		   MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("tooMuchGoodsNumSzpvg") ){
			/*StringBuffer hql = new StringBuffer();
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			ArrayList<String> objTimeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);         */
			//notEnoughGoodsNumSzpxg 库存不足预警商品(多少种) 或者 //tooMuchGoodsNumSzpvg 库存积压预警商品(多少种)
			/*hql.append(" select  count(Q.yushu),'1' from ( ");
			hql.append(" select (A.AspSL-IFNULL(C.CSL,0)) as yushu from (  ");
			hql.append(" SELECT ");
			hql.append(" many.itemcode AS AspName, ");
			hql.append(" sum(many.quantity) AS AspSL ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");
			hql.append(" toone.istemp != '1' ");
			hql.append(" OR toone.istemp IS NULL ");
			hql.append(" ) ");
			hql.append(" AND toone.flag = '1' ");
			hql.append(" AND many.itemcode IS NOT NULL ");
			hql.append(" AND many.itemcode <> '' ");
			hql.append(" and many.quantity > 0 ");
			hql.append(" AND many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" GROUP BY ");
			hql.append(" AspName ");
			hql.append(" )A LEFT JOIN ");
			hql.append(" (select B.BspName as CNAME,B.BspSL AS CSL FROM( ");
			hql.append(" SELECT ");
			hql.append(" many.itemcode AS BspName, ");
			hql.append(" sum(many.quantity) AS BspSL ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");
			hql.append(" toone.istemp != '1' ");
			hql.append(" OR toone.istemp IS NULL ");
			hql.append(" ) ");
			hql.append(" AND toone.flag = '2' ");
			hql.append(" AND many.itemcode IS NOT NULL ");
			hql.append(" AND many.itemcode <> '' ");
			hql.append(" and many.quantity > 0 ");
			hql.append(" AND many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" GROUP BY ");
			hql.append(" BspName)B)C  ");
			hql.append(" on ");
			hql.append("  C.CNAME = A.AspName )Q  where 1=1 ");
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"sentenceSQL"));*/
					//查询 本月过期现存商品(多少种) 不用从入库表和出库表中查询，应该从‘存货档案清单(现存量汇总表)’ inv_inventory 查询即可  InventoryCurrentStock
			StringBuffer hql = new StringBuffer();
			hql.append(" select count(DISTINCT(itemcode)),'1'  from inv_inventory "); 
			hql.append(" where 1=1  ");
			hql.append(" and itemcode is not null  and itemcode <> '' ");
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"sentenceSQL"));
				 
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("listGoodsNumSfgklmopz")){
			StringBuffer hql = new StringBuffer();//listGoodsNumSfgklmopz 查询列表中的‘商品数量’
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.quantity), 0) AS spSL ,'1' ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");
			hql.append(" toone.istemp != '1' ");
			hql.append(" ) ");
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"flagSQL"));
			hql.append(" AND many.itemcode IS NOT NULL ");
			hql.append(" AND many.itemcode <> '' ");
		    hql.append(" AND many.itemcode ='"+MyTool.getValueToStrByHashMapKey(hsMap,"itemcode")+"' ");
			hql.append(" AND many.quantity > 0 ");
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL"));
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"mytitleSQL"));
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"mynumSQL"));
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("listGoodsMoneySfgklmapz")){
			StringBuffer hql = new StringBuffer();//listGoodsMoneySfgklmapz 查询列表中的‘总价’
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.price), 0) AS spSL ,'1' ");
			hql.append(" FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			hql.append(" AND ( ");
			hql.append(" toone.istemp != '1' ");
			hql.append(" ) ");
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"flagSQL"));
			hql.append(" AND many.itemcode IS NOT NULL ");
			hql.append(" AND many.itemcode <> '' ");
		    hql.append(" AND many.itemcode ='"+MyTool.getValueToStrByHashMapKey(hsMap,"itemcode")+"' ");
			hql.append(" AND many.quantity > 0 ");
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL"));
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"mytitleSQL"));
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"mynumSQL"));
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("listGoodsLatelyTimeSfgklmakz")){
			StringBuffer hql = new StringBuffer();
			hql.append(" select  DATE_FORMAT(A.firstPayTime, '%Y-%m-%d %H:%i:%s' ),'1' from ( ");
			hql.append(" SELECT many.CREATETIME  as firstPayTime FROM ");//listGoodsLatelyTimeSfgklmakz 查询列表中的‘最近入库时间’或‘最近出库时间’【注意这里两个通用listGoodsLatelyTimeSfgklmakz】
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and toone.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and toone.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");
			}
			hql.append(" AND ( ");
			hql.append(" toone.istemp != '1' ");
			hql.append(" ) ");
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"flagSQL"));
			hql.append(" AND many.itemcode IS NOT NULL ");
			hql.append(" AND many.itemcode <> '' ");
		    hql.append(" AND many.itemcode ='"+MyTool.getValueToStrByHashMapKey(hsMap,"itemcode")+"' ");
			hql.append(" AND many.quantity > 0 ");
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL"));
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"mytitleSQL"));
			hql.append(MyTool.getValueToStrByHashMapKey(hsMap,"mynumSQL"));
			hql.append(" ORDER BY many.CREATETIME DESC "); 
			hql.append(" )A LIMIT 0,1 ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					String	stringRTinner =  ttList.get(0)[0].toString();
					hsMapReturn.put("stringRTinner", stringRTinner);
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("byManyItemcodeSijodvn")){
			StringBuffer hql = new StringBuffer();
			hql.append(" select A.mid,'1' from ( ");
			hql.append(" SELECT many.id  as mid FROM ");
			hql.append(" inv_stockrecordlines many ");
			hql.append(" INNER JOIN inv_stockrecords toone ON many.invstockrecords_id = toone.id ");
			if(hsMap.containsKey("tenantId")){
				hql.append(" and toone.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and toone.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			if(hsMap.containsKey("channelDistributorId")){
				hql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+hsMap.get("channelDistributorId")+"'");  
			}else{
				hql.append(" and 1=2 ");
			}
			hql.append(" AND ( ");////byManyItemcodeSijodvn 根据‘商品编码’查询最近的StockRecordLines出现的id
			hql.append(" toone.istemp != '1' ");
			hql.append(" ) ");
			hql.append(" AND many.itemcode IS NOT NULL  AND many.itemcode <> '' ");
		    hql.append(" AND many.itemcode ='"+MyTool.getValueToStrByHashMapKey(hsMap,"itemcode")+"' ");
			hql.append(" ORDER BY many.CREATETIME DESC "); 
			hql.append(" )A LIMIT 0,1 ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					String	stringRTinner =  ttList.get(0)[0].toString();
					hsMapReturn.put("stringRTinner", stringRTinner);
				}
		}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	
	///////////////////////////////////////////////////////////////////// 
	
	@Override    /** 最近30日销售商品数量按客户排行Top10  **/ 
	public Map<String, Object> findAnalysisTopViewAScdsvb(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuffer hql = new StringBuffer();
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("supplierSaleNumByStoreSavey")){
			String setSupplierID = queryDifferentIDsAbcd(MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierID"));
			//#查询 供应商给门店送货数量按门店进行top  Sql
			hql = new StringBuffer();
			hql.append(" select tooneB.name,B.bsl from( ");
			hql.append(" SELECT ");//supplierSaleNumByStoreSavey 供应商销售商品数量按客户（门店）排行
			hql.append(" A.dianpu as bdianpu, ");
			hql.append(" A.sl as bsl ");
			hql.append(" FROM ");
			hql.append(" ( ");
			hql.append(" SELECT ");
			hql.append(" toone.channelDistributor_id AS dianpu, ");
			hql.append(" IFNULL(sum(many.AMOUNT), 0) AS sl ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id "); 
			hql.append(" AND toone.channelDistributor_id IS NOT NULL ");
			hql.append(" AND toone.channelDistributor_id <> '' ");
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" AND toone.STATUS = '3' "); 
			hql.append(" and toone.SUPPLIER_ID is not null  and toone.SUPPLIER_ID <> '' ");
			hql.append(" and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" GROUP BY ");
			hql.append(" toone.channelDistributor_id ");  
			hql.append(" ORDER BY ");
			hql.append(" sl DESC ");
			hql.append(" ) A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
			hql.append(" )B INNER JOIN DRP_CHANNELDISTRIBUTOR tooneB ON B.bdianpu = tooneB.id ");
			hql.append(" AND tooneB.name  is not null and tooneB.name <> '' ");
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("supplierSaleMoneyByStoreSiuey")){
			String setSupplierID = queryDifferentIDsAbcd(MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierID"));
			hql = new StringBuffer();
			hql.append(" select tooneB.name,B.bsl from( ");
			hql.append(" SELECT ");//supplierSaleMoneyByStoreSiuey 供应商销售商品金额按客户（门店）排行
			hql.append(" A.dianpu as bdianpu, ");
			hql.append(" A.sl as bsl ");
			hql.append(" FROM ");
			hql.append(" ( ");
			hql.append(" SELECT ");
			hql.append(" toone.channelDistributor_id AS dianpu, ");
			hql.append(" IFNULL(sum(many.PRICE), 0) AS sl ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id "); 
			hql.append(" AND toone.channelDistributor_id IS NOT NULL ");
			hql.append(" AND toone.channelDistributor_id <> '' ");
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" AND toone.STATUS = '3' "); //AND toone. STATUS = '3' 代表已经完成的
			hql.append(" and toone.SUPPLIER_ID is not null  and toone.SUPPLIER_ID <> '' ");
			hql.append(" and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" GROUP BY ");
			hql.append(" toone.channelDistributor_id ");  
			hql.append(" ORDER BY ");
			hql.append(" sl DESC ");
			hql.append(" ) A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
			hql.append(" )B INNER JOIN DRP_CHANNELDISTRIBUTOR tooneB ON B.bdianpu = tooneB.id ");
			hql.append(" AND tooneB.name  is not null and tooneB.name <> '' ");
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("supplierSaleNumByPeopleSaey")){
			String setSupplierID = queryDifferentIDsAbcd(MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierID"));
			//#查询 供应商给门店送货数量按门店进行top  Sql  
			hql = new StringBuffer();
			hql.append(" SELECT ");//supplierSaleNumByPeopleSaey 供应商销售商品数量按客户（人）排行
			hql.append(" A.dianpu as bdianpu, ");
			hql.append(" A.sl as bsl ");
			hql.append(" FROM ");
			hql.append(" ( ");
			hql.append(" SELECT ");
			hql.append(" toone.purchasePerson AS dianpu, ");
			hql.append(" IFNULL(sum(many.AMOUNT), 0) AS sl ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id "); 
			hql.append(" AND toone.purchasePersonId IS NOT NULL ");
			hql.append(" AND toone.purchasePersonId <> '' ");
			hql.append(" AND toone.purchasePerson IS NOT NULL ");
			hql.append(" AND toone.purchasePerson <> '' ");
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" AND toone.STATUS = '3' "); 
			hql.append(" and toone.SUPPLIER_ID is not null  and toone.SUPPLIER_ID <> '' ");
			hql.append(" and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" GROUP BY ");
			hql.append(" toone.purchasePersonId ");  
			hql.append(" ORDER BY ");
			hql.append(" sl DESC ");
			hql.append(" ) A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("supplierSaleMoneyByPeopleSc")){
			String setSupplierID = queryDifferentIDsAbcd(MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierID"));
			//#查询 供应商给门店送货数量按门店进行top  Sql  
			hql = new StringBuffer();
			hql.append(" SELECT ");//supplierSaleMoneyByPeopleSc 供应商销售商品数量按客户（人）排行
			hql.append(" A.dianpu as bdianpu, ");
			hql.append(" A.sl as bsl ");
			hql.append(" FROM ");
			hql.append(" ( ");
			hql.append(" SELECT ");
			hql.append(" toone.purchasePerson AS dianpu, ");
			hql.append(" IFNULL(sum(many.PRICE), 0) AS sl ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id "); 
			hql.append(" AND toone.purchasePersonId IS NOT NULL ");
			hql.append(" AND toone.purchasePersonId <> '' ");
			hql.append(" AND toone.purchasePerson IS NOT NULL ");
			hql.append(" AND toone.purchasePerson <> '' ");
			@SuppressWarnings("unchecked")
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" AND toone.STATUS = '3' "); 
			hql.append(" and toone.SUPPLIER_ID is not null  and toone.SUPPLIER_ID <> '' ");
			hql.append(" and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" GROUP BY ");
			hql.append(" toone.purchasePersonId ");  
			hql.append(" ORDER BY ");
			hql.append(" sl DESC ");
			hql.append(" ) A LIMIT 0,"+MyTool.getValueToStrByHashMapKey(hsMap,"topNum")+" ");
		}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			ArrayList<String> nameArr = new ArrayList<String>();
			ArrayList<String> valueArr = new ArrayList<String>();
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<ttList.size();x++){
					nameArr.add(ttList.get(x)[0].toString());
					valueArr.add(ttList.get(x)[1].toString());
				}
			}
			 Integer topNum = Integer.parseInt(MyTool.getValueToStrByHashMapKey(hsMap,"topNum"));
			 if( nameArr.size() < topNum ){
				 for(int x=0;x<topNum;x++){
					 nameArr.add("");
					 valueArr.add("0");
					 if(nameArr.size() == topNum){
						 break;
					 }
				 }
			 }
			 for (int start = 0, end = nameArr.size() - 1; start < end; start++, end--) {
				 String temp = nameArr.get(end);
				 nameArr.set(end, nameArr.get(start));
				 nameArr.set(start, temp);
			 }
			 for (int start = 0, end = valueArr.size() - 1; start < end; start++, end--) {
				 String temp = valueArr.get(end);
				 valueArr.set(end, valueArr.get(start));
				 valueArr.set(start, temp);
			 }
			String jsonStrReturn = "{\"a\":"+1+"}";
			Gson gosn = new Gson();
			if(hsMap.containsKey("moneyTo-Two-Decimal-")){//因为是钱，所有要保留2位小数
				for(int x=0;x<valueArr.size();x++){
					String valueNum = valueArr.get(x);
					Double numA = MyTool.roundingTwoDecimal(Double.parseDouble(valueNum));
					String numB = MyTool.formatObjToNumStr(numA);
					valueArr.set(x, numB);
				}
			}
					
			String strA ="{\"nameArr\":"+gosn.toJson(nameArr)+"}";
			String strB ="{\"valueArr\":"+ valueArr.toString() +"}";
			jsonStrReturn = MyTool.mergeJsonStringTwo(strA,strB);
		hsMapReturn.put("jsonStrReturn", jsonStrReturn);	
		return hsMapReturn;
	}
	@Override    /**  查询sql的返回一个数值  **/  
	public Map<String, Object> findDataRtOneUajoop(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("supplierSaleGoodsNumUrvp")){
			StringBuffer hql = new StringBuffer();//supplierSaleGoodsNumUrvp 供应商统计报表 |销售商品数量 (种类)
			String setSupplierID = queryDifferentIDsAbcd(MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierID"));
			hql.append(" SELECT ");//#查询供应商销售商品种数
			hql.append(" count(DISTINCT(many.ITEMCODE) ) ,'1' ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ");
			hql.append(" ON ");
			hql.append(" many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and many.ITEMCODE <> '' and many.ITEMCODE IS NOT NULL and many.ITEMname <> '' and many.ITEMname IS NOT NULL ");
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> objTimeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);         
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("goodsNumMeyBykhidUiiuk")){
			StringBuffer hql = new StringBuffer();//goodsNumMeyBykhidUiiuk 根据和客户id查询采购商品数量和金额
			String setSupplierID = MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierID");
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.AMOUNT), 0),IFNULL(sum(many.PRICE), 0) "); 
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ");
			hql.append(" ON ");
			hql.append(" many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and many.ITEMCODE <> '' and many.ITEMCODE IS NOT NULL and many.ITEMname <> '' and many.ITEMname IS NOT NULL ");
			@SuppressWarnings("unchecked")
			ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("objTimeArr");
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" and toone.channelDistributor_id ='"+MyTool.getValueToStrByHashMapKey(hsMap,"channelDistributorid")+"' "); 
			
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());//数量合计
					  Double doubleInnerTwo =  Double.parseDouble(ttList.get(0)[1].toString());//金额合计
					  hsMapReturn.put("doubleInnerTwo", doubleInnerTwo);
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("supplierSaleGoodsMeyUrvp")){
			StringBuffer hql = new StringBuffer();//supplierSaleGoodsMeyUrvp 供应商统计报表 |销售商品金额
			String setSupplierID = queryDifferentIDsAbcd(MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierID"));
			hql.append(" SELECT ");//#查询供应商销售商品种数
			hql.append(" IFNULL(sum(many.PRICE), 0),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ");
			hql.append(" ON ");
			hql.append(" many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and many.ITEMCODE <> '' and many.ITEMCODE IS NOT NULL and many.ITEMname <> '' and many.ITEMname IS NOT NULL ");
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> objTimeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);         
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("supplierSaleOrderNumUnm")){
			StringBuffer hql = new StringBuffer();//supplierSaleOrderNumUnm 供应商统计报表 |今日销售订单数量
			String setSupplierID = queryDifferentIDsAbcd(MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierID"));
			hql.append(" SELECT ");
			hql.append(" count(*),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_order toone ");
			hql.append(" where ");
			hql.append(" toone.STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> objTimeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);         
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("supplierSaleOrderCustomNumUin")){
			StringBuffer hql = new StringBuffer();//supplierSaleOrderCustomNumUin 供应商统计报表 |今日客户数量   
			String setSupplierID = queryDifferentIDsAbcd(MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierID"));
			hql.append(" SELECT ");
			hql.append(" count(DISTINCT(toone.channelDistributor_id)),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_order toone ");
			hql.append(" where ");
			hql.append(" toone.STATUS = '3' AND toone.channelDistributor_id <> '' and toone.channelDistributor_id IS NOT NULL");
			hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> objTimeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);         
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("suplierSaleNewAddGsNumUin")){
			StringBuffer hql = new StringBuffer();//suplierSaleNewAddGsNumUin 供应商统计报表 |本月新增销售商品    
			String setSupplierID = queryDifferentIDsAbcd(MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierID"));
			hql.append(" SELECT ");
			hql.append(" count(DISTINCT(many.ITEMCODE)),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ");
			hql.append(" ON ");
			hql.append(" many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and many.ITEMCODE <> '' and many.ITEMCODE IS NOT NULL and many.ITEMname <> '' and many.ITEMname IS NOT NULL ");
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> objTimeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);         
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" and many.ITEMCODE not in ( ");
			hql.append(" SELECT ");
			hql.append(" DISTINCT(many.ITEMCODE) ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ");
			hql.append(" ON ");
			hql.append(" many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and many.ITEMCODE <> '' and many.ITEMCODE IS NOT NULL and many.ITEMname <> '' and many.ITEMname IS NOT NULL ");
			hql.append(" and toone.CREATETIME < "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" ) ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("suplierSaleNewAddCustomNumUnq")){
			StringBuffer hql = new StringBuffer();//suplierSaleNewAddCustomNumUnq 供应商统计报表 |本月新增客户
			String setSupplierID = queryDifferentIDsAbcd(MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierID"));
			hql.append(" SELECT ");
			hql.append(" count(DISTINCT(toone.channelDistributor_id)),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_order toone ");
			hql.append(" where ");
			hql.append(" toone.STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and toone.channelDistributor_id <> '' and toone.channelDistributor_id IS NOT NULL ");
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");
			List<String> objTimeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);         
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" and toone.channelDistributor_id not in ( ");
			hql.append(" SELECT ");
			hql.append(" DISTINCT(toone.channelDistributor_id) ");
			hql.append(" FROM ");
			hql.append(" pur_order toone ");
			hql.append(" where ");
			hql.append(" toone.STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and toone.channelDistributor_id <> '' and toone.channelDistributor_id IS NOT NULL ");
			hql.append(" and toone.CREATETIME < "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" ) ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("MDDDLBPurchaseGoodsNumUio")){
			StringBuffer hql = new StringBuffer();//MDDDLBPurchaseGoodsNumUio 门店订单统计列表 采购商品总数量
			String setSupplierID = MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql");
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.AMOUNT), 0),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.channelDistributor_id IS NOT NULL ");
			hql.append(" AND toone.channelDistributor_id <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQLipml")+" ");
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and toone.channelDistributor_id = '"+MyTool.getValueToStrByHashMapKey(hsMap,"mendianID")+"' ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("MDDDLBPurchaseGoodsMeyUio")){
			StringBuffer hql = new StringBuffer();//MDDDLBPurchaseGoodsMeyUio 门店订单统计列表 采购商品总金额
			String setSupplierID = MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql");
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.PRICE), 0),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.channelDistributor_id IS NOT NULL ");
			hql.append(" AND toone.channelDistributor_id <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQLipml")+" ");
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and toone.channelDistributor_id = '"+MyTool.getValueToStrByHashMapKey(hsMap,"mendianID")+"' ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("ZBPurchaseGoodsPersonMeyUid")){
			StringBuffer hql = new StringBuffer();//ZBPurchaseGoodsPersonMeyUid 总部订单统计列表 总部采购人总金额
			String setSupplierID = MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql");
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.PRICE), 0),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.purchasePersonId IS NOT NULL ");
			hql.append(" AND toone.purchasePersonId <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL")+" ");
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and toone.purchasePersonId = '"+MyTool.getValueToStrByHashMapKey(hsMap,"personID")+"' ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("ZBPurchaseGoodsPersonNumbUid")){
			StringBuffer hql = new StringBuffer();//ZBPurchaseGoodsPersonNumbUid 总部订单统计列表 总部采购人总数量
			String setSupplierID = MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql");
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.AMOUNT), 0),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.purchasePersonId IS NOT NULL ");
			hql.append(" AND toone.purchasePersonId <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL")+" ");
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and toone.purchasePersonId = '"+MyTool.getValueToStrByHashMapKey(hsMap,"personID")+"' ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("MDDDLBPurchaseGoodsTimeUio")){
			StringBuffer hql = new StringBuffer();//MDDDLBPurchaseGoodsTimeUio 门店订单统计列表 最近交易时间（采购订单创建时间）
			String setSupplierID = MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql");
			hql.append(" select  DATE_FORMAT(A.firstPayTime, '%Y-%m-%d' ),'1' from ( "); 
			hql.append(" SELECT ");
			hql.append(" toone.CREATETIME as firstPayTime ");
			hql.append(" FROM ");
			hql.append(" pur_order toone ");
			hql.append(" where toone.channelDistributor_id IS NOT NULL ");
			hql.append(" AND toone.channelDistributor_id <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQLipml")+" ");
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and toone.channelDistributor_id = '"+MyTool.getValueToStrByHashMapKey(hsMap,"mendianID")+"' ");
			hql.append(" ORDER BY toone.CREATETIME desc ");
			hql.append(" )A LIMIT 0,1 ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					String	stringRTinner =  ttList.get(0)[0].toString();
					hsMapReturn.put("stringRTinner", stringRTinner);
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("ZBPurchaseGoodsPersonTimeUid")){
			StringBuffer hql = new StringBuffer();//ZBPurchaseGoodsPersonTimeUid 总部订单统计列表 最近交易时间（采购订单创建时间）
			String setSupplierID = MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql");
			hql.append(" select  DATE_FORMAT(A.firstPayTime, '%Y-%m-%d' ),'1' from ( "); 
			hql.append(" SELECT ");
			hql.append(" toone.CREATETIME as firstPayTime ");
			hql.append(" FROM ");
			hql.append(" pur_order toone ");
			hql.append(" where toone.purchasePersonId IS NOT NULL ");
			hql.append(" AND toone.purchasePersonId <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL")+" ");
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and toone.purchasePersonId = '"+MyTool.getValueToStrByHashMapKey(hsMap,"personID")+"' ");
			hql.append(" ORDER BY toone.CREATETIME desc ");
			hql.append(" )A LIMIT 0,1 ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					String	stringRTinner =  ttList.get(0)[0].toString();
					hsMapReturn.put("stringRTinner", stringRTinner);
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("ZBPurchaseGoodsPersonUic")){
			StringBuffer hql = new StringBuffer();//ZBPurchaseGoodsPersonUic 总部订单统计列表 总部采购人姓名
			String setSupplierID = MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql");
			hql.append(" select  IFNULL( A.tpurchasePerson, ''),'1' from ( "); 
			hql.append(" SELECT ");
			hql.append(" toone.purchasePerson as tpurchasePerson ");
			hql.append(" FROM ");
			hql.append(" pur_order toone ");
			hql.append(" where toone. STATUS = '3' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL")+" ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and toone.purchasePersonId = '"+MyTool.getValueToStrByHashMapKey(hsMap,"personID")+"' ");
			hql.append(" AND toone.purchasePerson IS NOT NULL ");
			hql.append(" AND toone.purchasePerson <> '' ");
			hql.append(" ORDER BY toone.CREATETIME desc ");
			hql.append(" )A LIMIT 0,1 ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					String	stringRTinner =  ttList.get(0)[0].toString();
					hsMapReturn.put("stringRTinner", stringRTinner);
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("detailedUuiuyjglk")){
			StringBuffer hql = new StringBuffer();//detailedUuiuyjglk 门店订单统计列表>查看pur_orderlineitem的ID
			hql.append(" select  A.firstid,'1' from ( "); 
			hql.append(" SELECT ");
			hql.append(" many.id as firstid ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.channelDistributor_id IS NOT NULL ");
			hql.append(" AND toone.channelDistributor_id <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL")+" "); 
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql")+" "); 
			hql.append(" and toone.channelDistributor_id = '"+MyTool.getValueToStrByHashMapKey(hsMap,"mdid")+"' ");  
			hql.append(" and many.itemCode is not null ");
			hql.append(" and many.itemCode = '"+MyTool.getValueToStrByHashMapKey(hsMap,"itemCode")+"' ");  
			hql.append(" order by toone.CREATETIME desc ");
			hql.append(" )A LIMIT 0,1 ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					String	stringRTinner =  ttList.get(0)[0].toString();
					hsMapReturn.put("stringRTinner", stringRTinner);
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("detailedUuiDFyjglk")){
			StringBuffer hql = new StringBuffer();//detailedUuiDFyjglk 总部订单统计列表>查看pur_orderlineitem的ID
			hql.append(" select  A.firstid,'1' from ( "); 
			hql.append(" SELECT ");
			hql.append(" many.id as firstid ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.purchasePersonId IS NOT NULL ");
			hql.append(" AND toone.purchasePersonId <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL")+" "); 
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql")+" "); 
			hql.append(" and toone.purchasePersonId = '"+MyTool.getValueToStrByHashMapKey(hsMap,"personid")+"' ");  
			hql.append(" and many.itemCode is not null ");
			hql.append(" and many.itemCode = '"+MyTool.getValueToStrByHashMapKey(hsMap,"itemCode")+"' ");  
			hql.append(" order by toone.CREATETIME desc ");
			hql.append(" )A LIMIT 0,1 ");
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					String	stringRTinner =  ttList.get(0)[0].toString();
					hsMapReturn.put("stringRTinner", stringRTinner);
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("detailedTotalNumUuiuyjglk")){
			StringBuffer hql = new StringBuffer();//detailedTotalNumUuiuyjglk 门店订单统计列表>查看采购商品总数量
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.AMOUNT), 0),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.channelDistributor_id IS NOT NULL ");
			hql.append(" AND toone.channelDistributor_id <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL")+" "); 
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql")+" "); 
			hql.append(" and toone.channelDistributor_id = '"+MyTool.getValueToStrByHashMapKey(hsMap,"mdid")+"' ");  
			hql.append(" and many.itemCode is not null ");
			hql.append(" and many.itemCode = '"+MyTool.getValueToStrByHashMapKey(hsMap,"itemCode")+"' ");  
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("detailedTotalNumUuiDFuyjglk")){
			StringBuffer hql = new StringBuffer();//detailedTotalNumUuiDFuyjglk 总部订单统计列表>查看采购商品总数量
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.AMOUNT), 0),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.purchasePersonId IS NOT NULL ");
			hql.append(" AND toone.purchasePersonId <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL")+" "); 
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql")+" "); 
			hql.append(" and toone.purchasePersonId = '"+MyTool.getValueToStrByHashMapKey(hsMap,"personid")+"' ");  
			hql.append(" and many.itemCode is not null ");
			hql.append(" and many.itemCode = '"+MyTool.getValueToStrByHashMapKey(hsMap,"itemCode")+"' ");  
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("detailedTotalMeyUuiuyjglk")){
			StringBuffer hql = new StringBuffer();//detailedTotalMeyUuiuyjglk 门店订单统计列表>查看采购商品总金额
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.PRICE), 0),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.channelDistributor_id IS NOT NULL ");
			hql.append(" AND toone.channelDistributor_id <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL")+" "); 
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql")+" "); 
			hql.append(" and toone.channelDistributor_id = '"+MyTool.getValueToStrByHashMapKey(hsMap,"mdid")+"' ");  
			hql.append(" and many.itemCode is not null ");
			hql.append(" and many.itemCode = '"+MyTool.getValueToStrByHashMapKey(hsMap,"itemCode")+"' ");  
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		else if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("detailedTotalMeyUuiudFyjglk")){
			StringBuffer hql = new StringBuffer();//detailedTotalMeyUuiudFyjglk 总部订单统计列表>查看采购商品总金额  
			hql.append(" SELECT ");
			hql.append(" IFNULL(sum(many.PRICE), 0),'1' ");
			hql.append(" FROM ");
			hql.append(" pur_orderlineitem many ");
			hql.append(" INNER JOIN pur_order toone ON many.PURCHASEORDER_ID = toone.id ");
			hql.append(" AND toone.purchasePersonId IS NOT NULL ");
			hql.append(" AND toone.purchasePersonId <> '' ");
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"timeSQL")+" "); 
			hql.append(" AND toone. STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID IS NOT NULL ");
			hql.append(" AND toone.SUPPLIER_ID <> '' ");
			hql.append(" AND toone.SUPPLIER_ID in "+MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql")+" "); 
			hql.append(" and toone.purchasePersonId = '"+MyTool.getValueToStrByHashMapKey(hsMap,"personid")+"' ");  
			hql.append(" and many.itemCode is not null ");
			hql.append(" and many.itemCode = '"+MyTool.getValueToStrByHashMapKey(hsMap,"itemCode")+"' ");  
				List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	@Override    /**   最近30日采购订单视图 等  折线图统计  **/  
	public Map<String, Object> lookupDateBrokenlineViewUcbf(Map<String, Object> hsMap) throws Exception {
		String setSupplierID = MyTool.getValueToStrByHashMapKey(hsMap,"setSupplierIDSql");
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		ArrayList<String> valueArr = new ArrayList<String>();
		@SuppressWarnings("unchecked")  
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");  
		
		StringBuffer hql = new StringBuffer();
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("PurchaseOrderNumbUabc")){
			hql = new StringBuffer();
			hql.append(" SELECT ");//PurchaseOrderNumbUabc 最近30日采购订单数量视图
			hql.append(" DATE_FORMAT(toone.CREATETIME, '%Y-%m-%d' ) as M,count(*) ");
			hql.append(" FROM ");
			hql.append(" pur_order toone ");
			hql.append(" where ");
			hql.append(" toone.STATUS = '3' ");
			hql.append(" AND toone.SUPPLIER_ID is not null and toone.SUPPLIER_ID <> '' and toone.SUPPLIER_ID in "+setSupplierID+" "); 
			hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" "+MyTool.getValueToStrByHashMapKey(hsMap,"conditionSQL")+" ");//type  订单类型0,门店采购订单;1,总部采购订单;
			hql.append(" GROUP BY M ");
		}
			List<Object[]> ttList = queryDataDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
			ArrayList<MapBean> dayArr = MyTool.getMapBeanZeroArray_ByDateStringArray(objTimeArr);
			if(ttList !=null && ttList.size()>0){
				for(int x=0;x<dayArr.size();x++){
					String hkey = dayArr.get(x).getKey();
					for(int y=0;y<ttList.size();y++){
						String tkey = ttList.get(y)[0].toString();
						if(hkey.equals(tkey)){
							dayArr.set(x, new MapBean(hkey,ttList.get(y)[1].toString()));
						}
					}
				}
			}
			for(int x=0;x<dayArr.size();x++){
				valueArr.add(dayArr.get(x).getValue());
			}
		hsMapReturn.put("valueArr", valueArr);
		return hsMapReturn;
	}
	/**  根据参数获得不同的店铺id，或者供应商id,或者....id **/
	@Override
	public String queryDifferentIDsAbcd(String setSupplierID) throws Exception {
		StringBuffer hql = new StringBuffer();
		
		if(setSupplierID.equals("{S---tH-d}all-Supplier-ID{E---dT-l}")){//{S---tH-d}all-Supplier-ID{E---dT-l}  定死的
			hql = new StringBuffer();
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("status", "3");//正式的3
			List<Supplier> sList = this.findAllByConditions(Supplier.class, params);
			if(sList != null){
				if(sList.size()>0){
					for(int x=0;x<sList.size();x++){
						if(x==0){
							hql.append("\'"+sList.get(x).getId()+"\'");
						}
						if(x!=0){
							hql.append(",\'"+sList.get(x).getId()+"\'");
						}
					}
				}
			}
		}else{
			 hql = new StringBuffer();
			 hql.append(MyTool.analysisJsonStringSix(setSupplierID));
		}
		if(!hql.toString().contains("'")){
			hql.append("\'"+"\'");
		}
		return ("("+hql.toString()+")");//
	}
}
