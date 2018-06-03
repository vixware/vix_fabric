<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<style>
.myHoverLine:hover {
    /* 指定a标签鼠标移动上去加下划线 */
	border-bottom: 1px solid #000000;
	color: #fff;
	text-decoration: none;
}

.myHoverLine { 
    /* 指定a标签鼠标移出时，字体颜色为白色 */
	color: #fff;
}
</style>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-list-alt fa-desktop "></i> 工作台
			</h1>
		</div>
		<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
			<div class="text-right">
				<button class="btn btn-primary" type="button" onclick="addCustomer();" style="margin-top: 7px;">会员办理</button>
				<button class="btn btn-warning" type="button" onclick="customerPay();" style="margin-top: 7px;">会员充值</button>
				<button class="btn btn-info" type="button" onclick="customerLoss();" style="margin-top: 7px;">会员卡挂失</button>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12 col-md-12">
			<div class="well">
				<div class="row" style="padding-top: 20px;">
					<div class="col-md-2">
						<div class="well well-lg" style="background-color: #ffa160;">
							<div class="row">
								<div class="col-md-12 text-center txt-color-white">
									<strong class="">今日总消费金额 </strong><br> <a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('Money{Today}','workHomePage');"><strong class="font-lg"><span id="d05Tnum">¥0.00</span></strong></a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-2">
						<div class="well well-lg" style="background-color: #7cc522;">
							<div class="row">
								<div class="col-md-12 text-center txt-color-white">
									<strong class="">今日进店客户</strong><br>
									<!-- workHomePage代表工作台 -->
									<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('IntoTS{Today}','workHomePage');"><strong class="font-lg"><span id="d07Tnum">&nbsp;0人</span></strong></a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-2">
						<div class="well well-lg" style="background-color: #5fd0ff;">
							<div class="row">
								<div class="col-md-12 text-center txt-color-white">
									<strong class="">今日新增会员卡</strong><br> <a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('IncreaseC{Today}','workHomePage');"><strong class="font-lg"><span id="d06Tnum">&nbsp;0张</span></strong></a>
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
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								<label class="control-label"> <strong>选择日期： </strong>
								</label>
							</div>
							<div class="input-group">
								<input placeholder="时间" style="width: 130px;" id="startTimeA" name="startCreateTime" data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
							</div>
							<button type="button" class="btn btn-info" onClick="controlJump();">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button type="button" class="btn btn-default" onclick="$('#startTimeA').val('');">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
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
						<hr class="simple" style="border-color: #c0c0c0;">
						<div class="row">
							<div class="col-xs-3 col-sm-3 text-center">
								<h5>
									<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('OrderNum{Today}','workHomePage');"><span class="font-lg txt-color-red padding-5"><span id="d01Tnum">0.00元</span></span></a>
									<div class="padding-5"></div>
									<strong class="txt-color-greenLight">今日总营业收入</strong>
								</h5>
								<div class="padding-5"></div>
								<p>
									<strong class="txt-color-pink">与昨天对比：</strong><span id="d01Cstr">增加</span><span id="d01Cnum">0.00元</span>
								</p>
							</div>
							<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
								<h5>
									<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('RechargeM{Today}','workHomePage');"><span class="font-lg txt-color-red padding-5"><span id="d02Tnum">0.00元</span></span></a>
									<!-- RechargeM 充值记录	 -->
									<div class="padding-5"></div>
									<strong class="txt-color-greenLight">今日总储值收入</strong>
								</h5>
								<div class="padding-5"></div>
								<p>
									<strong class="txt-color-pink">与昨天对比：</strong><span id="d02Cstr">增加</span><span id="d02Cnum">0.00元</span>
								</p>
							</div>
							<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
								<h5>
									<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('NewAdd{Today}','workHomePage');"><span class="font-lg txt-color-red padding-5"><span id="d03Tnum">0</span>人</span></a>
									<div class="padding-5"></div>
									<strong class="txt-color-greenLight">今日新增会员</strong>
								</h5>
								<div class="padding-5"></div>
								<p>
									<strong class="txt-color-pink">与昨天对比：</strong><span id="d03Cstr">增加</span><span id="d03Cnum">0</span>人
								</p>
							</div>
							<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
								<h5>
									<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('OrderNum{Today}','workHomePage');"><span class="font-lg txt-color-red padding-5"><span id="d04Tnum">0</span>笔</span></a>
									<div class="padding-5"></div>
									<strong class="txt-color-greenLight">今日订单数量</strong>
								</h5>
								<div class="padding-5"></div>
								<p>
									<strong class="txt-color-pink">与昨天对比：</strong><span id="d04Cstr">增加</span><span id="d04Cnum">0</span>笔
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
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget">
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
					<h2>今日客流量</h2>
				</header>
				<div class="row">
					<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div>
							<div class="widget-body no-padding" style="width: 99%">
								<div id="dataViewC" style="height: 350px"></div>
							</div>
						</div>
					</article>
				</div>
			</div>
		</article>
	</div>

	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget">
				<header>
					<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
					<h2>今日销售情况</h2>
				</header>
				<div class="row">
					<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div>
							<div class="widget-body no-padding" style="width: 99%">
								<div id="patient" style="height: 350px"></div>
							</div>
						</div>
					</article>
				</div>
			</div>
		</article>
	</div>

	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget">
				<header>
					<span class="widget-icon"> <i class="fa fa-sitemap"></i>
					</span>
					<h2>今日销售排行榜</h2>
				</header>
				<div>
					<div class="col-sm-12 col-md-6" id="mytableTopB"></div>
					<div class="col-sm-12 col-md-6" id="mytableTopA"></div>
				</div>
			</div>
		</article>
	</div>

