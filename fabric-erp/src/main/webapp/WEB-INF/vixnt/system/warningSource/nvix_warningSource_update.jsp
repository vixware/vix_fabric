<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cog"></i> 系统管理 <span>> 预警源设置 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/system/nvixntWarningSourceAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>基本信息</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="moduleCategoryForm">
			<input id="moduleCategoryId" name="moduleCategory.id" value="${moduleCategory.id}" type="hidden" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
						<div class="col-md-4">
							<input id="categoryCode" name="moduleCategory.categoryCode" value="${moduleCategory.categoryCode}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"> 名称:</label>
						<div class="col-md-4">
							<input id="categoryName" name="moduleCategory.categoryName" value="${moduleCategory.categoryName}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 描述:</label>
						<div class="col-md-4">
							<input id="categoryDescription" name="moduleCategory.categoryDescription" value="${moduleCategory.categoryDescription}" class="form-control" type="text" />
						</div>
					</div>
					<div id="" class="jarviswidget jarviswidget-color-white">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>预警类型</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="名称" class="form-control" id="searchProductName">
										</div>
										<button onclick="warningTypeTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchProductName').val('');warningTypeTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="goSaveOrUpdateWarningType('','新增');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="add" />
											</button>
										</div>
									</div>
								</div>
								<table id="warningTypeTableId" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th>编号</th>
											<th data-options="field:'typeName',width:100,align:'center',editor:'text'">名称</th>
											<th data-options="field:'executionFrequency',width:100,align:'center',editor:'numberbox'">执行频率</th>
											<th field="executionFrequencyUtil" width="100" formatter="utilFormatter" editor="{type:'combobox',options:{valueField:'executionFrequencyUtil',textField:'name',data:utilData}}">单位</th>
											<th data-options="field:'executeTime',width:100,align:'center',editor:'datebox'">执行时间</th>
											<th field="classCode" width="100" formatter="typeFormatter" editor="{type:'combobox',options:{valueField:'classCode',textField:'name',data:typeData}}">预警类型</th>
											<th data-options="field:'typeDescription',width:100,align:'center',editor:'text'">描述</th>
											<th>操作</th>
										</tr>
									</thead>
								</table>
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
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/system/nvixntWarningSourceAction!goList.action');">
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
var warningTypeColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"data" : function(data) {
		return data.typeName;
	}
	}, {
	"data" : function(data) {
		return data.executionFrequency;
	}
	}, {
	"data" : function(data) {
		if(data.executionFrequencyUtil == "H"){
			return "时";
		}else if(data.executionFrequencyUtil == "M"){
			return "分";
		}else if(data.executionFrequencyUtil == "S"){
			return "秒";
		}
		return "";
	}
	}, {
	"data" : function(data) {
		return data.executeTimeStr;
	}
	}, {
	"data" : function(data) {
		if(data.classCode == 'inventory'){
			return "库存预警";
		}else if(data.classCode == 'qualityGuaranteePeriodTaskqualityGuaranteePeriodTask'){
			return "保质期预警";
		}else if(data.classCode == 'crm'){
			return "客户生日提醒";
		}
		return "";
	}
	}, {
	"data" : function(data) {
		return data.typeDescription;
	}
	}, {
	"title" : "操作",
	"width" : "7%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateWarningType('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteWarningById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var warningTypeTable = initDataTable("warningTypeTableId", "${nvix}/nvixnt/system/nvixntWarningSourceAction!goSingleWarningTypeList.action", warningTypeColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#moduleCategoryId").val();
		var searchProductName = $("#searchProductName").val();
		searchProductName = Url.encode(searchProductName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchProductName" : searchProductName,
		"id" : id
		};
	});
$("#moduleCategoryForm").validationEngine();
function saveOrUpdate() {
	if ($("#moduleCategoryForm").validationEngine('validate')) {
		$("#moduleCategoryForm").ajaxSubmit({
		type : "post",
		url : "${nvix}/nvixnt/system/nvixntWarningSourceAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			loadContent('system_warningSource', '${nvix}/nvixnt/system/nvixntWarningSourceAction!goList.action');
		}
		});
	} else {
		return false;
	}
};
function goSaveOrUpdateWarningType(id,title){
	var moduleCategoryId = $("#moduleCategoryId").val();
	if(moduleCategoryId){
		openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixntWarningSourceAction!goSaveOrUpdateWarningType.action?id='+id,'warningTypeForm',title,750,355,warningTypeTable);
	}else{
		if ($("#moduleCategoryForm").validationEngine('validate')) {
			$("#moduleCategoryForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/system/nvixntWarningSourceAction!saveOrUpdate.action",
			dataType : "text",
			success : function(result) {
				var r = result.split(":");
				if(r[0] == "0"){
					showMessage(r[1]);
					return;
				}else{
					showMessage(r[1]);
					$("#moduleCategoryId").val(r[2]);
					openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixntWarningSourceAction!goSaveOrUpdateWarningType.action?id='+id,'warningTypeForm',title,750,355,warningTypeTable);
				}
			}
			});
		} else {
			return false;
		}
	}
}
function deleteWarningById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/system/nvixntWarningSourceAction!deleteWarningById.action?id=' + id, '是否删除预警类型?', warningTypeTable);
}
</script>