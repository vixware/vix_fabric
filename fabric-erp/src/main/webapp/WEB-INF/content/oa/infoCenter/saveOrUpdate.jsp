<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	/**添加收信人*/
	function addressee(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 800,
						height : 600,
						title:"选择收信人",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var selectIds = "";
									var selectNames = "";
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											
											selectIds += "," + v[0];
											selectNames = v[1];
										}
									}
									$("#senderPeople").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	/**添加抄送人*/
	function everybody(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 800,
						height : 600,
						title:"选择抄送人",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var selectIds = "";
									var selectNames = "";
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											
											selectIds += "," + v[0];
											selectNames = v[1];
										}
									}
									$("#ccPeople").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	
	/**添加抄送人*/
	function everybody1(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 800,
						height : 600,
						title:"选择抄送人",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var selectIds = "";
									var selectNames = "";
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											
											selectIds += "," + v[0];
											selectNames = v[1];
										}
									}
									$("#ccPeople1").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
</script>
<input type="hidden" name="" id="messageId" value="${id}" />
<div class="addbox">
	<div class="addtitle">草稿箱</div>
	<div class="addbox_content">
		<table class="add_stable">
			<tr>
				<td>收 信 人：</td>
				<td><input id="senderPeople" name="senderPeople" style="width: 305px; height: 25px;" value="${messageManagement.senderPeople}" type="text" class="validate[required] text-input" /> <input class="btn" type="button" value="添加" onclick="addressee();" /></td>
			</tr>
			<tr>
				<td>抄 送 人：</td>
				<td><input id="ccPeople" name="ccPeople" style="width: 305px; height: 25px;" value="${messageManagement.ccPeople}" type="text" /> <input class="btn" type="button" value="添加" onclick="everybody();" /></td>
			</tr>
			<tr>
				<td>抄 送 人：</td>
				<td><input id="ccPeople1" name="ccPeople1" style="width: 305px; height: 25px;" value="${messageManagement.ccPeople1}" type="text" /> <input class="btn" type="button" value="添加" onclick="everybody1();" /></td>
			</tr>
			<tr>
				<td>发送日期：</td>
				<td><input id="sendDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="sendDate" value="<fmt:formatDate value='${messageManagement.sendDate}' type='both' pattern='yyyy-MM-dd' />" type="text" style="width: 305px; height: 25px;" onclick="showTime('sendDate','yyyy-MM-dd')" /> <img onclick="showTime('sendDate','yyyy-MM-dd')"
					src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
			<tr>
				<td>附件选择：</td>
				<td><a href="javascript:editEntity();">添加文件</a></td>
			</tr>
			<tr>
				<td colspan="3"><textarea id="newscontent" name="newscontent" class="example" rows="2" style="width: 899px; height: 175px;">${messageManagement.newscontent }</textarea></td>
			</tr>
		</table>
	</div>
	<div class="add_s addtip">
		<table class="add_stable">
			<tr align="right">
				<td width="10"><input type="button" onclick="saveOrSend();" value="发送" name="" class="sbtn"></td>
			</tr>
		</table>
	</div>
</div>