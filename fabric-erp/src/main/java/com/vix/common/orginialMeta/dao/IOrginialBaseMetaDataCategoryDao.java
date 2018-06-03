package com.vix.common.orginialMeta.dao;

import java.util.List;

import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataCategoryImpVo;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IOrginialBaseMetaDataCategoryDao extends IBaseHibernateDao {

	/**
	 * 导入元数据分类
	 * @param voList
	 * @throws Exception
	 */
	void saveForImportBaseMetaDataCategory(List<OrginialBaseMetaDataCategoryImpVo> voList)throws Exception;
}
