<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="homeColumnDetailForm" class="form-horizontal" style="padding: 40px;" action="${nvix}/nvixnt/system/nvixntWebColumnAction!saveOrUpdateHomeColumnDetail.action">
	<input type="hidden" id="homeColumnDetailId" name="homeColumnDetail.id" value="${homeColumnDetail.id}" /> 
	<input type="hidden" id="imageFilePath" name="homeColumnDetail.imageFilePath" value="${homeColumnDetail.imageFilePath}" /> 
	<input type="hidden" id="imageFileName" name="homeColumnDetail.imageFileName" value="${homeColumnDetail.imageFileName}" /> 
	<input type="hidden" id="homeColumnId" name="homeColumnDetail.homeColumn.id" value="${homeColumnDetail.homeColumn.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 图片:</label>
			<div class="col-md-8">
				<div style="float: left; display: inline;">
					<img id="mainPic" onerror="$('#mainPic').attr('src','${nvix}/vixntcommon/base/img/demo/64x64.png')" src="${homeColumnDetail.imageFilePath}" width="30px" height="30px" onmouseover="$('#mainPicBig').attr('style','position: absolute;padding:-50px;z-index:9999;');" onmouseout="$('#mainPicBig').attr('style','display:none;');" /> <img
						id="mainPicBig" onerror="$('#mainPicBig').attr('src','${nvix}/vixntcommon/base/img/demo/64x64.png')" src="${homeColumnDetail.imageFilePath}" width="100" height="100" style="display: none; position: absolute;" /> &nbsp;&nbsp;
				</div>
				<input type="file" id="fileToUpload" name="fileToUpload" onchange="imageChange();" style="width: 80%;">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 顺序:</label>
			<div class="col-md-3">
				<input id="orderBy" name="homeColumnDetail.orderBy" value="${homeColumnDetail.orderBy}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 状态:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="homeColumnDetail.status == 0">active</s:if>"> <input type="radio" value="0" id="status" name="homeColumnDetail.status" <s:if test="homeColumnDetail.status == 0">checked="checked"</s:if>>不展示
					</label> <label class="btn btn-default <s:if test="homeColumnDetail.status == 1">active</s:if>"> <input type="radio" value="1" id="status" name="homeColumnDetail.status" <s:if test="homeColumnDetail.status == 1">checked="checked"</s:if>>展示
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 链接:</label>
			<div class="col-md-8">
				<input id="linkAddress" name="homeColumnDetail.linkAddress" value="${homeColumnDetail.linkAddress}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#homeColumnDetailForm").validationEngine();
	function imageChange() {
		var homeColumnDetailId = $("#homeColumnDetailId").val();
		uploadFileOrImage("homeColumnDetailImageForm", "${nvix}/nvixnt/system/nvixntWebColumnAction!saveOrUpdatePicture.action?id=" + homeColumnDetailId, "fileToUpload", "image", function(data) {
			var d = data.split(",");
			if (d[0] == '0') {
				showMessage(d[1]);
			} else {
				$('#homeColumnDetailId').val(d[0]);
				$('#imageFilePath').val(d[1]);
				$("#mainPic").attr("src", d[1]);
				$("#mainPicBig").attr("src", d[1]);
				showMessage("图片上传成功!");
			}
		});
	};
</script>