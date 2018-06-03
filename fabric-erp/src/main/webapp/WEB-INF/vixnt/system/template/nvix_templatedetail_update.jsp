<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="homeTemplateDetailForm" class="form-horizontal" style="padding: 40px;" action="${nvix}/nvixnt/system/nvixntTemplateAction!saveOrUpdateTemplateDetail.action">
	<input type="hidden" id="homeTemplateDetailId" name="homeTemplateDetail.id" value="${homeTemplateDetail.id}" /> 
	<input type="hidden" id="homeTemplateId" name="homeTemplateDetail.homeTemplate.id" value="${homeTemplateDetail.homeTemplate.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 类型:</label>
			<div class="col-md-3">
				<select id="code" name="homeTemplateDetail.code" class="form-control validate[required]">
					<s:if test="homeTemplateDetailMap.size > 0">
						<s:iterator value="homeTemplateDetailMap">
							<option value="${key}" <c:if test='${homeTemplateDetail.code == key}'>selected="selected"</c:if>>${value}</option>
						</s:iterator>
					</s:if>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
			<div class="col-md-3">
				<input id="name" name="homeTemplateDetail.name" value="${homeTemplateDetail.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 顺序:</label>
			<div class="col-md-3">
				<input id="orderBy" name="homeTemplateDetail.orderBy" value="${homeTemplateDetail.orderBy}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 状态:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="homeTemplateDetail.status == 0">active</s:if>"> <input type="radio" value="0" id="status" name="homeTemplateDetail.status" <s:if test="homeTemplateDetail.status == 0">checked="checked"</s:if>>不展示
					</label> <label class="btn btn-default <s:if test="homeTemplateDetail.status == 1">active</s:if>"> <input type="radio" value="1" id="status" name="homeTemplateDetail.status" <s:if test="homeTemplateDetail.status == 1">checked="checked"</s:if>>展示
					</label>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#homeTemplateDetailForm").validationEngine();
</script>