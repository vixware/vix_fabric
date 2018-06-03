<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="customerAddressForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/nvixCustomerAddressAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="customerAddress.id" value="${customerAddress.id}" />
	<input id="customerId" name="customerAddress.customerAccount.id" value="${customerAddress.customerAccount.id}" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>客户:</label>
			<div class="col-md-4">
				<input id="customerName" type="text" value="${customerAddress.customerAccount.name}" data-prompt-position="topLeft" class="form-control validate[required]" readonly="readonly" />
				<%-- <div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="customerName" type="text" value="${customerAddress.customerAccount.name}" data-prompt-position="topLeft" class="form-control validate[required]" readonly="readonly" onfocus="chooseCustomer();" />
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#customerId').val('');$('#customerName').val('');">清空</button>
							</div>
						</div>
					</div>
				</div> --%>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否默认:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <c:if test="${customerAddress.isDefault == 1}">active</c:if>"> 
						<input type="radio" value="1" id="isDefault" name="customerAddress.isDefault" class="validate[required]" <c:if test="${customerAddress.isDefault == 1}">checked="checked"</c:if>>是
					</label> 
					<label class="btn btn-default <c:if test="${customerAddress.isDefault == 0}">active</c:if>"> 
						<input type="radio" value="0" id="isDefault" name="customerAddress.isDefault" class="validate[required]" <c:if test="${customerAddress.isDefault == 0}">checked="checked"</c:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>省份:</label>
			<div class="col-md-4">
				<select id="pId" name="customerAddress.province.id" data-prompt-position="topLeft" class="form-control validate[required]" onchange="loadCity();">
					<option value="">------请选择------</option>
					<c:forEach items="${provinceList}" var="entity">
						<option value="${entity.id}" <c:if test="${customerAddress.province.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>城市:</label>
			<div class="col-md-4">
				<select id="cId" name="customerAddress.city.id" data-prompt-position="topLeft" class="form-control validate[required]" onchange="loadDistrict();">
					<option value="">------请选择------</option>
					<c:forEach items="${cityList}" var="entity">
						<option value="${entity.id}" <c:if test="${customerAddress.city.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>区县:</label>
			<div class="col-md-4">
				<select id="dId" name="customerAddress.district.id" data-prompt-position="topLeft" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${districtList}" var="entity">
						<option value="${entity.id}" <c:if test="${customerAddress.district.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label">邮编:</label>
			<div class="col-md-4">
				<input id="postCode" name="customerAddress.postCode" value="${customerAddress.postCode}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>地址:</label>
			<div class="col-md-10">
				<input id="address" name="customerAddress.address" value="${customerAddress.address}" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#customerAddressForm").validationEngine();
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer');
	}
	
	function loadCity(){
		var provinceId = $("#pId").val();
		$("#cId").load("${nvix}/nvixnt/system/nvixntAddressInfoAction!loadSubAddressInfo.action?parentId="+provinceId);
		$("#dId").val("");
	}
	
	function loadDistrict(){
		var cityId = $("#cId").val();
		$("#dId").load("${nvix}/nvixnt/system/nvixntAddressInfoAction!loadSubAddressInfo.action?parentId="+cityId);
	}
</script>