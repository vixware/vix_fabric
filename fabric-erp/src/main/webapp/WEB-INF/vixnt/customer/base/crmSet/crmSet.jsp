<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i>客户关系管理 <span>>基础设置  </span>
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
			<h2>基础配置</h2>
		</header>
		<div>
			<div class="widget-body">
				<form id="crmSetForm" class="form-horizontal">
					<fieldset>
						<div id="jobInfo">
							<input type="hidden" id="id" name="crmSet.id" value="${crmSet.id}" />
							<input type="hidden" id="createTime" name="crmSet.createTime" value="${crmSet.createTimeStr}" />
							<input type="hidden" id="updateTime" name="crmSet.updateTime" value="${crmSet.updateTimeStr}" />
							<legend>基础配置信息:</legend>
							<div class="form-group">
								<label class="col-md-2 control-label"><span class="text-danger">*</span>客户提醒天数设置:</label>
								<div class="col-md-4">
									<input id="remindDay" name="crmSet.remindDay" value="${crmSet.remindDay}" class="form-control validate[required,custom[integer],min[1]]" type="text" />
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
	$("#crmSetForm").validationEngine();
	function saveOrUpdate() {
		if ($("#crmSetForm").validationEngine('validate')) {
			$("#crmSetForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixCrmSetAction!saveOrUpdate.action",
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