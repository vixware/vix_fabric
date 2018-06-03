package com.vix.nvix.system.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.entity.HomeColumn;
import com.vix.nvix.common.base.entity.HomeColumnDetail;

/**
 * 网站栏目管理
 * 
 * @类全名 com.vix.nvix.system.action.ParkingCarMobileWebColumnAction
 *
 * @author zhanghaibing
 *
 * @date 2017年8月12日
 */
@Controller
@Scope("prototype")
public class NvixntWebColumnAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private String homeColumnId;
	private HomeColumn homeColumn;
	private HomeColumnDetail homeColumnDetail;

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != homeColumn.getId()) {
				isSave = false;
			}
			homeColumn.setIsTemp("");
			homeColumn = vixntBaseService.merge(homeColumn);
			if ("Y".equals(homeColumn.getTypeCode())) {
				Map<String, Object> homeColumnParams = getParams();
				homeColumnParams.put("typeCode," + SearchCondition.EQUAL, homeColumn.getTypeCode());
				List<HomeColumn> homeColumnList = vixntBaseService.findAllByConditions(HomeColumn.class, homeColumnParams);
				if (homeColumnList != null && homeColumnList.size() > 0) {
					for (HomeColumn column : homeColumnList) {
						column.setStatus("N");
						column = vixntBaseService.merge(column);
					}
				}
			}
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

	/**
	 * 保存栏目明细
	 */
	public void saveOrUpdateHomeColumnDetail() {
		boolean isSave = true;
		try {
			if (null != homeColumnDetail.getId()) {
				isSave = false;
			}
			homeColumnDetail = vixntBaseService.merge(homeColumnDetail);
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

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				homeColumn = vixntBaseService.findEntityByAttribute(HomeColumn.class, "id", id);
			} else {
				homeColumn = new HomeColumn();
				homeColumn.setIsTemp("1");
				homeColumn = vixntBaseService.merge(homeColumn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/**
	 * 跳转到新增栏目明细
	 * 
	 * @return
	 */
	public String goSaveOrUpdateHomeColumnDetail() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				homeColumnDetail = vixntBaseService.findEntityByAttribute(HomeColumnDetail.class, "id", id);
			} else {
				homeColumnDetail = new HomeColumnDetail();
				if (StringUtils.isNotEmpty(homeColumnId)) {
					homeColumn = vixntBaseService.findEntityById(HomeColumn.class, homeColumnId);
					if (homeColumn != null) {
						homeColumnDetail.setHomeColumn(homeColumn);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateHomeColumnDetail";
	}

	public void goSingleList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			pager = vixntBaseService.findPagerByHqlConditions(pager, HomeColumn.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goHomeColumnDetailList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id)) {
				params.put("homeColumn.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, HomeColumnDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				HomeColumn homeColumn = vixntBaseService.findEntityByAttribute(HomeColumn.class, "id", id);
				if (null != homeColumn) {
					Map<String, Object> params = getParams();
					params.put("homeColumn.id," + SearchCondition.EQUAL, id);
					List<HomeColumnDetail> homeColumnDetailList = vixntBaseService.findAllByConditions(HomeColumnDetail.class, params);

					if (homeColumnDetailList != null && homeColumnDetailList.size() > 0) {
						for (HomeColumnDetail homeColumnDetail : homeColumnDetailList) {
							if (null != homeColumnDetail) {
								vixntBaseService.deleteByEntity(homeColumnDetail);
							}
						}
					}
					vixntBaseService.deleteByEntity(homeColumn);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void deleteHomeColumnDetailById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				HomeColumnDetail homeColumnDetail = vixntBaseService.findEntityByAttribute(HomeColumnDetail.class, "id", id);
				if (null != homeColumnDetail) {
					vixntBaseService.deleteByEntity(homeColumnDetail);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/**
	 * 保存图片
	 */
	@Override
	public void saveOrUpdatePicture() {
		try {
			// 保存图片
			String[] savePathAndName = saveUploadPicture();
			if (savePathAndName != null && savePathAndName.length == 2) {
				HomeColumnDetail homeColumnDetail = null;
				if (StrUtils.isNotEmpty(id)) {
					homeColumnDetail = vixntBaseService.findEntityById(HomeColumnDetail.class, id);
				} else {
					homeColumnDetail = new HomeColumnDetail();
				}
				homeColumnDetail.setImageFilePath("/img/parkingCar/" + savePathAndName[1].toString());
				homeColumnDetail.setImageFileName(savePathAndName[1].toString());
				homeColumnDetail.setCreateTime(new Date());
				homeColumnDetail = vixntBaseService.merge(homeColumnDetail);
				if (homeColumnDetail != null) {
					renderText(homeColumnDetail.getId() + "," + "/img/parkingCar/" + savePathAndName[1].toString());
				} else {
					renderText("0,上传失败!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 禁用
	 */
	public void disabledHomeColumnById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				HomeColumn homeColumn = vixntBaseService.findEntityByAttribute(HomeColumn.class, "id", id);
				if (null != homeColumn) {
					homeColumn.setStatus("N");
					homeColumn = vixntBaseService.merge(homeColumn);
					renderText("禁用成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("禁用失败!");
		}
	}

	/**
	 * 启用
	 */
	public void enableHomeColumnById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				HomeColumn homeColumn = vixntBaseService.findEntityByAttribute(HomeColumn.class, "id", id);
				if (null != homeColumn) {
					homeColumn.setStatus("Y");
					homeColumn = vixntBaseService.merge(homeColumn);

					Map<String, Object> homeColumnParams = getParams();
					homeColumnParams.put("typeCode," + SearchCondition.EQUAL, homeColumn.getTypeCode());
					List<HomeColumn> homeColumnList = vixntBaseService.findAllByConditions(HomeColumn.class, homeColumnParams);
					if (homeColumnList != null && homeColumnList.size() > 0) {
						homeColumnList.remove(homeColumn);
						for (HomeColumn column : homeColumnList) {
							column.setStatus("N");
							column = vixntBaseService.merge(column);
						}
					}
					renderText("启用成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("启用失败!");
		}
	}

	public String[] saveUploadPicture() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
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
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the homeColumnId
	 */
	public String getHomeColumnId() {
		return homeColumnId;
	}

	/**
	 * @param homeColumnId
	 *            the homeColumnId to set
	 */
	public void setHomeColumnId(String homeColumnId) {
		this.homeColumnId = homeColumnId;
	}

	/**
	 * @return the homeColumn
	 */
	public HomeColumn getHomeColumn() {
		return homeColumn;
	}

	/**
	 * @param homeColumn
	 *            the homeColumn to set
	 */
	public void setHomeColumn(HomeColumn homeColumn) {
		this.homeColumn = homeColumn;
	}

	/**
	 * @return the homeColumnDetail
	 */
	public HomeColumnDetail getHomeColumnDetail() {
		return homeColumnDetail;
	}

	/**
	 * @param homeColumnDetail
	 *            the homeColumnDetail to set
	 */
	public void setHomeColumnDetail(HomeColumnDetail homeColumnDetail) {
		this.homeColumnDetail = homeColumnDetail;
	}

}