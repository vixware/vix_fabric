package com.vix.nvix.warehouse.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import com.vix.core.utils.JSonUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.standingbook.controller.StandingBookController;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;

@Controller
@Scope("prototype")
public class VixntStandingBookAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private StandingBookController standingBookController;
	private String id;
	private String parentId;
	private String treeType;
	private String invShelfJson;
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			if(StringUtils.isNotEmpty(parentId)&&!"all".equals(parentId)){
				params.put("invWarehouse.id,"+SearchCondition.EQUAL, parentId);
			}
			List<InvShelf> invShelfList = vixntBaseService.findAllByConditions(InvShelf.class, params);
			StringBuffer jsonObj =  new StringBuffer();
			jsonObj.append("{");
			jsonObj.append("\"value\":");
			jsonObj.append("[");
				jsonObj.append("{");
				jsonObj.append("\"id\": \""+"all"+"\",");
				jsonObj.append("\"word\": \""+"全部货位"+"\"");
				jsonObj.append("}");
			if(invShelfList != null && invShelfList.size() > 0){
				for (InvShelf invShelf : invShelfList) {
					jsonObj.append(",{");
					jsonObj.append("\"id\": \""+invShelf.getId()+"\",");
					jsonObj.append("\"word\": \""+invShelf.getName()+"\"");
					jsonObj.append("}");
				}
			}
			jsonObj.append("]");
			jsonObj.append("}");
			invShelfJson = jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String itemname = getDecodeRequestParameter("itemname");
			if (StringUtils.isNotEmpty(itemname)) {
				params.put("itemname," + SearchCondition.ANYLIKE, itemname.trim());
			}
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null) {
				// 如果登录的员工属于经销商或门店
				params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
			}
			String invShelfName = getDecodeRequestParameter("invShelfName");
			if(StringUtils.isNotEmpty(invShelfName)){
				params.put("invShelf.name,"+SearchCondition.ANYLIKE, invShelfName);
			}
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}

			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType)) {
				if ("W".equals(treeType)) {
					params.put("invWarehouse.id," + SearchCondition.EQUAL, parentId);
				}
			}

			params.put("isQualfied," + SearchCondition.EQUAL, 1);
			pager = vixntBaseService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void importFile() {
		Map<String, String> msgMap = new HashMap<String, String>();
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
				msgMap.put("success", "0");
				msgMap.put("error", "没有选择文件!");
			} else {
				standingBookController.importShopEcProductPrice(fileToUpload, fileToUploadFileName);
				msgMap.put("success", "1");
				msgMap.put("msg", "导入成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msgMap.put("success", "0");
			msgMap.put("error", e.getMessage());
		} finally {
			if (fis != null) {
				IOUtils.closeQuietly(fis);
			}
		}
		String reMsg = JSonUtils.makeMapToJson(msgMap);
		renderHtml(reMsg);
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				InventoryCurrentStock pb = vixntBaseService.findEntityById(InventoryCurrentStock.class, id);
				if (null != pb) {
					standingBookController.deleteInventoryCurrentStock(pb);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void exportInventoryExcel() {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "库存台账表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

			Map<String, Object> params = getParams();
			// 根据盘点单导出相应数据
			// params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			List<InventoryCurrentStock> inventoryCurrentStockList = vixntBaseService.findAllByConditions(InventoryCurrentStock.class,params);
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("inventory_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("inventory_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("inventoryCurrentStockList", inventoryCurrentStockList);
					xlsArea.applyAt(new CellRef("inventoryCurrentStock!A1"), context);
					transformer.write();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String loadInvShelfList(){
		try {
			Map<String, Object> params = getParams();
			if(StringUtils.isNotEmpty(parentId)&&!"all".equals(parentId)){
				params.put("invWarehouse.id,"+SearchCondition.EQUAL, parentId);
			}
			List<InvShelf> invShelfList = vixntBaseService.findAllByConditions(InvShelf.class, params);
			StringBuffer jsonObj =  new StringBuffer();
			jsonObj.append("{");
			jsonObj.append("\"value\":");
			jsonObj.append("[");
				jsonObj.append("{");
				jsonObj.append("\"id\": \""+"all"+"\",");
				jsonObj.append("\"word\": \""+"全部货位"+"\"");
				jsonObj.append("}");
			if(invShelfList != null && invShelfList.size() > 0){
				for (InvShelf invShelf : invShelfList) {
					jsonObj.append(",{");
					jsonObj.append("\"id\": \""+invShelf.getId()+"\",");
					jsonObj.append("\"word\": \""+invShelf.getName()+"\"");
					jsonObj.append("}");
				}
			}
			jsonObj.append("]");
			jsonObj.append("}");
			invShelfJson = jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadInvShelfList";
	}
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	public String getInvShelfJson() {
		return invShelfJson;
	}
	public void setInvShelfJson(String invShelfJson) {
		this.invShelfJson = invShelfJson;
	}
}
