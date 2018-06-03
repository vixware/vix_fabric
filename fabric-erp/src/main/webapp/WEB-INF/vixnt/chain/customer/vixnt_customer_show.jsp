<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.js" type="text/javascript"></script>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
			<s:if test=" returnPage=='workHomePage' || returnPage.contains(\"ctt\")  ">  <!-- 去工作台 guo-->
				<i class="fa fa-list-alt fa-fw "></i> 工作台 
			</s:if>	
			<s:elseif test=" controlSQL == 'HYL' ">
					<i class="fa fa-list-alt fa-bar-chart-o"></i> 智能分析 <span>> 会员量分析 </span>
			</s:elseif>
			<s:elseif test="controlSQL == 'HYGL' || queryTime.contains(\"t-Sm-HOT\") ">
				   <i class="fa-fw fa fa-user"></i>会员管理
			</s:elseif>
			<s:elseif test=" returnPage == 'cusAnaPage' ">   <!-- 去客户分析页面 -->
				<i class="fa fa-list-alt fa-bar-chart-o"></i> 智能分析 <span>> 客户分析 </span>
			</s:elseif>
			<s:else>
				<i class="fa fa-fw fa-user"></i> 会员管理 <span>&gt; 会员信息</span>
			</s:else>	
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<s:if test=" returnPage=='workHomePage' || returnPage.contains(\"ctt\")  ">  <!-- 去工作台 guo-->   
					<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!goMemberListNewHtml.action?controlSQL=${controlSQL}&returnPage=${returnPage }');"> 
						<i class="fa fa-rotate-left"></i> 返回                                                                                      
					</button>
				</s:if>	
				<s:elseif test=" returnPage=='mBerManage' ||  returnPage.contains(\"t-Sm-HOT\") || returnPage.contains(\"l-Tm-HOT\")  "> <!-- 去会员管理 guo-->
					<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!goMemberListNewHtml.action?controlSQL=${controlSQL}&returnPage=${returnPage }');">
						<i class="fa fa-rotate-left"></i> 返回                                                                                         
					</button>
				</s:elseif>
				<s:elseif test=" controlSQL=='HYL' "> <!-- 去会员量 guo-->
					<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntPurchasingBehaviorAction!goList.action');">
						<i class="fa fa-rotate-left"></i> 返回                                                                                         
					</button>
				</s:elseif>
				<s:elseif test="controlSQL == 'HYGL' || queryTime.contains(\"t-Sm-HOT\") ">
					<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!goMember.action?queryTime=${queryTime}');">
						<i class="fa fa-rotate-left"></i> 返回                                                                                         
					</button>
				</s:elseif>
				<s:elseif test=" controlSQL.contains(\"MemberAll\") ||  controlSQL.contains(\"ClipAll\")  ">   <!-- 去客户分析页面 -->
					<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!goMemberListNewHtml.action?controlSQL=${controlSQL}&returnPage=${returnPage }');">
						<i class="fa fa-rotate-left"></i> 返回                                                                                         
					</button>
				</s:elseif>
				<s:else>
				<button class="btn btn-default" type="button" onclick="loadContent('drp_allocationitem', '${nvix}/nvixnt/nvixCustomerAccountAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
				</s:else>
			</div>
		</div>
	</div>
	
	<div class="row">
				<div class="col-sm-12 col-md-12">
					<div class="well">
						<!-- 头部会员信息内容部分 -->
						<div class="row">
							<div class="col-xs-12">
								<div class="media search-media">
									<div class="media-left">
										<s:if test="customerAccount.headImage==null || customerAccount.headImage==''">
										<img style="width: 130px; height: 130px;" class="media-object" src="${nvix}/vixntcommon/base/img/tx.jpg">
										</s:if>
										<s:if test="customerAccount.headImage!=null && customerAccount.headImage!=''">
										<img style="width: 130px; height: 130px;" class="media-object" src="${customerAccount.headImage}">
										</s:if>
									</div>

									<div class="media-body">
										<div class="row">
											<div class="col-xs-6">
												<h4 class="media-heading">
												<input type="hidden" id="customerId" value="${customerAccount.id}" name="customerAccount.id" />
												<input type="hidden" id="clipId" value="${customerAccountClip.id}" name="customerAccountClip.id" />
													<i class="ace-icon glyphicon glyphicon-user blue bigger-110"></i> <span>${customerAccount.name}</span>
													
													 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
													 <c:if test="${customerAccountClip.card.type == '1'}">
													 	<span>账户余额：</span> <span class="reds">￥<fmt:formatNumber value="${customerAccount.money}" pattern="##.##" minFractionDigits="2"></fmt:formatNumber></span>
													 </c:if>
													 <c:if test="${customerAccountClip.card.type == '2'}">
													 	<span>计次卡</span> <a href='javascript:void(0);' class='btn btn-xs btn-default' onclick="show();"title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>
													 </c:if>	
												</h4>
											</div>
											<div class="col-xs-6">
												<div class="profile-skills">
													<!-- <div class="progress">
														<div class="progress-bar progress-bar-danger" style="width: 38%">
															<span class="pull-left">会员热度</span> <span class="pull-right">38%</span>
														</div>
													</div> -->
												</div>
											</div>
										</div>
										<p>
											<span>电话：${customerAccount.mobilePhone}</span>
										</p>
										<p>
											<span>会员等级：</span><span class="reds">${customerAccount.memberLevel.name}</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>会员卡号:${customerAccountClip.name}</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>会员积分：${customerAccountClip.pointValue}</span>
										</p>
										<p>
										<c:forEach items="${memberTagList}" var="entity">
											<span class="btn btn-warning btn-sm tooltip-warning" data-rel="tooltip" data-placement="top" title="" data-original-title="">${entity.name}</span>
										</c:forEach>
										<!-- 	<span class="btn btn-primary btn-sm" data-rel="tooltip" title="" data-original-title="某某插件">营销插件</span>
											<span class="btn btn-warning btn-sm tooltip-warning" data-rel="tooltip" data-placement="top" title="" data-original-title="某某插件">营销插件</span>
											<span class="btn btn-success btn-sm tooltip-success" data-rel="tooltip" data-placement="top" title="" data-original-title="某某插件">营销插件</span>
											<span class="btn btn-danger btn-sm tooltip-error" data-rel="tooltip" data-placement="top" title="" data-original-title="某某插件">营销插件</span>
											<span class="btn btn-info btn-sm tooltip-info" data-rel="tooltip" data-placement="top" title="" data-original-title="某某插件">营销插件</span> -->
										</p>
										<div class="search-actions text-center" id="isExpiryDate1">
											<div class="space-10"></div>
											<c:if test="${customerAccount.isReport != 'Y' && customerAccountClip.isReport != 'Y'}">
												<a class="btn btn-sm btn-block btn-primary" onclick="pay();">会员充值</a> <a class="btn btn-sm btn-block btn-info" onclick="swivel();">会员转卡</a> <a class="btn btn-sm btn-block btn-success" onclick="loss();">会员卡挂失</a>
											</c:if>
											<c:if test="${customerAccount.isReport == 'Y' || customerAccountClip.isReport == 'Y'}">
												<a class="btn btn-sm btn-block btn-default" onclick="">会员充值</a> <a class="btn btn-sm btn-block btn-default" onclick="">会员转卡</a> <a class="btn btn-sm btn-block btn-default" onclick="">会员卡挂失</a>
											</c:if>
										</div>
										<div class="search-actions text-center" id="isExpiryDate">
											<div class="space-10"></div>
												<a class="btn btn-sm btn-block btn-default" onclick="">会员充值</a> <a class="btn btn-sm btn-block btn-default" onclick="">会员转卡</a> <a class="btn btn-sm btn-block btn-default" onclick="">会员卡挂失</a>
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
											<li class="selected" onclick="">会员分析</li>
											<li onclick="">会员资料</li>
											<li onclick="invWarehouseTable.ajax.reload();">优惠券</li>
											<li onclick="rechargeRecordTable.ajax.reload();">充值记录</li>
											<li onclick="customerAccountTable.ajax.reload();">消费记录</li>
											<li onclick="rechargeRecordTable.ajax.reload();">积分记录</li>
											<li onclick="pointRecordTable.ajax.reload();">积分兑换记录</li>
										</ul>
									</div>
								</div>
							</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="tab-cons">
									<div class="common">
										<!-- 块信息展示 -->
										<div class="row">
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<hr class="simple" style="border-color: #c0c0c0;">
												<div class="row">
													<div class="col-xs-3 col-sm-3 text-center">
														<h5>
															<span class="font-lg txt-color-red padding-5">${rechargeAmount}次</span>
															<div class="padding-5"></div>
															<strong class="txt-color-greenLight">累计消费次数</strong>
														</h5>
														<div class="padding-5"></div>
													</div>
													<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
														<h5>
															<span class="font-lg txt-color-red padding-5">${allAmount}元</span>
															<div class="padding-5"></div>
															<strong class="txt-color-greenLight">累计消费金额</strong>
														</h5>
														<div class="padding-5"></div>
													</div>
													<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
														<h5>
															<span class="font-lg txt-color-red padding-5">${averageAmount}元</span>
															<div class="padding-5"></div>
															<strong class="txt-color-greenLight">平均消费金额</strong>
														</h5>
														<div class="padding-5"></div>
													</div>
													<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0; padding: 0 10px;">
														<h5>
															<span class="font-lg txt-color-red padding-5">${dayAmount}元</span>
															<div class="padding-5"></div>
															<strong class="txt-color-greenLight">今日消费金额</strong>
														</h5>
														<div class="padding-5"></div>
													</div>
												</div>
												<hr class="simple" style="border-color: #c0c0c0;">
											</div>
										</div>
										<!-- 图标信息展示 -->
										<div class="row">
											<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
												<div class="jarviswidget" id="wid-id-6" data-widget-editbutton="false">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i>
														</span>
														<h2>会员购买商品数量Top5</h2>
													</header>
													<div>
														<div id="domMain" style="height: 400px"></div>
														<script type="text/javascript">
															var myChart = echarts.init(document.getElementById('domMain'));
															option = {
																    color: ['#3398DB'],
																    tooltip : {
																        trigger: 'axis',
																        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
																            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
																        }
																    },
																    grid: {
																        left: '3%',
																        right: '4%',
																        bottom: '3%',
																        containLabel: true
																    },
																    xAxis : [
																        {
																            type : 'value'
																        }
																    ],
																    yAxis : [
																		{
																		    type : 'category',
																		    data : [${itemNames}],
																		    axisTick: {
																		        alignWithLabel: true
																		    }
																		}
																    ],
																    series : [
																        {	
																        	name:"购买数量",
																            type:'bar',
																            barWidth: '60%',
																            data:[${amounts}]
																        }
																    ]
																};
															myChart.setOption(option);
														</script>
													</div>
												</div>
											</article>
											<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
												<div class="jarviswidget" id="wid-id-6" data-widget-editbutton="false">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i>
														</span>
														<h2>会员购买商品数量</h2>
													</header>
													<div>
														<div id="domMain1" style="height: 400px"></div>
														<script type="text/javascript">
															var myChart = echarts.init(document.getElementById('domMain1'));
															option = {
																    /* title : {
																        //text: '某站点用户访问来源',
																       // subtext: '纯属虚构',
																        x:'center'
																    }, */
																    tooltip : {
																        trigger: 'item',
																        formatter: "{a} <br/>{b} : {c} ({d}%)"
																    },
																    legend: {
																        orient: 'vertical',
																        left: 'left',
																        data: [${goodsNames}]
																    },
																    series : [
																        {
																            type: 'pie',
																            radius : '55%',
																            center: ['50%', '60%'],
																            data:[
																                ${itemAmounts}
																            ],
																            itemStyle: {
																                emphasis: {
																                    shadowBlur: 10,
																                    shadowOffsetX: 0,
																                    shadowColor: 'rgba(0, 0, 0, 0.5)'
																                }
																            }
																        }
																    ]
																};
															myChart.setOption(option);
														</script>
													</div>
												</div>
											</article>
										</div>
									</div>
									<div class="common">
										<div class="widget-body">
											<form class="form-horizontal" id="customerAccountForm">
											<fieldset>
												<legend>基本信息:</legend>
												<div class="form-group">
													<label class="col-md-2 control-label"><span class="text-danger">*</span>姓名:</label>
													<div class="col-md-3">
														<input id="name" name="customerAccount.name" value="${customerAccount.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
													</div>
													<label class="col-md-2 control-label">手机:</label>
													<div class="col-md-3">
														<input id="mobilePhone" name="customerAccount.mobilePhone" value="${customerAccount.mobilePhone}" data-prompt-position="topLeft" class="form-control validate[custom[phone]]" type="text" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">身份证号码:</label>
														<div class="col-md-3">
															<input id="identityId" name="customerAccount.identityId" value="${customerAccount.identityId}" data-prompt-position="topLeft" class="form-control validate[custom[chinaIdLoose]]" type="text" readonly="readonly"/>
														</div>
													<label class="col-md-2 control-label">性别:</label>
													<div class="col-md-3">
														<div data-toggle="buttons" class="btn-group">
															<label class="btn btn-default <s:if test="customerAccount.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="customerAccount.sex" <s:if test="customerAccount.sex == 1">checked="checked"</s:if>>男
															</label> <label class="btn btn-default <s:if test="customerAccount.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="customerAccount.sex" <s:if test="customerAccount.sex == 0">checked="checked"</s:if>>女
															</label>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">年龄:</label>
													<div class="col-md-3">
														<input id="age" name="customerAccount.age" value="${customerAccount.age}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
													</div>
													<label class="col-md-2 control-label">地址:</label>
													<div class="col-md-3">
														<input id="address" name="customerAccount.address"  value="${customerAccount.address}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">生日:</label>
													<div class="col-md-3">
														<div class="input-group">
															<input id="birthday" name="customerAccount.birthday" readonly="readonly" value="${customerAccount.birthdayStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
																class="fa fa-calendar"></i></span>
														</div>
													</div>
													<label class="col-md-2 control-label">等级标识:</label>
													<div class="col-md-3">
														<select id="memberLevelId" name="customerAccount.memberLevel.id" class="form-control" readonly="readonly">
															<option value="">------请选择------</option>
															<c:forEach items="${memberLevelList}" var="entity">
																<option value="${entity.id}" <c:if test="${customerAccount.memberLevel.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
													<div class="col-md-3">
														<div data-toggle="buttons" class="btn-group">
															<label class="btn btn-default <s:if test='customerAccount.isUse == "Y"'>active</s:if>"> <input type="radio" value="Y" id="status" name="customerAccount.isUse" class="validate[required]" <s:if test='item.status == "T"'>checked="checked"</s:if>>启用
															</label> <label class="btn btn-default <s:if test='customerAccount.isUse == "N"'>active</s:if>"> <input type="radio" value="N" id="status" name="customerAccount.isUse" <s:if test='item.status == "F"'>checked="checked"</s:if>>禁用
															</label>
														</div>
													</div>
												</div>
												<legend>联系方式:</legend>
												<div class="form-group">
													<label class="col-md-2 control-label">QQ:</label>
													<div class="col-md-3">
														<input id="qqAccount" name="customerAccount.qqAccount" value="${customerAccount.qqAccount}" class="form-control" type="text" readonly="readonly"/>
													</div>
													<label class="col-md-2 control-label">微信:</label>
													<div class="col-md-3">
														<input id="msnAccount" name="customerAccount.msnAccount" value="${customerAccount.msnAccount}" class="form-control" type="text" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">邮箱:</label>
													<div class="col-md-3">
														<input id="email" name="customerAccount.email" value="${customerAccount.email}" class="form-control validate[custom[email]]" type="text" readonly="readonly"/>
													</div>
												</div>
												<legend>推荐人:</legend>
												<div class="form-group">
													<label class="col-md-2 control-label">推荐人:</label>
													<div class="col-md-3">
														<input id="" name="" value="" class="form-control validate" type="text" readonly="readonly"/>
													</div>
												</div>
												<legend>会员卡:</legend>
												<div class="form-group">
													<label class="col-md-2 control-label">会员类型:</label>
													<div class="col-md-3">
														<input id="clipCode" name="customerAccountClip.card.name" value="${customerAccountClip.card.name}" class="form-control validate" type="text" readonly="readonly"/>
													</div>
													
													<label class="col-md-2 control-label">会员卡号:</label>
													<div class="col-md-3">
														<input id="clipName" name="customerAccountClip.name" value="${customerAccountClip.name}" class="form-control validate" type="text" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group">
													<%-- <label class="col-md-2 control-label">会员卡类型:</label>
													<div class="col-md-3">
														<select id="clipType" name="customerAccountClip.type" class="form-control" onclick="check();">
															<option value="0" <c:if test='${customerAccountClip.type == "1"}'>selected="selected"</c:if>>储值卡</option>
															<option value="1" <c:if test='${customerAccountClip.type == "2"}'>selected="selected"</c:if>>计次卡</option>
															<option value="2" <c:if test='${customerAccountClip.type == "3"}'>selected="selected"</c:if>>储值计次卡</option>
														</select>
													</div> --%>
													<label class="col-md-2 control-label">会员卡有效期至:</label>
													<div class="col-md-3">
														<input id="clipExpiryDate" name="customerAccountClip.expiryDate" value="${customerAccountClip.expiryDateStr}" class="form-control validate" type="text" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group">
													<c:if test="${customerAccountClip.card.type == '1'}">
													<label class="col-md-2 control-label">会员卡余额:</label>
													<div class="col-md-3">
														<input id="clipMoney" name="customerAccountClip.money" value="<fmt:formatNumber value='${customerAccountClip.money}' pattern='##.##' minFractionDigits='2'></fmt:formatNumber>" class="form-control validate" type="text" readonly="readonly"/>
													</div>
													</c:if>
													<label class="col-md-2 control-label">会员卡积分:</label>
													<div class="col-md-3">
														<input id="clipPointValue" name="customerAccountClip.pointValue" value="${customerAccountClip.pointValue}" class="form-control validate" type="text" readonly="readonly"/>
													</div>
												</div>
												<%-- <div class="form-group">
													<label class="col-md-2 control-label">生效日期:</label>
													<div class="col-md-3">
														<div class="input-group">
															<input id="startTime" name="customerAccountClip.startTime" value="${customerAccountClip.startTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" readonly="readonly"/> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
																class="fa fa-calendar"></i></span>
														</div>
													</div>
													<label class="col-md-2 control-label">截止日期:</label>
													<div class="col-md-3">
														<div class="input-group">
															<input id="endTime" name="customerAccountClip.endTime" value="${customerAccountClip.endTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" readonly="readonly"/> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
																class="fa fa-calendar"></i></span>
														</div>
													</div>
												</div> --%>
												<c:if test="${customerAccountClip.card.type != '1'}">
												<div class="form-group" id="itemDiv">
													<label class="col-md-1 control-label"></label>
													<div class="col-md-10">
														<div id="contactPersonDiv" class="jarviswidget jarviswidget-color-white">
															<header>
																<span class="widget-icon"> <i class="fa fa-table"></i></span>
																<h2>服务项目</h2>
															</header>
															<div>
																<div class="widget-body no-padding">
																	<%-- <div style="margin: 5px;">
																		<div class="form-group" style="margin: 0 0px;">
																			<div class="col-md-3">
																				<input type="text" value="" placeholder="服务项目" class="form-control" id="searchItem">
																			</div>
																			<button onclick="itemTable.ajax.reload();" type="button" class="btn btn-primary listMenu-float-left">
																				<i class="glyphicon glyphicon-search"></i> 检索
																			</button>
																			<button onclick="$('#searchItem').val('');itemTable.ajax.reload();" type="button" class="btn btn-primary listMenu-float-left">
																				<i class="glyphicon glyphicon-repeat"></i> 清空
																			</button>
																			<div class="listMenu-float-right">
																				<button onclick="addItems();" type="button" class="btn btn-primary">
																					<i class="glyphicon glyphicon-plus"></i>
																					<s:text name="add" />
																				</button>
																			</div>
																		</div>
																	</div> --%>
																	<table id="itemTableId" class="table table-striped table-bordered table-hover" width="100%">
																	</table>
																</div>
															</div>
														</div>
													</div>
												</div>
												</c:if>
											</fieldset>
											</form>
										</div>
									</div>
									<!-- 优惠券 -->
									<div class="common">
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"> <i class="fa fa-table"></i>
												</span>
												<h2>优惠券列表</h2>
											</header>
											<div>
												<div class="widget-body no-padding">
													<div id="">
														<!-- <form role="search" class="navbar-form navbar-left">
															<div class="form-group">
																优惠券名称: <input type="text" value="" class="form-control" id="selectName">
															</div>
															<button onclick="invWarehouseTable.ajax.reload();" type="button" class="btn btn-primary">
																<i class="glyphicon glyphicon-search"></i> 检索
															</button>
															<button onclick="$('#selectName').val('');invWarehouseTable.ajax.reload();" type="button" class="btn btn-">
																<i class="glyphicon glyphicon-repeat"></i> 清空
															</button>
														</form> -->
													</div>
													<table id="invWarehouseTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
												</div>
											</div>
										</div>	
									</div>
									<!-- 充值记录 -->
									<div class="common">
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"> <i class="fa fa-table"></i></span>
												<h2>充值记录</h2>
											</header>
											<div>
												<div class="widget-body no-padding">
													<!-- <div>
														<form role="search" class="navbar-form navbar-left">
															<div class="form-group">
																名称: <input type="text" value="" class="form-control" id="searchName">
															</div>
															<button onclick="memberTagTable.ajax.reload();" type="button" class="btn btn-primary">
																<i class="glyphicon glyphicon-search"></i> 检索
															</button>
															<button onclick="$('#searchName').val('');memberTagTable.ajax.reload();" type="button" class="btn btn-default">
																<i class="glyphicon glyphicon-repeat"></i> 清空
															</button>
														</form>
													</div> -->
													<table id="rechargeRecordTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
												</div>
											</div>
										</div>
									</div>
									<!-- 消费记录 -->
									<div class="common">
										<article style="overflow: hidden; zoom: 1;">
											<div class="jarviswidget">
												<header>
													<span class="widget-icon"> <i class="fa fa-table"></i>
													</span>
													<h2>消费记录列表</h2>
												</header>
												<div>
													<div class="widget-body no-padding">
														<!-- <div id="">
															<form role="search" class="navbar-form navbar-left">
																<div class="form-group">
																	会员名称: <input type="text" value="" class="form-control" id="selectName">
																</div>
																<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-primary">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#selectName').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
															</form>
														</div> -->
														<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
												</div>
											</div>
										</article>
									</div>
									<!-- 积分获取记录 -->
									<div class="common"><div class="jarviswidget">
											<header>
												<span class="widget-icon"> <i class="fa fa-table"></i></span>
												<h2>积分记录</h2>
											</header>
											<div>
												<div class="widget-body no-padding">
													<table id="pointRecordTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
												</div>
											</div>
										</div>
									</div>
									<!-- 积分兑换记录 -->
									<div class="common">
										<div class="jarviswidget">
											<header>
												<span class="widget-icon"> <i class="fa fa-table"></i></span>
												<h2>积分兑换列表</h2>
											</header>
											<div>
												<div class="widget-body no-padding">
													<!-- <div>
														<form role="search" class="navbar-form navbar-left">
															<div class="form-group">
																名称: <input type="text" value="" class="form-control" id="searchName">
															</div>
															<button onclick="memberTagTable.ajax.reload();" type="button" class="btn btn-primary">
																<i class="glyphicon glyphicon-search"></i> 检索
															</button>
															<button onclick="$('#searchName').val('');memberTagTable.ajax.reload();" type="button" class="btn btn-default">
																<i class="glyphicon glyphicon-repeat"></i> 清空
															</button>
														</form>
													</div> -->
													<table id="areaLevelTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
												</div>
											</div>
										</div>
									</div>
									<div class="common">6666</div>
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
	
	tab_menu_li.click(function(){
	    var index = $(this).index()+1;
	    $(this).addClass('selected')
	            .siblings().removeClass('selected');
	    var tab_menu_li_index = tab_menu_li.index(this);
	    $('.tab-cons .common').eq(tab_menu_li_index).show()
	            .siblings().hide();
	})
