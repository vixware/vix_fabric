<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html style="height: 100%;overflow: hidden;">
<head>
    <%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-分类</title>
</head>
<body>
<header style="position: static;">
    <a href="javascript:history.back();" class="b-header-code b-header-return"></a>
    <div style="padding-top: 7px;">
        <div class="b-header-search" style="margin-top: 0;">
            <a href="${beiken}/beiken/beikenIndexAction!goSearch.action"><b></b>搜索医疗器械</a>
        </div>
    </div>
    <!-- <a href="#" class="b-header-more"></a> -->
</header>
<section class="subcategory">
    <ul class="subCatGor_l">
    	<c:if test="${employee.type == '2'}">
    		<c:if test="${storeItemCatalogList != null && fn:length(storeItemCatalogList) > 0}">
	    		<c:forEach items="${storeItemCatalogList}" var="itemCatalog" varStatus="s">
	    			<li <c:if test="${s.index == 0}">class="vis"</c:if> id="${itemCatalog.id}">${itemCatalog.name}</li>
	    		</c:forEach>
	    	</c:if>
    	</c:if>
    	<c:if test="${employee.type == '1'}">
	    	<c:if test="${firstItemCatalogList != null && fn:length(firstItemCatalogList) > 0}">
	    		<c:forEach items="${firstItemCatalogList}" var="itemCatalog" varStatus="s">
	    			<li <c:if test="${s.index == 0}">class="vis"</c:if> id="${itemCatalog.id}">${itemCatalog.name}</li>
	    		</c:forEach>
	    	</c:if>
	    </c:if>
    </ul>
    <div class="subCatGor_r">
        <ul>
        	<c:if test="${employee.type == '2'}">
        		<li><a href="${beiken}/beiken/beikenCategoryAction!goProductList.action?id=${storeItemCatalog.id}">全部</a> </li>
        	</c:if>
        	<c:if test="${employee.type == '1'}">
	            <li><a href="${beiken}/beiken/beikenCategoryAction!goProductList.action?id=${itemCatalog.id}">全部</a> </li>
	            <c:if test="${secondItemCatalogList != null && fn:length(secondItemCatalogList) > 0}">
	        		<c:forEach items="${secondItemCatalogList}" var="catalog" varStatus="s">
	            		<li><a href="${beiken}/beiken/beikenCategoryAction!goProductList.action?id=${catalog.id}">${catalog.name}</a> </li>
	            	</c:forEach>
	            </c:if>
			</c:if>
        </ul>
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
            $(".subCatGor_r").load("${beiken}/beiken/beikenCategoryAction!loadItemCatalog.action?id="+id);
        });


    });

</script>
