package com.vix.common.common.select.metaData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.meta.entity.BaseMetaDataCategory;
import com.vix.common.meta.service.IBaseMetaDataCategoryService;
import com.vix.common.meta.service.IBaseMetaDataService;
import com.vix.core.web.Pager;

/**
 * 元数据对象的选择
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class CommonSelectMetaDataAction extends BaseAction{

	/** 左侧的分类id */
	private String id;
	
	/** 元数据名称 */
	private String metaDataName;
	
	@Autowired
	private IBaseMetaDataService baseMetaDataService;
	@Autowired
	private IBaseMetaDataCategoryService baseMetaDataCategoryService;
	
    public String goChooseMetaData(){
    	if(StringUtils.isEmpty(chkStyle)){
			chkStyle = "checkbox";
		}
        return "goChooseMetaData";
    }
	    
	/** 获取列表数据  */
    public String goChooseMetaDataList(){
    	try {
    		//Map<String,Object> params = new HashMap<String, Object>();
    		//params.put("baseMetaDataCategory.id", id);
    		//Pager pager = baseMetaDataService.findPagerByHqlConditions(getPager(), BaseMetaData.class, params);
    		//setPager(pager);
    		
    		
    		Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(metaDataName)){
				params.put("metaDataName", "%"+metaDataName+"%");
			}
			if(id!=null){
				params.put("categoryId", id);
			}
			
			Pager pager = baseMetaDataService.findBaseMetaDataPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
        return "goChooseMetaDataList";
    }
    
    
    /** 不包括id */
    
    public String goChooseMetaData2(){
    	if(StringUtils.isEmpty(chkStyle)){
			chkStyle = "checkbox";
		}
        return "goChooseMetaData2";
    }
	    
	/** 获取列表数据  */
    public String goChooseMetaDataList2(){
    	try {
    		/*Map<String,Object> params = new HashMap<String, Object>();
    		Pager pager = baseMetaDataService.findPagerByHqlConditions(getPager(), BaseMetaData.class, params);
    		setPager(pager);*/
    		Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(metaDataName)){
				params.put("metaDataName", "%"+metaDataName+"%");
			}
			
			if(id!=null){
				params.put("categoryId", id);
			}
			Pager pager = baseMetaDataService.findBaseMetaDataPager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
        return "goChooseMetaDataList2";
    }
    
    
    
    
    /**
     * 元数据左侧的树分类
     */
    public void findTreeToJson() {
		try {
			//List<BaseMetaDataCategory> listMetaDataCate = baseMetaDataService.findAllByEntityClassNoTenantId(BaseMetaDataCategory.class);
			//List<BaseMetaDataCategory> listMetaDataCate = baseMetaDataService.findAllByEntityClass(BaseMetaDataCategory.class);
			List<BaseMetaDataCategory> listMetaDataCate = baseMetaDataCategoryService.findAllBaseMetaDataCategory();
			
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listMetaDataCate.size();
			for (int i = 0; i < count; i++) {
				BaseMetaDataCategory metaCate = listMetaDataCate.get(i);
				strSb.append("{id:\"");
				strSb.append(metaCate.getId());
				strSb.append("\",name:\"");
				strSb.append(metaCate.getCategoryName());
				strSb.append("\",isParent:false}");
					
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMetaDataName() {
		return metaDataName;
	}

	public void setMetaDataName(String metaDataName) {
		this.metaDataName = metaDataName;
	}
    
}
