<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="uploaderForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixFileAction!updateUploader.action">
	<input type="hidden" id="uploaderId" name="uploader.id" value="${uploader.id}" /> 
	<input type="hidden" id="employeeIds" name="employeeIds" value="${employeeIds }" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>附件分类:</label>
			<div class="col-md-7">
				<select id="uploaderTypeId" name="uploader.uploaderType.id" data-prompt-position="topLeft" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${uploaderTypeList}" var="entity">
						<option value="${entity.id}" <c:if test="${uploader.uploaderType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>密级:</label>
			<div class="col-md-6">
				<select id="declassified" name="uploader.declassified" class="form-control validate[required]">
					<option value="1" <c:if test='${uploader.declassified eq 1}'>selected="selected"</c:if>>1级</option>
					<option value="2" <c:if test='${uploader.declassified eq 2}'>selected="selected"</c:if>>2级</option>
					<option value="3" <c:if test='${uploader.declassified eq 3}'>selected="selected"</c:if>>3级</option>
					<option value="4" <c:if test='${uploader.declassified eq 4}'>selected="selected"</c:if>>4级</option>
					<option value="5" <c:if test='${uploader.declassified eq 5}'>selected="selected"</c:if>>5级</option>
					<option value="6" <c:if test='${uploader.declassified eq 6}'>selected="selected"</c:if>>6级</option>
					<option value="7" <c:if test='${uploader.declassified eq 7}'>selected="selected"</c:if>>7级</option>
					<option value="8" <c:if test='${uploader.declassified eq 8}'>selected="selected"</c:if>>8级</option>
					<option value="9" <c:if test='${uploader.declassified eq 9}'>selected="selected"</c:if>>9级</option>
				</select>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span class="text-danger">*</span>可查阅人:
			</label>
			<div class="col-md-7">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="employeeNames" class="form-control validate[required]" value="${employeeNames }" data-role="tagsinput">
							<div class="input-group-btn">
								<button onclick="chooseEmployee();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-plus"></i>
								</button>
								<button onclick="$('#employeeIds').val('');$('#employeeNames').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span class="text-danger">*</span>上传文档:
			</label>
			<div class="col-md-7">
				<input type="file" id="docToUpload" name="docToUpload" class="validate[required]">
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goEmployeeChoose.action', 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#employeeIds').val(emp[0]);
				$('#employeeNames').val(emp[1]);
			} else {
				layer.alert("请选择人员!");
			}
		});
	};
</script>