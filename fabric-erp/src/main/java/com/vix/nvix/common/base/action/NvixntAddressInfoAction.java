package com.vix.nvix.common.base.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.SortSet;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.entity.AddressInfo;
import com.vix.nvix.common.base.entity.AddressInfoBo;
import com.vix.nvix.common.base.template.ExcelTemplate;

/**
 * 地址信息
 */
@Controller
@Scope("prototype")
public class NvixntAddressInfoAction extends VixntBaseAction{

	private static final long serialVersionUID = 1L;
	
	/** 商品分类 */
	private AddressInfo addressInfo;
	private String id;
	private String parentId;
	/** 地址树是否只获取第一级 */
	private String isFirstLevel;
	/** 附件 */
	protected File fileToUpload;
	/** 附件的名称 */
	protected String fileToUploadFileName;
	
	/** 获取列表数据  */
	public void goSingleListJson(){
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			String name = getRequestParameter("name");
			if(StrUtils.isNotEmpty(name)){
				name = decode(name, "utf-8");
				params.put("name,"+SearchCondition.ANYLIKE, name.trim());
			}
			if(StrUtils.objectIsNull(parentId)){
				params.put("parentAddressInfo.id,"+SearchCondition.IS, null);
			}else{
				params.put("parentAddressInfo.id,"+SearchCondition.EQUAL, parentId);
			}
			Pager pager = getPager();
			if(StrUtils.objectIsNull(pager.getOrderField()) && StrUtils.objectIsNull(pager.getOrderBy())){
				pager.setOrderField("orderBy");
				pager.setOrderBy("asc");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, AddressInfo.class, params);
			
			String[] excludes = {"*.dataCreater","*.subAddressInfos"};
			renderDataTable(pager,excludes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StrUtils.objectIsNotNull(id)){
				addressInfo = vixntBaseService.findEntityById(AddressInfo.class, id);
			}else{
				if(StrUtils.objectIsNotNull(parentId)){
					AddressInfo ai = vixntBaseService.findEntityById(AddressInfo.class,parentId);
					if(null != ai){
						addressInfo = new AddressInfo();
						addressInfo.setParentAddressInfo(ai);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StrUtils.objectIsNotNull(addressInfo.getId())){
				isSave = false;
			}else{
				addressInfo.setCreateTime(new Date());
			}
			
			/** 检查空值对象 */
			String[] attrArray = {"parentAddressInfo"};
			checkEntityNullValue(addressInfo, attrArray);

			/** 中文首字母 */
			String aiName = addressInfo.getName();
			if(StrUtils.objectIsNotNull(aiName)){
				String pbPy = ChnToPinYin.getPYString(aiName);
				if(null != pbPy && !"".equals(pbPy)){
					String word = pbPy.toUpperCase().substring(0, 1);
					addressInfo.setFirstLetter(word);
				}
			}
			
			addressInfo = vixntBaseService.merge(addressInfo);
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
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			AddressInfo ai = vixntBaseService.findEntityById(AddressInfo.class, id);
			if(null != ai){
				if(ai.getSubAddressInfos().size()>0){
					renderText("地址包含子地址,不允许删除!");
				}else{
					vixntBaseService.deleteByEntity(ai);
					renderText(DELETE_SUCCESS);
				}
			}else{
				renderText("地址不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("地址已经被使用,不允许删除!");
		}
	}
	
	/** 载入下级地址 */
	private List<AddressInfo> subAddressInfoList;
	public String loadSubAddressInfo(){
		try {
			if(StrUtils.objectIsNotNull(parentId)){
				subAddressInfoList = vixntBaseService.findAllByEntityClassAndAttribute(AddressInfo.class, "parentAddressInfo.id", parentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return "loadSubAddressInfo";
	}
	
	/** 树形结构JSON */
	public void findTreeToJson(){
		try{
			List<AddressInfo> listAddressInfo = new ArrayList<AddressInfo>();
			/** 获取查询参数 */
			Map<String,Object> params = new HashMap<String,Object>();
			if(StrUtils.objectIsNotNull(id)){
				params.put("parentAddressInfo.id," + SearchCondition.EQUAL, id);
				listAddressInfo = vixntBaseService.findAllByConditions(AddressInfo.class, params);
			}else{
				params.put("parentAddressInfo.id," + SearchCondition.IS, null);
				listAddressInfo = vixntBaseService.findAllByConditions(AddressInfo.class, params);
			}
			SortSet ss = new SortSet("orderBy");
			Collections.sort(listAddressInfo, ss);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for(int i =0;i<listAddressInfo.size();i++){
				AddressInfo cc = listAddressInfo.get(i);
				if(cc.getSubAddressInfos().size() > 0){
					strSb.append("{id:\"");
					strSb.append(cc.getId());
					strSb.append("\",name:\"");
					strSb.append(cc.getName());
					/** 默认只获取第一级节点 */
					if(null != isFirstLevel && "1".equals(isFirstLevel)){
						strSb.append("\",open:false,isParent:false}");
					}else{
						strSb.append("\",open:false,isParent:true}");
					}
				}else{
					strSb.append("{id:\"");
					strSb.append(cc.getId());
					strSb.append("\",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:false}");
				}
				if(i < listAddressInfo.size() -1){
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String goChooseAddressInfo(){
		return "goChooseAddressInfo";
	}

	public String goChooseAddressInfoFile(){
		return "goChooseAddressInfoFile";
	}
	
	/** 导入地址信息 */
	public void importAddressInfo(){
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
			} else {
				try (InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream("addressInfo_template.xml")) {
					XLSReader reader = ReaderBuilder.buildFromXML(xmlInputStream);
					try (InputStream xlsInputStream = new FileInputStream(fileToUpload)) {
						List<AddressInfoBo> addressInfoBoList = new ArrayList<AddressInfoBo>();
						Map<String, Object> beans = new HashMap<String, Object>();
						beans.put("addressInfoBoList",addressInfoBoList);
						reader.read(xlsInputStream, beans);
						if (addressInfoBoList != null && addressInfoBoList.size() > 0) {
							for (AddressInfoBo addressInfoBo : addressInfoBoList) {
								if (addressInfoBo != null) {
									if (StringUtils.isEmpty(addressInfoBo.getCode()) || StringUtils.isEmpty(addressInfoBo.getName())) {
										return;
									}
									AddressInfo addressInfo = null;
									try {
										addressInfo = vixntBaseService.findEntityByAttributeNoTenantId(AddressInfo.class, "code", addressInfoBo.getCode());
									} catch (Exception e13) {
										e13.printStackTrace();
									}
									AddressInfo parentAddressInfo = null;
									if (addressInfo != null) {
									} else {
										addressInfo = new AddressInfo();
										if(StrUtils.isNotEmpty(addressInfoBo.getCode())){
											addressInfo.setCode(addressInfoBo.getCode());
										}
										if(StrUtils.isNotEmpty(addressInfoBo.getName())){
											addressInfo.setName(addressInfoBo.getName());
										}
										if(StrUtils.isNotEmpty(addressInfoBo.getFirstLetter())){
											addressInfo.setFirstLetter(addressInfoBo.getFirstLetter());
										}
										if(StrUtils.isNotEmpty(addressInfoBo.getOrderBy().toString())){
											addressInfo.setOrderBy(addressInfoBo.getOrderBy());
										}
										if(StrUtils.isNotEmpty(addressInfoBo.getParentCode())){
											parentAddressInfo = vixntBaseService.findEntityByAttributeNoTenantId(AddressInfo.class, "code", addressInfoBo.getParentCode());
											addressInfo.setParentAddressInfo(parentAddressInfo);
										}
										addressInfo.setCreateTime(new Date());
										addressInfo.setUpdateTime(new Date());
										initEntityBaseController.initEntityBaseAttribute(addressInfo);
										addressInfo = vixntBaseService.merge(addressInfo);
									}
									
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				IOUtils.closeQuietly(fis);
			}
		}
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIsFirstLevel() {
		return isFirstLevel;
	}

	public void setIsFirstLevel(String isFirstLevel) {
		this.isFirstLevel = isFirstLevel;
	}

	public AddressInfo getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}

	public List<AddressInfo> getSubAddressInfoList() {
		return subAddressInfoList;
	}

	public void setSubAddressInfoList(List<AddressInfo> subAddressInfoList) {
		this.subAddressInfoList = subAddressInfoList;
	}

	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}
}