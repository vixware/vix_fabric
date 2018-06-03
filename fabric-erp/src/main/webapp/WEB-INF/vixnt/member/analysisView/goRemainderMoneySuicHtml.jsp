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
				<h2>会员列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								姓名: <input type="text" value="" class="form-control" id="selectName" style="width: 130px;">
								手机号: <input type="text" value="" class="form-control" id="phone" style="width: 130px;">
							</div>
							<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-primary" id="searchButtonB">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectName').val('');$('#phone').val('');$('#carNumber').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
		</div> 
	
	</div>
</div>
<script type="text/javascript">
//开始统计列表
pageSetUp();
var customerAccountColumns = [ {
"title" : "编号",
"width" : "5%",
"defaultContent" : ''
}, {
"title" : "姓名",
"width" : "12%",
"data" : function(data) {
return data.name;
}
}, {
"title" : "手机",
"width" : "12%",
"data" : function(data) {
return data.mobilePhone;
}
}, {
"title" : "会员卡号",
"width" : "11%",
"data" : function(data) {  
return data.clipNumber;
}
}, {
"title" : "会员卡类型",
"width" : "11%",
"data" : function(data) {
	if(data.customerClipType == '1'){
		return "<span class='label label-warning'>余额卡</span>";
	}else if(data.customerClipType == '2'){
		return "<span class='label label-info'>次&nbsp;&nbsp;&nbsp;&nbsp;卡</span>";
	}		
	return ""; 
}
},{
"title" : "门店",
"width" : "12%",
"data" : function(data) {       
	return data.channelDistributorName;
}
}, {
"title" : "会员卡总余额",
"width" : "11%",
"data" : function(data) {  
return Number(data.remainderMoney).toFixed(2);
}
}, {
"title" : "会员卡到期时间",
"width" : "11%",
"data" : function(data) {  
return data.selfExtendStringField2;
}
}, {
"title" : "会员创建时间",
"width" : "11%",
"data" : function(data) {
return data.createTimeFormatA;
}
} ];   
var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/vixntMemberManageDataAction!goStatisticsData.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
	var selectName = $("#selectName").val();
	var phone = $("#phone").val();
	selectName = Url.encode(selectName);
	var controlSQLWd = $("#controlSQL").val();
	controlSQLWd = Url.encode(controlSQLWd);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"phone" : phone,
	"controlSQLWd" : controlSQLWd,
	"selectName" : selectName
	};
}, 10, '0');

function goList(dateType) {
	loadContent('', '${nvix}/nvixnt/vixntMemberManageDataAction!goSingleListStatistics.action?dateType=' + dateType);
};
</script>