<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-确认订单</title>
</head>
<body>
<header>
    <%-- <a href="${beiken}/beiken/beikenShoppingCartAction!goIndex.action?key=shoppingcart" class="b-header-code b-header-return"></a>确认订单 --%>
    <a href="${beiken}/beiken/beikenShoppingCartAction!goIndex.action?key=shoppingcart" class="b-header-code b-header-return"></a>确认订单
</header>
<section class="surOrd">
    <div class="view back_gray">
        <div class="wrap submit-order">
            <!-- 收货地址 -->
            <div class="addr-box mb10 b_b">
            	<c:choose>
            		<c:when test="${address == null}">
            			<a href="${beiken}/beiken/beikenUserCenterAction!goConsignee.action?type=order" class="r-info">
		                    <p class="c_gray">点击添加收货地址</p>
		                    <div class="bnj-sprite-icon arrow_r"></div>
		                </a>
            		</c:when>
            		<c:otherwise>
            			<a href="${beiken}/beiken/beikenUserCenterAction!goConsignee.action?type=order" class="r-info">
		                    <input id="consigneeId" type="hidden" value="${address.id}">
		                    <h3 class="mui-flex">
		                        <span class="cell t_l">收货人:${address.consigneeName}</span>
		                        <span class="cell t_r">电话:${address.mobilePhone}</span>
		                    </h3>
		                    <p class="c_gray">${address.province.name}&nbsp;&nbsp;${address.city.name}&nbsp;&nbsp;${address.district.name}&nbsp;&nbsp;${address.address}</p>
		                    <div class="bnj-sprite-icon arrow_r"></div>
		                </a>
            		</c:otherwise>
            	</c:choose>
            </div>
            <div class="order-box">
                <div class="order-item b_t b_b mb10 ">
                    <div class="order-item-title b_b plr10 mui-flex">
                        <div class="t-shopname  t_l"><span class="t-name">(${requireGoodsOrder.code})<!-- <i class="bnj-sprite-icon arrow"> --></i></span></div>
                        <!-- <div class="t-btn  t_r"><a href="javascript:;" class="t-tel">联系商家</a></div> -->
                    </div>
                    <ul class="order-item-pro">
                    	<c:if test="${requireGoodsOrderItemList != null && fn:length(requireGoodsOrderItemList) > 0}">
                    		<c:forEach items="${requireGoodsOrderItemList}" var="item">
                    			<li class="goods-item">
		                            <div class="i-p"><a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.itemId}"><img src="${beiken}/mobilecommon/base/images/demoimg/collect_pic2.jpg" alt=""></a></div>
		                            <div class="i-t"><div class="i-t-title"><a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.itemId}"><h3>${item.title}(${item.specification})</h3></a></div>
		                                <div class="i-t-price">
		                                    <span class="price-num c_red"><i class="mui-price-rmb">¥</i>${item.price}</span>
		                                    x<span>${fn:split(item.amount,'.')[0]}</span>
		                                </div>
		                            </div>
		                        </li>
                    		</c:forEach>
                    	</c:if>
                    </ul>
                    <div class="p10 leavewords"><textarea name="" id="buyerMessage" class="words" placeholder="给商家留言（100字以内）" maxlength="100"></textarea></div>
                </div>
            </div>

			 <!-- <div class="usercoin plr10 b_b b_t mb10">
                <a href="#" class="dis_block pffs">配送方式 
                	<input type="text" id="deliveryType" placeholder="请选择配送方式" class="rg pfval" readonly="readonly">
                	<span class="bnj-sprite-icon arrow_b"></span>
                </a>
				<form action="" method="get"  class="pf_fs">
                    <label><input name="Fruit" type="radio" value="顺丰快递" />顺丰快递 </label><br>
                    <label><input name="Fruit" type="radio" value="中通快递" />中通快递</label><br>
                </form>
				
            </div> -->
            <c:if test="${employee.type == '1'}">
	            <div class="usercoin plr10 b_b b_t mb10">
	                <a href="javascript:void(0);" class="dis_block xzkh">选择客户
	                	<input type="hidden" id="channelId" value="">
	                	<input type="text" placeholder="请选择客户" class="rg service" readonly="readonly">
	                	<span class="bnj-sprite-icon arrow_r busPhone"></span>
	                </a>
	            </div>
	     	</c:if>
            <div class="usercoin plr10 b_b b_t mb10">
                <a href="#" class="dis_block zffs" style="margin-bottom:5px;">支付方式 
                	<input type="hidden" id="payType" value="">
                	<input type="text" placeholder="请选择支付方式" class="rg zfval" readonly="readonly">
                	<span class="bnj-sprite-icon arrow_b"></span>
                </a>
                <form action="" method="get"  class="zf_fs">
                    <label><input name="Fruit" type="radio" value="wx" />微信支付 </label><br>
                    <label><input name="Fruit" type="radio" value="cash" />线下支付</label><br>
                </form>
            </div>
            <div class="order-price p10 b_b b_t">
                <div class="mui-flex">
                    <div class="cell">商品金额:</div><div class="cell t_r c_red">￥<span class="totalPrice">${requireGoodsOrder.amount}</span></div>
                </div>
                <!-- <div class="mui-flex">
                    <div class="cell">运费:</div><div class="cell t_r c_red">￥<span class="deliveryFee">0</span></div>
                </div>
                <div class="mui-flex">
                    <div class="cell">优惠券抵:</div><div class="cell t_r c_red">-￥<span class="usecoupon">0</span></div>
                </div>
                <div class="mui-flex">
                    <div class="cell">农币抵:</div><div class="cell t_r c_red">-￥<span class="useScore">0</span></div>
                </div> -->
            </div>
        </div>
    </div>
    <div class="order-footer">
        <div class="order-submit-price">实付款:	<span>￥<i>${requireGoodsOrder.amount}</i></span></div>
        <div class="order-submit-btn"><a href="javascript:submitOrder('${requireGoodsOrder.id}');">提交订单</a></div>
    </div>
    <input type="hidden" id="employeeType" value="${employee.type}">
