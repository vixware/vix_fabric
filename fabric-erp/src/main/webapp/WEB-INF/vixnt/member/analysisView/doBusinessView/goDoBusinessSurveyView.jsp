<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="content">

			<div class="row">
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">
						<i class="fa-fw fa fa-home"></i> 门店管理 <span>> 门店数据统计 </span> <span>> 门店营业统计</span>
					</h1>
				</div>
			</div>
			
			<section id="widget-grid" class="">

				<div class="row">
					<div class="col-sm-3">
						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="icon-append fa fa-calendar"></i> 选择日期
							</h5>
								<form>
									<div class="input-group" style="width: 85%;float : left;">
										<input placeholder="开始时间" style="width: 100%;"  id="startTimeB" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span style="width: -1%;" class="input-group-addon"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
									</div>
									<div style="visibility: hidden;"><br>-</div>
									<div class="input-group"  style="width: 85%;">
										<input placeholder="结束时间" style="width: 100%;" id="endTimeB" name="endCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span style="width: -1%;" class="input-group-addon"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endCreateTime'});"><i class="fa fa-calendar"></i></span>
									</div>
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
			<select multiple="multiple" id="multiselect1" class="chooseMultiple" size="10" style="width:99%;"  >
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
													<select multiple="multiple" class="chooseMultiple" size="10"  style="width:99%;"  >
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
						<div class="well">
						<hr class="simple" style="border-color:#c0c0c0;">
							<div class="row">
							  <div class="col-xs-6 col-sm-6 text-center" >
								<div class="page-content page-bjss">
									<div class="row"  >
										<div class="col-xs-12">
											  <div id="meterIDa" style="width: 100%;height:320px;">
											  </div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
								<div class="page-content page-bjss">
									<div class="row"  >
										<div class="col-xs-12">
											  <div id="meterIDb" style="width: 100%;height:320px;"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<hr class="simple" style="border-color:#c0c0c0;">
						</div>
				</div>
					
					<div class="col-sm-9">
					
							<div class="jarviswidget" data-widget-editbutton="false" role="widget">
								<header>
									<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
									<h2>销售金额</h2>
								</header>
								<div class="row">
									<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div>
											<div class="widget-body no-padding">
												<div id="dataViewA" style="height: 250px"></div>
											</div>
										</div>
									</article>
								</div>	
							</div>
							
							<div class="jarviswidget" data-widget-editbutton="false" role="widget">   
								<header>
									<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
									<h2>销售金额</h2>
								</header>
								<div class="row">
									<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div>
											<div class="widget-body no-padding">
												<div id="dayItemB" style="height: 250px"></div>
											</div>
										</div>
									</article>
								</div>	
							</div>
							
							<div class="jarviswidget" data-widget-editbutton="false" role="widget">   
								<header>
									<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
									<h2>销售数量</h2>
								</header>
								<div class="row">
									<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div>
											<div class="widget-body no-padding">
												<div id="dayItemC" style="height: 250px"></div>
											</div>
										</div>
									</article>
								</div>	
							</div>
							
							<div class="row">
								<div class="col-sm-12 col-md-12">
										<div class="row">
											<div class="col-sm-6 col-md-6">
												<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
														<h2>热销Top10</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewB" style="height: 250px"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-6 col-md-6">
												<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>   
														<h2>滞销Top10</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewC" style="height: 250px"></div>
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
            var myChart = echarts.init(document.getElementById('meterIDa'));
            var option = {
            title: {
                text: '\n\n\n\n\n\n\n\n\n\n\n\n\n环比销售金额',
                subtext:'1000.00',
                left:'center',  
               	textStyle: {      
                   fontWeight: 'bolder',
                   shadowBlur: 10
               },
               subtextStyle:{  
		            	fontSize: 18,
		                fontWeight: 'bolder',
		        		color: '#00E100'
		        }
            },
            toolbox: { //可视化的工具箱
                show: true,
                feature: {
                    restore: { //重置
                        show: false
                    },
                    saveAsImage: {//保存图片
                        show: false
                    }
                }
            },
            tooltip: { //弹窗组件
                formatter: "{a} <br/>{b} : {c}%"
            },
            series: [{
                name: '',
                type: 'gauge',
                min: -100,
	            max: 100,
	            axisLine: {            // 坐标轴线
	                lineStyle: {       // 属性lineStyle控制线条样式
	                	color: [[0.5, '#DA3939'],[1, '#0FE144']]
	                    /* ,width: 10 */
	                }
	            },
                detail: {formatter:'{value}%'
                	,textStyle:{
                		fontWeight:'bold',
                		fontSize:15
                	}	
                },
                data: [{value: -45, name: '销售额环比'}]
            }]

            };
            myChart.clear();
            myChart.setOption(option);
 		    $(window).resize(myChart.resize);
        </script>
