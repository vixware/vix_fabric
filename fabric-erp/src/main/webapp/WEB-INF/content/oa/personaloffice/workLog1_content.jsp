<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
//加载时间
$(function() {
    if (document.getElementById("logDate").value == "") {
	    var myDate = new Date();
	    $("#logDate").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours()+ ":" + myDate.getMinutes()+ ":" + myDate.getSeconds());
    }
    
  //默认选择类型
    if($('input:radio[name=logType]:checked').length==0){
    	$('input:radio[name=logType]:first').trigger('click');
    }
});
</script>
<div class="addleft">
	<div class="addbtn">
		<%-- <p> <img src="${vix}/common/img/address_book.png" /> 工作日志</p> --%>
		<!-- <a href="#">最新</a><a href="#">新建</a><a href="#">查询</a> -->
	</div>
	<div id="date2" class="date_box"></div>
	<script language="javascript" type="text/javascript">
		WdatePicker({eCont:'date2',dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'});
	</script>
	<div class="addbox">
		<div class="addtitle">最新点评</div>
		<ul class="ul_list">
			<s:iterator value="pager.resultList" var="entity" status="s">
				<li><a href="javascript:;" onclick="popNews('${entity.id}');">${entity.uploadPersonName}</a></li>
			</s:iterator>
		</ul>
	</div>
</div>
<div class="addright">
	<div class="addbox">
		<div class="addtitle">新建日志</div>
		<div class="addbox_content">
			<table class="add_stable">
				<tr>
					<td width=62px>日志标题：</td>
					<td><input id="title" name="title" value="${workLog.title}" type="text" style="width: 320px; height: 25px;" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<td>日志类型：</td>
					<td><s:radio list="#{'0':'工作日志','1':'个人日志'}" id="logType" name="logType" value="%{workLog.logType}" theme="simple"></s:radio></td>
				</tr>
				<tr>
					<td>时间：</td>
					<td><input id="logDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="logDate" onclick="showTime('logDate','yyyy-MM-dd HH:mm:ss')" value="<fmt:formatDate value='${workLog.logDate}' type='both' pattern='yyyy-MM-dd' />" type="text" style="width: 320px; height: 25px;" /> <img
						onclick="showTime('logDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr>
					<th align="right">内容：</th>
					<td colspan="3"><textarea id="logContent" name="logContent">${workLog.logContent}</textarea> <script type="text/javascript">
				 var logContents = KindEditor.create('textarea[name="logContent"]',
					{basePath:'${vix}/plugin/KindEditor/',
						width:580,
						height:178,
						cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
						uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
						fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
						allowFileManager : true 
					}
				);
				</script></td>
				</tr>
			</table>
		</div>
		<div class="add_s addtip">
			<table class="add_stable">
				<tr align="center">
					<td><input onclick="saveOrUpdateOrder();" type="button" value="保存" name="" class="sbtn"></td>
				</tr>
			</table>
		</div>
	</div>
</div>
</div>