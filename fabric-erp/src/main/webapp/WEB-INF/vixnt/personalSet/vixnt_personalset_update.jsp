<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 系统管理 <span>> 个人设置 </span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>个人信息</h2>
		</header>
		<div>
			<div class="jarviswidget-editbox">
				<input class="form-control" type="text">
			</div>
			<div class="widget-body">
				<form id="attributeForm" class="form-horizontal">
					<input type="hidden" id="empId" name="emp.id" value="${emp.id}" /> <input type="hidden" id="userAccountId" name="userAccount.id" value="${userAccount.id}" />
					<fieldset>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 姓名:</label>
							<div class="col-md-4">
								<div class="input-group">
									<input id="name" name="emp.name" value="${emp.name}" class="form-control validate[required]" type="text" readonly="readonly" /> <span class="input-group-addon"><i class=" fa fa-user"></i></span>
								</div>
							</div>
							<label class="col-md-2 control-label"> 性别:</label>
							<div class="col-md-4">
								<div class="btn-group" data-toggle="buttons">
									<label class="btn btn-default <c:if test='${emp.gender eq 1}'>active</c:if>"> <input type="radio" name="emp.gender" id="icon-2" value="1" <c:if test='${emp.gender eq 1}'>checked</c:if>> 男
									</label> <label class="btn btn-default <c:if test='${emp.gender eq 0}'>active</c:if>"> <input type="radio" name="emp.gender" id="icon-1" value="0" <c:if test='${emp.gender eq 0}'>checked</c:if>> 女
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> <span class="text-danger">*</span> 微信:
							</label>
							<div class="col-md-4">
								<div class="input-group">
									<input id="weixinid" name="emp.weixinid" value="${emp.weixinid}" class="form-control validate[required]" type="text" /><span class="input-group-addon"><i class=" fa fa-weixin"></i></span>
								</div>
							</div>
							<label class="col-md-2 control-label"> 手机:</label>
							<div class="col-md-4">
								<div class="input-group">
									<input id="mobile" name="emp.mobile" value="${emp.mobile}" class="form-control" type="text" /> <span class="input-group-addon"><i class=" fa fa-phone"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 邮箱:</label>
							<div class="col-md-4">
								<div class="input-group">
									<input id="email" name="emp.email" value="${emp.email}" class="form-control" type="text" /><span class="input-group-addon"><i class=" fa fa-envelope-o"></i></span>
								</div>
							</div>
							<label class="col-md-2 control-label">登陆账号:</label>
							<div class="col-md-4">
								<div class="input-group">
									<input id="account" name="userAccount.account" value="${userAccount.account }" class="form-control validate[required]" type="text" readonly="readonly" /><span class="input-group-addon"><i class="fa fa-user"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">密码:</label>
							<div class="col-md-4">
								<div class="input-group">
									<input id="password" name="userAccount.password" value="${userAccount.initpassword }" class="form-control validate[required]" type="password" /> <span class="input-group-addon"><i class=" glyphicon glyphicon-lock"></i></span>
								</div>
							</div>
							<label class="col-md-2 control-label">确认密码:</label>
							<div class="col-md-4">
								<div class="input-group">
									<input id="passwordConfirm" name="userAccount.passwordConfirm" value="${userAccount.initpassword }" class="form-control validate[required]" type="password" /><span class="input-group-addon"><i class=" glyphicon glyphicon-lock"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 头像:</label>
							<div class="col-md-4">
								<div style="float: left; display: inline;">
									<img id="mainPic" onerror="$('#mainPic').attr('src','${nvix}/vixntcommon/base/img/avatars/sunny.png')" src="${emp.headImgUrl}" width="30px" height="30px" onmouseover="$('#mainPicBig').attr('style','position: absolute;padding:-50px;z-index:9999;');"
										onmouseout="$('#mainPicBig').attr('style','display:none;');" /> <img id="mainPicBig" onerror="$('#mainPicBig').attr('src','${nvix}/vixntcommon/base/img/avatars/sunny.png')" src="${emp.headImgUrl}" width="100" height="100" style="display: none; position: absolute;" /> &nbsp;&nbsp;
								</div>
								<input type="file" id="fileToUpload" name="fileToUpload" onchange="imageChange();" style="width: 80%;">
							</div>
						</div>
					</fieldset>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-12">
								<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">
									<i class="fa fa-save"></i> 保存
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#attributeForm").validationEngine();
	function saveOrUpdate() {
		if ($("#attributeForm").validationEngine('validate')) {
			if ($("#password").val() != $("#passwordConfirm").val()) {
				showMessage("密码和确认密码不一致!");
				return false;
			} else {
				$("#attributeForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/personalSetAction!saveOrUpdate.action",
				dataType : "text",
				success : function(data) {
					showMessage(data);
					loadContent('', '${nvix}/nvixnt/personalSetAction!goSaveOrUpdate.action');
				}
				});
			}
		} else {
			return false;
		}
	};
</script>