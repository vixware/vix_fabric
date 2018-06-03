package com.vix.common.securityDra.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.common.securityDra.service.IDataResRowPolicyObjService;
import com.vix.core.utils.JSonUtils;
import com.vix.core.web.Pager;

/**
 * 数据权限配置action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class DataResRowPolicyObjSelectAction extends BaseSecAction {

	@Autowired
	private IDataResRowPolicyObjService dataResRowPolicyObjService;
	
	/** 元数据对象id */
	private String id;
	/** 元数据属性  */
	private String metadataProperty;
	
	private List<BaseMetaDataDefine> mdPropertyList ;
	
	public void checkMetadataProperty() throws Exception{
		//id=1L;
		//metadataProperty = "posName";
		BaseMetaDataDefine mdDefine = dataResRowPolicyObjService.findMddByMdId(id, metadataProperty);
		
		BaseMetaDataDefine mdDefineTmp = new BaseMetaDataDefine();
		BeanUtils.copyProperties(mdDefine, mdDefineTmp, new String[]{"baseMetaData"});
		String mdDefineJson = JSonUtils.makeBeanToJson(mdDefineTmp);
		renderJson(mdDefineJson);
		
		//Pager pager = dataResRowPolicyObjService.findCommonBizSelectList(getPager(), id);
		//System.out.println(pager);
		/*BaseMetaDataDefine mdDefineTmp = new BaseMetaDataDefine();
		String mdDefineJson = JSonUtils.makeBeanToJson(mdDefineTmp);
		renderJson(mdDefineJson);*/
	}
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			/*Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(policyName)){
				params.put("metaDataViewName,"+SearchCondition.ANYLIKE, policyName);
			}
			Pager pager = dataResRowPolicyObjService.findPolicyObjPager(getPager(), params);
			setPager(pager);*/
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	
	public String goChooseCommonBiz() {
		//if (StringUtils.isEmpty(chkStyle)) {
			chkStyle = "checkbox";
		//}
		return "goChooseCommonBiz";
	}

	/**
	 * 
	 * @return
	 */
	public String goChooseCommonBizList() {
		try {
			Map<String, Object> params = getParams();
			
			Pager pager = dataResRowPolicyObjService.findCommonBizSelectList(getPager(), id);
			setPager(pager);
			
			
			mdPropertyList = dataResRowPolicyObjService.findMdPropertyNotId(id);
			if(mdPropertyList==null){
				mdPropertyList = new ArrayList<BaseMetaDataDefine>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseCommonBizList";
	}
	    
	    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMetadataProperty() {
		return metadataProperty;
	}

	public void setMetadataProperty(String metadataProperty) {
		this.metadataProperty = metadataProperty;
	}

	public List<BaseMetaDataDefine> getMdPropertyList() {
		return mdPropertyList;
	}

	public void setMdPropertyList(List<BaseMetaDataDefine> mdPropertyList) {
		this.mdPropertyList = mdPropertyList;
	}
 

}
