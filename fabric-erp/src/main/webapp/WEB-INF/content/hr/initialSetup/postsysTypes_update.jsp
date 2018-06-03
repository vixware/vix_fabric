<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#courseTypeForm").validationEngine();

$(function() {
    if($('input:radio[name=disabled]:checked').length==0){
    	$('input:radio[name=disabled]:first').trigger('click');
    }
});
</script>
<form id="courseTypeForm">
	<div style="padding: 5px;">
		<s:hidden id="id" name="postsysTypes.id" value="%{postsysTypes.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">编码:&nbsp;</td>
					<td><input id="code" name="postsysTypes.code" value="${postsysTypes.code}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">职务类型:&nbsp;</td>
					<td><input id="name" name="postsysTypes.name" value="${postsysTypes.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr>
					<td align="right">是否启用:&nbsp;</td>
					<td><s:radio list="#{'0':'是','1':'否'}" name="disabled" value="%{postsysTypes.disabled}" theme="simple"></s:radio></td>
				</tr>
			</table>
		</div>
	</div>
</form>