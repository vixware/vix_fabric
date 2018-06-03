<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>事物主题 : <input type="text" name="theme" id="theme" class="int" /></td>
				<td>当事人 : <input type="text" name="employeeName" id="employeeName" class="int" /></td>
			</tr>
			<tr height="30">
				<td>审批人 : <input type="text" name="uploadPersonName" id="uploadPersonName" class="int" /></td>
				<td>试用期职务 : <input type="text" name="probationPost" id="probationPost" class="int" /></td>
			</tr>
			<tr height="30">
				<td>转正后所属部门 : <input type="text" name="afterThePromotionDepartment" id="afterThePromotionDepartment" class="int" /></td>
				<td>转正后职位 : <input type="text" name="afterThePromotionPost" id="afterThePromotionPost" class="int" /></td>
			</tr>
		</table>
	</div>
</div>