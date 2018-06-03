<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#policyInformationForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="policyInformationForm">
		<s:hidden id="policyInformationId" name="policyInformationId" value="%{policyInformation.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr>
					<td colspan="3"><textarea id="policyFeedbackContent" name="policyFeedbackContent">${policyInformation.policyFeedbackContent }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
							var policyFeedbackContent = KindEditor.create('textarea[name="policyFeedbackContent"]', {
                            basePath : '${vix}/plugin/KindEditor/',
                            width : 800,
                            height : 200,
                            cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
                            uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
                            fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
                            allowFileManager : true
                            });
						</script></td>
				</tr>
			</table>
		</div>
	</form>
</div>