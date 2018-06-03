<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-cog"></i> 系统管理 <span onclick="">&gt; 门户配置</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/system/nvixntWebColumnAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>栏目信息</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="homeColumnForm">
				<fieldset>
					<input type="hidden" id="id" name="homeColumn.id" value="${homeColumn.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label"> 栏目类型:</label>
						<div class="col-md-3">
							<select id="typeCode" name="homeColumn.typeCode" class="form-control validate[required]">
								<option value="LBT_COLUMN" <c:if test='${homeColumn.typeCode == "LBT_COLUMN"}'>selected="selected"</c:if>>首页轮播图</option>
							</select>
						</div>
						<label class="col-md-2 control-label"> 栏目名称:</label>
						<div class="col-md-3">
							<input id="name" name="homeColumn.name" value="${homeColumn.name}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 顺序:</label>
						<div class="col-md-3">
							<input id="orderBy" name="homeColumn.orderBy" value="${homeColumn.orderBy}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"> 是否启用:</label>
						<div class="col-md-3">
							<select id="status" name="homeColumn.status" class="form-control validate[required]">
								<option value="Y" <c:if test='${homeColumn.status == "Y"}'>selected="selected"</c:if>>是</option>
								<option value="N" <c:if test='${homeColumn.status == "N"}'>selected="selected"</c:if>>否</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">栏目明细:</label>
						<div class="col-md-8">
							<div id="" class="jarviswidget">
								<header>
									<span class="widget-icon"><i class="fa fa-table"></i></span>
									<h2>栏目明细</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin: 5px;">
											<div class="form-group" style="margin: 0 0px;">
												<div class="col-md-3">
													<input type="text" value="" placeholder="名称" class="form-control" id="searchItemName">
												</div>
												<button onclick="homeColumnDetailTable.ajax.reload();" type="button" class="btn btn-primary listMenu-float-left">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchItemName').val('');homeColumnDetailTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
												<div class="listMenu-float-right">
													<button onclick="goSaveOrUpdateHomeColumnDetail('','新增');" type="button" class="btn btn-success">
														<i class="glyphicon glyphicon-plus"></i>
														<s:text name="新增" />
													</button>
												</div>
											</div>
										</div>
										<table id="homeColumnDetailTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
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
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/system/nvixntWebColumnAction!goList.action');">
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
	var homeColumnDetailColumns = [ {
	"width" : "10%",
	"orderable" : false,
	"title" : "编号",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "路径",
	"data" : function(data) {
		return data.imageFilePath;
	}
	}, {
	"title" : "栏目明细顺序",
	"data" : function(data) {
		return data.orderBy;
	}
	}, {
	"title" : "栏目明细状态",
	"data" : function(data) {
		if (data.status == 1) {
			return "<span class='label label-info'>展示</span>";
		} else if (data.status == 0) {
			return "<span class='label label-primary'>不展示</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"orderable" : false,
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateHomeColumnDetail('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteHomeColumnDetailById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var homeColumnDetailTable = initDataTable("homeColumnDetailTableId", "${nvix}/nvixnt/system/nvixntWebColumnAction!goHomeColumnDetailList.action?id=${homeColumn.id}", homeColumnDetailColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});

	function goSaveOrUpdateHomeColumnDetail(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixntWebColumnAction!goSaveOrUpdateHomeColumnDetail.action?homeColumnId=' + $('#id').val() + "&id=" + id, "homeColumnDetailForm", title, 750, 335, homeColumnDetailTable);
	};
	function deleteHomeColumnDetailById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/system/nvixntWebColumnAction!deleteHomeColumnDetailById.action?id=' + id, '是否删除该栏目明细?', homeColumnDetailTable);
	};
	$("#homeColumnForm").validationEngine();
	function saveOrUpdate() {
		if ($("#homeColumnForm").validationEngine('validate')) {
			$("#homeColumnForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/system/nvixntWebColumnAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/system/nvixntWebColumnAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>