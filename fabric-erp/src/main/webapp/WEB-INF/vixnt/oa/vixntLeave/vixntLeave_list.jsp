<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-bar-chart-o"></i> 办公 <span onclick="loadContent('workPlans','${nvix}/nvixnt/vixntLeaveAction!goList.action');">&gt; 请假出差管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('','新增');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>请假单列表</h2>
			<ul class="nav nav-tabs pull-right in" id="myTab">
				<li class="<c:if test='${source != "other"}'>active</c:if>"><a href="#s1" data-toggle="tab">我的请假单</a></li>
				<li class="<c:if test='${source == "other"}'>active</c:if>"><a href="#s2" data-toggle="tab">下属的请假单</a></li>
			</ul>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="" class="tab-content">
					<div class="tab-pane fade in <c:if test='${source != "other"}'>active</c:if>" id="s1">
						<form class="navbar-form navbar-left" role="search">
							主题：
							<div class="form-group">
								<input class="form-control" type="text" value="" id="mytitle">
							</div>
							<button onclick="myTripRecordTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#mytitle').val('');myTripRecordTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="myTripRecordTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<div class="tab-pane fade in <c:if test='${source == "other"}'>active</c:if>" id="s2">
						<form class="navbar-form navbar-left" role="search">
							主题：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="主题" id="title">
							</div>
							<button onclick="tripRecordTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#title').val('');tripRecordTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="tripRecordTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	//我的请假单
	var myTripRecordColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "原因",
	"width" : "30%",
	"name" : "vacateReason",
	"data" : function(data) {
		return data.vacateReason;
	}
	}, {
	"title" : "开始时间",
	"width" : "15%",
	"name" : "vacateStartdateTimeStr",
	"data" : function(data) {
		return data.vacateStartdateTimeStr;
	}
	}, {
	"title" : "截止时间",
	"width" : "15%",
	"name" : "vacateendDateTimeStr",
	"data" : function(data) {
		return data.vacateendDateTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var myTripRecordTable = initDataTable("myTripRecordTableId", "${nvix}/nvixnt/vixntLeaveAction!goMyTripRecord.action", myTripRecordColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#mytitle").val();
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title
		};
	});
	//下属的请假单
	var tripRecordColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "原因",
	"width" : "40%",
	"name" : "vacateReason",
	"data" : function(data) {
		return data.vacateReason;
	}
	}, {
	"title" : "开始时间",
	"width" : "10%",
	"name" : "vacateStartdateTimeStr",
	"data" : function(data) {
		return data.vacateStartdateTimeStr;
	}
	}, {
	"title" : "截止时间",
	"width" : "15%",
	"name" : "vacateendDateTimeStr",
	"data" : function(data) {
		return data.vacateendDateTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		return "<a class='btn btn-xs btn-default' onclick=\"goShowTripRecord('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
	}
	} ];

	var tripRecordTable = initDataTable("tripRecordTableId", "${nvix}/nvixnt/vixntLeaveAction!goTripRecord.action", tripRecordColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#title").val();
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title
		};
	});

	//新增
	function saveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntLeaveAction!goSaveOrUpdate.action?id=' + id, "tripRecordForm", title, 850, 550, myTripRecordTable);
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntLeaveAction!deleteById.action?id=' + id, '是否删除请假单?', myTripRecordTable);
	};
	//根据ID查看
	function goShowTripRecord(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntLeaveAction!goShowTripRecord.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>