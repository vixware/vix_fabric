<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="dimensionForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixProjectAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="project.id" value="${project.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>任务编码:</label>
			<div class="col-md-4">
				<input id="name" name="project.name" value="${project.name}" class="form-control required" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 任务名称:</label>
			<div class="col-md-4">
				<input id="name" name="project.name" value="${project.name}" class="form-control required" type="text" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 开始时间:</label>
			<div class="col-md-4">
				<input id="name" name="project.name" value="${project.name}" class="form-control required" type="text" onClick="WdatePicker()" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 结束时间:</label>
			<div class="col-md-4">
				<input id="name" name="project.name" value="${project.name}" class="form-control required" type="text" onClick="WdatePicker()" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 任务内容:</label>
			<div class="col-md-10">
				<input id="name" name="project.name" value="${project.name}" class="form-control required" type="text" />
			</div>

		</div>
		<div class="jarviswidget jarviswidget-color-darken">
			<header style="height: 1px; border-color: #ddd !important"></header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin: 5px;">
						<div class="form-group" style="margin: 0 5px;">
							<div class="col-md-3">
								<input type="text" value="" placeholder="名称" class="form-control" id="searchDimensionDetailName">
							</div>
							<button onclick="ddTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchName').val('');ddTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
							<div class="listMenu-float-right">
								<button onclick="saveOrUpdateDetail('');" type="button" class="btn btn-primary">
									<i class="glyphicon glyphicon-plus"></i> 上传附件
								</button>
							</div>
						</div>
					</div>
					<table id="dimensionDetail" class="table table-striped table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th width="10%">编号</th>
								<th width="25%">附件名称</th>
								<th width="40%">上传时间</th>
								<th width="10%">操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#currencyTypeForm").validate();
</script>