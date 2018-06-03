package com.vix.core.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @ClassName: ImportByExcel
 * @Description: excel的导入
 * @author wangminchen wangmingchen@gmail.com
 * @date Mar 17, 2011 2:27:30 PM
 *
 */
public class ImportByExcel {

	public HSSFDataFormatter hdf;
	public DateFormat df;

	public ImportByExcel() {
		hdf = new HSSFDataFormatter();
		df = new SimpleDateFormat("yyyy-MM-dd");
	}

	/**
	 * 取得EXCEL文件中的数据，支持多个sheet数据
	 * 
	 * @param file
	 * @param sheetIndex  
	 * @param erroList
	 * @param rule
	 * @return
	 * @throws Exception
	 */
	public static List getData(File file,int sheetIndex, List erroList,Map<String, String> rule,String postfix ) throws Exception {
		List dataList = null;
		// 取得 Workbook 对象
		Workbook wb = getWorkbook(file.getAbsolutePath(),postfix);
		// 获得第几个sheet
		int sheetCount = wb.getNumberOfNames();
		Sheet sheet = wb.getSheetAt(sheetIndex);
		Map sheetData = getDateFormSheet(sheet, rule);
		dataList = (List) sheetData.get("data");
		List tempErrorList = (List) sheetData.get("error");
		erroList.addAll(tempErrorList);
		return dataList;
	}

