<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
		<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
		<script type="text/javascript" src="${vix}/wechatcommon/js/jquery.form.js"></script>
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=3Xd8Tpzi1FG5ILFpHLb4utuh&v"></script>
		<style type="text/css">
			body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
			#allmap {width:100%;height:500px;}
		</style>
	</head>
	<body>
		<form id="punchCardForm">
			<section class="newMeet newCla new">
				<input type="hidden" id="nonceStr" name="nonceStr" value="${nonceStr }" />
				<input type="hidden" id="timestamp" name="timestamp" value="${timestamp}" />
				<input type="hidden" id="signature" name="signature" value="${signature }" />
				<input type="hidden" id="qiyeCorpId" name="qiyeCorpId" value="${qiyeCorpId }" />
				<input type="hidden" id="longitude" name="longitude" value="${longitude}" />
				<input type="hidden" id="latitude" name="latitude" value="${latitude}" />
				<input type="hidden" id="ipAddress" name="ipAddress" value="${ipAddress}" />
				<div class="newMeet_list2 newMeet_list_span">
					<dl>
						<dt>
							<span>考勤规则</span>
							<strong> 
								<select id="" name="" value="">
									<option value ="工作日">工作日</option>
								  	<option value ="加班">加班</option>
								</select>
							</strong> 
						</dt>
						<dt>
							<span>${nowTimes}</span> 
							<strong>
								<input type="text" value="您已工作${times }小时" readonly="readonly">
							</strong>
						</dt>
					</dl>
				</div>
				<br/>
				<s:if test="punList.size() >= 1">
					<div id="doAttendance" class="proDet proDet_b">
						<h2>
							<span>
								签到地址:<c:out value="${doAddress}"/>
								<br/>
								<b>签到时间: ${startTimes}</b>
								<br/>
								<b>签到状态:
									<c:if test="${isLate == 0 }"><b style="color: blue;font-size: 18px;">已签到</b></c:if>
									<c:if test="${isLate == 1 }"><b style="color: red;font-size: 18px;">迟到</b></c:if>
								</b>
							</span>
						</h2>
					</div>
				</s:if>
				<s:else>
					<div id="doAttendance" class="proDet">
						<h2>
							<span>签到:${startTimes }&nbsp;&nbsp;${doAddress}</span>
							<strong>
								<a href="#" onclick="doAttendance();">签到</a>
							</strong>
						</h2>
					</div>
				</s:else>
				<s:if test="punList.size() >= 2">
					<div id="doAttendanceOut" class="proDet proDet_b">
						<h2>
							<span>
								签退地址:<c:out value="${doOutAddress}"/>
								<br/>
								<b>签退时间:${endTimes}</b>
								<br/>
								<b>签退状态:
									<c:if test="${isEarly == 0 }"><b style="color: blue;font-size: 18px;">已签退</b></c:if>
									<c:if test="${isEarly == 1 }"><b style="color: red;font-size: 18px;">早退</b></c:if>
								</b>
							</span>
						</h2>
					</div>
				</s:if>
				<s:else>
					<div id="doAttendanceOut" class="proDet">
						<h2>
							<span>签退:${endTimes}&nbsp;&nbsp;${doOutAddress}</span>
							<strong>
								<a href="#" onclick="doAttendanceOut();">签退</a>
							</strong>
						</h2>
					</div>
				</s:else>
				<div style="width: 100%; height: 100%; border: 1px solid gray" id="allmap"></div>
			</section>
		</form>
	</body>
</html>
<script type="text/javascript">
	function doAttendance(){
		$("#punchCardForm").ajaxSubmit({
			type : "post",
			url : "${vix}/wechat/signPunchAction!doAttendance.action",
			dataType : "text",
			success : function(html) {
				$("#doAttendance").html(html);
				alert("签到成功!");
			}
		});
	}
	
	function doAttendanceOut(){
		$("#punchCardForm").ajaxSubmit({
			type : "post",
			url : "${vix}/wechat/signPunchAction!doAttendanceOut.action",
			dataType : "text",
			success : function(html) {
				$("#doAttendanceOut").html(html);
				alert("签退成功!");
			}
		});
	}

	var qiyeCorpId = $('#qiyeCorpId').val();
	var timestamp = $('#timestamp').val();
	var nonceStr = $('#nonceStr').val();
	var signature = $('#signature').val();

	wx.config({
		debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId : qiyeCorpId, // 必填，企业号的唯一标识，此处填写企业号corpid
		timestamp : timestamp, // 必填，生成签名的时间戳
		nonceStr : nonceStr, // 必填，生成签名的随机串
		signature : signature,// 必填，签名，见附录1
		jsApiList : [ 'getLocation']
		// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	
	wx.ready(function() {
		wx.getLocation({
		    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
		    success: function (res) {
		        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
		        var longitude = res.longitude ; // 经度，浮点数，范围为180 ~ -180。
		        var speed = res.speed; // 速度，以米/每秒计
		        var accuracy = res.accuracy; // 位置精度
		       
		    }
		});
		 autoLocation();
		// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	});
	
	wx.error(function(res) {
		// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	});
	
	//地图初始化	
    var bm = new BMap.Map("allmap");
	//声明地址解析器
    var geoc = new BMap.Geocoder();
    //自动定位
	var autoLocation = function () {
        if (navigator.geolocation) { //判断浏览器是否能获取当前位置
            navigator.geolocation.getCurrentPosition(AddrSuc, AddrFail);
        }
        else {
            simpleNoty("自动定位失败,请联系管理员！");
        }
    }
    //获取当前坐标成功
    function AddrSuc(param) {
        var lng = param.coords.longitude;
        var lat = param.coords.latitude;
		if(lng != ''){
			$('#longitude').val(lng);
		}
		if(lat != ''){
			$('#latitude').val(lat);
		}
        var point = new BMap.Point(lng, lat);
        //将gps坐标转换为百度地址坐标
        BMap.Convertor.translate(point, 0, translateCallback(point));
    }
     
    //获取坐标失败
    function AddrFail(err) {
        showNotify("自动定位失败");
    }
    
    //坐标转换
    function translateCallback(point) {
        geoc.getLocation(point, function (rs) {
            var addComp = rs.addressComponents;  //查询得到的地址对象组件
            //addComp.streetNumber：街道门牌号
            //addComp.city：城市
            //addComp.district：区
            //addComp.street：街道
            //addComp.province：省
            var address = addComp.city + addComp.district + addComp.street + addComp.streetNumber;
            $("#ipAddress").val(address);
       		loadLocation(point,address);
        })
    }
	
 	function loadLocation(point,address){
        bm.centerAndZoom(point, 15);
 	    bm.addControl(new BMap.NavigationControl());
     	
	    //坐标转换完之后的回调函数
	   	translateCallback = function (data){
	      if(data.status === 0) {
	        var marker = new BMap.Marker(data.points[0]);
	        bm.addOverlay(marker);
	        bm.setCenter(data.points[0]);
	        var opts = {
           	  width : 50,     // 信息窗口宽度
           	  height: 40,     // 信息窗口高度
           	  title : "您当前位置:" , // 信息窗口标题
           	}
	    	// 创建信息窗口对象 
	        var infoWindow = new BMap.InfoWindow(address, opts);  
	     	//开启信息窗口
	        bm.openInfoWindow(infoWindow,data.points[0]); 
	      }
	    }

	    setTimeout(function(){
	        var convertor = new BMap.Convertor();
	        var pointArr = [];
	        pointArr.push(point);
	        convertor.translate(pointArr, 1, 5, translateCallback)
	    }, 1000);
	}
</script>