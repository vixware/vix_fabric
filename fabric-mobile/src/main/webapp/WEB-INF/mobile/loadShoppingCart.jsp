<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<c:choose>
	<c:when test="${requireGoodsOrderList != null && fn:length(requireGoodsOrderList) > 0}">
		<div class="shopCart_con">
			<c:forEach items="${requireGoodsOrderList}" var="order">
				<div class="shop-group-item b_t mb10">
				    <div class="shop-title b_b">
				        <!-- <div class="check-icon bnj-sprite-icon"></div> -->
				        <div class="shop-title-r"><div class="title"><a href="#"><span class="shop-icon bnj-sprite-icon"></span>(${order.code})<em class="arrow bnj-sprite-icon"></em></a></div></div>
				    </div>
						<c:forEach items="${requireGoodsOrderItemMap}" var="itemMap">
		   					<c:if test="${itemMap.key == order.id}">
		   						<c:forEach items="${itemMap.value}" var="item">
									<li class="shop-cart-item b_b">
					             		<!-- <div class="check-icon bnj-sprite-icon"></div> -->
				            			<div class="shop-cart-item-r">
				                			<div class="i-p"><a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.itemId}"><img src="${beiken}/mobilecommon/base/images/demoimg/collect_pic2.jpg" alt=""></a></div>
				               				<div class="i-t">
				                   				<div class="i-t-title"><a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.itemId}">${item.title}(${item.specification})</a></div>
				                   				<div class="i-t-price">
				                   					<span class="price-num c_red mr10">
				                   						<i>￥</i>
												    	<c:choose>
												    		<c:when test="${employee.type == '2'}">
												    			<em class="price">${item.price}</em>
												    		</c:when>
												    		<c:when test="${employee.type == '1'}">
												    			<input type="number" size="9" value="${item.price}"  class="c_red" style="font-size: 18px;border: none;width: 130px;" onblur="updateItmePrice('${item.id}',this);">
												    		</c:when>
												    	</c:choose>
				                   					</span>
				                   				</div>
						                 	<div class="quantity-wrapper">
						           				<a href="javascript:void(0);" onclick="updateItmeCount('${fn:split(item.amount,'.')[0]}','${item.id}','reduce');" class="quantity-decrease <c:if test='${item.amount == 1}'>disabled</c:if>"></a>
						                        <input type="text" size="4" value="${fn:split(item.amount,'.')[0]}" name="num" class="quantity" readonly="">
						                        <a href="javascript:void(0);" onclick="updateItmeCount('${fn:split(item.amount,'.')[0]}','${item.id}','add');" class="quantity-increase"></a>
						                        &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:deleteItem('${item.id}');" style="color: red; text-align: right;">删除</a>
						  					</div>
				               				</div>
				            			</div>
				 					</li>
				 				</c:forEach>
				 			</c:if>
						</c:forEach>
					</ul>
				</div>
			</c:forEach>
		</div>
		<div class="payment-total-bar b50">
			<div class="settlefooter">
				<div class="payment-l">
					<!-- <div class="checkAll">
						<div class="check-icon bnj-sprite-icon checkAllBtn"></div><label>全选</label>
					</div> -->
					<div class="payment-total-money">
						<p class="pay-total">合计<span>¥<i class="pay-total-num">${shoppingCartTotalFee}</i></span>(不含运费)</p>
						<!-- <p class="pay-freight">(不含运费)</p> -->
			       </div>
			   </div>
			   <div class="payment-settle-btn">
			       <a href="${beiken}/beiken/beikenOrderAction!goConfirmOrder.action">提交订单</a>
			    </div>
			</div>
			<!-- 编辑页面 -->
			<!-- <div class="editfooter">
			     <div class="checkAll">
			         <div class="check-icon bnj-sprite-icon checkAllBtn"></div><label>全选</label>
			     </div>
			     <a href="javascript:;" class="delete-settle-btn">删除</a>
			 </div>-->
		</div>
	</c:when>
	<c:otherwise>
		<div class="wrap" style="padding-top: 100px;">
	        <div class="shop-group">
	        	<div class="nogoods">您还没有添加商品到购物车！</div>
	        	<div class="toshop">
	        		<a href="${beiken}/beiken/beikenIndexAction!goIndex.action?key=home">去购物</a>
	        	</div>
	        </div>
	    </div>
	</c:otherwise>
</c:choose>