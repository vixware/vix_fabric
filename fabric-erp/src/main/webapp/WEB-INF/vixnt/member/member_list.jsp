<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<style>
	.myHoverLine:hover{  /* 指定a标签鼠标移动上去加下划线 */
    border-bottom: 1px solid #000000;
    	color: #fff;
    text-decoration: none;
	}
	.myHoverLine {/* 指定a标签鼠标移出时，字体颜色为白色 */
	    color: #fff;
	}
</style>
<div id="content">
				<div class="row">
					<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
						<h1 class="page-title txt-color-blueDark">
							<i class="fa-fw fa fa-user"></i>会员管理
						</h1>
					</div>
					<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row" style="padding-top:20px;">
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#ffa160;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
																							   <strong class="">${titleDate }总消费金额</strong><br> 
	   <s:if test=" signStr=='DY' ">
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('Money{${queryTime}}','${queryTime}');"><strong class="font-lg"><span id="">¥${HDBCMoney }</span></strong></a>
		</s:if>
<s:else>  
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('Money{ThisMonthOT}','mBerManage');"><strong class="font-lg"><span id="">¥${HDBCMoney }</span></strong></a>
</s:else> 
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#7cc522;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">${titleDate }进店客户</strong><br>  <!-- 进店Into the store 简写为‘IntoTS’代表‘进店客户’ -->
<s:if test=" signStr=='DY' ">
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('IntoTS{${queryTime}}','${queryTime}');"><strong class="font-lg"><span id="">&nbsp;${HDBCCustomers }人</span></strong></a>
</s:if>
<s:else> 
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('IntoTS{ThisMonthOT}','mBerManage');"><strong class="font-lg"><span id="">&nbsp;${HDBCCustomers }人</span></strong></a>  
</s:else>
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#5fd0ff;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">${titleDate }新增会员卡</strong><br>       <!-- IncreaseC说明：IncreaseC是IncreaseCard的简写 代表‘新增会员卡’-->
<s:if test=" signStr=='DY' ">
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('IncreaseC{${queryTime}}','${queryTime}');"><strong class="font-lg"><span  id="">&nbsp;${HDBCCard }张</span></strong></a>
</s:if>
<s:else>												
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('IncreaseC{ThisMonthOT}','mBerManage');"><strong class="font-lg"><span  id="">&nbsp;${HDBCCard }张</span></strong></a>
</s:else>
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
											<label class="control-label" >
											<strong>选择日期： </strong>   <input type="hidden" value="${queryTime }" id="queryTime"  /> <input type="hidden" value="" id="queryTimeTwo"  />
											</label>
										</div>    
										<div class="input-group">
											<input placeholder="时间" value="${queryTimeText }" style="width: 130px;" id="startTimeC" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM'})"  type="text" /> <span class="input-group-addon"
												onclick="WdatePicker({dateFmt:'yyyy-MM',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
										</div>
										<button onclick="controlJump();" type="button" class="btn btn-info" id="">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#startTimeC').val('');$('#queryTime').val('');loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!goMember.action');" type="button" class="btn btn-default">
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
									<hr class="simple" style="border-color:#c0c0c0;">
									<div class="row">
									    <div class="col-xs-3 col-sm-3 text-center" >
											<h5>
											    <s:if test=" signStr=='DY' ">
<a class="myHoverLine"   href="javascript:;" onClick="controlSQLWdMethod('NewAdd{${queryTime}}','${queryTime}');"><span class="font-lg txt-color-red padding-5" >${thisMonthNA}人</span></a>
											    </s:if>
											    <s:else>
<a class="myHoverLine"   href="javascript:;" onClick="controlSQLWdMethod('NewAdd{ThisMonthOT}','mBerManage');"><span class="font-lg txt-color-red padding-5" >${thisMonthNA}人</span></a>
												</s:else>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">${titleDate }新增会员</strong>
											</h5>
						  <div class="padding-5"></div> 
											<p>
											 <s:if test=" signStr=='DY'">
上月新增<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('NewAdd{${queryTimeDY}}','${queryTimeDY}');"><strong class="txt-color-pink"> ${lastMonthNA}人</strong></a> 环比<strong class="txt-color-pink"> <i class="${iclastMonthMomNA }" style="color:${islastMonthMomNA };"></i>${ilastMonthMomNAnum}%</strong>
											 </s:if>
											 <s:else>
 上月新增<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('NewAdd{LastMonthOT}','mBerManage');"><strong class="txt-color-pink"> ${lastMonthNA}人</strong></a> 环比<strong class="txt-color-pink"> <i class="${iclastMonthMomNA }" style="color:${islastMonthMomNA };"></i>${ilastMonthMomNAnum}%</strong>
											 </s:else>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
											<s:if test=" signStr=='DY' ">
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('OrderNum{${queryTime}}','${queryTime}');"><span class="font-lg txt-color-red padding-5">${thisMonthORNum}笔</span></a>
											 </s:if>
											<s:else>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('OrderNum{ThisMonthOT}','mBerManage');"><span class="font-lg txt-color-red padding-5">${thisMonthORNum}笔</span></a>
											</s:else>	
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">${titleDate }订单数</strong>
											</h5>
						<div class="padding-5"></div> 
											<p>
											<s:if test=" signStr=='DY' ">
