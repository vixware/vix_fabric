<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>>客户管理  </span><span>>客户列表 </span>
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-12 text-align-right">
				<div class="btn-group">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('','enterPrise');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增客户
					</button>
					<!-- <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" onclick="saveOrUpdate('','enterPrise');">
						新增客户 <span class="caret"></span>
					</button>
					<<ul class="dropdown-menu js-status-update pull-left">
						<li><a href="javascript:void(0);" onclick="saveOrUpdate('','enterPrise');">新增企业客户</a></li>
						<li><a href="javascript:void(0);" onclick="saveOrUpdate('','internal');">新增内部客户</a></li>
						<li><a href="javascript:void(0);" onclick="saveOrUpdate('','personal');">新增个人客户</a></li>
					</ul> -->
				</div>
			</div>
		</div>
	</div>
	<section id="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="overflow: hidden; zoom: 1;">
				<div id="ecOrderAuditDiv" class="jarviswidget jarviswidget-color-darken" data-widget-colorbutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>客户列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="customerSearchForm" style="padding-bottom: 60px;">
								<form role="search" class="navbar-form navbar-left">
									<input type="hidden" id="syncTag" value="${syncTag}" />
									<div class="form-group">
										客户姓名: <input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName"> 
										客户编码: <input type="text" value="" placeholder="客户编码" class="form-control" id="searchCustomerCode">
									</div>
									<button onclick="customerTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchCustomerName').val('');$('#searchCustomerCode').val('');customerTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
									<button onclick="var index = layer.open({title:'高级检索',type: 1, area: ['700px', '580px'], content: $('#advanceSearch')});" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-search"></i> 高级检索
									</button>
								</form>
								<div id="advanceSearch" style="padding:20px 15px;display:none;">
									<form id="advanceSearchForm" class="form-horizontal">
										<fieldset>
											<div class="form-group">
												<lable class="col-md-2 control-label">简称:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="shortName" placeholder="简称" class="form-control"/>
												</div>
												<lable class="col-md-2 control-label">阶段:</lable>
												<div class="col-md-4"> 
													<select id="customerStageId" class="form-control">
														<option value="">------请选择------</option>
														<c:forEach items="${customerStageList}" var="entity">
															<option value="${entity.id}">${entity.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">种类:</lable>
												<div class="col-md-4"> 
													<select id="customerTypeId" class="form-control">
														<option value="">------请选择------</option>
														<c:forEach items="${customerTypeList}" var="entity">
															<option value="${entity.id}">${entity.name}</option>
														</c:forEach>
													</select>
												</div>
												<lable class="col-md-2 control-label">关系等级:</lable>
												<div class="col-md-4"> 
													<select id="relationshipClassId" class="form-control">
														<option value="">------请选择------</option>
														<c:forEach items="${relationshipClassList}" var="entity">
															<option value="${entity.id}">${entity.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">来源:</lable>
												<div class="col-md-4"> 
													<select id="customerSourceId" class="form-control">
														<option value="">------请选择------</option>
														<c:forEach items="${customerSourceList}" var="entity">
															<option value="${entity.id}">${entity.name}</option>
														</c:forEach>
													</select>
												</div>
												<lable class="col-md-2 control-label">行业:</lable>
												<div class="col-md-4"> 
													<select id="industryId" class="form-control">
														<option value="">------请选择------</option>
														<c:forEach items="${industryList}" var="entity">
															<option value="${entity.id}" >${entity.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">人员规模:</lable>
												<div class="col-md-4"> 
													<select id="staffScaleId" class="form-control">
														<option value="">------请选择------</option>
														<c:forEach items="${staffScaleList}" var="entity">
															<option value="${entity.id}">${entity.name}</option>
														</c:forEach>
													</select>
												</div>
												<lable class="col-md-2 control-label">热点程度:</lable>
												<div class="col-md-4"> 
													<select id="hotLevelId" class="form-control">
														<option value="">------请选择------</option>
														<c:forEach items="${hotLevelList}" var="entity">
															<option value="${entity.id}">${entity.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">价值评估:</lable>
												<div class="col-md-4"> 
													<select id="valueAssessment" class="form-control">
														<option value="">------请选择------</option>
														<option value="1" >高</option>
														<option value="2" >中</option>
														<option value="3" >低</option>
													</select>
												</div>
												<lable class="col-md-2 control-label">信用等级:</lable>
												<div class="col-md-4"> 
													<select id="creditLevel" class="form-control">
														<option value="">------请选择------</option>
														<option value="1" >高</option>
														<option value="2" >中</option>
														<option value="3" >低</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">所有者:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="employeeName" placeholder="所有者" class="form-control"/>
												</div>
												<lable class="col-md-2 control-label">电话传真:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="phoneFax" placeholder="电话传真" class="form-control validata[custom[onlyNumberSp]]"/>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">省份:</lable>
												<div class="col-md-4"> 
													<select id="provinceId" class="form-control" onchange="loadCity();">
														<option value="">------请选择------</option>
														<c:forEach items="${provinceList}" var="entity">
															<option value="${entity.id}">${entity.name}</option>
														</c:forEach>
													</select>
												</div>
												<lable class="col-md-2 control-label">城市:</lable>
												<div class="col-md-4"> 
													<select id="cityId" class="form-control">
														<option value="">------请选择------</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">创建开始日期:</label>
												<div class="col-md-4">
													<div class="input-group">
														<input id="createStartTime" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
														<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'createStartTime'});"><i class="fa fa-calendar"></i></span>
													</div>
												</div>
												<label class="col-md-2 control-label">创建结束日期:</label>
												<div class="col-md-4">
													<div class="input-group">
														<input id="createEndTime" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
														<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'createEndTime'});"><i class="fa fa-calendar"></i></span>
													</div>
												</div>
											</div>
											<!-- <div class="form-group">
												<label class="col-md-2 control-label">更新开始日期:</label>
												<div class="col-md-4">
													<div class="input-group">
														<input id="updateStartTime" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
														<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'updateStartTime'});"><i class="fa fa-calendar"></i></span>
													</div>
												</div>
												<label class="col-md-2 control-label">更新结束日期:</label>
												<div class="col-md-4">
													<div class="input-group">
														<input id="updateEndTime" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
														<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'updateEndTime'});"><i class="fa fa-calendar"></i></span>
													</div>
												</div>
											</div> -->
											<div class="form-group">
												<label class="col-md-2 control-label">停滞天数:</label>
												<div class="col-md-4">
													<div class="input-group">
														<div class="input-group-btn">
															<input id="btnType" type="hidden" value="">
															<button type="button" class="btn btn-default" tabindex="-1" id="btn">请选择</button>
															<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" tabindex="-1" aria-expanded="false">
																<span class="caret"></span>
															</button>
															<ul class="dropdown-menu" role="menu">
																<li><a href="javascript:void(0);" onclick="$('#btn').html('&lt;');$('#btnType').val('lt');">&lt;</a></li>
																<li><a href="javascript:void(0);" onclick="$('#btn').html('&gt;');$('#btnType').val('gt');">&gt;</a></li>
																<li><a href="javascript:void(0);" onclick="$('#btn').html('=');$('#btnType').val('eq');">=</a></li>
															</ul>
														</div>
														<input id="stagnateStartDay" value="" data-prompt-position="topLeft" class="form-control validate[custom[integer],min[1]]" type="text" />
													</div>
												</div>
												<label class="col-md-2 control-label">联系人:</label>
												<div class="col-md-4">
													<input type="text" value="" id="contactPersonName" placeholder="联系人" class="form-control"/>
												</div>
											</div>
											<div class="form-group" style="text-align:center;">
												<button onclick="advanceSearch();" type="button" class="btn btn-primary">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="clearSearchCondition('shortName,customerStageId,customerTypeId,relationshipClassId,customerSourceId,industryId,staffScaleId,hotLevelId,valueAssessment,creditLevel,employeeName,phoneFax,provinceId,cityId,createStartTime,createEndTime,updateStartTime,updateEndTime,stagnateStartDay,contactPersonName,customerTable');"
													type="button" class="btn btn-primary">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
												<button onclick="layer.closeAll('page');" type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-remove"></i> 关闭
												</button>
											</div>
										</fieldset>
									</form>
								</div>
							</div>
							<div class="jarviswidget" id="customerAccountTabs" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false" role="widget">
								<header>
									<ul class="nav nav-tabs pull-left in">
										<li <s:if test="syncTag == 'enterPrise'">class="active"</s:if>><a data-toggle="tab" href="#customerAccountDiv" onclick="$('#syncTag').val('enterPrise');customerTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">企业客户</span>
										</a></li>
										<!-- <li <s:if test="syncTag == 'internal'">class="active"</s:if>><a data-toggle="tab" href="#customerAccountDiv" onclick="$('#syncTag').val('internal');customerTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">内部客户</span>
										</a></li>
										<li <s:if test="syncTag == 'personal'">class="active"</s:if>><a data-toggle="tab" href="#customerAccountDiv" onclick="$('#syncTag').val('personal');customerTable.ajax.reload();"> <i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">个人客户</span>
										</a></li> -->
									</ul>
								</header>
								<div class="widget-body" style="padding: 0;">
									<div id="customerAccountDiv" class="tab-content">
										<div class="tab-pane no-padding active" id="customerAccountDiv">
											<table id="customerAccount" class="table table-striped table-bordered table-hover" width="100%">
												<thead>
													<tr>
														<th width="10%">编号</th>
														<th>客户编码</th>
														<th>客户名称</th>
														<!-- <th width="10">客户简称</th>
														<th width="10">热点程度</th> -->
														<th>客户种类</th>
														<!-- <th width="10">关系等级</th> -->
														<th>客户来源</th>
														<th>阶段</th>
														<th>所有者</th>
														<th>创建日期</th>
														<th>更新日期</th>
														<th>停滞天数</th>
														<th width="10%">操作</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
		<input id="employeeId" type="hidden" value="">
	</section>
