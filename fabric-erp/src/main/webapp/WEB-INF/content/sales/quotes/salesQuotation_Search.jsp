<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var quoteSubject = "";
var customerAccount = "";
var startTime = "";
var endTime = "";
function loadConditions(){
	quoteSubject = $('#quoteSubject').val();
	quoteSubject=Url.encode(quoteSubject);
	quoteSubject=Url.encode(quoteSubject);
	customerAccount = $('#customerAccount').val();
	customerAccount=Url.encode(customerAccount);
	customerAccount=Url.encode(customerAccount);
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
}
loadConditions();
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<th>
				<td>主题 : <input type="text" name="quoteSubject" id="quoteSubject" class="int" />
				</td>
				<td>客户 : <input type="text" name="customerAccount" id="customerAccount" class="int" />
				</td>
				</th>
			</tr>
			<tr height="30">
				<th>
				<td>单据日期从:<input id="startTime" class="int" type="text" onFocus="var endTime=$dp.$('endTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'startTime\')}'})" /> 至: <input id="endTime" class="int" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'endTime\')}'})" /></td>
				</th>
			</tr>
		</table>
	</div>
</div>