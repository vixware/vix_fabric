<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en-us" id="extr-page">
<head>
<meta charset="utf-8" />
<title>中盛云ERP</title>
<meta name="description" content="" />
<meta name="author" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-production-plugins.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-production.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-skins.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/smartadmin-rtl.min.css" />
<link rel="stylesheet" href="${nvix}/vixntcommon/base/js/plugin/validengine/css/validationEngine.jquery.css" type="text/css" />
</head>
<body class="animated fadeInDown">
	<header id="header">
		<div id="logo-group">
			<span id="logo"> <img src="${nvix}/vixntcommon/base/img/logo.png" alt="SmartAdmin">
			</span>
		</div>
	</header>
	<div id="main" role="main" style="background-image:url(${nvix}/vixntcommon/base/img/background.png);padding-top:60px;">
		<!-- MAIN CONTENT -->
		<div id="loginContent" class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm"></div>
				<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
					<div class="well no-padding">
						<form action="<c:url value='/j_spring_security_check'/>" method="post" id="login-form" class="smart-form client-form">
							<input id="tenantId" name="tenantId" type="hidden" value="" />
							<header>登录</header>
							<fieldset>
								<section>
									<label class="label">用户名:</label> <label class="input"><i class="icon-append fa fa-user"></i> <input id='j_username' name='j_username' type="text" class="validate[required]" /> <b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> 请输入用户名</b> </label>
								</section>
								<section>
									<input type='hidden' id='j_password' name='j_password' /> <label class="label">密码:</label> <label class="input"><i class="icon-append fa fa-lock"></i> <input id='pwd' name='pwd' type="password" class="validate[required]" /> <b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> 请输入密码</b> </label>
								</section>
								<section>
									<label class="label">验证码:</label> <label class="input"> <!-- class="validate[required]" --> <input id="validateCode" name="validateCode" type="text" onkeydown="loginKeyDown(event);" style="vertical-align: middle; width: 72%; display: inline;" /> <b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i>请输入验证码</b>
										<img src="${nvix}/validateCode.svl" onclick="this.src='${nvix}/validateCode.svl?d='+new Date()" style="vertical-align: middle; height: 32px; left: 1%; width: 26%; display: inline;" />
									</label>
								</section>
							</fieldset>
							<footer>
								<button id="buttonid" type="submit" class="btn btn-primary" onclick="loginSubmit();">登录</button>
							</footer>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${nvix}/vixntcommon/base/js/libs/jquery-2.1.1.min.js"></script>
	<script src="${nvix}/vixntcommon/base/js/bootstrap/bootstrap.min.js"></script>
	<script src="${nvix}/vixntcommon/base/js/plugin/validengine/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript"></script>
	<script src="${nvix}/vixntcommon/base/js/plugin/validengine/js/jquery.validationEngine.min.js" type="text/javascript"></script>
	<script src="${nvix}/vixntcommon/base/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
	<script src="${nvix}/vixntcommon/base/js/md5-min.js"></script>
	<script type="text/javascript">
		//runAllForms();
		$("#login-form").validationEngine();
		function loginSubmit() {
			var pwd = hex_md5($("#pwd").val());
			$("#j_password").val(pwd);
			if ($("#login-form").validationEngine('validate')) {
				$("#login-form").submit();
			}
		};
		function loginKeyDown(evt) {
			evt = (evt) ? evt : ((window.event) ? window.event : "");
			keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
			if (keyCode == 13) {
				var pwd = hex_md5($("#pwd").val());
				$("#j_password").val(pwd);
				if ($("#login-form").validationEngine('validate')) {
					$("#login-form").submit();
				}
			}
		};
		if ($("#mainContent").length > 0) {
			location.reload();
		};
		$(function() {
			//调用
			var username = getQueryStringV(location.href, "username");
			var password = getQueryStringV(location.href, "password");
			if (username != '' && password != '') {
				$("#j_username").val(username);
				$("#j_password").val(password);
				$("#pwd").val(password);
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
			for (var i = 0; i < parameters.length; i++) {
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
	</script>
</body>
</html>