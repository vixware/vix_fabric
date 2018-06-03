<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-dollar"></i> <a>会员消费管理</a><span>> 消费记录</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>消费记录列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										会员姓名: <input type="text" value="" class="form-control" id="selectName">
										会员手机号: <input type="text" value="" class="form-control" id="phone">
									</div>
									<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#selectName').val('');$('#phone').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var customerAccountColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "订单号",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "会员姓名",
	"data" : function(data) {
		return data.customerName;
	}
	}, {
	"title" : "会员电话",
	"data" : function(data) {
		return data.customerPhone;
	}
	}, {
	"title" : "门店",
	"data" : function(data) {
		return data.channelDistributorName;
	}
	}, {
	"title" : "商品金额",
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"title" : "会员折扣",
	"data" : function(data) {
		return data.discount * 100 + "%";
	}
	}, {
	"title" : "优惠金额",
	"data" : function(data) {
		if(data.discountFee != null && data.discountFee != ''){
			return data.discountFee;
		}
		return 0;
	}
	}, {
	"title" : "满减金额",
	"data" : function(data) {
		return data.reduceAmount;
	}
	}, {
	"title" : "支付金额",
	"data" : function(data) {
		return data.payAmount;
	}
	},{
	"title" : "消费日期",
	"data" : function(data) {
		return data.payDateStr;
	}
	}, {
	"title" : "支付方式",
	"data" : function(data) {
		if(data.payType == "1"){
			return "<span class='label label-info'>现金</span>";
		}else if(data.payType == "2"){
			return "<span class='label label-info'>余额</span>";
		}else if(data.payType == "3"){
			return "<span class='label label-info'>银行卡</span>";
		}else if(data.payType == "4"){
			return "<span class='label label-info'>微信</span>";
		}else if(data.payType == "5"){
			return "<span class='label label-info'>支付宝</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "8%",
	"orderable" : false,
	"data" : function(data) {
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goItemList('" + data.id + "','查看消费明细');\" title='查看消费明细'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		return show; 
	}
	} ];

	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/nvixExpenseRecordAction!goSingleList.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var phone = $("#phone").val();
		var selectName = $("#selectName").val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"phone" : phone,
		"selectName" : selectName
		};
	}, 10, '0');
	
	function goItemList(id,title) {
		openWindowForShow('${nvix}/nvixnt/nvixExpenseRecordAction!goItemList.action?id=' + id, title, 850, 500);
	}
</script>