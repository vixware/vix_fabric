package com.vix.eam.action;

import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.eam.common.action.EamBaseAction;
import com.vix.eam.entity.EqDocument;
import com.vix.eam.entity.EqLevel;
import com.vix.eam.entity.EqMonitorPoint;
import com.vix.eam.entity.EqSparePart;
import com.vix.eam.entity.EqStructure;
import com.vix.eam.entity.EqWarranty;
import com.vix.eam.entity.Equipment;

@Controller
@Scope("prototype")
public class AccountManageAction extends EamBaseAction {
	public static final String GO_PRUCHASE_LIST_CONTENT = "goListContent";
	/** 列表数据页 */
	private static final long serialVersionUID = 1L;


	private List<EqStructure> categoryList;
	private EqStructure eqStructure;

	private String eqId;
	private String parentEqCode;

	private Equipment equipment;

	EqSparePart sparePart;
	EqDocument document;
	EqWarranty warranty;
	EqMonitorPoint monitorPoint;

	/**
	 * 基本信息
	 */
	public String eqJbxx() {
		try {
			if (eqId != null && !"".equals(eqId)) {
				this.equipment = this.baseHibernateService.findEntityById(
						Equipment.class, this.eqId);
			} else {
				if (null != parentId && !"".equals(parentId)) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "eqJbxx";
	}

	/**
	 * 保存基本信息
	 */
	public String saveJbxx() {
		if (equipment != null) {
			try {
				if (equipment.getId() != null && !equipment.getId().equals("")
						&& !equipment.getId().equals("0")) {
					this.baseHibernateService.update(equipment);
					EqStructure eqNode = this.baseHibernateService
							.findEntityByAttribute(EqStructure.class, "eqCode",
									equipment.getEqCode());
					if (equipment.getHasSub() != null
							&& equipment.getHasSub() == 1) {
						if (eqNode != null && eqNode.getId().equals("")
								&& !eqNode.getId().equals("0")) {
							if (equipment.getEqName() != null
									&& !equipment.getEqName().equals(
											eqNode.getEqName())) {
								eqNode.setEqName(equipment.getEqName());
								this.baseHibernateService.update(eqNode);
							}
						} else {
							eqNode = new EqStructure();
							eqNode.setEqCode(equipment.getEqCode());
							eqNode.setEqName(equipment.getEqName());
							eqNode.setParentId(this.getParentId());
							eqNode.setEqType("1");
							this.baseHibernateService.save(eqNode);
						}
					} else {
						if (eqNode != null && eqNode.getId().equals("")
								&& !eqNode.getId().equals("0")) {
							// 逻辑上应该删掉去掉对应的设备位置
							// 删除后应该有催已经添加的下级设备做一定处理
							// 暂时不做自动删除处理
						}
					}
				} else {
					this.baseHibernateService.save(equipment);
					if (equipment.getHasSub() != null
							&& equipment.getHasSub() == 1) {
						EqStructure eqNode = new EqStructure();
						eqNode.setEqCode(equipment.getEqCode());
						eqNode.setEqName(equipment.getEqName());
						eqNode.setParentId(this.getParentId());
						eqNode.setEqType("1");
						this.baseHibernateService.save(eqNode);
					}
				}

				BaseSecAction.renderText(this.equipment.getId().toString());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return NONE;
	}

	public String eqJscs() {
		return "eqJscs";
	}

	public String eqBjxx() {
		return "eqBjxx";
	}

	public String eqBjxxPager() {
		// 备件信息
		if (eqId != null && !"".equals(eqId)) {
			Map<String, Object> params = getParams();
			this.addTimeLimitToParam(params);
			params.put("equipment.id," + SearchCondition.EQUAL, this.eqId);

			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			// 在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			try {
				this.baseHibernateService.findPagerByHqlConditions(pager,
						EqSparePart.class, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "eqBjxxPager";
	}

	public String eqBjxxEdit() {
		if (this.id != null && !"".equals(id)) {
			try {
				this.sparePart = this.baseHibernateService.findEntityById(
						EqSparePart.class, this.id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "eqBjxxEdit";
	}

	public void saveEqBjxx() {
		this.saveBaseEntity(this.sparePart);

		BaseSecAction.renderText(String.valueOf(this.sparePart.getId()));
	}

	public String eqJcxx() {
		return "eqJcxx";
	}

	public String eqJcxxPager() {
		return "eqJcxxPager";
	}

	public String eqJcxxEdit() {
		return "eqJcxxEdit";
	}

	public String eqBxxx() {
		return "eqBxxx";
	}

	public String eqBxxxPager() {
		// 保修信息
		if (eqId != null && !"".equals(eqId)) {
			Map<String, Object> params = getParams();
			this.addTimeLimitToParam(params);
			params.put("equipment.id," + SearchCondition.EQUAL, this.eqId);

			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			// 在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			try {
				this.baseHibernateService.findPagerByHqlConditions(pager,
						EqWarranty.class, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "eqBxxxPager";
	}

	public String eqBxxxEdit() {
		if (this.id != null && !"".equals(id)) {
			try {
				this.warranty = this.baseHibernateService.findEntityById(
						EqWarranty.class, this.id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "eqBxxxEdit";
	}

	public void saveEqBxxx() {
		this.saveBaseEntity(this.warranty);

		BaseSecAction.renderText(String.valueOf(this.warranty.getId()));
	}

	public String eqSbwd() {
		return "eqSbwd";
	}

	public String eqSbwdPager() {
		// 设备文档
		if (eqId != null && !"".equals(eqId)) {
			Map<String, Object> params = getParams();
			this.addTimeLimitToParam(params);
			params.put("equipment.id," + SearchCondition.EQUAL, this.eqId);

			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			// 在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			try {
				this.baseHibernateService.findPagerByHqlConditions(pager,
						EqDocument.class, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "eqSbwdPager";
	}

	public String eqSbwdEdit() {
		if (this.id != null && !"".equals(id)) {
			try {
				this.document = this.baseHibernateService.findEntityById(
						EqDocument.class, this.id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "eqSbwdEdit";
	}

	public void saveEqSbwd() {
		if (this.document == null)
			return;

		String[] savePathAndName = this.saveUploadFile();
		if (savePathAndName != null && savePathAndName.length == 2) {
			this.document.setFileName(savePathAndName[1]);
			this.document.setFilePath(savePathAndName[0]);
		}

		this.document.setUploadPersonId(SecurityUtil.getCurrentUserId());
		this.document.setUploadPerson(SecurityUtil.getCurrentUserName());
		this.document.setUploadTime(new Date());

		this.saveBaseEntity(this.document);

		BaseSecAction.renderText(String.valueOf(this.document.getId()));
	}

	public String downloadEqDocument() {
		if (null != id && !"".equals(id)) {
			try {
				EqDocument doc = baseHibernateService.findEntityById(
						EqDocument.class, this.id);
				String fileName = doc.getFileName();
				String filePath = doc.getFilePath();
				String title = doc.getTitle();
				int idx = fileName.lastIndexOf(".");
				if (idx != -1) {
					title = title + fileName.substring(idx);
				}

				this.setOriFileName(title);

				String downloadFile = filePath + fileName;
				this.setInputStream(new FileInputStream(downloadFile));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return NONE;
		}
		return "downloadEqDocument";
	}

	public void executeDrools() throws Exception {

	}

	public void executeEsper() throws Exception {

	}

	// 档案管理
	public String daArchiveRecordMgr() {
		return "daArchiveRecordMgr";
	}

	public String daArchiveRecordMgrList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentId," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), EqStructure.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "daArchiveRecordMgrList";
	}

	public String daArchiveRecordMgrDetail() {
		return "daArchiveRecordMgrDetail";
	}

	// 活动与变动管理
	public String hdStatusRecordMgr() {
		return "hdStatusRecordMgr";
	}

	public String hdStatusRecordMgrList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentId," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), EqStructure.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "hdStatusRecordMgrList";
	}

	public String hdStatusRecordMgrDetail() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentId," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), EqStructure.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "hdStatusRecordMgrDetail";
	}

	// 故障体系结构
	public String gzErrorCodeMgr() {
		return "gzErrorCodeMgr";
	}

	public String gzErrorCodeMgrList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentId," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), EqStructure.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gzErrorCodeMgrList";
	}

	public String gzErrorCodeMgrDetail() {
		return "gzErrorCodeMgrDetail";
	}

	// 保修服务合同管理
	public String bxServiceInfoMgr() {
		return "bxServiceInfoMgr";
	}

	public String bxServiceInfoMgrList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentId," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), EqStructure.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bxServiceInfoMgrList";
	}

	public String bxServiceInfoMgrDetail() {
		return "bxServiceInfoMgrDetail";
	}

	// 备件管理
	public String sbRepairPartMgr() {
		return "sbRepairPartMgr";
	}

	public String sbRepairPartMgrList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentId," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), EqStructure.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sbRepairPartMgrList";
	}

	public String sbRepairPartMgrDetail() {
		return "sbRepairPartMgrDetail";
	}

	// 设备基础数据管理
	public String sjBaseDataMgr() {
		return "sjBaseDataMgr";
	}

	public String sjBaseDataMgr_eqLevel() {
		try {
			Map<String, Object> params = getParams();
			this.addTimeLimitToParam(params);
			Pager pager = getPager();
			pager.setPageSize(10);
			pager = this.baseHibernateService.findPagerByHqlConditions(pager,
					EqLevel.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sjBaseDataMgr_eqLevel";
	}

	public String sjBaseDataMgr_eqType() {
		return "sjBaseDataMgr_eqType";
	}

	public String sjBaseDataMgr_eqCategory() {
		return "sjBaseDataMgr_eqCategory";
	}

	public String sjBaseDataMgr_eqTechParam() {
		return "sjBaseDataMgr_eqTechParam";
	}

	// 逻辑设备层次结构
	public String ljLogicTreeMgr() {
		return "ljLogicTreeMgr";
	}

	public String ljLogicTreeMgrList() {
		try {
			Map<String, Object> params = getParams();
			this.addTimeLimitToParam(params);
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentId," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), EqStructure.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ljLogicTreeMgrList";
	}

	public String ljLogicTreeMgrDetail() {
		return "ljLogicTreeMgrDetail";
	}

	public String saveEqCategory() {
		if (eqStructure != null) {
			try {
				if (eqStructure.getId() != null
						&& eqStructure.getId().equals("")
						&& !eqStructure.getId().equals("0")) {
					this.baseHibernateService.update(eqStructure);
				} else {
					this.baseHibernateService.save(eqStructure);
				}

				BaseSecAction.renderText(this.eqStructure.getId().toString());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return NONE;
	}

	// 统计分析
	public String tjStatisticMgr() {
		return "tjStatisticMgr";
	}

	// 台账管理
	public String eqMove() {
		return "eqMove";
	}

	public String eqReplace() {
		return "eqReplace";
	}

	public String eqRepair() {
		return "eqRepair";
	}

	public String eqRemove() {
		return "eqRemove";
	}

	public String eqRepairRecord() {
		return "eqRepairRecord";
	}

	public String eqMoveRecord() {
		return "eqMoveRecord";
	}

	@Override
	public String goList() {
		try {
			categoryList = baseHibernateService
					.findAllByEntityClass(EqStructure.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			this.addTimeLimitToParam(params);
			String name = getRequest().getParameter("name");
			if (null != name && !"".equals(name)) {
				params.put("eqName," + SearchCondition.ANYLIKE, name);
			}

			if (null != this.parentEqCode && this.parentEqCode.length() > 0) {
				params.put("parentEquipment," + SearchCondition.EQUAL,
						this.parentEqCode);
			}

			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			// 在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			baseHibernateService.findPagerByHqlConditions(pager, Equipment.class,
					params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_PRUCHASE_LIST_CONTENT;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			baseHibernateService.deleteById(EqStructure.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goChooseCategory() {
		return "goChooseCategory";
	}

	public static String getGoPruchaseListContent() {
		return GO_PRUCHASE_LIST_CONTENT;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public List<EqStructure> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<EqStructure> categoryList) {
		this.categoryList = categoryList;
	}


	public String getParentEqCode() {
		return parentEqCode;
	}

	public void setParentEqCode(String parentEqCode) {
		this.parentEqCode = parentEqCode;
	}

	public EqSparePart getSparePart() {
		return sparePart;
	}

	public void setSparePart(EqSparePart sparePart) {
		this.sparePart = sparePart;
	}

	public EqDocument getDocument() {
		return document;
	}

	public void setDocument(EqDocument document) {
		this.document = document;
	}

	public EqWarranty getWarranty() {
		return warranty;
	}

	public void setWarranty(EqWarranty warranty) {
		this.warranty = warranty;
	}

	public EqMonitorPoint getMonitorPoint() {
		return monitorPoint;
	}

	public void setMonitorPoint(EqMonitorPoint monitorPoint) {
		this.monitorPoint = monitorPoint;
	}

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	public EqStructure getEqStructure() {
		return eqStructure;
	}

	public void setEqStructure(EqStructure eqStructure) {
		this.eqStructure = eqStructure;
	}

}
