<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>收信人 : <input type="text" name="senderPeople" id="senderPeople" class="int" /></td>
				<td>抄送人 : <input type="text" name="ccPeople" id="ccPeople" class="int" /></td>
			</tr>
			<tr height="30">
				<td>抄送人1 : <input type="text" name="ccPeople1" id="ccPeople1" class="int" /></td>
				<td>内容 : <input type="text" name="newscontent" id="newscontent" class="int" /></td>
			</tr>
		</table>
	</div>
</div>