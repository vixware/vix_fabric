<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="announcementNotificationForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/noticeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="announcementNotification.id" value="${announcementNotification.id}" /> <input type="hidden" id="content" name="announcementNotificationContent" value="${announcementNotification.content}" /> <input type="hidden" id="pointPraiseNums"
		name="announcementNotification.pointPraiseNums" value="${announcementNotification.pointPraiseNums}" /> <input type="hidden" id="replyNums" name="announcementNotification.replyNums" value="${announcementNotification.replyNums}" /> <input type="hidden" id="readTimes"
		name="announcementNotification.readTimes" value="${announcementNotification.readTimes}" /> <input type="hidden" id="employeeIds" name="employeeIds" value="" /> <input type="hidden" id="fileId" name="fileId" value="" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 公告标题:</label>
			<div class="col-md-10">
				<input id="title" name="announcementNotification.title" value="${announcementNotification.title}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 开始时间:</label>
			<div class="col-md-4">
				<input id="activeStartDate" name="announcementNotification.activeStartDate" value="<s:date name="%{announcementNotification.activeStartDate}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 结束时间:</label>
			<div class="col-md-4">
				<input id="activeEndDate" name="announcementNotification.activeEndDate" value="<s:date name="%{announcementNotification.activeEndDate}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">类型:</label>
			<div class="col-md-4">
				<s:if test="announcementNotification.bulletinType == 0">
					<label class="radio radio-inline"> <input type="radio" id="bulletinType" name="announcementNotification.bulletinType" value="0" checked="checked"> 公告
					</label>
					<label class="radio radio-inline"> <input type="radio" id="bulletinType" name="announcementNotification.bulletinType" value="1"> 通知
					</label>
				</s:if>
				<s:elseif test="announcementNotification.bulletinType == 1">
					<label class="radio radio-inline"> <input type="radio" id="bulletinType" name="announcementNotification.bulletinType" value="0"> 公告
					</label>
					<label class="radio radio-inline"> <input type="radio" id="bulletinType" name="announcementNotification.bulletinType" value="1" checked="checked"> 通知
					</label>
				</s:elseif>
				<s:else>
					<label class="radio radio-inline"> <input type="radio" id="bulletinType" name="announcementNotification.bulletinType" value="0" checked="checked"> 公告
					</label>
					<label class="radio radio-inline"> <input type="radio" id="bulletinType" name="announcementNotification.bulletinType" value="1"> 通知
					</label>
				</s:else>
			</div>
			<label class="col-md-2 control-label">状态:</label>
			<div class="col-md-4">
				<s:if test="announcementNotification.isPublish == 0">
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="announcementNotification.isPublish" value="0" checked="checked"> 发布
					</label>
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="announcementNotification.isPublish" value="1"> 终止
					</label>
				</s:if>
				<s:elseif test="announcementNotification.isPublish == 1">
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="announcementNotification.isPublish" value="0"> 发布
					</label>
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="announcementNotification.isPublish" value="1" checked="checked"> 终止
					</label>
				</s:elseif>
				<s:else>
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="announcementNotification.isPublish" value="0" checked="checked"> 发布
					</label>
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="announcementNotification.isPublish" value="1"> 终止
					</label>
				</s:else>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> <span class="text-danger">*</span> 发布对象:
			</label>
			<div class="col-md-10">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="employeeNames" name="announcementNotification.pubNames" value="${announcementNotification.pubNames }" class="form-control validate[required]" type="text" readonly="readonly" data-prompt-position="topLeft" />
							<div class="input-group-btn">
								<button onclick="chooseEmployee();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i> 选择
								</button>
								<button onclick="$('#employeeIds').val('');$('#employeeNames').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i> 清空
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">图片:</label>
			<div class="col-md-10">
				<div style="float: left; display: inline;">
					<img id="mainPic" onerror="$('#mainPic').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${nvix}/${announcementNotification.firstPictureUrl}" width="30px" height="30px" onmouseover="$('#mainPicBig').attr('style','position: absolute;padding:-50px;z-index:9999;');"
						onmouseout="$('#mainPicBig').attr('style','display:none;');" /> <img id="mainPicBig" onerror="$('#mainPicBig').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${nvix}/${announcementNotification.firstPictureUrl}" width="100" height="100"
						style="display: none; position: absolute;" /> &nbsp;&nbsp;
				</div>
				<input type="file" id="fileToUpload" name="fileToUpload" onchange="fileChange();" style="width: 80%;">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 内容简介:</label>
			<div class="col-md-10">
				<input id="plotSummary" name="announcementNotification.plotSummary" value="${announcementNotification.plotSummary}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> <span class="text-danger">*</span> 内容:
			</label>
			<div class="col-md-10">
				<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="jarviswidget" id="">
						<header>
							<span class="widget-icon"> <i class="fa fa-pencil"></i>
							</span>
							<h2>内容</h2>
						</header>
						<div>
							<div class="jarviswidget-editbox"></div>
							<div class="widget-body no-padding">
								<textarea id="announcementNotificationContent" name="announcementNotification.content"> ${announcementNotification.content }</textarea>
							</div>
						</div>
					</div>
				</article>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$(document).ready(function() {
		//富文本编辑器
		CKEDITOR.replace('announcementNotificationContent', {
		height : '350px',
		startupFocus : true
		});
	});
	//表单校验
	$("#projectImageForm").validationEngine();
	//上传图片
	function fileChange() {
		uploadFileOrImage("projectImageForm", "${nvix}/nvixnt/noticeAction!saveOrUpdateWxpQyPicture.action", "fileToUpload", "image", function(data) {
			var d = data.split(",");
			if (d[0] == '0') {
				showMessage(d[1]);
			} else {
				$('#fileId').val(d[0]);
				$("#mainPic").attr("src", "${nvix}" + d[1]);
				$("#mainPicBig").attr("src", "${nvix}" + d[1]);
				showMessage("文件上传成功!");
			}
		});
	};
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goEmployeeChoose.action', 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#employeeIds').val(emp[0]);
				$('#employeeNames').val(emp[1]);
			} else {
				layer.alert("请选择人员!");
			}
		});
	};
</script>