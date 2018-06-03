var BNJ=new B_mobile_comm.Main();
var B_msg=new B_mobile_comm.messager();
var B_secur=new B_mobile_comm.security();
var B_stor=new B_mobile_util.Storage();
var B_market=new B_mobile_comm.Main.market();
if(BNJ.getQuery('id')){
	var shopId=BNJ.getQuery('id');
}
var paging = new B_mobile_comm.paging("paging_span");
var page=1;
var totalPage=0;
/**
*店铺首页----------------------------------------------------
*/
function shopHome(){
	var prm=new Object();
	prm.mode="1.7";
	prm.shopId=shopId;
	if(B_secur.valdate()){
		prm.userId=B_secur.userId;
	}
	BNJ.buildAjax3();
	BNJ.ajaxPrm.data=JSON.stringify(prm);
	BNJ.ajaxPrm.success=function(data){
		data=BNJ.valdateData(data);
		if(data!=undefined){
			shopHtml(data);
		}
	}
	$.ajax(BNJ.ajaxPrm);
}
//店铺头部
function shopHeadHtml(data){
	var html='';
	html+='<section class="shop-head" style="background:url('+data.shopSignUrl+') no-repeat; background-size:100% 100%;"><p class="shop-photo"><span><img src="'+data.shopIconUrl+'" alt="" onerror="this.onerror=null;this.src=\'../img/default/seller_default.jpg\'"></span></p>'+
			'<div class="shop-name">'+data.shopName+'</div></section>';
	
	var level=BNJ.level(data.shopGrade,'seller');
	html+='<section class="shop-level mui-flex plr10 b_b"><div class="cell t_l">店铺等级：<span class="level '+level.class+'">'+level.html+'</span></div></section>';
	html+='<section class="shop-score mui-flex mb10">'+
			'<div class="cell">描述相符:<span class="c_red"><i>'+data.descConformance+'</i>分</span></div><div class="cell">服务态度:<span class="c_red"><i>'+data.serviceAttitude+'</i>分</span></div><div class="cell">发货速度:<span class="c_red"><i>'+data.deliverySpeed+'</i>分</span></div>'+
		'</section>';
	if(data.couponCount==1){
		html+='<section class="pro-coupon plr10 single mb10" couponId="'+data.couponJson.couponId+'">'+
				'<em class="icon"></em><span class="txt">'+data.couponJson.couponName+'</span>'+
			'</section>';
	}else if(data.couponCount==2){
		html+='<section class="pro-coupon plr10 double mb10">'+
				'<a href="javascript:;">'+
					'<em class="icon"></em><span class="txt">多种优惠任选</span><span class="bnj-sprite-icon arrow_r"></span>'+
				'</a>'+
			'</section>';
	}	
	return html;
}
//店铺首页页面html
function shopHtml(data){
	$('.shop-body').html(B_msg.loading());
	var html='';
	
	html=shopHeadHtml(data);

	if(data.shopNotice){
		html+='<section class="shop-notice plr10 mb10"><div class="text c_gray">'+data.shopNotice+'</div></section>';
	}

	if(data.recommendItemJsons && data.recommendItemJsons.length>0){
		html+='<div class="shop-goods-list mb10">'+
				'<h3 class="title plr10"><span class="title-icon"></span>店长推荐</h3>'+
				'<div class="list-view vertical_layout">'+
					'<ul>';
		html+=goodslisthtml(data.recommendItemJsons);			
		html+=		'</ul>';			
		html+=	'</div>';		
		html+='</div>';
	}

	if(data.itemJsons && data.itemJsons.length>0){
		html+='<div class="shop-goods-list mb10">'+
				'<h3 class="title plr10"><span class="title-icon"></span>热销商品</h3>'+
				'<div class="list-view vertical_layout">'+
					'<ul>';
		html+=goodslisthtml(data.itemJsons);			
		html+=		'</ul>';			
		html+=	'</div>';		
		html+='</div>';
	}
	html+='<section class="p10 mb10">'+
				'<a href="javascript:;" class="btn-g-l all-goods">查看所有商品</a>'+
			'</section>';
	$('.shop-body').html(html);
	if(data.isCollect && data.isCollect=="1"){
		$('.shop-footer .shop-favor').addClass('collect').find('.text').html('取消收藏');
	}else{
		$('.shop-footer .shop-favor').removeClass('collect').find('.text').html('店铺收藏');
	}
	if(data.serviceTel){
		var tel=data.serviceTel.join(',');
		$('.shop-tel').attr('tel',tel);
	}
}
//店铺首页商家电话弹框
function shopTel(tel){
	var telArr=[];
	var html='';
	if(tel!=undefined){
		telArr=tel.split(',');
		B_msg.tell('商家联系电话',telArr);
	}
}

