<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="promotionRuleDetailForm" class="form-horizontal" style="padding:40px;" action="${nvix}/nvixnt/nvixntPromotionRuleDetailAction!saveOrUpdate.action">
	<input type="hidden" id="promotionRuleDetailId" name = "promotionRuleDetail.id" value="${promotionRuleDetail.id}"/>
	<input type="hidden" id="promotionRuleId" name = "promotionRuleDetail.promotionRule.id" value="${promotionRuleDetail.promotionRule.id}"/>
	<input type="hidden" id="promotionType" value="${promotionRule.promotionType }"  />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>促销商品:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input type="hidden" id="itemId" name="promotionRuleDetail.item.id" value="${promotionRuleDetail.item.id}"/>
							<input id="itemName" name="itemName" value="${promotionRuleDetail.item.name}" type="text" readonly="readonly" onclick="chooseEcProduct();" data-prompt-position="topLeft" class="form-control validate[required]"/>
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#itemId').val('');$('#itemName').val('');$('#ecProductSku').val('');$('#ecProductPrice').val('');">
									清空
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${promotionRule.promotionType == 'PROMOTION_SECONDSALE'}">
			<div class="form-group">
				<label class="col-md-2 control-label">SKU:</label>
				<div class="col-md-4">
					<input id="itemSku" name="promotionRuleDetail.sku" value="${promotionRuleDetail.sku}"  data-prompt-position="topLeft" readonly="readonly" class="form-control" type="text"/>
				</div>
				<label class="col-md-2 control-label"><span class="text-danger">*</span>促销价格:</label>
				<div class="col-md-4">
					<input id="promotionPrice" name="promotionRuleDetail.promotionPrice" value="${promotionRuleDetail.promotionPrice}" type="text" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">销售价格:</label>
				<div class="col-md-4">
					<input id="itemPrice" name="itemPrice" value="${promotionRuleDetail.item.price}" readonly="readonly" class="form-control" />
				</div>
				<label class="col-md-2 control-label"><span class="text-danger">*</span>销售数量:</label>
				<div class="col-md-4">
					<input id="limitSaleCount" name="promotionRuleDetail.limitSaleCount" value="${promotionRuleDetail.limitSaleCount}" type="text" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" />
				</div>
			</div>
		</c:if>
		<c:if test="${promotionRule.promotionType != 'PROMOTION_SALEGIFT' && promotionRule.promotionType != 'PROMOTION_SECONDSALE'}">
			<c:if test="${promotionRule.promotionType == 'PROMOTION_PRICE'}">
				<div class="form-group">
					<label class="col-md-2 control-label">SKU:</label>
					<div class="col-md-4">
						<input id="itemSku" name="promotionRuleDetail.sku" value="${promotionRuleDetail.sku}"  data-prompt-position="topLeft" readonly="readonly" class="form-control" type="text"/>
					</div>
					<label class="col-md-2 control-label"><span class="text-danger">*</span>促销价格:</label>
					<div class="col-md-4">
						<input id="promotionPrice" name="promotionRuleDetail.promotionPrice" value="${promotionRuleDetail.promotionPrice}" type="text" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">销售价格:</label>
					<div class="col-md-4">
						<input id="itemPrice" name="itemPrice" value="${promotionRuleDetail.item.price}" readonly="readonly" class="form-control" />
					</div>
				</div>
			</c:if>
			<c:if test="${promotionRule.promotionType == 'PROMOTION_DISCOUNT'}">
				<div class="form-group">
					<label class="col-md-2 control-label">SKU:</label>
					<div class="col-md-4">
						<input id="itemSku" name="promotionRuleDetail.sku" value="${promotionRuleDetail.sku}"  data-prompt-position="topLeft" readonly="readonly" class="form-control" type="text"/>
					</div>
					<label class="col-md-2 control-label"><span class="text-danger">*</span>折扣:</label>
					<div class="col-md-4">
						<input id="promotionDiscount" name="promotionRuleDetail.promotionDiscount" value="${promotionRuleDetail.promotionDiscount}" type="text" data-prompt-position="topLeft" class="form-control validate[required,custom[number,max[1]]]" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label"><span class="text-danger">*</span>促销价格:</label>
					<div class="col-md-4">
						<input id="promotionPrice" name="promotionRuleDetail.promotionPrice" value="${promotionRuleDetail.promotionPrice}" type="text" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" />
					</div>
					<label class="col-md-2 control-label">销售价格:</label>
					<div class="col-md-4">
						<input id="itemPrice" name="itemPrice" value="${promotionRuleDetail.item.price}" readonly="readonly" class="form-control" />
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label class="col-md-2 control-label"><span class="text-danger">*</span>限制销量:</label>
				<div class="col-md-4">
					<div data-toggle="buttons" class="btn-group">
						<label class="btn btn-default <s:if test="promotionRuleDetail.isLimitBuyCount == 1">active</s:if>" onclick="$('#limitSaleCountDiv').attr('style','');">
							<input type="radio" id="isLimitBuyCount1" name="promotionRuleDetail.isLimitBuyCount" data-prompt-position="topLeft" class="validate[required]"
							<s:if test="promotionRuleDetail.isLimitBuyCount == 1"> checked="checked"</s:if> value="1" />是
						</label>
						<label class="btn btn-default <s:if test="promotionRuleDetail.isLimitBuyCount == null || promotionRuleDetail.isLimitBuyCount == 0">active</s:if>" onclick="$('#limitSaleCountDiv').attr('style','display:none;');">
							<input type="radio" id="isLimitBuyCount2" name="promotionRuleDetail.isLimitBuyCount" data-prompt-position="topLeft" class="validate[required]"
							<s:if test="promotionRuleDetail.isLimitBuyCount == null || promotionRuleDetail.isLimitBuyCount == 0"> checked="checked"</s:if> value="0" />否
						</label>
					</div>
				</div>
			</div>
			<div id="limitSaleCountDiv" class="form-group">
				<label class="col-md-2 control-label">限制销售数量:</label>
				<div class="col-md-4">
					<input id="limitSaleCount" name="promotionRuleDetail.limitSaleCount" value="${promotionRuleDetail.limitSaleCount}" type="text" data-prompt-position="topLeft" class="form-control validate[custom[integer]]" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">最小购买数量:</label>
				<div class="col-md-4">
					<input id="minBuyCount" name="promotionRuleDetail.minBuyCount" value="${promotionRuleDetail.minBuyCount}" type="text" data-prompt-position="topLeft" class="form-control validate[custom[integer]]" />
				</div>
			</div>
		</c:if>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-4">
				<input id="promotionRuleDetailMemo" name="promotionRuleDetail.memo" value="${promotionRuleDetail.memo}" type="text" class="form-control" />
			</div>
		</div>
		<c:if test="${promotionRule.promotionType == 'PROMOTION_SALEGIFT'}">
			<div id="DIV_PROMOTION_FULLGIFT" class="form-group">
				<label class="col-md-2 control-label"></label>
				<div class="col-md-10">
					<div class="jarviswidget jarviswidget-color-white" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
						<header>
							<span class="widget-icon"> <i class="fa fa-table"></i> </span>
							<h2>满赠赠品列表</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin:5px;">
									<div class="form-group" style="margin: 0 5px;">
										<div class=" listMenu-float-right">
											<button onclick="saveOrUpdateEcProductGiftForPRD('');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
											</button>
										</div>
									</div>
								</div>
							    <table id="saleGiftEcProductGift" class="table table-striped table-bordered table-hover" width="100%">
							   		<thead>
							   			<tr>
											<th width="8%">编号</th>
											<th width="20%">商品</th>
											<th width="10%">赠品数量</th>
											<th width="15%">备注</th>
											<th width="10%">操作</th>
										</tr>
							    </table>
						 	</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</fieldset>
