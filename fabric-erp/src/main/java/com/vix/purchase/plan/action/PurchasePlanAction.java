package com.vix.purchase.plan.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;
import com.vix.mdm.purchase.plan.entity.PurchasePlanPackage;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.purchase.plan.controller.PurchasePlanController;

/**
 * 采购计划管理
 * 
 * @ClassFullName com.vix.purchase.plan.action.PurchasePlanAction
 *
 * @author bjitzhang
 *
 * @date 2016年1月25日
 *
 */
@Controller
@Scope("prototype")
public class PurchasePlanAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IItemService itemService;
	@Autowired
	private PurchasePlanController purchasePlanController;
	private String id;
	private String pageNo;
	/** 附件 */
	private File fileToUpload;
	private String categoryId;
	/** 附件的名称 */
	private String fileToUploadFileName;
	private PurchasePlan purchasePlan;
	private PurchasePlanItems purchasePlanItems;
	/**
	 * 计量单位
	 */
	private List<MeasureUnit> measureUnitList;
	private List<PurchasePlan> purchasePlanList;
	private String purchasePlanId;
	private String purchasePlanIds;
	private String organdempid;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			purchasePlanList = purchasePlanController.findPurchasePlanList(params);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isReport," + SearchCondition.EQUAL, "0");
			String status = getRequestParameter("status");
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 记录当前页面
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}

			String title = getRequestParameter("title");
			if (title != null && !"".equals(title)) {
				params.put("name," + SearchCondition.EQUAL, title);
			}
			String supplierName = getRequestParameter("supplierName");
			if (supplierName != null && !"".equals(supplierName)) {
				params.put("supplierName," + SearchCondition.EQUAL, supplierName);
			}
			//获取当前登陆人需要填写的计划
			if (SecurityUtil.getCurrentEmpId() != null) {
				params.put("employee.id," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpId());
			}
			Pager pager = purchasePlanController.goSingleList(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdatePurchasePlanItems() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			measureUnitList = purchasePlanController.doListMeasureUnitList(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlanItems = purchasePlanController.doListPurchasePlanItemsById(id);
			} else {
				purchasePlanItems = new PurchasePlanItems();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdatePurchasePlanItems";
	}

	public void getPurchasePlanItemsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchasePlan purchasePlan = purchasePlanController.doListEntityById(id);
				if (null != purchasePlan) {
					json = convertListToJson(new ArrayList<PurchasePlanItems>(purchasePlan.getPurchasePlanItems()), purchasePlan.getPurchasePlanItems().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public String deletePurchasePlanItemsById() {
		try {
			PurchasePlanItems purchasePlanItems = purchasePlanController.doListPurchasePlanItemsById(id);
			if (null != purchasePlanItems) {
				purchasePlanController.deletePurchasePlanItems(purchasePlanItems);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goListItemList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("itemClass," + SearchCondition.IN, "goods,finishedgoods");
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("id");
				getPager().setOrderBy("desc");
			}
			getPager().setPageSize(10);
			if (null != categoryId && !"".equals(categoryId)) {
				Pager pager = itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
				setPager(pager);
			} else {
				itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListItemList";
	}

	public String saveOrUpdatePurchasePlanItems() {
		boolean isSave = true;
		try {
			if (null != purchasePlanItems.getId() && !"".equals(purchasePlanItems.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlan = purchasePlanController.doListEntityById(id);
				if (purchasePlan != null) {
					purchasePlanItems.setPurchasePlan(purchasePlan);
				}
				//
				Double price = 0D;
				if (purchasePlanItems.getAmount() != null && purchasePlanItems.getUnitcost() != null) {
					price = purchasePlanItems.getAmount() * purchasePlanItems.getUnitcost();
					purchasePlanItems.setPrice(price);
				}
				//
				purchasePlanController.doSavePurchasePlanItems(purchasePlanItems);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 填写普通计划
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlan = purchasePlanController.doListEntityById(id);
			} else {
				purchasePlan = new PurchasePlan();
				purchasePlan.setStatus("1");
				purchasePlan.setPurchasePlanCode(autoCreateCode.getBillNO(BillType.PUR_PLAN));
				Employee employee = getEmployee();
				if (employee != null) {
					purchasePlan.setCreator(employee.getName());
				} else {
					purchasePlan.setCreator(SecurityUtil.getCurrentUserName());
				}
				purchasePlan = purchasePlanController.doSavePurchasePlan(purchasePlan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 计划任务
	 * 
	 * @return
	 */
	public String goWriteTaskPlan() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlan = purchasePlanController.doListEntityById(id);
			} else {
				purchasePlan = new PurchasePlan();
				purchasePlan.setStatus("2");
				purchasePlan.setPurchasePlanCode(autoCreateCode.getBillNO(BillType.PUR_PLAN));
				Employee employee = getEmployee();
				if (employee != null) {
					purchasePlan.setCreator(employee.getName());
				} else {
					purchasePlan.setCreator(SecurityUtil.getCurrentUserName());
				}
				//purchasePlan = purchasePlanController.doSavePurchasePlan(purchasePlan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goWriteTaskPlan";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != purchasePlan.getId() && !"".equals(purchasePlan.getId())) {
				isSave = false;
			}
			// 设置采购订单状态为未审批
			purchasePlan.setIsReport("0");
			// 采购计划明细
			initEntityBaseController.initEntityBaseAttribute(purchasePlan);
			//处理修改留痕
			billMarkProcessController.processMark(purchasePlan, updateField);

			//计划
			if (StringUtils.isEmpty(purchasePlan.getStatus())) {
				purchasePlan.setStatus("1");
			}
			// 获取当前员工信息
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = purchasePlanController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					purchasePlan.setEmployee(employee);
				}
			}
			PurchasePlan p = purchasePlanController.doListEntityById(purchasePlan.getId());
			Double price = 0D;
			Double amount = 0D;
			Set<PurchasePlanItems> purchasePlanItemsset = p.getPurchasePlanItems();
			for (PurchasePlanItems purchasePlanItems : purchasePlanItemsset) {
				price += purchasePlanItems.getPrice();
				amount += purchasePlanItems.getAmount();
			}
			purchasePlan.setPrice(price);
			purchasePlan.setAmount(amount);
			if (purchasePlan.getPurchasePlanPackage() == null || StringUtils.isEmpty(purchasePlan.getPurchasePlanPackage().getId())) {
				purchasePlan.setPurchasePlanPackage(null);
			}
			purchasePlan = purchasePlanController.doSavePurchasePlan(purchasePlan);
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

	//计划下发到部门或人
	public String planToDoP() {
		boolean isSave = true;
		try {
			PurchasePlanPackage purchasePlanPackage = new PurchasePlanPackage();
			purchasePlanPackage.setCode(autoCreateCode.getBillNO(BillType.PUR_PLAN));
			purchasePlanPackage.setName(purchasePlan.getPurchasePlanName());
			purchasePlanPackage.setPlanType("T");
			purchasePlanPackage.setStatus("0");
			initEntityBaseController.initEntityBaseAttribute(purchasePlanPackage);
			purchasePlanPackage = purchasePlanController.doSavePurchasePlanPackage(purchasePlanPackage);
			if (StringUtils.isNotEmpty(purchasePlan.getExecuteType())) {
				//下发计划到部门或个人
				if ("D".equals(purchasePlan.getExecuteType())) {
					organdempid = StringUtils.substring(organdempid, 0, organdempid.length() - 1);
					OrganizationUnit organizationUnit = purchasePlanController.doListOrganizationUnitById(organdempid);
					if (organizationUnit != null && organizationUnit.getId() != null) {
						Map<String, Object> pa = new HashMap<String, Object>();
						pa.put("organizationUnit.id," + SearchCondition.EQUAL, organizationUnit.getId());
						List<Employee> employeeList = purchasePlanController.doListEmployeeList(pa);
						for (Employee e : employeeList) {
							if (e != null) {
								PurchasePlan p = new PurchasePlan();
								BeanUtils.copyProperties(purchasePlan, p, new String[] { "id" });
								// 设置采购订单状态为未审批
								p.setStatus("S1");
								p.setIsReport("0");
								initEntityBaseController.initEntityBaseAttribute(p);
								//处理修改留痕
								billMarkProcessController.processMark(p, updateField);
								//计划任务
								p.setStatus("2");
								p.setEmployee(e);
								if (p.getSupplier() == null || StringUtils.isEmpty(p.getSupplier().getId())) {
									p.setSupplier(null);
								}
								p.setPurchasePlanCode(autoCreateCode.getBillNO(BillType.PUR_PLAN));
								p.setPurchasePlanPackage(purchasePlanPackage);
								p.setCreator(e.getName());
								p = purchasePlanController.doSavePurchasePlan(p);
							}
						}
					}
				} else if ("P".equals(purchasePlan.getExecuteType())) {
					Employee employee = purchasePlanController.doListEmployeeById(organdempid);
					if (employee != null) {
						purchasePlan.setEmployee(employee);
						// 设置采购订单状态为未审批
						purchasePlan.setStatus("S1");
						purchasePlan.setIsReport("0");
						// 采购计划明细
						initEntityBaseController.initEntityBaseAttribute(purchasePlan);
						//处理修改留痕
						billMarkProcessController.processMark(purchasePlan, updateField);
						//计划任务
						purchasePlan.setStatus("2");
						purchasePlan.setCreator(employee.getName());
						purchasePlan.setPurchasePlanCode(autoCreateCode.getBillNO(BillType.PUR_PLAN));
						purchasePlan.setPurchasePlanPackage(purchasePlanPackage);
						purchasePlan = purchasePlanController.doSavePurchasePlan(purchasePlan);
					}
				}
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
		return UPDATE;
	}

	public String goPurchasePlanPackage() {
		return "goPurchasePlanPackage";
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			PurchasePlan purchasePlan = purchasePlanController.doListEntityById(id);
			if (null != purchasePlan) {
				purchasePlanController.doDeleteByEntity(purchasePlan);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取附件json数据 */
	public void getAttachmentsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PurchasePlan po = purchasePlanController.findEntityById(id);
				json = convertListToJson(new ArrayList<Attachments>(po.getAttachments()), po.getAttachments().size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String addAttachments() {
		return "addAttachments";
	}

	/** 上传附件 */
	public void uploadAttachments() {
		try {
			String idStr = getRequestParameter("id");
			if (null != idStr && !"".equals(idStr)) {
				PurchasePlan po = purchasePlanController.findEntityById(idStr);
				if (null != fileToUpload) {
					String separator = System.getProperty("file.separator");
					/** 上传目录 */
					String baseDir = "E:\\upload";
					File dir = new File(baseDir);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
					String[] fileNames = fileToUploadFileName.split("\\.");
					String fileName = fileNames[0];
					String extFileName = fileNames[fileNames.length - 1];
					String newFilePath = "";
					long timeTemp = System.currentTimeMillis();
					String newFileName = fileName + "_" + timeTemp + "." + extFileName;
					newFilePath = baseDir + separator + newFileName;

					BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(newFilePath));
					byte[] buf = new byte[1024 * 100];
					int len = -1;
					while ((len = bufIn.read(buf)) != -1) {
						bufOut.write(buf, 0, len);
					}
					bufOut.close();
					bufIn.close();
					Attachments atts = new Attachments();
					atts.setName(newFileName);
					atts.setPurchasePlan(po);
					purchasePlanController.mergeAttachments(atts);
					renderJson("文件上传成功!");
				}
			} else {
				renderJson("订单id获取失败!");
			}

		} catch (Exception ex) {
			renderJson("文件上传失败!");
			ex.printStackTrace();
		}

	}

	/** 删除附件 */
	public void deleteAttachments() {
		try {
			String afId = getRequestParameter("afId");
			if (null != afId && !"".equals(afId)) {
				Attachments atts = purchasePlanController.findAttachmentsEntityById(afId);
				if (null != atts) {
					/** 上传目录 */
					String separator = System.getProperty("file.separator");
					String baseDir = getServletContext().getRealPath(separator + "richContent");
					baseDir += separator;
					baseDir += atts.getName();
					File file = new File(baseDir);
					if (file.exists()) {
						file.delete();
					}
					purchasePlanController.deleteAttachmentsEntity(atts);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取明细数据行数 */
	public void getPurchasePlanItemsCount() {
		try {
			Integer count = 0;
			if (null != id && !"".equals(id)) {
				PurchasePlan pp = purchasePlanController.findEntityById(id);
				if (null != pp && null != pp.getPurchasePlanItems()) {
					count = pp.getPurchasePlanItems().size();
				}
			}
			renderJson(count.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("0");
		}
	}

	/** 获取列表数据 */
	public String goSubRadioSingleList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			params.put("status," + SearchCondition.EQUAL, "3");
			Pager pager = purchasePlanController.goSingleList2(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioSingleList";
	}

	public String goChooseRadioSupplier() {
		return "goChooseRadioSupplier";
	}

	public String goChoosePurchasePlanPackage() {
		return "goChoosePurchasePlanPackage";
	}

	/** 获取列表数据 */
	public String goPurchasePlanPackageSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = purchasePlanController.goPurchasePlanPackagePager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchasePlanPackageSingleList";
	}

	public String goPurchasePlanDetail() {
		try {
			if (StringUtils.isNotEmpty(purchasePlanId)) {
				purchasePlan = purchasePlanController.findEntityById(purchasePlanId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchasePlanDetail";
	}

	public String goPurchasePlanDetailList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("purchasePlan.id," + SearchCondition.EQUAL, purchasePlanId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchasePlanDetailList";
	}

	/**
	 * 跳转到用户查看页面
	 * 
	 * @return
	 */
	public String goShowPurchasePlan() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlan = purchasePlanController.findEntityById(purchasePlanId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchasePlan";
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * @return the pageNo
	 */
	public String getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the fileToUpload
	 */
	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	/**
	 * @param fileToUpload
	 *            the fileToUpload to set
	 */
	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	/**
	 * @return the fileToUploadFileName
	 */
	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	/**
	 * @param fileToUploadFileName
	 *            the fileToUploadFileName to set
	 */
	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}

	/**
	 * @return the purchasePlan
	 */
	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	/**
	 * @return the organdempid
	 */
	public String getOrgandempid() {
		return organdempid;
	}

	/**
	 * @param organdempid
	 *            the organdempid to set
	 */
	public void setOrgandempid(String organdempid) {
		this.organdempid = organdempid;
	}

	/**
	 * @param purchasePlan
	 *            the purchasePlan to set
	 */
	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	/**
	 * @return the purchasePlanItems
	 */
	public PurchasePlanItems getPurchasePlanItems() {
		return purchasePlanItems;
	}

	/**
	 * @param purchasePlanItems
	 *            the purchasePlanItems to set
	 */
	public void setPurchasePlanItems(PurchasePlanItems purchasePlanItems) {
		this.purchasePlanItems = purchasePlanItems;
	}

	/**
	 * @return the measureUnitList
	 */
	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	/**
	 * @param measureUnitList
	 *            the measureUnitList to set
	 */
	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}

	/**
	 * @return the purchasePlanList
	 */
	public List<PurchasePlan> getPurchasePlanList() {
		return purchasePlanList;
	}

	/**
	 * @param purchasePlanList
	 *            the purchasePlanList to set
	 */
	public void setPurchasePlanList(List<PurchasePlan> purchasePlanList) {
		this.purchasePlanList = purchasePlanList;
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
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the purchasePlanId
	 */
	public String getPurchasePlanId() {
		return purchasePlanId;
	}

	/**
	 * @param purchasePlanId
	 *            the purchasePlanId to set
	 */
	public void setPurchasePlanId(String purchasePlanId) {
		this.purchasePlanId = purchasePlanId;
	}

	/**
	 * @return the purchasePlanIds
	 */
	public String getPurchasePlanIds() {
		return purchasePlanIds;
	}

	/**
	 * @param purchasePlanIds
	 *            the purchasePlanIds to set
	 */
	public void setPurchasePlanIds(String purchasePlanIds) {
		this.purchasePlanIds = purchasePlanIds;
	}

}
