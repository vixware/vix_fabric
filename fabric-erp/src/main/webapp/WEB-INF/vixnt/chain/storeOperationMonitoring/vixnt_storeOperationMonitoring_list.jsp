<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-list-alt fa-fw "></i> 店铺运营监控 <span>> 店铺运营监控 </span>
			</h1>
		</div>
	</div>
	
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="text-right">
							<a href="javascript:void(0);" class="btn btn-primary sign">会员签到</a> <a href="javascript:void(0);" class="btn btn-warning sign_mess">会员信息</a>
							</div>
							   <div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<hr class="simple" style="border-color: #c0c0c0;">
									<div class="row">
										<div class="col-xs-3 col-sm-3 text-center">
											<h5>
												<span class="font-lg txt-color-red padding-5">142人</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">当前顾客数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
												<strong class="txt-color-pink">与昨天对比：</strong>增加 61人
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
											<h5>
												<span class="font-lg txt-color-red padding-5">123人</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日到店会员数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
												<strong class="txt-color-pink">与昨天对比：</strong>增加 11人
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
											<h5>
												<span class="font-lg txt-color-red padding-5">1423人</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日到店顾客总数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
												<strong class="txt-color-pink">与昨天对比：</strong>增加 211人
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
											<h5>
												<span class="font-lg txt-color-red padding-5">24分钟</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日平均停留时长</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
												<strong class="txt-color-pink">与昨天对比：</strong>增加 3分钟
											</p>
											</div>
										</div>
										<hr class="simple" style="border-color: #c0c0c0;">
									</div>
								</div>
						</div>
					</div>
				</div>
	
	<div class="row">
		<div class="col-sm-12 col-md-12">
			<!-- <div class="well">  well -->
				<!--  <br> --> 
				<div class="row">
					<div class="col-sm-6 col-md-6" >
						<div id="wid-id-1" class="jarviswidget jarviswidget-color-blue" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-sortable="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-sitemap"></i>
								</span>
								<h2>今日客流量排行榜</h2>
							</header>
							<div class="row">

								<div class="col-sm-12 col-md-12" >
									<table class="table table-striped table-forum"  style="height: 400px">
										<thead>
											<tr>
												<th class="text-right" colspan="2">占比</th>
												<th class="text-center hidden-xs hidden-sm" style="width: 200px;">专区名称</th>
												<th class="hidden-xs hidden-sm" style="width: 200px;">与昨天对比</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="text-center" style="width: 40px;"><i class="fa fa-globe fa-1x txt-color-red"></i></td>
												<td>
													<h4>
														<a href="javascript:void(0);"> 21.2% </a>
													</h4>
												</td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);">学习用品区</a></td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);"><i class="fa fa-long-arrow-up txt-color-green"></i> 10.2%</a></td>
											</tr>
											<tr>
												<td class="text-center" style="width: 40px;"><i class="fa fa-globe fa-1x txt-color-blue"></i></td>
												<td>
													<h4>
														<a href="javascript:void(0);"> 18.2% </a>
													</h4>
												</td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);">服装区</a></td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);"><i class="fa fa-long-arrow-up txt-color-green"></i> 10.2%</a></td>
											</tr>
											<tr>
												<td class="text-center" style="width: 40px;"><i class="fa fa-globe fa-1x txt-color-green"></i></td>
												<td>
													<h4>
														<a href="javascript:void(0);"> 60.6% </a>
													</h4>
												</td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);">生活用品区</a></td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);"><i class="fa fa-long-arrow-down txt-color-red"></i> 10.2%</a></td>
											</tr>
											<tr>
												<td class="text-center" style="width: 40px;"><i class="fa fa-globe fa-1x txt-color-green"></i></td>
												<td>
													<h4>
														<a href="javascript:void(0);"> 60.6% </a>
													</h4>
												</td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);">生活用品区</a></td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);"><i class="fa fa-long-arrow-down txt-color-red"></i> 10.2%</a></td>
											</tr>
											<tr>
												<td class="text-center" style="width: 40px;"><i class="fa fa-globe fa-1x txt-color-pink"></i></td>
												<td>
													<h4>
														<a href="javascript:void(0);"> 60.6% </a>
													</h4>
												</td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);">生活用品区</a></td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);"><i class="fa fa-long-arrow-down txt-color-red"></i> 10.2%</a></td>
											</tr>
											<tr>
												<td class="text-center" style="width: 40px;"><i class="fa fa-globe fa-1x txt-color-orange"></i></td>
												<td>
													<h4>
														<a href="javascript:void(0);"> 60.6% </a>
													</h4>
												</td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);">生活用品区</a></td>
												<td class="text-center hidden-xs hidden-sm"><a href="javascript:void(0);"><i class="fa fa-long-arrow-down txt-color-red"></i> 10.2%</a></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>


					<div class="col-sm-6 col-md-6" >

						<div class="jarviswidget jarviswidget-color-blue" id="wid-id-0" data-widget-colorbutton="false" data-widget-fullscreenbutton="false" data-widget-editbutton="false" data-widget-sortable="false">
							<header>
								<span class="widget-icon"> <i class="fa fa-sitemap"></i>
								</span>
								<h2>今日新增客户统计</h2>
							</header>
							<div>
								<div class="jarviswidget-editbox"></div>
									<div class="widget-body">
											<div id="dataViewC" style="height: 405px"></div>
									</div>
							</div>
						</div>
					</div>
				</div>
			<!-- </div>  well -->
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-12 col-md-12">
			<!-- <div class="well">  well -->
				   <div class="row" >
					<article class="col-sm-12 col-md-12 col-lg-12">
						<div id="wid-id-1" class="jarviswidget jarviswidget-color-blue" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-sortable="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-sitemap"></i>
								</span>
								<h2>数据概览</h2>
							</header>
							<div>

								<!-- widget content -->
								<div class="col-sm-3 col-md-3 text-center">
									<h3>进店率</h3>
									<div class="widget-body">
										<form>
											<fieldset>
												<div class="knobs-demo">
													<div>
														<input class="knob" data-width="120" data-height="120" data-displayInput=true value="56" data-displayPrevious=true data-fgColor="#428BCA">
													</div>
												</div>
											</fieldset>
										</form>
									</div>
									<p>顾客总数1000人</p>
									<p>进店560人</p>
								</div>
								<div class="col-sm-3 col-md-3 text-center">
									<h3>驻留率</h3>
									<div class="widget-body">
										<form>
											<fieldset>
												<div class="knobs-demo">
													<div>
														<input class="knob" data-width="120" data-height="120" data-displayInput=true value="33" data-displayPrevious=true data-fgColor="#71843F">
													</div>
												</div>
											</fieldset>
										</form>
									</div>
									<p>顾客总数1000人</p>
									<p>进店330人</p>
								</div>
								<div class="col-sm-3 col-md-3 text-center">
									<h3>新老用户数</h3>
									<div class="widget-body">
										<form>
											<fieldset>
												<div class="knobs-demo">
													<div>
														<input class="knob" data-width="120" data-height="120" data-displayInput=true value="10" data-displayPrevious=true data-fgColor="#F3CC33">
													</div>
												</div>
											</fieldset>
										</form>
									</div>
									<p>顾客总数1000人</p>
									<p>新客户100人</p>
								</div>
								<div class="col-sm-3 col-md-3 text-center">
									<h3>wife使用率</h3>
									<div class="widget-body">
										<form>
											<fieldset>
												<div class="knobs-demo">
													<div>
														<input class="knob" data-width="120" data-height="120" data-displayInput=true value="80" data-displayPrevious=true data-fgColor="#A62D4C">
													</div>
												</div>
											</fieldset>
										</form>
									</div>
									<p>顾客总数1000人</p>
									<p>wife使用800人</p>
								</div>
							</div>
						</div>
					</article>
				</div>
		<!-- 	</div>  well -->
		</div>
	</div>
	
