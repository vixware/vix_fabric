<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>发送人 : <input type="text" name="uploadPersonName" id="uploadPersonName" class="int" /></td>
				<td>收信人 : <input type="text" name="recipients" id="recipients" class="int" /></td>
			</tr>
			<tr height="30">
				<td>手机号 : <input type="text" name="phoneNumber" id="phoneNumber" class="int" /></td>
				<td>内容 : <input type="text" name="phoneSmsContent" id="phoneSmsContent" class="int" /></td>
			</tr>
		</table>
	</div>
</div>