</div>
<script type="text/javascript">
	function addCustomer() {
		$.ajax({
		url : '${nvix}/nvixnt/nvixCustomerAccountAction!goSaveOrUpdate.action',
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	}
	function customerPay() {
		$.ajax({
		url : '${nvix}/nvixnt/nvixCustomerAccountAction!pay.action',
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	}
	function customerLoss() {
		$.ajax({
		url : '${nvix}/nvixnt/nvixCustomerAccountAction!goLossCustomer.action',
		success : function(html) {
			layer.open({
			type : 1,
			title : "会员挂失",
			area : [ '825px', '355px' ],
			closeBtn : 1,
			content : html,
			btn : [ '确定', '关闭' ],
			yes : function(index, layero) {
				var clipId = $("#customerAccountClipId").val();
				if (clipId != '') {
					layer.close(index);
					$.ajax({
					url : '${nvix}/nvixnt/nvixCustomerAccountClipAction!report.action',
					data : {
						'customerAccountClipId' : clipId
					},
					success : function(result) {
						var r = result.split(":");
						if (r[0] == "1") {
							layer.alert(r[1]);
							loadContent('', '${nvix}/nvixnt/nvixCustomerAccountClipAction!goList.action');
						} else {
							layer.alert(r[1]);
						}
					}
					});
				} else {
					layer.alert('请选择会员卡!');
				}
			}
			});
		}
		});
	}
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	function judgeTimeLargeNow(timeStr) {//获取的时间thetime 格式应为  2016-5-28
		var timeStrDate = new Date(Date.parse(timeStr.replace(/-/g, "/")));
		var curDate = new Date();
		if (timeStrDate > curDate) {
			return 1;/*return 1 时  alert("请重新选择开始时间，开始时间不能超过今天"); */
		} else {
			return 6;
		}
	}
	function controlJump() {
		var startTime = $("#startTimeA").val();
		var state = 0;
		if (startTime == null || startTime.length < 3) {
			layer.alert("请选择时间!");
			state++;
		}
		if (judgeTimeLargeNow(startTime) == 1) {
			state++;
			layer.alert("请重新选择时间,时间不能超过今天!");
		}
		if (state == 0) {
			var queryTime = startTime + startTime + '{rN-pE{ctt}}';//ctt代表返回 nvix_content.jsp页面
			$.ajax({
			url : '${nvix}/nvixnt/vixntMemberManageDataAction!searchTimeWHPage.action',
			data : {
				queryTime : queryTime
			},
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
		}
	}

	function controlSQLWdMethod(condition, returnPage) {//传递查询条件给列表
		newHtml(condition, returnPage);
	}
	function newHtml(controlSQL, returnPage) {//新增查看新页面;  
		$.ajax({
		url : '${nvix}/nvixnt/vixntMemberManageDataAction!goMemberListNewHtml.action',
		data : {
		controlSQL : controlSQL,
		returnPage : returnPage
		},
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function myNumToFixed2(objArr) {//我的把数组保留2位小数返回新数组
		var newArr = [];
		if (Array.isArray(objArr)) {
			for (var x = 0; x < objArr.length; x++) {
				newArr[x] = parseFloat(objArr[x].toFixed(2));
			}
		}
		return newArr;
	}
	function queryMethod_C(startTime, endTime) {
		$.ajax({
		url : "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonCSylb.action",
		type : "POST",
		data : {
		startTime : startTime,
		endTime : endTime
		},
		dataType : "json",
		success : function(json) {
			var myChart = echarts.init(document.getElementById('dataViewC'));
			var myColours = [ '#f7a35c', '#990099', '#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1', '#66FF00' ];
			var option = {
			title : {
			left : 'center',
			text : ''
			},
			tooltip : {
			trigger : 'axis',
			formatter : '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[1]+'"></span>{a0} : {c0}次<br/>',
			axisPointer : {
			type : 'shadow',
			crossStyle : {
				color : '#FFFF33'
			}
			}
			},
			legend : {
				data : [ '客户消费次数' ]
			},
			grid : {
			left : '1%',
			right : '1%',
			bottom : '3%',
			containLabel : true
			},
			xAxis : [ {
			type : 'category',
			data : json.timeStr
			//['8:00~9:00','9:00~10:00','10:00~11:00','10:00~11:00','11:00~12:00','11:00~12:00']
			} ],
			yAxis : [ {
			type : 'value',
			name : '消费次数/次',
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
			} ],
			series : [ {
			name : '客户消费次数',
			type : 'line',
			smooth : true,
			data : json.memberOrderPassengersArr,//["10","23","16","27","18","78"],
			itemStyle : {
				normal : {
					color : '' + myColours[1]
				}
			}
			} ]
			};
			myChart.clear();
			myChart.setOption(option);
			$(window).resize(myChart.resize);
		}
		});
	}
	$(document)
			.ready(function() {
				pageSetUp();
				// PAGE RELATED SCRIPTS

				$.ajax({
				url : "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonA.action",
				type : "POST",
				data : {
					controlSQL : 'NTtotalBusinessIncome'
				},//查询‘总营业收入’ numRt：返回数字  用 NT 代表，  totalBusinessIncome 总营业收入
				dataType : "json",
				success : function(json) {
					//说明：后缀Cnum代表compare number 就是要在页面显示的比较后的数字
					//说明：后缀Cstr代表compare String 就是要在页面显示的比较后的字符串
					//说明：后缀Tnum代表Today number 就是要在页面显示的今天的数据，数字；
					$("#d01Cnum").text((json.NTtotalBusinessIncomeCnum).toFixed(2) + '元');
					$("#d01Cstr").text(json.NTtotalBusinessIncomeCstr);
					$("#d01Tnum").text((json.NTtotalBusinessIncomeTnum).toFixed(2) + '元');
				}
				});
				$.ajax({
				url : "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonA.action",
				type : "POST",
				data : {
					controlSQL : 'NTtotalRecharge'
				},//查询‘总储值收入’ numRt：返回数字  用 NT 代表，  NTtotalRecharge 
				dataType : "json",
				success : function(json) {
					$("#d02Cnum").text((json.NTtotalRechargeCnum).toFixed(2) + '元');
					$("#d02Cstr").text(json.NTtotalRechargeCstr);
					$("#d02Tnum").text((json.NTtotalRechargeTnum).toFixed(2) + '元');
				}
				});
				/* $.ajax({    
					url: "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonA.action",  
				 	type: "POST",
				 	data: {controlSQL:'NTnoPaymentOrder'},// numRt：返回数字  用 NT 代表，  NTnoPaymentOrder未支付订单
				    dataType: "json",
					success:function(json){
						$("#d03Cnum").text(json.NTnoPaymentOrderCnum ); 
						$("#d03Cstr").text(json.NTnoPaymentOrderCstr);
						$("#d03Tnum").text(json.NTnoPaymentOrderTnum ); 
				}}); */
				$.ajax({
				url : "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonA.action",
				type : "POST",
				data : {
					controlSQL : 'NTregisterMemberNum'
				},// numRt：返回数字  用 NT 代表，  registerMemberNum 今日注册会员人数
				dataType : "json",
				success : function(json) {
					$("#d03Cnum").text(json.NTregisterMemberNumCnum);
					$("#d03Cstr").text(json.NTregisterMemberNumCstr);
					$("#d03Tnum").text(json.NTregisterMemberNumTnum);
				}
				});
				/* $.ajax({    
					url: "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonA.action",  
				 	type: "POST",
				 	data: {controlSQL:'NTtotalDiscountMoney'},// numRt：返回数字  用 NT 代表，  NTtotalDiscountMoney总折扣金额
				    dataType: "json",
					success:function(json){
						$("#d04Cnum").text((json.NTtotalDiscountMoneyCnum).toFixed(2)); 
						$("#d04Cstr").text(json.NTtotalDiscountMoneyCstr);
						$("#d04Tnum").text((json.NTtotalDiscountMoneyTnum).toFixed(2)); 
				}}); */
				$.ajax({
				url : "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonA.action",
				type : "POST",
				data : {
					controlSQL : 'NTtodayOrdersNumBlock'
				},// NTtodayOrdersNumBlock 今日订单数目，付款或者不付款都查询  Block数据块显示
				dataType : "json",
				success : function(json) {
					$("#d04Cnum").text(json.NTtodayOrdersNumBlockCnum);
					$("#d04Cstr").text(json.NTtodayOrdersNumBlockCstr);
					$("#d04Tnum").text(json.NTtodayOrdersNumBlockTnum);
				}
				});
				$.ajax({
				url : "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonB.action",
				type : "POST",
				data : {
					controlSQL : 'NTtodayConsumptionMoney'
				},//numRt：返回数字  用 NT代表，NTtodayConsumptionMoney 今日‘总消费金额’
				dataType : "json",
				success : function(json) {
					$("#d05Tnum").text('¥' + (json.NTtodayConsumptionMoneyTnum).toFixed(2));
				}
				});
				$.ajax({
				url : "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonB.action",
				type : "POST",
				data : {
					controlSQL : 'NTtodayAddCardNum'
				},// todayAddCardNum 今日新增会员卡
				dataType : "json",
				success : function(json) {
					$("#d06Tnum").html("&nbsp;" + json.NTtodayAddCardNumTnum + "张");
				}
				});
				$.ajax({
				url : "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonB.action",
				type : "POST",
				data : {
					controlSQL : 'NTtodayIntoStoreCustomers'
				},//NTtodayIntoStoreCustomers 今日进店客户，(充值的+下订单付费的+下订单不付费的)
				dataType : "json",
				success : function(json) {
					$("#d07Tnum").html("&nbsp;" + json.NTtodayIntoStoreCustomersTnum + "人");
				}
				});
				$
						.ajax({
						url : "${nvix}/nvixnt/vixNtIndexAction!nvixContentJsonC.action",
						type : "POST",
						data : {
						controlSQL : 'JVtodayPassengerFlowTop',
						topNum : '24'
						},// jsonKeyValueForView：为视图view返回Json键值对; 用 JV 代表;  查询  '今日销售情况排行' 客流量  todayPassengerFlowTop
						dataType : "json",
						success : function(json) {
							var myChart = echarts.init(document.getElementById('patient'));
							var myColours = [ '#4F82BB', '#C0504E', '#7900FF', '#BD514B' ];
							var option = {
							title : {
							left : 'center',
							text : '',
							subtext : '' + json.isNull,
							subtextStyle : { //副标题样式
							fontSize : 16,
							color : '#FF0033'
							}
							},
							tooltip : {
							trigger : 'axis',
							formatter : '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[0]+'"></span>{a0} : {c0}元<br/>' + '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[1]+'"></span>{a1} : {c1}笔<br/>',
							axisPointer : {
							type : 'shadow',
							crossStyle : {
								color : '#FFFF33'
							}
							}
							},
							legend : {
								data : [ '销售额', '销售单数' ]
							},
							grid : {
							left : '1%',
							right : '1%',
							bottom : '3%',
							containLabel : true
							},
							xAxis : [ {
							type : 'category',
							data : json.stringXaxis
							//['8:00~9:00','9:00~10:00','10:00~11:00','10:00~11:00','11:00~12:00','11:00~12:00']
							} ],
							yAxis : [ {
							type : 'value',
							name : '销售额/元'
							}, {
							type : 'value',
							name : '销售单数/笔',
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
							} ],
							series : [ {
							name : '销售额',
							type : 'bar',
							data : myNumToFixed2(json.numberYaxisMoney), //["100","230","100","230","100","230"]  
							itemStyle : {
								normal : {
									color : '' + myColours[0]
								}
							}
							}, {
							name : '销售单数',
							type : 'line',
							smooth : true,
							data : json.numberYaxisOrdernum,//["10","23","16","27","18","78"],
							yAxisIndex : 1,
							itemStyle : {
								normal : {
									color : '' + myColours[1]
								}
							}
							} ]
							};
							myChart.clear();
							myChart.setOption(option);
							$(window).resize(myChart.resize);

						}
						});
				queryMethod_C('Today', 'Today');
				$.ajax({
				url : "${nvix}/nvixnt/vixNtIndexAction!nvixContentHtmlA.action",
				type : "POST",
				data : {
					topNum : '10'
				},//查询：客户消费排行TOP10
				cache : false,
				success : function(html) {
					$("#mytableTopA").html(html);
				}
				});
				$.ajax({
				url : "${nvix}/nvixnt/vixNtIndexAction!nvixContentHtmlB.action",
				type : "POST",
				data : {
					topNum : '10'
				},//查询：商品销量排行 TOP10
				cache : false,
				success : function(html) {
					$("#mytableTopB").html(html);
				}
				});
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
				change : function(value) {
					//console.log("change : " + value);
				},
				release : function(value) {
					//console.log(this.$.attr('value'));
					//console.log("release : " + value);
				},
				cancel : function() {
					//console.log("cancel : ", this);
				}
				});
			});
</script>