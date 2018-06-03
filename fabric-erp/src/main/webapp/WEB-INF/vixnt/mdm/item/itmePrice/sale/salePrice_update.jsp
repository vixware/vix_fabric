<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>

<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 商品管理<span>&gt; 商品定价</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('mdm_salePrice','${nvix}/nvixnt/mdm/nvixntSalePriceAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>销售定价</h2>
		</header>
		<div class="widget-body">
			<form id="salePriceForm" class="form-horizontal" action="${nvix}/nvixnt/mdm/nvixntSalePriceAction!saveOrUpdate.action">
				<input type="hidden" id="id" name="priceCondition.id" value="${priceCondition.id}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>销售组织:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="treeMenu" class="input-group">
										<input id="saleOrgId" name="priceCondition.saleOrg.id" type="hidden" value="${priceCondition.saleOrg.id}" /> 
										<input id="saleOrgName" type="text" onfocus="chooseSaleOrg(); return false;" 
											value="${priceCondition.saleOrg.fullName}" type="text" class="form-control validate[required]" readonly="readonly" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#saleOrgId').val('');$('#saleOrgName').val('');">清空</button>
										</div>
										<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="saleOrgTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>主题:</label>
						<div class="col-md-3">
							<input id="name" name="priceCondition.name" value="${priceCondition.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test="priceCondition.enable == 1">active</s:if>">
									<input type="radio" value="1" id="enable" name="priceCondition.enable" class="validate[required]" <s:if test="priceCondition.enable == 1">checked="checked"</s:if>>是
								</label>
								<label class="btn btn-default <s:if test="priceCondition.enable == 0">active</s:if>">
									<input type="radio" value="0" id="enable" name="priceCondition.enable" class="validate[required]" <s:if test="priceCondition.enable == 0">checked="checked"</s:if>>否
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>币种:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="priceCondition.currencyType.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${priceCondition.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="startEffectiveTime" name="priceCondition.startEffectiveTime" value="${priceCondition.startEffectiveTimeStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" />
								 <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startEffectiveTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="endEffectiveTime" name="priceCondition.endEffectiveTime" value="${priceCondition.endEffectiveTimeStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" />
									<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endEffectiveTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>税率:</label>
						<div class="col-md-3">
							<select id="taxRateId" name="priceCondition.taxRate.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${taxRateList}" var="entity">
									<option value="${entity.id}" <c:if test="${priceCondition.taxRate.id == entity.id}">selected="selected"</c:if>>${entity.name}%</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</fieldset>
				<br/>
				<div class="form-group">
					<label class="col-md-1 control-label"></label>
					<div class="col-md-12">
						<div class="jarviswidget jarviswidget-color-darken">
							<header style="height: 1px; border-color: #ddd !important"></header>
							<div>
								<div id="rightContent" class="widget-body no-padding">
									<div class="jarviswidget" id="priceDetailTabs" style="margin: 0;padding:12px 12px 12px 12px;" data-widget-togglebutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
										<header>
											<ul class="nav nav-tabs pull-left in">
												<li class="active">
													<a data-toggle="tab" href="#priceTab" onclick="priceTable.ajax.reload();">
														<i class="fa fa-list-alt"></i>
														<span class="hidden-mobile hidden-tablet">价格区间</span> 
													</a>
												</li>
												<li>
													<a data-toggle="tab" href="#countPriceTab" onclick="countPriceTable.ajax.reload();">
														<i class="fa fa-list-alt"></i>
														<span class="hidden-mobile hidden-tablet">数量区间</span> 
													</a>
												</li>
											</ul>
										</header>
										<div class="widget-body" style="padding: 0;">
											<div class="tab-content">
												<div class="tab-pane no-padding active" id="priceTab">
													<div id="itemPriceSearchForm" style="margin:5px;">
														<div class="form-group" style="margin: 0 5px;">
															<div class="col-md-3">
																<input type="text" value="" id="priceSearchName" placeholder="" class="form-control"/>
															</div>
															<button onclick="priceTable.ajax.reload();" type="button" class="btn btn-info">
																<i class="glyphicon glyphicon-search"></i> 检索
															</button>
															<button onclick="clearSearchCondition('priceSearchName',priceTable);" type="button" class="btn btn-default">
																<i class="glyphicon glyphicon-repeat"></i> 清空
															</button>
															<div class=" listMenu-float-right" >
																<button onclick="saveOrUpdatePriceConditionDetail('','price');" type="button" class="btn btn-primary">
																	<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
																</button>
															</div>
														</div>
													</div>
													<table id="priceTable" class="table table-striped table-bordered table-hover" width="100%"></table>
												</div>
												<div class="tab-pane no-padding" id="countPriceTab">
													<div id="purchasePriceSearchForm" style="margin:5px;">
														<div class="form-group" style="margin: 0 5px;">
															<div class="col-md-3">
																<input type="text" value="" id="countSearchName" placeholder="" class="form-control"/>
															</div>
															<button onclick="countPriceTable.ajax.reload();" type="button" class="btn btn-info">
																<i class="glyphicon glyphicon-search"></i> 检索
															</button>
															<button onclick="clearSearchCondition('countSearchName',countPriceTable);" type="button" class="btn btn-default">
																<i class="glyphicon glyphicon-repeat"></i> 清空
															</button>
															<div class=" listMenu-float-right" >
																<button onclick="saveOrUpdatePriceConditionDetail('','count');" type="button" class="btn btn-primary">
																	<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
																</button>
															</div>
														</div>
													</div>
													<table id="countTable" class="table table-striped table-bordered table-hover" width="100%" ></table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> &nbsp; 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('mdm_salePrice','${nvix}/nvixnt/mdm/nvixntSalePriceAction!goList.action');">返回</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	var priceColumns = [
		{"orderable" : false,"title" : "编号","width" : "5%","data" : function(data){return "&nbsp;";}}, 	 
		{"orderable" : false,"title" : "条件类型","width" : "10%","data" : function(data){
			if(data.conditionType == 0){
				return "客户";
			}else if(data.conditionType == 1){
				return "渠道";
			}else{
				return "";
			}
		}}, 	                    
		/* {"title":"条件类型","width":"7%","name":"minPrice","data" : function(data) {
			if(data.conditionType == 'purchaseFrameworkAgreement'){return '<s:text name="getText('mdm_purchaseFrameworkAgreement')"/>';}
			if(data.conditionType == 'customerAccountCategory'){return '<s:text name="getText('mdm_customerAccountCategory')"/>';}
			if(data.conditionType == 'itemCategory'){return '<s:text name="getText('mdm_itemCategory')"/>';}
			if(data.conditionType == 'itemGroup'){return '<s:text name="getText('mdm_itemGroup')"/>';}
			if(data.conditionType == 'channelDistributor'){return '<s:text name="getText('mdm_channelDistributor')"/>';}
			if(data.conditionType == 'customerAccount'){return '<s:text name="getText('mdm_customerAccount')"/>';}
			if(data.conditionType == 'item'){return '<s:text name="getText('mdm_item')"/>';}
			if(data.conditionType == 'supplier'){return '<s:text name="getText('mdm_supplier')"/>';}
			if(data.conditionType == 'customerAccountGroup'){return '<s:text name="getText('mdm_customerAccountGroup')"/>';}
			if(data.conditionType == 'customerAccountAndItem'){return '<s:text name="getText('mdm_customerAccountAndItem')"/>';}
			return '';
		}}, */
		{"orderable" : false,"title":"销售区域","width":"10%","data" : function(data) {return data.regionalName;}},
		/* {"title":"客户分类","width":"6%","name":"customerAccountCategory","data" : function(data) {return data.customerAccountCategory == null ? '' : data.customerAccountCategory.name;}},
		{"title":"${vv:varView('vix_mdm_item')}分类","width":"6%","name":"itemCatalog","data" : function(data) {return data.itemCatalog == null ? '' : data.itemCatalog.name;}},
		{"title":"${vv:varView('vix_mdm_item')}组","width":"6%","name":"itemGroup","data" : function(data) {return data.itemGroup == null ? '' : data.itemGroup.name;}},
		{"title":"客户","width":"6%","name":"customerAccount","data" : function(data) {return data.customerAccount == null ? '' : data.customerAccount.name;}},
		{"title":"${vv:varView('vix_mdm_item')}编码","width":"6%","name":"item","data" : function(data) {return data.item == null ? '' : data.item.code;}},
		{"title":"${vv:varView('vix_mdm_item')}","width":"6%","name":"item","data" : function(data) {return data.item == null ? '' : data.item.name;}},
		{"title":"分销商","width":"6%","name":"channelDistributor","data" : function(data) {return data.channelDistributor == null ? '' : data.channelDistributor.name;}}, */
		{"orderable" : false,"title":"客户","width":"10%","data" : function(data) {return data.customerAccountName == '' ? '无' : data.customerAccountName;}},
		{"orderable" : false,"title":"渠道","width":"10%","data" : function(data) {return data.channelDistributorName == '' ? '无' : data.channelDistributorName;}},
		{"orderable" : false,"title":"商品名称","width":"10%","data" : function(data) {
			if(data.conditionType == 0){
				return data.itemName;
			}else if(data.conditionType == 1){
				return data.storeItemName;
			}
		}},
		{"orderable" : false,"title":"开始价格","width":"10%","data" : function(data) {return data.startTotalAmount+"元";}},
		{"orderable" : false,"title":"结束价格","width":"10%","data" : function(data) {return data.endTotalAmount+"元";}},
		{"orderable" : false,"title":"返款","width":"10%","data" : function(data) {return data.refund == null ? '无返款' : data.refund+"元";}},
		{"orderable" : false,"title":"折扣","width":"10%","data" : function(data) {return data.discount == null ? '无折扣' : data.discount+"%";}},
		/* {"title":"备注","width":"6%","name":"memo","data" : function(data) {return data.memo;}}, */
		{"orderable" : false,"title":"操作","width":"6%","data" : function(data) {
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdatePriceConditionDetail('" + data.id + "','price');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deletePriceConditionEntity('"+data.id +"','price');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];
	               	                        	             	
	var priceTable = initDataTable("priceTable","${nvix}/nvixnt/mdm/nvixntSalePriceAction!getPriceJson.action",priceColumns,function(page,pageSize,orderField,orderBy){
		var name = $('#priceSearchName').val();
		name = Url.encode(name);
		var id = $("#id").val();
		var type = 'price';
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name,"type":type};
	},5,'1','1');
	    
	var countPriceColumns = [
  		{"orderable" : false,"title" : "编号","width" : "5%","data" : function(data){return "&nbsp;";}}, 	 
  		{"orderable" : false,"title" : "条件类型","width" : "10%","data" : function(data){
			if(data.conditionType == 0){
				return "客户";
			}else if(data.conditionType == 1){
				return "渠道";
			}else{
				return "";
			}
		}},                      
  		/* {"title":"条件类型","width":"6%","name":"minPrice","data" : function(data) {
  			if(data.conditionType == 'purchaseFrameworkAgreement'){return '<s:text name="getText(\'mdm_purchaseFrameworkAgreement\')"/>';}
  			if(data.conditionType == 'customerAccountCategory'){return '<s:text name="getText(\'mdm_customerAccountCategory\')"/>';}
  			if(data.conditionType == 'itemCategory'){return '<s:text name="getText(\'mdm_itemCategory\')"/>';}
  			if(data.conditionType == 'itemGroup'){return '<s:text name="getText(\'mdm_itemGroup\')"/>';}
  			if(data.conditionType == 'channelDistributor'){return '<s:text name="getText(\'mdm_channelDistributor\')"/>';}
  			if(data.conditionType == 'customerAccount'){return '<s:text name="getText(\'mdm_customerAccount\')"/>';}
  			if(data.conditionType == 'item'){return '<s:text name="getText(\'mdm_item\')"/>';}
  			if(data.conditionType == 'supplier'){return '<s:text name="getText(\'mdm_supplier\')"/>';}
  			if(data.conditionType == 'customerAccountGroup'){return '<s:text name="getText(\'mdm_customerAccountGroup\')"/>';}
  			if(data.conditionType == 'customerAccountAndItem'){return '<s:text name="getText(\'mdm_customerAccountAndItem\')"/>';}
  			return '';
  		}}, */
  		{"orderable" : false,"title":"销售区域","width":"10%","data" : function(data) {return data.regionalName;}},
  		/* {"title":"客户分类","width":"6%","name":"customerAccountCategory","data" : function(data) {return data.customerAccountCategory == null ? '' : data.customerAccountCategory.name;}},
  		{"title":"${vv:varView('vix_mdm_item')}分类","width":"5%","name":"itemCatalog","data" : function(data) {return data.itemCatalog == null ? '' : data.itemCatalog.name;}},
  		{"title":"客户组","width":"6%","name":"customerAccountGroup","data" : function(data) {return data.customerAccountGroup == null ? '' : data.customerAccountGroup.name;}},
  		{"title":"客户","width":"6%","name":"itemGroup","data" : function(data) {return data.itemGroup == null ? '' : data.itemGroup.name;}},
  		{"title":"${vv:varView('vix_mdm_item')}组","width":"5%","name":"customerAccount","data" : function(data) {return data.customerAccount == null ? '' : data.customerAccount.name;}},
  		{"title":"${vv:varView('vix_mdm_item')}编码","width":"5%","name":"item","data" : function(data) {return data.item == null ? '' : data.item.code;}},
  		{"title":"${vv:varView('vix_mdm_item')}","width":"5%","name":"item","data" : function(data) {return data.item == null ? '' : data.item.name;}},
  		{"title":"分销商","width":"6%","name":"minPrice","data" : function(data) {return data.channelDistributor == null ? '' : data.channelDistributor.name;}}, */
  		{"orderable" : false,"title":"客户","width":"10%","data" : function(data) {return data.customerAccountName == '' ? '无' : data.customerAccountName;}},
  		{"orderable" : false,"title":"渠道","width":"10%","data" : function(data) {return data.channelDistributorName == '' ? '无' : data.channelDistributorName;}},
		{"orderable" : false,"title":"商品名称","width":"10%","data" : function(data) {
			if(data.conditionType == 0){
				return data.itemName;
			}else if(data.conditionType == 1){
				return data.storeItemName;
			}
		}},
  		{"orderable" : false,"title":"开始数量","width":"10%","data" : function(data) {return data.startCount;}},
  		{"orderable" : false,"title":"结束数量","width":"10%","data" : function(data) {return data.endCount;}},
  		/* {"orderable" : false,"title":"单位","width":"10%","data" : function(data) {return data.unit;}}, */
  		{"orderable" : false,"title":"价格","width":"10%","data" : function(data) {return data.price == null ? '无价格' : data.price+"元";}},
  		{"orderable" : false,"title":"折扣","width":"10%","data" : function(data) {return data.discount == null ? '无折扣' : data.discount+"%";}},
  		/* {"orderable" : false,"title":"备注","width":"6%","name":"memo","data" : function(data) {return data.memo;}}, */
  		{"orderable" : false,"title":"操作","width":"10%","data" : function(data) {
  			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdatePriceConditionDetail('" + data.id + "','count');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
  			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deletePriceConditionEntity('"+data.id +"','count');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
  			return update + "&nbsp;&nbsp;" + del;
  		}}
  	];
	               	             	                         	             	
   	var countPriceTable = initDataTable("countTable","${nvix}/nvixnt/mdm/nvixntSalePriceAction!getCountPriceJson.action",countPriceColumns,function(page,pageSize,orderField,orderBy){
		var name = $('#countSearchName').val();
		name = Url.encode(name);
		var type = 'count';
		var id = $("#id").val();
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name,"type":type};
	},5,'1','1');
	
	/** 保存客户信息 */
	$("#salePriceForm").validationEngine();
	function saveOrUpdate(id){
		//表单验证
		if($('#salePriceForm').validationEngine('validate')){
			var startTime = $("#startEffectiveTime").val() + " 00:00:01";
			var endTime = $("#endEffectiveTime").val() + " 00:00:01";;
			var tag = dateTimeRange(startTime,endTime);
			if(!tag){
				layer.alert("结束时间不能小于开始时间!");
				return tag;
			}else{
				$("#salePriceForm").ajaxSubmit({
					type: "post",
					url:"${nvix}/nvixnt/mdm/nvixntSalePriceAction!saveOrUpdate.action",
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						showMessage(result);
						loadContent('mdm_salePrice','${nvix}/nvixnt/mdm/nvixntSalePriceAction!goList.action');
					}
				});
			}
		}
	}
	
	/** 初始化分类下拉列表树 */
	var chooseSaleOrg = initDropListTree("treeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","saleOrgId","saleOrgName","saleOrgTree","menuContent",null,function(treeNode){
		if(treeNode.treeType == "C"){
			layer.alert("请选择部门!");
			$("#saleOrgId").val("");
			$("#saleOrgName").val("");
		}
	});
	function saveOrUpdatePriceConditionDetail(id,type){
		var priceConditionId = $("#id").val();
		if(priceConditionId){
			var startTime = $("#startEffectiveTime").val() + " 00:00:01";;
			var endTime = $("#endEffectiveTime").val() + " 00:00:01";;
			var tag = dateTimeRange(startTime,endTime);
			if(!tag){
				layer.alert("结束时间不能小于开始时间!");
				return tag;
			}else{
				if(type == "price"){
					openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixntSalePriceAction!goSaveOrUpdatePriceConditionPriceArea.action?priceConditionId='+priceConditionId+'&id='+id,'priceConditionPriceAreaForm','价格区间',720,480,priceTable,function(){
						var startTotalAmount = $("#startTotalAmount").val();
						var endTotalAmount = $("#endTotalAmount").val();
						if(Number(endTotalAmount) <= Number(startTotalAmount)){
							layer.alert("结束价格不能小于开始价格!");
							return false;
						}
					});
				}else if(type == "count"){
					openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixntSalePriceAction!goSaveOrUpdatePriceConditionCountArea.action?priceConditionId='+priceConditionId+'&id='+id,'priceConditionCountAreaForm','数量区间',720,480,countPriceTable,function(){
						var startCount = $("#startCount").val();
						var endCount = $("#endCount").val();
						if(Number(endCount) <= Number(startCount)){
							layer.alert("结束数量不能小于开始数量!");
							return false;
						}
					});
				}
			}
		}else{
			if ($("#salePriceForm").validationEngine('validate')) {
				var startTime = $("#startEffectiveTime").val() + " 00:00:01";;
				var endTime = $("#endEffectiveTime").val() + " 00:00:01";;
				var tag = dateTimeRange(startTime,endTime);
				if(!tag){
					layer.alert("结束时间不能小于开始时间!");
					return tag;
				}else{
					$("#salePriceForm").ajaxSubmit({
						type : "post",
						url : "${nvix}/nvixnt/mdm/nvixntSalePriceAction!saveOrUpdateInner.action",
						dataType : "text",
						success : function(data) {
							var d = data.split(':');
							if(d[1]){
								$("#id").val(d[1]);
								if(type == "price"){
									openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixntSalePriceAction!goSaveOrUpdatePriceConditionPriceArea.action?priceConditionId='+d[1]+'&id='+id,'priceConditionPriceAreaForm','价格区间',720,480,priceTable,function(){
										var startTotalAmount = $("#startTotalAmount").val();
										var endTotalAmount = $("#endTotalAmount").val();
										if(Number(endTotalAmount) <= Number(startTotalAmount)){
											layer.alert("结束价格不能小于开始价格!");
											return false;
										}
									});
								}else if(type == "count"){
									openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixntSalePriceAction!goSaveOrUpdatePriceConditionCountArea.action?priceConditionId='+d[1]+'&id='+id,'priceConditionCountAreaForm','数量区间',720,480,countPriceTable,function(){
										var startCount = $("#startCount").val();
										var endCount = $("#endCount").val();
										if(Number(endCount) <= Number(startCount)){
											layer.alert("结束数量不能小于开始数量!");
											return false;
										}
									});
								}
							}else{
								layer.alert(d[0]);
								return
							}
						}
					});
				}
			}
		}
	}
	
	//删除
	function deletePriceConditionEntity(id,type) {
		if(type == 'price'){
			deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixntSalePriceAction!deletePriceConditionPriceArea.action?id=' + id, '是否删除该价格区间?', priceTable);
		}else if(type == 'count'){
			deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixntSalePriceAction!deletePriceConditionCountArea.action?id=' + id, '是否删除该数量区间?', countPriceTable);
		}
	};
	/** 页面加载完调用 */
	pageSetUp();
</script>