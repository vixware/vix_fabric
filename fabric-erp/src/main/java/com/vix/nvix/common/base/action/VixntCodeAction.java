package com.vix.nvix.common.base.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.Organization;
import com.vix.system.billTypeManagement.entity.BillsCategory;
import com.vix.system.billTypeManagement.service.IBillTypeManagementService;
import com.vix.system.billTypeManagement.vo.BillTypeUnit;
import com.vix.system.code.controller.CodeController;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

@Controller
@Scope("prototype")
public class VixntCodeAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private CodeController codeController;
	@Autowired
	private IBillTypeManagementService billTypeManagementService;

	/**
	 * 编码规则表
	 */
	private EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle;

	private String id;
	private String billTypeCode;
	private String codingPreview;
	private String treeType;

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(billTypeCode)) {
				encodingRulesTableInTheMiddle = vixntBaseService.findEntityByAttribute(EncodingRulesTableInTheMiddle.class, "billType", billTypeCode);
				if (encodingRulesTableInTheMiddle != null) {
				} else {
					encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
					encodingRulesTableInTheMiddle.setBillType(billTypeCode);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/** 新增修改编码规则 */
	public void saveOrUpdate() {

		boolean isSave = true;
		try {
			encodingRulesTableInTheMiddle.setCodeType("C");
			initEntityBaseController.initEntityBaseAttribute(encodingRulesTableInTheMiddle);
			encodingRulesTableInTheMiddle = codeController.doSaveEncodingRulesTableInTheMiddle(encodingRulesTableInTheMiddle);
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
	}

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载分类和l的混合树 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
	public void findOrgAndUnitTreeToJson() {
		try {
			loadOrg(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 异步获取树结构
	 * 
	 * @param nodeId
	 * @param nodeTreeType
	 */
	private void loadOrg(String nodeId, String nodeTreeType) {
		try {
			List<BillTypeUnit> orgUnitList = null;
			List<Organization> orgList = null;
			if (null != nodeId && !"".equals(nodeId) && !"-1".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("B") || nodeTreeType.equals("X")) {
						// 加载公司信息和分类信息
						orgUnitList = billTypeManagementService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = billTypeManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			if (orgUnitList == null) {
				orgUnitList = new LinkedList<BillTypeUnit>();
			}
			if (orgList != null) {
				for (Organization orgTmp : orgList) {
					BillTypeUnit ou1 = new BillTypeUnit(orgTmp.getId(), "C", orgTmp.getOrgName(), orgTmp.getCompanyInnerCode());
					if (orgTmp.getSubOrganizations() != null && orgTmp.getSubOrganizations().size() > 0) {
						List<BillTypeUnit> ou2List = new LinkedList<BillTypeUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new BillTypeUnit(childOrg.getId(), "C", childOrg.getOrgName(), childOrg.getCompanyInnerCode()));
						}
						ou1.setSubBillTypeUnits(ou2List);
					}
					List<BillsCategory> bcList = billTypeManagementService.findBillsCategoryListByOrgId(orgTmp.getId());
					if (bcList != null && bcList.size() > 0) {
						List<BillTypeUnit> billTypeUnitList = new LinkedList<BillTypeUnit>();
						for (BillsCategory billsCategory : bcList) {
							if (billsCategory != null) {
								billTypeUnitList.add(new BillTypeUnit(billsCategory.getId(), "B", billsCategory.getCategoryName(), billsCategory.getCategoryCode()));
							}
						}
						ou1.setSubBillTypeUnits(billTypeUnitList);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				BillTypeUnit billTypeUnit = orgUnitList.get(i);
				if (billTypeUnit.getSubBillTypeUnits() != null && billTypeUnit.getSubBillTypeUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(billTypeUnit.getId());
					strSb.append("\",treeType:\"");
					strSb.append(billTypeUnit.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(billTypeUnit.getTreeName());
					strSb.append("\",code:\"");
					strSb.append(billTypeUnit.getTreeCode());
					strSb.append("\",open:true,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(billTypeUnit.getId());
					strSb.append("\",treeType:\"");
					strSb.append(billTypeUnit.getTreeType());
					strSb.append("\",name:\"");
					strSb.append(billTypeUnit.getTreeName());
					strSb.append("\",code:\"");
					strSb.append(billTypeUnit.getTreeCode());
					strSb.append("\",open:true,isParent:false}");
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

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public EncodingRulesTableInTheMiddle getEncodingRulesTableInTheMiddle() {
		return encodingRulesTableInTheMiddle;
	}

	public void setEncodingRulesTableInTheMiddle(EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle) {
		this.encodingRulesTableInTheMiddle = encodingRulesTableInTheMiddle;
	}

	public String getBillTypeCode() {
		return billTypeCode;
	}

	public void setBillTypeCode(String billTypeCode) {
		this.billTypeCode = billTypeCode;
	}

	public String getCodingPreview() {
		return codingPreview;
	}

	public void setCodingPreview(String codingPreview) {
		this.codingPreview = codingPreview;
	}

}
