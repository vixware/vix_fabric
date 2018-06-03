package com.vix.drp.storeInformation.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.storeInformation.controller.StoreInformationController;
import com.vix.drp.storeInformation.service.IStoreInformationService;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;
import com.vix.system.entity.Attachment;

/**
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-11
 */
@Controller
@Scope("prototype")
public class StoreInformationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private StoreInformationController storeInformationController;

	private String id;
	private String ids;
	private String parentId;
	private String pageNo;
	private String treeType;
	private String type;
	/** 经销商 */
	private ChannelDistributor channelDistributor;

	private List<ChannelDistributor> channelDistributorList;
	@Autowired
	private IStoreInformationService storeInformationService;
	private String source;
	private List<CurrencyType> currencyTypeList;
	private String organizationId;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type," + SearchCondition.ANYLIKE, "4");
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			channelDistributorList = storeInformationController.doListChannelDistributorList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			params.put("type," + SearchCondition.EQUAL, "4");
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			String code = getRequestParameter("code");
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code.trim());
			}
			if (null == id) {
			} else {
				if ("C".equals(treeType)) {
					// 点击的树节点是公司
					params.put("organization.id," + SearchCondition.EQUAL, id);
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					params.put("parentChannelDistributor.id," + SearchCondition.EQUAL, id);
				}
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = storeInformationController.doListChannelDistributor(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			currencyTypeList = storeInformationController.doListCurrencyTypeList(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelDistributor = storeInformationController.doListEntityById(id);
			} else {
				if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
					if ("C".equals(treeType)) {
						/* 要根据orgType 判断组织结构类型 */
						Organization o = storeInformationService.findEntityById(Organization.class, parentId);
						if (null != o) {
							channelDistributor = new ChannelDistributor();
							channelDistributor.setCode(VixUUID.createCode(10));
							channelDistributor.setType(type);
							channelDistributor.setOrganization(o);
							channelDistributor.setIsHasParentChannelDistributor("0");
						}
					} else if ("CH".equals(treeType)) {
						// 点击的树节点是分销体系结构
						ChannelDistributor parentChannelDistributor = storeInformationService.findEntityById(ChannelDistributor.class, parentId);
						if (parentChannelDistributor != null) {
							channelDistributor = new ChannelDistributor();
							channelDistributor.setCode(VixUUID.createCode(10));
							channelDistributor.setType(type);
							channelDistributor.setParentChannelDistributor(parentChannelDistributor);
							channelDistributor.setOrganization(parentChannelDistributor.getOrganization());
							channelDistributor.setIsHasParentChannelDistributor("1");
						}
					}
				} else {
					channelDistributor = new ChannelDistributor();
					channelDistributor.setCode(VixUUID.createCode(10));
					channelDistributor.setType(type);
					channelDistributor.setIsHasParentChannelDistributor("0");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != channelDistributor.getId() && !"".equals(channelDistributor.getId())) {
				isSave = false;
			}
			//处理中文索引
			String py = ChnToPinYin.getPYString(channelDistributor.getName());
			channelDistributor.setChineseCharacter(py.toUpperCase());
			// 需要根据组织机构的类型来判断上级公司机构
			if (null != channelDistributor.getOrganization() && channelDistributor.getOrganization().getId() != null) {
			} else {
				channelDistributor.setOrganization(null);
			}
			channelDistributor.setType("4");
			if (channelDistributor.getEmployee() != null && channelDistributor.getEmployee().getId() != null && !"".equals(channelDistributor.getEmployee().getId())) {
			} else {
				channelDistributor.setEmployee(null);
			}
			if (channelDistributor.getParentChannelDistributor() != null && channelDistributor.getParentChannelDistributor().getId() != null && !"".equals(channelDistributor.getParentChannelDistributor().getId())) {
				channelDistributor.setIsHasParentChannelDistributor("1");
			} else {
				channelDistributor.setParentChannelDistributor(null);
				channelDistributor.setIsHasParentChannelDistributor("0");
			}
			initEntityBaseController.initEntityBaseAttribute(channelDistributor);
			//处理修改留痕
			billMarkProcessController.processMark(channelDistributor, updateField);
			storeInformationController.doSaveStoreInfomation(channelDistributor);
			
			//创建门店的同时 生成一条编码规则
			EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
			initEntityBaseController.initEntityBaseAttribute(encodingRulesTableInTheMiddle);
			encodingRulesTableInTheMiddle.setBillType(channelDistributor.getCode());
			encodingRulesTableInTheMiddle.setCodeLength(10);
			encodingRulesTableInTheMiddle.setEnableSeries(2);
			encodingRulesTableInTheMiddle.setCodeType("C");
			encodingRulesTableInTheMiddle.setSerialNumberBegin(1l);
			encodingRulesTableInTheMiddle.setSequenceID("9");
			encodingRulesTableInTheMiddle.setSerialNumberEnd(999999999L);
			encodingRulesTableInTheMiddle.setSerialNumberStep(1);
			storeInformationController.doSaveEncodingRulesTableInTheMiddle(encodingRulesTableInTheMiddle);

			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public void savePic() {
		try {
			// 保存附件
			String[] savePathAndName = saveUploadPic();
			Attachment attachment = new Attachment();
			if (savePathAndName != null && savePathAndName.length == 2) {
				attachment.setName(savePathAndName[1]);
				attachment.setPath(savePathAndName[0]);
				renderText("ftp://127.0.0.1/" + SecurityUtil.getCurrentUserTenantId() + "/" + savePathAndName[1].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSearch() {
		return "goSearch";
	}

	@SuppressWarnings("resource")
	private String[] saveUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSavePic();
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
				String[] fileNames = fileToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = fileToUploadFileName.substring(0, fileToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;

				String saveFilePath = saveFolder + "/" + saveFileName;

				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(saveFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();

				pathAndName = new String[2];
				pathAndName[0] = saveFolder;
				pathAndName[1] = saveFileName;

			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	@Override
	public String getUploadFileSavePic() {

		String baseFolder = "D:/img";

		String newFilePath = "";
		String tenantId = SecurityUtil.getCurrentUserTenantId();
		if (StrUtils.isEmpty(tenantId))
			tenantId = "no_tenantId";

		newFilePath = baseFolder + "/" + tenantId;

		File dir = new File(newFilePath);
		if (!dir.exists())
			dir.mkdirs();

		return newFilePath;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			ChannelDistributor cd = storeInformationController.doListEntityById(id);
			if (null != cd) {
				storeInformationController.doDeleteByEntity(cd);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				storeInformationController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载公司和部门的混合树 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
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
			if (null != nodeId && !"".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("O") || nodeTreeType.equals("CH")) {
						// 加载公司信息和部门信息
						orgUnitList = storeInformationService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = storeInformationService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	public String goChooseOrganization() {
		return "goChooseOrganization";
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listOrganization = storeInformationService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = storeInformationService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

}
