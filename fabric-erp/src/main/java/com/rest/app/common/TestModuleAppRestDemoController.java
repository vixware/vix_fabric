package com.rest.app.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.vix.common.security.entity.Module;
import com.vix.common.security.service.IModuleService;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;

/**
 * @ClassName: TestModuleAppRestController
 * @Description: App Test 服务 
 * @author wangmingchen
 * @date 2015年11月16日 下午2:16:15
 */

@Controller
@RequestMapping(value = "restService/app/common/module")
public class TestModuleAppRestDemoController extends BaseRestController{

	private static Logger logger = LoggerFactory.getLogger(TestModuleAppRestDemoController.class);

	@Resource(name="moduleService")
	private IModuleService moduleService;
	
	/**
	 * @Title: list
	 * @Description: 列表样例 
	 */
	@RequestMapping(value="findAll.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	//@ResponseBody
	public void list(HttpServletRequest request, HttpServletResponse response,
			Module param) throws Exception {
		//System.out.println("查询所有:"+SecurityUtil.getCurrentUserName());
		
		//System.out.println("Name:" + param.getName() + ";  Code :" + param.getModuleCode());
		List<Module> list = moduleService.findAllByEntityClass(Module.class);
		List<Module> resList = new ArrayList<Module>();
		for(Module module : list){
			Module moduleTmp = new Module();
			BeanUtils.copyProperties(module, moduleTmp, new String[]{"industryManagementModules"});
			resList.add(moduleTmp);
		}
		renderResult(response,resList);
	}
	
	/**
	 * @Title: list
	 * @Description: 列表样例 
	 * 分页参数 :    pageNo  第几页    pageSize  每页的数量
	 */
	@RequestMapping(value="findModulePager.rs",method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void findModulePager(HttpServletRequest request, HttpServletResponse response,
			Pager pager,
			Module module) throws Exception {
		System.out.println("条件Name:" + module.getName());
		System.out.println("分页,pageNo：" + pager.getPageNo()+",pageSize:"+pager.getPageSize());
		Map<String,Object> searchParam = new HashMap<String,Object>();
		if(StrUtils.isNotEmpty(module.getName())){
			searchParam.put("name", module.getName());
		}
		
		//如下的数据查询需自行实现
		Pager pagerRes = moduleService.findModulePager(pager, searchParam);
		System.out.println("合计:" + pagerRes.getTotalCount());
		//@SuppressWarnings("unchecked")
		//List<Module> list = pagerRes.getResultList();
		/*for(Module m : list){
			Module moduleTmp = new Module();
			BeanUtils.copyProperties(m, moduleTmp, new String[]{"industryManagementModules"});
			resList.add(moduleTmp);
		}*/
		renderResultPager(response,pagerRes);
	}
	

	/**
	 * @Title: get
	 * @Description: 单一对象查询
	 */
	@RequestMapping(value = "/query.rs", method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void get(//@PathVariable("id") String id,
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("单一查询:{}.",id);
		Module module = moduleService.findEntityById(Module.class, id);
		Module moduleTmp = null;
		if (module == null) {
			//message = "对象不存在(id:" + id + ")";
			//throw new RestException(HttpStatus.NOT_FOUND, message);
		}else{
			moduleTmp = new Module();
			BeanUtils.copyProperties(module, moduleTmp, new String[]{"industryManagementModules"});
		}
		renderResult(response,moduleTmp);
	}
	
	/**
	 * @Title: saveOrUpdate
	 * @Description: 保存  和  更新样例
	 */
	@RequestMapping(value="saveOrUpdate.rs",method = RequestMethod.POST)//, consumes = MediaTypes.JSON
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response,
							Module module) throws Exception {//@RequestBody 
		logger.info("保存.");
		System.out.println("INSAVE:" + module.getName()+"##"+module.getModuleCode());
		//验证module
		//表单验证    TODO 
		// 方式1 手动判断
		/*if(module==null){
			throw new BizException("参数不能为空");
		}
		if(StringUtils.isEmpty(module.getModuleCode())){
			throw new BizException("模块编码不能为空");
		}*/
		
		//方式2 使用简单验证器
		Map<String,String> valMsgMap = ValidatonUtil.validator(new Validator(){
				@Override
				protected void validate() {
					//String field, String errorKey, String errorMessage
					//field 对应的实体的属性， errorKey 是自定义的唯一标识，errorMessage 是错误提示
					validateRequiredString("moduleCode", "moduleCodeMsg", "请输入模块编码!");
					validateRequiredString("name", "nameMsg", "请输入模块名称!");
					//System.out.println("before:" + getMsgMap());
					//如果是自定义的特殊格式教研，请使用 addError
					addError("moduleCodeKey", "模块编码重复！");
					//System.out.println(getMsgMap());
				}
			}, module, false);
		if(!valMsgMap.isEmpty()){
			renderResultNotValid(response, valMsgMap);
			return;
		}
		//2TODO  保存和更新代码待实现
		//module = moduleService.saveRestModule(module);
		Module moduleTmp = new Module();
		BeanUtils.copyProperties(module, moduleTmp, new String[]{"industryManagementModules"});
		renderResult(response,moduleTmp);
	}
	
	/**
	 * @Title: delete
	 * @Description:  删除样例
	 */
	@RequestMapping(value = "/delete.rs", method = RequestMethod.DELETE)//或者不使用  DELETE 二是 添加value进行单独映射
	public void delete(//@PathVariable("id") String id,
							HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("删除:{}.",id);
		
		//TODO 删除代码待具体实现
		Module module = moduleService.findEntityById(Module.class, id);
		System.out.println(String.format("待删除对象id:%s.", id));
		if(module==null){
			throw new ValidateException("没有查询到待删除的模块id！");
		}else{
			//moduleService.deleteById(Module.class, id);
		}
		renderResult(response,null);
	}
	
}
