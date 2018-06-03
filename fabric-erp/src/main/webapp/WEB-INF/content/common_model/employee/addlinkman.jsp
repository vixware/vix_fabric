<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">
$(function(){
	//设置选中
	$("#contactType").val('${linkman.contactType}');
});
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="caForm">
		<s:hidden id="daId" name="linkman.id" value="%{linkman.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">序号:&nbsp;</th>
					<td><input id="employeeCode" name="linkman.employeeCode" value="${linkman.employeeCode}" type="text" /></td>
					<th align="right">联系人:&nbsp;</th>
					<td><input id="contact" name="linkman.contact" value="${linkman.contact}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">类型:&nbsp;</th>
					<td><select id="contactType" name="contactType" style="width: 50">
							<option value="">请选择</option>
							<option value="1">手机</option>
							<option value="2">传真</option>
							<option value="3">电子邮件</option>
					</select></td>
					<th align="right">内容:&nbsp;</th>
					<td><input id="contactContent" name="linkman.contactContent" value="${linkman.contactContent}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">备注:&nbsp;</th>
					<td><input id="remarks" name="linkman.remarks" value="${linkman.remarks}" type="text" /></td>
				</tr>

			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#caForm").validationEngine();
</script>