</script>
<script>
pageSetUp();
//消费记录
var customerId = $("#customerId").val();
var clipId = $("#clipId").val();
var customerAccountColumns = [ {
"title" : "编号",
"width" : "8%",
"data" : function(data) {
	return "";
}
}, {
"title" : "会员姓名",
"data" : function(data) {
	return data.customerName;
}
}, {
"title" : "电话",
"data" : function(data) {
	return data.customerPhone;
}
}, {
"title" : "消费金额",
"data" : function(data) {
	return data.payAmount;
}
}, {
"title" : "消费日期",
"data" : function(data) {
	return data.payDateStr;
}
}, {
"title" : "支付方式",
"data" : function(data) {
	if(data.payType == "1"){
		return "<span class='label label-warning'>现金</span>";
	}else if(data.payType == "2"){
		return "<span class='label label-info'>余额</span>";
	}else if(data.payType == "3"){
		return "<span class='label label-info'>银行卡</span>";
	}else if(data.payType == "4"){
		return "<span class='label label-info'>微信</span>";
	}else if(data.payType == "5"){
		return "<span class='label label-info'>支付宝</span>";
	}
	return "";
}
}/* , {
"title" : "操作",
"width" : "15%",
"orderable" : false,
"data" : function(data) {
	var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "','查看');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
	return show; 
}
} */ ];

