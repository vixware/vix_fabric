<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var returnOrderCode = "";
var customerName = "";
function loadConditions(){
	returnOrderCode = $('#returnOrderCode').val(); 
	returnOrderCode=Url.encode(returnOrderCode);
	returnOrderCode=Url.encode(returnOrderCode);
	customerName = $('#customerName').val(); 
	customerName=Url.encode(customerName);
	customerName=Url.encode(customerName);
}
loadConditions();
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<th>
				<td>单据编码 : <input type="text" name="returnOrderCode" id="returnOrderCode" class="int" />
				</td>
				<td>客户名称 : <input type="text" name="customerAccount" id="customerAccount" class="int" />
				</td>
				</th>
			</tr>
		</table>
	</div>
</div>