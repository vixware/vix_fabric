<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
				<div class="row">
					<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
						<h1 class="page-title txt-color-blueDark">
							<i class="fa-fw fa fa-user"></i>会员管理<span>>会员分析</span>
						</h1>
					</div>
					<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<h1 class="txt-color-red">
								<span class="semi-bold">从2017年9月15日注册********起</span>
							</h1>
							<hr>
							<div class="row" style="padding-top:20px;">
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#ffa160;">
										<div class="row">
											<div class="col-md-4">
												<img src="img/xz/jrxl.png" class="img-responsive" alt="img">
											</div>
											<div class="col-md-8 text-right txt-color-white">
												<strong class="">会员总数量</strong><br>
												<strong class="font-lg">2750 人</strong>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#7cc522;">
										<div class="row">
											<div class="col-md-4">
												<img src="img/xz/jrxl.png" class="img-responsive" alt="img">
											</div>
											<div class="col-md-8 text-right txt-color-white">
												<strong class="">会员平均消费</strong><br>
												<strong class="font-lg">￥2750.00</strong>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#5fd0ff;">
										<div class="row">
											<div class="col-md-4">
												<img src="img/xz/jrxl.png" class="img-responsive" alt="img">
											</div>
											<div class="col-md-8 text-right txt-color-white">
												<strong class="">会员总消费</strong><br>
												<strong class="font-lg">￥17250.00</strong>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<form id="productForm" class="form-horizontal bv-form" novalidate="novalidate"  style="padding-top:15px;">
										<fieldset>
											<div class="form-group has-feedback">
												<label class="col-lg-1 control-label">
													<strong>选择日期：</strong>
												</label>
												<div class="col-lg-3">
													<div class="input-group">
														<span class="input-group-addon">
															<i class="fa fa-calendar"></i>
														</span>
														<input class="form-control" name="amount" placeholder="2017年8月15日 - 2017年9月15日" data-bv-field="amount" type="text">
													</div>
												</div>
												<div class="col-lg-2">
													<button class="btn btn-primary btn-sm" type="button" data-toggle="#jobInfo">
														<i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;
														<strong>查询</strong>
													</button>
												</div>
												<label class="col-lg-6 control-label">
													<strong>共发送<span class="txt-color-red">1条</span>信息，激活<span class="txt-color-red">10个</span>回头客户，产生消费<span class="txt-color-red">120元</span>。</strong>
												</label>
											</div>
										</fieldset>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<hr class="simple" style="border-color:#c0c0c0;">
									<div class="row">
										<div class="col-xs-3 col-sm-3 text-center">
											<h5>
												<span class="font-lg txt-color-red padding-5">142人</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">会员总人数</strong>
											</h5>
											<div class="padding-5"></div>
											<p><strong class="txt-color-pink">与昨天对比：</strong>增加 61人 </p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
												<span class="font-lg txt-color-red padding-5">7123条</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">信息发送量</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											  <strong class="txt-color-pink">营销：</strong>1条&nbsp;&nbsp;
											  <strong class="txt-color-blue">生日提醒：</strong>10条 
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
												<span class="font-lg txt-color-red padding-5">423人</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">激活回头客消费</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											  <strong class="txt-color-pink">人数：</strong>12人&nbsp;&nbsp;
											  <strong class="txt-color-blue">人均消费：</strong>123元 
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
												<span class="font-lg txt-color-red padding-5">激活效率分析</span>
											</h5>
											<div class="padding-5"></div>
											<p>每天回头客唤醒成本大约<strong class="txt-color-pink"> 3元</strong></p>
											<p>每条营销带来收入<strong class="txt-color-pink"> 12元</p>
										</div>
									</div>
									<hr class="simple" style="border-color:#c0c0c0;">
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
							<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="jarviswidget">
									<header>
										<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i>
										</span>
										<h2>今日客流量排行</h2>
									</header>
									<div id="kolOrderStatusId">
										<div class="widget-body no-padding">
											<div id="patient" style="height: 350px"></div>
											<script type="text/javascript">
												var kolOrderStatus = echarts.init(document.getElementById('patient'));
												var	option1 = {
													    tooltip : {
													        trigger: 'axis'
													    },
													    legend: {
													        data:['销售额','销售单数']
													    },
													    xAxis : [
													        {
													            data : ['8:00~9:00','9:00~10:00','10:00~11:00','10:00~11:00','11:00~12:00','11:00~12:00']
													        }
													    ],
													    yAxis : [
													        {
													            type : 'value',name:'销售额/元'
													        },
													        {
													        	type:'value',name: '销售单数'
													        }
													    ],
													    series : [
															{
															    name:'销售额',
															    type:'bar',
															    data:["100","230","100","230","100","230"],
															    itemStyle :{
											    	            	normal:{
											    	            		color:'#8085E9' 
											    	            	}
											    	            } 
															},
													        {
													            name:'销售单数',
													            type:'bar',
													            data:["10","23","16","27","18","78"],
													            yAxisIndex: 1,
													            itemStyle :{
											    	            	normal:{
											    	            		color:'#66CC66' 
											    	            	}
											    	            } 
													        }
													    ]
													};
												kolOrderStatus.setOption(option1);
											</script>
										</div>
									</div>
								</div>
							</article>
						</div>
		</div>
