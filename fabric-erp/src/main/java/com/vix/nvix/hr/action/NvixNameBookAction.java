package com.vix.nvix.hr.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.oa.task.taskDefinition.entity.Uploader;
/**
 * @花名册
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixNameBookAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private Employee employee;
    private String empoyeeId;
    
	/** 花名册列表*/
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String name = getRequestParameter("name");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Employee.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 员工列表*/
	public String goEmployeeList(){
		return "employeeList";
	}
	/** 跳转至员工修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				employee = vixntBaseService.findEntityById(Employee.class, id);
			} else {
				employee = new Employee();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(employee.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(employee);
			employee = vixntBaseService.merge(employee);
			if (isSave) {
				renderText("1:" + employee.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + employee.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
		
	}
	
	/** 删除员工信息*/
	public void deleteById() {
		try {
			 Employee em = vixntBaseService.findEntityById(Employee.class, id);
			if (null != em) {
				vixntBaseService.deleteByEntity(em);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		
	}
    
    /** 附件列表*/
    public void getUploadersList(){
    	try {
    		Pager pager = getPager();
    		Map<String, Object> params = getParams();
    		String searchName = getDecodeRequestParameter("searchName");
    		if (StringUtils.isNotEmpty(searchName)) {
				params.put("fileName," + SearchCondition.EQUAL, searchName);
			}
    		if (StringUtils.isNotEmpty(id)) {
				params.put("employee.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, Uploader.class, params);
			}
    		renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /** 保存附件*/
    public void saveOrUpdateUploaders(){
    	try {
    		String[] savePathAndName = this.saveEmployeeUploadPic();
    		if(savePathAndName != null && savePathAndName.length == 2){
    			 Uploader uploader = new Uploader();
    			 uploader.setFileName(savePathAndName[1].toString());
    			 uploader.setFilePath("/file/ws/" + savePathAndName[1].toString());
    			 uploader.setCreateTime(new Date());
    				if (StringUtils.isNotEmpty(id)) {
    					employee = vixntBaseService.findEntityById(Employee.class, id);
    					if (employee != null) {
    						uploader.setEmployee(employee);
    					}
    				}
    				vixntBaseService.merge(uploader);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /** 删除附件*/
    public void deleteUploaderById(){
    	try {
			if(StringUtils.isNoneEmpty(id)){
				 Uploader uploader = vixntBaseService.findEntityById(Uploader.class, id);
				 if(null != uploader){
					 String fileName = uploader.getFileName();
					 String baseFolder = "c:/file/";
					 String downloadFile = baseFolder + fileName;
					 File f = new File(downloadFile); // 输入要删除的附件位置
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
    
    /** 导出花名册*/
    public void exportEmployeeExcel(){
    	try {
    		HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "花名册.xls";
			if(ua != null && ua.indexOf("Firefox") >= 0){
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			}else{
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			}
			Map<String, Object> params = getParams();
			List<Employee> employeeList = vixntBaseService.findAllByConditions(Employee.class, params);
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("employee_export_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("employee_export_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("employeeList", employeeList);
					xlsArea.applyAt(new CellRef("employee!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public String[] saveEmployeeUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				/** 上传目录 */
				String saveFolder = this.getEmployeeUploadFileSavePic();
				@SuppressWarnings("resource")
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
				String[] fileNames = fileToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = fileToUploadFileName.substring(0, fileToUploadFileName.length() - extFileName.length() - 1);

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

    public String getEmployeeUploadFileSavePic() {

		String baseFolder = "c:/file";

		String newFilePath = "";

		newFilePath = baseFolder;

		File dir = new File(newFilePath);
		if (!dir.exists())
			dir.mkdirs();

		return newFilePath;
	}
    
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getEmpoyeeId() {
		return empoyeeId;
	}

	public void setEmpoyeeId(String empoyeeId) {
		this.empoyeeId = empoyeeId;
	}
	
}
