<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%-- <link href="${nvix}/common/js/vreport/vreport/css/vreport.css" rel="stylesheet" type="text/css" />
<link href="${nvix}/common/js/vreport/jquery/css/jquery-ui-1.9.1.custom.css" rel="stylesheet" type="text/css" />
<script src="${nvix}/common/js/vreport/vprint.js" type="text/javascript"></script>
<script src="${nvix}/common/js/raphael-min.js" type="text/javascript"></script>
<script src="${nvix}/common/js/vreport/flowtask.js" type="text/javascript"></script> --%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-user"></i> 会员营销 <span>&gt; 智能营销</span> <span>&gt; 营销流程执行汇总</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('drp_distributionsystem','${nvix}/nvixnt/vixntDistributionSystemAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12 col-md-12">
			<div class="well">
				<!-- 头部会员信息内容部分 -->
				<div class="row">
					<div class="col-xs-12">
						<div id="" class="jarviswidget">
							<header>
								<h2>流程信息</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="contentDiv"></div>
									<script>
									   var report = new Rreport({
										contentDiv : 'contentDiv',
										isEdit : true,
										});
										$('#vreporttoolbar').attr({ style : 'display:none'});
										report.loadData(${activityFlow});
							      </script>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 菜单选项切换部分 -->
				<div class="row">
					<div class="col-xs-12">
						<div class="tab_menu">
							<ul>
								<li onclick="customerAccountTable.ajax.reload();" class="selected">优惠券列表</li>
								<li onclick="memberTagTable.ajax.reload();">短信列表</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="tab-cons">
							<div class="common">
								<article style="overflow: hidden; zoom: 1;">
									<div class="jarviswidget">
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i>
											</span>
											<h2>优惠券列表</h2>
										</header>
										<div>
											<div class="widget-body no-padding">
												<div id="">
													<form role="search" class="navbar-form navbar-left">
														<div class="form-group">
															主题: <input type="text" value="" class="form-control" id="selectName">
														</div>
														<button onclick="rechargeRecordTable.ajax.reload();" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="$('#selectName').val('');rechargeRecordTable.ajax.reload();" type="button" class="btn btn-">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
													</form>
												</div>
												<table id="rechargeRecordTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
										</div>
									</div>
								</article>
							</div>
							<div class="common">
								<div class="jarviswidget">
									<header>
										<span class="widget-icon"> <i class="fa fa-table"></i>
										</span>
										<h2>短信列表</h2>
									</header>
									<div>
										<div class="widget-body no-padding">
											<div id="">
												<form role="search" class="navbar-form navbar-left">
													<div class="form-group">
														主题: <input type="text" value="" class="form-control" id="searchName">
													</div>
													<button onclick="messageSendRecordTable.ajax.reload();" type="button" class="btn btn-primary">
														<i class="glyphicon glyphicon-search"></i> 检索
													</button>
													<button onclick="$('#searchName').val('');messageSendRecordTable.ajax.reload();" type="button" class="btn btn-default">
														<i class="glyphicon glyphicon-repeat"></i> 清空
													</button>
												</form>
											</div>
											<table id="messageSendRecordTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var tab_menu_li = $('.tab_menu li');
	$('.tab-cons .common:gt(0)').hide();

	tab_menu_li.click(function() {
		var index = $(this).index() + 1;
		$(this).addClass('selected').siblings().removeClass('selected');
		var tab_menu_li_index = tab_menu_li.index(this);
		$('.tab-cons .common').eq(tab_menu_li_index).show().siblings().hide();
	});
	var rechargeRecordColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "储值金额",
	"data" : function(data) {
		return data.payMoney;
	}
	}, {
	"title" : "储值日期",
	"data" : function(data) {
		return data.payDateStr;
	}
	}, {
	"title" : "支付方式",
	"data" : function(data) {
		if (data.payType == "1") {
			return "<span class='label label-info'>现金</span>";
		} else if (data.payType == "2") {
			return "<span class='label label-info'>余额</span>";
		} else if (data.payType == "3") {
			return "<span class='label label-info'>银行卡</span>";
		} else if (data.payType == "4") {
			return "<span class='label label-info'>微信</span>";
		} else if (data.payType == "5") {
			return "<span class='label label-info'>支付宝</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "','查看');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		return show;
	}
	} ];

	var rechargeRecordTable = initDataTable("rechargeRecordTableId", "${nvix}/nvixnt/vixntDistributionSystemAction!goRechargeRecordList.action", rechargeRecordColumns, function(page, pageSize, orderField, orderBy) {
		var channelDistributorId = $("#channelDistributorId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"channelDistributorId" : channelDistributorId
		};
	}, 10, '0');

	var messageSendRecordColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "手机号",
	"data" : function(data) {
		return data.mobilePhone;
	}
	}, {
	"title" : "短信内容",
	"width" : "50%",
	"data" : function(data) {
		return data.content;
	}
	}, {
	"title" : "发送时间",
	"data" : function(data) {
		return data.postTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return del;
	}
	} ];

	var messageSendRecordTable = initDataTable("messageSendRecordTableId", "${nvix}/nvixnt/vixntDistributionSystemAction!goMessageSendRecordList.action", messageSendRecordColumns, function(page, pageSize, orderField, orderBy) {
		var channelDistributorId = $("#channelDistributorId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"channelDistributorId" : channelDistributorId
		};
	});

</script>