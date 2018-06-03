<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>标题 : <input type="text" name="questName" id="questName" class="int" /></td>
				<td>发布人 : <input type="text" name="uploadPersonName" id="uploadPersonName" class="int" /></td>
			</tr>
			<tr height="30">
				<td>有效期 : <input type="text" name="validity" id="validity" class="int" /></td>
				<td>任务权重 : <input type="text" name="taskWeights" id="taskWeights" class="int" /></td>
			</tr>
			<tr height="30">
				<td>执行人/部门 : <input type="text" name="executiveAgency" id="executiveAgency" class="int" /></td>
				<td>考核人/部门 : <input type="text" name="assessDepartment" id="assessDepartment" class="int" /></td>
			</tr>
		</table>
	</div>
</div>