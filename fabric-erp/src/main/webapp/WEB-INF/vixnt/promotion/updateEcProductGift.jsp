<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="ecProductGiftForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/nvixntItemGiftAction!saveOrUpdate.action">
	<input type="hidden" id="itemGiftId" name="itemGift.id" value="${itemGift.id}"/>
	<input type="hidden" id="promotionRuleId" name="itemGift.promotionRule.id" value="${promotionRuleDetail.promotionRule.id}"/>
	<input type="hidden" id="promotionRuleDetailId" name = "itemGift.promotionRuleDetail.id" value="${promotionRuleDetail.id}"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>赠送商品:</label>
			<div class="col-md-8">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input type="hidden" id="itemId" name="itemGift.item.id" value="${itemGift.item.id}"/>
							<input id="itemName" name="itemName" value="${itemGift.item.name}" type="text" readonly="readonly"
								 onclick="chooseListData('${nvix}/nvixnt/nvixntItemGiftAction!goChooseItem.action','single','选择商品列表','item');" 
								 data-prompt-position="topLeft" class="form-control validate[required]"/>
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#productBrandId').val('');$('#productBrandName').val('');">清空</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>赠品数量:</label>
			<div class="col-md-8">
				<input id="ecProductGiftGiftCount" name="itemGift.giftCount" value="${itemGift.giftCount}" type="text" data-prompt-position="topLeft" class="form-control validate[required,custom[integer]]" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">备注:</label>
			<div class="col-md-8">
				<input id="ecProductGiftMemo" name="itemGift.memo" value="${itemGift.memo}" type="text" class="form-control" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#ecProductGiftForm").validationEngine();
</script>