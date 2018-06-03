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
				<i class="fa fa-lg fa-fw fa-user"></i> 客户关系管理 <span>> 销售跟踪 </span><span>> 应收款/回款计划所有者排行 </span>
			</h1>
			<input type="hidden" value="" id="queryTime">
			<input type="hidden" value="backSectionPlanAmountOwnerTop" id="queryType">  
			<input type="hidden" value="" id="queryControlSQL">     
			<input type="hidden" value="20" id="queryTopNum">
		</div>
	</div>
	<section id="widget-grid" class="">
		<div class="row">
			<div class="col-sm-3">
				<div class="well padding-10">
					<h5 class="margin-top-0">
						<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> 选择时间(计划回款日期)
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
								<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltSupplier'>应收款/回款计划所有者排行Top20</span></li>
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
									{"id" : "backSectionPlanAmountOwnerTop!20","word" : "应收款/回款计划所有者排行Top20"},
									{"id" : "backSectionPlanAmountOwnerTop!50","word" : "应收款/回款计划所有者排行Top50"},
						   		]
						     }
						 });
						 /**选择供应商时**/
					 	$("input#sltSupplier").on("onSetSelectValue", function (event, result) {
					 		/** 注意:这里设置id时,必须是  id+!+数字 **/
						    var sID = result.id;
						    var arr = sID.split("!");
						    qCondition.type=arr[0];
						    var selectTypeStr=result.key;
						    goCustomerAccountDistribute(arr[0],selectTypeStr,arr[1]);
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
										<h2>应收款/回款计划所有者排行</h2>
									</header>
									<div id="customerAccountDistributeId">
										<div class="widget-body no-padding">
											<div id="ShowDataViewA" style="height: 560px"></div>
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
function queryCondition(time,type,controlSQL,topNum){
    this.time = time;
    this.type = type;
    this.controlSQL = controlSQL;
    this.topNum = topNum;
}
var qCondition = new queryCondition("", "backSectionPlanAmountOwnerTop","","20");//不能删除，后面用
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
	queryCustomerAccountDistribute();
}
</script>
<script type="text/javascript">
	/** 为查询'分布视图'准备条件,时间,type,和当前选中条件的显示,做准备 **/
	function goCustomerAccountDistribute(typeID,selectTypeStr,topNumStr) {
		if (typeID != "" && selectTypeStr !="" && topNumStr !="" ) {
			qCondition.type=typeID;
			qCondition.topNum=topNumStr;
			if($('#alreadySltSupplier').length>0){
				$('#alreadySltSupplier').text(selectTypeStr);
			}else{
				$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltSupplier'>"+selectTypeStr+"</span></li>")
			}
			transmitConditionPie();
		}
	}
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
	/** 查询'分布视图' **/
	function queryCustomerAccountDistribute() {
		$.ajax({
			url : "${nvix}/nvixnt/nvixSaleChanceStatisticsAction!backPlanAmountCustomerTopQuery.action",
			type: "POST",
			dataType: "json",
			data: {queryTime:$('#queryTime').val(),type:$('#queryType').val(),topNum:$('#queryTopNum').val()},		
			success : function(json) {
				if(qCondition.topNum == '50'){
					document.getElementById("ShowDataViewA").style.height= 950 +"px";
				}else{
					document.getElementById("ShowDataViewA").style.height= 560 +"px";
				}
				var myColor = '#549FF1';//控制bar柱条颜色和提示框小圆圈颜色  
				var myChart = echarts.init(document.getElementById('ShowDataViewA'));
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
		   	    legend: {
		   	        data: ['金额']
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
		   	            name: '金额',
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