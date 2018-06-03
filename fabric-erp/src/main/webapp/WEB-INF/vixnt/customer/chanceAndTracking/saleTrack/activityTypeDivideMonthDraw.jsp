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
				<i class="fa fa-lg fa-fw fa-user"></i> 客户关系管理 <span>> 销售跟踪 </span><span>> 销售活动类型/月份分布</span>
			</h1>
			<input type="hidden" value="ThisQuarterOT" id="queryTime">
			<input type="hidden" value="ActivityType!all" id="queryType">  
		</div>
	</div>
	<section id="widget-grid" class="">
		<div class="row">
			<div class="col-sm-3">
				<div class="well padding-10">
					<h5 class="margin-top-0">
						<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> 选择时间:
					</h5>
					<ul class="demo-btns tab-btn">
						<li>
							<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs mytxt-color-wathet" id="ThisQuarterOT" onclick="clickTime('ThisQuarterOT','本季度');" >本季度</a>
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
			                       <input type="text"  name="reservation" id="reservation" class="form-control" value="${dateStrFront } - ${dateStrAfter }" /><span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span> 
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
								<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltTime'>本季度</span></li>
								<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltSupplier'>统计全部状态的销售机会</span></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="well padding-10">
					<h5 class="margin-top-0">
						<i class="fa fa-fw fa-list-ol"></i> 统计限制条件:
					</h5>
					<div class="row">
						<div class="input-group" style="width:97%;padding-left:15px;">
							<input type="text" class="form-control" id="sltSupplier">
							<div class="input-group-btn">
	           					<button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
	                   				<span class="caret"></span>
	                			</button>
	                			<ul class="dropdown-menu dropdown-menu-right" role="menu"></ul>
							</div>
	    				</div>
					</div>
					<script type="text/javascript">
						var testdataBsSuggest = $("#sltSupplier").bsSuggest({
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
						     data:${jsonBsSuggestJava}
						     /* {
						    	 "value" : [
									{"id" : "ActivityType!all","word" : "统计全部状态的销售机会"},
									{"id" : "ActivityType!all","word" : "仅统计'状态=跟踪中'的销售机会"},
									{"id" : "ActivityType!all","word" : "仅统计'状态=放弃'的销售机会"},
						   		]
						     } */
						 });
						 /**选择供应商时**/
					 	$("input#sltSupplier").on("onSetSelectValue", function (event, result) {
						    var sID = result.id;
						    qCondition.type=sID;
						    var selectTypeStr=result.key;
							goCustomerAccountDistribute(sID,selectTypeStr);
						});
			 		</script>
				</div>
				
			</div>
			<div class="col-sm-9">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="row">
							<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="jarviswidget" data-widget-editbutton="false" role="widget">
									<header>
										<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i>
										</span>
										<h2>销售活动类型/月份分布(按销售活动的'活动日期'统计)</h2>
									</header>
									<div id="customerAccountDistributeId">
										<div class="widget-body no-padding" id="viewABC" style="height: 600px"></div>
									</div>
								</div>
							</article>
						</div>
					</div>
				</div>
			</div>	
		</div>
	</section>
</div>
<script type="text/javascript">
var myColorsArrVar = ['#7cb5ec', '#757575', '#90ed7d', '#CC33FF', '#0000FF','#f15c80', '#e4d354', '#171717', '#8d4653', '#91e8e1'];
var xAxisDataArrVar = []; 
var seriesOptionArrVar = [];
var objNameArrVar = [];
var objIdArrVar = [];
var seriesObjVar = {
        name:'',
         type:'bar',
         data: [],
         itemStyle :{
         	normal:{
         		color:''
         	}
         } 
  };
