package com.vix.ebusiness.order.orderDownLoadLog.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.system.latestOperate.entity.LatestOperateEntity;

@Controller
@Scope("prototype")
public class OrderDownLoadLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String pageNo;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager.setPageSize(100);
			pager.setOrderBy("DESC");
			pager.setOrderField("operateTime");
			params.put("url", "OrderProcessAction");

			//处理搜索条件
			String searchContent = getDecodeRequestParameter("searchContent");
			if (searchContent != null && !"".equals(searchContent)) {
				params.put("operate," + SearchCondition.ANYLIKE, searchContent);
			}
			//处理搜索条件
			pager = baseHibernateService.findPagerByHqlConditions(pager, LatestOperateEntity.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
}
