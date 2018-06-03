<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-desktop"></i> 区块链管理<span>&gt; 票据管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<!-- row -->
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>票据列表</h2>
			<ul class="nav nav-tabs pull-right in" id="myTab">
				<li class="<c:if test='${source != "other"}'>active</c:if>"><a href="#s1" data-toggle="tab">我的票据</a></li>
				<li class="<c:if test='${source == "other"}'>active</c:if>"><a href="#s2" data-toggle="tab">待签收票据</a></li>
			</ul>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="" class="tab-content">
					<div class="tab-pane fade in <c:if test='${source != "other"}'>active</c:if>" id="s1">
						<form class="navbar-form navbar-left" role="search">
							票据号：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="标题" id="mytitle">
							</div>
							<button onclick="myworkLogTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#mytitle').val('');myworkLogTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="myworkLog" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<div class="tab-pane fade in <c:if test='${source == "other"}'>active</c:if>" id="s2">
						<form class="navbar-form navbar-left" role="search">
							票据号：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="标题" id="title">
							</div>
							<button onclick="otherworkLogTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#title').val('');otherworkLogTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="otherworkLog" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var myworkLogColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "票据号",
	"width" : "55%",
	"name" : "billInfoId",
	"data" : function(data) {
		return data.billInfoId;
	}
	}, {
	"title" : "票据状态",
	"width" : "15%",
	"data" : function(data) {
		return data.state;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var show = "<a class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var endorse = "<a class='btn btn-xs btn-default' onclick=\"goEndorseFabricBill('" + data.id + "');\" title='发起背书'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		return show + "&nbsp;&nbsp;" + endorse;
	}
	} ];

	var myworkLogTable = initDataTable("myworkLog", "${nvix}/nvixnt/vixntFabricBillAction!goSingleList.action", myworkLogColumns, function(page, pageSize, orderField, orderBy) {
		var mytitle = $("#mytitle").val();
		mytitle = Url.encode(mytitle);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"title" : mytitle
		};
	});

	var otherworkLogColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "票据号",
	"width" : "45%",
	"name" : "billInfoId",
	"data" : function(data) {
		return data.billInfoId;
	}
	}, {
	"title" : "票据状态",
	"width" : "15%",
	"name" : "state",
	"data" : function(data) {
		return data.state;
	}
	}, {
	"title" : "操作",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "<a class='btn btn-xs btn-default' onclick=\"goShowFabricBill('" + data.id + "','other');\" title='详情'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
	}
	} ];

	var otherworkLogTable = initDataTable("otherworkLog", "${nvix}/nvixnt/vixntFabricBillAction!goWaitEndSingleList.action", otherworkLogColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#title").val();
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"title" : title
		};
	});

	function goShowFabricBill(id, source) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntFabricBillAction!goShowFabricBill.action?id=' + id + '&source=' + source,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goEndorseFabricBill(id, source) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntFabricBillAction!goEndorseFabricBill.action?id=' + id + '&source=' + source,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};

	//新增
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntFabricBillAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>