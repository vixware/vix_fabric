<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-首页</title>
</head>
<body>
<header>
    <a href="#" class="b-header-code"></a>
    <div class="b-header-search">
        <a href="${beiken}/beiken/beikenIndexAction!goSearch.action"><b></b>搜索医疗器械</a>
    </div>
    <a href="#" class="b-header-news"></a>
</header>
<section class="index">
    <div class="banner" id="banner">
        <ul class="bd">
            <li><a href="#"><img src="${beiken}/mobilecommon/base/images/demoimg/banner_pic3.png" alt=""></a></li>
            <li><a href="#"><img src="${beiken}/mobilecommon/base/images/demoimg/banner_pic3.png" alt=""></a></li>
        </ul>
        <ol class="hd">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ol>
    </div>
    <div class="subjectsub mb10">
        <a href="#"><img src="${beiken}/mobilecommon/base/images/demoimg/subjectsub5.png"></a>
    </div>
    <div class="subjectsub mb10 b_t sub_4">
    	<c:choose>
    		<c:when test="${employee.type == '2'}">
    			<c:if test="${storeItemList != null && fn:length(storeItemList) > 0}">
		   			<c:forEach items="${storeItemList}" var="item" varStatus="s">
		   				<c:if test="${s.index < 10}">
		   					<div class="obj-item"><a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.id}"><img src="${beiken}/mobilecommon/base/images/demoimg/subjectsub${s.index%4+1}.png"></a></div>
		   				</c:if>
		   			</c:forEach>
		   		</c:if>
    		</c:when>
    		<c:when test="${employee.type == '1'}">
    			<c:if test="${itemList != null && fn:length(itemList) > 0}">
		   			<c:forEach items="${itemList}" var="item" varStatus="s">
		   				<c:if test="${s.index < 10}">
		   					<div class="obj-item"><a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.id}"><img src="${beiken}/mobilecommon/base/images/demoimg/subjectsub${s.index%4+1}.png"></a></div>
		   				</c:if>
		   			</c:forEach>
		   		</c:if>
    		</c:when>
    	</c:choose>
    </div>
</section>
<%@ include file="/mobilecommon/page/foot.jsp"%>
<!---加载-->
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
    $(function(){
        TouchSlide({
            slideCell:"#banner",
            titCell:".hd",
            mainCell:".bd",
            effect:"left",
            autoPlay:true,
            autoPage:'<li>&nbsp;1</li>'
        });
    });
</script>