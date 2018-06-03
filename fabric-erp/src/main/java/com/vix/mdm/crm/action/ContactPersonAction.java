package com.vix.mdm.crm.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.ContactPerson;

@Controller
@Scope("prototype")
public class ContactPersonAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private ContactPerson contactPerson;
	private String pageNo;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;
	private List<ContactPerson> cpList;

	@Override
	@SuppressWarnings("unchecked")
	public String goList(){
		try{
			Map<String,Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			baseHibernateService.findPagerByHqlConditions(getPager(), ContactPerson.class, params);
			cpList = getPager().getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "goList";
	}
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), ContactPerson.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSubSingleList(){
		try {
			Map<String,Object> params = getParams();
			getPager().setPageSize(6);
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), ContactPerson.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				contactPerson = baseHibernateService.findEntityById(ContactPerson.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != contactPerson.getId()){
				isSave = false;
			}
			String firstName = contactPerson.getLastName();
			String py = ChnToPinYin.getPYString(firstName);
			contactPerson.setChineseFirstLetter(py.toUpperCase());
			contactPerson = baseHibernateService.merge(contactPerson);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			ContactPerson pb = baseHibernateService.findEntityById(ContactPerson.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	@Override
	public void uploadAttachment(){
		try{
			if(null != fileToUpload){
				String separator = System.getProperty("file.separator");
				/** 上传目录 */
				String baseDir = getServletContext().getRealPath(separator+"richContent");
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
				String[] fileNames = fileToUploadFileName.split("\\.");
				String fileName = fileNames[0];
				String extFileName = fileNames[fileNames.length-1];
				if(fileNames.length>2){
					for(int i = 1; i<fileNames.length-1; i++){
						fileName += "."+fileNames[i];
					}
				}
				String newFilePath = "";
				long newFileName = System.currentTimeMillis();
				newFilePath = baseDir+separator+fileName+"_"+newFileName+"."+extFileName;
				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(newFilePath));
				byte[] buf = new byte[1024*100];
				int len = -1;
				while((len = bufIn.read(buf))!= -1){
					bufOut.write(buf, 0, len);
				}
				bufOut.close();
				bufIn.close();
				renderJson("文件上传成功!");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			renderJson("文件上传失败!");
		}
		
	}
	
	public String goFastList(){
		return "goFastList";
	}
	
	public String goChooseType(){
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

	public List<ContactPerson> getCpList() {
		return cpList;
	}

	public void setCpList(List<ContactPerson> cpList) {
		this.cpList = cpList;
	}
}
