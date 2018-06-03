package com.vix.nvix.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.oa.attendance.entity.MonthlyStatistics;
//import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @类全名 com.vix.nvix.oa.action.AttendanceResultDetailAction
 *
 * @author zhanghaibing
 * @param <SearchConditionWrap>
 *
 * @date 2016年7月15日
 */
@Controller
@Scope("prototype")
public class AttendanceResultCollectAction<SearchConditionWrap> extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public void goMonthlyStatisticsList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 显示昨天的考勤数据
			String datestr = getMonthStr(new Date());
			if (datestr != null) {
				// params.put("dayAndMonth," + SearchCondition.EQUAL, datestr);
			}
			Employee emp = getEmployee();
			if (null != emp) {
				params.put("recordNum," + SearchCondition.EQUAL, emp.getCode());
			}
			String empName = getDecodeRequestParameter("empName");
			if (StringUtils.isNotEmpty(empName)) {
				params.put("empName," + SearchCondition.ANYLIKE, empName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, MonthlyStatistics.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMonthStr(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String monthStr = sdf.format(d);
		return monthStr;
	}

	public void dealEmpMonthCardRecords() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			vixntBaseController.dealEmpMonthCardRecords(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出月考勤统计
	 */
	@RequestMapping("/exportAttendanceOfMonthExcel")
	public void exportAttendanceOfMonthExcel(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 生成提示信息
		OutputStream fOut = null;
		try {
			// 进行转码，使其支持中文文件名
			String fileName = encodeFilename("月考勤汇总", req);// 处理中文文件名
			res.setHeader("content-disposition", "attachment;filename=" + fileName + ".xlsx");
			// 产生工作簿对象
			File file = loadTemplateFile(req.getSession(), "AttendanceOfMonth.xlsx");
			if (null != file) {
				InputStream is = new FileInputStream(file);
				XSSFWorkbook workbook = new XSSFWorkbook(is);

				// 产生工作表对象
				XSSFSheet sheet = workbook.getSheetAt(0);
				// 设置样式
				XSSFCellStyle rowStyle = createXSSFCellStyle(workbook);
				rowStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
				rowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				XSSFCellStyle rowFootStyle = createXSSFCellStyle(workbook);
				rowFootStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
				rowFootStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				// 获取月考勤
				List<MonthlyStatistics> list = loadExportAttendanceOfMonth(req);
				generateSellerInfoSheet(list, sheet, rowStyle, rowFootStyle);
				fOut = res.getOutputStream();
				workbook.write(fOut);
				// workbook.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fOut) {
					fOut.flush();
					fOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void generateSellerInfoSheet(List<MonthlyStatistics> list, XSSFSheet sheet, XSSFCellStyle rowStyle, XSSFCellStyle rowFootStyle) throws Exception {
		if (null != list && list.size() > 0) {
			if (null != list && list.size() > 0) {
				for (MonthlyStatistics ms : list) {
					for (int i = 1; i <= list.size(); i++) {
						/** 创建行 */
						XSSFRow row = sheet.createRow(i);
						/** 创建列 */
						int contentIndex = 0;
						XSSFCell cell1 = row.createCell(contentIndex);
						cell1.setCellStyle(rowStyle);
						cell1.setCellType(Cell.CELL_TYPE_STRING);
						cell1.setCellValue(ms.getEmpName());
						contentIndex++;
						XSSFCell cell2 = row.createCell(contentIndex);
						cell2.setCellStyle(rowStyle);
						cell2.setCellType(Cell.CELL_TYPE_STRING);
						cell2.setCellValue(ms.getDayAndMonth());
						contentIndex++;
						XSSFCell cell3 = row.createCell(contentIndex);
						cell3.setCellStyle(rowStyle);
						cell3.setCellType(Cell.CELL_TYPE_STRING);
						cell3.setCellValue(ms.getWorkDays());
						contentIndex++;
						XSSFCell cell4 = row.createCell(contentIndex);
						cell4.setCellStyle(rowStyle);
						cell4.setCellType(Cell.CELL_TYPE_STRING);
						cell4.setCellValue(ms.getCardNum() / 2);
						contentIndex++;
						// XSSFCell cell5 =
						// row.createCell(contentIndex);cell5.setCellStyle(rowStyle);
						// cell5.setCellType(XSSFCell.CELL_TYPE_STRING);
						// cell5.setCellValue();contentIndex++;
						XSSFCell cell6 = row.createCell(contentIndex);
						cell6.setCellStyle(rowStyle);
						cell6.setCellType(Cell.CELL_TYPE_STRING);
						cell6.setCellValue(ms.getCardNum() / 2);
						contentIndex++;
						XSSFCell cell7 = row.createCell(contentIndex);
						cell7.setCellStyle(rowStyle);
						cell7.setCellType(Cell.CELL_TYPE_STRING);
						cell7.setCellValue(ms.getCardNum() / 2);
						contentIndex++;
						XSSFCell cell8 = row.createCell(contentIndex);
						cell8.setCellStyle(rowStyle);
						cell8.setCellType(Cell.CELL_TYPE_STRING);
						cell8.setCellValue(ms.getCardNum() / 2);
						contentIndex++;
						XSSFCell cell9 = row.createCell(contentIndex);
						cell9.setCellStyle(rowStyle);
						cell9.setCellType(Cell.CELL_TYPE_STRING);
						cell9.setCellValue(ms.getCardNum() / 2);
						contentIndex++;
					}
				}
			}
		}
	}

	private List<MonthlyStatistics> loadExportAttendanceOfMonth(HttpServletRequest req) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String dayAndMonth = DateUtil.getLastYearMonth(sdf.format(new Date()));
		Map<String, Object> params = getParams();
		params.put("dayAndMonth," + SearchCondition.EQUAL, dayAndMonth);
		return vixntBaseService.findAllByConditions(MonthlyStatistics.class, params);
	}

	/** 载入考勤统计模板 */
	private File loadTemplateFile(HttpSession session, String templateFileName) throws Exception {
		String path = session.getServletContext().getRealPath("") + System.getProperty("file.separator") + "template" + System.getProperty("file.separator") + "attendance" + System.getProperty("file.separator") + "out" + System.getProperty("file.separator") + templateFileName;
		File file = new File(path);
		if (file.exists()) {
			return file;
		}
		return null;
	}

	/** 设置excel样式 */
	private XSSFCellStyle createXSSFCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 水平布局：居中
		style.setBorderTop(CellStyle.BORDER_THIN); // 上边边框
		style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边边框颜色
		style.setBorderBottom(CellStyle.BORDER_THIN); // 底部边框
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 底部边框颜色
		style.setBorderLeft(CellStyle.BORDER_THIN); // 左边边框
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边边框颜色
		style.setBorderRight(CellStyle.BORDER_THIN); // 右边边框
		style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边边框颜色
		return style;
	}

	/**
	 * 设置下载文件中文件的名称
	 * 
	 * @param filename
	 * @param request
	 * @return
	 */
	public static String encodeFilename(String filename, HttpServletRequest request) {
		/**
		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
		 */
		String agent = request.getHeader("USER-AGENT");
		try {
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				String newFileName = URLEncoder.encode(filename, "UTF-8");
				newFileName = StringUtils.replace(newFileName, "+", "%20");
				if (newFileName.length() > 150) {
					newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
					newFileName = StringUtils.replace(newFileName, " ", "%20");
				}
				return newFileName;
			}
			if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
				return MimeUtility.encodeText(filename, "UTF-8", "B");

			return filename;
		} catch (Exception ex) {
			return filename;
		}
	}
}