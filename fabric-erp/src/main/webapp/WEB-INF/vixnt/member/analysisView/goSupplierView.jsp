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
</style>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 供应商管理 <span>> 供应商统计报表 </span> <input type="hidden" value="${setSupplierID }" id="setSupplierID"  />
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
												<strong class="">本月新增供应商</strong><br>    
<a class="myHoverLine" href="javascript:;" onClick="controlSQLThree('Jfbr9D{SupplierBusinessUi{ThisMonthOT}}','PurchasePageUnu');"><strong class="font-lg">${supplierLSizeUac}家</strong></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#4AB1E3;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月新增采购商品 </strong><br>      
<a class="myHoverLine" href="javascript:;" onClick="controlSQLThree('JfUr8D{purchaseNewAddGsNumUiiu{ThisMonthOT}}','PurchasePageUnu');"><strong class="font-lg"><span id="">${NewAddGsNumUin}种</span></strong></a>  
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#38B7AF;">
										<div class="row">  
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月新增客户</strong><br>    
<a class="myHoverLine" href="javascript:;" onClick="controlSQLThree('JfYr7D{purchaseNewAddCustomUixu{ThisMonthOT}}','PurchasePageUnu');"><strong class="font-lg"><span id="">${NewAddCustomNumUnq}位</span></strong></a>  
											</div>
										</div>   
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<hr class="simple" style="border-color:#c0c0c0;">
									<div class="row">
										<div class="col-xs-3 col-sm-3 text-center">   
											<h5>   <!-- fromPage：PurchasePageUnu 返回 供应商统计报表 | purchaseGsNum 今日采购商品数量 -->
<a class="myHoverLine" href="javascript:;" onClick="controlSQLThree('JfUr8D{purchaseGsNum{Today}}','PurchasePageUnu');"><span class="font-lg txt-color-red padding-5">${sSaleGstodayNum }种</span></a> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日采购商品数量</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日采购<strong class="txt-color-pink"> ${sSaleGsyesteNum }种</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${sSaleGstodayIclass }"></i>${sSaleGstodayImNum }%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLThree('JfUr8D{purchaseGsNum{Today}}','PurchasePageUnu');"><span class="font-lg txt-color-red padding-5">${sSaleGstodayMey}元</span></a> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日采购商品金额</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日采购<strong class="txt-color-pink"> ${sSaleGsyesteMey}元</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${sSaleGMeytodayIclass}"></i>${sSaleGMeytodayImNum }%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLThree('JfHr5D{todayPOrderNum{Today}}','PurchasePageUnu');"><span class="font-lg txt-color-red padding-5">${sSOrdertodayNum}笔</span></a>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日采购订单数量</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日订单<strong class="txt-color-pink"> ${sSOrderyesteNum}笔</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${sSOrdertodayIclass}"></i>${sSOrdertodayImNum}%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLThree('JfYr7D{purchaseTodayCustomUicu{Today}}','PurchasePageUnu');"><span class="font-lg txt-color-red padding-5">${sSOCustomtodayNum}位</span></a>  
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日客户数量</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日客户<strong class="txt-color-pink"> ${sSOCustomyesteNum}位</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${sSOCustomtodayIclass}"></i>${sSOCustomtodayImNum}%</strong>
											</p>
										</div>
									</div>
									<hr class="simple" style="border-color:#c0c0c0;">
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
						<h2>最近30日采购订单视图</h2>
					</header>
					<div class="row">
						<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div>
								<div class="widget-body no-padding">
									<div id="dayItem" style="height: 400px"></div>
								</div>
							</div>
						</article>
					</div>	
				</div>
				
	
	<div class="row">
		<div class="col-sm-12 col-md-12">
			
				<div class="row">
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>最近30日采购商品数量Top10</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewA" style="height: 400px"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>最近30日采购商品金额Top10</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewB" style="height: 400px"></div>
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
								<h2>最近30日采购商品数量按客户排行Top10</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewE" style="height: 400px"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>最近30日采购商品金额按客户排行Top10</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewF" style="height: 400px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			
		</div>
	</div>
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>门店订单统计列表</h2> 
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							采购门店: <input type="text" value="" class="form-control" id="mynum" style="width: 130px;" placeholder="采购门店">
						</div>
						<div class="input-group">
							<input placeholder="开始时间" style="width: 130px;" id="startTimeB" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
						</div>
						<div class="input-group">
							<input placeholder="结束时间" style="width: 130px;" id="endTimeB" name="endCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endCreateTime'});"><i class="fa fa-calendar"></i></span>
						</div>
					
						<button  type="button" class="btn btn-info" id="searchButtonB">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#mytitle').val('');$('#mynum').val('');$('#startTimeB').val('');$('#endTimeB').val('');listGoodsInSqoqmTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="listGoodsInSqoqmTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>总部订单统计列表</h2> 
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							采购人: <input type="text" value="" class="form-control" id="mypeople" style="width: 130px;" placeholder="采购人">
						</div>
						<div class="input-group">
							<input placeholder="开始时间" style="width: 130px;" id="startTimeC" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
						</div>
						<div class="input-group">
							<input placeholder="结束时间" style="width: 130px;" id="endTimeC" name="endCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endCreateTime'});"><i class="fa fa-calendar"></i></span>
						</div>
					
						<button  type="button" class="btn btn-info" id="searchButtonC">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#mypeople').val('');$('#startTimeC').val('');$('#endTimeC').val('');listGoodsSthfmTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="listGoodsSthfmTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	
