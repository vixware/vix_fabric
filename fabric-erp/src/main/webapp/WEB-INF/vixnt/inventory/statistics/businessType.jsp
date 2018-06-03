<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${nvix}/vixntcommon/base/js/plugin/daterangepicker/daterangepicker-bs3.css">
<script src="${nvix}/vixntcommon/base/js/plugin/daterangepicker/moment.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/daterangepicker/daterangepicker.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<style>
	.mytxt-color-wathet { /* 目标按钮浅蓝色 */
	background-color:#428BCA !important;
	}
</style>
<div id="content">

			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 库存统计 <span>&gt; 类型结构分析</span>
					</h1>
				</div>
			</div>
			
			<section id="widget-grid" class="">

				<div class="row">
					<div class="col-sm-3">
						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="icon-append fa fa-calendar"></i> 选择时间
							</h5>
							
							<ul class="demo-btns tab-btn">
								<li>   
									<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs mytxt-color-wathet" id="Today"  onclick="">今日</a>  
								</li>
								<li>
									<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs" id="ThisWeek" onclick="">本周</a>
								</li>
								<li>
									<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs" id="ThisMonthOT" onclick="">本月</a>
								</li>
								<li>
									<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs" id="ThisQuarterOT" onclick="">本季度</a>
								</li>
								<li>
									<a href="javascript:void(0);" class="btn bg-color-blueLight   txt-color-white btn-xs" id="ThisYearOT" onclick="">本年</a>
								</li>
							</ul>
							
								<form class="form-horizontal">
				                 <fieldset>
				                  <div class="control-group">
				                    <div class="controls">
				                     <div class="input-prepend input-group">
				                       <input type="text"  name="reservation" id="reservation" class="form-control" value="2014-5-21 - 2014-6-21" /><span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span> 
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
										<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span>鞋</li>
									</ul>
								</div>
							</div>
							
						</div>
						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="fa fa-globe"></i> 区域
							</h5>
							<div class="row">
							<div class="form-group">
												<div class="col-md-12" >
			<select multiple="multiple" id="multiselect1" class="chooseMultiple" size="5" style="width:99%;"  >
														<option class="list-multiple-item">北京</option>
														<option class="list-multiple-item">上海</option>
														<option class="list-multiple-item">南京</option>
														<option class="list-multiple-item">天津</option>
														<option class="list-multiple-item">广州</option>
														<option class="list-multiple-item">江苏</option>
														<option class="list-multiple-item">宝鸡</option>
														<option class="list-multiple-item">云南</option>
														<option class="list-multiple-item">内蒙古</option>
														<option class="list-multiple-item">呼和浩特</option>
														<option class="list-multiple-item">黑龙江</option>
														<option class="list-multiple-item">安阳</option>
														<option class="list-multiple-item">南阳</option>
														<option class="list-multiple-item">成都</option>
														<option class="list-multiple-item">信阳</option>
														<option class="list-multiple-item">山西</option>
														<option class="list-multiple-item">陕西</option>
														<option class="list-multiple-item">广西</option>
														<option class="list-multiple-item">山东</option>
														<option class="list-multiple-item">桂林</option>
													</select>
												</div>
											</div>
							</div>
							
						</div>

						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="fa fa-fw fa-institution"></i> 门店: 
							</h5>
							
							<div class="row">
							<div class="form-group">
												<div class="col-md-12" >
													<select multiple="multiple" class="chooseMultiple" size="5"  style="width:99%;"  >
														<option class="list-multiple-item">京东一号店</option>
														<option class="list-multiple-item">京东二号店</option>
														<option class="list-multiple-item">京东三号店</option>
														<option class="list-multiple-item">京东四号店</option>
														<option class="list-multiple-item">京东五号店</option>
														<option class="list-multiple-item">京东六号店</option>
														<option class="list-multiple-item">淘宝一号店</option>
														<option class="list-multiple-item">淘宝二号店</option>
														<option class="list-multiple-item">淘宝三号店</option>
														<option class="list-multiple-item">淘宝四号店</option>
														<option class="list-multiple-item">淘宝五号店</option>
														<option class="list-multiple-item">淘宝六号店</option>
														<option class="list-multiple-item">淘宝七号店</option>
														<option class="list-multiple-item">淘宝八号店</option>
														<option class="list-multiple-item">淘宝九号店</option>
														<option class="list-multiple-item">网易商城一号号店</option>
														<option class="list-multiple-item">网易商城二号店</option>
														<option class="list-multiple-item">网易商城三号店</option>
														<option class="list-multiple-item">网易商城四号店</option>
														<option class="list-multiple-item">网易商城五号店</option>
													</select>
												</div>
											</div>
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
														<h2>产品类别排行Top10</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewB" style="height: 300px"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-6 col-md-6">
												<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>   
														<h2>产品类别饼图</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewC" style="height: 300px"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
								</div>
							</div>
							
							<div class="jarviswidget" data-widget-editbutton="false" role="widget"> 
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i></span>
									<h2>产品类别分析列表</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
					</div>
				</div>
				
			</section>
			

		</div>
