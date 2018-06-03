package com.vix.mdm.item.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemGroup;
import com.vix.mdm.item.entity.PriceConditionCountArea;
import com.vix.mdm.item.entity.PriceConditionPriceArea;

@Controller
@Scope("prototype")
public class ItemGroupAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private String ids;
	private ItemGroup itemGroup;
	private String pageNo;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			if(null != name && !"".equals(name)){
				name = decode(name,"UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE,name);
			}
			params.put("isTemp,"+SearchCondition.NOEQUAL,"1");
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(),ItemGroup.class,params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goListContent";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				itemGroup = baseHibernateService.findEntityById(ItemGroup.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}else{
				itemGroup = new ItemGroup();
				itemGroup.setCreateTime(new Date());
				loadCommonData(itemGroup);
				itemGroup.setIsTemp("1");
				itemGroup  = baseHibernateService.merge(itemGroup);
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
			if(null != itemGroup.getId()){
				isSave = false;
			}
			itemGroup.setIsTemp("0");
			itemGroup = baseHibernateService.merge(itemGroup);
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
			ItemGroup pb = baseHibernateService.findEntityById(ItemGroup.class, id);
			if(null != pb){
				List<PriceConditionCountArea> listPcca = baseHibernateService.findAllByEntityClassAndAttribute(PriceConditionCountArea.class, "itemGroup.id", pb.getId());
				List<PriceConditionPriceArea> listPcpa = baseHibernateService.findAllByEntityClassAndAttribute(PriceConditionPriceArea.class, "itemGroup.id", pb.getId());
				if((null == listPcca || listPcca.size() <= 0) && (null == listPcpa || listPcpa.size() <= 0)){
					pb.getItems().clear();
					baseHibernateService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}else{
					setMessage("该物料组正在使用,不允许删除!");
				}
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
 
	public String goChooseItemGroup(){
		return "goChooseItemGroup";
	}

	public void getItemJson(){
		try {
			String json = "";
			String id = getRequestParameter("id");
			if(null != id && !"".equals(id)){
				ItemGroup pg = baseHibernateService.findEntityById(ItemGroup.class,id);
				json = convertListToJson(new ArrayList<Item>(pg.getItems()),pg.getItems().size(),"itemGroup");
			}
			if(!"".equals(json)){
				renderJson(json);
			}else{
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String addItemById(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				ItemGroup pg = baseHibernateService.findEntityById(ItemGroup.class,id);
				if(null != pg){
					String itemIds = getRequestParameter("itemIds");
					if(null != itemIds && !"".equals(itemIds)){
						String[] ids = itemIds.split(",");
						for(String itemId : ids){
							if(null != itemId && !"".equals(itemId)){
								Item item = baseHibernateService.findEntityById(Item.class, itemId);
								item.setItemGroup(pg);
								baseHibernateService.merge(item);
								setMessage("添加成功!");
							}
						}
					}
				}
			}else{
				setMessage(getText("mdm_itemNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(getText("mdm_itemAddFail"));
		}
		return UPDATE;
	}

	public String deleteItemById(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				Item item = baseHibernateService.findEntityById(Item.class,id);
				item.setItemGroup(null);
				baseHibernateService.merge(item);
				renderText(DELETE_SUCCESS);
				return UPDATE;
			}else{
				setMessage(getText("mdm_itemNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public ItemGroup getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(ItemGroup itemGroup) {
		this.itemGroup = itemGroup;
	}
}
