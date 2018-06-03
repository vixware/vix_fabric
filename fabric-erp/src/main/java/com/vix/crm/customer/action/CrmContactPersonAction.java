package com.vix.crm.customer.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.CrmContactType;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class CrmContactPersonAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private String ids;
	private String name;
	private String shortName;
	private String title;
	private String sex;
	private ContactPerson contactPerson;
	private String pageNo;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

	private List<CustomerAccount> indexList;
	// 百家姓集合
	Map<String, List<String>> fastMap = new HashMap<String, List<String>>();

	@Override
	@SuppressWarnings("unchecked")
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			baseHibernateService.findPagerByHqlConditions(getPager(),ContactPerson.class, params);
			indexList = getPager().getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goList";
	}

	/** 获取列表数据 */
	public String goListContent() {
		Pager pager = getPager();
		List<CustomerAccount> cList = new ArrayList<CustomerAccount>();
		List<ContactPerson> cpList = new ArrayList<ContactPerson>();
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(12);
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}

			String firstName = getRequestParameter("firstName");
			if (null != firstName && !"".trim().equals(firstName)) {
				firstName = new String(getRequest().getParameter("firstName").getBytes("iso-8859-1"));
				params.put("lastName," + SearchCondition.ANYLIKE, firstName);
				cpList = baseHibernateService.findAllByConditions(ContactPerson.class, params);
				if (null != cpList && cpList.size() > 0) {
					pager.setResultList(cpList);
				}else {
					params.clear();
					params.put("firstName,"+ SearchCondition.ANYLIKE, firstName);
					cpList = baseHibernateService.findAllByConditions(ContactPerson.class, params);
					if(null != cpList && cpList.size() > 0){
						pager.setResultList(cpList);
					}
				}
			}

			String customerAccountName = getRequestParameter("customerAccountName");
			if (null != customerAccountName&& !"".trim().equals(customerAccountName)) {
				customerAccountName = new String(getRequest().getParameter("customerAccountName").getBytes("iso-8859-1"));
				params.clear();
				params.put("name," + SearchCondition.ANYLIKE,customerAccountName);
				cList = baseHibernateService.findAllByConditions(CustomerAccount.class, params);
				if (null != cList && cList.size() > 0) {
					for (CustomerAccount customerAccount : cList) {
						ContactPerson cp = customerAccount.getContactPerson();
						cpList.add(cp);
						if (null != cpList && cpList.size() > 0) {
							pager.setResultList(cpList);
						}
					}
				}
			} else {
				pager = baseHibernateService.findPagerByHqlConditions(getPager(), ContactPerson.class, params);
			}
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	// 高级搜索
	public String goSearch() {
		return "goSearch";
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), ContactPerson.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				contactPerson = baseHibernateService.findEntityById(ContactPerson.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	private CustomerAccount customerAccount;
	private List<CrmContactType> crmContactTypeList;
	public String goSingleSaveOrUpdate() {
		try {
			crmContactTypeList =  baseHibernateService.findAllByEntityClass(CrmContactType.class);
			if (null != id && !"".equals(id)) {
				contactPerson = baseHibernateService.findEntityById(ContactPerson.class, id);
				if(null == contactPerson.getCreatedBy() || "".equals(contactPerson.getCreatedBy())){
					if(null != SecurityUtil.getCurrentEmpId() && !"".equals(SecurityUtil.getCurrentEmpId())){
						Employee emp = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
						if(null != emp){
							contactPerson.setCreatedBy(emp.getName());
						}else{
							contactPerson.setCreatedBy(SecurityUtil.getCurrentUserName());
						}
					}else{
						contactPerson.setCreatedBy(SecurityUtil.getCurrentUserName());
					}
				}
				if(null == contactPerson.getCreateTime()){
					if(null == contactPerson.getDateEntered()){
						contactPerson.setDateEntered(new Date());
						contactPerson.setCreateTime(new Date());
					}
				}else {
					contactPerson.setDateEntered(contactPerson.getCreateTime());
				}
			}else {
				contactPerson = new ContactPerson();
				loadCommonData(contactPerson);
				contactPerson.setCreatedBy(SecurityUtil.getCurrentUserName());
				contactPerson.setDateEntered(new Date());
			}
			String customerAccountId = getRequestParameter("customerAccountId");
			if(null != customerAccountId && !"".trim().equals(customerAccountId)){
				customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, customerAccountId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleSaveOrUpdate";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != contactPerson.getId() && !"".equals(contactPerson.getId())) {
				isSave = false;
			} else {
				contactPerson.setCreateTime(new Date());
				loadCommonData(contactPerson);
				contactPerson.setCreateTime(new Date());
			}
			if(null == contactPerson.getPrimaryContact()){
				contactPerson.setPrimaryContact("0");
			}
			if(null != contactPerson.getName() && !"".equals(contactPerson.getName())){
				contactPerson.setLastName(contactPerson.getName().substring(0,1));
				contactPerson.setFirstName(contactPerson.getName().substring(1, contactPerson.getName().length()-1));
				if(null != contactPerson.getLastName() && !"".equals(contactPerson.getLastName())){
					contactPerson.setChineseFirstLetter(ChnToPinYin.getPYString(contactPerson.getLastName()).toUpperCase());
				}
			}
			contactPerson = baseHibernateService.merge(contactPerson);
			if (isSave) {
				renderText(contactPerson.getId());
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
		
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			ContactPerson pb = baseHibernateService.findEntityById(ContactPerson.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
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
				baseHibernateService.batchDelete(ContactPerson.class, delIds);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	@Override
	public void uploadAttachment() {
		try {
			if (null != fileToUpload) {
				String separator = System.getProperty("file.separator");
				/** 上传目录 */
				String baseDir = getServletContext().getRealPath(separator + "richContent");
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
				newFilePath = baseDir + separator + fileName + "_"
						+ newFileName + "." + extFileName;
				BufferedOutputStream bufOut = new BufferedOutputStream(
						new FileOutputStream(newFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();
				bufIn.close();
				renderJson("文件上传成功!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("文件上传失败!");
		}
	}

	/** 本月纪念日 */
	private List<ContactPerson> currentMonthDate = new ArrayList<ContactPerson>();
	/** 下月纪念日 */
	private List<ContactPerson> nextMonthDate = new ArrayList<ContactPerson>();

	public String goFastList() {
		String firstChar = "";
		String firstChian = "";
		try {
			Date date = new Date();
			String sDate = DateUtil.format(DateUtil.getFirstDayOfMonth(date),"yyyy-MM-dd");
			String eDate = DateUtil.format(DateUtil.getLastDayOfMonth(date),"yyyy-MM-dd");
			Map<String, Object> params = getParams();
			params.put("birthday," + SearchCondition.BETWEENAND, sDate + " 00:00:01!" + eDate + " 23:59:59");
			currentMonthDate = baseHibernateService.findAllByConditions(ContactPerson.class, params);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, 1);
			date = cal.getTime();
			params.clear();
			sDate = DateUtil.format(DateUtil.getFirstDayOfMonth(date),"yyyy-MM-dd");
			eDate = DateUtil.format(DateUtil.getLastDayOfMonth(date),"yyyy-MM-dd");
			params.put("birthday," + SearchCondition.BETWEENAND, sDate+ " 00:00:01!" + eDate + " 23:59:59");
			nextMonthDate = baseHibernateService.findAllByConditions(ContactPerson.class, params);

			List<ContactPerson> cpList = baseHibernateService.findAllByEntityClass(ContactPerson.class);
			if (null != cpList && cpList.size() > 0) {
				for (ContactPerson cp : cpList) {
					Set<String> keySet = new HashSet<String>();//临时存储姓氏，保证唯一
					List<String> list = new ArrayList<String>();//ResultList结果集（存储姓氏）
					if (null != cp.getChineseFirstLetter() && !"".trim().equals(cp.getChineseFirstLetter())) {
						firstChar = cp.getChineseFirstLetter().substring(0, 1);//字母
						if (null != cp.getName() && !"".trim().equals(cp.getName())) {
							firstChian = cp.getName().substring(0, 1);//姓
							if(null != fastMap.get(firstChar) && fastMap.get(firstChar).size() > 0){//如果为空直接添加，否则条件选择
								list = fastMap.get(firstChar);//获取已有的集合
								keySet.addAll(list);//转换到set中准备去重复
								list.clear();//清空list,准备装入去重后的数据
								keySet.add(firstChian);//去重复
								list.addAll(keySet);//添加去重复后的新数据
								fastMap.put(firstChar, list);//添加到map
							}else {
								list.add(firstChian);
								fastMap.put(firstChar, list);
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goFastList";
	}

	/** 获取列表数据 */
	@SuppressWarnings("unchecked")
	public String goFastListContent() {
		Pager pager = getPager();
		pager.setResultList(new ArrayList<>());
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager.setPageSize(20);
			if (null != name && !"".trim().equals(name)) {
				name = decode(name, "UTF-8");
				params.put("chineseFirstLetter," + SearchCondition.ANYLIKE,name);
				List<ContactPerson> cList = baseHibernateService.findAllByConditions(ContactPerson.class, params);
				if (null != cList && cList.size() > 0) {
					List<ContactPerson> cpList = new ArrayList<ContactPerson>();
					for (ContactPerson cp : cList) {
						String shortChar = cp.getChineseFirstLetter().toUpperCase().substring(0, 1);
						if (shortChar.equals(name)) {
							cpList.add(cp);
						}
					}
					if (null != cpList && cpList.size() > 0) {
						pager.setResultList(cpList);
					} else {
						cpList.add(new ContactPerson());
						pager.setResultList(cpList);
					}
				} else {
					cList.add(new ContactPerson());
					pager.setResultList(cList);
				}
			}
			if (null != shortName && !"".trim().equals(shortName)) {
				shortName = decode(shortName, "UTF-8");
				params.put("lastName," + SearchCondition.ANYLIKE, shortName);
				pager.setResultList(baseHibernateService.findAllByConditions(ContactPerson.class, params));
			}
			if(null != title && !"".trim().equals(title)){
				title = decode(title, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, title);
				pager.setResultList(baseHibernateService.findAllByConditions(ContactPerson.class, params));
			}
			if(null != sex && !"".trim().equals(sex)){
				params.put("sex," + SearchCondition.EQUAL,sex);
				pager.setResultList(baseHibernateService.findAllByConditions(ContactPerson.class, params));
			}
			String internal = getRequestParameter("internal");
			if(null != internal && !"".trim().equals(internal)){
				params.put("type," + SearchCondition.EQUAL, internal);
				List<CustomerAccount> caList = baseHibernateService.findAllByConditions(CustomerAccount.class, params);
				if(null != caList && caList.size() > 0){
					for(CustomerAccount ca : caList){
						ContactPerson contactPerson = ca.getContactPerson();
						pager.getResultList().add(contactPerson);
					}
				}else {
					pager.getResultList().add(new ContactPerson());
				}
			}
			String primaryContact = getRequestParameter("primaryContact");
			if(null != primaryContact && !"".trim().equals(primaryContact)){
				params.put("primaryContact," + SearchCondition.EQUAL, primaryContact);
				pager.setResultList(baseHibernateService.findAllByConditions(ContactPerson.class, params));
			}
			if (params.size() <= 0) {
				pager = baseHibernateService.findPagerByHqlConditions(getPager(), ContactPerson.class, params);
			}
			if (pager.getResultList().size() < 20) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 20 - listSize; i++) {
					pager.getResultList().add(new ContactPerson());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "FastListContent";
	}

	public String goChooseType() {
		return "goChooseType";
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

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}

	public List<ContactPerson> getCurrentMonthDate() {
		return currentMonthDate;
	}

	public void setCurrentMonthDate(List<ContactPerson> currentMonthDate) {
		this.currentMonthDate = currentMonthDate;
	}

	public List<ContactPerson> getNextMonthDate() {
		return nextMonthDate;
	}

	public void setNextMonthDate(List<ContactPerson> nextMonthDate) {
		this.nextMonthDate = nextMonthDate;
	}

	public List<CustomerAccount> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<CustomerAccount> indexList) {
		this.indexList = indexList;
	}
	
	public Map<String, List<String>> getFastMap() {
		return fastMap;
	}

	public void setFastMap(Map<String, List<String>> fastMap) {
		this.fastMap = fastMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public List<CrmContactType> getCrmContactTypeList() {
		return crmContactTypeList;
	}

	public void setCrmContactTypeList(List<CrmContactType> crmContactTypeList) {
		this.crmContactTypeList = crmContactTypeList;
	}
	
	

}
