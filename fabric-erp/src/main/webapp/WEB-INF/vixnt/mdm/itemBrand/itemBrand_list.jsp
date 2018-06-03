<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 商品管理<span>> 设置 </span> <span>> 品牌设置 </span>
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
						<h2>品牌列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										名称: <input id="itemBrandName" type="text" value="" class="form-control">
									</div>
									<button onclick="itemBrandTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#itemBrandName').val('');itemBrandTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="itemBrandTable" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">

	var itemBrandColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "编码",
	"name" : "code",
	"width" : "10%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "名称",
	"name" : "name",
	"width" : "15%",
	"data" : function(data) {
		if(data.name){
			return "<a href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "');\" title='更新'>"+data.name+"</a>";
		}
		return "";
	}
	}, {
	"title" : "公司名称",
	"name" : "brandCompany",
	"width" : "20%",
	"data" : function(data) {
		return data.brandCompany;
	}
	}, {
	"title" : "公司地址",
	"name" : "companyAddress",
	"width" : "15%",
	"data" : function(data) {
		return data.companyAddress;
	}
	}, {
	"title" : "顺序",
	"name" : "orderBy",
	"width" : "10%",
	"data" : function(data) {
		return data.orderBy;
	}
	}, {
	"title" : "备注",
	"name" : "memo",
	"width" : "15%",
	"data" : function(data) {
		return data.memo;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var itemBrandTable = initDataTable("itemBrandTable", "${nvix}/nvixnt/mdm/nvixntItemBrandAction!getItemBrandJson.action", itemBrandColumns, function(page, pageSize, orderField, orderBy) {
		var itemBrandName = $("#itemBrandName").val();
		itemBrandName = Url.encode(itemBrandName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"itemBrandName" : itemBrandName
		};
	});

	$("#itemBrandForm").validationEngine();
	//新增编辑
	function saveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixntItemBrandAction!goSaveOrUpdate.action?id=' + id, "itemBrandForm", id == '' ? '新增品牌' : '修改品牌', 750, 440, itemBrandTable);
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixntItemBrandAction!deleteById.action?id=' + id, '是否删除该品牌 ?', itemBrandTable);
	};
</SCRIPT>