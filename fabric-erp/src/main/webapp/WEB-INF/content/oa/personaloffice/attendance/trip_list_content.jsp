<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var reason = "";
var approver ="";
var businessLocation ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadReason(){
	reason = $('#personal_reason').val();
	reason = Url.encode(reason);
	reason = Url.encode(reason);
}
function loadApprover(){
	approver = $('#personal_approver').val();
	approver = Url.encode(approver);
	approver = Url.encode(approver);
}
function loadBusinessLocation(){
	businessLocation = $('#personal_businessLocation').val();
	businessLocation = Url.encode(businessLocation);
	businessLocation = Url.encode(businessLocation);
}



/*判断搜索内容是否为空*/
function validateSearch(temp){
	if(null == temp || "" == temp){
		return false;
	}
	return true;
}

/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#personal_reason").val("");
		$("#personal_approver").val("");
		$("#personal_businessLocation").val("");
	}
}

/*改变搜索按钮的显示*/
 function changeDisplay(){
	var divText = $("#lb_search_advanced").text();
	if(divText == "高级搜索"){
		$("#nameS").attr({disabled:'true'});
		$("#simpleSearch").hide();
		$("#simpleReset").hide();
	}
	else{
		$("#nameS").removeAttr("disabled");
		$("#simpleSearch").show();
		$("#simpleReset").show();
	}
}


var searchUrl = "${vix}/oa/personalAttendanceAction!goSearchList2.action"; 
var listId3 = "newtab3";
function searchForContent(i){
	loadName();
	loadReason();
	loadApprover();
	loadBusinessLocation();
	if(i == 0){
		pager("start",searchUrl+"?i="+i+"&reason="+name,listId3);
	}
	else{
		pager("start",searchUrl+"?i="+i+"&reason="+reason+"&approver="+approver+"&businessLocation="+businessLocation,listId3);
	}
}


loadName();
//载入分页列表数据
pager("start","${vix}/oa/personalAttendanceAction!goTripRecordList.action?name="+name,'newtab3');
</script>
<div class="search_simple">
	<label>出差原因:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
	</label> <strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search' /></strong>
</div>
<div class="search_advanced">
	<label>出差原因:<input id="personal_reason" name="" type="text" class="int" /></label> <label>出差人:<input id="personal_approver" name="" type="text" class="int" /></label> <label>出差地区:<input id="personal_businessLocation" name="" type="text" class="int" /></label> <label> <input name="" type="button" class="btn"
		value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent();" />
	</label>
</div>