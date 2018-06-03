<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cog"></i> 系统管理 <span>> 企业号设置 </span>
			</h1>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<div class="col-sm-12">
				<div class="jarviswidget">
					<header>
						<h2>企业号信息</h2>
					</header>
					<div>
						<div class="jarviswidget-editbox">
							<input class="form-control" type="text">
						</div>
						<div class="widget-body">
							<form id="wxpWeixinSiteForm" class="form-horizontal">
								<input type="hidden" id="id" name="wxpWeixinSite.id" value="${wxpWeixinSite.id}" />
								<fieldset>
									<div class="form-group">
										<label class="col-md-2 control-label"><span class="text-danger">*</span> 企业号名称:</label>
										<div class="col-md-8">
											<div class="input-group">
												<input id="name" name="wxpWeixinSite.name" value="${wxpWeixinSite.name}" class="form-control validate[required]" type="text" /> <span class="input-group-addon"></span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label"> <span class="text-danger">*</span> CorpID:
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<input id="qiyeCorpId" name="wxpWeixinSite.qiyeCorpId" value="${wxpWeixinSite.qiyeCorpId}" class="form-control validate[required]" type="text" /><span class="input-group-addon"></span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label"> CorpSecret:</label>
										<div class="col-md-8">
											<div class="input-group">
												<input id="qiyeSecret" name="wxpWeixinSite.qiyeSecret" value="${wxpWeixinSite.qiyeSecret}" class="form-control" type="text" /> <span class="input-group-addon"></span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label"> AccessToken:</label>
										<div class="col-md-8">
											<div class="input-group">
												<input id="qiyeAccessToken" name="wxpWeixinSite.qiyeAccessToken" value="${wxpWeixinSite.qiyeAccessToken}" class="form-control" type="text" readonly="readonly" /><span class="input-group-addon"></span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">备注:</label>
										<div class="col-md-8">
											<textarea class="form-control" name="wxpWeixinSite.memo" rows="4">${wxpWeixinSite.memo }</textarea>
										</div>
									</div>
								</fieldset>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-12">
											<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">
												<i class="fa fa-save"></i> 保存
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<script type="text/javascript">
	$("#wxpWeixinSiteForm").validationEngine();
	function saveOrUpdate() {
		if ($("#wxpWeixinSiteForm").validationEngine('validate')) {
			$("#wxpWeixinSiteForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/wxpWeixinSiteAction!saveOrUpdate.action",
			dataType : "text",
			success : function() {
				loadContent('', '${nvix}/nvixnt/wxpWeixinSiteAction!goList.action');
			}
			});
		} else {
			return false;
		}
	}
</script>