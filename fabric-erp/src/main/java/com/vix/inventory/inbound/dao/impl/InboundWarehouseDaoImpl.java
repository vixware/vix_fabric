/**
 * 
 */
package com.vix.inventory.inbound.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.inventory.inbound.dao.IInboundWarehouseDao;

/**
 * @author zhanghaibing
 * 
 * @date 2013-7-23
 */
@Transactional
@Repository("inboundWarehouseDao")
public class InboundWarehouseDaoImpl extends BaseHibernateDaoImpl implements IInboundWarehouseDao {
	private static final long serialVersionUID = -2819846803117610680L;

}