上月订单<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('OrderNum{${queryTimeDY}}','${queryTimeDY}');"><strong class="txt-color-pink"> ${lastMonthORNum}笔</strong></a> 环比<strong class="txt-color-pink"> <i class="${iclastMonthMomORNum }" style="color:${islastMonthMomORNum };"></i>${ilastMonthMomORNumnum}%</strong>
											 </s:if>
											 <s:else> 
上月订单<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('OrderNum{LastMonthOT}','mBerManage');"><strong class="txt-color-pink"> ${lastMonthORNum}笔</strong></a> 环比<strong class="txt-color-pink"> <i class="${iclastMonthMomORNum }" style="color:${islastMonthMomORNum };"></i>${ilastMonthMomORNumnum}%</strong>
											</s:else>
											</p>
										</div>   
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
											<s:if test=" signStr=='DY' ">
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('Money{${queryTime}}','${queryTime}');"><span class="font-lg txt-color-red padding-5">${thisMonthMM}元</span></a>
											 </s:if>
											<s:else>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('Money{ThisMonthOT}','mBerManage');"><span class="font-lg txt-color-red padding-5">${thisMonthMM}元</span></a>
											</s:else>	
												<div class="padding-5"></div>
											<s:if test=" signStr=='DY' ">
<strong class="txt-color-greenLight">${titleDate } <a class="myHoverLine"  href="javascript:;" onClick="controlSQLWdMethod('Money{${queryTime}}','${queryTime}');"><span class="txt-color-blue">${thisMonthMMPeopleNum}位</span></a> 会员消费</strong>
											</s:if>
											<s:else>
<strong class="txt-color-greenLight">${titleDate } <a class="myHoverLine"  href="javascript:;" onClick="controlSQLWdMethod('Money{ThisMonthOT}','mBerManage');"><span class="txt-color-blue">${thisMonthMMPeopleNum}位</span></a> 会员消费</strong>
											</s:else>
											</h5>
						 <div class="padding-5"></div>
											<p>
											<s:if test=" signStr=='DY' ">
上月消费<a class="myHoverLine"  href="javascript:;" onClick="controlSQLWdMethod('Money{${queryTimeDY}}','${queryTimeDY}');"><strong class="txt-color-pink"> ${lastMonthMM}元</strong></a> 环比<strong class="txt-color-pink"> <i class="${iclastMonthMomMM}" style="color:${islastMonthMomMM};"></i>${ilastMonthMomMMnum}%</strong>
											</s:if>
											<s:else>
上月消费<a class="myHoverLine"  href="javascript:;" onClick="controlSQLWdMethod('Money{LastMonthOT}','mBerManage');"><strong class="txt-color-pink"> ${lastMonthMM}元</strong></a> 环比<strong class="txt-color-pink"> <i class="${iclastMonthMomMM}" style="color:${islastMonthMomMM};"></i>${ilastMonthMomMMnum}%</strong>
											</s:else>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
							<s:if test=" signStr=='DY' ">
<a class="myHoverLine"   href="javascript:;" onClick="controlSQLWdMethod('Card{${queryTime}}','${queryTime}');"><span class="font-lg txt-color-red padding-5">${thisMonthCTM}人</span></a>
							</s:if>		
							<s:else>		
<a class="myHoverLine"   href="javascript:;" onClick="controlSQLWdMethod('Card{ThisMonthOT}','mBerManage');"><span class="font-lg txt-color-red padding-5">${thisMonthCTM}人</span></a>
						 	</s:else>				
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">会员卡${titleDate }过期会员</strong>
											</h5>
						 <div class="padding-5"></div>
											<p>
							<s:if test=" signStr=='DY' ">
