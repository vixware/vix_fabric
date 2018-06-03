<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-登录</title>
</head>
<body style="background: #fff;">
<header>
 	登录
</header>
<section class="login">
    <form action="<c:url value='/j_spring_security_check'/>" method="post" id="login-form">
        <dl>
            <dt>
            	<span></span>
            	<strong class="red_errow">
            		<input type="text" id='j_username' name='j_username' placeholder="请输入账号">
            	</strong>
            </dt>
            <!-- 如果错误加上样式red_errow-->
            <dt>
            	<span class="icon2"></span>
            	<strong>
            		<input type="password" id='pwd' name='pwd' placeholder="请输入密码">
            		<input type='hidden' id='j_password' name='j_password'/> 
            	</strong>
            </dt>
            <%-- <dd><a href="${beiken}/beiken/beikenIndexAction!goForgetPassword.action">忘记密码</a> </dd> --%>
        </dl>
        <h2>
            <a href="javascript:void(0);" onclick="loginSubmit()" class="btn">登录</a>
            <%-- <a href="${beiken}/beiken/beikenIndexAction!goRegister.action" class="btn gray_btn">注册</a> --%>
        </h2>
    </form>
</section>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
	$(function() {
		var url = location.search;//获取url中"?"符后的字串
		if (url.indexOf("?") != -1) {//判断是否有参数
			var str = url.substr(1);//从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
			var strs = str.split("=");//用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
			var strs1 = strs[1].split("&");
			if (strs1[0] == "true") {
				$.toptip('用户名或密码错误');
				$("#j_password").val('');
				$("#pwd").val('');
			}
		}
	});
	function loginSubmit() {
		var username = $("#j_username").val();
		var pwd = $("#pwd").val();
		if (!username) {
			$.toptip('请输入您的账号');
			return;
		}
		if (!pwd) {
			$.toptip('请输入密码');
			return;
		}
		pwd = hex_md5(pwd);
		$("#j_password").val(pwd);
		$("#login-form").submit();
	};
	$(function() {
		var username = $("#j_username").val();
		var password = $("#j_password").val();
		if (username != null && password != null && username != '' && password != '') {
			$("#login-form").submit();
		}
	});
</script>
