<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>主题 : <input type="text" name="noticeTheName" id="noticeTheName" class="int" /></td>
				<td>通知部门 : <input type="text" name="companyOrDepartment" id="companyOrDepartment" class="int" /></td>
			</tr>
			<tr height="30">
				<td>发送部门 : <input type="text" name="sendObj" id="sendObj" class="int" /></td>
				<td>发布人 : <input type="text" name="uploadPersonName" id="uploadPersonName" class="int" /></td>
			</tr>
		</table>
	</div>
</div>