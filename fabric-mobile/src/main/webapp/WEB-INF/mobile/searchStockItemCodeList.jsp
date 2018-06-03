<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-库存</title>
</head>
<body>
<header>
    <a href="javascript:history.back();" class="b-header-code b-header-return"></a>查询库存
</header>
<section class="gooLis">
	<div class="view pb0" style="padding-top: 50px;">
		<div class="wrap">
			<c:choose>
				<c:when test="${inventoryCurrentStockList != null && fn:length(inventoryCurrentStockList) > 0}">
					<div class="list-view horizontal_layout">
						<ul>
							<c:forEach items="${inventoryCurrentStockList}" var="inventory">
								<li class="goods-item" id="688">
				                    <div class="list-item">
				                        <div class="i-p">
				                            <a href="javascript:void(0);">
				                            	<img src="${beiken}/mobilecommon/base/images/demoimg/collect_pic2.jpg" alt="">
				                            </a>
				                        </div>
				                        <div class="i-t">
				                            <div class="i-t-title"><h3 class="ellips">${inventory.itemname}</h3></div>
				                            <div class="i-t-main"><span class="i-t-area">${inventory.warehouse}</span></div>
				                            <div class="i-t-icon"></div>
				                            <div class="i-t-price"><span class="price-num c_red mr10">库存:${fn:split(inventory.quantity,'.')[0]}</span></div>
				                        </div>
				                    </div>
				                </li>
							</c:forEach>
			            </ul>
					</div>
				</c:when>
				<c:otherwise>
					<div class="bs_nodata"><p class="nodata-img"></p><p><a href="javascript:;">暂无数据</a></p></div>
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
</script>
