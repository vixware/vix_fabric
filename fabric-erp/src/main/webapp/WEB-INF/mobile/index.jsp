<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_js.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	
	
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js" type="text/javascript" ></script>
	
	<title>联盟链-首页</title>
</head>
<body>
<header>
    <a href="#" class="header_position">北京<i class="iconfont icon-icon--1"></i></a>
    <div class="header_search"><a href="#"><span><i class="iconfont icon-iconfontzhizuobiaozhun22"></i><b>请输入关键字</b></span><strong><i class="iconfont icon-jia"></i></strong></a> </div>
</header>
<section class="mt_45 mb_53">
    <!-- banner -->
    <div class="swiper-container index_banner" id="swiper">
        <div class="swiper-wrapper">
            <div class="swiper-slide"><a href="#"><img src="${ws}/mobilecommon/base/images/banner1.jpg"></a> </div>
            <div class="swiper-slide"><a href="#"><img src="${ws}/mobilecommon/base/images/banner2.jpg"></a> </div>
            <div class="swiper-slide"><a href="#"><img src="${ws}/mobilecommon/base/images/banner1.jpg"></a> </div>
            <div class="swiper-slide"><a href="#"><img src="${ws}/mobilecommon/base/images/banner2.jpg"></a> </div>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>
    <div class="index_nav">
        <ul>
            <li><a href="${ws}/front/fabricContractAction!goNewContract.action?id="><span><i class="iconfont icon-jia"></i></span><b>创建合约</b></a> </li>
            <li><a href="#"><span><i class="iconfont icon-gonggongziyuanjiaoyi"></i></span><b>交易市场</b></a> </li>
            <li><a href="${ws}/front/fabricAssetAction!goNewAsset.action?id="><span><i class="iconfont icon-zichan"></i></span><b>发布资产</b></a> </li>
        </ul>
    </div>
    <div class="deinfo-list">
        <div class="weui-media-box weui-media-box_text">
            <h4 class="weui-media-box__title">北京市顺义区林木苗圃地4.5亩出租智能合约</h4>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><span style="border:1px solid #0092D8;color:#0092D8;font-size: 10px;padding:1px 3px;border-radius: 3px;">发</span>&nbsp;陈明 <span style="font-size: 10px;">创建时间：2018-3-10 12:00</span></p>
            </div>
            <div class="weui-cell__ft"><a href="${ws}/front/fabricAssetAction!goAssetDetail.action?id=" class="weui-btn weui-btn_mini weui-btn_primary">交易详情</a></div>
        </div>
    </div>
    <div class="deinfo-list">
        <div class="weui-media-box weui-media-box_text">
            <h4 class="weui-media-box__title">北京市顺义区林木苗圃地4.5亩出租智能合约</h4>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p><span style="border:1px solid #0092D8;color:#0092D8;font-size: 10px;padding:1px 3px;border-radius: 3px;">发</span>&nbsp;陈明 <span style="font-size: 10px;">创建时间：2018-3-10 12:00</span></p>
            </div>
            <div class="weui-cell__ft"><a href="${ws}/front/fabricContractAction!goContractDetail.action?id=" class="weui-btn weui-btn_mini weui-btn_primary">交易详情</a></div>
        </div>
    </div>
</section>
<!-- 底部代码 -->
<div class="weui-tabbar">
    <a href="${ws}/front/fabricIndexAction!goIndex.action?key=home" class="weui-tabbar__item  weui-bar__item_on">
        <i class="iconfont icon-home"></i>
        <p class="weui-tabbar__label">首页</p>
    </a>
    <a href="${ws}/front/fabricContractAction!goContractList.action?id=" class="weui-tabbar__item ">
        <i class="iconfont icon-heyue"></i>
        <p class="weui-tabbar__label">合约</p>
    </a>
    <a href="${ws}/front/fabricAssetAction!goAssetList.action?id=" class="weui-tabbar__item ">
        <i class="iconfont icon-zichanxinxi"></i>
        <p class="weui-tabbar__label">资产</p>
    </a>
    <a href="${ws}/front/fabricUserCenterAction!goIndex.action?id=" class="weui-tabbar__item ">
        <i class="iconfont icon-wode"></i>
        <p class="weui-tabbar__label">我的</p>
    </a>
</div>
<input type="hidden" id="qiyeCorpId" value="${qiyeCorpId}">
<input type="hidden" id="timestamp" value="${timestamp}">
<input type="hidden" id="nonceStr" value="${nonceStr}">
<input type="hidden" id="signature" value="${signature}">
</body>
<script type="text/javascript" >
	
	//微信核心配置
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: $("#qiyeCorpId").val(), // 必填，公众号的唯一标识
	    timestamp: $("#timestamp").val(), // 必填，生成签名的时间戳
	    nonceStr: $("#nonceStr").val(), // 必填，生成签名的随机串
	    signature: $("#signature").val(),// 必填，签名，见附录1
	    jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});

	wx.checkJsApi({
	    jsApiList: ['getLocation'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
	    success: function(res) {
	        // 以键值对的形式返回，可用的api值true，不可用为false
	        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
	    }
	});
	wx.ready(function(){
		/* var currentCity = $("#currentCity").text();
		if(!currentCity){
			wx.getLocation({
			    success: function (res) {
			        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
			        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
			       	var speed = res.speed; // 速度，以米/每秒计
			        var accuracy = res.accuracy; // 位置精度
			        var key = 'PRXBZ-T3FAS-ZVVOV-6LPL2-6IAEO-XZFQW';
			        currentAddressCity(latitude,longitude,key);
			        //loadCustomerAccount($("#currentCity").text());
			    }
		    });  
		}else{
			//loadCustomerAccount($("#currentCity").text());
		} */
	});
	// 定位
	function currentAddressCity(latitude,longitude,key){
	   	var url3 = encodeURI("http://apis.map.qq.com/ws/geocoder/v1/?location=" + latitude + "," + longitude + 
	   			"&key="+key+"&output=jsonp&&callback=?&coord_type=1");
	   	$.getJSON(url3, function (result) {
       		if(result.result!=undefined){
          		var city=result.result.address_component.city;
             	city = city.substr(0,city.length-1);
             	$("#currentCity").text(city);
             	city=Url.encode(city);
             	var url = "${diandoc}/diandoc/dianDocIndexAction!mergeCity.action?city="+city;
         		$.get(url);
         	}
     	});
	}
</script>
</html>
