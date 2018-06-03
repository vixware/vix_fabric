<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> 会员交互管理 <span>> 会员整合管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSyncCustomerAccount()">
						<i class="glyphicon glyphicon-list"></i>&nbsp;会员整合
					</button>
				</a>
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSyncCustomerAccount()">
						<i class="glyphicon glyphicon-list"></i>&nbsp;短信发送
					</button>
				</a>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header role="heading">
			<ul class="nav nav-tabs pull-left in">
				<li class="active"><a data-toggle="tab" href="#hr1" aria-expanded="true"> 待整合会员列表 </a></li>
				<li class=""><a data-toggle="tab" href="#hr2" aria-expanded="true"> 已整合会员列表 </a></li>
				<!-- <li class=""><a data-toggle="tab" href="#hr3" aria-expanded="true"> 操作日志 </a></li> -->
			</ul>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div class="tab-content">
					<div class="tab-pane active" id="hr1">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								姓名: <input type="text" value="" class="form-control" id="vixTaskName">
							</div>
							<button onclick="vixTaskTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#vixTaskName').val('');vixTaskTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="vixTask" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<div class="tab-pane" id="hr2">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								姓名: <input type="text" value="" class="form-control" id="myVixTaskName">
							</div>
							<button onclick="myVixTaskTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#myVixTaskName').val('');myVixTaskTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="myVixTask" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<!-- <div class="tab-pane" id="hr3">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								日志名称: <input type="text" value="" class="form-control" id="myTeamVixTaskName">
							</div>
							<button onclick="myTeamVixTaskTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#myTeamVixTaskName').val('');myTeamVixTaskTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="myTeamVixTask" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div> -->
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	var vixTaskColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "姓名",
	"width" : "25%",
	"data" : function(data) {
		return data.questName;
	}
	}, {
	"title" : "手机",
	"width" : "20%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "联系地址",
	"width" : "15%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "来源店铺",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改任务');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
	}
	} ];

	var vixTaskTable = initDataTable("vixTask", "${nvix}/nvixnt/MembershipIntegrationAction!goSingleList.action", vixTaskColumns, function(page, pageSize, orderField, orderBy) {
		var vixTaskName = $("#vixTaskName").val();
		vixTaskName = Url.encode(vixTaskName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"vixTaskName" : vixTaskName
		};
	}, 10, "1");
	var myVixTaskColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "姓名",
	"width" : "25%",
	"data" : function(data) {
		return data.questName;
	}
	}, {
	"title" : "手机",
	"width" : "20%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "联系地址",
	"width" : "15%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "来源店铺",
	"width" : "15%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改任务');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
	}
	} ];

	var myVixTaskTable = initDataTable("myVixTask", "${nvix}/nvixnt/MembershipIntegrationAction!goSingleList2.action", myVixTaskColumns, function(page, pageSize, orderField, orderBy) {
		var vixTaskName = $("#myVixTaskName").val();
		vixTaskName = Url.encode(vixTaskName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"vixTaskName" : vixTaskName
		};
	}, 10, "1");
	/* var myTeamVixTaskColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "操作内容",
	"width" : "25%",
	"data" : function(data) {
		return data.questName;
	}
	}, {
	"title" : "操作人",
	"width" : "20%",
	"data" : function(data) {
		return "<div class='project-members'>" + data.empliststr + "</div>";
	}
	}, {
	"title" : "截止日期",
	"width" : "15%",
	"data" : function(data) {
		return data.taskEndTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改任务');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
	}
	} ];

	var myTeamVixTaskTable = initDataTable("myTeamVixTask", "${nvix}/nvixnt/vixntMembershipIntegrationAction!goSingleList1.action", myTeamVixTaskColumns, function(page, pageSize, orderField, orderBy) {
		var vixTaskName = $("#myTeamVixTaskName").val();
		vixTaskName = Url.encode(vixTaskName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"vixTaskName" : vixTaskName
		};
	}, 10, "1"); */
	function goProjectTaskDetails(id) {
		loadContent('mid_projectstask', '${nvix}/nvixnt/MembershipIntegrationAction!goProjectTaskDetails.action?id=' + id);
	};
	function goSyncCustomerAccount() {
		$.ajax({
		url : '${nvix}/nvixnt/MembershipIntegrationAction!goSyncCustomerAccount.action',
		cache : false,
		success : function(result) {
			loadContent('', '${nvix}/nvixnt/MembershipIntegrationAction!goList.action');
		}
		});
	};
	function sendMessage() {
		$.ajax({
		url : '${nvix}/nvixnt/vixntMembershipIntegrationAction!sendMessage.action',
		cache : false,
		success : function(result) {
		}
		});
	};
</script>