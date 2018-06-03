<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<form id="itemCatalogFileForm" method="post" enctype="multipart/form-data">
			<input type="hidden" id="id" name="itemCatalogFile.id" value="${id}" />
			<table class="table-padding020">
				<tr>
					<td>${vv:varView('vix_mdm_item')}文件：</td>
					<td><input type="file" id="fileToUpload" name="fileToUpload" style="width: 90%;" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>