package com.vix.nvix.drp.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierAccountingInfo;
import com.vix.mdm.srm.share.entity.SupplierAptitudeInfo;
import com.vix.mdm.srm.share.entity.SupplierBankInfo;
import com.vix.mdm.srm.share.entity.SupplierCreditInfo;
import com.vix.mdm.srm.share.entity.SupplierIndicators;
import com.vix.mdm.srm.share.entity.SupplierLevel;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

/**
 * 
 * @类全名 com.vix.nvix.drp.action.VixntSupplierAction
 *
 * @author zhanghaibing
 *
 * @date 2016年9月2日
 */
@Controller
@Scope("prototype")
public class VixntSupplierAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private Supplier supplier;
	private List<CurrencyType> currencyTypeList;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	private String supplierId;
	private SupplierBankInfo supplierBankInfo;// 银行信息
	private SupplierAptitudeInfo supplierAptitudeInfo;// 资质信息
	private SupplierAccountingInfo supplierAccountingInfo;// 财务信息
	private SupplierCreditInfo supplierCreditInfo;// 信用
	private SupplierIndicators supplierIndicators;// 指标
	private List<SupplierLevel> supplierLevelList;
	public String goSourceList() {// 寻源
		return "goSourceList";
	}
	public String goAuditingList() {// 待评估
		return "goAuditingList";
	}
	public String goBuildingList() {// 待建档
		return "goBuildingList";
	}
	/** 获取列表数据 私有 正式供应商 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			params.put("status," + SearchCondition.EQUAL, "3");
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String supplierName = getDecodeRequestParameter("supplierName");
			if (StringUtils.isNotEmpty(supplierName)) {
				params.put("name," + SearchCondition.ANYLIKE, supplierName.trim());
			}
			String code = getDecodeRequestParameter("code");
			if (StringUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			String contacts = getDecodeRequestParameter("contacts");
			if (StringUtils.isNotEmpty(contacts)) {
				params.put("contacts," + SearchCondition.ANYLIKE, contacts);
			}
			String type = getDecodeRequestParameter("type");
			if (StringUtils.isNotEmpty(type)) {
				params.put("type," + SearchCondition.ANYLIKE,type);
			}
			String industry = getDecodeRequestParameter("industry");
			if (StringUtils.isNotEmpty(industry)) {
				params.put("industry," + SearchCondition.ANYLIKE,industry);
			}
			String supplierCategoryId = getDecodeRequestParameter("supplierCategoryId");
			if (StringUtils.isNotEmpty(supplierCategoryId)) {
				params.put("supplierCategory.id," + SearchCondition.EQUAL,supplierCategoryId);
			}
			String cellphone = getDecodeRequestParameter("cellphone");
			if (StringUtils.isNotEmpty(cellphone)) {
				params.put("cellphone," + SearchCondition.ANYLIKE,cellphone);
			}
			Employee e = getEmployee();
			if (e != null) {
				if ("1".equals(e.getIsViewData())) {
					//拥有查看权限的人员不进行控制
				} else {
					params.put("purchaserEmployee.id," + SearchCondition.EQUAL, e.getId());
				}
				pager = vixntBaseService.findPagerByHqlConditions(pager, Supplier.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取供应商 待评估的
	 */
	public void goSingleAuditingList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("status," + SearchCondition.EQUAL, "2");
			String supplierName = getDecodeRequestParameter("supplierName");
			if (StringUtils.isNotEmpty(supplierName)) {
				params.put("name," + SearchCondition.ANYLIKE, supplierName.trim());
			}
			String code = getDecodeRequestParameter("code");
			if (StringUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			String contacts = getDecodeRequestParameter("contacts");
			if (StringUtils.isNotEmpty(contacts)) {
				params.put("contacts," + SearchCondition.ANYLIKE, contacts);
			}
			String type = getDecodeRequestParameter("type");
			if (StringUtils.isNotEmpty(type)) {
				params.put("type," + SearchCondition.ANYLIKE,type);
			}
			String industry = getDecodeRequestParameter("industry");
			if (StringUtils.isNotEmpty(industry)) {
				params.put("industry," + SearchCondition.ANYLIKE,industry);
			}
			String supplierCategoryId = getDecodeRequestParameter("supplierCategoryId");
			if (StringUtils.isNotEmpty(supplierCategoryId)) {
				params.put("supplierCategory.id," + SearchCondition.EQUAL,supplierCategoryId);
			}
			String cellphone = getDecodeRequestParameter("cellphone");
			if (StringUtils.isNotEmpty(cellphone)) {
				params.put("cellphone," + SearchCondition.ANYLIKE,cellphone);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Supplier.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取供应商 寻源的 待建档的
	 */
	public void goSingleSourceList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("status," + SearchCondition.EQUAL, "1");
			String supplierName = getDecodeRequestParameter("supplierName");
			if (StringUtils.isNotEmpty(supplierName)) {
				params.put("name," + SearchCondition.ANYLIKE, supplierName.trim());
			}
			String code = getDecodeRequestParameter("code");
			if (StringUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			String contacts = getDecodeRequestParameter("contacts");
			if (StringUtils.isNotEmpty(contacts)) {
				params.put("contacts," + SearchCondition.ANYLIKE, contacts);
			}
			String type = getDecodeRequestParameter("type");
			if (StringUtils.isNotEmpty(type)) {
				params.put("type," + SearchCondition.ANYLIKE,type);
			}
			String industry = getDecodeRequestParameter("industry");
			if (StringUtils.isNotEmpty(industry)) {
				params.put("industry," + SearchCondition.ANYLIKE,industry);
			}
			String supplierCategoryId = getDecodeRequestParameter("supplierCategoryId");
			if (StringUtils.isNotEmpty(supplierCategoryId)) {
				params.put("supplierCategory.id," + SearchCondition.EQUAL,supplierCategoryId);
			}
			String cellphone = getDecodeRequestParameter("cellphone");
			if (StringUtils.isNotEmpty(cellphone)) {
				params.put("cellphone," + SearchCondition.ANYLIKE,cellphone);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Supplier.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = getParams();
			currencyTypeList = vixntBaseService.findAllByConditions(CurrencyType.class, params);
			supplierLevelList = vixntBaseService.findAllByEntityClass(SupplierLevel.class);
			if (StringUtils.isNotEmpty(id)) {
				supplier = vixntBaseService.findEntityById(Supplier.class, id);
				supplier.setStatus("3");
			} else {
				supplier = new Supplier();
				supplier.setCode(VixUUID.createCode(12));
				supplier.setStatus("3");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateSourceSupplier() {
		try {
			Map<String, Object> params = getParams();
			currencyTypeList = vixntBaseService.findAllByConditions(CurrencyType.class, params);
			supplierLevelList = vixntBaseService.findAllByEntityClass(SupplierLevel.class);
			if (StringUtils.isNotEmpty(id)) {
				supplier = vixntBaseService.findEntityById(Supplier.class, id);
				supplier.setStatus("1");
			} else {
				supplier = new Supplier();
				supplier.setCode(VixUUID.createCode(12));
				supplier.setStatus("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSourceSupplier";
	}
	public String goSaveOrUpdateBuildingSupplier() {
		try {
			Map<String, Object> params = getParams();
			currencyTypeList = vixntBaseService.findAllByConditions(CurrencyType.class, params);
			supplierLevelList = vixntBaseService.findAllByEntityClass(SupplierLevel.class);
			if (StringUtils.isNotEmpty(id)) {
				supplier = vixntBaseService.findEntityById(Supplier.class, id);
				supplier.setStatus("2");
			} else {
				supplier = new Supplier();
				supplier.setCode(VixUUID.createCode(12));
				supplier.setStatus("2");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateBuildingSupplier";
	}
	public String goSaveOrUpdateAuditingSupplier() {
		try {
			Map<String, Object> params = getParams();
			currencyTypeList = vixntBaseService.findAllByConditions(CurrencyType.class, params);
			supplierLevelList = vixntBaseService.findAllByEntityClass(SupplierLevel.class);
			if (StringUtils.isNotEmpty(id)) {
				supplier = vixntBaseService.findEntityById(Supplier.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateAuditingSupplier";
	}
	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(supplier.getId())) {
				isSave = false;
			}
			String py = ChnToPinYin.getPinYinHeadChar(supplier.getName()).toUpperCase();
			supplier.setChineseCharacter(py);
			supplier.setStatus("3");
			initEntityBaseController.initEntityBaseAttribute(supplier);
			supplier = vixntBaseService.merge(supplier);

			EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle = null;
			Map<String, Object> itemparams = new HashMap<String, Object>();
			itemparams.put("billType", py);
			itemparams.put("tenantId", supplier.getTenantId());
			StringBuilder itemhql = standingBookHqlProvider.findEncodingRulesTableInTheMiddleBillType(itemparams);
			encodingRulesTableInTheMiddle = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
			if (encodingRulesTableInTheMiddle == null) {
				encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
				initEntityBaseController.initEntityBaseAttribute(encodingRulesTableInTheMiddle);
				encodingRulesTableInTheMiddle.setBillType(py);
				encodingRulesTableInTheMiddle.setCodeLength(10);
				encodingRulesTableInTheMiddle.setEnableSeries(2);
				encodingRulesTableInTheMiddle.setCodeType("C");
				encodingRulesTableInTheMiddle.setSerialNumberBegin(1l);
				encodingRulesTableInTheMiddle.setSequenceID(py);
				encodingRulesTableInTheMiddle.setSerialNumberEnd(999999999L);
				encodingRulesTableInTheMiddle.setSerialNumberStep(1);
				vixntBaseService.merge(encodingRulesTableInTheMiddle);
			}
			if (isSave) {
				renderText("1:" + supplier.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + supplier.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}
	/**
	 * 建档
	 */
	public void saveOrUpdateBuildingSupplier() {
		try {
			supplier.setStatus("2");
			supplier.setUpdateTime(new Date());
			supplier = vixntBaseService.merge(supplier);
			renderText("1:建档成功!");
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:建档失败!");
		}
	}
	/**
	 * 寻源
	 */
	public void saveOrUpdateSourceSupplier() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(supplier.getId())) {
				isSave = false;
			}
			supplier.setStatus("1");
			supplier.setUpdateTime(new Date());
			supplier = vixntBaseService.merge(supplier);
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

	public void saveOrUpdateAuditing() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				supplier = vixntBaseService.findEntityById(Supplier.class, id);
				if (supplier != null) {
					supplier.setStatus("3");
					supplier.setUpdateTime(new Date());
					supplier = vixntBaseService.merge(supplier);
					renderText("1:评估通过!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:评估不通过!");
		}
	}

	public void saveOrUpdateAuditingIsNo() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				supplier = vixntBaseService.findEntityById(Supplier.class, id);
				if (supplier != null) {
					supplier.setStatus("0");
					supplier.setUpdateTime(new Date());
					supplier = vixntBaseService.merge(supplier);
					renderText("0:评估不通过!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:评估失败!");
		}
	}
	/** 处理删除操作 */
	public void deleteById() {
		try {
			Supplier supplier = vixntBaseService.findEntityById(Supplier.class, id);
			if (null != supplier) {
				vixntBaseService.deleteByEntity(supplier);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void getAttachmentsList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("path," + SearchCondition.EQUAL, searchName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("supplier.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, Attachments.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存附件
	 */
	public void saveOrUpdateAttachments() {
		try {
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				Attachments attachments = new Attachments();
				attachments.setName(savePathAndName[1].toString());
				Supplier supplier = getSupplier();
				if (supplier != null) {
					attachments.setCreator(supplier.getName());
				}
				attachments.setPath("/img/ws/" + savePathAndName[1].toString());
				attachments.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(id)) {
					supplier = vixntBaseService.findEntityById(Supplier.class, id);
					if (supplier != null) {
						attachments.setSupplier(supplier);
					}
				}
				vixntBaseService.merge(attachments);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除附件
	 */
	public void deleteUploaderById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Attachments attachments = vixntBaseService.findEntityById(Attachments.class, id);
				if (null != attachments) {
					String fileName = attachments.getName();
					String baseFolder = "c:/img/";
					String downloadFile = baseFolder + fileName;
					File f = new File(downloadFile); // 输入要删除的文件位置
					if (f.exists()) {
						f.delete();
					}
					vixntBaseService.deleteByEntity(attachments);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * 获取银行信息
	 */
	public void getSupplierBankInfoList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(id)) {
				params.put("supplier.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, SupplierBankInfo.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增或修改银行信息
	 * 
	 * @return
	 */
	public String goSaveOrUpdateSupplierBankInfo() {
		try {
			if (StringUtils.isNotEmpty(id) && !"undefined".equals(id)) {
				supplierBankInfo = vixntBaseService.findEntityById(SupplierBankInfo.class, id);
			} else {
				supplierBankInfo = new SupplierBankInfo();
				if (StringUtils.isNotEmpty(supplierId)) {
					supplier = vixntBaseService.findEntityById(Supplier.class, supplierId);
					if (supplier != null) {
						supplierBankInfo.setSupplier(supplier);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSupplierBankInfo";
	}
	/**
	 * 保存银行信息
	 */
	public void saveOrUpdateSupplierBankInfo() {
		try {
			if (supplierBankInfo != null) {
				initEntityBaseController.initEntityBaseAttribute(supplierBankInfo);
				supplierBankInfo = vixntBaseService.merge(supplierBankInfo);
				renderText("1:" + supplierBankInfo.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除银行信息
	 */
	public void deleteSupplierBankInfoById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				supplierBankInfo = vixntBaseService.findEntityById(SupplierBankInfo.class, id);
				if (supplierBankInfo != null) {
					vixntBaseService.deleteByEntity(supplierBankInfo);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * 获取资质信息
	 */
	public void getSupplierAptitudeInfoList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(id)) {
				params.put("supplier.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, SupplierAptitudeInfo.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增资质
	 * 
	 * @return
	 */
	public String goSaveOrUpdateSupplierAptitudeInfo() {
		try {
			if (StringUtils.isNotEmpty(id) && !"undefined".equals(id)) {
				supplierAptitudeInfo = vixntBaseService.findEntityById(SupplierAptitudeInfo.class, id);
			} else {
				supplierAptitudeInfo = new SupplierAptitudeInfo();
				if (StringUtils.isNotEmpty(supplierId)) {
					supplier = vixntBaseService.findEntityById(Supplier.class, supplierId);
					if (supplier != null) {
						supplierAptitudeInfo.setSupplier(supplier);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSupplierAptitudeInfo";
	}
	/**
	 * 保存资质
	 */
	public void saveOrUpdateAptitudeInfo() {
		try {
			if (supplierAptitudeInfo != null) {
				initEntityBaseController.initEntityBaseAttribute(supplierAptitudeInfo);
				supplierAptitudeInfo = vixntBaseService.merge(supplierAptitudeInfo);
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除资质
	 */
	public void deleteAptitudeInfoById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				supplierAptitudeInfo = vixntBaseService.findEntityById(SupplierAptitudeInfo.class, id);
				if (supplierAptitudeInfo != null) {
					vixntBaseService.deleteByEntity(supplierAptitudeInfo);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * 获取财务信息
	 */
	public void getSupplierAccountingInfoList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(id)) {
				params.put("supplier.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, SupplierAccountingInfo.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增财务信息
	 * 
	 * @return
	 */
	public String goSaveOrUpdateSupplierAccountingInfo() {
		try {
			if (StringUtils.isNotEmpty(id) && !"undefined".equals(id)) {
				supplierAccountingInfo = vixntBaseService.findEntityById(SupplierAccountingInfo.class, id);
			} else {
				supplierAccountingInfo = new SupplierAccountingInfo();
				if (StringUtils.isNotEmpty(supplierId)) {
					supplier = vixntBaseService.findEntityById(Supplier.class, supplierId);
					if (supplier != null) {
						supplierAccountingInfo.setSupplier(supplier);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSupplierAccountingInfo";
	}
	/**
	 * 保存财务信息
	 */
	public void saveOrUpdateAccounting() {
		try {
			if (supplierAccountingInfo != null) {
				initEntityBaseController.initEntityBaseAttribute(supplierAccountingInfo);
				supplierAccountingInfo = vixntBaseService.merge(supplierAccountingInfo);
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除财务信息
	 */
	public void deleteAccountingById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				supplierAccountingInfo = vixntBaseService.findEntityById(SupplierAccountingInfo.class, id);
				if (supplierAccountingInfo != null) {
					vixntBaseService.deleteByEntity(supplierAccountingInfo);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * 获取指标
	 */
	public void getSupplierIndicatorsList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(id)) {
				params.put("supplier.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, SupplierIndicators.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增指标
	 * 
	 * @return
	 */
	public String goSaveOrUpdateSupplierIndicators() {
		try {
			if (StringUtils.isNotEmpty(id) && !"undefined".equals(id)) {
				supplierIndicators = vixntBaseService.findEntityById(SupplierIndicators.class, id);
			} else {
				supplierIndicators = new SupplierIndicators();
				if (StringUtils.isNotEmpty(supplierId)) {
					supplier = vixntBaseService.findEntityById(Supplier.class, supplierId);
					if (supplier != null) {
						supplierIndicators.setSupplier(supplier);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSupplierIndicators";
	}
	/**
	 * 保存指标
	 */
	public void saveOrUpdateIndicators() {
		try {
			if (supplierIndicators != null) {
				initEntityBaseController.initEntityBaseAttribute(supplierIndicators);
				supplierIndicators = vixntBaseService.merge(supplierIndicators);
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}
	/**
	 * 删除指标
	 */
	public void deleteIndicatorsById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				supplierIndicators = vixntBaseService.findEntityById(SupplierIndicators.class, id);
				if (supplierIndicators != null) {
					vixntBaseService.deleteByEntity(supplierIndicators);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * 获取信用
	 */
	public void getSupplierCreditInfoList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(id)) {
				params.put("supplier.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, SupplierCreditInfo.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增信用
	 * 
	 * @return
	 */
	public String goSaveOrUpdateSupplierCreditInfo() {
		try {
			if (StringUtils.isNotEmpty(id) && !"undefined".equals(id)) {
				supplierCreditInfo = vixntBaseService.findEntityById(SupplierCreditInfo.class, id);
			} else {
				supplierCreditInfo = new SupplierCreditInfo();
				if (StringUtils.isNotEmpty(supplierId)) {
					supplier = vixntBaseService.findEntityById(Supplier.class, supplierId);
					if (supplier != null) {
						supplierCreditInfo.setSupplier(supplier);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSupplierCreditInfo";
	}

	/**
	 * 选择人员
	 * 
	 * @return
	 */
	public String goEmployeeChoose() {
		return "goEmployeeChoose";
	}

	/**
	 * 人员列表
	 */
	public void goEmployeeList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empType," + SearchCondition.EQUAL, "S");
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Employee.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存信用
	 */
	public void saveOrUpdateCredit() {
		try {
			if (supplierCreditInfo != null) {
				initEntityBaseController.initEntityBaseAttribute(supplierCreditInfo);
				supplierCreditInfo = vixntBaseService.merge(supplierCreditInfo);
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}
	/**
	 * 删除信用
	 */
	public void deleteCreditInfoById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				supplierCreditInfo = vixntBaseService.findEntityById(SupplierCreditInfo.class, id);
				if (supplierCreditInfo != null) {
					vixntBaseService.deleteByEntity(supplierCreditInfo);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	private Employee emp;
	private UserAccount entity;
	private UserAccount entityForm;
	private String parentId;
	private String addUserAccountRoleIds;
	private String rolenames;
	public String goSaveOrUpdateEmployee() {
		try {
			if (StringUtils.isNotEmpty(parentId)) {
				supplier = vixntBaseService.findEntityById(Supplier.class, parentId);
			}
			if (supplier != null) {
				if (StringUtils.isNotEmpty(supplier.getEmployeeId())) {
					emp = vixntBaseService.findEntityById(Employee.class, supplier.getEmployeeId());
					if (emp != null) {
						if (emp.getUserAccounts() != null && emp.getUserAccounts().size() > 0) {
							for (UserAccount userAccount : emp.getUserAccounts()) {
								entity = userAccount;
								addUserAccountRoleIds = "";
								rolenames = "";
								if (entity != null) {
									for (Role role : entity.getRoles()) {
										addUserAccountRoleIds += "," + role.getId();
										rolenames += " " + role.getName();
									}
								}
							}
						} else {
							entity = new UserAccount();
							entity.setEmployee(new BaseEmployee(id));
							entity.setEnable("1");
						}
					}
				} else {
					emp = new Employee();
					emp.setSupplier(supplier);
					entity = new UserAccount();
					entity.setEnable("1");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateEmployee";
	}
	public void saveOrUpdateEmployee() {
		boolean isSave = true;
		String accountId = null;
		try {
			initEntityBaseController.initEntityBaseAttribute(emp);
			emp = vixntBaseService.merge(emp);
			if ("0".equals(emp.getUserType())) {
				supplier = vixntBaseService.findEntityById(Supplier.class, emp.getSupplier().getId());
				supplier.setEmployeeId(emp.getId());
				supplier = vixntBaseService.merge(supplier);
			}

			if (null != entityForm.getId()) {
				accountId = entityForm.getId();
				isSave = false;
			}

			String addRoleIds = getRequest().getParameter("addUserAccountRoleIds");
			String deleteRoleIds = getRequest().getParameter("deleteUserAccountRoleIds");
			if (emp != null) {
				accountManagementService.saveOrUpdate(addRoleIds, deleteRoleIds, accountId, "fx", entityForm.getAccount(), entityForm.getPassword(), emp.getId(), "1", emp.getCompanyCode());
			}

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
	public String goChooseSupplier() {
		return "goChooseSupplier";
	}
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public SupplierBankInfo getSupplierBankInfo() {
		return supplierBankInfo;
	}
	public void setSupplierBankInfo(SupplierBankInfo supplierBankInfo) {
		this.supplierBankInfo = supplierBankInfo;
	}
	public SupplierAptitudeInfo getSupplierAptitudeInfo() {
		return supplierAptitudeInfo;
	}
	public void setSupplierAptitudeInfo(SupplierAptitudeInfo supplierAptitudeInfo) {
		this.supplierAptitudeInfo = supplierAptitudeInfo;
	}
	public SupplierAccountingInfo getSupplierAccountingInfo() {
		return supplierAccountingInfo;
	}
	public void setSupplierAccountingInfo(SupplierAccountingInfo supplierAccountingInfo) {
		this.supplierAccountingInfo = supplierAccountingInfo;
	}
	public SupplierCreditInfo getSupplierCreditInfo() {
		return supplierCreditInfo;
	}
	public void setSupplierCreditInfo(SupplierCreditInfo supplierCreditInfo) {
		this.supplierCreditInfo = supplierCreditInfo;
	}
	public SupplierIndicators getSupplierIndicators() {
		return supplierIndicators;
	}
	public void setSupplierIndicators(SupplierIndicators supplierIndicators) {
		this.supplierIndicators = supplierIndicators;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public UserAccount getEntity() {
		return entity;
	}
	public void setEntity(UserAccount entity) {
		this.entity = entity;
	}
	public UserAccount getEntityForm() {
		return entityForm;
	}
	public void setEntityForm(UserAccount entityForm) {
		this.entityForm = entityForm;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the addUserAccountRoleIds
	 */
	public String getAddUserAccountRoleIds() {
		return addUserAccountRoleIds;
	}
	/**
	 * @param addUserAccountRoleIds
	 *            the addUserAccountRoleIds to set
	 */
	public void setAddUserAccountRoleIds(String addUserAccountRoleIds) {
		this.addUserAccountRoleIds = addUserAccountRoleIds;
	}
	/**
	 * @return the rolenames
	 */
	public String getRolenames() {
		return rolenames;
	}
	/**
	 * @param rolenames
	 *            the rolenames to set
	 */
	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}
	public List<SupplierLevel> getSupplierLevelList() {
		return supplierLevelList;
	}
	public void setSupplierLevelList(List<SupplierLevel> supplierLevelList) {
		this.supplierLevelList = supplierLevelList;
	}
}