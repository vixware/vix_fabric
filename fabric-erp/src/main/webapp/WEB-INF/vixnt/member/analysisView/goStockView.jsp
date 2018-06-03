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
				<i class="fa-fw fa fa-home"></i> 门店管理 <span>> 门店数据统计 </span> <span>> 门店库存报表</span>
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
												<strong class="">现存商品量</strong><br>     <!--  storeStockPageSvy 表示  门店库存报表  -->
<a class="myHoverLine" href="javascript:;" onClick="controlSQLTwo('JaSqExistGoodS{}','storeStockPageSvy');"><strong class="font-lg">${nowStockGoodsNum}种</strong></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#4AB1E3;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月过期现存商品 </strong><br>      
<a class="myHoverLine" href="javascript:;" onClick="controlSQLTwo('JaSdGoodScv{OverdueExist{ThisMonthOT}}','storeStockPageSvy');"><strong class="font-lg"><span id="">${invalidGoodsNum}种</span></strong></a>  
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#38B7AF;">
										<div class="row">  <!-- 库存不足预警商品:进货-出货=余数， 余数<20 (暂且这么定义) -->
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">库存不足预警商品</strong><br>    
<a class="myHoverLine" href="javascript:;" onClick="controlSQLTwo('JaSdGoodScv{notEnoughGoods{20}}','storeStockPageSvy');"><strong class="font-lg"><span id="">${notEnoughGoodsNum}种</span></strong></a>   
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#7074B9;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">库存积压预警商品</strong><br>    
<a class="myHoverLine" href="javascript:;" onClick="controlSQLTwo('JaSdGoodScv{tooMuchGoods{200}}','storeStockPageSvy');"><strong class="font-lg"><span id="">${tooMuchGoodsNum}种</span></strong></a>  
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
										<button type="button" class="btn btn-info" onClick="controlJump();">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button type="button" class="btn btn-default"  onclick="$('#startTimeA').val('');controlJumpForclear();">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
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
										<div class="col-xs-6 col-sm-6 text-center">
											<h5>
												<span class="font-lg txt-color-red padding-5">${whGINtodayNum }种</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">${titleDate}入库商品总数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日入库<strong class="txt-color-pink"> ${whGINyesteNum }种</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${whGINtodayIclass }"></i>${whGINtodayImNum }%</strong>
											</p>
										</div>
										<div class="col-xs-6 col-sm-6 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
 												<span class="font-lg txt-color-red padding-5">${whGOUTtodayNum }种</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">${titleDate}出库商品总数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日出库<strong class="txt-color-pink"> ${whGOUTyesteNum}种</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${whGOUTtodayIclass }"></i>${whGOUTtodayImNum }%</strong>
											</p>
										</div>
										<!-- <div class="col-xs-4 col-sm-4 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
 												<span class="font-lg txt-color-red padding-5"><span id="d03Tnum">3</span>件</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日盘库商品损耗</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日损耗<strong class="txt-color-pink"> 0件</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-down" style="color:#05CD15;"></i>0.00%</strong>
											</p>
										</div> -->
									</div>
									<hr class="simple" style="border-color:#c0c0c0;">
								</div>
							</div>
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
								<h2>最近30日商品入库数量Top10</h2>
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
								<h2>最近30日商品入库金额Top10</h2>
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
								<h2>最近30日商品出库数量Top10</h2>
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
								<h2>最近30日商品出库金额Top10</h2>
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
			<h2>商品入库列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							商品名称: <input type="text" value="" class="form-control" id="mytitle" style="width: 130px;" placeholder="请输入商品名称">
						</div>
						<div class="form-group">
							商品编码: <input type="text" value="" class="form-control" id="mynum" style="width: 130px;" placeholder="请输入商品编码">
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
			<h2>商品出库列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							商品名称: <input type="text" value="" class="form-control" id="mytitleCK" style="width: 130px;" placeholder="请输入商品名称">
						</div>
						<div class="form-group">
							商品编码: <input type="text" value="" class="form-control" id="mynumCK" style="width: 130px;" placeholder="请输入商品编码">
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
						<button onclick="$('#mytitleCK').val('');$('#mynumCK').val('');$('#startTimeC').val('');$('#endTimeC').val('');listGoodsOutSaocmTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="listGoodsOutSaocmTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>现存商品列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							商品名称: <input type="text" value="" class="form-control" id="mytitleXC" style="width: 130px;" placeholder="请输入商品名称">
						</div>
						<div class="form-group">
							商品编码: <input type="text" value="" class="form-control" id="mynumXC" style="width: 130px;" placeholder="请输入商品编码">
						</div>
						<button onclick="listGoodsExistScodmTable.ajax.reload();"  type="button" class="btn btn-info" id="searchButtonD">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#mytitleXC').val('');$('#mynumXC').val('');listGoodsExistScodmTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="listGoodsExistScodmTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	
