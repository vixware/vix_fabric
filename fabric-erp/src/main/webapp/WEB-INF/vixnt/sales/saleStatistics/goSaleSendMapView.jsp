<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${nvix}/vixntcommon/base/js/plugin/daterangepicker/daterangepicker-bs3.css">
<script src="${nvix}/vixntcommon/base/js/plugin/daterangepicker/moment.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/daterangepicker/daterangepicker.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/china.js"></script>
<style>
	.mytxt-color-wathet { /* 目标按钮浅蓝色 */
	background-color:#428BCA !important;
	}
</style>
<div id="content">

			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售智能分析 <span>&gt; 货物流向分析</span>
					</h1>
					<input type="hidden" value="Today" id="queryTime">
				</div>
			</div>
			
			<section id="widget-grid" class="">

				<div class="row">
					<div class="col-sm-3">
						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> 选择时间
							</h5>
							
							<ul class="demo-btns tab-btn">
									<li>   
										<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs mytxt-color-wathet" id="Today"  onclick="clickTime('Today','今日');">今日</a>  
									</li>
									<li>
										<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs" id="ThisWeek" onclick="clickTime('ThisWeek','本周');" >本周</a>
									</li>
									<li>
										<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs" id="ThisMonthOT" onclick="clickTime('ThisMonthOT','本月');" >本月</a>
									</li>
									<li>
										<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs" id="ThisQuarterOT" onclick="clickTime('ThisQuarterOT','本季度');" >本季度</a>
									</li>
									<li>
										<a href="javascript:void(0);" class="btn bg-color-blueLight   txt-color-white btn-xs" id="ThisYearOT" onclick="clickTime('ThisYearOT','本年');" >本年</a>
									</li>
							</ul>
							
								<form class="form-horizontal">
				                 <fieldset>
				                  <div class="control-group">
				                    <div class="controls">
				                     <div class="input-prepend input-group">
				                       <input type="text"  name="reservation" id="reservation" class="form-control" value="${todayStr } - ${todayStr }" /><span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span> 
				                     </div>
				                    </div>
				                  </div>
				                 </fieldset>
			              	  </form>
						</div>
						
						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="fa fa-tags"></i> 当前选择条件:
							</h5>
							
							<div class="row">
								<div class="col-lg-12">
									<ul class="list-group no-margin chooseSort">    
										<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltTime'>今日</span></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-sm-9">
							<div class="row">
							<div class="col-sm-12 col-md-12">
								<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
									<header>
										<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
										<h2>销售订单数发货地图</h2>
									</header>
									<div>
										<div class="widget-body no-padding">
											<div id="dataViewB" style="height: 500px"></div>
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
														<h2>发货订单数排行TOP10</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewD" style="height: 250px"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-6 col-md-6">
												<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>   
														<h2>发货订单金额排行TOP10</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewC" style="height: 250px"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
								</div>
							</div>
							
					</div>
				</div>
				
			</section>
			

		</div>
    	
