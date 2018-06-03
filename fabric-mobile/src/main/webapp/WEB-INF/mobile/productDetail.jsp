<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<title>贝垦-商品详情</title>
</head>
<body>
<header>
    <a href="javascript:history.back();" class="b-header-code b-header-return"></a>商品详情
    <!-- <a href="#" class="b-header-more"></a> -->
</header>
<section class="proDet">
    <div class="banner" id="banner">
        <ul class="bd">
        	<c:choose>
        		<c:when test="${itemImageList != null && fn:length(itemImageList) > 0}">
        			<c:forEach items="${itemImageList}" var="item">
        				<li><img src="${beiken}/${item.imgPath}" alt=""></li>
        			</c:forEach>
        		</c:when>
        		<c:otherwise>
        			<li><img src="${beiken}/mobilecommon/base/images/demoimg/subjectsub6.png" alt=""></li>
            		<li><img src="${beiken}/mobilecommon/base/images/demoimg/subjectsub7.png" alt=""></li>
        		</c:otherwise>
        	</c:choose>
        </ul>
        <ol class="hd">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ol>
    </div>
    <div class="proDet_list1 mb10">
    	<c:choose>
    		<c:when test="${employee.type == '2'}">
    			<h1 class="p10 b_b">${storeItem.name}(${storeItem.specification})</h1>
        		<h2 class="c_red b_b">￥${storeItem.purchasePrice}</h2>
    		</c:when>
    		<c:when test="${employee.type == '1'}">
    			<h1 class="p10 b_b">${item.name}(${item.specification})</h1>
        		<h2 class="c_red b_b">￥${item.price}</h2>
    		</c:when>
    	</c:choose>
        <!-- <h3 class="p10 b_b"><b>限区域</b><b class="orange_btn">农币抵100%</b></h3> -->
        <%-- <h4 class="p10 b_b"><span >运费:10.00</span><span>月销售：0笔</span><span>${item.origin}</span></h4> --%>
    </div>
    <%-- <div class="selPar"><a href="#"><span>已选</span><strong>金、全网通、非合约机、1件</strong></a> </div>
    <div class="freWit" id="freWit">
        <div class="title"><a href="#">自由搭配</a> </div>
        <div class="con">
            <div class="bd">
                <ul>
                    <li><a href="#"><img src="${beiken}/mobilecommon/base/images/demoimg/collect_pic1.jpg"><span>进口大芒果</span></a> </li>
                    <li><a href="#"><img src="${beiken}/mobilecommon/base/images/demoimg/selWel_pic1.jpg"><span>进口大芒果</span></a> </li>
                    <li><a href="#"><img src="${beiken}/mobilecommon/base/images/demoimg/selWel_pic2.jpg"><span>进口大芒果</span></a> </li>
                </ul>
                <ul>
                    <li><a href="#"><img src="${beiken}/mobilecommon/base/images/demoimg/collect_pic1.jpg"><span>进口大芒果</span></a> </li>
                    <li><a href="#"><img src="${beiken}/mobilecommon/base/images/demoimg/selWel_pic1.jpg"><span>进口大芒果</span></a> </li>
                    <li><a href="#"><img src="${beiken}/mobilecommon/base/images/demoimg/selWel_pic2.jpg"><span>进口大芒果</span></a> </li>
                </ul>
            </div>
        </div>
    </div> --%>
    <%-- <div class="proDet_list2 b_t b_b mb10">
        <h1 class="p10 b_b">商品评价(0)</h1>
        <h2 class="p10"><a href="${beiken}/beiken/beikenProductAction!goProductComment.action" class="more">查看更多评价</a> </h2>
    </div> --%>
    <div class="proDet_list4 b_t">
        <div class="tab_menu">
            <ul>
                <!-- <li class="selected">商品详情</li> -->
                <li>商品详情</li>
                <!-- <li>店铺推荐</li> -->
            </ul>
        </div>
        <div class="tab_con">
            <div class="common">
            	<c:choose>
		    		<c:when test="${employee.type == '2'}">
		    			${storeItem.memo}
		    		</c:when>
		    		<c:when test="${employee.type == '1'}">
		    			${item.memo}
		    		</c:when>
		    	</c:choose>
                <img src="${beiken}/mobilecommon/base/images/demoimg/subjectsub6.png" alt="">
                <hr>
                <img src="${beiken}/mobilecommon/base/images/demoimg/subjectsub7.png" alt="">
            </div>
            <div class="common">
                <div class="pro-container">
                    <div class="pro-imgtext" style="display: none;">
                        <div class="tips c_gray">详情图文较多，会消耗较多流量，建议在wifi环境下浏览<br>
                            <a href="javascript:;" id="getCon" class="c_gray">点击查看图文详情</a>
                        </div>
                    </div>

                    <%-- <div class="pro-recommend">
                        <div class="list-view vertical_layout back_gray">
                            <ul>
                            	<c:if test="${itemList != null && fn:length(itemList) > 0}">
                            		<c:forEach items="${itemList}" var="items">
                            			<li class="goods-item" id="1532">
			                                <div class="list-item">
			                                    <div class="i-p">
			                                        <a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${items.id}">
			                                        	<img src="${beiken}/mobilecommon/base/images/demoimg/collect_pic2.jpg" alt="">
			                                        </a>
			                                    </div>
			                                    <div class="i-t">
			                                        <div class="i-t-title">
			                                            <h3>
			                                                <a href="${beiken}/beiken/beikenProductAction!goProductDetail.action?id=${items.id}">${items.name}</a>
			                                          	</h3>
			                                    	</div>
			                                    	<div class="i-t-main">
			                                    		<span class="i-t-area">${items.origin}</span>
			                                    		<span> 已售<em>0</em></span>
			                                    	</div>
			                                    	<div class="i-t-icon">
			                                    		<span class="limit">限</span>
			                                    	</div>
			                                    	<div class="i-t-price">
			                                    		<span class="price-num c_red mr10"><i class="mui-price-rmb">¥</i>${items.price}</span>
			                                    		<span class="i-t-freight">运费:4.00</span>
			                                    	</div>
			                                    </div>
			                    				<div class="i-shop-cart bs_shoplistcar" data-itemid="1532" onclick="addShoppingCart('${items.id}');">
			                    					<span></span>
			                    				</div>
			                    			</div>
		                    			</li>
                            		</c:forEach>
                            	</c:if>
                    		</ul>
                    	</div>
                    </div> --%>
          		</div>
            </div>
        </div>
    </div>
	<input type="hidden" id="orderId" value="">
