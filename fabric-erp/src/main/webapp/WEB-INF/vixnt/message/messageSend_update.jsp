<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 会员交互管理 <span onclick="">&gt; 短信管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/messageSendAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>短信模板</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="messageTemplateForm">
				<fieldset>
					<input type="hidden" id="id" name="messageTemplate.id" value="${messageTemplate.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 短信主题:</label>
						<div class="col-md-3">
							<input id="name" name="messageTemplate.name" value="${messageTemplate.name}" class="form-control validate[required]" type="text" placeholder="短信主题" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> </label>
						<div class="col-md-8">
							<div class="jarviswidget jarviswidget-color-gray">
								<header>
									<h2>
										<strong>短信内容</strong>
									</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<textarea class="form-control no-border" rows="4" id="messageContent" name="messageTemplate.messageContent"> ${messageTemplate.messageContent }</textarea>
										<div class="widget-footer">
											<button class="btn btn-sm btn-default" type="button" onclick="$('#messageContent').val('');">
												<i class="glyphicon glyphicon-repeat"></i> 清空
											</button>
										</div>
									</div>
								</div>
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
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/messageSendAction!goList.action');">
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
	//保存
	$("#messageTemplateForm").validationEngine();
	function saveOrUpdate() {
		if ($("#messageTemplateForm").validationEngine('validate')) {
			$("#messageTemplateForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/messageSendAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/messageSendAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>