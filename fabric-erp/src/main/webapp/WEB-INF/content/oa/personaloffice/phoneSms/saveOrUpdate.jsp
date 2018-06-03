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
									$("#recipients").val(selectNames);
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
				<td><input id="recipients" name="recipients" style="width: 305px; height: 25px;" value="${phoneSms.recipients}" type="text" class="validate[required] text-input" /> <input class="btn" type="button" value="添加" onclick="addressee();" /> <input type="hidden" id="pubIds" name="phoneSms" value="${phoneSms.pubIds}" /></td>
			</tr>
			<tr>
				<td>手机号码：</td>
				<td><input id="phoneNumber" name="phoneNumber" style="width: 305px; height: 25px;" value="${phoneSms.phoneNumber}" type="text" /></td>
			</tr>
			<tr>
				<td>发送日期：</td>
				<td><input id="sendTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="sendTime" value="<fmt:formatDate value='${phoneSms.sendTime}' type='both' pattern='yyyy-MM-dd' />" type="text" style="width: 305px; height: 25px;" onclick="showTime('sendTime','yyyy-MM-dd')" /> <img onclick="showTime('sendTime','yyyy-MM-dd')"
					src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
			<tr>
				<td colspan="3"><textarea id="phoneSmsContent" name="phoneSmsContent" class="example" rows="2" style="width: 899px; height: 175px;">${phoneSms.phoneSmsContent }</textarea></td>
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