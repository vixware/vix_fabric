<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
$('#left h2').click(
	function () {
		var hc = $(this).attr("class");
		if (hc == 'current'){
			$('#left h2').removeClass("current");
			$('#left ul').slideUp('fast');
		}else{
			$('#left h2').removeClass("current");
			$('#left ul').slideUp('fast');
			$(this).next().slideDown('fast');
			$(this).addClass("current");
		}
});
//-->
</script>
<h2>
	<img src="${vix}/common/img/icon_22.gif" alt="" />
	<s:text name="vix_system" />
</h2>
<ul>
	<li><a href="#" onclick="loadRightContent('${vix}/system/organizationAction!goList.action');">机构管理</a></li>
	<li><a href="#" onclick="loadRightContent('${vix}/system/departmentAction!goList.action');">部门管理</a></li>
	<li><a href="#" onclick="loadRightContent('${vix}/system/employeeAction!goList.action');">职员管理</a></li>
</ul>
<h2>
	<img src="${vix}/common/img/icon_22.gif" alt="" />
	<s:text name="vix_security" />
</h2>
<ul>
	<li><a href="#" onclick="loadRightContent('${vix}/security/roleAction!goList.action');">角色管理</a></li>
	<li><a href="#" onclick="loadRightContent('${vix}/security/userAction!goList.action');">账户管理</a></li>
	<li><a href="#" onclick="loadRightContent('${vix}/security/authorityAction!goList.action');">权限管理</a></li>
	<li><a href="#" onclick="loadRightContent('${vix}/security/resourceAction!goList.action');">资源管理</a></li>
</ul>