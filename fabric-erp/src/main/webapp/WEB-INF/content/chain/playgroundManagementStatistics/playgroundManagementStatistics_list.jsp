<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function chooseParentOrganization(flag) {
		$.ajax({
		url : '${vix}/drp/distributionSystemRelationShipAction!goChooseOrganization.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择父公司",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#channelDistributorId").val(result[0]);
						$("#channelDistributorName").val(result[1]);
					} else {
						$("#channelDistributorId").val("");
						$("#channelDistributorName").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function opencontainer15(){
		 $('#container15').highcharts({
	            chart: {
	                type: 'bar'
	            },
	            title: {
	                text: '设备投币数排行'
	            },
	            xAxis: {
	                categories:  ${ classifiedRevenueNameList },
	                title: {
	                    text: null
	                }
	            },
	            yAxis: {
	                min: 0,
	                title: {
	                    text: '个',
	                    align: 'high'
	                }
	            },
	            tooltip: {
	                valueSuffix: ' 个'
	            },
	            credits: {
	                enabled: false
	            },
	            series: [{
	            	name : '投币数',
	            	color: '#00EC00',
	                data:  ${ classifiedRevenueAmountList }
	            }]
	        }); 
	}
	function opencontainer(){
		$('#container').highcharts({
			chart : {
				type : 'column'
			},
			title : {
				text : '年龄段会员占比'
			},
			xAxis : {
				categories : ${ageStatisticStr}
			},
			yAxis : {
			max:50,//纵轴的最大值  
    		min : 0,
			title : {
				text : '占比',
				rotation :0,
   			    x: -20
			}
			},
			tooltip : {
				formatter : function() {
					return '<b>' + this.x + '</b><br/>' + this.series.name + ': ' + this.y + '<br/>' + 'Total: ' + this.point.stackTotal;
				}
			},
			series :  [ {
				name : '%',
				data : ${ customerAgeList }
				} ]
			});
	}
	function opendata3(){
		   $.post('${vix}/chain/playgroundManagementStatisticsAction!getCustomerCountList.action?createTime8='+$("#createTime8").val()+"&endTime8="+$("#endTime8").val(), {
			    }, function(result) {
			    });
		$('#data3').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '会员统计'
        },
        xAxis: {
            categories: ${memberShipCardTypeStr}
        },
        yAxis: {
    		min : 0,
            title: {
                text: '开卡人数',
               	rotation :0,
   			    x: -20
            }
        },
        series: ${ customerList }
    });
		$("#createTime8").val('');
		$("#endTime8").val('');	
	}
	function opencontainer10(){
		$('#container10').highcharts({
			chart : {
				type : 'column'
			},
			title : {
				text : '设备分类营收'
			},
			xAxis : {
				categories : ${ nameList }
			},
			yAxis : {
			min : 0,
			title : {
				text : '销售额',
				rotation :0,
				x: -20
			}
			},
			tooltip : {
				formatter : function() {
					return '<b>' + this.x + '</b><br/>' + this.series.name + ': ' + this.y + '<br/>' + 'Total: ' + this.point.stackTotal;
				}
			},
			series : [ {
			name : '元',
			data : ${ salesAmountList }
			} ]
			});	
	}
	function opencontainer20(){
	$('#container20').highcharts({
			chart : {
				type : 'column'
			},
			title : {
				text : '商品统计信息'
			},
			xAxis : {
				categories : ${ goodsList }
			},
			yAxis : {
			max:50,//纵轴的最大值  
    		min : 0,
			title : {
				text : '销售额',
				rotation :0,
   			    x: -20
			}
			},
			tooltip : {
				formatter : function() {
					return '<b>' + this.x + '</b><br/>' + this.series.name + ': ' + this.y + '<br/>' + 'Total: ' + this.point.stackTotal;
				}
			},
			series : [ {
			name : '元',
			data : ${ netTotalList }
			} ]
			});
		
	}
	
	function opendata2(){
		loadContent('${vix}/chain/playgroundManagementStatisticsAction!goList.action?createTime1='+$("#createTime1").val()+"&endTime1="+$("#endTime1").val()+"&channelDistributorId1="+$("#channelDistributorId1").val());
		$("#createTime1").val('');
		$("#endTime1").val('');			
		$("#channelDistributorId1").val('');	
	}
	function opencontainer2(){

		$('#container2').highcharts({
		chart : {
			type : 'spline'
		},
		title : {
			text : '收入走势'
		},
		xAxis : {
			tickInterval: ${ tickInterval },
			categories :${ dayList },
			labels:{
                // x:45,//调节x偏移
                //y:-35,//调节y偏移
                //rotation:-35//调节倾斜角度偏移
             }
		},
		yAxis : {
		title : {
			text : '金额(元)',
			rotation :0,
		    x: -20
		},
		//tickInterval: 20000,  //自定义刻度    
		max:10000,//纵轴的最大值  
		min : 0,
		labels : {
			formatter : function() {
				return this.value 
			}
		}
		},
		tooltip : {
		crosshairs : true,
		shared : true
		},
		plotOptions : {
			spline : {
				marker : {
				radius : 4,
				lineColor : '#FF0000',
				lineWidth : 1
				}
			}
		},
		series : [{
		color: '#FF0000',
		name : '金额',
		data :${ personList }
		}]
		});	
	}
	
	function opencontainer3(){
		
		 $('#container3').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: 1,
		            plotShadow: true
		        },
		        title: {
		            text: '会员级别比例'
		        },
		        tooltip: {
		    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: '会员级别比例',
		            data: ${gradeRatioStr}
		        }]
		    });
	}
	
	
	function opencontainer5(){
		 $('#container5').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: 1,//null,
		            plotShadow: true
		        },
		        title: {
		            text: '会员分布'
		        },
		        tooltip: {
		    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: '会员分布占比',
		            data: [
		                ['北城区',   45.0],
		                ['城区外',       26.8],
		                {
		                    name: '中心区域',
		                    y: 12.8,
		                    sliced: true,
		                    selected: true
		                },
		                ['南城区',    8.5],
		                ['西城区',     6.2],
		                ['东城区',   0.7]
		            ]
		        }]
		    });
	}
	
	function opencontainer4(){
		 $('#container4').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: 1,//null,
		            plotShadow: true
		        },
		        title: {
		            text: '性别分布'
		        },
		        tooltip: {
		    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: '比例',
		            data: ${sexProportionStr}
		        }]
		    });
	}
	function changetext(changevalue){
		 var a=document.getElementById ("spanid");
		 if(changevalue==2){
		 	 a.innerHTML = '<img width="16" height="16" style="vertical-align: middle" src="img/category_list.png">&nbsp;'+"门店营收";
		 }else if (changevalue==3){
			 a.innerHTML = '<img width="16" height="16" style="vertical-align: middle" src="img/category_list.png">&nbsp;'+"会员分析";
		 }else if (changevalue==4){
			 a.innerHTML = '<img width="16" height="16" style="vertical-align: middle" src="img/category_list.png">&nbsp;'+"设备统计";
		 }else {
			 a.innerHTML = '<img width="16" height="16" style="vertical-align: middle" src="img/category_list.png">&nbsp;'+"运营监控";
		 }
	}
	 /*  $(function() {opencontainer2();}) */
	 function allOverview(){
			$('#other').highcharts({
			chart : {
				type : 'spline'
			},
			title : {
				text : '收入走势'
			},
			xAxis : {
				tickInterval: ${ tickInterval },
				categories :${ dayList },
				labels:{
	                // x:45,//调节x偏移
	                //y:-35,//调节y偏移
	                //rotation:-35//调节倾斜角度偏移
	             }
			},
			yAxis : {
			//tickInterval: 20000,  //自定义刻度    
			max:10000,//纵轴的最大值  
			min : 0,
			title : {
				text : '金额(元)',
				rotation :0,
			    x: -20
			},
			labels : {
				formatter : function() {
					return this.value 
				}
			}
			},
			plotOptions : {
				spline : {
					marker : {
					radius : 4,
					lineColor : '#FF0000',
					lineWidth : 1
					}
				}
			},
			series : [{
			color: '#FF0000',
			name : '金额',
			data :${ personList }
			}]
			});	
			$('#other1').highcharts({
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: '会员统计'
		        },
		        xAxis: {
		            categories: ${memberShipCardTypeStr}
		        },
		        yAxis: {
		    		min : 0,
		            title: {
		                text: '开卡人数',
		               	rotation :0,
		   			    x: -20
		            }
		        },
		        series: ${ customerList }
		    });
			
			 $('#main').highcharts({
		            chart: {
		                type: 'bar'
		            },
		            title: {
		                text: '设备投币数排行'
		            },
		            xAxis: {
		                categories:  ${ classifiedRevenueNameList },
		                title: {
		                    text: null
		                }
		            },
		            yAxis: {
		                min: 0,
		                title: {
		                    text: '个',
		                    align: 'high'
		                }
		            },
		            tooltip: {
		                valueSuffix: ' 个'
		            },
		            credits: {
		                enabled: false
		            },
		            series: [{
		            	name : '投币数',
		            	color: '#00EC00',
		                data:  ${ classifiedRevenueAmountList }
		            }]
		        }); 
			
			 $('#main1').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: 1,
			            plotShadow: true
			        },
			        title: {
			            text: '会员级别比例'
			        },
			        tooltip: {
			    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                    style: {
			                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                    }
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: '会员级别比例',
			            data: ${gradeRatioStr}
			        }]
			    });
	 }
	$(function() {
		allOverview();
	});
