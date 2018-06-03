package com.vix.mdm.item.dao;

import java.io.File;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IItemCatalogDao extends IBaseHibernateDao {

	public int importProductCategory(File file, String fileName, String companyCode) throws Exception;

}
