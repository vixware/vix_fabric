<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>VixWareERP</title>
<script type="text/javascript">
</script>
</head>
<body>
	<!-- top -->
	<!-- head -->

	<div class="sub_menu">
		<h2></h2>
	</div>
	<div class="content">
		<s:form action="" method="post" theme="simple">
			<div class="order_table">
				<table>
					<tr>
						<th width="100">合同编号：</th>
						<td width="150"><s:textfield name="eqCode" type="text"></s:textfield></td>
						<th width="100">合同名称：</th>
						<td width="150"><s:textfield type="text" name="eqName"></s:textfield></td>
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th>设备编号：</th>
						<td><s:textfield name="eqCategory" type="text"></s:textfield></td>
						<th>设备名称：</th>
						<td><s:textfield name="eqType" type="text"></s:textfield></td>
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th>合同备注：</th>
						<td colspan="5"><textarea name="guZhang" cols="78" rows="1"></textarea></td>
					</tr>
					<tr>
						<th>合同文本：</th>
						<td colspan="5">附件</td>
					</tr>
					<tr>
						<th>合同附件：</th>
						<td colspan="5">附件</td>
					</tr>
				</table>
			</div>

			<div class="sub_menu sub_menu_bot">
				<div class="drop">
					<p>
						<a href="###"><span>保存</span></a> <a href="###"><span>取消</span></a>
					</p>
				</div>
			</div>
			<!--submenu-->
		</s:form>
	</div>

	<div id="mainContent" style="display: none"></div>
	<!-- content -->
	<!-- foot -->
</body>
</html>