</div>

<script type="text/javascript">
	var customerColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		if(data.type == 'personal'){
			return data.contactPerson.name;
		}else {
			return data.name;
		}
	}
	}, {
	"name" : "customerType",
	"data" : function(data) {
		return data.customerType == null ? '' : data.customerType.name;
	}
	}, {
	"name" : "customerSource",
	"data" : function(data) {
		return data.customerSource == null ? '' : data.customerSource.name;
	}
	}, {
	"name" : "customerStage",
	"data" : function(data) {
		return data.customerStage == null ? '' : data.customerStage.name;
	}
	}, {
	"name" : "employee",
	"data" : function(data) {
		return data.employee == null ? '无' : data.employee.name;
	}
	}, {
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"name" : "updateTime",
	"data" : function(data) {
		return data.updateTimeStr;
	}
	}, {
	"name" : "stagnateDay",
	"data" : function(data) {
		if(data.stagnateDay != 0){
			return data.stagnateDay+"天";
		}else{
			return "";
		}
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.id == null) {
			return '';
		}
		var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "','" + data.type + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		//var receive = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"receive('" + data.id + "');\" title='领取'><span class='txt-color-blue pull-right'><i class='fa fa-user'></i></span></a>";
		var view = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"view('" + data.id + "','" + data.type + "');\" title='客户视图'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var release = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"release('" + data.id + "');\" title='释放'><span class='txt-color-red pull-right'><i class='fa fa-undo'></i></span></a>";
		/* if(data.isReceive == '1'){
			return update + "&nbsp;&nbsp;" + view + "&nbsp;&nbsp;" + release + "&nbsp;&nbsp;" + del;
		}else{
			return update + "&nbsp;&nbsp;" + view + "&nbsp;&nbsp;" + receive + "&nbsp;&nbsp;" + del;
		} */
		return update + "&nbsp;&nbsp;" + view + "&nbsp;&nbsp;" + release + "&nbsp;&nbsp;" + del;
	}
	} ];

	var customerTable = initDataTable("customerAccount", "${nvix}/nvixnt/nvixCustomerAction!goListContentJson.action", customerColumns, function(page, pageSize, orderField, orderBy) {
		var syncTag = $("#syncTag").val();
		var name = $("#searchCustomerName").val();
		var shortName = $("#shortName").val();
		name = Url.encode(name);
		shortName = Url.encode(shortName);
		var code = $("#searchCustomerCode").val();
		code = Url.encode(code);
		var parentId = $("#id").val();
		var customerStageId = $("#customerStageId").val();
		var customerTypeId = $("#customerTypeId").val();
		var relationshipClassId = $("#relationshipClassId").val();
		var customerSourceId = $("#customerSourceId").val();
		var industryId = $("#industryId").val();
		var staffScaleId = $("#staffScaleId").val();
		var hotLevelId = $("#hotLevelId").val();
		var valueAssessment = $("#valueAssessment").val();
		var creditLevel = $("#creditLevel").val();
		var employeeName = $("#employeeName").val();
		employeeName = Url.encode(employeeName);
		var phoneFax = $("#phoneFax").val();
		phoneFax = Url.encode(phoneFax);
		var provinceId = $("#provinceId").val();
		var cityId = $("#cityId").val();
		var createStartTime = $("#createStartTime").val();
		var createEndTime = $("#createEndTime").val();
		var updateStartTime = $("#updateStartTime").val();
		var updateEndTime = $("#updateEndTime").val();
		var stagnateStartDay = $("#stagnateStartDay").val();
		var btnType = $("#btnType").val();
		var contactPersonName = $("#contactPersonName").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"customerStageId" : customerStageId,
		"customerTypeId" : customerTypeId,
		"relationshipClassId" : relationshipClassId,
		"customerSourceId" : customerSourceId,
		"industryId" : industryId,
		"staffScaleId" : staffScaleId,
		"hotLevelId" : hotLevelId,
		"valueAssessment" : valueAssessment,
		"creditLevel" : creditLevel,
		"provinceId" : provinceId,
		"cityId" : cityId,
		"createStartTime" : createStartTime,
		"createEndTime" : createEndTime,
		"updateStartTime" : updateStartTime,
		"updateEndTime" : updateEndTime,
		"stagnateStartDay" : stagnateStartDay,
		"btnType" : btnType,
		"contactPersonName" : contactPersonName,
		"syncTag" : syncTag,
		"name" : name,
		"shortName" : shortName,
		"employeeName" : employeeName,
		"phoneFax" : phoneFax,
		"code" : code
		};
	});

	//新增编辑
	function saveOrUpdate(id, syncTag) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixCustomerAction!goSaveOrUpdate.action?id=' + id + "&customerAccountType=" + syncTag);
	};

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerAction!deleteById.action?id=' + id, '是否删除该客户?', customerTable);
	}
	
	/** 领取客户  */
	function receive(id) {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee',function(){
			var employeeId = $("#employeeId").val();
			if(!employeeId){
				layer.alert("请选择销售员");
				return;
			}else{
				$.ajax({
					type : "post",
					url : "${nvix}/nvixnt/nvixCustomerAction!receive.action",
					data : {
						"id" : id,
						"employeeId" : employeeId
					},
					cache : false,
					success : function(result) {
						var r = result.split(":");
						if(r[0] != '0'){
							customerTable.ajax.reload();
						}else{
							layer.alert(r[1]);
							return ;
						}
					}
				})
			}
		});
	}
	
	function release(id) {
		layer.confirm("是否释放客户", {title:"提示信息"}, function(index){
			var loadIndex = layer.load(2);
			$.ajax({
				type : "post",
				url : "${nvix}/nvixnt/nvixCustomerAction!release.action",
				data : {
					"id" : id
				},
				cache : false,
				success : function(result) {
					/** 关闭遮盖层 */
					layer.close(loadIndex);
					var r = result.split(":");
					if(r[0] != '0'){
						customerTable.ajax.reload();
					}else{
						layer.alert(r[1]);
						return ;
					}
				}
			})
			layer.close(index);
		});
	}
	
	// 客户视图
	function view(id,syncTag){
		$.ajax({
			url : '${nvix}/nvixnt/nvixCustomerAction!view.action?id=' + id + "&customerAccountType=" + syncTag + "&source=list",
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
		});
	}
	
	function loadCity(){
		var provinceId = $("#provinceId").val();
		$("#cityId").load("${nvix}/nvixnt/system/nvixntAddressInfoAction!loadSubAddressInfo.action?parentId="+provinceId);
		$("#districtId").val("");
	}
	
	$("#advanceSearchForm").validationEngine();
	function advanceSearch(){
		//表单验证
		if($("#advanceSearchForm").validationEngine('validate')){
			customerTable.ajax.reload();
			layer.closeAll('page');
		}
	}

	/** 页面加载完调用 */
	pageSetUp();
</SCRIPT>