package com.vix.mdm.item.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.NumberFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.mdm.item.dao.IItemCatalogDao;
import com.vix.mdm.item.entity.ItemCatalog;


@Repository("itemCatalogDao")
public class ItemCatalogDaoImpl extends BaseHibernateDaoImpl implements IItemCatalogDao {

	private static final long serialVersionUID = 1L;

	@Override
	public int importProductCategory(File file, String fileName, String companyCode) throws Exception {
		int count = 0;
		InputStream is = new FileInputStream(file);
		String fileExName = fileName.split("\\.")[1];
		if(null != fileExName && "xls".equals(fileExName.toLowerCase())){
			count = importXlsProductCategory(is,companyCode);
		}
		if(null != fileExName && "xlsx".equals(fileExName.toLowerCase())){
			count = importXlsxProductCategory(is,companyCode);
		}
		return count;
	}
	
	private int importXlsxProductCategory(InputStream is, String companyCode) throws Exception{
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
		        ItemCatalog pc = null;
		        // 循环列Cell     
		        for(int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++){  
		        	XSSFCell xssfCell = xssfRow.getCell( cellNum);  
		        	if(xssfCell == null){  
		        		continue;  
		        	}
		        	String value = getXlsxValue(xssfCell);
		        	value = value.trim();
		        	/** 编码 */
		        	if(cellNum == 0){
		        		if(null == value || "".equals(value)){
		        			break;
		        		}
		        		pc = this.findEntityByAttribute(ItemCatalog.class, "codeRule", value);
		        		if(null == pc){
		        			pc = new ItemCatalog();
		        			pc.setCompanyCode(companyCode);
		        			pc.setCodeRule(value);
		        		}
		        		/** 设置父级分类 */
		        		if(null != pc && null == pc.getParentItemCatalog()){
		        			if(value.contains("_")){
		        				String parentProductCategoryCode = value.substring(0,value.lastIndexOf("_"));
		        				ItemCatalog parentPc = this.findEntityByAttribute(ItemCatalog.class, "codeRule", parentProductCategoryCode);
		        				if(null != parentPc){
		        					pc.setParentItemCatalog(parentPc);
		        				}
		        			}
		        		}
		        	}
		        	/** 名称 */
		        	if(cellNum == 1 && null != value && !"".equals(value)){
		        		pc.setName(value);
		        	}
		        	/** 状态 
		        	if(cellNum == 2 && null != value && !"".equals(value)){
		        		pc.setEnable(value);
		        	}
		        	/** 顺序
		        	if(cellNum == 3 && null != value && !"".equals(value)){
		        		if(NumberUtil.isNumeric(value)){
		        			pc.setOrderBy(Long.parseLong(value));
		        		}
		        	}
		        	/** 备注 */
		        	if(cellNum == 4 && null != value && !"".equals(value)){
		        		pc.setMemo(value);
		        	}
		        }
		        if(null != pc && null != pc.getCodeRule() && !"".equals(pc.getCodeRule())){
		        	if(null == pc.getId()){
		        		importCount++;
		        	}
		        	this.merge(pc);
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
	
	private int importXlsProductCategory(InputStream is, String companyCode) throws Exception{
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
	    		 ItemCatalog pc = null;
		        // 循环列Cell    
		        for(int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++){  
		        	HSSFCell hssfCell = hssfRow.getCell( cellNum);  
		        	if(hssfCell == null){  
		        		continue;  
		        	}
		        	String value = getXlsValue(hssfCell);
		        	value = value.trim();
		        	/** 编码 */
		        	if(cellNum == 0){
		        		if(null == value || "".equals(value)){
		        			break;
		        		}
		        		pc = this.findEntityByAttribute(ItemCatalog.class, "codeRule", value);
		        		if(null == pc){
		        			pc = new ItemCatalog();
		        			pc.setCompanyCode(companyCode);
		        			pc.setCodeRule(value);
		        		}
		        		/** 设置父级分类 */
		        		if(null != pc && null == pc.getParentItemCatalog()){
		        			if(value.contains("_")){
		        				String parentProductCategoryCode = value.substring(0,value.lastIndexOf("_"));
		        				ItemCatalog parentPc = this.findEntityByAttribute(ItemCatalog.class, "codeRule", parentProductCategoryCode);
		        				if(null != parentPc){
		        					pc.setParentItemCatalog(parentPc);
		        				}
		        			}
		        		}
		        	}
		        	/** 名称 */
		        	if(cellNum == 1 && null != value && !"".equals(value)){
		        		pc.setName(value);
		        	}
		        	/** 状态 
		        	if(cellNum == 2 && null != value && !"".equals(value)){
		        		pc.setEnable(value);
		        	}
		        	/** 顺序
		        	if(cellNum == 3 && null != value && !"".equals(value)){
		        		if(NumberUtil.isNumeric(value)){
		        			pc.setOrderBy(Long.parseLong(value));
		        		}
		        	}
		        	/** 备注 */
		        	if(cellNum == 4 && null != value && !"".equals(value)){
		        		pc.setMemo(value);
		        	}
		        }
		        if(null != pc && null != pc.getCodeRule() && !"".equals(pc.getCodeRule())){
		        	if(null == pc.getId()){
		        		importCount++;
		        	}
		        	this.merge(pc);
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
}
