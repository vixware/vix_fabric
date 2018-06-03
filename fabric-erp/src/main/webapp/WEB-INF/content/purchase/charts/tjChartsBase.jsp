<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/date-time/date.js" type="text/javascript"></script>
<script src="${vix}/common/js/date-time/boostrap-daterangepicker-zh_CN.js" type="text/javascript"></script>
<link href="${vix}/common/js/date-time/daterangepicker.css" type="text/css" rel="stylesheet" />
<script src="${vix}/plugin/highcharts/highcharts.js"></script>

<script type="text/javascript">
_pad_page_refresh_main_content = true;

$('.link_btn[l_type=time_type].range').daterangepicker({},function(startDate,endDate){
	var timeRange = '';
	if(this.startDate && this.endDate){
		timeRange = formatDate(this.startDate,10) + '_' + formatDate(this.endDate,10);
	}
	if(timeRange != ''){
		this.element.text(timeRange);
		options_clicked(this.element);
	}else{
		this.element.text('时间范围');
	}
});

$(function(){

	loadMenuContent('${vix}/purchase/purchaseMainAction!goMenuContent.action');
	
	$('.mail_link>li>a.link_btn').each(function(){
		//init text 
		gen_options_title($(this));
		
		//set default selection
		var btn_l_type = $(this).attr('l_type');
		if($('.mail_link>li>a.link_btn[l_type='+btn_l_type+'].on').length==0)
			$(this).addClass('on');
		
		//add click event
		$(this).click(function(e){
			var l_type = $(this).attr('l_type');
			$('.mail_link>li>a.link_btn[l_type='+l_type+']').removeClass('on');
			$(this).addClass('on');
			options_clicked($(this));
		});
	});
	
	options_clicked();
});


function formatDate(date,len) {
	if(!len || len>10 || len<=0)len=10;
	
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    if(month<10) month = '0' + month;
    var day = date.getDate();
    if(day<10) day = '0' + day;
    var str =  year + "-" + month + "-" + day ;
    return str.substring(0,len);
}

function gen_options_title(obj){
	var title = '';
	var l_type = obj.attr('l_type');
	var l_value = obj.attr('l_value');
	if(l_type=='data_target'){
		title += '统计对象：';
		if(l_value=='item'){
			title += '<span>商品</span>';
		}else if(l_value=='supplier'){
			title += '<span>供应商</span>';
		}
	}else if(l_type=='data_source'){
		title += '数据源：';
		if(l_value=='order'){
			title += '<span>采购订单</span>';
		}else if(l_value=='arrival'){
			title += '<span>采购到货单</span>';
		}else if(l_value=='inbound'){
			title += '<span>采购入库单</span>';
		}
	}else if(l_type=='time_type'){
		var preText = l_value==1?'本':'最近'+l_value;
		if(obj.is('.day')){
			title += '日图	'+preText+'日';
		}else if(obj.is('.month')){
			title += '月图	'+preText+'月';
		}else if(obj.is('.year')){
			title += '年图	'+preText+'年';
		}else if(obj.is('.range')){
			title += '时间范围';
		}
	}
	obj.html(title);
}

function options_clicked(obj){
	if(obj && obj.length>0){
		var l_type = obj.attr('l_type');
		if(l_type=='data_target'){
		}else if(l_type=='data_source'){
		}else if(l_type=='time_type'){
			var l_value = obj.attr('l_value');
			if(obj.is('.day')){
				
			}else if(obj.is('.month')){
				
			}else if(obj.is('.year')){
				
			}else if(obj.is('.range')){
				
			}
		}
	}
	
	load_charts();
	charts_load_data();
}