	/**
	 * 得到一个sheet下的值
	 * 
	 * @param sheet
	 * @throws Exception
	 */
	public static Map getDateFormSheet(Sheet sheet, Map<String, String> rule)
			throws Exception {
		Map dataRecord = new HashMap();
		// 结果list
		List resultList = new ArrayList();
		List errorList = new ArrayList();
		Map ruleMap = null;
		Map ruleMapHead = null;
		// 解析验证规则
		Map tempMap = parseCheckRule(rule);
		ruleMap = (Map) tempMap.get("dataRule");
		ruleMapHead = (Map) tempMap.get("headRule");
		// 列数
		int colNum = ruleMapHead.size();
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			Row hssfRow = (Row) it.next();
			Map tempDataMap = getRowData(hssfRow, colNum, ruleMap, ruleMapHead);
			List tempListByData = (List) tempDataMap.get("rowData");
			List tempListByError = (List) tempDataMap.get("rowError");
			errorList.addAll(tempListByError);
			resultList.add(tempListByData);
		}
		dataRecord.put("data", resultList);
		dataRecord.put("error", errorList);
		return dataRecord;
	}

	/**
	 * 
	 * @param hssfRow
	 * @param colNum
	 * @param ruleMap
	 * @param ruleMapHead
	 * @return
	 * @throws Exception
	 */
	public static Map getRowData(Row hssfRow, int colNum, Map ruleMap,
			Map ruleMapHead) throws Exception {
		ImportByExcel ibe = null;
		String regRule = null;
		String result = null;
		List tempList = new ArrayList();
		List erroList = new ArrayList();
		Map dataRecordByRow = new HashMap();
		if (null == ibe) {
			ibe = new ImportByExcel();
		}
		// 取得单元格数据
		for (int i = 0; i < colNum; i++) {
			Cell cell = hssfRow.getCell(i,
					Row.RETURN_NULL_AND_BLANK);
			String value = ibe.getCellValue(cell);
			value = removeNewLine(value);
			String _value = (value == null ? "" : value.trim());
			if (hssfRow.getRowNum() > 0) {
				regRule = (String) ruleMap.get(i);
				result = ibe.checkValue(regRule, value);
				if (!"ok".equals(result)) {
					StringBuffer sb = new StringBuffer();
					sb.append("第 ").append((hssfRow.getRowNum() + 1)).append(
							" 行,第 ").append((i + 1)).append(" 列  ：").append(
							result);
					erroList.add(sb.toString());
				}
			} else {
				regRule = (String) ruleMapHead.get(i);
				if (null == _value || (!regRule.equals(_value))) {
					throw new Exception(_value + ":表头格式不正确！");
				}
			}
			tempList.add(value);
		}
		dataRecordByRow.put("rowData", tempList);
		dataRecordByRow.put("rowError", erroList);
		return dataRecordByRow;
	}

	/**
	 * 解析规则
	 * 
	 * @param rule
	 * @return
	 */
	public static Map parseCheckRule(Map<String, String> rule) {
		Map ruleData = new HashMap();
		// 定义规则
		Map ruleMap = new HashMap();
		Map ruleMapHead = new HashMap();
		String[] rules = rule.get("dataRule").split(",");
		for (int i = 0; i < rules.length; i++) {
			ruleMap.put(i, rules[i]);
		}
		String[] headRules = rule.get("headRule").split(",");
		for (int i = 0; i < headRules.length; i++) {
			ruleMapHead.put(i, headRules[i]);
		}
		ruleData.put("dataRule", ruleMap);
		ruleData.put("headRule", ruleMapHead);
		return ruleData;
	}

	/**
	 * 数据基本校验。
	 * 
	 * @param checkType
	 * @param value
	 * @return
	 */
	public String checkValue(String checkType, String value) {
		String descErroe = "ok";
		switch (checkType.charAt(0)) {
		case 'I': {
			String regxString = "^[0-9]+$";// 数字 可以为空
			if(null!=value&&!"".equals(value)){
				if ( !Pattern.matches(regxString, value.trim())) {
					descErroe = "(非必填项)必须是数字... ";
				}
			}
			break;
		}
		case 'S': {
			String regxString = "^\\S$";// 可以为空的字符串
			if(null!=value&&!"".equals(value)){
				if(isDateFormat(value)){
					descErroe = "必须是字符";
				}
					
			}
			break;
			
		}
		case 'R': {
			String regxString = "^\\s$";// 空字符串
			if (null == value || Pattern.matches(regxString, value.trim())) {
				descErroe = "不能为空";
			}
			break;
		}
		case 'N': {
			String regxString = "^[0-9]+$";// 数字
			if (null == value || !Pattern.matches(regxString, value)) {
				descErroe = "必须是数字";
			}
			break;
		}
		case 'D': {
			if (null == value || !isDateFormat(value))
				descErroe = "必须是日期：格式为1999-01-01";
		}
			break;
		default:
		}
		return descErroe;
	}

	/**
	 * 取得 Workbook 对象
	 * 
	 * @param list
	 * @return
	 */
	public static Workbook getWorkbook(String templateFile,String postfix) {
		Workbook wb = null;
		try {
			File excel = new File(templateFile);
			InputStream inputStream=new FileInputStream(excel);
			if("xls".equals(postfix)){// excel 2003 process
				wb = WorkbookFactory.create(
						new POIFSFileSystem(inputStream)
				);
			}else{
					wb = WorkbookFactory.create(
							OPCPackage.open(inputStream)
					);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wb;
	}
    
	/**
	 * 校验用户数是否合法.
	 */
	public boolean invalidUserNum(String value) {
		String[] num = value.split(";");
		for (int i = 0; i < num.length; i++) {
			if (!isInteger(num[i]))
				return true;
		}
		return false;
	}

	public boolean isDateFormat(String value) {
		try {
			df.parse(value);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public boolean isInteger(String value) {
		try {
			if (value.indexOf('.') != -1)
				value = value.substring(0, value.indexOf('.'));
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public boolean invalidNumber(String value) {
		String[] num = value.split(";");
		for (int i = 0; i < num.length; i++) {
			if (!isNumber(num[i]))
				return true;
		}
		return false;
	}

	public boolean isNumber(String value) {
		try {
			Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public  String  getCellValue(Cell cell) {
		if (cell == null)
			return null;
		int type = cell.getCellType();
		switch (type) {
		case Cell.CELL_TYPE_BLANK:
			return null;
		case Cell.CELL_TYPE_STRING:
			return convertString2Narrow(hdf.formatCellValue(cell));
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell))
				return df.format(cell.getDateCellValue());
			return hdf.formatCellValue(cell);
		default:
			return hdf.formatCellValue(cell);
		}
	}
	/**
	 * 
	 * @param row
	 * @param index
	 * @return
	 */
	public  String  getCellValue(HSSFRow row,int index) {
		HSSFCell cell = row.getCell((short) index);
		if (cell == null)
			return null;
		int type = cell.getCellType();
		switch (type) {
		case Cell.CELL_TYPE_BLANK:
			return "";
		case Cell.CELL_TYPE_STRING:
			return convertString2Narrow(hdf.formatCellValue(cell));
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell))
				return df.format(cell.getDateCellValue());
			return hdf.formatCellValue(cell);
		default:
			return hdf.formatCellValue(cell);
		}
	}

	/**
	 * 将字符串中的全角(wide)字符转换为半角(narrow)字符.<br/>
	 * 
	 * <pre>
	 * 算法说明：
	 * 1.英文字符（半角）的ASCII值处在32到126之间，在进行全角半角转换时，也就是转换这些字符。
	 * 2.对于大多数英文字符，将其ASCII值加上65248就变成中文字符。
	 * 3.半角空格&quot; &quot;，其ASCII值为32，全角空格&quot;　&quot;，其ASCII值为12288
	 *   英文句号&quot;.&quot;，其ASCII值为46，全角句号&quot;。&quot;，其ASCII值为12290
	 * 4.除了上面第3条描述的其它特殊字符，不作转换处理。
	 * </pre>
	 * 
	 * @param source
	 *            源字符串
	 * @return 半角字符串
	 */
	public static final String convertString2Narrow(String source) {
		StringBuffer result = new StringBuffer();
		char[] ca = source.toCharArray();
		for (int i = 0; i < source.length(); i++) {
			if (ca[i] == 12288) {// 全角空格' '
				result.append(' ');
			} else if (ca[i] == 12290) {// 全角句号'。'
				result.append('.');
			} else if (ca[i] >= 65281 && ca[i] <= 65374) {
				result.append((char) (ca[i] - 65248));
			} else {
				result.append(ca[i]);
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * @param row
	 * @param index
	 * @return
	 */
	public static int getIntCellValue(HSSFRow row, int index) {
		int rtn = 0;
		try {
			HSSFCell cell = row.getCell((short) index);
			rtn = (int) cell.getNumericCellValue();
		} catch (RuntimeException e) {
		}
		return rtn;
	}

	/**
	 * 
	 * @param row
	 * @param index
	 * @return
	 */
	public static Date getDateCellValue(HSSFRow row, int index) {
		Date rtn = null;
		try {
			HSSFCell cell = row.getCell((short) index);
			rtn = cell.getDateCellValue();
		} catch (RuntimeException e) {
		}
		return rtn;
	}

	/**
	 * 
	 * @param row
	 * @param index
	 * @return
	 */
	public static String getStringCellValue(HSSFRow row, int index) {
		String rtn = "";
		try {
			HSSFCell cell = row.getCell((short) index);
			rtn = cell.getRichStringCellValue().getString();

		} catch (RuntimeException e) {
		}
		return rtn;
	}

	/**
	 * 
	 * @param excel
	 *            读取的EXCEL文件
	 * @param index
	 *            第几个SHEET
	 * @param size
	 *            一共多少个字段
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public static List<List<Object>> getDataListForExcel(File excel, int index,String[] dataType) throws FileNotFoundException, IOException {
		// 读取excel文件
		List<List<Object>> lists = new ArrayList<List<Object>>();
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(excel));
		// 读取第index页的内容
		HSSFSheet sheet = wb.getSheetAt(index);
		// 从数据行开始读取数据
		for (int i = 0; i < sheet.getLastRowNum()+1; i++) {
			HSSFRow row = sheet.getRow(i);
			List<Object> objects = new ArrayList<Object>();
			for (int j = 0; j < dataType.length; j++) {
				ImportByExcel ib = new ImportByExcel();
				objects.add(ib.getCellValue(row,j));
			}
			lists.add(objects);
		}

		return lists;
	}

	/**
	 * 定位到末一列，读出所有的数据
	 * 
	 * @param excel
	 *            EXCEL文件
	 * @param index
	 *            第几个SHEET
	 * @param dataType
	 *            这一列里每一行对应的属性的类型
	 * @param indexRow
	 *            第几列
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<Object> getDataForColspanExcel(File excel, int index,
			String[] dataType, int indexCols) throws FileNotFoundException,
			IOException {
		// 读取excel文件
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(excel));
		// 读取第index页的内容
		HSSFSheet sheet = wb.getSheetAt(index);
		List<Object> lists = new ArrayList<Object>();
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			HSSFRow row = sheet.getRow(i);
			ImportByExcel ib = new ImportByExcel();
			lists.add(ib.getCellValue(row,indexCols));
		}
		return lists;

	}
	
	public static  String removeNewLine(String str)  {
		
		return null==str?null:str.replace("\n", "");
	}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println(123);
		//ImportByExcel.getDataListForExcel(excel, 0,dataType );
		//getData(file, sheetIndex, erroList, rule, postfix);
		List<List<Object>> rr = getDataListForExcel(new File("D:\\关键指标录入_201011.xls"), 0, new String[]{"String","String","String","String","String","String","String","String","String","String","String","String","String","String","String"});
		
	}
	
}