<script type="text/javascript">
            var myChart = echarts.init(document.getElementById('meterIDb'));
            var option = {
            		 title: {
                         text: '\n\n\n\n\n\n\n\n\n\n\n\n\n同比销售金额',
                         subtext:'1000.00',
                         left:'center',  
                        	textStyle: {      
                            fontWeight: 'bolder',
                            shadowBlur: 10
                        },
                        subtextStyle:{  
         		            	fontSize: 18,
         		                fontWeight: 'bolder',
         		        		color: '#00E100'
         		        }
                     },
            toolbox: { //可视化的工具箱
                show: true,
                feature: {
                    restore: { //重置
                        show: false
                    },
                    saveAsImage: {//保存图片
                        show: false
                    }
                }
            },
            tooltip: { //弹窗组件
                formatter: "{a} <br/>{b} : {c}%"
            },
            series: [{
                name: '',
                type: 'gauge',
                min: -100,
	            max: 100,
	            axisLine: {            // 坐标轴线
	                lineStyle: {       // 属性lineStyle控制线条样式
	                	color: [[0.5, '#DA3939'],[1, '#0FE144']]
	                }
	            },
                detail: {formatter:'{value}%'
                	,textStyle:{
                		fontWeight:'bold',
                		fontSize:15
                	}
                
                },
                data: [{value: -12.13, name: '销售额同比'}]
            }]

            };
            myChart.clear();
            myChart.setOption(option);
 		    $(window).resize(myChart.resize);
        </script>