function load_charts(){
	var dataTargetObj = $('.link_btn[l_type=data_target].on');
	var dataSourceObj = $('.link_btn[l_type=data_source].on');
	var timeTypeObj = $('.link_btn[l_type=time_type].on');
	if(dataTargetObj.length==0 || dataSourceObj.length==0 || timeTypeObj.length==0)
		return;
	
	var dataTarget = dataTargetObj.attr('l_value');
	var dataSource = dataSourceObj.attr('l_value');
	var timeType = '';
	var timeRange = '';
	
	var today = new Date();
	var timeToday = Date.parse(today);
	var oneDay = 86400000;

	var timeValue = timeTypeObj.attr('l_value');
	if(!timeValue || timeValue<=0)timeValue = 1;
	
	var timeValueDiff = timeValue - 1;

	if(timeTypeObj.is('.day')){
		timeType = 'day';
		timeRange = formatDate(new Date(timeToday-oneDay*timeValueDiff),10) + "_" + formatDate(today,10);
	}else if(timeTypeObj.is('.month')){
		timeType = 'month';
	    var time2 = formatDate(today,7);
	    today.setMonth(today.getMonth() - timeValueDiff);
	    timeRange = formatDate(today,7) + '_' + time2;
	}else if(timeTypeObj.is('.year')){
		timeType = 'year';
	    var time2 = formatDate(today,4);
	    today.setFullYear(today.getFullYear() - timeValueDiff);
	    timeRange = formatDate(today,4) + '_' + time2;
	}else if(timeTypeObj.is('.range')){
		timeType = 'range';
	    timeRange = timeTypeObj.text();
	    if(timeRange.indexOf('_')==-1 || timeRange.length!=21){
	    	$(this).trigger('click');
	    	return;
	    }
	}
	
	var data = {};
	data.dataTarget = dataTarget;
	data.dataSource = dataSource;
	data.timeType = timeType;
	data.timeRange = timeRange;	

	var chartTitle = dataSourceObj.find('span').text() + ' ' + timeTypeObj.text();

	$.ajax({ url:'${vix}/purchase/purchaseChartsAction!tjLoadChartsData.action'
		,data:data
		,type:"post"
		,dataType:"json"
		,success:function(data){
		    if(data){
		    	$('#charts_div').show();
				loadChartImg(chartTitle, data);
		    }else{
		    	$('#charts_div').hide();
		    }
    }});
	
}

function charts_load_data(){
	var dataTargetObj = $('.link_btn[l_type=data_target].on');
	var dataSourceObj = $('.link_btn[l_type=data_source].on');
	var timeTypeObj = $('.link_btn[l_type=time_type].on');
	if(dataTargetObj.length==0 || dataSourceObj.length==0 || timeTypeObj.length==0)
		return;

	var dataTarget = dataTargetObj.attr('l_value');
	var dataSource = dataSourceObj.attr('l_value');
	var timeType = '';
	var timeRange = '';
	
	var today = new Date();
	var timeToday = Date.parse(today);
	var oneDay = 86400000;

	var timeValue = timeTypeObj.attr('l_value');
	if(!timeValue || timeValue<=0)timeValue = 1;
	
	var timeValueDiff = timeValue - 1;

	if(timeTypeObj.is('.day')){
		timeType = 'day';
		timeRange = formatDate(new Date(timeToday-oneDay*timeValueDiff),10) + "_" + formatDate(today,10);
	}else if(timeTypeObj.is('.month')){
		timeType = 'month';
	    var time2 = formatDate(today,7);
	    today.setMonth(today.getMonth() - timeValueDiff);
	    timeRange = formatDate(today,7) + '_' + time2;
	}else if(timeTypeObj.is('.year')){
		timeType = 'year';
	    var time2 = formatDate(today,4);
	    today.setFullYear(today.getFullYear() - timeValueDiff);
	    timeRange = formatDate(today,4) + '_' + time2;
	}else if(timeTypeObj.is('.range')){
		timeType = 'range';
	    timeRange = timeTypeObj.text();
	    if(timeRange.indexOf('_')==-1 || timeRange.length!=21){
	    	$(this).trigger('click');
	    	return;
	    }
	}
	
	var data = {};
	data.dataTarget = dataTarget;
	data.dataSource = dataSource;
	data.timeType = timeType;
	data.timeRange = timeRange;
	
	_pad_all_loadPage('${vix}/purchase/purchaseChartsAction!tjLoadChartsGrid.action','charts_data_grid',false,data,null);
}

