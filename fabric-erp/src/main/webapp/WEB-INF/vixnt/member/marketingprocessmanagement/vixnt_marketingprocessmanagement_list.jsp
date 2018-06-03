<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-globe"></i> 会员营销 <span>&gt; 智能营销</span><span>&gt; 营销流程管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<!-- row -->
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>营销流程列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form class="navbar-form navbar-left" role="search">
					主题：
					<div class="form-group">
						<input class="form-control" type="text" value="" id="messageName">
					</div>
					<button onclick="marketingActivitiesTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#messageName').val('');marketingActivitiesTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="marketingActivitiesTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var marketingActivitiesColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "活动主题",
	"name" : "name",
	"data" : function(data) {
		return "<a onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'>"+data.name+"</a>";
	}
	}, {
	"title" : "开始时间",
	"data" : function(data) {
		return data.startTimeTimeStr;
	}
	}, {
	"title" : "结束时间",
	"data" : function(data) {
		return data.endTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"name" : "status",
	"data" : function(data) {
		if(data.status == "1"){
			return "<span class='label label-success'>已启动</span>";
		}else if(data.status == "2"){
			return "<span class='label label-info'>已停止</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var start = "<a class='btn btn-xs btn-default' onclick=\"goStart('" + data.id + "');\" title='启动'><span class='txt-color-blue pull-right'><i class='fa fa-play'></i></span></a>";
		var stop = "<a class='btn btn-xs btn-default' onclick=\"goStop('" + data.id + "');\" title='停止'><span class='txt-color-blue pull-right'><i class='fa fa-stop'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + start + "&nbsp;&nbsp;" + stop + "&nbsp;&nbsp;" + del;
	}
	} ];

	var marketingActivitiesTable = initDataTable("marketingActivitiesTableId", "${nvix}/nvixnt/vixntMarketingProcessManagementAction!goListContent.action", marketingActivitiesColumns, function(page, pageSize, orderField, orderBy) {
		var messageName = $("#messageName").val();
		messageName = Url.encode(messageName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"messageName" : messageName
		};
	});

	//新增
	function saveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntMarketingProcessManagementAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
	function goStart(id) {
		updateEntityByConfirm('${nvix}/nvixnt/vixntMarketingProcessManagementAction!goStartFlowTask.action?id=' + id, '确定启动流程吗?', marketingActivitiesTable);
	}
	
	function goStop(id) {
		updateEntityByConfirm('${nvix}/nvixnt/vixntMarketingProcessManagementAction!goStopFlowTask.action?id=' + id, '确定停止流程吗?', marketingActivitiesTable);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntMarketingProcessManagementAction!deleteById.action?id=' + id, '是否删除营销流程?', marketingActivitiesTable);
	};
	
</script>