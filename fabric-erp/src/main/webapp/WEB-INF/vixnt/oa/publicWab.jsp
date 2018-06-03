<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 办公 <span onclick="loadContent('wab','${nvix}/nvixnt/addressesAction!goViewWab.action');">&gt; 公共通讯录</span>
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="well well-sm">
				<div class="row">
					<div class="col-sm-12 col-md-12 col-lg-12">
						<div class="well well-light well-sm no-margin no-padding">
							<div class="row">
								<div class="col-sm-12">
									<div id="myCarousel" class="carousel fade profile-carousel">
										<ol class="carousel-indicators">
											<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
											<li data-target="#myCarousel" data-slide-to="1" class=""></li>
											<li data-target="#myCarousel" data-slide-to="2" class=""></li>
										</ol>
										<div class="carousel-inner">
											<!-- Slide 1 -->
											<div class="item active">
												<img src="${nvix}/vixntcommon/base/img/demo/s1.jpg" alt="demo user">
											</div>
											<!-- Slide 2 -->
											<div class="item">
												<img src="${nvix}/vixntcommon/base/img/demo/s2.jpg" alt="demo user">
											</div>
											<!-- Slide 3 -->
											<div class="item">
												<img src="${nvix}/vixntcommon/base/img/demo/m3.jpg" alt="demo user">
											</div>
										</div>
									</div>
								</div>

								<div class="col-sm-12">
									<div class="row">
										<div id="information">
											<div class="col-sm-3 profile-pic">
												<img src="${nvix}${wab.pictureurl}">
												<div class="padding-10">
													<h4 class="font-md">
														<small>${wab.position}</small> <br> <strong>职位与性别</strong>
													</h4>
												</div>
											</div>
											<div class="col-sm-5">
												<h1>
													<span>${wab.name}</span> <span> <s:if test="%{wab.wabtype == 0}">男</s:if> <s:elseif test="%{wab.wabtype == 1}">女</s:elseif>
													</span> <br> <small>${wab.company}</small>
												</h1>
												<ul class="list-unstyled">
													<li>
														<p class="text-muted">
															<i class="fa fa-map-marker"></i>&nbsp;&nbsp;<span class="txt-color-darken">${wab.langCode}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-phone"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${wab.mobileno}&nbsp;&nbsp;${wab.mobileno1} &nbsp;&nbsp;${wab.mobileno2}&nbsp;&nbsp;${wab.mobileno3} &nbsp;&nbsp;${wab.mobileno4}&nbsp;&nbsp;${wab.mobileno5} </span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-home"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${wab.calls}&nbsp;&nbsp;${wab.calls1} &nbsp;&nbsp;${wab.calls2}&nbsp;&nbsp;${wab.calls3} &nbsp;&nbsp;${wab.calls4}&nbsp;&nbsp;${wab.calls5} </span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-envelope"></i>&nbsp;&nbsp; <a href="mailto:simmons@smartadmin"> ${wab.email}&nbsp;&nbsp;${wab.email1} &nbsp;&nbsp;${wab.email2}&nbsp;&nbsp;${wab.email3} &nbsp;&nbsp;${wab.email4}&nbsp;&nbsp;${wab.email5} </a>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-qq"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${wab.qq}&nbsp;&nbsp;${wab.qq1} &nbsp;&nbsp;${wab.qq2}&nbsp;&nbsp;${wab.qq3} &nbsp;&nbsp;${wab.qq4}&nbsp;&nbsp;${wab.qq5} </span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-gear"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${wab.custom}&nbsp;&nbsp;${wab.custom1} &nbsp;&nbsp;${wab.custom2}&nbsp;&nbsp;${wab.custom3} &nbsp;&nbsp;${wab.custom4}&nbsp;&nbsp;${wab.custom5} </span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-reorder"></i>&nbsp;&nbsp; <span class="txt-color-darken"> <a href="javascript:void(0);" rel="tooltip" title="" data-placement="top" data-original-title="Create an Appointment"> <s:if test="%{wab.statuss == 0}">私有通讯簿</s:if> <s:elseif test="%{wab.statuss == 1}">公共通讯簿</s:elseif>
															</a>
															</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-calendar"></i>&nbsp;&nbsp; <span class="txt-color-darken"> <a href="javascript:void(0);" rel="tooltip" title="" data-placement="top" data-original-title="Create an Appointment"> <fmt:formatDate value="${wab.startTime}" type="both" pattern="yyyy-MM-dd" />
															</a>
															</span>
														</p>
													</li>
												</ul>
												<p class="font-md">
													<i>个人简介</i>
												</p>
												<p>${wab.memo}</p>
											</div>
										</div>
										<div class="col-sm-4">
											<h1>
												<small>联系人</small>
											</h1>
											<s:if test="wabList.size > 0">
												<s:iterator value="wabList" var="entity" status="s">
													<div class="user" title="" onclick="goInformation('${entity.id}');">
														<img src="${nvix}${entity.pictureurl}"> <a href="javascript:void(0);">${entity.name}</a>
														<div class="email">${entity.mobileno}</div>
													</div>
												</s:iterator>
											</s:if>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<hr>
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-align-right">
										<div class="page-title">
											<a href="#" class="btn btn-primary" onclick="saveOrUpdate('');">新增通讯录</a>
											<div class="btn-group">
												<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
													公共通讯录 <span class="caret"></span>
												</button>
												<ul class="dropdown-menu">
													<li><a href="javascript:void(0);" onclick="loadContent('wab','${nvix}/nvixnt/addressesAction!goList.action');">个人通讯录</a></li>
												</ul>
											</div>
										</div>
									</div>
									<div class="padding-10">

										<ul class="nav nav-tabs tabs-pull-right">
											<li><a href="#a1" data-toggle="tab">录入信息</a></li>
											<li class="active"><a href="#a2" data-toggle="tab">通讯薄</a></li>
										</ul>
										<div class="tab-content padding-top-10">
											<div class="tab-pane fade" id="a1">
												<div id="wabSearchForm" class="input-group input-group-lg">
													<form class="navbar-form navbar-left" role="search">
														姓名：
														<div class="form-group">
															<input class="form-control" type="text" value="" placeholder="姓名" id="searchCode">
														</div>
														公司：
														<div class="form-group">
															<input class="form-control" type="text" value="" placeholder="公司" id="searchCodeA">
														</div>
														地址：
														<div class="form-group">
															<input class="form-control" type="text" value="" placeholder="地址" id="searchCodeB">
														</div>
														职位：
														<div class="form-group">
															<input class="form-control" type="text" value="" placeholder="职位" id="searchCodeC">
														</div>
														<button onclick="wabTable.ajax.reload();" type="button" class="btn btn-info">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="$('#searchCode').val('');$('#searchCodeA').val('');$('#searchCodeB').val('');$('#searchCodeC').val('');wabTable.ajax.reload();" type="button" class="btn btn-default">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
													</form>
												</div>
												<table id="wab" class="table table-striped table-bordered table-hover">
													<thead>
														<tr>
															<th>编号</th>
															<th>姓名</th>
															<th>公司</th>
															<th>地址</th>
															<th>手机号</th>
															<th>工作电话</th>
															<th>状态</th>
															<th>性别</th>
															<th>操作</th>
														</tr>
													</thead>
												</table>
											</div>
											<div class="tab-pane fade in active" id="a2">
												<s:if test="wabList.size > 0">
													<s:iterator value="wabList" var="entity" status="s">
														<div class="user" title="" onclick="goInformation('${entity.id}');">
															<img src="${nvix}${entity.pictureurl}"> <a href="javascript:void(0);">${entity.name}</a>
															<div class="email">${entity.mobileno}</div>
														</div>
													</s:iterator>
												</s:if>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
