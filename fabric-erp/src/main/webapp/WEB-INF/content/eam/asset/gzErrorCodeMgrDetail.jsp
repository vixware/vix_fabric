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
						<th>故障代码：</th>
						<td><s:textfield name="eqCode" type="text"></s:textfield></td>
						<th>故障名称：</th>
						<td><s:textfield type="text" name="eqName"></s:textfield></td>
						<th>状态：</th>
						<td><s:select name="eqSatus" list="#{'1':'设计','2':'运行','3':'库存','4':'维修','5':'退出运行','6':'废弃'}" theme="simple"></s:select></td>
					</tr>
					<tr>
						<th>设备类别：</th>
						<td><s:textfield name="eqCategory" type="text"></s:textfield></td>
						<th>设备类型：</th>
						<td><s:textfield name="eqType" type="text"></s:textfield></td>
					</tr>
					<tr>
						<th>故障描述：</th>
						<td colspan="5"><textarea name="guZhang" cols="57" rows="1"></textarea></td>
					</tr>
					<tr>
						<th>移动选项：</th>
						<td><label for="ero_1"><input type="radio" name="eqRepairOption" id="ero_1" value="1" checked="checked" />维修申请</label> <label for="ero_2"><input type="radio" name="eqRepairOption" id="ero_2" value="2" />维修记录</label></td>
						<th>严重程度：</th>
						<td colspan="5"><label for="eho_1"><input type="radio" name="eqHurryOption" id="eho_1" value="1" checked="checked" />正常</label> <label for="eho_2"><input type="radio" name="eqHurryOption" id="eho_2" value="2" />紧急</label></td>
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