function queryCondition(time,type){
    this.time = time;
    this.type = type;
}
var qCondition = new queryCondition("ThisQuarterOT", "ActivityType!all");//不能删除，后面用
/** 点击时间按钮 **/
function clickTime(id,timeString){
	 $('#NoTime').removeClass('mytxt-color-wathet');
	 $('#ThisMonthOT').removeClass('mytxt-color-wathet');
	 $('#ThisQuarterOT').removeClass('mytxt-color-wathet');
	 $('#ThisYearOT').removeClass('mytxt-color-wathet');
     $('#'+id).addClass('mytxt-color-wathet');
     if(id=="NoTime"){
    	 qCondition.time="";
     }else{
     	 qCondition.time=id;
     }
     transmitConditionPie();
     if($('#alreadySltTime').length>0){
		$('#alreadySltTime').text(timeString);
	 }else{
		$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltTime'>"+timeString+"</span></li>");
	 }
}
/** 传递查询参数 服务 饼图 **/
function transmitConditionPie(){
	$('#queryTime').val(qCondition.time);
	$('#queryType').val(qCondition.type);
	queryCustomerAccountDistribute();
}
</script>
<script type="text/javascript">
	/** 为查询'分布视图'准备条件,时间,type,和当前选中条件的显示,做准备 **/
	function goCustomerAccountDistribute(typeID,selectTypeStr) {
		if (typeID != "" && selectTypeStr !="") {
			qCondition.type=typeID;
			if($('#alreadySltSupplier').length>0){
				$('#alreadySltSupplier').text(selectTypeStr);
			}else{
				$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltSupplier'>"+selectTypeStr+"</span></li>")
			}
			transmitConditionPie();
		}
	}
	/** 查询'分布视图' **/
	function queryCustomerAccountDistribute() {
		$.ajax({
			url : "${nvix}/nvixnt/nvixSaleChanceStatisticsAction!activityDivideMonthDrawQuery.action",
			type: "POST",
			dataType: "json",
			data: {queryTime:$('#queryTime').val(),type:$('#queryType').val()},		
			success : function(json) {
				objNameArrVar = json.objNameArr;
				objIdArrVar = json.objIdArr;
				var stateInt = 0;
				var arrLength = objNameArrVar.length;
				for(var i=0 ;i<arrLength;i++){
					queryAjaxNesting(objNameArrVar[i],objIdArrVar[i],myColorsArrVar[(i%(arrLength))],i,arrLength);
					stateInt = 2;
				}
				if(stateInt == 2){
					var myChart = echarts.init(document.getElementById('viewABC'));
					var option = {
			 		    	    tooltip : {
			 		    	        trigger: 'axis',
			 		    	   		axisPointer: {
			 		       	        	 type: 'shadow',
			 		     	            crossStyle: {
			 		     	                color: '#FFFF33'
			 		     	            }
			 		     	        }
			 		    	    },
			 		    	    legend: {
			 		    	        data:objNameArrVar//['促销活动','清仓甩卖']
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
			 		    	            data : xAxisDataArrVar //["2018-01", "2018-02", "2018-03"]
			 		    	        }
			 		    	    ],
			 		    	    yAxis : [
			 		    	        { type : 'value',name: '数量/个',
			  		    				axisLabel : { //前面是y轴屏蔽小数  ,axisLabel                 
			  		    					formatter : function(value, index) {
			  		    						var str = value.toString()
			  		    						if (str.indexOf('.') >= 0) {
			  		    							return null;
			  		    						} else {
			  		    							return value;
			  		    						}
			  		    					}
			  		    				}
			 		    	        } 
			 		    	    ],
			 		    	    series : seriesOptionArrVar//一个复杂的对象数组    
		 		    };
						myChart.clear();
						myChart.setOption(option);
			 		    $(window).resize(myChart.resize);
				}else{
					$("#viewABC").html("<center style='color:#FF3366'><br><br><br><br><br><br><br><br><br><br>无数据</center>");
				}
			}
		});
	}
	/** ajax嵌套继续查询'分布视图' **/
	function queryAjaxNesting(objNameArrVarNesting,objIdArrVarNesting,colorNesting,i,arrLengthInt) {
		var newTypeStr = 'ActivityType!'+objIdArrVarNesting;
		$.ajax({
			async : false,
			url : "${nvix}/nvixnt/nvixSaleChanceStatisticsAction!discoveryTimeMonthViewQuery.action",
			type: "POST",
			dataType: "json",
			data: {queryTime:$('#queryTime').val(),type:newTypeStr},		
			success : function(json) {
				if(arrLengthInt == 1){
					seriesOptionArrVar = [];//如果指定ID查询,这里必须清空(seriesOptionArrVar = []);而且执行顺序必须在下面代码之前;
				}
				seriesObjVar.name = ''+objNameArrVarNesting;
				seriesObjVar.data = json.valueArr;
				seriesObjVar.itemStyle.normal.color=''+colorNesting;
		    	seriesOptionArrVar[i]=seriesObjVar;
		    	seriesObjVar = {//这一步,在seriesOptionArrVar[i]=seriesObjVar;之后清空seriesObjVar对象非常重要
		    	         name:'',
		    	         type:'bar',
		    	         data: [],
		    	         itemStyle :{
		    	         	normal:{
		    	         		color:''
		    	         	}
		    	         } 
		    	};
		    	xAxisDataArrVar = json.nameArr;
			}
		});
	}
</script>

<script type="text/javascript">
        $(document).ready(function() {
        	/** 删除已选条件 **/
        	$(".chooseSort").on("click","li span",function(){
        		$(this).parents("li").remove();
        	});
        	/** 点击自定义时间时 **/
            $('#reservation').daterangepicker(null, function(start, end, label) {});
            /** 自定义时间被确认时 **/
       		$('#reservation').on('apply.daterangepicker', function(ev, picker) {
	       	    var startstr = picker.startDate.format('YYYY-MM-DD');
	       	    var endstr = picker.endDate.format('YYYY-MM-DD');
	       	    var strTime = startstr+endstr;
	       	    qCondition.time=strTime;
	       	     var timeString = startstr+" - "+endstr;
		       	 if($('#alreadySltTime').length>0){
		     		$('#alreadySltTime').text(timeString);
		     	 }else{
		     		$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltTime'>"+timeString+"</span></li>");
		     	 }
		       	transmitConditionPie();
       		});
       		transmitConditionPie();
        });
</script>