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
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 采购智能分析 <span>&gt; 采购仪表盘</span>
			</h1>
		</div>
	</div>
	
			<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row" style="padding-top:20px;">
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#EF9178;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月采购询价</strong><br>     
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','LookPurchaseInquiry','PurchasePlate','','');"><strong class="font-lg">${numInquire}单</strong></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#4AB1E3;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月采购计划</strong><br>      
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','LookPurchasePlan','PurchasePlate','','');"><strong class="font-lg">${numPlan}单</strong></a>  
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#2DAEB7;">  
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月采购退货单</strong><br>    
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','LookPurchaseReturn','PurchasePlate','','');"><strong class="font-lg">${numPurchaseReturn}单</strong></a>   
											</div>
										</div>   
									</div>
								</div>
								
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#C8AB8D;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月订单金额环比</strong><br>    
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','LookPurchaseOrderListShow','PurchasePlate','','all!null');"><strong class="font-lg"><i class="${momStrOrderMoneyfa }" style="color:${momStrOrderMoneyColor};"></i>${momStrOrderMoneyNum}%</strong></a>   
											</div>
										</div>   
									</div>
								</div>
								
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#828282;">    
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月订单金额同比</strong><br>    
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','LookPurchaseOrderListShow','PurchasePlate','','all!null');"><strong class="font-lg"><i class="${anStrOrderMoneyfa }" style="color:${anStrOrderMoneyColor};"></i>${anStrOrderMoneyNum }%</strong></a>   
											</div>
										</div>   
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				
				<%-- <div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row">
								<div>
									<form role="search" class="navbar-form navbar-left">
										<div class="form-group">
											<label class="control-label" >
											<strong>选择日期： </strong>
											</label>
										</div>    
										<div class="input-group">
											<input value="${queryTime }" placeholder="时间" style="width: 130px;" id="startTimeA" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" /> <span class="input-group-addon"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
										</div>
										<button type="button" class="btn btn-info" >
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button type="button" class="btn btn-default" >
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div> --%>
				
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<hr class="simple" style="border-color:#c0c0c0;">
									<div class="row">
										<div class="col-xs-3 col-sm-3 text-center">
											<h5>
			<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','LookPurchaseOrderListShow','PurchasePlate','','all!null');">
												<span class="font-lg txt-color-red padding-5">${numPurOrderMoney }元</span>
			</a> 	
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">本月采购订单总金额</strong>
											</h5>
											<div class="padding-5"></div>
											<!-- <p>
											昨日<strong class="txt-color-pink"> 2单</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-down tmyColorA"></i>10%</strong>
											</p> -->
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
		<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','LookPurchaseOrderListShow','PurchasePlate','','d!null');">
 												<span class="font-lg txt-color-red padding-5">${numCompletedOrderMoney }元</span>
 		</a> 										
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">本月已完成采购订单金额</strong>
											</h5>
											<div class="padding-5"></div>
											<!-- <p>
											昨日<strong class="txt-color-pink"> 2单</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-up tmyColorB }"></i>20%</strong>
											</p> -->
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
		<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','LookPurchaseOrderListShow','PurchasePlate','','d!null');">									
 												<span class="font-lg txt-color-red padding-5">${numCompletedOrder }单</span>
 		</a> 										 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">本月已完成采购订单数</strong>
											</h5>
											<div class="padding-5"></div>
											<!-- <p>
											昨日损耗<strong class="txt-color-pink"> 0件</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-down" style="color:#05CD15;"></i>0.00%</strong>
											</p> -->
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
		<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','LookPurchaseOrderListShow','PurchasePlate','','abce!null');">
 												<span class="font-lg txt-color-red padding-5">${numOnPassageOrder}单</span> 
 		</a> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">本月在途采购订单数</strong>
											</h5>
											<div class="padding-5"></div>
											<!-- <p>
											昨日损耗<strong class="txt-color-pink"> 0件</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-down" style="color:#05CD15;"></i>0.00%</strong>
											</p> -->
										</div>
									</div>
									<hr class="simple" style="border-color:#c0c0c0;">
								</div>
							</div>
						</div>
					</div>
				</div>
				
	<div class="row">
		<div class="col-sm-12 col-md-12">
				<div class="row" > <!-- 慢加载统计 -->
					<div class="col-sm-12 col-md-12">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>最近30日采购趋势图</h2>
							</header>
							<div>
								<div class="widget-body no-padding" id="slowLoadPurchaseQuantityTrend">
									<div id="" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>			
		
		<div class="row">
		<div class="col-sm-12 col-md-12">
				<div class="row"> <!-- 慢加载统计 -->
					<div class="col-sm-12 col-md-12">
							<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
								<header>
									<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
									<h2>最近30日已完成采购订单金额趋势图</h2>
								</header>
								<div>
									<div class="widget-body no-padding"  id="slowLoadPurchaseMoneyTrend">
										<div id="" style="height: 300px"></div>
									</div>
								</div>
							</div>
						</div>
				</div>
		</div>
	</div>		
	
	<div class="row">
		<div class="col-sm-12 col-md-12">
			
				<div class="row">
					<div class="col-sm-6 col-md-6" > <!-- 慢加载统计 -->
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>采购订单中:采购产品数量TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding" id="slowLoadPurchaseItemQuantity">
									<div id="" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6" >  <!-- 慢加载统计 -->
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>采购订单中:采购产品金额TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding" id="slowLoadPurchaseItemMoney">
									<div id="" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-6 col-md-6" >  <!-- 慢加载统计 -->
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>采购订单中:供应商采购金额排行TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding" id="slowLoadPurOrderSupplierMoney">
									<div id="" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6" > <!-- 慢加载统计 -->
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>采购订单中:供应商采购订单数排行TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding" id="slowLoadPurOrderSupplierQuantity">
									<div id="" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			
		</div>
	</div>
	<form action="" method="post"  id="exportMD" target="form_iframe" style="margin: 0"></form>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>采购订单分析列表</h2>
			<a href="javascript:void(0);" class="btn bg-color-blueLight mytxt-color-wathet  txt-color-white btn-sm" id=""  onclick="exportExcel();" style="float:right;padding:2px 10px 2px;margin:4px 10px 3px 5px;" >导出</a>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							供应商: <input type="text" value="" class="form-control" id="mytitle" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" onclick="purchaseAnalysisListaTable.ajax.reload();" >
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button type="button" class="btn btn-default" onclick="$('#mytitle').val('');purchaseAnalysisListaTable.ajax.reload();"  >
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="purchaseAnalysisListaTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
/** 数字点击跳转页面查询  **/   
function clickNumQueryGo(queryTime,queryWhat,returnPage,goToWhere,backupsStr) {
	var urlStr = '1';
	if(queryWhat == 'LookPurchaseInquiry'){
		urlStr = '${nvix}/nvixnt/purchase/vixntPurchaseStatisticsClickAction!clickLookPurchaseInquiry.action';
	}else if(queryWhat == 'LookPurchasePlan'){
		urlStr = '${nvix}/nvixnt/purchase/vixntPurchaseStatisticsClickAction!clickLookPurchasePlan.action';
	}else if(queryWhat == 'LookPurchaseReturn'){
		urlStr = '${nvix}/nvixnt/purchase/vixntPurchaseStatisticsClickAction!clickLookPurchaseReturn.action';
	}else if(queryWhat == 'LookPurchaseOrderListShow'){
		urlStr = '${nvix}/nvixnt/purchase/vixntPurchaseStatisticsClickAction!clickLookPurchaseOrderListShow.action';
	}
	if(urlStr != '1'){
		$.ajax({
			url : urlStr,
			data: {queryTime:queryTime,queryWhat:queryWhat,returnPage:returnPage,goToWhere:goToWhere,backupsStr:backupsStr},  
			cache : false,
			success : function(html) {
				$("#mainContent").html(html); 
			}
		});
	}
};
/** 用js把数组的‘数字元’转成‘万元’后返回新数组,这个方法在其他慢加载页面上被调用  **/
function myNumArrToMillionYuan(objArr) {
	var newArr =[];
	if(Array.isArray(objArr)){
		for(var x=0;x<objArr.length;x++){
			var money = Number(objArr[x]);
			var millionYuan = money/10000;
			newArr[x]= parseFloat(millionYuan.toFixed(2));
		}
	}
    return newArr;  
}
/** 慢加载：最近30日采购趋势图  **/
function slowLoadPurchaseQuantityTrend(){
	$.ajax({  
		url : '${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!slowLoadPurchaseQuantityTrend.action',
		type: "POST",
	 	data: {queryTime:'-Lately-Day{30}'},
		success : function(html) {
			$("#slowLoadPurchaseQuantityTrend").html(html); 
		}
	});
}
/** 慢加载：最近30日已完成采购订单金额趋势图  **/
function slowLoadPurchaseMoneyTrend(){
	$.ajax({  
		url : '${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!slowLoadPurchaseMoneyTrend.action',
		type: "POST",
	 	data: {queryTime:'-Lately-Day{30}'},
		success : function(html) {
			$("#slowLoadPurchaseMoneyTrend").html(html); 
		}
	});
}
/** 慢加载：采购订单中:采购产品数量TOP10  **/
function slowLoadPurchaseItemQuantity(){
	$.ajax({  
		url : '${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!slowLoadPurchaseItemQuantity.action',
		type: "POST",
	 	data: {queryTime:'-Lately-Day{30}',topNum:'10'},
		success : function(html) {
			$("#slowLoadPurchaseItemQuantity").html(html); 
		}
	});
}
/** 慢加载：采购订单中:采购产品金额TOP10  **/
function slowLoadPurchaseItemMoney(){
	$.ajax({  
		url : '${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!slowLoadPurchaseItemMoney.action',
		type: "POST",
	 	data: {queryTime:'-Lately-Day{30}',topNum:'10'},
		success : function(html) {
			$("#slowLoadPurchaseItemMoney").html(html); 
		}
	});
}
/** 慢加载：采购订单中:供应商采购金额排行TOP10  **/
function slowLoadPurOrderSupplierMoney(){
	$.ajax({  
		url : '${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!slowLoadPurOrderSupplierMoney.action',
		type: "POST",
	 	data: {queryTime:'-Lately-Day{30}',topNum:'10'},
		success : function(html) {
			$("#slowLoadPurOrderSupplierMoney").html(html); 
		}
	});
}
/** 慢加载：采购订单中:供应商采购订单数排行TOP10  **/
function slowLoadPurOrderSupplierQuantity(){
	$.ajax({  
		url : '${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!slowLoadPurOrderSupplierQuantity.action',
		type: "POST",
	 	data: {queryTime:'-Lately-Day{30}',topNum:'10'},
		success : function(html) {
			$("#slowLoadPurOrderSupplierQuantity").html(html); 
		}
	});
}
$(document).ready(function() {
	pageSetUp();
	slowLoadPurchaseQuantityTrend();
	slowLoadPurchaseMoneyTrend();
	slowLoadPurchaseItemQuantity();
	slowLoadPurchaseItemMoney();
	slowLoadPurOrderSupplierMoney();
	slowLoadPurOrderSupplierQuantity();
	/** 下面都是要替换成真实统计的内容 **/
})
</script>

