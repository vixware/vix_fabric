package com.vix.eam.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.eam.common.action.EamBaseAction;
import com.vix.eam.entity.EqCategory;

@Controller
@Scope("prototype")
public class PreMaintainAction extends EamBaseAction {
	private static final long serialVersionUID = 1L;

	/* 基础数据管理 */
	public String jcBaseDataMgr() {
		return "jcBaseDataMgr";
	}

	public String jcBaseDataMgrList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentCategory.id," + SearchCondition.EQUAL,
						parentId);
			}
			Pager pager = this.baseHibernateService.findPagerByHqlConditions(
					getPager(), EqCategory.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jcBaseDataMgrList";
	}

	public String jcBaseDataMgrDetail() {
		return "jcBaseDataMgrDetail";
	}

	/* 维护任务管理 */
	public String whWorkMgr() {
		return "whWorkMgr";
	}

	public String whWorkMgrList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentCategory.id," + SearchCondition.EQUAL,
						parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), EqCategory.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "whWorkMgrList";
	}

	public String whWorkMgrDetail() {
		return "whWorkMgrDetail";
	}

	/* 任务工单生成 */
	public String gdGenWorkOrder() {
		return "gdGenWorkOrder";
	}

	public String gdGenWorkOrderList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentCategory.id," + SearchCondition.EQUAL,
						parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), EqCategory.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gdGenWorkOrderList";
	}

	public String gdGenWorkOrderDetail() {
		return "gdGenWorkOrderDetail";
	}

	/* 大修项目管理 */
	public String dxProjectMgr() {
		return "dxProjectMgr";
	}

	/* 计划监控及调整 */
	public String jkInfoMgr() {
		return "jkInfoMgr";
	}

	public String jkInfoMgrList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentCategory.id," + SearchCondition.EQUAL,
						parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), EqCategory.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jkInfoMgrList";
	}

	public String jkInfoMgrDetail() {
		return "jkInfoMgrDetail";
	}

	/* 维护成本估算 */
	public String cbCostMgr() {
		return "cbCostMgr";
	}

	public String cbCostMgrList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("parentCategory.id," + SearchCondition.EQUAL,
						parentId);
			}
			Pager pager = this.baseHibernateService.findPagerByHqlConditions(
					getPager(), EqCategory.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "cbCostMgrList";
	}

	public String cbCostMgrDetail() {
		return "cbCostMgrDetail";
	}

}
