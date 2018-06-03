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
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售智能分析 <span>&gt; 销售仪表盘</span>
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
												<strong class="">今日销售订单数</strong><br>       
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('Today','SeeNumOrMoneyOfSalesOrders','SalePlate','','');"><strong class="font-lg">${todaySalesOrderNum }单</strong></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#4AB1E3;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">今日销售金额</strong><br>      
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('Today','SeeNumOrMoneyOfSalesOrders','SalePlate','','');"><strong class="font-lg">${todaySalesMoney }元</strong></a>  
											</div>
										</div>	   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#38B7AF;">
										<div class="row"> 
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">今日客户数</strong><br>    
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('Today','SeeTheTodayCustomer','SalePlate','','');"><strong class="font-lg">${todaySorderCustomerNum }位</strong></a>    
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#7074B9;">
										<div class="row">  
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">今日销售产品种类</strong><br>   
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('Today','SeeTypesOfSalesProducts','SalePlate','','');"><strong class="font-lg">${todayProductTypes }种</strong></a>    
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
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','SeeNumOrMoneyOfSalesOrders','SalePlate','','');">
												<span class="font-lg txt-color-red padding-5">${thisMonthSalesOrderNum }单</span>
</a>											
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">本月销售订单数</strong>
											</h5>
											<div class="padding-5"></div>
<p>
环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${hbClassa }"></i>${hbNuma }%</strong>
同比<strong class="txt-color-pink"> <i class="fa fa-arrow-${tbClassa }"></i>${tbNuma }%</strong>
</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','SeeNumOrMoneyOfSalesOrders','SalePlate','','');">
 												<span class="font-lg txt-color-red padding-5">${thisMonthSalesMoney }元</span> 
</a>	
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">本月销售金额</strong>
											</h5>
											<div class="padding-5"></div>
<p>
环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${hbClassb }"></i>${hbNumb }%</strong>
同比<strong class="txt-color-pink"> <i class="fa fa-arrow-${tbClassb }"></i>${tbNumb }%</strong>
</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','SeeTheNewCustomer','SalePlate','','');">
 												<span class="font-lg txt-color-red padding-5">${newCustomerNum }位</span> 
</a>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">本月新客户</strong>
											</h5>
											<div class="padding-5"></div>
<p>
环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${hbClassc }"></i>${hbNumc }%</strong>
同比<strong class="txt-color-pink"> <i class="fa fa-arrow-${tbClassc }"></i>${tbNumc }%</strong>
</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
<a class="myHoverLine" href="javascript:;" onClick="clickNumQueryGo('ThisMonthOT','SeeReturnGoods','SalePlate','','');">
 												<span class="font-lg txt-color-red padding-5">${returnOrederNum }单</span> 
 </a>												
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">本月退货订单数</strong>
											</h5>
											<div class="padding-5"></div>
