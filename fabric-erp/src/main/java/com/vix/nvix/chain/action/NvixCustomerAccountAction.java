package com.vix.nvix.chain.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.message.constant.MessageTemplateConstant;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.properties.Utils;
import com.vix.common.properties.util.MyTool;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.web.Pager;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.crm.member.entity.MemberTag;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.ChannelDistributorMessageSet;
import com.vix.drp.integralRulesSet.entity.IntegralChangeHistory;
import com.vix.drp.integralRulesSet.entity.IntegralRules;
import com.vix.drp.pointRecord.entity.PointRecord;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CardEntityDetail;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerAccountClip;
import com.vix.mdm.crm.entity.CustomerAccountClipDetail;
import com.vix.mdm.crm.entity.RechargeRecord;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.oa.personaloffice.service.IQueryDataService;
import com.vix.system.entity.Attachment;

import net.sf.json.JSONObject;
/**
 * 会员管理
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixCustomerAccountAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IQueryDataService queryDataService;
	private String attachmentId;
	private String id;
	private String clipId;
	private String parentId;
	private String treeType;
	private CustomerAccount customerAccount;
	private CustomerAccountClip customerAccountClip;
	private List<MemberLevel> memberLevelList= new ArrayList<MemberLevel>();
	private List<MemberTag> memberTagList= new ArrayList<MemberTag>();
	private List<MemberTag> customerAccounMmemberTagList = new ArrayList<MemberTag>();
	private Attachment attachment;
	private RechargeRecord rechargeRecord;
	private ChannelDistributor channelDistributor;
	private String cardEntityId;
	private String baseimg;
	private String tagId;
	private IntegralRules integralRules;
	private String changeMoney;
	private String changePoint;
	private String memberTagName;
	private DecimalFormat    df   = new DecimalFormat("######0.00"); 
	private String queryTime;
	private String returnPage; 
	
	/**
	 * 查询会员列表
	 */
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("updateTime");
			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType)) {
				if ("CH".equals(treeType)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			String phone = getDecodeRequestParameter("phone");
			if(StringUtils.isNotEmpty(phone)){
				params.put("mobilePhone,"+SearchCondition.ANYLIKE,phone);
			}
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("name,"+SearchCondition.ANYLIKE,selectName);
			}
			String carNumber = getDecodeRequestParameter("carNumber");
			if(StringUtils.isNotEmpty(carNumber)){
				params.put("clipNumber,"+SearchCondition.ANYLIKE,carNumber);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void goChooseCustomer(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isUse,"+SearchCondition.EQUAL, "Y");
			params.put("clipNumber,"+SearchCondition.IS, null);
			pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdateCustomer(){
		try {
			Map<String, Object> params = getParams();
			memberLevelList = vixntBaseService.findAllByConditions(MemberLevel.class, params);
			if(StringUtils.isNotEmpty(id)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				if(customerAccount != null){
					customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
					if(customerAccountClip == null){
						renderText("0");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCustomer";
	}
	public String goSaveOrUpdate() {
		try {
			Employee employee = getEmployee();
			if(employee != null){
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
			}
			if (StringUtils.isNotEmpty(id)) {
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
				
			} else {
				String type = getDecodeRequestParameter("type");
				customerAccount = new CustomerAccount();
				customerAccount.setSex("1");
				customerAccount.setIsUse("Y");
				customerAccount.setType(type);
				if(channelDistributor != null){
					customerAccount.setChannelDistributor(channelDistributor);
				}
				customerAccountClip = new CustomerAccountClip();
				customerAccountClip.setCode(VixUUID.createCode(12));
				customerAccountClip.setIsUse("Y");
			}
			Map<String, Object> params = getParams();
			memberLevelList = vixntBaseService.findAllByConditions(MemberLevel.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	/**
	 * 一天的消费金额
	 */
	private Double dayAmount = 0d;
	/**
	 * 累计消费金额
	 */
	private Double allAmount = 0d;
	/**
	 * 累计消费次数
	 */
	private Long rechargeAmount = 0l;
	/**
	 * 平均消费金额
	 */
	private Double averageAmount = 0d;
	/**
	 * 
	 * @return
	 */
	public String show(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				if(customerAccount != null){
					customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
					if(customerAccount != null){
						Map<String, Object> params1 = getParams();
						if(StringUtils.isNotEmpty(customerAccount.getMemberTag())){
							params1.put("id,"+SearchCondition.IN, customerAccount.getMemberTag());
							memberTagList = vixntBaseService.findAllByConditions(MemberTag.class, params1);
						}
					}
					Map<String, Object> params = getParams();
					memberLevelList = vixntBaseService.findAllByConditions(MemberLevel.class, params);
					//customerAccountClipTypeList = vixntBaseService.findAllByConditions(CustomerAccountClipType.class, params);
					Map<String, Object> params1 = getParams();
					params1.put("customerAccount.id,"+SearchCondition.EQUAL, id);
					params1.put("isTemp,"+SearchCondition.NOEQUAL, "1");
					params1.put("type,"+SearchCondition.EQUAL, "1");
					List<RequireGoodsOrder> requireGoodsOrderList = vixntBaseService.findAllByConditions(RequireGoodsOrder.class, params1);
					for (RequireGoodsOrder requireGoodsOrder : requireGoodsOrderList) {
						if(null != requireGoodsOrder && requireGoodsOrder.getAmount() >= 0){
							allAmount += requireGoodsOrder.getPayAmount();
							rechargeAmount ++;
						}
					}
					if(allAmount >0 && rechargeAmount >0){
						allAmount = Double.valueOf(df.format(allAmount));
						averageAmount = Double.valueOf(df.format(allAmount/rechargeAmount));
					}
					String nowDate = DateUtil.format(new Date(), "yyyy-MM-dd");
					Map<String, Object> params2 = getParams();
					params2.put("customerAccount.id,"+SearchCondition.EQUAL, id);
					params2.put("isTemp,"+SearchCondition.NOEQUAL, "1");
					params2.put("createTime,"+SearchCondition.BETWEENAND, nowDate +" 00:00:01!"+ nowDate +" 23:59:59");
					params2.put("type,"+SearchCondition.EQUAL, "1");
					List<RequireGoodsOrder> requireGoodsOrders = vixntBaseService.findAllByConditions(RequireGoodsOrder.class, params2);
					for (RequireGoodsOrder requireGoodsOrder : requireGoodsOrders) {
						if(null != requireGoodsOrder && requireGoodsOrder.getAmount() >= 0){
							dayAmount += requireGoodsOrder.getPayAmount();
						}
					}
					dayAmount = Double.valueOf(df.format(dayAmount));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		getCustomerExpense();
		getCustomerAllExpense();
		return "show";
	}
	public void saveOrUpdate() {
		try {
			Employee employee = getEmployee();
			if(employee != null){
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class,employee.getChannelDistributor().getId());
			}
			if(checkPhone(customerAccount.getMobilePhone())){
				customerAccount.setCode(VixUUID.createCodeByNumber(10));
				customerAccount.setStatus("0");	
				customerAccount.setCreateTime(new Date());
				customerAccount.setUpdateTime(new Date());
				if(channelDistributor != null){
					customerAccount.setChannelDistributor(channelDistributor);
				}
				if(checkClipNumber(customerAccountClip.getName())){
					initEntityBaseController.initEntityBaseAttribute(customerAccount);
					customerAccount = vixntBaseService.merge(customerAccount);
					if(customerAccount != null){
						if(StringUtils.isNotEmpty(customerAccountClip.getName())){
							customerAccountClip.setCreateTime(new Date());
							customerAccountClip.setUpdateTime(new Date());
							customerAccountClip.setCustomerAccount(customerAccount);
							customerAccountClip.setIsTemp("");
							customerAccountClip.setIsReport("N");
							if("2".equals(customerAccountClip.getCard().getType())){
								customerAccountClip.setMoney(0d);
							}else{
								customerAccountClip.setMoney(customerAccountClip.getPayMoney());
							}
							initEntityBaseController.initEntityBaseAttribute(customerAccountClip);
							customerAccountClip = vixntBaseService.merge(customerAccountClip);
							if(StringUtils.isNotEmpty(customerAccountClip.getCard().getId())){
								Map<String, Object> params = getParams();
								params.put("cardEntity.id,"+SearchCondition.EQUAL, customerAccountClip.getCard().getId());
								List<CardEntityDetail> list = vixntBaseService.findAllByConditions(CardEntityDetail.class, params);
								if(null != list && list.size() > 0){
									for (CardEntityDetail cardEntityDetail : list) {
										if(cardEntityDetail != null){
											CustomerAccountClipDetail customerAccountClipDetail = new CustomerAccountClipDetail();
											customerAccountClipDetail.setCustomerAccount(customerAccount);
											customerAccountClipDetail.setCustomerAccountClip(customerAccountClip);
											customerAccountClipDetail.setItem(cardEntityDetail.getItem());
											customerAccountClipDetail.setNumber(cardEntityDetail.getNum());
											initEntityBaseController.initEntityBaseAttribute(customerAccountClipDetail);
											customerAccountClipDetail = vixntBaseService.merge(customerAccountClipDetail);
										}
									}
								}
							}
							if(customerAccountClip != null){
								customerAccount.setStatus("1");
								customerAccount.setCustomerClipType(customerAccountClip.getCardType());
								customerAccount.setMoney(customerAccountClip.getMoney());
								customerAccount.setExpiryDate(customerAccountClip.getExpiryDate());
								customerAccount.setPoint(customerAccountClip.getPointValue());
								customerAccount.setClipNumber(customerAccountClip.getName());
								initEntityBaseController.initEntityBaseAttribute(customerAccount);
								customerAccount = vixntBaseService.merge(customerAccount);
							}
						} 
					}
					if(null != customerAccountClip){
						renderText(customerAccount.getId()+":"+customerAccountClip.getId());
					}else{
						renderText(customerAccount.getId()+":"+null);
					}
				}else{
					renderText("0:该会员卡号已存在!");
				}
			}else{
				renderText("0:该手机号已经被注册!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateCustomer(){
		try {
			initEntityBaseController.initEntityBaseAttribute(customerAccount);
			customerAccount = vixntBaseService.merge(customerAccount);
			if(customerAccount != null){
				customerAccountClip.setCustomerAccount(customerAccount);
				initEntityBaseController.initEntityBaseAttribute(customerAccountClip);
				customerAccountClip.setUpdateTime(new Date());
				customerAccountClip = vixntBaseService.merge(customerAccountClip);
				if(customerAccountClip != null){
					customerAccount.setMoney(customerAccountClip.getMoney());
					customerAccount.setPoint(customerAccountClip.getPointValue());
					customerAccount.setClipNumber(customerAccountClip.getName());
					customerAccount.setUpdateTime(new Date());
					customerAccount.setExpiryDate(customerAccountClip.getExpiryDate());
					customerAccount.setCustomerClipType(customerAccountClip.getCardType());
					initEntityBaseController.initEntityBaseAttribute(customerAccount);
					customerAccount = vixntBaseService.merge(customerAccount);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String pay(){
		try {
			String customerAccountId = getDecodeRequestParameter("customerAccountId");
			if(StringUtils.isNotEmpty(customerAccountId)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, customerAccountId);
				if(customerAccount != null){
					customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
					if(customerAccountClip != null){
						rechargeRecord = new RechargeRecord();
						rechargeRecord.setCustomerAccountClip(customerAccountClip);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pay";
	}
	protected void upLoadCustomerAccount(CustomerAccount customerAccount) throws Exception {
		JSONObject json = new JSONObject();
		json.put("channelDistributorCode", customerAccount.getChannelDistributor().getId());
		json.put("code", customerAccount.getCode());
		json.put("name", customerAccount.getName());
		json.put("mobilePhone", customerAccount.getName());
		System.out.println(json);
		String resp = postToPos("", json.toString(), "", "");
		if (StringUtils.isNotEmpty(resp)) {
			System.out.println(resp);
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			CustomerAccount customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
			if (null != customerAccount) {
				//vixntBaseService.batchDeleteBySql("DELETE FROM sys_attachment  WHERE customerAccount_id = '"+id+"'", null);
				vixntBaseService.deleteByEntity(customerAccount);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void checkNotUse(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				if(customerAccount != null){
					customerAccount.setIsUse("N");
					customerAccount.setUpdateTime(new Date());
					vixntBaseService.merge(customerAccount);
					customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
					if(customerAccountClip != null){
						customerAccountClip.setIsUse("N");
						customerAccountClip.setUpdateTime(new Date());
						vixntBaseService.merge(customerAccountClip);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void checkUse(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				if(customerAccount != null){
					customerAccount.setIsUse("Y");
					customerAccount.setUpdateTime(new Date());
					vixntBaseService.merge(customerAccount);
					customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
					if(customerAccountClip != null){
						customerAccountClip.setIsUse("Y");
						customerAccountClip.setUpdateTime(new Date());
						vixntBaseService.merge(customerAccountClip);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void findOrgAndUnitTreeToJson() {
		try {
			loadOrg(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadOrg(String nodeId, String nodeTreeType) {
		try {
			List<OrgUnit> orgUnitList = null;
			List<Organization> orgList = null;
			if (null != nodeId && !"".equals(nodeId) && !"undefined".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("O")) {
						// 加载公司信息和部门信息
						orgUnitList = vixntBaseService.findOrgAndUnitTreeList(nodeTreeType, nodeId, "O");
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = vixntBaseService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgUnit>();
			}

			if (orgList != null) {
				for (Organization orgTmp : orgList) {
					OrgUnit ou1 = new OrgUnit(orgTmp.getId(), "C", orgTmp.getOrgName());
					if (orgTmp.getSubOrganizations().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new OrgUnit(childOrg.getId(), "C", childOrg.getOrgName()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					if (orgTmp.getOrganizationUnits().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (OrganizationUnit organizationUnit : orgTmp.getOrganizationUnits()) {
							ou2List.add(new OrgUnit(organizationUnit.getId(), "O", organizationUnit.getFs()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				OrgUnit org = orgUnitList.get(i);
				if (org.getSubOrgUnits() != null && org.getSubOrgUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 拍照上传
	 */
	public void saveOrUpdateAttachments() {
		String headPath = "";
		try {
			if(StringUtils.isNotEmpty(baseimg)){
				byte[] buffer = Utils.decryptBasedDesByByte(baseimg);
				String saveFolder = this.getUploadFileSavePic();
				long newFileMark = System.currentTimeMillis() / 1000;
				String headImagePath = "ms_" + newFileMark + ".jpg";
				FileOutputStream fos=new FileOutputStream(saveFolder +  File.separator + headImagePath); 
				if(buffer != null && buffer.length > 0 ){
					headPath = "/img/ws/"+headImagePath;
				}
				fos.write(buffer);  
					fos.flush();  
					fos.close();  
					fos=null;
			}
			if(StringUtils.isNotEmpty(headPath)){
				renderText("1,"+headPath);
			}else{
				renderText("0,上传失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0,上传失败!");
		}
	}
	/**
	 * 批量导入会员信息
	 */
	public void importFile() {
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
			} else {
				try (InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream("customerAccount_template.xml")) {
					XLSReader reader = ReaderBuilder.buildFromXML(xmlInputStream);
					try (InputStream xlsInputStream = new FileInputStream(fileToUpload)) {
						List<CustomerAccount> customerAccountList = new ArrayList<CustomerAccount>();
						Map<String, Object> beans = new HashMap<String, Object>();
						beans.put("customerAccountList",customerAccountList);
						reader.read(xlsInputStream, beans);
						Employee employee = getEmployee();
						if(employee != null){
							channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
						}
						if(customerAccountList != null && customerAccountList.size() >0){
							for (CustomerAccount customerAccount : customerAccountList) {
								if(customerAccount != null){
									customerAccount.setCode(VixUUID.createCodeByNumber(10));
									customerAccount.setCreateTime(new Date());
									customerAccount.setUpdateTime(new Date());
									customerAccount.setStatus("1");
									if(channelDistributor !=null){
										customerAccount.setChannelDistributor(channelDistributor);
									}
									initEntityBaseController.initEntityBaseAttribute(customerAccount);
									vixntBaseService.merge(customerAccount);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				IOUtils.closeQuietly(fis);
			}
		}
	}
	
	/**
	 * 下载模板
	 * 
	 * @return
	 */
	public String downloadTemplate() {
		try {
			String fileName = "customerAccount_template.xls";
			setOriFileName(fileName);
			InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream(fileName);
			setInputStream(xmlInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "downloadTemplate";
	}
	
	public void getItemSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchItem = getDecodeRequestParameter("searchItem");
			if(StringUtils.isNotEmpty(searchItem)){
				params.put("item.name," + SearchCondition.ANYLIKE, searchItem);
			}
			String clipId = getDecodeRequestParameter("clipId");
			if(StringUtils.isNotEmpty(clipId)){
				params.put("customerAccountClip.id," + SearchCondition.EQUAL, clipId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAccountClipDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goChooseChannelDistributor(){
		return "goChooseChannelDistributor";
	}
	public void getChannelDistributorList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("type,"+SearchCondition.EQUAL, "4");
			pager = vixntBaseService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 购买商品名字
	 */
	private String itemNames;
	
	/**
	 * 购买商品数量
	 */
	private String amounts;
	/**
	 * 获取会员的消费商品数量Top5
	 */
	public void getCustomerExpense(){
		try {
			if(StringUtils.isNotEmpty(id)){
				Map<String, Object> params = getParams();
				params.put("customerAccountId", id);
				List<RequireGoodsOrderVo> requireGoodsOrderVos = queryDataService.getCustomerExpense(params);
				if(null != requireGoodsOrderVos && requireGoodsOrderVos.size() >0){
					itemNames = "";
					amounts = "";
					for (RequireGoodsOrderVo requireGoodsOrderVo : requireGoodsOrderVos) {
						if(requireGoodsOrderVo != null){
							itemNames += "\"" + requireGoodsOrderVo.getItemName() + "\"" + ",";
							amounts += requireGoodsOrderVo.getAmount()+ ",";
						}
					}
					itemNames = itemNames.substring(0, itemNames.length()-1);
					amounts = amounts.substring(0, amounts.length()-1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 购买商品数量
	 */
	private String itemAmounts;
	/**
	 * 商品名称
	 */
	private String goodsNames;
	public void getCustomerAllExpense(){
		try {
			if(StringUtils.isNotEmpty(id)){
				Map<String, Object> params = getParams();
				params.put("customerAccountId", id);
				List<RequireGoodsOrderVo> requireGoodsOrderVos = queryDataService.getCustomerAllExpense(params);
				if(null != requireGoodsOrderVos && requireGoodsOrderVos.size() >0){
					itemAmounts = "";
					goodsNames = "";
					for (RequireGoodsOrderVo requireGoodsOrderVo : requireGoodsOrderVos) {
						itemAmounts += "{ value:"+ requireGoodsOrderVo.getAmount() + ",name:'"+requireGoodsOrderVo.getItemName()+"'},";
						goodsNames += "\"" + requireGoodsOrderVo.getItemName() + "\"" + ",";
					}
					itemAmounts = itemAmounts.substring(0, itemAmounts.length()-1);
					goodsNames = goodsNames.substring(0, goodsNames.length()-1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String makeTag(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				Map<String, Object> params = getParams();
				memberTagList = vixntBaseService.findAllByConditions(MemberTag.class, params);
				if(customerAccount != null){
					Map<String, Object> params1 = getParams();
					if(StringUtils.isNotEmpty(customerAccount.getMemberTag())){
						params1.put("id,"+SearchCondition.IN, customerAccount.getMemberTag());
						customerAccounMmemberTagList = vixntBaseService.findAllByConditions(MemberTag.class, params1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "makeTag";
	}
	
	public String loadMemberTag(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				if(customerAccount != null){
					Map<String, Object> params = getParams();
					if(StringUtils.isNotEmpty(customerAccount.getMemberTag())){
						params.put("id,"+SearchCondition.IN, customerAccount.getMemberTag());
						customerAccounMmemberTagList = vixntBaseService.findAllByConditions(MemberTag.class, params);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadMemberTag";
	}
	public String loadMemberTagLib(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				Map<String, Object> params = getParams();
				memberTagList = vixntBaseService.findAllByConditions(MemberTag.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadMemberTagLib";
	}
	/**
	 * 打标签
	 */
	public void saveTagAndMake(){
		try {
			if(StringUtils.isNotEmpty(id)&&StringUtils.isNotEmpty(memberTagName)){
				MemberTag memberTag = new MemberTag();
				memberTag.setName(memberTagName);
				initEntityBaseController.initEntityBaseAttribute(memberTag);
				memberTag = vixntBaseService.merge(memberTag);
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				if(StringUtils.isNotEmpty(customerAccount.getMemberTag()) && null != memberTag ){
					String [] memberTags = customerAccount.getMemberTag().split(",");
					for (String string : memberTags) {
						if(string.equals(memberTag.getId())){
							renderText("该用户已经有该标签了");
							return;
						}
					}
					customerAccount.setMemberTag(customerAccount.getMemberTag() +","+memberTag.getId());
				}else{
					customerAccount.setMemberTag(","+memberTag.getId());
				}
				customerAccount.setUpdateTime(new Date());
				initEntityBaseController.initEntityBaseAttribute(customerAccount);
				customerAccount = vixntBaseService.merge(customerAccount);
				renderText("打标签成功!");
			}else{
				renderText("打标签失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("打标签失败!");
		}
	}
	public void makeTagForCustomer(){
		try {
			if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(tagId)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				if(customerAccount.getMemberTag() != null){
					String [] memberTag = customerAccount.getMemberTag().split(",");
					for (String string : memberTag) {
						if(string.equals(tagId)){
							renderText("该用户已经有该标签了");
							return;
						}
					}
					customerAccount.setMemberTag(customerAccount.getMemberTag() +","+tagId);
				}else{
					customerAccount.setMemberTag(","+tagId);
				}
				customerAccount.setUpdateTime(new Date());
				initEntityBaseController.initEntityBaseAttribute(customerAccount);
				customerAccount = vixntBaseService.merge(customerAccount);
				renderText("打标签成功!");
			}else{
				renderText("打标签失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("打标签失败!");
		}
	}
	public void deleteTag(){
		try {
			if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(tagId)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				if(customerAccount.getMemberTag() != null){
					String [] memberTag = customerAccount.getMemberTag().split(",");
					String str = "";
					for (String string : memberTag) {
						if(string.equals(tagId)||string.equals("")){
							continue;
						}
						str += ","+string;
					}
					customerAccount.setMemberTag(str);
					customerAccount.setUpdateTime(new Date());
					initEntityBaseController.initEntityBaseAttribute(customerAccount);
					customerAccount = vixntBaseService.merge(customerAccount);
					renderText("删除标签成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 智能分析>会员量分析>查询会员列表 guo 
	 */
	public void goSingleListStatistics() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				params.put("channelDistributor.id", employee.getChannelDistributor().getId() );
				String phone = getDecodeRequestParameter("phone");          
				if(StringUtils.isNotEmpty(phone)){
					params.put("mobilePhone,"+SearchCondition.EQUAL,phone);
				}
				String selectName = getDecodeRequestParameter("selectName");
				if(StringUtils.isNotEmpty(selectName)){
					params.put("name,"+SearchCondition.ANYLIKE,selectName);
				}
				String carNumber = getDecodeRequestParameter("carNumber");
				if(StringUtils.isNotEmpty(carNumber)){
					params.put("clipNumber,"+SearchCondition.EQUAL,carNumber);
				}
				String startTime = getDecodeRequestParameter("startTime");
				String endTime = getDecodeRequestParameter("endTime");
				String queryTimeOnce = getDecodeRequestParameter("queryTimeOnce");
				if(StringUtils.isNotEmpty(queryTimeOnce)){
						 List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(queryTimeOnce);
						 startTime = timeArr.get(0);
						 endTime = timeArr.get(1);
				}
				String queryTimeTwo = getDecodeRequestParameter("queryTimeTwo");
				if(StringUtils.isNotEmpty(queryTimeTwo)){
						 List<String> timeArr = MyTool.getTimeArrByHtmlParameterString(queryTimeTwo);
						 startTime = timeArr.get(0);
						 endTime = timeArr.get(1);
				}
				if(StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)){
					params.put("createTime,"+SearchCondition.BETWEENAND, startTime +" 00:00:00!"+ endTime +" 23:59:59");
				}
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderBy("desc");
					pager.setOrderField("createTime");
				}
				pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 智能分析>会员量分析>查询会员列表>查看详情 guo
	 */
	public String goSaveOrUpdateAnalysis(){
		try {
			customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
			customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
			getRequest().setAttribute("controlSQL",controlSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateAnalysis";
	}
	
	public boolean checkPhone(String phone){
		boolean tag = true;
		try {
			if(StringUtils.isNotEmpty(phone)){
				Map<String, Object> params = getParams();
				params.put("mobilePhone,"+SearchCondition.EQUAL, phone);
				List<CustomerAccount> list = vixntBaseService.findAllDataByConditions(CustomerAccount.class, params);
				if(list.size()>0){
					tag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag;
	}
	
	public boolean checkClipNumber(String number){
		boolean tag = true;
		try {
			if(StringUtils.isNotEmpty(number)){
				Map<String, Object> params = getParams();
				params.put("name,"+SearchCondition.EQUAL, number);
				List<CustomerAccountClip> list = vixntBaseService.findAllDataByConditions(CustomerAccountClip.class, params);
				if(list.size()>0){
					tag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag;
	}
	public String goLossCustomer(){
		return "goLossCustomer";
	}
	
	public String integralChange(){
		try {
			if(StringUtils.isNotEmpty(id)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
			}
			Map<String, Object> params = getParams();
			params.put("status,"+SearchCondition.EQUAL, "0");
			List<IntegralRules> integralRuleList = vixntBaseService.findAllDataByConditions(IntegralRules.class, params);
			if(integralRuleList != null && integralRuleList.size() > 0){
				integralRules = integralRuleList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "integralChange";
	}
	
	public void integralChangeCustomer(){
		try {
			if(StringUtils.isNotEmpty(id)&&StringUtils.isNotEmpty(changeMoney)&&StringUtils.isNotEmpty(changePoint)){
				customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
				if(customerAccount != null){
					customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
					if(customerAccountClip != null){
						if("1".equals(customerAccountClip.getCard().getType())){
							customerAccount.setMoney(customerAccount.getMoney()+Double.valueOf(changeMoney));
							customerAccount.setPoint(customerAccount.getPoint()-Double.valueOf(changePoint));
							customerAccount = vixntBaseService.merge(customerAccount);
							customerAccountClip.setMoney(customerAccount.getMoney());
							customerAccountClip.setPointValue(customerAccount.getPoint());	
							customerAccountClip = vixntBaseService.merge(customerAccountClip);
							PointRecord pointRecord = new PointRecord();
							pointRecord.setCreateTime(new Date());
							pointRecord.setUpdateTime(new Date());
							pointRecord.setPointSource("积分兑换余额");
							pointRecord.setOperation("积分兑换");
							pointRecord.setPointType("2");
							pointRecord.setPointValue(Double.valueOf(changePoint));
							pointRecord.setCustomerAccount(customerAccount);
							initEntityBaseController.initEntityBaseAttribute(pointRecord);
							pointRecord = vixntBaseService.merge(pointRecord);
							IntegralChangeHistory integralChangeHistory = new IntegralChangeHistory();
							integralChangeHistory.setCustomerAccount(customerAccount);
							integralChangeHistory.setCreateTime(new Date());
							integralChangeHistory.setUpdateTime(new Date());
							integralChangeHistory.setChangeDate(new Date());
							integralChangeHistory.setMoney(Double.valueOf(changeMoney));
							integralChangeHistory.setIntegralAmount(Double.valueOf(changePoint));
							initEntityBaseController.initEntityBaseAttribute(integralChangeHistory);
							integralChangeHistory = vixntBaseService.merge(integralChangeHistory);
							renderText("兑换成功!");
						}
						Employee employee = getEmployee();
						if(employee != null){
							channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
							if(channelDistributor != null){
								ChannelDistributorMessageSet channelDistributorMessageSet = vixntBaseService.findEntityByAttribute(ChannelDistributorMessageSet.class,"channelDistributor.id", channelDistributor.getId());
								if(channelDistributorMessageSet != null && "0".equals(channelDistributorMessageSet.getIsEnable()) && "0".equals(channelDistributorMessageSet.getSalesInform())){
									Map<String, String> orderStatusMap = new HashMap<String, String>();
									orderStatusMap.put(MessageTemplateConstant.username, customerAccount.getName());
									orderStatusMap.put(MessageTemplateConstant.cardNo, customerAccountClip.getName());
									orderStatusMap.put(MessageTemplateConstant.integral, changePoint);
									orderStatusMap.put(MessageTemplateConstant.amount, changeMoney);
									String resp = sendMessage("4", customerAccount.getMobilePhone(), orderStatusMap);
									if(StringUtils.isNotEmpty(resp) && resp.contains("|")){
										saveMessageSendRecord(2,"4","积分兑换",customerAccount.getMobilePhone(),channelDistributor,employee);
										if(StringUtils.isNotEmpty(String.valueOf(channelDistributorMessageSet.getNum()))&& null !=channelDistributorMessageSet.getNum()){
											channelDistributorMessageSet.setNum(channelDistributorMessageSet.getNum()+1);
										}else{
											channelDistributorMessageSet.setNum(1d);
										}
										initEntityBaseController.initEntityBaseAttribute(channelDistributorMessageSet);
										channelDistributorMessageSet = vixntBaseService.merge(channelDistributorMessageSet);
									}
									System.out.println(resp);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("兑换失败!");
		}
	}
	/**
	 * 导出会员信息
	 * @throws Exception
	 */
	public void exportCustomerAccountExcel() throws Exception {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "会员表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

			Map<String, Object> params = getParams();
			List<CustomerAccount> customerAccountList = vixntBaseService.findAllByConditions(CustomerAccount.class, params);
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("customerAccount_export_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("customerAccount_export_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("customerAccountList", customerAccountList);
					xlsArea.applyAt(new CellRef("customerAccount!A1"), context);
					transformer.write();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public List<MemberLevel> getMemberLevelList() {
		return memberLevelList;
	}

	public void setMemberLevelList(List<MemberLevel> memberLevelList) {
		this.memberLevelList = memberLevelList;
	}

	public List<MemberTag> getMemberTagList() {
		return memberTagList;
	}

	public void setMemberTagList(List<MemberTag> memberTagList) {
		this.memberTagList = memberTagList;
	}

	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public CustomerAccountClip getCustomerAccountClip() {
		return customerAccountClip;
	}

	public void setCustomerAccountClip(CustomerAccountClip customerAccountClip) {
		this.customerAccountClip = customerAccountClip;
	}

	public String getClipId() {
		return clipId;
	}

	public void setClipId(String clipId) {
		this.clipId = clipId;
	}
	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}
	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}
	public String getBaseimg() {
		return baseimg;
	}
	public void setBaseimg(String baseimg) {
		this.baseimg = baseimg;
	}
	public Double getDayAmount() {
		return dayAmount;
	}
	public void setDayAmount(Double dayAmount) {
		this.dayAmount = dayAmount;
	}
	public Double getAllAmount() {
		return allAmount;
	}
	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
	}
	public Long getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(Long rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public Double getAverageAmount() {
		return averageAmount;
	}
	public void setAverageAmount(Double averageAmount) {
		this.averageAmount = averageAmount;
	}
	public String getItemNames() {
		return itemNames;
	}
	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}
	public String getItemAmounts() {
		return itemAmounts;
	}
	public void setItemAmounts(String itemAmounts) {
		this.itemAmounts = itemAmounts;
	}
	public String getAmounts() {
		return amounts;
	}
	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}
	public String getGoodsNames() {
		return goodsNames;
	}
	public void setGoodsNames(String goodsNames) {
		this.goodsNames = goodsNames;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}
	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}
	public String getCardEntityId() {
		return cardEntityId;
	}
	public void setCardEntityId(String cardEntityId) {
		this.cardEntityId = cardEntityId;
	}
	public String controlSQL;
	public String getControlSQL() {
		return controlSQL;
	}
	public void setControlSQL(String controlSQL) {
		this.controlSQL = controlSQL;
	}
	public IntegralRules getIntegralRules() {
		return integralRules;
	}
	public void setIntegralRules(IntegralRules integralRules) {
		this.integralRules = integralRules;
	}
	public String getChangeMoney() {
		return changeMoney;
	}
	public void setChangeMoney(String changeMoney) {
		this.changeMoney = changeMoney;
	}
	public String getChangePoint() {
		return changePoint;
	}
	public void setChangePoint(String changePoint) {
		this.changePoint = changePoint;
	}
	public List<MemberTag> getCustomerAccounMmemberTagList() {
		return customerAccounMmemberTagList;
	}
	public void setCustomerAccounMmemberTagList(List<MemberTag> customerAccounMmemberTagList) {
		this.customerAccounMmemberTagList = customerAccounMmemberTagList;
	}
	public String getMemberTagName() {
		return memberTagName;
	}
	public void setMemberTagName(String memberTagName) {
		this.memberTagName = memberTagName;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getReturnPage() {
		return returnPage;
	}
	public void setReturnPage(String returnPage) {
		this.returnPage = returnPage;
	}
}