<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<style>
	.tmyColorA{color:#05CD15} /* 粉红色下箭头 用于环比符号显示 */
	.tmyColorB{color:#D0000D} /* 蓝色上箭头 用于环比符号显示 */
	.myHoverLine:hover{  /* 指定a标签鼠标移动上去加下划线 */
    border-bottom: 1px solid #000000;
    	color: #fff;
    text-decoration: none;
	}
	.myHoverLine {/* 指定a标签鼠标移出时，字体颜色为白色 */
	    color: #fff;
	}
	.mytxt-color-wathet { /* 目标按钮浅蓝色 */
	background-color:#428BCA !important;
	}
</style>	
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 门店管理 <span>> 门店数据统计 </span> <span>> 门店营业概况</span>
				   <input type="hidden" value="${setChannelDistributorID }" id="setChannelDistributorID"  />
				   <input type="hidden" value="${queryTime }" id="queryTime"  />
				   <input type="hidden" value="${selectDistributorID }" id="selectDistributorID"  />
				   <input type="hidden" value="${startTime }" id="startTime"  />
				   <input type="hidden" value="${endTime }" id="endTime"  />
				   <input type="hidden" value="${liabcd }" id="liabcd"  />
			</h1>
		</div>
	</div>
	
				<div class="row">
						<div class="col-sm-12 col-md-12">
							<div class="well">
							
						<div class="row">	
							<div class="row">
								<div class="col-ms-12 col-md-12 col-lg-12">
									<label class="col-md-1 control-label" style="margin-top:5px;margin-left:0px;"><strong>快速查询: </strong></label>
										<div class="col-ms-8 col-md-8 col-lg-8">
										<ul class="demo-btns tab-btn">
											<li>   
		 <a href="javascript:void(0);" class="btn bg-color-blueLight ${lia } txt-color-white btn-sm" id="Today"  onclick="queryTimeChangeByLi(this,'lia');">今日</a>  
											</li>
											<li>
												<a href="javascript:void(0);" class="btn bg-color-blueLight ${lib } txt-color-white btn-sm" id="Yesterday"  onclick="queryTimeChangeByLi(this,'lib');">昨日</a>
											</li>
											<li>
												<a href="javascript:void(0);" class="btn bg-color-blueLight ${lic } txt-color-white btn-sm" id="-Lately-Day{7}" onclick="queryTimeChangeByLi(this,'lic');">最近7日</a>
											</li>
											<li>
												<a href="javascript:void(0);" class="btn bg-color-blueLight ${lid } txt-color-white btn-sm" id="-Lately-Day{30}" onclick="queryTimeChangeByLi(this,'lid');">最近30日</a>
											</li>
										</ul>
									</div>
								</div>
						  </div>
							
							<div class="row">
									<div class="col-ms-12 col-md-12 col-lg-12">  
										<label class="col-md-1 control-label" style="margin-top:15px;margin-left:0px;"><strong>选择时间: </strong></label>
									<div class="col-ms-8 col-md-8 col-lg-8" style="paddingLeft: 0px;">
										<form role="search" class="navbar-form navbar-left" style="paddingLeft: 0px;margin-left:-13px;">  
										<div class="input-group"  >
	 <input placeholder="开始时间" value="${startTime }" style="width: 130px;" id="startTimeA" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" /> <span class="input-group-addon"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
										</div>
										<div class="input-group">
 <input placeholder="结束时间" value="${endTime }" style="width: 130px;" id="endTimeA" name="endCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" /> <span class="input-group-addon"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endCreateTime'});"><i class="fa fa-calendar"></i></span>
										</div>
										<a onclick="$('#startTimeA').val('');$('#endTimeA').val('');" href="javascript:void(0);" class="btn bg-color-blueLight txt-color-white btn-sm" id=""  >清空</a>
										</form>
									</div>
									</div>	
							</div>
							
							<div class="row">		
									<div class="col-ms-12 col-md-12 col-lg-12">  
										<label class="col-md-1 control-label" style="margin-top:5px;margin-left:0px;"><strong>选择门店: </strong></label>
									<div class="col-ms-9 col-md-9 col-lg-9" >
											<select id="companyID" style="width: 165px;" onchange="queryIDChange(this);">
											
											<s:if test="channelDistributorList==null || channelDistributorList.isEmpty()">  
												<option value=""></option>
											</s:if>
											<s:elseif test="channelDistributorList.size ==1 ">
													<s:iterator value="channelDistributorList" var="cbean" >	  
														<option value="{S---tH-d}{${cbean.id}}{E---dT-l}">${cbean.name}</option>
												    </s:iterator>
											</s:elseif>
											 <s:elseif test="channelDistributorList.size >=2 ">
													<s:iterator value="channelDistributorList" var="cbean" >
														<s:if test='#cbean.id=="all-C---lD---r-ID"'> 
															<option value="{S---tH-d}${cbean.id}{E---dT-l}">${cbean.name}</option>
														</s:if>
														<s:else>
															<option value="{S---tH-d}{${cbean.id}}{E---dT-l}">${cbean.name}</option>
														</s:else>
												    </s:iterator>
											 </s:elseif> 
											</select>
											<!-- <button type="button" class="btn btn-default btn-sm"  onclick="$('#startTimeA').val('');$('#endTimeA').val('');" style="margin-top:-2px;">
												<i class="glyphicon glyphicon-repeat"></i> 重置
											</button> -->
											<button type="button" class="btn btn-info btn-sm" style="margin-top:-2px;" onclick="queryTimeChangeBybutton();"> 
											<i class="glyphicon glyphicon-search"></i> 查询
											</button>
										</div>
									</div>
							</div>
							
							<div class="row" style="margin-top:5px;">		
									<div class="col-ms-12 col-md-12 col-lg-12">  
										<label class="col-md-1 control-label" style="margin-top:5px;margin-left:0px;"><strong>当前信息: </strong></label>
										<div class="col-ms-9 col-md-9 col-lg-9" >
											<label  style="margin-top:5px;margin-left:0px;"><strong style="margin-right:20px;color:#0066CC;" id="currentCompanyNam"></strong><strong style="color:#0066CC;">${todayStrweekNumStr}</strong></label>
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
					<hr class="simple" style="border-color:#c0c0c0;">
	<div class="row">
		<div class="col-xs-3 col-sm-3 text-center">
			<h5>
				<span class="font-lg txt-color-red padding-5">￥${doBusinessNA}</span>
				<div ></div>
				<span class="font-lg txt-color-red padding-5">${doBusinessNB}笔</span>
				<div class="padding-5"></div>
				<strong class="txt-color-greenLight">营业收入</strong>
			</h5>
			<div class="padding-5"></div>
			<p>环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${doBusinessColor}"></i>${doBusinessNum}%</strong></p>
		</div>
		<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
			<h5>
				<span class="font-lg txt-color-red padding-5">￥${rechargeNA}</span>
				<div ></div>
				<span class="font-lg txt-color-red padding-5">${rechargeNB}笔</span>
				<div class="padding-5"></div>
				<strong class="txt-color-greenLight">储值收入</strong>
			</h5>
			<div class="padding-5"></div>
			<p>环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${rechargeColor}"></i>${rechargeNum}%</strong></p>
		</div>
		<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
			<h5>
				<span class="font-lg txt-color-red padding-5">￥${discountNA}</span>
				<div ></div>
				<span class="font-lg txt-color-red padding-5">${discountNB}笔</span>
				<div class="padding-5"></div>
				<strong class="txt-color-greenLight">折扣金额</strong>
			</h5>
			<div class="padding-5"></div>
			<p>环比<strong class="txt-color-pink"> <i class="fa fa-arrow-${discountColor}"></i>${discountNum}%</strong></p>
		</div>
		<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
			<h5>
				<span class="font-lg txt-color-red padding-5">￥88.00</span>
				<div ></div>
				<span class="font-lg txt-color-red padding-5">1笔</span>
				<div class="padding-5"></div>
				<strong class="txt-color-greenLight">退款金额</strong>
			</h5>
			<div class="padding-5"></div>
			<p>环比<strong class="txt-color-pink"> <i class="fa fa-arrow-down" style="color:#05CD15;"></i>30.00%</strong></p>
		</div>
	</div>
	<hr class="simple" style="border-color:#c0c0c0;">
				</div>
			</div>
		</div>
	</div>
</div>
					
				</div>
	
	
</div>
<script type="text/javascript">
function myNumToFixed2(objArr) {//我的把数组保留2位小数返回新数组
	var newArr =[];
	if(Array.isArray(objArr)){
		for(var x=0;x<objArr.length;x++){
			newArr[x]= parseFloat(objArr[x].toFixed(2)); 
		}
	}
    return newArr;  
}
function currentCompanyNamM() {//页面初始化时，输出当前显示数据公司的 name
	var find = $("#selectDistributorID").val();
	if(find.length>1){
		var currentCompanyName = $("#companyID").find("option[value='"+find+"']").text();
		$("#currentCompanyNam").text(currentCompanyName);
	}else{
		var myselect=document.getElementById("companyID");
		var currentCompanyName = "";
		if(myselect.options.length >=1){
			currentCompanyName = myselect.options[0].text;
		}
		$("#currentCompanyNam").text(currentCompanyName);
	}
}
function queryIDChange(obj){//选择不同的门店  
	var selectId = $(obj).val();
	$("#setChannelDistributorID").val(selectId);
}
function queryTimeChangeByLi(obj,liabcd){//选择时间的改变 A  通过Li点击  liabcd改变颜色样式
	var queryTime = $(obj).attr("id");
	$("#queryTime").val(queryTime);
	$("#liabcd").val(liabcd);
	var setChannelDistributorID = $("#setChannelDistributorID").val();
	var url = '${nvix}/nvixnt/vixntDoBusinessDataAction!goDoBusinessView.action?queryTime='+queryTime+"&setChannelDistributorID="+setChannelDistributorID+"&liabcd="+liabcd;
	loadContent('drp_dobusinesssurvey',url);
}
function queryTimeChangeBybutton(){
	if(searchCheck()==1){
		var startTime = $('#startTimeA').val(); 
		var endTime = $('#endTimeA').val();  
		var queryTime = startTime+endTime;
		var setChannelDistributorID = $("#setChannelDistributorID").val();
		var liabcd = $("#liabcd").val();
		var url = '${nvix}/nvixnt/vixntDoBusinessDataAction!goDoBusinessView.action?queryTime='+queryTime+"&setChannelDistributorID="+setChannelDistributorID+"&startTime="+startTime+"&endTime="+endTime+"&liabcd="+liabcd;
		loadContent('drp_dobusinesssurvey',url);
	}
}
function queryIDChangeForFind(){//为了 li按钮快速查询时，改变对应的下拉框的显示
	var find = $("#selectDistributorID").val();
	if(find.length>1){
		$("#companyID").find("option[value='"+find+"']").attr("selected",true);
		$("#setChannelDistributorID").val(find);
	}
}

/* 下面是检索时间段的长度的验证方法 */
function searchCheck(){//检查检索条件
	var tmState = 1;
	var tenantId = $("#companyID").val();
	var searchStartTime = $("#startTimeA").val();
	var searchEndTime = $("#endTimeA").val();
	if(tenantId.length==0){
		alert("请选择门店");
		tmState++;
	}
	if(searchStartTime.length==0){
		alert("请选择开始时间（最近90天以内）");
		tmState++;
	}
	if(searchEndTime.length==0){
		alert("请选择结束时间（最近90天以内）");
		tmState++;
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
		alert("对不起，请重新选择时间段，开始时间要在结束时间之前");
		tmState++;
	}
	if(e_now<e_2){
		alert("对不起，请重新选择时间段，结束时间不能超过今天");
		tmState++;
	}
	var compareA = d_1+" 00:00:00";
	var testDate = new Date();
	var testStrA = testDate.format("yyyy-MM-dd");
	var testStr = testStrA+" 00:00:00";
	var result = GetDateDiff(compareA, testStr, "day");
	if(result>89){
		alert("提示:最多查询90天时间段以内的数据");
		tmState++;
		tmState = 1;
	}
	if(tmState ==1 ){//检索条件合格时
		return tmState;
	}
}
/** 
* 时间对象的格式化 
*/  
Date.prototype.format = function(format)  {  
/* * format="yyyy-MM-dd hh:mm:ss"; */  
	var o = {  
		"M+" : this.getMonth() + 1,  
		"d+" : this.getDate(),  
		"h+" : this.getHours(),  
		"m+" : this.getMinutes(),  
		"s+" : this.getSeconds(),  
		"q+" : Math.floor((this.getMonth() + 3) / 3),  
		"S" : this.getMilliseconds()  
	}  
	if (/(y+)/.test(format))  {  
	format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4  
	- RegExp.$1.length));  
	}  
	for (var k in o)  {  
		if (new RegExp("(" + k + ")").test(format))  
	{  
	format = format.replace(RegExp.$1, RegExp.$1.length == 1  
	? o[k]  
	: ("00" + o[k]).substr(("" + o[k]).length));  
	}  
	}  
	return format;  
}  
/* 
* 获得时间差,时间格式为 年-月-日 小时:分钟:秒 或者 年/月/日 小时：分钟：秒 
* 其中，年月日为全格式，例如 ： 2010-10-12 01:00:00 
* 返回精度为：秒，分，小时，天
*/
function GetDateDiff(startTime, endTime, diffType) {
    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式 
    startTime = startTime.replace(/\-/g, "/");
    endTime = endTime.replace(/\-/g, "/");
    //将计算间隔类性字符转换为小写
    diffType = diffType.toLowerCase();
    var sTime = new Date(startTime);      //开始时间
    var eTime = new Date(endTime);  //结束时间
    //作为除数的数字
    var divNum = 1;
    switch (diffType) {
        case "second":
            divNum = 1000;
            break;
        case "minute":
            divNum = 1000 * 60;
            break;
        case "hour":
            divNum = 1000 * 3600;
            break;
        case "day":
            divNum = 1000 * 3600 * 24;
            break;
        default:
            break;
    }
    return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(divNum));
}
function padleft0(obj) {//补齐两位数的函数 (检查开始时间,结束时间,用)
    return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);  
}
/* 上面是检索时间段的长度的验证方法 */

$(document).ready(function() {
	pageSetUp();
	currentCompanyNamM();
	queryIDChangeForFind();
})
</script>
		<!-- <script>   现在暂时用不上你了
		$(function(){
	        var tab_menu_li = $('.tab-btn li');
	
	        tab_menu_li.click(function(){
	            $(this).children("a").addClass('mytxt-color-wathet')
	                    .parents("li").siblings().children("a").removeClass('mytxt-color-wathet');
	        })
	    });
		</script> -->