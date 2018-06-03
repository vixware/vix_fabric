<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="trendsForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/newsAdministrationAction!saveOrUpdatePublishNews.action">
	<input type="hidden" id="id" name="trends.id" value="${trends.id}" /> <input type="hidden" id="employeeIds" name="employeeIds" value="" /> <input type="hidden" id="fileId" name="fileId" value="" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 新闻标题:</label>
			<div class="col-md-4">
				<input id="title" name="trends.title" value="${trends.title}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label">副标题:</label>
			<div class="col-md-4">
				<input id="subtitle" name="trends.subtitle" value="${trends.subtitle}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 发布时间:</label>
			<div class="col-md-4">
				<input id="createTime" name="trends.createTime" value="${trends.createTimeStr}" data-prompt-position="topLeft" class="form-control  validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" />
			</div>
			<label class="col-md-2 control-label"></span> 新闻来源:</label>
			<div class="col-md-4">
				<input id="sourceFrom" name="trends.sourceFrom" value="${trends.sourceFrom}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">置顶：</label>
			<div class="col-md-4">
				<s:if test="trends.isTop == 0">
					<label class="radio radio-inline"> <input type="radio" id="isTop" name="trends.isTop" value="0" checked="checked"> 否
					</label>
					<label class="radio radio-inline"> <input type="radio" id="isTop" name="trends.isTop" value="1"> 是
					</label>
				</s:if>
				<s:elseif test="trends.isTop == 1">
					<label class="radio radio-inline"> <input type="radio" id="isTop" name="trends.isTop" value="0"> 否
					</label>
					<label class="radio radio-inline"> <input type="radio" id="isTop" name="trends.isTop" value="1" checked="checked"> 是
					</label>
				</s:elseif>
				<s:else>
					<label class="radio radio-inline"> <input type="radio" id="isTop" name="trends.isTop" value="0" checked="checked"> 否
					</label>
					<label class="radio radio-inline"> <input type="radio" id="isTop" name="trends.isTop" value="1"> 是
					</label>
				</s:else>
			</div>
			<label class="col-md-2 control-label">状态：</label>
			<div class="col-md-4">
				<s:if test="trends.isPublish == 0">
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="trends.isPublish" value="0" checked="checked"> 发布
					</label>
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="trends.isPublish" value="1"> 终止
					</label>
				</s:if>
				<s:elseif test="trends.isPublish == 1">
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="trends.isPublish" value="0"> 发布
					</label>
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="trends.isPublish" value="1" checked="checked"> 终止
					</label>
				</s:elseif>
				<s:else>
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="trends.isPublish" value="0" checked="checked"> 发布
					</label>
					<label class="radio radio-inline"> <input type="radio" id="isPublish" name="trends.isPublish" value="1"> 终止
					</label>
				</s:else>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">发布范围:</label>
			<div class="col-md-10">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="employeeNames" name="trends.bizOrgNames" value="${trends.bizOrgNames }" class="form-control" type="text" readonly="readonly" data-prompt-position="topLeft" />
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
			<label class="col-md-2 control-label">新闻图片:</label>
			<div class="col-md-10">
				<div style="float: left; display: inline;">
					<img id="mainPic" onerror="$('#mainPic').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${nvix}/${trends.firstPictureUrl}" width="30px" height="30px" onmouseover="$('#mainPicBig').attr('style','position: absolute;padding:-50px;z-index:9999;');"
						onmouseout="$('#mainPicBig').attr('style','display:none;');" /> <img id="mainPicBig" onerror="$('#mainPicBig').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${nvix}/${trends.firstPictureUrl}" width="100" height="100" style="display: none; position: absolute;" />
					&nbsp;&nbsp;
				</div>
				<input type="file" id="fileToUpload" name="fileToUpload" onchange="fileChange();" style="width: 80%;">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">关键字:</label>
			<div class="col-md-4">
				<input id="keywords" name="trends.keywords" value="${trends.keywords}" placeholder="关键字，词组之间用逗号隔开" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">新闻地址:</label>
			<div class="col-md-4">
				<input id="sourceFromUrl" name="trends.sourceFromUrl" value="${trends.sourceFromUrl}" placeholder="录入新闻来源的网络地址" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> <span class="text-danger">*</span> 新闻内容:
			</label>
			<div class="col-md-10">
				<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="jarviswidget" id="">
						<header>
							<span class="widget-icon"> <i class="fa fa-pencil"></i>
							</span>
							<h2>新闻详情</h2>
						</header>
						<div>
							<div class="jarviswidget-editbox"></div>
							<div class="widget-body no-padding">
								<textarea id="trendsContent" name="trends.content"> ${trends.content }</textarea>
							</div>
						</div>
					</div>
				</article>
			</div>
	</fieldset>
</form>
<script type="text/javascript">
	$(document).ready(function() {
		//富文本编辑器
		CKEDITOR.replace('trendsContent', {
		height : '350px',
		startupFocus : true
		});
	});
	$("#projectImageForm").validationEngine();
	//上传图片
	function fileChange() {
		uploadFileOrImage("projectImageForm", "${nvix}/nvixnt/newsAdministrationAction!saveOrUpdateWxpQyPicture.action", "fileToUpload", "image", function(data) {
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