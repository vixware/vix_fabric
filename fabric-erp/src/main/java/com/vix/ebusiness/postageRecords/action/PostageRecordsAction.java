package com.vix.ebusiness.postageRecords.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.ebusiness.option.entity.Logistics;
import com.vix.ebusiness.postageRecords.entity.PostageRecords;

@Controller
@Scope("prototype")
public class PostageRecordsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String parentId;

	/**
	 * 费用记录
	 */
	private PostageRecords postageRecords;
	private List<PostageRecords> postageRecordsList;

	@Override
	public String goList() {
		try {
			postageRecordsList = baseHibernateService.findAllByEntityClass(PostageRecords.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(parentId) && !"0".equals(parentId)) {
				params.put("logistics.id," + SearchCondition.EQUAL, parentId);
			}

			//处理搜索条件
			String searchContent = getRequestParameter("searchContent");
			String outerId = getRequestParameter("outerId");
			String shippingNo = getRequestParameter("shippingNo");
			String logisticsName = getDecodeRequestParameter("logisticsName");
			if (searchContent != null && !"".equals(searchContent)) {
				params.put("order.outerId," + SearchCondition.EQUAL, searchContent);
			}
			if (outerId != null && !"".equals(outerId)) {
				params.put("order.outerId," + SearchCondition.EQUAL, outerId);
			}
			if (shippingNo != null && !"".equals(shippingNo)) {
				params.put("order.shippingNo," + SearchCondition.EQUAL, shippingNo);
			}
			if (logisticsName != null && !"".equals(logisticsName)) {
				params.put("logistics.name," + SearchCondition.ANYLIKE, logisticsName);
			}
			//处理搜索条件
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, PostageRecords.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 获取物流树
	 */
	public void findLogisticsTreeToJson() {
		try {
			List<Logistics> logisticsList = new ArrayList<Logistics>();
			logisticsList = baseHibernateService.findAllByEntityClass(Logistics.class);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			int count = logisticsList.size();
			for (int i = 0; i < count; i++) {
				Logistics org = logisticsList.get(i);
				strSb.append("{id:\"");
				strSb.append(org.getId());
				strSb.append("\",treeType:\"L\"");
				strSb.append(",name:\"");
				strSb.append(org.getName());
				strSb.append("\",open:false,isParent:false}");
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public PostageRecords getPostageRecords() {
		return postageRecords;
	}

	public void setPostageRecords(PostageRecords postageRecords) {
		this.postageRecords = postageRecords;
	}

	public List<PostageRecords> getPostageRecordsList() {
		return postageRecordsList;
	}

	public void setPostageRecordsList(List<PostageRecords> postageRecordsList) {
		this.postageRecordsList = postageRecordsList;
	}

}
