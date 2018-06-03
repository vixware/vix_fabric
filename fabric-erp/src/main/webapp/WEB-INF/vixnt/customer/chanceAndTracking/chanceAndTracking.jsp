<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售前管理 </span><span>> 销售机会 </span>
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
						<h2>销售机会列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="chanceAndTrackingSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										主题:<input type="text" value="" placeholder="机会主题" class="form-control" id="searchSubject"> 
										客户:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName">
									</div>
									<button onclick="chanceAndTrackingTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchSubject').val('');$('#searchCustomerName').val('');chanceAndTrackingTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="chanceAndTracking" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="8%">编号</th>
										<th>主题</th>
										<th>客户</th>
										<th>项目</th>
										<th>类型</th>
										<th>负责人</th>
										<th>预计签单日期</th>
										<th width="10%">预期金额</th>
										<th>可能性</th>
										<th>阶段</th>
										<th>状态</th>
										<th>阶段停留</th>
										<th width="8%">更新时间</th>
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
	var chanceAndTrackingColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "subject",
	"data" : function(data) {
		return data.subject;
	}
	}, {
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccount == null ? '' : data.customerAccount.name;
	}
	}, {
	"name" : "crmProject",
	"data" : function(data) {
		return data.crmProject == null ? '' : data.crmProject.subject;
	}
	}, {
	"name" : "saleType",
	"data" : function(data) {
		return data.saleType == null ? '' : data.saleType.name;
	}
	}, {
	"name" : "employee",
	"data" : function(data) {
		return data.employee == null ? '' : data.employee.name;
	}
	}, {
	"name" : "preOrderDate",
	"data" : function(data) {
		return data.preOrderDateStr;
	}
	}, {
	"name" : "expectedValue",
	"data" : function(data) {
		return data.expectedValue;
	}
	}, {
	"name" : "possibility",
	"data" : function(data) {
		if(data.possibility != null && data.possibility > 0){
			return data.possibility+"%";
		}else{
			return "0%";
		}
	}
	}, {
	"name" : "saleStage",
	"data" : function(data) {
		return data.saleStage == null ? '' : data.saleStage.name;
	}
	},{
	"name" : "saleChanceStatus",
	"data" : function(data) {
		return data.saleChanceStatus == null ? '' : data.saleChanceStatus.name;
	}
	}, {
	"name" : "stagnateDay",
	"data" : function(data) {
		if(data.stagnateDay != 0){
			return data.stagnateDay+"天";
		}else{
			return "";
		}
	}
	}, {
	"name" : "updateTime",
	"data" : function(data) {
		return data.updateTimeStr;
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

	var chanceAndTrackingTable = initDataTable("chanceAndTracking", "${nvix}/nvixnt/nvixChanceAndTrackingAction!goListContent.action", chanceAndTrackingColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchSubject").val();
		var cName = $("#searchCustomerName").val();
		name = Url.encode(name);
		cName = Url.encode(cName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"subject" : name,
		"customerName" : cName
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixChanceAndTrackingAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixChanceAndTrackingAction!deleteById.action?id=' + id, '是否删除该销售机会?', chanceAndTrackingTable);
	}

	pageSetUp();
</SCRIPT>