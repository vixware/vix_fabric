<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>编号 : <input type="text" name="bookCode" id="bookCode" class="int" /></td>
				<td>书名 : <input type="text" name="bookName" id="bookName" class="int" /></td>
			</tr>
			<tr height="30">
				<td>ISBN号 : <input type="text" name="ISBN" id="ISBN" class="int" /></td>
				<td>作者 : <input type="text" name="author" id="author" class="int" /></td>
			</tr>
			<tr height="30">
				<td>出版社 : <input type="text" name="press" id="press" class="int" /></td>
				<td>开本 : <input type="text" name="folio" id="folio" class="int" /></td>
			</tr>
			<tr height="30">
				<td>版次 : <input type="text" name="rev" id="rev" class="int" /></td>
				<td>印次 : <input type="text" name="impression" id="impression" class="int" /></td>
			</tr>
		</table>
	</div>
</div>