</div>
<script type="text/javascript">
function controlSQLThree(changeSQL,fromPage){
	$.ajax({  
		url : '${nvix}/nvixnt/vixntSupplierViewDataAction!startsWithForListPageSupplier.action',
		data: {changeSQL:changeSQL,fromPage:fromPage,setSupplierID:$("#setSupplierID").val()},  
		cache : false,
		success : function(html) {
			$("#mainContent").html(html); 
		}
	});
}
function judgeTimeLargeNow(timeStr) {//获取的时间thetime 格式应为  2016-5-28
	var timeStrDate = new Date(Date.parse(timeStr.replace(/-/g,"/")));
	var curDate = new Date();
	if(timeStrDate > curDate){
		return 1;/*return 1 时  alert("请重新选择开始时间，开始时间不能超过今天"); */
	}else{
		return 6;
	}
}
function padleft0(obj) {//补齐两位数的函数 
    return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);  
}
function controlJump() {
	var startTime = $("#startTimeA").val();
	var state = 0;
	if(startTime == null || startTime.length < 3 ){
		layer.alert("请选择时间!");
		state++;
	}
	if(judgeTimeLargeNow(startTime) == 1){
		state++;
		layer.alert("请重新选择时间,时间不能超过今天!");
	}
	if(state == 0){
		var queryTime = startTime;
		var act = "?queryTime="+queryTime;
		loadContent('drp_inventorystatistics','${nvix}/nvixnt/vixntStockViewDataAction!goStockView.action'+act);
	}
}
function controlJumpForclear() {//清空时
	loadContent('drp_inventorystatistics','${nvix}/nvixnt/vixntStockViewDataAction!goStockView.action');
}

