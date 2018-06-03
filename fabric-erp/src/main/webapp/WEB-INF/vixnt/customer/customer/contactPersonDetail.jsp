<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="well well-sm">
				<div class="row">
					<div class="col-sm-12 col-md-12 col-lg-12">
						<div class="well well-light well-sm no-margin no-padding">
							<div class="row">
								<div class="col-sm-12">
									<div class="row">
										<div class="col-sm-3 profile-pic">
											<img src="${nvix}/vixntcommon/base/img/avatars/sunny-big.png" alt="demo user">
											<div class="padding-10">
												<h4 class="font-md">
													<c:if test="${contactPerson.sex == 1}">
														<strong>男</strong> 
													</c:if>
													<c:if test="${contactPerson.sex == 0}">
														<strong>女</strong> 
													</c:if>
													<br> <small>性别</small>
												</h4>
												<br> 
												<h4 class="font-md">
													<strong>${contactPerson.msnAccount}</strong> <br> <small>生日</small>
												</h4>
												<br> 
												<h4 class="font-md">
													<strong>${contactPerson.qqAccount}</strong> <br> <small>微信</small>
												</h4>
												<br> 
												<h4 class="font-md">
													<strong>${contactPerson.birthdayStr}</strong> <br> <small>生日</small>
												</h4>
												<br> 
												<h4 class="font-md">
													<strong>${contactPerson.createdBy}</strong> <br> <small>创建人</small>
												</h4>
												<br> 
												<h4 class="font-md">
													<strong>${contactPerson.createTimeStr}</strong> <br> <small>创建时间</small>
												</h4>
											</div>
										</div>
										<div class="col-sm-9">
											<h1>
												${contactPerson.name}
											</h1>
											<div class="col-sm-8">
												<ul class="list-unstyled">
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">负责业务：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${contactPerson.presideBusiness}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">手机：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${contactPerson.mobile}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">邮件：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${contactPerson.email}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">称谓：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${contactPerson.callTitle}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">公司：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${contactPerson.company}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">部门：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${contactPerson.department}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">职务：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${contactPerson.position}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">工作电话：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${contactPerson.directPhone}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">传真：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${contactPerson.fax}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">家庭电话：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${contactPerson.phoneHome}</span>
														</p>
													</li>
													<li>
														<p class="text-muted">
															<strong class="txt-color-darken">家庭住址：</strong>&nbsp;&nbsp;<span class="txt-color-darken">${contactPerson.address}</span>
														</p>
													</li>
												</ul>
												<br>
												<p>
													<strong class="txt-color-darken">备注</strong>
												</p>
												<p>${contactPerson.memo}</p>
											</div>
											<div class="col-sm-4">
												<div class="padding-10 text-right">
													<h4 class="font-md">
														<strong class="txt-color-red">${contactPerson.contactPersonType.name}</strong> <br> <small>联系人分类</small>
													</h4>
													<br> 
													<h4 class="font-md">
														<strong class="txt-color-red">${contactPerson.crmContactType.name}</strong> <br> <small>联系人类型</small>
													</h4>
												</div>
											</div>
											<br>
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