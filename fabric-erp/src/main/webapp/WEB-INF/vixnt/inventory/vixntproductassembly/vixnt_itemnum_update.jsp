<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="numForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/vixntProductAssemblyAction!updateItemNum.action">
	<input type="hidden" id="id" name="id" value="${id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label">数量:</label>
			<div class="col-md-7">
				<input id="num" name="num" value="" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>