function loadChartImg(chartTitle, data){
	var yAxisTitle = '采购数量';
	var chartsType = 'column';
	if(data.categories.length>15)
		chartsType = 'line';
	
	var tooltipFormat = '<span style="color:{series.color}">\u25CF</span> {series.name}<br/>';
	tooltipFormat += '数量: <b>{point.y}</b><br/>';
	tooltipFormat += '货款: {point.tatol_fee}<br/>';
	tooltipFormat += '税金: {point.tatol_tax}<br/>';
	
	$('#charts_div').highcharts({
        chart: {
            type: chartsType
        },
        title: {
            text: chartTitle
        },
        tooltip: {
        	pointFormat: tooltipFormat
        },
        xAxis: {
        	categories: data.categories
        },
        yAxis: {
            title: {
                text: yAxisTitle
            }
        }, 
        series: data.series
    });
}
</script>
<style>
.mail_link>li>a.on {
	background-color: #eee;
	font-weight: bold;
}

.mail_link.limit_items {
	
}
</style>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module"></div>
	</h2>
</div>

<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>

	<div class="box">
		<table style="" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td valign="top">
					<div class="mail_left" style="width: 220px;">
						<ul class="mail_link">
							<li><a l_type="data_target" l_value="item" class="link_btn " href="javascript:void(0);">商品</a></li>
							<li><a l_type="data_target" l_value="supplier" class="link_btn " href="javascript:void(0);">经销商</a></li>
						</ul>
						<ul class="mail_link">
							<li><a l_type="data_source" l_value="order" class="link_btn " href="javascript:void(0);">采购订单（已完成）</a></li>
							<li><a l_type="data_source" l_value="arrival" class="link_btn " href="javascript:void(0);">采购到货单</a></li>
							<li><a l_type="data_source" l_value="inbound" class="link_btn " href="javascript:void(0);">采购入库单</a></li>
						</ul>
						<ul class="mail_link">
							<li><a l_type="time_type" l_value="7" class="link_btn day" href="javascript:void(0);">最近 7 日</a></li>
							<li><a l_type="time_type" l_value="30" class="link_btn day" href="javascript:void(0);">最近 30 日</a></li>
							<li><a l_type="time_type" l_value="6" class="link_btn month" href="javascript:void(0);">最近 6 月</a></li>
							<li><a l_type="time_type" l_value="12" class="link_btn month" href="javascript:void(0);">最近 12 月</a></li>
							<li><a l_type="time_type" l_value="1" class="link_btn year" href="javascript:void(0);">今年</a></li>
							<li><a l_type="time_type" l_value="3" class="link_btn year" href="javascript:void(0);">最近 3 年</a></li>
							<li><a l_type="time_type" l_value="" class="link_btn range" href="javascript:void(0);">时间范围</a></li>
						</ul>
					</div>
				</td>
				<td valign="top">
					<div class="right_content" style="border: none;">
						<div id="charts_div" style="width: 800px; height: 330px; display: none;"></div>
						<div id="charts_data_grid" style="width: 800px;"></div>
					</div>
				</td>
				<td valign="top">
					<!-- 
			<div class="mail_left">
				<ul class="mail_link limit_items">
					<li><a l_type="data_source" l_value="order" class="link_btn " href="javascript:void(0);">采购订单（已完成）</a></li>
					<li><a l_type="data_source" l_value="arrival" class="link_btn " href="javascript:void(0);">采购到货单</a></li>
					<li><a l_type="data_source" l_value="inbound" class="link_btn " href="javascript:void(0);">采购入库单</a></li>
				</ul>
			</div>
 -->
				</td>
			</tr>
		</table>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>