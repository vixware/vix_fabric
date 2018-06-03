<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-cube"></i> 门店管理 <span>&gt;业务管理</span> <span>&gt;商品管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('drp_allocationitem', '${nvix}/nvixnt/vixntAllocationItemAction!goStoreItemList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>门店商品列表</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="storeItemForm">
				<input type="hidden" id="storeItemId" name="storeItem.id" value="${storeItem.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
						<div class="col-md-3">
							<input id="storeItemCode" name="storeItem.code" value="${storeItem.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
						<div class="col-md-3">
							<input id="name" name="storeItem.name" value="${storeItem.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 是否PLU商品:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='storeItem.isplu == "T"'>active</s:if>"> <input type="radio" value="T" id="isplu" name="storeItem.isplu" class="validate[required]" <s:if test='storeItem.isplu == "T"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='storeItem.isplu == "F"'>active</s:if>"> <input type="radio" value="F" id="isplu" name="storeItem.isplu" <s:if test='storeItem.isplu == "F"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label"> PLU编码:</label>
						<div class="col-md-3">
							<input id="plu" name="storeItem.plu" value="${storeItem.plu}" data-prompt-position="topLeft" class="form-control validate[custom[phone]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> PLU模式:</label>
						<div class="col-md-3">
							<select id="pluMode" name="storeItem.pluMode" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<option value="10" <s:if test='storeItem.pluMode == "10"'>selected="selected"</s:if>>称重</option>
								<option value="20" <s:if test='storeItem.pluMode == "20"'>selected="selected"</s:if>>计件</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 商品分类:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="itemCategoryTreeMenu" class="input-group">
										<input id="itemCatalogId" name="storeItem.itemCatalog.id" type="hidden" value="${storeItem.itemCatalog.id}" /> <input id="itemCatalogName" name="storeItem.itemCatalog.name" type="text" onfocus="showItemCategory(); return false;" value="${storeItem.itemCatalog.name}" type="text"
											readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#itemCatalogId').val('');$('#itemCatalogName').val('');">清空</button>
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
						<label class="col-md-2 control-label"> 规格型号:</label>
						<div class="col-md-3">
							<input id="specification" name="storeItem.specification" value="${storeItem.specification}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 条形码:</label>
						<div class="col-md-3">
							<input id="barCode" name="storeItem.barCode" value="${storeItem.barCode}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> EAN/UPC:</label>
						<div class="col-md-3">
							<input id="eanupc" name="storeItem.eanupc" value="${storeItem.eanupc}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> SKU码:</label>
						<div class="col-md-3">
							<input id="skuCode" name="storeItem.skuCode" value="${storeItem.skuCode}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 计量单位组:</label>
						<div class="col-md-3">
							<select id="measureUnitGroupId" name="storeItem.measureUnitGroup.id" data-prompt-position="topLeft" class="form-control" onchange="loadItemMeasureUnit();">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitGroupList}" var="entity">
									<option value="${entity.id}" <c:if test="${storeItem.measureUnitGroup.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"> 主计量单位:</label>
						<div class="col-md-3">
							<select id="measureUnitId" name="storeItem.measureUnit.id" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitList}" var="entity">
									<option value="${entity.id}" <c:if test="${storeItem.measureUnit.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>

					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否启用:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='storeItem.status == "T"'>active</s:if>"> <input type="radio" value="T" id="status" name="storeItem.status" class="validate[required]" <s:if test='storeItem.status == "T"'>checked="checked"</s:if>>启用
								</label> <label class="btn btn-default <s:if test='storeItem.status == "F"'>active</s:if>"> <input type="radio" value="F" id="status" name="storeItem.status" <s:if test='storeItem.status == "F"'>checked="checked"</s:if>>禁用
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否折扣:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='storeItem.isDiscount == "T"'>active</s:if>"> <input type="radio" value="T" id="isDiscount" name="storeItem.isDiscount" class="validate[required]" <s:if test='storeItem.isDiscount == "T"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='storeItem.isDiscount == "F"'>active</s:if>"> <input type="radio" value="F" id="isDiscount" name="storeItem.isDiscount" <s:if test='storeItem.isDiscount == "F"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">币种:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="storeItem.currencyType.id" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${storeItem.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>零售价格:</label>
						<div class="col-md-3">
							<input id="price" name="storeItem.price" value="${storeItem.price}" class="form-control validate[required,custom[number]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">采购价格:</label>
						<div class="col-md-3">
							<input id="purchasePrice" name="storeItem.purchasePrice" value="${storeItem.purchasePrice}" class="form-control validate[custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label">会员价:</label>
						<div class="col-md-3">
							<input id="vipPrice" name="storeItem.vipPrice" value="${storeItem.vipPrice}" class="form-control validate[custom[number]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">助记码:</label>
						<div class="col-md-3">
							<input id="pinyin" name="storeItem.pinyin" value="${storeItem.pinyin}" class="form-control validate[custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label">收益科目:</label>
						<div class="col-md-3">
							<input id="revAccountId" name="storeItem.revAccountId" value="${storeItem.revAccountId}" class="form-control validate[custom[number]]" type="text" />
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('drp_allocationitem', '${nvix}/nvixnt/vixntAllocationItemAction!goStoreItemList.action');">
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
	/** 初始化分类下拉列表树 */
	var showItemCategory = initDropListTree("itemCategoryTreeMenu", "${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action", "itemCatalogId", "itemCatalogName", "itemCategoryTree", "itemCategoryMenu");
	//加载主计量单位
	function loadItemMeasureUnit() {
		var mug = $("#measureUnitGroupId").val();
		if (mug != "") {
			$.ajax({
			url : "${nvix}/nvixnt/mdm/nvixntItemAction!loadMeasureUnit.action?type=itemMeasureUnit&id=" + $("#id").val() + "&measureUnitGroupId=" + mug,
			cache : false,
			success : function(html) {
				$("#measureUnitId").html(html);
			}
			});
			$.ajax({
			url : "${nvix}/nvixnt/mdm/nvixntItemAction!loadMeasureUnit.action?type=itemAssitMeasureUnit&id=" + $("#id").val() + "&measureUnitGroupId=" + mug,
			cache : false,
			success : function(html) {
				$("#assitMeasureUnitId").html(html);
			}
			});
		}
	}

	$("#storeItemForm").validationEngine();
	function saveOrUpdate() {
		if ($("#storeItemForm").validationEngine('validate')) {
			$("#storeItemForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntAllocationItemAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('drp_allocationitem', '${nvix}/nvixnt/vixntAllocationItemAction!goStoreItemList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>