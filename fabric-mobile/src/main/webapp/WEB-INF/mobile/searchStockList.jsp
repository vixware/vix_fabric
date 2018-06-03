<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-查询库存</title>
</head>
<body>
<!-- <header>
    <a href="javascript:history.back();" class="b-header-code b-header-return"></a>
    <div class="b-header-search">
        <span class="b-header-search-icon bnj-sprite-icon"></span>
        <div class="b-search-form-input">
            <input id="itemcode" type="text" maxlength="20" autocomplete="off" name="keyword" value="" placeholder="输入商品编码查询库存">
        </div>
    </div>
    <div class="b-header-text search" onclick="searchStock();">查询</div>
</header> -->
<header style="position: static;">
    <a href="javascript:history.back();" class="b-header-code b-header-return"></a>
    <div style="padding-top: 7px;">
        <div class="b-header-search" style="margin-top: 0;">
            <a href="${beiken}/beiken/beikenUserCenterAction!goSearchStock.action"><b></b>输入商品编码查询库存</a>
        </div>
    </div>
</header>
<section class="subcategory">
    <ul class="subCatGor_l">
    	<c:if test="${firstItemCatalogList != null && fn:length(firstItemCatalogList) > 0}">
    		<c:forEach items="${firstItemCatalogList}" var="itemCatalog" varStatus="s">
    			<li <c:if test="${s.index == 0}">class="vis"</c:if> id="${itemCatalog.id}">${itemCatalog.name}</li>
    		</c:forEach>
    	</c:if>
    </ul>
    <div class="subCatGor_r">
    	<c:choose>
	    	<c:when test="${inventoryCurrentStockList != null && fn:length(inventoryCurrentStockList) > 0}">
		        <div class="list-view horizontal_layout">
		            <ul>
		           		<c:forEach items="${inventoryCurrentStockList}" var="inventory">
		           			<li class="goods-item" id="688">
			                    <div class="list-item">
			                        <div class="i-p">
			                            <a href="javascript:void(0)">
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
</section>
<%@ include file="/mobilecommon/page/foot.jsp"%>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
	$(function(){
	    var herderH = $('header').outerHeight(true);
	    if($('html,body').height()>=$(window).height()){
	        $('.subCatGor_l,.subCatGor_r ul').height($(window).height()-herderH).css('overflow-y','auto');
	    }
	    $(window).resize(function(){
	        if($('html,body').height()>=$(window).height()){
	            $('.subCatGor_l,.subCatGor_r ul').height($(window).height()-herderH).css('overflow-y','auto');
	        }
	    })
	
	    //$('.subCatGor_r .list-view:gt(0)').hide(0);
	    $('.subCatGor_l li').click(function(){
	        var index = $(this).index();
	        $(this).addClass('vis').siblings('li').removeClass('vis');
	        //$('.subCatGor_r .list-view').eq(index).show(0).siblings('.list-view').hide(0);
	        var id = $(this).attr("id");
            $(".subCatGor_r").load("${beiken}/beiken/beikenUserCenterAction!loadSearchStock.action?id="+id);
	    })
	});
</script>
