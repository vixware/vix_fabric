<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 生产管理 <span>> 工作中心 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/produce/nvixWorkCenterAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>工作中心</h2>
		</header>
		<div class="widget-body">
			<form id="workCenterForm" class="form-horizontal">
				<input type="hidden" id="id" name="workCenter.id" value="${workCenter.id}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>工作中心编码:</label>
						<div class="col-md-3">
							<input id="org" name="workCenter.org" value="${workCenter.org}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>工作中心名称:</label>
						<div class="col-md-3">
							<input id="orgName" name="workCenter.orgName" value="${workCenter.orgName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>每日人工产能:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="humanCapacity" name="workCenter.humanCapacity" value="${workCenter.humanCapacity}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
								<span class="input-group-addon">人时</span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>每日机器产能:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="machineCapacity" name="workCenter.machineCapacity" value="${workCenter.machineCapacity}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
								<span class="input-group-addon">机时</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>标准人工效率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="orgEfficiency" name="workCenter.orgEfficiency" value="${workCenter.orgEfficiency}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
								<span class="input-group-addon">%</span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>标准机器负荷:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="machineLoad" name="workCenter.machineLoad" value="${workCenter.machineLoad}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
								<span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>制费分摊依据:</label>
						<div class="col-md-3">
							<select id="types" name="workCenter.types" class="form-control validate[required]">
								<option value="0" <c:if test='${workCenter.types eq 0}'>selected="selected"</c:if>>人时</option>
								<option value="1" <c:if test='${workCenter.types eq 1}'>selected="selected"</c:if>>机时</option>
								<option value="2" <c:if test='${workCenter.types eq 2}'>selected="selected"</c:if>>人工</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>标准制费分摊率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="systemRate" name="workCenter.systemRate" value="${workCenter.systemRate}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
								<span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>标准工资率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="wageRate" name="workCenter.wageRate" value="${workCenter.wageRate}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
								<span class="input-group-addon">%</span>
							</div>
						</div>
						<label class="col-md-2 control-label">每小时人工工资:</label>
						<div class="col-md-3">
							<input id="hourlyWages" name="workCenter.hourlyWages" value="${workCenter.hourlyWages}" data-prompt-position="topLeft" class="form-control validate[custom[integer],min[1]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">需要装配和操作人数:</label>
						<div class="col-md-3">
							<input id="assemblyOperationNumber" name="workCenter.assemblyOperationNumber" value="${workCenter.assemblyOperationNumber}" data-prompt-position="topLeft" class="form-control validate[custom[integer],min[1]]" type="text" />
						</div>
						<label class="col-md-2 control-label">每小时制造费用:</label>
						<div class="col-md-3">
							<input id="manufacturingCostPerHour" name="workCenter.manufacturingCostPerHour" value="${workCenter.manufacturingCostPerHour}" data-prompt-position="topLeft" class="form-control validate[custom[number],min[1]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">机器类型:</label>
						<div class="col-md-3">
							<input id="machineType" name="workCenter.machineType" value="${workCenter.machineType}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">机台类型说明:</label>
						<div class="col-md-3">
							<input id="machineTypeDescription" name="workCenter.machineTypeDescription" value="${workCenter.machineTypeDescription}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">部门:</label>
						<div class="col-md-3">
							<input id="departments" name="workCenter.departments" value="${workCenter.departments}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">WIP厂库:</label>
						<div class="col-md-3">
							<input id="wipWarehouse" name="workCenter.wipWarehouse" value="${workCenter.wipWarehouse}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">成本中心:</label>
						<div class="col-md-3">
							<input id="costCenter" name="workCenter.costCenter" value="${workCenter.costCenter}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">WIP储位:</label>
						<div class="col-md-3">
							<input id="wipStorage" name="workCenter.wipStorage" value="${workCenter.wipStorage}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">位置:</label>
						<div class="col-md-3">
							<input id="position" name="workCenter.position" value="${workCenter.position}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">预设排队时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="defaultQueueTime" name="workCenter.defaultQueueTime" value="${workCenter.defaultQueueTime}" data-prompt-position="topLeft" class="form-control validate[custom[number],min[1]]" type="text" />
								<span class="input-group-addon">h</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">每天班次数量:</label>
						<div class="col-md-3">
							<input id="numberDailyFlights" name="workCenter.numberDailyFlights" value="${workCenter.numberDailyFlights}" data-prompt-position="topLeft" class="form-control validate[custom[integer],min[1]]" type="text" />
						</div>
						<label class="col-md-2 control-label">预设搬运时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="handlingTime" name="workCenter.handlingTime" value="${workCenter.handlingTime}" data-prompt-position="topLeft" class="form-control validate[custom[number],min[1]]" type="text" />
								<span class="input-group-addon">h</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">是否生产线:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <c:if test="${workCenter.whetherProductionLine == 1}">active</c:if>"> 
									<input type="radio" value="1" id="whetherProductionLine" name="workCenter.whetherProductionLine" <c:if test="${workCenter.whetherProductionLine == 1}">checked="checked"</c:if>>是
								</label> 
								<label class="btn btn-default <c:if test="${workCenter.whetherProductionLine == 0}">active</c:if>"> 
									<input type="radio" value="0" id="whetherProductionLine" name="workCenter.whetherProductionLine" <c:if test="${workCenter.whetherProductionLine == 0}">checked="checked"</c:if>>否
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label">预设上班班次:</label>
						<div class="col-md-3">
							<input id="presetWorkShifts" name="workCenter.presetWorkShifts" value="${workCenter.presetWorkShifts}" data-prompt-position="topLeft" class="form-control validate[custom[integer],min[1]]" type="text" />
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-md-2 control-label">预设上班班次:</label>
						<div class="col-md-3">
							<input id="presetWorkShifts" name="workCenter.presetWorkShifts" value="${workCenter.presetWorkShifts}" data-prompt-position="topLeft" class="form-control validate[custom[integer],min[1]]" type="text" />
						</div>
						<label class="col-md-2 control-label">工作日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="workDate" name="workCenter.workDate" value="${workCenter.workDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'workDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div> --%>
					<div class="form-group">
						<label class="col-md-2 control-label">创建者:</label>
						<div class="col-md-3">
							<input id="establishmentUser" name="workCenter.establishmentUser" value="${workCenter.establishmentUser}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">创建日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="creDate" name="workCenter.creDate" value="${workCenter.creDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'creDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="workCenter.memo" class="form-control" rows="4">${workCenter.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-primary" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/produce/nvixWorkCenterAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#workCenterForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#workCenterForm").validationEngine('validate')) {
			$("#workCenterForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/produce/nvixWorkCenterAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				showMessage(result);
				loadContent('', '${nvix}/nvixnt/produce/nvixWorkCenterAction!goList.action');
			}
			});
		}
	}
</script>