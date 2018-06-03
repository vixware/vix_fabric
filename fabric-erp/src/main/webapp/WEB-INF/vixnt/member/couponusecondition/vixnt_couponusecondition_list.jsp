<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-globe"></i> 会员营销 <span onclick="">&gt; 优惠券管理</span><span onclick="">&gt; 优惠券使用汇总</span>
			</h1>
		</div>
	</div>
	<!-- row -->
	<div class="jarviswidget">
		<header><span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>优惠券使用汇总</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form class="navbar-form navbar-left" role="search">
					标题：
					<div class="form-group">
						<input class="form-control" type="text" value="" placeholder="标题" id="messageName">
					</div>
					<button onclick="messageTemplateTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#messageName').val('');messageTemplateTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="messageTemplateTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var messageTemplateColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "编码",
	"data" : function(data) {
		return data.code;
	}
	},{
	"title" : "面额",
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"title" : "使用限制",
	"data" : function(data) {
		if(data.couponUseCondition == '1'){
			return "满"+data.expenditure+"元";
		}else  if(data.couponUseCondition == '0'){
			return "无限制";
		}
		return "";
	}
	}, {
	"title" : "发放数量",
	"data" : function(data) {
		return data.quantity;
	}
	}, {
	"title" : "未使用数量",
	"data" : function(data) {
		return data.noUseQuantityCount;
	}
	},{
	"title" : "已使用数量",
	"data" : function(data) {
		return data.useQuantityCount;
	}
	}, {
	"title" : "作废数量",
	"data" : function(data) {
		return data.cancellationQuantityCount;
	}
	} ];

	var messageTemplateTable = initDataTable("messageTemplateTableId", "${nvix}/nvixnt/vixntCouponUseConditionAction!goSingleList.action", messageTemplateColumns, function(page, pageSize, orderField, orderBy) {
		var messageName = $("#messageName").val();
		messageName = Url.encode(messageName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"messageName" : messageName
		};
	});
</script>