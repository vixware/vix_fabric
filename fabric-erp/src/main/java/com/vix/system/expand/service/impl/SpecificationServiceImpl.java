package com.vix.system.expand.service.impl;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.utils.SortSet;
import com.vix.core.utils.StrUtils;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.nvix.wx.constant.SearchCondition;
import com.vix.system.expand.dao.ISpecificationDao;
import com.vix.system.expand.service.ISpecificationService;

@Service("specificationService")
@Transactional
public class SpecificationServiceImpl extends BaseHibernateServiceImpl implements ISpecificationService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ISpecificationDao specificationDao;

	/** 创建表  */
	@Override
	public boolean createTable(String dbType,ItemCatalog itemCatalog,String tableName) throws Exception{
		return specificationDao.createTable(dbType, itemCatalog,tableName);
	}
	
	/** 检查表是否存在 */
	@Override
	public boolean checkTableExist(String dbType,String tableName) throws Exception{
		return specificationDao.checkTableExist(dbType, tableName);
	}
	
	/** 检查表中列是否存在 */
	@Override
	public boolean checkColumnExist(String dbType,String tableName,String columnName) throws Exception{
		return specificationDao.checkColumnExist(dbType, tableName,columnName);
	}
	
	/** 删除商品扩展表  */
	@Override
	public boolean dropTable(String dbType,String tableName) throws Exception{
		return specificationDao.dropTable(dbType, tableName);
	}

	/** 添加商品扩展表字段  */
	@Override
	public boolean addTableField(String dbType,String tableName,String columnName) throws Exception {
		return specificationDao.addTableField(dbType, tableName, columnName);
	}

	/** 删除商品扩展表字段  */
	@Override
	public boolean deleteTableField(String dbType,String tableName,String columnName) throws Exception {
		return specificationDao.deleteTableField(dbType, tableName, columnName);
	}

	@Override
	public Specification mergeSpecification(Specification specification) throws Exception {
		String tableName = "";
		ItemCatalog oe = null;
		if(null != specification.getItemCatalog() && null != specification.getItemCatalog().getId()){
			oe = specificationDao.findEntityById(ItemCatalog.class, specification.getItemCatalog().getId());
			if(null != oe && oe.getSpecifications().size() > 0){
				tableName = oe.getSpecifications().iterator().next().getSpecificationTableName();
			}
		}
		if(null != tableName && !"".equals(tableName)){
			specification.setSpecificationTableName(tableName);
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
			specification.setSpecificationTableName("SPECTABLE_"+sdf.format(new Date()));
		}
		specification = specificationDao.merge(specification);
		if(null != specification.getSpecificationTableName() && !"".equals(specification.getSpecificationTableName()) && null != specification.getCode() && !"".equals(specification.getCode())){
			/**创建数据表*/
			boolean a = specificationDao.checkTableExist(PropertyConfigLoader.dbType, specification.getSpecificationTableName());
			if(!a){
				oe = specificationDao.findEntityById(ItemCatalog.class, specification.getItemCatalog().getId());
				specificationDao.createTable(PropertyConfigLoader.dbType, oe, specification.getSpecificationTableName());
			}else{
				/**添加字段(维护数据表)*/
				boolean b = specificationDao.checkColumnExist(PropertyConfigLoader.dbType, specification.getSpecificationTableName(), specification.getCode());
				if(!b){
					specificationDao.addTableField(PropertyConfigLoader.dbType, specification.getSpecificationTableName(), specification.getCode());
				}
			}
		}
		if(specificationDao.checkColumnExist(PropertyConfigLoader.dbType, specification.getSpecificationTableName(), specification.getCode())){
			if(null == specification.getIsColumnGenerate() || !"1".equals(specification.getIsColumnGenerate())){
				specification.setIsColumnGenerate("1");
				specification = specificationDao.merge(specification);
			}
		}
		return specification;
	}

	@Override
	public String findTableNameByProductCategoryId(String categoryId) throws Exception {
		if(StrUtils.objectIsNull(categoryId)){
			return null;
		}
		
		String tableName = "";
		ItemCatalog pc = this.findEntityById(ItemCatalog.class, categoryId);
		if(null != pc){
			/** 获取第一级规格表 */
			if(pc.getSpecifications().size() > 0){
				tableName = pc.getSpecifications().iterator().next().getSpecificationTableName();
				if(null != tableName && !"".equals(tableName)){
					return tableName;
				}
			}else if(null != pc.getParentItemCatalog()){
				ItemCatalog prePc = this.findEntityById(ItemCatalog.class,pc.getParentItemCatalog().getId());
				if(null != prePc){
					/** 获取第二级规格表 */
					if(prePc.getSpecifications().size() > 0){
						tableName = prePc.getSpecifications().iterator().next().getSpecificationTableName();
						if(null != tableName && !"".equals(tableName)){
							return tableName;
						}
					}else if(null != prePc.getParentItemCatalog()){
						ItemCatalog prePrePc = this.findEntityById(ItemCatalog.class,prePc.getParentItemCatalog().getId());
						if(null != prePrePc){
							/** 获取第三级规格表 */
							if(prePrePc.getSpecifications().size() > 0){
								tableName = prePrePc.getSpecifications().iterator().next().getSpecificationTableName();
								if(null != tableName && !"".equals(tableName)){
									return tableName;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public List<Specification> findSpecificationByProductCatetoryId(String categoryId, Map<String, Object> params)
			throws Exception {
		if(null == categoryId || "".equals(categoryId)){
			return null;
		}
		
		ItemCatalog pc = this.findEntityById(ItemCatalog.class, categoryId);
		if(null == pc || null == pc.getId()){
			
		}
		
		/** set 存放规格的code,用以过滤重写的规格。 */
		Set<String> specCodeSet = new HashSet<String>();
		params.put("itemCatalog.id,"+SearchCondition.EQUAL, pc.getId());
		List<Specification> sList = this.findAllByConditions(Specification.class, params);

		if(null != pc.getParentItemCatalog() && null != pc.getParentItemCatalog().getId()){
			/** 继承第二级分类下的规格 */
			ItemCatalog secondPc = pc.getParentItemCatalog();
			params.put("itemCatalog.id,"+SearchCondition.EQUAL, secondPc.getId());
			List<Specification> secondSpecList = this.findAllByConditions(Specification.class, params);
			if(null != secondSpecList && secondSpecList.size() > 0){
				for(Specification spec : secondSpecList){
					if(!specCodeSet.contains(spec.getCode())){
						specCodeSet.add(spec.getCode());
						sList.add(spec);
					}
				}
			}
			
			/** 继承第一级分类下的规格 */
			if(null != secondPc.getParentItemCatalog() && null != secondPc.getParentItemCatalog().getId()){
				ItemCatalog firstPc = secondPc.getParentItemCatalog();
				params.put("itemCatalog.id,"+SearchCondition.EQUAL, firstPc.getId());
				List<Specification> firstSpecList = this.findAllByConditions(Specification.class, params);
				if(null != firstSpecList && firstSpecList.size() > 0){
					for(Specification spec : firstSpecList){
						if(!specCodeSet.contains(spec.getCode())){
							specCodeSet.add(spec.getCode());
							sList.add(spec);
						}
					}
				}
			}
		}
	 
		return sList;
	}

	@Override
	public boolean addSpecificationGroup(String id, String productCategoryIdStr,
			List<List<SpecificationDetail>> specificationDetailList, String specData, Map<String, Object> params)
			throws Exception {
		/** 校验数据 */
		if(StrUtils.objectIsNull(id) || StrUtils.objectIsNull(productCategoryIdStr) || null == specificationDetailList || specificationDetailList.size() <= 0){
			return false;
		}
		Item ep = this.findEntityById(Item.class, id);
		if(null == ep){return false;}
		
		String specTableName = this.findTableNameByProductCategoryId(productCategoryIdStr);
		List<Specification> specList = this.findSpecificationByProductCatetoryId(productCategoryIdStr,params);
		if(StrUtils.objectIsNull(specTableName) || null == specList || specList.size() <= 0){
			return false;
		}
		SortSet scm = new SortSet("orderBy","asc");
		Collections.sort(specList, scm);

		/** 保存之前修改的规格数据 */
		if(StrUtils.objectIsNotNull(specData)){
			specificationDao.updateEcProductSpecData(ep,productCategoryIdStr,specTableName,specData);
		}
		
		boolean tag = false;
		for(List<SpecificationDetail> sdList : specificationDetailList){
			if(null == sdList || sdList.size() <= 0){
				continue;
			}
			
			Map<String,String> sdMap = new HashMap<String,String>();
			StringBuilder sdCodeBuilder = new StringBuilder();
			for(int i=0; i < specList.size(); i++){
				Specification s = specList.get(i);
				SpecificationDetail sd = sdList.get(i);
				sdMap.put(s.getCode(), sd.getName());
				/** 构建该条数据的规格明细code */
				sdCodeBuilder.append(sd.getCode());
				if(i < specList.size() - 1){
					sdCodeBuilder.append(",");
				}
			}
			
			/** 检查数据是否已经生成 */
			StringBuilder checkSqlBuilder = new StringBuilder("select id from ");
			checkSqlBuilder.append(specTableName);
			checkSqlBuilder.append(" where OBJECT_ID = '");
			checkSqlBuilder.append(id);
			checkSqlBuilder.append("' and OBJECT_TYPE = 'EcProductType' and MDM_ITEMCATALOG_ID = '");
			checkSqlBuilder.append(productCategoryIdStr);
			checkSqlBuilder.append("' ");
			
			StringBuilder sqlBuilder = new StringBuilder("insert into ");
			sqlBuilder.append(specTableName);
			sqlBuilder.append(" (ID,OBJECT_ID,OBJECT_TYPE,SPEC_CODE,MDM_ITEMCATALOG_ID,INVENTORY_COUNT,TENANTID,");
			for(int i = 0; i < specList.size(); i++){
				Specification s = specList.get(i);
				sqlBuilder.append(s.getCode());
				if(i < sdList.size() - 1){
					sqlBuilder.append(",");
				}
			}
			sqlBuilder.append(") values('");
			sqlBuilder.append(UUID.randomUUID().toString());
			sqlBuilder.append("','");
			sqlBuilder.append(id);
			sqlBuilder.append("','EcProductType','");
			//sqlBuilder.append(ep.getPlatformRole());
			/*if(null == ep.getPlatformRoleId()){
				sqlBuilder.append("',null,'");
			}else{
				sqlBuilder.append("','");
				sqlBuilder.append(ep.getPlatformRoleId());
				sqlBuilder.append("','");
			}*/
			
			sqlBuilder.append(sdCodeBuilder+"','");
			sqlBuilder.append(productCategoryIdStr+"'");
			//sqlBuilder.append("','");
			//sqlBuilder.append(getPlatformRoleId);
//			sqlBuilder.append("',");
//			sqlBuilder.append(ep.getPurchasePrice());
			sqlBuilder.append(",");
			sqlBuilder.append(ep.getPrice());
			sqlBuilder.append(",");
//			sqlBuilder.append(ep.getMobilePrice());
//			sqlBuilder.append(",");
//			sqlBuilder.append(ep.getAppPrice());
//			sqlBuilder.append(",");
			//sqlBuilder.append(ep.getMarketPrice());
//			sqlBuilder.append(",");
			//sqlBuilder.append(ep.getListedPrice());
//			sqlBuilder.append(",");
			sqlBuilder.append(ep.getCoinExchange());
			sqlBuilder.append(",");
			for(int i=0; i < sdList.size(); i++){
				if(null == sdList.get(i) || null == sdList.get(i).getSpecification()){continue;}
				
				String key = sdList.get(i).getSpecification().getCode();
				if(StrUtils.objectIsNull(key)){continue;}
				
				checkSqlBuilder.append(" and ");
				checkSqlBuilder.append(specList.get(i).getCode());
				checkSqlBuilder.append(" = '");
				checkSqlBuilder.append(sdMap.get(key) == null ? "" : sdMap.get(key));
				checkSqlBuilder.append("' ");
				
				sqlBuilder.append("'");
				sqlBuilder.append(sdMap.get(key) == null ? "" : sdMap.get(key));
				sqlBuilder.append("'");
				if(i < sdList.size() - 1){
					sqlBuilder.append(",");
				}
			}
			sqlBuilder.append(")");
			
			/** 检查数据是否已经生成 */
			List<Object> objList = this.findAllBySql(checkSqlBuilder.toString());
			if(null == objList || objList.size() <= 0){
				this.executeSql(sqlBuilder.toString(), null);
				tag = true;
			}
		}
		return tag;
	}

	private <T> List<T> findAllBySql(String sql) throws Exception{
		
		return baseHibernateDao.findAllBySql(sql,null);
	}

	@Override
	public boolean deleteSpecificationGroup(String sgId, String ecProductId, String productCategoryId)
			throws Exception {
		/** 校验数据 */
		if(StrUtils.objectIsNull(sgId) || StrUtils.objectIsNull(ecProductId) || StrUtils.objectIsNull(productCategoryId)){
			return false;
		}
		String specTableName = this.findTableNameByProductCategoryId(productCategoryId);
		if(StrUtils.objectIsNull(specTableName)){
			return false;
		}
		
		StringBuilder sqlBuilder = new StringBuilder("delete from ");
		sqlBuilder.append(specTableName);
		sqlBuilder.append(" where OBJECT_ID = '");
		sqlBuilder.append(ecProductId);
		sqlBuilder.append("' and OBJECT_TYPE = 'EcProductType' and MDM_ITEMCATALOG_ID = '");
		sqlBuilder.append(productCategoryId);
		sqlBuilder.append("' and id = '");
		sqlBuilder.append(sgId);
		sqlBuilder.append("'");
		
		this.executeSql(sqlBuilder.toString(),null);
		return true;
	}
}
