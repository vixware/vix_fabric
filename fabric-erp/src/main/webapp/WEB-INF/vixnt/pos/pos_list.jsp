<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<html lang="en-us">
<head>
<meta charset="utf-8">
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<%@ include file="/vixntcommon/page/resource_css.jsp"%>
<!-- FAVICONS -->
<link rel="shortcut icon" href="img/favicon/favicon.ico" type="image/x-icon">
<link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">

<link rel="apple-touch-icon" href="img/splash/sptouch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="76x76" href="img/splash/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="120x120" href="img/splash/touch-icon-iphone-retina.png">
<link rel="apple-touch-icon" sizes="152x152" href="img/splash/touch-icon-ipad-retina.png">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="apple-touch-startup-image" href="img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
<link rel="apple-touch-startup-image" href="img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
<link rel="apple-touch-startup-image" href="img/splash/iphone.png" media="screen and (max-device-width: 320px)">
</head>
<body class="">
	<div id="main" role="main" style="margin-left: 0px;">
		<div id="content">
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<div class=row>
							<div class="col-sm-5 pad-l pad-r" style="height: 660px; position: relative;">
								<div class="tool-header">
									<div class="tool-header-msg">
										<input id="channelId" type="hidden" value="${channelDistributor.id}">
										<div>门店名称：${channelDistributor.name}</div>
										<div>门店编码：${channelDistributor.code}</div>
									</div>
									<div id="customerAccount">
										<c:if test="${customerAccount != null}">
											<c:choose>
												<c:when test="${customerAccount.headImage != null && customerAccount.headImage != ''}">
													<img src="${customerAccount.headImage}" style="width: 30px;height: 30px">
												</c:when>
												<c:otherwise>
													<img src="${nvix}/vixntcommon/base/img/icon-pos/photo.jpg">
												</c:otherwise>
											</c:choose>
											<input id="customerAccountId" type="hidden" value="${customerAccount.id}">
											<input id="money" type="hidden" value="${customerAccount.money}">
											<input id="point" type="hidden" value="${customerAccount.point}">
											<div class="tool-header-msg">
												<div>会员：${customerAccount.name}</div>
												<div>手机号：${customerAccount.mobilePhone}</div>
											</div>
											<div class="tool-header-msg">
												<c:choose>
													<c:when test="${customerAccount.point != null && customerAccount.point > 0}">
														<div>可用积分：${customerAccount.point}</div>
													</c:when>
													<c:otherwise>
														<div>可用积分：0</div>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${customerAccount.money != null && customerAccount.money > 0}">
														<div>可用余额：${customerAccount.money}</div>
													</c:when>
													<c:otherwise>
														<div>可用余额：0</div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="tool-header-msg">
												<div>会员等级：${customerAccount.memberLevelName}</div>
												<div>会员折扣：${customerAccount.discount}%</div>
											</div>
										</c:if>
									</div>
								</div>
								<div class="tool-search">
									<input id="itemCode" type="text" value="" placeholder="扫描商品条形码或输入商品编号" readonly="readonly">
									<div class="tool-search-box">
										<i class="icon-search"></i>
									</div>
								</div>
								<div class="tool-goods">
									<div class="pro-head">
										<div class="pro-head-msg">序号</div>
										<div class="pro-head-msg">商品编码</div>
										<div class="pro-head-msg">商品名称</div>
										<div class="pro-head-msg">数量</div>
										<div class="pro-head-msg">单价</div>
										<div class="pro-head-msg">小计</div>
									</div>
									<div id="order">
									</div>
								</div>
								<div class="tool-footer">
									<div class="tool-js" id="orderCountAndPrice">
										<div class="tool-js-msg">
											数量：<span id="orderCount" style="font-size: 25px;">0</span>
										</div>
										<div class="tool-js-msg">
											金额：<span id="orderPrice" style="font-size: 25px;">￥0.00</span>
										</div>
										<!-- <div class="tool-js-msg">
											优惠：<span>￥65.00</span>
										</div> -->
									</div>
									<div class="tool-bar">
										<i class="icon-down "></i>
									</div>
									<div class="tool-count">
										<div class="tool-count-l">
											<div class="js-btn" style="cursor: pointer;" onclick="updateItemCount('count',7);">7</div>
											<div class="js-btn" style="cursor: pointer;" onclick="updateItemCount('count',8);">8</div>
											<div class="js-btn" style="cursor: pointer;" onclick="updateItemCount('count',9);">9</div>
											<div class="js-btn" style="cursor: pointer;" onclick="">退格</div>
											<div class="js-btn" style="cursor: pointer;" onclick="updateItemCount('count',4);">4</div>
											<div class="js-btn" style="cursor: pointer;" onclick="updateItemCount('count',5);">5</div>
											<div class="js-btn" style="cursor: pointer;" onclick="updateItemCount('count',6);">6</div>
											<div class="js-btn" style="cursor: pointer;" onclick="">清空</div>
											<div class="js-btn" style="cursor: pointer;" onclick="updateItemCount('count',1);">1</div>
											<div class="js-btn" style="cursor: pointer;" onclick="updateItemCount('count',2);">2</div>
											<div class="js-btn" style="cursor: pointer;" onclick="updateItemCount('count',3);">3</div>
											<div class="js-btn" style="cursor: pointer;" onclick="">取消</div>
											<div class="js-btn" style="cursor: pointer;" onclick="">0</div>
											<div class="js-btn" style="cursor: pointer;" onclick="">00</div>
											<div class="js-btn" style="cursor: pointer;" onclick="">.</div>
											<div class="js-btn" style="cursor: pointer;" onclick="">确定</div>
										</div>
										<div class="tool-count-r">
											<div class="jiesuan" style="cursor: pointer;" onclick="goSubmitOrder();">结算</div>
											<div class="yingyep">${userInfo.account}</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-1 tool-pos">
								<div class="tool-lists">
									<i class="icon-sort"></i>
								</div>
								<div class="tool-list" style="cursor: pointer;" onclick="updateItemCount('add');">
									<i class="icon-add"></i>
								</div>
								<div class="tool-list" style="cursor: pointer;" onclick="updateItemCount('reduce');">
									<i class="icon-jian"></i>
								</div>
								<div class="tool-list" style="cursor: pointer;" onclick="deleteItem();">删除</div>
								<div class="tool-list" style="cursor: pointer;" onclick="pendingOrder();">挂单</div>
								<div class="tool-list" style="cursor: pointer;" onclick="">优惠</div>
								<div class="tool-list" style="cursor: pointer;" onclick="">退货</div>
								<div class="tool-list" style="cursor: pointer;" onclick="selectGift();">赠送</div>
								<div class="tool-list" style="cursor: pointer;" onclick="selectCustomer();">会员</div>
								<div class="tool-list">
									<i class="icon-set"></i>
								</div>
								<div class="tool-list" style="cursor: pointer;" onclick="window.close();" >
									<i class="icon-close"></i>
								</div>
							</div>
							<div class="col-sm-6 tool-content pad-l" style="height: 660px;">
								<!-- 第一行切换 -->
								<div class="tool-first-tabs" style="height:60px;">
									<div class="tab-right" style="cursor: pointer;">
										<i class="forward"></i>
									</div>
									<div class="tab-left" style="cursor: pointer;">
										<i class="backward_disabled"></i>
									</div>
									<div class="tool-first-tabAll" id="icon_list">
										<c:forEach items="${itemCatalogList}" var="entity"  varStatus="s">
									 		<div <c:if test='${s.index == 0}'>class="cur"</c:if> onclick="loadItemCatalog('${entity.id}');">${entity.name}</div>
									 	</c:forEach> 
									</div>
								</div>
								<!-- 第二行切换 -->
								<div class="tool-second-tabs">
									<div class="tab-right" style="cursor: pointer;">
										<i class="forward"></i>
									</div>
									<div class="tab-left" style="cursor: pointer;">
										<i class="backward_disabled"></i>
									</div>
									<div class="tool-second-tabs">
										<div class="tool-second-tab cur">
											<div class="tool-second-tabAll" id="icon_call">
												<c:forEach items="${secondItemCatalogList}" var="entity" varStatus="s">
													<div <c:if test='${s.index == 0}'>class="cur"</c:if> id="${entity.id}">
														<a href="javascript:void(0);" onclick="loadItem('${entity.id}');">${entity.name}</a>
													</div>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
								<!-- 内容  -->
								<div id="itemList">
									<div class="tool-pro-lists">
										<div class="tool-content-main cur">
											<c:if test="${itemList != null && fn:length(itemList) > 0}">
												<c:forEach items="${itemList}" var="item" varStatus="s">
													<c:if test="${s.index < 20}">
														<div class="tool-content-main-box" style="cursor: pointer;" onclick="addItem('${item.id}')">
															<div class="box-text">${item.code}${item.name}</div>
															<div class="box-price">￥${item.price}</div>
														</div>
													</c:if>
												</c:forEach>	
											</c:if>
										</div>
									</div>
									<c:if test="${pager != null && pager.totalPage > 1}">
										<input id="pageNo" type="hidden" value="${pager.pageNo}">
										<input id="pageCount" type="hidden" value="${pager.pageCount}">
										<input id="totalPage" type="hidden" value="${pager.totalPage}">
										<div class="content-tab-btn">
											<div class="left-btn" style="cursor: pointer;" onclick="loadItemByPage('pre');">
												<i></i>
											</div>
											<div class="right-btn" style="cursor: pointer;" onclick="loadItemByPage('next');">
												<i></i>
											</div>
										</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/vixntcommon/page/resource_js.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		pageSetUp();
		// PAGE RELATED SCRIPTS
		$('.tree > ul').attr('role', 'tree').find('ul').attr('role', 'group');
		$('.tree').find('li:has(ul)').addClass('parent_li').attr('role', 'treeitem').find(' > span').attr('title', 'Collapse this branch').on('click', function(e) {
			var children = $(this).parent('li.parent_li').find(' > ul > li');
			if (children.is(':visible')) {
				children.hide('fast');
				$(this).attr('title', 'Expand this branch').find(' > i').removeClass().addClass('fa fa-lg fa-plus-circle');
			} else {
				children.show('fast');
				$(this).attr('title', 'Collapse this branch').find(' > i').removeClass().addClass('fa fa-lg fa-minus-circle');
			}
			e.stopPropagation();
		});			
		 $('.knob').knob({
		        change: function (value) {
		            //console.log("change : " + value);
		        },
		        release: function (value) {
		            //console.log(this.$.attr('value'));
		            //console.log("release : " + value);
		        },
		        cancel: function () {
		            //console.log("cancel : ", this);
		        }
		    });
	})
