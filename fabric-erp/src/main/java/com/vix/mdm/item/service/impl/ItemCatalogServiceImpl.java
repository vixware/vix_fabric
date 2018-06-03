package com.vix.mdm.item.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.mdm.item.dao.IItemCatalogDao;
import com.vix.mdm.item.service.IItemCatalogService;

@Service("itemCatelogService")
public class ItemCatalogServiceImpl extends BaseHibernateServiceImpl implements IItemCatalogService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	IItemCatalogDao itemCatalogDao;

	@Override
	public int importProductCategory(File file, String fileName,String companyCode) throws Exception {
		return itemCatalogDao.importProductCategory(file, fileName, companyCode);
	}

}