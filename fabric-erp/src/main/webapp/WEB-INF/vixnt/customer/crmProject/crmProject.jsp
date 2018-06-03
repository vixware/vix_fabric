<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 项目 </span>
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
						<h2>项目列表</h2>
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
										<th>客户</th>
										<th>项目阶段</th>
										<th>项目状态</th>
										<th>售前状态</th>
										<th>立项日期</th>
										<th>预计签单日期</th>
										<th>项目进度</th>
										<th width="10%">操作</th>
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
	"name" : "subject",
	"data" : function(data) {
		return data.subject;
	}
	}, {
	"name" : "personInCharge",
	"data" : function(data) {
		return data.personInCharge == null ? '' : data.personInCharge.name;
	}
	}, {
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccount == null ? '' : data.customerAccount.name;
	}
	}, {
	"name" : "projectStage",
	"data" : function(data) {
		return data.projectStage == null ? '' : data.projectStage.name;
	}
	}, {
	"name" : "projectStatus",
	"data" : function(data) {
		return data.projectStatus == null ? '' : data.projectStatus.name;
	}
	},{
	"name" : "projectSalePreviousStage",
	"data" : function(data) {
		return data.projectSalePreviousStage == null ? '' : data.projectSalePreviousStage.name;
	}
	}, {
	"name" : "projectEstablishDate",
	"data" : function(data) {
		return data.projectEstablishDateStr;
	}
	}, {
	"name" : "forecastSignDate",
	"data" : function(data) {
		return data.forecastSignDateStr;
	}
	},{
	"name" : "projectSchedule",
	"data" : function(data) {
		if(data.projectSchedule != null && data.projectSchedule > 0){
			return "<div data-progressbar-value='"+data.projectSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
		}else{
			return "";
		}
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (null != data.id) {
			var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var view = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"view('" + data.id + "');\" title='项目视图'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			var discuss = "<a class='btn btn-xs btn-default' onclick=\"goDiscuss('" + data.id + "');\" title='项目沟通'><span class='txt-color-green pull-right'><i class='glyphicon glyphicon-comment'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			var feedback = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goFeedback('" + data.id + "');\" title='项目进度'><span class='txt-color-green pull-right'><i class='fa fa-comment-o'></i></span></a>";
			return update + "&nbsp;&nbsp;" + view + "&nbsp;&nbsp;" + discuss + "&nbsp;&nbsp;" + feedback + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var crmProjectTable = initDataTable("crmProjectTableId", "${nvix}/nvixnt/nvixCrmProjectAction!goListContent.action", crmProjectColumns, function(page, pageSize, orderField, orderBy) {
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
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixCrmProjectAction!goSaveOrUpdate.action?id=' + id);
	}
	// 项目沟通
	function goDiscuss(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixCrmProjectAction!goCrmProjectDiscuss.action?id=' + id);
	}
	
	function goFeedback(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCrmProjectAction!goSaveOrUpdateFeedback.action?id=' + id, "feedbackForm", "项目进度", 750, 250, crmProjectTable);
	};
	
	// 客户视图
	function view(id){
		$.ajax({
			url : '${nvix}/nvixnt/nvixCrmProjectAction!view.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
		});
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCrmProjectAction!deleteById.action?id=' + id, '是否删除该项目?', crmProjectTable);
	}

	pageSetUp();
</SCRIPT>