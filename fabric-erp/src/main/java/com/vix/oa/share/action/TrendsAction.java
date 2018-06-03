package com.vix.oa.share.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.share.controller.TrendsController;
import com.vix.oa.share.entity.Trends;

/**
 * 
 * @ClassName: TrendsAction
 * @Description: 行政办公——新闻管理
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-3-20 下午6:10:26
 */
@Controller
@Scope("prototype")
public class TrendsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(TrendsController.class);
	@Autowired
	private TrendsController trendsController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private Trends trends;
	private String id;
	public Integer isPublish;
	public Integer isTop;
	private String pageNo;
	private Date updateTime;
	/** 新闻管理 */
	private List<Trends> trendsList;
	private Trends entity;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			trendsList = trendsController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取新闻列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 状态
			String isPublish = getRequestParameter("isPublish");
			if (null != isPublish && !"".equals(isPublish)) {
				params.put("isPublish," + SearchCondition.EQUAL, Integer.parseInt(isPublish));
			}
			// 按最近使用
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
			// uploadPersonId包含当前登录人id
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			/** 根据isTop和时间进行倒序排序，isTop优先 */
			String orderField = getPager().getOrderField();
			if (null != orderField && !"".equals(orderField)) {
				orderField = "isTop desc," + orderField;
				getPager().setOrderField(orderField);
			} else {
				orderField = "isTop desc,createTime ";
				getPager().setOrderField(orderField);
				getPager().setOrderBy("desc");
			}
			Pager pager = trendsController.doSubSingleList(params, getPager());
			logger.info("获取新闻列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取新闻搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 标题
			String title = getRequestParameter("title");
			if (null != title && !"".equals(title)) {
				title = URLDecoder.decode(title, "utf-8");
			}
			// 副标题
			String subtitle = getRequestParameter("subtitle");
			if (null != subtitle && !"".equals(subtitle)) {
				subtitle = URLDecoder.decode(subtitle, "utf-8");
			}
			// 关键字
			String keywords = getRequestParameter("keywords");
			if (null != keywords && !"".equals(keywords)) {
				keywords = URLDecoder.decode(keywords, "utf-8");
			}
			// 发布人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			// 发布范围
			String bizOrgNames = getRequestParameter("bizOrgNames");
			if (null != bizOrgNames && !"".equals(bizOrgNames)) {
				bizOrgNames = URLDecoder.decode(bizOrgNames, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
				pager = trendsController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				if (null != title && !"".equals(title)) {
					params.put("title," + SearchCondition.ANYLIKE, title);
				}
				if (null != keywords && !"".equals(keywords)) {
					params.put("keywords," + SearchCondition.ANYLIKE, keywords);
				}
				if (null != subtitle && !"".equals(subtitle)) {
					params.put("subtitle," + SearchCondition.ANYLIKE, subtitle);
				}
				if (null != bizOrgNames && !"".equals(bizOrgNames)) {
					params.put("bizOrgNames," + SearchCondition.ANYLIKE, bizOrgNames);
				}
				pager = trendsController.goSingleList(params, getPager());
			}
			logger.info("获取新闻搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至新闻修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				trends = trendsController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理新闻新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trends.getId()) && !"".equals(trends.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = trends.getTitle();
			String py = ChnToPinYin.getPYString(title);
			trends.setChineseFirstLetter(py.toUpperCase());
			initEntityBaseController.initEntityBaseAttribute(trends);
			trends = trendsController.doSaveSalesOrder(trends);
			this.trends.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.trends.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			trends.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.trends);
			logger.info("新增！");
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
		}
		return UPDATE;
	}

	/** 处理新闻删除操作 */
	public String deleteById() {
		try {
			Trends pb = trendsController.findEntityById(id);
			if (null != pb) {
				trendsController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 对新闻状态进行操作，根据业务需求选择是发布或终止
	 * 
	 * @return
	 */
	public void updateIsPublish() {
		try {
			Trends pb = trendsController.findEntityById(id);
			if (pb.getIsPublish() == 1) {
				isPublish = 0;
			} else if (pb.getIsPublish() == 0) {
				isPublish = 1;
			}
			pb.setIsPublish(isPublish);
			trends = trendsController.doSaveSalesOrder(pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对新闻状态进行操作，根据业务需求选择是置顶或不置顶
	 * 
	 * @return
	 */
	public void updateIsTopTrends() {
		try {
			Trends pb = trendsController.findEntityById(id);
			if (pb.getIsTop() == 1) {
				isTop = 0;
			} else if (pb.getIsTop() == 0) {
				isTop = 1;
			}
			pb.setIsTop(isTop);
			trends = trendsController.doSaveSalesOrder(pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public List<Trends> getTrendsList() {
		return trendsList;
	}

	public void setTrendsList(List<Trends> trendsList) {
		this.trendsList = trendsList;
	}

	public Trends getTrends() {
		return trends;
	}

	public void setTrends(Trends trends) {
		this.trends = trends;
	}

	public Trends getEntity() {
		return entity;
	}

	public void setEntity(Trends entity) {
		this.entity = entity;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

}
