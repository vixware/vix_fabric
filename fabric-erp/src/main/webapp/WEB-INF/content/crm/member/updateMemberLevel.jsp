<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#memberLevelForm").validationEngine();
	if ($('#levelType').val() != "1") {
		$('#levelTypeB').attr('checked', 'checked');
	} else {
		$('#levelTypeA').attr('checked', 'checked');
	}
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="memberLevelForm">
		<div class="box order_table" style="line-height: normal;">
			<s:hidden id="memberLevelId" name="memberLevelId" value="%{memberLevel.id}" theme="simple" />
			<s:hidden id="levelType" name="levelType" value="%{memberLevel.levelType}" theme="simple" />
			<table class="table-padding020">
				<tr height="30">
					<td>编码:<input type="text" id="code" name="code" value="${memberLevel.code}" style="width: 300px; margin-left: 20px;" /></td>
				</tr>
				<tr height="30">
					<td>名称:<input type="text" id="name" name="name" value="${memberLevel.name}" style="width: 300px; margin-left: 20px;" /></td>
				</tr>
				<tr height="30">
					<td><input type="radio" name="levelType" id="levelTypeA" value="1" />金额:<input type="text" id="fromAmount" name="fromAmount" value="${memberLevel.fromAmount}" style="width: 215px;" />~<input type="text" id="toAmount" name="toAmount" value="${memberLevel.toAmount}" style="width: 215px;" /><span>(元)</span></td>
				</tr>
				<tr height="30">
					<td><input type="radio" name="levelType" id="levelTypeB" value="2" />积分:<input type="text" id="fromPoints" name="fromPoints" value="${memberLevel.fromPoints}" style="width: 215px;" />~<input type="text" id="toPoints" name="toPoints" value="${memberLevel.toPoints}" style="width: 215px;" /></td>
				</tr>
				<tr height="30">
					<td>备注:<input type="text" id="memo" name="memo" value="${memberLevel.memo}" style="margin-left: 20px; width: 450px;" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>