</div>
<script type="text/javascript">
function queryMethod_C(startTime,endTime) {
	 
 			var myChart = echarts.init(document.getElementById('dataViewC'));
 			var myColors = ['#193A77', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
					var option = {
		 		    	    title : {
		 		    	       // text: '最近30日会员消费方式分析',
		 		    	    },
		 		    	    tooltip : {
		 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}人<br/>'+
		'',
		 		    	   		axisPointer: {
		 		       	        	 type: 'shadow',
		 		     	            crossStyle: {
		 		     	                color: '#FFFF33'
		 		     	            }
		 		     	        }
		 		    	    },
		 		    	    legend: {
		 		    	        data:['新增客户']
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
		 		    	            data : ["10-30","10-31","11-01","11-02","11-03","11-04"]

		 		    	        }
		 		    	    ],
		 		    	    yAxis : [
		 		    	        {
		 		    	            type : 'value',name: '消费次数/次'
		 		    	            	,axisLabel: { //前面是y轴屏蔽小数  ,axisLabel                 
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
		 		    	            name:'新增客户',
		 		    	            type:'line',
		 		    	            smooth:true, 
		 		    	            data:[211, 344, 466, 555, 166, 299],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[0] 
		 		    	            	}
		 		    	            } 
		 		    	        }	   
		 		    	    ]
		 		    	};
						myChart.clear();
						myChart.setOption(option);
			 		    $(window).resize(myChart.resize);
	
}
$(document).ready(function() {
	pageSetUp();
	// PAGE RELATED SCRIPTS
	queryMethod_C('Day30','Day30');
	$(".knob").knob({
        max: 940,
        min: 500,
        thickness: .3,
        fgColor: '#2B99E6',
        bgColor: '#303030',
        'release':function(e){
            $('#img').animate({width:e});
        }
    });
     
    $(".knob2").knob({
        'release':function(e){
            $('#img').animate({width:e});
        }
    });
})
</script>


