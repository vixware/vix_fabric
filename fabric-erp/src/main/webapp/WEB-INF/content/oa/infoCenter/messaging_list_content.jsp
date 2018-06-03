<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
/**发送消息*/
function saveOrSend(){
	$.post('${vix}/oa/messageManagementAction!saveMessaging.action',
		{
		'messageManagement.id':$("#messageId").val(),	
		'messageManagement.senderPeople':$("#senderPeople").val(),						  
		'messageManagement.ccPeople':$("#ccPeople").val(),						  
		'messageManagement.ccPeople1':$("#ccPeople1").val(),	
		'messageManagement.sendDate':$("#sendDate").val(),	
		'messageManagement.newscontent' : newscontents.html(),
		'messageManagement.pubIds' : $("#pubIds").val(),
		'messageManagement.isTop':$('input:radio[name=isTop]').val()
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
		loadContent('${vix}/oa/messageManagementAction!goList.action?senderPeople=${senderPeople}');
		}
	);
}

/**保存为草稿消息*/
function saveOrDrafts(){
	$.post('${vix}/oa/messageManagementAction!saveUnsentMessages.action',
		{
		'messageManagement.senderPeople':$("#senderPeople").val(),						  
		'messageManagement.ccPeople':$("#ccPeople").val(),						  
		'messageManagement.ccPeople1':$("#ccPeople1").val(),	
		'messageManagement.sendDate':$("#sendDate").val(),	
		'messageManagement.pubIds' : $("#pubIds").val(),
		'messageManagement.newscontent' : newscontents.html(),	
		'messageManagement.isTop':$('input:radio[name=isTop]').val()
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
		loadContent('${vix}/oa/messageManagementAction!goList.action?senderPeople=${senderPeople}');
		}
	);
}

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
									$("#pubIds").val(selectIds+",");
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
	
	/**
	 * 上传附件
	 */
	function editEntity(id){
			$.ajax({
				  url:"${vix}/oa/messageManagementAction!eqSbwdEdit.action?id="+id,
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 780,
							height : 280,
							title:"上传附件",
							html:html,
							callback : function(action){
								if(action == 'ok'){
										$("#brandForm").ajaxSubmit({
										     type: "post",
										     url: "${vix}/oa/messageManagementAction!saveEqSbwd.action",
										     dataType: "text",
										     success: function(result){
										    	return true; 
											//loadContent ('${vix}/oa/messageManagementAction!goMessagingList.action?id='+result);
										     }
										 });
								}
							},
							btnsbar : $.btn.OKCANCEL
						});
				  }
			});
		};
		
		
		$(function() {
		    //加载发送时间(新增)
		    if (document.getElementById("sendDate").value == "") {
			    var myDate = new Date();
			    $("#sendDate").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours()+ ":" + myDate.getMinutes()+ ":" + myDate.getSeconds());
		    }
		});
</script>

<div class="addbox">
	<div class="addtitle">发送消息</div>
	<div class="addbox_content">
		<table class="add_stable">
			<input type="hidden" id="id" name="id" value="${messageManagement.id}" />
			<tr>
				<td>收 信 人：</td>
				<td><input id="senderPeople" name="senderPeople" style="width: 305px; height: 25px;" value="${messageManagement.senderPeople}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span> <input class="btn" type="button" value="添加" onclick="addressee();" /> <input type="hidden" id="pubIds"
					name="messageManagement" value="${messageManagement.pubIds}" /></td>
			</tr>
			<tr>
				<td>抄 送 人：</td>
				<td><input id="ccPeople" name="ccPeople" style="width: 305px; height: 25px;" value="${messageManagement.ccPeople}" type="text" /> <span style="color: red;">*</span> <input class="btn" type="button" value="添加" onclick="everybody();" /></td>
			</tr>
			<tr>
				<td>抄 送 人：</td>
				<td><input id="ccPeople1" name="ccPeople1" style="width: 305px; height: 25px;" value="${messageManagement.ccPeople1}" type="text" /> <span style="color: red;">*</span> <input class="btn" type="button" value="添加" onclick="everybody1();" /></td>
			</tr>
			<tr>
				<td>发送日期：</td>
				<td><input id="sendDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="sendDate" value="<fmt:formatDate value='${messageManagement.sendDate}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" type="text" style="width: 305px; height: 25px;" onclick="showTime('sendDate','yyyy-MM-dd HH:mm:ss')" /> <img
					onclick="showTime('sendDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
			<tr>
				<td>附件选择：</td>
				<td><a href="#" onclick="editEntity(0,$('#id').val());">添加文件</a></td>
			</tr>
			<tr>
				<td colspan="3"><textarea id="newscontent" name="newscontent">${messageManagement.newscontent}</textarea> <script type="text/javascript">
				 var newscontents = KindEditor.create('textarea[name="newscontent"]',
					{basePath:'${vix}/plugin/KindEditor/',
						width:656,
						height:260,
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
			<tr align="right">
				<td><input type="button" onclick="saveOrDrafts();" value="保存草稿" name="" class="sbtn"></td>
				<td width="10"><input type="button" onclick="saveOrSend();" value="发送" name="" class="sbtn"></td>
			</tr>
		</table>
	</div>
</div>