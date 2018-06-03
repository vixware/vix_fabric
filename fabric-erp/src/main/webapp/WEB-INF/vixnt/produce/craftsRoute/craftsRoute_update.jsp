<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 生产管理 <span>> 工艺路线 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/produce/nvixCraftsRouteAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>工艺路线</h2>
		</header>
		<div class="widget-body">
			<form id="craftsRouteForm" class="form-horizontal">
				<input type="hidden" id="id" name="craftsRoute.id" value="${craftsRoute.id}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>工艺路线编码:</label>
						<div class="col-md-3">
							<input id="craftsCode" name="craftsRoute.craftsCode" value="${craftsRoute.craftsCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>工艺路线名称:</label>
						<div class="col-md-3">
							<input id="craftsName" name="craftsRoute.craftsName" value="${craftsRoute.craftsName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>物料编码:</label>
						<div class="col-md-3">
							<input id="materialCode" name="craftsRoute.materialCode" value="${craftsRoute.materialCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>物料名称:</label>
						<div class="col-md-3">
							<input id="materialName" name="craftsRoute.materialName" value="${craftsRoute.materialName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>版本号:</label>
						<div class="col-md-3">
							<input id="versionNumber" name="craftsRoute.versionNumber" value="${craftsRoute.versionNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>版本说明:</label>
						<div class="col-md-3">
							<input id="versionDescription" name="craftsRoute.versionDescription" value="${craftsRoute.versionDescription}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">工艺路线类型:</label>
						<div class="col-md-3">
							<input id="types" name="craftsRoute.types" value="${craftsRoute.types}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">规格:</label>
						<div class="col-md-3">
							<input id="specifications" name="craftsRoute.specifications" value="${craftsRoute.specifications}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">工艺路线规则:</label>
						<div class="col-md-3">
							<input id="processRules" name="craftsRoute.processRules" value="${craftsRoute.processRules}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">固定提前期(天):</label>
						<div class="col-md-3">
							<input id="fixedLeadTime" name="craftsRoute.fixedLeadTime" value="${craftsRoute.fixedLeadTime}" data-prompt-position="topLeft" class="form-control validate[custom[integer],min[1]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">计量单位:</label>
						<div class="col-md-3">
							<input id="unitsMeasurement" name="craftsRoute.unitsMeasurement" value="${craftsRoute.unitsMeasurement}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">变更单号:</label>
						<div class="col-md-3">
							<input id="changeNumber" name="craftsRoute.changeNumber" value="${craftsRoute.changeNumber}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">状态:</label>
						<div class="col-md-3">
							<input id="state" name="craftsRoute.state" value="${craftsRoute.state}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">创建日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="craDate" name="craftsRoute.craDate" value="${craftsRoute.craDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'craDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="craftsRoute.memo" class="form-control" rows="4">${craftsRoute.memo}</textarea>
						</div>
					</div>
					<div id="" class="jarviswidget jarviswidget-color-white">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>工序列表</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="工序名称" class="form-control" id="searchProcessName">
										</div>
										<div class="col-md-3">
											<input type="text" value="" placeholder="工作中心名称" class="form-control" id="searchOrgName">
										</div>
										<button onclick="craftsRouteDetailTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchProcessName').val('');$('#searchOrgName').val('');craftsRouteDetailTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="goSaveOrUpdateCraftsRouteDetail('');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="新增工序" />
											</button>
										</div>
									</div>
								</div>
								<table id="craftsRouteDetailTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-primary" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/produce/nvixCraftsRouteAction!goList.action');">
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
	var craftsRouteDetailColumns = [ {
		"title" : "编号",
		"width" : "5%",
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"title" : "工序编号",
		"name" : "processCode",
		"data" : function(data) {
			return data.processCode;
		}
		}, {
		"title" : "工序名称",
		"name" : "standardProcedure",
		"data" : function(data) {
			return data.standardProcedure;
		}
		}, {
		"title" : "工序说明",
		"name" : "processExplain",
		"data" : function(data) {
			return data.processExplain;
		}
		}, {
		"title" : "工作中心",
		"name" : "workCenter",
		"data" : function(data) {
			return data.workCenterName;
		}
		},{
		"title" : "厂商名称",
		"name" : "tradeName",
		"data" : function(data) {
			return data.tradeName;
		}
		}, {
		"title" : "操作",
		"width" : "7%",
		"orderable" : false,
		"data" : function(data) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateCraftsRouteDetail('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteCraftsRouteDetailById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
	
		}
	} ];
	var craftsRouteDetailTable = initDataTable("craftsRouteDetailTableId", "${nvix}/nvixnt/produce/nvixCraftsRouteAction!goCraftsRouteDetailList.action", craftsRouteDetailColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#id").val();
		var searchProcessName = $("#searchProcessName").val();
		var searchOrgName = $("#searchOrgName").val();
		searchProcessName = Url.encode(searchProcessName);
		searchOrgName = Url.encode(searchOrgName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : searchProcessName,
		"orgName" : searchOrgName,
		"id" : id
		};
	});
	
	$("#craftsRouteForm").validationEngine();
	function saveOrUpdate(id) {
		//表单验证
		if ($("#craftsRouteForm").validationEngine('validate')) {
			$("#craftsRouteForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/produce/nvixCraftsRouteAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				var r = result.split(":");
				if(r[0] != '0'){
					loadContent('', '${nvix}/nvixnt/produce/nvixCraftsRouteAction!goList.action');
				}else{
					showMessage(r[1]);
					return ;
				}
			}
			});
		}
	}
	
	function goSaveOrUpdateCraftsRouteDetail(id, title) {
		if (id) {
			openSaveOrUpdateWindow('${nvix}/nvixnt/produce/nvixCraftsRouteAction!goSaveOrUpdateCraftsRouteDetail.action?id=' + id, 'craftsRouteDetailForm', id == '' ? '新增工序' : '修改工序', 850, 450, craftsRouteDetailTable);
		} else {
			if ($("#craftsRouteForm").validationEngine('validate')) {
				$("#craftsRouteForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/produce/nvixCraftsRouteAction!saveOrUpdate.action",
					dataType : "text",
					success : function(result) {
						var r = result.split(":");
						if(r[0] != "0"){
							$("#id").val(r[1]);
							openSaveOrUpdateWindow('${nvix}/nvixnt/produce/nvixCraftsRouteAction!goSaveOrUpdateCraftsRouteDetail.action?id=' + id + "&craftsRouteId=" + r[1], 'craftsRouteDetailForm', id == '' ? '新增工序' : '修改工序', 850, 450, craftsRouteDetailTable);
						}else{
							showMessage(r[1]);
							return;
						}
					}
				});
			} else {
				return false;
			}
		}
	};
	
	function deleteCraftsRouteDetailById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/produce/nvixCraftsRouteAction!deleteCraftsRouteDetailById.action?id=' + id, '是否删除工序?', craftsRouteDetailTable);
	};
	
</script>