<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-个人中心</title>
</head>
<body>
<header>
    <%-- <a href="${beiken}/beiken/beikenUserCenterAction!goSet.action?key=user" class="b-header-code b-header-set"></a> --%>个人中心
    <!-- <a href="#" class="b-header-more b-header-news2"></a> -->
</header>
<section class="useCenter">
    <div class="useCenter_list1 mb10">
        <h2><img src="${beiken}/mobilecommon/base/images/useCen_heaPos_pic.jpg">${employee.name}</h2>
        <%-- <ul>
            <li><a href="#"><b>0</b><span>收藏的宝贝</span></a> </li>
            <li><a href="${beiken}/beiken/beikenUserCenterAction!goConsignee.action"><b>1</b><span>收货地址</span></a> </li>
        </ul> --%>
    </div>
    <div class="useCenter_list3 mb10 b_b">
        <ul>
        	<li><a href="${beiken}/beiken/beikenUserCenterAction!goConsignee.action?type=user"><b class="icon1"></b>收货地址</a> </li>
        	<c:if test="${employee.type == '1'}">
        		<li><a href="${beiken}/beiken/beikenUserCenterAction!searchStock.action"><b class="icon2"></b>查询库存</a> </li>
        	</c:if>
            <li><a href="${beiken}/beiken/beikenOrderAction!goIndex.action?status=all"><b class="icon1"></b>全部订单</a> </li>
        </ul>
        <ol class="tab_menu">
            <li class="selected" onclick="goOrderList('nopay',this)"><a href="javascript:void(0);"><b class="icon1"></b><span>待付款</span></a> </li>
            <li onclick="goOrderList('ispay')"><a href="javascript:void(0);"><b class="icon2"></b><span>待发货</span></a> </li>
            <li onclick="goOrderList('receive')"><a href="javascript:void(0);"><b class="icon3"></b><span>待收货</span></a> </li>
        </ol>
    </div>
    <div class="tab_con">
        <div class="common" >
        	<c:choose>
        		<c:when test="${requireGoodsOrderList != null && fn:length(requireGoodsOrderList) > 0}">
        			<c:forEach items="${requireGoodsOrderList}" var="order">
	        			<div class="orderlist">
			                <div class="order-item b_b mb10 b_t">
			                    <div class="order-item-title b_b plr10 mui-flex">
			                        <div class="t-shopname cell t_l"><span class="t-name"><a href="#">(${order.code})</a><!-- <i class="bnj-sprite-icon arrow"></i> --></span></div>
				              		<div class="c_red cell t_r">等待付款</div>
			                    </div>
		                   		 <a href="#">
			                        <ul class="order-item-pro">
			                        	<c:forEach items="${requireGoodsOrderItemMap}" var="itemMap">
			   								<c:if test="${itemMap.key == order.id}">
			   									<c:forEach items="${itemMap.value}" var="item">
				                        			<li class="goods-item">
						                                <div class="i-p">
						                                	<a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.itemId}">
						                                		<img src="${beiken}/mobilecommon/base/images/demoimg/collect_pic2.jpg" alt="">
						                                	</a>
						                                </div>
						                                <div class="i-t">
						                                    <div class="i-t-title">
						                                    	<a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.itemId}">
						                                    		<h3>${item.title}(${item.specification})</h3>
						                                    	</a>
						                                    </div>
						                                    <div class="i-t-price"><span class="price-num c_red"><i class="mui-price-rmb">¥</i>${item.price}x${fn:split(item.amount,'.')[0]}</span></div>
						                                </div>
						                            </li>
			                        			</c:forEach>
			                        		</c:if>
			                        	</c:forEach>
			                        </ul>
			                        <div class="order-total-price plr10 b_b"><p>合计：<span class="t-price c_red">￥<i>${order.amount}</i></span>(含运费￥<span class="t-freight">0.00</span>共<span class="t-amount">${fn:split(order.num,'.')[0]}</span>件商品)</p></div>
			                    </a>
			        			<div class="order-btn plr10"><a href="${beiken}/beiken/beikenOrderAction!goOrderPayment.action?orderId=${order.id}" class="btn1_red order-pay">支付</a></div>
			                </div>
			            </div>
					</c:forEach>
		   		</c:when>
		   		<c:otherwise>
		   			<div class="bs_nodata"><p class="nodata-img" style="margin-top: 0px;"></p><p><a href="javascript:;">暂无数据</a></p></div>
		   		</c:otherwise>
        	</c:choose>
        </div>
    </div>
</section>
<%@ include file="/mobilecommon/page/foot.jsp"%>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
    $(function(){
        var tab_menu_li = $('.tab_menu li');
        //$('.tab_con .common:gt(0)').hide();

        tab_menu_li.click(function(){
            var index = $(this).index()+1;
            $(this).addClass('selected').siblings().removeClass('selected');
            //var tab_menu_li_index = tab_menu_li.index(this);
            //$('.tab_con .common').eq(tab_menu_li_index).show().siblings().hide();
        })
    });
    function goOrderList(status,obj){
    	$(".common").load("${beiken}/beiken/beikenUserCenterAction!goOrderList.action?status="+status);
    }
</script>
