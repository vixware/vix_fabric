package com.vix.system.enumeration.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.system.enumeration.dao.IEnumerationDao;
import com.vix.system.enumeration.entity.EnumValue;
import com.vix.system.enumeration.entity.Enumeration;
import com.vix.system.enumeration.service.IEnumerationService;

@Repository("enumerationDao")
public class EnumerationDaoImpl extends BaseHibernateDaoImpl implements IEnumerationDao {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IEnumerationService enumerationService;
	
	@Override
	public int importFiles(File file, String fileName) throws Exception {
		int count = 0;
		InputStream is = new FileInputStream(file);
		String fileExName = fileName.split("\\.")[1];
		if(null != fileExName && "xls".equals(fileExName.toLowerCase())){
			count = importXlsEnumeration(is);
		}
		if(null != fileExName && "xlsx".equals(fileExName.toLowerCase())){
			count = importXlsxEnumeration(is);
		}
		return count;
	}

	private int importXlsxEnumeration(InputStream is) throws Exception{
		int importCount = 0;
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);  
	    // 循环工作表Sheet  
	    for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){
	    	XSSFSheet xssfSheet = xssfWorkbook.getSheetAt( numSheet);  
		    if(xssfSheet == null){  
		    	continue;  
		    }  
		    // 循环行Row   
		    for(int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++ ){  
		    	XSSFRow xssfRow = xssfSheet.getRow( rowNum);  
		        if(xssfRow == null || rowNum == 0){  
		          continue;  
		        }
		        Enumeration enumeration = null;
		        EnumValue enumValue = null;
		        // 循环列Cell     
		        for(int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++){  
		        	XSSFCell xssfCell = xssfRow.getCell( cellNum);  
		        	if(xssfCell == null){  
		        		continue;  
		        	}
		        	String value = getXlsxValue(xssfCell);
		        	value = value.trim();
		        	/** 分类编码 */
		        	if(cellNum == 0){
		        		if(null == value || "".equals(value)){
		        			break;
		        		}
		        		enumeration = this.findEntityByAttribute(Enumeration.class, "enumerationCode", value);
		        		if(null == enumeration){
		        			enumeration = new Enumeration();
		        			enumeration.setEnumerationCode(value);
		        		}
		        	}
		        	if(null == enumeration){break;}
		        	/** 字典编码 */
		        	if(cellNum == 1){
		        		if(null == value && "".equals(value)){
		        		}else {
		        			enumValue = new EnumValue();
		        			enumValue.setEnumValueCode(value);
		        			enumValue.setEnumeration(enumeration);
		        		}
		        	}
		        	/** 字典名称或分类名称*/
		        	if(cellNum == 2 && null != value && !"".equals(value)){
		        		if(null == enumValue){
		        			enumeration.setName(value);
		        			enumeration.setDisplayName(value);
		        		}else {
		        			enumValue.setDisplayName(value);
		        		}
		        	}
		        	/** 备注 */
		        	if(cellNum == 3 && null != value && !"".equals(value)){
		        		if(null == enumValue){
		        			enumeration.setMemo(value);
		        		}else {
		        			enumValue.setValue(value);
		        		}
		        	}
		        	/** 是否缺省*/
		        	if(cellNum == 4 && null != value && !"".equals(value)){
		        		if(null != enumValue){
		        			//设置参数默认值
		        			enumValue.setIsDefault(value);
		        			//启用字典参数
		        			enumValue.setEnable("1");
		        		}
		        	}
		        }
		        if(null != enumValue && null != enumValue.getEnumValueCode() && !"".equals(enumValue.getEnumValueCode())){
		        	enumValue = this.merge(enumValue);
		        	Set<EnumValue> eSet = new HashSet<EnumValue>();
		        	eSet.add(enumValue);
		        	enumeration.setEnumValues(eSet);
		        	this.merge(enumeration);
		        }else {
		        	if(null != enumeration && null != enumeration.getEnumerationCode() && !"".equals(enumeration.getEnumerationCode())){
		        		if(null == enumeration.getId()){
		        			importCount++;
		        		}
		        		this.merge(enumeration);
		        	}
		        }
		    }  
	    } 
	    return importCount;
	}
	
	private String getXlsxValue(XSSFCell xssfCell){  
		if(xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN){  
			return String.valueOf( xssfCell.getBooleanCellValue());  
		}else if(xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC){
			double d = xssfCell.getNumericCellValue();
			String s = NumberFormat.getInstance().format(d);  
			return s.replaceAll(",", "");  
		}else{  
			xssfCell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf( xssfCell.getStringCellValue());  
		}  
	}  
	
	private int importXlsEnumeration(InputStream is) throws Exception{
		int importCount = 0;
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook( is);   
	    // 循环工作表Sheet  
	    for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){  
	    	HSSFSheet hssfSheet = hssfWorkbook.getSheetAt( numSheet);  
	    	if(hssfSheet == null){  
	    		continue;  
	    	}  
	    	// 循环行Row   
	    	for(int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++){  
	    		HSSFRow hssfRow = hssfSheet.getRow( rowNum);  
	    		if(hssfRow == null || rowNum == 0){  
	    			continue;  
	    		}
	    		 Enumeration enumeration = null;
			     EnumValue enumValue = null;
		        // 循环列Cell    
		        for(int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++){  
		        	HSSFCell hssfCell = hssfRow.getCell( cellNum);  
		        	if(hssfCell == null){  
		        		continue;  
		        	}
		        	String value = getXlsValue(hssfCell);
		        	value = value.trim();
		        	/** 分类编码 */
		        	if(cellNum == 0){
		        		if(null == value || "".equals(value)){
		        			break;
		        		}
		        		enumeration = this.findEntityByAttribute(Enumeration.class, "enumerationCode", value);
		        		if(null == enumeration){
		        			enumeration = new Enumeration();
		        			enumeration.setEnumerationCode(value);
		        		}
		        	}
		        	if(null == enumeration){break;}
		        	/** 字典编码 */
		        	if(cellNum == 1){
		        		if(null == value && "".equals(value)){
		        		}else {
		        			enumValue = new EnumValue();
		        			enumValue.setEnumValueCode(value);
		        			enumValue.setEnumeration(enumeration);
		        		}
		        	}
		        	/** 字典名称或分类名称*/
		        	if(cellNum == 2 && null != value && !"".equals(value)){
		        		if(null == enumValue){
		        			enumeration.setName(value);
		        			enumeration.setDisplayName(value);
		        		}else {
		        			enumValue.setDisplayName(value);
		        		}
		        	}
		        	/** 备注 */
		        	if(cellNum == 3 && null != value && !"".equals(value)){
		        		if(null == enumValue){
		        			enumeration.setMemo(value);
		        		}else {
		        			enumValue.setValue(value);
		        		}
		        	}
		        	/** 是否缺省*/
		        	if(cellNum == 4 && null != value && !"".equals(value)){
		        		if(null != enumValue){
		        			//设置参数默认值
		        			enumValue.setIsDefault(value);
		        			//启用字典参数
		        			enumValue.setEnable("1");
		        		}
		        	}
		        }
		        if(null != enumValue && null != enumValue.getEnumValueCode() && !"".equals(enumValue.getEnumValueCode())){
		        	enumValue = this.merge(enumValue);
		        	Set<EnumValue> eSet = new HashSet<EnumValue>();
		        	eSet.add(enumValue);
		        	enumeration.setEnumValues(eSet);
		        	this.merge(enumeration);
		        }else {
		        	if(null != enumeration && null != enumeration.getEnumerationCode() && !"".equals(enumeration.getEnumerationCode())){
		        		if(null == enumeration.getId()){
		        			importCount++;
		        		}
		        		this.merge(enumeration);
		        	}
		        }
		    }  
	    } 
	    return importCount;
	}
	
	private String getXlsValue(HSSFCell hssfCell){  
		if(hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN){  
			return String.valueOf( hssfCell.getBooleanCellValue());  
		}else if(hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
			double d = hssfCell.getNumericCellValue();
			String s = NumberFormat.getInstance().format(d);  
			return s.replaceAll(",", ""); 
		}else if(hssfCell.getCellType() == Cell.CELL_TYPE_STRING){
			return String.valueOf(hssfCell.getStringCellValue());
		}else{  
			hssfCell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(hssfCell.getStringCellValue());  
		}  
	}

	@Override
	public Enumeration findEnumerationByHql(String hql, Map<String,Object> params) throws Exception {
		Enumeration enumeration = null;
		enumeration = enumerationService.findObjectByHql(hql, params);
		if(null != enumeration){
			return enumeration;
		}else {
			return new Enumeration();
		}
	}
}