function myNumToFixed2(objArr) {//我的把数组保留2位小数返回新数组
	var newArr =[];
	if(Array.isArray(objArr)){
		for(var x=0;x<objArr.length;x++){
			newArr[x]= parseFloat(objArr[x].toFixed(2)); 
		}
	}
    return newArr;  
}
function queryMethod_A(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntSupplierViewDataAction!supplierAnalysisViewA.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10',setSupplierID:$("#setSupplierID").val()},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewA'));
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        text: '',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}<br/>'
		    	        		/* +'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#FF9900"></span>{a1} : {c1}%<br/>'
		    	        		+'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#297029"></span>{a2} : {c2}%<br/>' */
		    	        ,  
		    	        axisPointer: {
		    	        	 type: 'shadow',
		    	            crossStyle: {
		    	                color: '#FFFF33'
		    	            }
		    	        }
		    	    },
			   	    legend: {
			   	        data: ['采购商品数量'] 
		    	  		,left: 'center'
			   	    },
			   	    grid: {
			   	        left: '3%',
			   	        right: '4%',
			   	        bottom: '3%',
			   	        containLabel: true
			   	    },
			   	    xAxis: [{
			   	        type: 'value',
			   	        boundaryGap: [0, 0.01]
			   	    }],
			   	    yAxis: [{
			   	        type: 'category',    
			   	        data:json.nameArr       //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '采购商品数量',
			   	            type: 'bar',
			   	         	data: myNumToFixed2(json.valueArr), //参考  data: [30,78,111,178],
			   	         itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#B6A2DE' 
		                                ];
		                                return colorList[params.dataIndex]
		                            },
		                        }
		    	            },
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}'
			   	                },
			   	                
			   	            }
			   	        }
			   	    ]
			   	};
				myChart.clear();
				myChart.setOption(option);
	 		    $(window).resize(myChart.resize);
	}});
}
function queryMethod_B(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntSupplierViewDataAction!supplierAnalysisViewB.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10',setSupplierID:$("#setSupplierID").val()},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewB'));
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        text: '',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}元<br/>'
		    	        		/* +'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#FF9900"></span>{a1} : {c1}%<br/>'
		    	        		+'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#297029"></span>{a2} : {c2}%<br/>' */
		    	        ,  
		    	        axisPointer: {
		    	        	 type: 'shadow',
		    	            crossStyle: {
		    	                color: '#FFFF33'
		    	            }
		    	        }
		    	    },
			   	    legend: {
			   	        data: ['采购商品金额'] 
		    	  		,left: 'center'
			   	    },
			   	    grid: {
			   	        left: '3%',
			   	        right: '4%',
			   	        bottom: '3%',
			   	        containLabel: true
			   	    },
			   	    xAxis: [{
			   	        type: 'value',
			   	        boundaryGap: [0, 0.01]
			   	    }],
			   	    yAxis: [{
			   	        type: 'category',    
			   	        data:json.nameArr       //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '采购商品金额',
			   	            type: 'bar',
			   	         	data: myNumToFixed2(json.valueArr), //参考  data: [30,78,111,178],
			   	         itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#4ECFD1' 
		                                ];
		                                return colorList[params.dataIndex]
		                            },
		                        }
		    	            },
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}元'
			   	                },
			   	                
			   	            }
			   	        }
			   	    ]
			   	};
				myChart.clear();
				myChart.setOption(option);
	 		    $(window).resize(myChart.resize);
	}});
}
function queryMethod_E(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntSupplierViewDataAction!supplierAnalysisViewE.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10',setSupplierID:$("#setSupplierID").val()},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewE'));
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        text: '',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#549FF0"></span>{a0} : {c0}<br/>'
		    	        		/* +'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#FF9900"></span>{a1} : {c1}%<br/>'
		    	        		+'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#297029"></span>{a2} : {c2}%<br/>' */
		    	        ,  
		    	        axisPointer: {
		    	        	 type: 'shadow',
		    	            crossStyle: {
		    	                color: '#FFFF33'
		    	            }
		    	        }
		    	    },
			   	    legend: {
			   	        data: ['客户采购商品数量']
		    	  		,left: 'center'
			   	    },
			   	    grid: {
			   	        left: '3%',
			   	        right: '4%',
			   	        bottom: '3%',
			   	        containLabel: true
			   	    },
			   	    xAxis: [{
			   	        type: 'value',
			   	        boundaryGap: [0, 0.01]
			   	    }],
			   	    yAxis: [{
			   	        type: 'category',    
			   	        data:json.nameArr       //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '客户采购商品数量',
			   	            type: 'bar',
			   	         	data: myNumToFixed2(json.valueArr), //参考  data: [30,78,111,178],
			   	         itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#549FF0' 
		                                ];
		                                return colorList[params.dataIndex]
		                            },
		                        }
		    	            },
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}'
			   	                },
			   	                
			   	            }
			   	        }
			   	    ]
			   	};
				myChart.clear();
				myChart.setOption(option);
	 		    $(window).resize(myChart.resize);
	}});
}
function queryMethod_F(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntSupplierViewDataAction!supplierAnalysisViewF.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10',setSupplierID:$("#setSupplierID").val()},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewF'));
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        text: '',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}元<br/>'
		    	        		/* +'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#FF9900"></span>{a1} : {c1}%<br/>'
		    	        		+'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#297029"></span>{a2} : {c2}%<br/>' */
		    	        ,  
		    	        axisPointer: {
		    	        	 type: 'shadow',
		    	            crossStyle: {
		    	                color: '#FFFF33'
		    	            }
		    	        }
		    	    },
			   	    legend: {
			   	        data: ['客户采购商品金额']
		    	  		,left: 'center'
			   	    },
			   	    grid: {
			   	        left: '3%',
			   	        right: '4%',
			   	        bottom: '3%',
			   	        containLabel: true
			   	    },
			   	    xAxis: [{
			   	        type: 'value',
			   	        boundaryGap: [0, 0.01]
			   	    }],
			   	    yAxis: [{
			   	        type: 'category',    
			   	        data:json.nameArr       //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '客户采购商品金额',
			   	            type: 'bar',
			   	         	data: myNumToFixed2(json.valueArr), //参考  data: [30,78,111,178],
			   	         itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#6BBE30' 
		                                ];
		                                return colorList[params.dataIndex]
		                            },
		                        }
		    	            },
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}元'
			   	                },
			   	                
			   	            }
			   	        }
			   	    ]
			   	};
				myChart.clear();
				myChart.setOption(option);
	 		    $(window).resize(myChart.resize);
	}});
}

