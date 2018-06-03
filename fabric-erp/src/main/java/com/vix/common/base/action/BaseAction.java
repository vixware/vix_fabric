package com.vix.common.base.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vix.common.base.controller.BillMarkProcessController;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.code.AutoCreateCode;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.vixlog.IOperateLog;
import com.vix.core.constant.SearchCondition;
import com.vix.core.flow.activiti.service.IStandardActivitiService;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.utils.StrUtils;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.system.billTypeManagement.entity.BillsProperty;

/**
 * 
 * @类全名 com.vix.common.base.action.BaseAction
 *
 * @author zhanghaibing
 *
 * @date 2017年9月28日
 */
public abstract class BaseAction extends BaseSecAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	public IStandardActivitiService standardActivitiService;
	/**
	 * 初始化基础数据
	 */
	@Autowired
	public InitEntityBaseController initEntityBaseController;
	/**
	 * 修改留痕
	 */
	@Autowired
	public BillMarkProcessController billMarkProcessController;
	/**
	 * 保存操作日志
	 */
	@Autowired
	public IOperateLog vixOperateLog;
	/**
	 * 自动生成编码
	 */
	@Autowired
	public AutoCreateCode autoCreateCode;
	protected File docToUpload;
	protected String docToUploadFileName;

	public List<BizType> listBizType() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			return baseHibernateService.findAllByConditions(BizType.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 是否允许提交审批，如果业务单据绑定了流程则返回1允许提交审批 */
	public String isAllowAudit(String billType) throws Exception {
		String tenantId = SecurityUtil.getCurrentUserTenantId();
		if (StringUtils.isNotEmpty(tenantId)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId," + SearchCondition.EQUAL, tenantId);
			params.put("propertyCode," + SearchCondition.EQUAL, billType);
			List<BillsProperty> brList = baseHibernateService.findAllByConditions(BillsProperty.class, params);
			if (null != brList && brList.size() > 0) {
				BillsProperty bp = brList.get(0);
				if (bp != null) {
					String processDefinitionId = standardActivitiService.findProcessDefinitionIdByCode(bp.getId() + "X");
					if (null != processDefinitionId && !"".equals(processDefinitionId)) {
						return "1";
					}
				}
			}
		}
		return "0";
	}

	/** 装载通用数据 */
	public void loadCommonData(BaseEntity baseEntity) throws InvocationTargetException, IllegalAccessException {
		String ti = SecurityUtil.getCurrentUserTenantId();
		if (null != ti && !"".equals(ti)) {
			BeanUtils.setProperty(baseEntity, "tenantId", ti);
		} else {
			String tenantId = PropertyConfigLoader.system_tenantId;
			if (null != tenantId && !"".equals(tenantId)) {
				BeanUtils.setProperty(baseEntity, "tenantId", tenantId);
			} else {
				BeanUtils.setProperty(baseEntity, "tenantId", "1001");
			}
		}

		Object objCc = getSession().getAttribute("companyCode");
		/** 载入公司编码 */
		if (null != objCc && !"".equals(objCc)) {
			BeanUtils.setProperty(baseEntity, "companyCode", objCc.toString());
		} else {
			String companyCode = PropertyConfigLoader.system_companyCode;
			if (null != companyCode && !"".equals(companyCode)) {
				BeanUtils.setProperty(baseEntity, "companyCode", companyCode);
			} else {
				BeanUtils.setProperty(baseEntity, "companyCode", "1001");
			}
		}

		/** 载入数据创建人 */
		Employee emp = getEmployee();
		if (null != emp && null != emp.getId() && !"".equals(emp.getId())) {
			BeanUtils.setProperty(baseEntity, "creator", emp.getId().toString());
		}
	}

	/** 对象空值检查 */
	public void checkEntityNullValue(BaseEntity baseEntity, String[] attrArray) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (null == baseEntity || null == attrArray || attrArray.length <= 0) {
			return;
		}
		// 判断字符串数组中是否有空
		for (String attr : attrArray) {
			if (StrUtils.objectIsNull(attr)) {
				continue;
			}
			// 处理对象名第一个字母转小写
			String frontStr = attr.substring(0, 1);
			String backStr = attr.substring(1, attr.length());
			Object targetObj = PropertyUtils.getProperty(baseEntity, frontStr.toLowerCase() + backStr);

			// 判断对象是否未空
			if (null != targetObj) {
				String id = BeanUtils.getProperty(targetObj, "id");
				if (null == id || "".equals(id)) {
					PropertyUtils.setProperty(baseEntity, attr, null);
				}
			}
		}
	}

	/** 获取职员姓名 */
	public Employee getEmployee() {
		Employee emp = null;
		try {
			String empId = SecurityUtil.getCurrentEmpId();
			if (empId != null) {
				emp = baseHibernateService.findEntityById(Employee.class, empId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return emp;
	}

	/** 获取公司信息 */
	public Organization getOrganization() {
		Organization organization = null;
		try {
			String tenantId = SecurityUtil.getCurrentUserTenantId();
			if (tenantId != null) {
				organization = baseHibernateService.findEntityByAttribute(Organization.class, "tenantId", tenantId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return organization;
	}

	/** 获取职员姓名 */
	public String getEmployee(String id) {
		if (null != id && !"".equals(id)) {
			try {
				BaseEmployee e = baseHibernateService.findEntityById(BaseEmployee.class, id);
				if (null != e) {
					return e.getName();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "";
	}

	public UserAccount getCurrentUser() {
		return SecurityUtil.getCurrentUserAccount();
	}
	
	public String getUploadFileSavePic() {

		String baseFolder = "c:/img";

		String newFilePath = "";

		newFilePath = baseFolder;

		File dir = new File(newFilePath);
		if (!dir.exists())
			dir.mkdirs();

		return newFilePath;
	}
	
	public String[] saveDocUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != docToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSavePic();
				@SuppressWarnings("resource")
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(docToUpload));
				int size = bufIn.available();
				String[] fileNames = docToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = docToUploadFileName.substring(0, docToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;

				String saveFilePath = saveFolder + "/" + saveFileName;

				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(saveFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();

				pathAndName = new String[4];
				pathAndName[0] = saveFolder;
				pathAndName[1] = saveFileName;
				if (size > 1024) {
					pathAndName[2] = String.valueOf(Math.ceil(size / 1024) + "K");
				} else {
					pathAndName[2] = String.valueOf(size + " Bytes");
				}
				pathAndName[3] = extFileName;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	public File getDocToUpload() {
		return docToUpload;
	}

	public void setDocToUpload(File docToUpload) {
		this.docToUpload = docToUpload;
	}

	public String getDocToUploadFileName() {
		return docToUploadFileName;
	}

	public void setDocToUploadFileName(String docToUploadFileName) {
		this.docToUploadFileName = docToUploadFileName;
	}

}