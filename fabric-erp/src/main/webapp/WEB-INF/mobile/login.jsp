<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
    <title>联盟链-登录</title>
</head>
<body>
<section class="login">
    <header class="useCen_header" style="background: transparent;">
        <a href="#" class="header_return"><i class="iconfont icon-icon--"></i></a>
    </header>
	<div class="loginLogo">联盟链</div>
    
	
	
    <div class="loginContetn">
        <form action="<c:url value='/j_spring_security_check'/>" method="post" id="login-form">
            <div class="numinput">
                <i class="iconfont icon-wode"></i><input type="text" id='j_username' name='j_username'  placeholder="邮箱/手机号/账号">
            </div>
            <div class="passinput">
                <i class="iconfont icon-unie65b"></i>
                <input type="password" id='pwd' name='pwd' placeholder="请输入您的密码">
            	<input type='hidden' id='j_password' name='j_password'/> 
                <i class="iconfont icon-chakan"></i>
            </div>
            <div class="dlBtn"><a href="javascript:void(0);" class=""  onclick="loginSubmit();">登录</a></div>
            <div class="opeartbtn"><a href="#">忘记密码</a>&nbsp;|&nbsp;<a href="#">快速注册</a></div>
        </form>
        <div class="thirdUse">
            <div class="thirdUsetitle"><span></span>&nbsp;&nbsp;第三方账号登录 &nbsp;&nbsp;<span></span></div>
            <div class="thirdUsecontent">
                <ul>
                    <li><p><i class="iconfont icon-wechat"></i></p></li>
                    <li><p><i class="iconfont icon-QQ"></i></p></li>
                    <li><p><i class="iconfont icon-tubiao214"></i></p></li>
                    <li><p><i class="iconfont icon-renrenwang"></i></p></li>
                </ul>
            </div>
        </div>
    </div>
</section>
</body>
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
</html>