/**   
* @Title: ApplicationFormDaoImpl.java 
* @Package com.vix.contract.dao.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-24 下午1:43:44  
*/
package com.vix.purchase.apply.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.purchase.apply.dao.IPurchaseApplyDao;

/**
 * @Description: 
 * @author ivan 
 * @date 2013-07-24
 */
@Repository("purchaseApplyDao")
public class PurchaseApplyDaoImpl extends BaseHibernateDaoImpl implements IPurchaseApplyDao {

	private static final long serialVersionUID = 1L;

}