<script type="text/javascript">
function queryMethodE(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntSalesAnalysisAction!consumptionAnalysisViewB.action",  
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
        dataType: "json",
 		success:function(json){
     		var myChart2 = echarts.init(document.getElementById('dataViewB'));
     		var myColours = ['#CC3399','#FF3399','#7900FF','#BD514B'];
			var	option2 = {
			   	    title: {
			   	    	left: 'left',
			   	        //text: '最近30日会员消费排行Top10',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[0]+'"></span>{a0} : {c0}元<br/>'
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
			   	        data: ['会员消费金额']
		    	  		,left: 'center'
			   	    },
			   	    grid: {
			   	        left: '3%',
			   	        right: '6%',
			   	        bottom: '3%',
			   	        containLabel: true
			   	    },
			   	    xAxis: [{
			   	        type: 'value',
			   	        boundaryGap: [0, 0.01]
			   	    }],
			   	    yAxis: [{
			   	        type: 'category',    
			   	        data:json.nameArr       //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '会员消费金额',
			   	            type: 'bar',
			   	         	data: myNumToFixed2(json.valueArr), //参考  data: [30,78,111,178],
			   	         itemStyle :{
			 	            	normal:{
			 	            		color:''+myColours[0] 
			 	            	}
			 	            } ,
		    	            
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
				myChart2.clear();
				myChart2.setOption(option2);
	 		    $(window).resize(myChart2.resize);
	}});
}
function queryMethodD(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntSalesAnalysisAction!consumptionAnalysisViewC.action",  
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewC'));
     		var myColours = ['#629EA9','#FF3399','#7900FF','#BD514B'];
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        //text: '最近30日会员消费排行Top10',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[0]+'"></span>{a0} : {c0}件<br/>'
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
			   	        data: ['商品销售数量']
		    	  		,left: 'center'
			   	    },
			   	    grid: {
			   	        left: '3%',
			   	        right: '6%',
			   	        bottom: '3%',
			   	        containLabel: true
			   	    },
			   	    xAxis: [{
			   	        type: 'value',
			   	        boundaryGap: [0, 0.01]
			   	    }],
			   	    yAxis: [{
			   	        type: 'category',    
			   	        data:json.nameArr       //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '商品销售数量',
			   	            type: 'bar',
			   	         	data: myNumToFixed2(json.valueArr), //参考  data: [30,78,111,178],
			   	         itemStyle :{
			 	            	normal:{
			 	            		color:''+myColours[0] 
			 	            	}
			 	            } ,
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}件'
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
function queryMethodA(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntPurchasingBehaviorAction!purchasingBehaviorActionViewA.action",   
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime},
        dataType: "json",
 		success:function(json){
 			var myChart = echarts.init(document.getElementById('dayItem'));
			var myColours = ['#4ECFD1','#FF3399','#7900FF','#BD514B'];
			var option = {
		 	    title : {
		 	    	//text: '新增会员量视图',
		 	    },
		 	    tooltip : {
		 	        trigger: 'axis',
		 	         formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[0]+'"></span>{a0}: {c0}人<br/>'+
		 	      	    '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[1]+'"></span>{a1}: {c1}人 <br/>'+ 
		 	            '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[2]+'"></span>{a2}: {c2}人 <br/>'+ 
		 	         	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[3]+'"></span>{a3}: {c3}人 <br/>'+ 
		 	         	'',
		 	   		axisPointer: {
		    	        	 type: 'shadow',
		  	            crossStyle: {
		  	                color: '#FFFF33'
		  	            }
		  	        }
		 	    },
		 	    legend: {
		 	        data:['男性','女性','未知性别','新增会员']
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
		 	            data : json.timeStr//["17-01", "17-02", "17-03", "17-04", "17-05", "17-06", "17-07", "17-08", "17-09", "17-10", "17-11", "17-12"]
		 	        }
		 	    ],
		 	    yAxis : [
		 	        {
		 	            type : 'value',name: '数量/人'
		 	            	,axisLabel: {                   
		        	             formatter: function (value, index) {
		        	                	var str = value.toString()
		        	                	  if( str.indexOf('.') >= 0 ){
		        	                		return null;
		        	                	}else{
		        	                		return value;
		        	                	} 
		    		             }                
		    		        }
		 	        }
		 	    ],
		 	    series : [
		 	        {
		 	            name:'男性',
		 	            type:'line',
		 	            smooth:true,  
		 	            data:json.Man,//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10],  margin-bottom: 2px;
		 	            itemStyle :{
		 	            	normal:{
		 	            		color:''+myColours[0] 
		 	            	}
		 	            } 
		 	        },
		 	        {
		 	            name:'女性',
		 	            type:'line',
		 	            smooth:true,  
		 	            data:json.Woman,//[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2],
		 	            itemStyle :{
		 	            	normal:{
		 	            		color:''+myColours[1]
		 	            	}
		 	            } 
		 	        },
		 	        {
		 	            name:'未知性别',
		 	            type:'line',
		 	            smooth:true,
		 	            data:json.Unknown,//[3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3],
		 	            itemStyle :{
		 	            	normal:{
		 	            		color:''+myColours[2]
		 	            	}
		 	            } 
		 	        },
		 	        {
		 	            name:'新增会员',
		 	            type:'line',
		 	           	smooth:true,
		 	            data:json.Total,//[6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 15, 15],
		 	            itemStyle :{
		 	            	normal:{
		 	            		color:''+myColours[3]
		 	            	}
		 	            } 
		 	        },
		 	    ]
		 	};
					myChart.clear();
					myChart.setOption(option);
				    $(window).resize(myChart.resize);
	}});
}
function queryMethodB(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntSalesAnalysisAction!consumptionAnalysisViewA.action",  
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime},
        dataType: "json",
 		success:function(json){
 			var myChart = echarts.init(document.getElementById('dataViewA'));
 			var myColors = ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
					var option = {
		 		    	    title : {
		 		    	       // text: '最近30日会员消费方式分析',
		 		    	    },
		 		    	    tooltip : {
		 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}元<br/>'+
	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[1]+'"></span>{a1}: {c1}元<br/>'+ 
	 '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[2]+'"></span>{a2}: {c2}元<br/>'+ 
	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[3]+'"></span>{a3}: {c3}元<br/>'+ 
	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[4]+'"></span>{a4}: {c4}元<br/>'+ 
	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[5]+'"></span>{a5}: {c5}元<br/>'+
		'',
		 		    	   		axisPointer: {
		 		       	        	 type: 'shadow',
		 		     	            crossStyle: {
		 		     	                color: '#FFFF33'
		 		     	            }
		 		     	        }
		 		    	    },
		 		    	    legend: {
		 		    	        data:['现金消费','微信消费','支付宝消费','银行卡消费','其他消费']
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
		 		    	            data : json.timeStr//["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
		 		    	        }
		 		    	    ],
		 		    	    yAxis : [
		 		    	        {
		 		    	            type : 'value',name: '消费/元'
		 		    	        }
		 		    	    ],
		 		    	    series : [
		 		    	        {
		 		    	            name:'现金消费',
		 		    	            type:'bar',
		 		    	            stack: '消费总金额',
		 		    	            data: myNumToFixed2(json.Cash),   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[0] 
		 		    	            	}
		 		    	            } 
		 		    	        },
		 		    	        {
		 		    	            name:'微信消费',
		 		    	            type:'bar',
		 		    	            stack: '消费总金额',
		 		    	            data: myNumToFixed2(json.WeChat),   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[1] 
		 		    	            	}
		 		    	            } 
		 		    	        },
		 		    	        {
		 		    	            name:'支付宝消费',
		 		    	            type:'bar',
		 		    	            stack: '消费总金额',
		 		    	            data: myNumToFixed2(json.Alipay),    //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[2]
		 		    	            	}
		 		    	            } 
		 		    	        },
		 		    	       {
		 		    	            name:'银行卡消费',
		 		    	            type:'bar',
		 		    	            stack: '消费总金额',
		 		    	            data: myNumToFixed2(json.BankCard),    //[211, 344, 1466, 555, 666, 799, 921, 1043, 1999, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[3]
		 		    	            	}
		 		    	            } 
		 		    	        },
		 		    	        {
		 		    	            name:'其他消费',
		 		    	            type:'bar',
		 		    	            stack: '消费总金额',
		 		    	            data: myNumToFixed2(json.Other),    //  [211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[4] 
		 		    	            	}
		 		    	            } 
		 		    	        },
		 			 				    { 
		 			    	        	name: '消费总金额', 
		 			    	        	type: 'bar', 
		 			    	        	stack: '消费总金额', 
		 			    	        	label: { 
		 				    	        	normal: { 
		 					    	        	show: true, 
		 					    	        	position: 'insideBottom', 
		 					    	        	formatter:'{c}', 
		 					    	        	textStyle:{ color:''+myColors[5] } 
		 				    	        	}
		 			    	        	}, 
		 				    	        	itemStyle:{ 
		 				    	        	normal:{ 
		 				    	        	color:'rgba(128, 128, 128, 0)' 
		 				    	        	} 
		 				    	        	}, 
		 				    	        	data: myNumToFixed2(json.Total)    // [211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383]
		 			    	        	},
		 		    	    ]
		 		    	};
						myChart.clear();
						myChart.setOption(option);
			 		    $(window).resize(myChart.resize);
	}});
}
function queryMethodC(startTime,endTime,DivID) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonC.action",  
     	type: "POST",
     	data: {controlSQL:'JVtodayPassengerFlowTop',topNum:'24'},// jsonKeyValueForView：为视图view返回Json键值对; 用 JV 代表;  查询  '今日销售情况排行' 客流量  todayPassengerFlowTop
        dataType: "json",
 		success:function(json){
 			var testArrBar = [30.31, 10.31,90.0,50,150, 30 ,  50 ,  50 ,  50 , 150 , 250 , 350 ,  10.31 , 90.0 , 50 , 150 , 30 ,  50 ,  50 ,  50 , 150 , 250 , 350 ,30.31];
 			var testArrHB = [130.31,-110.31,70.0,20,130, 30 , -60 , -30 , -20 , 110 , 210 , 150 , -20.31 , 30.0 , 40 , 150 , 20 , -30 , -10 , -30 , 110 , 210 , 250 ,10.31];
 			var testArrTB = [30.31,-10.31,90.0,50,150, 30 , -50 , -50 , -50 , 150 , 250 , 350 , -10.31 , 90.0 , 50 , 150 , 30 , -50 , -50 , -50 , 150 , 250 , 350 ,30.31];
 			var myChart = echarts.init(document.getElementById(''+DivID));
 			var myColours = ['#4F82BB','#DA3939','#0FE144','#7900FF'];
			var	option = {
					 title: {
					    	left: 'center',
					        text: '',
					        subtext:''+json.isNull,
					        subtextStyle:{  //副标题样式
					            fontSize: 16,
					        	color: '#FF0033'
					        } 
					    },
		    	    tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[0]+'"></span>{a0} : {c0}元<br/>'
		    	        		+'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[1]+'"></span>{a1} : {c1}%<br/>'
		    	        		+'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[2]+'"></span>{a2} : {c2}%<br/>'
		    	        ,  
		    	        axisPointer: {
		    	        	 type: 'shadow',
		    	            crossStyle: {
		    	                color: '#FFFF33'
		    	            }
		    	        }
		    	    }, 
				    legend: {
				    	data:['销售金额','环比增幅','同比增幅']
				    },
				    grid: {
 		    	        left: '1%',
 		    	        right: '1%',
 		    	        bottom: '3%',
 		    	        containLabel: true
 		    	    },
				    xAxis : [
				        {  
				        	type : 'category',
				            data : json.stringXaxis //['8:00~9:00','9:00~10:00','10:00~11:00','10:00~11:00','11:00~12:00','11:00~12:00']
				        }
				    ],
				    yAxis : [
						        {
						            type : 'value',name: '销售金额/元'
						            	 
						        },
						        {type: 'value',scale: true,name: '比率',max: 600,min: -600,boundaryGap: [0.2, 0.2],axisLabel: {formatter: '{value} %'},}
						    ],
						    series : [
								        {
								            name:'销售金额',
								            type:'bar',
								            itemStyle: {
								                normal: {color:''+myColours[0]
								                }//设置柱子颜色
								            },
								            yAxisIndex: 0,
								            barWidth : 51,
								            data:myNumToFixed2(testArrBar),//参考： data:[80, 38, 51, 134, 120, 339, 50]  myNumToFixed2(json.numberYaxisMoney),
								        },
								        {
								            name:'环比增幅',
								            smooth:true,
								            type:'line',
								            itemStyle: {
								            	 normal: {color:''+myColours[1]
									                }//设置柱子颜色
								            },
								            yAxisIndex: 1,
								            data:myNumToFixed2(testArrHB),     //参考：[-12, 282, -321, 204, -190, 30, -110]
								        }
								         ,{
								            name:'同比增幅',
								            smooth:true,
								            type:'line',
								            stack: '总量',
								            itemStyle: {
								            	 normal: {color:''+myColours[2]
									                }//设置柱子颜色
								            },
								            yAxisIndex: 1,
								            data:myNumToFixed2(testArrTB),     //[null,120, 332, 101, 259, 220, 220, 190,null]    //参考： data:[null,120, 332, 101, 259, 220, 220, 190,null]
								        } 
								    ]	    
				};
			myChart.clear();
			myChart.setOption(option);
 		    $(window).resize(myChart.resize);
	
	}});
}
		$(document).ready(function() {
			pageSetUp();//初始化小图
			 
			queryMethodA('Day30','Day30');
			queryMethodB('Day30','Day30');
			queryMethodC('Day30','Day30','dayItemB');
			queryMethodC('Day30','Day30','dayItemC');
			queryMethodD('Day30','Day30');
			queryMethodE('Day30','Day30');
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
			
		});

</script>

