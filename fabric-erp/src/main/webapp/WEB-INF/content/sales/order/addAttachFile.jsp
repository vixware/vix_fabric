<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="background: #DCE7F1">
	<form method="post" enctype="multipart/form-data">
		<div style="margin: 15px;">
			<input type="file" id="fileToUpload" name="fileToUpload" maxlength="30" style="text-align: right;" />
		</div>
		<table>
			<tr>
				<th>名称：</th>
				<td><s:textfield id="title" name="document.title" type="text"></s:textfield><font color="red">*</font></td>
				<th>类型：</th>
				<td><s:textfield id="fileType" name="document.fileType" type="text"></s:textfield></td>
			</tr>
			<tr>
				<th>上传文件：</th>
				<td colspan="3"><input type="file" id="fileToUpload" name="fileToUpload" style="width: 90%;" /></td>
			</tr>
			<tr>
				<th>文件备注：</th>
				<td colspan="3" style="padding-top: 5px;"><textarea id="note" name="document.note" rows="1" cols="1" style="width: 90%; height: 50px;"><s:property value="document.note" escape="false" /></textarea></td>
			</tr>
		</table>
	</form>
</div>