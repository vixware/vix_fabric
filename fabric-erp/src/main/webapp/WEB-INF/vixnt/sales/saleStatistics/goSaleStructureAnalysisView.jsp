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
						<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售智能分析 <span>&gt; 销售结构分析</span>
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
						
						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="fa fa-fw icon-iconfont-productCategory"></i> 商品分类:
							</h5>
								<div class="row">
									<ul id="tree" class="ztree" style="height: 130px; overflow-x: hidden; overflow-y: auto;"></ul>
									<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
									<script type="text/javascript">
										var zTree;
										var setting = {
										async : {
										enable : true,
										url : "${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action",
										autoParam : [ "id", "treeType" ]
										},
										callback : {
											onClick : onClick
										}
										};
										function onClick(event, treeId, treeNode, clickFlag) {
											$("#selectId").val(treeNode.id);
											$("#selectTreeType").val(treeNode.treeType);
											/* invWarehouseTable.ajax.reload(); */
											transmitCondition();
										}
										zTree = $.fn.zTree.init($("#tree"), setting);
									</script>
								</div>
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
														<h2>产品类别销售排行Top10</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewBmoney" style="height: 350px"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-6 col-md-6">
												<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>   
														<h2>产品类别销售排行饼图</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewCnum" style="height: 350px"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
								</div>
							</div>
							
							<form action="" method="post"  id="exportMD" target="form_iframe" style="margin: 0"></form>
							<div class="jarviswidget" data-widget-editbutton="false" role="widget"> 
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i></span>
									<h2>产品类别销售分析列表</h2>
									<a href="javascript:void(0);" class="btn bg-color-blueLight mytxt-color-wathet  txt-color-white btn-sm" id=""  onclick="exportExcel();" style="float:right;padding:2px 10px 2px;margin:4px 10px 3px 5px;" >导出</a>
								</header>
								<div>
									<div class="widget-body no-padding">
										<table id="materielCategoryTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
							
					</div>
				</div>
			</section>
		</div>
<script type="text/javascript">
/** categoryArrMoney  categoryArrName categoryTotalDouble  查询'产品类别销售分析列表'的参数 **/
var categoryArrMoney = "";
var categoryArrName = "";
var categoryTotalDouble = "";
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
			url: '${nvix}/nvixnt/nvixntSalesAnalysisAction!queryStructureSalesTopView.action', 
			type: "POST",  
data: {queryTime:$('#queryTime').val(),controlSQL:'SalesStructureBarAndPieTop',regionalID:$('#queryRegional').val(),customerAccountID:$('#queryCustomer').val(),employeeID:$('#queryEmployee').val(),categoryId:$('#selectId').val()},
		    dataType: "json",
			success:function(json){
			/** 下面是柱图操作  **/
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
		   	     	data:json.nameArrBarTen  //参考  data:['','','','','','','','','大众1号车轮','x反光镜']
		   	    }],
		   	    series: [
		   	        {
		   	            name: '产品销售金额',
		   	            type: 'bar',
		   	         	data: myNumArrToMillionYuan(json.valueArrBarTen),//参考  data: [30,78,111,178],
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
			/** 下面是饼图操作  **/  
			queryPieTopMoney(json.valueArrPieTen,json.nameArrPieTen);
			/** 下面是列表操作  **/
		 	 categoryArrMoney = json.valueArrTable;
		 	 categoryArrName = json.nameArrTable;
		 	 categoryTotalDouble = json.totalDouble; 
			 materielCategoryTable.ajax.reload();
	}});
}
function queryPieTopMoney(valueArr,nameArr) {
		var numberArr  = myNumArrToMillionYuan(valueArr);//[191,81];
		var stringArr  = nameArr;//['已完成采购订单','在途采购订单'];
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
		var noData ='';
			 if(numberArr.length == 0){
				noData = '暂无数据';
			 }
		var myChart2 = echarts.init(document.getElementById("dataViewCnum"));
		var myColours = ['#7cb5ec', '#757575', '#90ed7d', '#CC33FF', '#0000FF','#f15c80', '#e4d354', '#171717', '#8d4653', '#91e8e1'];
		var	option2 = {
		color:myColours,
		title : {
			        text: '',
			        x:'center',
	   			  	subtext: noData,
	        subtextStyle:{  //副标题样式
	            fontSize: 16,
	        	color: '#FF0033'
	        } 
			},
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c}万元 ({d}%)"
	    },
	    series : [
	        {
	            name: '产品销售金额',
	            type: 'pie',
	            radius : '60%',//圆的半径大小
	            data:arraySeries,  //参考 [{value:987, name:'储值卡'},{value:310, name:'计次卡'},{value:234, name:'储值计次卡'}],
	             itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            } 
	        }
	    ]
	};
		    myChart2.clear();
		    myChart2.setOption(option2);
		    $(window).resize(myChart2.resize);
}
</script>
<script type="text/javascript">
pageSetUp();
var materielCategoryColumns = [ {
"title" : "编号",
"width" : "25%",
"defaultContent" : ''
}, {
"title" : "产品类别",
"width" : "25%",
"data" : function(data) {
	return (data.name != null ? data.name : "" );
}
}, {
"title" : "销售金额",
"width" : "25%",
"data" : function(data) {
	return (data.money != null ? Number(data.money).toFixed(2) : "" );
}
}, {
"title" : "占比",
"width" : "25%",
"data" : function(data) {
	return (data.pro != null ? Number(data.pro).toFixed(2)+"%" : "" );  
}
}];
	var materielCategoryTable = initDataTable("materielCategoryTableId", "${nvix}/nvixnt/nvixntSalesAnalysisAction!queryStructureSalesTopTable.action", materielCategoryColumns, function(page, pageSize, orderField, orderBy) {
		var categoryArrMoneyV = Url.encode(JSON.stringify(categoryArrMoney));
		var categoryArrNameV = Url.encode(JSON.stringify(categoryArrName));
		var categoryTotalDoubleV = Url.encode(categoryTotalDouble);
		return {
			"page" : page,
			"pageSize" : pageSize,
			"categoryArrMoney" : categoryArrMoneyV,
			"categoryArrName" : categoryArrNameV,
			"categoryTotalDouble" : categoryTotalDoubleV
		};
}, 10, '0');
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
<script type="text/javascript">
/** 导出列表 ***/
function exportExcel() {
	form = document.getElementById("exportMD");
	var categoryArrMoneyV = Url.encode(JSON.stringify(categoryArrMoney));
	var categoryArrNameV = Url.encode(JSON.stringify(categoryArrName));  
	var categoryTotalDoubleV = Url.encode(categoryTotalDouble);
	form.action = "${nvix}/nvixnt/nvixntSalesAnalysisAction!outExcelToStructureSalesTopTable.action?categoryArrMoney="+categoryArrMoneyV+"&categoryArrName="+categoryArrNameV+"&categoryTotalDouble="+categoryTotalDoubleV;
	form.submit();
}
</script>