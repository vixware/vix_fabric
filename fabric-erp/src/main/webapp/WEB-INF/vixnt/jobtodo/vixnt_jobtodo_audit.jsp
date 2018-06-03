<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script type="text/javascript">
	function loadBillPage() {
		var url = "";
		url = "${nvix}/nvixnt" + $("#jobTodoUrl").val() + '?id=' + $("#billId").val() + "&taskId=" + $("#taskId").val();
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			$("#billId").after(html);
		}
		});
	}
	loadBillPage();
</script>
<input type="hidden" id="taskId" value="${jobTodo.id }" />
<input type="hidden" id="jobTodoUrl" value="${jobTodo.url }" />
<input type="hidden" id="billType" value="${jobTodo.sourceClass}" />
<input type="hidden" id="billId" value="${jobTodo.sourceClassPk}" />