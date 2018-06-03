package com.vix.contract.config.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.entity.BillsType;
import com.vix.system.code.controller.CodeController;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

@Controller
@Scope("prototype")
public class ContractTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ContractTypeAction.class);
	@Autowired
	private CodeController codeController;
	private String id;
	private String ids;
	private String billTypeId;
	/**
	 * 编码规则表
	 */
	private EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle;
	private List<EncodingRulesTableInTheMiddle> encodingRulesTableInTheMiddleList;
	/**
	 * 单据类型
	 */
	private BillsType billsType;

	@Autowired
	private InitEntityBaseController initEntityBaseController;

	private HttpSession session = getSession();

	@Override
	public String goList() {
		try {
			encodingRulesTableInTheMiddleList = codeController.doListEncodingRulesTableInTheMiddleIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String billTypeId = getRequestParameter("billTypeId");
			String treeType = getRequestParameter("treeType");
			if ("B".equals(treeType)) {
				/* 单据分类 */
			} else if ("T".equals(treeType)) {
				/* 单据类型 */
				params.put("billsType.id," + SearchCondition.EQUAL, billTypeId);
			}
			Pager pager = codeController.doListEncodingRulesTableInTheMiddle(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 编码级数管理页面跳转
	 * 
	 * 执行跳转的时候将单据类型ID编码存到session中
	 * 
	 * 根据单据类型编码获取编码实体，如果存在就返回 ，没有就重新创建
	 * 
	 * @return
	 */
	public String goSaveOrUpdateEncoding() {
		try {
			if (null != id && !"".equals(id)) {
				encodingRulesTableInTheMiddle = codeController.doListEncodingRulesTableInTheMiddleById(id);
			} else {
				if (billTypeId != null && !"".equals(billTypeId)) {
					billsType = codeController.doListBillsTypeById(billTypeId);
					/* 将单据类型ID编码存到session中 */
					session.setAttribute("billTypeId", billTypeId);
					if (billsType != null) {
						encodingRulesTableInTheMiddle = codeController.doListEncodingRulesTableInTheMiddleByAttribute(billsType.getTypeCode());
						if (encodingRulesTableInTheMiddle != null) {
						} else {
							encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
							encodingRulesTableInTheMiddle.setBillType(billsType.getTypeCode());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateEncoding";
	}

	public String goCodeList() {
		return "goSaveOrUpdate";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		String billTypeCode = getRequestParameter("billTypeCode");
		session.setAttribute("billTypeId", billTypeId);
		try {
			if (billTypeCode != null && !"".equals(billTypeCode)) {
				encodingRulesTableInTheMiddle = codeController.doListEncodingRulesTableInTheMiddleByAttribute(billTypeCode);
				if (encodingRulesTableInTheMiddle != null) {
				} else {
					encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
					encodingRulesTableInTheMiddle.setBillType(billTypeCode);
					encodingRulesTableInTheMiddle.setLevel1value(billTypeCode);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	/** 新增修改编码规则 */
	public String saveOrUpdate() {

		boolean isSave = true;
		try {
			// 被选中的checkbox的值
			String chkids = getRequestParameter("chkids");
			/* 取出单据类型的ID编码 ,并从session移除billTypeId */
			if (session.getAttribute("billTypeId") != null && !"".equals(session.getAttribute("billTypeId"))) {
				billTypeId = String.valueOf(session.getAttribute("billTypeId"));
				session.removeAttribute("billTypeId");
			}
			if (billTypeId != null && !"".equals(billTypeId)) {
				billsType = codeController.doListBillsTypeById(billTypeId);
			}
			encodingRulesTableInTheMiddle = codeController.doSaveEncodingRulesTableInTheMiddle(encodingRulesTableInTheMiddle);
			initEntityBaseController.initEntityBaseAttribute(encodingRulesTableInTheMiddle);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public BillsType getBillsType() {
		return billsType;
	}

	public void setBillsType(BillsType billsType) {
		this.billsType = billsType;
	}

	public String getBillTypeId() {
		return billTypeId;
	}

	public void setBillTypeId(String billTypeId) {
		this.billTypeId = billTypeId;
	}

	public EncodingRulesTableInTheMiddle getEncodingRulesTableInTheMiddle() {
		return encodingRulesTableInTheMiddle;
	}

	public void setEncodingRulesTableInTheMiddle(EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle) {
		this.encodingRulesTableInTheMiddle = encodingRulesTableInTheMiddle;
	}

	public List<EncodingRulesTableInTheMiddle> getEncodingRulesTableInTheMiddleList() {
		return encodingRulesTableInTheMiddleList;
	}

	public void setEncodingRulesTableInTheMiddleList(List<EncodingRulesTableInTheMiddle> encodingRulesTableInTheMiddleList) {
		this.encodingRulesTableInTheMiddleList = encodingRulesTableInTheMiddleList;
	}

}
