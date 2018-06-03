package com.vix.nvix.oa.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * 
 * @ClassName: AddressesAction
 * @Description: 通讯录
 * @author bobchen
 * @date 2016年1月6日 下午4:08:08
 *
 */
@Controller
@Scope("prototype")
public class AddressesAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private Employee employee;
	private List<Employee> employeeList;
	private String id;
	private String fileId;
	private String syncTag;

	/** 个人通讯录 */
	@Override
	public String goList() {
		try {
			employee = super.getEmployee();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId," + SearchCondition.NOEQUAL, "");
			employeeList = vixntBaseService.findAllByConditions(Employee.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goViewWab() {
		return "goViewWab";
	}

	/**
	 * 跳转通讯录页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				employee = vixntBaseService.findEntityById(Employee.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 保存头像
	 */
	public void saveOrUpdateWxpQyPicture() {
		try {
			// 保存图片
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setCreateTime(new Date());
				wxpQyPicture = vixntBaseService.mergeOriginal(wxpQyPicture);
				if (wxpQyPicture != null) {
					renderText(wxpQyPicture.getId() + "," + "/img/wechat/" + savePathAndName[1].toString());
				} else {
					renderText("0,保存失败!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] saveUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSavePic();
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

	@Override
	public String getUploadFileSavePic() {

		String baseFolder = "c:/img";

		String newFilePath = "";

		newFilePath = baseFolder;

		File dir = new File(newFilePath);
		if (!dir.exists())
			dir.mkdirs();

		return newFilePath;
	}

	public void saveOrUpdate() {
		try {
			employee = vixntBaseService.merge(employee);
			if (StringUtils.isNotEmpty(fileId)) {
				WxpQyPicture wxpQyPicture = vixntBaseService.findEntityById(WxpQyPicture.class, fileId);
				wxpQyPicture.setEmployee(employee);
				wxpQyPicture = vixntBaseService.mergeOriginal(wxpQyPicture);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			Employee pb = vixntBaseService.findEntityById(Employee.class, id);
			if (null != pb) {
				vixntBaseService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 查看人员信息 */
	public String goInformation() {
		try {
			employee = vixntBaseService.findEntityById(Employee.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInformation";
	}

	/** 查询个人通讯录数据 */
	public void goWab() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 姓名
			String searchCode = getDecodeRequestParameter("searchCode");
			if (StringUtils.isNotEmpty(searchCode)) {
				params.put("name," + SearchCondition.ANYLIKE, searchCode);
			}
			params.put("userId," + SearchCondition.NOEQUAL, "");
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			pager = vixntBaseService.findPagerByHqlConditions(getPager(), Employee.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goPublicWab() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 姓名
			String searchCode = getDecodeRequestParameter("searchCode");
			if (StringUtils.isNotEmpty(searchCode)) {
				params.put("name," + SearchCondition.ANYLIKE, searchCode);
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			params.put("status," + SearchCondition.EQUAL, "1");
			pager = vixntBaseService.findPagerByHqlConditions(getPager(), Employee.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getSyncTag() {
		return syncTag;
	}

	public void setSyncTag(String syncTag) {
		this.syncTag = syncTag;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Override
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}
