<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<c:choose>
	<c:when test="${requireGoodsOrderList != null && fn:length(requireGoodsOrderList) > 0}">
		<c:forEach items="${requireGoodsOrderList}" var="order">
			<div class="orderlist">
				<div class="order-item b_b mb10 b_t">
	                 <div class="order-item-title b_b plr10 mui-flex">
	                     <div class="t-shopname cell t_l"><span class="t-name"><a href="#">(${order.code})</a><!-- <i class="bnj-sprite-icon arrow"></i> --></span></div>
	                     <c:if test="${status == 'nopay'}">
	                     	<div class="c_red cell t_r">等待付款</div>
	                     </c:if>
	                     <c:if test="${status == 'ispay'}">
	                     	<div class="c_red cell t_r">待发货</div>
	                     </c:if>
	                     <c:if test="${status == 'receive'}">
	                     	<div class="c_red cell t_r">待收货</div>
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
	                 <c:if test="${status == 'nopay'}">
                     	<div class="order-btn plr10"><a href="${beiken}/beiken/beikenOrderAction!goOrderPayment.action?orderId=${order.id}" class="btn1_red order-pay">支付</a></div>
                     </c:if>
				</div>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="bs_nodata"><p class="nodata-img" style="margin-top: 0px;"></p><p><a href="javascript:;">暂无数据</a></p></div>
	</c:otherwise>
</c:choose>