package com.vix.common.securityDca.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.common.meta.entity.BaseMetaDataExtend;
import com.vix.common.security.entity.DataResColConfig;
import com.vix.common.security.entity.DataResColPolicy;
import com.vix.common.securityDca.service.IDataColSecService;
import com.vix.common.securityDca.vo.ColViewStatus;
import com.vix.common.securityDca.vo.MetaDataProperty;
import com.vix.core.utils.JSonUtils;
import com.vix.core.web.Pager;

/**
 * 列级权限的具体配置
 * @author Administrator
 *
 */
@Controller
@Scope("request")
public class DataColPolicyAction extends BaseSecAction {

	@Autowired
	private IDataColSecService dataColSecService;
	
	/** 配置id  */
	private String dataConfigId;
	
	/** 列级权限配置 的 id */
	private String policyId;
	
	/** 元数据类的主键  */
	private String id;
	
	private List<DataResColConfig> dataColConfigList;
	
	private List<MetaDataProperty> metaDataPropertyList;
	
	private Map<String,DataResColPolicy> plyMap;
	
	private List<String> jsonParam;
	
	/**
	 * 覆盖父类方法  加载选择列表数据
	 */
	@Override
	public String goList(){
		try {
			dataColConfigList = dataColSecService.findAllByEntityClass(DataResColConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			if(policyId!=null){
				//params.put
			}
			Pager pager = dataColSecService.findMetaDataPagerByHqlConditions(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			
			/*if(null != id && id.StringValue() > 0){
				entity = dataColSecService.findEntityById(DataResColConfig.class, id);
				Organization orgTmp = organizationService.findOrganizationByCompCode(entity.getCompanyCode());
				parentId = orgTmp.getId();
				parentTreeName=orgTmp.getOrgName();
				
			}else{
				entity=new DataResColConfig();
				entity.setFlag(1);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public String goSaveOrUpdateData() {
		metaDataPropertyList = new ArrayList<MetaDataProperty>();
		StringBuffer sb = new StringBuffer();
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0") && StringUtils.isNotEmpty(dataConfigId)){//if(id!=null && id>0 && dataConfigId!=null){
				//System.out.println("id:"+id+",dataConfigId:"+dataConfigId);
				
				List<BaseMetaDataDefine> baseMdList = new LinkedList<BaseMetaDataDefine>();
				List<BaseMetaDataExtend> baseExtMdList = new LinkedList<BaseMetaDataExtend>();
				
				BaseMetaData bmd = dataColSecService.findMetaDataDefine(id);
				baseMdList.addAll(bmd.getBaseMetaDataDefines());
				baseExtMdList.addAll(bmd.getBaseMetaDataExtends());
				
				
				List<DataResColPolicy> drpList = dataColSecService.findDataColPolicyList(dataConfigId, id);
				plyMap = new HashMap<String, DataResColPolicy>();
				for(DataResColPolicy ply:drpList){
					BaseMetaDataDefine md = ply.getBaseMetaDataDefine();
					BaseMetaDataExtend me = ply.getBaseMetaDataExtend();
					
					String mdd_mde_id = "";
					if(md!=null){
						mdd_mde_id = md.getId()+"|D";
					}else if(me!=null){
						mdd_mde_id = me.getId()+"|E";
					}
					
					if(StringUtils.isNotEmpty(mdd_mde_id)){
						plyMap.put(mdd_mde_id, ply);
					}
				}
				
				for(BaseMetaDataDefine mdd:baseMdList){
					String mddIdTmp = mdd.getId()+"|D";
					MetaDataProperty mp = new MetaDataProperty();
					if(plyMap.containsKey(mddIdTmp)){
						DataResColPolicy dp=plyMap.get(mddIdTmp);
						BeanUtils.copyProperties(dp, mp);
						//mp.setMdpId(mddIdTmp);
					}else{
						DataResColPolicy dp=new DataResColPolicy();
						BeanUtils.copyProperties(dp, mp);
						mp.setViewListStatus("Y");
						mp.setViewDetailStatus("Y");
					}
					mp.setMdpId( mdd.getId());
					mp.setType("D");
					mp.setPropertyName(mdd.getPropertyName());
					metaDataPropertyList.add(mp);
					//metaDataPropertyList.add(new MetaDataProperty(mdd.getId()+"|D", mdd.getPropertyName()));
				}
				
				for(BaseMetaDataExtend mde:baseExtMdList){
					String mdeIdTmp = mde.getId()+"|E";
					MetaDataProperty mp = new MetaDataProperty();
					if(plyMap.containsKey(mdeIdTmp)){
						DataResColPolicy dp=plyMap.get(mdeIdTmp);
						BeanUtils.copyProperties(dp, mp);
						//mp.setMdpId(mdeIdTmp);
					}else{
						DataResColPolicy dp=new DataResColPolicy();
						BeanUtils.copyProperties(dp, mp);
						mp.setViewListStatus("Y");
						mp.setViewDetailStatus("Y");
					}
					mp.setMdpId(mde.getId());
					mp.setType("E");
					mp.setPropertyName(mde.getBoName());
					metaDataPropertyList.add(mp);
					//metaDataPropertyList.add(new MetaDataProperty(mde.getId()+"|E", mde.getBoName()));
				}
			}
			//sb.append(new JSONSerializer().exclude("class").serialize(metaDataPropertyList));//.exclude(exludeExpression)
			Collections.sort(metaDataPropertyList, new Comparator<MetaDataProperty>() {
				@Override
				public int compare(MetaDataProperty o1, MetaDataProperty o2) {
					//TODO CODE
					//if(o1.getMdpId()>o2.getMdpId()){
					if(o1.getMdpId().compareTo(o2.getMdpId())>0){
						return 1;
					}else if(o1.getMdpId().compareTo(o2.getMdpId())<0){//if(o1.getMdpId()<o2.getMdpId()){
						return -1;
					}
					return 0;
				}
			});
			renderHtml(convertListToJson(metaDataPropertyList, metaDataPropertyList.size(),"dataResColConfig","baseMetaData","baseMetaDataDefine","baseMetaDataExtend"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 
	 * @return
	 */
	public String findViewStatus(){
		List<ColViewStatus> viewList = new LinkedList<ColViewStatus>();
		viewList.add(new ColViewStatus("YES","可见"));
		viewList.add(new ColViewStatus("NO","不可见"));
		String statusStr = JSonUtils.toJSon(viewList);

		renderHtml(viewList.toString());
		return null;
	}
	

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			/*if (dataColSecService.isEntityExist(DataResColConfig.class,entityForm.getId(), "companyCode", entityForm.getCompanyCode())) {
				setMessage("公司编码已经存在");
				return "update";
			}
			if (dataColSecService.isEntityExist(DataResColConfig.class,entityForm.getId(), "rangeScope", entityForm.getRangeScope())) {
				setMessage("公司编码已经存在");
				return "update";
			}
			if(parentId==null){
				setMessage("没有选择公司！");
				return "update";
			}
			
			if(null != entityForm.getId()){
				isSave = false;
			}
			Organization org = dataColSecService.findEntityById(Organization.class, parentId);
			entityForm.setCompanyCode(org.getCompanyCode());
			dataColSecService.merge(entityForm);*/
			List<MetaDataProperty> allList = new LinkedList<MetaDataProperty>();
			for (String crvStr : jsonParam) {
				//String str1 = crvStr.trim().replace("\n", "").replace("\\", "\\\\").replace("\"", "\\\"");
				//String str = str1.replace("'", "\"");
				MetaDataProperty mdp = JSonUtils.readValue(crvStr, MetaDataProperty.class);
				allList.add(mdp);
			}
			if(id!=null && dataConfigId!=null){
				dataColSecService.saveColPly(id, dataConfigId, allList);
			}
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(SAVE_SUCCESS);
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
			//dataColSecService.deleteById(DataResColConfig.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DataResColConfig> getDataColConfigList() {
		return dataColConfigList;
	}

	public void setDataColConfigList(List<DataResColConfig> dataColConfigList) {
		this.dataColConfigList = dataColConfigList;
	}

	public String getDataConfigId() {
		return dataConfigId;
	}

	public void setDataConfigId(String dataConfigId) {
		this.dataConfigId = dataConfigId;
	}

	public List<MetaDataProperty> getMetaDataPropertyList() {
		return metaDataPropertyList;
	}

	public void setMetaDataPropertyList(List<MetaDataProperty> metaDataPropertyList) {
		this.metaDataPropertyList = metaDataPropertyList;
	}

	public Map<String, DataResColPolicy> getPlyMap() {
		return plyMap;
	}

	public void setPlyMap(Map<String, DataResColPolicy> plyMap) {
		this.plyMap = plyMap;
	}

	public List<String> getJsonParam() {
		return jsonParam;
	}

	public void setJsonParam(List<String> jsonParam) {
		this.jsonParam = jsonParam;
	}

}
