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
				<i class="fa fa-lg fa-fw fa-user"></i> 客户关系管理 <span>> 客户管理 </span><span>> 客户统计 </span>
			</h1>
			<input type="hidden" value="" id="queryTime">
			<input type="hidden" value="customerType" id="queryType">  
			<input type="hidden" value="contractTotalAmountTop" id="queryControlSQL">     
			<input type="hidden" value="20" id="queryTopNum">
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
								<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltSupplier'>客户种类</span></li>
								<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltContract'>客户top20(合同总金额)</span></li>
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
									{"id" : "customerType","word" : "客户种类"},
									{"id" : "customerStage","word" : "客户阶段"},
									{"id" : "customerHotLevel","word" : "热点程度"},
									{"id" : "customerCreditLevel","word" : "信用等级"},
									{"id" : "customerRelationshipClass","word" : "关系等级"},
									{"id" : "customerStaffScale","word" : "人员规模"},
									{"id" : "customerCustomerSource","word" : "来源分布"},
									{"id" : "customerIndustry","word" : "所属行业"},
									{"id" : "customerValueAssessment","word" : "价值评估"},
									{"id" : "cstomerAccountType","word" : "账户类型"},
									{"id" : "cstomerProvince","word" : "省份分布"},
									{"id" : "cstomerCity","word" : "城市分布"},
									{"id" : "cstomerEmployee","word" : "所有者分布"},
									{"id" : "cstomerAccountCategory","word" : "客户分类"},
						   		]
						     }
						 });
						 /**选择供应商时**/
					 	$("input#sltSupplier").on("onSetSelectValue", function (event, result) {
						    var sID = result.id;
						    qCondition.type=sID;
						    var selectTypeStr=result.key;
							goCustomerAccountDistribute(sID,selectTypeStr);
							transmitConditionPie();
						});
			 		</script>
				</div>
				
				<div class="well padding-10">
					<h5 class="margin-top-0">
						<i class="fa fa-fw fa-list-ol"></i> 排行选择条件:
					</h5>
					<div class="row">
						<div class="input-group" style="width:97%;padding-left:15px;">
							<input type="text" class="form-control" id="sltContract">
							<div class="input-group-btn">
	           					<button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
	                   				<span class="caret"></span>
	                			</button>
	                			<ul class="dropdown-menu dropdown-menu-right" role="menu"></ul>
							</div>
	    				</div>
					</div>
					<script type="text/javascript">
						var testdataBsSuggest = $("#sltContract").bsSuggest({
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
									{"id" : "contractTotalAmountTop!20","word" : "客户top20(合同总金额)"},
									{"id" : "contractTotalAmountTop!50","word" : "客户top50(合同总金额)"},
									{"id" : "backSectionRecordAmount!20","word" : "客户top20(回款总金额)"},
									{"id" : "backSectionRecordAmount!50","word" : "客户top50(回款总金额)"},
									{"id" : "backSectionPlanAmount!20","word" : "应收款对应客户top20"},
									{"id" : "backSectionPlanAmount!50","word" : "应收款对应客户top50"},
									{"id" : "activityFrequency!20","word" : "客户销售活动次数top20"},
									{"id" : "activityFrequency!50","word" : "客户销售活动次数top50"},
						   		]
						     }
						 });
						 /**选择时**/
					 	$("input#sltContract").on("onSetSelectValue", function (event, result) {
					 		/** 注意:这里设置id时,必须是  id+!+数字 **/
						    var sID = result.id;
						    var arr = sID.split("!");
						    qCondition.controlSQL=arr[0];
						    var selectTypeStr=result.key;
						    goContractReady(arr[0],selectTypeStr,arr[1]);
							transmitConditionBar();
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
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('customerType','客户种类')">客户种类</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('customerStage','客户阶段')">客户阶段</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('customerHotLevel','热点程度')">热点程度</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('customerCreditLevel','信用等级')">信用等级</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('customerRelationshipClass','关系等级')">关系等级</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('customerStaffScale','人员规模')">人员规模</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('customerCustomerSource','来源分布')">来源分布</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('customerIndustry','所属行业')">所属行业</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('customerValueAssessment','价值评估')">价值评估</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('cstomerAccountType','账户类型')">账户类型</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('cstomerProvince','省份分布')">省份分布</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('cstomerCity','城市分布')">城市分布</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('cstomerEmployee','所有者分布')">所有者分布</a></li>
													<li><a href="javascript:void(0);" onclick="goCustomerAccountDistribute('cstomerAccountCategory','客户分类')">客户分类</a></li>
												</ul>
											</div>
										</div>
									</header>
									<div id="customerAccountDistributeId">
										<div class="widget-body no-padding">
											<div id="customerAccountDistribute" style="height: 350px"></div>
											<script type="text/javascript">
												var customerAccountDistribute = echarts.init(document.getElementById('customerAccountDistribute'));
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
													            data:[${customerNum}]
													        }
													    ]
													};
												customerAccountDistribute.clear();
												customerAccountDistribute.setOption(option1);
									 		    $(window).resize(customerAccountDistribute.resize);
											</script>
										</div>
									</div>
								</div>
							</article>
						</div>
					</div>
				</div>
													<!-- <hr class="simple" style="border-color: #c0c0c0;"> -->
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="row">
							<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="jarviswidget">
									<header>
										<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i>
										</span>
										<h2>排行</h2>
										<div class="widget-toolbar">
											<div class="btn-group">
												<button class="btn dropdown-toggle btn-xs btn-default" data-toggle="dropdown">
													显示 <i class="fa fa-caret-down"></i>
												</button>
												<ul class="dropdown-menu js-status-update pull-right">
													<li><a href="javascript:void(0);" onclick="goContractReady('contractTotalAmountTop','客户top20(合同总金额)','20')">客户top20(合同总金额)</a></li>
													<li><a href="javascript:void(0);" onclick="goContractReady('contractTotalAmountTop','客户top50(合同总金额)','50')">客户top50(合同总金额)</a></li>
													<li><a href="javascript:void(0);" onclick="goContractReady('backSectionRecordAmount','客户top20(回款总金额)','20')">客户top20(回款总金额)</a></li>
													<li><a href="javascript:void(0);" onclick="goContractReady('backSectionRecordAmount','客户top50(回款总金额)','50')">客户top50(回款总金额)</a></li>
													<li><a href="javascript:void(0);" onclick="goContractReady('backSectionPlanAmount','应收款对应客户top20','20')">应收款对应客户top20</a></li>
													<li><a href="javascript:void(0);" onclick="goContractReady('backSectionPlanAmount','应收款对应客户top50','50')">应收款对应客户top50</a></li>
													<li><a href="javascript:void(0);" onclick="goContractReady('activityFrequency','客户销售活动次数top20','20')">客户销售活动次数top20</a></li>
													<li><a href="javascript:void(0);" onclick="goContractReady('activityFrequency','客户销售活动次数top50','50')">客户销售活动次数top50</a></li>
												</ul>
											</div>
										</div>
									</header>
									<div>
										<div class="widget-body no-padding">
											<div id="contractTopBar" style="height: 350px"></div>
											<script type="text/javascript">
											</script>
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
/** 把页面查询条件封装成js对象 type控制饼图,controlSQL控制柱图,topNum控制bar图 **/
function queryCondition(time,type,controlSQL,topNum){
    this.time = time;
    this.type = type;
    this.controlSQL = controlSQL;
    this.topNum = topNum;
}
var qCondition = new queryCondition("", "customerType","contractTotalAmountTop","20");//不能删除，后面用
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
     transmitConditionBar();
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
/** 传递查询参数 服务 柱图 **/
function transmitConditionBar(){
	$('#queryTime').val(qCondition.time);
	$('#queryType').val(qCondition.type);
	$('#queryControlSQL').val(qCondition.controlSQL);
	$('#queryTopNum').val(qCondition.topNum);
	queryBarView();
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
			url : "${nvix}/nvixnt/nvixCustomerStatisticsAction!goCustomerAccountDistribute.action",
			data: {queryTime:$('#queryTime').val(),type:$('#queryType').val()},		
			cache : false,
			success : function(html) {
				$("#customerAccountDistributeId").html(html);
			}
		});
	}