var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/nvixExpenseRecordAction!goSingleList.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
	// var selectName = $("#selectName").val();
	//selectName = Url.encode(selectName);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"customerAccountId" : customerId
	};
}, 10, '0');
//优惠券
var invWarehouseColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "名称",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "面额",
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"title" : "状态",
	"data" : function(data) {
		if(data.isUsed == "1"){
			return "已使用";
		}else if(data.isUsed =="2"){
			return "已作废";
		}else{
			return "未使用";
		}
	}
	}, {
	"title" : "生效日期",
	"data" : function(data) {
		return data.effectiveDateStr;
	}
	}, {
	"title" : "截止日期",
	"data" : function(data) {
		return data.cutOffDateStr;
	}
	}];

var invWarehouseTable = initDataTable("invWarehouseTableId", "${nvix}/nvixnt/nvixCouponManagementAction!getCouponDetailList.action", invWarehouseColumns, function(page, pageSize, orderField, orderBy) {
	//var selectName = $("#selectName").val();
	//selectName = Url.encode(selectName);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"customerAccountId" : customerId,
	//"selectName" : selectName
	};
}, 10, '0');
	
//积分兑换历史记录
var memberTagColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "兑换人",
	"width" : "15%",
	"data" : function(data) {
		return data.customerName;
	}
	}, {
	"title" : "消耗积分",
	"width" : "15%",
	"data" : function(data) {
		return data.integralAmount;
	}
	}/* , {
	"title" : "商品名称",
	"width" : "15%",
	"data" : function(data) {
		return data.itemName;
	}
	} */, {
	"title" : "兑换金额",
	"width" : "15%",
	"data" : function(data) {
		return data.money+"元";
	}
	}, {
	"title" : "兑换日期",
	"width" : "15%",
	"data" : function(data) {
		return data.changeDateStr;
	}
	}];

