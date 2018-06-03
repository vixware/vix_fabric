package com.vix.oa.news.action;

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
import com.vix.core.web.Pager;
import com.vix.oa.share.controller.NewsManagementController;
import com.vix.oa.share.entity.Comments;
import com.vix.oa.share.entity.Trends;

/**
 * 
 * @ClassName: NewsManagementAction
 * @Description: 个人办公——新闻管理
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-3-22 下午2:52:56
 */
@Controller
@Scope("prototype")
public class NewsManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(NewsManagementController.class);

	@Autowired
	private NewsManagementController newsManagementController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	private Trends trends;

	private Comments comments;

	private String id;

	private String pageNo;

	private Date updateTime;

	/** 新闻管理 */
	private List<Trends> trendsList;
	/** 新闻管理 */
	private List<Comments> commentsList;

	private Trends entity;

	private String parentId;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			trendsList = newsManagementController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isPublish," + SearchCondition.EQUAL, 0);
			// 状态
			String newsType = getRequestParameter("newsType");
			if (null != newsType && !"".equals(newsType)) {
				params.put("newsType," + SearchCondition.EQUAL, Integer.parseInt(newsType));
			}
			// 按最近使用
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
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
			Pager pager = newsManagementController.doSubSingleList(params, getPager());
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
				pager = newsManagementController.goSearchList(params, getPager());
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
				pager = newsManagementController.goSingleList(params, getPager());
			}
			logger.info("获取新闻搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				comments = newsManagementController.doListEntityById1(id);
				this.saveBaseEntity(this.trends);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(comments.getId()) && !"".equals(comments.getId())) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(comments);
			comments = newsManagementController.doSaveSalesOrder1(comments);

			this.comments.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.comments.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			comments.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			comments.setCreateTime(new Date());
			this.saveBaseEntity(this.comments);
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

	/**
	 * 获取到新闻内容，获取内容时将新闻的阅读次数加一，保存阅读次数
	 * 
	 * @author chenzhengwen
	 * @date 2014-7-18上午10:38:12
	 * @return
	 */
	public String goSeenoticenotice() {
		try {
			trends = baseHibernateService.findEntityById(Trends.class, id);
			System.out.println(trends.getReadTimes() + "=====");
			if (trends.getReadTimes() == 0) {
				trends.setReadTimes(1l);
			} else {
				trends.setReadTimes(trends.getReadTimes() + 1l);
			}
			trends = baseHibernateService.merge(trends);
			logger.info("获取评论人员列表数据");
			// setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSeenoticenotice";
	}

	/** 处理新闻查看操作 */
	public String popNews() {
		return "popNews";
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Trends pb = newsManagementController.findEntityById(id);
			if (null != pb) {
				newsManagementController.doDeleteByEntity(pb);
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

	public String getId() {
		return id;
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

	public Trends getEntity() {
		return entity;
	}

	public void setEntity(Trends entity) {
		this.entity = entity;
	}

	public Trends getTrends() {
		return trends;
	}

	public void setTrends(Trends trends) {
		this.trends = trends;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public List<Comments> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<Comments> commentsList) {
		this.commentsList = commentsList;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
