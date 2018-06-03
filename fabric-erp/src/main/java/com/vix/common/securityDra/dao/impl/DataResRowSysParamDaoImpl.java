package com.vix.common.securityDra.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.vix.common.security.entity.DataResRowSystemParams;
import com.vix.common.securityDra.dao.IDataResRowSysParamDao;
import com.vix.common.securityDra.vo.row.DataResRowSystemParamsVO;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

/**
 * 行集权限配置
 * 
 * @author Administrator
 *
 */
@Repository("dataResRowSysParamDao")
public class DataResRowSysParamDaoImpl extends BaseHibernateDaoImpl implements IDataResRowSysParamDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7225332209161242653L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.securityDra.dao.IDataResRowSysParamDao#findResRowSysParams
	 * ()
	 */
	@Override
	public List<DataResRowSystemParamsVO> findResRowSysParams() throws Exception {
		List<DataResRowSystemParams> resList = findAllByEntityClassNoTenantId(DataResRowSystemParams.class);

		List<DataResRowSystemParamsVO> resVoList = new ArrayList<DataResRowSystemParamsVO>();
		for (DataResRowSystemParams res : resList) {
			DataResRowSystemParamsVO vo = new DataResRowSystemParamsVO();

			String metaDataName = res.getBaseMetaData().getMetaDataName();
			BeanUtils.copyProperties(res, vo, new String[]{"baseMetaData"});
			vo.setBaseMetaDataClassName(metaDataName);

			resVoList.add(vo);
		}
		return resVoList;

	}
}