var memberTagTable = initDataTable("areaLevelTableId", "${nvix}/nvixnt/nvixIntegraChangeHistory!goSingleList.action", memberTagColumns, function(page, pageSize, orderField, orderBy) {
	//var searchName = $("#searchName").val();
	//searchName = Url.encode(searchName);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"customerAccountId" : customerId,
	//"searchName" : searchName
	};
});
var rechargeRecordColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "充值金额",
	"width" : "15%",
	"data" : function(data) {
		return data.payMoney;
	}
	}, {
	"title" : "赠送金额",
	"width" : "15%",
	"data" : function(data) {
		if(!data.giftAmount){
			return "";	
		}
		return data.giftAmount;
	}
	}, {
	"title" : "支付方式",
	"width" : "15%",
	"data" : function(data) {
		if(data.payType == "1"){
			return "<span class='label label-warning'>现金</span>";
		}else if(data.payType == "3"){
			return "<span class='label label-warning'>银行卡</span>";
		}else if(data.payType == "4"){
			return "<span class='label label-info'>微信</span>";
		}else if(data.payType == "5"){
			return "<span class='label label-warning'>支付宝</span>";
		}
		return "";
	}
	}, {
	"title" : "充值日期",
	"width" : "15%",
	"data" : function(data) {
		return data.payDateTimeStr;
	}
	}];

