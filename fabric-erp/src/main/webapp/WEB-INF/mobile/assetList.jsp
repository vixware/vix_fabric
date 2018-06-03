<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_js.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	 <title>联盟链-资产列表</title>
</head>
<body>
<header>
    <a href="javascript:history.back();" class="header_return"><i class="iconfont icon-iconfontzhizuobiaozhun22"></i></a>
    <p class="coupons">资产列表</p>
    <a href="#" class="header_r_txt"><i class="iconfont icon-jia"></i></a>
</header>
<section class="mt_45 mb_53 assets">
    <c:forEach items="${fabricAssetList}" var="fabricAsset">
    
    <div class="weui-form-preview mt_10" >
    	<a href="${ws}/front/fabricAssetAction!goAssetDetail.action?id=${fabricAsset.id}">
        <div class="weui-form-preview__hd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label"><i class="iconfont icon-icon-chongdie main_color" style="vertical-align: middle;margin-right: 5px;"></i>${fabricAsset.assetName}</label>
                <em class="weui-form-preview__value"><span class="use">使用中</span></em>
            </div>
            <span class="leftRad"></span>
            <span class="rightRad"></span>
        </div>
        <div class="weui-form-preview__bd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">面积</label>
                <span class="weui-form-preview__value">${fabricAsset.plantingArea}</span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">取得使用权限</label>
                <span class="weui-form-preview__value">${fabricAsset.dateOfAccessStr}</span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">所在地址</label>
                <span class="weui-form-preview__value">${fabricAsset.site}</span>
            </div>
        </div>
        </a>
    </div>
    </c:forEach>
    <!-- <div class="weui-form-preview mt_10">
        <div class="weui-form-preview__hd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label"><i class="iconfont icon-icon-chongdie main_color" style="vertical-align: middle;margin-right: 5px;"></i>东海云顶茶山</label>
                <em class="weui-form-preview__value"><span class="">未使用</span></em>
            </div>
            <span class="leftRad"></span>
            <span class="rightRad"></span>
        </div>
        <div class="weui-form-preview__bd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">面积</label>
                <span class="weui-form-preview__value">20000㎡</span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">取得使用权限</label>
                <span class="weui-form-preview__value">80年</span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">所在地址</label>
                <span class="weui-form-preview__value">浙江宁海东北部最高山脉</span>
            </div>
        </div>
    </div> -->
</section>
<!-- 底部代码 -->
<div class="weui-tabbar">
    <a href="${ws}/front/fabricIndexAction!goIndex.action?key=home" class="weui-tabbar__item ">
        <i class="iconfont icon-home"></i>
        <p class="weui-tabbar__label">首页</p>
    </a>
    <a href="${ws}/front/fabricContractAction!goContractList.action?id=" class="weui-tabbar__item  ">
        <i class="iconfont icon-heyue"></i>
        <p class="weui-tabbar__label">合约</p>
    </a>
    <a href="${ws}/front/fabricAssetAction!goAssetList.action?id=" class="weui-tabbar__item weui-bar__item_on">
        <i class="iconfont icon-zichanxinxi"></i>
        <p class="weui-tabbar__label">资产</p>
    </a>
    <a href="${ws}/front/fabricUserCenterAction!goIndex.action?id=" class="weui-tabbar__item ">
        <i class="iconfont icon-wode"></i>
        <p class="weui-tabbar__label">我的</p>
    </a>
</div>
</body>
</html>