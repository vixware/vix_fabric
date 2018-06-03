<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i><a onclick="loadContent('mdm_itemGroup','${nvix}/nvixnt/mdm/nvixntItemGroupAction!goList.action');">商品管理</a>  <span>> 商品组设置 </span>
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
	<section id="widget-grid">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>商品组列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										名称: <input id="itemGroupName" type="text" value="" class="form-control">
									</div>
									<button onclick="itemGroupTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#itemGroupName').val('');itemGroupTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="itemGroupTable" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var itemGroupColumns = [
		{"orderable" : false,"title" : "编号","width" : "7%","data" : function(data){return "&nbsp;";}}, 
		{"title" : "编码","name":"code","width" : "10%","data" : function(data){return data.code;}}, 
		{"title" : "名称","name":"name","width" : "15%","data" : function(data){return data.name;}}, 
		{"title" : "公司名称","name":"brandCompany","width" : "20%","data" : function(data){return data.brandCompany;}}, 
		{"title" : "公司地址","name":"companyAddress","width" : "18%","data" : function(data){return data.companyAddress;}}, 
		{"title" : "顺序","name":"orderBy","width" : "10%","data" : function(data){return data.orderBy;}}, 
		{"title" : "备注","name":"memo","width" : "15%","data" : function(data){return data.memo;}}, 
		{"title" : "操作","width" : "10%","data" : function(data){
			if(data.id != null){
				var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
			}
			return '';
		}
	}];

	var itemGroupTable = initDataTable("itemGroupTable", "${nvix}/nvixnt/mdm/nvixntItemGroupAction!getItemGroupJson.action", itemGroupColumns, function(page, pageSize, orderField, orderBy) {
		var itemGroupName = $("#itemGroupName").val();
		itemGroupName = Url.encode(itemGroupName);
		return {"page" : page,"pageSize" : pageSize,"orderField" : orderField,"orderBy" : orderBy,"itemGroupName" : itemGroupName};
	});

	$("#itemGroupForm").validationEngine();
	//新增编辑
	function saveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixntItemGroupAction!goSaveOrUpdate.action?id=' + id, "itemGroupForm",id == '' ? '新增分组' : '修改分组', 750, 440, itemGroupTable);
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixntItemGroupAction!deleteById.action?id=' + id, '是否删除该品牌 ?', itemGroupTable);
	};
</SCRIPT>