<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-注册</title>
</head>
<body style="background: #fff;">
<header>
    <a href="${beiken}/beiken/beikenIndexAction!goLogin.action" class="b-header-code b-header-return"></a>注册
</header>
<section class="login register">
    <form method="post" id="userAccountForm">
        <dl>
            <dt>
            	<strong>
            		<input type="text" id="account" name="userAccount.account" placeholder="手机号">
            	</strong>
            </dt>
            <dt class="ver">
            	<strong><input type="text" id="validateCode" placeholder="验证码"></strong>
            	<input type="button" value="发送" class="senVer" onclick="settime(this)">
            </dt>
            <dt>
            	<strong class="red_errow">
            		<input type="hidden" id="pwd" name="userAccount.password" >
            		<input type="password" id="password"placeholder="密码">
            	</strong>
            </dt>
            <dt>
            	<strong>
            		<input type="password" id="passwordConfirm" placeholder="再次输入密码">
            	</strong>
            </dt>
            <dd><a href="${beiken}/beiken/beikenIndexAction!goLogin.action">已有账号,去登录</a> </dd>
        </dl>
        <h2>
            <a href="javascript:void(0);" id="registerBtn" onclick="register();" class="btn">注册</a>
        </h2>
    </form>
    <form action="<c:url value='/j_spring_security_check'/>" method="post" id="login-form">
		<input type="hidden" id='j_username' name='j_username' /> 
		<input type="hidden" id="j_password" name="j_password" />
	</form>
</section>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
	function register(){
		var account = $("#account").val();
		var validateCode = $("#validateCode").val();
		var password = $("#password").val();
		var passwordConfirm = $("#passwordConfirm").val();
		if(!account){
			$.toptip('请输入您的手机号');
			return;
		}
		if (!/^0?1[3|4|5|7|8][0-9]\d{8}$/.test(account)) {
			$.toptip('手机号输入有误，请您重新输入');
			return;
		}
		if (!validateCode) {
			$.toptip('请您输入手机验证码');
			return;
		}
		if (!password) {
			$.toptip('请您输入密码');
			return;
		}
		if (password.length<6 || password.length>20) {
			$.toptip('请您按要求设置密码');
			return;
		}
		if (!passwordConfirm) {
			$.toptip('请您输入确认密码');
			return;
		}
		if (passwordConfirm != password) {
			$.toptip('两次输入不一致，请您重新输入');
			return;
		}
		
		$("#registerBtn").attr("onclick","");
		var pwd = hex_md5(password);
		$("#pwd").val(pwd);
		$("#userAccountForm").ajaxSubmit({
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			url : "${beiken}/beiken/beikenIndexAction!register.action",
			success : function(result) {
				var r = result.split(":");
				if(r[0] > 0){
					$("#j_password").val(pwd);
					$("#j_username").val(account);
					$("#login-form").submit();
				}else{
					$.toptip(r[1]);
					return;
				}
			}
		});
	}
	
    var countdown=60;
    function settime(obj) {
        if (countdown == 0) {
            obj.removeAttribute("disabled");
            obj.value="获取验证码";
            countdown = 60;
            $(this).addClass("select");
            return;
        } else {
            obj.setAttribute("disabled", true);
            obj.value="重新发送(" + countdown + ")";
            countdown--;
        }
        setTimeout(function() {
                    settime(obj) }
                ,1000)
    }

</script>