</script>
<script type="text/javascript">
/** 为查询'排行'准备条件,时间,type,和当前选中条件的显示,做准备 **/
function goContractReady(controlSQLID,selectStr,topNumStr) {
	if (controlSQLID != "" && selectStr !="" && topNumStr !="" ) {
		qCondition.controlSQL=controlSQLID;
		qCondition.topNum=topNumStr;
		if($('#alreadySltContract').length>0){
			$('#alreadySltContract').text(selectStr);
		}else{
			$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltContract'>"+selectStr+"</span></li>")
		}
		transmitConditionBar();
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
/** 查询bar视图 **/
function queryBarView() {
	 $.ajax({    
		url : "${nvix}/nvixnt/nvixCustomerStatisticsAction!queryContractTopBar.action",
	 	type: "POST",
	 	data: {queryTime:$('#queryTime').val(),controlSQL:$('#queryControlSQL').val(),topNum:$('#queryTopNum').val()},	
	    dataType: "json",
		success:function(json){ 
			var myChart = echarts.init(document.getElementById('contractTopBar'));
			var myBarColor = "#53A0F2";
			var mySeriesName ="金额";
			var myTooltipName ="万元";
			var myYAxisName ="金额/万元";
			var mySeriesData = "";
			var myState = "double";
			if(json.optionStr=="activityFrequency"){
				 mySeriesName ="销售活动次数";
				 myTooltipName ="次";
				 myYAxisName ="次数/次";
				 myState = "int";
				 mySeriesData = json.valueArr;
			}else{
				mySeriesData = myNumArrToMillionYuan(json.valueArr);
			}
			var	option = {
			    title : {
			        text: '排行',
			        x:'center'
			    },
			   tooltip : {
	    	        trigger: 'axis',
formatter: '客户 : {a0}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myBarColor+'"></span>{b} : {c0}'+myTooltipName+'<br/>'
,  
	    	        axisPointer: {
	    	        	 type: 'shadow',
	    	            crossStyle: {
	    	                color: '#FFFF33'
	    	            }
	    	        }
	    	    }, 
			    legend: {
			    	x:'right',y:'bottom',top:'middle',orient:'vertical',
			    	align:'left',  
			    	itemWidth:38,
			    	itemHeight:21,
					data:json.nameArr//['1件','2件','3件','4件','5件','6件','7件','8件','9件','10件及以上'],
		    },
		    grid: {
    	        left: '1%',
    	        right: '1%',
    	        bottom: '3%',
    	        containLabel: true
    	    },
    	    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false}
		        }
		    },
			    xAxis : [
			        {
			            type : 'category',
				        axisLabel:{  
			               interval:0, 
			               rotate:10,//75度角倾斜显示  
			            },
			             boundaryGap : true,
			            data :json.nameArr//参考：['1件','2件','3件','4件','5件','6件','7件','8件','9件','10件及以上']  
			        }
			    ],
			    yAxis : [
			    	{
			            type : 'value',name: ''+myYAxisName
			            ,axisLabel : { //前面是y轴屏蔽小数  ,axisLabel //并做出判断                
							formatter : function(value, index) {
								if(myState == "double"){
									return value;
								}else{
									 var str = value.toString()
									if (str.indexOf('.') >= 0) {
										return null;
									} else {
										return value;
									}
								}
							}
						}
			        }
			    ],
			    series : [
			        {
			            name:''+mySeriesName,
			            type:'bar',
			            itemStyle: {
			                normal: {color: ''+myBarColor}//设置柱子颜色
			            },
			            yAxisIndex: 0,
			            /* barWidth : 51, */
			            data:mySeriesData, //参考： data:[80, 38, 51, 134, 120, 339, 50] 
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
		       	transmitConditionBar();
       		});
       		queryBarView();
        });
</script>