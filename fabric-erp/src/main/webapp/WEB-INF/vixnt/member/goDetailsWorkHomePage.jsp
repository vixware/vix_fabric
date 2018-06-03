<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<h1 class="page-title txt-color-blueDark">
				<s:if test=" returnPage=='workHomePage' || returnPage.contains(\"ctt\") ">
				<i class="fa fa-list-alt fa-fw "></i> 工作台
				</s:if>
				<s:elseif test=" returnPage=='mBerManage' ||  returnPage.contains(\"t-Sm-HOT\") || returnPage.contains(\"l-Tm-HOT\")  ">
				<i class="fa-fw fa fa-user"></i>会员管理
				</s:elseif>
			</h1>
			</h1>
		</div>
		 
		      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
				<div class="page-title">
				<s:if test=" returnPage=='workHomePage' || returnPage.contains(\"ctt\")  ">  <!-- 去工作台-->   
					<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!goMemberListNewHtml.action${newReturnPage }');"> 
						<i class="fa fa-rotate-left"></i> 返回                                                                                               
					</button>
				</s:if>	
				<s:elseif test=" returnPage=='mBerManage' ||  returnPage.contains(\"t-Sm-HOT\") || returnPage.contains(\"l-Tm-HOT\")  "> <!-- 去会员管理-->
					<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!goMemberListNewHtml.action${newReturnPage}');"> 
						<i class="fa fa-rotate-left"></i> 返回                                                                                              
					</button>
				</s:elseif>
				</div>
			  </div> 
		
	</div>
	<div class="jarviswidget">
		<header>
			<h2>会员详情</h2>
		</header>
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
															<input id="" name="customerAccount.sexStr" value="${customerAccount.sexStr}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
														</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">年龄:</label>
													<div class="col-md-3">
														<input id="age" name="customerAccount.age" value="${customerAccount.age}" data-prompt-position="topLeft" class="form-control " type="text" readonly="readonly"/>
													</div>
													<label class="col-md-2 control-label">地址:</label>
													<div class="col-md-3">
														<input id="address" name="customerAccount.address"  value="${customerAccount.address}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">生日:</label>
													<div class="col-md-3">
															<input id="birthday" name="customerAccount.birthday"  value="${customerAccount.birthdayStr}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
													</div>
													<label class="col-md-2 control-label">等级:</label>
													<div class="col-md-3">
														<input id="memberLevelName" name="customerAccount.memberLevelName"  value="${customerAccount.memberLevelName}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
													</div>  
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">会员创建时间:</label>
													<div class="col-md-3">
														<input id="createTimeFormatA" name="customerAccount.createTimeFormatA"  value="${customerAccount.createTimeFormatA}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
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
								<label class="col-md-2 control-label">会员卡类型:</label>
								<div class="col-md-3">
									<input id="typeNameStr" name="customerAccountClip.typeNameStr" value="${customerAccountClip.typeNameStr}" class="form-control" type="text" readonly="readonly"/>
								</div>
													<label class="col-md-2 control-label">会员卡有效期限:</label>
													<div class="col-md-3">
														<div class="input-group">
															<input id="clipExpiryDate" name="customerAccountClip.expiryDate" value="${customerAccountClip.expiryDate}" class="form-control validate" type="text" readonly="readonly"/><span class="input-group-addon"><i class="fa">年</i></span>
														</div>
													</div>
												</div>
												<div class="form-group">
	<%-- <c:if test="${customerAccountClip.card.type == '1'}"> --%>
													<label class="col-md-2 control-label">会员卡余额:</label>
													<div class="col-md-3">
														<input id="clipMoney" name="customerAccountClip.money" value="${customerAccountClip.money}" class="form-control " type="text" readonly="readonly"/>
													</div>
	<%-- </c:if> --%>
													<label class="col-md-2 control-label">会员卡积分:</label>
													<div class="col-md-3">
														<input id="clipPointValue" name="customerAccountClip.pointValue" value="${customerAccountClip.pointValue}" class="form-control validate" type="text" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">生效日期:</label>
													<div class="col-md-3">
															<input id="startTime" name="customerAccountClip.startTime"  value="${customerAccountClip.startTimeStr}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
													</div>
													<label class="col-md-2 control-label">截止日期:</label>
													<div class="col-md-3">
														    <input id="endTime" name="customerAccountClip.endTime"  value="${customerAccountClip.endTimeStr}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label">会员卡创建时间:</label>
													<div class="col-md-3">
														<input id="clipCreateTimeFormatA" name="customerAccountClip.createTimeFormatA"  value="${customerAccountClip.createTimeFormatA}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
													</div>
												</div>
											</fieldset>
				
			 
			     <div class="form-actions">
					<div class="row">
						<div class="col-md-12">
						<s:if test=" returnPage=='workHomePage' || returnPage.contains(\"ctt\")  ">
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!goMemberListNewHtml.action${newReturnPage }');"> 
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</s:if>
						<s:elseif test=" returnPage=='mBerManage' ||  returnPage.contains(\"t-Sm-HOT\") || returnPage.contains(\"l-Tm-HOT\")  "> <!-- 去会员管理-->
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntMemberManageDataAction!goMemberListNewHtml.action${newReturnPage }');"> 
								<i class="fa fa-rotate-left"></i> 返回
							</button>	
						</s:elseif>
						</div>
					</div>
				</div>
    	 
				
			</form>
	
		</div>
		<!-- end widget content -->
	
	</div>
	</div>
</div>
<script type="text/javascript">
</script>