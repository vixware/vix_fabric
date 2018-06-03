<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售前管理 </span><span>> 竞争对手 </span>
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
						<h2>竞争对手列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="chanceAndTrackingSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										公司名称:<input type="text" value="" placeholder="公司名称" class="form-control" id="searchCompanyName"> 
										客户:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchName">
									</div>
									<button onclick="competitorTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchName').val('');$('#searchCompanyName').val('');competitorTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="competitorTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="8%">编号</th>
										<th>公司名称</th>
										<th>价格</th>
										<th>竞争能力</th>
										<th>对应机会</th>
										<th>客户</th>
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
	var competitorColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "companyName",
	"data" : function(data) {
		return data.companyName;
	}
	}, {
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"name" : "competitive",
	"data" : function(data) {
		if(data.competitive != null){
			return "<span class='label label-info'>"+data.competitive.name+"</span>";
		}
	}
	}, {
	"name" : "saleChance",
	"data" : function(data) {
		return data.saleChance == null ? '' : data.saleChance.subject;
	}
	},{
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccount == null ? '' : data.customerAccount.name;
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

	var competitorTable = initDataTable("competitorTableId", "${nvix}/nvixnt/nvixCompetitorAction!goListContent.action", competitorColumns, function(page, pageSize, orderField, orderBy) {
		var companyName = $("#searchCompanyName").val();
		var name = $("#searchName").val();
		companyName = Url.encode(companyName);
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"subject" : name,
		"companyName" : companyName,
		"customerName" : name
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixCompetitorAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCompetitorAction!deleteById.action?id=' + id, '是否删除该竞争对手?', competitorTable);
	}

	pageSetUp();
</SCRIPT>