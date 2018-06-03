<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="invWarehouseForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/vixntWarehouseAction!saveOrUpdateWarehouse.action">
	<input type="hidden" id="id" name="invWarehouse.id" value="${invWarehouse.id}" /> <input type="hidden" id="code" name="invWarehouse.code" value="${invWarehouse.code}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>仓库名称:</label>
			<div class="col-md-4">
				<input id="name" name="invWarehouse.name" value="${invWarehouse.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 仓库类型:</label>
			<div class="col-md-4">
				<select id="properties" name="invWarehouse.properties" class="form-control validate[required]">
					<option value="1" <c:if test='${invWarehouse.properties eq 1}'>selected="selected"</c:if>>普通仓</option>
					<option value="2" <c:if test='${invWarehouse.properties eq 2}'>selected="selected"</c:if>>赠品仓</option>
					<option value="3" <c:if test='${invWarehouse.properties eq 3}'>selected="selected"</c:if>>WIP-在制品区[工作中心]</option>
					<option value="4" <c:if test='${invWarehouse.properties eq 4}'>selected="selected"</c:if>>正品仓</option>
					<option value="5" <c:if test='${invWarehouse.properties eq 5}'>selected="selected"</c:if>>残次品仓</option>
					<option value="6" <c:if test='${invWarehouse.properties eq 6}'>selected="selected"</c:if>>其他仓</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">电话:</label>
			<div class="col-md-4">
				<input id="telephone" name="invWarehouse.telephone" value="${invWarehouse.telephone}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label"> 计价方式:</label>
			<div class="col-md-4">
				<select id="priceStyle" name="invWarehouse.priceStyle" class="form-control">
					<option value="1" <c:if test='${invWarehouse.priceStyle eq 1}'>selected="selected"</c:if>>全月平均法</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>库管员:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="warehousePersonName" name="invWarehouse.warehousePerson" value="${invWarehouse.warehousePerson }" class="form-control validate[required]" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="chooseEmployee();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否默认:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="invWarehouse.isDefault == 1">active</s:if>"> <input type="radio" id="status1" name="invWarehouse.isDefault" data-prompt-position="topLeft" class="validate[required]" <s:if test="invWarehouse.isDefault == 1"> checked="checked"</s:if> value="1" />否
					</label> <label class="btn btn-default <s:if test="invWarehouse.isDefault == 0">active</s:if>"> <input type="radio" id="status2" name="invWarehouse.isDefault" data-prompt-position="topLeft" class="validate[required]" <s:if test="invWarehouse.isDefault == 0"> checked="checked"</s:if> value="0" />是
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 地址:</label>
			<div class="col-md-10">
				<input id="postion" name="invWarehouse.postion" value="${invWarehouse.postion}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 备注:</label>
			<div class="col-md-10">
				<textarea id="memo" name="invWarehouse.memo" class="form-control">${invWarehouse.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/vixntWarehouseAction!goEmployeeChoose.action', 'single', '选择人员', 'warehousePerson');
	};
</script>