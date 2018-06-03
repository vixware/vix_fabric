<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-忘记密码</title>
</head>
<body style="background: #fff;">
<header>
    <a href="${beiken}/beiken/beikenIndexAction!goLogin.action" class="b-header-code b-header-return"></a>手机验证
</header>
<section class="forGetPas mt_50">
    <div class="log-box">
        <!-- 表单 -->
        <div class="input-item mb10">
            <input type="text" class="input-common" name="cellphone" id="cellphone" placeholder="手机号" maxlength="11" onkeyup="BNJ.enterNum(this)">
        </div>
        <div class="input-item mb25">
            <div style="width:70%; float:left; margin-right:10px;">
                <input type="text" class="input-common red_errow" name="valiCode" id="valiCode" placeholder="验证码">
            </div>
            <div style="width:25%; float:right;">
                <input type="button" class="btn2_gray" id="getValdateBtn" value="发送">
            </div>
        </div>
        <a href="javascript:;" class="btn" id="next">下一步</a>
    </div>
</section>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
