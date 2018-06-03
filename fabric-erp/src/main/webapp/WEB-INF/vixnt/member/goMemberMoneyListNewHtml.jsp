<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<h1 class="page-title txt-color-blueDark">
					<s:if test=" returnPage=='workHomePage' || returnPage.contains(\"ctt\") ">  <!-- 去工作台-->
						 <i class="fa fa-list-alt fa-fw "></i> 工作台 
					</s:if>
					<s:else>
							<i class="fa-fw fa fa-user"></i>会员管理  
					</s:else>
					<input type="hidden" value="${returnPage }" id="returnPage"  />  <input type="hidden" value="${controlSQL }" id="controlSQL"  />
			</h1>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">   
				<s:if test=" returnPage=='workHomePage' ">
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');"> 
					<i class="fa fa-rotate-left"></i> 返回             
				</button>
			 	 </s:if>
			 	 <s:elseif test="returnPage.contains(\"ctt\")"> 
			  	<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!searchTimeWHPage.action?queryTime=${returnPage}');"> 
					<i class="fa fa-rotate-left"></i> 返回            
				</button>
			  	</s:elseif>
			  	<s:elseif test=" returnPage.contains(\"t-Sm-HOT\") || returnPage.contains(\"l-Tm-HOT\") ">
			 	 <button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!goMember.action?queryTime=${returnPage}');"> 
					<i class="fa fa-rotate-left"></i> 返回              
				</button>
			 	 </s:elseif>
			 	<s:else>   
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!goMember.action');"> 
					<i class="fa fa-rotate-left"></i> 返回              
				</button>
				</s:else>
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
							<button onclick="" type="button" class="btn btn-info" id="searchButtonB">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectName').val('');$('#phone').val('');$('#carNumber').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-default">
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
$(document).ready(function() {
	pageSetUp();
	// PAGE RELATED SCRIPTS
	$('#searchButtonB').click(function(){ //客户消费明细检索
		customerAccountTable.ajax.reload();
	});
})

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
"title" : "当前消费总金额",
"width" : "11%",
"data" : function(data) {  
return Number(data.selfExtendStringField1).toFixed(2);
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
}, {
"title" : "操作",
"width" : "4%",
"orderable" : false,
"data" : function(data) {     
	var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "','查看详情');\" title='查看详情'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
	 return show;
}
} ];   
var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/vixntMemberManageDataAction!goSingleListStatistics.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
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
function saveOrUpdate(id, title) {//查看详情
	$.ajax({
		url :'${nvix}/nvixnt/nvixCustomerAccountAction!show.action',//去超的统计页面
		data: {controlSQL:$("#controlSQL").val(),id:id,returnPage:$("#returnPage").val()},		
		cache : false,
		success : function(html) {
			$("#mainContent").html(html); 
		}
		});
};
function goList(dateType) {
	loadContent('', '${nvix}/nvixnt/vixntMemberManageDataAction!goSingleListStatistics.action?dateType=' + dateType);
};
</script>