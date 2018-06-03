<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="vix_name" /></title>
<link href="${vix}/common/css/login.css" type="text/css" id="font" rel="stylesheet" />
<script src="${vix}/common/core/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${vix}/common/js/encode/md5Vixnt.js" type="text/javascript"></script>
<script src="${vix}/common/js/login.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	//调用
	var username = getQueryStringV(location.href, "username");
	var password = getQueryStringV(location.href, "password");
	if (username != '' && password != '') {
		$("#j_username").val(username);
		$("#j_password").val(password);
		$("#buttonid").click();
	}
});

function getQueryStringV(vhref, name) {
	// 如果链接没有参数，或者链接中不存在我们要获取的参数，直接返回空 
	if (vhref.indexOf("?") == -1 || vhref.indexOf(name + '=') == -1) {
		return '';
	}
	// 获取链接中参数部分 
	var queryString = vhref.substring(vhref.indexOf("?") + 1);
	// 分离参数对 ?key=value&key2=value2 
	var parameters = queryString.split("&");
	var pos, paraName, paraValue;
	for ( var i = 0; i < parameters.length; i++) {
		// 获取等号位置 
		pos = parameters[i].indexOf('=');
		if (pos == -1) {
			continue;
		}
		// 获取name 和 value 
		paraName = parameters[i].substring(0, pos);
		paraValue = parameters[i].substring(pos + 1);

		if (paraName == name) {
			return unescape(paraValue.replace(/\+/g, " "));
		}
	}
	return '';
}
	$(function() {
		loginPageBgEvent(1);

		setVixTenantId();
		setRedirectUrl();
	});
	function loginSubmit() {
		var j_username = $('#j_username').val();
		if (j_username == '') {
			alert('请输入登录账号');
			return;
		} else if ($('#j_password').val() == '') {
			alert('请输入登录密码');
			return;
		} else if (j_username.indexOf('@') != -1) {
			var idx = j_username.indexOf('@');
			$('#j_username').val(j_username.substring(0, idx));
			$('#tenantId').val(j_username.substring(idx + 1));
		}

		//密码加密
		$('#j_password').val(md5Vixnt.hex_md5($('#j_password').val()));
		$("#loginForm").submit();
	}

	if ($("#mainContent").length > 0) {
		location.reload();
	}
	function setVixTenantId() {
		var vixTenantId = "${param.tenantId}";
		if (vixTenantId == null || vixTenantId == "") {
			vixTenantId = getCookie4TenantId("VIX_TENANTID");
		}
		$("#tenantId").val(vixTenantId);
	}

	function setRedirectUrl() {
		var service = "${param.service}";
		if (service == null || service == "") {
			service = getCookie4TenantId("service");
		}
		$("#service").val(service);
	}

	//读取cookie
	function getCookie4TenantId(name) {
		var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
		if (arr = document.cookie.match(reg))
			return unescape(arr[2]);
		else
			return null;
	}
	//${param.tenantId}
</script>
</head>
<body>
	<ul id="bg">
		<li id="bg1" class="bg1"></li>
		<li id="bg2" class=""></li>
		<li id="bg3" class=""></li>
		<li id="bg4" class=""></li>
		<li id="bg5" class=""></li>
		<li id="bg6" class=""></li>
		<li id="bg7" class=""></li>
		<li id="bg8" class=""></li>
		<li id="bg9" class=""></li>
		<li id="bg10" class=""></li>
	</ul>
	<div class="top_bar">
		<div class="top">
			<ul class="bgbtn">
				<li class="bgbtn1 onthis" rel="1"><span></span></li>
				<li class="bgbtn2" rel="2"><span></span></li>
				<li class="bgbtn3" rel="3"><span></span></li>
				<li class="bgbtn4" rel="4"><span></span></li>
				<li class="bgbtn5" rel="5"><span></span></li>
				<li class="bgbtn6" rel="6"><span></span></li>
				<li class="bgbtn7" rel="7"><span></span></li>
				<li class="bgbtn8" rel="8"><span></span></li>
				<li class="bgbtn9" rel="9"><span></span></li>
				<li class="bgbtn10" rel="10"><span></span></li>
			</ul>
			<a id="autoBtn" class="pause" href="javascript:;"></a>
		</div>
	</div>
	<div class="formbg"></div>
	<div class="loginbox">
		<div class="title">
			<h1>
				<img src="${vix}/common/img/login/logo.png" class="png" />
			</h1>
		</div>
		<div class="loginform">
			<!-- j_spring_security_check -->
			<form id="loginForm" action="<c:url value='/j_spring_security_check'/>" method="post">
				<p class="error"></p>
				<div class="formline">
					<label>用户名：</label> <input id='j_username' name='j_username' type="text" class="ipt" /> <input id="tenantId" name="tenantId" type="hidden" value="" /> <input id="service" name="service" type="hidden" value="" />
				</div>
				<div class="formline">
					<label>密 码：</label> <input class="ipt" id='j_password' name='j_password' type="password" />
				</div>
				<div class="formline">
					<label>验证码：</label> <input class="ipt card" id="validateCode" name="validateCode" type="text" size="8" /> <img style="cursor: pointer;" src="${vix}/validateCode.svl" onclick="this.src='${vix}/validateCode.svl?d='+new Date()" />
				</div>
				<div class="formline">
					<c:if test="${not empty param.login_error}">
						<p>${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</p>
					</c:if>
				</div>
				<div class="formline">
					<input id="buttonid" type="submit" value="登 录" class="loginbtn" onclick="loginSubmit();" />
				</div>
			</form>
		</div>
	</div>
	<div class="footer_bar">
		<div class="footer">Copyright © 2009 - 2012 Vixware Soft Crop. 版权所有</div>
	</div>
</body>
</html>
<script type="text/javascript">
function saveOrUpdateWxpWeixinSite() {
	$.post('${nvix}/nvixnt/wxpWeixinSiteAction!saveOrUpdateWxpWeixinSite.action', {
		'authCode' : location.href.split('#')[0]
	}, function(result) {
	});
};
$(function() {
	//saveOrUpdateWxpWeixinSite();
});

</script>