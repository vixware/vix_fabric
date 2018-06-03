<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
</head>

<body>
	<section class="newClaPro">
		<div class="search">
			<p>
				<img src="${vix}/wechatcommon/images/search_icon.png" /><input type="text" placeholder="搜索名称">
			</p>
		</div>
		<div class="newClaPro_list1">
			<ul>
				<li value="默认项目" class="check"><img src="${vix}/wechatcommon/images/newClaPro_icon1.png"><span>默认项目</span></li>
				<li value="开发项目"><img src="${vix}/wechatcommon/images/newClaPro_icon1.png"><span>开发项目</span></li>
			</ul>
		</div>
		<div class="taskDetail_list2">
			<h2>
				<span class="btn"><a href="#">取消</a> </span><span class="btn"><a href="#" onclick="goback();">确定</a> </span>
			</h2>
		</div>
	</section>
</body>
</html>
<script type="text/javascript">
	$('.newClaPro_list1 ul li').click(function() {
		$(this).addClass('check').siblings('li').removeClass('check');
	});
	var areaCode;
	$("ul li").each(function() {
		$(this).click(function() {
			areaCode = $(this).attr("value");
		})
	});
	function goback() {
		$.ajax({
		url : "",
		context : document.body,
		success : function() {
			location.href = "${vix}/wechat/wechatTravelClaimsAction!goSaveOrUpdate.action?areaCode=" + areaCode;
		}
		});
	}
</script>