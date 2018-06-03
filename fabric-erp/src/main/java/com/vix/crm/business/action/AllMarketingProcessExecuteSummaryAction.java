package com.vix.crm.business.action;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.CouponSendLog;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.wsx.excel.loader.LoadManager;
import com.wsx.excel.service.ExcelService;
import com.wsx.excel.service.ExcelServiceImpl;

@Controller
@Scope("prototype")
public class AllMarketingProcessExecuteSummaryAction extends BaseAction implements LoadManager {
	private static final long serialVersionUID = 1L;

	private String pageNo;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, MembershipSubdivision.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	@SuppressWarnings("unchecked")
	public void getLogJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();

			String sort = ServletActionContext.getRequest().getParameter("sort");
			if (sort != null) {
				pager.setOrderField(sort);
			}
			String order = ServletActionContext.getRequest().getParameter("order");
			if (order != null) {
				pager.setOrderBy(order);
			}
			Integer page = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
			Integer pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
			if (null != page && !"".equals(page)) {
				pager.setPageNo(page);
			}
			if (null != pageSize && !"".equals(pageSize)) {
				pager.setPageSize(pageSize);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, CouponSendLog.class, params);
			setPager(pager);

			renderText(convertListToJson(pager.getResultList(), pager.getTotalCount()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String exportTakeStockExcel() throws Exception {
		try {
			String xlsPath = "resource/export/process_summary_template.xls";
			String xmlPath = "resource/export/process_summary_template.xml";
			File xlsFile = new File(Thread.currentThread().getContextClassLoader().getResource(xlsPath).toURI());
			File xmlFile = new File(Thread.currentThread().getContextClassLoader().getResource(xmlPath).toURI());
			ExcelService excelService = new ExcelServiceImpl(this);
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "流程汇总单.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			excelService.exportExcel(xmlFile, xlsFile, excelResponse.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List loadData(String arg0, String[] arg1) {
		List<Map<String, Object>> resList = new LinkedList<Map<String, Object>>();
		try {
			Map<String, Object> params = getParams();
			List<CouponSendLog> couponSendLogList = baseHibernateService.findAllByConditions(CouponSendLog.class, params);
			for (CouponSendLog couponSendLog : couponSendLogList) {
				if (couponSendLog != null) {
					Map<String, Object> reMap = new HashMap<String, Object>();
					reMap.put("name", couponSendLog.getName());
					reMap.put("createTime", couponSendLog.getCreateTime());
					resList.add(reMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
