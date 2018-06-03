<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 商品管理 <span>> 采购定价 </span>
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
						<h2>价格列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										主题: <input id="Pname" type="text" value="" class="form-control">
									</div>
									<button onclick="purchasePriceTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#Pname').val('');purchasePriceTable.ajax.reload();" type="button" class="btn btn-default">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
								<table id="purchasePriceTable" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</article>
			</div>
		</section>
	</div>
<script type="text/javascript">
	pageSetUp();
	var purchasePriceColumns = [
		{"orderable" : false,"title" : "编号","width" : "5%","data" : function(data){return "&nbsp;";}}, 
		{"orderable" : false,"title" : "主题","width" : "25%","data" : function(data){return data.name;}}, 
		{"orderable" : false,"title" : "是否启用","width" : "10%","data" : function(data){
			if(data.enable == 1){
				return "<span class='label label-success'>启用</span>";
			}else{
				return "<span class='label label-danger'>禁用</span>";;
			}
		}}, 
		{"orderable" : false,"title" : "销售组织","width" : "20%","data" : function(data){return data.saleOrgFullName;}}, 
		{"orderable" : false,"title" : "税率","width" : "10%","data" : function(data){return null == data.taxRate ? '' : data.taxRate.taxRate+"%";}}, 
		{"orderable" : false,"title" : "开始有效时间","width" : "10%","data" : function(data){return data.startEffectiveTimeStr;}}, 
		{"orderable" : false,"title" : "结束有效时间","width" : "10%","data" : function(data){return data.endEffectiveTimeStr;}}, 
		{"orderable" : false,"title" : "操作","width" : "10%","data" : function(data){
			if(null != data.id){
				var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				var enable = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"enable('" + data.id + "');\" title='启用'><span class='txt-color-blue pull-right'><i class='fa fa-unlock'></i></span></a>";
				var noable = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"noable('" + data.id + "');\" title='禁用'><span class='txt-color-red pull-right'><i class='fa fa-lock'></i></span></a>";
				if(data.enable == 1){
					return update + "&nbsp;&nbsp;" + noable + "&nbsp;&nbsp;" + del;
				}else{
					return update + "&nbsp;&nbsp;" + enable + "&nbsp;&nbsp;" + del;
				}
			}
			return '';
		}
	}];

	var purchasePriceTable = initDataTable("purchasePriceTable", "${nvix}/nvixnt/mdm/nvixntPurchasePriceAction!getPurchasePriceJson.action", purchasePriceColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#Pname").val();
		name = Url.encode(name);
		return {"page" : page,"pageSize" : pageSize,"orderField" : orderField,"orderBy" : orderBy,"name" : name};
	});

	$("#salePriceForm").validationEngine();
	//新增编辑常量
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/mdm/nvixntPurchasePriceAction!goSaveOrUpdate.action?id=' + id);
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixntPurchasePriceAction!deleteById.action?id=' + id, '是否删除该采购定价?', purchasePriceTable);
	};
	
	function enable(id) {
		updateEntityByConfirm('${nvix}/nvixnt/mdm/nvixntPurchasePriceAction!enable.action?id=' + id, "是否启用改采购定价?", purchasePriceTable)
	}
	function noable(id) {
		updateEntityByConfirm('${nvix}/nvixnt/mdm/nvixntPurchasePriceAction!noable.action?id=' + id, "是否禁用改采购定价?", purchasePriceTable)
	}
</SCRIPT>