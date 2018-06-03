<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 商品管理 <span>&gt; 服务管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('drp_allocationitem', '${nvix}/nvixnt/mdm/nvixntItemAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>服务列表</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="itemForm">
				<input type="hidden" id="itemId" name="item.id" value="${item.id}" />
				<input type="hidden" id="isTemp" name="item.isTemp" value="${item.isTemp}" />
				<input type="hidden" id="isDeleted" name="item.isDeleted" value="${item.isDeleted}" />
				<input type="hidden" id="isServiceItem" name="item.isServiceItem" value="${item.isServiceItem}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
						<div class="col-md-3">
							<input id="code" name="item.code" value="${item.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
						<div class="col-md-3">
							<input id="name" name="item.name" value="${item.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 服务分类:</label>
						<div class="col-md-3">
							<input id="itemCatalogId" name="item.itemCatalog.id" type="hidden" value="${item.itemCatalog.id}" /> <input id="itemCatalogName" name="item.itemCatalog.name" value="${item.itemCatalog.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"> 单位:</label>
						<div class="col-md-3">
							<select id="measureUnitId" name="item.measureUnit.id" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${measureUnitList}" var="entity">
									<option value="${entity.id}" <c:if test="${item.measureUnit.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否启用:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='item.status == "T"'>active</s:if>"> <input type="radio" value="T" id="status" name="item.status" class="validate[required]" <s:if test='item.status == "T"'>checked="checked"</s:if>>启用
								</label> <label class="btn btn-default <s:if test='item.status == "F"'>active</s:if>"> <input type="radio" value="F" id="status" name="item.status" <s:if test='item.status == "F"'>checked="checked"</s:if>>禁用
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否折扣:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='item.isDiscount == "T"'>active</s:if>"> <input type="radio" value="T" id="isDiscount" name="item.isDiscount" class="validate[required]" <s:if test='item.isDiscount == "T"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='item.isDiscount == "F"'>active</s:if>"> <input type="radio" value="F" id="isDiscount" name="item.isDiscount" <s:if test='item.isDiscount == "F"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">币种:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="item.currencyType.id" data-prompt-position="topLeft" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${item.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>价格:</label>
						<div class="col-md-3">
							<input id="price" name="item.price" value="${item.price}" class="form-control validate[required,custom[number]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">会员价:</label>
						<div class="col-md-3">
							<input id="vipPrice" name="item.vipPrice" value="${item.vipPrice}" class="form-control validate[custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label">助记码:</label>
						<div class="col-md-3">
							<input id="pinyin" name="item.pinyin" value="${item.pinyin}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">服务内容:</label>
						<div class="col-md-8">
							<textarea id="serviceContent" name="item.serviceContent" class="form-control" rows="4">${item.serviceContent }</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('drp_allocationitem', '${nvix}/nvixnt/mdm/nvixntItemAction!goList.action');">
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
	$("#itemForm").validationEngine();
	function saveOrUpdate() {
		if ($("#itemForm").validationEngine('validate')) {
			$("#itemForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/mdm/nvixntItemAction!saveOrUpdateService.action",
			dataType : "text",
			success : function(id) {
				loadContent('drp_allocationitem', '${nvix}/nvixnt/mdm/nvixntItemAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>