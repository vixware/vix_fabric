<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="wabForm" method="post" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/addressesAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="wab.id" value="${wab.id}" /> <input type="hidden" id="fileId" name="fileId" value="" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>姓名:</label>
			<div class="col-md-4">
				<input id="name" name="wab.name" value="${wab.name}" class="form-control validate[required]" type="text" placeholder="姓名" />
			</div>
			<label class="col-md-2 control-label">公司:</label>
			<div class="col-md-4">
				<input id="company" name="wab.company" value="${wab.company}" class="form-control" type="text" placeholder="公司" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">地址:</label>
			<div class="col-md-4">
				<input id="langCode" name="wab.langCode" value="${wab.langCode}" class="form-control" type="text" placeholder="地址" />
			</div>
			<label class="col-md-2 control-label">职位:</label>
			<div class="col-md-4">
				<input id="position" name="wab.position" value="${wab.position}" class="form-control" type="text" placeholder="职位" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label"> 生日:</label>
			<div class="col-md-4">
				<input id="startTime" name="wab.startTime" value="<s:date name="%{wab.startTime}" format="yyyy-MM-dd"/>" class="form-control" type="text" placeholder="点击选择生日" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
			</div>
			<label class="col-md-2 control-label"> 性别:</label>
			<div class="col-md-4">
				<s:if test="wab.wabtype == 0">
					<label class="radio radio-inline"> <input type="radio" id="wabtype" name="wab.wabtype" value="0" checked="checked"> 男
					</label>
					<label class="radio radio-inline"> <input type="radio" id="wabtype" name="wab.wabtype" value="1"> 女
					</label>
				</s:if>
				<s:elseif test="wab.wabtype == 1">
					<label class="radio radio-inline"> <input type="radio" id="wabtype" name="wab.wabtype" value="0"> 男
					</label>
					<label class="radio radio-inline"> <input type="radio" id="wabtype" name="wab.wabtype" value="1" checked="checked"> 女
					</label>
				</s:elseif>
				<s:else>
					<label class="radio radio-inline"> <input type="radio" id="wabtype" name="wab.wabtype" value="0" checked="checked"> 男
					</label>
					<label class="radio radio-inline"> <input type="radio" id="wabtype" name="wab.wabtype" value="1"> 女
					</label>
				</s:else>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label"> 状态:</label>
			<div class="col-md-4">
				<s:if test="wab.statuss == 0">
					<label class="radio radio-inline"> <input type="radio" id="statuss" name="wab.statuss" value="0" checked="checked"> 私有
					</label>
					<label class="radio radio-inline"> <input type="radio" id="statuss" name="wab.statuss" value="1"> 公共
					</label>
				</s:if>
				<s:elseif test="wab.statuss == 1">
					<label class="radio radio-inline"> <input type="radio" id="statuss" name="wab.statuss" value="0"> 私有
					</label>
					<label class="radio radio-inline"> <input type="radio" id="statuss" name="wab.statuss" value="1" checked="checked"> 公共
					</label>
				</s:elseif>
				<s:else>
					<label class="radio radio-inline"> <input type="radio" id="statuss" name="wab.statuss" value="0" checked="checked"> 私有
					</label>
					<label class="radio radio-inline"> <input type="radio" id="statuss" name="wab.statuss" value="1"> 公共
					</label>
				</s:else>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label"> 头像:</label>
			<div class="col-md-10">
				<div style="float: left; display: inline;">
					<img id="mainPic" onerror="$('#mainPic').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${nvix}/${wab.pictureurl}" width="30px" height="30px" onmouseover="$('#mainPicBig').attr('style','position: absolute;padding:-50px;z-index:9999;');" onmouseout="$('#mainPicBig').attr('style','display:none;');" /> <img id="mainPicBig"
						onerror="$('#mainPicBig').attr('src','${nvix}/vixntcommon/base/images/project.png')" src="${nvix}/${wab.pictureurl}" width="100" height="100" style="display: none; position: absolute;" /> &nbsp;&nbsp;
				</div>
				<input type="file" id="fileToUpload" name="fileToUpload" onchange="fileChange();" style="width: 80%;">
			</div>
		</div>

	</fieldset>

	<fieldset>
		<div class="form-group">
			<label class="col-lg-2 control-label">手机号:</label>
			<div class="col-lg-6">
				<input type="text" class="form-control" id="mobileno" name="wab.mobileno" value="${wab.mobileno}" placeholder="手机号" />
			</div>
			<div class="col-lg-4">
				<button type="button" class="btn btn-primary btn-sm" data-toggle="#jobInfo">
					<i class="fa fa-plus"></i>
				</button>
			</div>
		</div>
	</fieldset>

	<div id="jobInfo" style="display: none;">
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">手机号:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="mobileno1" name="wab.mobileno1" value="${wab.mobileno1}" placeholder="手机号" />
				</div>
			</div>
		</fieldset>

		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">手机号:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="mobileno2" name="wab.mobileno2" value="${wab.mobileno2}" placeholder="手机号" />
				</div>
			</div>
		</fieldset>

		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">手机号:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="mobileno3" name="wab.mobileno3" value="${wab.mobileno3}" placeholder="手机号" />
				</div>
			</div>
		</fieldset>
	</div>

	<fieldset>
		<div class="form-group">
			<label class="col-lg-2 control-label">公司号:</label>
			<div class="col-lg-6">
				<input type="text" class="form-control" id="calls" name="wab.calls" value="${wab.calls}" placeholder="公司号" />
			</div>
			<div class="col-lg-4">
				<button type="button" class="btn btn-primary btn-sm" data-toggle="#phoneInfo">
					<i class="fa fa-plus"></i>
				</button>
			</div>
		</div>
	</fieldset>

	<div id="phoneInfo" style="display: none;">
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">公司号:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="calls1" name="wab.calls1" value="${wab.calls1}" placeholder="公司号" />
				</div>
			</div>
		</fieldset>
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">公司号:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="calls2" name="wab.calls2" value="${wab.calls2}" placeholder="公司号" />
				</div>
			</div>
		</fieldset>
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">公司号:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="calls3" name="wab.calls3" value="${wab.calls3}" placeholder="公司号" />
				</div>
			</div>
		</fieldset>
	</div>

	<fieldset>
		<div class="form-group">
			<label class="col-lg-2 control-label">邮箱:</label>
			<div class="col-lg-6">
				<input type="text" class="form-control" id="email" name="wab.email" value="${wab.email}" placeholder="邮箱" />
			</div>
			<div class="col-lg-4">
				<button type="button" class="btn btn-primary btn-sm" data-toggle="#phoneInfos">
					<i class="fa fa-plus"></i>
				</button>
			</div>
		</div>
	</fieldset>

	<div id="phoneInfos" style="display: none;">
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">邮箱:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="email1" name="wab.email1" value="${wab.email1}" placeholder="邮箱" />
				</div>
			</div>
		</fieldset>
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">邮箱:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="email2" name="wab.email2" value="${wab.email2}" placeholder="邮箱" />
				</div>
			</div>
		</fieldset>
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">邮箱:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="email3" name="wab.email3" value="${wab.email3}" placeholder="邮箱" />
				</div>
			</div>
		</fieldset>
	</div>

	<fieldset>
		<div class="form-group">
			<label class="col-lg-2 control-label">QQ:</label>
			<div class="col-lg-6">
				<input type="text" class="form-control" id="qq" name="wab.qq" value="${wab.qq}" placeholder="QQ" />
			</div>
			<div class="col-lg-4">
				<button type="button" class="btn btn-primary btn-sm" data-toggle="#phoneInfoss">
					<i class="fa fa-plus"></i>
				</button>
			</div>
		</div>
	</fieldset>

	<div id="phoneInfoss" style="display: none;">
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">QQ:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="qq1" name="wab.qq1" value="${wab.qq1}" placeholder="QQ" />
				</div>
			</div>
		</fieldset>
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">QQ:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="qq2" name="wab.qq2" value="${wab.qq2}" placeholder="QQ" />
				</div>
			</div>
		</fieldset>
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">QQ:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="qq3" name="wab.qq3" value="${wab.qq3}" placeholder="QQ" />
				</div>
			</div>
		</fieldset>
	</div>

	<fieldset>
		<div class="form-group">
			<label class="col-lg-2 control-label">自定义:</label>
			<div class="col-lg-6">
				<input type="text" class="form-control" id="custom" name="wab.custom" value="${wab.custom}" placeholder="自定义" />
			</div>
			<div class="col-lg-4">
				<button type="button" class="btn btn-primary btn-sm" data-toggle="#phoneInfosss">
					<i class="fa fa-plus"></i>
				</button>
			</div>
		</div>
	</fieldset>
	<div id="phoneInfosss" style="display: none;">
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">自定义:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="custom1" name="wab.custom1" value="${wab.custom1}" placeholder="自定义" />
				</div>
			</div>
		</fieldset>
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">自定义:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="custom2" name="wab.custom2" value="${wab.custom2}" />
				</div>
			</div>
		</fieldset>
		<fieldset>
			<div class="form-group">
				<label class="col-lg-2 control-label">自定义:</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="custom3" name="wab.custom3" value="${wab.custom3}" placeholder="自定义" />
				</div>
			</div>
		</fieldset>
	</div>

	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"> 个人简介:</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="4" id="memo" name="wab.memo" value="${wab.memo}" placeholder="个人简介">${wab.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
$("#wabForm").validationEngine();
//上传头像
function fileChange() {
	uploadFileOrImage("projectImageForm", "${nvix}/nvixnt/addressesAction!saveOrUpdateWxpQyPicture.action", "fileToUpload", "image", function(data) {
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
</script>
<script src="${nvix}/vixntcommon/base/js/plugin/bootstrapvalidator/bootstrapValidator.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#wabForm').bootstrapValidator({
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		
	}).find('button[data-toggle]').on('click', function() {
		var $target = $($(this).attr('data-toggle'));
		$target.toggle();
		if (!$target.is(':visible')) {
			$('#wabForm').data('bootstrapValidator').disableSubmitButtons(false);
		}
	});
})
</script>
