<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<style type="text/css">
	.tmyColorA{color:#05CD15} /* 粉红色下箭头 用于环比符号显示 */
	.tmyColorB{color:#D0000D} /* 蓝色上箭头 用于环比符号显示 */
	.myHoverLine:hover{  /* 指定a标签鼠标移动上去加下划线 */
    border-bottom: 1px solid #000000;
    	color: #fff;
    text-decoration: none;
	}
	.myHoverLine {/* 指定a标签鼠标移出时，字体颜色为白色 */
	    color: #fff;
	}
	.mytxt-color-wathet { /* 目标按钮浅蓝色 */
	background-color:#428BCA !important;
	}
</style>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-warning" type="button" onclick="loadContent('mid_customerAccount','${nvix}/nvixnt/nvixCustomerAction!goList.action');">
						客户
					</button>
					<button class="btn btn-success" type="button" onclick="loadContent('mid_contactPerson','${nvix}/nvixnt/nvixContactPersonAction!goList.action');">
						联系人
					</button>
					<button class="btn btn-primary" type="button" onclick="loadContent('mid_crmProject','${nvix}/nvixnt/nvixCrmProjectAction!goList.action');">
						项目
					</button>
					<button class="btn btn-danger" type="button" onclick="loadContent('mid_chanceAndTracking','${nvix}/nvixnt/nvixChanceAndTrackingAction!goList.action');">
						销售机会
					</button>
					<button class="btn btn-info" type="button" onclick="loadContent('mid_saleLead','${nvix}/nvixnt/nvixSaleLeadAction!goList.action');">
						销售线索
					</button>
				</a>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12 col-md-12">
			<div class="well">
				<div class="row" style="padding-top:20px;">
					<div class="col-md-3">
						<div class="well well-lg" style="background-color:#EF9178;">
							<div class="row">
								<div class="col-md-12 text-center txt-color-white">
									<strong class="">本月新增客户数量</strong><br>    
									<a class="myHoverLine" href="javascript:void(0);" >
										<strong class="font-lg">${customerCount}人</strong>
									</a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="well well-lg" style="background-color:#4AB1E3;">
							<div class="row">
								<div class="col-md-12 text-center txt-color-white">
									<strong class="">本月新增订单数量</strong><br>      
									<a class="myHoverLine" href="javascript:void(0);" >
										<strong class="font-lg">${salesOrderCount}单</strong>
									</a>  
								</div>
							</div>   
						</div>
					</div>
					<div class="col-md-3">
						<div class="well well-lg" style="background-color:#2DAEB7;">  
							<div class="row">
								<div class="col-md-12 text-center txt-color-white">
									<strong class="">本月跟踪项目数量</strong><br>    
									<a class="myHoverLine" href="javascript:void(0);" >
										<strong class="font-lg">${projectCount}个</strong>
									</a>   
								</div>
							</div>   
						</div>
					</div>
					<div class="col-md-3">
						<div class="well well-lg" style="background-color:#C8AB8D;">
							<div class="row">
								<div class="col-md-12 text-center txt-color-white">
									<strong class="">本月回款金额</strong><br>    
									<a class="myHoverLine" href="javascript:void(0);" >
										<strong class="font-lg">${amount}元</strong>
									</a> 
								</div>
							</div>   
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-6">
			<div class="jarviswidget">
				<header>
					<span class="widget-icon"> <i class="fa fa-envelope-o"></i></span>
					<h2>提醒</h2>
					<ul class="nav nav-tabs pull-right in" id="myTab">
						<li class="active"><a data-toggle="tab" href="#d0"><span class="hidden-mobile hidden-tablet">客户提醒</span></a></li>
						<li><a data-toggle="tab" href="#d1"><span class="hidden-mobile hidden-tablet">纪念日</span></a></li>
						<li><a data-toggle="tab" href="#d2"><span class="hidden-mobile hidden-tablet">未回款</span></a></li>
						<li><a data-toggle="tab" href="#d3"><span class="hidden-mobile hidden-tablet">今日回款</span></a></li>
					</ul>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div class="tab-content">
							<div class="tab-pane fade active in" id="d0">
								<table id="customerAccountRemindTableId" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th width="8%">编号</th>
											<th>客户编码</th>
											<th>客户名称</th>
											<th>所有者</th>
											<th>停滞天数</th>
										</tr>
									</thead>
								</table>
							</div>
							<div class="tab-pane fade in" id="d1">
								<table id="memorialDayListTableId" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th width="8%">编号</th>
											<th>纪念日类型</th>
											<th>客户</th>
											<th>联系人</th>
											<th>纪念日</th>
										</tr>
									</thead>
								</table>
							</div>
							<div class="tab-pane fade in" id="d2">
								<table id="backPlanTableId" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th width="8%">编号</th>
											<th>金额</th>
											<th>计划回款日期</th>
											<th>订单</th>
											<th>客户</th>
											<th>期次</th>
											<th>是否回款</th>
											<th>所有人</th>
										</tr>
									</thead>
								</table>
							</div>
							<div class="tab-pane fade in" id="d3">
								<table id="backRecordTableId" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th width="8%">编号</th>
											<th>金额</th>
											<th>期次</th>
											<th>回款日期</th>
											<th>是否开票</th>
											<th>客户</th>
											<th>订单</th>
											<th>所有人</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</article>
		<article class="col-sm-12 col-md-12 col-lg-6">
			<div class="jarviswidget">
				<header>
					<span class="widget-icon"> <i class="fa fa-envelope-o"></i></span>
					<h2>项目情况</h2>
					<ul class="nav nav-tabs pull-right in" id="myTab">
						<li class="active"><a data-toggle="tab" href="#d4"><span class="hidden-mobile hidden-tablet">项目沟通</span></a></li>
						<li><a data-toggle="tab" href="#d5"><span class="hidden-mobile hidden-tablet">项目进度</span></a></li>
					</ul>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div class="tab-content">
							<div class="tab-pane fade active in" id="d4">
								<table id="evaluationReviewTableId" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th width="8%">编号</th>
											<th width="50%">沟通内容</th>
											<th width="12%">项目</th>
											<th width="10%">沟通人</th>
											<th width="20%">沟通时间</th>
										</tr>
									</thead>
								</table>
							</div>
							<div class="tab-pane fade in" id="d5">
								<table id="crmProjectTableId" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th width="8%">编号</th>
											<th>项目名称</th>
											<th>负责人</th>
											<th>客户</th>
											<th>立项日期</th>
											<th>项目进度</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</article>
	</div>
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget">
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
									客户:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName3">
								</div>
								<button onclick="chanceAndTrackingTable.ajax.reload();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i> 检索
								</button>
								<button onclick="$('#searchSubject').val('');$('#searchCustomerName3').val('');chanceAndTrackingTable.ajax.reload();" type="button" class="btn btn-default">
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
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</article>
	</div>
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-6">
			<div class="jarviswidget">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>销售线索列表</h2>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div id="chanceAndTrackingSearchForm">
							<form role="search" class="navbar-form navbar-left">
								<div class="form-group">
									主题:<input type="text" value="" placeholder="线索主题" class="form-control" id="searchSubject1"> 
									客户:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName4">
								</div>
								<button onclick="saleLeadTable.ajax.reload();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i> 检索
								</button>
								<button onclick="$('#searchSubject1').val('');$('#searchCustomerName4').val('');saleLeadTable.ajax.reload();" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i> 清空
								</button>
							</form>
						</div>
						<table id="saleLeadTableId" class="table table-striped table-bordered table-hover" width="100%">
							<thead>
								<tr>
									<th width="8%">编号</th>
									<th>主题</th>
									<th>客户</th>
									<th>项目</th>
									<th>头衔</th>
									<th>类型</th>
									<th>概率</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</article>
		<article class="col-sm-12 col-md-12 col-lg-6">
			<div class="jarviswidget">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>销售活动列表</h2>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div id="chanceAndTrackingSearchForm">
							<form role="search" class="navbar-form navbar-left">
								<div class="form-group">
									主题:<input type="text" value="" placeholder="活动主题" class="form-control" id="title"> 
									客户:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName2">
								</div>
								<button onclick="saleActivityTable.ajax.reload();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i> 检索
								</button>
								<button onclick="$('#title').val('');$('#searchCustomerName2').val('');saleActivityTable.ajax.reload();" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i> 清空
								</button>
							</form>
						</div>
						<table id="saleActivityTableId" class="table table-striped table-bordered table-hover" width="100%">
							<thead>
								<tr>
									<th width="8%">编号</th>
									<th>主题</th>
									<th>类型</th>
									<th>客户</th>
									<th>负责人</th>
									<th>创建人</th>
									<th>活动地点</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</article>
	</div>
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-6">
			<div class="jarviswidget">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>回款计划列表</h2>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div id="chanceAndTrackingSearchForm">
							<form role="search" class="navbar-form navbar-left">
								<div class="form-group">
									客户姓名:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName"> 
									所有人姓名:<input type="text" value="" placeholder="所有人姓名" class="form-control" id="searchOwnerName">
								</div>
								<button onclick="backSectionPlanTable.ajax.reload();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i> 检索
								</button>
								<button onclick="$('#searchOwnerName').val('');$('#searchCustomerName').val('');backSectionPlanTable.ajax.reload();" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i> 清空
								</button>
							</form>
						</div>
						<table id="backSectionPlanTableId" class="table table-striped table-bordered table-hover" width="100%">
							<thead>
								<tr>
									<th width="8%">编号</th>
									<th>金额</th>
									<th>计划回款日期</th>
									<th>订单</th>
									<th>客户</th>
									<th>期次</th>
									<th>是否回款</th>
									<th>所有人</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</article>
		<article class="col-sm-12 col-md-12 col-lg-6">
			<div class="jarviswidget">
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
									客户姓名:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName1"> 
									所有人姓名:<input type="text" value="" placeholder="所有人姓名" class="form-control" id="searchOwnerName1">
								</div>
								<button onclick="backSectionRecordTable.ajax.reload();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i> 检索
								</button>
								<button onclick="$('#searchOwnerName1').val('');$('#searchCustomerName1').val('');backSectionRecordTable.ajax.reload();" type="button" class="btn btn-default">
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
									<th>所有人</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</article>
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	// 销售机会
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
	}];

	var chanceAndTrackingTable = initDataTable("chanceAndTracking", "${nvix}/nvixnt/nvixChanceAndTrackingAction!goListContent.action", chanceAndTrackingColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchSubject").val();
		var cName = $("#searchCustomerName3").val();
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
	},5);
	// 销售线索
	var saleLeadColumns = [ {
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
		"name" : "title",
		"data" : function(data) {
			return data.title;
		}
		}, {
		"name" : "type",
		"data" : function(data) {
			return data.type;
		}
		}, {
		"name" : "probability",
		"data" : function(data) {
			if(data.probability != null && data.probability > 0){
				return data.probability+"%";
			}else{
				return "0%";
			}
		}
	}];

	var saleLeadTable = initDataTable("saleLeadTableId", "${nvix}/nvixnt/nvixSaleLeadAction!goListContent.action", saleLeadColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchSubject1").val();
		var cName = $("#searchCustomerName4").val();
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
	},5);
	// 回款计划
	var backSectionPlanColumns = [ {
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
		"name" : "backSectionPlanDate",
		"data" : function(data) {
			return data.backSectionPlanDateStr;
		}
		},{
		"name" : "salesOrder",
		"data" : function(data) {
			return data.salesOrder == null ? "" : data.salesOrder.title;
		}
		}, {
		"name" : "customerAccount",
		"data" : function(data) {
			return data.customerAccount == null ? "" : data.customerAccount.name;
		}
		}, {
		"name" : "stageNumber",
		"data" : function(data) {
			return data.stageNumber;
		}
		}, {
		"name" : "backSectionPlanStatus",
		"data" : function(data) {
			if(data.backSectionPlanStatus == 1){
				return "<span class='label label-success'>是</span>";
			}else{
				return "<span class='label label-info'>否</span>";
			}
		}
		}, {
		"name" : "owner",
		"data" : function(data) {
			return data.owner == null ? "" : data.owner.name;
		}
	}];

	var backSectionPlanTable = initDataTable("backSectionPlanTableId", "${nvix}/nvixnt/nvixBackSectionPlanAction!goListContent.action", backSectionPlanColumns, function(page, pageSize, orderField, orderBy) {
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
	},5);
	var backPlanColumns = [ {
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
		"name" : "backSectionPlanDate",
		"data" : function(data) {
			return data.backSectionPlanDateStr;
		}
		},{
		"name" : "salesOrder",
		"data" : function(data) {
			return data.salesOrder == null ? "" : data.salesOrder.title;
		}
		}, {
		"name" : "customerAccount",
		"data" : function(data) {
			return data.customerAccount == null ? "" : data.customerAccount.name;
		}
		}, {
		"name" : "stageNumber",
		"data" : function(data) {
			return data.stageNumber;
		}
		}, {
		"name" : "backSectionPlanStatus",
		"data" : function(data) {
			if(data.backSectionPlanStatus == 1){
				return "<span class='label label-success'>是</span>";
			}else{
				return "<span class='label label-info'>否</span>";
			}
		}
		}, {
		"name" : "owner",
		"data" : function(data) {
			return data.owner == null ? "" : data.owner.name;
		}
	}];

	var backPlanTable = initDataTable("backPlanTableId", "${nvix}/nvixnt/nvixBackSectionPlanAction!goBackSectionPlanListContent.action", backPlanColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	},5);
	// 回款记录
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
		}, {
		"name" : "owner",
		"data" : function(data) {
			return data.owner == null ? "" : data.owner.name;
		}
	}];

	var backSectionRecordTable = initDataTable("backSectionRecordTableId", "${nvix}/nvixnt/nvixBackSectionRecordAction!goListContent.action", backSectionRecordColumns, function(page, pageSize, orderField, orderBy) {
		var owner = $("#searchOwnerName1").val();
		var name = $("#searchCustomerName1").val();
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
	},5);
	var backRecordColumns = [ {
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
		}, {
		"name" : "owner",
		"data" : function(data) {
			return data.owner == null ? "" : data.owner.name;
		}
	}];

	var backRecordTable = initDataTable("backRecordTableId", "${nvix}/nvixnt/nvixBackSectionRecordAction!goBackSectionRecordListContent.action", backRecordColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	},5);
	// 销售活动
	var saleActivityColumns = [ {
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"name" : "title",
		"data" : function(data) {
			return data.title;
		}
		}, {
		"name" : "saleActivity",
		"data" : function(data) {
			if(data.saleActivity != null){
				return "<span class='label label-info'>"+data.saleActivity.name+"</span>";
			}
		}
		}, {
		"name" : "customerAccount",
		"data" : function(data) {
			return data.customerAccount.name;
		}
		},{
		"name" : "personInCharge",
		"data" : function(data) {
			return data.personInCharge.name;
		}
		}, {
		"name" : "created_by",
		"data" : function(data) {
			return data.created_by.name;
		}
		}, {
		"name" : "address",
		"data" : function(data) {
			return data.address;
		}
	}];

	var saleActivityTable = initDataTable("saleActivityTableId", "${nvix}/nvixnt/nvixSaleActivityAction!goListContent.action", saleActivityColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#title").val();
		var cName = $("#searchCustomerName2").val();
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
	},5);
	// 纪念日
	var customerAccountRemindColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.code;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.employee == null ? '无' : data.employee.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if(data.stagnateDay != 0){
			return data.stagnateDay+"天";
		}else{
			return "";
		}
	}
	}];

	var customerAccountRemindTable = initDataTable("customerAccountRemindTableId", "${nvix}/nvixnt/nvixCustomerAction!goCustomerAccountRemind.action", customerAccountRemindColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	},5);
	// 纪念日
	var memorialDayColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if(data.memorialDayType != null){
			return "<span class='label label-info'>"+data.memorialDayType.name+"</span>";
		}
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.customerAccount == null ? '' : data.customerAccount.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.contactPerson == null ? '' : data.contactPerson.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.dateStr;
	}
	}];

	var memorialDayTable = initDataTable("memorialDayListTableId", "${nvix}/nvixnt/nvixMemorialDayAction!goMemorialDayListContent.action", memorialDayColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	},5);
	// 沟通记录
	var evaluationReviewColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.evaluationContent;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.crmProject == null ? '' : data.crmProject.subject;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.employee == null ? '' : data.employee.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.evaluationTimeTimeStr;
	}
	}];

	var evaluationReviewTable = initDataTable("evaluationReviewTableId", "${nvix}/nvixnt/nvixCrmProjectAction!getEvaluationReviewListJson.action", evaluationReviewColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	}, 5);
	// 项目进度
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
	"name" : "projectEstablishDate",
	"data" : function(data) {
		return data.projectEstablishDateStr;
	}
	}, {
	"name" : "projectSchedule",
	"data" : function(data) {
		if(data.projectSchedule != null && data.projectSchedule > 0){
			return "<div data-progressbar-value='"+data.projectSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
		}else{
			return "<div data-progressbar-value='0' class='progress progress-xs'><div class='progress-bar'></div></div>";
		}
	}
	} ];

	var crmProjectTable = initDataTable("crmProjectTableId", "${nvix}/nvixnt/nvixCrmProjectAction!goListContent.action", crmProjectColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	},5);
</script>