<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
//设置性别选中
$(document).ready(function(){
	$("#newSex").val('${contacts.sex }');
	$('.tc_texticon').live('click',function(){
		if($.trim($(this).text()) == '+'){
			$(this).closest('td').append('<div class="tc_copay">'+$(this).closest('.tc_copay').html()+'</div>');
			$('.tc_texticon',$(this).closest('td')).text('-');
			$('.tc_texticon:last',$(this).closest('td')).text('+');
		}else if($.trim($(this).text()) == '-'){
			$(this).closest('.tc_copay').remove();
		}
	});
});
$("#contactsForm").validationEngine();
//选择分类
function chooseContactsCategory(){
	$.ajax({
		  url:'${vix}/common/contactsAction!goChooseContactsCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择所属分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
									$("#parentId").val(result[0]);
									$("#catalog").val(result[1]);
							}else{
								$("#parentId").val("");
								$("#catalog").val("");
								asyncbox.success("请选择所属分类信息!","提示信息");
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
	<form id="contactsForm">
		<s:hidden id="cId" name="contacts.id" value="%{contacts.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="newCode" value="${contacts.code}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">姓名:&nbsp;</th>
					<td><input id="newName" value="${contacts.name}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">年龄:&nbsp;</th>
					<td><input id="newAge" value="${contacts.age}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">性别:&nbsp;</th>
					<td><select id="newSex" class="validate[required] text-input">
							<option value="">请选择</option>
							<option value="0">男</option>
							<option value="1">女</option>
					</select> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">联系电话:&nbsp;</th>
					<td><input id="newTelephone" value="${contacts.telephone}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">所属分类：</td>
					<td><input type="hidden" id="parentId" value="${contacts.contactsCategory.id }" /> <input id="catalog" value="${contacts.contactsCategory.name }" type="text" size="30" readonly="readonly" class="validate[required] text-input" /> <span style="color: red;">*</span> <input class="btn" type="button" value="选择"
						onclick="chooseContactsCategory();" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>