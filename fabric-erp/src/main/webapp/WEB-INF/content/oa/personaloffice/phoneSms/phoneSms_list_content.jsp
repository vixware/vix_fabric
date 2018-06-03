<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
/**发送消息*/
function saveOrSend(){
	$.post('${vix}/oa/phoneSmsAction!saveMessaging.action',
		{
		'phoneSms.id':$("#messageId").val(),	
		'phoneSms.recipients':$("#recipients").val(),						  
		'phoneSms.phoneNumber':$("#phoneNumber").val(),						  	
		'phoneSms.sendTime':$("#sendTime").val(),	
		'phoneSms.phoneSmsContent' : phoneSmsContents.html(),
		'phoneSms.pubIds' : $("#pubIds").val(),
		'phoneSms.isTop':$('input:radio[name=isTop]').val()
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
		loadContent('${vix}/oa/phoneSmsAction!goList.action?recipients=${recipients}');
		}
	);
}

/**保存为草稿消息*/
function saveOrDrafts(){
	$.post('${vix}/oa/phoneSmsAction!saveUnsentMessages.action',
		{
		'phoneSms.recipients':$("#recipients").val(),						  
		'phoneSms.phoneNumber':$("#phoneNumber").val(),						  
		'phoneSms.sendTime':$("#sendTime").val(),	
		'phoneSms.pubIds' : $("#pubIds").val(),
		'phoneSms.phoneSmsContent' : phoneSmsContents.html(),	
		'phoneSms.isTop':$('input:radio[name=isTop]').val()
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
		loadContent('${vix}/oa/phoneSmsAction!goList.action?recipients=${recipients}');
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
									$("#recipients").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
		
	$(function() {
	    //加载发送时间(新增)
	    if (document.getElementById("sendTime").value == "") {
		    var myDate = new Date();
		    $("#sendTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours()+ ":" + myDate.getMinutes()+ ":" + myDate.getSeconds());
	    }
	});
</script>

<div class="addbox">
	<div class="addtitle">发送消息</div>
	<div class="addbox_content">
		<table class="add_stable">
			<input type="hidden" id="id" name="id" value="${phoneSms.id}" />
			<tr>
				<td>收 信 人：</td>
				<td><input id="recipients" name="recipients" style="width: 305px; height: 25px;" value="${phoneSms.recipients}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span> <input class="btn" type="button" value="添加" onclick="addressee();" /> <input type="hidden" id="pubIds" name="phoneSms"
					value="${phoneSms.pubIds}" /></td>
			</tr>
			<tr>
				<td>手机号码：</td>
				<td><input id="phoneNumber" name="phoneNumber" style="width: 305px; height: 25px;" value="${phoneSms.phoneNumber}" type="text" /> <span style="color: red;">*</span></td>
			</tr>
			<tr>
				<td>发送日期：</td>
				<td><input id="sendTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="sendTime" value="<fmt:formatDate value='${phoneSms.sendTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" type="text" style="width: 305px; height: 25px;" onclick="showTime('sendTime','yyyy-MM-dd HH:mm:ss')" /> <img
					onclick="showTime('sendTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
			<tr>
				<td colspan="3"><textarea id="phoneSmsContent" name="phoneSmsContent">${phoneSms.phoneSmsContent}</textarea> <script type="text/javascript">
							 var phoneSmsContents = KindEditor.create('textarea[name="phoneSmsContent"]',
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