<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 基础设置 <span>> 系统常量管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>系统常量列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										常量值: <input id="defaultValue" type="text" value="" class="form-control">
									</div>
									<button onclick="systemVarTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#defaultValue').val('');systemVarTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="systemVarTab" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var systemVarColumns = [
		{"orderable" : false,"title":"编号","width":"10%","data" : function(data){return "&nbsp;";}}, 
		{"title":"变量","name":"varCode","width":"30%","data" : function(data){return data.varCode;}}, 
		{"title":"值","name":"defaultValue","width":"30%","data" : function(data){return data.defaultValue;}}, 
		{"title":"描述","name":"description","width":"20%","data" : function(data){return data.description;}}, 
		{"title":"操作","width" : "10%","data" : function(data){
			if(data.id != null){
				var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
			}
			return '';
		}
	}];

	var systemVarTable = initDataTable("systemVarTab", "${nvix}/nvixnt/nvixntSystemVarAction!getSystemVarJson.action", systemVarColumns, function(page, pageSize, orderField, orderBy) {
		var defaultValue = $("#defaultValue").val();
		defaultValue = Url.encode(defaultValue);
		return {"page" : page,"pageSize" : pageSize,"orderField" : orderField,"orderBy" : orderBy,"defaultValue" : defaultValue};
	});

	$("#systemVarForm").validationEngine();
	//新增编辑常量
	function saveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntSystemVarAction!goSaveOrUpdate.action?id=' + id, "systemVarForm",id == '' ? "新增系统常量" : "修改系统常量", 750, 350, systemVarTable);
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntSystemVarAction!deleteById.action?id=' + id, '是否删除该常量?', systemVarTable);
	};
</SCRIPT>