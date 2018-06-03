<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>VixWareERP-新增设备</title>
<link href="${vix}/common/css/breadcrumb01/breadcrumb01.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
</script>
</head>
<body>
	<s:form id="jbxxForm" name="jbxxForm" action="" method="post" theme="simple">
		<div class="order_table">
			<table>
				<tr>
					<th>延期日期：</th>
					<td><input id="postingDate3" name="postingDate3" value="<fmt:formatDate value='${purchaseOrder.postingDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('postingDate3','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th>用途分类：</th>
					<td><input name="aaaaa" type="text" /></td>
				</tr>
				<tr>
					<th>检验日期：</th>
					<td><input id="postingDate2" name="postingDate2" value="<fmt:formatDate value='${purchaseOrder.postingDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('postingDate2','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th>下次检验日期：</th>
					<td><input id="postingDate1" name="postingDate1" value="<fmt:formatDate value='${purchaseOrder.postingDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('postingDate1','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr>
					<th>注册编号：</th>
					<td><input name="aaaaa" type="text" /></td>
					<th>使用证编号：</th>
					<td><input name="aaaaa" type="text" /></td>
				</tr>
				<tr>
					<th>材质：</th>
					<td><input name="aaaaa" type="text" /></td>
					<th>型式：</th>
					<td><input name="aaaaa" type="text" /></td>
				</tr>
				<tr>
					<th>充水试验高度：</th>
					<td><input name="aaaaa" type="text" /></td>
					<th>安全状况等级：</th>
					<td><input name="aaaaa" type="text" /></td>
				</tr>
				<tr>
					<th>操作压力：</th>
					<td><input name="aaaaa" type="text" /></td>
					<th>压力等级：</th>
					<td><input name="aaaaa" type="text" /></td>
				</tr>
				<tr>
					<th>容器编号：</th>
					<td><input name="aaaaa" type="text" /></td>
					<th>容器分类：</th>
					<td><input name="aaaaa" type="text" /></td>
				</tr>
				<tr>
					<th>容积(m³)：</th>
					<td><input name="aaaaa" type="text" /></td>
					<th>介质：</th>
					<td><input name="aaaaa" type="text" /></td>
				</tr>
				<tr>
					<th>设计压力：</th>
					<td><input name="aaaaa" type="text" /></td>
					<th>设计温度：</th>
					<td><input name="aaaaa" type="text" /></td>
				</tr>
				<tr>
					<th>最高温度：</th>
					<td><input name="aaaaa" type="text" /></td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
</html>