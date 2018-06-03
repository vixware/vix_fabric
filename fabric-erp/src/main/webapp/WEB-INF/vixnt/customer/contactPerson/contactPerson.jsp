<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 联系人管理 </span><span>> 联系人 </span>
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget jarviswidget-color-darken" id="conTag" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>联系人列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="contactPersonSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										联系人姓名:<input type="text" value="" placeholder="联系人姓名" class="form-control" id="searchCPName"> 
										联系人姓氏首字母:<input type="text" value="" placeholder="联系人姓氏首字母" class="form-control" id="searchFirstName"> 
										客户姓名:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCAName">
									</div>
									<button onclick="contactPersonTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchCPName').val('');$('#searchFirstName').val('');$('#searchCAName').val('');contactPersonTable.ajax.reload();" type="button" class="btn btn-default">
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
												<lable class="col-md-2 control-label">性别:</lable>
												<div class="col-md-4"> 
													<select id="sex" class="form-control">
														<option value="">------请选择------</option>
														<option value="1">男</option>
														<option value="0">女</option>
													</select>
												</div>
												<lable class="col-md-2 control-label">职务:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="position" placeholder="职务" class="form-control"/>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">称谓:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="callTitle" placeholder="称谓" class="form-control"/>
												</div>
												<lable class="col-md-2 control-label">部门:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="department" placeholder="部门" class="form-control"/>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">联系人类型:</lable>
												<div class="col-md-4"> 
													<select id="crmContactTypeId" class="form-control">
														<option value="">------请选择------</option>
														<c:forEach items="${crmContactTypeList}" var="entity">
															<option value="${entity.id}">${entity.name}</option>
														</c:forEach>
													</select>
												</div>
												<lable class="col-md-2 control-label">联系人分类:</lable>
												<div class="col-md-4"> 
													<select id="contactPersonTypeId" class="form-control">
														<option value="">------请选择------</option>
														<c:forEach items="${contactPersonTypeList}" var="entity">
															<option value="${entity.id}">${entity.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">负责业务:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="presideBusiness" placeholder="负责业务" class="form-control"/>
												</div>
												<lable class="col-md-2 control-label">工作电话:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="directPhone" placeholder="工作电话" class="form-control validate[custom[phone]]"/>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">手机:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="mobile" placeholder="手机" class="form-control validate[custom[phone]]"/>
												</div>
												<lable class="col-md-2 control-label">邮件:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="email" placeholder="邮件" class="form-control validate[custom[email]]"/>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">家庭电话:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="phoneHome" placeholder="家庭电话" class="form-control validate[custom[phone]]"/>
												</div>
												<lable class="col-md-2 control-label">家庭住址:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="address" placeholder="家庭住址" class="form-control"/>
												</div>
											</div>
											<div class="form-group">
												<lable class="col-md-2 control-label">微信:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="msnAccount" placeholder="微信" class="form-control"/>
												</div>
												<lable class="col-md-2 control-label">QQ:</lable>
												<div class="col-md-4"> 
													<input type="text" value="" id="qqAccount" placeholder="QQ" class="form-control"/>
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
											<div class="form-group">
												<label class="col-md-2 control-label">生日开始日期:</label>
												<div class="col-md-4">
													<div class="input-group">
														<input id="birthdayStartTime" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
														<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthdayStartTime'});"><i class="fa fa-calendar"></i></span>
													</div>
												</div>
												<label class="col-md-2 control-label">生日结束日期:</label>
												<div class="col-md-4">
													<div class="input-group">
														<input id="birthdayEndTime" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
														<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthdayEndTime'});"><i class="fa fa-calendar"></i></span>
													</div>
												</div>
											</div>
											<div class="form-group" style="text-align:center;">
												<button onclick="advanceSearch();" type="button" class="btn btn-primary">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="clearSearchCondition('sex,position,callTitle,department,crmContactTypeId,presideBusiness,contactPersonTypeId,directPhone,mobile,email,phoneHome,address,msnAccount,qqAccount,createStartTime,createEndTime,birthdayStartTime,birthdayEndTime,contactPersonTable');"
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
							<table id="contactPerson" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="5%">编号</th>
										<th width="15%">客户</th>
										<th width="15%">联系人姓名</th>
										<th width="20%">负责业务</th>
										<th width="15%">邮件</th>
										<th width="10%">联系人类型</th>
										<th width="10%">电话</th>
										<th width="15%">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var contactPersonColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return '&nbsp;';
	}
	}, {
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccountName;
	}
	}, {
	"name" : "lastName",
	"data" : function(data) {
		return data.lastName == null ? data.customerAccount == null ? '' : data.customerAccount.type == 'personal' ? data.customerAccount.name : '' : data.firstName == null ? data.lastName : data.lastName + data.firstName;
	}
	}, {
	"name" : "presideBusiness",
	"data" : function(data) {
		return data.presideBusiness;
	}
	}, {
	"name" : "email",
	"data" : function(data) {
		return data.email;
	}
	}, {
	"name" : "crmContactType",
	"data" : function(data) {
		return "<span class='label label-info'>"+data.crmContactTypeName+"</span>";
	}
	}, {
	"name" : "mobile",
	"data" : function(data) {
		return data.mobile;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.id == null) {
			return "";
		}
		var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var contactPersonTable = initDataTable("contactPerson", "${nvix}/nvixnt/nvixContactPersonAction!goListContent.action", contactPersonColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchCPName").val();
		var parentName = $("#searchCAName").val();
		var firstName = $("#searchFirstName").val();
		name = Url.encode(name);
		firstName = Url.encode(firstName);
		parentName = Url.encode(parentName);
		var sex = $("#sex").val();
		var position = $("#position").val();
		position = Url.encode(position);
		var callTitle = $("#callTitle").val();
		callTitle = Url.encode(callTitle);
		var department = $("#department").val();
		department = Url.encode(department);
		var crmContactTypeId = $("#crmContactTypeId").val();
		var presideBusiness = $("#presideBusiness").val();
		presideBusiness = Url.encode(presideBusiness);
		var contactPersonTypeId = $("#contactPersonTypeId").val();
		var directPhone = $("#directPhone").val();
		directPhone = Url.encode(directPhone);
		var mobile = $("#mobile").val();
		mobile = Url.encode(mobile);
		var email = $("#email").val();
		email = Url.encode(email);
		var phoneHome = $("#phoneHome").val();
		phoneHome = Url.encode(phoneHome);
		var address = $("#address").val();
		address = Url.encode(address);
		var msnAccount = $("#msnAccount").val();
		msnAccount = Url.encode(msnAccount);
		var qqAccount = $("#qqAccount").val();
		qqAccount = Url.encode(qqAccount);
		var createStartTime = $("#createStartTime").val();
		var createEndTime = $("#createEndTime").val();
		var birthdayStartTime = $("#birthdayStartTime").val();
		var birthdayEndTime = $("#birthdayEndTime").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"sex" : sex,
		"position" : position,
		"callTitle" : callTitle,
		"department" : department,
		"crmContactTypeId" : crmContactTypeId,
		"presideBusiness" : presideBusiness,
		"contactPersonTypeId" : contactPersonTypeId,
		"directPhone" : directPhone,
		"mobile" : mobile,
		"email" : email,
		"phoneHome" : phoneHome,
		"address" : address,
		"msnAccount" : msnAccount,
		"qqAccount" : qqAccount,
		"createStartTime" : createStartTime,
		"createEndTime" : createEndTime,
		"birthdayStartTime" : birthdayStartTime,
		"birthdayEndTime" : birthdayEndTime,
		"name" : name,
		"firstName" : firstName,
		"parentName" : parentName
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixContactPersonAction!goSaveOrUpdate.action?id=' + id + '&source=contactPerson');
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixContactPersonAction!deleteById.action?id=' + id, '是否删除该联系人?', contactPersonTable);
	}
	
	$("#advanceSearchForm").validationEngine();
	function advanceSearch(){
		//表单验证
		if($("#advanceSearchForm").validationEngine('validate')){
			contactPersonTable.ajax.reload();
			layer.closeAll('page');
		}
	}

	pageSetUp();
</SCRIPT>