var rechargeRecordTable = initDataTable("rechargeRecordTableId", "${nvix}/nvixnt/nvixRechargeRecordAction!goSingleListByCustomerAccountId.action", rechargeRecordColumns, function(page, pageSize, orderField, orderBy) {
	//var searchName = $("#searchName").val();
	//searchName = Url.encode(searchName);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"customerAccountClipId" : clipId,
	//"searchName" : searchName
	};
});
var pointRecordColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "积分值",
	"data" : function(data) {
		return data.pointValue;
	}
	}, {
	"title" : "积分来源",
	"width" : "15%",
	"data" : function(data) {
		return "<span class='label label-info'>"+data.pointSource+"</span>";
	}
	}, {
	"title" : "类型",
	"data" : function(data) {
		if(data.pointType == "1"){
			return "<span class='label label-warning'>增加</span>";
		}else if(data.pointType == "2"){
			return "<span class='label label-warning'>减少</span>";
		}
		return "";
	}
	}, {
	"title" : "积分操作",
	"data" : function(data) {
		return data.operation;
	}
	}, {
	"title" : "操作日期",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}];

var pointRecordTable = initDataTable("pointRecordTableId", "${nvix}/nvixnt/integralManagementsubsidiaryAction!goSingleListByCustomerById.action", pointRecordColumns, function(page, pageSize, orderField, orderBy) {
	//var searchName = $("#searchName").val();
	//searchName = Url.encode(searchName);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"customerAccountId" : customerId,
	//"searchName" : searchName
	};
});
function swivel(id,title){
	openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAccountClipAction!swivel.action?customerAccountId=${customerAccount.id}', "memberTagForm", "转卡", 750, 450, customerAccountTable);
}
function pay(id,title){
	//openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAccountAction!pay.action?customerAccountId=${customerAccount.id}', "memberTagForm", title, 750, 450, customerAccountTable);
	$.ajax({
		url : '${nvix}/nvixnt/nvixCustomerAccountAction!pay.action?customerAccountId=${customerAccount.id}',
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
}
function  loss() {
	$.ajax({
		url:'${nvix}/nvixnt/nvixCustomerAccountClipAction!report.action?customerAccountId=${customerAccount.id}',
		success:function(result){
			var r = result.split(":");
			if(r[0]=="1"){
				layer.alert(r[1]);
				loadContent('','${nvix}/nvixnt/nvixCustomerAccountClipAction!goList.action');
			}else{
				layer.alert(r[1]);
			}
		}
	});
}
var itemTableColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "服务名称",
	"width" : "10%",
	"name" : "code",
	"data" : function(data) {
		return data.itemName;
	}
	}, {
	"title" : "服务内容",
	"width" : "15%",
	"data" : function(data) {
		return data.itemServiceContent;
	}
	}, {
	"title" : "服务次数",
	"width" : "15%",
	"data" : function(data) {
		return data.number;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"addItems('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

var itemTable = initDataTable("itemTableId", "${nvix}/nvixnt/nvixCustomerAccountAction!getItemSingleList.action", itemTableColumns, function(page, pageSize, orderField, orderBy) {
	//var searchItem = $("#searchItem").val();
	//searchItem = Url.encode(searchItem);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"clipId" : clipId,
	//"searchItem" : searchItem
	};
});
$(function(){
	var b = new Date().getTime();
	var c = $("#clipExpiryDate").val();
	var d = new Date(c).getTime();
	if(b>=d){
		$("#isExpiryDate1").attr("style","display:none;");
		$("#isExpiryDate").attr("style","");
	}else{
		$("#isExpiryDate1").attr("style","");
		$("#isExpiryDate").attr("style","display:none;");
	}
});				
function show(){
	openWindowForShow("${nvix}/nvixnt/nvixCustomerAccountClipAction!showItem.action?clipId=${customerAccountClip.id}","服务项目",650,475);
}
</script>