<script type="text/javascript">
/** 把页面查询条件封装成js对象 **/
function queryCondition(time){
    this.time = time;
}
var qCondition = new queryCondition("Today");//不能删除，后面用
/** 点击时间按钮 **/
function clickTime(id,timeString){
	 $('#Today').removeClass('mytxt-color-wathet');
	 $('#ThisWeek').removeClass('mytxt-color-wathet');
	 $('#ThisMonthOT').removeClass('mytxt-color-wathet');
	 $('#ThisQuarterOT').removeClass('mytxt-color-wathet');
	 $('#ThisYearOT').removeClass('mytxt-color-wathet');
     $('#'+id).addClass('mytxt-color-wathet');
     qCondition.time=id;
     transmitCondition();
     if($('#alreadySltTime').length>0){
		$('#alreadySltTime').text(timeString);
	 }else{
		$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltTime'>"+timeString+"</span></li>");
	 }
}
/** 传递查询参数 **/
function transmitCondition(){
	$('#queryTime').val(qCondition.time);
	queryTopMoney();
	queryTopNum();
	sendOrderNumInEChartsMap();
}
</script>
<script type="text/javascript">	
/** 发货订单数在echarts地图中显示sendOrderNumInEChartsMap() **/
function sendOrderNumInEChartsMap() {
	$.ajax({    
		url: '${nvix}/nvixnt/nvixntSalesAnalysisAction!sendOrderNumInEChartsMap.action', 
		type: "POST",
		data: {queryTime:$('#queryTime').val(),controlSQL:'sendOrderNumInEChartsMap'},
	    dataType: "json",
		success:function(json){
		var valueArr =  json.numberResult;//多少个
	     var nameArr =  json.stringResult;//谁
	     var seriesArray = [];  
		 var map ={}; 
		 for(var i=0 ;i<valueArr.length;i++){
			map.value = valueArr[i];
		    map.name = nameArr[i]; 
		    seriesArray[i]=map;
		    map ={};
	 	 }
		var myChart = echarts.init(document.getElementById('dataViewB'));
		var myMax = json.myMax;
		var option = {
				title : {
					text : '销售订单数发货地图',
					left : 'center'
				},
				tooltip : {
					trigger : 'item'
				},
				legend : {
					orient : 'vertical',
					left : 'left',
					data : [ '订单数' ]
				},
				visualMap : {
					min : 0,
					max : myMax,
					splitNumber : 10,
					color : [ '#FF1111', '#EFEF21', '#000000',
							'#eac736', '#F709F7', '#50a3ba', '#0000FF',
							'#5E6BA2', '#00FFCC' ],
					textStyle : {
						color : '#000000'
					},
					left : 'left',
					top : 'bottom',
					//    text: ['高','低'],           // 文本，默认为数值文本
					calculable : true,
					type : 'piecewise'
				},
				toolbox : {
					show : true,
					orient : 'vertical',
					left : 'right',
					top : 'center',
					feature : {
						dataView : {
							readOnly : false
						},
						restore : {},
						saveAsImage : {}
					}
				},
				series : [ {
					name : '订单数',
					type : 'map',
					mapType : 'china',
					roam : false,
					label : {
						normal : {
							show : true
						},
						emphasis : {
							show : true
						}
					},
					data : seriesArray
					/*   [    //示例数据
					{
						name : '重庆',
						value : 8
					},
					{
						name : '天津',
						value : 4
					},
					{
						name : '北京',
						value : 99999
					}, 
					 
					{
						name : '上海',
						value : 6
					}, 
					 
					{
						name : '河北',
						value : 10
					}, {
						name : '河南',
						value : 14
					}, {
						name : '云南',
						value : 101
					}, {
						name : '辽宁',
						value : 101
					}, {
						name : '黑龙江',
						value : 101
					}, {
						name : '湖南',
						value : 101
					}, {
						name : '安徽',
						value : 101
					}, {
						name : '山东',
						value : 101
					}, {
						name : '新疆',
						value : 101
					}, {
						name : '江苏',
						value : 101
					}, {
						name : '浙江',
						value : 101
					}, {
						name : '江西',
						value : 101
					}, {
						name : '湖北',
						value : 101
					}, {
						name : '广西',
						value : 101
					}, {
						name : '甘肃',
						value : 101
					}, {
						name : '山西',
						value : 101
					}, {
						name : '内蒙古',
						value : 101
					}, {
						name : '陕西',
						value : 101
					}, {
						name : '吉林',
						value : 101
					}, {
						name : '福建',
						value : 101
					}, {
						name : '贵州',
						value : 101
					}, {
						name : '广东',
						value : 101
					}, {
						name : '青海',
						value : 101
					}, {
						name : '西藏',
						value : 101
					}, {
						name : '四川',
						value : 1101
					}, {
						name : '宁夏',
						value : 111101
					}, {
						name : '海南',
						value : 101
					}, {
						name : '台湾',
						value : 101
					}, {
						name : '香港',
						value : 101
					}, {
						name : '澳门',
						value : 101
					} ] */
				} ]
			};
			myChart.clear();
			myChart.setOption(option);
		    $(window).resize(myChart.resize);
	}});
}
</script>
<script type="text/javascript">
/** 用js把数组的‘数字元’转成‘万元’后返回新数组  **/
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
	function queryTopMoney() {
		$.ajax({    
			url: '${nvix}/nvixnt/nvixntSalesAnalysisAction!querySendMapTopView.action', 
			type: "POST",
			data: {queryTime:$('#queryTime').val(),controlSQL:'OrderMapMoneyBar',topNum:'10'},
		    dataType: "json",
			success:function(json){
			var myColor = '#6EC16E';//控制bar柱条颜色和提示框小圆圈颜色  
			var myChart = echarts.init(document.getElementById('dataViewC'));
			var	option = {
	   	    title: {
	   	    	left: 'left',
	   	        text: '',
	   	        subtext: ''
	   	    },
	   		 tooltip : {
    	        trigger: 'axis',
    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColor+'"></span>{a0} : {c0}万元<br/>'
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
	   	        data: ['订单金额合计']
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
	   	     	data:json.nameArr  //参考  data:['','','','','','','','','大众1号车轮','x反光镜']
	   	    }],
	   	    series: [
	   	        {
	   	            name: '订单金额合计',
	   	            type: 'bar',
	   	         	data: myNumArrToMillionYuan(json.valueArr),//参考  data: [30,78,111,178],
	   	         	itemStyle: {
                        normal: {
                            color: function(params) {
                                var colorList = [
                                  ''+myColor 
                                ];
                                return colorList[params.dataIndex]
                            },
                        }
    	            },
    	            
	   	            label: {
	   	                normal: {
	   	                    show: true,
	   	                    position: 'right',
	   	                    formatter: '{c}万元'
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
</script>		
<script type="text/javascript">
function queryTopNum() {
		$.ajax({    
			url: '${nvix}/nvixnt/nvixntSalesAnalysisAction!querySendMapTopView.action', 
			type: "POST",
			data: {queryTime:$('#queryTime').val(),controlSQL:'OrderMapNumBar',topNum:'10'},
		    dataType: "json",
			success:function(json){
			var myColor = '#FDB455';//控制bar柱条颜色和提示框小圆圈颜色  
			var myChart = echarts.init(document.getElementById('dataViewD'));
			var	option = {
	   	    title: {
	   	    	left: 'left',
	   	        text: '',
	   	        subtext: ''
	   	    },
	   		 tooltip : {
    	        trigger: 'axis',
    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColor+'"></span>{a0} : {c0}单<br/>'
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
	   	        data: ['发货订单数']
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
	   	     	data:json.nameArr  //参考  data:['','','','','','','','','大众1号车轮','x反光镜']
	   	    }],
	   	    series: [
	   	        {
	   	            name: '发货订单数',
	   	            type: 'bar',
	   	         	data: json.valueArr,//参考  data: [30,78,111,178],
	   	         	itemStyle: {
                        normal: {
                            color: function(params) {
                                var colorList = [
                                  ''+myColor 
                                ];
                                return colorList[params.dataIndex]
                            },
                        }
    	            },
    	            
	   	            label: {
	   	                normal: {
	   	                    show: true,
	   	                    position: 'right',
	   	                    formatter: '{c}单'
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
</script>				
<script type="text/javascript">
function choice() {//页面选择条件 
	$('#startdate').datepicker({
		dateFormat : 'yy-mm-dd',
		prevText : '<i class="fa fa-chevron-left"></i>',
		nextText : '<i class="fa fa-chevron-right"></i>',
		onSelect : function(selectedDate) {
			$('#finishdate').datepicker('option', 'minDate', selectedDate);
		}
	});
	$(".chooseSort").on("click","li span",function(){
		$(this).parents("li").remove();
	})
}
		$(document).ready(function() {
			pageSetUp();//初始化小图
			choice();
			sendOrderNumInEChartsMap();
			queryTopMoney();
			queryTopNum();
		});
</script>
<script type="text/javascript">
        $(document).ready(function() {
        	/** 点击自定义时间时 **/
            $('#reservation').daterangepicker(null, function(start, end, label) {});
            /** 自定义时间被确认时 **/
       		$('#reservation').on('apply.daterangepicker', function(ev, picker) {
	       	    var startstr = picker.startDate.format('YYYY-MM-DD');
	       	    var endstr = picker.endDate.format('YYYY-MM-DD');
	       	    var strTime = startstr+endstr;
	       	    qCondition.time=strTime;
	       	    transmitCondition();
	       	     var timeString = startstr+" - "+endstr;
		       	 if($('#alreadySltTime').length>0){
		     		$('#alreadySltTime').text(timeString);
		     	 }else{
		     		$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltTime'>"+timeString+"</span></li>");
		     	 }
       		});
        });
</script>