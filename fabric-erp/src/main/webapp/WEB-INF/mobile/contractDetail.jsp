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
    <a href="javascript:history.back();" class="header_return"><i class="iconfont icon-icon--"></i></a>
    <p class="coupons">合约详情</p>
</header>
<section class="mt_45 mb_53">
    <div class="deinfo-list">
        <div class="weui-media-box weui-media-box_text">
            <h4 class="weui-media-box__title">${fabricContract.title}</h4>
            <p class="weui-media-box__desc">创建时间：${fabricContract.createTimeStr }</p>
            <p class="weui-media-box__desc">经营人：${fabricContract.operator}  </p>
        </div>
    </div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>产权证明</p>
            </div>
            <div class="weui-cell__ft"><img src="images/chanquan.png" alt=""></div>
        </div>
    </div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>种植面积（㎡）</p>
            </div>
            <div class="weui-cell__ft">${fabricContract.plantingArea}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>预计产量</p>
            </div>
            <div class="weui-cell__ft">${fabricContract.estimatedOutput}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>预订价格</p>
            </div>
            <div class="weui-cell__ft">${fabricContract.predeterminedPrice}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>现货价格</p>
            </div>
            <div class="weui-cell__ft">${fabricContract.spotPrice}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>预付定金</p>
            </div>
            <div class="weui-cell__ft">${fabricContract.prepaidDeposit}</div>
        </div>
    </div>
    <div class="weui-cells">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd">
                <p>交易模式</p>
            </div>
            <div class="weui-cell__ft">${fabricContract.tradingMode}</div>
        </a>
    </div>
    <div class="weui-cells">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd">
                <p>支付方式</p>
            </div>
            <div class="weui-cell__ft">币</div>
        </a>
    </div>
    <div class="weui-cells">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd">
                <p>关联资产</p>
            </div>
            <div class="weui-cell__ft">（1）</div>
        </a>
        <div class="weui-cell">
            <div class="weui-cell__hd"><i class="iconfont icon-icon-chongdie"></i></div>
            <div class="weui-cell__bd">
                <p></p>
            </div>
            <div class="weui-cell__ft">${fabricContract.title}</div>
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