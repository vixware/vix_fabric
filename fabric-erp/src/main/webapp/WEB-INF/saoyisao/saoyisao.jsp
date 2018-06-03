<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/wxcommon/wx/common/padtaglib.jsp"%>
<html>
<head>
	<base href="${vix}/module/o2o/">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>微信绑定</title>
	<script src="${vix}/module/saoyisao/js/jquery.2.0.3.min.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="${vix}/module/saoyisao/js/weixin_jssdk.js" type="text/javascript"></script>
	<script type="text/javascript">
		function workingPage(){
			wx.scanQRCode({
			    needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
			    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
			    success: function (res) {
				    var result = res.resultStr; //当needResult 为 1 时，扫码返回的结果
				}
			});
		}
	</script>
</head>
<body>
<div class="widget-main center login-container form-horizontal">

	<div class="header" style="margin-top: 50%;">	
		<button class="width-35 btn btn-small btn-primary" onclick="workingPage()">
			<i class="icon-key"></i>
				扫一扫
		</button>
	</div>
</div>
</body>
</html>