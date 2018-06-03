<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/date.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/common/js/jquery.form.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/date.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/iscroll.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/zxxFile.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<title>添加日志</title>

</head>
<input type="hidden" id="nonceStr" value="${nonceStr }" />
<input type="hidden" id="timestamp" value="${timestamp}" />
<input type="hidden" id="signature" value="${signature }" />
<input type="hidden" id="qiyeCorpId" value="${qiyeCorpId }" />
<script type="text/javascript">
	function saveOrUpdate(sub) {
		var title = $("#title").val();
		var logContent = $("#logContent").val();

		// 验证
		if (!title || $.trim(title).length <= 0) {
			alert("请输入日志标题");
			return;
		}
		if (!logContent || $.trim(logContent).length <= 0) {
			alert("请输入日志内容");
			return;
		}

		$("#workLogForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/jobLogAction!saveOrUpdate.action?sub="+sub,
		dataType : "text",
		success : function(data) {
			var d = data.split(",");
			if(d[1]==1){
			   window.location.href = "${vix}/wechat/jobLogAction!goList.action";
			}else {
			   window.location.href = "${vix}/wechat/jobLogAction!goDraftList.action";
			}
		}
		});
	}
	$(function() {
		$("#logType").val(${workLog.logType });
		$(".newMeet_list1 h4 a b").click(function() {
			$(this).parents('a').remove();
		});
	});
	function deletepic(wxpQyPictureId) {
		$.ajax({
			type : "post",
			url : "${vix}/wechat/jobLogAction!deletePictureById.action?wxpQyPictureId="+ wxpQyPictureId,
			data : {
			"wxpQyPictureId" : wxpQyPictureId
			},
			dataType : "Text",
			success : function(id) {
			}
			});
	};
	
	function uploaddoc() {
		$("#workLogForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/jobLogAction!saveOrUpdate.action",
		dataType : "text",
		success : function(data) {
			var d = data.split(",");
			window.location.href = "${vix}/wechat/jobLogAction!goSaveOrUpdate.action?id=" + d[0];
		}
		});
	};
	function goChooseEmployee() {
		$("#workLogForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/jobLogAction!saveOrUpdate.action",
		dataType : "text",
		success : function(data) {
			var d = data.split(",");
			window.location = "${vix}/wechat/jobLogAction!goChooseEmployee.action?id=" + d[0];
		}
		});
	};
	function godeletedoc(uploaderId) {
		$.post("${vix}/wechat/jobLogAction!deleteUploaderById.action?uploaderId=" + uploaderId, {}, function(id) {
			$.ajax({
				url : '${vix}/wechat/jobLogAction!goJobLogDocList.action?id=' + id,
				cache : false,
				success : function(html) {
					$("#uploaderId").html(html);
				}
				});
		});
	};
</script>

<body>
	<form theme="simple" enctype="multipart/form-data" id="workLogForm">
		<section class="newMeet newCla new">
			<input type="hidden" id="workLogId" name="workLog.id" value="${workLog.id }" /> <input type="hidden" id="employeeId" name="employee.id" value="${employee.id }" />
			<div class="newMeet_list2">
				<dl>
					<dt>
						<span>日志类型</span> <strong> <select id="logType" name="workLog.logType">
								<option value="0" selected="selected">工作日志</option>
								<option value="1">个人日志</option>
						</select>
						</strong>
					</dt>
				</dl>
			</div>
			<div class="newMeet_list1">
				<h2>
					<input type="text" id="title" name="workLog.title" value="${workLog.title }" placeholder="请输入日志标题">
				</h2>
				<h3>
					<textarea id="logContent" name="workLog.logContent" cols="" rows="" placeholder="请输入日志内容">${workLog.logContent }</textarea>
				</h3>
			</div>
			<div class="detBox" id="isPointPraiseId">
				<div class="con">
					<div class="newMeet_list1">
						<h4 id="imgId">
							<input type="hidden" id="pictureTag" value="1" />
							<s:if test="wxpQyPictureList.size > 0">
								<s:iterator value="wxpQyPictureList" var="entity" status="s">
									<a href="#" class="imgtag" onclick="removeImgId(this,'${entity.id}');"><img src="${vix}${entity.pictureUrl }" class="upload_image" /></a>
								</s:iterator>
							</s:if>
							<a id="btn_addPic1" class="btn_addPic1"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /> <input class=filePrew id="fileToUpload" title=支持jpg、jpeg、gif、png格式，文件小于5M tabIndex=3 type=file size=3 name="fileToUpload"></a> <a href="#" onclick="removeTag();"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
						</h4>
					</div>
				</div>
			</div>
			<div id="uploaderId">
				<div class="newMeet_list1 newCla_list2">
					<h1>
						<span>附件（${docNum }）</span><a class="btn_addPic" href="javascript:void(0);">+上传 <input class="filePrew" tabIndex="3" type="file" size="3" id="docToUpload" name="docToUpload" onchange="uploaddoc();"></a>
					</h1>
				</div>
				<!----- 已有的附件--->
				<div class="attLis" id="uploaderId">
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
			</div>
			<div class="newMeet_list1 newCla_list2">
				<p>上级领导</p>
				<h4 id="empid">
					<input type="hidden" id="tag" value="1" /> <input type="hidden" id="ids" name="ids" value="${ids}" />
					<s:if test="employeeList.size > 0">
						<s:iterator value="employeeList" var="entity" status="s">
							<a href="#" class="cmptag" onclick="removeEmpId(this,'${entity.id}');"><img src="${entity.headImgUrl }" /></a>
						</s:iterator>
					</s:if>
					<a href="#" onclick="goChooseEmployee();"><img src="${vix}/wechatcommon/images/newMeet_icon3.png" /></a><a href="#" onclick="removeBTag();"><img src="${vix}/wechatcommon/images/newMeet_icon4.png" /></a>
				</h4>
			</div>
			<div style="height: 70px; clear: both;"></div>
			<div class="taskDetail_list2">
				<h2>
					<span class="btn"><a href="#" onclick="saveOrUpdate('0');">保存为草稿</a> </span><span class="btn"><a href="#" onclick="saveOrUpdate('1');">立即提交</a> </span>
				</h2>
				<p>中盛科技提供技术支持</p>
			</div>
		</section>
	</form>
</body>
</html>
<script type="text/javascript">

	$(function() {
		$('.newCla_list2 p span b').click(function() {
			if ($(this).hasClass('cur')) {
				$(this).removeClass('cur');
			} else {
				$(this).addClass('cur');
			}
		})
	});
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
		if ($('#pictureTag').val() == 1) {
			$("#imgId a").each(function() {
				if ($(this).hasClass("imgtag")) {
					$(this).prepend("<b></b>");
				}
			});
			$('#pictureTag').val(0);
		} else {
			$("#imgId a").each(function() {
				$(this).find("b").remove();
			});
			$('#pictureTag').val(1);
		}
	};
	function removeImgId(a,wxpQyPictureId) {
		var b = a.getElementsByTagName("b");
		if (b.length > 0) {
			$(a).remove();
			deletepic(wxpQyPictureId)
		}
	};
	
	
	
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
					html = html + '<a href="#" class="imgtag" onclick="removeImgId(this,\'${entity.id}\');"><img id="uploadImage_' + i + '" src="' + e.target.result + '" class="upload_image" /></a>';
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