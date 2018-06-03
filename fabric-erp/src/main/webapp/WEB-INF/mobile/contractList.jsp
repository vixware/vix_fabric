<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_js.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>联盟链-合约列表</title>
</head>
<body>
<header>
   <a href="javascript:history.back();" class="header_return"><i class="iconfont icon-iconfontzhizuobiaozhun22"></i></a>
    <p class="coupons">合约列表</p>
    <a href="#" class="header_r_txt"><i class="iconfont icon-jia"></i></a>
</header>
<section class="mt_45 mb_53 contract">
    <div class="weui-tab">
        <div class="weui-navbar">
            <a class="weui-navbar__item weui-bar__item--on" href="#tab1">合约市场</a>
            <a class="weui-navbar__item" href="#tab2">我的合约</a>
        </div>
        <div class="weui-tab__bd">
            <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
            	<c:forEach items="${fabricContractList}" var ="fabricContract">
                <div class="deinfo-list">
                    <div class="weui-media-box weui-media-box_text">
                        <h4 class="weui-media-box__title">${fabricContract.title}</h4>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <p><span style="border:1px solid #0092D8;color:#0092D8;font-size: 10px;padding:1px 3px;border-radius: 3px;">发</span>&nbsp;${fabricContract.operator} <span style="font-size: 10px;">创建时间：${fabricContract.createTimeStr}</span></p>
                        </div>
                        <div class="weui-cell__ft"><a href="${ws}/front/fabricContractAction!goContractDetail.action?id=${fabricContract.id}" class="weui-btn weui-btn_mini weui-btn_primary">交易详情</a></div>
                    </div>
                </div>
            	</c:forEach>
            </div>
            <div id="tab2" class="weui-tab__bd-item">
            	<c:forEach items="${fabricContractList}" var ="fabricContract">
                <div class="deinfo-list">
                    <div class="weui-media-box weui-media-box_text">
                        <h4 class="weui-media-box__title">${fabricContract.title}</h4>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <p><span style="border:1px solid #0092D8;color:#0092D8;font-size: 10px;padding:1px 3px;border-radius: 3px;">发</span>&nbsp;${fabricContract.operator} <span style="font-size: 10px;">创建时间：${fabricContract.createTimeStr}</span></p>
                        </div>
                        <div class="weui-cell__ft"><a href="${ws}/front/fabricContractAction!goContractDetail.action?id=${fabricContract.id}" class="weui-btn weui-btn_mini weui-btn_primary">交易详情</a></div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
</body>
<div class="weui-tabbar">
    <a href="${ws}/front/fabricIndexAction!goIndex.action?key=home" class="weui-tabbar__item ">
        <i class="iconfont icon-home"></i>
        <p class="weui-tabbar__label">首页</p>
    </a>
    <a href="${ws}/front/fabricContractAction!goContractList.action?id=" class="weui-tabbar__item  weui-bar__item_on">
        <i class="iconfont icon-heyue"></i>
        <p class="weui-tabbar__label">合约</p>
    </a>
    <a href="${ws}/front/fabricAssetAction!goAssetList.action?id=" class="weui-tabbar__item ">
        <i class="iconfont icon-zichanxinxi"></i>
        <p class="weui-tabbar__label">资产</p>
    </a>
    <a href="${ws}/front/fabricUserCenterAction!goIndex.action?id=" class="weui-tabbar__item ">
        <i class="iconfont icon-wode"></i>
        <p class="weui-tabbar__label">我的</p>
    </a>
</div>
</html>