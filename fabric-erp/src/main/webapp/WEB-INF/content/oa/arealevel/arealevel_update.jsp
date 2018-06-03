<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#areaLevelForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="areaLevelForm">
		<s:hidden id="areaLevelId" name="areaLevelId" value="%{areaLevel.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="code" name="code" class="validate[required] text-input" value="${areaLevel.code}" style="width: 175px;" /><span style="color: red;">*</span></td>
					<td align="right">地区名称:&nbsp;</td>
					<td><input type="text" id="name" name="name" class="validate[required] text-input" value="${areaLevel.name}" style="width: 175px;" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>