<script type="text/javascript">
	pageSetUp();
	var purchaseAnalysisListaColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "供应商",
	"data" : function(data) {
		return (data.suppliername != null ? data.suppliername : "" );
	}
	},{
	"title" : "供应商订单数",   
	"data" : function(data) {
		return (data.ordernum != null ? data.ordernum : "" );
	}
	},{
	"title" : "供应商总金额",
	"data" : function(data) {
		return (data.ordermoney != null ? Number(data.ordermoney).toFixed(2) : "" );
	}
	},{
	"title" : "最近采购人",
	"data" : function(data) {
		return (data.purchaseperson != null ? data.purchaseperson : "" );
	}
	},{
	"title" : "最近采购时间",
	"data" : function(data) {
		return (data.mcreatetime != null ? data.mcreatetime : "" );
	}
	} ]; 																										
	var purchaseAnalysisListaTable = initDataTable("purchaseAnalysisListaTableId", "${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!goPurchaseStatisticsTable.action", purchaseAnalysisListaColumns, function(page, pageSize, orderField, orderBy) {
		var mytitle = $("#mytitle").val();
		mytitle = Url.encode(mytitle);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"mytitle" : mytitle,
		};
	}, 10, '0');
</script>
<script type="text/javascript">
/** 导出列表 ***/
function exportExcel() {
	form = document.getElementById("exportMD");
	var mytitle = $("#mytitle").val();
	mytitle = Url.encode(mytitle);
	form.action = "${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!outExcelToPurchaseStatisticsTable.action?mytitle="+mytitle;
	form.submit();
}
</script>