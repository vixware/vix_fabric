<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 基础设置 <span>> 税率 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
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
						<h2>税率列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										税率名称: <input id="taxRateName" type="text" value="" class="form-control">
									</div>
									<button onclick="exchangeRateTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#taxRateName').val('');exchangeRateTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="exchangeRateTable" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var exchangeRateColumns = [
		{"orderable" : false,"title" : "编号","width" : "10%","data" : function(data){return "&nbsp;";}}, 
		{"title" : "编码","width" : "30%","data" : function(data){return data.code;}}, 
		{"title" : "名称","width" : "30%","data" : function(data){return data.name;}}, 
		{"title" : "税率","width" : "20%","data" : function(data){return data.taxRate;}}, 
		{"title" : "操作","width" : "10%","data" : function(data){
			if(null == data.id){
				return '';
			}
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
	}];

	var exchangeRateTable = initDataTable("exchangeRateTable", "${nvix}/nvixnt/system/nvixntTaxRateAction!goSingleList.action", exchangeRateColumns, function(page, pageSize, orderField, orderBy) {
		var defaultValue = $("#taxRateName").val();
		defaultValue = Url.encode(defaultValue);
		return {"page" : page,"pageSize" : pageSize,"orderField" : orderField,"orderBy" : orderBy,"name" : defaultValue};
	});

	$("#taxRateForm").validationEngine();
	//新增编辑常量
	function saveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixntTaxRateAction!goSaveOrUpdate.action?id=' + id, "taxRateForm", "新增税率", 530, 320, exchangeRateTable);
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/system/nvixntTaxRateAction!deleteById.action?id=' + id, '是否删除该税率?', exchangeRateTable);
	};
</SCRIPT>