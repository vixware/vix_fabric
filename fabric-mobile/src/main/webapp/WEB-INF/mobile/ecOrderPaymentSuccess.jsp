<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-订单支付成功</title>
</head>
<body>
<header>
    <a href="个人中心.html" class="b-header-code b-header-return"></a>订单支付成功
</header>
<section class="payMent">
    <div class="payMent_list1">
        <ul>
            <li><a href="javascript:void(0);"><b>1</b><span>我的购物车</span></a> </li>
            <li><a href="javascript:void(0);"><b>2</b><span>填写我的订单信息</span></a> </li>
            <li><a href="javascript:void(0);"><b>3</b><span>提交订单信息</span></a> </li>
            <div class="linefff"></div>
        </ul>
    </div>
    <div class="payMent_list2">
        <p><b>*</b>订单已提交成功，我们会尽快安排发货！</p>
        <a href="${beiken}/beiken/beikenUserCenterAction!goIndex.action?key=user"><p>查看我的订单状态</p></a>
        <a href="${beiken}/beiken/beikenIndexAction!goIndex.action?key=home">跳转到首页</a>
    </div>
    <div class="payMent_list3">
        <ul>
            <li><b>订单号：</b><span>${requireGoodsOrder.code}</span></li>
            <li><b>应付金额：</b><span>&yen;${requireGoodsOrder.amount}</span></li>
            <li>
            	<b>支付方式：</b>
            	<c:if test="${requireGoodsOrder.payType == 'cash'}"><span>线下支付</span></c:if>
            	<c:if test="${requireGoodsOrder.payType == 'wx'}"><span>微信支付</span></c:if>
            </li>
        </ul>
    </div>
</section>
</body>
</html>