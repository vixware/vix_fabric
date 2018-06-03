<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 系统管理 <span>> 基础设置 </span><span>> 电商同步配置 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>同步配置</h2>
		</header>
		<div>
			<div class="widget-body">
				<form id="vixEcSetForm" class="form-horizontal">
					<fieldset>
						<div id="jobInfo">
							<input type="hidden" id="id" name="vixEcSet.id" value="${vixEcSet.id}" />
							<legend>商品配置信息:</legend>
							<div class="form-group">
								<label class="col-md-2 control-label"> 是否同步商品到电商: </label>
								<div class="col-md-4">
									<div data-toggle="buttons" class="btn-group">
										<label class="btn btn-default <s:if test="vixEcSet.isUploadToEc == 0">active</s:if>"> <input type="radio" value="0" id="isUploadToEc" name="vixEcSet.isUploadToEc" <s:if test="vixEcSet.isUploadToEc == 0">checked="checked"</s:if>>是
										</label> <label class="btn btn-default <s:if test="vixEcSet.isUploadToEc == 1">active</s:if>"> <input type="radio" value="1" id="isUploadToEc" name="vixEcSet.isUploadToEc" <s:if test="vixEcSet.isUploadToEc == 1">checked="checked"</s:if>>否
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label"> 商品接口地址:</label>
								<div class="col-md-10">
									<input id="ec_useraccount_upload" name="vixEcSet.ec_useraccount_upload" value="${vixEcSet.ec_useraccount_upload}" class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label"> 商品接口调用账号:</label>
								<div class="col-md-4">
									<input id="ec_useraccount" name="vixEcSet.ec_useraccount" value="${vixEcSet.ec_useraccount}" class="form-control" type="text" />
								</div>
								<label class="col-md-2 control-label"> 商品接口调用密码:</label>
								<div class="col-md-4">
									<input id="ec_password" name="vixEcSet.ec_password" value="${vixEcSet.ec_password}" class="form-control" type="password" />
								</div>
							</div>
						</div>
					</fieldset>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-12">
								<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
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
	$("#vixEcSetForm").validationEngine();
	function saveOrUpdate() {
		if ($("#vixEcSetForm").validationEngine('validate')) {
			$("#vixEcSetForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntEcSetAction!saveOrUpdate.action",
			dataType : "text",
			success : function(data) {
				showMessage(data,'success');
			}
			});
		} else {
			return false;
		}
	};
</script>