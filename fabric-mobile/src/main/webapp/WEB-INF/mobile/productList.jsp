<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-商品列表</title>
</head>
<body>
<header>
    <a href="javascript:history.back();" class="b-header-code b-header-return"></a>
    <div class="b-header-search">
		<a href="${beiken}/beiken/beikenIndexAction!goSearch.action"><b></b>搜索医疗器械</a>
    </div>
</header>
<section class="gooLis">
	<div class="view pb0" style="padding-top: 50px;margin-bottom: 50px;">
		<div class="wrap">
			<c:choose>
				<c:when test="${employee.type == '2'}">
					<c:choose>
						<c:when test="${storeItemList != null && fn:length(storeItemList) > 0}">
							<div class="list-view horizontal_layout">
								<ul>
									<c:forEach items="${storeItemList}" var="item">
										<li class="goods-item" id="688">
						                    <div class="list-item">
						                        <div class="i-p">
						                            <a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.id}">
						                            	<img src="${beiken}/mobilecommon/base/images/demoimg/collect_pic2.jpg" alt="">
						                            </a>
						                        </div>
						                        <div class="i-t">
						                            <div class="i-t-title">
						                            	<h3 class="ellips">
						                            		<a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.id}">${item.name}(${item.specification})</a>
						                            	</h3>
						                            </div>
						                            <div class="i-t-main"><%-- <span class="i-t-area">${item.origin}</span><span> 已售<em>1</em></span> --%></div>
						                            <div class="i-t-icon"></div>
						                            <div class="i-t-price"><span class="price-num c_red mr10"><i class="mui-price-rmb">¥</i>${item.purchasePrice}</span></div>
						                        </div>
						                        <a href="javascript:addShoppingCart('${item.id}');"><div class="i-shop-cart bs_shoplistcar" data-itemid="688"><span></span></div></a>
						                    </div>
						                </li>
									</c:forEach>
				            	</ul>
							</div>
							<div id="paging_span"><div class="bs_loading"><p style="margin-top:5px;"><a href="javascript:void(0);" style="font-size:14px;">没有更多</a></p></div></div>
							<div class="go_top"></div>
							<input id="orderId" type="hidden" value="">
						</c:when>
						<c:otherwise>
							<div class="bs_nodata"><p class="nodata-img"></p><p><a href="javascript:;">暂无数据</a></p></div>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${employee.type == '1'}">
					<c:choose>
						<c:when test="${itemList != null && fn:length(itemList) > 0}">
							<div class="list-view horizontal_layout">
								<ul>
									<c:forEach items="${itemList}" var="item">
										<li class="goods-item" id="688">
						                    <div class="list-item">
						                        <div class="i-p">
						                            <a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.id}">
						                            	<img src="${beiken}/mobilecommon/base/images/demoimg/collect_pic2.jpg" alt="">
						                            </a>
						                        </div>
						                        <div class="i-t">
						                            <div class="i-t-title">
						                            	<h3 class="ellips">
						                            		<a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${item.id}">${item.name}(${item.specification})</a>
						                            	</h3>
						                            </div>
						                            <div class="i-t-main"><%-- <span class="i-t-area">${item.origin}</span><span> 已售<em>1</em></span> --%></div>
						                            <div class="i-t-icon"></div>
						                            <div class="i-t-price"><span class="price-num c_red mr10"><i class="mui-price-rmb">¥</i>${item.price}</span></div>
						                        </div>
						                        <a href="javascript:addShoppingCart('${item.id}');"><div class="i-shop-cart bs_shoplistcar" data-itemid="688"><span></span></div></a>
						                    </div>
						                </li>
									</c:forEach>
				            	</ul>
							</div>
							<div id="paging_span"><div class="bs_loading"><p style="margin-top:5px;"><a href="javascript:;" style="font-size:14px;">没有更多</a></p></div></div>
							<div class="go_top"></div>
							<input id="orderId" type="hidden" value="">
						</c:when>
						<c:otherwise>
							<div class="bs_nodata"><p class="nodata-img"></p><p><a href="javascript:;">暂无数据</a></p></div>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
		</div>
	</div>
</section>
<%@ include file="/mobilecommon/page/foot.jsp"%>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
	function goShoppingCart(){
		var orderId = $("#orderId").val();
		alert(orderId);
		document.location.href = "${beiken}/beiken/beikenShoppingCartAction!goIndex.action?key=shoppingcart&orderId=" + orderId;
	}
	function addShoppingCart(itemId){
		var orderId = $("#orderId").val();
		$.ajax({
			url : "${beiken}/beiken/beikenShoppingCartAction!addShoppingCart.action",
			cache : false,
			data : {
				"itemId" : itemId,
				"orderId" : orderId
			},
			success : function(result){
				var r= result.split(":");
				if(r[0] != '0'){
					$("#orderId").val(r[1].trim());
					var cartnum = $("#add").text();
					$("#add").text(Number(cartnum) + 1);
				}else{
					$.toptip(r[1]);
					return ;
				}
			}
		})
	}
</script>