$(function(){
	//搜索店铺内商品
	$('.goShopSearch').click(function(){
		location.href=url.domain+'/market/search.html?shopId='+shopId;
	})
	//查看所有商品
	$(document).on('click','.all-goods',function(){
		location.href=url.domain+'/market/goodslist.html?shopId='+shopId;
	})
	//店铺电话
	$('.shop-tel').click(function(){
		if(closeDropBox()){
			var tel=$(this).attr('tel');
			shopTel(tel);
		}
	})
	//收藏店铺
	$('.shop-favor').click(function(){
		if(closeDropBox()){
			var _this=$(this);
			if(_this.hasClass('collect')){
				B_market.removestore("2",shopId,function(){
					_this.removeClass('collect').find('.text').html('店铺收藏');
				});
			}else{
				B_market.addstore("2",shopId,function(){
					_this.addClass('collect').find('.text').html('取消收藏');
				});
			}
		}
	})
	//内部分类
	$('.classify').click(function(){
		if(closeDropBox()){
			shopcate();
		}
	})
	//店铺故事
	$('.shop-story').click(function(){
		if(closeDropBox()){
			location.href=url.domain+'/market/shopstory.html?id='+shopId;
		}
	})
	//关闭店铺内部分类弹框
	$('.shop-footer').click(function(){
		closeDropBox();
	})
	$('body').on('click','.modalmessageBG',function(){
		$('.dropBox').remove();
		$('.modalmessageBG').remove();
		$("body").css({"overflow":"auto"});
	})
	//领取优惠券
	$(document).on('click','.pro-coupon',function(){
		if($(this).hasClass('single')){
			//领取单个优惠券
			B_market.coupon($(this).attr('couponId'),function(){
				B_secur.login(url.domain+'/market/shophome.html?id='+shopId)
			});
		}else if($(this).hasClass('double')){
			location.href='getcoupon.html?tid='+shopId+'&type=2';
		}
	})
	
})
//关闭店铺内部分类弹框
function closeDropBox(){
	if($('.dropBox').hasClass('dropBox-expand')){
		$('.dropBox').remove();
		$('.modalmessageBG').remove();
		$("body").css({"overflow":"auto"});
		return false;
	}else{ return true}
}

/**
 * 店铺内部分类弹框--------------------------
 */
function shopcate(){
	B_market.internalCategory(shopId,"1",function(data){
		loadRootCate(data); //加载店铺分类一级

		var subcategoryid=$('#rootList li.selected').attr('id');
		if(subcategoryid!="rootAll"){
			B_market.subCategory(subcategoryid,"2",function(data){
				loadSubCate(data,subcategoryid);  //加载店铺分类二级
			})
		}
		$('#rootList li').click(function(){
			var thisid=$(this).attr('id');
			if(thisid!="rootAll"){
				$(this).addClass('selected').siblings().removeClass('selected');
				B_market.subCategory(thisid,"2",function(data){
					loadSubCate(data,thisid);  //加载店铺分类二级
				})
			}
		})
		B_mobile_comm.messager.modalShow(9990);
		$("body").css({"overflow":"hidden"});
	});
}
function loadRootCate(data){
	data=BNJ.valdateData(data);
	if(data!=undefined){
		if(data.catJsons){
			var html='';
			html+='<div class="dropBox bottomdrop dropBox-expand" id="shop-cart">'+
					'<div class="dropBox-sub">';
			html+='<div class="dropBox-l category-rootList" id="rootList"><ul>';
			html+='<li id="rootAll" class="all ';
			if(data.catJsons.length==0){
				html+=' selected';
			}
			html+='"><a href="goodslist.html?shopId='+shopId+'">全部商品</a></li>';
			for(var i=0;i<data.catJsons.length;i++){
				html+='<li id="'+data.catJsons[i].catId+'"';
				if(i==0){
					html+=' class="selected"';
				}
				html+='>'+data.catJsons[i].catName+'</li>';
			}
			html+='</ul></div><div class="dropBox-r"></div></div></div>'
			$('body').append(html);
		}
	}
	B_msg.closeAll();
}
function loadSubCate(data,parid){
	var loading=B_msg.loading();
	$('.dropBox-r').html(loading);
	
	data=BNJ.valdateData(data);
	if(data!=undefined){
		if(data.catJsons){
			var html='';
			html+='<ul class="branchList">';
			html+='<li id="subAll" class="all ';
			if(data.catJsons.length==0){
				html+=' selected';
			}
			html+='"><a href="goodslist.html?shopId='+shopId+'&cat1='+parid+'">全部</a></li>';
			for(var i=0;i<data.catJsons.length;i++){
				html+='<li id="'+data.catJsons[i].catId+'"';
				if(i==0){
					html+=' class="selected"';
				}
				html+='><a href="goodslist.html?shopId='+shopId+'&cat2='+data.catJsons[i].catId+'">'+data.catJsons[i].catName+'</a></li>';
			}
			html+='</ul>'
			$('.dropBox-r').html(html);
			$('.dropBox-sub').append($('.dropBox-r'));
		}
	}
	B_msg.closeAll();
}

