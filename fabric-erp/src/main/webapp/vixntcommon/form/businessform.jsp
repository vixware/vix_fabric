<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-file-text-o "></i> 表单管理<span>&gt; 业务表维护</span>
			</h1>
		</div>
	</div>
	<iframe src="${flow_ip}/form/businessform/businessform_list" style="width: 100%; height: 550px; border-style: none; margin: 0px; padding: 0px;"> </iframe>
</div>