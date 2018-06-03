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
<link href="${vix}/wechatcommon/weui-master/dist/style/weui.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/weui-master/dist/example/example.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/weui-master/dist/example/zepto.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/weui-master/dist/example/weui.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/weui-master/dist/example/example.js"></script>
<script type="text/javascript">
	$(function() {
		$('#meetingStartTime').date({
			theme : "datetime"
		});
		$('#meetingEndTime').date({
			theme : "datetime"
		});
	});
	function saveOrUpdate(sub) {
		var meetingTheme = $("#meetingTheme").val();
		var meetingDescription = $("#meetingDescription").val();

		// 验证
		if (!meetingTheme || $.trim(meetingTheme).length <= 0) {
			alert("请输入会议主题");
			return;
		}

		if (!meetingDescription || $.trim(meetingDescription).length <= 0) {
			alert("请输入会议内容");
			return;
		}
		$("#applicationMgForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/conferenceAssistantAction!saveOrUpdate.action?sub=" + sub,
		dataType : "text",
		success : function(json) {
			window.location.href = "${vix}/wechat/conferenceAssistantAction!goMyList.action";
		}
		});
	}
	function goChooseIssuer() {
		$("#applicationMgForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/conferenceAssistantAction!saveOrUpdate.action",
		dataType : "text",
		success : function(data) {
			var d = data.split(";");
			window.location.href = "${vix}/wechat/conferenceAssistantAction!goChooseIssuer.action?id=" + d[0] + "&issuerIds=" + d[1];
		}
		});
	};
	function goChooseAffiliated() {
		$("#applicationMgForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/conferenceAssistantAction!saveOrUpdate.action",
		dataType : "text",
		success : function(data) {
			var d = data.split(";");
			window.location.href = "${vix}/wechat/conferenceAssistantAction!goChooseAffiliated.action?id=" + d[0] + "&affIds=" + d[2];
		}
		});
	};
	function uploaddoc() {
		$("#applicationMgForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/conferenceAssistantAction!saveOrUpdate.action",
		dataType : "text",
		success : function(data) {
			var d = data.split(";");
			window.location = "${vix}/wechat/conferenceAssistantAction!goSaveOrUpdate.action?id=" + d[0] + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};

	function godeletedoc(uploaderId) {
		$.post("${vix}/wechat/conferenceAssistantAction!deleteUploaderById.action?uploaderId=" + uploaderId, {}, function(id) {
			window.location = "${vix}/wechat/conferenceAssistantAction!goSaveOrUpdate.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		});
	};
	function deletePicture(wxpQyPictureId) {
		$.ajax({
		type : "post",
		url : "${vix}/wechat/conferenceAssistantAction!deletePictureById.action?wxpQyPictureId=" + wxpQyPictureId,
		dataType : "Text",
		success : function(id) {
			//window.location = '${vix}/wechat/conferenceAssistantAction!goSaveOrUpdate.action?id=' + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};

	function removeImgTag() {
		if ($('#imgTag').val() == 1) {
			$("#imgId a").each(function() {
				if ($(this).hasClass("imgclass")) {
					$(this).prepend("<b></b>");
				}
			});
			$('#imgTag').val(0);
		} else {
			$("#imgId a").each(function() {
				$(this).find("b").remove();
			});
			$('#imgTag').val(1);
		}
	}
	function removeImgId(a, id) {
		var b = a.getElementsByTagName("b");
		if (b.length > 0) {
			$(a).remove();
			deletePicture(id);
		}
	}
</script>
</head>
<body>
	<form id="applicationMgForm">
		<input type="hidden" id="applicationMgId" name="applicationMg.id" value="${applicationMg.id }" /> <input type="hidden" id="employeeId" name="applicationMg.employee.id" value="${applicationMg.employee.id }" />
		<section class="newMeet newCla new">
			<div class="newMeet_list1">
				<h2>
					<input type="text" id="meetingTheme" name="applicationMg.meetingTheme" value="${applicationMg.meetingTheme }" placeholder="请输入会议主题" required>
				</h2>
				<h3>
					<textarea id="meetingDescription" name="applicationMg.meetingDescription" cols="" rows="" placeholder="请输入会议内容">${applicationMg.meetingDescription }</textarea>
				</h3>
			</div>
			<div class="detBox">
				<div class="con">
					<div class="newMeet_list1">
						<h4 id="imgId">
							<input type="hidden" id="imgTag" value="1" />
							<s:if test="wxpQyPictureList.size > 0">
								<s:iterator value="wxpQyPictureList" var="entity" status="s">
									<a href="#" class="imgclass" onclick="removeImgId(this,'${entity.id}');"><img src="${vix}${entity.pictureUrl }" class="upload_image" /></a>
								</s:iterator>
							</s:if>
							<a id="btn_addPic1" class="btn_addPic1"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /><input class=filePrew id="fileToUpload" title=支持jpg、jpeg、gif、png格式，文件小于5M tabIndex=3 type=file size=3 name="fileToUpload"></a> <a href="#" onclick="removeImgTag();"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
						</h4>
					</div>
				</div>
			</div>
			<div class="newMeet_list2">
				<dl>
					<dt>
						<span>开始时间</span><strong><input type="text" id="meetingStartTime" name="applicationMg.meetingStartTime" value="${fn:substring(applicationMg.meetingStartTime, 0,19)}"></strong>
					</dt>
					<dt>
						<span>结束时间</span><strong><input type="text" id="meetingEndTime" name="applicationMg.meetingEndTime" value="${fn:substring(applicationMg.meetingEndTime, 0,19)}"></strong>
					</dt>
					<div id="datePlugin"></div>
				</dl>
			</div>
			<div class="newMeet_list2">
				<dl>
					<dt>
						<span>会议地点</span> <strong><s:select id="meetingRequestId" name="applicationMg.meetingRequest.id" headerKey="-1" headerValue="--请选择--" class="validate[required,minCheckbox[1]]" list="%{meetingRequestList}" listValue="meetingName" listKey="id" value="%{applicationMg.meetingRequest.id}" multiple="false" theme="simple">
							</s:select> </strong>
					</dt>
				</dl>
			</div>
		</section>
		<section class="newMeet newCla">
			<div class="newMeet_list1 newCla_list2">
				<!-- <p>
					二维码签到<span><b class="cur"></b></span>
				</p> -->
				<h1>
					<span>附件（${docNum }）</span><a class="btn_addPic" href="javascript:void(0);">+上传 <input class="filePrew" tabIndex="3" type="file" size="3" id="docToUpload" name="docToUpload" onchange="uploaddoc();"></a>
				</h1>
			</div>
			<!----- 已有的附件--->
			<div class="attLis">
				<s:if test="uploaderList.size > 0">
					<s:iterator value="uploaderList" var="entity" status="s">
						<dl>
							<dt>
								<span><img src="${vix}/vixntcommon/base/images/icon-08.png"></span> <strong><b>${entity.fileName }</b>${entity.filesize }</strong>
							</dt>
							<a onclick="godeletedoc('${entity.id }');"><dd></dd></a>
						</dl>
					</s:iterator>
				</s:if>
			</div>
			<div class="newMeet_list1 newCla_list2">
				<p>发起人</p>
				<h4 id="id">
					<input type="hidden" id="tag" name="tag" value="1" />
					<s:if test="employeeList.size > 0">
						<input type="hidden" id="ids" name="ids" value="${ids}" />
						<s:iterator value="employeeList" var="entity" status="s">
							<a href="#" class="cmptag" onclick="removeEmpId(this,'${entity.id}');"><img src="${entity.headImgUrl }" /></a>
						</s:iterator>
					</s:if>
					<a href="#" onclick="goChooseIssuer();"><img src="${vix}/wechatcommon/images/newMeet_icon3.png" /></a><a href="#" onclick="removeBTag();"><img src="${vix}/wechatcommon/images/newMeet_icon4.png" /></a>
				</h4>
				<p>参会人</p>
				<h4 id="tagId">
					<input type="hidden" id="affiliatedtag" name="affiliatedtag" value="1" />
					<s:if test="empList.size > 0">
						<input type="hidden" id="affiliatedIds" name="affiliatedIds" value="${affiliatedIds}" />
						<s:iterator value="empList" var="entity" status="s">
							<a href="#" class="affiliatedtag" onclick="removeAffiliatedEmpId(this,'${entity.id}');"><img src="${entity.headImgUrl }" /></a>
						</s:iterator>
					</s:if>
					<a href="#" onclick="goChooseAffiliated();"><img src="${vix}/wechatcommon/images/newMeet_icon3.png" /></a><a href="#" onclick="removeTag();"><img src="${vix}/wechatcommon/images/newMeet_icon4.png" /></a>
				</h4>
			</div>
			<div class="taskDetail_list2">
				<h2>
					<span class="btn"><a href="#" onclick="saveOrUpdate('1');">保存为草稿</a> </span><span class="btn"><a href="#" onclick="saveOrUpdate('0');">立即提交</a> </span>
				</h2>
				<p>中盛科技提供技术支持</p>
			</div>
		</section>
	</form>
</body>
</html>
<script>
	function removeEmpId(a, id) {
		var b = a.getElementsByTagName("b");
		if (b.length > 0) {
			var ids = $('#ids').val();
			ids = ids.replace(id, '');
			$('#ids').val(ids);
			$(a).remove();
		}
	};
	function removeAffiliatedEmpId(a, id) {
		var b = a.getElementsByTagName("b");
		if (b.length > 0) {
			var affiliatedIds = $('#affiliatedIds').val();
			affiliatedIds = affiliatedIds.replace(id, '');
			$('#affiliatedIds').val(affiliatedIds);
			$(a).remove();
		}
	};
	function removeBTag() {
		if ($('#tag').val() == 1) {
			$("#id a").each(function() {
				if ($(this).hasClass("cmptag")) {
					$(this).prepend("<b></b>");
				}
			});
			$('#tag').val(0);
		} else {
			$("#id a").each(function() {
				$(this).find("b").remove();
			});
			$('#tag').val(1);
		}
	};
	function removeTag() {
		if ($('#affiliatedtag').val() == 1) {
			$("#tagId a").each(function() {
				if ($(this).hasClass("affiliatedtag")) {
					$(this).prepend("<b></b>");
				}
			});
			$('#affiliatedtag').val(0);
		} else {
			$("#tagId a").each(function() {
				$(this).find("b").remove();
			});
			$('#affiliatedtag').val(1);
		}
	};
	$('.newMeet_list2 dl .switch b,.newCla_list2 p b').click(function() {
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
	});
	$('.surePay h1 a').click(function() {
		$('.surePay').fadeOut(300);
	});

	var params = {
	fileInput : $("#fileToUpload").get(0),
	url : $("#uploadForm").attr("action"),
	filter : function(files) {
		var arrFiles = [];
		for (var i = 0, file; file = files[i]; i++) {
			var fileKb = Math.ceil(file.size / 1024);
			var fileExt = (/[.]/.exec(file.name)) ? /[^.]+$/.exec(file.name.toLowerCase()) : '';
			if (fileKb > 5000) {
				alert('您这张"' + file.name + '"图片大小过大，应小于5000k');
			}
			if (file.type == 'image' && fileExt != "jpg" && fileExt != "jpeg" && fileExt != "JPG" && fileExt != "JPEG" && fileExt != "gif" && fileExt != "GIF" && fileExt != "png" && fileExt != "PNG") {
				alert('文件"' + file.name + '"不是图片。');
			}
			arrFiles.push(file);
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
					html = html + '<a href="#" class="imgclass" onclick="removeImgId(this,\'${entity.id}\');"><img id="uploadImage_' + i + '" src="' + e.target.result + '" class="upload_image" /></a>';
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