<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>型号 : <input type="text" name="model" id="model" class="int" /></td>
				<td>车牌号 : <input type="text" name="plateNumber" id="plateNumber" class="int" /></td>
			</tr>
			<tr height="30">
				<td>引擎号 : <input type="text" name="engineNumber" id="engineNumber" class="int" /></td>
				<td>购买价 : <input type="text" name="price" id="price" class="int" /></td>
			</tr>
		</table>
	</div>
</div>