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
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售前管理 </span><span>> 销售机会统计 </span>
			</h1>
			<input type="hidden" value="" id="queryTime">
			<input type="hidden" value="saleChanceEmployee" id="queryType">  
			<input type="hidden" value="" id="queryControlSQL">     
			<input type="hidden" value="" id="queryTopNum">
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
							<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs mytxt-color-wathet" id="NoTime" onclick="clickTime('NoTime','无时间限制');" >无时间限制</a>
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
								<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltTime'>无时间限制</span></li>
								<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltSupplier'>销售机会负责人分布</span></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="well padding-10">
					<h5 class="margin-top-0">
						<i class="fa fa-fw fa-list-ol"></i> 分布选择条件:
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
									{"id" : "saleChanceEmployee","word" : "销售机会负责人分布"},
									{"id" : "saleChanceSaleSource","word" : "销售机会来源分布"},
									{"id" : "saleChanceSaleType","word" : "销售机会类型分布"},
						   		]
						     }
						 });
						 /**选择供应商时**/
					 	$("input#sltSupplier").on("onSetSelectValue", function (event, result) {
						    var sID = result.id;
						    qCondition.type=sID;
						    var selectTypeStr=result.key;
							goPieChartView(sID,selectTypeStr);
							transmitConditionPie();
						});
			 		</script>
				</div>
				
			</div>
			<div class="col-sm-9">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="row">
							<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="jarviswidget">
									<header>
										<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i>
										</span>
										<h2>分布</h2>
										<div class="widget-toolbar">
											<div class="btn-group">
												<button class="btn dropdown-toggle btn-xs btn-default" data-toggle="dropdown">
													显示 <i class="fa fa-caret-down"></i>
												</button>
												<ul class="dropdown-menu js-status-update pull-right">
													<li><a href="javascript:void(0);" onclick="goPieChartView('saleChanceEmployee','销售机会负责人分布')">销售机会负责人分布</a></li>
													<li><a href="javascript:void(0);" onclick="goPieChartView('saleChanceSaleSource','销售机会来源分布')">销售机会来源分布</a></li>
													<li><a href="javascript:void(0);" onclick="goPieChartView('saleChanceSaleType','销售机会类型分布')">销售机会类型分布</a></li>
												</ul>
											</div>
										</div>
									</header>
									<div>
										<div class="widget-body no-padding">
											<div id="pieChartsa" style="height: 350px"></div>
										</div>
									</div>
								</div>
							</article>
						</div>
					</div>
				</div>
													<!-- <hr class="simple" style="border-color: #c0c0c0;"> -->
				
			</div>	
		</div>
	</section>
</div>
<script type="text/javascript">
/** 把页面查询条件封装成js对象 type控制饼图,controlSQL控制柱图,topNum控制bar图 **/
function queryCondition(time,type,controlSQL,topNum){
    this.time = time;
    this.type = type;
    this.controlSQL = controlSQL;
    this.topNum = topNum;
}
var qCondition = new queryCondition("", "saleChanceEmployee","","");//不能删除，后面用
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
	$('#queryControlSQL').val(qCondition.controlSQL);
	$('#queryTopNum').val(qCondition.topNum);
	queryPieChartView();
}
</script>
<script type="text/javascript">
	/** 为查询'分布视图'准备条件,时间,type,和当前选中条件的显示,做准备 **/
	function goPieChartView(typeID,selectTypeStr) {
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
	function queryPieChartView() {
		  $.ajax({
			url : "${nvix}/nvixnt/nvixSaleChanceStatisticsAction!queryPieView.action",
			data: {queryTime:$('#queryTime').val(),type:$('#queryType').val()},		
			dataType: "json",
			success:function(json){ 
					var numberArr  = json.valueArr;//[111,101,99];
					var stringArr  = json.nameArr;//['类别A','类别B','类别C'];
					var arraySeries = [];  
					var map ={}; 
					for(var i=0 ;i<numberArr.length;i++){
						map.value = numberArr[i];
					    map.name = stringArr[i]; 
						arraySeries[i]=map;
					    map ={};
					 }
					 var arrayLegend = []; 
					 for(var i=0 ;i<stringArr.length;i++){
						arrayLegend[i]=stringArr[i]; 
					 }
				var pieChartObj = echarts.init(document.getElementById('pieChartsa'));
				var	option1 = {
					    title : {
					        text: '分布',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            magicType : {
					                show: true, 
					                type: ['pie', 'funnel'],
					                option: {
					                    funnel: {
					                        x: '25%',
					                        width: '50%',
					                        funnelAlign: 'left',
					                        max: 1548
					                    }
					                }
					            }
					        }
					    },
					    calculable : true,
					    series : [
					        {
					            name:'分布',
					            type:'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:arraySeries
					        }
					    ]
					};
				pieChartObj.clear();
				pieChartObj.setOption(option1);
	 		    $(window).resize(pieChartObj.resize);
			
		 }}); 
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
       		queryPieChartView();
        });
</script>