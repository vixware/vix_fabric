<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-list-alt fa-bar-chart-o"></i> 智能分析 <span>> 会员量分析 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8"></div>
	</div>
	 
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
			<h2>最近30日新增会员量视图</h2>
		</header>
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div>
					<div class="widget-body no-padding">
						<div id="dayItem" style="height: 400px"></div>
					</div>
				</div>
			</article>
		</div>	
	</div>
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
			<h2>最近30日会员总量视图</h2>
		</header>
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div>
					<div class="widget-body no-padding">
						<div id="dayItemB" style="height: 400px"></div>
					</div>
				</div>
			</article>
		</div>	
	</div>
	
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>会员列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							姓名: <input type="text" value="" class="form-control" id="selectName" style="width: 130px;">
							手机号: <input type="text" value="" class="form-control" id="phone" style="width: 130px;">
							会员卡号: <input type="text" value="" class="form-control" id="carNumber" style="width: 130px;">
						</div>
						<div class="input-group">
							<input placeholder="开始时间" style="width: 130px;" id="startTimeB" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
						</div>
						<div class="input-group">
							<input placeholder="结束时间" style="width: 130px;" id="endTimeB" name="endCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endCreateTime'});"><i class="fa fa-calendar"></i></span>
						</div>
						<button onclick="" type="button" class="btn btn-info" id="searchButtonB">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#selectName').val('');$('#phone').val('');$('#carNumber').val('');$('#startTimeB').val('');$('#endTimeB').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function padleft0(obj) {//补齐两位数的函数 
    return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);  
}
function timeClear(divrow){ //清空时间
		 $("#"+divrow+" input[name='startCreateTime']").each(function(){
			    $(this).val("开始时间");
		});
		 $("#"+divrow+" input[name='endCreateTime']").each(function(){
			    $(this).val("结束时间");
		});
	}
function myNumToFixed2(objArr) {//我的把数组保留2位小数返回新数组
			var newArr =[];
			if(Array.isArray(objArr)){
				for(var x=0;x<objArr.length;x++){
					newArr[x]= parseFloat(objArr[x].toFixed(2)); 
				}
			}
		    return newArr;  
		}
		
function queryMethod_A(startTime,endTime) {
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
		 	            data:json.Man,//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10],
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
function queryMethod_B(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntPurchasingBehaviorAction!purchasingBehaviorActionViewB.action",   
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime},
        dataType: "json",
 		success:function(json){
 			var myChart = echarts.init(document.getElementById('dayItemB'));
			var myColours = ['#4ECFD1','#FF3399','#7900FF','#BD514B'];
			var option = {
 	    title : {
 	        //text: '会员总量视图',
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
 	        data:['男性','女性','未知性别','会员总量']
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
 	            data :json.timeStr// ["17-01", "17-02", "17-03", "17-04", "17-05", "17-06", "17-07", "17-08", "17-09", "17-10", "17-11", "19-12"] //json.timeStr//
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
 	            data: json.Man,//[11, 2, 13, 4, 5, 6, 7, 8, 9, 10, 10, 10],
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
 	            data: json.Woman,//[12, 2, 12, 2, 2, 2, 2, 2, 2, 2, 2, 2],
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
 	            data: json.Unknown,//[13, 3, 13, 3, 3, 3, 3, 3, 3, 3, 3, 3],
 	            itemStyle :{
 	            	normal:{
 	            		color:''+myColours[2]
 	            	}
 	            } 
 	        },
 	        {
 	            name:'会员总量',
 	            type:'line',
 	      	 	smooth:true,
 	            data:json.Total,//[36, 7, 38, 9, 10, 11, 12, 13, 14, 15, 15, 15],  //json.Total,//
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
		
		
		
	$(document).ready(function() {
		pageSetUp();
		// PAGE RELATED SCRIPTS
		queryMethod_A('Day30','Day30');
		queryMethod_B('Day30','Day30');
		$('#searchButtonB').click(function(){ //客户消费明细检索
			 var state = 1;
			 var searchStartTime =  $('#startTimeB').val();  
			 var searchEndTime   =  $('#endTimeB').val();
			 	var remind = "请同时选择开始时间和结束时间";
			 	if(searchStartTime.length<=3 && searchEndTime.length>3){
					alert(remind);
					state++;
				}
				if(searchStartTime.length>3 && searchEndTime.length<=3){
					alert(remind);
					state++;
				}
				var d_1 = searchStartTime;
				var d_2 = searchEndTime;
				var nowtime = new Date();  
		        var year = nowtime.getFullYear();  
		        var month = padleft0(nowtime.getMonth() + 1);  
		        var day = padleft0(nowtime.getDate()); 
				var d_now = year + "-" + month + "-" + day ;
				var e_now = new Date(d_now).getTime();
				var e_1 = new Date(d_1).getTime();
				var e_2 = new Date(d_2).getTime();
				if(e_1>e_2){
					alert('对不起，请重新选择时间段，开始时间要在结束时间之前');
					state++;
				}
				if(state == 1){
					customerAccountTable.ajax.reload();
				}	  
		});
	})

</script>
<script type="text/javascript">
pageSetUp();
var customerAccountColumns = [ {
"title" : "编号",
"width" : "8%",
"defaultContent" : ''
}, {
"title" : "姓名",
"width" : "11%",
"data" : function(data) {
	return data.name;
}
}, {
"title" : "卡号",
"width" : "11%",
"data" : function(data) {
	return data.clipNumber;
}
}, {
"title" : "手机",
"width" : "11%",
"data" : function(data) {
	return data.mobilePhone;
}
},{
"title" : "门店",
"width" : "11%",
"data" : function(data) {       
	return data.channelDistributorName;
}
}, {
"title" : "余额",
"width" : "10%",
"data" : function(data) {
	return (data.money) == null ? "" : (data.money).toFixed(2);
}
}, {
"title" : "会员等级",
"width" : "10%",
"data" : function(data) {
	return data.memberLevelName;
}
}, {
"title" : "积分",
"width" : "10%",
"data" : function(data) {
	return data.point;
}
}, {
"title" : "创建时间",
"width" : "10%",
"data" : function(data) {
	return data.createTimeFormatB;
}
}, {
"title" : "操作",
"width" : "8%",
"orderable" : false,
"data" : function(data) {     
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "','查看详情');\" title='查看详情'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		 return show;
}
} ];
	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/nvixCustomerAccountAction!goSingleListStatistics.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var selectName = $("#selectName").val();
		var phone = $("#phone").val();
		var carNumber = $('#carNumber').val();
		selectName = Url.encode(selectName);
		var startTime = $("#startTimeB").val();
		var endTime = $("#endTimeB").val();
		startTime = Url.encode(startTime);
		endTime = Url.encode(endTime);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"parentId" : parentId,
		"treeType" : treeType,
		"phone" : phone,
		"carNumber" : carNumber,
		"startTime" : startTime,
		"endTime" : endTime,
		"selectName" : selectName
		};
	}, 10, '0');
	function saveOrUpdate(id, title) {//查看详情
		$.ajax({
			url :'${nvix}/nvixnt/nvixCustomerAccountAction!show.action',//杨学超的跳转 
			data: {controlSQL:'HYL',id:id}, 
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
			});
	};
	function goList(dateType) {
		loadContent('', '${nvix}/nvixnt/vixntStoreSalesStatisticsAction!goList.action?dateType=' + dateType);
	};
</script>