<script>
	$(".sign").click(function(){
		$(".alert_box").show();
	});
	$(".alert_box .close_box").click(function(){
		$(".alert_box").hide();
	});
	$(".sign_mess").click(function(){
		$(".alert_box_vip").show();
	});
	$(".alert_box_vip .close_box").click(function(){
		$(".alert_box_vip").hide();
	});
	$(document).ready(function() {
		pageSetUp();
		// PAGE RELATED SCRIPTS
		$('.tree > ul').attr('role', 'tree').find('ul').attr('role', 'group');
		$('.tree').find('li:has(ul)').addClass('parent_li').attr('role', 'treeitem').find(' > span').attr('title', 'Collapse this branch').on('click', function(e) {
			var children = $(this).parent('li.parent_li').find(' > ul > li');
			if (children.is(':visible')) {
				children.hide('fast');
				$(this).attr('title', 'Expand this branch').find(' > i').removeClass().addClass('fa fa-lg fa-plus-circle');
			} else {
				children.show('fast');
				$(this).attr('title', 'Collapse this branch').find(' > i').removeClass().addClass('fa fa-lg fa-minus-circle');
			}
			e.stopPropagation();
		});			
		 $('.knob').knob({
		        change: function (value) {
		            //console.log("change : " + value);
		        },
		        release: function (value) {
		            //console.log(this.$.attr('value'));
		            //console.log("release : " + value);
		        },
		        cancel: function () {
		            //console.log("cancel : ", this);
		        }
		    });
	})
</script>
<script type="text/javascript">
	pageSetUp();
	$(document).ready(function() {
		
		// PAGE RELATED SCRIPTS
		$('.tree > ul').attr('role', 'tree').find('ul').attr('role', 'group');
		$('.tree').find('li:has(ul)').addClass('parent_li').attr('role', 'treeitem').find(' > span').attr('title', 'Collapse this branch').on('click', function(e) {
			var children = $(this).parent('li.parent_li').find(' > ul > li');
			if (children.is(':visible')) {
				children.hide('fast');
				$(this).attr('title', 'Expand this branch').find(' > i').removeClass().addClass('fa fa-lg fa-plus-circle');
			} else {
				children.show('fast');
				$(this).attr('title', 'Collapse this branch').find(' > i').removeClass().addClass('fa fa-lg fa-minus-circle');
			}
			e.stopPropagation();
		});			
		 $('.knob').knob({
		        change: function (value) {
		            //console.log("change : " + value);
		        },
		        release: function (value) {
		            //console.log(this.$.attr('value'));
		            //console.log("release : " + value);
		        },
		        cancel: function () {
		            //console.log("cancel : ", this);
		        }
		 });
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		 /* 线图 */
	    var lineOptions = {
			    ///Boolean - Whether grid lines are shown across the chart
			    scaleShowGridLines : true,
			    //String - Colour of the grid lines
			    scaleGridLineColor : "rgba(0,0,0,.05)",
			    //Number - Width of the grid lines
			    scaleGridLineWidth : 1,
			    //Boolean - Whether the line is curved between points
			    bezierCurve : true,
			    //Number - Tension of the bezier curve between points
			    bezierCurveTension : 0.4,
			    //Boolean - Whether to show a dot for each point
			    pointDot : true,
			    //Number - Radius of each point dot in pixels
			    pointDotRadius : 4,
			    //Number - Pixel width of point dot stroke
			    pointDotStrokeWidth : 1,
			    //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
			    pointHitDetectionRadius : 20,
			    //Boolean - Whether to show a stroke for datasets
			    datasetStroke : true,
			    //Number - Pixel width of dataset stroke
			    datasetStrokeWidth : 2,
			    //Boolean - Whether to fill the dataset with a colour
			    datasetFill : true,
			    //Boolean - Re-draw chart on page resize
		        responsive: true,
			    //String - A legend template
		    };

		    var lineData = { labels: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
		        datasets: [
			        {
			            label: "My First dataset",
			            fillColor: "rgba(220,220,220,0.2)",
			            strokeColor: "rgba(220,220,220,1)",
			            pointColor: "rgba(220,220,220,1)",
			            pointStrokeColor: "#fff",
			            pointHighlightFill: "#fff",
			            pointHighlightStroke: "rgba(220,220,220,1)",
			            data: [65, 59, 80, 81, 56, 55, 40]
			        },
			        
			    ]
		    };
		    var ctx = document.getElementById("lineChart1").getContext("2d");
		    var myNewChart = new Chart(ctx).Line(lineData, lineOptions);
	})
</script>