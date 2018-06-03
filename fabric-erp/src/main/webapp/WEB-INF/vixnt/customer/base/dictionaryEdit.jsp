<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="main" role="main">
	<div id="ribbon">
		<span class="ribbon-button-alignment"> 
			<span id="refresh" class="btn btn-ribbon" data-action="resetWidgets" data-title="refresh" rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true"> 
				<i class="fa fa-refresh"></i>
			</span>
		</span>
		<ol class="breadcrumb">
			<li>首页</li>
			<li>返回</li>
		</ol>
	</div>
	<div id="content">
		<div class="row">
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark">
					<i class="fa fa-table fa-fw "></i>客户关系管理 <span>>客户管理</span>
				</h1>
			</div>
			<div class="col-lg-8 text-align-right">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-12 text-align-right">
					<div class="btn-group">
						<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">新增客户 <span class="caret"></span></button>
						<ul class="dropdown-menu js-status-update pull-left">
							<li><a href="javascript:void(0);" onclick="saveOrUpdate('','enterPrise');">新增企业客户</a></li>
							<li><a href="javascript:void(0);" onclick="saveOrUpdate('','internal');">新增内部客户</a></li>
							<li><a href="javascript:void(0);" onclick="saveOrUpdate('','personal');">新增个人客户</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<section id="">
			<div style="overflow: hidden; position: relative; padding: 0;">
				<article style="overflow: hidden; zoom: 1;">
					<div id="ecOrderAuditDiv" class="jarviswidget jarviswidget-color-darken" data-widget-colorbutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
						<header>
							<span class="widget-icon"> <i class="fa fa-table"></i> </span>
							<h2>客户列表</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div id="customerSearchForm" style="padding-bottom: 60px;">
									<form role="search" class="navbar-form navbar-left">
										<input type="hidden" id="syncTag" value="${syncTag}"/>
										<div class="form-group">
											客户名称: <input type="text" value="" placeholder="客户名称" class="form-control" id="searchCustomerName">
											 客户编码: <input type="text" value="" placeholder="客户编码" class="form-control" id="searchCustomerCode">
										</div>
										<button onclick="customerTable.ajax.reload();" type="button" class="btn btn-info">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchCustomerName').val('');$('#searchCustomerCode').val('');customerTable.ajax.reload();" type="button" class="btn btn-default">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
								<div class="jarviswidget" id="customerAccountTabs" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false" role="widget">
									<header>
										<ul class="nav nav-tabs pull-left in">
											<li <s:if test="syncTag == 'enterPrise'">class="active"</s:if>>
												<a data-toggle="tab" href="#customerAccountDiv" onclick="$('#syncTag').val('enterPrise');customerTable.ajax.reload();">
													<i class="fa fa-list-alt"></i>
													<span class="hidden-mobile hidden-tablet">企业客户</span> 
												</a>
											</li>
											<li <s:if test="syncTag == 'internal'">class="active"</s:if>>
												<a data-toggle="tab" href="#customerAccountDiv" onclick="$('#syncTag').val('internal');customerTable.ajax.reload();">
													<i class="fa fa-list-alt"></i>
													<span class="hidden-mobile hidden-tablet">内部客户</span> 
												</a>
											</li>
											<li <s:if test="syncTag == 'personal'">class="active"</s:if>>
												<a data-toggle="tab" href="#customerAccountDiv" onclick="$('#syncTag').val('personal');customerTable.ajax.reload();">
													<i class="fa fa-list-alt"></i>
													<span class="hidden-mobile hidden-tablet">个人客户</span> 
												</a>
											</li>
										</ul>
									</header>
									<div class="widget-body" style="padding: 0;">
										<div id="customerAccountDiv" class="tab-content">
											<div class="tab-pane no-padding active" id="customerAccountDiv">
												<table id="customerAccount" class="table table-striped table-bordered table-hover" width="100%">
											   		<thead>			                
														<tr>
															<th width="10">编号</th>
															<th width="10">客户编码</th>
															<th width="10">客户名称</th>
															<th width="10">客户简称</th>
															<th width="10">热点程度</th>
															<th width="10">客户种类</th>
															<th width="10">关系等级</th>
															<th width="10">客户来源</th>
															<th width="10">阶段</th>
															<th width="10">操作</th>
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
		</section>
	</div>
</div>

<script type="text/javascript">
	var customerColumns = [
			{"orderable":false,"data" : function(data) {return "&nbsp;";}},
			{"name":"code","data" : function(data) {return data.code;}},
			{"name":"name","data" : function(data) {return data.name;}},
			{"name":"shorterName","data" : function(data) {return data.shorterName;}},
			{"name":"hotLevel","data" : function(data) {return data.hotLevel == null ? '' : data.hotLevel.name;}},
			{"name":"customerType","data" : function(data) {return data.customerType == null ? '' : data.customerType.name;}},
			{"name":"relationshipClass","data" : function(data) {return data.relationshipClass == null ? '' : data.relationshipClass.name;}},
			{"name":"customerSource","data" : function(data) {return data.customerSource == null ? '' : data.customerSource.name;}},
			{"name":"customerStage","data" : function(data) {return data.customerStage == null ? '' : data.customerStage.name;}},
			{"orderable":false,"data" : function(data) {
				if(data.id == null){return '';}
				var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "','" + data.type + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
			}}
		];
	
	    var customerTable = initDataTable("customerAccount","${nvix}/nvixnt/nvixCustomerAction!goListContentJson.action",customerColumns,function(page,pageSize,orderField,orderBy){
		    var syncTag = $("#syncTag").val();
		    var name = $("#searchCustomerName").val();
		    name = Url.encode(name);
		    var code = $("#searchCustomerCode").val();
		    code = Url.encode(code);
			var parentId = $("#id").val();
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"parentId":parentId,"syncTag":syncTag,"name":name,"code":code};
	});
	
	//新增编辑
	function saveOrUpdate(id,syncTag){
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixCustomerAction!goSaveOrUpdate.action?id=' + id + "&customerAccountType=" + syncTag);
	};
	
	//删除
	function deleteById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerAction!deleteById.action?id='+id,'是否删除该客户?',customerTable);
	}

	
	/** 页面加载完调用 */
	pageSetUp();
</SCRIPT>