<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<style>
.content {
	margin-bottom: 0;
}

.cardTable {
	padding: 0 10px;
}

.cardTable table th, .cardTable table td {
	padding: 5px;
	vertical-align: top;
	border: #CCC solid 1px;
}

.cardTable table th {
	background: #DCE7F1;
}

.cardTable table .tr {
	text-align: right;
}

.cardTable .popupArea {
	height: 300px;
}

.cardTable .checkbox {
	vertical-align: middle;
}

.cardTable label {
	margin-right: 10px;
}
</style>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
    //加载更新日期(新增)
    if (document.getElementById("loginDate").value == "") {
	    var myDate = new Date();
	    $("#loginDate").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
    }
    
    /** 日期比较 **/
   /*  function compareDate(strDate1,strDate2)
    {
        var date1 = new Date(strDate1.replace(/\-/g, "\/"));
        var date2 = new Date(strDate2.replace(/\-/g, "\/"));
        return date1-date2;
    } */
     
    /** 比较 **/
   /*  function doCompare(){
        var strDate1 = document.getElementById("strDate1").value;
        var strDate2 = document.getElementById("strDate2").value;
        var result = compareDate(strDate1,strDate2);
        if ( result>0 ) {
            alert("strDate1晚于strDate2");
        }else if( result<0 ){
            alert("strDate1早于strDate2");
        }else if ( result==0 ){
            alert("strDate1等于strDate2");
        }
    } */
});
</script>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box cardTable">
		<form id="brandForm">
			<input type="hidden" id="id" name="id" value="${attendance.id}" />
			<table width="100%">
				<tr>
					<th class="tr">当前系统日期：</th>
					<td><input id="loginDate" type="text" name="loginDate" value="${attendance.loginDate}" readonly="readonly"> <!--  <input type="text" id="strDate1" name="strDate1" value="2012-07-01"/>
        <input type="text" id="strDate2" name="strDate2" value="2012-08-01"/>
        <input type="button" id="compareBtn" name="compareBtn" value="比较" onClick="doCompare();"/> -->
				</tr>
				<tr>
					<th class="tr">上下班签到：</th>
					<td><s:radio list="#{'0':'签到','1':'签离'}" name="reason" value="%{attendance.reason}" theme="simple"></s:radio></td>
				</tr>
			</table>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>