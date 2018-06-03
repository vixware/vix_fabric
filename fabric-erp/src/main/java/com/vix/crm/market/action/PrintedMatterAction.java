package com.vix.crm.market.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.market.entity.PrintedMatter;
import com.vix.crm.market.entity.PrintedMatterUnit;

@Controller
@Scope("prototype")
public class PrintedMatterAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private PrintedMatter printedMatter;
	private String pageNo;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String name = getRequest().getParameter("name");
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), PrintedMatter.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 获取列表数据  */
	public String goSubListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), PrintedMatter.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	private List<PrintedMatterUnit> printedMatterUnitList;
	public String goSaveOrUpdate() {
		try {
			printedMatterUnitList = baseHibernateService.findAllByEntityClass(PrintedMatterUnit.class);
			if(null != id && !"".equals(id)){
				printedMatter = baseHibernateService.findEntityById(PrintedMatter.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StrUtils.objectIsNotNull(printedMatter.getId())){
				isSave = false;
			}else{
				printedMatter.setCreateTime(new Date());
				loadCommonData(printedMatter);
				if(null != printedMatter.getPrintedCount()){
					printedMatter.setStockNumber(printedMatter.getPrintedCount());
				}
			}
			
			if(null == printedMatter.getPrintedMatterUnit()|| "".equals(printedMatter.getPrintedMatterUnit().getId()) || "0".equals(printedMatter.getPrintedMatterUnit().getId())){
				printedMatter.setPrintedMatterUnit(null);
			}
			printedMatter = baseHibernateService.merge(printedMatter);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			PrintedMatter pb = baseHibernateService.findEntityById(PrintedMatter.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if(null != ids && !"".equals(ids)){
				List<String> delIds = new ArrayList<String>();
				for(String idStr : ids.split(",")){
					if(null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)){
						delIds.add(idStr);
					}
				}
				baseHibernateService.batchDelete(PrintedMatter.class,delIds);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public String goChoosePrintedMatter(){
		return "goChoosePrintedMatter";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public PrintedMatter getPrintedMatter() {
		return printedMatter;
	}

	public void setPrintedMatter(PrintedMatter printedMatter) {
		this.printedMatter = printedMatter;
	}

	public List<PrintedMatterUnit> getPrintedMatterUnitList() {
		return printedMatterUnitList;
	}

	public void setPrintedMatterUnitList(
			List<PrintedMatterUnit> printedMatterUnitList) {
		this.printedMatterUnitList = printedMatterUnitList;
	}
}
