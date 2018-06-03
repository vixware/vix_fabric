package com.vix.nvix.oa.document.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.oa.document.entity.DocumentUploader;
import com.vix.nvix.oa.document.entity.SecretGrade;
import com.vix.nvix.oa.document.entity.SendDocument;
import com.vix.nvix.oa.document.entity.SendType;
import com.vix.nvix.oa.document.entity.UrgentDegree;

@Controller
@Scope("prototype")
public class NvixSendDocumentAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private SendDocument sendDocument;
	private String entityName;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			String title = getDecodeRequestParameter("title");
			if (null != title && !"".equals(title)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
			}
			baseHibernateService.findPagerByHqlConditions(pager, SendDocument.class, params);
			String[] excludes = { "" };
			renderDataTable(pager,excludes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private List<SendType> sendTypeList;
	private List<SecretGrade> secretGradeList;
	private List<UrgentDegree> urgentDegreeList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			sendTypeList = baseHibernateService.findAllByEntityClass(SendType.class);
			secretGradeList = baseHibernateService.findAllByEntityClass(SecretGrade.class);
			urgentDegreeList = baseHibernateService.findAllByEntityClass(UrgentDegree.class);
			if(null != id && StrUtils.isNotEmpty(id)){
				sendDocument = baseHibernateService.findEntityById(SendDocument.class,id);
			}else{
				sendDocument = new SendDocument();
				sendDocument.setSendDate(new Date());
				sendDocument.setStatus("0");
				Employee employee = getEmployee();
				if (employee != null) {
					sendDocument.setCreator(employee.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理修改操作  */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.isNotEmpty(sendDocument.getId())) {
				isSave = false;
			} else {
				sendDocument.setCreateTime(new Date());
			}
			sendDocument = baseHibernateService.merge(sendDocument);
			String response = dealStartAndSubmitByBillsCode(BillType.OA_SEND_DOCUMENT, sendDocument);
			System.out.println(response);
			if (isSave) {
				renderText(SAVE_SUCCESS);
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
	
	public void saveOrUpdateInner() {
		try {
			sendDocument = baseHibernateService.merge(sendDocument);
			renderText("1:"+sendDocument.getId());
		} catch (Exception e) {
			e.printStackTrace();
			renderText("1:"+SAVE_FAIL);
		}
	}
	
	/** 处理删除操作 */
	public void deleteById() {
		try {
			sendDocument = baseHibernateService.findEntityById(SendDocument.class,id);
			if(null != sendDocument){
				baseHibernateService.deleteByEntity(sendDocument);
				renderText(DELETE_SUCCESS);
			}else{
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	/**
	 * 保存附件
	 */
	public void saveOrUpdateUploader() {
		try {
			String[] saveDocPathAndName = saveDocUploadPic();
			if (saveDocPathAndName != null && saveDocPathAndName.length == 4) {
				DocumentUploader documentUploader = new DocumentUploader();
				documentUploader.setName(saveDocPathAndName[1].toString());
				Employee employee = getEmployee();
				if (employee != null) {
					documentUploader.setCreator(employee.getName());
				}
				documentUploader.setPath("/img/document/" + saveDocPathAndName[1].toString());
				documentUploader.setFileSize(saveDocPathAndName[2].toString());
				documentUploader.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(id)) {
					sendDocument = vixntBaseService.findEntityById(SendDocument.class, id);
					if (sendDocument != null) {
						documentUploader.setSendDocument(sendDocument);
					}
				}
				documentUploader.setFileType(saveDocPathAndName[3].toString());
				documentUploader = vixntBaseService.merge(documentUploader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取附件列表
	public void goUploaderList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(id)) {
				params.put("receiveDocument.id," + SearchCondition.EQUAL, id);
				String searchName = getDecodeRequestParameter("searchName");
				if (StringUtils.isNotEmpty(searchName)) {
					params.put("name," + SearchCondition.ANYLIKE, searchName);
				}
				pager = vixntBaseService.findPagerByHqlConditions(pager, DocumentUploader.class, params);
			}
			renderDataTable(pager);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载附件
	 * 
	 * @return
	 */
	public String downloadUploader() {
		if (StringUtils.isNotEmpty(id)) {
			try {
				DocumentUploader uploader = vixntBaseService.findEntityById(DocumentUploader.class, this.id);
				String fileName = uploader.getName();
				this.setOriFileName(fileName);
				String baseFolder = "c:/img/";
				String downloadFile = baseFolder + fileName;
				this.setInputStream(new FileInputStream(downloadFile));
				if (uploader.getDownloadNum() != null) {
					uploader.setDownloadNum(uploader.getDownloadNum() + 1);
				} else {
					uploader.setDownloadNum(1);
				}
				uploader = vixntBaseService.merge(uploader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return NONE;
		}
		return "downloadUploader";
	}

	// 删除附件
	public void deleteUploaderById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				DocumentUploader uploader = vixntBaseService.findEntityById(DocumentUploader.class, id);
				if (null != uploader) {
					String fileName = uploader.getName();
					String baseFolder = "c:/img/";
					String downloadFile = baseFolder + fileName;
					File f = new File(downloadFile); // 输入要删除的文件位置
					if (f.exists()) {
						f.delete();
					}
					vixntBaseService.deleteByEntity(uploader);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	/** 收文审核列表页面 */
	public String goCheckList(){
		return "goCheckList";
	}
	
	public void goCheckListContent(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			params.put("status," + SearchCondition.EQUAL, "0");
			String title = getDecodeRequestParameter("title");
			if (null != title && !"".equals(title)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
			}
			baseHibernateService.findPagerByHqlConditions(pager, SendDocument.class, params);
			String[] excludes = { "" };
			renderDataTable(pager,excludes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 收文审核 */
	/*public String goCheck(){
		try{
			sendTypeList = baseHibernateService.findAllByEntityClass(SendType.class);
			secretGradeList = baseHibernateService.findAllByEntityClass(SecretGrade.class);
			urgentDegreeList = baseHibernateService.findAllByEntityClass(UrgentDegree.class);
			if(null != id && StrUtils.isNotEmpty(id)){
				sendDocument = baseHibernateService.findEntityById(SendDocument.class,id);
				Employee employee = getEmployee();
				if (employee != null) {
					sendDocument.setApprover(employee.getName());
				}
				sendDocument.setCheckDate(new Date());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goCheck";
	}*/

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getEntityName() {
		return entityName;
	}

	@Override
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public SendDocument getSendDocument() {
		return sendDocument;
	}

	public void setSendDocument(SendDocument sendDocument) {
		this.sendDocument = sendDocument;
	}

	public List<SendType> getSendTypeList() {
		return sendTypeList;
	}

	public void setSendTypeList(List<SendType> sendTypeList) {
		this.sendTypeList = sendTypeList;
	}

	public List<SecretGrade> getSecretGradeList() {
		return secretGradeList;
	}

	public void setSecretGradeList(List<SecretGrade> secretGradeList) {
		this.secretGradeList = secretGradeList;
	}

	public List<UrgentDegree> getUrgentDegreeList() {
		return urgentDegreeList;
	}

	public void setUrgentDegreeList(List<UrgentDegree> urgentDegreeList) {
		this.urgentDegreeList = urgentDegreeList;
	}
}