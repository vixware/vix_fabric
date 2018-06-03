<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 办公 <span>&gt; 个人通讯录</span>
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
											<div class="item active">
												<img src="${nvix}/vixntcommon/base/img/demo/s1.jpg" alt="demo user">
											</div>
											<div class="item">
												<img src="${nvix}/vixntcommon/base/img/demo/s2.jpg" alt="demo user">
											</div>
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
												<img src="${employee.headImgUrl}">
												<div class="padding-10">
													<h4 class="font-md">
														<small>${employee.organizationUnitName}</small> <br> <strong>部门</strong>
													</h4>
												</div>
											</div>
											<div class="col-sm-5">
												<h1>
													<span>${employee.name}</span> <span> <s:if test="%{employee.wabtype == 0}">男</s:if> <s:elseif test="%{employee.wabtype == 1}">女</s:elseif>
													</span>
												</h1>
												<ul class="list-unstyled">
													<li>
														<p class="text-muted">
															<i class="fa fa-phone"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${employee.mobile}&nbsp;&nbsp;</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-envelope"></i>&nbsp;&nbsp; <a href=""> ${employee.email}&nbsp;&nbsp;</a>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-qq"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${employee.qq}&nbsp;&nbsp;</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-weixin"></i>&nbsp;&nbsp; <span class="txt-color-darken"> ${employee.weixinid}&nbsp;&nbsp; </span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<i class="fa fa-calendar"></i>&nbsp;&nbsp; <span class="txt-color-darken"> <a href="javascript:void(0);" rel="tooltip" title="" data-placement="top" data-original-title="Create an Appointment"> <fmt:formatDate value="${employee.startTime}" type="both" pattern="yyyy-MM-dd" />
															</a>
															</span>
														</p>
													</li>
												</ul>
												<p class="font-md">
													<i>个人简介</i>
												</p>
												<p>${employee.memo}</p>
											</div>
										</div>
										<div class="col-sm-4">
											<h1>
												<small>联系人</small>
											</h1>
											<s:if test="employeeList.size > 0">
												<s:iterator value="employeeList" var="entity" status="s">
													<div style="cursor: pointer;" class="user" title="" onclick="goInformation('${entity.id}');">
														<img src="${entity.headImgUrl}"> <a href="javascript:void(0);">${entity.name}</a>
														<div class="email">${entity.email}</div>
													</div>
												</s:iterator>
											</s:if>
										</div>
									</div>
								</div>
								<div class="col-sm-12">
									<div id="" class="jarviswidget" style="padding: 20px 20px;">
										<header>
											<span class="widget-icon"><i class="fa fa-table"></i></span>
											<h2>通讯录</h2>
										</header>
										<div>
											<div class="widget-body no-padding">
												<div style="margin: 5px;">
													<div class="form-group" style="margin: 0 0px;">
														<div class="col-md-3">
															<input type="text" value="" placeholder="姓名" class="form-control" id="searchCode">
														</div>
														<button onclick="wabTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="$('#searchCode').val('');wabTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
														<div class="listMenu-float-right">
															<button onclick="saveOrUpdate('','新增');" type="button" class="btn btn-primary">
																<i class="glyphicon glyphicon-plus"></i>
																<s:text name="新增通讯录" />
															</button>
														</div>
													</div>
												</div>
												<table id="wab" class="table table-striped table-bordered table-hover" width="100%"></table>
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
	function saveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/addressesAction!goSaveOrUpdate.action?id=' + id, "wabForm", "新增通讯录", 800, 650, wabTable);
	};

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/addressesAction!deleteById.action?id=' + id, '是否删除通讯录?', wabTable);
	};

	var wabColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"name" : "currentResidence",
	"data" : function(data) {
		return data.currentResidence;
	}
	}, {
	"name" : "mobile",
	"data" : function(data) {
		return data.mobile;
	}
	}, {
	"name" : "weixinid",
	"data" : function(data) {
		return data.weixinid;
	}
	}, {
	"name" : "telephone",
	"data" : function(data) {
		return data.telephone;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var wabTable = initDataTable("wab", "${nvix}/nvixnt/addressesAction!goWab.action", wabColumns, function(page, pageSize, orderField, orderBy) {
		var searchCode = $("#searchCode").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchCode" : searchCode
		};
	});

	//查看人员信息
	function goInformation(id) {
		$.ajax({
		url : '${nvix}/nvixnt/addressesAction!goInformation.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#information").html(html);
		}
		});
	};
</script>