<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#itemReplaceForm").validationEngine();
function chooseItem(){
	$.ajax({
		  url:'${vix}/mdm/item/itemAction!goChooseItem.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 580,
					title:"选择${vv:varView('vix_mdm_item')}",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != ''){
								var r = returnValue.split(",");
								$("#replaceItemId").val(r[0]);
								$("#replaceItemCode").val(r[1]);
								$("#replaceItemName").val(r[2]);
							}else{
								asyncbox.success("请选择${vv:varView('vix_mdm_item')}!","<s:text name='vix_message'/>");
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="itemReplaceForm">
		<s:hidden id="irId" name="itemReplace.id" value="%{itemReplace.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<td align="right">${vv:varView('vix_mdm_item')}编码</td>
					<td><input id="replaceItemCode" value="${itemReplace.item.code}" /></td>
					<td align="right">${vv:varView('vix_mdm_item')}名称:&nbsp;</td>
					<td><input id="replaceItemName" value="${itemReplace.item.name}" /> <input id="replaceItemId" type="hidden" value="${itemReplace.item.id}" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem();">选择</span></span></td>
				</tr>
				<tr height="40">
					<th align="right">优先级:&nbsp;</th>
					<td><input id="priority" name="itemReplace.priority" value="${itemReplace.priority}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">备注:&nbsp;</th>
					<td><input id="memo" name="itemReplace.memo" value="${itemReplace.memo}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>