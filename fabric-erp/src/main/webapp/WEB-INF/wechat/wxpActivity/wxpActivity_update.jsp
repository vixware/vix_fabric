<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布活动</title>
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/date.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery.form.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/date.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/iscroll.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/zxxFile.js"></script>

<script type="text/javascript">
	$(function() {
		$('#startTime').date({
			theme : "datetime"
		});
		$('#endTime').date({
			theme : "datetime"
		});
	});
	function saveOrUpdate() {
		var title = $("#title").val();
		var wxpActivityContent = $("#wxpActivityContent").val();

		// 验证
		if (!title || $.trim(title).length <= 0) {
			alert("请输入活动主题");
			return;
		}

		if (!wxpActivityContent || $.trim(wxpActivityContent).length <= 0) {
			alert("请输入活动内容");
			return;
		}
		$("#wxpActivityForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/wxpActivityAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			window.location.href = "${vix}/wechat/wxpActivityAction!goList.action?id=" + id;
		}
		});
	};
	function goshowMap() {
		window.location.href = "${vix}/wechat/wxpActivityAction!goShowMap.action";
	}
</script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
}

.btn_addPic {
	display: block;
	position: relative;
	width: 50px;
	height: 39px;
	overflow: hidden;
	border: 1px solid #EBEBEB;
	background: none repeat scroll 0 0 #2ECC32;
	color: #999999;
	cursor: pointer;
	text-align: center;
}

.btn_addPic1 {
	display: block;
	position: relative;
	overflow: hidden;
	color: #999999;
	cursor: pointer;
	text-align: center;
}

.filePrew {
	display: block;
	position: absolute;
	top: 0;
	left: 0;
	width: 50px;
	height: 39px;
	font-size: 100px; /* 增大不同浏览器的可点击区域 */
	opacity: 0; /* 实现的关键点 */
	filter: alpha(opacity = 0); /* 兼容IE */
}
</style>
</head>
<body>
	<form id="wxpActivityForm" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" id="wxpActivityId" name="wxpActivity.id" value="${wxpActivity.id }" />
		<section class="newMeet newCla posEve">
			<div class="newMeet_list1">
				<h2>
					<input type="text" id="title" name="wxpActivity.title" value="${wxpActivity.title }" placeholder="请输入活动主题">
				</h2>
				<h3>
					<textarea id="wxpActivityContent" name="wxpActivity.content" cols="" rows="" placeholder="请输入活动内容">${wxpActivity.content }</textarea>
				</h3>
			</div>
			<div class="newMeet_list1">
				<h4>
					<s:if test="wxpQyPictureList.size > 0">
						<s:iterator value="wxpQyPictureList" var="entity" status="s">
							<a href="#"><img src="${entity.pictureUrl }" class="upload_image" /></a>
						</s:iterator>
					</s:if>
					<a id="btn_addPic1" class="btn_addPic1"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /><input class=filePrew id="fileToUpload" title=支持jpg、jpeg、gif、png格式，文件小于5M tabIndex=3 type=file size=3 name="fileToUpload"></a><a href="#"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
				</h4>
			</div>
			<div class="newMeet_list2">
				<dl>
					<dt>
						<span>活动开始时间</span><strong><input type="text" id="startTime" name="wxpActivity.startTime" placeholder="2016-04-23"></strong>
					</dt>
					<dt>
						<span>活动结束时间</span><strong><input type="text" id="endTime" name="wxpActivity.endTime" placeholder="2016-04-23"></strong>
					</dt>
					<dt class="switch">
						<span>报名截止前30分钟提醒</span><strong><b></b></strong>
					</dt>
					<dt class="switch">
						<span>开启评论功能</span><strong><b class="cur"></b></strong>
					</dt>
					<dt class="switch">
						<span>将封面图片(第一张)显示在正文中</span><strong><b></b></strong>
					</dt>
					<dt class="surePay_click">
						<span>支付费用</span><strong><a href="#">不需费用</a> </strong>
					</dt>
					<dt class="eveLoc">
						<span>活动地点</span><strong><input type="text" placeholder="输入活动地点"></strong><a href="#" onclick="goshowMap();">地图</a>
					</dt>
					<dt>
						<span>活动参与对象</span><strong><a href="#">本部门</a> </strong>
					</dt>
					<dt>
						<span>数量限制(0为不限制)</span><strong>99</strong>
					</dt>
					<div id="datePlugin"></div>
				</dl>
			</div>
			<div class="newMeet_list1 newCla_list2">
				<h1>
					<span>附件（0）</span><a href="#" class="btn">+上传</a>
				</h1>
			</div>
			<div class="taskDetail_list2">
				<h2>
					<span class="btn"><a href="#" onclick="saveOrUpdate();">保存为草稿</a> </span><span class="btn"><a href="#" onclick="saveOrUpdate();">立即提交</a> </span>
				</h2>
				<p>如果你还没有确定现在立即发布，可以保存为草稿，之后可以再次编辑。</p>
			</div>
		</section>
	</form>
	<!---------  支付方式 ------->
	<div class="dialog_bg surePay">
		<div class="dialog_middle">
			<div class="dialog_con">
				<h1>
					支付方式<a href="#" class="icon"></a>
				</h1>
				<dl>
					<dt>
						<a href="#">AA付款</a> <span><input type="text">元</span>
					</dt>
					<dt class="check">
						<a href="#">自由付款</a>
					</dt>
					<dd>
						<b class="cur">使用微信在线支付（报名时支付）</b>
					</dd>
				</dl>
				<h2>
					<a href="#">不需费用</a><a href="#">确定</a>
				</h2>
			</div>
		</div>
	</div>
	<!---------  支付方式 end------->
</body>
</html>
<script>
	$('.newMeet_list2 dl .switch b,.surePay dl dd b').click(function() {
		if ($(this).hasClass('cur')) {
			$(this).removeClass('cur');
		} else {
			$(this).addClass('cur');
		}
	});
	$('.surePay dl dt a').click(function() {
		$(this).addClass('check').siblings('dt').removeClass('check');
	});
	$('.surePay_click').click(function() {
		$('.surePay').css("display", "table");
	})
	$('.surePay h1 a').click(function() {
		$('.surePay').fadeOut(300);
	});
	var params = {
	fileInput : $("#fileToUpload").get(0),
	url : $("#uploadForm").attr("action"),
	filter : function(files) {
		var arrFiles = [];
		for (var i = 0, file; file = files[i]; i++) {
			if (file.type.indexOf("image") == 0) {
				if (file.size >= 5120000) {
					alert('您这张"' + file.name + '"图片大小过大，应小于500k');
				} else {
					arrFiles.push(file);
				}
			} else {
				alert('文件"' + file.name + '"不是图片。');
			}
		}
		return arrFiles;
	},
	onSelect : function(files) {
		var html = '', i = 0;
		$("#btn_addPic1").before(html);
		var funAppendImage = function() {
			file = files[i];
			if (file) {
				var reader = new FileReader()
				reader.onload = function(e) {
					html = html + '<a href="#"><img id="uploadImage_' + i + '" src="' + e.target.result + '" class="upload_image" /></a>';
					i++;
					funAppendImage();
				}
				reader.readAsDataURL(file);
			} else {
				$("#btn_addPic1").before(html);
			}
		};
		funAppendImage();
	}
	};
	ZXXFILE = $.extend(ZXXFILE, params);
	ZXXFILE.init();
</script>