<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/wxcommon/wx/common/padtaglib.jsp"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>微信图片上传</title>
	<script src="${vix}/module/saoyisao/js/jquery.2.0.3.min.js"></script>
	<%@ include file="/wx/common/inc_weixin_jssdk.jsp"%>
	<script type="text/javascript">
		function chooseImage(){
			wx.chooseImage({
			    count: 1, // 默认9
			    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
			    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
			    success: function (res) {
			        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
			    	$("#img").attr("src",localIds);
			        $("#localIds").val(localIds);
			    	//uploadImage(localIds);
			    }
			});
		}
		function uploadImage(){
			var localIds = $("#localIds").val();
			wx.uploadImage({
			    localId: localIds.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
			    isShowProgressTips: 1, // 默认为1，显示进度提示
			    success: function (res) {
			        var serverId = res.serverId; // 返回图片的服务器端ID
			        postImage(serverId);
			    }
			});
		}
		function postImage(serverId){
			$.ajax({ 
				url:"${vix}/p/wx/wxImageAction!postImage.action"
	    		,data:"serverIds="+serverId
	    		,dataType:"text"
	    		,type:"post"
	    		,success:function(result){
	        }});
		}
	</script>
</head>
<body>
<div class="widget-main center login-container form-horizontal">
	<div class="header" style="margin-top: 50%;">	
		<button class="width-35 btn btn-small btn-primary" onclick="chooseImage()" style="height: 50px;">
			<i class="icon-key"></i>
				选择图片
		</button>
		<input type="text" id="localIds">
		<img alt="" id="img" src="" height="100px;">
		<button class="width-35 btn btn-small btn-primary" onclick="uploadImage()" style="height: 50px;">
			<i class="icon-key"></i>
				选择图片
		</button>
	</div>
</div>
</body>
</html>