<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="couponPlanForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/nvixCouponManagementAction!saveOrUpdate.action">
	<input id="id" name="coupon.id" value="${coupon.id }" type="hidden" />
	<input id="status" name="coupon.status" value="${coupon.status }" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>方案名称:</label>
			<div class="col-md-10">
				<input id="name" name="coupon.name" value="${coupon.name}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" />
			</div>
		</div>
		<div class="form-group">
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
			<label id="memberTags1" class="col-md-2 control-label"> 客户标签:</label>
			<div id="memberTags2" class="col-md-4">
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
			<label id="quantity1" class="col-md-2 control-label" style="display:none;">发放数量:</label>
			<div id="quantity2" class="col-md-4" style="display:none;">
				<input id="quantity" name="coupon.quantity" value="${coupon.quantity}" type="text" data-prompt-position="topLeft" class="form-control validate[custom[integer]]" />
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
				<div class="input-group">
					<input id="expenditure" name="coupon.expenditure" value="${coupon.expenditure}" type="text" data-prompt-position="topLeft" class="form-control validate[custom[number]]" />
					<span class="input-group-addon"">元</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>面值:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="amount" type="text" name="coupon.amount" value="${coupon.amount}" class="form-control">
					<span class="input-group-addon"">元</span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>有效期:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="couponValidatePeriod" type="text" name="coupon.couponValidatePeriod" value="${coupon.couponValidatePeriod}" class="form-control validate[required]"><span class="input-group-addon"">天</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea name="coupon.memo" class="form-control">${coupon.memo} </textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"></label>
			<div class="col-md-10">
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
			$("#quantity1").attr("style","display:none;");
			$("#quantity2").attr("style","display:none;");
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
<%-- <%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 分销管理 <span>&gt; 优惠券管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixCouponManagementAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>优惠券</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="couponForm">
				<input type="hidden" id="couponId" name="coupon.id" value="${coupon.id}" /> <input type="hidden" id="channelDistributorId" name="coupon.channelDistributor.id" value="${coupon.channelDistributor.id }" />
				<fieldset>
					<legend>基本信息:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 优惠券名称:</label>
						<div class="col-md-3">
							<input id="couponName" name="coupon.name" value="${coupon.name}" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"> 发券方式:</label>
						<div class="col-md-3">
							<select id="endmode" name="coupon.endmode" class="form-control">
								<option value="1" <c:if test='${coupon.endmode eq 1}'>selected="selected"</c:if>>会员领取</option>
								<option value="2" <c:if test='${coupon.endmode eq 2}'>selected="selected"</c:if>>直接发放</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 客户标签:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input type="hidden" id="memberTagsIds" name="coupon.memberTagsIds" value="${coupon.memberTagsIds}" /> <input id="memberTagsNames" name="coupon.memberTagsNames" value="${coupon.memberTagsNames}" class="form-control" type="text" readonly="readonly" />
										<div class="input-group-btn">
											<button onclick="goChooseMemberTag();" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
											</button>
											<button onclick="$('#memberTagsIds').val('');$('#memberTagsNames').val('');" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"> 营销类型:</label>
						<div class="col-md-3">
							<select id="shopMarketType" name="coupon.shopMarketType" class="form-control">
								<option value="shop" <s:if test='coupon.shopMarketType == "shop"'>selected="selected"</s:if>> 店铺优惠券</option>
								<option value="category" <s:if test='coupon.shopMarketType == "category"'>selected="selected"</s:if>> 分类优惠券</option>
								<option value="microShopCategory" <s:if test='coupon.shopMarketType == "microShopCategory"'>selected="selected"</s:if>> 店铺分类优惠券</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 门店:</label>
						<div class="col-md-3">
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
						<label class="col-md-2 control-label"> 商品分类:</label>
						<div class="col-md-3">
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
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 优惠券面值:</label>
						<div class="col-md-3">
							<input id="amount" name="coupon.amount" value="${coupon.amount}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"> 发放数量:</label>
						<div class="col-md-3">
							<input id="quantity" name="coupon.quantity" value="${coupon.quantity}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 使用条件:</label>
						<div class="col-md-3">
							<select id="couponUseCondition" name="coupon.couponUseCondition" class="form-control">
								<option value="1" <c:if test='${coupon.couponUseCondition == "1"}'>selected="selected"</c:if>> 订单总额</option>
								<option value="0" <c:if test='${coupon.couponUseCondition == "0"}'>selected="selected"</c:if>> 无限制</option>
							</select>
						</div>
						<label class="col-md-2 control-label"> 订单金额:</label>
						<div class="col-md-3">
							<input id="expenditure" name="coupon.expenditure" value="${coupon.expenditure}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 种类:</label>
						<div class="col-md-3">
							<select id="type" name="coupon.type" class="form-control">
								<option value="1" <c:if test='${coupon.type == "1"}'>selected="selected"</c:if>> 代金券</option>
								<option value="2" <c:if test='${coupon.type == "2"}'>selected="selected"</c:if>> 折扣券</option>
								<option value="3" <c:if test='${coupon.type == "3"}'>selected="selected"</c:if>> 满减券</option>
							</select>
						</div>
						<label class="col-md-2 control-label"> 状态:</label>
						<div class="col-md-3">
							<select id="status" name="coupon.status" class="form-control">
								<option value="1" <c:if test='${coupon.status == "1"}'>selected="selected"</c:if>> 已发放</option>
								<option value="0" <c:if test='${coupon.status == "0"}'>selected="selected"</c:if>> 未发放</option>
							</select>
						</div>
					</div>
					<legend>优惠券有效期:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="userStartDate" name="coupon.userStartDate" value="${coupon.userStartDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'userStartDate'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="userEndDate" name="coupon.userEndDate" value="${coupon.userEndDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'userEndDate'});"><i
									class="fa fa-calendar"></i></span>
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
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixCouponManagementAction!goList.action');">
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
	$("#storeItemForm").validationEngine();
	function saveOrUpdate() {
		if ($("#couponForm").validationEngine('validate')) {
			$("#couponForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixCouponManagementAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/nvixCouponManagementAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
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
</script> --%>