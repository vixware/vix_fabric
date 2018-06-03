<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var customerAccount = "";
var ddCode = "";
var salePerson = "";
function loadConditions(){
	customerAccount = $('#customerAccount').val();
	customerAccount=Url.encode(customerAccount);
	customerAccount=Url.encode(customerAccount);
	ddCode = $("#startTime").val();
	ddCode=Url.encode(ddCode);
	ddCode=Url.encode(ddCode);
	salePerson = $("#salePerson").val();
	salePerson=Url.encode(salePerson);
	salePerson=Url.encode(salePerson);
}
loadConditions();
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<th>
				<td>发货单号 : <input type="text" name="ddCode" id="ddCode" class="int" />
				</td>
				<td>客户名称 : <input type="text" name="customerAccount" id="customerAccount" class="int" />
				</td>
				<td>业务员 : <input type="text" name="salePerson" id="salePerson" class="int" />
				</td>
				</th>
			</tr>
		</table>
	</div>
</div>