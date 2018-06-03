<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/date.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery.form.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/date.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/iscroll.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/zxxFile.js"></script>
<script type="text/javascript">
	function saveOrUpdate() {
		var title = $("#title").val();
		var trendsContent = $("#trendsContent").val();

		// 验证
		if (!title || $.trim(title).length <= 0) {
			alert("请输入新闻标题");
			return;
		}

		if (!trendsContent || $.trim(trendsContent).length <= 0) {
			alert("请输入新闻内容");
			return;
		}
		$("#wechatNewsForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/identityVerificationAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			window.location.href = "${vix}/wechat/identityVerificationAction!goList.action?id=" + id;
		}
		});
	};
</script>
</head>
<body>
	<form id="wechatNewsForm" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" id="trendsId" name="trends.id" value="${trends.id }" />
		<section class="newMeet newCla new">
			<div class="newMeet_list2">
				<dl>
					<dt>
						<span>类型</span> <strong> <select id="catalogy" name="trends.catalogy">
								<option value="1">公告</option>
								<option value="2">通知</option>
								<option value="3" selected="selected">新闻</option>
						</select>
						</strong>
					</dt>
				</dl>
			</div>
			<div class="newMeet_list1">
				<h2>
					<input type="text" id="title" name="trends.title" value="${trends.title }" placeholder="请输入新闻主题">
				</h2>
				<h3>
					<textarea id="trendsContent" name="trends.content" cols="" rows="" placeholder="请输入新闻内容">${trends.content }</textarea>
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
		</section>
		<section class="newMeet newCla posEve">
			<div class="newMeet_list2">
				<dl>
					<dt class="switch">
						<span>将封面图片(第一张)显示在正文中</span><strong><b></b></strong>
					</dt>
					<dt class="switch">
						<span>开启评论功能</span><strong><b class="cur"></b></strong>
					</dt>
					<dt class="switch">
						<span>发布时发送通知消息</span><strong><b class="cur"></b></strong>
					</dt>
				</dl>
			</div>
			<div class="taskDetail_list2">
				<h2>
					<span class="btn"><a href="#" onclick="saveOrUpdate();">保存为草稿</a> </span><span class="btn"><a href="#" onclick="saveOrUpdate();">立即提交</a> </span>
				</h2>
				<p>中盛科技提供技术支持</p>
			</div>
		</section>
	</form>
</body>
</html>
<script>
	$('.newMeet_list2 dl .switch b').click(function() {
		if ($(this).hasClass('cur')) {
			$(this).removeClass('cur');
		} else {
			$(this).addClass('cur');
		}
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