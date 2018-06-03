<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="smart-form" style="margin:5px;padding:10px 0;">
	<form id="addressInfoFileForm" action="${nvix}/nvixnt/system/nvixntAddressInfoAction!importAddressInfo.action" method="post" enctype="multipart/form-data">
		<section style="padding: 5px 20px;">
			<label class="label">地址信息文件:</label>
			<div class="input input-file">
				<span class="button"><input type="file" id="fileToUpload" name="fileToUpload" onchange="$('#addressInfoFile').val(this.value);">选择</span>
				<input id="addressInfoFile" type="text" readonly="readonly">
			</div>
			<div class="note">
				注：导入文件格式为Excel.
				<%-- <button type="button" class="btn btn-primary" style="padding:4px;" onclick="window.open('${snow}/common/snowAction!downloadTemplate.action?tag=system_addressInfo');">下载模板</button> --%>
			</div>
		</section>
	</form>
</div>