<script type="text/javascript">		
function queryMethodC(divId) {
	    var numberArr  = [111,101,99,88,77,66,55,44,33,22,137];
		var stringArr  = ['类别A','类别B','类别C','类别D','类别E','类别F','类别G','类别H','类别I','类别J','其他'];
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
		var myChart2 = echarts.init(document.getElementById(''+divId));
		var myColours = ['#CC3399','#FF3399','#7900FF','#BD514B'];
		var	option2 = {
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
	        formatter: "{a} <br/>{b} : {c}元 ({d}%)"
	    },
	    series : [
	        {
	            name: '销售金额',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
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
function queryMethodB(divId) {
		var myChart = echarts.init(document.getElementById(divId));
	var	option = {
	   	    title: {
	   	    	left: 'left',
	   	        text: '',
	   	        subtext: ''
	   	    },
	   		 tooltip : {
    	        trigger: 'axis',
    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}<br/>'
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
	   	        data: ['产品类别销售金额']
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
	   	     data:['','','','','','','','类别C','类别B','类别A']
	   	        /* data:json.nameArr */       //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
	   	    }],
	   	    series: [
	   	        {
	   	            name: '产品类别销售金额',
	   	            type: 'bar',
	   	         data: [0,0,0,0,0,0,0,12,311,1278],
	   	         	/* data: myNumToFixed2(json.valueArr),  *///参考  data: [30,78,111,178],
	   	         itemStyle: {
                        normal: {
                            color: function(params) {
                                var colorList = [
                                  '#003399' 
                                ];
                                return colorList[params.dataIndex]
                            },
                        }
    	            },
    	            
	   	            label: {
	   	                normal: {
	   	                    show: true,
	   	                    position: 'right',
	   	                    formatter: '{c}元'
	   	                },
	   	                
	   	            }
	   	        }
	   	    ]
	   	};
		myChart.clear();
		myChart.setOption(option);
		$(window).resize(myChart.resize);
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
	$(".choosegroup .list-group-item").click(function(){
		var html=$(this).html();
		$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span>"+html+"</li>")
	});
	$(".chooseMultiple .list-multiple-item").click(function(){
		var html=$(this).html();
		$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span>"+html+"</li>")
	});
	$(".list-unstyled li span").click(function(){
		var html=$(this).html();
		$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span>"+html+"</li>")
	});
	$(".chooseSort").on("click","li span",function(){
		$(this).parents("li").remove();
	})
}
		$(document).ready(function() {
			pageSetUp();//初始化小图
			choice();
			queryMethodB("dataViewB");
			queryMethodC("dataViewC");
		});
</script>

<script type="text/javascript">
pageSetUp();
var customerAccountColumns = [ {
"title" : "编号",
"width" : "20%",
"defaultContent" : ''
}, {
"title" : "产品类别",
"width" : "20%",
"data" : function(data) {
	return "";
}
}, {
"title" : "销售金额",
"width" : "20%",
"data" : function(data) {
	return "";
}
}, {
"title" : "占比",
"width" : "20%",
"data" : function(data) {
	return "";
}
},{
"title" : "最近销售时间",
"width" : "20%",
"data" : function(data) {       
	return "";
}
}];
	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!goTable.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize
		};
	}, 10, '0');
</script>

<script type="text/javascript">
        $(document).ready(function() {
           $('#reservation').daterangepicker(null, function(start, end, label) {});
        });
</script>
<script type="text/javascript">
$(document).ready(function() {
    var tab_menu_li = $('.tab-btn li');
     tab_menu_li.click(function(){
         $(this).children("a").addClass('mytxt-color-wathet')
                 .parents("li").siblings().children("a").removeClass('mytxt-color-wathet');
     })
 });
</script>