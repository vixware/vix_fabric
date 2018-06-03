/**
 * 
 */
package com.vix.nvix.oa.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.oa.attendance.entity.PunchCardRecord;
import com.vix.nvix.oa.entity.AttendanceAndClock;
import com.vix.nvix.oa.entity.AttendanceAndRecord;
import com.vix.oa.task.taskDefinition.entity.Uploader;

/**
 * @author Bluesnow 2016年6月22日
 */
@Controller
@Scope("prototype")
public class AttendanceDetailAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private String empId;
	private String startTime;
	private String endTime;
	private String parentId;
	private String empName;
	private String empCode;

	private Uploader uploader;
	private List<AttendanceAndClock> attendanceAndClockList;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public void goDailyStatisticsList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			pager = vixntBaseService.findPagerByHqlConditions(pager, PunchCardRecord.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDetailJson() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			pager = vixntBaseService.findPagerByHqlConditions(pager, PunchCardRecord.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				vixntBaseService.findEntityById(AttendanceAndRecord.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void getAttendanceById() {
		try {
			if (StrUtils.isNotEmpty(empId)) {
				Map<String, Object> params = getParams();
				OrganizationUnit org = vixntBaseService.findEntityByAttributeNoTenantId(OrganizationUnit.class, "id", empId);
				if (null != org) {
					params.put("organizationUnit.id", empId);
					List<Employee> empList = vixntBaseService.findAllByConditions(Employee.class, params);
					if (empList.size() > 0) {

					}
				} else {
				}
				attendanceAndClockList = vixntBaseService.findAllByConditions(AttendanceAndClock.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导入原始考勤记录
	 * 
	 * @return
	 */
	public String goAddUploader() {
		return "goAddUploader";
	}

	public void updateUploader() {
		try {
			String[] saveDocPathAndName = saveDocUploadPic();
			if (saveDocPathAndName != null && saveDocPathAndName.length == 3) {
				uploader.setFileName(saveDocPathAndName[1].toString());
				Employee employee = getEmployee();
				if (employee != null) {
					uploader.setCreator(employee.getName());
				}
				uploader.setFilePath("/attendance/attLogs/" + saveDocPathAndName[1].toString());
				uploader.setFilesize(saveDocPathAndName[2].toString());
				uploader.setCreateTime(new Date());
				uploader = vixntBaseService.merge(uploader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings("resource")
	public String[] saveDocUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != docToUpload) {
				/** 上传目录 */
				String saveFolder = getUploadFileSavePic();
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(docToUpload));
				int size = bufIn.available();
				String[] fileNames = docToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = docToUploadFileName.substring(0, docToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;
				String saveFilePath = saveFolder + "/" + saveFileName;
				// 上传
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
				pathAndName[2] = String.valueOf(size);
				// 执行数据导入
				readTxtFile(saveFilePath);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	public void readTxtFile(String filePath) {
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				List<PunchCardRecord> punchList = new ArrayList<PunchCardRecord>();
				while ((lineTxt = bufferedReader.readLine()) != null) {
					PunchCardRecord pcr = new PunchCardRecord();
					pcr.setUserCode(lineTxt.trim().split("\\t")[0]);
					pcr.setPunchCardDate(lineTxt.trim().split("\\t")[1]);
					// pcr.setRecordDate(DateUtil.parse(lineTxt.trim().split("\\t")[1]));
					pcr.setSource("2");
					pcr.setIpAddress("刷卡原始数据");
					initEntityBaseController.initEntityBaseAttribute(pcr);
					punchList.add(pcr);
					if (punchList.size() >= 100) {
						vixntBaseService.batchSave(punchList);
						punchList.clear();
					}
				}
				vixntBaseService.batchSave(punchList);
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}

	@Override
	public String getUploadFileSavePic() {
		String baseFolder = "d:/AttendanceLogs";
		String newFilePath = "";
		newFilePath = baseFolder;
		File dir = new File(newFilePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<AttendanceAndClock> getAttendanceAndClockList() {
		return attendanceAndClockList;
	}

	public void setAttendanceAndClockList(List<AttendanceAndClock> attendanceAndClockList) {
		this.attendanceAndClockList = attendanceAndClockList;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

}