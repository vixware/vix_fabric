package com.vix.common.base.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.vix.common.common.bo.MessageObject;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.constant.SearchCondition;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.utils.ContextUtil;
import com.vix.core.utils.JSONFlexUtils;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * Struts2基础类.
 */
public abstract class BaseSecAction extends ActionSupport {

	protected static Logger logger = LoggerFactory.getLogger(BaseSecAction.class);

	private static final long serialVersionUID = 1L;

	protected static final long time_millisec_one_day = 86400000L;
	@Autowired
	public IBaseHibernateService baseHibernateService;
	/** 返回值标识 */
	public static final String GO_LIST = "goList";
	/** 返回值标识 */
	public static final String GO_VIEW = "goView";
	public static final String GO_ERROR_BIZ = "errorBiz";

	/** 返回值标识 */
	public static final String GO_LIST_PROJECT = "goListProject";
	/** 返回值标识 */
	public static final String GO_LIST_TYPE = "goListType";
	/** 列表页 */
	public static final String GO_SINGLE_LIST = "goSingleList";
	/** 列表页 */
	/** 列表页 */
	public static final String GO_SUB_SINGLE_LIST = "goSubSingleList";
	/** 列表数据页 */
	public static final String GO_SAVE_OR_UPDATE = "goSaveOrUpdate";
	/** 列表数据页 */
	public static final String GO_PURCHASE_AGREEMENT = "goPurchaseAgreement";
	/** 列表数据页 */
	public static final String GO_SAVE_OR_UPDATE_APPLY = "goSaveOrUpdateApply";
	/** 列表数据页 */
	public static final String GO_SALES_AGREEMENT = "goSalesAgreement";
	/** 列表数据页 */
	public static final String GO_PM_CONTRACT = "goPmContract";
	/** 添加或修改 */
	public static final String UPDATE = "update";

	protected static final String SAVE_SUCCESS = "保存成功!";
	protected static final String SAVE_FAIL = "保存失败!";
	protected static final String UPDATE_SUCCESS = "修改成功!";
	protected static final String UPDATE_FAIL = "修改失败!";
	protected static final String DELETE_SUCCESS = "删除成功!";
	protected static final String DELETE_FAIL = "删除失败!";
	protected static final String OPER_SUCCESS = "操作成功!";
	protected static final String OPER_FAIL = "操作失败!";

	/** header 常量定义 */
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;

	/** content-type 常量定义 */
	private static final String TEXT_TYPE = "text/plain";
	private static final String JSON_TYPE = "application/json";
	private static final String XML_TYPE = "text/xml";
	private static final String HTML_TYPE = "text/html";
	private static final String JS_TYPE = "text/javascript";

	protected String fromKey = "";

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static ObjectMapper mapper = new ObjectMapper();
	/** 分页对象 */
	private Pager pager;
	/** 查询参数 */
	private Map<String, Object> params;

	/** 语言 */
	private String language;

	private String message;
	/**
	 * 处理修改留痕字段
	 */
	protected String updateField;
	// 文件上传使用
	/** 附件 */
	protected File fileToUpload;
	/** 附件的名称 */
	protected String fileToUploadFileName;

	// 文件下载使用
	String oriFileName;
	private InputStream inputStream;

	/** 选择类型 radio checkbox */
	protected String chkStyle;

	protected String selectId;

	protected String advFilterStr;
	protected int gridPageSize;

	Properties pathConfig;

	public BaseSecAction() {
		configPager();
		configParams();
	}

	private String error;

	public String fDouble(Double num) {
		return String.format("%.2f", num);
	}

	public double showNum(Double num) {
		return this.showNum(num, 2);
	}

	public double showNum(Double num, int scale) {
		if (num == null)
			return 0;
		BigDecimal value = new BigDecimal(num);
		value.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return value.doubleValue();
	}

	public String currentUserName() {
		return SecurityUtil.getCurrentUserName();
	}

	/**
	 * 输入信息到日志文件中,可以考虑是用Helper类提供服务.
	 */
	public void executeLogger(org.slf4j.Logger logger, String info) {
		logger.info(info);
	}

