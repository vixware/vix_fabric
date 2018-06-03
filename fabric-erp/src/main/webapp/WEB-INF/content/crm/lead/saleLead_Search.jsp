<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var subject = "";
var title = "";
var customerName = "";
function loadConditions(){
	subject = $('#subject').val();
	subject=Url.encode(subject);
	subject=Url.encode(subject);
	title = $('#title').val();
	title=Url.encode(title);
	title=Url.encode(title);
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
				<td>线索主题 : <input type="text" name="subject" id="subject" class="int" />
				</td>
				<td>头衔 : <input type="title" name="title" id="title" class="int" />
				</td>
				<td>客户名称 : <input type="text" name="customerName" id="customerName" class="int" />
				</td>
			</tr>
		</table>
	</div>
</div>