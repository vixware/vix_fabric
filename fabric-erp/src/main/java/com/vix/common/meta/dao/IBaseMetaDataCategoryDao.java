package com.vix.common.meta.dao;

import java.util.List;

import com.vix.common.meta.vo.BaseMetaDataCategoryImpVo;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IBaseMetaDataCategoryDao extends IBaseHibernateDao {

	/**
	 * 导入元数据分类
	 * @param voList
	 * @throws Exception
	 */
	void saveForImportBaseMetaDataCategory(List<BaseMetaDataCategoryImpVo> voList)throws Exception;
}
