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
			<input type="hidden" value="${controlSQL }" id="controlSQL"  /> <input type="hidden" value="${returnPage }" id="returnPage"  /> 
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
				<h2>进店客户消费和充值统计列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								姓名: <input type="text" value="" class="form-control" id="selectName" style="width: 130px;">
								手机号: <input type="text" value="" class="form-control" id="phone" style="width: 130px;">
								会员卡号: <input type="text" value="" class="form-control" id="cardNum" style="width: 130px;">
							</div>
							<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-info" id="" >
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectName').val('');$('#phone').val('');$('#cardNum').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-default">
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

pageSetUp();
//开始统计列表 
var customerAccountColumns = [ {
"title" : "编号",
"width" : "9%",
"defaultContent" : ''
}, {
"title" : "姓名",
"width" : "13%",
"data" : function(data) {
return data.name;
}
}, {
"title" : "手机",
"width" : "13%",
"data" : function(data) {
return data.mobilePhone;
}
}, {
"title" : "会员卡号",
"width" : "13%",
"data" : function(data) {  
	return data.clipNumber;
}
}, {
"title" : "会员卡类型",
"width" : "13%",
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
"width" : "13%",
"data" : function(data) {       
	return data.channelDistributorName;
}
}, {
"title" : "当前消费总金额",
"width" : "13%",
"data" : function(data) {  
return Number(data.selfExtendStringField1).toFixed(2);
}
}, {
"title" : "当前充值总金额",
"width" : "13%",
"data" : function(data) {  
return Number(data.rechargeTotal).toFixed(2);
}
} ];   
var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/vixntMemberManageDataAction!goStatisticsData.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
	var selectName = $("#selectName").val();
	var phone = $("#phone").val();
	selectName = Url.encode(selectName);
	var controlSQLWd = $("#controlSQL").val();
	controlSQLWd = Url.encode(controlSQLWd);
	var cardNum =$('#cardNum').val();
	cardNum = Url.encode(cardNum);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"phone" : phone,
	"controlSQLWd" : controlSQLWd,
	"cardNum" : cardNum,
	"selectName" : selectName
	};
}, 10, '0');
</script>