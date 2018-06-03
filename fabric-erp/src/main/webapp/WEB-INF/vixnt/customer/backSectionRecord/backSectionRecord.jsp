<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售中管理 </span><span>> 回款记录 </span>
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget jarviswidget-color-darken" id="chanceAndTrackingHead" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>回款记录列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="chanceAndTrackingSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										客户姓名:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName"> 
										所有人姓名:<input type="text" value="" placeholder="所有人姓名" class="form-control" id="searchOwnerName">
									</div>
									<button onclick="backSectionRecordTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchOwnerName').val('');$('#searchCustomerName').val('');backSectionRecordTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="backSectionRecordTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="8%">编号</th>
										<th>金额</th>
										<th>期次</th>
										<th>回款日期</th>
										<th>是否开票</th>
										<th>客户</th>
										<th>订单</th>
										<th>项目</th>
										<th>所有人</th>
										<th width="8%">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var backSectionRecordColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "amount",
	"data" : function(data) {
		if(data.amount != null){
			return data.amount;
		}else{
			return 0;
		}
	}
	}, {
	"name" : "stageNumber",
	"data" : function(data) {
		return data.stageNumber;
	}
	}, {
	"name" : "backSectionDate",
	"data" : function(data) {
		return data.backSectionDateStr;
	}
	},{
	"name" : "isBilling",
	"data" : function(data) {
		if(data.isBilling == 1){
			return "<span class='label label-success'>已开</span>";
		}else if(data.isBilling == 0){
			return "<span class='label label-info'>未开</span>";
		}else if(data.isBilling == 2){
			return "<span class='label label-info'>不开发票</span>";
		}
	}
	}, {
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccount == null ? "" : data.customerAccount.name;
	}
	}, {
	"name" : "salesOrder",
	"data" : function(data) {
		return data.salesOrder == null ? "" : data.salesOrder.title;
	}
	},{
	"name" : "crmProject",
	"data" : function(data) {
		return data.crmProject == null ? "" : data.crmProject.subject;
	}
	}, {
	"name" : "owner",
	"data" : function(data) {
		return data.owner == null ? "" : data.owner.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (null != data.id) {
			var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var backSectionRecordTable = initDataTable("backSectionRecordTableId", "${nvix}/nvixnt/nvixBackSectionRecordAction!goListContent.action", backSectionRecordColumns, function(page, pageSize, orderField, orderBy) {
		var owner = $("#searchOwnerName").val();
		var name = $("#searchCustomerName").val();
		owner = Url.encode(owner);
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"owner" : owner,
		"customerName" : name
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixBackSectionRecordAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixBackSectionRecordAction!deleteById.action?id=' + id, '是否删除该回款记录?', backSectionRecordTable);
	}

	pageSetUp();
</SCRIPT>