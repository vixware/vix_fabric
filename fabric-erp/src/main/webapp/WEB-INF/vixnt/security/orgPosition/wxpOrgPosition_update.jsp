<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="organizationUnitForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixntOrgPositionAction!saveOrUpdate.action">
	<input type="hidden" id="entityId" name="entityForm.id" value="${entity.id}" /> <input type="hidden" id="orgUnitId" name="entityForm.organizationUnit.id" value="${entity.organizationUnit.id}" /> <input type="hidden" id="orgId" name="entityForm.organization.id" value="${entity.organization.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 岗位编码:</label>
			<div class="col-md-4">
				<input id="code" name="entityForm.code" value="${entity.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 岗位名称:</label>
			<div class="col-md-4">
				<input id="posName" name="entityForm.posName" value="${entity.posName}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 岗位序号:</label>
			<div class="col-md-4">
				<input id="postNumber" name="entityForm.postNumber" value="${entity.postNumber}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label"> 岗位性质:</label>
			<div class="col-md-4">
				<input id="jobNature" name="entityForm.jobNature" value="${entity.jobNature}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 所属部门:</label>
			<div class="col-md-10">
				<input id="parentUnitName" name="" value="${entity.organizationUnit.fs}" class="form-control" type="text" readonly="readonly" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 上级岗位:</label>
			<div class="col-md-7">
				<input type="hidden" id="parentPosId" name="entityForm.parentOrgPosition.id" value="${entity.parentOrgPosition.id}" /> <input type="text" id="parentPosName" name="entityForm.parentOrgPosition.posName" class="form-control" readonly="readonly" value="${entity.parentOrgPosition.posName}" />
			</div>
			<div class="col-md-3">
				<button onclick="chooseParentOrgPosition('${orgId}');" type="button" class="btn btn-info">
					<i class="glyphicon glyphicon-plus"></i> 选择
				</button>
				<button onclick="$('#parentPosId').val('');$('#parentPosName').val('');" type="button" class="btn btn-default">
					<i class="glyphicon glyphicon-repeat"></i> 清空
				</button>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 编制人数:</label>
			<div class="col-md-4">
				<input id="personAmount" name="entityForm.personAmount" value="${entity.personAmount}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label"> 职务:</label>
			<div class="col-md-4">
				<select id="orgJobId" name="entityForm.orgJob.id" data-prompt-position="topLeft" class="form-control">
					<c:forEach items="${orgJobList}" var="entity">
						<option value="${entity.id}" <c:if test="${entity.orgJob.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">岗位薪级标准:</label>
			<div class="col-md-4">
				<input id="postSalaryScale" name="entityForm.postSalaryScale" value="${entity.postSalaryScale}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">标准薪点:</label>
			<div class="col-md-4">
				<input id="standardPayPoint" name="entityForm.standardPayPoint" value="${entity.standardPayPoint}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	/**
	 * 选择公司的岗位
	 */
	function chooseParentOrgPosition(orgId) {
		var mycars = new Array("确定", "取消")
		$.ajax({
		url : '${nvix}/nvixnt/nvixntOrgPositionAction!goChoosePosition.action',
		data : "orgId=" + orgId,
		cache : false,
		success : function(html) {
			layer.open({
			type : 1,
			title : "选择岗位",
			area : [ 450 + 'px', 250 + 'px' ],
			closeBtn : 1,
			content : html,
			btn : mycars,
			yes : function(index, layero) {
				layer.close(index);
			},
			btn2 : function(index, layero) {
				layer.close(index);
			}
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("系统错误，请联系管理员");
		}
		});
	};
</script>