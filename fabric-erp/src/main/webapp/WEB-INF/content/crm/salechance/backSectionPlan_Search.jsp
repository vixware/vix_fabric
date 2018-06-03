<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var charger = "";
var customerName = "";
function loadConditions(){
	owner = $('#owner').val();
	owner=Url.encode(owner);
	owner=Url.encode(owner);
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
				<td>回款客户 : <input type="text" name="customerName" id="customerName" class="int" />
				</td>
				<td>所有人 : <input type="text" name="owner" id="owner" class="int" />
				</td>
			</tr>
		</table>
	</div>
</div>