 <%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_js.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>联盟链-个人中心</title>
</head>
<body>
<header>
    <%-- <a href="${beiken}/beiken/beikenUserCenterAction!goSet.action?key=user" class="b-header-code b-header-set"></a> --%>个人中心
    <!-- <a href="#" class="b-header-more b-header-news2"></a> -->
</header>
<section class="mb_53">
    <div class="useCen_list1">
        <header class="useCen_header" style="background: transparent;">
            <a href="#" class="header_r_txt">设置</a>
        </header>
        <h2><img src="${ws}/mobilecommon/base/images/logo3.png"><b>王建东</b></h2>
        <div class="mymsg">
            <ul>
                <li><span>86</span><b>我的合约</b></li>
                <li><span>76</span><b>我的资产</b></li>
                <li><span>66</span><b>交易记录</b></li>
            </ul>
        </div>
    </div>
    <div class="weui-cells mt_10">
        <a class="weui-cell weui-cell_access" href="${ws}/front/fabricAssetAction!goAssetList.action?id=">
            <div class="weui-cell__hd mr_10"><i class="iconfont icon-loufang main_color"></i></div>
            <div class="weui-cell__bd"><p>我的资产</p></div>
            <div class="weui-cell__ft"></div>
        </a>
    </div>
    <div class="weui-cells mt_10">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__hd mr_10"><i class="iconfont icon-jiaoyijilu main_color"></i></div>
            <div class="weui-cell__bd"><p>交易记录</p></div>
            <div class="weui-cell__ft"></div>
        </a>
        <a class="weui-cell weui-cell_access" href="${ws}/front/fabricContractAction!goContractList.action?id=">
            <div class="weui-cell__hd mr_10"><i class="iconfont icon-touzijilu orange_color"></i></div>
            <div class="weui-cell__bd"><p>合约记录</p></div>
            <div class="weui-cell__ft"></div>
        </a>
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__hd mr_10"><i class="iconfont icon-drxx74 main_color"></i></div>
            <div class="weui-cell__bd"><p>安全设置</p></div>
            <div class="weui-cell__ft"></div>
        </a>
    </div>
    <div class="weui-cells mt_10">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__hd mr_10"><i class="iconfont icon-shoucang orange_color"></i></div>
            <div class="weui-cell__bd"><p>收藏</p></div>
            <div class="weui-cell__ft"></div>
        </a>
    </div>
    <div class="weui-cells mt_10">
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__hd mr_10"><i class="iconfont icon-fankuiyijian green_color"></i></div>
            <div class="weui-cell__bd"><p>意见反馈</p></div>
            <div class="weui-cell__ft"></div>
        </a>
        <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__hd mr_10"><i class="iconfont icon-guanyuwomen green_color"></i></div>
            <div class="weui-cell__bd"><p>关于我们</p></div>
            <div class="weui-cell__ft"></div>
        </a>
    </div>
</section>
</body>
<div class="weui-tabbar">
    <a href="${ws}/front/fabricIndexAction!goIndex.action?key=home" class="weui-tabbar__item ">
        <i class="iconfont icon-home"></i>
        <p class="weui-tabbar__label">首页</p>
    </a>
    <a href="${ws}/front/fabricContractAction!goContractList.action?id=" class="weui-tabbar__item  ">
        <i class="iconfont icon-heyue"></i>
        <p class="weui-tabbar__label">合约</p>
    </a>
    <a href="${ws}/front/fabricAssetAction!goAssetList.action?id=" class="weui-tabbar__item ">
        <i class="iconfont icon-zichanxinxi"></i>
        <p class="weui-tabbar__label">资产</p>
    </a>
    <a href="${ws}/front/fabricUserCenterAction!goIndex.action?id=" class="weui-tabbar__item weui-bar__item_on">
        <i class="iconfont icon-wode"></i>
        <p class="weui-tabbar__label">我的</p>
    </a>
</div>
</html>