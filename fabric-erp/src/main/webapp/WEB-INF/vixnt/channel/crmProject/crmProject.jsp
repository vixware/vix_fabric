<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 渠道管理 <span>> 渠道项目报备 </span>
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
						<h2>项目报备列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="chanceAndTrackingSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										主题:<input type="text" value="" placeholder="项目主题" class="form-control" id="title"> 
										客户:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName"> 
									</div>
									<button onclick="crmProjectTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#title').val('');$('#searchCustomerName').val('');crmProjectTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="crmProjectTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="8%">编号</th>
										<th>项目主题</th>
										<th>负责人</th>
										<th>立项日期</th>
										<th>客户</th>
										<th>项目阶段</th>
										<th>项目状态</th>
										<th>渠道</th>
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
	var crmProjectColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.subject;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.personInCharge.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.projectEstablishDateStr;
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		return data.customerAccount.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.projectStage.name
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.projectStatus.name
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.channelDistributor.name
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

	var crmProjectTable = initDataTable("crmProjectTableId", "${nvix}/nvixnt/channel/vixntChannelCrmProjectAction!goListContent.action", crmProjectColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#title").val();
		var cName = $("#searchCustomerName").val();
		title = Url.encode(title);
		cName = Url.encode(cName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title,
		"customerName" : cName
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/channel/vixntChannelCrmProjectAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/channel/vixntChannelCrmProjectAction!deleteById.action?id=' + id, '是否删除该项目报备?', crmProjectTable);
	}

	pageSetUp();
</SCRIPT>