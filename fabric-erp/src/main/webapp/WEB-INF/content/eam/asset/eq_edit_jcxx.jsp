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
	<div class="right_btn">
		<span><a href="#"><img src="${vix}/common/css/images/icons/add.png" alt="" />添加</a></span>
	</div>
	<div class="pagelist drop">
		<ul>
			<li class="tp"><a href="#">选项</a>
				<ul>
					<li><a href="#">删除</a></li>
					<li><a href="#">邮件</a></li>
					<li><a href="#">批量更新</a></li>
					<li><a href="#">合并</a></li>
					<li><a href="#">添加到目标列表</a></li>
					<li><a href="#">导出</a></li>
				</ul></li>
		</ul>
		<strong>选中:0</strong>
		<div>
			<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
		</div>
	</div>
	<div class="table_10">
		<table class="list">
			<tr>
				<th width="50">
					<div class="list_check">
						<div class="drop">
							<label><input name="aaaaa" type="checkbox" value="" /></label>
							<ul>
								<li><a href="#">所有</a></li>
								<li><a href="#">其他</a></li>
								<li><a href="#">式样</a></li>
								<li><a href="#">关闭</a></li>
							</ul>
						</div>
					</div>
				</th>
				<th>&nbsp;</th>
				<th>监测点编号<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
				<th>监测点名称<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
				<th>监测点位置编码<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
				<th>监测项目代码<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
				<th>监测项目名称<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
				<th>值类型<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
				<th>度量单位<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
				<th>最大值<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
				<th>最小值<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
				<th>累计值<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
				<th>&nbsp;</th>
			</tr>
			<!-- 
					  <tr>
						<td><input name="aaaaa" type="checkbox" value="" /></td>
						<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
						<td>8349389</td>
						<td>轴承</td>
						<td>23237886</td>
						<td>TEMP</td>
						<td>温度</td>
						<td>有限值</td>
						<td>摄氏度</td>
						<td>120</td>
						<td>30</td>
						<td>无</td>
						<td>
						<div class="untitled">
							<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
							<div class="popup">
								<strong>
									<i class="close"><a href="#"></a></i>
									<i><a href="#"></a></i>
									<i><a href="#"></a></i>
									<b>帮助</b>							</strong>
								<p>帮助</p>
							</div>
						</div>
						</td>
					  </tr>
 -->
		</table>
	</div>
	<div class="pagelist drop">
		<ul>
			<li class="ed"><a href="#">选项</a>
				<ul>
					<li><a href="#">删除</a></li>
					<li><a href="#">邮件</a></li>
					<li><a href="#">批量更新</a></li>
					<li><a href="#">合并</a></li>
					<li><a href="#">添加到目标列表</a></li>
					<li><a href="#">导出</a></li>
				</ul></li>
		</ul>
		<strong>选中:0</strong>
		<div>
			<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
		</div>
	</div>
</body>
</html>