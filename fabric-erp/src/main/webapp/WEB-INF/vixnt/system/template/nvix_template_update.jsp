<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cog"></i> 系统管理 <span>> 工作台模板管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/system/nvixntTemplateAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>模板信息</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="homeTemplateForm">
				<fieldset>
					<input type="hidden" id="id" name="homeTemplate.id" value="${homeTemplate.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label"> 模板类型:</label>
						<div class="col-md-3">
							<select id="typeCode" name="homeTemplate.typeCode" class="form-control validate[required]">
								<s:if test="homeTemplateMap.size > 0">
									<s:iterator value="homeTemplateMap">
										<option value="${key}" <c:if test='${homeTemplate.typeCode == key}'>selected="selected"</c:if>>${value}</option>
									</s:iterator>
								</s:if>
							</select>
						</div>
						<label class="col-md-2 control-label"> 模板名称:</label>
						<div class="col-md-3">
							<input id="name" name="homeTemplate.name" value="${homeTemplate.name}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">明细:</label>
						<div class="col-md-8">
							<div id="" class="jarviswidget">
								<header>
									<span class="widget-icon"><i class="fa fa-table"></i></span>
									<h2>明细</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin: 5px;">
											<div class="form-group" style="margin: 0 0px;">
												<div class="col-md-3">
													<input type="text" value="" placeholder="名称" class="form-control" id="searchItemName">
												</div>
												<button onclick="homeTemplateDetailTable.ajax.reload();" type="button" class="btn btn-primary listMenu-float-left">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchItemName').val('');homeTemplateDetailTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
												<div class="listMenu-float-right">
													<button onclick="goSaveOrUpdateTemplateDetail('','新增');" type="button" class="btn btn-success">
														<i class="glyphicon glyphicon-plus"></i>
														<s:text name="新增" />
													</button>
												</div>
											</div>
										</div>
										<table id="homeTemplateDetailTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
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
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/system/nvixntTemplateAction!goList.action');">
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
	$("#homeTemplateForm").validationEngine();
	function saveOrUpdate() {
		if ($("#homeTemplateForm").validationEngine('validate')) {
			$("#homeTemplateForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/system/nvixntTemplateAction!saveOrUpdate.action",
			dataType : "text",
			success : function(result) {
				showMessage(result, 'success');
				loadContent('', '${nvix}/nvixnt/system/nvixntTemplateAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};

	var homeTemplateDetailColumns = [ {
	"width" : "10%",
	"orderable" : false,
	"title" : "编号",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "类型",
	"data" : function(data) {
		if (data.code == "NVIXNT_TASK") {
			return "<span class='label label-default'>任务</span>";
		} else if (data.code == "NVIXNT_MESSAGE") {
			return "<span class='label label-warning'>提醒</span>";
		} else if (data.code == "NVIXNT_CALENDAR") {
			return "<span class='label label-success'>日程</span>";
		} else if (data.code == "NVIXNT_PROJECT") {
			return "<span class='label label-primary'>项目</span>";
		} else if (data.code == "NVIXNT_DOCUMENT") {
			return "<span class='label label-danger'>文档</span>";
		}
		return "";
	}
	}, {
	"title" : "主题",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "顺序",
	"data" : function(data) {
		return data.orderBy;
	}
	}, {
	"title" : "状态",
	"data" : function(data) {
		if (data.status == 1) {
			return "<span class='label label-info'>展示</span>";
		} else if (data.status == 0) {
			return "<span class='label label-default'>不展示</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"orderable" : false,
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateTemplateDetail('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteHomeTemplateDetailById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var homeTemplateDetailTable = initDataTable("homeTemplateDetailTableId", "${nvix}/nvixnt/system/nvixntTemplateAction!goHomeTemplateDetailList.action?id=${homeTemplate.id}", homeTemplateDetailColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});

	function goSaveOrUpdateTemplateDetail(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixntTemplateAction!goSaveOrUpdateTemplateDetail.action?homeTemplateId=' + $('#id').val() + "&id=" + id, "homeTemplateDetailForm", title, 750, 335, homeTemplateDetailTable);
	};
	function deleteHomeTemplateDetailById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/system/nvixntTemplateAction!deleteHomeTemplateDetailById.action?id=' + id, '是否删除该模板明细?', homeTemplateDetailTable);
	};
</script>