/** 
 * 店铺故事-----------------------------------------------------------------------------------
*/
/**
*店铺头部
*/
function shophead(){
	var prm=new Object();
	prm.mode="1.7";
	prm.shopId=shopId;
	if(B_secur.valdate()){
		prm.userId=B_secur.userId;
	}
	BNJ.buildAjax3();
	BNJ.ajaxPrm.data=JSON.stringify(prm);
	BNJ.ajaxPrm.success=function(data){
		data=BNJ.valdateData(data);
		if(data!=undefined){
			var html='';
			html=shopHeadHtml(data);
			$('.shop-body').prepend(html);
		}
		B_msg.closeAll();
	}
	$.ajax(BNJ.ajaxPrm);
}
/**
*店铺故事列表
*/
function shopStoryList(){
	var prm=new Object();
	prm.mode="1.6";
	prm.shopId=shopId;
	prm.page=page;
	BNJ.buildAjax2();
	BNJ.ajaxPrm.data=JSON.stringify(prm);
	BNJ.ajaxPrm.success=function(data){
		data=BNJ.valdateData(data);
		if(data!=undefined){
			data.total>0?totalPage=true:totalPage=false; //是否有数据
			loadStoryList(data.shopStoryJsons,data.isnext);
		}
		B_msg.closeAll();
	}
	$.ajax(BNJ.ajaxPrm);
}
/**
*店铺故事列表html
*/
function loadStoryList(shopStoryJsons,isnext){
	if(shopStoryJsons!=undefined){
		var html='';
		for(var i=0;i<shopStoryJsons.length;i++){
			var story=shopStoryJsons[i];
			html+='<li class="story-item" id="'+story.shopStoryId+'">'+
					'<a href="../frame.html?title=店铺故事&redirect='+encodeURIComponent(encodeURIComponent(story.storyUrl))+'">'+
						'<span class="story-img"><img src="'+story.imageUrl+'" alt="" onerror="this.onerror=null;this.src=\'../img/default/default_place_img.jpg\'"></span>'+
						'<div class="story-title">'+
							'<h3 class="title-con">'+story.title+'</h3>'+
							'<p class="title-time c_gray">'+story.createTime+'</p>'+
						'</div>'+
					'</a>'+
				'</li>'
		}
		$('.story-list ul').append(html);
	}else{
		isnext="no";
	}
	if(isnext=="no" || isnext==undefined){
		isnext=false;
	}else{
		page++;
		isnext=true;
	}
	paging.show(isnext,"shopStoryList",totalPage);
}
/**
*店铺故事详情 暂定不用
*/
function shopStoryDet(){
	var shopStoryId=BNJ.getQuery('storyid');
	var prm=new Object();
	prm.mode="1.8";
	prm.shopStoryId=shopStoryId;
	prm.type="1";
	BNJ.buildAjax2();
	BNJ.ajaxPrm.data=JSON.stringify(prm);
	BNJ.ajaxPrm.success=function(data){
		data=BNJ.valdateData(data);
		if(data!=undefined){
			loadStoryDet(data);
		}
		B_msg.closeAll();
	}
	$.ajax(BNJ.ajaxPrm);
}
function loadStoryDet(data){
	var html='';
	html='<section class="story-detail b_t b_b">'+
			'<h3 class="story-title">'+data.title+'</h3>'+
			'<p class="story-time f_12 c_gray">2012-12-12  20:45</p>'+
			'<p class="story-con c_gray">'+data.content+'</p>'+
		'</section>';
	$('.shop-body').append(html);
}
