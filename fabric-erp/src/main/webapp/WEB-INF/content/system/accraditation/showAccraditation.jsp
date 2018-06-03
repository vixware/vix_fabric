<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	$ ("#billsTypeForm").validationEngine ();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="billsTypeForm">
		<s:hidden id="billsTypeId" name="billsTypeId" value="%{billsType.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr>
					<td colspan="3"><textarea id="mainContent" name="mainContent">${contract.mainContent}</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
							var contents = KindEditor.create ('textarea[name="mainContent"]' , {
							basePath : '${vix}/plugin/KindEditor/' ,
							width : 950 ,
							height : 200 ,
							cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css' ,
							uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp' ,
							fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp' ,
							allowFileManager : true
							});
						</script></td>
				</tr>
			</table>
		</div>
	</form>
</div>