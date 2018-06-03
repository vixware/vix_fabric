<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var title = "";
var customerAccount = "";
var salePerson = "";
function loadConditions(){
	title = $('#title').val();
	title=Url.encode(title);
	title=Url.encode(title);
	customerAccount = $('#customerAccount').val();
	customerAccount=Url.encode(customerAccount);
	customerAccount=Url.encode(customerAccount);
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
				<td>主题 : <input type="text" name="title" id="title" class="int" />
				</td>
				<td>客户 : <input type="text" name="customerAccount" id="customerAccount" class="int" />
				</td>
				<td>业务员: <input type="text" name="salePerson" id="salePerson" class="int" />
				</td>
				</th>
			</tr>
		</table>
	</div>
</div>