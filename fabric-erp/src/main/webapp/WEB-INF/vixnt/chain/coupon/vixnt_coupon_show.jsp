<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="couponPlanForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/nvixCouponManagementAction!saveOrUpdate.action">
	<input id="id" name="coupon.id" value="${coupon.id}" type="hidden" />
	<input id="status" name="coupon.status" value="${coupon.status}" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>方案名称:</label>
			<div class="col-md-4">
				<input id="name" name="coupon.name" value="${coupon.name}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" readonly="readonly"/>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>发放方式:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<input id="endmode" value="${coupon.endmode}" type="hidden"/>
					<label id="type1" class="btn btn-default <s:if test="coupon.endmode == 1">active</s:if>" onclick="couponPlanTypeChange('1');">
						<input type="radio" name="coupon.endmode" data-prompt-position="topLeft" class="validate[required]"
						<s:if test="coupon.endmode == 1"> checked="checked"</s:if> value="1" />发放
					</label>
					<label id="type2" class="btn btn-default <s:if test="coupon.endmode == 2">active</s:if>" onclick="couponPlanTypeChange('2');">
						<input type="radio" name="coupon.endmode" data-prompt-position="topLeft" class="validate[required]"
						<s:if test="coupon.endmode == 2"> checked="checked"</s:if> value="2" />领取
					</label>
					<label id="type3" class="btn btn-default <s:if test="coupon.endmode == 3">active</s:if>" onclick="couponPlanTypeChange('3');">
						<input type="radio" name="coupon.endmode" data-prompt-position="topLeft" class="validate[required]"
						<s:if test="coupon.endmode == 3"> checked="checked"</s:if> value="3" />其他
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label id="memberTags1" class="col-md-2 control-label" > 客户标签:</label>
			<div id="memberTags2" class="col-md-4" >
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input type="hidden" id="memberTagsIds" name="coupon.memberTagsIds" value="${coupon.memberTagsIds}" /> <input id="memberTagsNames" name="coupon.memberTagsNames" value="${coupon.memberTagsNames}" class="form-control" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseMemberTag();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#memberTagsIds').val('');$('#memberTagsNames').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<label id="quantity1" class="col-md-2 control-label" >发放数量:</label>
			<div id="quantity2" class="col-md-4">
				<input id="quantity" name="coupon.quantity" value="${coupon.quantity}" type="text" data-prompt-position="topLeft" class="form-control validate[custom[integer]]" readonly="readonly"/>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>营销类型:</label>
			<div class="col-md-8">
				<div data-toggle="buttons" class="btn-group">
					<input id="shopMarketType" value="${coupon.shopMarketType}" type="hidden"/>
					<label class="btn btn-default <s:if test="coupon.shopMarketType == 'shop'">active</s:if>" onclick="marketTypeChange('shop');">
						<input type="radio" id="shopMarketType1" name="coupon.shopMarketType" data-prompt-position="topLeft" class="validate[required]"
						<s:if test="coupon.shopMarketType == 'shop'"> checked="checked"</s:if> value="shop" />店铺
					</label>
					<label class="btn btn-default <s:if test="coupon.shopMarketType == 'category'">active</s:if>" onclick="marketTypeChange('category');">
						<input type="radio" id="shopMarketType2" name="coupon.shopMarketType" data-prompt-position="topLeft" class="validate[required]"
						<s:if test="coupon.shopMarketType == 'category'"> checked="checked"</s:if> value="category" />商品分类
					</label>
					<label class="btn btn-default <s:if test="coupon.shopMarketType == 'microShopCategory'">active</s:if>" onclick="marketTypeChange('microShopCategory');">
						<input type="radio" id="shopMarketType2" name="coupon.shopMarketType" data-prompt-position="topLeft" class="validate[required]"
						<s:if test="coupon.shopMarketType == 'microShopCategory'"> checked="checked"</s:if> value="microShopCategory" />店铺商品
					</label>
				</div>
			</div>
		</div>
			<div class="form-group">
			<label class="col-md-2 control-label"> 门店:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="channelDistributorName" value="${coupon.channelDistributor.name }" class="form-control" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseChannelDistributor();" type="button" class="btn btn-primary">
									<i class="glyphicon glyphicon-plus"></i>
								</button>
								<button onclick="$('#channelDistributorId').val('');$('#channelDistributorName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<label class="col-md-2 control-label" id="platformProductCategory1">商品分类:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div id="itemCategoryTreeMenu" class="input-group">
							<input id="itemCatalogIds" name="coupon.itemCatalogIds" type="hidden" value="${coupon.itemCatalogIds}" /> <input id="itemCatalogNames" name="coupon.itemCatalogNames" type="text" onfocus="showItemCategory(); return false;" value="${coupon.itemCatalogNames}" type="text" readonly="readonly" class="form-control" />
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#itemCatalogIds').val('');$('#itemCatalogNames').val('');">清空</button>
							</div>
							<div id="itemCategoryMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
								<ul id="itemCategoryTree" class="ztree"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>使用条件:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="coupon.couponUseCondition == 0">active</s:if>">
						<input type="radio" id="couponUseCondition1" name="coupon.couponUseCondition" data-prompt-position="topLeft" class="validate[required]"
						<s:if test="coupon.couponUseCondition == 0"> checked="checked"</s:if> value="0" onchange="couponUseConditionChange('0');" />无限制
					</label>
					<label class="btn btn-default <s:if test="coupon.couponUseCondition == 1">active</s:if>">
						<input type="radio" id="couponUseCondition2" name="coupon.couponUseCondition" data-prompt-position="topLeft" class="validate[required]"
						<s:if test="coupon.couponUseCondition == 1"> checked="checked"</s:if> value="1" onchange="couponUseConditionChange('1');" />订单总额满
					</label>
				</div>
			</div>
			<label id="totalOrderAmount1" class="col-md-2 control-label">订单总额:</label>
			<div id="totalOrderAmount2" class="col-md-4">
				<input id="expenditure" name="coupon.expenditure" value="${coupon.expenditure}" type="text" data-prompt-position="topLeft" class="form-control validate[custom[number]]" readonly="readonly"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>面值:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="amount" type="text" name="coupon.amount" value="${coupon.amount}" class="form-control" readonly="readonly">
					<span class="input-group-addon"">元</span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>有效期:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input type="text" name="couponValidatePeriod" value="${coupon.couponValidatePeriod}" class="form-control validate[required]" readonly="readonly"><span class="input-group-addon"">天</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea name="coupon.memo" class="form-control">${coupon.memo} </textarea>
			</div>
		</div>
		<div class="jarviswidget jarviswidget-color-white" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i> </span>
				<h2>优惠券列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
				    <table id="coupon" class="table table-striped table-bordered table-hover" width="100%">
				   		<thead>			                
							<tr>
								<th width="10%">编号</th>
								<th width="12%">编码</th>
								<th width="15%">会员</th>
								<th width="10%">面值</th>
								<th width="18%">备注</th>
							</tr>
						</thead>
				    </table>
			 	</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#couponPlanForm").validationEngine();
	// 优惠券列表
	var couponColumns = [
   		{"orderable" : false,"data" : function(data) {return "";}},
   		{"name" : "code","data" : function(data) {return data.code;}},
   		{"data" : function(data) {return data.customerName;}},
   		{"name" : "amount","data" : function(data) {return data.amount;}},
   		{"orderable" : false,"data" : function(data) {return data.memo;}},
   	];
   	var couponTable = initDataTable("coupon","${nvix}/nvixnt/nvixCouponManagementAction!getCouponDetailList.action",couponColumns,function(page,pageSize,orderField,orderBy){
   		var id = $("#id").val();
   		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"couponId":id};
   	});
   	
   	function saveOrUpdate(id){
   		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCouponManagementAction!goSaveOrUpdate.action?id='+id,"couponForm",id == '' ? "新增优惠券" : "修改优惠券",720,350,couponTable);
   	};
   	
   	function deleteById(id){
   		deleteEntityByConfirm('${nvix}/nvixnt/nvixCouponManagementAction!deleteById.action?id='+id,'是否删除优惠券?',couponTable);
   	}
   	
   	
	function couponPlanTypeChange(tag){
		if(tag == '1'){
			$("#memberTags1").attr("style","");
			$("#memberTags2").attr("style","");
			$("#quantity1").attr("style","");
			$("#quantity2").attr("style","");
		}
		if(tag == '2'){
			$("#memberTags1").attr("style","display:none;");
			$("#memberTags2").attr("style","display:none;");
			$("#quantity1").attr("style","");
			$("#quantity2").attr("style","");
		}
		if(tag == '3'){
			$("#memberTags1").attr("style","display:none;");
			$("#memberTags2").attr("style","display:none;");
			$("#quantity1").attr("style","");
			$("#quantity2").attr("style","");
		}
	}
	couponPlanTypeChange($("#couponPlanType").val());
	
	function marketTypeChange(tag){
		if(tag == 'platform_general'){
			$("#platformProductCategory1").attr("style","display:none;");
			$("#platformProductCategory2").attr("style","display:none;");
		}
		if(tag == 'platform_category'){
			$("#platformProductCategory1").attr("style","");
			$("#platformProductCategory2").attr("style","");
		}
	}
	marketTypeChange($("#marketType").val());
	
	function couponUseConditionChange(tag){
		if(tag == '0'){
			$("#totalOrderAmount1").attr("style","display:none;");
			$("#totalOrderAmount2").attr("style","display:none;");
		}else{
			$("#totalOrderAmount1").attr("style","");
			$("#totalOrderAmount2").attr("style","");
		}
	}
	$(function(){
		var b = $("input[name='coupon.endmode']:checked").val();
		if(b == '1'){
			$("#memberTags1").attr("style","");
			$("#memberTags2").attr("style","");
			$("#quantity1").attr("style","");
			$("#quantity2").attr("style","");
		}
		if(b == '2'){
			$("#memberTags1").attr("style","display:none;");
			$("#memberTags2").attr("style","display:none;");
			$("#quantity1").attr("style","");
			$("#quantity2").attr("style","");
		}
		if(b == '3'){
			$("#memberTags1").attr("style","display:none;");
			$("#memberTags2").attr("style","display:none;");
			$("#quantity1").attr("style","");
			$("#quantity2").attr("style","");
		}
		var a = $("input[name='coupon.couponUseCondition']:checked").val();
		if(a == '0'){
			$("#totalOrderAmount1").attr("style","display:none;");
			$("#totalOrderAmount2").attr("style","display:none;");
		}else{
			$("#totalOrderAmount1").attr("style","");
			$("#totalOrderAmount2").attr("style","");
		}
	})
	//初始化下拉列表树
	//var showMenu = initDropListTree("treeMenu","${snow}/ec/category/productCategoryAction!findTreeToJson.action","productCategoryId","productCategoryName","productCategoryTrees","menuContent");
	/** 初始化分类下拉列表树 */
	var showItemCategory = initDropListTree("itemCategoryTreeMenu", "${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action", "itemCatalogIds", "itemCatalogNames", "itemCategoryTree", "itemCategoryMenu");
	//弹窗方式初始化分类
	function chooseItemCatagory() {
		var ids = $("#objectExpandId").val();
		chooseListData('${nvix}/nvixnt/mdm/nvixntItemAction!goChooseMultiItemCatalog.action', 'multi', '选择分类', null, function() {
			var ItemCatagory = chooseMap.valueSetWithClear().split(":");
			if (ItemCatagory != '' && ItemCatagory.length == 2) {
				$('#itemCatalogIds').val(ItemCatagory[0]);
				$('#itemCatalogNames').val(ItemCatagory[1]);
			} else {
				layer.alert("请选择商品类型!");
			}
		});
	};
	function goChooseChannelDistributor() {
		chooseListData('${nvix}/nvixnt/vixntStoreRequireGoodsAction!goChooseChannelDistributor.action', 'single', '选择门店', 'channelDistributor');
	};
	function goChooseMemberTag() {
		chooseListData('${nvix}/nvixnt/nvixCouponManagementAction!goChooseMemberTag.action', 'multi', '选择标签', null, function() {
			var ItemCatagory = chooseMap.valueSetWithClear().split(":");
			if (ItemCatagory != '' && ItemCatagory.length == 2) {
				$('#memberTagsIds').val(ItemCatagory[0]);
				$('#memberTagsNames').val(ItemCatagory[1]);
			} else {
				layer.alert("请选择标签!");
			}
		});
	};
</script>
