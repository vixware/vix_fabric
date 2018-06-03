<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var loginPerson = "";
var loginOrder ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadLoginPerson(){
	loginPerson = $('#personal_loginPerson').val();
	loginPerson = Url.encode(loginPerson);
	loginPerson = Url.encode(loginPerson);
}
function loadLoginOrder(){
	loginOrder = $('#personal_loginOrder').val();
	loginOrder = Url.encode(loginOrder);
	loginOrder = Url.encode(loginOrder);
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
		$("#personal_loginPerson").val("");
		$("#personal_loginOrder").val("");
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


var searchUrl = "${vix}/oa/personalAttendanceAction!goSearchList4.action"; 
var listId5 = "newtab5";
function searchForContent(i){
	loadName();
	loadLoginPerson();
	loadLoginOrder();
	if(i == 0){
		pager("start",searchUrl+"?i="+i+"&loginPerson="+name,listId5);
	}
	else{
		pager("start",searchUrl+"?i="+i+"&loginPerson="+loginPerson+"&loginOrder="+loginOrder,listId5);
	}
}
</script>
<div class="search_simple">
	<label>录入人:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
	</label> <strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search' /></strong>
</div>
<div class="search_advanced">
	<label>录入人:<input id="personal_loginPerson" name="" type="text" class="int" /></label> <label>登录编号:<input id="personal_loginOrder" name="" type="text" class="int" /></label> <label> <input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input name="" type="button" class="btn"
		value="<s:text name='cmn_reset'/>" onclick="resetForContent();" />
	</label>
</div>