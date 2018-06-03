package com.vix.system.industrymanagement.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataCategory;
import com.vix.common.orginialMeta.service.IOrginialBaseMetaDataCategoryService;
import com.vix.common.orginialMeta.service.IOrginialBaseMetaDataService;
import com.vix.core.web.Pager;
import com.vix.system.industrymanagement.service.IIndustryManagementService;

/**
 * 元数据对象的选择
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class IndustryManagementMetaDataAction extends BaseAction{

	/** 左侧的分类id */
	private Long id;
	
	/** 元数据名称 */
	private String metaDataName;
	
	private Long industryMgtModuleId;
	
	@Autowired
	private IOrginialBaseMetaDataService baseMetaDataService;
	@Autowired
	private IOrginialBaseMetaDataCategoryService baseMetaDataCategoryService;
	
	@Autowired
	private IIndustryManagementService industryManagementService;
    
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
    		Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(metaDataName)){
				metaDataName = decode(metaDataName, "UTF-8");
				params.put("metaDataName", "%"+metaDataName+"%");
			}
			
			if(id!=null){
				params.put("categoryId", id);
			}
			params.put("industryMgtModuleId", industryMgtModuleId);
			//Pager pager = baseMetaDataService.findBaseMetaDataPager(getPager(), params);
			Pager pager = industryManagementService.findSelectMetaDataPagerByIndustryMgt(getPager(), params);
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
			List<OrginialBaseMetaDataCategory> listMetaDataCate = baseMetaDataCategoryService.findAllBaseMetaDataCategory();
			
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listMetaDataCate.size();
			for (int i = 0; i < count; i++) {
				OrginialBaseMetaDataCategory metaCate = listMetaDataCate.get(i);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMetaDataName() {
		return metaDataName;
	}

	public void setMetaDataName(String metaDataName) {
		this.metaDataName = metaDataName;
	}

	public Long getIndustryMgtModuleId() {
		return industryMgtModuleId;
	}

	public void setIndustryMgtModuleId(Long industryMgtModuleId) {
		this.industryMgtModuleId = industryMgtModuleId;
	}
    
}
