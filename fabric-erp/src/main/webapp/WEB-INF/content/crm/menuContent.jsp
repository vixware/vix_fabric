<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li><a href="#" onclick="loadContent('${vix}/crm/agenda/dailyAction!goList.action');"><img src="${vix}/common/img/crm/daily.png" alt="" />新增日报</a></li>
<li><a href="#" onclick="fastCreateCustomer();"><img src="${vix}/common/img/crm/customer.png" alt="" />新增客户</a></li>
<li><a href="#" onclick="loadContent('${vix}/crm/customer/crmContactPersonAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/crm/contactPerson.png" alt="" />新增联系人</a></li>
<li><a href="#" onclick="loadContent('${vix}/crm/activity/activityAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/crm/saleActivity.png" alt="" />新增活动</a></li>
<li><a href="#" onclick="loadContent('${vix}/crm/salechance/saleChanceAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/crm/saleChance.png" alt="" />新增销售机会</a></li>
<li><a href="#" onclick="loadContent('${vix}/crm/project/crmProjectAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/crm/project.png" alt="" />新增项目</a></li>
<li><a href="#" onclick="loadContent('${vix}/crm/project/projectRequirementAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/crm/projectRequirement.png" alt="" />新增需求</a></li>
<li><a href="#" onclick="loadContent('${vix}/crm/project/projectSolutionAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/crm/projectSolution.png" alt="" />新增解决方案</a></li>
<li><a href="#" onclick="loadContent('${vix}/crm/project/competitorAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/crm/competitors.png" alt="" />新增竞争对手</a></li>