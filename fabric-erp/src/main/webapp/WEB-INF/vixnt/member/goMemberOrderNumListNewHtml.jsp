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
					<input type="hidden" value="${returnPage }" id="returnPage"  /> <input type="hidden" value="${controlSQL }" id="controlSQL"  />
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
				<h2>订单列表</h2>
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
function myJudgeNulltoFixed(num) {//判断null值后再进行保留两位小数 
	if(num != null && typeof(num)!="undefined"){
		return (num).toFixed(2); 
	}else{
		return "";
	}
}
function myJudgeNulltoPayType(num) {//判断null值后再进行付款方式的显示//1,现金;2,余额;3,银行卡;4,微信;5,支付宝;
	if(num != null && typeof(num)!="undefined"){
		if(num == '1'){
			return "<span class='label label-warning'>现金</span>";
		}else if(num == '2'){
			return "<span class='label label-info'>余额</span>";
		}else if(num == '3'){
			return "<span class='label label-info'>银行卡</span>";
		}else if(num == '4'){
			return "<span class='label label-info'>微信</span>";
		}else if(num == '5'){
			return "<span class='label label-info'>支付宝</span>";
		}
	}else{
		return "";
	}
}
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
		return Number(data.amount).toFixed(2);
	}
	}, {
	"title" : "优惠金额",
	"data" : function(data) {
		if(data.discountFee != null && data.discountFee != ''){
			return Number(data.discountFee).toFixed(2);
		}
		return '0.00';
	}
	}, {
	"title" : "满减金额",
	"data" : function(data) {
		return Number(data.reduceAmount).toFixed(2);
	}
	}, {
	"title" : "支付金额",
	"data" : function(data) {
		return Number(data.payAmount).toFixed(2);
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
	var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "','查看订单明细');\" title='查看订单明细'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
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

function saveOrUpdate(id,title) { 
	var str = 'id='+ id +"&"+"controlSQL="+$("#controlSQL").val();  
	openWindowForShow('${nvix}/nvixnt/vixntMemberManageDataAction!goOrderDetailsPage.action?' + str, title, 850, 450);
}

</script>