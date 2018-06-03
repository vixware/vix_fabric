<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${nvix}/vixntcommon/base/js/plugin/daterangepicker/daterangepicker-bs3.css">
<script src="${nvix}/vixntcommon/base/js/plugin/daterangepicker/moment.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/daterangepicker/daterangepicker.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/bootstrap-suggest/bootstrap-suggest.min.js"></script>
<style>
	.mytxt-color-wathet { /* 目标按钮浅蓝色 */
	background-color:#428BCA !important;
	}
</style>
<div id="content">

			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售智能分析 <span>&gt; 产品销量排行</span>
					</h1>
					<input type="hidden" value="Today" id="queryTime">
					<input type="hidden" value="" id="queryRegional">
					<input type="hidden" value="" id="queryCustomer">
					<input type="hidden" value="" id="queryEmployee">
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
										<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltRegional'>全部地区</span></li>
										<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltCustomer'>全部客户</span></li>
										<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltEmployee'>全部业务员</span></li>
									</ul>
								</div>
							</div>
							
						</div>
						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="fa fa-globe"></i> 地域:
							</h5>
							<div class="row">
								<div class="input-group" style="width:97%;padding-left:15px;">
                                        <input type="text" class="form-control" id="sltRegionalSuggest">
                                        <div class="input-group-btn">
                                            <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                            </ul>
                                        </div>
                                    </div>
							</div>
							<script type="text/javascript">
					 var testdataBsSuggest = $("#sltRegionalSuggest").bsSuggest({
					     indexId: 1,
					     indexKey: 1,
					     idField: "id",                 
					     keyField: "",              
					     effectiveFields: ["word"], 
					     listStyle: {
					         "padding-top":0, "max-height": "123px", "max-width": "300px",
					         "overflow": "auto", "width": "auto",
					         "transition": "0.3s", "-webkit-transition": "0.3s", "-moz-transition": "0.3s", "-o-transition": "0.3s"       
					     },                            
					     data:${jsonObj}
					 });
					 /**选择供应商时**/
					 	$("input#sltRegionalSuggest").on("onSetSelectValue", function (event, result) {
						    var sID = result.id;
						    if(sID=='all'){
						    	sID="";
						    }
						    qCondition.RegionalID=sID;
						    var html=result.key;
							if($('#alreadySltRegional').length>0){
								$('#alreadySltRegional').text(html);
							}else{
								$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltRegional'>"+html+"</span></li>")
							}
							transmitCondition();
						});
					 </script>
						</div>

						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="fa fa-group"></i> 客户:
							</h5>
							<div class="row">
								<div class="input-group" style="width:97%;padding-left:15px;">
                                        <input type="text" class="form-control" id="sltCustomerSuggest">  
                                        <div class="input-group-btn">
                                            <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                            </ul>
                                        </div>
                                    </div>
							</div>
							<script type="text/javascript">
					 var testdataBsSuggest = $("#sltCustomerSuggest").bsSuggest({
					     indexId: 1,
					     indexKey: 1,
					     idField: "id",                 
					     keyField: "",              
					     effectiveFields: ["word"], 
					     listStyle: {
					         "padding-top":0, "max-height": "123px", "max-width": "300px",
					         "overflow": "auto", "width": "auto",
					         "transition": "0.3s", "-webkit-transition": "0.3s", "-moz-transition": "0.3s", "-o-transition": "0.3s"       
					     },                            
					     data:${jsonObjTwo}
					 });
					 /**选择客户时**/
					 	$("input#sltCustomerSuggest").on("onSetSelectValue", function (event, result) {
						    var sID = result.id;
						    if(sID=='all'){
						    	sID="";
						    }
						    qCondition.CustomerID=sID;
						    var html=result.key;
							if($('#alreadySltCustomer').length>0){
								$('#alreadySltCustomer').text(html);
							}else{
								$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltCustomer'>"+html+"</span></li>")
							}
							transmitCondition();
						});
					 </script>
						</div>
						
						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="fa fa-group"></i> 业务员:
							</h5>
							<div class="row">
								<div class="input-group" style="width:97%;padding-left:15px;">
                                        <input type="text" class="form-control" id="sltEmployeeSuggest">  
                                        <div class="input-group-btn">
                                            <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                            </ul>
                                        </div>
                                    </div>
							</div>
							<script type="text/javascript">
					 var testdataBsSuggest = $("#sltEmployeeSuggest").bsSuggest({
					     indexId: 1,
					     indexKey: 1,
					     idField: "id",                 
					     keyField: "",              
					     effectiveFields: ["word"], 
					     listStyle: {
					         "padding-top":0, "max-height": "123px", "max-width": "300px",
					         "overflow": "auto", "width": "auto",
					         "transition": "0.3s", "-webkit-transition": "0.3s", "-moz-transition": "0.3s", "-o-transition": "0.3s"       
					     },                            
					     data:${jsonObjThree}
					 });
					 /**选择业务员时**/
					 	$("input#sltEmployeeSuggest").on("onSetSelectValue", function (event, result) {
						    var sID = result.id;
						    if(sID=='all'){
						    	sID="";
						    }
						    qCondition.EmployeeID=sID;
						    var html=result.key;
							if($('#alreadySltEmployee').length>0){
								$('#alreadySltEmployee').text(html);
							}else{
								$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltEmployee'>"+html+"</span></li>")
							}
							transmitCondition();
						});
					 </script>
						</div>
						
					</div>
					<div class="col-sm-9">
							<div class="row">
								<div class="col-sm-12 col-md-12">
										<div class="row">
											<div class="col-sm-6 col-md-6">
												<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
														<h2>产品销售金额排行Top10</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewBmoney" style="height: 632px"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-6 col-md-6">
												<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>   
														<h2>产品销售数量排行Top10</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewCnum" style="height: 632px"></div>
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
function queryCondition(time,RegionalID,CustomerID,EmployeeID){
    this.time = time;
    this.RegionalID = RegionalID;
    this.CustomerID = CustomerID;
    this.EmployeeID = EmployeeID;
}
var qCondition = new queryCondition("Today", "","","");//不能删除，后面用
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
	$('#queryRegional').val(qCondition.RegionalID);
	$('#queryCustomer').val(qCondition.CustomerID);
	$('#queryEmployee').val(qCondition.EmployeeID);
	queryTopmoney();
	queryTopnum();
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
	function queryTopmoney() {
		$.ajax({    
			url: '${nvix}/nvixnt/nvixntSalesAnalysisAction!queryProductSalesTopView.action', 
			type: "POST",
			data: {queryTime:$('#queryTime').val(),controlSQL:'ProductSaleTopmoney',regionalID:$('#queryRegional').val(),customerAccountID:$('#queryCustomer').val(),employeeID:$('#queryEmployee').val(),topNum:'10'},
		    dataType: "json",
			success:function(json){
			var myColor = '#6EC16E';//控制bar柱条颜色和提示框小圆圈颜色  
			var myChart = echarts.init(document.getElementById('dataViewBmoney'));
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
	   	        data: ['产品销售金额']
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
	   	            name: '产品销售金额',
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
function queryTopnum() {
		$.ajax({    
			url: '${nvix}/nvixnt/nvixntSalesAnalysisAction!queryProductSalesTopView.action', 
			type: "POST",
			data: {queryTime:$('#queryTime').val(),controlSQL:'ProductSaleTopnum',regionalID:$('#queryRegional').val(),customerAccountID:$('#queryCustomer').val(),employeeID:$('#queryEmployee').val(),topNum:'10'},
		    dataType: "json",
			success:function(json){
			var myColor = '#FDB455';//控制bar柱条颜色和提示框小圆圈颜色  
			var myChart = echarts.init(document.getElementById('dataViewCnum'));
			var	option = {
	   	    title: {
	   	    	left: 'left',
	   	        text: '',
	   	        subtext: ''
	   	    },
	   		 tooltip : {
    	        trigger: 'axis',
    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColor+'"></span>{a0} : {c0}<br/>'
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
	   	        data: ['产品销售数量']
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
	   	            name: '产品销售数量',
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
			queryTopmoney();
			queryTopnum();
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