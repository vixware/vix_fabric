package com.vix.common.security.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixSecAction;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.service.IRoleService;
import com.vix.common.security.service.IUserAccountService;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.SecurityConfigConstant;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;


/**
* @ClassName: UserAccountAction
* @Description: 用户的账户列表管理
* @author wangmingchen
* @date 2012-6-19 上午11:41:41
*
 */
@Controller
@Scope("request")
public class UserAccountAction extends VixSecAction {

    @Resource(name="userAccountService")
    private IUserAccountService userAccountService;
    
    @Resource(name="roleService")
    private IRoleService roleService;
    
    /** 用户账户id */
    private String id;
    
    private UserAccount entity;
    
    private UserAccount entityForm;
    
    private String account;
    
    private String password;
    private String passwordConfirm;
    
    private Role roleList;
    
    /** 是否超级管理员  或者 承租户管理员  */
    private Boolean isAdmin;
    
    /** 获取列表数据  */
    public String goSingleList(){
        try {
            Map<String,Object> params = new HashMap<String, Object>();
            /*if(userId==null){
                setPager(new Pager());
                return GO_SINGLE_LIST;
            }else{
                params.put("user.id,"+SearchCondition.EQUAL,userId);
            }*/
            
            String account = getRequestParameter("account");
            if(StringUtils.isNotEmpty(account)){
                //account = decode(account, "UTF-8");
                //params.put("account,"+SearchCondition.ANYLIKE,account);
            	params.put("account","%"+account+"%");
            }
           // params.put("id",0L);
            
            Pager pager = userAccountService.findUserAccountPager(getPager(), params);
            setPager(pager);
            
           /*测试更改公司编码
            UserAccount ua = SecurityUtil.getCurrentUserAccount();
            System.out.println("CompanyInnerCode:"+ua.getCompanyInnerCode()+";DepartmentCode:"+ua.getDepartmentCode());
            //更改
            SecuritySettingUtil.modifyCurrentUserCompanyInnerCode("111", "222");
            ua = SecurityUtil.getCurrentUserAccount();
            System.out.println("更改后 ：CompanyInnerCode:"+ua.getCompanyInnerCode()+";DepartmentCode:"+ua.getDepartmentCode());
            */
            //HibernateConfigurationHelper.getTableName(BaseMetaDataDefine.class.getName());
            /*Object aaa = ContextUtil.getSessionAttr(SecurityScope.HQL_USER_OBJ);//.setSessionAttr("aaa", 111);
            System.out.println(aaa);*/
            //Map<String,Object> paramTest = new HashMap<String, Object>();
            //paramTest.put("_id_", 0L);
            //Role rr = userAccountService.findObjectByHql("select r from Role r where 0=r.id", paramTest);
            //System.out.println("AssA"+rr.getRoleCode());
        }catch(Exception e){
            e.printStackTrace();
        }
        return GO_SINGLE_LIST;
    }
    
    /** 跳转至用户账户修改页面 */
    public String goSaveOrUpdate() {
        try {
        	if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
                entity = userAccountService.findEntityById(UserAccount.class, id);
                //Employee userEmp = entity.getEmployee();
                isAdmin = userAccountService.findIsSuperAdmin(id);
            }else{
            	entity = new UserAccount();
            	entity.setEnable("1");
            	entity.setUserResourceReadType(BizConstant.COMMON_SECURITY_USERMENUTYPE_U);
            	isAdmin = false;
            }
            
            getRequest().setAttribute("lpList", SecurityConfigConstant.SECURITY_LOGIN_PAGE_NOTE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GO_SAVE_OR_UPDATE;
    }
    
    public void checkUserAccount(){
    	Boolean isSuccess = true;
    	String message = OPER_SUCCESS;
    	try {
    		Boolean isEdit = (StrUtils.isNotEmpty(id))?true:false;
    		//SecurityUtil.getCurrentUserTenantId()
    		Boolean hasUa = userAccountService.findHasUserAccount(isEdit, id, account, null);
    		if(hasUa){
    			message = "账号重复，请重新填写！";
    			isSuccess = false;
    		}
    		handleBaseMessageRenderText(isSuccess, message, null);
    	} catch (Exception e) {
    		handleBaseMessageRenderText(false, OPER_FAIL, null);
        }
    }
    
    /** 处理修改操作  */
    public String saveOrUpdate() {
        boolean isSave = true;
        try {
            if(null != entityForm.getId()){
                isSave = false;
            }
            String addRoleIds = getRequest().getParameter("addUserAccountRoleIds");
            String deleteRoleIds = getRequest().getParameter("deleteUserAccountRoleIds");
            if(entityForm.getEmployee()==null){
            	this.setMessage("必须选择职员！");
            }else{
            	 userAccountService.saveOrUpdate(addRoleIds,deleteRoleIds,entityForm);
                 if(isSave){
                     renderText(SAVE_SUCCESS);
                 }else{
                     renderText(SAVE_SUCCESS);
                 }
            }
           
        } catch (Exception e) {
            if(isSave){
            	handleException(e, SAVE_FAIL);
            }else{
            	handleException(e, UPDATE_FAIL);
            }
        }
        return UPDATE;
    }
    
    
    /** 跳转至用户账户修改页面 */
    public String goResetPwd() {
        try {
        	if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
                entity = userAccountService.findEntityById(UserAccount.class, id);
                return "goResetPwd";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String resetPwd() {
        try {
        	if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
        		if(StringUtils.isEmpty(password) || StringUtils.isEmpty(passwordConfirm)
        				|| !password.endsWith(passwordConfirm)){
        			renderText(UPDATE_FAIL);
        		}else{
        			userAccountService.update4RestPwd(id, password);
            		renderText(SAVE_SUCCESS);
        		}
        		
            }else{
            	renderText(UPDATE_FAIL);
            }
            
        } catch (Exception e) {
        	handleException(e, UPDATE_FAIL);
        }
        return UPDATE;
    }
    
    
    
    /** 处理删除操作 */
    public String deleteById() {
        try {
            userAccountService.deleteById(UserAccount.class, id);
            renderText(DELETE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            renderText(DELETE_FAIL);
        }
        return UPDATE;
    }
    
    /**
     * @Title: goChooseRole
     * @Description: 跳转到选择角色界面 
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String goChooseRole(){
    	if(StringUtils.isEmpty(chkStyle)){
			chkStyle = "checkbox";
		}
        return "goChooseRole";
    }
    
   /**
    * 
    * @return
    */
    public String goChooseRoleList(){
    	try {
    		Map<String,Object> params =getParams();
    		Pager pager = roleService.findPagerByHqlConditions(getPager(), Role.class, params); setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
        return "goChooseRoleList";
    }
    
    
    /**
     * 
     * @return
     */
    public String goChooseUserAccount(){
        return "goChooseUserAccount";
    }
    
   /**
    * 
    * @return
    */
    public String goChooseUserAccountList(){
    	try {
    		Map<String,Object> params =getParams();
    		Pager pager = roleService.findPagerByHqlConditions(getPager(), UserAccount.class, params); setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
        return "goChooseUserAccountList";
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getRoleList() {
        return roleList;
    }

    public void setRoleList(Role roleList) {
        this.roleList = roleList;
    }

	public UserAccount getEntity() {
		return entity;
	}

	public void setEntity(UserAccount entity) {
		this.entity = entity;
	}

	public UserAccount getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(UserAccount entityForm) {
		this.entityForm = entityForm;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
    
}
