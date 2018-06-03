<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cog"></i> 微信管理 <span>> 公众号管理 </span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>公众号信息</h2>
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
							<label class="col-md-2 control-label"><span class="text-danger">*</span>APPID: </label>
							<div class="col-md-3">
								<input id="appId" name="wxpWeixinSite.appId" value="${wxpWeixinSite.appId}" class="form-control validate[required]" type="text" />
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>SECRET:</label>
							<div class="col-md-3">
								<input id="secret" name="wxpWeixinSite.secret" value="${wxpWeixinSite.secret}" class="form-control validate[required]" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">ACCESS_TOKEN:</label>
							<div class="col-md-8">
								<input id="accessToken" name="wxpWeixinSite.accessToken" value="${wxpWeixinSite.accessToken}" class="form-control" type="text" readonly="readonly" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">商户号: </label>
							<div class="col-md-3">
								<input id="mchID" name="wxpWeixinSite.mchID" value="${wxpWeixinSite.mchID}" class="form-control" type="text" />
							</div>
							<label class="col-md-2 control-label">支付秘钥:</label>
							<div class="col-md-3">
								<input id="privateKey" name="wxpWeixinSite.privateKey" value="${wxpWeixinSite.privateKey}" class="form-control" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">HTTPS证书的本地路径: </label>
							<div class="col-md-3">
								<input id="certLocalPath" name="wxpWeixinSite.certLocalPath" value="${wxpWeixinSite.certLocalPath}" class="form-control" type="text" />
							</div>
							<label class="col-md-2 control-label">HTTPS证书密码:</label>
							<div class="col-md-3">
								<input id="certPassword" name="wxpWeixinSite.certPassword" value="${wxpWeixinSite.certPassword}" class="form-control" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">应用服务器IP: </label>
							<div class="col-md-3">
								<input id="spbillCreateIp" name="wxpWeixinSite.spbillCreateIp" value="${wxpWeixinSite.spbillCreateIp}" class="form-control" type="text" />
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
	};
</script>