package com.vix.crm.customer.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerAccountGroup;
import com.vix.mdm.crm.entity.CustomerShare;
import com.vix.mdm.crm.service.ICustomerAccountService;

@Controller
@Scope("prototype")
public class CustomerAccountShareAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICustomerAccountService customerAccountService;

	private List<CustomerAccountGroup> customerAccountGroupList;
	private String type; //share:共享 move：转移
	
	public String goCustomerAccountShare(){
		try{
			if(null != type && type.equals("share")){
				customerAccountGroupList = customerAccountService.findAllByEntityClass(CustomerAccountGroup.class);
			}
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		return "goCustomerAccountShare";
	}

	public String batchMove(){
		try {
			String ids = getRequestParameter("ids");
			String empRead = getRequestParameter("empRead");String[] empReadArray = empRead.split(",");
			String empWrite = getRequestParameter("empWrite");String[]empWriteArray = empWrite.split(",");
			String userGroupRead = getRequestParameter("userGroupRead");String[] userGroupReadArray = userGroupRead.split(",");
			String userGroupWrite = getRequestParameter("userGroupRead");String[] userGroupWriteArray = userGroupWrite.split(",");
			List<CustomerShare> csList = new ArrayList<CustomerShare>();
			for(int i = 0; i < empReadArray.length ;i++){
				csList.add(new CustomerShare());
			}
			/** 处理职员对客户的读取权限 */
			for(int i = 0; i < empReadArray.length ;i++){
				String temp = empReadArray[i];
				if(null != temp && !"".equals(temp)){
					String[] iv = temp.split(":");
					CustomerShare cs = csList.get(i);
					if(null != cs){
						if(null == cs.getEmployee()){
							cs.setEmployee(new Employee());
							cs.getEmployee().setId(iv[0]);
						}
						cs.setRead(iv[1]);
					}
				}
			}
			/** 处理职员对客户的写入权限 */
			for(int i = 0; i < empWriteArray.length ;i++){
				String temp = empWriteArray[i];
				if(null != temp && !"".equals(temp)){
					String[] iv = temp.split(":");
					CustomerShare cs = csList.get(i);
					if(null != cs){
						if(null == cs.getEmployee()){
							cs.setEmployee(new Employee());
							cs.getEmployee().setId(iv[0]);
						}
						cs.setWrite(iv[1]);
					}
				}			
			}
			List<CustomerShare> needRemoveCs = new ArrayList<CustomerShare>();
			for(int i = 0; i < csList.size() ;i++){
				CustomerShare cs = csList.get(i);
				if(null == cs.getEmployee() || ("0".equals(cs.getRead()) && "0".equals(cs.getWrite()))){
					needRemoveCs.add(cs);
				}
			}
			csList.removeAll(needRemoveCs);
			/** 设置权限涉及的客户，通过职员id和客户id更新权限值，不存在的权限则新增 */
			for(String idStr : ids.split(",")){
				if(null != idStr && !"".equals(idStr)){
					for(int i = 0; i < csList.size() ;i++){
						CustomerShare cs = csList.get(i);
						cs.setCustomerAccount(new CustomerAccount());
						cs.getCustomerAccount().setId(idStr);
						Map<String,Object> params = getParams();
						params.put("customerAccount.id,"+SearchCondition.EQUAL,cs.getCustomerAccount().getId());
						params.put("employee.id,"+SearchCondition.EQUAL,cs.getEmployee().getId());
						List<CustomerShare> tempList = customerAccountService.findAllByConditions(CustomerShare.class, params);
						if(null != tempList && tempList.size() > 0){
							CustomerShare tempCs = tempList.get(0);
							if(null != tempCs){
								if("1".equals(cs.getRead())){
									tempCs.setRead(cs.getRead());
								}
								if("1".equals(cs.getWrite())){
									tempCs.setWrite(cs.getWrite());
								}
								customerAccountService.merge(tempCs);
							}
						}else{
							customerAccountService.merge(cs);
						}
					}
				}
			}
			String currentEmpId = getEmployee().getId();
			/** 客户转移需要删除当前用户对选中客户的权限 */
			if(null != currentEmpId && !currentEmpId.equals("") && !currentEmpId.equals("0")){
				List<CustomerShare> csCurrentEmpList = new ArrayList<CustomerShare>();
				for(String idStr : ids.split(",")){
					if(null != idStr && !"".equals(idStr)){
						Map<String,Object> params = getParams();
						params.put("customerAccount.id,"+SearchCondition.EQUAL, Long.parseLong(idStr));
						params.put("employee.id,"+SearchCondition.EQUAL,currentEmpId);
						List<CustomerShare> tempList = customerAccountService.findAllByConditions(CustomerShare.class, params);
						if(null != tempList && tempList.size() > 0){
							csCurrentEmpList.addAll(tempList);
						}
					}
				}
				for(CustomerShare cs : csCurrentEmpList){
					customerAccountService.deleteByEntity(cs);
				}
			}
			
			/** 处理客户组读写权限 */
			List<CustomerShare> csCList = new ArrayList<CustomerShare>();
			for(int i = 0; i < userGroupReadArray.length ;i++){
				csCList.add(new CustomerShare());
			}
			/** 处理客户组对客户的读取权限 */
			for(int i = 0; i < userGroupReadArray.length ;i++){
				String temp = userGroupReadArray[i];
				if(null != temp && !"".equals(temp)){
					String[] iv = temp.split(":");
					CustomerShare cs = csCList.get(i);
					if(null != cs){
						if(null == cs.getEmployee()){
							cs.setCustomerAccountGroup(new CustomerAccountGroup());
							cs.getCustomerAccountGroup().setId(iv[0]);
						}
						cs.setWrite(iv[1]);
					}
				}
			}
			/** 处理客户组对客户的写入权限 */
			for(int i = 0; i < userGroupWriteArray.length ;i++){
				String temp = userGroupWriteArray[i];
				if(null != temp && !"".equals(temp)){
					String[] iv = temp.split(":");
					CustomerShare cs = csCList.get(i);
					if(null != cs){
						if(null == cs.getEmployee()){
							cs.setCustomerAccountGroup(new CustomerAccountGroup());
							cs.getCustomerAccountGroup().setId(iv[0]);
						}
						cs.setWrite(iv[1]);
					}
				}
			}
			List<CustomerShare> needRemoveCCs = new ArrayList<CustomerShare>();
			for(int i = 0; i < csCList.size() ;i++){
				CustomerShare cs = csCList.get(i);
				if(null == cs.getCustomerAccountGroup() || ("0".equals(cs.getRead()) && "0".equals(cs.getWrite()))){
					needRemoveCCs.add(cs);
				}
			}
			csCList.removeAll(needRemoveCCs);
			for(String idStr : ids.split(",")){
				if(null != idStr && !"".equals(idStr)){
					for(int i = 0; i < csCList.size() ;i++){
						CustomerShare cs = csCList.get(i);
						cs.setCustomerAccount(new CustomerAccount());
						cs.getCustomerAccount().setId(idStr);
						Map<String,Object> params = getParams();
						params.put("customerAccount.id,"+SearchCondition.EQUAL,cs.getCustomerAccount().getId());
						params.put("customerAccountGroup.id,"+SearchCondition.EQUAL,cs.getCustomerAccountGroup().getId());
						List<CustomerShare> tempList = customerAccountService.findAllByConditions(CustomerShare.class, params);
						if(null != tempList && tempList.size() > 0){
							CustomerShare tempCs = tempList.get(0);
							if(null != tempCs){
								if("1".equals(cs.getRead())){
									tempCs.setRead(cs.getRead());
								}
								if("1".equals(cs.getWrite())){
									tempCs.setWrite(cs.getWrite());
								}
								customerAccountService.merge(tempCs);
							}
						}else{
							customerAccountService.merge(cs);
						}
					}
				}
			}
			setMessage("转移成功!");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("转移失败!");
		}
		return UPDATE;
	}
	
	public String batchShare(){
		try {
			String ids = getRequestParameter("ids");
			String empRead = getRequestParameter("empRead");String[] empReadArray = empRead.split(",");
			String empWrite = getRequestParameter("empWrite");String[]empWriteArray = empWrite.split(",");
			String userGroupRead = getRequestParameter("userGroupRead");String[] userGroupReadArray = userGroupRead.split(",");
			String userGroupWrite = getRequestParameter("userGroupRead");String[] userGroupWriteArray = userGroupWrite.split(",");
			/** 处理职员读写权限 */
			List<CustomerShare> csEList = new ArrayList<CustomerShare>();
			for(int i = 0; i < empReadArray.length ;i++){
				csEList.add(new CustomerShare());
			}
			for(int i = 0; i < empReadArray.length ;i++){
				String temp = empReadArray[i];
				if(null != temp && !"".equals(temp)){
					String[] iv = temp.split(":");
					CustomerShare cs = csEList.get(i);
					if(null != cs){
						if(null == cs.getEmployee()){
							cs.setEmployee(new Employee());
							cs.getEmployee().setId(iv[0]);
						}
						cs.setRead(iv[1]);
					}
				}
			}
			for(int i = 0; i < empWriteArray.length ;i++){
				String temp = empWriteArray[i];
				if(null != temp && !"".equals(temp)){
					String[] iv = temp.split(":");
					CustomerShare cs = csEList.get(i);
					if(null != cs){
						if(null == cs.getEmployee()){
							cs.setEmployee(new Employee());
							cs.getEmployee().setId(iv[0]);
						}
						cs.setWrite(iv[1]);
					}
				}			
			}
			List<CustomerShare> needRemoveECs = new ArrayList<CustomerShare>();
			for(int i = 0; i < csEList.size() ;i++){
				CustomerShare cs = csEList.get(i);
				if(null == cs.getEmployee() || ("0".equals(cs.getRead()) && "0".equals(cs.getWrite()))){
					needRemoveECs.add(cs);
				}
			}
			csEList.removeAll(needRemoveECs);
			for(String idStr : ids.split(",")){
				if(null != idStr && !"".equals(idStr)){
					for(int i = 0; i < csEList.size() ;i++){
						CustomerShare cs = csEList.get(i);
						cs.setCustomerAccount(new CustomerAccount());
						cs.getCustomerAccount().setId(idStr);
						Map<String,Object> params = getParams();
						params.put("customerAccount.id,"+SearchCondition.EQUAL,cs.getCustomerAccount().getId());
						params.put("employee.id,"+SearchCondition.EQUAL,cs.getEmployee().getId());
						List<CustomerShare> tempList = customerAccountService.findAllByConditions(CustomerShare.class, params);
						if(null != tempList && tempList.size() > 0){
							CustomerShare tempCs = tempList.get(0);
							if(null != tempCs){
								if("1".equals(cs.getRead())){
									tempCs.setRead(cs.getRead());
								}
								if("1".equals(cs.getWrite())){
									tempCs.setWrite(cs.getWrite());
								}
								customerAccountService.merge(tempCs);
							}
						}else{
							customerAccountService.merge(cs);
						}
					}
				}
			}
			/** 处理客户组读写权限 */
			List<CustomerShare> csCList = new ArrayList<CustomerShare>();
			for(int i = 0; i < userGroupReadArray.length ;i++){
				csCList.add(new CustomerShare());
			}
			for(int i = 0; i < userGroupReadArray.length ;i++){
				String temp = userGroupReadArray[i];
				if(null != temp && !"".equals(temp)){
					String[] iv = temp.split(":");
					CustomerShare cs = csCList.get(i);
					if(null != cs){
						if(null == cs.getEmployee()){
							cs.setCustomerAccountGroup(new CustomerAccountGroup());
							cs.getCustomerAccountGroup().setId(iv[0]);
						}
						cs.setWrite(iv[1]);
					}
				}
			}
			for(int i = 0; i < userGroupWriteArray.length ;i++){
				String temp = userGroupWriteArray[i];
				if(null != temp && !"".equals(temp)){
					String[] iv = temp.split(":");
					CustomerShare cs = csCList.get(i);
					if(null != cs){
						if(null == cs.getEmployee()){
							cs.setCustomerAccountGroup(new CustomerAccountGroup());
							cs.getCustomerAccountGroup().setId(iv[0]);
						}
						cs.setWrite(iv[1]);
					}
				}
			}
			List<CustomerShare> needRemoveCCs = new ArrayList<CustomerShare>();
			for(int i = 0; i < csCList.size() ;i++){
				CustomerShare cs = csCList.get(i);
				if(null == cs.getCustomerAccountGroup() || ("0".equals(cs.getRead()) && "0".equals(cs.getWrite()))){
					needRemoveCCs.add(cs);
				}
			}
			csCList.removeAll(needRemoveCCs);
			for(String idStr : ids.split(",")){
				if(null != idStr && !"".equals(idStr)){
					for(int i = 0; i < csCList.size() ;i++){
						CustomerShare cs = csCList.get(i);
						cs.setCustomerAccount(new CustomerAccount());
						cs.getCustomerAccount().setId(idStr);
						Map<String,Object> params = getParams();
						params.put("customerAccount.id,"+SearchCondition.EQUAL,cs.getCustomerAccount().getId());
						params.put("customerAccountGroup.id,"+SearchCondition.EQUAL,cs.getCustomerAccountGroup().getId());
						List<CustomerShare> tempList = customerAccountService.findAllByConditions(CustomerShare.class, params);
						if(null != tempList && tempList.size() > 0){
							CustomerShare tempCs = tempList.get(0);
							if(null != tempCs){
								if("1".equals(cs.getRead())){
									tempCs.setRead(cs.getRead());
								}
								if("1".equals(cs.getWrite())){
									tempCs.setWrite(cs.getWrite());
								}
								customerAccountService.merge(tempCs);
							}
						}else{
							customerAccountService.merge(cs);
						}
					}
				}
			}
			setMessage("共享成功!");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("共享失败!");
		}
		return UPDATE;
	}
	
	public List<CustomerAccountGroup> getCustomerAccountGroupList() {
		return customerAccountGroupList;
	}

	public void setCustomerAccountGroupList(
			List<CustomerAccountGroup> customerAccountGroupList) {
		this.customerAccountGroupList = customerAccountGroupList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
