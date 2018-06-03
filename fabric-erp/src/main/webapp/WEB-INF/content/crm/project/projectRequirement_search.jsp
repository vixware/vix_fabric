<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var subject = "";
var crmProject = "";
var customerAccount = "";
function loadConditions(){
	subject = $('#subject').val();
	subject=Url.encode(subject);
	subject=Url.encode(subject);
	crmProject = $('#crmProject').val();
	crmProject=Url.encode(crmProject);
	crmProject=Url.encode(crmProject);
	customerAccount = $('#customerAccount').val();
	customerAccount=Url.encode(customerAccount);
	customerAccount=Url.encode(customerAccount);
}
loadConditions();
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>主题 : <input type="text" name="subject" id="subject" class="int" />
				</td>
				<td>项目 : <input type="text" name="crmProject" id="crmProject" class="int" />
				</td>
				<td>客户 : <input type="text" name="customerAccount" id="customerAccount" class="int" />
				</td>
			</tr>
		</table>
	</div>
</div>