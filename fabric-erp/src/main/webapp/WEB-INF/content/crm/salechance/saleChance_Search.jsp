<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var subject = "";
var charger = "";
var customerName = "";
function loadConditions(){
	subject = $('#subject').val();
	subject=Url.encode(subject);
	subject=Url.encode(subject);
	charger = $('#charger').val();
	charger=Url.encode(charger);
	charger=Url.encode(charger);
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
				<td>主题 : <input type="text" name="subject" id="subject" class="int" />
				</td>
				<td>负责人 : <input type="charger" name="charger" id="charger" class="int" />
				</td>
				<td>客户名称 : <input type="text" name="customerName" id="customerName" class="int" />
				</td>
			</tr>
		</table>
	</div>
</div>