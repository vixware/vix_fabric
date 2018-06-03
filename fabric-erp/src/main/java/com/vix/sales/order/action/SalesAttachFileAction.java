package com.vix.sales.order.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.sales.order.entity.SalesAttachFile;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class SalesAttachFileAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private String memo;
	private SalesAttachFile salesAttachFile;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;
	private String pageNo;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String subject = getRequestParameter("name");
			if(StrUtils.objectIsNotNull(subject)){
				subject = decode(subject, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, subject);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), SalesAttachFile.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != salesAttachFile.getId()){
				isSave = false;
			}else{
				salesAttachFile.setCreateTime(new Date());
				loadCommonData(salesAttachFile);
			}
			salesAttachFile = baseHibernateService.merge(salesAttachFile);
			if(isSave){
				setMessage("1,"+SAVE_SUCCESS);
			}else{
				setMessage("1,"+UPDATE_SUCCESS);
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
			SalesAttachFile pb = baseHibernateService.findEntityById(SalesAttachFile.class,id);
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
	
	/** 删除附件 */
	public void deleteAttachFile(){
		try {
			if(null != id && !"".equals(id)){
				SalesAttachFile saf = baseHibernateService.findEntityById(SalesAttachFile.class, id);
				if(null != saf){
					/** 上传目录 */
					String fileName = saf.getFileName();
					String filePath = saf.getPath();
					String baseDir = getServletContext().getRealPath(filePath);
					String deleteFile = baseDir + System.getProperty("file.separator") + fileName;
					File file = new File(deleteFile);
					if(file.exists()){
						file.delete();
					}
					baseHibernateService.deleteByEntity(saf);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void uploadAttachment(){
		try{
			if(null != id && !"".equals(id)){
				SalesOrder so = baseHibernateService.findEntityById(SalesOrder.class, id);
				if(null != fileToUpload){
					String separator = System.getProperty("file.separator");
					/** 上传目录 */
					String filePath = separator+"attached"+ separator + "sales";
					String baseDir = getServletContext().getRealPath(filePath);
					BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
					String[] fileNames = fileToUploadFileName.split("\\.");
					String fileName = fileNames[0];
					String extFileName = fileNames[fileNames.length-1];
					String newFilePath = "";
					long timeTemp = System.currentTimeMillis();
					String newFileName = fileName+"_"+timeTemp+"."+extFileName;
					newFilePath = baseDir+separator+newFileName;
					BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(newFilePath));
					byte[] buf = new byte[1024*100];
					int len = -1;
					while((len = bufIn.read(buf))!= -1){
						bufOut.write(buf, 0, len);
					}
					bufOut.close();
					bufIn.close();
					salesAttachFile.setFileName(newFileName);
					salesAttachFile.setType(extFileName);
					salesAttachFile.setPath(filePath);
					salesAttachFile.setSalesOrder(so);
					baseHibernateService.merge(salesAttachFile);
					renderJson("文件上传成功!");
				}
			}else{
				renderJson("订单id获取失败!");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			renderJson("文件上传失败!");
		}
		
	}
	
	public String downloadAttachedFile(){
		if(null != id && !"".equals(id)){
			try{
				salesAttachFile = baseHibernateService.findEntityById(SalesAttachFile.class, id);
				String fileName = salesAttachFile.getFileName();
				String filePath = salesAttachFile.getPath();
				String downloadFile = getServletContext().getRealPath(filePath) + System.getProperty("file.separator") + fileName;
		        this.setInputStream(new FileInputStream(downloadFile));
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			return NONE;
		}
		return "downloadAttachedFile";
	}	
	
	public String goTopDynamicMenuContent(){
		return "goTopDynamicMenuContent";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public SalesAttachFile getSalesAttachFile() {
		return salesAttachFile;
	}

	public void setSalesAttachFile(SalesAttachFile salesAttachFile) {
		this.salesAttachFile = salesAttachFile;
	}
}
