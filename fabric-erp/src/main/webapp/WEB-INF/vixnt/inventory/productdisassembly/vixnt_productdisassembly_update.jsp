<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-cube"></i> 商品管理 <span onclick="">&gt; 商品拆装</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('inv_vixntproductdisassembly','${nvix}/nvixnt/vixntProductDisassemblyAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>商品</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="stockRecordsForm">
				<fieldset>
					<input type="hidden" id="id" name="item.id" value="${item.id}" /> <input type="hidden" id="isBinding" name="item.isBinding" value="${item.isBinding}" />
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
										<input id="itemCatalogId" name="item.itemCatalog.id" type="hidden" value="${item.itemCatalog.id}" /> <input id="itemCatalogName" name="item.itemCatalog.name" type="text" onfocus="showItemCategory(); return false;" value="${item.itemCatalog.name}" type="text" readonly="readonly" class="form-control validate[required]" />
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
								<label class="btn btn-default <s:if test='item.status == "T"'>active</s:if>"> <input type="radio" value="T" id="status" name="item.status" class="validate[required]" <s:if test='item.status == "T"'>checked="checked"</s:if>>启用
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
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>商品</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="商品名称" class="form-control" id="searchItemName">
										</div>
										<button onclick="stockRecordLinesTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchItemName').val('');stockRecordLinesTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="goSaveOrUpdateItem('','新增');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="新增明细" />
											</button>
										</div>
									</div>
								</div>
								<table id="stockRecordLinesTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
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
							<button class="btn btn-default" type="button" onclick="loadContent('inv_vixntproductdisassembly','${nvix}/nvixnt/vixntProductDisassemblyAction!goList.action');">
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
	};
	var stockRecordLinesColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "商品编码",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "商品名称",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "单价",
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"title" : "单位",
	"name" : "unit",
	"data" : function(data) {
		if (data.measureUnit != null) {
			return data.measureUnit.name;
		}
		return "";
	}
	}, {
	"title" : "绑定数量",
	"name" : "bindingNum",
	"data" : function(data) {
		return data.bindingNum;
	}
	}, {
	"title" : "操作",
	"width" : "7%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateItem('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var stockRecordLinesTable = initDataTable("stockRecordLinesTableId", "${nvix}/nvixnt/vixntProductDisassemblyAction!goItemList.action", stockRecordLinesColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#id").val();
		var searchItemName = $("#searchItemName").val();
		searchItemName = Url.encode(searchItemName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchItemName" : searchItemName,
		"id" : id
		};
	});

	$("#stockRecordsForm").validationEngine();
	function saveOrUpdate() {
		if ($("#stockRecordsForm").validationEngine('validate')) {
			$("#stockRecordsForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntProductDisassemblyAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntProductDisassemblyAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	function goSaveOrUpdateItem(id, title) {
		var itemId = $("#id").val();
		if(itemId){
			openWindowForShow('${nvix}/nvixnt/vixntProductDisassemblyAction!goSaveOrUpdateItem.action?id=' + id, title, 750, 675);
		}else{
			if ($("#stockRecordsForm").validationEngine('validate')) {
				$("#stockRecordsForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntProductDisassemblyAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#id").val(r[2]);
						openWindowForShow('${nvix}/nvixnt/vixntProductDisassemblyAction!goSaveOrUpdateItem.action?id=' + id, title, 750, 675);
					}else{
						return;
					}
				}
				});
			} else {
				return false;
			}
		}
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntProductDisassemblyAction!deleteById.action?id=' + id, '是否删除商品?', stockRecordLinesTable);
	};
</script>