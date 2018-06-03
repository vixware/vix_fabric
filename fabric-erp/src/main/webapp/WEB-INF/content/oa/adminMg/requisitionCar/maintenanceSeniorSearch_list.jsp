<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>维护原因 : <input type="text" name="maintenanceReason" id="maintenanceReason" class="int" /></td>
				<td>经办人 : <input type="text" name="uploadPersonName" id="uploadPersonName" class="int" /></td>
			</tr>
			<tr height="30">
				<td>维护费用 : <input type="text" name="maintenanceCost" id="maintenanceCost" class="int" /></td>
				<td>维护情况 : <input type="text" name="maintenance" id="maintenance" class="int" /></td>
			</tr>
		</table>
	</div>
</div>