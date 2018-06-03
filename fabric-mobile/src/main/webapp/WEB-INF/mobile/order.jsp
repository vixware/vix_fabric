<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-全部订单</title>
</head>
<body>
<header>
    <a href="${beiken}/beiken/beikenUserCenterAction!goIndex.action?key=user" class="b-header-code b-header-return"></a>全部订单
    <!-- <a href="#" class="b-header-more"></a> -->
</header>
<section class="myOrder">
    <div class="tab_menu">
        <ul>
            <li <c:if test="${status == 'all'}">class="selected"</c:if>><a href="javascript:void(0);" onclick="loadOrderList('all');">全部</a></li>
            <li <c:if test="${status == 'nopay'}">class="selected"</c:if>><a href="javascript:void(0);" onclick="loadOrderList('nopay');">待付款</a></li>
            <li <c:if test="${status == 'audit'}">class="selected"</c:if>><a href="javascript:void(0);" onclick="loadOrderList('audit');">待审核</a></li>
            <li <c:if test="${status == 'ispay'}">class="selected"</c:if>><a href="javascript:void(0);" onclick="loadOrderList('ispay');">待发货</a></li>
            <li <c:if test="${status == 'receive'}">class="selected"</c:if>><a href="javascript:void(0);" onclick="loadOrderList('receive');">待收货</a></li>
            <li <c:if test="${status == 'finish'}">class="selected"</c:if>><a href="javascript:void(0);" onclick="loadOrderList('finish');">已完成</a></li>
        </ul>
    </div>
    <div class="tab_con">
        <!--- 全部-->
        <div class="common" >
        	<c:choose>
        		<c:when test="${requireGoodsOrderList != null && fn:length(requireGoodsOrderList) > 0}">
	        		<c:forEach items="${requireGoodsOrderList}" var="order">
	        			<div class="orderlist">
			                <div class="order-item b_b mb10 b_t">
			                    <div class="order-item-title b_b plr10 mui-flex">
			                        <div class="t-shopname cell t_l"><span class="t-name"><a href="#">(${order.code})</a><!-- <i class="bnj-sprite-icon arrow"></i> --></span></div>
			                        <c:if test="${order.payStatus == '0'}">
				                     	<div class="c_red cell t_r">等待付款</div>
				                     </c:if>
				                     <c:if test="${order.status == '0'}">
				                     	<div class="c_red cell t_r">待审核</div>
				                     </c:if>
				                     <c:if test="${order.status == '2'}">
				                     	<div class="c_red cell t_r">待发货</div>
				                     </c:if>
				                     <c:if test="${order.status == '3'}">
				                     	<div class="c_red cell t_r">待收货</div>
				                     </c:if>
				                     <c:if test="${order.status == '4'}">
				                     	<div class="c_red cell t_r">已完成</div>
				                     </c:if>
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
			                    <c:if test="${order.payStatus == '0'}">
			        				<div class="order-btn plr10"><a href="${beiken}/beiken/beikenOrderAction!goOrderPayment.action?orderId=${order.id}" class="btn1_red order-pay">支付</a></div>
			        			</c:if>
			                </div>
			            </div>
	        		</c:forEach>
	        	</c:when>
	        	<c:otherwise>
	        		<div class="bs_nodata"><p class="nodata-img"></p><p><a href="javascript:;">暂无数据</a></p></div>
	        	</c:otherwise>
        	</c:choose>
        </div>
    </div>
</section>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
    /* $(function(){
        var tab_menu_li = $('.tab_menu li');
        $('.tab_con .common:gt(0)').hide();

        tab_menu_li.click(function(){
            var index = $(this).index()+1;
            $(this).addClass('selected')
                    .siblings().removeClass('selected');
            var tab_menu_li_index = tab_menu_li.index(this);
            $('.tab_con .common').eq(tab_menu_li_index).show()
                    .siblings().hide();
        }).click(function(){
            $(this).addClass('hover');
        },function(){
            $(this).removeClass('hover');
        });
    }); */
    function loadOrderList(status){
    	document.location.href = '${beiken}/beiken/beikenOrderAction!goIndex.action?status='+status;
    }
</script>
