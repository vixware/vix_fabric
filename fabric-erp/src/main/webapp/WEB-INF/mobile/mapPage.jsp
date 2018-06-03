<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/mobilecommon/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/mobilecommon/page/head.jsp"%>
	<%@ include file="/mobilecommon/page/resource_css.jsp"%>
	<%@ include file="/mobilecommon/page/resource_js.jsp"%>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
	<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=4ZQBZ-COJ3F-T6EJJ-JZJPH-NMQ62-A2B44"></script>
	<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=4ZQBZ-COJ3F-T6EJJ-JZJPH-NMQ62-A2B44&libraries=drawing,geometry,autocomplete,convertor"></script>
	<title>联盟链-新建资产</title>
	<style type="text/css">
		*{
		    margin:0px;
		    padding:0px;
		}
		body, button, input, select, textarea {
		    font: 12px/16px Verdana, Helvetica, Arial, sans-serif;
		}
		#info{
		    width:303px;
		    padding-top:3px;
		    overflow:hidden;
		}
		.btn{
		    width:32.5%;
		}
		#container{
			min-width:300px;
			min-height:530px;
		}
	</style>
</head>
<body onload="init()">
<header>
    <a href="javascript:history.back();" class="header_return"><i class="iconfont icon-icon--"></i></a>
    <p class="coupons">新建资产</p>
    <a href="javascript:submitLocation();" class="header_r_txt">提交</a>
</header>
    <div id="container"></div>
	<div id="info">
     	<button id="gps" class="btn">开始记录轨迹</button>
      	<button id="stop" class="btn">轨迹记录结束</button>
        <button id="clean" class="btn">清除轨迹</button>
	</div>
	<input type="hidden" id="nonceStr" value="${nonceStr}" />
	<input type="hidden" id="timestamp" value="${timestamp}" />
	<input type="hidden" id="signature" value="${signature }" />
	<input type="hidden" id="qiyeCorpId" value="${qiyeCorpId }" />
	<input type="hidden" id="assetId" value="${id}" />
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js" type="text/javascript" ></script>
<script type="text/javascript">
	//微信核心配置
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: $("#qiyeCorpId").val(), // 必填，公众号的唯一标识
	    timestamp: $("#timestamp").val(), // 必填，生成签名的时间戳
	    nonceStr: $("#nonceStr").val(), // 必填，生成签名的随机串
	    signature: $("#signature").val(),// 必填，签名，见附录1
	    jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	wx.ready(function(){
		
	}); 
	
	function releaseAsset(){
		var assetName = $("#assetName").val();
		 if (!assetName) {
			$.alert("请输入名称！");
			return;
		}
		 /**if (!operator) {
			$.alert( "请输入经营人！");
			return;
		}
		if (!plantingArea) {
			$.alert( "请输入种植面积！");
			return;
		}
		if (!predeterminedPrice) {
			$.alert( "请输入预定价格");
			return;
		}
		if (!prepaidDeposit) {
			$.alert( "请输入预付定金");
			return;
		} */
		
		$("#asset-form").ajaxSubmit({
			type : "post",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			url : "${ws}/front/fabricAssetAction!saveOrUpdateAsset.action",
			success : function(result) {
				var r = result.split(":");
				if(r[0] != '0'){
					document.location.href = '${ws}/front/fabricAssetAction!goAssetList.action';
				}else{
					$.toptip(r[1]);
					return;
				}
			}
		});
	}
	
	function goMapPage(){
	   document.location.href='${ws}/front/fabricAssetAction!goMapPage.action';
	}
	var ints;
 	var points = [];
	var polygon;
	var map;
	function getLocation() {
		//alert(points);
		wx.getLocation({
			type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'  
			success: function (res) {
				var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
				var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
				var speed = res.speed; // 速度，以米/每秒计
				var accuracy = res.accuracy; // 位置精度
				points.push(new qq.maps.LatLng(latitude,longitude));
				var a =(points[0].lng + points[points.length-1].lng)/2;
				var b =(points[0].lat + points[points.length-1].lat)/2;
				map.panTo(new qq.maps.LatLng(b, a));
				polygon.setPath(points);
			},
			fail:function(){
				//
			}
		});
		ints = setInterval("getLocation()", 20000);
	}
	var init = function() {
		var center =new qq.maps.LatLng(40.003950,116.285648);//画面在起点和终点之间
	    map = new qq.maps.Map(document.getElementById('container'),{
	        center: center,
	        zoom: 20
	    });
	    polygon = new qq.maps.Polygon({
	        path:points,
	        strokeColor: '#000000',
	        strokeWeight: 1,
	        map: map
	    });
	  
	    //gps
	    var gps=document.getElementById("gps");
	    qq.maps.event.addDomListener(gps,"click",function(){
			alert("开始记录轨迹");
	    	getLocation();
	    });
	    //clean
	    var clean=document.getElementById("clean");
	    qq.maps.event.addDomListener(clean,"click",function(){
			alert("清空轨迹记录");
			var arr = points[0];
			points=[];
			points.push(arr);
			polygon.setPath(points);
			window.clearInterval(ints);
	    });
	}
  	function stopLocation(){
		 alert("结束记录轨迹");
		 window.clearInterval(ints);  
	}
	//结束记录轨迹
	var stop=document.getElementById("stop");
	qq.maps.event.addDomListener(stop,"click",function(){
	   stopLocation();
	});
	
	//提交坐标信息
	function submitLocation(){
		var assetId = $("#assetId").val();
		var data ={};
		data.id = assetId;
		data.points =JSON.stringify(points);
		$.ajax({ 
		     type : "POST",                   //提交方式 
		     url : "${ws}/front/fabricAssetAction!saveOrUpdateLocation.action", //路径 
		     data : data,                       //数据
		     success : function(result) {       //返回数据根据结果进行相应的处理 
		    	  	  var r = result.split(":");
				      if (r[0] != '0') { 
				    	  alert(r[2]);
				    	  document.location.href = '${ws}/front/fabricAssetAction!goNewAsset.action?id='+r[2];
					  }else{ 
						  $.toptip(r[1]);
						  return;
					  } 
			     } 
		    }); 
	}
	
</script>
</html>