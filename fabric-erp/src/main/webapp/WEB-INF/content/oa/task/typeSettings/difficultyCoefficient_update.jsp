<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#difficultyCoefficientForm").validationEngine();

$(function() {
    if($('input:radio[name=disabled]:checked').length==0){
    	$('input:radio[name=disabled]:first').trigger('click');
    }
});
</script>
<form id="difficultyCoefficientForm">
	<div style="padding: 5px;">
		<s:hidden id="id" name="difficultyCoefficient.id" value="%{difficultyCoefficient.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">编码:&nbsp;</td>
					<td><input id="code" name="difficultyCoefficient.code" value="${difficultyCoefficient.code}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">难度系数:&nbsp;</td>
					<td><input id="name" name="difficultyCoefficient.name" value="${difficultyCoefficient.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr>
					<td align="right">是否启用:&nbsp;</td>
					<td><s:radio list="#{'0':'否','1':'是'}" name="disabled" value="%{difficultyCoefficient.disabled}" theme="simple"></s:radio></td>
				</tr>
			</table>
		</div>
	</div>
</form>