</form>
<script type="text/javascript">
	$("#promotionRuleDetailForm").validationEngine();
	// 满赠赠品列表
	var saleGiftEcProductGiftColumns = [
		{"orderable" : false,"data" : function(data) {return "";}},
		{"name" : "item.name","data" : function(data) {return data.item == null ? "" : data.item.name;}},
		{"name" : "giftCount","data" : function(data) {return data.giftCount;}},
		{"orderable" : false,"data" : function(data) {return data.memo;}},
		{"orderable" : false,"data" : function(data) {
			var update = "<a href='javascript:void(0);' href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdateEcProductGiftForPRD('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteEcProductGiftById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];
	var saleGiftEcProductGiftTable = initDataTable("saleGiftEcProductGift","${nvix}/nvixnt/nvixntItemGiftAction!goSingleListJson.action",saleGiftEcProductGiftColumns,function(page,pageSize,orderField,orderBy){
	 	var promotionRuleDetailId = $("#promotionRuleDetailId").val();
	 	var type = "prd";
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"promotionRuleDetailId" : promotionRuleDetailId,"type" : type};
	});
	/** 保存满赠赠品  */
	function saveOrUpdateEcProductGiftForPRD(id){
		var promotionRuleDetailId = $("#promotionRuleDetailId").val();
		if(promotionRuleDetailId == ''){
			if($('#promotionRuleDetailForm').validationEngine('validate')){
				$("#promotionRuleDetailForm").attr("action","${nvix}/nvixnt/nvixntPromotionRuleDetailAction!saveOrUpdateInner.action");
				$("#promotionRuleDetailForm").ajaxSubmit({
		    		type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(",");
						if(r[0] == "0"){
							showMessage(r[1]);
						}else{
							$("#promotionRuleDetailId").val($.trim(r[0]));
							var promotionRuleDetailId = $("#promotionRuleDetailId").val();
							$("#promotionRuleDetailForm").attr("action","${nvix}/nvixnt/nvixntPromotionRuleDetailAction!saveOrUpdate.action");
							openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntItemGiftAction!goSaveOrUpdate.action?id='+id+"&promotionRuleDetailId="+promotionRuleDetailId,"ecProductGiftForm","满赠赠品信息",480,400,saleGiftEcProductGiftTable);
						}
						
					}
				});
			}
		}else{
			openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntItemGiftAction!goSaveOrUpdate.action?id='+id+"&promotionRuleDetailId="+promotionRuleDetailId,"ecProductGiftForm","满赠赠品信息",480,400,saleGiftEcProductGiftTable);
		}
	}
	
	function deleteEcProductGiftById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntItemGiftAction!deleteById.action?id='+id,'是否删除满赠赠品信息?',saleGiftEcProductGiftTable);
	}
	
	function chooseEcProduct(){
		chooseListData('${nvix}/nvixnt/nvixntItemGiftAction!goChooseItem.action',"single","选择商品列表","item",radioChange);
	}
	
	function radioChange(entityName,id,name,price,sku){
		parent.$('#' + entityName + 'Id').val(id);
		parent.$('#' + entityName + 'Name').val(name);
		parent.$('#' + entityName + 'Sku').val(sku);
		parent.$('#' + entityName + 'Price').val(price);
	}
	
	/** 根据商品加载商品的sku列表 */
	/* function loadEcProductSkuList(id,pt){
		if(id != ''){
			var prdId = $("#promotionRuleDetailId").val();
			$.ajax({
				url:'${nvix}/nvixnt/promotionRuleDetailAction!loadEcProductSkuByEcProductId.action?id='+id+'&prdId='+prdId,
				 cache: false,
				 success: function(html){
					if(pt == 'PROMOTION_PRICE'){
						$("#promotionPriceSkuTd").html(html);
					}
					if(pt == 'PROMOTION_DISCOUNT'){
						$("#promotionDiscountSkuTd").html(html);
					}
					if(pt == 'PROMOTION_SECONDSALE'){
						$("#secondSaleSkuTd").html(html);
					}
				}
			});
		}
	}
	
	function calcutePromotionDiscountPrice(){
		var sps = $("#ecProductPrice").val();
		var pd = $("#promotionDiscount").val();
		if(Number(sps) > 0 && parseFloat(pd) > 0){
			var result = Number(sps) * parseFloat(pd);
			$("#promotionPriceSpan").html(result.toFixed(2).toString());
		}
	}
	
	if($("#promotionRuleDetailId").val() != ''){
		calcutePromotionDiscountPrice();
		var epId = $("#ecProductId").val();
		if(epId != ''){
			var pt = $("#promotionType").val();
			loadEcProductSkuList(epId,pt);
		}
		ecProductGiftTable.ajax.reload();
	} */ 
</script>