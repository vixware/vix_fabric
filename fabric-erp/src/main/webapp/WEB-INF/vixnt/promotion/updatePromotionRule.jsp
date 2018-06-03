<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-user"></i> 会员营销 <span>&gt; 促销活动</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdatePromotionRule();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixntPromotionRuleAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>促销规则</h2>
		</header>
		<div class="widget-body">
			<form id="promotionRuleForm" class="form-horizontal" action="${nvix}/nvixnt/nvixntPromotionRuleAction!saveOrUpdate.action">
				<input id="id" name="promotionRule.id" value="${promotionRule.id }" type="hidden" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
						<div class="col-md-3">
							<input id="name" name="promotionRule.name" value="${promotionRule.name}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>促销策略:</label>
						<div class="col-md-3">
							<s:if test="promotionRule.id != null && promotionRule.id != '' && promotionRule.promotionType != ''">
								<input id="promotionType" type="hidden" name="promotionRule.promotionType" value="${promotionRule.promotionType}" />
								<s:if test="promotionRule.promotionType=='PROMOTION_FULLREDUCE'">
									<input type="text" readonly="readonly" value="满减 (整单)" class="form-control" />
								</s:if>
								<s:if test="promotionRule.promotionType=='PROMOTION_FULLGIFT'">
									<input type="text" readonly="readonly" value="满赠 (整单)" class="form-control" />
								</s:if>
								<s:if test="promotionRule.promotionType=='PROMOTION_DOUBLEINTEGRAL'">
									<input type="text" readonly="readonly" value="积分翻倍 (整单)" class="form-control" />
								</s:if>
								<s:if test="promotionRule.promotionType=='PROMOTION_FREEFREIGHT'">
									<input type="text" readonly="readonly" value="免运费 (整单)" class="form-control" />
								</s:if>
								<s:if test="promotionRule.promotionType=='PROMOTION_COUPON'">
									<input type="text" readonly="readonly" value="优惠券(整单)" class="form-control" />
								</s:if>
								<s:if test="promotionRule.promotionType=='PROMOTION_DISCOUNT'">
									<input type="text" readonly="readonly" value="价格折扣(单品)" class="form-control" />
								</s:if>
								<s:if test="promotionRule.promotionType=='PROMOTION_DISCOUNTCB'">
									<input type="text" readonly="readonly" value="价格折扣(全场)" class="form-control" />
								</s:if>
								<s:if test="promotionRule.promotionType=='PROMOTION_PRICE'">
									<input type="text" readonly="readonly" value="促销价(单品)" class="form-control" />
								</s:if>
								<s:if test="promotionRule.promotionType=='PROMOTION_SECONDSALE'">
									<input type="text" readonly="readonly" value="秒杀(单品)" class="form-control" />
								</s:if>
								<s:if test="promotionRule.promotionType=='PROMOTION_SALEGIFT'">
									<input type="text" readonly="readonly" value="买赠(单品)" class="form-control" />
								</s:if>

							</s:if>
							<s:else>
								<select id="promotionType" name="promotionRule.promotionType" onchange="promotionTypeChange();" data-prompt-position="topLeft" class="form-control validate[required]">
									<option value="">----请选择----</option>
									<s:iterator value="promotionTypeMap.entrySet().iterator()" var="key">
										<option value="<s:property value='key'/>" <s:if test="promotionRule.promotionType == key">selected="selected"</s:if>><s:text name="getText(value)" /></option>
									</s:iterator>
								</select>
							</s:else>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
						<div class="col-md-3 inputGroupContainer">
							<div class="input-group">
								<input id="startTime" name="promotionRule.startTime" value="${promotionRule.startTimeStr}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'startTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
						<div class="col-md-3 inputGroupContainer">
							<div class="input-group">
								<input id="endTime" name="promotionRule.endTime" value="${promotionRule.endTimeStr}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'endTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div id="DIV_PROMOTION_DISCOUNTCB" class="form-group" style="display: none;">
						<label name="PROMOTION_DISCOUNTCB" class="col-md-2 control-label"><span class="text-danger">*</span>折扣:</label>
						<div name="PROMOTION_DISCOUNTCB" class="col-md-3">
							<input id="promotionDiscountcb" name="promotionRule.promotionDiscountcb" value="${promotionRule.promotionDiscountcb}" type="text" class="form-control form-control validate[required,custom[number]]" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>状态:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test="promotionRule.status == null || promotionRule.status == 1">active</s:if>"> <input type="radio" id="status1" name="promotionRule.status" data-prompt-position="topLeft" class="validate[required]"
									<s:if test="promotionRule.status == null || promotionRule.status == 1"> checked="checked"</s:if> value="1" />启用
								</label> <label class="btn btn-default <s:if test="promotionRule.status == 0">active</s:if>"> <input type="radio" id="status2" name="promotionRule.status" data-prompt-position="topLeft" class="validate[required]" <s:if test="promotionRule.status == 0"> checked="checked"</s:if> value="0" />禁用
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-3">
							<input id="memo" name="promotionRule.memo" value="${promotionRule.memo}" type="text" class="form-control" />
						</div>
					</div>
					<div id="DIV_WHOLEORDER" class="form-group">
						<label class="col-md-2 control-label">订单总额:</label>
						<div class="col-md-3">
							<input id="orderTotalPrice" name="promotionRule.orderTotalPrice" value="${promotionRule.orderTotalPrice}" type="text" data-prompt-position="topLeft" class="form-control validate[custom[number]]" />
						</div>
						<label name="PROMOTION_FULLREDUCE" class="col-md-2 control-label">满减金额:</label>
						<div name="PROMOTION_FULLREDUCE" class="col-md-3">
							<input id="reducePrice" name="promotionRule.reducePrice" value="${promotionRule.reducePrice}" type="text" data-prompt-position="topLeft" class="form-control validate[custom[number]]" />
						</div>
						<label name="PROMOTION_DOUBLEINTEGRAL" class="col-md-2 control-label">积分倍数:</label>
						<div name="PROMOTION_DOUBLEINTEGRAL" class="col-md-3">
							<input id="scoreDouble" name="promotionRule.scoreDouble" value="${promotionRule.scoreDouble}" type="text" data-prompt-position="topLeft" class="form-control validate[custom[number]]" />
						</div>
					</div>
					<div id="DIV_PROMOTION_COUPON1" class="form-group" style="display: none;">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>优惠券开始时间:</label>
						<div class="col-md-3 inputGroupContainer">
							<div class="input-group">
								<input id="couponStartTime" name="promotionRule.couponStartTime" value="${promotionRule.couponStartTimeStr}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'couponStartTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>优惠券结束时间:</label>
						<div class="col-md-3 inputGroupContainer">
							<div class="input-group">
								<input id="couponEndTime" name="promotionRule.couponEndTime" value="${promotionRule.couponEndTimeStr}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'couponEndTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div id="DIV_PROMOTION_COUPON2" class="form-group" style="display: none;">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>优惠券面值:</label>
						<div class="col-md-3">
							<input id="couponCount" name="promotionRule.couponValue" value="${promotionRule.couponValue}" type="text" data-prompt-position="topLeft" class="form-control validate[required,custom[integer],min[1]]" />
							<%-- <div class="input-group">
								<select id="couponValueId" name="promotionRule.couponValue" data-prompt-position="topLeft" class="form-control validate[required]" >
									<option value="">------请选择------</option>
									<c:forEach items="${couponValueList}" var="entity">
										 <option value="${entity.value}" <c:if test="${promotionRule.couponValue == entity.value}">selected="selected"</c:if>>${entity.value}</option>
									</c:forEach>
								</select>
								<span class="input-group-addon" style="cursor:pointer;" onclick="addCouponValue();">新增</span>
							</div> --%>
						</div>
						<label class="col-md-2 control-label">优惠券数量:</label>
						<div class="col-md-3">
							<input id="couponCount" name="promotionRule.couponCount" value="${promotionRule.couponCount}" type="text" data-prompt-position="topLeft" class="form-control validate[required,custom[integer],min[1]]" />
						</div>
					</div>
					<div id="DIV_PROMOTION_FULLGIFT" class="form-group">
						<label class="col-md-2 control-label"></label>
						<div class="col-md-8">
							<div class="jarviswidget jarviswidget-color-white">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i>
									</span>
									<h2>满赠赠品列表</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin: 5px;">
											<div class="form-group" style="margin: 0 5px;">
												<div class="col-md-3">
													<input type="text" value="" placeholder="商品名称" class="form-control" id="fullGiftSearchName">
												</div>

												<button onclick="ecProductGiftTable.ajax.reload();" type="button" class="btn btn-info">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#fullGiftSearchName').val('');ecProductGiftTable.ajax.reload();" type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>

												<div class="listMenu-float-right">
													<button onclick="saveOrUpdateEcProductGiftForPR('');" type="button" class="btn btn-primary">
														<i class="glyphicon glyphicon-plus"></i>
														<s:text name="add" />
													</button>
												</div>
											</div>
										</div>
										<table id="ecProductGift" class="table table-striped table-bordered table-hover" width="100%">
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
					<div id="DIV_PromotionRuleDetail" class="form-group">
						<label class="col-md-2 control-label"></label>
						<div class="col-md-8">
							<div class="jarviswidget jarviswidget-color-white">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i>
									</span>
									<h2>促销商品列表</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin: 5px;">
											<div class="form-group" style="margin: 0 5px;">
												<div class="col-md-3">
													<input type="text" value="" placeholder="商品名称" class="form-control" id="promotionRuleDetailSearchName">
												</div>

												<button onclick="promotionRuleDetailTable.ajax.reload();" type="button" class="btn btn-info">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#promotionRuleDetailSearchName').val('');promotionRuleDetailTable.ajax.reload();" type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>

												<div class=" listMenu-float-right">
													<button onclick="saveOrUpdatePromotionRuleDetail('');" type="button" class="btn btn-primary">
														<i class="glyphicon glyphicon-plus"></i>
														<s:text name="add" />
													</button>
												</div>
											</div>
										</div>
										<table id="promotionRuleDetail" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th width="8%">编号</th>
													<th width="20%">商品</th>
													<th width="10%">销售价格</th>
													<th width="10%">促销价格</th>
													<th width="10%">备注</th>
													<th width="10%">操作</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdatePromotionRule();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixntPromotionRuleAction!goList.action');">
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
	$("#promotionRuleForm").validationEngine();
	// 保存促销活动
	function saveOrUpdatePromotionRule() {
		var tag2 = true;
		var promotionType = $("#promotionType").val();
		if (promotionType == 'PROMOTION_COUPON') {
			tag2 = couponDateRange();
		}
		var tag1 = dateRange();
		if (!tag1) {
			layer.alert("结束时间不能小于开始时间!");
		} else if (!tag2) {
			layer.alert("优惠券结束时间不能小于开始时间!");
		} else {
			if ($('#promotionRuleForm').validationEngine('validate')) {
				$("#promotionRuleForm").ajaxSubmit({
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					showMessage(result);
					//loadRightContent('','${snow}/ec/activity/promotionRuleAction!goList.action','电子商务','活动管理','促销活动');
					loadContent('', '${nvix}/nvixnt/nvixntPromotionRuleAction!goList.action');
				}
				});
			}
		}
	}
	// 满赠赠品列表
	var ecProductGiftColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"name" : "item.name",
	"data" : function(data) {
		return data.itemName;
	}
	}, {
	"name" : "giftCount",
	"data" : function(data) {
		return data.giftCount;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.memo;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdateEcProductGiftForPR('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteEcProductGiftById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];
	var ecProductGiftTable = initDataTable("ecProductGift", "${nvix}/nvixnt/nvixntItemGiftAction!goSingleListJson.action", ecProductGiftColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#fullGiftSearchName").val();
		name = Url.encode(name);
		var promotionRuleId = $("#id").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"promotionRuleId" : promotionRuleId,
		"type" : "pr",
		"name" : name
		};
	});
	/** 保存满赠赠品  */
	function saveOrUpdateEcProductGiftForPR(id) {
		var tag = dateRange();
		if (!tag) {
			layer.alert("结束时间不能小于开始时间!");
		} else {
			var promotionRuleId = $("#id").val();
			if (promotionRuleId == '') {
				if ($('#promotionRuleForm').validationEngine('validate')) {
					$("#promotionRuleForm").attr("action", "${nvix}/nvixnt/nvixntPromotionRuleAction!saveOrUpdateInner.action");
					$("#promotionRuleForm").ajaxSubmit({
					type : "post",
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					success : function(result) {
						var r = result.split(",");
						if (r[0] == "0") {
							showMessage(r[1]);
						} else {
							promotionRuleId = $.trim(r[0]);
							$("#id").val(promotionRuleId);
							$("#promotionRuleForm").attr("action", "${nvix}/nvixnt/nvixntPromotionRuleAction!saveOrUpdate.action");
							openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntItemGiftAction!goSaveOrUpdate.action?id=' + id + "&promotionRuleId=" + promotionRuleId, "ecProductGiftForm", "满赠赠品信息", 480, 400, ecProductGiftTable, function() {
								$("#promotionRuleId").val(promotionRuleId);
							});
						}

					}
					});
				}
			} else {
				openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntItemGiftAction!goSaveOrUpdate.action?id=' + id + "&promotionRuleId=" + promotionRuleId, "ecProductGiftForm", "满赠赠品信息", 480, 400, ecProductGiftTable, function() {
					$("#promotionRuleId").val($("#id").val());
				});
			}
		}
	}
	// 时间校验函数
	function dateRange() {
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		return dateTimeRange(startTime, endTime);
	}
	function couponDateRange() {
		var couponStartTime = $("#couponStartTime").val();
		var couponEndTime = $("#couponEndTime").val();
		return dateTimeRange(couponStartTime, couponEndTime);
	}

	function deleteEcProductGiftById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntItemGiftAction!deleteById.action?id=' + id, '是否删除满赠赠品信息?', ecProductGiftTable);
	}

	// 促销商品列表
	var promotionRuleDetailColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"name" : "item.name",
	"data" : function(data) {
		return data.itemName;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.skuPrice > 0) {
			return data.skuPrice;
		} else {
			return data.itemPrice;
		}
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.promotionRulePromotionType == 'PROMOTION_SECONDSALE') {
			return data.promotionPrice;
		} else if (data.promotionRulePromotionType == 'PROMOTION_PRICE') {
			return data.promotionPrice;
		} else if (data.promotionRulePromotionType == 'PROMOTION_DISCOUNT') {
			if (data.skuPrice > 0) {
				return data.skuPrice * data.promotionDiscount;
			} else {
				return data.ecProduct.price * data.promotionDiscount;
			}
		} else if (data.promotionRulePromotionType == 'PROMOTION_SALEGIFT') {
			return "买赠";
		}
		return "";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.memo;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdatePromotionRuleDetail('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletePromotionRuleById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];
	var promotionRuleDetailTable = initDataTable("promotionRuleDetail", "${nvix}/nvixnt/nvixntPromotionRuleDetailAction!goSingleListJson.action", promotionRuleDetailColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#promotionRuleDetailSearchName").val();
		name = Url.encode(name);
		var promotionRuleId = $("#id").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"promotionRuleId" : promotionRuleId,
		"name" : name
		};
	});
	/** 保存促销商品  */
	function saveOrUpdatePromotionRuleDetail(id) {
		var tag = dateRange();
		if (!tag) {
			layer.alert("结束时间不能小于开始时间!");
		} else {
			var promotionRuleId = $("#id").val();
			if (promotionRuleId == '') {
				if ($('#promotionRuleForm').validationEngine('validate')) {
					$("#promotionRuleForm").attr("action", "${nvix}/nvixnt/nvixntPromotionRuleAction!saveOrUpdateInner.action");
					$("#promotionRuleForm").ajaxSubmit({
					type : "post",
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					success : function(result) {
						var r = result.split(",");
						if (r[0] == "0") {
							showMessage(r[1]);
						} else {
							promotionRuleId = $.trim(r[0]);
							$("#id").val(promotionRuleId);
							$("#promotionRuleForm").attr("action", "${nvix}/nvixnt/nvixntPromotionRuleAction!saveOrUpdate.action");
							openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntPromotionRuleDetailAction!goSaveOrUpdate.action?id=' + id, "promotionRuleDetailForm", "促销商品信息", 720, 500, promotionRuleDetailTable, function() {
								$("#promotionRuleId").val(promotionRuleId);
							});
						}

					}
					});
				}
			} else {
				openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntPromotionRuleDetailAction!goSaveOrUpdate.action?id=' + id, "promotionRuleDetailForm", "促销商品信息", 720, 500, promotionRuleDetailTable, function() {
					$("#promotionRuleId").val(promotionRuleId);
				});
			}
		}
	}

	function deletePromotionRuleById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntPromotionRuleDetailAction!deleteById.action?id=' + id, '是否删除促销商品信息?', promotionRuleDetailTable);
	}

	/** 隐藏所有促销条件的相关字段 */
	function hiddenAllPromotionTypeCondition() {
		$("#DIV_WHOLEORDER").attr("style", "display: none;");
		$("[name='PROMOTION_FULLREDUCE']").attr("style", "display: none;");
		$("#DIV_PROMOTION_FULLGIFT").attr("style", "display: none;");
		$("[name='PROMOTION_DOUBLEINTEGRAL']").attr("style", "display: none;");
		$("#DIV_PROMOTION_COUPON1").attr("style", "display: none;");
		$("#DIV_PROMOTION_COUPON2").attr("style", "display: none;");
		$("#DIV_PromotionRuleDetail").attr("style", "display: none;");
		$("#DIV_PROMOTION_DISCOUNTCB").attr("style", "display: none;");
	}
	function promotionTypeChange() {
		hiddenAllPromotionTypeCondition();
		var pt = $("#promotionType").val();
		/** 满减 */
		if (pt == 'PROMOTION_FULLREDUCE') {
			$("#DIV_WHOLEORDER").attr("style", "");
			$("[name='PROMOTION_FULLREDUCE']").attr("style", "");
		}
		if (pt == 'PROMOTION_DISCOUNTCB') {
			$("#DIV_PROMOTION_DISCOUNTCB").attr("style", "");
			$("[name='PROMOTION_DISCOUNTCB']").attr("style", "");
		}
		/** 满赠 */
		if (pt == 'PROMOTION_FULLGIFT') {
			$("#DIV_WHOLEORDER").attr("style", "");
			$("#DIV_PROMOTION_FULLGIFT").attr("style", "");
			/** 加载满赠列表 */
			ecProductGiftTable.ajax.reload();
		}
		/** 积分翻倍 */
		if (pt == 'PROMOTION_DOUBLEINTEGRAL') {
			$("#DIV_WHOLEORDER").attr("style", "");
			$("[name='PROMOTION_DOUBLEINTEGRAL']").attr("style", "");
		}
		/** 优惠券 */
		if (pt == 'PROMOTION_COUPON') {
			$("#DIV_WHOLEORDER").attr("style", "");
			$("#DIV_PROMOTION_COUPON1").attr("style", "");
			$("#DIV_PROMOTION_COUPON2").attr("style", "");
		}
		/** 免运费 */
		if (pt == 'PROMOTION_FREEFREIGHT') {
			$("#DIV_WHOLEORDER").attr("style", "");
		}
		/** 单品 */
		if (pt == 'PROMOTION_PRICE' || pt == 'PROMOTION_DISCOUNT' || pt == 'PROMOTION_SALEGIFT' || pt == 'PROMOTION_SECONDSALE' || pt == 'PROMOTION_DISCOUNT') {
			$("#DIV_PromotionRuleDetail").attr("style", "");
			/** 加载促销商品列表 */
			promotionRuleDetailTable.ajax.reload();
		}
	}

	/** 添加面值 */
	/* function addCouponValue(){
		openSaveOrUpdateWindow('${snow}/ec/activity/couponValueAction!goSaveOrUpdate.action?id=',"couponValueForm","优惠券面值",480,240,null,null,function(){
		$.ajax({
			  url:'${snow}/ec/activity/couponValueAction!loadCouponValueOption.action',
			  cache: false,
			  success: function(html){
				  var couponValueId = $("#couponValueId").val();
				  $("#couponValueId").html(html);
				  if(couponValueId != ''){
					  $("#couponValueId").val(couponValueId);
				  }
			  }
		});
	});
	} */

	promotionTypeChange();
</script>