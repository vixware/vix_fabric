<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="priceConditionCountAreaForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/mdm/nvixntSalePriceAction!saveOrUpdatePriceConditionCountArea.action">
	<input type="hidden" id="id" name="priceConditionCountArea.id" value="${priceConditionCountArea.id}" /> 
	<input type="hidden" id="priceConditionId" name="priceConditionCountArea.priceCondition.id" value="${priceConditionCountArea.priceCondition.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>条件类型:</label>
			<div class="col-md-4">
				<select id="conditionType" name="priceConditionCountArea.conditionType" class="form-control validate[required]" onchange="select();" >
					<option value="0" <c:if test="${priceConditionCountArea.conditionType == 0}">selected="selected"</c:if> >客户</option>
					<option value="1" <c:if test="${priceConditionCountArea.conditionType == 1}">selected="selected"</c:if> >渠道</option>
				</select>
			</div>
			<label class="col-md-2 control-label">地域:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="regionalId" type="hidden" name="priceConditionCountArea.regional.id" value="${priceConditionCountArea.regional.id}" />
							<input id="regionalName" name="priceConditionCountArea.regional.name" value="${priceConditionCountArea.regional.name}" class="form-control" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseRegional();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#regionalId').val('');$('#regionalName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div id="customer">
				<label class="col-md-2 control-label">客户:</label>
				<div class="col-md-4">
					<div class="row">
						<div class="col-sm-12">
							<div class="input-group">
								<input id="customerId" type="hidden" name="priceConditionCountArea.customerAccount.id" value="${priceConditionCountArea.customerAccount.id}" />
								<input id="customerName" name="priceConditionCountArea.customerAccount.name" value="${priceConditionCountArea.customerAccount.name}" class="form-control" type="text" readonly="readonly" />
								<div class="input-group-btn">
									<button onclick="goChooseCustomer();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i>
									</button>
									<button onclick="$('#customerId').val('');$('#customerName').val('');" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<label class="col-md-2 control-label"><span class="text-danger">*</span>商品:</label>
				<div class="col-md-4">
					<div class="row">
						<div class="col-sm-12">
							<div class="input-group">
								<input id="itemId" type="hidden" name="priceConditionCountArea.item.id" value="${priceConditionCountArea.item.id}" />
								<input id="itemName" name="priceConditionCountArea.item.name" value="${priceConditionCountArea.item.name}" class="form-control validate[required]" type="text" readonly="readonly" />
								<div class="input-group-btn">
									<button onclick="goChooseItem();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i>
									</button>
									<button onclick="$('#itemId').val('');$('#itemName').val('');" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="channel">
				<label class="col-md-2 control-label">渠道:</label>
				<div class="col-md-4">
					<div class="row">
						<div class="col-sm-12">
							<div class="input-group">
								<input id="channelDistributorId" type="hidden" name="priceConditionCountArea.channelDistributor.id" value="${priceConditionCountArea.channelDistributor.id}" />
								<input id="channelDistributorName" name="priceConditionCountArea.channelDistributor.name" value="${priceConditionCountArea.channelDistributor.name}" class="form-control" type="text" readonly="readonly" />
								<div class="input-group-btn">
									<button onclick="goChooseChannelDistributor();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i>
									</button>
									<button onclick="$('#channelDistributorId').val('');$('#channelDistributorName').val('');" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<label class="col-md-2 control-label"><span class="text-danger">*</span>渠道商品:</label>
				<div class="col-md-4">
					<div class="row">
						<div class="col-sm-12">
							<div class="input-group">
								<input id="storeItemId" type="hidden" name="priceConditionCountArea.storeItem.id" value="${priceConditionCountArea.storeItem.id}" />
								<input id="storeItemName" name="priceConditionCountArea.storeItem.name" value="${priceConditionCountArea.storeItem.name}" class="form-control validate[required]" type="text" readonly="readonly" />
								<div class="input-group-btn">
									<button onclick="goChooseChannelItem();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i>
									</button>
									<button onclick="$('#itemId').val('');$('#itemName').val('');" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%-- <label class="col-md-2 control-label">客户:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="customerAccountId" type="hidden" name="priceConditionCountArea.customerAccount.id" value="${priceConditionCountArea.customerAccount.id}" />
							<input id="customerAccountName" name="priceConditionCountArea.customerAccount.name" value="${priceConditionCountArea.customerAccount.name}" class="form-control validate[required]" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseCustomerAccount();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#customerAccountId').val('');$('#customerAccountName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div> --%>
		</div>
		<%-- <div class="form-group">
			<label class="col-md-2 control-label">客户分类:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="customerAccountCategoryId" type="hidden" name="priceConditionCountArea.customerAccountCategory.id" value="${priceConditionCountArea.customerAccountCategory.id}" />
							<input id="customerAccountCategoryName" name="priceConditionCountArea.itemCatalog.name" value="${priceConditionCountArea.customerAccountCategory.name}" class="form-control validate[required]" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseCustomerAccountCategory();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#customerAccountCategoryId').val('');$('#customerAccountCategoryName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<label class="col-md-2 control-label">商品分类:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="itemCatalogId" type="hidden" name="priceConditionCountArea.itemCatalog.id" value="${priceConditionCountArea.itemCatalog.id}" />
							<input id="itemCatalogName" name="priceConditionCountArea.itemCatalog.name" value="${priceConditionCountArea.itemCatalog.name}" class="form-control validate[required]" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseItemCatalog();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#itemCatalogId').val('');$('#itemCatalogName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">商品组:</label>
			<div class="col-md-4">
				<input id="" name="" value="" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">客户组:</label>
			<div class="col-md-4">
				<input id="" name="" value="" class="form-control" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">分销商:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="channelDistributorId" type="hidden" name="priceConditionCountArea.channelDistributor.id" value="${priceConditionCountArea.channelDistributor.id}" />
							<input id="channelDistributorName" name="priceConditionCountArea.channelDistributor.name" value="${priceConditionCountArea.channelDistributor.name}" class="form-control validate[required]" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseChannelDistributor();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#channelDistributorId').val('');$('#channelDistributorName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<label class="col-md-2 control-label">商品:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="itemId" type="hidden" name="priceConditionCountArea.item.id" value="${priceConditionCountArea.item.id}" />
							<input id="itemName" name="priceConditionCountArea.item.name" value="${priceConditionCountArea.item.name}" class="form-control validate[required]" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseItem();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#itemId').val('');$('#itemName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>开始数量:</label>
			<div class="col-md-4">
				<input id="startCount" name="priceConditionCountArea.startCount" value="${priceConditionCountArea.startCount}" class="form-control validate[required,custom[number],min[1]]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>结束数量:</label>
			<div class="col-md-4">
				<input id="endCount" name="priceConditionCountArea.endCount" value="${priceConditionCountArea.endCount}" class="form-control validate[required,custom[number],min[1]]" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>价格类型:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test='priceConditionCountArea.areaPriceType == "all"'>active</s:if>" onchange="check('all');"> 
						<input type="radio" value="all" id="areaPriceType" name="priceConditionCountArea.areaPriceType" class="validate[required]" <s:if test='priceConditionCountArea.areaPriceType == "all"'>checked="checked"</s:if>>全部
					</label> 
					<label class="btn btn-default <s:if test='priceConditionCountArea.areaPriceType == "price"'>active</s:if>" onchange="check('price');"> 
						<input type="radio" value="price" id="areaPriceType" name="priceConditionCountArea.areaPriceType" class="validate[required]" <s:if test='priceConditionCountArea.areaPriceType == "price"'>checked="checked"</s:if>>价格
					</label>
					<label class="btn btn-default <s:if test='priceConditionCountArea.areaPriceType == "discount"'>active</s:if>" onchange="check('discount');"> 
						<input type="radio" value="discount" id="areaPriceType" name="priceConditionCountArea.areaPriceType" class="validate[required]" <s:if test='priceConditionCountArea.areaPriceType == "discount"'>checked="checked"</s:if>>折扣
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div id="priceDiv">
				<label class="col-md-2 control-label"><span class="text-danger">*</span>价格:</label>
				<div class="col-md-4">
					<input id="price" name="priceConditionCountArea.price" value="${priceConditionCountArea.price}" class="form-control validate[required,custom[number],min[1]]" type="text"/>
				</div>
			</div>
			<div id="discountDiv">
				<label class="col-md-2 control-label"><span class="text-danger">*</span>折扣:</label>
				<div class="col-md-4">
					<div class="input-group">
						<input id="discount" name="priceConditionCountArea.discount" value="${priceConditionCountArea.discount}" class="form-control validate[required,custom[number],min[1],max[100]]" type="text" />
						<span class="input-group-addon">(1-100)%</span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<input id="memo" name="priceConditionCountArea.memo" value="${priceConditionCountArea.memo}" class="form-control" type="text"/>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#priceConditionCountAreaForm").validationEngine();
	$(function(){
		var areaPriceType = $("input[id='areaPriceType']:checked").val();
		check(areaPriceType);
		select();
	})
	
	function check(areaPriceType){
		if(areaPriceType == 'all'){
			$("#priceDiv").show();
			$("#discountDiv").show();
		}else if(areaPriceType == 'price'){
			$("#priceDiv").show();
			$("#discountDiv").hide();
		}else if(areaPriceType =='discount'){
			$("#priceDiv").hide();
			$("#discountDiv").show();
		}
	}
	
	function select(){
		var conditionType = $("#conditionType").val();
		if(conditionType == 0){
			$("#customer").show();
			$("#channel").hide();
		}else if(conditionType == 1){
			$("#customer").hide();
			$("#channel").show();
		}
	}
	
	function goChooseRegional() {
		chooseListData('${nvix}/nvixnt/mdm/nvixntPurchasePriceAction!goChooseRegional.action', 'single', '选择地域', 'regional');
	};
	
	function goChooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer');
	}
	
	function goChooseChannelDistributor() {
		chooseListData('${nvix}/nvixnt/mdm/nvixntSalePriceAction!goChooseChannelDistributor.action', 'single', '选择渠道', 'channelDistributor');
	}
	
	function goChooseChannelItem() {
		var channelId = $("#channelDistributorId").val();
		chooseListData('${nvix}/nvixnt/mdm/nvixntSalePriceAction!goChooseChannelItem.action?channelId=' + channelId, 'single', '选择渠道商品', 'storeItem');
	}
	
	function goChooseItem() {
		chooseListData('${nvix}/nvixnt/mdm/nvixntSalePriceAction!goChooseItem.action', 'single', '选择商品', 'item');
	};
</script>