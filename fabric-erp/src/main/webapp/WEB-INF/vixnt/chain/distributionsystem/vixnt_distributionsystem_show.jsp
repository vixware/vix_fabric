<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-user"></i> 连锁管理 <span>&gt; 连锁店铺管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('drp_distributionsystem','${nvix}/nvixnt/vixntDistributionSystemAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12 col-md-12">
			<div class="well">
				<input type="hidden" id="channelDistributorId" value="${channelDistributor.id}" name="channelDistributor.id" />
				<!-- 头部会员信息内容部分 -->
				<div class="row">
					<div class="col-xs-12">
						<div class="media search-media">
							<div class="media-left">
								<a href="#"> <img style="width: 130px; height: 130px;" class="media-object" src="${nvix}/vixntcommon/base/img/md.jpg">
								</a>
							</div>

							<div class="media-body">
								<div class="row">
									<div class="col-xs-5">
										<h4 class="media-heading">
											<span>${channelDistributor.name}</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span>短信余额：</span> <span class="reds">￥5000</span>
										</h4>
									</div>
								</div>
								<p>
									<span>联系电话：${channelDistributor.telephone}</span>
								</p>
								<p>
									<span>地址：</span><a href=""><span class="reds">${channelDistributor.address}</span></a>
								</p>
								<p>
									<a href=""><span class="btn btn-primary btn-sm" data-rel="tooltip" title="" data-original-title="">服务类型</span></a>
								</p>
								<div class="search-actions text-center">
									<div class="space-10"></div>
									<a class="btn btn-sm btn-block btn-primary" onclick="goMessage('${channelDistributor.id}');">短信充值</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 菜单选项切换部分 -->
				<div class="row">
					<div class="col-xs-12">
						<div class="tab_menu">
							<ul>
								<li class="selected">门店资料</li>
								<li onclick="employeeTable.ajax.reload();">员工列表</li>
								<li onclick="rechargeRecordTable.ajax.reload();">储值记录</li>
								<li onclick="messageSendRecordTable.ajax.reload();">短信发送记录</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="tab-cons">
							<div class="common">
								<div class="widget-body">
									<form class="form-horizontal" id="customerAccountForm">
										<fieldset>
											<legend>基本信息:</legend>
											<div class="form-group">
												<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
												<div class="col-md-3">
													<input id="code" name="channelDistributor.code" value="${channelDistributor.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
												</div>
												<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
												<div class="col-md-3">
													<input id="name" name="channelDistributor.name" value="${channelDistributor.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"> 简称:</label>
												<div class="col-md-3">
													<input id="shortName" name="channelDistributor.shortName" value="${channelDistributor.shortName}" data-prompt-position="topLeft" class="form-control" type="text" />
												</div>
												<label class="col-md-2 control-label"> 法人:</label>
												<div class="col-md-3">
													<input id="artificialPerson" name="channelDistributor.artificialPerson" value="${channelDistributor.artificialPerson}" data-prompt-position="topLeft" class="form-control" type="text" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"> 负责人: </label>
												<div class="col-md-3">
													<div class="row">
														<div class="col-sm-12">
															<div class="input-group">
																<input id="employeeName" value="${channelDistributor.employee.name }" class="form-control" type="text" readonly="readonly" />
																<div class="input-group-btn">
																	<button onclick="chooseEmployee();" type="button" class="btn btn-info">
																		<i class="glyphicon glyphicon-search"></i>
																	</button>
																	<button onclick="$('#employeeId').val('');$('#employeeName').val('');" type="button" class="btn btn-default">
																		<i class="glyphicon glyphicon-repeat"></i>
																	</button>
																</div>
															</div>
														</div>
													</div>
												</div>
												<label class="col-md-2 control-label"> 邮箱:</label>
												<div class="col-md-3">
													<div class="input-group">
														<input id="email" name="channelDistributor.email" value="${channelDistributor.email}" data-prompt-position="topLeft" class="form-control validate[custom[email]]" type="text" /> <span class="input-group-addon"><i class="fa fa-envelope-o"></i> </span>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"><span class="text-danger">*</span> 联系人:</label>
												<div class="col-md-3">
													<input id="contacts" name="channelDistributor.contacts" value="${channelDistributor.contacts}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
												</div>
												<label class="col-md-2 control-label"><span class="text-danger">*</span> 联系电话:</label>
												<div class="col-md-3">
													<div class="input-group">
														<input id="telephone" name="channelDistributor.telephone" value="${channelDistributor.telephone}" data-prompt-position="topLeft" class="form-control validate[required,custom[phone]]" type="text" /> <span class="input-group-addon"><i class="fa fa-phone"></i></span>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"> 所属行业:</label>
												<div class="col-md-3">
													<select id="industry" name="channelDistributor.industry" class="form-control">
														<option value="1" <c:if test='${channelDistributor.industry eq "1"}'>selected="selected"</c:if>>商贸流通</option>
														<option value="2" <c:if test='${channelDistributor.industry eq "2"}'>selected="selected"</c:if>>生产</option>
													</select>
												</div>
												<label class="col-md-2 control-label"> 员工人数:</label>
												<div class="col-md-3">
													<div class="row">
														<div class="col-sm-12">
															<div class="input-group">
																<input id="employeeCounts" name="channelDistributor.employeeCounts" value="${channelDistributor.employeeCounts }" type="text" class="form-control validate[custom[integer]]"> <span class="input-group-addon">(个)</span>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"> 类型:</label>
												<div class="col-md-3">
													<select id="type" name="channelDistributor.type" class="form-control">
														<option value="1" <c:if test='${channelDistributor.type eq 1}'>selected="selected"</c:if>>渠道</option>
														<option value="2" <c:if test='${channelDistributor.type eq 2}'>selected="selected"</c:if>>经销商</option>
														<option value="3" <c:if test='${channelDistributor.type eq 3}'>selected="selected"</c:if>>代理商</option>
														<option value="4" <c:if test='${channelDistributor.type eq 4}'>selected="selected"</c:if>>门店</option>
													</select>
												</div>
												<label class="control-label col-md-2"> 注册资金:</label>
												<div class="col-md-3">
													<div class="row">
														<div class="col-sm-12">
															<div class="input-group">
																<input class="form-control validate[custom[integer]]" id="registeredCapital" name="channelDistributor.registeredCapital" value="${channelDistributor.registeredCapital }" type="text"> <span class="input-group-addon">(¥)</span>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"><span class="text-danger">*</span> 币种:</label>
												<div class="col-md-3">
													<select id="currencyTypeId" name="channelDistributor.currencyType.id" class="form-control">
														<c:forEach items="${currencyTypeList}" var="entity">
															<option value="${entity.id}" <c:if test="${channelDistributor.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
														</c:forEach>
													</select>
												</div>
												<label class="col-md-2 control-label"> 业务员: </label>
												<div class="col-md-3">
													<div class="row">
														<div class="col-sm-12">
															<div class="input-group">
																<input id="salesEmployeeName" value="${channelDistributor.salesEmployee.name }" class="form-control" type="text" readonly="readonly" />
																<div class="input-group-btn">
																	<button onclick="chooseSalesEmployee();" type="button" class="btn btn-info">
																		<i class="glyphicon glyphicon-search"></i>
																	</button>
																	<button onclick="$('#salesEmployeeId').val('');$('#salesEmployeeName').val('');" type="button" class="btn btn-default">
																		<i class="glyphicon glyphicon-repeat"></i>
																	</button>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"> <span class="text-danger">*</span>是否启用:
												</label>
												<div class="col-md-3">
													<div data-toggle="buttons" class="btn-group">
														<label class="btn btn-default <s:if test="channelDistributor.status == 0">active</s:if>"> <input type="radio" value="0" id="status" name="channelDistributor.status" <s:if test="channelDistributor.status == 0">checked="checked"</s:if> class="validate[required]">是
														</label> <label class="btn btn-default <s:if test="channelDistributor.status == 1">active</s:if>"> <input type="radio" value="1" id="status" name="channelDistributor.status" <s:if test="channelDistributor.status == 1">checked="checked"</s:if>>否
														</label>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"><span class="text-danger">*</span> 地址:</label>
												<div class="col-md-8">
													<input id="address" name="channelDistributor.address" value="${channelDistributor.address}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
												</div>
											</div>
											<legend>其他信息:</legend>
											<div class="form-group">
												<label class="col-md-2 control-label"> 营业执照编号:</label>
												<div class="col-md-3">
													<input id="permit" name="channelDistributor.permit" value="${channelDistributor.permit}" data-prompt-position="topLeft" class="form-control" type="text" />
												</div>
												<label class="col-md-2 control-label"> 税号:</label>
												<div class="col-md-3">
													<input id="taxCode" name="channelDistributor.taxCode" value="${channelDistributor.taxCode}" data-prompt-position="topLeft" class="form-control" type="text" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"> 开户银行:</label>
												<div class="col-md-3">
													<input id="openingBank" name="channelDistributor.openingBank" value="${channelDistributor.openingBank}" data-prompt-position="topLeft" class="form-control" type="text" />
												</div>
												<label class="col-md-2 control-label"> 银行账号:</label>
												<div class="col-md-3">
													<input id="bankAccount" name="channelDistributor.bankAccount" value="${channelDistributor.bankAccount}" data-prompt-position="topLeft" class="form-control" type="text" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label"> 地图链接:</label>
												<div class="col-md-3">
													<input id="maplink" name="channelDistributor.maplink" value="${channelDistributor.maplink}" data-prompt-position="topLeft" class="form-control" type="text" />
												</div>
												<label class="col-md-2 control-label"> 所属地区:</label>
												<div class="col-md-3">
													<input id="region" name="channelDistributor.region" value="${channelDistributor.region}" data-prompt-position="topLeft" class="form-control" type="text" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">备注:</label>
												<div class="col-md-8">
													<textarea id="memo" name="channelDistributor.memo" class="form-control" rows="4">${channelDistributor.memo }</textarea>
												</div>
											</div>
										</fieldset>
									</form>
								</div>
							</div>
							<div class="common">
								<article style="overflow: hidden; zoom: 1;">
									<div class="jarviswidget">
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i>
											</span>
											<h2>员工列表</h2>
										</header>
										<div>
											<div class="widget-body no-padding">
												<div id="">
													<form role="search" class="navbar-form navbar-left">
														<div class="form-group">
															姓名: <input type="text" value="" class="form-control" id="employeeName">
														</div>
														<button onclick="employeeTable.ajax.reload();" type="button" class="btn btn-info">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="$('#employeeName').val('');employeeTable.ajax.reload();" type="button" class="btn btn-default">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
													</form>
												</div>
												<table id="employeeTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
										</div>
									</div>
								</article>
							</div>
							<div class="common">
								<article style="overflow: hidden; zoom: 1;">
									<div class="jarviswidget">
										<header>
											<span class="widget-icon"> <i class="fa fa-table"></i>
											</span>
											<h2>储值记录</h2>
										</header>
										<div>
											<div class="widget-body no-padding">
												<div id="">
													<form role="search" class="navbar-form navbar-left">
														<div class="form-group">
															储值日期: <input type="text" value="" class="form-control" id="selectName">
														</div>
														<button onclick="rechargeRecordTable.ajax.reload();" type="button" class="btn btn-info">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="$('#selectName').val('');rechargeRecordTable.ajax.reload();" type="button" class="btn btn-default">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
													</form>
												</div>
												<table id="rechargeRecordTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
										</div>
									</div>
								</article>
							</div>
							<div class="common">
								<div class="jarviswidget">
									<header>
										<span class="widget-icon"> <i class="fa fa-table"></i>
										</span>
										<h2>短信发送记录</h2>
									</header>
									<div>
										<div class="widget-body no-padding">
											<div id="">
												<form role="search" class="navbar-form navbar-left">
													<div class="form-group">
														姓名: <input type="text" value="" class="form-control" id="searchName">
													</div>
													<button onclick="messageSendRecordTable.ajax.reload();" type="button" class="btn btn-info">
														<i class="glyphicon glyphicon-search"></i> 检索
													</button>
													<button onclick="$('#searchName').val('');messageSendRecordTable.ajax.reload();" type="button" class="btn btn-default">
														<i class="glyphicon glyphicon-repeat"></i> 清空
													</button>
												</form>
											</div>
											<table id="messageSendRecordTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
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
	var tab_menu_li = $('.tab_menu li');
	$('.tab-cons .common:gt(0)').hide();

	tab_menu_li.click(function() {
		var index = $(this).index() + 1;
		$(this).addClass('selected').siblings().removeClass('selected');
		var tab_menu_li_index = tab_menu_li.index(this);
		$('.tab-cons .common').eq(tab_menu_li_index).show().siblings().hide();
	})
	pageSetUp();
	//消费记录
	var rechargeRecordColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "储值金额",
	"data" : function(data) {
		return data.payMoney;
	}
	}, {
	"title" : "储值日期",
	"data" : function(data) {
		return data.payDateStr;
	}
	}, {
	"title" : "支付方式",
	"data" : function(data) {
		if (data.payType == "1") {
			return "<span class='label label-info'>现金</span>";
		} else if (data.payType == "2") {
			return "<span class='label label-info'>余额</span>";
		} else if (data.payType == "3") {
			return "<span class='label label-info'>银行卡</span>";
		} else if (data.payType == "4") {
			return "<span class='label label-info'>微信</span>";
		} else if (data.payType == "5") {
			return "<span class='label label-info'>支付宝</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "','查看');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		return show;
	}
	} ];

	var rechargeRecordTable = initDataTable("rechargeRecordTableId", "${nvix}/nvixnt/vixntDistributionSystemAction!goRechargeRecordList.action", rechargeRecordColumns, function(page, pageSize, orderField, orderBy) {
		var channelDistributorId = $("#channelDistributorId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"channelDistributorId" : channelDistributorId
		};
	}, 10, '0');

	var messageSendRecordColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"data" : function(data) {
		return data.title;
	}
	}, {
	"title" : "手机号",
	"data" : function(data) {
		return data.mobilePhone;
	}
	}, {
	"title" : "短信内容",
	"width" : "50%",
	"data" : function(data) {
		return data.content;
	}
	}, {
	"title" : "短信类型",
	"data" : function(data) {
		if (data.sendType == '0') {
			return "<span class='label label-info'>验证码</span>";
		} else if (data.sendType == '1') {
			return "<span class='label label-info'>通知</span>";
		} else if (data.sendType == '2') {
			return "<span class='label label-info'>群发</span>";
		}
		return "";
	}
	}, {
	"title" : "发送时间",
	"data" : function(data) {
		return data.postTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return del;
	}
	} ];

	var messageSendRecordTable = initDataTable("messageSendRecordTableId", "${nvix}/nvixnt/vixntDistributionSystemAction!goMessageSendRecordList.action", messageSendRecordColumns, function(page, pageSize, orderField, orderBy) {
		var channelDistributorId = $("#channelDistributorId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"channelDistributorId" : channelDistributorId
		};
	});
	var employeeColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "姓名",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "手机号",
	"data" : function(data) {
		return data.telephone;
	}
	}, {
	"title" : "员工职务",
	"data" : function(data) {
		if (data.userType == '0') {
			return "<span class='label label-info'>店长</span>";
		} else if (data.userType == '1') {
			return "<span class='label label-info'>店员</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateStorePerson('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var employeeTable = initDataTable("employeeTableId", "${nvix}/nvixnt/vixntDistributionSystemAction!goEmployeeList.action", employeeColumns, function(page, pageSize, orderField, orderBy) {
		var channelDistributorId = $("#channelDistributorId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"channelDistributorId" : channelDistributorId
		};
	});

	function goMessage(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntDistributionSystemAction!goMessage.action?parentId=' + id, "rechargeRecordForm", title, 850, 375, rechargeRecordTable);
	};
	
	function goSaveOrUpdateStorePerson(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntDistributionSystemAction!goSaveOrUpdateStorePerson.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>