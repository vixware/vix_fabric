<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>合同编码 : <input type="text" name="contractCode" id="contractCode" class="int" /></td>
				<td>模板编号 : <input type="text" name="templeteId" id="templeteId" class="int" /></td>
			</tr>
			<tr height="30">
				<td>审批编号 : <input type="text" name="contractApproveCode" id="contractApproveCode" class="int" /></td>
				<td>主题 : <input type="text" name="theme" id="theme" class="int" /></td>
			</tr>
			<tr height="30">
				<td>创建人 : <input type="text" name="uploadPersonName" id="uploadPersonName" class="int" /></td>
			</tr>
		</table>
	</div>
</div>