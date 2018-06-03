/**
 * 
 */
package com.vix.nvix.system.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.system.expand.service.ISpecificationService;

/**
 * @author Bluesnow 2016年8月26日
 * 
 *         商品规格管理
 * 
 */
@Controller
@Scope("prototype")
public class NvixSpecificationAction extends VixntBaseAction {

	private static final long serialVersionUID = 9027659965427603496L;

	@Autowired
	private ISpecificationService specificationService;

	private String id;
	private String parentId;
	private String itemCatalogId;
	private String detailName;
	private String searchName;
	private String companyCode;
	private Specification specification;
	private SpecificationDetail specificationDetail;
	private List<Specification> specificationList;// 规格列表

	/** 获取列表数据 */
	public void getSpecificationJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (StrUtils.isNotEmpty(itemCatalogId)) {
				params.put("itemCatalog.id," + SearchCondition.EQUAL, itemCatalogId);
			}
			if (StrUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			specificationService.findPagerByHqlConditions(pager, Specification.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至规格修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				specification = specificationService.findEntityById(Specification.class, id);
			} else {
				String itemCatalogId = getRequestParameter("itemCatalogId");
				if (StringUtils.isNotEmpty(itemCatalogId)) {
					ItemCatalog itemCatalog = specificationService.findEntityById(ItemCatalog.class, itemCatalogId);
					if (null != itemCatalog) {
						specification = new Specification();
						specification.setItemCatalog(itemCatalog);
						specification.setIsTemp("1");
						String tableName = "";
						if (itemCatalog.getSpecifications().size() > 0) {
							for (Specification spec : itemCatalog.getSpecifications()) {
								if (null != spec && StrUtils.objectIsNotNull(spec.getSpecificationTableName())) {
									tableName = spec.getSpecificationTableName();
									break;
								}
							}
						}
						/** 生成表名 */
						if (StrUtils.objectIsNotNull(tableName)) {
							specification.setSpecificationTableName(tableName);
						} else {
							SimpleDateFormat sdfTable = new SimpleDateFormat("yyyyMMddhhmmss");
							specification.setSpecificationTableName("SPECTABLE_" + sdfTable.format(new Date()));
						}
						/** 生成字段名 */
						SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
						specification.setCode("s_c_" + sdf.format(new Date()));

						loadCommonData(specification);
						specification = specificationService.merge(specification);
					}
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
			if (null != specification.getId()) {
				isSave = false;
			} else {
				loadCommonData(specification);
			}
			if (null == specification.getItemCatalog() || null == specification.getItemCatalog().getId()) {
				setMessage("没有绑定商品分类!");
				return UPDATE;
			}
			specification.setIsTemp("0");
			specification = specificationService.mergeSpecification(specification);
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

	/** 处理修改操作 */
	public String saveOrUpdateInner() {
		try {
			if (null == specification.getItemCatalog() || null == specification.getItemCatalog().getId()) {
				renderText("没有绑定商品分类!");
				return UPDATE;
			}
			specification = specificationService.merge(specification);
			renderText(specification.getId().toString().trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	/** 创建数据表 */
	public String createOrUpdateTable() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				specification = vixntBaseService.findEntityById(Specification.class, id);
				if (specification != null) {
					ItemCatalog oe = specificationService.findEntityById(ItemCatalog.class, specification.getItemCatalog().getId());
					if (null == oe) {
						setMessage("请选择对象类型!");
						return UPDATE;
					}
					if (null == oe.getSpecifications() || oe.getSpecifications().size() <= 0) {
						setMessage("分类的规格为空,不能生成规格数据表!");
						return UPDATE;
					} else {
						Specification s = oe.getSpecifications().iterator().next();
						boolean tag = false;
						if (!specificationService.checkTableExist(PropertyConfigLoader.dbType, s.getSpecificationTableName())) {
							/** 创建数据表 */
							tag = specificationService.createTable(PropertyConfigLoader.dbType, oe, s.getSpecificationTableName());
							/** 设置规格数据表已经生成 */
							oe.setSpecTableIsGenerate("1");
							specificationService.merge(oe);
						}
						if (tag) {
							String hql = "update Specification s set s.specificationTableName = :specificationTableName where s.objectExpand.id = :oeId";
							Map<String, Object> p = new HashMap<String, Object>();
							p.put("specificationTableName", s.getSpecificationTableName());
							p.put("oeId", oe.getId());
							specificationService.batchUpdateByHql(hql, p);
							setMessage("规格数据表创建成功!");
						} else {
							setMessage("规格数据表已创建!");
						}
					}
				}
			} else {
				setMessage("分类获取失败!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Specification s = specificationService.findEntityById(Specification.class, id);
			if (null != s) {
				if (specificationService.checkTableExist(PropertyConfigLoader.dbType, s.getSpecificationTableName())) {
					if (specificationService.checkColumnExist(PropertyConfigLoader.dbType, s.getSpecificationTableName(), s.getCode())) {
						specificationService.deleteTableField(PropertyConfigLoader.dbType, s.getSpecificationTableName(), s.getCode());
					}
				}
				specificationService.deleteByAttribute(SpecificationDetail.class, "specification.id", s.getId());
				/*
				 * if (null != s.getObjectExpand() &&
				 * s.getObjectExpand().getSpecifications().size() == 1) {
				 * specificationService.dropTable(PropertyConfigLoader.dbType,
				 * s.getSpecificationTableName()); }
				 */
				specificationService.deleteByEntity(s);
				/** 清理空数据 */
				List<Specification> specList = specificationService.findAllByEntityClassAndAttribute(Specification.class, "isTemp", "1");
				for (Specification spec : specList) {
					if (null != spec) {
						if (null != spec.getSpecificationDetails() && spec.getSpecificationDetails().size() > 0) {
							specificationService.deleteByAttribute(SpecificationDetail.class, "specification.id", spec.getId());
						}
						specificationService.deleteByEntity(spec);
					}
				}
				renderText(DELETE_SUCCESS);
			} else {
				renderText("商品规格不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 选择分类下的规格供，以便于生产规格表 */
	public String chooseSpecification() {
		if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
			try {
				specificationList = specificationService.findAllByEntityClassAndAttribute(Specification.class, "itemCatalog.id", id);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "specification";
	}
	public void goSingleSpecificationDetail() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String name = getDecodeRequestParameter("name");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("specification.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, SpecificationDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdateSpecificationDetail() {
		try {
			String detailId = getDecodeRequestParameter("detailId");
			if (StringUtils.isNotEmpty(detailId)) {
				specificationDetail = vixntBaseService.findEntityById(SpecificationDetail.class, detailId);
			} else {
				specificationDetail = new SpecificationDetail();
				SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSS");
				specificationDetail.setCode("sd_code_" + sdf.format(new Date()));
				if (StringUtils.isNotEmpty(id)) {
					specification = vixntBaseService.findEntityById(Specification.class, id);
					if (specification != null) {
						specificationDetail.setSpecification(specification);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSpecificationDetail";
	}
	public void saveOrUpdateDetail() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(specificationDetail.getId())) {
				isSave = false;
				specificationDetail.setUpdateTime(new Date());
			} else {
				specificationDetail.setCreateTime(new Date());
			}
			specificationDetail = vixntBaseService.merge(specificationDetail);
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

	/** 处理删除操作 */
	public void deleteDetailById() {
		try {
			SpecificationDetail sd = vixntBaseService.findEntityById(SpecificationDetail.class, id);
			if (null != sd) {
				vixntBaseService.deleteByAttribute(SpecificationDetail.class, "specification.id", sd.getId());
				vixntBaseService.deleteByEntity(sd);
				renderText(DELETE_SUCCESS);
			} else {
				renderText("规格明细不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/**
	 * 保存附件
	 */
	public void uploadSpecificationDetailImage() {
		try {
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				renderText("1:/img/ws/" + savePathAndName[1].toString() + ":" + savePathAndName[1].toString());
			} else {
				renderText("0:上传失败!");
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

	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

	public List<Specification> getSpecificationList() {
		return specificationList;
	}

	public void setSpecificationList(List<Specification> specificationList) {
		this.specificationList = specificationList;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getItemCatalogId() {
		return itemCatalogId;
	}

	public void setItemCatalogId(String itemCatalogId) {
		this.itemCatalogId = itemCatalogId;
	}

	public SpecificationDetail getSpecificationDetail() {
		return specificationDetail;
	}

	public void setSpecificationDetail(SpecificationDetail specificationDetail) {
		this.specificationDetail = specificationDetail;
	}
}