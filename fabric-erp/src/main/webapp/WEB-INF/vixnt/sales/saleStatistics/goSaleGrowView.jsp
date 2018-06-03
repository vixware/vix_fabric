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
						<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售智能分析 <span>&gt; 销售增长分析</span>
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
										<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs mytxt-color-wathet" id="Today"  onclick="clickTime('Today','今日','Today');">今日</a>  
									</li>
									<li>
										<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs" id="ThisWeek" onclick="clickTime('ThisWeek','本周','ThisWeek');" >本周</a>
									</li>
									<li>
										<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs" id="ThisMonthOT" onclick="clickTime('ThisMonthAll','本月','ThisMonthOT');" >本月</a>
									</li>
									<li>
										<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs" id="ThisQuarterOT" onclick="clickTime('ThisQuarterAll','本季度','ThisQuarterOT');" >本季度</a>
									</li>
									<li>
										<a href="javascript:void(0);" class="btn bg-color-blueLight   txt-color-white btn-xs" id="ThisYearOT" onclick="clickTime('ThisYearAll','本年','ThisYearOT');" >本年</a>
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
								<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
									<header>
										<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
										<h2>销售订单数趋势图</h2>
									</header>
									<div>
										<div class="widget-body no-padding">
											<div id="dataViewCTrendNum" style="height: 280px"></div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
									<header>
										<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
										<h2>销售金额趋势图</h2>
									</header>
									<div>
										<div class="widget-body no-padding">
											<div id="dataViewCTrendMoney" style="height: 280px"></div>
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
function clickTime(id,timeString,addClassID){
	 $('#Today').removeClass('mytxt-color-wathet');
	 $('#ThisWeek').removeClass('mytxt-color-wathet');
	 $('#ThisMonthOT').removeClass('mytxt-color-wathet');
	 $('#ThisQuarterOT').removeClass('mytxt-color-wathet');
	 $('#ThisYearOT').removeClass('mytxt-color-wathet');
     $('#'+addClassID).addClass('mytxt-color-wathet');
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
	querySalesOrderTrendNum();
	querySalesOrderTrendMoney();
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
function querySalesOrderTrendNum() {  
	$.ajax({    
		url: '${nvix}/nvixnt/nvixntSalesAnalysisAction!querySalesOrderTrend.action', 
	 	type: "POST",
	 	data: {queryTime:$('#queryTime').val(),controlSQL:'querSalesOrderTrendNum',regionalID:$('#queryRegional').val(),customerAccountID:$('#queryCustomer').val(),employeeID:$('#queryEmployee').val()},         
	    dataType: "json",
			success:function(json){
			var myChart = echarts.init(document.getElementById('dataViewCTrendNum'));
			var myColors = ['#7cb5ec', '#FF6666', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
			var option = {
 		    	    title : {
 		    	       // text: '最近30日会员消费方式分析',
 		    	    },
 		    	    tooltip : {
 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}单<br/>'+
'',
 		    	   		axisPointer: {
 		       	        	 type: 'shadow',
 		     	            crossStyle: {
 		     	                color: '#FFFF33'
 		     	            }
 		     	        }
 		    	    },
 		    	    legend: {
 		    	        data:['销售订单']
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
 		    	            data : json.timeStr //["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
 		    	        }
 		    	    ],
 		    	    yAxis : [
 		    	        {
 		    	            type : 'value',name: '单位/单'
 		    	        }
 		    	    ],
 		    	   series : [
		    	        {
		    	            name:'销售订单',
		    	            type:'bar',
		    	            data: json.valueArr,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		    	            itemStyle :{
		    	            	normal:{
		    	            		color:''+myColors[0] 
		    	            	}
		    	            } 
		    	        },
		    	        {
 		    	            name:'销售订单',
 		    	            type:'line',
 		    	            smooth:true,
 		    	            data: json.valueArr,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
 		    	            itemStyle :{
 		    	            	normal:{
 		    	            		color:''+myColors[1] 
 		    	            	}
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
function querySalesOrderTrendMoney() {  
	$.ajax({    
		url: '${nvix}/nvixnt/nvixntSalesAnalysisAction!querySalesOrderTrend.action', 
	 	type: "POST",
	 	data: {queryTime:$('#queryTime').val(),controlSQL:'querSalesOrderTrendMoney',regionalID:$('#queryRegional').val(),customerAccountID:$('#queryCustomer').val(),employeeID:$('#queryEmployee').val()},         
	    dataType: "json",
			success:function(json){
			var myChart = echarts.init(document.getElementById('dataViewCTrendMoney'));
			var myColors = ['#548235', '#FF6666', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
			var option = {
 		    	    title : {
 		    	       // text: '最近30日会员消费方式分析',
 		    	    },
 		    	    tooltip : {
 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}万元<br/>'+
'',
 		    	   		axisPointer: {
 		       	        	 type: 'shadow',
 		     	            crossStyle: {
 		     	                color: '#FFFF33'
 		     	            }
 		     	        }
 		    	    },
 		    	    legend: {
 		    	        data:['销售金额']
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
 		    	            data : json.timeStr //["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
 		    	        }
 		    	    ],
 		    	    yAxis : [
 		    	        {
 		    	            type : 'value',name: '单位/万元'
 		    	        }
 		    	    ],
 		    	   series : [
		    	        {
		    	            name:'销售金额',
		    	            type:'bar',
		    	            data: myNumArrToMillionYuan(json.valueArr),   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		    	            itemStyle :{
		    	            	normal:{
		    	            		color:''+myColors[0] 
		    	            	}
		    	            } 
		    	        },
		    	        {
 		    	            name:'销售金额',
 		    	            type:'line',
 		    	            smooth:true,
 		    	            data: myNumArrToMillionYuan(json.valueArr),   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
 		    	            itemStyle :{
 		    	            	normal:{
 		    	            		color:''+myColors[1] 
 		    	            	}
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
			querySalesOrderTrendNum();
			querySalesOrderTrendMoney();
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
