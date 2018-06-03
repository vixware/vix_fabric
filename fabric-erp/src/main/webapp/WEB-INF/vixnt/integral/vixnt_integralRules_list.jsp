<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-user"></i> 会员管理 <span>> 积分规则管理 </span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
		<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>${integralRules.channelDistributor.name }积分规则</h2>
		</header>
		<div>
			<div class="widget-body">
				<form id="integralRulesForm" class="form-horizontal">
					<input type="hidden" id="id" name="integralRules.id" value="${integralRules.id}" /> <input type="hidden" id="channelDistributorId" name="integralRules.channelDistributor.id" value="${integralRules.channelDistributor.id}" />
					<fieldset>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
							<div class="col-md-3">
								<input id="name" name="integralRules.name" value="${integralRules.name}" class="form-control validate[required]" type="text" />
							</div>
							<label class="col-md-2 control-label"> <span class="text-danger">*</span>是否启用:
							</label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="integralRules.status == 0">active</s:if>"> <input type="radio" value="0" id="status" name="integralRules.status" <s:if test="integralRules.status == 0">checked="checked"</s:if> class="validate[required]">是
									</label> <label class="btn btn-default <s:if test="integralRules.status == 1">active</s:if>"> <input type="radio" value="1" id="status" name="integralRules.status" <s:if test="integralRules.status == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 注册是否赠送积分:</label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="integralRules.isZc == 1">active</s:if>"> <input type="radio" value="0" id="isZc" name="integralRules.isZc" <s:if test="integralRules.isZc == 0">checked="checked"</s:if> class="validate[required]">是
									</label> <label class="btn btn-default <s:if test="integralRules.isZc == 2">active</s:if>"> <input type="radio" value="1" id="isZc" name="integralRules.isZc" <s:if test="integralRules.isZc == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 赠送分值:</label>
							<div class="col-md-3">
								<input id="presentZc" name="integralRules.presentZc" value="${integralRules.presentZc}" class="form-control validate[required]" type="text" />
							</div>
						</div>
						<%-- <div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否限时赠送积分:</label>
							<div class="col-md-3">
								<select id="isXszc" name="integralRules.isXszc" class="form-control">
									<option value="1" <c:if test='${integralRules.isXszc eq "1"}'>selected="selected"</c:if>>是</option>
									<option value="2" <c:if test='${integralRules.isXszc eq "2"}'>selected="selected"</c:if>>否</option>
								</select>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 限时赠送积分比率:</label>
							<div class="col-md-3">
								<input id="xsConversiorate" name="integralRules.xsConversiorate" value="${integralRules.xsConversiorate}" class="form-control validate[required]" type="text" />
							</div>
						</div> --%>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 积分是否设置清零:</label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="integralRules.isJfyxq == 1">active</s:if>"> <input type="radio" value="1" id="isJfyxq" name="integralRules.isJfyxq" <s:if test="integralRules.isJfyxq == 1">checked="checked"</s:if> class="validate[required]">是
									</label> <label class="btn btn-default <s:if test="integralRules.isJfyxq == 2">active</s:if>"> <input type="radio" value="2" id="isJfyxq" name="integralRules.isJfyxq" <s:if test="integralRules.isJfyxq == 2">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 积分清零类型:</label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="integralRules.yxType == 1">active</s:if>"> <input type="radio" value="1" id="yxType" name="integralRules.yxType" <s:if test="integralRules.yxType == 1">checked="checked"</s:if> class="validate[required]">月
									</label> <label class="btn btn-default <s:if test="integralRules.yxType == 2">active</s:if>"> <input type="radio" value="2" id="yxType" name="integralRules.yxType" <s:if test="integralRules.yxType == 2">checked="checked"</s:if>>年
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
							<div class="col-md-3">
								<div class="input-group">
									<input id="startTime" name="integralRules.startTime" value="${integralRules.startTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'startTime'});"><i
										class="fa fa-calendar"></i></span>
								</div>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
							<div class="col-md-3">
								<div class="input-group">
									<input id="endTime" name="integralRules.endTime" value="${integralRules.endTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'endTime'});"><i
										class="fa fa-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 积分赠送比率:</label>
							<div class="col-md-3">
								<div class="input-group">
									<input id="conversiorate" name="integralRules.conversiorate" value="${integralRules.conversiorate}" class="form-control validate[required,custom[number]]" type="text" /> <span class="input-group-addon">1~N</span>
								</div>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 积分兑换比率:</label>
							<div class="col-md-3">
								<div class="input-group">
									<input id="integralConsumptionRatio" name="integralRules.integralConsumptionRatio" value="${integralRules.integralConsumptionRatio}" class="form-control validate[required,custom[number]]" type="text" /> <span class="input-group-addon">0~1</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 会员生日积分赠送比率:</label>
							<div class="col-md-3">
								<div class="input-group">
									<input id="memberBirthdayRatio" name="integralRules.memberBirthdayRatio" value="${integralRules.memberBirthdayRatio}" class="form-control validate[required,custom[integer]]" type="text" /> <span class="input-group-addon">1~N</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">备注:</label>
							<div class="col-md-8">
								<textarea name="integralRules.memo" class="form-control">${integralRules.memo } </textarea>
							</div>
						</div>
					</fieldset>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-12">
								<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
									<i class="fa fa-save"></i> 保存
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#integralRulesForm").validationEngine();
	function saveOrUpdate() {
		if ($("#integralRulesForm").validationEngine('validate')) {
			$("#integralRulesForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntIntegralRulesSetAction!saveOrUpdate.action",
			dataType : "text",
			success : function(data) {
				showMessage(data, 'success');
				loadContent('', '${nvix}/nvixnt/vixntIntegralRulesSetAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>