function timeRetrieval(startTimeID,endTimeID) {//   timeRetrieval时间检索的方法（开始时间的id,结束时间的id）如果合格就返回  ‘1’
	 var state = 1;
	 var searchStartTime =  $('#'+startTimeID).val();  
	 var searchEndTime   =  $('#'+endTimeID).val();
	 var remind = "请同时选择开始时间和结束时间";
	 	if(searchStartTime.length<=3 && searchEndTime.length>3){
			alert(remind);
			state++;
		}
		if(searchStartTime.length>3 && searchEndTime.length<=3){
			alert(remind);
			state++;
		}
		if(judgeTimeLargeNow(searchStartTime) == 1){
			state++;
			alert("请重新选择开始时间,开始时间不能超过今天!");
		}
		if(judgeTimeLargeNow(searchEndTime) == 1){
			state++;
			alert("请重新选择结束时间,结束时间不能超过今天!");
		}
		var d_1 = searchStartTime;
		var d_2 = searchEndTime;
		var nowtime = new Date();  
        var year = nowtime.getFullYear();  
        var month = padleft0(nowtime.getMonth() + 1);  
        var day = padleft0(nowtime.getDate()); 
		var d_now = year + "-" + month + "-" + day ;
		var e_now = new Date(d_now).getTime();
		var e_1 = new Date(d_1).getTime();
		var e_2 = new Date(d_2).getTime();
		if(e_1>e_2){
			alert('对不起，请重新选择时间段，开始时间要在结束时间之前');
			state++;
		}
		return state;	  
}

