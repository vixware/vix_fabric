package com.vix.common.security.dao;

import java.util.Map;

import com.vix.common.security.entity.UserAccountProxyApply;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.web.Pager;

/**
 * @ClassName: IUserAccountProxyDao
 * @Description: 账户代理DAO
 * @author wangmingchen
 * @date 2015年2月15日 下午2:13:03
 */
public interface IUserAccountProxyDao extends IBaseHibernateDao {

	/**
	 * @Title: findAcceptUserAccountProxyPager
	 * @Description: 查询 代理请求 是发给 当前登陆人  的
	 * @param @param pager
	 * @param @param params
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Pager    返回类型
	 * @throws
	 */
	public Pager findAcceptUserAccountProxyPager(Pager pager, Map<String, Object> params) throws Exception;
	
	/**
	 * @Title: findApplyUserAccountUserAccountProxyPager
	 * @Description: 查询 登陆人 发送给别人的请求（代理）
	 * @param @param pager
	 * @param @param params
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Pager    返回类型
	 * @throws
	 */
	public Pager findApplyUserAccountProxyPager(Pager pager, Map<String, Object> params) throws Exception;
	
	/**
	 * @Title: saveOrUpdateProxyConfig
	 * @Description: 保存代理信息
	 * @param @param userAccountProxyApply
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void saveOrUpdateProxyConfig(UserAccountProxyApply userAccountProxyApply) throws Exception;
}
