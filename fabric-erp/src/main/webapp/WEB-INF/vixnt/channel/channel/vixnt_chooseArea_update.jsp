<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="vixntDistributionSystemForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/channel/vixntChannelAction!saveChannelDistributorByArea.action">
	<input type="hidden" id="parentId" name="parentId" value="${parentId}" />
	<input type="hidden" id="type" name="type" value="${type}" />
	<input type="hidden" id="treeType" name="treeType" value="${treeType}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"> 区域:</label>
			<div class="col-md-10">
				<select id="area" name="area" class="form-control validate[required]">
					<option value="北京朝阳">北京朝阳</option>
					<option value="北京海淀">北京海淀</option>
					<option value="北京东城">北京东城</option>
					<option value="北京昌平">北京昌平</option>
				</select>
			</div>
		</div>
	</fieldset>
</form>