</section>
<footer class="b_buy">
    <ul>
        <!-- <li><a href="#"><b></b><span>客服电话</span></a> </li>
        <li><a href="javascript:;"><b class="icon2"></b><span>收藏</span></a> </li> -->
        <li>
        	<a href="${beiken}/beiken/beikenShoppingCartAction!goIndex.action?key=shoppingcart">
        		<b class="icon3"></b><span>购物车</span>
        		<i class="cartnum">
        			<c:choose>
	        			<c:when test="${shoppingCartCount != null && shoppingCartCount > 0}">${fn:split(shoppingCartCount,'.')[0]}</c:when>
	        			<c:otherwise>0</c:otherwise>
	        		</c:choose>
        		</i>
        	</a> 
        </li>
        <c:choose>
    		<c:when test="${employee.type == '2'}">
    			<li class="add-to-cart"><a href="javascript:addShoppingCart('${storeItem.id}');">加入购物车</a> </li>
    		</c:when>
    		<c:when test="${employee.type == '1'}">
    			<li class="add-to-cart"><a href="javascript:addShoppingCart('${item.id}');">加入购物车</a> </li>
    		</c:when>
    	</c:choose>
    </ul>
</footer>
</body>
</html>
<%@ include file="/mobilecommon/page/resource_js.jsp"%>
<script type="text/javascript">
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
					var cartnum = $(".cartnum").text();
					$(".cartnum").text(Number(cartnum) + 1);
				}else{
					$.toptip(r[1]);
					return ;
				}
			}
		})
	}
    $(function(){
        TouchSlide({
            slideCell:"#banner",
            titCell:".hd",
            mainCell:".bd",
            effect:"left",
            autoPlay:true,
            autoPage:'<li>&nbsp;1</li>'
        });
    });

    $(function(){
        TouchSlide({
            slideCell:"#freWit",
            autoPlay:false
        });
    });

    $(function(){
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
    });

    $('footer.b_buy ul li .icon2').click(function(){
        if($(this).hasClass('cur')){
            $(this).removeClass('cur');
        }else{
            $(this).addClass('cur');
        }
    });

    $('.proDetDia ul li strong b').click(function(){
        $(this).addClass('cur').siblings('b').removeClass('cur');
    });

    $('.proDetDia .close').click(function(){
        $('.proDetDia').hide();
    })

    $('.selPar').click(function(){
        $('.proDetDia').css('display','table');
    })

</script>
