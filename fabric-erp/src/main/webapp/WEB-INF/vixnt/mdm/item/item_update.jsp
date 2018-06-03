<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 商品管理 <span>&gt; 新增商品</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/mdm/nvixntItemAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>商品信息</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="itemsForm">
				<input type="hidden" id="ecConfig_enableSpecification" name="enableSpecification" value="<s:property value="loadEcConfig('ecConfig_enableSpecification')"/>"/>
				<input type="hidden" id="id" name="item.id" value="${item.id}" /> <input type="hidden" id="itemPurchasePropertiesId" name="itemPurchaseProperties.id" value="${itemPurchaseProperties.id}" /> <input type="hidden" id="itemInventoryPropertiesId" name="itemInventoryProperties.id" value="${itemInventoryProperties.id}" />
				<fieldset>
					<legend>基本信息:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
						<div class="col-md-3">
							<input id="itemCode" name="item.code" value="${item.code}" class="form-control validate[required]" type="text" onchange="checkItemCode();" <s:if test='item.id != ""'>readonly</s:if> />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
						<div class="col-md-3">
							<input id="name" name="item.name" value="${item.name}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<s:if test="inventoryParameters.enablePos == 1">
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否常用商品:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='item.isCommon == "X"'>active</s:if>"> <input type="radio" value="X" id="isCommon" name="item.isCommon" class="validate[required]" <s:if test='item.isCommon == "X"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='item.isCommon == ""'>active</s:if>"> <input type="radio" value="" id="isCommon" name="item.isCommon" <s:if test='item.isCommon == ""'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否PLU商品:</label>
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
							<input id="plu" name="item.plu" value="${item.plu}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">PLU模式:</label>
						<div class="col-md-3">
							<select id="pluMode" name="item.pluMode" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<option value="10" <s:if test='item.pluMode == "10"'>selected="selected"</s:if>>称重</option>
								<option value="20" <s:if test='item.pluMode == "20"'>selected="selected"</s:if>>计件</option>
							</select>
						</div>
					</div>
					</s:if>
					<div class="form-group">
						<label class="col-md-2 control-label">${vv:varView('vix_mdm_item')}类型:</label>
						<div class="col-md-3">
							<select id="objectExpandId" name="item.objectExpand.id" data-prompt-position="topLeft" class="form-control" onchange="objectExpandChange($(this).val());">
								<option value="">------请选择------</option>
								<c:forEach items="${objectExpandList}" var="entity">
									<option value="${entity.id}" <c:if test="${item.objectExpand.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}分类:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="itemCategoryTreeMenu" class="input-group">
										<input id="itemCatalogIds" name="item.itemCatalog.id" type="hidden" value="${item.itemCatalog.id}" /> <input id="itemCatalogNames" name="item.itemCatalog.name" type="text" onfocus="showItemCategory(); return false;" value="${item.itemCatalog.name}" type="text" readonly="readonly" class="form-control validate[required]" />
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
						<label class="col-md-2 control-label">${vv:varView('vix_mdm_item')}品牌:</label>
						<div class="col-md-3">
							<select id="itemBrandId" name="item.itemBrand.id" data-prompt-position="topLeft" class="form-control" onclick="setBarndName();">
								<option value="">------请选择------</option>
								<c:forEach items="${itemBrandList}" var="entity">
									<option value="${entity.id}" <c:if test="${item.itemBrand.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">规格型号:</label>
						<div class="col-md-3">
							<input id="specification" name="item.specification" value="${item.specification}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">条形码:</label>
						<div class="col-md-3">
							<input id="barCode" name="item.barCode" value="${item.barCode}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">EAN/UPC:</label>
						<div class="col-md-3">
							<input id="eanupc" name="item.eanupc" value="${item.eanupc}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">SKU码:</label>
						<div class="col-md-3">
							<input id="skuCode" name="item.skuCode" value="${item.skuCode}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>计量单位组:</label>
						<div class="col-md-3">
							<select id="measureUnitGroupId" name="item.measureUnitGroup.id" data-prompt-position="topLeft" class="form-control validate[required]" onchange="loadItemMeasureUnit();">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitGroupList}" var="entity">
									<option value="${entity.id}" <c:if test="${item.measureUnitGroup.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>

					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>主计量单位:</label>
						<div class="col-md-3">
							<select id="measureUnitId" name="item.measureUnit.id" data-prompt-position="topLeft" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitList}" var="entity">
									<option value="${entity.id}" <c:if test="${item.measureUnit.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">辅助计量单位:</label>
						<div class="col-md-3">
							<select id="assitMeasureUnitId" name="item.assitMeasureUnit.id" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitList}" var="entity">
									<option value="${entity.id}" <c:if test="${item.assitMeasureUnit.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">快捷码:</label>
						<div class="col-md-3">
							<input id="fastCode" name="item.fastCode" value="${item.fastCode}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='item.status == "T"'>active</s:if>"> <input type="radio" value="T" id="status" name="item.status" class="validate[required]" <s:if test='item.status == "T"'>checked="checked"</s:if>>启用
								</label> <label class="btn btn-default <s:if test='item.status == "F"'>active</s:if>"> <input type="radio" value="F" id="status" name="item.status" <s:if test='item.status == "F"'>checked="checked"</s:if>>禁用
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">搜索项1:</label>
						<div class="col-md-3">
							<input id="searchText1" name="item.searchText1" value="${item.searchText1}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">搜索项2:</label>
						<div class="col-md-3">
							<input id="searchText2" name="item.searchText2" value="${item.searchText2}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否折扣:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='item.isDiscount == "T"'>active</s:if>"> <input type="radio" value="T" id="isDiscount" name="item.isDiscount" class="validate[required]" <s:if test='item.isDiscount == "T"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='item.isDiscount == "F"'>active</s:if>"> <input type="radio" value="F" id="isDiscount" name="item.isDiscount" <s:if test='item.isDiscount == "F"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
						<s:if test="inventoryParameters.enablePos == 1">
							<label class="col-md-2 control-label">收益科目:</label>
							<div class="col-md-3">
								<input id="revAccountId" name="item.revAccountId" value="${item.revAccountId}" class="form-control" type="text" />
							</div>
						</s:if>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">服务内容:</label>
						<div class="col-md-3">
							<input id="serviceContent" name="item.serviceContent" value="${item.serviceContent}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">商品主图:</label>
						<div class="col-md-3">
							<input type="hidden" id="itemImgPath" name="item.imageFilePath" value="${item.imageFilePath}"/>
							<div style="float:left; display:inline;">
								<img id="itemBigImage" onerror="$('#itemBigImage').attr('src','${nvix}/common/img/default.png')" src="${item.imageFilePath}" width="30px" height="30px" onmouseover="$('#itemBigImageShow').attr('style','position: absolute;padding:-50px;z-index:9999;');" onmouseout="$('#itemBigImageShow').attr('style','display:none;');"/>
								<img id="itemBigImageShow" onerror="$('#itemBigImageShow').attr('src','${nvix}/common/img/default.png')" src="${item.imageFilePath}" width="100" height="100" style="display:none;position: absolute;"/>
								&nbsp;
							</div>
							<input type="file" id="itemImageFileToUpload" name="fileToUpload" onchange="fileToChange();" style="width:65%;">
						</div>	
					</div>
					<%-- <div class="form-group">
						<label class="col-md-2 control-label"></label> <input type="hidden" id="description" name="item.description" value="${item.description}" />
						<div class="col-md-8">
							<div class="jarviswidget jarviswidget-color-blue" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-fullscreenbutton="false" data-widget-sortable="false">
								<header>
									<span class="widget-icon"><i class="fa fa-pencil"></i></span>
									<h2>产品描述</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox"></div>
									<div class="widget-body no-padding">
										<div id="" class="summernote">${item.description}</div>
										<div class="widget-footer smart-form">
											<div class="btn-group">
												<button class="btn btn-sm btn-default" type="button" onclick="$('#description').html();">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div> --%>
					<div class="form-group">
					<label class="col-md-2 control-label">商品描述:</label>
						<div class="col-md-8">
						  <textarea id="description" name="item.description">${item.description}</textarea>
						  <script type="text/javascript" src="${nvix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
						        var editor = KindEditor.create('#description',
										{
											width:730,
											height:300,
											cssPath : '${nvix}/plugin/KindEditor/plugins/code/prettify.css',
											uploadJson : '${nvix}/nvixnt/hr/nvixRecruitStaffAction!uploadFile.action',
											allowFileManager : true 
										}
									);
						  </script>
					  </div>
					</div>
			<s:if test="inventoryParameters.enableSpecification == 1">		
			<s:if test="specificationList.size > 0">
				<legend>商品规格:</legend>
				<fieldset>
					<input type="hidden" id="specificationDetail" name="specificationDetail" value=""/>
					<s:iterator var="specification" value="specificationList">
						<div class="form-group">
							<label class="col-md-2 control-label" id="specName_${specification.id}" data-spec="${specification.name}">${specification.name}:</label>
							<div class="col-md-8">
								<div data-toggle="buttons" class="btn-group">
									<s:iterator var="specificationDetail" value="#specification.specificationDetails">
										<label class="btn btn-default">
											<input type="checkbox" id="specification_${specification.id}" name="specificationDetail_${specification.id}_${specificationDetail.id}" 
												value="${specificationDetail.id}"/>${specificationDetail.name}
										</label>
									</s:iterator>
								</div>
							</div>
						</div>
					</s:iterator>
					<div class="form-group">
						<label class="col-md-2">&nbsp;</label>
						<div class="col-md-8 no-padding">
							<div class="jarviswidget jarviswidget-color-white">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>规格列表</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin:5px;">
											<div class="form-group" style="margin: 0 5px;">
												<div class="col-md-3">
													<input type="text" value="" placeholder="SKU" class="form-control" id="specSearchForSku">
												</div>
												<button onclick="specificationDataTable.ajax.reload();" type="button" class="btn btn-info">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#specSearchForSku').val('');specificationDataTable.ajax.reload();" type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
												<div class="listMenu-float-right">
													<button onclick="generateSpecification();" type="button" class="btn btn-primary">
														<i class="glyphicon glyphicon-plus"></i>创建规格
													</button>
												</div>
											</div>
										</div>
									    <table id="specificationDataTable" class="table table-striped table-bordered table-hover" width="100%"></table>
								 	</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				</s:if>
				</s:if>
				<legend>商品图片:</legend>	
				<fieldset>
					<div class="form-group">
						<label class="col-md-2">&nbsp;</label>
						<div class="col-md-8 no-padding">
							<div class="jarviswidget jarviswidget-color-white">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>商品图片信息</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin:5px;">
											<div class="form-group" style="margin: 0 5px;">
												<label class="col-md-1 control-label" style="width:70px;">备注:</label>
												<div class="col-md-3">
													<input type="text" value="" placeholder="备注" class="form-control" id="searchEcProductImageMemo">
												</div>
												<button onclick="ecProductImageDataTable.ajax.reload();" type="button" class="btn btn-info">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchEcProductImageMemo').val('');ecProductImageDataTable.ajax.reload();" type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
												<div class="listMenu-float-right">
													<button onclick="saveOrUpdateEcProductImage('');" type="button" class="btn btn-primary">
														<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
													</button>
												</div>
											</div>
										</div>
									    <table id="ecProductImageDataTable" class="table table-striped table-bordered table-hover" width="100%">
									   		<thead>			                
												<tr>
													<th width="8%">编号</th>
													<th width="45%">名称</th>
													<th width="8%">顺序</th>
													<th width="30%">备注</th>
													<th width="8%">操作</th>
												</tr>
											</thead>
									    </table>
								 	</div>
								</div>
							</div>
						</div>
					</div>
			</fieldset>	
					
					<%-- <div class="form-group">
				<label class="col-md-2 control-label">附件：</label>
				<div class="col-md-8">
					<div class="jarviswidget jarviswidget-color-darken">
						<header style="height: 1px; border-color: #ddd !important"></header>
						<div>
							<div id="salesOrderRightContent" class="widget-body no-padding">
								<div class="jarviswidget" id="salesOrderDetailTabs" style="margin: 0; padding: 12px 12px 12px 12px;" data-widget-togglebutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
									<header>
										<ul class="nav nav-tabs pull-left in">
											<li class="active"><a data-toggle="tab" href="#orderDetailTab" onclick="orderDetailTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">本地上传</span>
											</a></li>
											<li><a data-toggle="tab" href="#orderDeliveryPlanTab" onclick="orderDeliveryPlanTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">视频中心</span>
											</a></li>
										</ul>
									</header>
									<div class="widget-body" style="padding: 0;">
										<div class="tab-content">
											<div class="tab-pane no-padding active" id="orderDetailTab">
												<div id="orderDetailSearchForm" style="margin: 5px;">
													<div class="form-group" style="margin: 0 5px;">
														<div class="col-md-3">
															<input type="text" value="" id="orderDetailSearchName" placeholder="名称" class="form-control" />
														</div>
														<button onclick="orderDetailTable.ajax.reload();" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="clearSearchCondition('orderDetailSearchName',orderDetailTable);" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
														<div class=" listMenu-float-right">
															<button onclick="checkSalesOrderisSave('items');" type="button" class="btn btn-primary">
																<i class="glyphicon glyphicon-plus"></i>
																<s:text name="add" />
															</button>
														</div>
													</div>
												</div>
												<table id="orderDetail" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
											<div class="tab-pane no-padding" id="orderDeliveryPlanTab">
												<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
													<div class="form-group" style="margin: 0 5px;">
														<div class="col-md-3">
															<input type="text" value="" id="orderDeliveryPlanSearchName" placeholder="名称" class="form-control" />
														</div>
														<button onclick="orderDeliveryPlanTable.ajax.reload();" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="clearSearchCondition('orderDeliveryPlanSearchName',orderDeliveryPlanTable);" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
														<div class=" listMenu-float-right">
															<button onclick="checkSalesOrderisSave('plan');" type="button" class="btn btn-primary">
																<i class="glyphicon glyphicon-plus"></i>
																<s:text name="add" />
															</button>
														</div>
													</div>
												</div>
												<table id="orderDeliveryPlan" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div> --%>
					<legend>税与价格:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label">币种:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="item.currencyType.id" data-prompt-position="topLeft" class="form-control">
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${item.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">利润率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="profitMargin" value="${item.profitMargin}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" /> <span class="input-group-addon"><i class="fa">(1-100)%</i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>零售价格:</label>
						<div class="col-md-3">
							<input id="price" name="item.price" value="${item.price}" class="form-control validate[required,custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label">直接采购价格:</label>
						<div class="col-md-3">
							<input id="purchasePrice" name="item.purchasePrice" value="${item.purchasePrice}" class="form-control validate[custom[number]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">成本价:</label>
						<div class="col-md-3">
							<input id="costPrice" name="item.costPrice" value="${item.costPrice}" class="form-control validate[custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label">进项税率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="inTax" name="item.inTax" value="${item.inTax}" data-prompt-position="topLeft" class="form-control validate[custom[number]]" type="text" /> <span class="input-group-addon"><i class="fa">%</i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">会员价:</label>
						<div class="col-md-3">
							<input id="vipPrice" name="item.vipPrice" value="${item.vipPrice}" class="form-control validate[custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label">销售税率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="saleTax" name="item.saleTax" value="${item.saleTax}" data-prompt-position="topLeft" class="form-control validate[custom[number]]" type="text" /> <span class="input-group-addon"><i class="fa">(1-100)%</i></span>
							</div>
						</div>
					</div>
					<%-- <h5>价格预警</h5>
			<div class="form-group">
				<label class="col-md-2 control-label">预警中心:</label>
				<div class="col-md-8">
					<div class="jarviswidget jarviswidget-color-darken">
						<header style="height: 1px; border-color: #ddd !important"></header>
						<div>
							<div id="rightContent" class="widget-body no-padding">
								<div class="jarviswidget" id="detailTabs" style="margin: 0; padding: 12px 12px 12px 12px;" data-widget-togglebutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
									<header>
										<ul class="nav nav-tabs pull-left in">
											<li class="active"><a data-toggle="tab" href="#orderInvoiceTab" onclick="orderInvoiceTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">采购价格</span>
											</a></li>
											<li><a data-toggle="tab" href="#accessoriesTab" onclick="accessoriesTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">零售价格</span>
											</a></li>
										</ul>
									</header>
									<div class="widget-body" style="padding: 0;">
										<div class="tab-content">
											<div class="tab-pane no-padding active" id="orderInvoiceTab">
												<div id="orderInvoiceSearchForm" style="margin: 5px;">
													<div class="form-group" style="margin: 0 5px;">
														<div class="col-md-3">
															<input type="text" value="" id="orderInvoiceSearchName" placeholder="发票抬头" class="form-control" />
														</div>
														<button onclick="orderInvoiceTable.ajax.reload();" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="clearSearchCondition('orderInvoiceSearchName',orderInvoiceTable);" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
														<div class=" listMenu-float-right">
															<button onclick="checkSalesOrderisSave('invoice');" type="button" class="btn btn-primary">
																<i class="glyphicon glyphicon-plus"></i>
																<s:text name="add" />
															</button>
														</div>
													</div>
												</div>
												<table id="orderInvoice" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
											<div class="tab-pane no-padding" id="accessoriesTab">
												<div id="accessoriesSearchForm" style="margin: 5px;">
													<div class="form-group" style="margin: 0 5px;">
														<div class="col-md-3">
															<input type="text" value="" id="accessoriesSearchName" placeholder="附件名称" class="form-control" />
														</div>
														<button onclick="accessoriesTable.ajax.reload();" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="clearSearchCondition('accessoriesSearchName',accessoriesTable);" type="button" class="btn btn-primary">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
														<div class=" listMenu-float-right">
															<button onclick="checkSalesOrderisSave('accessories');" type="button" class="btn btn-primary">
																<i class="glyphicon glyphicon-plus"></i>
																<s:text name="add" />
															</button>
														</div>
													</div>
												</div>
												<table id="accessories" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div> --%>
					<legend>流通数据:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label">企业代码:</label>
						<div class="col-md-3">
							<input id="enterpriseCode" name="item.enterpriseCode" value="${item.enterpriseCode}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">品牌:</label>
						<div class="col-md-3">
							<input id="itemBrandName" type="text" value="${item.itemBrand.name}" data-prompt-position="topLeft" class="form-control" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">生产国别:</label>
						<div class="col-md-3">
							<input id="productCountry" name="item.productCountry" value="${item.productCountry}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">产地:</label>
						<div class="col-md-3">
							<input id="origin" name="item.origin" value="${item.origin}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">投放日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="putOnDate" name="item.putOnDate" value="${item.putOnDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'putOnDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">停售日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="stopSellingDate" name="item.stopSellingDate" value="${item.stopSellingDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'stopSellingDate'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<legend>采购信息:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label">供应商:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="supplierId" name="item.supplierId" value="${item.supplierId}" type="hidden" /> <input id="supplierName" name="item.supplierName" type="text" value="${item.supplierName}" data-prompt-position="topLeft" class="form-control" onfocus="goChooseSupplier();" readonly="readonly" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#supplierId').val('');$('#supplierName').val('');">清空</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label">计量单位组:</label>
						<div class="col-md-3">
							<select id="purchaseMeasureUnitGroupId" name="itemPurchaseProperties.measureUnitGroup.id" data-prompt-position="topLeft" class="form-control" onchange="loadPurchaseMeasureUnit();">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitGroupList}" var="entity">
									<option value="${entity.id}" <c:if test="${itemPurchaseProperties.measureUnitGroup.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">采购基本计量单位:</label>
						<div class="col-md-3">
							<select id="purBaseUnitId" name="itemPurchaseProperties.purBaseUnit.id" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitList}" var="entity">
									<option value="${entity.id}" <c:if test="${itemPurchaseProperties.purBaseUnit.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">采购辅助计量单位:</label>
						<div class="col-md-3">
							<select id="purAssitUnitId" name="itemPurchaseProperties.purAssitUnit.id" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitList}" var="entity">
									<option value="${entity.id}" <c:if test="${itemPurchaseProperties.purAssitUnit.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">采购订单单位:</label>
						<div class="col-md-3">
							<select id="poUnitId" name="itemPurchaseProperties.poUnit.id" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitList}" var="entity">
									<option value="${entity.id}" <c:if test="${itemPurchaseProperties.poUnit.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">采购组:</label>
						<div class="col-md-3">
							<input id="purchaseGroup" name="itemPurchaseProperties.purchaseGroup" value="${itemPurchaseProperties.purchaseGroup}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">采购员:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="purchasePersonId" name="itemPurchaseProperties.purchasePerson.id" type="hidden" value="${itemPurchaseProperties.purchasePerson.id}" /> <input id="purchasePersonName" type="text" onfocus="chooseEmployee(); return false;" value="${itemPurchaseProperties.purchasePerson.name}" type="text" class="form-control" readonly="readonly" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#purchasePersonId').val('');$('#purchasePersonName').val('');">清空</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label">采购类型:</label>
						<div class="col-md-3">
							<select id="orderTypeId" name="" data-prompt-position="topLeft" class="form-control" onchange="loadPurchaseMeasureUnit();">
								<option value="">------请选择------</option>
								<c:forEach items="${orderTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${itemPurchaseProperties.orderType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">接收仓库:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="recieveWarehouseId" name="itemPurchaseProperties.recieveWarehouse.id" value="${itemPurchaseProperties.recieveWarehouse.id}" type="hidden" /> <input id="recieveWarehouseName" type="text" value="${itemPurchaseProperties.recieveWarehouse.name}" data-prompt-position="topLeft" class="form-control"
											onfocus="goChooseWarehouse('recieve');" readonly="readonly" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#recieveWarehouseId').val('');$('#recieveWarehouseName').val('');">清空</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label">库位:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="invShelfId" name="itemPurchaseProperties.invShelf.id" value="${itemPurchaseProperties.invShelf.id}" type="hidden" /> <input id="invShelfName" type="text" value="${itemPurchaseProperties.invShelf.name}" data-prompt-position="topLeft" class="form-control" onfocus="chooseShelfItemPurchase();" readonly="readonly" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#invShelfId').val('');$('#invShelfName').val('');">清空</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">接收地址:</label>
						<div class="col-md-3">
							<input id="recieveLocation" name="itemPurchaseProperties.recieveLocation" value="${itemPurchaseProperties.recieveLocation}" class="form-control" type="text" />
						</div>
					</div>
					<legend>库存信息:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label">最高库存:</label>
						<div class="col-md-3">
							<input id="maxStockAmount" name="itemInventoryProperties.maxStockAmount" value="${itemInventoryProperties.maxStockAmount}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">最低库存:</label>
						<div class="col-md-3">
							<input id="minStockAmount" name="itemInventoryProperties.minStockAmount" value="${itemInventoryProperties.minStockAmount}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">安全库存:</label>
						<div class="col-md-3">
							<input id="safeStockAmount" name="itemInventoryProperties.safeStockAmount" value="${itemInventoryProperties.safeStockAmount}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">默认仓库:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="treeMenu" class="input-group">
										<input id="defaultWarehouseId" name="itemInventoryProperties.defaultWarehouse.id" type="hidden" value="${itemInventoryProperties.defaultWarehouse.id}" /> <input id="defaultWarehouseName" name="itemInventoryProperties.defaultWarehouse.name" type="text" onfocus="goChooseWarehouse('default'); return false;"
											value="${itemInventoryProperties.defaultWarehouse.name}" type="text" readonly="readonly" class="form-control" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#defaultWarehouseId').val('');$('#defaultWarehouseName').val('');">清空</button>
										</div>
										<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="customerAccountCategoryTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">库位:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="defaultInvShelfId" name="itemInventoryProperties.defaultInvShelf.id" value="${itemInventoryProperties.defaultInvShelf.id}" type="hidden" /> <input id="defaultInvShelfName" type="text" value="${itemInventoryProperties.defaultInvShelf.name}" data-prompt-position="topLeft" class="form-control"
											onfocus="chooseShelfItemInventory();" readonly="readonly" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#defaultInvShelfId').val('');$('#defaultInvShelfName').val('');">清空</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> &nbsp; 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/mdm/nvixntItemAction!goList.action?syncTag=' +$('#type').val());">返回</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
	$("#itemsForm").validationEngine();
	function saveOrUpdate(id) {
		editor.sync();
	/* 	if($("#ecConfig_enableSpecification").val() == 'true' && typeof(specificationDataTable) != "undefined"){ */
			var specData = "";
			var data = specificationDataTable.rows().data();
	  		for(var i=0;i<data.length;i++){
	  			for(var j=0;j < 9;j++){
	  				if(data[i][j] == null || data[i][j] == ''){
	  					specData += "PlaceHolder";
	  				}else{
	  					specData += data[i][j];
	  				}
	  				specData += "!";
	  			}
	  			specData += ":";
	  		}
	  		$("#specificationDetail").val(specData);
	/* 	} */
		if ($('#itemsForm').validationEngine('validate')) {
			$("#itemsForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/mdm/nvixntItemAction!saveOrUpdate.action",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				var r = result.split(":");
				showMessage(r[1]);
				loadContent('mdm_items', '${nvix}/nvixnt/mdm/nvixntItemAction!goList.action');
			}
			});
		}
	}

	/** 初始化分类下拉列表树 */
	var showItemCategory = initDropListTree("itemCategoryTreeMenu", "${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action", "itemCatalogIds", "itemCatalogNames", "itemCategoryTree", "itemCategoryMenu");
	//弹窗方式初始化分类
	function chooseItemCatagory() {
		var ids = $("#objectExpandId").val();
		chooseListData('${nvix}/nvixnt/mdm/nvixntItemAction!goChooseMultiItemCatalog.action', 'multi', '选择分类', null, function() {
			var ItemCatagory = chooseMap.valueSetWithClear().split(":");
			if (ItemCatagory != '' && ItemCatagory.length == 2) {
				$('#itemCatalogIds').val(ItemCatagory[0]);
				$('#temCatalogNames').val(ItemCatagory[1]);
			} else {
				layer.alert("请选择商品类型!");
			}
		});
	}

	//设置品牌名称
	function setBarndName() {
		$("select").change(function() {
			$("#itemBrandName").val($("#itemBrandId").find("option:selected").text());
		});
	}

	//校验编码
	function checkItemCode() {
		$.ajax({
		url : '${nvix}/nvixnt/mdm/nvixntItemAction!checkItemCode.action?itemCode=' + $('#itemCode').val(),
		cache : false,
		success : function(result) {
			if (result != '') {
				layer.alert(result);
				$('#itemCode').val('');
			}
		}
		});
	}

	//选择采购员
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'purchasePerson');
	}

	//选在供应商
	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};

	//选择仓库
	function goChooseWarehouse(tag) {
		chooseListData('${nvix}/nvixnt/vixntTakeStockAction!goChooseWarehouse.action', 'single', '选择仓库', tag == 'recieve' ? 'recieveWarehouse' : 'defaultWarehouse');
	};
	//加载库位
	function chooseShelfItemInventory(tag) {
		var wareHouseId = $("#defaultWarehouseId").val();
		if (wareHouseId == '') {
			layer.alert("请选仓库!");
		} else {
			chooseListData('${nvix}/nvixnt/vixntTakeStockAction!goChooseShelf.action?warehouseId=' + wareHouseId, 'single', '选择货位','defaultInvShelf');
		}
	}
	//加载库位
	function chooseShelfItemPurchase() {
			var wareHouseId = $("#recieveWarehouseId").val();
		if (wareHouseId == '') {
			layer.alert("请选仓库!");
		} else {
			chooseListData('${nvix}/nvixnt/vixntTakeStockAction!goChooseShelf.action?warehouseId=' + wareHouseId, 'single', '选择货位', 'invShelf');
		}
	}
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

	function loadPurchaseMeasureUnit() {
		var mug = $("#purchaseMeasureUnitGroupId").val();
		if (mug != "") {
			$.ajax({
			url : "${nvix}/nvixnt/mdm/nvixntItemAction!loadMeasureUnit.action?type=purBaseUnit&id=" + $("#id").val() + "&measureUnitGroupId=" + mug,
			cache : false,
			success : function(html) {
				$("#purBaseUnitId").html(html);
			}
			});
			$.ajax({
			url : "${nvix}/nvixnt/mdm/nvixntItemAction!loadMeasureUnit.action?type=purAssitUnit&id=" + $("#id").val() + "&measureUnitGroupId=" + mug,
			cache : false,
			success : function(html) {
				$("#purAssitUnitId").html(html);
			}
			});
			$.ajax({
			url : "${nvix}/nvixnt/mdm/nvixntItemAction!loadMeasureUnit.action?type=poUnit&id=" + $("#id").val() + "&measureUnitGroupId=" + mug,
			cache : false,
			success : function(html) {
				$("#poUnitId").html(html);
			}
			});
		}
	}

	/** 初始化所属部门下拉列表树 */
	var showOrgUnitMenu = initDropListTree("orgUnitTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "internalUnitId", "internalUnitName", "customerAccountOrgUnitTree", "OrgUnitContent");
	//loadItemMeasureUnit();
	//loadPurchaseMeasureUnit();
	/** 页面加载完调用 */
	//富文本初始化
	$(document).ready(function() {
		pageSetUp();
		$('.summernote').summernote({
		height : 100,
		focus : false,
		tabsize : 2
		});
	});
	
	
	
	/** 规格列表 */
   	var specificationDataTableColumns = [
		{"name":"sku","title":"SKU码","width":"9%","orderable" :false,"data":function(data){
			var d = data[1] == null ? "" : data[1];
			return "<input type='text' class='form-control' style='width:98%;height:28px;' value='"+ d +"' onblur=\"changeSpecCellData(this,1,'" + data[0] + "');\"/>";
		}},
		{"name":"INVENTORY_COUNT","title":"零售价","width":"6%","orderable" :false,"data":function(data){
			var d = data[2] == null ? "" : data[2];
			return "<input type='text' class='form-control validate[custom[number]]' style='width:98%;height:28px;' value='"+ d +"' onblur=\"changeSpecCellData(this,2,'" + data[0] + "');\"/>";
		}}
   	];
	$("label[id^='specName_']").each(function(i){
		var sName = $(this).attr("data-spec");
		specificationDataTableColumns.push({"title":sName,"width":"5%","orderable" :false,"data":function(data){return data[i+3];}});
	});
	specificationDataTableColumns.push({"title":"操作","width":"5%","orderable" :false,"data":function(data){
		return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteSpecificationGroup('"+data[0]+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
	}});

  	var specificationDataTable = initDataTableNoPaging("specificationDataTable","${nvix}/nvixnt/mdm/nvixntItemAction!loadSpecificationDetailJson.action",specificationDataTableColumns,function(orderField,orderBy){
		var id = $("#id").val();
		var sku = $("#skuCode").val();
		var categoryId = $("#itemCatalogIds").val();
  		return {"orderField":orderField,"orderBy":orderBy,"id":id,"sku":sku,"categoryId":categoryId};
  	},"1","0");
  	
  	/** 生成规格 */
	var generateSpecification = function(){
		/** 打开遮盖层 */
		var loadIndex = layer.load(2);
		
		var hasSpecDetail = false;
		var specificationDetailIds = "";
		var specIds = "";
		$("input[id^='specification_']").each(function(){
			var sId = $(this).attr("id").split('_')[1];
			if(specIds.indexOf(sId +",") == -1){
				specIds =specIds + sId +",";
			}
		});
		var specIdsArray = specIds.split(",");
		for(var i=0;i<specIdsArray.length;i++){
			if(specIdsArray[i] == ""){
				continue;
			}
			var specDetailIds = "";
			$("input[name^='specificationDetail_"+specIdsArray[i]+"_']").each(function(){
				if($(this).parent().hasClass('active')){
					specDetailIds = specDetailIds + $(this).val()+",";
				}
			});
			if(specDetailIds == ""){
				specificationDetailIds = specificationDetailIds +  "0,:";
			}else{
				hasSpecDetail = true;
				specificationDetailIds = specificationDetailIds +  specDetailIds + ":";
			}
		}
		if(!hasSpecDetail){
			/** 关闭遮盖层 */
			layer.close(loadIndex);
			layer.alert("请选择规格!");
		}else{
			var id = $("#id").val();
			var productCategoryId = $("#itemCatalogIds").val();
			if(!productCategoryId){
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				layer.alert("请选择商品分类!");
				return;
			}else{
				if(id == ""){
					if($('#itemsForm').validationEngine('validate')){
						/** 根据配置加载扩展属性到form的隐藏域中 */
						if($("#ecConfig_enableExpandField").val() == 'true' && typeof loadAppendFieldValue == 'function'){
							loadAppendFieldValue();
						}
						/** 根据配置加载维度属性到form的隐藏域中 */
						if($("#ecConfig_enableDimension").val() == 'true'){
							var dimensionDetailIds = ",";
							$("input[id^='dimension_']").each(function(){
								if($(this).parent().hasClass('active')){
									dimensionDetailIds += $(this).val() + ",";
								}
							});
							$("#dimensionDetailIds").val(dimensionDetailIds);
						}
						$("#fileToUpload").remove();
						$("#itemsForm").ajaxSubmit({
				    		type: "post",
				    		url:"${nvix}/nvixnt/mdm/nvixntItemAction!saveOrUpdate.action",
				    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
							success : function(result) {
								$("#mainImageDiv").append("<input type='file' id='fileToUpload' name='fileToUpload' onchange='uploadEcProductMainImage();' style='width:65%;'>");
								var r = result.split(":");
								var backId = $.trim(r[0]);
								if(backId == "empty"){
									/** 关闭遮盖层 */
									layer.close(loadIndex);
									layer.alert(r[1]);
								}else{
									$("#id").val(backId);
									/** 关闭遮盖层 */
									layer.close(loadIndex);
									addSpecificationGroup(backId,productCategoryId,specificationDetailIds);
								}
							}
						});
					}else{
						/** 关闭遮盖层 */
						layer.close(loadIndex);
					}
				}else{
					/** 关闭遮盖层 */
					layer.close(loadIndex);
					addSpecificationGroup(id,productCategoryId,specificationDetailIds);
				}
			}
		}
	}
	/** 添加规格组 */
  	function addSpecificationGroup(id,productCategoryId,specificationDetailIds){
  		var specData = "";
  		layer.alert(1);
		var data = specificationDataTable.rows().data();
  		for(var i=0;i<data.length;i++){
  			for(var j=0;j < 9;j++){
  				if(data[i][j] == null || data[i][j] == ''){
  					specData += "PlaceHolder";
  				}else{
  					specData += data[i][j];
  				}
  				specData += "!";
  			}
  			specData += ":";
  		}
  		layer.confirm("是否根据选中的规格生成规格明细?", {title:"提示信息"}, function(index){
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			$.ajax({
				url:'${nvix}/nvixnt/mdm/nvixntItemAction!addSpecificationGroup.action?specificationDetailIds='+specificationDetailIds+"&productCategoryId="+ productCategoryId +"&id="+id+"&specData="+specData,
				cache: false,
				success: function(json){
					/** 关闭遮盖层 */
					layer.close(loadIndex);
					if(json == '1'){
						/** 禁用商品原有价格 */
						$("#sku").attr('readonly',"readonly");
						$("#price").attr('readonly',"readonly");
						$("#mobilePrice").attr('readonly',"readonly");
						$("#appPrice").attr('readonly',"readonly");
						$("#listedPrice").attr('readonly',"readonly");
						$("#marketPrice").attr('readonly',"readonly");
						$("#purchasePrice").attr('readonly',"readonly");
						layer.alert("规格添加成功!",function(i){
  							specificationDataTable.ajax.reload();
  							layer.close(i);
  						});
					}else{
						layer.alert("规格添加失败!");
					}
				}
			});
		    layer.close(index);
		});
  	}
  	function deleteSpecificationGroup(id){
  		layer.confirm("是否删除规则明细?", {title:"提示信息"}, function(index){
  			/** 打开遮盖层 */
  			var loadIndex = layer.load(2);
  			$.ajax({
  				url:'${nvix}/nvixnt/mdm/nvixntItemAction!deleteSpecificationGroup.action?sgId='+id+'&ecProductId='+$("#id").val()+'&productCategoryId='+$("#itemCatalogIds").val(),
  				cache: false,
  				success: function(json){
  					/** 关闭遮盖层 */
  					layer.close(loadIndex);
  					if(json == '1'){
  						layer.alert("规格删除成功!",function(i){
  							specificationDataTable.ajax.reload();
  							layer.close(i);
  						});
  					}else{
  						layer.alert("规格删除失败!");
  					}
  				}
  			});
  		    layer.close(index);
  		});
  	}
  	/** 将修改后的数据设置进table的data */
  	function changeSpecCellData(input,index,id){
  		if(index == 1){
  			/** 校验sku */
  			$.ajax({
  				url:'${nvix}/nvixnt/mdm/nvixntItemAction!checkSkuIsExist.action?ecProductId=' + $("#id").val() + '&sku='+ $(input).val(),
  				cache: false,
  				success: function(result){
  					var result = $.trim(result);
  					if(result != 'exist'){
  						var data = specificationDataTable.rows().data();
				  		for(var i=0;i<data.length;i++){
				  			if(data[i][0] == id){
				  				specificationDataTable.rows().data()[i][index] = $(input).val();
				  			}
				  		}
  					}else{
  						layer.alert("SKU:"+$(input).val()+",已存在不允许重复添加！", function(index){
  							layer.close(index);
  							$(input).val("");	
  						});
  					}
  				}
  			});
  		}else{
  			var data = specificationDataTable.rows().data();
	  		for(var i=0;i<data.length;i++){
	  			if(data[i][0] == id){
	  				specificationDataTable.rows().data()[i][index] = $(input).val();
	  			}
	  		}
  		}
  	}
  	
  	/** 检查是否包含规格明细，并设点商品sku属性不可编辑  FIXME:执行时机待测试，目前效果没加上 */
  	function updateEcProductSkuStatus(){
  		if(specificationDataTable.rows().data().length > 0){
	  		/** 禁用商品原有价格 */
			$("#sku").attr('readonly',"readonly");
			$("#price").attr('readonly',"readonly");
	  	}else{
	  		/** 禁用商品原有价格 */
			$("#sku").removeAttr('readonly');
			$("#price").removeAttr('readonly');
	  	}
  	}
  	$(function(){
  		updateEcProductSkuStatus();
  	});
  	
  	
  	
  	var ecProductImageColumns = [
          		{"orderable":false,"data" : function(data) {return "";}},
          		{"name":"persisName","data" : function(data) {
          			var image = data.imgPath;
       			if(image == null || image == ""){
       				return data.persisName;
       			}else{
       				var result = data.persisName + "&nbsp;&nbsp;<span style=\"cursor: pointer;font-weight: bold;\" onmouseover=\"$('#imageShow_"+data.id+"').attr('style','position: absolute;padding:-50px;');\" onmouseout=\"$('#imageShow_"+data.id+"').attr('style','display:none;');\">";
       				result += "<img src=\""+data.imgPath+"\" onerror=\"$(this).attr('src','${nvix}/common/img/default.png')\" width='20' height='20'/>";
       				result += "</span><img id=\"imageShow_"+data.id+"\" src=\""+data.imgPath+"\" onerror=\"$('#imageShow_"+data.id+"').attr('src','${nvix}/common/img/default.png')\" width='100' height='100' style='display:none;position: absolute;'/>"
       				return result;
       			}
          		}},
          		{"name":"orderBy","data" : function(data) {return data.orderBy;}},
          		{"name":"memo","data" : function(data) {return data.memo;}},
          		{"orderable":false,"data" : function(data) {
          			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdateEcProductImage('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
          			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteImageById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
          			return update + "&nbsp;&nbsp;" + del;
          		}}
          	];
          	var ecProductImageDataTable = initDataTableNoPaging("ecProductImageDataTable","${nvix}/nvixnt/mdm/nvixntItemAction!getItemImageList.action",ecProductImageColumns,function(orderField,orderBy){
       		var id = $("#id").val();
        	var searchName = $("#searchEcProductImageMemo").val();
          	searchName=Url.encode(searchName);
       		return {"orderField":orderField,"orderBy":orderBy,"id":id,"searchName":searchName};
       	});
  	                      	
           	function saveOrUpdateEcProductImage(id){
           		var ecProductId = $("#id").val();
				if(ecProductId == ""){
					editor.sync();
					if($('#itemsForm').validationEngine('validate')){
						$("#itemsForm").ajaxSubmit({
				    		type: "post",
				    		url:"${nvix}/nvixnt/mdm/nvixntItemAction!saveOrUpdate.action",
				    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
							success : function(result) {
								var r = result.split(":");
								if(r[0] == '0'){
									showMessage(r[1]);
									return; 
								}else{
									$("#id").val(r[0]);
									showMessage(r[1]);
									openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixntItemAction!goSaveOrUpdateImage.action?itemImageId='+id+'&id='+r[0],"itemImageForm","商品图片",480,340,ecProductImageDataTable);
								}
									
							}
						});
					}
				}else{
					openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixntItemAction!goSaveOrUpdateImage.action?itemImageId='+id+'&id='+ecProductId,"itemImageForm","商品图片",480,340,ecProductImageDataTable);
				} 	
			}
  	                      	
	function deleteImageById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixntItemAction!deleteImageById.action?id='+id,'是否删除商品图片?',ecProductImageDataTable);
   	}
	function fileToChange(){
		var id = $("#id").val();
		if(id){
			uploadFileOrImage("itemFileUploadForm","${nvix}/nvixnt/mdm/nvixntItemAction!uploadItemImage.action?id="+id,"itemImageFileToUpload","image",function(data){
				var d = data.split(":");
				if($.trim(d[0]) == '0'){
					layer.alert(d[1]);
				}else{
					$("#imageFilePath").val(d[1]);
		       		$("#itemBigImage").attr("src",d[1]);
		       		$("#itemBigImageShow").attr("src",d[1]);
		       		showMessage("图片上传成功!");
				}
			});
		}else{
			if($('#itemsForm').validationEngine('validate')){
				editor.sync();
				$("#itemsForm").ajaxSubmit({
		    		type: "post",
		    		url:"${nvix}/nvixnt/mdm/nvixntItemAction!saveOrUpdate.action",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(":");
						if(r[0] == '0'){
							showMessage(r[1]);
							return; 
						}else{
							$("#id").val(r[0]);
							showMessage(r[1]);
							uploadFileOrImage("itemFileUploadForm","${nvix}/nvixnt/mdm/nvixntItemAction!uploadItemImage.action?id="+r[0],"itemImageFileToUpload","image",function(data){
								var d = data.split(":");
								if($.trim(d[0]) == '0'){
									layer.alert(d[1]);
								}else{
									$("#imageFilePath").val(d[1]);
						       		$("#itemBigImage").attr("src",d[1]);
						       		$("#itemBigImageShow").attr("src",d[1]);
						       		showMessage("图片上传成功!");
								}
							});
						}
							
					}
				});
			}
		}
	}
</script>