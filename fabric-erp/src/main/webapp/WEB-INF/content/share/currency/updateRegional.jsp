<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
$("#regionalForm").validationEngine();
//-->
</script>
<input type="hidden" id="id" name="id" value="${regional.id}" />
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="regionalForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td width="10%" align="right">编码:</td>
					<td width="40%"><input id="code" name="regional.code" value="${regional.code}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td width="10%" align="right">名称:</td>
					<td width="40%"><input id="name" name="regional.name" value="${regional.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">状态:</td>
					<td><s:if test="regional.enable == 1">
							<input type="radio" id="enable1" name="enable" value="1" checked="checked" />启用
							<input type="radio" id="enable1" name="enable" value="0" />禁用
						</s:if> <s:elseif test="regional.enable == 0">
							<input type="radio" id="enable1" name="enable" value="1" />启用
							<input type="radio" id="enable1" name="enable" value="0" checked="checked" />禁用
						</s:elseif> <s:else>
							<input type="radio" id="enable1" name="enable" value="1" checked="checked" />启用
							<input type="radio" id="enable1" name="enable" value="0" />禁用
						</s:else></td>
					<td align="right">备注:</td>
					<td><input id="memo" name="regional.memo" value="${regional.memo}" type="text" size="30" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
