<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>编码 : <input type="text" name="bookCodes" id="bookCodes" class="int" /></td>
				<td>借书证号 : <input type="text" name="bookNumber" id="bookNumber" class="int" /></td>
			</tr>
			<tr height="30">
				<td>书名 : <input type="text" name="bookNames" id="bookNames" class="int" /></td>
				<td>借用/归还人 : <input type="text" name="recipientsWho" id="recipientsWho" class="int" /></td>
			</tr>
			<tr height="30">
				<td>经办人 : <input type="text" name="uploadPersonName" id="uploadPersonName" class="int" /></td>
			</tr>
		</table>
	</div>
</div>