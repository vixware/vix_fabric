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
				<i class="fa fa-lg fa-fw fa-user"></i> 客户关系管理 <span>> 销售跟踪 </span><span>> 负责人/机会状态统计 </span>
			</h1>
			<input type="hidden" value="ThisQuarterOT" id="queryTime">
			<input type="hidden" value="qChargerDivisionStatus" id="queryType">  
		</div>
	</div>
	<section id="widget-grid" class="">
		<div class="row">
			<div class="col-sm-3">
				<div class="well padding-10">
					<h5 class="margin-top-0">
						<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> 销售机会发现时间
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
								<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltSupplier'>负责人/机会状态统计</span></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="well padding-10">
					<h5 class="margin-top-0">
						<i class="fa fa-fw fa-list-ol"></i> 选择条件:
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
						     data:{
						    	 "value" : [
									{"id" : "qChargerDivisionStatus","word" : "负责人/机会状态统计"},
						   		]
						     }
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
										<h2>负责人/机会状态统计</h2>
									</header>
									<div id="customerAccountDistributeId">
										<div class="widget-body no-padding" id="viewABC"></div>
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
/** 查询机会状态数组legendArr **/
var legendArrName = [];//legendArrName 和  legendArrid 保存数据使用
var legendArrid = [];
var stateInt = 0;
var myColorsArrVar = ['#7cb5ec', '#757575', '#90ed7d', '#CC33FF', '#0000FF','#f15c80', '#e4d354', '#171717', '#8d4653', '#91e8e1'];
function queryLegendArr() {
	$.ajax({
		url : "${nvix}/nvixnt/nvixSaleChanceStatisticsAction!queryLegendArr.action",
		type: "POST",
		dataType: "json",
		success : function(json){
			legendArrName = json.nameArr;
			legendArrid = json.idArr;
			stateInt = 1;
			for(var i=0 ;i<legendArrName.length;i++){
				$("#viewABC").append("<div id='viewABC"+i+"'style='height: 200px'></div>");
				stateInt = 2;
			}
			transmitConditionPie();
		}
	});
}
function queryMaxNum() {
	var num = 10; 
	if(stateInt ==2){
		$.ajax({
			url : "${nvix}/nvixnt/nvixSaleChanceStatisticsAction!chargerDivisionStatusViewQuery.action",
			type: "POST",
			dataType: "json",
			async:false,
			data: {queryTime:$('#queryTime').val(),type:'queryMaxNum'},
			success : function(json) {
				num = json.maxNum;
			}
		});
	}
	return num;
}
/** 查询'分布视图' **/
function queryCustomerAccountDistribute(divId,str,legendArrId,myColorsStr,maxNum) {
	if(stateInt ==2){
		$.ajax({
			url : "${nvix}/nvixnt/nvixSaleChanceStatisticsAction!chargerDivisionStatusViewQuery.action",
			type: "POST",
			dataType: "json",
			data: {queryTime:$('#queryTime').val(),type:$('#queryType').val(),legendArrId:legendArrId},		
			success : function(json) {
				var nameArrJson = json.nameArr;//['陈默','李宁','AMIS','安妮','李飞','王小二','张三','李四','王五','赵六','刘能123123'];
				var myChart = echarts.init(document.getElementById(divId));
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
	 		    	        data:[(''+str)]
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
	 		    	            data : nameArrJson //["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
	 		    	        }
	 		    	    ],
	 		    	    yAxis : [
	 		    	        {
	 		    	            type : 'value',name: '单位/个',max:maxNum,
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
	 		    	    series : [
	 		    	        {
	 		    	            name:(''+str),
	 		    	            type:'bar',
	 		    	            data: json.valueArr,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
	 		    	            itemStyle :{
	 		    	            	normal:{
	 		    	            		color:(''+myColorsStr)
	 		    	            	}
	 		    	            } 
	 		    	        }
	 		    	    ]
	 		    	};
					myChart.clear();
					myChart.setOption(option);
		 		    $(window).resize(myChart.resize);
			}
		});
	}
}
function queryCondition(time,type){
    this.time = time;
    this.type = type;
}
var qCondition = new queryCondition("ThisQuarterOT", "qChargerDivisionStatus");//不能删除，后面用
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
	var maxNum = 10;
	if(legendArrName.length>=1){
		maxNum = queryMaxNum();
	}
	for(var i=0 ;i<legendArrName.length;i++){
		queryCustomerAccountDistribute(('viewABC'+i),legendArrName[i],legendArrid[i],myColorsArrVar[(i%(myColorsArrVar.length))],maxNum);
	}
	if(stateInt !=2 ){
		$("#viewABC").html("<div id='viewABC"+99+"'style='height: 350px;color:#FF3366'><center><br><br><br><br><br><br>无数据</center></div>");
	}
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
</script>

<script type="text/javascript">
        $(document).ready(function() {
        	queryLegendArr();
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
        });
</script>