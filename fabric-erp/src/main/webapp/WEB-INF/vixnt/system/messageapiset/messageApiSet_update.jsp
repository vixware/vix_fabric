<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-envelope"></i> 短信管理 <span onclick="">&gt; 短信接口配置</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/messageApiSetAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>短信接口</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="ecMessageConfigForm">
				<fieldset>
					<input type="hidden" id="id" name="ecMessageConfig.id" value="${ecMessageConfig.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>接口类型:</label>
						<div class="col-md-3">
							<select id="msgType" name="ecMessageConfig.msgType" class="form-control validate[required]">
								<option value="AO_YI_HU_TONG" <s:if test='ecMessageConfig.msgType == "AO_YI_HU_TONG"'>selected="selected"</s:if>>奥易互通</option>
								<option value="MEI_LIAN_RUAN_TONG" <s:if test='ecMessageConfig.msgType == "MEI_LIAN_RUAN_TONG"'>selected="selected"</s:if>>美联软通</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test="ecMessageConfig.enable == 1">active</s:if>"> <input type="radio" value="1" id="enable" name="ecMessageConfig.enable" <s:if test="ecMessageConfig.enable == 1">checked="checked"</s:if> class="validate[required]">启用
								</label> <label class="btn btn-default <s:if test="ecMessageConfig.enable == 0">active</s:if>"> <input type="radio" value="0" id="enable" name="ecMessageConfig.enable" <s:if test="ecMessageConfig.enable == 0">checked="checked"</s:if>>禁用
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 接口账号:</label>
						<div class="col-md-3">
							<input id="msgAccount" name="ecMessageConfig.msgAccount" value="${ecMessageConfig.msgAccount}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 接口密码:</label>
						<div class="col-md-3">
							<input id="msgPassword" name="ecMessageConfig.msgPassword" value="${ecMessageConfig.msgPassword}" class="form-control validate[required]" type="password" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 接口URL:</label>
						<div class="col-md-8">
							<input id="msgUrl" name="ecMessageConfig.msgUrl" value="${ecMessageConfig.msgUrl}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 余额 查询URL:</label>
						<div class="col-md-8">
							<input id="msgBalanceUrl" name="ecMessageConfig.msgBalanceUrl" value="${ecMessageConfig.msgBalanceUrl}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">内容前缀:</label>
						<div class="col-md-3">
							<input type="text" id="msgContentPrefix" name="ecMessageConfig.msgContentPrefix" value="${ecMessageConfig.msgContentPrefix}" class="form-control"/>
						</div>
						<label class="col-md-2 control-label">内容后缀:</label>
						<div class="col-md-3">
							<input type="text" id="msgContentSuffix" name="ecMessageConfig.msgContentSuffix" value="${ecMessageConfig.msgContentSuffix}" class="form-control"/>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/messageApiSetAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#ecMessageConfigForm").validationEngine();
	function saveOrUpdate() {
		if ($("#ecMessageConfigForm").validationEngine('validate')) {
			$("#ecMessageConfigForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/messageApiSetAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/messageApiSetAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>