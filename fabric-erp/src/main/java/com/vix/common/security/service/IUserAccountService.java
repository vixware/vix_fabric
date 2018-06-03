package com.vix.common.security.service;

import java.util.Date;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vix.common.security.entity.UserAccount;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

/**
 * @ClassName: IUserAccountService
 * @Description: 账户业务接口
 * @author wangmingchen
 * @date 2013-5-7 下午8:50:01
 *
 */
public interface IUserAccountService extends IBaseHibernateService{
	
	//UserDetails loadUserByUsername(String username, String tenantId) throws UsernameNotFoundException;
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	/**
	 * 加载用户详情 用于服务sringsecurity
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	UserAccount findUserByAccount(String userName) throws Exception;
	
	/**
	 *  加载用户详情 用于服务sringsecurity  tenantId
	 * @param userName
	 * @param tenantId
	 * @return
	 * @throws Exception
	 */
	UserAccount findUserByAccountAndTenantId(String userName,String tenantId) throws Exception;
	
	UserAccount findUserByUsernameAndPwd(String username,String password)throws UsernameNotFoundException;
	
	/**
	 * @Title: findUserByAccount4ServiceAuth
	 * @Description: 服务的账户认证
	 * @param @param userNameEncode  服务传过来加密的账号
	 * @param @param pwdEncode
	 * @param @param tenantIdEncode
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return UserAccount    返回类型
	 * @throws
	 */
	UserAccount findUserByAccount4ServiceAuth(String userNameEncode,String pwdEncode,String tenantIdEncode)throws Exception;
	
    /**
     * @Title: findUserAccountPager
     * @Description: 帐号的分页列表
     * @param @param pager
     * @param @param params
     * @param @return
     * @param @throws Exception    设定文件
     * @return Pager    返回类型
     * @throws
     */
    public Pager findUserAccountPager(Pager pager,Map<String,Object> params) throws Exception;
    
    /**
     * 保存用户信息
     * @param addRoleIds
     * @param delRoleIds
     * @param userAccount
     * @return
     * @throws Exception
     */
    public UserAccount saveOrUpdate(String addRoleIds,String delRoleIds,UserAccount userAccount)throws Exception;
    
    /**
     * @Title: findHasUserAccount
     * @Description: 验证存在重复账户
     * @param @param isEdit 是否编辑
     * @param @param userAccountId 如果是编辑时传入，账户id
     * @param @param account 账号
     * @param @param tenantId
     * @param @return
     * @param @throws Exception    设定文件
     * @return Boolean    返回类型
     * @throws
     */
    public Boolean findHasUserAccount(Boolean isEdit,String userAccountId,String account,String tenantId) throws Exception;
    /**
     * 根据Ticket得到账户信息
     * @param ticket
     * @return
     * @throws Exception
     */
    public UserAccount findUserAccountByTicket(String ticket)throws Exception;
    
    /**
     * @Title: findIsSuperAdmin
     * @Description: 判断帐号是否是承租户管理员
     * @param @param userAccountId
     * @param @return
     * @param @throws Exception    设定文件
     * @return Boolean    返回类型
     * @throws
     */
    public Boolean findIsSuperAdmin(String userAccountId)throws Exception;
    
    /**
     * @Title: update4ActiveAccount
     * @Description: 激活账号 
     * @param @param account  加密过的账号名
     * @param @param activeToken 加密过的时间戳   激活时间必须在此时间之前 
     * @param @return
     * @param @throws Exception    设定文件
     * @return Boolean    返回类型
     * @throws
     */
    public Boolean update4ActiveAccount(String encodeAccount,String encodeActiveToken)throws Exception;
    
    /**
     * @Title: update4RestPwd
     * @Description: 设置密码
     * @param @param id
     * @param @param encodePassword
     * @param @return
     * @param @throws Exception    
     * @return Boolean   
     * @throws
     */
    public Boolean update4RestPwd(String id,String encodePassword)throws Exception;
    
    /**
     * @Title: findLastUpdateAccountTime
     * @Description: 查询账号的最后更新时间
     */
    public Date findLastUpdateAccountTime(String account)throws Exception;

	UserAccount saveUserAccount(UserAccount userInfo) throws Exception;

}
