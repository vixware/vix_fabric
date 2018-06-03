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
	$(function() {
		$('#meetingStartTime').date({
			theme : "datetime"
		});
		$('#meetingEndTime').date({
			theme : "datetime"
		});
	});
	function saveOrUpdate() {
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
		url : "${vix}/wechat/conferenceAssistantAction!saveOrUpdate.action?sub=1",
		dataType : "text",
		success : function(json) {
			window.location.href = "${vix}/wechat/conferenceAssistantAction!goList.action";
		}
		});
	}
	function goChooseIssuer() {
		$("#applicationMgForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/conferenceAssistantAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			window.location.href = "${vix}/wechat/conferenceAssistantAction!goChooseIssuer.action?id=" + id;
		}
		});
	};
	function goChooseAffiliated() {
		$("#applicationMgForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/conferenceAssistantAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			window.location.href = "${vix}/wechat/conferenceAssistantAction!goChooseAffiliated.action?id=" + id;
		}
		});
	};
	function uploaddoc() {
		$("#applicationMgForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/conferenceAssistantAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			window.location = "${vix}/wechat/conferenceAssistantAction!goSaveOrUpdate.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
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
	<form id="applicationMgForm">
		<input type="hidden" id="applicationMgId" name="applicationMg.id" value="${applicationMg.id }" /> <input type="hidden" id="employeeId" name="applicationMg.employee.id" value="${applicationMg.employee.id }" />
		<section class="newMeet newCla new">
			<div class="newMeet_list1">
				<h2>
					<input type="text" id="meetingTheme" name="applicationMg.meetingTheme" value="${applicationMg.meetingTheme }" placeholder="请输入会议主题">
				</h2>
				<h3>
					<textarea id="meetingDescription" name="applicationMg.meetingDescription" cols="" rows="" placeholder="请输入会议内容">${applicationMg.meetingDescription }</textarea>
				</h3>
			</div>
			<div class="newMeet_list1">
				<h4>
					<s:if test="wxpQyPictureList.size > 0">
						<s:iterator value="wxpQyPictureList" var="entity" status="s">
							<a href="#"><img src="${vix}${entity.pictureUrl }" class="upload_image" /></a>
						</s:iterator>
					</s:if>
					<a id="btn_addPic1" class="btn_addPic1"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /><input class=filePrew id="fileToUpload" title=支持jpg、jpeg、gif、png格式，文件小于5M tabIndex=3 type=file size=3 name="fileToUpload"></a><a href="#"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
				</h4>
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
							<a onclick=""><dd></dd></a>
						</dl>
					</s:iterator>
				</s:if>
			</div>
			<div class="newMeet_list1 newCla_list2">
				<p>发起人</p>
				<h4>
					<s:if test="employeeList.size > 0">
						<input type="hidden" id="ids" name="ids" value="${ids}" />
						<s:iterator value="employeeList" var="entity" status="s">
							<a href="#"><img src="${entity.headImgUrl }" /></a>
						</s:iterator>
					</s:if>
					<a href="#" onclick="goChooseIssuer();"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /></a><a href="#"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
				</h4>
				<p>参会人</p>
				<h4>
					<s:if test="empList.size > 0">
						<input type="hidden" id="affiliatedIds" name="affiliatedIds" value="${affiliatedIds}" />
						<s:iterator value="empList" var="entity" status="s">
							<a href="#"><img src="${entity.headImgUrl }" /></a>
						</s:iterator>
					</s:if>
					<a href="#" onclick="goChooseAffiliated();"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /></a><a href="#"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
				</h4>
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
			if (file.type.indexOf("image") == 0) {
				if (file.size >= 512000) {
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