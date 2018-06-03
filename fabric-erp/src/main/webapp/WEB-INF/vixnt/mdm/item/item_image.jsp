<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="itemImageForm" class="form-horizontal" style="padding:40px;" action="${nvix}/nvixnt/mdm/nvixntItemAction!saveOrUpdateImage.action">
	<fieldset>
		<input type="hidden" id="itemImageId" name="itemImage.id" value="${itemImage.id}"/>
		<input type="hidden" id="itemId" name="itemImage.item.id" value="${itemImage.item.id}"/>
		<div class="form-group">
			<label class="col-md-3 control-label">图片:</label>
			<div class="col-md-8">
				<input type="hidden" id="itemImageImgPath" name="itemImage.imgPath" value="${itemImage.imgPath}"/>
				<input type="hidden" id="itemImagePersisName" name="itemImage.persisName" value="${itemImage.persisName}"/>
				<div style="float:left; display:inline;">
					<img id="itemImageBigImage" onerror="$('#itemImageBigImage').attr('src','${nvix}/common/img/default.png')" src="${itemImage.imgPath}" width="30px" height="30px" onmouseover="$('#itemImageBigImageShow').attr('style','position: absolute;padding:-50px;z-index:9999;');" onmouseout="$('#itemImageBigImageShow').attr('style','display:none;');"/>
					<img id="itemImageBigImageShow" onerror="$('#itemImageBigImageShow').attr('src','${nvix}/common/img/default.png')" src="${itemImage.imgPath}" width="100" height="100" style="display:none;position: absolute;"/>
					&nbsp;
				</div>
				<input type="file" id="itemImageFileToUpload" name="fileToUpload" onchange="uploaditemImage();" style="width:65%;">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>顺序:</label>
			<div class="col-md-8">
				<input id="orderBy" name="itemImage.orderBy" value="${itemImage.orderBy}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">备注:</label>
			<div class="col-md-8">
				<input id="piMemo" name="itemImage.memo" value="${itemImage.memo}" data-prompt-position="topLeft" class="form-control" type="text"/>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#itemImageForm").validationEngine();
	
	/** 上传商品主图 */
	function uploaditemImage() {
		uploadFileOrImage("itemImageFileUploadForm","${nvix}/nvixnt/mdm/nvixntItemAction!uploadEcPrudoctImage.action","itemImageFileToUpload","image",function(data){
			var d = data.split(":");
			if($.trim(d[0]) == '0'){
				layer.alert(d[1]);
			}else{
				$("#itemImageId").val(d[0]);
				$("#itemImageImgPath").val(d[1]);
				$("#itemImagePersisName").val(d[2]);
	       		$("#itemImageBigImage").attr("src",d[1]);
	       		$("#itemImageBigImageShow").attr("src",d[1]);
	       		showMessage("图片上传成功!");
			}
		});
	}
</script>