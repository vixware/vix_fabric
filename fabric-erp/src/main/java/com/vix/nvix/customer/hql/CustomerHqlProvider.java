package com.vix.nvix.customer.hql;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.common.properties.util.MyTool;
import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class CustomerHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}
		/** 查询 客户关系管理 > 客户管理 > 客户统计 饼图 (条件1)  **/
	public StringBuilder findCustomerTypeList(Map<String, Object> params) throws ParseException {
		StringBuilder hql = new StringBuilder();
		hql.append(" select t.`name`, count(*) n,t.id from ");
		hql.append(" crm_customeraccount c ");
		hql.append(" left join crm_b_customertype t on c.customertype_id = t.id ");
		hql.append((params.containsKey("tenantId"))? " and c.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
		hql.append((params.containsKey("companyInnerCode"))? " and c.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
		if(params.containsKey("timeArr")){
			@SuppressWarnings("unchecked")
			ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
			hql.append(" and c.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and c.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		}
		hql.append(" where type = 'enterprise' ");
		hql.append(" and istemp = '0' ");
		hql.append(" and isdeleted = '0' ");
		hql.append(" and t.`name` is not null and t.name <> '' ");
		hql.append(" group by t.id ");
		hql.append(" order by n desc ");
		return hql;
	}
	/** 查询 客户关系管理 > 客户管理 > 客户统计 饼图 (条件2)  **/
	public StringBuilder findCustomerStageList(Map<String, Object> params) throws ParseException {
		StringBuilder hql = new StringBuilder();
		hql.append(" select t.`name`, count(*) n,t.id from ");
		hql.append(" crm_customeraccount c left join crm_b_customerstage t on c.customerstage_id = t.id ");
		hql.append((params.containsKey("tenantId"))? " and c.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
		hql.append((params.containsKey("companyInnerCode"))? " and c.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
		if(params.containsKey("timeArr")){
			@SuppressWarnings("unchecked")
			ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
			hql.append(" and c.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and c.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		}
		hql.append(" where type = 'enterprise' ");
		hql.append(" and istemp = '0' ");
		hql.append(" and isdeleted = '0' ");
		hql.append(" and t.`name` is not null and t.name <> '' ");
		hql.append(" group by t.id ");
		hql.append(" order by n desc "); 
		return hql;
	}
	/** 查询 客户关系管理 > 客户管理 > 客户统计 饼图 (本方法查询目标的type必须以customer开头如'customerRelationshipClass','关系等级')  **/
	@SuppressWarnings("unchecked")
	public StringBuilder findCustomerListByControlSQL(Map<String, Object> params) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if((params.get("type")+"").toString().equals("customerHotLevel")){//customerHotLevel热点程度
			hql.append(" select t.`name`, count(*) n,t.id from ");
			hql.append(" crm_customeraccount m inner join CRM_B_HOTLEVEL t on m.hotLevel_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and t.name is not null and t.name <> '' ");    
			hql.append(" group by t.id ");
			hql.append(" order by n desc "); 
		}else if((params.get("type")+"").toString().equals("customerCreditLevel")){//customerCreditLevel 信用等级;
			hql.append(" select ( case m.creditlevel when '1' then '高' when '2' then '中' when '3' then '低' else m.creditlevel end ) as levelstr, count(*) n,m.creditlevel from ");
			hql.append(" crm_customeraccount m where 1=1  ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and m.creditLevel is not null and m.creditLevel <> '' ");    
			hql.append(" group by m.creditLevel  order by n desc ");
		}else if((params.get("type")+"").toString().equals("customerRelationshipClass")){//customerRelationshipClass关系等级
			hql.append(" select t.name, count(*) n,t.id from ");
			hql.append(" crm_customeraccount m inner join CRM_B_RELATIONSHIPCLASS t on m.relationshipClass_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and t.name is not null and t.name <> '' ");    
			hql.append(" group by t.id  order by n desc ");
		}else if((params.get("type")+"").toString().equals("customerStaffScale")){//customerStaffScale人员规模
			hql.append(" select t.name, count(*) n,t.id from ");
			hql.append(" crm_customeraccount m inner join CRM_B_STAFFSCALE t on m.staffScale_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and t.id is not null and t.name <> '' ");    
			hql.append(" group by t.id  order by n desc ");
		}else if((params.get("type")+"").toString().equals("customerCustomerSource")){//customerCustomerSource来源
			hql.append(" select t.name, count(*) n,t.id from ");
			hql.append(" crm_customeraccount m inner join CRM_B_CUSTOMERSOURCE t on m.customerSource_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and t.name is not null and t.name <> '' ");    
			hql.append(" group by t.id  order by n desc ");
		}else if((params.get("type")+"").toString().equals("customerIndustry")){//customerIndustry所属行业
			hql.append(" select t.name, count(*) n,t.id from ");
			hql.append(" crm_customeraccount m inner join CRM_B_INDUSTRY t on m.industry_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and t.name is not null and t.name <> '' ");    
			hql.append(" group by t.id  order by n desc ");
		}else if((params.get("type")+"").toString().equals("customerValueAssessment")){//customerValueAssessment价值评估
			hql.append(" select ( case m.valueAssessment when '1' then '高' when '2' then '中' when '3' then '低' else m.valueAssessment end ) as str, count(*) n,m.valueAssessment from ");
			hql.append(" crm_customeraccount m where 1=1  ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and m.valueAssessment is not null and m.valueAssessment <> '' ");    
			hql.append(" group by m.valueAssessment  order by n desc ");
		}
		return hql;
	}
	/** 查询 客户关系管理 > 客户管理 > 客户统计 饼图2 (本方法查询目标的type必须以cstomer开头如"cstomerAccountType": "账户类型") **/
	@SuppressWarnings("unchecked")
	public StringBuilder findCustomerListByControlSQLStr(Map<String, Object> params) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if((params.get("type")+"").toString().equals("cstomerAccountType")){//cstomerAccountType账户类型
			hql.append(" select t.name, count(*) n,t.id from ");
			hql.append(" crm_customeraccount m inner join CRM_B_ACCOUNTTYPE t on m.accountType_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and t.name is not null and t.name <> '' ");    
			hql.append(" group by t.id  order by n desc ");
		}else if((params.get("type")+"").toString().equals("cstomerProvince")){//cstomerProvince省份分布
			hql.append(" select t.name, count(*) n,t.id from ");
			hql.append(" crm_customeraccount m inner join SYSTEM_ADDRESSINFO t on m.province_ID = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and t.name is not null and t.name <> '' ");    
			hql.append(" group by t.id  order by n desc ");
		}else if((params.get("type")+"").toString().equals("cstomerCity")){//cstomerCity城市分布
			hql.append(" select t.name, count(*) n,t.id from ");
			hql.append(" crm_customeraccount m inner join SYSTEM_ADDRESSINFO t on m.city_ID = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and t.name is not null and t.name <> '' ");    
			hql.append(" group by t.id  order by n desc ");
		}else if((params.get("type")+"").toString().equals("cstomerEmployee")){//cstomerEmployee所有者分布
			hql.append(" select t.name, count(*) n,t.id from ");
			hql.append(" crm_customeraccount m inner join hr_org_employee t on m.employee_ID = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and t.name is not null and t.name <> '' ");    
			hql.append(" group by t.id  order by n desc ");
		}else if((params.get("type")+"").toString().equals("cstomerAccountCategory")){//cstomerAccountCategory客户分类
			hql.append(" select t.name, count(*) n,t.id from ");
			hql.append(" crm_customeraccount m inner join crm_c_customerAccountCategory t on m.customerAccountCategory_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.type = 'enterprise'  and m.istemp = '0'  and m.isdeleted = '0' ");
			hql.append(" and t.name is not null and t.name <> '' ");    
			hql.append(" group by t.id  order by n desc ");
		}
		return hql;
	}
	
	/** 查询 客户关系管理 > 客户管理 > 客户统计 >合同排行 barView 根据不同的controlSQL topNum queryTime参数,查询不同的数据  @throws ParseException **/
	@SuppressWarnings("unchecked")
	public StringBuilder queryContractTopBar(Map<String, Object> params) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if((params.get("controlSQL")+"").toString().equals("contractTotalAmountTop")){//contractTotalAmountTop  客户top20或50(合同总金额)
			hql.append(" select m.secondParty, sum(ifnull(m.totalAmount,0)) as sqlnum from CTM_CMN_CONTRACT m ");
			hql.append(" where m.contractType=3 ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.signDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.signDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.secondPartyId is not null and m.secondPartyId <> '' ");//secondPartyId乙方id,  
			hql.append(" and m.secondParty is not null and m.secondParty <> '' "); //secondParty乙方name,
			hql.append(" group by m.secondPartyId  order by sqlnum desc ");
			hql.append(" limit 0,"+(params.get("topNum")+"").toString()+" ");
		}else if((params.get("controlSQL")+"").toString().equals("backSectionRecordAmount")){//backSectionRecordAmount 客户top20或50(回款金额)
			hql.append(" select t.name, sum(ifnull(m.amount,0)) as sqlnum from crm_backsectionrecord m inner join crm_customeraccount t on m.customeraccount_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.backSectionDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.backSectionDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and t.name is not null and t.name <> '' "); 
			hql.append(" group by m.customeraccount_id  order by sqlnum desc ");
			hql.append(" limit 0,"+(params.get("topNum")+"").toString()+" ");
		}else if((params.get("controlSQL")+"").toString().equals("backSectionPlanAmount")){//backSectionPlanAmount 应收款对应客户top50/20
			hql.append(" select t.name, sum(ifnull(m.amount,0)) as sqlnum from crm_backsectionplan m inner join crm_customeraccount t on m.customeraccount_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.backSectionPlanDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.backSectionPlanDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and t.name is not null and t.name <> '' and m.backSectionPlanStatus='0' "); 
			hql.append(" group by m.customeraccount_id  order by sqlnum desc ");
			hql.append(" limit 0,"+(params.get("topNum")+"").toString()+" ");
		}else if((params.get("controlSQL")+"").toString().equals("activityFrequency")){//activityFrequency 客户销售活动次数top20
			hql.append(" select t.name, count(*) as sqlnum from CRM_ACTIVITY m inner join crm_customeraccount t on m.projectStage_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.date >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.date <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and t.name is not null and t.name <> '' and m.isdeleted !='1' "); 
			hql.append(" group by m.projectStage_id  order by sqlnum desc ");
			hql.append(" limit 0,"+(params.get("topNum")+"").toString()+" ");
		}
		return hql;
	}
}
