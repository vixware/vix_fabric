<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="specificationDetailForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/system/nvixSpecificationAction!saveOrUpdateDetail.action">
	<input type="hidden" id="id" name="specificationDetail.id" value="${specificationDetail.id}" /> 
	<input type="hidden" id="specificationId" name = "specificationDetail.specification.id" value="${specificationDetail.specification.id}"/>
	<input type="hidden" id="imageFileName" name="specificationDetail.imageFileName" value="${specificationDetail.imageFileName}"/>
	<input type="hidden" id="imageFilePath" name="specificationDetail.imageFilePath" value="${specificationDetail.imageFilePath}">
	<input type="hidden" id="imageFileRealName" name="specificationDetail.imageFileRealName" value="${specificationDetail.imageFileRealName}">
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-8">
				<input id="code" name="specificationDetail.code" value="${specificationDetail.code}" data-prompt-position="topLeft" class="form-control required" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-8">
				<input id="name" name="specificationDetail.name" value="${specificationDetail.name}" data-prompt-position="topLeft" class="form-control required" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">规格图片:</label>
			<div class="col-md-8">
				<div style="float:left; display:inline;">
					<img id="mainPic" onerror="$('#mainPic').attr('src','${nvix}/common/img/default.png')" src="${specificationDetail.specDetailImagePath}" width="30px" height="30px" onmouseover="$('#mainPicBig').attr('style','position: absolute;padding:-50px;z-index:9999;');" onmouseout="$('#mainPicBig').attr('style','display:none;');"/>
					<img id="mainPicBig" onerror="$('#mainPicBig').attr('src','${nvix}/common/img/default.png')" src="${specificationDetail.specDetailImagePath}" width="100" height="100" style="display:none;position: absolute;"/>
					&nbsp;&nbsp;
				</div>
				<a href="javascript:void(0);" class="btn btn-default btn-sm" onclick="$('#fileToUpload').click();">上传图片</a>
				<input type="file" id="fileToUpload" name="fileToUpload" onchange="$('#imageFileName').val(this.value);fileChange();" style="display:none;">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">备注:</label>
			<div class="col-md-8">
				<input id="memo" name="specificationDetail.memo" value="${specificationDetail.memo}" data-prompt-position="topLeft" class="form-control" type="text"/>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
$("#specificationDetailForm").validationEngine();
function fileChange() {
	uploadFileOrImage("specDetailImageForm","${nvix}/nvixnt/system/nvixSpecificationAction!uploadSpecificationDetailImage.action","fileToUpload","image",function(data){
		var d = data.split(":");
		if(d[0] == '0'){
			showMessage(d[1]);
		}else{
			$("#imageFilePath").val(d[1]);
			$("#imageFileName").val(d[2]);
			$("#imageFileRealName").val(d[2]);
			$("#mainPic").attr("src",d[1]);
			$("#mainPicBig").attr("src",d[1]);
			showMessage("文件上传成功!");
		}
	});
}
</script>