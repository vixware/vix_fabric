package com.vix.front.base.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.service.IUserAccountService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrderItem;
import com.vix.nvix.common.base.dao.IVixntBaseDao;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Controller
@Scope("request")
public class FabricIndexAction extends FabricBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private IVixntBaseService vixntBaseService;
	@Autowired
	private IVixntBaseDao vixntBaseDao;

	private String id;
	private String key;
	// 账号
	private String account;
	// 密码
	private String password;
	private Double shoppingCartCount;
	private UserAccount userAccount;
	private Employee employee;
	private List<StoreItem> storeItemList;
	private List<Item> itemList;

	/*
	 * 跳转到登录页
	 */
	public String goLogin() {
		String url = getRequest().getRequestURL().toString();
		String osName = System.getProperty("os.name");
		if (StringUtils.isNotEmpty(osName) && osName.startsWith("Windows") && url.contains("http://www.vixware.cn")) {
			System.out.println("IP:" + getIpAddr(getRequest()) + "OSNAME:" + osName);
			//return null;
		}
		String openId = getRequestParameter("openId");
		String source = getRequestParameter("source");
		String error = getRequestParameter("error");

		if ("logout".equals(source) || "true".equals(error)) {
		} else {
			// 获得当前方法名
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			if (StringUtils.isEmpty(openId)) {
				if (url.contains("http://www.vixware.cn")) {
					checkWxVisitUser(method);
				}
			} else {
				System.out.println("method:" + method + " openId:" + openId);
				getSession().setAttribute("openId", openId);
			}
		}
		return "goLogin";
	}

	/*
	 * 处理登录
	 */
	public String login() {
		if (account != null && password != null) {
			return "goIndex";
		}
		return "goLogin";
	}

	/*
	 * 跳转到忘记密码
	 */
	public String goForgetPassword() {
		return "goForgetPassword";
	}

	/*
	 * 跳转到注册
	 */
	public String goRegister() {
		return "goRegister";
	}

	/** 注册 */
	public void register() {
		try {
			if (null != userAccount) {
				if (checkUserAccountIsExists(userAccount.getAccount())) {
					renderText("0:该手机号已经被注册使用了,请更换手机号");
				} else {
					userAccount.setCreateTime(new Date());
					userAccount.setUpdateTime(new Date());
					userAccountService.mergeOriginal(userAccount);
					renderText("1:" + SAVE_SUCCESS);
				}
			} else {
				renderText("0:" + SAVE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:" + SAVE_FAIL);
		}
	}

	private boolean checkUserAccountIsExists(String account) {
		boolean isExists = false;
		try {
			if (StrUtils.isNotEmpty(account)) {
				UserAccount userAccount = userAccountService.findEntityByAttributeNoTenantId(UserAccount.class, "account", account);
				if (userAccount != null) {
					isExists = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isExists;
	}

	/*
	 * 跳转到首页
	 */
	public String goIndex() {
		try {
			// 初始化微信参数
			dealWechatTicket();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndex";
	}

	/*
	 * 跳转到搜索页
	 */
	public String goSearch() {
		// 计算购物车数量
		initShoppingCartCount();
		return "goSearch";
	}
	
	/*
	 * 搜索商品
	 */
	public String searchProduct(){
		
		return "searchProduct";
	}

	/*
	 * 计算购物车数量
	 */
	private void initShoppingCartCount() {
		try {
			shoppingCartCount = 0d;
			Map<String, Object> orderParams = new HashMap<String, Object>();
			employee = getEmployee();
			orderParams.put("employee.id," + SearchCondition.EQUAL, employee.getId());
			orderParams.put("isTemp," + SearchCondition.EQUAL, "1");
			List<RequireGoodsOrder> requireGoodsOrders = vixntBaseService.findAllDataByConditions(RequireGoodsOrder.class, orderParams);
			for (RequireGoodsOrder requireGoodsOrder : requireGoodsOrders) {
				if (requireGoodsOrder != null) {
					List<RequireGoodsOrderItem> requireGoodsOrderItems = vixntBaseService.findAllByEntityClassAndAttribute(RequireGoodsOrderItem.class, "requireGoodsOrder.id", requireGoodsOrder.getId());
					for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItems) {
						shoppingCartCount += requireGoodsOrderItem.getAmount();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Employee getEmployee(){
		Employee emp = null;
		try{
			String empId = SecurityUtil.getCurrentEmpId();
			if(empId!=null){
				emp =  vixntBaseService.findEntityById(Employee.class, empId);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return emp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public List<StoreItem> getStoreItemList() {
		return storeItemList;
	}

	public void setStoreItemList(List<StoreItem> storeItemList) {
		this.storeItemList = storeItemList;
	}

	public Double getShoppingCartCount() {
		return shoppingCartCount;
	}

	public void setShoppingCartCount(Double shoppingCartCount) {
		this.shoppingCartCount = shoppingCartCount;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}