<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		$('#vacateendDate').date({
			theme : "datetime"
		});
		$('#vacateStartdate').date({
			theme : "datetime"
		});
	});

	function editTripRecord() {
		var name = $("#name").val();
		var vacateReason = $("#vacateReason").val();

		// 验证
		if (!name || $.trim(name).length <= 0) {
			alert("请输入请假标题");
			return;
		}

		if (!vacateReason || $.trim(vacateReason).length <= 0) {
			alert("请输入请假内容");
			return;
		}
		$("#leaveApproveForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/weChatLeaveApproveAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			window.location = "${vix}/wechat/weChatLeaveApproveAction!goSaveOrUpdate.action";
		}
		});
	};
	//选择审批人
	function goChooseApprovalPerson() {
		$("#leaveApproveForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/weChatLeaveApproveAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			window.location.href = "${vix}/wechat/weChatLeaveApproveAction!goChooseApprovalPerson.action?id=" + id;
		}
		});
	};
</script>
</head>
<body>
	<form id="leaveApproveForm">
		<input type="hidden" id="tripRecordId" name="tripRecord.id" value="${tripRecord.id }" /> <input type="hidden" id="employeeId" name="tripRecord.employee.id" value="${tripRecord.employee.id }" />
		<section class="newMeet newCla new">
			<div class="newMeet_list2">
				<dl>
					<dt>
						<span>类型</span> <strong> <select id="vacateType" name="tripRecord.vacateType">
								<option value="">请选择类型</option>
								<option value="1" <c:if test='${tripRecord.vacateType eq 1}'>selected="selected"</c:if>>调休</option>
								<option value="2" <c:if test='${tripRecord.vacateType eq 2}'>selected="selected"</c:if>>事假</option>
								<option value="3" <c:if test='${tripRecord.vacateType eq 3}'>selected="selected"</c:if>>病假</option>
								<option value="4" <c:if test='${tripRecord.vacateType eq 4}'>selected="selected"</c:if>>年休假</option>
								<option value="5" <c:if test='${tripRecord.vacateType eq 5}'>selected="selected"</c:if>>婚假</option>
								<option value="6" <c:if test='${tripRecord.vacateType eq 6}'>selected="selected"</c:if>>产假/陪产假</option>
								<option value="7" <c:if test='${tripRecord.vacateType eq 7}'>selected="selected"</c:if>>丧假</option>
								<option value="8" <c:if test='${tripRecord.vacateType eq 8}'>selected="selected"</c:if>>远途出差</option>
						</select>
						</strong>
					</dt>
				</dl>
			</div>
			<div class="newMeet_list1">
				<h2>
					<input type="text" id="name" name="tripRecord.name" value="${tripRecord.name }" placeholder="请输入请假标题">
				</h2>
				<h3>
					<textarea id="vacateReason" name="tripRecord.vacateReason" cols="" rows="" placeholder="请输入请假原因">${tripRecord.vacateReason }</textarea>
				</h3>
			</div>
			<div class="newMeet_list2">
				<dl>
					<dt>
						<span>申请时长（天）</span><strong><input type="text" id="dates" name="tripRecord.dates" value="${tripRecord.dates }"></strong>
					</dt>
					<dt>
						<span>申请时长（小时）</span><strong><input type="text" id="minutes" name="tripRecord.minutes" value="${tripRecord.minutes }"></strong>
					</dt>
					<dt>
						<span>开始时间</span><strong><input type="text" id="vacateendDate" name="tripRecord.vacateendDate" value="<s:date name="%{tripRecord.vacateendDate}" format="yyyy-MM-dd HH:mm"/>"></strong>
					</dt>
					<dt>
						<span>结束时间</span><strong><input type="text" id="vacateStartdate" name="tripRecord.vacateStartdate" value="<s:date name="%{tripRecord.vacateStartdate}" format="yyyy-MM-dd HH:mm"/>"></strong>
					</dt>
					<div id="datePlugin"></div>
				</dl>
			</div>
			<div class="newMeet_list1 newCla_list2">
				<h1>本次申请共${tripRecord.dates }天</h1>
				<h4>
					<s:if test="wxpQyPictureList.size > 0">
						<s:iterator value="wxpQyPictureList" var="entity" status="s">
							<a href="#"><img src="${vix}${entity.pictureUrl }" class="upload_image" /></a>
						</s:iterator>
					</s:if>
					<a id="btn_addPic1" class="btn_addPic1"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /><input class=filePrew id="fileToUpload" title=支持jpg、jpeg、gif、png格式，文件小于5M tabIndex=3 type=file size=3 name="fileToUpload"></a><a href="#"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
				</h4>
				<p>审批人</p>
				<h4 id="id">
					<input type="hidden" id="tag" name="tag" value="1" />
					<s:if test="employeeList.size > 0">
						<input type="hidden" id="ids" name="ids" value="${ids}" />
						<s:iterator value="employeeList" var="entity" status="s">
							<a href="#" class="cmptag" onclick="removeEmpId(this,'${entity.id}');"><img src="${entity.headImgUrl }" /></a>
						</s:iterator>
					</s:if>
					<a href="#" onclick="goChooseApprovalPerson();"><img src="${vix}/wechatcommon/images/newMeet_icon3.png" /></a><a href="#" onclick="removeBTag();"><img src="${vix}/wechatcommon/images/newMeet_icon4.png" /></a>
				</h4>
			</div>
			<div style="height: 70px; clear: both;"></div>
			<div class="taskDetail_list2">
				<h2>
					<span class="btn"><a href="#" onclick="editTripRecord();">保存为草稿</a> </span><span class="btn"><a href="#" onclick="editTripRecord();">立即提交</a> </span>
				</h2>
				<p>中盛科技提供技术支持</p>
			</div>
		</section>
	</form>
</body>
</html>
<script type="text/javascript">
	function removeEmpId(a, id) {
		var b = a.getElementsByTagName("b");
		if (b.length > 0) {
			var ids = $('#ids').val();
			ids = ids.replace(id, '');
			$('#ids').val(ids);
			$(a).remove();
		}
	}
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
	}
	$(function() {
		$('.newCla_list2 p span b').click(function() {
			if ($(this).hasClass('cur')) {
				$(this).removeClass('cur');
			} else {
				$(this).addClass('cur');
			}
		});
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