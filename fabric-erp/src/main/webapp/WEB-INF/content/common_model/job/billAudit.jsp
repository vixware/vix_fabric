<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#jobTodoForm").validationEngine();
	function goAudit(taskId) {
		asyncbox.confirm('确定要提交审批吗?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/common/model/jobTodoAction!goAudit.action?id=' + taskId,
				cache : false,
				success : function(html) {
					asyncbox.open({
					modal : true,
					width : 660,
					height : 380,
					title : "流程审批",
					html : html,
					callback : function(action) {
						if (action == 'ok' && $('#jobTodoForm').validationEngine('validate')) {
							$.post('${vix}/common/model/jobTodoAction!audit.action', {
							'id' : $("#taskId").val(),
							'opinion' : $("#opinion").val(),
							'conditionRule' : $(":radio[name=conditionRule][checked]").val()
							}, function(result) {
								asyncbox.success(result, "<s:text name='cmn_message'/>", function(action) {
									loadContent('${vix}/common/model/jobTodoAction!goList.action');
								});
							});
						}
					},
					btnsbar : $.btn.OKCANCEL
					});
				}
				});
			}
		});
	}
	function loadBillPage() {
		var url = "";
		url = "${vix}" + $("#jobTodoUrl").val() + '?id=' + $("#billId").val() + "&taskId=" + $("#taskId").val();
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