</section>
<!-- 电话弹框---->
<c:if test="${employee.type == '1'}">
	<div class="dialog diaPhn">
	    <div class="dialog_middle">
	        <div class="dialog_con">
	            <h1>请选择客户</h1>
	            <c:if test="${channelDistributorDetailList != null && fn:length(channelDistributorDetailList) > 0}">
	            	<c:forEach items="${channelDistributorDetailList}" var="channelDetail">
	            		<a href="javascript:void(0);" id="${channelDetail.channelDistributor.id}">${channelDetail.channelDistributor.name}</a>
	            	</c:forEach>
	            </c:if>
	        </div>
	    </div>
	</div>
</c:if>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script>
	$(function () {
	    $('.xzkh').click(function (event) {
	        event.stopPropagation();
	        var offset = $(event.target).offset();
	        $('.diaPhn').css('display','table');
	    });
		$('.dialog').click(function(){
			$('.diaPhn').css('display','none');
		})
		$('.dialog_con a').click(function(){
			$('.diaPhn').css('display','none');
			$("#channelId").val($(this).attr("id"));
			$('.service').val($(this).html());
		})
	});
	
	$(".pffs").click(function(){
		$(".pf_fs").slideToggle();
	})
	$(".pf_fs label input").click(function(){
        var html=$(this).val();
        $(".pfval").val("");
        $(".pfval").val(html);
        $(".pf_fs").slideToggle();
    })

    $(".zffs").click(function(){
        $(".zf_fs").slideToggle();
    });
    $(".zf_fs label input").click(function(){
        var value = $(this).val();
        var html = $(this).parent('label').text();
        $(".zfval").val("");
        $(".zfval").val(html);
        $("#payType").val(value);
        $(".zf_fs").slideToggle();
    })
    
    function submitOrder(orderId){
    	var employeeType = $("#employeeType").val();
    	var consigneeId = $("#consigneeId").val();
    	var channelId = $("#channelId").val();
    	var buyerMessage = $("#buyerMessage").val();
    	//var deliveryType = $("#deliveryType").val();
    	var payType = $("#payType").val();
    	if(!consigneeId){
    		$.toptip("请填写收货地址");
			return ;
    	}
    	if(employeeType == '1' && !channelId){
    		$.toptip("请选择客户");
			return ;
    	}
    	/* if(!deliveryType){
    		$.toptip("请选择配送方式");
			return ;
    	} */
    	if(!payType){
    		$.toptip("请选择支付方式");
			return ;
    	}
    	$.ajax({
			url : "${beiken}/beiken/beikenOrderAction!submitOrder.action",
			cache : false,
			data : {
				"orderId" : orderId,
				"buyerMessage" : buyerMessage,
				"payType" : payType,
				"consigneeId" : consigneeId,
				"channelId" : channelId
			},
			success : function(result){
				var r= result.split(":");
				if(r[0] != '0'){
					if(payType == 'wx'){
						document.location.href = '${beiken}/beiken/beikenOrderAction!goOrderPayment.action?orderId='+r[1];
					}else{
						document.location.href = '${beiken}/beiken/beikenOrderAction!goOrderPaymentSuccess.action?orderId='+r[1];
					}
				}else{
					$.toptip(r[1]);
					return ;
				}
			}
		})
    }
</script>