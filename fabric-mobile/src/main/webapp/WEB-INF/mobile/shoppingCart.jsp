<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-购物车</title>
</head>
<body>
<header>
	<!-- <a href="javascript:history.back();" class="b-header-code b-header-return"></a> -->购物车
	<!-- <a href="#" class="b-header-edit" style="right: 0;">编辑</a> -->
</header>
<section class="shopCart">
	<c:choose>
		<c:when test="${requireGoodsOrderList != null && fn:length(requireGoodsOrderList) > 0}">
			<div class="shopCart_con">
				<c:forEach items="${requireGoodsOrderList}" var="order">
			        <div class="shop-group-item b_t mb10">
			            <div class="shop-title b_b">
			                <!-- <div class="check-icon bnj-sprite-icon"></div> -->
			                <div class="shop-title-r"><div class="title"><a href="#"><span class="shop-icon bnj-sprite-icon"></span>(${order.code})<em class="arrow bnj-sprite-icon"></em></a></div></div>
			            </div>
			            <ul class="shop-cart-list">
			   				<c:forEach items="${requireGoodsOrderItemMap}" var="itemMap">
			   					<c:if test="${itemMap.key == order.id}">
			   						<c:forEach items="${itemMap.value}" var="item">
			   							<li class="shop-cart-item b_b">
						                    <!-- <div class="check-icon bnj-sprite-icon"></div> -->
						                    <div class="shop-cart-item-r">
						                        <div class="i-p"><a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.itemId}"><img src="${beiken}/mobilecommon/base/images/demoimg/collect_pic2.jpg" alt=""></a></div>
						                        <div class="i-t">
						                            <div class="i-t-title"><a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.itemId}"">${item.title}(${item.specification})</a></div>
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
		               <!--  <div class="checkAll">
		                    <div class="check-icon bnj-sprite-icon checkAllBtn"></div><label>全选</label>
		                </div> -->
		                <div class="payment-total-money" style="padding-right: 10px;">
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
</section>
<%@ include file="/mobilecommon/page/foot.jsp"%>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
    $(function(){
        $('.shop-title .check-icon,.shop-cart-item .check-icon,.checkAll .check-icon').click(function(){
            if($(this).hasClass('cur')){
                $(this).removeClass('cur');
            }else{
                $(this).addClass('cur');
            }
        });

        /* $(".b-header-edit").click(function(){
            if ($("section").hasClass("colEdi")) {
                $(this).text("编辑");
                $("section").removeClass("colEdi");
            } else {
                $(this).text("完成");
                $("section").addClass("colEdi");
            }
        }); */

        //购物车出现小红数字js
        var $iQuantityIncrease = $('.quantity-increase');
        var $iQuantityDecrease = $('.quantity-decrease');
        var $iAdd= $('#add');
        $iQuantityIncrease.on('click',function(e){
           $('#add').show();
        });
        $iQuantityDecrease.on('click',function(e){
           $('#add').hide();
        });
    })
    // 更新购物车数量
    function updateItmeCount(num,itemId,type){
    	if(type == 'reduce' && num == 1){
    		$.toptip("请至少选择一件商品");
			return ;
    	}
    	$.ajax({
			url : "${beiken}/beiken/beikenShoppingCartAction!updateItmeCount.action",
			cache : false,
			data : {
				"itemId" : itemId,
				"type" : type
			},
			success : function(result){
				var r= result.split(":");
				if(r[0] != '0'){
					var url = "${beiken}/beiken/beikenShoppingCartAction!loadShoppingCart.action";
					$(".shopCart").load(url);
					$("#add").load("${beiken}/beiken/beikenShoppingCartAction!loadShoppingCartCount.action");
					$("#add").show();
				}else{
					$.toptip(r[1]);
					return ;
				}
			}
		})
    }
    // 更新购物车价格
    function updateItmePrice(itemId,obj){
    	var price = Number($(obj).val());
    	$.ajax({
			url : "${beiken}/beiken/beikenShoppingCartAction!updateItmePrice.action",
			cache : false,
			data : {
				"itemId" : itemId,
				"price" : price
			},
			success : function(result){
				var r= result.split(":");
				if(r[0] != '0'){
					var url = "${beiken}/beiken/beikenShoppingCartAction!loadShoppingCart.action";
					$(".shopCart").load(url);
					$("#add").load("${beiken}/beiken/beikenShoppingCartAction!loadShoppingCartCount.action");
					$("#add").show();
				}else{
					$.toptip(r[1]);
					return ;
				}
			}
		})
    }
    
    /** 删除购物车 */
    function deleteItem(itemId){
    	$.confirm("确认删除该商品吗?", "提示信息", function() {
    		$.ajax({
    			url : "${beiken}/beiken/beikenShoppingCartAction!deleteItem.action",
    			cache : false,
    			data : {
    				"itemId" : itemId
    			},
    			success : function(result){
    				var r= result.split(":");
    				if(r[0] != '0'){
    					var url = "${beiken}/beiken/beikenShoppingCartAction!loadShoppingCart.action";
    					$(".shopCart").load(url);
    					$("#add").load("${beiken}/beiken/beikenShoppingCartAction!loadShoppingCartCount.action");
    				}else{
    					$.toptip(r[1]);
    					return ;
    				}
    			}
    		})
    	}, function() {
		});
    }

</script>