上月过期<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('Card{${queryTimeDY}}','${queryTimeDY}');"><strong class="txt-color-pink"> ${lastMonthCTM}人</strong></a> 环比<strong class="txt-color-pink"> <i class="${iclastMonthMomCTM }" style="color:${islastMonthMomCTM };"></i>${ilastMonthMomCTMnum}%</strong>
							</s:if>	
							<s:else>			
 上月过期<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('Card{LastMonthOT}','mBerManage');"><strong class="txt-color-pink"> ${lastMonthCTM}人</strong></a> 环比<strong class="txt-color-pink"> <i class="${iclastMonthMomCTM }" style="color:${islastMonthMomCTM };"></i>${ilastMonthMomCTMnum}%</strong>
							</s:else>				
											</p>
										</div>
									</div>
									<hr class="simple" style="border-color:#c0c0c0;">
								</div>
							</div>
						</div>
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
						<div class="input-group" style="display: none;" >
							<input placeholder="开始时间" style="width: 130px;" id="startTimeB" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
						</div>
						<div class="input-group" style="display: none;" >
							<input placeholder="结束时间" style="width: 130px;" id="endTimeB" name="endCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onfocus="WdatePicker({maxDate:'%y-%M-%d'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endCreateTime'});"><i class="fa fa-calendar"></i></span>
						</div>
						<button onclick="" type="button" class="btn btn-info" id="searchButtonB">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button id="cleanButtonB" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>  
	
			<!-- END MAIN CONTENT -->

		</div>
<script type="text/javascript">
function judgeTimeLargeNow(timeStr) {//获取的时间thetime 格式应为  2016-05-28
	var timeStrDate = new Date(Date.parse(timeStr.replace(/-/g,"/")));
	var curDate = new Date();
	if(timeStrDate > curDate){
		return 1;/*return 1 时  alert("请重新选择开始时间，开始时间不能超过今天"); */
	}else{
		return 6;
	}
}  
function controlJump() {
	var startTime = $("#startTimeC").val();
	var state = 0;
	if(startTime == null || startTime.length < 2 ){
		layer.alert("请选择时间!");
		state++;
	}
	if(judgeTimeLargeNow((startTime+'-01')) == 1){
		state++;
		layer.alert("请重新选择时间,时间不能超过本月!");
	}
	if(state == 0){
		var queryTime = startTime+'{t-Sm-HOT}';//2017-10{t-Sm-HOT}
		var url = '${nvix}/nvixnt/vixntMemberManageDataAction!goMember.action?queryTime='+queryTime;
		loadContent('',url);
	}
}
function controlSQLWdMethod(condition,returnPage) {//传递查询条件给列表
	newHtml(condition,returnPage);
}
function newHtml(controlSQL,returnPage) {  
	$.ajax({
		url : '${nvix}/nvixnt/vixntMemberManageDataAction!goMemberListNewHtml.action',
		data: {controlSQL:controlSQL,returnPage:returnPage},  
		cache : false,
		success : function(html) {
			$("#mainContent").html(html); 
		}
	});
};
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
	$(document).ready(function() {
		pageSetUp();
		// PAGE RELATED SCRIPTS
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
				if(state >= 1){//这里检索时间去掉
					customerAccountTable.ajax.reload();
				}	  
		});
		onclick="$('#selectName').val('');$('#phone').val('');$('#carNumber').val('');$('#startTimeB').val('');$('#endTimeB').val('');customerAccountTable.ajax.reload();"
			$('#cleanButtonB').click(function(){ //清空
				var queryTimeOnce = $("#queryTime").val();
				if(queryTimeOnce != null ){
					if(queryTimeOnce.length > 2){
						$('#selectName').val('');
						$('#phone').val('');
						$('#carNumber').val('');
						$('#startTimeB').val('');
						$('#endTimeB').val('');
						$('#queryTimeTwo').val(queryTimeOnce);
						customerAccountTable.ajax.reload();
					}
				}
				if((queryTimeOnce+'1')=='1'){
					$('#selectName').val('');
					$('#phone').val('');
					$('#carNumber').val('');
					$('#startTimeB').val('');
					$('#endTimeB').val('');
					customerAccountTable.ajax.reload();
				}
			});
	})

//开始统计列表
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
	return (data.money).toFixed(2);
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
	var numb = 0;
	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/nvixCustomerAccountAction!goSingleListStatistics.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var queryTimeOnce = $("#queryTime").val();
		if(queryTimeOnce != null ){
			if(queryTimeOnce.length > 2){
				numb ++;
				if(numb >= 2){
					queryTimeOnce = queryTimeOnce;
				}
				queryTimeOnce = Url.encode(queryTimeOnce);
			}
		}
		var queryTimeTwo = $("#queryTimeTwo").val();
		queryTimeTwo = Url.encode(queryTimeTwo);
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
		"phone" : phone,
		"carNumber" : carNumber,
		"startTime" : startTime,
		"endTime" : endTime,
		"queryTimeOnce" : queryTimeOnce,
		"queryTimeTwo" : queryTimeTwo,
		"selectName" : selectName
		};
	}, 10, '0');
	function saveOrUpdate(id, title) {//查看详情
		$.ajax({
			url :'${nvix}/nvixnt/nvixCustomerAccountAction!show.action',//杨学超的跳转
			data: {queryTime:$("#queryTime").val(),controlSQL:'HYGL',id:id}, 
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