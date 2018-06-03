<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<form id="salesAttachFileForm" method="post" enctype="multipart/form-data">
			<input type="hidden" id="id" name="salesAttachFile.id" value="${id}" />
			<table class="table-padding020">
				<tr>
					<td>名称：</td>
					<td><input id="name" name="salesAttachFile.name" type="text" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td>上传文件：</td>
					<td><input type="file" id="fileToUpload" name="fileToUpload" style="width: 90%;" /></td>
				</tr>
				<tr>
					<td>文件备注：</td>
					<td style="padding-top: 5px;"><textarea id="memo" name="salesAttachFile.memo" rows="1" cols="1" style="width: 90%; height: 50px;"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>