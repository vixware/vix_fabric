package com.vix.srm.config.action;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierCategory;

@Controller
@Scope("prototype")
public class DirectoryManagementAction extends BaseAction{
	public static final String GO_SPPLIERMGR_LIST_CONTENT ="goListContent";/** 列表数据页 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long parentId;
	
	@Override
	public String goList(){
		try{
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return GO_LIST;
	}
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			this.addTimeLimitToParam(params);
			
			if(parentId!=null && parentId>0)
				params.put("supplierId,"+SearchCondition.EQUAL, this.parentId);
			
			Pager pager = this.getPager();
			pager.setOrderField("createTime");
			pager.setOrderBy("desc");

			//在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			try{
				this.baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
			}catch(Exception e){
				e.printStackTrace();
			}	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SPPLIERMGR_LIST_CONTENT;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}
	
	
	/** 树形结构JSON */
	public void findTreeToJson(){
		try{
			/** 获取查询参数 */
			Map<String,Object> params = getParams();
			Map<String,Object> paramsSupp = getParams();
			
			//分类是直接删除，不需要有效时间控制
			//this.addTimeLimitToParam(params);
			this.addTimeLimitToParam(paramsSupp);
			
			if(null!=id && id>0){
				params.put("supplierCategory.id,"+SearchCondition.EQUAL, id);
				paramsSupp.put("supplierCategory.id,"+SearchCondition.EQUAL, id);
				//status 3是有效
				paramsSupp.put("status," + SearchCondition.EQUAL, Supplier.status_formal);
			}
			
			List<SupplierCategory> listCategory = this.baseHibernateService.findAllByConditions(SupplierCategory.class, params);

			List<Supplier> suppList = this.baseHibernateService.findAllByConditions(Supplier.class, paramsSupp);
			
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for(int i =0;i<listCategory.size();i++){
				SupplierCategory cc = listCategory.get(i);

				strSb.append("{id:");
				strSb.append(cc.getId());
				strSb.append(",name:\"");
				strSb.append(cc.getName());
				strSb.append("\",open:false,isParent:true},");
			}

			for(int i =0;i<suppList.size();i++){
				Supplier cc = suppList.get(i);

				strSb.append("{id:");
				strSb.append(cc.getId());
				strSb.append(",name:\"");
				strSb.append(cc.getName());
				strSb.append("\",open:false,isParent:false},");
			}
			
			if(listCategory.size()>0 || suppList.size()>0)
				strSb.substring(0, strSb.length()-1);
			
			strSb.append("]");
			renderHtml(strSb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String goChooseCategory(){
		return "goChooseCategory";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