<p>
环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${hbClassd }"></i>${hbNumd }%</strong>
同比<strong class="txt-color-pink"> <i class="fa fa-arrow-${tbClassd }"></i>${tbNumd }%</strong>
</p>
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
				<div class="row" id="sLoadSalesOrderTrendNum">  <!-- 慢加载页面 -->
				</div>
		</div>
	</div>			
		
		<div class="row">
		<div class="col-sm-12 col-md-12">
				<div class="row" id="sLoadSalesOrderTrendMoney">  <!-- 慢加载页面 -->
				</div>
		</div>
	</div>		
	
	<div class="row">
		<div class="col-sm-12 col-md-12">
			
				<div class="row">
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>产品销售数量TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding" id='sLoadSaleProductTopNum'>  <!-- 慢加载页面 -->
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>产品销售金额TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding" id='sLoadSaleProductTopMoney' >  <!-- 慢加载页面 -->
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>客户购买金额TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding" id='sLoadCustomerBuyTopMoney' >  <!-- 慢加载页面 -->
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>销售人员业绩TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding" id='sLoadSalesmanSellTopMoney' >  <!-- 慢加载页面 -->
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
			<h2>销售订单分析列表</h2>
			<a href="javascript:void(0);" class="btn bg-color-blueLight mytxt-color-wathet  txt-color-white btn-sm" id=""  onclick="exportExcel();" style="float:right;padding:2px 10px 2px;margin:4px 10px 3px 5px;" >导出</a>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							客户: <input type="text" value="" class="form-control" id="mytitle" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" onclick="saleAnalysisListaTable.ajax.reload();" >
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button type="button" class="btn btn-default" onclick="$('#mytitle').val('');saleAnalysisListaTable.ajax.reload();"  >
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="saleAnalysisListaTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
/** 数字点击跳转页面查询  **/   
function clickNumQueryGo(queryTime,queryWhat,returnPage,goToWhere,backupsStr) {
	var urlStr = '1';
	if(queryWhat == 'SeeNumOrMoneyOfSalesOrders'){
		urlStr = '${nvix}/nvixnt/nvixntSalesStatisticsClickAction!clickSeeNumOrMoneyOfSalesOrders.action';
	}else if(queryWhat == 'SeeReturnGoods'){
		urlStr = '${nvix}/nvixnt/nvixntSalesStatisticsClickAction!clickSeeReturnGoods.action';
	}else if(queryWhat == 'SeeTheNewCustomer'){
		urlStr = '${nvix}/nvixnt/nvixntSalesStatisticsClickAction!clickSeeTheNewCustomer.action';
	}else if(queryWhat == 'SeeTheTodayCustomer'){
		urlStr = '${nvix}/nvixnt/nvixntSalesStatisticsClickAction!clickSeeTheTodayCustomer.action';
	}else if(queryWhat == 'SeeTypesOfSalesProducts'){
		urlStr = '${nvix}/nvixnt/nvixntSalesStatisticsClickAction!clickSeeTypesOfSalesProducts.action';
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
</script>
<script type="text/javascript">
/** 慢加载：销售智能分析 > 销售仪表盘>销售人员业绩TOP10**/
function sLoadSalesmanSellTopMoney(){
	$.ajax({  
		url : '${nvix}/nvixnt/nvixntSalesStatisticsAction!sLoadSalesmanSellTopMoney.action',
		success : function(html) {
			$("#sLoadSalesmanSellTopMoney").html(html); 
		}
	});
}
/** 慢加载：销售智能分析 > 销售仪表盘>客户购买金额TOP10**/
function sLoadCustomerBuyTopMoney(){
	$.ajax({  
		url : '${nvix}/nvixnt/nvixntSalesStatisticsAction!sLoadCustomerBuyTopMoney.action',
		success : function(html) {
			$("#sLoadCustomerBuyTopMoney").html(html); 
		}
	});
}
/** 慢加载：销售智能分析 > 销售仪表盘>产品销售金额TOP10**/
function sLoadSaleProductTopMoney(){
	$.ajax({  
		url : '${nvix}/nvixnt/nvixntSalesStatisticsAction!sLoadSaleProductTopMoney.action',
		success : function(html) {
			$("#sLoadSaleProductTopMoney").html(html); 
		}
	});
}
/** 慢加载：销售智能分析 > 销售仪表盘>产品销售数量TOP10**/
function sLoadSaleProductTopNum(){
	$.ajax({  
		url : '${nvix}/nvixnt/nvixntSalesStatisticsAction!sLoadSaleProductTopNum.action',
		success : function(html) {
			$("#sLoadSaleProductTopNum").html(html); 
		}
	});
}
/** 慢加载：销售智能分析 > 销售仪表盘>最近30日销售订单趋势**/
function sLoadSalesOrderTrendNum(){
	$.ajax({  
		url : '${nvix}/nvixnt/nvixntSalesStatisticsAction!sLoadSalesOrderTrendNum.action',
		success : function(html) {
			$("#sLoadSalesOrderTrendNum").html(html); 
		}
	});
}
/** 慢加载：销售智能分析 > 销售仪表盘>最近30日销售金额趋势图**/
function sLoadSalesOrderTrendMoney(){
	$.ajax({  
		url : '${nvix}/nvixnt/nvixntSalesStatisticsAction!sLoadSalesOrderTrendMoney.action',
		success : function(html) {
			$("#sLoadSalesOrderTrendMoney").html(html); 
		}
	});
}
</script>
<script type="text/javascript">
$(document).ready(function() {
	pageSetUp();
	sLoadSalesOrderTrendNum();
	sLoadSalesOrderTrendMoney();
	sLoadSaleProductTopNum();
	sLoadSaleProductTopMoney();
	sLoadCustomerBuyTopMoney();
	sLoadSalesmanSellTopMoney();
})
</script>
<script type="text/javascript">
	pageSetUp();
	var saleAnalysisListaColumns = [ {
		"title" : "编号",
		"width" : "25%",
		"data" : function(data) {
			return "";
		}
		}, {
		"title" : "客户",
		"width" : "25%",
		"data" : function(data) {  
			return (data.khname != null ? data.khname : "" );
		}
		},{
		"title" : "购买总金额",
		"width" : "25%",
		"data" : function(data) {
			return (data.tmoney != null ? Number(data.tmoney).toFixed(2) : "" );
		}
		},{
		"title" : "最近购买时间",
		"width" : "25%",
		"data" : function(data) {
			return (data.mbilldate != null ? data.mbilldate : "" );
		}
		} ];
	var saleAnalysisListaTable = initDataTable("saleAnalysisListaTableId", "${nvix}/nvixnt/nvixntSalesStatisticsAction!goSaleStatisticsTable.action", saleAnalysisListaColumns, function(page, pageSize, orderField, orderBy) {
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
	form.action = "${nvix}/nvixnt/nvixntSalesStatisticsAction!outExcelToSaleStatisticsTable.action?mytitle="+mytitle;
	form.submit();
}
</script>