</script>
<!-- head -->
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />Print</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />Help</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">连锁经营管理 </a></li>
				<li><a href="#">游乐场运营管理统计</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<!-- sub menu -->
<input type="hidden" id="customerList" name="customerList" value="${customerList}" />
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="mail_left">
				<div class="addbox" style="margin-top: 0">
					<div class="addtitle">
						<span class="l"><img width="16" height="16" style="vertical-align: middle" src="img/reports_list.png">&nbsp;游乐场运营管理统计</span>
					</div>
					<ul class="navmenu">
						<li><a href="#" onclick="javascript:tab(14,1,'np',event),changetext(0),opencontainer2(),tab(30,1,'a',event);">运营监控</a></li>
						<li><a href="#" onclick="javascript:tab(14,2,'np',event),changetext(2),opendata10(),tab(30,2,'a',event);">门店营收</a></li>
						<li><a href="#" onclick="javascript:tab(14,3,'np',event),changetext(3),opencontainer3(),tab(30,12,'a',event);">会员分析</a></li>
						<li><a href="#" onclick="javascript:tab(14,4,'np',event),changetext(4),opencontainer10(),tab(30,15,'a',event);">设备统计</a></li>
					</ul>
				</div>
				<div class="addbox">
					<div class="addtitle">
						<span class="l" id="spanid"><img width="16" height="16" style="vertical-align: middle" src="img/category_list.png">&nbsp;运营监控</span>
					</div>
					<div class="np_clist lineh30" id="np1">
						<ul class="navmenu">
							<li><a href="#" onclick="javascript:tab(30,1,'a',event),opencontainer2();">收入走势</a></li>
							<li><a href="#" onclick="javascript:tab(30,3,'a',event),opendata3();">会员统计</a></li>
							<li><a href="#" onclick="javascript:tab(30,4,'a',event),opendata4();">月收入明细</a></li>
							<!-- <li><a href="#" onclick="javascript:tab(30,5,'a',event),opencontainer5();">集团会员分布</a></li> -->
							<li><a href="#" onclick="javascript:tab(30,6,'a',event),opendata5();">集团收入报表</a></li>
							<li><a href="#" onclick="javascript:tab(30,7,'a',event),opendata6();">消费人次报表</a></li>
						</ul>
					</div>
					<div class="np_clist lineh30" id="np2" style="display: none;">
						<ul class="navmenu">
							<li><a href="#" onclick="javascript:tab(30,2,'a',event),opendata10();">日收入汇总</a></li>
							<li><a href="#" onclick="javascript:tab(30,8,'a',event),opendata7();">水吧销售统计</a></li>
							<li><a href="#" onclick="javascript:tab(30,9,'a',event),opendata8();">套餐销售统计</a></li>
							<li><a href="#" onclick="javascript:tab(30,11,'a',event),opencontainer20();">商品统计信息</a></li>
						</ul>
					</div>
					<div class="np_clist lineh30" id="np3" style="display: none;">
						<ul class="navmenu">
							<li><a href="#" onclick="javascript:tab(30,12,'a',event),opencontainer3();">会员级别比例</a></li>
							<li><a href="#" onclick="javascript:tab(30,13,'a',event),opencontainer();">会员年龄段统计</a></li>
							<li><a href="#" onclick="javascript:tab(30,14,'a',event),opencontainer4();">会员性别分布</a></li>
						</ul>
					</div>
					<div class="np_clist lineh30" id="np4" style="display: none;">
						<ul class="navmenu">
							<li><a href="#" onclick="javascript:tab(30,15,'a',event),opencontainer10();">设备分类营收</a></li>
							<li><a href="#" onclick="javascript:tab(30,16,'a',event),opencontainer15();">设备投币数排行</a></li>
							<li><a href="#" onclick="javascript:tab(30,17,'a',event),opendata9();">设备消费占比</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="right">
			<div class="right_content" id="a1" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;收入走势</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId1" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime1" name="createTime1" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime1','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime1" name="endTime1" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime1','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" onclick="opendata2();" /></td>
						</tr>
					</table>
				</div>
				<div id="container2" style="width: 1000px; height: 400px; margin: 0 auto"></div>
			</div>
			<div class="right_content" id="a12" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;会员级别比例</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId2" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime2" name="createTime2" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime2','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime2" name="endTime2" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime2','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" /></td>
						</tr>
					</table>
				</div>
				<div id="container3" style="width: 1000px; height: 400px; margin: 0 auto"></div>
			</div>
			<div class="right_content" id="a13" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;会员年龄段统计</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId3" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime3" name="createTime3" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime3','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime3" name="endTime3" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime3','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" /></td>
						</tr>
					</table>
				</div>
				<div id="container" style="height: 400px; width: 1000px; margin: 0 auto"></div>
			</div>
			<div class="right_content" id="a14" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;会员性别分布</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId4" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime4" name="createTime4" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime4','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime4" name="endTime4" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime4','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" /></td>
						</tr>
					</table>
				</div>
				<div id="container4" style="height: 400px; width: 1000px; margin: 0 auto"></div>
			</div>
			<div class="right_content" id="a5" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;集团会员分布</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId5" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime5" name="createTime5" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime5','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime5" name="endTime5" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime5','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" /></td>
						</tr>
					</table>
				</div>
				<div id="container5" style="height: 400px; width: 1000px; margin: 0 auto"></div>
			</div>
			<div class="right_content" id="a6" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;集团收入报表</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<form action="${vix}/chain/playgroundManagementStatisticsAction!getGroupIncomeStatementList.action" id="form6"></form>
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId6" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime6" name="createTime6" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime6','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime6" name="endTime6" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime6','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" onclick="opendata5();" value="查询" /></td>
						</tr>
					</table>
					</form>
				</div>
				<script type="text/javascript">
				function opendata5(){
							$('#data5').datagrid({
							url : '${vix}/chain/playgroundManagementStatisticsAction!getGroupIncomeStatementList.action?createTime6='+$("#createTime6").val()+"&endTime6="+$("#endTime6").val(),
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							columns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'storename',
							title : '分店',
							width : 200,
							align : 'center'
							},  {
							field : 'salesDate',
							title : '日期',
							width : 100,
							editor : 'datebox',
							align : 'center',
							formatter : function(val, rec) {
								if (val != null && val != "") {
									var d = new Date(val);
									return format(d);
								} else
									return "";
							}
							}, {
							field : 'saleCoinAmount',
							title : '售币金额',
							width : 100,
							align : 'center'
							},{
							field : 'cateringSalesAmount',
							title : '餐饮销售金额',
							width : 100,
							align : 'center'
							}, {
							field : 'goodsSalesAmount',
							title : '商品销售金额',
							width : 100,
							align : 'center'
							}, {
							field : 'total',
							title : '总计',
							width : 100,
							align : 'center'
							}, {
							field : 'cash',
							title : '现金',
							width : 100,
							align : 'center'
							}, {
							field : 'pos',
							title : 'POS机',
							width : 100,
							align : 'center'
							}, {
							field : 'groupBuying',
							title : '团购',
							width : 100,
							align : 'center'
							}] ]
							});
							$("#createTime6").val('');
							$("#endTime6").val('');
							$("#channelDistributorId6").val('');
							}
						function format(date) {
							var y = date.getFullYear();
							var m = date.getMonth() + 1;
							var d = date.getDate();
							return y + '-' + m + '-' + d;
						}
						</script>
				<div style="padding: 8px;">
					<table id="data5"></table>
				</div>
			</div>
			<div id="a2" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;日收入汇总</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId7" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime7" name="createTime7" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime7','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime7" name="endTime7" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime7','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" /></td>
						</tr>
					</table>
				</div>
				<script type="text/javascript">
				
				function opendata10(){
							$('#data10').datagrid({
							url : '${vix}/chain/playgroundManagementStatisticsAction!getDayIncomeSummaryList.action',
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							columns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'cash',
							title : '现金(应收)',
							width : 150,
							align : 'center'
							},{
							field : 'pos',
							title : 'POS机(应收)',
							width : 150,
							align : 'center'
							}, {
							field : 'groupBuying',
							title : '团购(应收)',
							width : 150,
							align : 'center'
							}, {
							field : 'realIncomesCash',
							title : '现金(实收)',
							width : 150,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'realIncomesPos',
							title : 'POS机(实收)',
							width : 150,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'realIncomesGroupBuying',
							title : '团购(实收)',
							width : 150,
							align : 'right',
							required : 'true'
							} ] ]
							});}
						</script>
				<div style="padding: 8px;">
					<table id="data10"></table>
				</div>
			</div>
			<div class="right_content" id="a3" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;会员统计</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<form action="">
						<table width="100%" style="margin-bottom: 0;">
							<tr>
								<td width="10%" class="ar bggray">所属门店：</td>
								<td width="40%" colspan="3"><s:select id="channelDistributorId8" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
									</s:select> <span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td width="10%" class="ar bggray">日期：</td>
								<td width="40%" colspan="3"><input id="createTime8" name="createTime8" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime8','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime8" name="endTime8" type="text"
									class="ipt time_aoto_input" /> <img onclick="showTime('endTime8','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" onclick="opendata3();" value="查询" /></td>
							</tr>
						</table>
					</form>
				</div>
				<div id="data3" style="width: 1000px; height: 400px; margin: 0 auto"></div>
			</div>
			<div class="right_content" id="a4" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;月收入明细</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId9" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime9" name="createTime9" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime9','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime9" name="endTime9" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime9','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" onclick="opendata4();" value="查询" /></td>
						</tr>
					</table>
				</div>
				<script type="text/javascript">
				function opendata4(){
							$('#data4').datagrid({
							url : '${vix}/chain/playgroundManagementStatisticsAction!getMonthAccountBalanceList.action?createTime9='+$("#createTime9").val()+"&endTime9="+$("#endTime9").val()+"&channelDistributorId9="+$("#channelDistributorId9").val(),
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'salesDate',
							title : '日期',
							width : 100,
							editor : 'datebox',
							align : 'center',
							formatter : function(val, rec) {
								if (val != null && val != "") {
									var d = new Date(val);
									return format(d);
								} else
									return "";
							}
							}, {
							field : 'saleCoinAmount',
							title : '售币金额',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'cateringSalesAmount',
							title : '餐饮销售金额',
							width : 100,
							align : 'center'
							}, {
							field : 'goodsSalesAmount',
							title : '商品销售金额',
							width : 100,
							align : 'center'
							}, {
							field : 'total',
							title : '总计',
							width : 100,
							align : 'center'
							}, {
							field : 'cash',
							title : '现金',
							width : 100,
							align : 'center'
							}, {
							field : 'pos',
							title : 'POS机',
							width : 100,
							align : 'center'
							}, {
							field : 'groupBuying',
							title : '团购',
							width : 100,
							align : 'center'
							}] ]
							});
							$("#createTime9").val('');
							$("#endTime9").val('');			
							$("#channelDistributorId9").val('');			
				}
						</script>
				<div style="padding: 8px;">
					<table id="data4"></table>
				</div>
			</div>
			<div class="right_content" id="a15" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;设备分类营收</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId10" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime10" name="createTime10" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime10','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime10" name="endTime10" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime10','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" /></td>
						</tr>
					</table>
				</div>
				<div id="container10" style="height: 400px; width: 1000px; margin: 0 auto"></div>
			</div>
			<div class="right_content" id="a16" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;设备投币数排行</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId11" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime11" name="createTime11" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime11','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime11" name="endTime11" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime11','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" /></td>
						</tr>
					</table>
				</div>
				<div id="container15" style="height: 400px; width: 1000px; margin: 0 auto"></div>
			</div>
			<div class="right_content" id="a17" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;设备消费占比</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId12" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime12" name="createTime12" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime12','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime12" name="endTime12" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime12','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" /></td>
						</tr>
					</table>
				</div>
				<script type="text/javascript">
				function opendata9(){
							$('#data9').datagrid({
							url : '${vix}/chain/playgroundManagementStatisticsAction!getEquipmentSalesList.action',
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'type',
							title : '机台大类',
							width : 100,
							align : 'center'
							}, {
							field : 'project',
							title : '项目',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'coinNumber',
							title : '分类投币数',
							width : 100,
							align : 'center'
							}, {
							field : 'totalCoin',
							title : '总币数',
							width : 100,
							align : 'center'
							},{
							field : 'ratioBetweenProjects',
							title : '项目间比例',
							width : 100,
							align : 'center'
							}, {
							field : 'totalProportion',
							title : '总比例',
							width : 100,
							align : 'center'
							}] ]
							});}
						</script>
				<div style="padding: 8px;">
					<table id="data9"></table>
				</div>
			</div>
			<div class="right_content" id="a8" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;水吧销售统计</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId13" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime13" name="createTime13" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime13','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime13" name="endTime13" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime13','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" /></td>
						</tr>
					</table>
				</div>
				<script type="text/javascript">
				function opendata7(){
							$('#data7').datagrid({
							url : '${vix}/chain/playgroundManagementStatisticsAction!getWaterSalesStatisticsList.action',
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'name',
							title : '杂项套餐名称',
							width : 100,
							align : 'center'
							}, {
							field : 'salesAmount',
							title : '销售数量',
							width : 100,
							align : 'center'
							}, {
							field : 'oldAmount',
							title : '原金额',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'paidAmount',
							title : '实收金额',
							width : 100,
							align : 'center'
							}, {
							field : 'cash',
							title : '现金支付',
							width : 100,
							align : 'center'
							}, {
							field : 'pos',
							title : 'POS机支付',
							width : 100,
							align : 'center'
							}, {
							field : 'groupBuying',
							title : '团购券支付',
							width : 100,
							align : 'center'
							}] ]
							});}
						</script>
				<div style="padding: 8px;">
					<table id="data7"></table>
				</div>
			</div>
			<div class="right_content" id="a9" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;套餐销售统计</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId14" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime14" name="createTime14" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime14','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime14" name="endTime14" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime14','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" /></td>
						</tr>
					</table>
				</div>
				<script type="text/javascript">
				function opendata8(){
							$('#data8').datagrid({
							url : '${vix}/chain/playgroundManagementStatisticsAction!getPackagesSalesStatisticsList.action',
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'name',
							title : '套餐名称',
							width : 150,
							align : 'center'
							}, {
							field : 'salesAmount',
							title : '销售数量',
							width : 150,
							align : 'center'
							}, {
							field : 'totalAmount',
							title : '总金额',
							width : 150,
							align : 'center'
							} ] ],
							columns : [ [{
							field : 'cash',
							title : '现金支付',
							width : 150,
							align : 'center'
							}, {
							field : 'pos',
							title : 'POS机支付',
							width : 150,
							align : 'center'
							}, {
							field : 'groupBuying',
							title : '团购券支付',
							width : 150,
							align : 'center'
							}] ]
							});}
						</script>
				<div style="padding: 8px;">
					<table id="data8"></table>
				</div>
			</div>
			<div class="right_content" id="a7" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;消费人次报表</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId15" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime15" name="createTime15" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime15','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime15" name="endTime15" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime15','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" onclick="opendata6();" value="查询" /></td>
						</tr>
					</table>
				</div>
				<script type="text/javascript">
				function opendata6(){
							$('#data6').datagrid({
							url : '${vix}/chain/playgroundManagementStatisticsAction!getConsumerPeopleReportList.action?createTime15='+$("#createTime15").val()+"&endTime15="+$("#endTime15").val(),
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							columns : [ [  {
							field : 'id',
							title : '编号',
							width : 100,
							hidden : true,
							align : 'center'
							}, {
							field : 'macName',
							title : '机台',
							width : 200,
							align : 'center'
							}, {
							field : 'tatal',
							title : '总投币数',
							width : 200,
							align : 'center'
							},{
							field : 'perCapitaNumberCoins',
							title : '人均投币数',
							width : 200,
							align : 'center'
							}] ]
							});
							$("#createTime15").val('');
							$("#endTime15").val('');	
				}
						</script>
				<div style="padding: 8px;">
					<table id="data6"></table>
				</div>
			</div>
			<div class="right_content" id="a11" style="border: 0; display: none;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;商品统计信息</span>
					</h2>
					<span class="pull-right" style="padding-right: 15px;"><a href="#" onclick="javascript:tab(30,18,'a',event),allOverview();">返回总览</a></span>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">所属门店：</td>
							<td width="40%" colspan="3"><s:select id="channelDistributorId16" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" multiple="false" theme="simple">
								</s:select> <span style="color: red;">*</span></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3"><input id="createTime16" name="createTime16" type="text" class="ipt time_aoto_input" /> <img onclick="showTime('createTime16','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">&nbsp;&nbsp;~&nbsp;&nbsp;<input id="endTime16" name="endTime16" type="text"
								class="ipt time_aoto_input" /> <img onclick="showTime('endTime16','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><input type="button" class="btn" value="查询" /></td>
						</tr>
					</table>
				</div>
				<div id="container20" style="height: 400px; width: 1000px; margin: 0 auto"></div>
			</div>
			<!-- 新加 -->
			<div class="right_content" id="a18" style="border: 0;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<span class="pull-left"><img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;总览</span>
					</h2>
				</div>
				<div class="box">
					<div class="order_monitor clearfix">
						<div class="order_m_left">
							<div id="other"></div>
						</div>
						<div class="order_m_right">
							<div id="other1"></div>
						</div>
					</div>
					<div class="order_monitor clearfix">
						<div class="order_m_left">
							<div id="main"></div>
						</div>
						<div class="order_m_right">
							<div id="main1"></div>
						</div>
					</div>
					<!-- right -->
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>
<style>
.order_monitor {
	padding: 15px;
}

.order_monitor h2 span, .order_box h2 span {
	font-size: .7em;
	margin: 0 0 0 15px;
	font-weight: lighter;
}

.order_m_left {
	float: left;
	width: 50%;
	display: inline;
	margin-right: 10px;
}

.order_m_left ul {
	padding: 10px 0;
}

.order_m_left ul li {
	float: left;
	font-size: 1.2em;
	width: 50%;
	line-height: 2em;
	margin: 10px 0;
	text-align: center;
	border: #ddd solid 1px;
	border-left: none;
	border-right: none;
	background: #eee;
}

.order_m_left ul li strong {
	font-weight: bold;
}

.order_m_left ul li.oml_first {
	width: 100%;
}

.order_m_right {
	overflow: hidden;
}

.order_m2 {
	padding: 15px;
}

.order_m2_l {
	float: left;
	display: inline;
	width: 50%;
	margin-right: 10px;
}

.order_m2_l table {
	line-height: 2em;
	font-size: 1.2em;
	margin: 35px 0 0;
	text-align: center;
}

.order_m2_l table strong {
	font-weight: bold;
}

.order_m2_r {
	overflow: hidden;
}
</style>