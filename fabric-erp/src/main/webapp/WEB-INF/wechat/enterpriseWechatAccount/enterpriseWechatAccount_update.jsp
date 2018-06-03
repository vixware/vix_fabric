<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#wxpWeixinSiteform").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="wxpWeixinSiteform">
		<s:hidden id="wxpWeixinSiteId" name="wxpWeixinSiteId" value="%{wxpWeixinSite.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">主题:&nbsp;</td>
					<td><input type="text" id="wxpWeixinSiteName" name="wxpWeixinSiteName" class="validate[required] text-input" value="${wxpWeixinSite.name}" /><span style="color: red;">*</span></td>
					<td></td>
					<td></td>
				</tr>
				<tr height="40">
					<td align="right">CorpID:&nbsp;</td>
					<td colspan="3"><input type="text" id="qiyeCorpId" name="qiyeCorpId" class="validate[required] text-input" value="${wxpWeixinSite.qiyeCorpId}" style="width: 500px;" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">CorpSecret:&nbsp;</td>
					<td colspan="3"><input type="text" id="qiyeSecret" name="qiyeSecret" class="validate[required] text-input" value="${wxpWeixinSite.qiyeSecret}" style="width: 500px;" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">AccessToken:&nbsp;</td>
					<td colspan="3"><input type="text" id="qiyeAccessToken" name="qiyeAccessToken" value="${wxpWeixinSite.qiyeAccessToken}" style="width: 500px;" /></td>
				</tr>
				<tr height="40">
					<td align="right">创建人:&nbsp;</td>
					<td><input type="text" id="creator" name="creator" value="${wxpWeixinSite.creator}" /></td>
					<td align="right">时间:&nbsp;</td>
					<td><input id="createTime" name="createTime" value="<s:date name="%{wxpWeixinSite.createTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" class="validate[required] text-input" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
						style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="memo" value="${wxpWeixinSite.memo}" style="width: 500px;" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>