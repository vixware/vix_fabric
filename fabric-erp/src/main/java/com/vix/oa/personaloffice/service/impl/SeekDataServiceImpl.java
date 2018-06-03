package com.vix.oa.personaloffice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.properties.util.MyTool;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.nvix.common.base.dao.IVixntBaseDao;
import com.vix.oa.personaloffice.service.ISeekDataService;


@Service("seekDataService")
public class SeekDataServiceImpl extends BaseHibernateServiceImpl implements ISeekDataService {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IVixntBaseDao vixntBaseDao;
	
	@Override    /**  查讯返回一个值  **/  
	public Map<String, Object> seekReturnOneDataSoulLiA(Map<String, Object> hsMap) throws Exception {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		Double totalDouble = 0.0;
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("doBusinessIncomeFigureAvBn")){
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");//doBusinessIncomeFigureAvBn 计算‘营业收入’，其中AvBn是随机字母后缀，防止重复
			List<String> timeArrObj = MyTool.getTimeArrByHtmlParameterString(timeStr);
			String setDistributorIDBrackets = MyTool.getValueToStrByHashMapKey(hsMap,"setDistributorIDBrackets");//('1','2')
				StringBuffer hql = new StringBuffer();
				hql.append(" SELECT ");
				if(MyTool.getValueToStrByHashMapKey(hsMap,"conditionSQL").equals("sumMoney")){
				hql.append(" IFNULL(sum(many.payAmount), 0),'1' ");//查询自 订单，payTime付款时间  payAmount 实付金额  //sumMoney是多少 钱的合计
				}
				if(MyTool.getValueToStrByHashMapKey(hsMap,"conditionSQL").equals("countOrdersNum")){
				hql.append(" count(many.id),'1' ");//countOrdersNum是多少 笔的合计
				}
				hql.append(" FROM ");
				hql.append(" DRP_REQUIREGOODSORDER many INNER JOIN DRP_CHANNELDISTRIBUTOR toone on many.channelDistributor_id = toone.id "); 
				hql.append(" and many.channelDistributor_id in "+setDistributorIDBrackets+" "); 
				hql.append(" and many.type = '1' ");//#交易类型(POS会员消费1;门店消费2;) 
				hql.append(" and many.payTime >= "+ MyTool.StringUseToSql(timeArrObj.get(0)));
				hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrObj.get(timeArrObj.size()-1))));
				hql.append(" and many.isTemp != '1' ");
				List<Object[]> ttList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("rechargeIncomeFigureAvBn")){
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");//rechargeIncomeFigureAvBn 计算  储值收入
			List<String> timeArrObj = MyTool.getTimeArrByHtmlParameterString(timeStr);
			String setDistributorIDBrackets = MyTool.getValueToStrByHashMapKey(hsMap,"setDistributorIDBrackets");//('1','2')
				StringBuffer hql = new StringBuffer();
				hql.append(" SELECT ");
				if(MyTool.getValueToStrByHashMapKey(hsMap,"conditionSQL").equals("sumMoney")){
				hql.append(" IFNULL(sum(many.payMoney), 0),'1' ");//查询自 订单，payTime付款时间  payAmount 实付金额  //sumMoney是多少 钱的合计
				}
				if(MyTool.getValueToStrByHashMapKey(hsMap,"conditionSQL").equals("countOrdersNum")){
				hql.append(" count(many.id),'1' ");//countOrdersNum是多少 笔的合计
				}
			hql.append(" FROM ");
			hql.append(" CRM_RECHARGERECORD many where many.isTemp!='1' "); 
			hql.append(" and many.channelDistributor_id in "+setDistributorIDBrackets+" "); 
			hql.append(" and many.payDate >= "+ MyTool.StringUseToSql(timeArrObj.get(0)));
			hql.append(" and many.payDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrObj.get(timeArrObj.size()-1))));
				List<Object[]> ttList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		if(MyTool.getValueToStrByHashMapKey(hsMap,"controlSQL").equals("discountFigureAviBn")){
			String timeStr = MyTool.getValueToStrByHashMapKey(hsMap,"timeStr");//discountFigureAviBn 计算折扣
			List<String> timeArrObj = MyTool.getTimeArrByHtmlParameterString(timeStr);
			String setDistributorIDBrackets = MyTool.getValueToStrByHashMapKey(hsMap,"setDistributorIDBrackets");//('1','2')
				StringBuffer hql = new StringBuffer();
				hql.append(" SELECT ");
				if(MyTool.getValueToStrByHashMapKey(hsMap,"conditionSQL").equals("sumMoney")){
				hql.append(" IFNULL(sum(many.discountFee),0),'1' ");//查询 折扣多少钱
				}
				if(MyTool.getValueToStrByHashMapKey(hsMap,"conditionSQL").equals("countOrdersNum")){
				hql.append(" count(DISTINCT(many.REQUIREGOODSORDER_ID)),'1' ");// 折扣大于0的多少笔
				}
			hql.append(" FROM ");
			hql.append(" DRP_REQUIREGOODSORDERITEM many INNER JOIN  DRP_REQUIREGOODSORDER toone on many.REQUIREGOODSORDER_ID = toone.id ");
			hql.append(" and toone.id in( ");
			hql.append(" SELECT ");
			hql.append(" many.id ");
			hql.append(" FROM ");
			hql.append(" DRP_REQUIREGOODSORDER many ");
			hql.append(" where ");
			hql.append(" many.channelDistributor_id in "+setDistributorIDBrackets+" "); 
			hql.append(" AND many.type = '1' ");
			hql.append(" and many.payTime >= "+ MyTool.StringUseToSql(timeArrObj.get(0)));
			hql.append(" and many.payTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrObj.get(timeArrObj.size()-1))));
			hql.append(" AND many.isTemp != '1' ");
			hql.append(" ) ");
			hql.append(" and many.discountFee > 0 ");
				List<Object[]> ttList = vixntBaseDao.findAllBySql(hql.toString(),new HashMap<String,Object>());
				if(ttList !=null && ttList.size()>0){
					  totalDouble =  Double.parseDouble(ttList.get(0)[0].toString());
				}
		}
		hsMapReturn.put("totalDouble", totalDouble);
		return hsMapReturn;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**  根据用户账号及其角色     查询符合条件符合权限的的 门店集合  ***/
	@Override
	public List<ChannelDistributor> seekChannelDistributorList(Map<String, Object> hsMap) throws Exception{
		hsMap.put("id," + SearchCondition.NOEQUAL, "---1uUj---");//现在先用恒等式限制条件，将来 params.put 条件即可
		//params.put("status", "3");//现在不知道哪些ChannelDistributor的条件限制是正确的，所有先不设置条件
		List<ChannelDistributor> sList = this.findAllByConditions(ChannelDistributor.class, hsMap);
		return sList;
	}
	/**  根据传入参数的不同，控制查询门店的id不同 ；用于拼接sql ；
	 *  **/
	@Override
	public String seekDifferentIDsAbcd(String setChannelDistributorID) throws Exception {
		StringBuffer hql = new StringBuffer();
		
		if(setChannelDistributorID.equals("{S---tH-d}all-C---lD---r-ID{E---dT-l}")){//{S---tH-d}all-C---lD---r-ID{E---dT-l}  定死的
			hql = new StringBuffer();//{S---tH-d}all-C---lD---r-ID{E---dT-l} 是  {S---tH-d}all-ChannelDistributor-ID{E---dT-l} 的简写  用于查询符合条件的所有的 门店id
			Map<String, Object> params = new HashMap<String,Object>();
			List<ChannelDistributor> sList = this.seekChannelDistributorList(params);
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
			 hql.append(MyTool.analysisJsonStringSix(setChannelDistributorID));
		}
		if(!hql.toString().contains("'")){
			hql.append("\'"+"\'");
		}
		return ("("+hql.toString()+")");//
	}
	
}
