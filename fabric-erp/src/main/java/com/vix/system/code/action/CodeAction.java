package com.vix.system.code.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.entity.BillsType;
import com.vix.system.code.controller.CodeController;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

@Controller
@Scope("prototype")
public class CodeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(CodeAction.class);
	@Autowired
	private CodeController codeController;
	private String id;

	private List<BillsType> billsTypeList;
	/**
	 * 编码规则表
	 */
	private EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle;
	private List<EncodingRulesTableInTheMiddle> encodingRulesTableInTheMiddleList;

	private String billTypeCode;

	private String codingPreview;

	@Override
	public String goList() {
		try {
			encodingRulesTableInTheMiddleList = codeController
					.doListEncodingRulesTableInTheMiddleIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 只通过billTypeCode进行关联
			if (billTypeCode != null && !"".equals(billTypeCode)) {
				params.put("billType," + SearchCondition.EQUAL, billTypeCode);
			}
			Pager pager = codeController.doListEncodingRulesTableInTheMiddle(
					params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 新增编码级数
	 * 
	 * @return
	 */
	public String goSaveOrUpdateEncoding() {
		try {
			if (null != id && !"".equals(id)) {
				encodingRulesTableInTheMiddle = codeController
						.doListEncodingRulesTableInTheMiddleById(id);
			} else {
				if (billTypeCode != null && !"".equals(billTypeCode)) {
					encodingRulesTableInTheMiddle = codeController
							.doListEncodingRulesTableInTheMiddleByAttribute(billTypeCode);
					if (encodingRulesTableInTheMiddle != null) {
					} else {
						encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
						encodingRulesTableInTheMiddle.setBillType(billTypeCode);
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
		try {
			billsTypeList = codeController.doListBillsTypeList();
			if (billTypeCode != null && !"".equals(billTypeCode)) {
				encodingRulesTableInTheMiddle = codeController
						.doListEncodingRulesTableInTheMiddleByAttribute(billTypeCode);
				if (encodingRulesTableInTheMiddle != null) {
				} else {
					encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
					encodingRulesTableInTheMiddle.setBillType(billTypeCode);
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
			encodingRulesTableInTheMiddle = codeController
					.doSaveEncodingRulesTableInTheMiddle(encodingRulesTableInTheMiddle);
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

	public EncodingRulesTableInTheMiddle getEncodingRulesTableInTheMiddle() {
		return encodingRulesTableInTheMiddle;
	}

	public void setEncodingRulesTableInTheMiddle(
			EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle) {
		this.encodingRulesTableInTheMiddle = encodingRulesTableInTheMiddle;
	}

	public List<EncodingRulesTableInTheMiddle> getEncodingRulesTableInTheMiddleList() {
		return encodingRulesTableInTheMiddleList;
	}

	public void setEncodingRulesTableInTheMiddleList(
			List<EncodingRulesTableInTheMiddle> encodingRulesTableInTheMiddleList) {
		this.encodingRulesTableInTheMiddleList = encodingRulesTableInTheMiddleList;
	}

	public List<BillsType> getBillsTypeList() {
		return billsTypeList;
	}

	public void setBillsTypeList(List<BillsType> billsTypeList) {
		this.billsTypeList = billsTypeList;
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
