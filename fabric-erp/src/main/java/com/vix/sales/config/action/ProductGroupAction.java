package com.vix.sales.config.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.Regional;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
import com.vix.sales.config.entity.ProductGroup;

@Controller
@Scope("prototype")
public class ProductGroupAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private String ids;
	private ProductGroup productGroup;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			if (null != id && !"".equals(id)) {
				params.put("salesOrg.id," + SearchCondition.EQUAL, id);
			}
			String name = getRequest().getParameter("name");
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), ProductGroup.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id) && !"0".equals(id)) {
				productGroup = baseHibernateService.findEntityById(
						ProductGroup.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			} else {
				productGroup = new ProductGroup();
				productGroup.setIsTemp("1");
				loadCommonData(productGroup);
				productGroup = baseHibernateService.merge(productGroup);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		try {
			if (StrUtils.objectIsNotNull(productGroup.getId())) {
				ProductGroup pb = baseHibernateService.findEntityById(
						ProductGroup.class, productGroup.getId());
				productGroup.setItems(pb.getItems());
				productGroup.setEmployees(pb.getEmployees());
				productGroup.setRegionals(pb.getRegionals());
			}
			
			String[] attrArray ={"salesOrg"};
			checkEntityNullValue(productGroup,attrArray);
			
			productGroup.setIsTemp("0");
			productGroup = baseHibernateService.merge(productGroup);
			renderText(UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(UPDATE_SUCCESS);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			ProductGroup pb = baseHibernateService.findEntityById(
					ProductGroup.class, id);
			if (null != pb) {
				pb.getItems().clear();
				pb.getEmployees().clear();
				pb.getRegionals().clear();
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr)
							&& !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				baseHibernateService.batchDelete(ProductGroup.class, delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getEmployeeJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ProductGroup pg = baseHibernateService.findEntityById(
						ProductGroup.class, id);
				json = convertListToJson(
						new ArrayList<Employee>(pg.getEmployees()), pg
								.getEmployees().size(), "productGroup");
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

	public String addEmployeeById() {
		try {
			ProductGroup pg = baseHibernateService.findEntityById(
					ProductGroup.class, id);
			if (null != pg) {
				String empIds = getRequestParameter("empIds");
				if (null != empIds && !"".equals(empIds)) {
					String[] ids = empIds.split(",");
					for (String empId : ids) {
						if (null == empId || "".equals(empId)) {
							continue;
						}
						Employee emp = baseHibernateService.findEntityById(
								Employee.class, empId);
						if (null != emp && !pg.getEmployees().contains(emp)) {
							pg.getEmployees().add(emp);
							baseHibernateService.merge(pg);
							setMessage("添加成功!");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}

	public String deleteEmployeeById() {
		try {
			ProductGroup pg = baseHibernateService.findEntityById(
					ProductGroup.class, id);
			if (null != pg) {
				String empId = getRequestParameter("empId");
				if (null != empId && !"".equals(empId)) {
					for (Employee emp : pg.getEmployees()) {
						if (emp.getId() == empId) {
							pg.getEmployees().remove(emp);
							baseHibernateService.merge(pg);
							renderText(DELETE_SUCCESS);
							return UPDATE;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ProductGroup pg = baseHibernateService.findEntityById(
						ProductGroup.class, id);
				json = convertListToJson(new ArrayList<Item>(pg.getItems()), pg
						.getItems().size(), "productGroup");
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

	public String addItemById() {
		try {
			ProductGroup pg = baseHibernateService.findEntityById(
					ProductGroup.class, id);
			if (null != pg) {
				String itemId = getRequestParameter("itemId");
				if (null != itemId && !"".equals(itemId)) {
					Item item = baseHibernateService.findEntityById(Item.class,
							itemId);
					if (null != item) {
						pg.getItems().add(item);
						baseHibernateService.merge(pg);
						setMessage("添加成功!");
						return UPDATE;
					}
				}
			}
			setMessage(getText("sale_itemNotExist"));
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}

	public String deleteItemById() {
		try {
			ProductGroup pg = baseHibernateService.findEntityById(
					ProductGroup.class, id);
			if (null != pg) {
				String itemId = getRequestParameter("itemId");
				if (null != itemId && !"".equals(itemId)) {
					for (Item item : pg.getItems()) {
						if (item.getId() == itemId) {
							pg.getItems().remove(item);
							baseHibernateService.merge(pg);
							renderText(DELETE_SUCCESS);
							return UPDATE;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getRegionalJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ProductGroup pg = baseHibernateService.findEntityById(
						ProductGroup.class, id);
				json = convertListToJson(
						new ArrayList<Regional>(pg.getRegionals()), pg
								.getRegionals().size(), "productGroup");
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

	public String addRegionalById() {
		try {
			ProductGroup pg = baseHibernateService.findEntityById(
					ProductGroup.class, id);
			if (null != pg) {
				String regionalIds = getRequestParameter("regionalIds");
				if (null != regionalIds && !"".equals(regionalIds)) {
					String[] ids = regionalIds.split(",");
					for (String regionalId : ids) {
						if (null == regionalId || "".equals(regionalId)) {
							continue;
						}
						Regional regional = baseHibernateService.findEntityById(
								Regional.class, regionalId);
						if (null != regional
								&& !pg.getRegionals().contains(regional)) {
							pg.getRegionals().add(regional);
							baseHibernateService.merge(pg);
							setMessage("添加成功!");
						}
					}
				}
				if (null == getMessage()) {
					setMessage(getText("sale_regionalNotExist"));
				}
			} else {
				setMessage("产品组未获取到!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}

	public String deleteRegionalById() {
		try {
			ProductGroup pg = baseHibernateService.findEntityById(
					ProductGroup.class, id);
			if (null != pg) {
				String regionalId = getRequestParameter("regionalId");
				if (null != regionalId && !"".equals(regionalId)) {
					for (Regional regional : pg.getRegionals()) {
						if (regional.getId() == regionalId) {
							pg.getRegionals().remove(regional);
							baseHibernateService.merge(pg);
							renderText(DELETE_SUCCESS);
							return UPDATE;
						}
					}
				}
			}
			setMessage(getText("sale_regionalNotExist"));
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public ProductGroup getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}
}