</div>
<script type="text/javascript">
function controlSQLTwo(changeSQL,fromPage){
	$.ajax({  
		url : '${nvix}/nvixnt/vixntMemberManageDataAction!startsWithForListPage.action',
		data: {changeSQL:changeSQL,fromPage:fromPage},  
		cache : false,//<a class="myHoverLine" href="javascript:;" onClick="controlSQLTwo('JaeSqKg{ExistGoodS}','storeStockPageSvy');">
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
		url: "${nvix}/nvixnt/vixntStockViewDataAction!stockAnalysisViewA.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
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
			   	        data: ['商品入库数量']
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
			   	            name: '商品入库数量',
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
		url: "${nvix}/nvixnt/vixntStockViewDataAction!stockAnalysisViewB.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
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
			   	        data: ['商品入库金额']
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
			   	            name: '商品入库金额',
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
		url: "${nvix}/nvixnt/vixntStockViewDataAction!stockAnalysisViewE.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
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
			   	        data: ['商品出库数量']
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
			   	            name: '商品出库数量',
			   	            type: 'bar',
			   	         	data: myNumToFixed2(json.valueArr), //参考  data: [30,78,111,178],
			   	         itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#003399' 
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
		url: "${nvix}/nvixnt/vixntStockViewDataAction!stockAnalysisViewF.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
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
			   	        data: ['商品出库金额']
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
			   	            name: '商品出库金额',
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
$(document).ready(function() {
	pageSetUp();
	queryMethod_A('-Lately-Day{30}','-Lately-Day{30}');
	queryMethod_B('-Lately-Day{30}','-Lately-Day{30}');
	queryMethod_E('-Lately-Day{30}','-Lately-Day{30}');
	queryMethod_F('-Lately-Day{30}','-Lately-Day{30}');
	$('#searchButtonB').click(function(){ //商品入库列表检索
		if(timeRetrieval('startTimeB','endTimeB')==1){
			listGoodsInSqoqmTable.ajax.reload();
		}
	});
	$('#searchButtonC').click(function(){ //商品出库列表检索
		if(timeRetrieval('startTimeC','endTimeC')==1){
			listGoodsOutSaocmTable.ajax.reload();
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
	}, {
	"title" : "商品编码",
	"data" : function(data) {
		return data.itemcode;
	}
	},{
	"title" : "商品名称",   
	"data" : function(data) {
		return data.itemname;  
	}
	},{
	"title" : "规格型号",
	"data" : function(data) {
		return data.specification;
	}
	},{
	"title" : "单价",
	"data" : function(data) {
		return data.unitcost;
	}
	},{
	"title" : "数量",
	"data" : function(data) {
		return data.quantityNum;
	}
	},{
	"title" : "单位",
	"data" : function(data) {
	  return data.measureUnitName;
	}
	},{
	"title" : "总价",
	"data" : function(data) {
		return data.totalPrice;
	}
	},{
	"title" : "最近入库时间",    
	"data" : function(data) {
		return data.totime;
	}
	} ]; 
	var listGoodsInSqoqmTable = initDataTable("listGoodsInSqoqmTableId", "${nvix}/nvixnt/vixntStockViewDataAction!goGoodsQueryList.action", listGoodsInSqoqmColumns, function(page, pageSize, orderField, orderBy) {
		var myflag = "In";//标记查入库，还是‘出库’
		myflag = Url.encode(myflag);
		var mytitle = $("#mytitle").val();
		mytitle = Url.encode(mytitle);
		var mynum = $("#mynum").val();
		var startTime = $("#startTimeB").val();
		var endTime = $("#endTimeB").val();
		startTime = Url.encode(startTime);
		endTime = Url.encode(endTime);
		mynum = Url.encode(mynum);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"mytitle" : mytitle,
		"startTime" : startTime,
		"endTime" : endTime,
		"myflag" : myflag,
		"mynum" : mynum
		};
	});
</script>

<script type="text/javascript">
	var listGoodsOutSaocmColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "商品编码",
	"data" : function(data) {
		return data.itemcode;
	}
	},{
	"title" : "商品名称",   
	"data" : function(data) {
		return data.itemname;  
	}
	},{
	"title" : "规格型号",
	"data" : function(data) {
		return data.specification;
	}
	},{
	"title" : "单价",
	"data" : function(data) {
		return data.unitcost;
	}
	},{
	"title" : "数量",
	"data" : function(data) {
		return data.quantityNum;
	}
	},{
	"title" : "单位",
	"data" : function(data) {
	  return data.measureUnitName;
	}
	},{
	"title" : "总价",
	"data" : function(data) {
		return data.totalPrice;
	}
	},{
	"title" : "最近出库时间",    
	"data" : function(data) {
		return data.totime;
	}
	} ]; 
	var listGoodsOutSaocmTable = initDataTable("listGoodsOutSaocmTableId", "${nvix}/nvixnt/vixntStockViewDataAction!goGoodsQueryListOut.action", listGoodsOutSaocmColumns, function(page, pageSize, orderField, orderBy) {
		var myflag = "Out";//标记查入库，还是‘出库’
		myflag = Url.encode(myflag);
		var mytitle = $("#mytitleCK").val();
		mytitle = Url.encode(mytitle);
		var mynum = $("#mynumCK").val();
		var startTime = $("#startTimeC").val();
		var endTime = $("#endTimeC").val();
		startTime = Url.encode(startTime);
		endTime = Url.encode(endTime);
		mynum = Url.encode(mynum);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"mytitle" : mytitle,
		"startTime" : startTime,
		"endTime" : endTime,
		"myflag" : myflag,
		"mynum" : mynum
		};
	});
</script>
<script type="text/javascript">
	var listGoodsExistScodmColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "商品编码",
	"data" : function(data) {
		return data.itemcode;
	}
	},{
	"title" : "商品名称",   
	"data" : function(data) {
		return data.itemname;  
	}
	},{
	"title" : "规格型号",
	"data" : function(data) {
		return data.specification;
	}
	},{
	"title" : "单价",
	"data" : function(data) {
		return data.unitcost;
	}
	},{
	"title" : "数量",
	"data" : function(data) {
		return data.quantityNum;
	}
	},{
	"title" : "单位",
	"data" : function(data) {
	  return data.measureUnitName;
	}
	},{
	"title" : "总价",
	"data" : function(data) {
		return data.totalPrice;  
	}
	},{
	"title" : "最近入库时间",    
	"data" : function(data) {
		return data.totimeIn;
	}
	},{
	"title" : "最近出库时间",    
	"data" : function(data) {
		return data.totimeOut;
	}
	} ]; 
	var listGoodsExistScodmTable = initDataTable("listGoodsExistScodmTableId", "${nvix}/nvixnt/vixntStockViewDataAction!goGoodsQueryListExist.action", listGoodsExistScodmColumns, function(page, pageSize, orderField, orderBy) {
		var mytitle = $("#mytitleXC").val();//Exist 存在的
		mytitle = Url.encode(mytitle);
		var mynum = $("#mynumXC").val();
		mynum = Url.encode(mynum);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"mytitle" : mytitle,
		"mynum" : mynum
		};
	});
</script>