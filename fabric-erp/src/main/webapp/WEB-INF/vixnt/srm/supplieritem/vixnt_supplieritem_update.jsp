<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 供应商管理<span>> 商品管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('drp_allocationitem', '${nvix}/nvixnt/vixntSupplierItemAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>供应商</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="storeItemForm">
				<input type="hidden" id="storeItemId" name="item.id" value="${item.id}" /> <input type="hidden" id="imagesId" name="imagesId" value="${imagesId}" /> <input type="hidden" id="imageFilePath" name="item.imageFilePath" value="${item.imageFilePath}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
						<div class="col-md-3">
							<input id="storeItemCode" name="item.code" value="${item.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
						<div class="col-md-3">
							<input id="name" name="item.name" value="${item.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>商品分类:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="itemCategoryTreeMenu" class="input-group">
										<input id="itemCatalogId" name="item.itemCatalog.id" type="hidden" value="${item.itemCatalog.id}" /> <input id="itemCatalogName" name="item.itemCatalog.name" type="text" onfocus="showItemCategory(); return false;" value="${item.itemCatalog.name}" readonly="readonly"
											class="form-control validate[required]" />
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
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否PLU商品:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='item.isplu == "T"'>active</s:if>"> <input type="radio" value="T" id="isplu" name="item.isplu" class="validate[required]" <s:if test='item.isplu == "T"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='item.isplu == "F"'>active</s:if>"> <input type="radio" value="F" id="isplu" name="item.isplu" <s:if test='item.isplu == "F"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">PLU编码:</label>
						<div class="col-md-3">
							<input id="plu" name="item.plu" value="${item.plu}" data-prompt-position="topLeft" class="form-control validate[custom[phone]]" type="text" />
						</div>
						<label class="col-md-2 control-label"> PLU模式:</label>
						<div class="col-md-3">
							<select id="pluMode" name="item.pluMode" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<option value="10" <s:if test='item.pluMode == "10"'>selected="selected"</s:if>>称重</option>
								<option value="20" <s:if test='item.pluMode == "20"'>selected="selected"</s:if>>计件</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">规格型号:</label>
						<div class="col-md-3">
							<input id="specification" name="item.specification" value="${item.specification}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">条形码:</label>
						<div class="col-md-3">
							<input id="barCode" name="item.barCode" value="${item.barCode}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">EAN/UPC:</label>
						<div class="col-md-3">
							<input id="eanupc" name="item.eanupc" value="${item.eanupc}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">SKU码:</label>
						<div class="col-md-3">
							<input id="skuCode" name="item.skuCode" value="${item.skuCode}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 计量单位组:</label>
						<div class="col-md-3">
							<select id="measureUnitGroupId" name="item.measureUnitGroup.id" data-prompt-position="topLeft" class="form-control validate[required]" onchange="loadItemMeasureUnit();">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitGroupList}" var="entity">
									<option value="${entity.id}" <c:if test="${item.measureUnitGroup.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主计量单位:</label>
						<div class="col-md-3">
							<select id="measureUnitId" name="item.measureUnit.id" data-prompt-position="topLeft" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitList}" var="entity">
									<option value="${entity.id}" <c:if test="${item.measureUnit.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='item.status == "T"'>active</s:if>" disabled="disabled"> <input type="radio" value="T" id="status" name="item.status" class="validate[required]" <s:if test='item.status == "T"'>checked="checked"</s:if>>启用
								</label> <label class="btn btn-default <s:if test='item.status == "F"'>active</s:if>"> <input type="radio" value="F" id="status" name="item.status" <s:if test='item.status == "F"'>checked="checked"</s:if>>禁用
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否折扣:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='item.isDiscount == "T"'>active</s:if>"> <input type="radio" value="T" id="isDiscount" name="item.isDiscount" class="validate[required]" <s:if test='item.isDiscount == "T"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='item.isDiscount == "F"'>active</s:if>"> <input type="radio" value="F" id="isDiscount" name="item.isDiscount" <s:if test='item.isDiscount == "F"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>币种:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="item.currencyType.id" data-prompt-position="topLeft" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${item.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>零售价格:</label>
						<div class="col-md-3">
							<input id="price" name="item.price" value="${item.price}" class="form-control validate[required,custom[number]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>采购价格:</label>
						<div class="col-md-3">
							<input id="purchasePrice" name="item.purchasePrice" value="${item.purchasePrice}" class="form-control validate[custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>会员价:</label>
						<div class="col-md-3">
							<input id="vipPrice" name="item.vipPrice" value="${item.vipPrice}" class="form-control validate[custom[number]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>商品主图:</label>
						<div class="col-md-3">
							<div style="float: left; display: inline;">
								<img id="mainPic" onerror="$('#mainPic').attr('src','${nvix}/vixntcommon/base/img/demo/64x64.png')" src="${item.imageFilePath}" width="30px" height="30px" onmouseover="$('#mainPicBig').attr('style','position: absolute;padding:-50px;z-index:9999;');"
									onmouseout="$('#mainPicBig').attr('style','display:none;');" /> <img id="mainPicBig" onerror="$('#mainPicBig').attr('src','${nvix}/vixntcommon/base/img/demo/64x64.png')" src="${item.imageFilePath}" width="100" height="100" style="display: none; position: absolute;" /> &nbsp;&nbsp;
							</div>
							<input type="file" id="fileToUpload" name="fileToUpload" onchange="imageChange();" style="width: 80%;">
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('drp_vixntsupplieritem', '${nvix}/nvixnt/vixntSupplierItemAction!goList.action');">
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
			url : "${nvix}/nvixnt/vixntSupplierItemAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntSupplierItemAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};

	function imageChange() {
		uploadFileOrImage("itemImageForm", "${nvix}/nvixnt/vixntSupplierItemAction!saveOrUpdatePicture.action", "fileToUpload", "image", function(data) {
			var d = data.split(",");
			if (d[0] == '0') {
				showMessage(d[1]);
			} else {
				$('#imagesId').val(d[0]);
				$('#imageFilePath').val(d[1]);
				$("#mainPic").attr("src", d[1]);
				$("#mainPicBig").attr("src", d[1]);
				showMessage("图片上传成功!");
			}
		});
	};
</script>