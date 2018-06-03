package com.vix.system.action.expand;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.system.expand.service.ISpecificationService;

@Controller
@Scope("prototype")
public class SpecificationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ISpecificationService specificationService;

	private String id;
	private String companyCode;
	private Specification specification;
	private List<Specification> specificationList;//规格列表

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				params.put("objectExpand.id" + "," + SearchCondition.EQUAL, id);
			}
			if (null != companyCode && !"".equals(companyCode)) {
				params.put("companyCode," + SearchCondition.EQUAL, companyCode);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = specificationService.findPagerByHqlConditions(getPager(), Specification.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
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
						if(itemCatalog.getSpecifications().size() > 0){
							for (Specification spec : itemCatalog.getSpecifications()) {
								if(null != spec && StrUtils.objectIsNotNull(spec.getSpecificationTableName())){
									tableName = spec.getSpecificationTableName();
									break;
								}
							}
						}
						/** 生成表名*/
						if(StrUtils.objectIsNotNull(tableName)){
							specification.setSpecificationTableName(tableName);
						}else{
							SimpleDateFormat sdfTable = new SimpleDateFormat("yyyyMMddhhmmss");
							specification.setSpecificationTableName("SPECTABLE_" + sdfTable.format(new Date()));
						}
						/** 生成字段名*/
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
				setMessage(getText("ec_specificationUnBindCategory"));
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
				setMessage(getText("ec_specificationUnBindCategory"));
				return UPDATE;
			}
			specification = specificationService.merge(specification);
			setMessage(specification.getId().toString().trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	/** 创建数据表 */
	public String createOrUpdateTable() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ItemCatalog oe = specificationService.findEntityById(ItemCatalog.class, id);
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
				if (null != s.getItemCatalog() && s.getItemCatalog().getSpecifications().size() == 1) {
					specificationService.dropTable(PropertyConfigLoader.dbType, s.getSpecificationTableName());
				}
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
				setMessage(getText("ec_specificationNotExist"));
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
				specificationList = specificationService.findAllByEntityClassAndAttribute(Specification.class, "objectExpand.id", id);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "specification";
	}

	public String getId() {
		return id;
	}

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
}
