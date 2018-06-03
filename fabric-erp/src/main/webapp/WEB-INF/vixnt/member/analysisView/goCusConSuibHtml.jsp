<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<h1 class="page-title txt-color-blueDark">
					<s:if test=" returnPage=='cusAnaPage' ">  
						 <i class="fa fa-list-alt fa-bar-chart-o"></i> 智能分析 <span>> 客户分析 </span>
					</s:if>
					<input type="hidden" value="${returnPage }" id="returnPage"  />  <input type="hidden" value="${controlSQL }" id="controlSQL"  />
			</h1>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
			  <s:if test="returnPage=='cusAnaPage'">
				<button class="btn btn-default" type="button" onclick="loadContent('AnalysisSrwopPageB','${nvix}/nvixnt/vixntMembershipStructureAnalysisAction!goList.action');"> 
					<i class="fa fa-rotate-left"></i> 返回               
				</button>
			  </s:if>
			</div>
		</div>
	</div>
	
		<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>客户消费列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							姓名: <input type="text" value="" class="form-control" id="mytitle" style="width: 130px;" placeholder="请输入姓名">
						</div>
						<div class="form-group">
							手机号: <input type="text" value="" class="form-control" id="mymobilePhone" style="width: 130px;" placeholder="请输入手机号">
						</div>
						<button onclick="stockRecordsTable.ajax.reload();" type="button" class="btn btn-info" >
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#mytitle').val('');$('#mymobilePhone').val('');stockRecordsTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="stockRecordsTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div> 
	
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	var stockRecordsColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "姓名",
	"data" : function(data) {
		return data.name;
	}
	},{
	"title" : "手机号",   
	"data" : function(data) {
		return data.mobilePhone;   
	}
	},{
	"title" : "消费总金额(元)",
	"data" : function(data) {
		return Number(data.selfExtendStringField1).toFixed(2);
	}
	},{
	"title" : "消费次数",
	"data" : function(data) {
		return data.selfExtendStringField2;
	}
	},{
	"title" : "消费均价(元)",
	"data" : function(data) {
		return Number(data.selfExtendStringField3).toFixed(2);
	}
	},{
	"title" : "最近消费金额",
	"data" : function(data) {
		return Number(data.selfExtendStringField5).toFixed(2);
	}
	},{
	"title" : "最近消费时间",    
	"data" : function(data) {
		return data.selfExtendStringField4;
	}
	} ]; 
	var stockRecordsTable = initDataTable("stockRecordsTableId", "${nvix}/nvixnt/vixntSalesAnalysisAction!goSingleList.action", stockRecordsColumns, function(page, pageSize, orderField, orderBy) {
		var mytitle = $("#mytitle").val();
		mytitle = Url.encode(mytitle);
		var mymobilePhone = $("#mymobilePhone").val();
		var startTime = "NoTime";
		var endTime = "NoTime";
		startTime = Url.encode(startTime);
		endTime = Url.encode(endTime);
		mymobilePhone = Url.encode(mymobilePhone);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"mytitle" : mytitle,
		"startTime" : startTime,
		"endTime" : endTime,
		"mymobilePhone" : mymobilePhone
		};
	});
</script>