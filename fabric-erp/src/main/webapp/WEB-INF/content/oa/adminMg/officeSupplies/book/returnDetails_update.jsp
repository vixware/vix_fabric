<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#bookBorrowForm").validationEngine();

$(function() {
    if($('input:radio[name=disabled]:checked').length==0){
    	$('input:radio[name=disabled]:first').trigger('click');
    }
});
</script>
<form id="bookBorrowForm">
	<div style="padding: 5px;">
		<input type="hidden" id="bookEntryId" name="bookEntryId" value="${bookBorrow.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">图书编码:&nbsp;</td>
					<td><input disabled="disabled" id="code" name="bookBorrow.code" value="${bookBorrow.code}" class="validate[required] text-input" /></span></td>
					<td align="right">图书名称:&nbsp;</td>
					<td><input disabled="disabled" id="bookName" name="bookBorrow.bookName" value="${bookBorrow.bookName}" class="validate[required] text-input" /></span></td>
				</tr>
				<tr height="30">
					<td align="right">借书卡号:&nbsp;</td>
					<td><input disabled="disabled" id="bookNumber" name="bookBorrow.bookRegister.bookNumber" value="${bookBorrow.bookRegister.bookNumber}" class="validate[required] text-input" /></td>
					<td align="right">借书人:&nbsp;</td>
					<td><input disabled="disabled" id="recipientsWho" name="bookBorrow.bookRegister.recipientsWho" value="${bookBorrow.bookRegister.recipientsWho}" class="validate[required] text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">借书量:&nbsp;</td>
					<td><input disabled="disabled" id="totalNumber" name="bookBorrow.totalNumber" value="${bookBorrow.totalNumber}" class="validate[required] text-input" /></span></td>
					<td align="right">借书时间:&nbsp;</td>
					<td><input disabled="disabled" id="startTime" name="bookBorrow.startTime" value="${bookBorrow.startTime}" class="validate[required] text-input" /></span></td>
				</tr>
				<tr height="30">
					<td align="right">未还量:&nbsp;</td>
					<td><input disabled="disabled" id="borrowNumber" name="bookBorrow.borrowNumber" value="${bookBorrow.borrowNumber}" class="validate[required] text-input" /></span></td>
					<td align="right">归还量:&nbsp;</td>
					<td><input id="returnNumber" name="bookBorrow.returnNumber" value="${bookBorrow.returnNumber}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</div>
</form>