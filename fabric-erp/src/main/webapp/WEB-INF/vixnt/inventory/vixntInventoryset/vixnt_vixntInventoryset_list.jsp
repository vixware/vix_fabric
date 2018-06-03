<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-cubes"></i> 库存管理 <span>> 库存设置 </span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>库存设置</h2>
		</header>
		<div>
			<div class="widget-body">
				<form id="inventoryParametersForm" class="form-horizontal">
					<input type="hidden" id="id" name="inventoryParameters.id" value="${inventoryParameters.id}" />
					<fieldset>
						<div class="form-group">
							<label class="col-md-2 control-label"> 是否启用电商: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.enableEc == 0">active</s:if>"> <input type="radio" value="0" id="enableEc" name="inventoryParameters.enableEc" <s:if test="inventoryParameters.enableEc == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.enableEc == 1">active</s:if>"> <input type="radio" value="1" id="enableEc" name="inventoryParameters.enableEc" <s:if test="inventoryParameters.enableEc == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"> 是否服务: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.isServiceItem == 0">active</s:if>"> <input type="radio" value="0" id="isServiceItem" name="inventoryParameters.isServiceItem" <s:if test="inventoryParameters.isServiceItem == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.enableEc == 1">active</s:if>"> <input type="radio" value="1" id="isServiceItem" name="inventoryParameters.isServiceItem" <s:if test="inventoryParameters.isServiceItem == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 有无保质期管理: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.isShelfLife == 1">active</s:if>"> <input type="radio" value="1" id="isShelfLife" name="inventoryParameters.isShelfLife" <s:if test="inventoryParameters.isShelfLife == 1">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.isShelfLife == 0">active</s:if>"> <input type="radio" value="0" id="isShelfLife" name="inventoryParameters.isShelfLife" <s:if test="inventoryParameters.isShelfLife == 0">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"> 是否启用货位: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.enableShelf == 1">active</s:if>"> <input type="radio" value="1" id="enableShelf" name="inventoryParameters.enableShelf" <s:if test="inventoryParameters.enableShelf == 1">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.enableShelf == 0">active</s:if>"> <input type="radio" value="0" id="enableShelf" name="inventoryParameters.enableShelf" <s:if test="inventoryParameters.enableShelf == 0">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 是否启用规格: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.enableSpecification == 1">active</s:if>"> <input type="radio" value="1" id="enableSpecification" name="inventoryParameters.enableSpecification"
										<s:if test="inventoryParameters.enableSpecification == 1">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.enableSpecification == 0">active</s:if>"> <input type="radio" value="0" id="enableSpecification" name="inventoryParameters.enableSpecification"
										<s:if test="inventoryParameters.enableSpecification == 0">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"> 是否启用POS收银: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.enablePos == 1">active</s:if>"> <input type="radio" value="1" id="enablePos" name="inventoryParameters.enablePos" <s:if test="inventoryParameters.enablePos == 1">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.enablePos == 0">active</s:if>"> <input type="radio" value="0" id="enablePos" name="inventoryParameters.enablePos" <s:if test="inventoryParameters.enablePos == 0">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 有无组装拆卸业务: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.isAssemblyAndDisassembly == 0">active</s:if>"> <input type="radio" value="0" id="isAssemblyAndDisassembly" name="inventoryParameters.isAssemblyAndDisassembly"
										<s:if test="inventoryParameters.isAssemblyAndDisassembly == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.isAssemblyAndDisassembly == 1">active</s:if>"> <input type="radio" value="1" id="isAssemblyAndDisassembly" name="inventoryParameters.isAssemblyAndDisassembly"
										<s:if test="inventoryParameters.isAssemblyAndDisassembly == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"> 有无批次管理: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.isBatch == 0">active</s:if>"> <input type="radio" value="0" id="isBatch" name="inventoryParameters.isBatch" <s:if test="inventoryParameters.isBatch == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.isBatch == 1">active</s:if>"> <input type="radio" value="1" id="isBatch" name="inventoryParameters.isBatch" <s:if test="inventoryParameters.isBatch == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 有无形态转换业务: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.isPatternsTransition == 0">active</s:if>"> <input type="radio" value="0" id="isPatternsTransition" name="inventoryParameters.isPatternsTransition"
										<s:if test="inventoryParameters.isPatternsTransition == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.isPatternsTransition == 1">active</s:if>"> <input type="radio" value="1" id="isPatternsTransition" name="inventoryParameters.isPatternsTransition"
										<s:if test="inventoryParameters.isPatternsTransition == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"> 有无成套件管理: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.isSuite == 0">active</s:if>"> <input type="radio" value="0" id="isSuite" name="inventoryParameters.isSuite" <s:if test="inventoryParameters.isSuite == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.isSuite == 1">active</s:if>"> <input type="radio" value="1" id="isSuite" name="inventoryParameters.isSuite" <s:if test="inventoryParameters.isSuite == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 有无委托代销: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.isConsignmentSales == 0">active</s:if>"> <input type="radio" value="0" id="isConsignmentSales" name="inventoryParameters.isConsignmentSales"
										<s:if test="inventoryParameters.isConsignmentSales == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.isConsignmentSales == 1">active</s:if>"> <input type="radio" value="1" id="isConsignmentSales" name="inventoryParameters.isConsignmentSales"
										<s:if test="inventoryParameters.isConsignmentSales == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"> 有无受托代销管理: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.isOnCommission == 0">active</s:if>"> <input type="radio" value="0" id="isOnCommission" name="inventoryParameters.isOnCommission" <s:if test="inventoryParameters.isOnCommission == 0">checked="checked"</s:if>>是
									</label> <label class="btn btn-default <s:if test="inventoryParameters.isOnCommission == 1">active</s:if>"> <input type="radio" value="1" id="isOnCommission" name="inventoryParameters.isOnCommission" <s:if test="inventoryParameters.isOnCommission == 1">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"> 出入库模式: </label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="inventoryParameters.outMode == 0">active</s:if>"> <input type="radio" value="0" id="outMode" name="inventoryParameters.outMode" <s:if test="inventoryParameters.outMode == 0">checked="checked"</s:if>>先入先出
									</label> <label class="btn btn-default <s:if test="inventoryParameters.outMode == 1">active</s:if>"> <input type="radio" value="1" id="outMode" name="inventoryParameters.outMode" <s:if test="inventoryParameters.outMode == 1">checked="checked"</s:if>>先入后出
									</label>
								</div>
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
	$("#inventoryParametersForm").validationEngine();
	function saveOrUpdate() {
		if ($("#inventoryParametersForm").validationEngine('validate')) {
			$("#inventoryParametersForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntInventorySetAction!saveOrUpdate.action",
			dataType : "text",
			success : function(data) {
				showMessage(data, 'success');
				loadContent('', '${nvix}/nvixnt/vixntInventorySetAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>