<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cog"></i> 系统管理 <span>> 用户信息 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/system/userApplicationAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>基本信息</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="userApplicationForm">
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 公司名称:</label>
						<div class="col-md-4">
							<div class="input-group">
								<input id="orgName" name="userApplication.orgName" value="${userApplication.orgName}" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="icon-append fa fa-user"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"> 行业:</label>
						<div class="col-md-4">
							<div class="input-group">
								<input id="industry" name="userApplication.industry" value="${userApplication.industry}" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="icon-append fa fa-user"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 联系人:</label>
						<div class="col-md-4">
							<div class="input-group">
								<input id="companyOrgContact" name="userApplication.companyOrgContact" value="${userApplication.companyOrgContact}" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="icon-append fa fa-user"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"> 电话:</label>
						<div class="col-md-4">
							<div class="input-group">
								<input id="telephone" name="userApplication.telephone" value="${userApplication.telephone}" class="form-control" type="text" /> <span class="input-group-addon"><i class="icon-append fa fa-phone"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 邮箱:</label>
						<div class="col-md-4">
							<div class="input-group">
								<input id="email" name="userApplication.email" value="${userApplication.email}" class="form-control" type="text" /><span class="input-group-addon"><i class="icon-append fa fa-envelope-o"></i></span>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/system/userApplicationAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>