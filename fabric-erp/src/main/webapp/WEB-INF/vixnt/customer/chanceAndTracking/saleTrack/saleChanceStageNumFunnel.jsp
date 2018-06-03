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
				<i class="fa fa-lg fa-fw fa-user"></i> 客户关系管理 <span>> 销售跟踪 </span><span>> 各阶段机会数量漏斗 </span>
			</h1>
			<input type="hidden" value="ThisQuarterOT" id="queryTime">
			<input type="hidden" value="NumFunnel!all" id="queryType">  
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
								<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltSupplier'>统计全部状态的销售机会</span></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="well padding-10">
					<h5 class="margin-top-0">
						<i class="fa fa-fw fa-list-ol"></i> 漏斗图限制条件:
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
									{"id" : "NumFunnel!all","word" : "统计全部状态的销售机会"},
									{"id" : "NumFunnel!all","word" : "仅统计'状态=跟踪中'的销售机会"},
									{"id" : "NumFunnel!all","word" : "仅统计'状态=放弃'的销售机会"},
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
										<h2>各阶段机会数量漏斗</h2>
									</header>
									<div id="customerAccountDistributeId">
										<div class="widget-body no-padding">
											<div class="row">
												 <div class="col-sm-9">
												 	<div id="funnelMainView" style="width: 100%;height:680px;"></div>
												 </div>  
												 <div class="col-sm-3" style="height:660px;"  id="funnelAdditionalView">
												 </div>
											 </div>
										</div>
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
function queryCondition(time,type){
    this.time = time;
    this.type = type;
}
var qCondition = new queryCondition("ThisQuarterOT", "NumFunnel!all");//不能删除，后面用
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
			url : "${nvix}/nvixnt/nvixSaleChanceStatisticsAction!saleChanceStageFunnelQuery.action",
			type: "POST",
			dataType: "json",
			data: {queryTime:$('#queryTime').val(),type:$('#queryType').val()},		
			success : function(json) {
				var nameArrVar = json.nameArr;
				var valueArrVar = json.valueArr;
				var tempObjVar = {value: 0, name: ''};
				var dataArrVar = [];
				var stateInt = 0;
				var valueArrVarLength = valueArrVar.length;
				for(var i=0 ;i<valueArrVarLength;i++){
					tempObjVar.value = valueArrVar[i];
					tempObjVar.name = ''+nameArrVar[i];
					dataArrVar[i]=tempObjVar;
					tempObjVar = {value: 0, name: ''};
					stateInt = 2;
				}
				if(stateInt !=2 ){
					$('#funnelAdditionalView').html("");
					$("#funnelMainView").html("<center style='color:#FF3366'><br><br><br><br><br><br>无数据</center>");
				}else{
					var myChart = echarts.init(document.getElementById('funnelMainView'));
	                option = {
	                	    tooltip: {
	                	        trigger: 'item',
	                	        formatter: "{a} <br/>{b} : {c}个"
	                	    },
	                	    toolbox: {
	                	        feature: {
	                	            dataView: {readOnly: false},
	                	            restore: {},
	                	            saveAsImage: {}
	                	        }
	                	    },
	                	    legend: {
	                	        data: nameArrVar
	                	    },
	                	    calculable: true,
	                	    series: [
	                	        {
	                	            name:'漏斗图',
	                	            type:'funnel',
	                	            left: '10%',
	                	            top: 60,
	                	            bottom: 60,
	                	            width: '80%',
	                	            height: 600,
	                	            sort: 'descending',
	                	            gap: 2,
	                	            label: {
	                	                normal: {
	                	                    show: true,
	                	                    position: 'inside'
	                	                },
	                	                emphasis: {
	                	                    textStyle: {
	                	                        fontSize: 20
	                	                    }
	                	                }
	                	            },
	                	            labelLine: {
	                	                normal: {
	                	                    length: 10,
	                	                    lineStyle: {
	                	                        width: 1,
	                	                        type: 'solid'
	                	                    }
	                	                }
	                	            },
	                	            itemStyle: {
	                	                normal: {
	                	                    borderColor: '#fff',
	                	                    borderWidth: 1
	                	                }
	                	            },
	                	            data: dataArrVar
	                	        }
	                	    ]
	                	};
	                myChart.clear();
					myChart.setOption(option);
		 		    $(window).resize(myChart.resize);
		 		    
		 		    if(valueArrVarLength>=1){
		 		    	var everyHeightMy = parseInt(600/valueArrVarLength);
		 		    	var topHeightMy = (everyHeightMy/2)+60;
		 		    	for(var i=0 ;i<valueArrVarLength;i++){
		 		    		if(i==0){
		 		    			$('#funnelAdditionalView').html("");
		 		    			$("#funnelAdditionalView").append("<div style='height:"+topHeightMy+"px;'></div>");
		 		    		}
		 		    		$("#funnelAdditionalView").append("<div style='height:"+everyHeightMy+"px;'><strong style='color:#474747;'>"+nameArrVar[i]+"</strong><strong style='margin:0px 0px 0px 10px;color:#4FB4FF;'>"+valueArrVar[i]+"个</strong></div>");
			 		   	}
		 		    }
				}
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