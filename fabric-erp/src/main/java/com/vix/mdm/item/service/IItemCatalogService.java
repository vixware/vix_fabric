package com.vix.mdm.item.service;

import java.io.File;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;


public interface IItemCatalogService extends IBaseHibernateService{

	public int importProductCategory(File file, String fileName, String companyCode) throws Exception;
}