function queryMethod_C(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntSupplierViewDataAction!supplierAnalysisViewG.action",   
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,setSupplierID:$("#setSupplierID").val()},
        dataType: "json",
 		success:function(json){
 			var myChart = echarts.init(document.getElementById('dayItem'));
			var myColours = ['#4ECFD1','#FF3399','#7900FF','#BD514B'];
			var option = {
		 	    title : {
		 	    	//text: '新增会员量视图',
		 	    },
		 	    tooltip : {
		 	        trigger: 'axis',
		 	         formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[0]+'"></span>{a0}: {c0}笔<br/>'+
		 	      	    '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[1]+'"></span>{a1}: {c1}笔 <br/>'+ 
		 	            '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[2]+'"></span>{a2}: {c2}笔 <br/>'+ 
		 	         	'',
		 	   		axisPointer: {
		    	        	 type: 'shadow',
		  	            crossStyle: {
		  	                color: '#FFFF33'
		  	            }
		  	        }
		 	    },
		 	    legend: {
		 	        data:['门店采购订单数','总部采购订单数','所有采购订单数']
		 	    },
		 	    grid: {
		 	        left: '1%',
		 	        right: '1%',
		 	        bottom: '3%',
		 	        containLabel: true
		 	    },
		 	    calculable : true,
		 	    xAxis : [
		 	        {
		 	            type : 'category',
		 	            data : json.timeStr//["17-01", "17-02", "17-03", "17-04", "17-05", "17-06", "17-07", "17-08", "17-09", "17-10", "17-11", "17-12"]
		 	        }
		 	    ],
		 	    yAxis : [
		 	        {
		 	            type : 'value',name: '数量/笔'
		 	            	,axisLabel: {                   
		        	             formatter: function (value, index) {
		        	                	var str = value.toString()
		        	                	  if( str.indexOf('.') >= 0 ){
		        	                		return null;
		        	                	}else{
		        	                		return value;
		        	                	} 
		    		             }                
		    		        }
		 	        }
		 	    ],
		 	    series : [
		 	        {
		 	            name:'门店采购订单数',
		 	            type:'line',
		 	            smooth:true,  
		 	            data:json.MD,//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10],
		 	            itemStyle :{
		 	            	normal:{
		 	            		color:''+myColours[0] 
		 	            	}
		 	            } 
		 	        },
		 	        {
		 	            name:'总部采购订单数',
		 	            type:'line',
		 	            smooth:true,  
		 	            data:json.ZB,//[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2],
		 	            itemStyle :{
		 	            	normal:{
		 	            		color:''+myColours[1]
		 	            	}
		 	            } 
		 	        },
		 	        {
		 	            name:'所有采购订单数',
		 	            type:'line',
		 	            smooth:true,
		 	            data:json.Total,//[3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3],
		 	            itemStyle :{
		 	            	normal:{
		 	            		color:''+myColours[2]
		 	            	}
		 	            } 
		 	        },
		 	    ]
		 	};
					myChart.clear();
					myChart.setOption(option);
				    $(window).resize(myChart.resize);
	}});
}
$(document).ready(function() {
	pageSetUp();
	queryMethod_A('-Lately-Day{30}','-Lately-Day{30}');
	queryMethod_B('-Lately-Day{30}','-Lately-Day{30}');
	queryMethod_E('-Lately-Day{30}','-Lately-Day{30}');
	queryMethod_F('-Lately-Day{30}','-Lately-Day{30}');
	queryMethod_C('-Lately-Day{30}','-Lately-Day{30}');
	
	$('#searchButtonB').click(function(){ 
		if(timeRetrieval('startTimeB','endTimeB')==1){
			listGoodsInSqoqmTable.ajax.reload();
		}
	});
	$('#searchButtonC').click(function(){ 
		if(timeRetrieval('startTimeC','endTimeC')==1){
			listGoodsSthfmTable.ajax.reload();
		}
	});
})
</script>