//新增通讯录
function saveOrUpdate(id){
	openSaveOrUpdateWindow('${nvix}/nvixnt/addressesAction!goSaveOrUpdate.action?id=' + id, "wabForm", "新增通讯录", 800, 650, wabTable, null, function() {
		loadContent('wab', '${nvix}/nvixnt/addressesAction!goList.action');
	});
};

//删除
function deleteById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/addressesAction!deleteById.action?id='+id,'是否删除通讯录?',wabTable);
}

var wabColumns = [
	{"orderable":false,"data" : function(data) {return "&nbsp;";}},
	{"name":"name","data" : function(data) {return data.name;}},
	{"name":"company","data" : function(data) {return data.company;}},
	{"name":"langCode","data" : function(data) {return data.langCode;}},
	{"name":"mobileno","data" : function(data) {return data.mobileno;}},
	{"name":"calls","data" : function(data) {return data.calls;}},
	{"name":"statuss","data" : function(data) {return data.statuss== 0 ? '私有' :'公共';}},
	{"name":"wabtype","data" : function(data) {return data.wabtype== 0 ? '男' :'女';}},
	{"orderable":false,"data" : function(data) {
		var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a class='btn btn-xs btn-default' onclick=\"deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}}
];

var wabTable = initDataTable("wab", "${nvix}/nvixnt/addressesAction!goPublicWab.action", wabColumns, function(page, pageSize, orderField, orderBy) {
	var searchCode = $("#searchCode").val();
	var searchCodeA = $("#searchCodeA").val();
	var searchCodeB = $("#searchCodeB").val();
	var searchCodeC = $("#searchCodeC").val();
	return {
	"page" : page,
	"pageSize" : pageSize,
	"orderField" : orderField,
	"orderBy" : orderBy,
	"searchCode" : searchCode,
	"searchCodeA" : searchCodeA,
	"searchCodeB" : searchCodeB,
	"searchCodeC" : searchCodeC
	};
});

//查看人员信息
function goInformation(id){
	$.ajax({
		url:'${nvix}/nvixnt/addressesAction!goInformation.action?id='+id,
		cache : false,
		success : function(html) {
			$("#information").html(html);
		}
	});
}
</script>