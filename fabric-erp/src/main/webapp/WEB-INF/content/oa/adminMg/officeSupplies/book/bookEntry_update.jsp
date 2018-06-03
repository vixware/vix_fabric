<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#bookCabinetForm").validationEngine();
function bookBaseCategory() {
	$.ajax({
	url : '${vix}/oa/bookEntryAction!goBookCategory.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 560,
		height : 340,
		title : "选择分类",
		html : html,
		callback : function(action, returnValue) {
			if (action == 'ok') {
				if (returnValue != undefined) {
					var result = returnValue.split(",");
					$("#bookCategoryId").val(result[0]);
					$("#bookCategoryName").val(result[1]);
				} else {
					$("#bookCategoryId").val("");
					$("#bookCategoryName").val("");
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
	<form id="bookCabinetForm" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden id="bookEntryId" name="bookEntry.id" value="%{bookEntry.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">分类:&nbsp;</td>
					<td><s:hidden id="bookCategoryId" name="bookEntry.bookCategory.id" value="%{bookEntry.bookCategory.id}" theme="simple" /> <input type="text" id="bookCategoryName" name="bookEntry.bookCategory.name" value="${bookEntry.bookCategory.name}" readonly="readonly" /> <input class="btn" type="button" value="选择" onclick="bookBaseCategory();" />
					</td>
				</tr>
				<tr height="40">
					<td align="right">图书类别:&nbsp;</td>
					<td><input type="text" id="bookType" name="bookEntry.bookType" class="validate[required] text-input" value="${bookEntry.bookType}" /><span style="color: red;">*</span></td>
					<td align="right">图书编码:&nbsp;</td>
					<td><input type="text" id="bookCode" name="bookEntry.bookCode" class="validate[required] text-input" value="${bookEntry.bookCode}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">ISBN号:&nbsp;</td>
					<td><input type="text" id="ISBN" name="bookEntry.ISBN" class="validate[required] text-input" value="${bookEntry.ISBN}" /><span style="color: red;">*</span></td>
					<td align="right">书名:&nbsp;</td>
					<td><input type="text" id="bookName" name="bookEntry.bookName" class="validate[required] text-input" value="${bookEntry.bookName}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">作者:&nbsp;</td>
					<td><input type="text" id="author" name="bookEntry.author" class="validate[required] text-input" value="${bookEntry.author}" /><span style="color: red;">*</span></td>
					<td align="right">出版社:&nbsp;</td>
					<td><input type="text" id="press" name="bookEntry.press" class="validate[required] text-input" value="${bookEntry.press}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">开本:&nbsp;</td>
					<td><input type="text" id="folio" name="bookEntry.folio" class="validate[required] text-input" value="${bookEntry.folio}" /><span style="color: red;">*</span></td>
					<td align="right">印张:&nbsp;</td>
					<td><input type="text" id="sheet" name="bookEntry.sheet" class="validate[required] text-input" value="${bookEntry.sheet}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">版次:&nbsp;</td>
					<td><input type="text" id="rev" name="bookEntry.rev" class="validate[required] text-input" value="${bookEntry.rev}" /><span style="color: red;">*</span></td>
					<td align="right">印次:&nbsp;</td>
					<td><input type="text" id="impression" name="bookEntry.impression" class="validate[required] text-input" value="${bookEntry.impression }" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">印数:&nbsp;</td>
					<td><input type="text" id="printing" name="bookEntry.printing" class="validate[required] text-input" value="${bookEntry.printing}" /><span style="color: red;">*</span></td>
					<td align="right">定价:&nbsp;</td>
					<td><input type="text" id="price" name="bookEntry.price" class="validate[required] text-input" value="${bookEntry.price}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">存放地点:&nbsp;</td>
					<td><input type="text" id="address" name="bookEntry.address" class="validate[required] text-input" value="${bookEntry.address}" /><span style="color: red;">*</span></td>
					<td align="right">数量:&nbsp;</td>
					<td><input type="text" id="amount" name="bookEntry.amount" class="validate[required] text-input" value="${bookEntry.amount}" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>