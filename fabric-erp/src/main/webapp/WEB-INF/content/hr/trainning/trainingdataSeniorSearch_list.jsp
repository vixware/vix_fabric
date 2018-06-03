<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>资料编码 : <input type="text" name="datumCode" id="datumCode" class="int" /></td>
				<td>资料名称 : <input type="text" name="courseName" id="courseName" class="int" /></td>
			</tr>
			<tr height="30">
				<td>资料费用 : <input type="text" name="datumCost" id="datumCost" class="int" /></td>
				<td>存放位置 : <input type="text" name="storageLocation" id="storageLocation" class="int" /></td>
			</tr>
			<tr height="30">
				<td>作者 : <input type="text" name="datumauthor" id="datumauthor" class="int" /></td>
				<td>出版社 : <input type="text" name="publishingUnit" id="publishingUnit" class="int" /></td>
			</tr>
		</table>
	</div>
</div>