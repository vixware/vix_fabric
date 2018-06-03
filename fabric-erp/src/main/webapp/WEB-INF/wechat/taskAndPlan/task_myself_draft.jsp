<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>草稿</title>
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/date.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery.form.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/date.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/iscroll.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/zxxFile.js"></script>
<script type="text/javascript">
	$(function() {
		$('#taskStartTime').date({
			theme : "datetime"
		});
		$('#taskEndTime').date({
			theme : "datetime"
		});
	});
	function saveOrUpdate(sub) {
		var questName = $("#questName").val();
		var taskDescription = $("#taskDescription").val();

		if (!questName || $.trim(questName).length <= 0) {
			alert("请输入任务标题");
			return;
		}
		if (!taskDescription || $.trim(taskDescription).length <= 0) {
			alert("请输入任务内容");
			return;
		}
		$("#vixTaskForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/weChatTaskPlanAction!saveOrUpdate.action?sub=" + sub,
		dataType : "text",
		success : function(json) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goWechatTask.action";
		}
		});
	};
	function uploaddoc() {
		$("#vixTaskForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/weChatTaskPlanAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goWechatTask.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
	function goResponsiblePersonChoose() {
		$("#vixTaskForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/weChatTaskPlanAction!saveOrUpdate.action",
		dataType : "text",
		success : function(json) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goResponsiblePersonChoose.action?id=" + json;
		}
		});
	};
	function godeletedoc(uploaderId) {
		$.post("${vix}/wechat/weChatTaskPlanAction!deleteUploaderById.action?uploaderId=" + uploaderId, {}, function(id) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goWechatTask.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		});
	};
	//移除选择的人员
	function goDeleteEmployee(id, vixTaskId) {
		$.post("${vix}/wechat/weChatTaskPlanAction!deleteUploaderById.action?employeeId=" + id + "&vixTaskId=" + vixTaskId, {}, function(id) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goWechatTask.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		});
	};
	function deletePicture(wxpQyPictureId) {
		$.ajax({
		type : "post",
		url : "${vix}/wechat/weChatTaskPlanAction!deletePictureById.action?wxpQyPictureId=" + wxpQyPictureId,
		dataType : "Text",
		success : function(id) {
			//window.location = '${vix}/wechat/weChatTaskPlanAction!goWechatTask.action?id=' + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
</script>
</head>
<body>
	<form theme="simple" enctype="multipart/form-data" id="vixTaskForm">
		<input type="hidden" id="vixTaskId" name="vixTask.id" value="${vixTask.id }" /> <input type="hidden" id="employeeId" name="vixTask.employee.id" value="${vixTask.employee.id }" /> <input type="hidden" id="pictureUrl" name="vixTask.pictureUrl" value="${vixTask.pictureUrl }" />
		<section class="newMeet newCla new">
			<div class="newMeet_list2">
				<dl>
					<dt>
						<span>类型</span> <strong> <s:select id="taskTypeId" name="vixTask.taskType.id" class="validate[required,minCheckbox[1]]" list="%{taskTypeList}" listValue="name" listKey="id" value="%{vixTask.taskType.id}" multiple="false" theme="simple">
							</s:select>
						</strong>
					</dt>
				</dl>
			</div>
			<div class="newMeet_list1">
				<h2>
					<input type="text" id="questName" name="vixTask.questName" value="${vixTask.questName }" placeholder="请输入任务标题">
				</h2>
				<h3>
					<textarea id="taskDescription" name="vixTask.taskDescription" cols="" rows="" placeholder="请输入任务内容">${vixTask.taskDescription }</textarea>
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
							<a id="btn_addPic1" class="btn_addPic1"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /><input class=filePrew id="fileToUpload" title=支持jpg、jpeg、gif、png格式，文件小于5M tabIndex=3 type=file size=3 name="fileToUpload"></a> <a href="#" onclick="removeTag();"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
						</h4>
					</div>
				</div>
			</div>
			<div class="newMeet_list1 newCla_list2">
				<h1>
					<span>附件（${docNum }）</span> <a class="btn_addPic" href="javascript:void(0);">+上传 <input class="filePrew" tabIndex="3" type="file" size="3" id="docToUpload" name="docToUpload" onchange="uploaddoc();"></a>
				</h1>
			</div>
			<!----- 已有的附件--->
			<div class="attLis">
				<s:if test="uploaderList.size > 0">
					<s:iterator value="uploaderList" var="entity" status="s">
						<dl>
							<dt>
								<span><img src="${vix}/vixntcommon/base/images/icon-08.png"></span> <strong><b>${fn:substring(entity.fileName, 0,20)}</b>${entity.filesize }</strong>
							</dt>
							<a onclick="godeletedoc('${entity.id }');"><dd></dd></a>
						</dl>
					</s:iterator>
				</s:if>
			</div>
			<div class="newMeet_list2">
				<dl>
					<dt>
						<span>优先级</span> <strong> <select id="priority" name="vixTask.priority">
								<option value="1" <c:if test='${vixTask.priority eq 1}'>selected="selected"</c:if>>普通</option>
								<option value="2" <c:if test='${vixTask.priority eq 2}'>selected="selected"</c:if>>!不紧急但重要</option>
								<option value="3" <c:if test='${vixTask.priority eq 3}'>selected="selected"</c:if>>!!紧急但不重要</option>
								<option value="4" <c:if test='${vixTask.priority eq 4}'>selected="selected"</c:if>>!!!紧急且重要</option>
						</select>
						</strong>
					</dt>
					<dt>
						<span>开始时间</span><strong><input type="text" id="taskStartTime" name="vixTask.taskStartTime" value="${fn:substring(vixTask.taskStartTime, 0,19)}"></strong>
					</dt>
					<dt>
						<span>结束时间</span><strong><input type="text" id="taskEndTime" name="vixTask.taskEndTime" value="${fn:substring(vixTask.taskEndTime, 0,19)}"></strong>
					</dt>
					<div id="datePlugin"></div>
				</dl>
			</div>
			<div class="newMeet_list1 newCla_list2">
				<p>负责人</p>
				<h4 id="empid">
					<input type="hidden" id="tag" value="1" /> <input type="hidden" id="ids" name="ids" value="${ids}" />
					<s:if test="employeeList.size > 0">
						<s:iterator value="employeeList" var="entity" status="s">
							<a href="#" class="cmptag" onclick="removeEmpId(this,'${entity.id}');"><img src="${entity.headImgUrl }" /></a>
						</s:iterator>
					</s:if>
					<a href="#" onclick="goResponsiblePersonChoose();"><img src="${vix}/wechatcommon/images/newMeet_icon3.png" /></a> <a href="#" onclick="removeBTag();"><img src="${vix}/wechatcommon/images/newMeet_icon4.png" /></a>
				</h4>
			</div>
			<div class="taskDetail_list2">
				<h2>
					<span class="btn"><a href="#" onclick="saveOrUpdate('0');">保存为草稿</a> </span> <span class="btn"><a href="#" onclick="saveOrUpdate('1');">立即提交</a> </span>
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
	}
	function removeBTag() {
		if ($('#tag').val() == 1) {
			$("#empid a").each(function() {
				if ($(this).hasClass("cmptag")) {
					$(this).prepend("<b></b>");
				}
			});
			$('#tag').val(0);
		} else {
			$("#empid a").each(function() {
				$(this).find("b").remove();
			});
			$('#tag').val(1);
		}
	}
	function removeTag() {
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
	$(function() {
		$('.newCla_list2 p span b').click(function() {
			if ($(this).hasClass('cur')) {
				$(this).removeClass('cur');
			} else {
				$(this).addClass('cur');
			}
		})
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
				var reader = new FileReader();
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