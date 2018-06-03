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
						<th>设备名称：</th>
						<td><s:textfield type="text" name="eqName"></s:textfield></td>
						<th>设备编号：</th>
						<td><s:textfield name="eqCode" type="text"></s:textfield></td>
						<th>设备状态：</th>
						<td><s:select name="eqSatus" list="#{'1':'设计','2':'运行','3':'库存','4':'维修','5':'退出运行','6':'废弃'}" theme="simple"></s:select></td>
					</tr>
					<tr>
						<th>父设备名称：</th>
						<td><s:textfield name="parentEqname" type="text"></s:textfield></td>
						<th>父设备编码：</th>
						<td><s:textfield name="parentEquipment" type="text"></s:textfield></td>
						<th>设备描述：</th>
						<td rowspan="2"><textarea name="description" cols="19" rows="2"></textarea></td>
					</tr>
					<tr>
						<th>设备类别：</th>
						<td><s:textfield name="eqCategory" type="text"></s:textfield></td>
						<th>设备类型：</th>
						<td><s:textfield name="eqType" type="text"></s:textfield></td>
					</tr>
					<tr>
						<th>安装日期：</th>
						<td><input id="_installDate" name="outFactoryDate" value="<fmt:formatDate value='${purchaseOrder.outFactoryDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /></td>
						<th>出厂日期：</th>
						<td><input id="_outFactoryDate" name="outFactoryDate" value="<fmt:formatDate value='${purchaseOrder.outFactoryDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /></td>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<td></td>
						<th></th>
						<td></td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>故障描述：</th>
						<td colspan="5"><textarea name="guZhang" cols="57" rows="1"></textarea></td>
					</tr>
					<tr>
						<th>负责人：</th>
						<td><s:textfield name="fuZeRen" type="text"></s:textfield></td>
						<th>联系方式：</th>
						<td><s:textfield name="lianXiFangShi" type="text"></s:textfield></td>
						<th>故障日期：</th>
						<td><input id="_guZhangRiqi" name="guZhangRiqi" value="<fmt:formatDate value='${purchaseOrder.guZhangRiqi }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_guZhangRiqi','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
						<a href="###"><span>提交</span></a> <a href="###"><span>取消</span></a>
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