<script type="text/javascript">
	pageSetUp();
	var listGoodsInSqoqmColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "采购门店",   
	"data" : function(data) {  
		return data.mdname;  
	}
	},{
	"title" : "采购商品总数量",
	"data" : function(data) { 
		return (data.purchaseGoodsNum).toFixed(2);
	}
	},{
	"title" : "采购商品总金额",
	"data" : function(data) {
		return (data.purchaseGoodsMey).toFixed(2);
	}
	},{
	"title" : "最近交易时间",
	"data" : function(data) {
		return data.innerCREATETIME;
	}
	}/* , {
		"title" : "操作",
		"width" : "8%",
		"orderable" : false,
		"data" : function(data) {     
			var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.mdid + "','查看明细');\" title='查看明细'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			 return show;
		}
		}  去掉，代码先留着吧，去掉注释就可用 */  ];   
	var listGoodsInSqoqmTable = initDataTable("listGoodsInSqoqmTableId", "${nvix}/nvixnt/vixntSupplierViewDataAction!goGoodsQueryList.action", listGoodsInSqoqmColumns, function(page, pageSize, orderField, orderBy) {
		var setSupplierID = $("#setSupplierID").val();//id  
		setSupplierID = Url.encode(setSupplierID);
		var mytitle = "";
		mytitle = Url.encode(mytitle);
		var mynum = $("#mynum").val();
		var startTime = $("#startTimeB").val();
		var endTime = $("#endTimeB").val();
		startTime = Url.encode(startTime);
		endTime = Url.encode(endTime);
		mynum = Url.encode(mynum);
		var orderType = "00";//订单类型00,门店采购订单;11,总部采购订单; 
		return {	
		"page" : page,
		"pageSize" : pageSize,
		"setSupplierID" : setSupplierID,
		"mytitle" : mytitle,
		"startTime" : startTime,
		"endTime" : endTime,
		"orderType" : orderType,
		"mynum" : mynum
		};
	});
	
	function saveOrUpdate(id,title) { 
		var startTime = $("#startTimeB").val();
		var endTime = $("#endTimeB").val();
		var queryTime = startTime+endTime;
		var str = 'id='+ id +"&"+"queryTime="+queryTime+"&"+"setSupplierID="+$("#setSupplierID").val();  
		openWindowForShow('${nvix}/nvixnt/vixntSupplierViewDataAction!goPurchaseDetailsPage.action?' + str, title, 850, 450);
	}
</script>

<script type="text/javascript">
	var listGoodsSthfmColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "采购人",   
	"data" : function(data) {  
		return data.innerPname;  
	}
	},{
	"title" : "采购商品总数量",
	"data" : function(data) { 
		return (data.personGoodsNum).toFixed(2);
	}
	},{
	"title" : "采购商品总金额",
	"data" : function(data) {
		return (data.personGoodsMey).toFixed(2);
	}
	},{
	"title" : "最近交易时间",
	"data" : function(data) {
		return data.innerCREATETIME;
	}
	}/* , {
		"title" : "操作",
		"width" : "8%",
		"orderable" : false,
		"data" : function(data) {     
			var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"detailedB('" + data.psid + "','查看明细','" + data.innerPname + "');\" title='查看明细'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			 return show;
		}
		}  去掉，代码先留着吧，去掉注释就可用  */  ];   
	var listGoodsSthfmTable = initDataTable("listGoodsSthfmTableId", "${nvix}/nvixnt/vixntSupplierViewDataAction!goGoodsQueryListTwoPerson.action", listGoodsSthfmColumns, function(page, pageSize, orderField, orderBy) {
		var setSupplierID = $("#setSupplierID").val();//id  
		setSupplierID = Url.encode(setSupplierID);
		var mypeople = $("#mypeople").val();
		mypeople = Url.encode(mypeople);
		
		var startTime = $("#startTimeC").val();
		var endTime = $("#endTimeC").val();
		startTime = Url.encode(startTime);
		endTime = Url.encode(endTime);
		
		var orderType = "11";//订单类型00,门店采购订单;11,总部采购订单; 
		return {	
		"page" : page,
		"pageSize" : pageSize,
		"setSupplierID" : setSupplierID,
		"mypeople" : mypeople,
		"startTime" : startTime,
		"endTime" : endTime,
		"orderType" : orderType
		};
	});
	function detailedB(id,title,personName) {
		var startTime = $("#startTimeC").val();
		var endTime = $("#endTimeC").val();
		var queryTime = startTime+endTime;
		var str = 'id='+ id +"&"+"queryTime="+queryTime+"&"+"setSupplierID="+$("#setSupplierID").val()+"&"+"personName="+personName;  
		openWindowForShow('${nvix}/nvixnt/vixntSupplierViewDataAction!goPurchaseDetailsPageTwo.action?' + str, title, 850, 450);
	}
</script>