</script>
<script>
	$(function(){
		//菜单一的切换
	    var tab_menu_li = $('.tool-first-tabs .tool-first-tabAll div');
	    tab_menu_li.click(function(){
	    	var index = $(this).index();
	        $(this).addClass('cur').siblings().removeClass('cur');
	        $('.tool-second-tabs .tool-second-tab').eq(index).addClass('cur').siblings().removeClass('cur');
	    }); 
	  //菜单二的切换
	    $('.tool-second-tabs').on('click','.tool-second-tab.cur .tool-second-tabAll div',function(){
	    	var index = $(this).index(); 
	    	$(this).addClass('cur').siblings().removeClass('cur');
	    	$('.tool-pro-lists .tool-content-main').eq(index).addClass('cur').siblings().removeClass('cur');
	    });
	  //下拉隐藏计算键盘
	  $('.tool-bar').on('click','i',function(){
		  if($(this).hasClass('up')){
			  $('.tool-count').slideDown();
			  $(this).removeClass('up');
		  }else{
			  $('.tool-count').slideUp();
			  $(this).addClass('up');
		  }
	  });
	  //左切换
	  	//1.左按钮鼠标进入
	  $("#itemList").on("mouseover",".left-btn i", function(){
		  $(this).addClass('cur');
	  });
	  	//2.左按钮点击
	  /* $('.left-btn i').click(function(){
		  if($('.tool-pro-lists .tool-content-main.cur').index()==0){
			 
		  }else{
			  $('.tool-pro-lists .tool-content-main.cur').removeClass('cur').prev().addClass('cur');
		  }
	  }); */
	  	//3.右按钮鼠标移出
	  $('#itemList').on('mouseout','.left-btn i',function(){
		  $(this).removeClass('cur');
	  });
	  //右切换
	  	//1.右按钮鼠标进入
	  $("#itemList").on("mouseover",".right-btn i", function(){
		  $(this).addClass('cur');
	  })
	   //2.右按钮鼠标移出
	  $("#itemList").on("mouseout",".right-btn i", function(){
		  $(this).removeClass('cur');
	  })
	  	//3.右按钮点击
	  /* $('.right-btn i').click(function(){
		  if($('.tool-pro-lists .tool-content-main.cur').index()==5){
			 
		  }else{
			  $('.tool-pro-lists .tool-content-main.cur').removeClass('cur').next().addClass('cur');
		  }
	  }); */
		//左侧商品信息选中
	  	$(".tool-goods").on("click","dl",function(){
	  		$("#itemCode").val($(this).attr("role"));
		  	$(this).addClass("cur").siblings().removeClass("cur");
	  	})
	  	
	  	$("#itemList").on('mouseover','.tool-content-main-box',function(){
			$(this).css("background","#2AA6D9")
		});
	  	$("#itemList").on('mouseout','.tool-content-main-box',function(){
	  		$(this).css("background","#e2e2e2")
		});
	  	$(".js-btn").hover(function(){
		  	$(this).css("background","#2AA6D9")
	  	},function(){
		  	$(this).css("background","#a9a9a9")
	  	})
	  	$(".tool-list").hover(function(){
			$(this).css("background","#2AA6D9")
		},function(){
			$(this).css("background","#EABF00")
		})

	});
	
	/********************* 业务方法开始 ***********/
	// 切换加载二级分类
	function loadItemCatalog(id) {
		$("#icon_call").load("${nvix}/nvixnt/vixntPosAction!loadItemCatalog.action?id="+id);
		$("#itemList").load("${nvix}/nvixnt/vixntPosAction!loadItem.action?id="+id+"&type=first");
	}
	// 加载商品
	function loadItem(id) {
		$("#itemList").load("${nvix}/nvixnt/vixntPosAction!loadItem.action?id="+id+"&type=second");
	}
	// 商品分页
	function loadItemByPage(type) {
		var id = $("#icon_call .cur").attr("id");
		var pageNo = Number($("#pageNo").val());
		var totalPage = Number($("#totalPage").val());
		if(type == 'pre' && pageNo == 1){
			layer.alert("当前已经是第一页");
			return ;
		} 
		if(type == 'next' && pageNo == totalPage){
			layer.alert("当前已经是最后一页");
			return ;
		} 
		if(type == 'next'){
			pageNo = pageNo + 1;
		}else if(type == 'pre'){
			pageNo = pageNo - 1;
		}
		$("#itemList").load("${nvix}/nvixnt/vixntPosAction!loadItemByPage.action?id="+id+"&pageNo="+pageNo);
	}
	// 选择商品
	function addItem(itemId) {
		var orderId = $("#orderId").val();
		var channelId = $("#channelId").val();
		if(channelId){
			$.ajax({
				url : "${nvix}/nvixnt/vixntPosAction!addItem.action",
				cache : false,
				data : {
					"itemId" : itemId,
					"orderId" : orderId,
					"channelId" : channelId
				},
				success : function(result){
					var r= result.split(":");
					if(r[0] != '0'){
						$("#itemCode").val(r[2]);
						$("#order").load("${nvix}/nvixnt/vixntPosAction!loadOrder.action?orderId="+r[1]);
						$("#orderCountAndPrice").load("${nvix}/nvixnt/vixntPosAction!loadOrderCountAndPrice.action?orderId="+r[1]);
					}else{
						layer.alert(r[1]);
						return ;
					}
				}
			})
		}else{
			layer.alert("请选择门店");
		}
	}
	// 修改商品数量
	function updateItemCount(type,count){
		var itemId = $("#order .cur").attr("id");
		if(itemId){
			$.ajax({
				url : "${nvix}/nvixnt/vixntPosAction!updateItemCount.action",
				cache : false,
				data : {
					"itemId" : itemId,
					"type" : type,
					"count" : count
				},
				success : function(result){
					var r= result.split(":");
					if(r[0] != '0'){
						$("#order").load("${nvix}/nvixnt/vixntPosAction!loadOrder.action?orderId="+r[1]);
						$("#orderCountAndPrice").load("${nvix}/nvixnt/vixntPosAction!loadOrderCountAndPrice.action?orderId="+r[1]);
					}else{
						layer.alert(r[1]);
						return ;
					}
				}
			})
		}else{
			layer.alert("请选择商品");
		}
	}
	// 删除商品
	function deleteItem(){
		var itemId = $("#order .cur").attr("id");
		var orderId = $("#orderId").val();
		if(itemId){
			$.ajax({
				url : "${nvix}/nvixnt/vixntPosAction!deleteItem.action",
				cache : false,
				data : {
					"itemId" : itemId,
					"orderId" : orderId
				},
				success : function(result){
					var r= result.split(":");
					if(r[0] != '0'){
						$("#itemCode").val("");
						$("#order").load("${nvix}/nvixnt/vixntPosAction!loadOrder.action?orderId="+r[1]);
						$("#orderCountAndPrice").load("${nvix}/nvixnt/vixntPosAction!loadOrderCountAndPrice.action?orderId="+r[1]);
					}else{
						layer.alert(r[1]);
						return ;
					}
				}
			})
		}else{
			layer.alert("请选择商品");
		}
	}
	// 挂单
	function pendingOrder(){
		var orderId = $("#orderId").val();
		var customerAccountId = $("#customerAccountId").val();
		$.ajax({
			url : '${nvix}/nvixnt/vixntPosAction!pendingOrder.action',
			cache: false,
			data : {
				"orderId" : orderId,
				"customerAccountId" : customerAccountId
			},
			success: function(html){
				$("#order").load("${nvix}/nvixnt/vixntPosAction!loadOrder.action?orderId=''");
				$("#orderCountAndPrice").load("${nvix}/nvixnt/vixntPosAction!loadOrderCountAndPrice.action?orderId=''");
				$("#customerAccount").load("${nvix}/nvixnt/vixntPosAction!loadCustomerAccount.action?id=''");
				layer.open({
				    type: 1,
				    title: "挂单记录",
				    area: ['850px', '500px'],
				    closeBtn: 1,
				    content: html,
				    btn: ['确定','关闭']
			    ,yes: function(index, layero){
			    	var pendingOrderId = $("#pendingOrderId").val();
			    	if(pendingOrderId){
			    		checkPendingOrder(pendingOrderId,customerAccountId);
			    		layer.close(index);
			    	}else{
			    		layer.alert("请选择要取出的挂单")
			    	}
				},cancel: function(index, layero){
				}});
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert("系统错误，请联系管理员");
			}
		});
	}
	// 取出挂单
	function checkPendingOrder(id,customerAccountId) {
		$.ajax({
			url : "${nvix}/nvixnt/vixntPosAction!checkPendingOrder.action",
			cache : false,
			data : {
				"id" : id
			},
			success : function(result){
				var r= result.split(":");
				if(r[0] != '0'){
					$("#order").load("${nvix}/nvixnt/vixntPosAction!loadOrder.action?orderId="+r[1]);
					$("#orderCountAndPrice").load("${nvix}/nvixnt/vixntPosAction!loadOrderCountAndPrice.action?orderId="+r[1]);
					$("#customerAccount").load("${nvix}/nvixnt/vixntPosAction!loadCustomerAccount.action?id="+customerAccountId);
				}else{
					layer.alert(r[1]);
					return ;
				}
			}
		})
	};
	// 选择会员
	function selectCustomer(){
		$.ajax({
			url : '${nvix}/nvixnt/vixntPosAction!selectCustomer.action',
			cache: false,
			success: function(html){
				layer.open({
				    type: 1,
				    title: "选择会员",
				    area: ['750px', '400px'],
				    closeBtn: 1,
				    content: html,
				    btn: ['确定','关闭']
			    ,yes: function(index, layero){
			    	var code = $("#code").val();
			    	var channelId = $("#channelId").val();
			    	if(code){
			    		$.ajax({
			    			url : "${nvix}/nvixnt/vixntPosAction!checkCustomer.action",
			    			data : {
			    				"code" : code,
			    				"channelId" : channelId
			    			},
			    			cache : false,
			    			success : function(result){
			    				var r= result.split(":");
			    				if(r[0] != '0'){
			    					$("#customerAccount").load("${nvix}/nvixnt/vixntPosAction!loadCustomerAccount.action?id="+r[1]);
						    		layer.close(index);
			    				}else{
			    					layer.alert(r[1]);
			    					return ;
			    				}
			    			}
			    		})
			    	}else{
			    		layer.alert("请输入手机号或会员卡号")
			    	}
				},cancel: function(index, layero){
				}});
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert("系统错误，请联系管理员");
			}
		});
	}
	// 订单结算
	function goSubmitOrder(){
		var orderId = $("#orderId").val();
		var customerAccountId = $("#customerAccountId").val();
		if(!orderId){
			layer.alert("没有要结算的订单");
			return ;
		}
		if(!customerAccountId){
			layer.alert("请选择会员");
			return ;
		}
		$.ajax({
			url : '${nvix}/nvixnt/vixntPosAction!goSubmitOrder.action',
			cache: false,
			data : {
				"orderId" : orderId,
				"customerAccountId" : customerAccountId
			},
			success: function(html){
				layer.open({
				    type: 1,
				    title: "订单结算",
				    area: ['850px', '350px'],
				    closeBtn: 1,
				    content: html,
				    btn: ['确定','关闭']
			    ,yes: function(index, layero){
			    	var payType = $("input[name='payType']:checked").val();
			    	var couponId = $("input[name='couponId']:checked").val();
			    	var payAmount = $("#payAmount").val();
			    	var reduceAmount = $("#reduceAmount").val();
			    	var amount = $("#amount").val();
			    	var money = $("#money").val();
			    	if(payType == '2' && Number(payAmount) > Number(money)){
			    		layer.alert("余额不足支付金额");
			    		return ;
			    	}else if(payType == '4'){
			    		if(Number(payAmount) != 0){
			    			weixinPay(orderId,customerAccountId,payAmount,reduceAmount,payType,amount,couponId,index);
			    		}else{
			    			layer.alert("支付金额为0,不能使用微信支付");
				    		return ;
			    		}
			    	}else{
			    		$.ajax({
			    			url : "${nvix}/nvixnt/vixntPosAction!submitOrder.action",
			    			cache : false,
			    			data : {
			    				"orderId" : orderId,
			    				"customerAccountId" : customerAccountId,
			    				"payType" : payType,
			    				"payAmount" : payAmount,
			    				"reduceAmount" : reduceAmount,
			    				"amount" : amount,
			    				"couponId" : couponId
			    			},
			    			success : function(result){
			    				var r= result.split(":");
			    				if(r[0] != '0'){
			    					$("#order").load("${nvix}/nvixnt/vixntPosAction!loadOrder.action?orderId=''");
			    					$("#orderCountAndPrice").load("${nvix}/nvixnt/vixntPosAction!loadOrderCountAndPrice.action?orderId=''");
			    					$("#customerAccount").load("${nvix}/nvixnt/vixntPosAction!loadCustomerAccount.action?id=''");
			    					goPrintOrder(orderId);
			    					layer.close(index);
			    				}else{
			    					layer.alert(r[1]);
			    					return ;
			    				}
			    			}
			    		})
			    	}
				},cancel: function(index, layero){
				}});
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert("系统错误，请联系管理员");
			}
		});
	}
	// 赠品
	function selectGift(){
		var orderId = $("#orderId").val();
		if(orderId){
			$.ajax({
				url : '${nvix}/nvixnt/vixntPosAction!selectGift.action',
				cache: false,
				data : {
					"orderId" : orderId
				},
				success: function(html){
					layer.open({
					    type: 1,
					    title: "赠品列表",
					    area: ['850px', '500px'],
					    closeBtn: 1,
					    content: html,
					    btn: ['确定','关闭']
				    ,yes: function(index, layero){
				    	var itemId = $("#itemId").val();
				    	if(itemId){
				    		saveGift(itemId,orderId);
				    		layer.close(index);
				    	}else{
				    		layer.alert("请选择赠品")
				    	}
					},cancel: function(index, layero){
					}});
				},error: function(XMLHttpRequest, textStatus, errorThrown) {
					layer.alert("系统错误，请联系管理员");
				}
			});
		}else{
			layer.alert("暂无订单");
		}
	}
	// 保存赠品
	function saveGift(itemId,orderId) {
		$.ajax({
			url : "${nvix}/nvixnt/vixntPosAction!saveGift.action",
			cache : false,
			data : {
				"itemId" : itemId,
				"orderId" : orderId
			},
			success : function(result){
				var r= result.split(":");
				if(r[0] != '0'){
					$("#itemCode").val(r[2]);
					$("#order").load("${nvix}/nvixnt/vixntPosAction!loadOrder.action?orderId="+r[1]);
					$("#orderCountAndPrice").load("${nvix}/nvixnt/vixntPosAction!loadOrderCountAndPrice.action?orderId="+r[1]);
				}else{
					layer.alert(r[1]);
					return ;
				}
			}
		})
	};
	// 微信支付
	function weixinPay(orderId,customerAccountId,payAmount,reduceAmount,payType,amount,couponId,loadIndex){
		$.ajax({
			url : '${nvix}/nvixnt/vixntWxPayAction!weixinPay.action',
			cache: false,
			data : {
				"orderId" : orderId,
				"customerAccountId" : customerAccountId,
				"payType" : payType,
				"payAmount" : payAmount,
				"reduceAmount" : reduceAmount,
				"amount" : amount,
				"couponId" : couponId
			},
			success: function(html){
				layer.open({
				    type: 1,
				    title: "微信支付",
				    area: ['330px', '370px'],
				    closeBtn: 1,
				    content: html
				});
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert("系统错误，请联系管理员");
			}
		});
		//重复执行(轮循查询订单是否支付成功)
	    var t1 = window.setInterval("checkPay('" + orderId + "')",2000); 
	}
	
	function checkPay(orderId){
		$.ajax({
			url : '${nvix}/nvixnt/vixntWxPayAction!checkPay.action',
			cache: false,
			data : {
				"orderId" : orderId
			},
			success: function(result){
				if(result == "success"){
					goPrintOrder(orderId);
					window.location.href = '${nvix}/nvixnt/vixntWxPayAction!goPaySuccess.action';
				}
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert("系统错误，请联系管理员");
			}
		});
	}
	
	// 打印小票
	var LODOP;
	function goPrintOrder(orderId) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntPosAction!goPrintOrder.action?orderId=' + orderId,
		cache : false,
		success : function(html) {
			LODOP = getLodop();
			LODOP.SET_PRINT_PAGESIZE(3, "210mm", "80mm", "");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
			LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
			LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
			LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);//打印后自动关闭预览窗口
			LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
			LODOP.SET_PREVIEW_WINDOW(1, 2, 0, 1024, 550, "");
			LODOP.PRINT();
			/* LODOP.PRINT_DESIGN(); */
			/* LODOP.PREVIEW(); */
		}
		});
	};

	/********************* 业务方法结束 ***********/
</script>
<script src="${nvix}/vixntcommon/base/js/tabswitch1.js"></script>
<script src="${nvix}/vixntcommon/base/js/tabswitch2.js"></script> 
</body>
</html>