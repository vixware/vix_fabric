<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-设置</title>
</head>
<body>
<header>
    <a href="javascript:history.back();" class="b-header-code b-header-return"></a>设置
</header>
<section class="mt_50">
    <ul class="set">
        <li><a href="#">修改登录密码<span class="bnj-sprite-icon arrow_r"></span></a></li>
        <li><a href="#">重新绑定手机<span class="bnj-sprite-icon arrow_r"></span></a></li>
        <li class="contactus"><a href="javascript:;">联系我们<span class="bnj-sprite-icon arrow_r"></span></a></li>
    </ul>
    <div class="layout"><a href="${beiken}/beiken/beikenIndexAction!goLogin.action" class="btn" id="layout">退出登录</a></div>
</section>
<%@ include file="/mobilecommon/page/foot.jsp"%>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