	public <T> String convertListToJsonInclude(List<T> dataList, String... inludeExpression) {
		if (dataList == null)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(dataList.size()).append(",");
		sb.append("\"rows\":");
		// sb.append(new
		// JSONSerializer().exclude("class").exclude(exludeExpression).serialize(dataList));
		sb.append(new JSONSerializer().transform(new DateTransformer("yyyy-MM-dd"), Date.class).include(inludeExpression).serialize(dataList));

		sb.append("}");
		return sb.toString();
	}

	public <T> String convertListToJson(List<T> dataList, String... exludeExpression) {
		if (dataList == null)
			return "";
		return convertListToJson(dataList, dataList.size(), exludeExpression);
	}

	public <T> String convertListToJson(List<T> dataList, long total, String... exludeExpression) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(total).append(",");
		sb.append("\"rows\":");
		// sb.append(new
		// JSONSerializer().exclude("class").exclude(exludeExpression).serialize(dataList));
		sb.append(new JSONSerializer().transform(new DateTransformer("yyyy-MM-dd"), Date.class).exclude("class").exclude("*.class").exclude("*.handler").exclude("*.hibernateLazyInitializer").exclude(exludeExpression).serialize(dataList));

		sb.append("}");
		return sb.toString();
	}

	/**
	 * @Title: convertListToJson @Description:
	 *         把数据List封装成jqueryUI的dataGrid需要的Json对象 @param @param dataList
	 *         数据列表 @param @param total 总数量 @param @return 设定文件 @return String
	 *         返回类型 @throws
	 */
	public <T> String convertListToJson(List<T> dataList, List<T> list, long total, String... exludeExpression) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(total).append(",");
		sb.append("\"rows\":");
		// sb.append(new
		// JSONSerializer().exclude("class").exclude(exludeExpression).serialize(dataList));
		sb.append(new JSONSerializer().exclude("class").exclude("*.class").exclude("*.handler").exclude("*.hibernateLazyInitializer").exclude(exludeExpression).serialize(dataList));

		sb.append(",\"footer\":");
		// sb.append(new
		// JSONSerializer().exclude("class").exclude(exludeExpression).serialize(list));
		sb.append(new JSONSerializer().exclude("class").exclude("*.class").exclude("*.handler").exclude("*.hibernateLazyInitializer").exclude(exludeExpression).serialize(list));

		sb.append("}");
		return sb.toString();
	}

	public String goList() {
		return GO_LIST;
	}

	// -- 取得Request/Response/Session的简化函数 --//
	/**
	 * 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession() {
		// return ServletActionContext.getRequest().getSession();
		return getRequest().getSession();
	}

	/**
	 * 取得HttpRequest的简化函数.
	 */
	public static HttpServletRequest getRequest() {
		// return ServletActionContext.getRequest();
		return ContextUtil.getRequest();
	}

	/**
	 * 取得HttpResponse的简化函数.
	 */
	public static HttpServletResponse getResponse() {
		// return ServletActionContext.getResponse();
		return ContextUtil.getResponse4Struts();
	}

	/**
	 * 取得Request Parameter的简化方法.
	 */
	public static String getRequestParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 获取web容器全局范围
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {
		// return ServletActionContext.getServletContext();
		return ContextUtil.getServletContext();
	}

	/**
	 * 获得上下文绝对路径
	 * 
	 * @return
	 */
	public static String getContextRealPath() {
		// return ServletActionContext.getServletContext().getRealPath("/");
		return ContextUtil.getAppRealPath("/");
	}

	// -- 绕过jsp/freemaker直接输出文本的函数 --//
	/**
	 * 直接输出内容的简便函数. eg. render("text/plain", "hello", "encoding:GBK");
	 * render("text/plain", "hello", "no-cache:false"); render("text/plain",
	 * "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(final String contentType, final String content, final String... headers) {
		HttpServletResponse response = initResponse(contentType, headers);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text, final String... headers) {
		render(TEXT_TYPE, text, headers);
	}

	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html, final String... headers) {
		render(HTML_TYPE, html, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml, final String... headers) {
		render(XML_TYPE, xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String jsonString, final String... headers) {
		render(JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName
	 *            callback函数名.
	 * @param object
	 *            Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonp(final String callbackName, final Object object, final String... headers) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		String result = new StringBuilder().append(callbackName).append("@").append(jsonString).toString();

		// 渲染Content-Type为javascript的返回内容,输出结果为javascript语句,
		// 如callback197,{html:'Hello World!!!'};
		render(JS_TYPE, result, headers);
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static HttpServletResponse initResponse(final String contentType, final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");

			if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
			}
		}

		HttpServletResponse response = ServletActionContext.getResponse();

		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			setNoCacheHeader(response);
		}
		return response;
	}

	public <T> String convertListToJsonNoPage(List<T> dataList, long total, String... exludeExpression) {
		StringBuilder sb = new StringBuilder();
		// sb.append("{");

		sb.append(new JSONSerializer().exclude("*.class").exclude("*.handler").exclude("*.hibernateLazyInitializer").exclude(exludeExpression).serialize(dataList));

		// sb.append("}");

		return sb.toString();
	}

	public <T> String convertListToJsonNoPage(boolean isCopy, List<T> dataList, long total, String... exludeExpression) {
		StringBuilder sb = new StringBuilder();
		// sb.append("{");

		if (!isCopy) {
			sb.append(new JSONSerializer().exclude("class").exclude("*.class").exclude("*.handler").exclude("*.hibernateLazyInitializer").exclude(exludeExpression).serialize(dataList));
		} else {
			List<T> dataList2 = new LinkedList<T>();
			dataList2.addAll(dataList);

			sb.append(new JSONSerializer().exclude("class").exclude("*.class").exclude("*.handler").exclude("*.hibernateLazyInitializer").exclude(exludeExpression).serialize(dataList2));
		}

		// sb.append("}");

		return sb.toString();
	}

	/**
	 * 设置客户端缓存过期时间 Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		// Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
		// Http 1.1 header
		response.setHeader("Cache-Control", "max-age=" + expiresSeconds);
	}

	/**
	 * 设置客户端无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 0);
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
	}

	/** 获取查询及刷新参数 */
	private Map<String, Object> configParams() {
		if (null == params) {
			params = new HashMap<String, Object>();
		}
		String name = getRequest().getParameter("name");
		try {
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// loadCompanyCode();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return params;
	}

	/** 载入公司编码查询条件 */
	protected void loadCompanyCode() {
		String companyCode = "NoPermission";
		Object objCc = getSession().getAttribute("companyCode");
		Object objTi = getSession().getAttribute("tenantId");
		if (null != objCc && !"".equals(objCc.toString())) {
			String[] ccArray = objCc.toString().split("#");
			companyCode = ccArray[0];
		}
		params.put("companyCode," + SearchCondition.ENDLIKE, companyCode);
		params.put("tenantId," + SearchCondition.EQUAL, objTi);
	}

	/**
	 * 是否允许提交审批，如果业务单据绑定了流程则返回1允许提交审批 public String isAllowAudit(String
	 * billType) throws Exception { BillsProperty bp =
	 * basicService.findEntityByAttribute(BillsProperty.class, "propertyCode",
	 * billType); if(null != bp){ String processDefinitionId =
	 * standardActivitiService.findProcessDefinitionIdByCode(bp.getId()+"X");
	 * if(null != processDefinitionId && !"".equals(processDefinitionId)){
	 * return "1"; } } return "0"; }
	 */
	/** 解码 */
	public String decode(String str, String enc) throws Exception {
		str = URLDecoder.decode(str, enc);
		return str;
	}

	/** 配置分页数据 */
	protected void configPager() {
		String pageNo = getRequestParameter("page");
		String pageSize = getRequestParameter("pageSize");
		String orderField = getRequestParameter("orderField");
		String orderBy = getRequestParameter("orderBy");
		if (null != pageNo && !"".equals(pageNo)) {
			getPager().setPageNo(Integer.valueOf(pageNo));
		} else {
			getPager().setPageNo(1);
		}
		if (null != pageSize && !"".equals(pageSize)) {
			getPager().setPageSize(Integer.valueOf(pageSize));
		}
		if (null != orderField && !"".equals(orderField)) {
			getPager().setOrderField(orderField);
			if (null != orderBy && !"".equals(orderBy)) {
				getPager().setOrderBy(orderBy);
			} else {
				getPager().setOrderBy("asc");
			}
		}
	}

	/**
	 * 基础的保存对象方法，不做任何处理
	 * 
	 * @param baseEntity
	 *            不向request返回任何信息
	 */
	protected void saveBaseEntity(BaseEntity baseEntity) {
		if (baseEntity == null)
			return;

		try {
			if (StringUtils.isNotEmpty(baseEntity.getId())) {// &&
																// baseEntity.getId()
																// > 0
				baseHibernateService.update(baseEntity);
			} else {
				baseHibernateService.save(baseEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将上传文件保存到指定路径
	 */
	public String[] saveUploadFile() {
		return saveUploadFile(null);
	}

	@SuppressWarnings("resource")
	public String[] saveUploadFile(String uploadMark) {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSaveFolder(uploadMark);
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
				String[] fileNames = fileToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = fileToUploadFileName.substring(0, fileToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;

				String saveFilePath = saveFolder + saveFileName;

				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(saveFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();

				pathAndName = new String[2];
				pathAndName[0] = saveFolder;
				pathAndName[1] = saveFileName;

			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	/**
	 * 后面需要改成读库
	 * 
	 * @return
	 */
	protected String getUploadFileSaveFolder(String uploadMark) {
		String separator = System.getProperty("file.separator");

		String baseFolder = "d:/vix_file";
		if (uploadMark != null) {
			String propKey = null;
			if ("repair".equals(uploadMark))
				propKey = "mmx_repair_doc_path";
			else if ("supplier".equals(uploadMark))
				propKey = "supplier_attach_path";
			else if ("purchase".equals(uploadMark))
				propKey = "purchase_attach_path";

			if (pathConfig == null && propKey != null) {
				pathConfig = new Properties();
				ClassLoader clsLoader = BaseSecAction.class.getClassLoader();
				InputStream in = clsLoader.getResourceAsStream("path_config.properties");
				try {
					pathConfig.load(in);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (pathConfig != null)
					baseFolder = pathConfig.getProperty(propKey);
			} else {
				baseFolder = baseFolder + File.separator + uploadMark;
			}
		}

		String newFilePath = "";
		String tenantId = SecurityUtil.getCurrentUserTenantId();
		if (StrUtils.isEmpty(tenantId))
			tenantId = "no_tenantId";

		newFilePath = baseFolder + separator + tenantId + separator;

		File dir = new File(newFilePath);
		if (!dir.exists())
			dir.mkdirs();

		return newFilePath;
	}

	/** 上传文件 */
	public void uploadAttachment() {
		try {
			if (null != fileToUpload) {
				String separator = System.getProperty("file.separator");
				/** 上传目录 */
				String baseDir = getServletContext().getRealPath(separator + "uploadFiles" + separator + "article");
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
				String[] fileNames = fileToUploadFileName.split("\\.");
				String fileName = fileNames[0];
				String extFileName = fileNames[fileNames.length - 1];
				if (fileNames.length > 2) {
					for (int i = 1; i < fileNames.length - 1; i++) {
						fileName += "." + fileNames[i];
					}
				}
				String newFilePath = "";
				long newFileName = System.currentTimeMillis();
				newFilePath = baseDir + separator + fileName + "_" + newFileName + "." + extFileName;
				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(newFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();
				bufIn.close();
				renderJson(fileToUploadFileName);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("文件上传失败!");
		}

	}

	/** 获取资源存储目录 */
	public String getBasePath(String resPath) throws Exception {
		// String path =
		// ServletActionContext.getServletContext().getRealPath(resPath);
		String path = ContextUtil.getAppRealPath(resPath);
		/** 创建目录 */
		File dirVal = new File(path);
		if (!dirVal.exists()) {
			dirVal.mkdir();
		}
		return path;
	}

	/** 保存文件 */
	public void saveFile(String content, String filePath) throws Exception {
		FileOutputStream fos = new FileOutputStream(filePath);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "GB2312");
		osw.write(content);
		osw.flush();
		osw.close();
	}

	/** 删除文件 */
	public void deleteFile(String filePath) throws Exception {
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}
	}

	public String formatDateToString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (null == date) {
			return "";
		}
		return df.format(date);
	}

	public Date formatStringToDate(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (null == date) {
			return null;
		}
		return df.parse(date);
	}

	public String formatDateToTimeString(Date s) {
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null == s)
			return "";
		return time.format(s);
	}

	public Date formatTimeStringToDate(String s) throws ParseException {
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null == s)
			return null;
		return time.parse(s);
	}

	public void addTimeLimitToParam(Map<String, Object> params) {
		Date dateNow = new Date();
		params.put("endTime," + SearchCondition.MORETHAN, dateNow);
		params.put("startTime," + SearchCondition.LESSTHAN, dateNow);
	}

	/**
	 * 解析页面传递参数advFilterStr，生成相应的检索、排序
	 * advFilterStr的格式：column_order_filter_datetype_filtertype column：字段名称
	 * order：排序asc、desc filter：过滤条件
	 * dateType：字段类型，默认是string，使用like检索。可选值:string/integer/date
	 * filtertype：过滤方式，默认按string使用like检索，可选值:like/eq/le/ge/gt/lt/in/ne/is/bt
	 * bt=wetween,此时过去条件格式为abc!def
	 * 
	 * @param params
	 * @param pager
	 */
	public void addAdvFilterAndSort(Map<String, Object> params, Pager pager) {
		if (StrUtils.isNotEmpty(this.advFilterStr) && this.advFilterStr.indexOf("_") != -1) {
			String[] columnStrArr = this.advFilterStr.split(",");
			List<String[]> sortList = new ArrayList<String[]>();
			for (String columnStr : columnStrArr) {
				columnStr = StringUtils.replace(columnStr, "[douhao]", ",");
				// 会忽略空参数
				List<String> advFilterList = StrUtils.splitStr(columnStr, "_");
				if (advFilterList == null || advFilterList.size() == 1)
					continue;

				String columnName = advFilterList.get(0);
				if (StrUtils.isNotEmpty(columnName))
					columnName = StringUtils.replace(columnName, "[xiahuaxian]", "_");

				String filterStr = advFilterList.get(2);
				if (StrUtils.isNotEmpty(filterStr))
					filterStr = StringUtils.replace(filterStr, "[xiahuaxian]", "_");

				String dataType = "string";
				String filterType = null;

				if (advFilterList.size() > 3) {
					if (advFilterList.size() >= 4 && StrUtils.isNotEmpty(advFilterList.get(3))) {
						// 数据类型
						dataType = StringUtils.replace(advFilterList.get(3), "[xiahuaxian]", "_");
					}
					if (advFilterList.size() >= 5 && StrUtils.isNotEmpty(advFilterList.get(4))) {
						// 查询条件
						filterType = StringUtils.replace(advFilterList.get(4), "[xiahuaxian]", "_");
					}
				}

				String searchCondition = this.fixSearchCondition(filterType);
				Object searchValue = this.fixSearchValue(filterStr, dataType);

				if (StrUtils.isNotEmpty(searchCondition) && searchValue != null) {
					params.put(columnName + "," + searchCondition, searchValue);
				} else if (SearchCondition.IS.equals(searchCondition)) {
					// is的情况下查询条件无效
					params.put(columnName + "," + searchCondition, null);
				}

				String orderBy = advFilterList.get(1);
				if (StrUtils.isNotEmpty(orderBy)) {
					sortList.add(new String[] { columnName, orderBy });
				}
			}

			if (sortList.size() > 0) {
				String[] orderby = sortList.get(0);

				pager.setOrderField(orderby[0]);
				pager.setOrderBy(orderby[1]);
			}
		}
	}

	/**
	 * 获取最近使用的时间段
	 * 
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	protected Date getLeastRecentlyUsedTime(String days) throws ParseException {

		if ("THREEDAYS".equals(days)) {
			return dateFormat.parse(DaysUtils.getThreeDays());
		} else if ("SEVENDAYS".equals(days)) {
			return dateFormat.parse(DaysUtils.getSevenDays());
		} else if ("ONEMONTH".equals(days)) {
			return dateFormat.parse(DaysUtils.getOneMonth());
		} else {
			return dateFormat.parse(DaysUtils.getThreeMonths());
		}
	}

	/**
	 * 将查询对象转型
	 * 
	 * @param value
	 * @param dataType
	 *            类型，默认是string，使用like检索。可选值:string/integer/date
	 * @return
	 */
	private Object fixSearchValue(String value, String dataType) {
		if (StrUtils.isEmpty(value))
			return null;
		else if (StrUtils.isEmpty(dataType))
			return value;

		value = value.trim();

		try {
			if ("string".equalsIgnoreCase(dataType) || "str".equalsIgnoreCase(dataType)) {
				return value;
			} else if ("integer".equalsIgnoreCase(dataType) || "int".equalsIgnoreCase(dataType)) {
				return Integer.parseInt(value);
			} else if ("date".equalsIgnoreCase(dataType)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.parse(value);
			} else {
				return value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 处理查询条件 like/eq/le/ge/gt/lt/in/ne/is/bt bt=wetween,此时过去条件格式为abc!def
	 * 
	 * @return
	 */
	private String fixSearchCondition(String filterType) {
		String searchCondition = null;
		if (StrUtils.isEmpty(filterType)) {
			// 没有指定默认是like
			searchCondition = SearchCondition.ANYLIKE;
		} else if ("eq".equalsIgnoreCase(filterType)) {
			searchCondition = SearchCondition.EQUAL;
		} else if ("ge".equalsIgnoreCase(filterType)) {
			searchCondition = SearchCondition.MORETHANANDEQUAL;
		} else if ("le".equalsIgnoreCase(filterType)) {
			searchCondition = SearchCondition.LESSTHANANDEQUAL;
		} else if ("gt".equalsIgnoreCase(filterType)) {
			searchCondition = SearchCondition.MORETHAN;
		} else if ("lt".equalsIgnoreCase(filterType)) {
			searchCondition = SearchCondition.LESSTHAN;
		} else if ("in".equalsIgnoreCase(filterType)) {
			searchCondition = SearchCondition.IN_SUB;
		} else if ("ne".equalsIgnoreCase(filterType)) {
			searchCondition = SearchCondition.NOEQUAL;
		} else if ("is".equalsIgnoreCase(filterType)) {
			searchCondition = SearchCondition.IS;
		} else if ("like".equalsIgnoreCase(filterType)) {
			searchCondition = SearchCondition.ANYLIKE;
		} else if ("bt".equalsIgnoreCase(filterType)) {
			searchCondition = SearchCondition.BETWEENAND;
		}

		return searchCondition;
	}

	public Pager getPager() {
		if (null == pager) {
			pager = new Pager();
		}
		if (this.getGridPageSize() > 0)
			pager.setPageSize(this.getGridPageSize());

		return pager;
	}

	/**
	 * @Title: handleException @Description: 处理异常 暂时使用 @param @param ex
	 *         异常 @param @param defaultMsg 默认信息 @param @param returnMsgPage 返回页面
	 *         默认应该是"update"显示json @param @return 设定文件 @return String
	 *         返回类型 @throws
	 */
	protected String handleExceptionBase(Exception e, String defaultMsg, String returnMsgPage) {
		if (e instanceof BizException || e instanceof IllegalArgumentException) {
			setMessage(e.getMessage());
		} else {
			setMessage(defaultMsg);
		}
		return returnMsgPage;// "update";
	}

	protected String handleException(Exception e, String defaultMsg) {
		return handleExceptionBase(e, defaultMsg, UPDATE);
	}

	/**
	 * @Title: handleBaseMessage @Description: 处理消息 @param @param
	 *         isSuccess @param @param message @param @param bizObj
	 *         业务对象 @param @return 设定文件 @return String 返回类型 @throws
	 */
	protected <T> String handleBaseMessage(Boolean isSuccess, String message, T bizObj) {
		Map<String, Object> msgObj = new HashMap<String, Object>();
		msgObj.put("isSuccess", isSuccess);
		msgObj.put("msg", message);
		msgObj.put("bizObj", bizObj);
		msgObj.put("message", message);

		String resObjMsg = JSonUtils.toJSon(msgObj);
		return resObjMsg;
	}

	protected <T> void handleBaseMessageRenderText(Boolean isSuccess, String message, T bizObj) {
		String resObjMsg = handleBaseMessage(isSuccess, message, bizObj);
		renderText(resObjMsg);
	}

	/**
	 * @Title: renderJsonMessage @Description: 输出JSON提示消息 @param @param
	 *         isSuccess 是否成功 @param @param message 消息提示 @param @param bizObj
	 *         业务对象 @return void @throws
	 */
	public <T> void renderJsonMessage(Boolean success, String message, T bo, Map<String, String> msgMap) {
		/*
		 * Map<String, Object> msgObj = new HashMap<String, Object>();
		 * msgObj.put("isSuccess", isSuccess); msgObj.put("msg", message);
		 * msgObj.put("bizObj", bizObj); msgObj.put("message", message); String
		 * resObjMsg = JSonUtils.toJSon(msgObj);
		 */
		MessageObject mo = new MessageObject(success, message, bo, msgMap);
		render(JSON_TYPE, JSonUtils.toJSon(mo));
	}

	protected void renderDataTable(Pager pager) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("recordsTotal", pager.getTotalCount());
		pageMap.put("recordsFiltered", pager.getTotalCount());
		pageMap.put("data", pager.getResultList());
		String jsonPageStr = JSONFlexUtils.toJsonInclude(pageMap, "data");
		if (logger.isDebugEnabled()) {
			logger.debug("分页JSON:{}", jsonPageStr);
		}
		render(JSON_TYPE, jsonPageStr);
	}

	public void renderDataTable(Pager pager, String[] excludes) throws Exception {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("recordsTotal", pager.getTotalCount());
		pageMap.put("recordsFiltered", pager.getTotalCount());
		pageMap.put("data", pager.getResultList());
		String json = JSONFlexUtils.toJsonInclude(pageMap, excludes, "data");
		render(JSON_TYPE, json);
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Map<String, Object> getParams() {
		// return params;
		return new HashMap<String, Object>();
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public File getFileToUpload() {
		return fileToUpload;
	}

	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getChkStyle() {
		return chkStyle;
	}

	public void setChkStyle(String chkStyle) {
		this.chkStyle = chkStyle;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getOriFileName() {
		return oriFileName;
	}

	public void setOriFileName(String oriFileName) {
		try {
			if (StrUtils.isNotEmpty(oriFileName))
				this.oriFileName = new String(oriFileName.getBytes("UTF-8"), "ISO8859-1");
		} catch (Exception e) {
			e.printStackTrace();
			this.oriFileName = oriFileName;
		}
	}

	/**
	 * 解码
	 * 
	 * @param name
	 * @param enc
	 * @return
	 * @throws Exception
	 */
	public String getDecodeRequestParameter(String name) throws Exception {
		String parameterName = getRequestParameter(name);
		if (parameterName != null) {
			return decode(parameterName, "UTF-8");
		}
		return null;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getAdvFilterStr() {
		return advFilterStr;
	}

	public void setAdvFilterStr(String advFilterStr) {
		this.advFilterStr = advFilterStr;
	}

	public int getGridPageSize() {
		return gridPageSize;
	}

	public void setGridPageSize(int gridPageSize) {
		this.gridPageSize = gridPageSize;
	}

	public String getFromKey() {
		return fromKey;
	}

	public void setFromKey(String fromKey) {
		this.fromKey = fromKey;
	}

	public String getSelectId() {
		return selectId;
	}

	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}

	/**
	 * @return the updateField
	 */
	public String getUpdateField() {
		return updateField;
	}

	/**
	 * @param updateField
	 *            the updateField to set
	 */
	public void setUpdateField(String updateField) {
		this.updateField = updateField;
	}

}