<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>VixWareERP-存档设备</title>
<script type="text/javascript">
</script>
</head>
<body>
	<!-- top -->
	<!-- head -->

	<div class="sub_menu">
		<h2>
			<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" />打印</a> <a href="#"><img src="${vix}/common/img/icon_15.gif" alt="" />帮助</a>
			</i> <strong><img src="${vix}/common/img/drp_channel.png" alt="" />新增设备</strong>
		</h2>
	</div>
	<div class="content">
		<s:form action="" method="post" theme="simple">
			<div class="order">
				<dl>

					<dd>
						<div class="order_table">
							<table>
								<tr>
									<th>设备名称：</th>
									<td><s:textfield type="text" name="eqName"></s:textfield></td>
									<th>零件号：</th>
									<td><s:textfield name="eqPartSn" type="text"></s:textfield></td>
									<th>序号：</th>
									<td><s:textfield name="eqSerialNo" type="text"></s:textfield></td>
								</tr>
								<tr>
									<th>设备类别：</th>
									<td><s:select name="eqCategory" list="#{'1':'基础数据维护'}" theme="simple"></s:select></td>
									<th>设备类型：</th>
									<td><s:select name="eqType" list="#{'1':'基础数据维护'}" theme="simple"></s:select></td>
									<th>设备状态：</th>
									<td><s:select name="eqSatus" list="#{'1':'设计','2':'运行','3':'库存','4':'维修','5':'退出运行','6':'废弃'}" theme="simple"></s:select></td>
								</tr>
								<tr>
									<th>设备组号：</th>
									<td><s:textfield name="eqGroupCode" type="text"></s:textfield></td>
									<th>设备组名称：</th>
									<td><s:textfield name="eqGroupName" type="text"></s:textfield></td>
									<th>重要程度：</th>
									<td><s:select name="criticality" list="#{'1':'不重要(NC)', '2':'重要程度低(LC)', '3':'中等重要程度(MC)', '4':'高重要程度(HC)', '5':'对环境有特殊影响(EC)'}" theme="simple"></s:select></td>
								</tr>
								<tr>
									<th>安装房间号：</th>
									<td><s:textfield name="eqRoomCode" type="text"></s:textfield></td>
									<th>安装位置编号：</th>
									<td><s:textfield name="eqPlaceCode" type="text"></s:textfield></td>
									<th>型号规格：</th>
									<td><s:select name="modelnum" list="#{'1':'基础数据维护'}" theme="simple"></s:select></td>
								</tr>
								<tr>
									<th>父设备编码：</th>
									<td><s:select name="parentEquipment" list="#{'1':'值列表选择'}" theme="simple"></s:select></td>
									<th>父设备名称：</th>
									<td><s:textfield name="parentEqname" type="text"></s:textfield></td>
									<th>技术等级：</th>
									<td><s:select name="techClass" list="#{'1':'值列表选择'}" theme="simple"></s:select></td>
								</tr>
								<tr>
									<th>度量单位：</th>
									<td><s:textfield name="measureUnit" type="text"></s:textfield></td>
									<th>上限值：</th>
									<td><s:textfield name="upLimit" type="text"></s:textfield></td>
									<th>下限值：</th>
									<td><s:textfield name="downLimit" type="text"></s:textfield></td>
								</tr>
								<tr>
									<th>客户名称：</th>
									<td><s:textfield name="kehuMingcheng" type="text"></s:textfield></td>
									<th>采购单号：</th>
									<td><s:textfield name="caigouDanhao" type="text"></s:textfield></td>
									<th>采购日期：</th>
									<td><input id="_caigouRiqi" name="caigouRiqi" value="<fmt:formatDate value='${purchaseOrder.caigouRiqi }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_caigouRiqi','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								</tr>
								<tr>
									<th>设备账号：</th>
									<td><s:textfield name="eqAccount" type="text"></s:textfield></td>
									<th>设备成本：</th>
									<td><s:textfield name="totalCost" type="text"></s:textfield></td>
									<th>保修期：</th>
									<td><input id="_warrantyDate" name="warrantyDate" value="<fmt:formatDate value='${purchaseOrder.warrantyDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_warrantyDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								</tr>
								<tr>
									<th>采购价格：</th>
									<td><s:textfield name="caigouJiage" type="text"></s:textfield></td>
									<th>生产商：</th>
									<td><s:textfield name="madeName" type="text"></s:textfield></td>
									<th>供应商：</th>
									<td><s:textfield name="supplyName" type="text"></s:textfield></td>
								</tr>
								<tr>
									<th>出厂日期：</th>
									<td><input id="_outFactoryDate" name="outFactoryDate" value="<fmt:formatDate value='${purchaseOrder.outFactoryDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_outFactoryDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
									<th>出厂编号：</th>
									<td><s:textfield name="chuchangBianhao" type="text"></s:textfield></td>
									<th>资产编号：</th>
									<td><s:textfield name="zichanBianhao" type="text"></s:textfield></td>
								</tr>
								<tr>
									<th>设备尺寸：</th>
									<td><s:textfield name="eqSize" type="text"></s:textfield></td>
									<th>设备重量：</th>
									<td><s:textfield name="weight" type="text"></s:textfield></td>
									<th>重量单位：</th>
									<td><s:textfield name="wunit" type="text"></s:textfield></td>
								</tr>
								<tr>
									<th>安装日期：</th>
									<td><input id="_installDate" name="installDate" value="<fmt:formatDate value='${purchaseOrder.installDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_installDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
									<th>启用日期：</th>
									<td><input id="_putinDate" name="putinDate" value="<fmt:formatDate value='${purchaseOrder.putinDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_putinDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								</tr>
								<tr>
									<th>设备下是否有其他设备：</th>
									<td><s:select name="hasSub" list="#{'1':'是', '2':'否'}" theme="simple"></s:select></td>
									<th>是否与其他设备相连接：</th>
									<td><s:select name="isConnected" list="#{'1':'是', '2':'否'}" theme="simple"></s:select></td>
								</tr>
								<tr>
									<th>设备描述：</th>
									<td><textarea name="description" cols="19" rows="3"></textarea></td>
								</tr>
							</table>
						</div>
						<div class="right_menu">
							<ul>
								<li class="current"><a onclick="tab(6,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />监测信息</a></li>
								<li><a onclick="tab(6,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />技术参数</a></li>
								<li><a onclick="tab(6,3,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />备件信息</a></li>
								<li><a onclick="tab(6,4,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />保修信息</a></li>
								<li><a onclick="tab(6,5,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />日志信息</a></li>
							</ul>
						</div>
						<div id="a1">
							<div class="right_btn">
								<span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span>
								<span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span>
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
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>8349369</td>
										<td>电池</td>
										<td>23237990</td>
										<td>OT</td>
										<td>运行时间</td>
										<td>累计值</td>
										<td>小时</td>
										<td>无</td>
										<td>无</td>
										<td>8000</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
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
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
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
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
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
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>8349369</td>
										<td>电池</td>
										<td>23237990</td>
										<td>OT</td>
										<td>运行时间</td>
										<td>累计值</td>
										<td>小时</td>
										<td>无</td>
										<td>无</td>
										<td>8000</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>8349369</td>
										<td>电池</td>
										<td>23237990</td>
										<td>OT</td>
										<td>运行时间</td>
										<td>累计值</td>
										<td>小时</td>
										<td>无</td>
										<td>无</td>
										<td>8000</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>8349369</td>
										<td>电池</td>
										<td>23237990</td>
										<td>OT</td>
										<td>运行时间</td>
										<td>累计值</td>
										<td>小时</td>
										<td>无</td>
										<td>无</td>
										<td>8000</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
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
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
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
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
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
						</div>
						<div id="a2" style="display: none;">
							<div class="right_btn">
								<span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span>
								<span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span>
							</div>
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
						</div>
						<div id="a3" style="display: none;">
							<div class="right_btn">
								<span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span>
								<span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span>
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
										<th>备件编码<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>备件名称<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>规格型号<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>计量单位<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>储备定额<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>实际库存<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>描述<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>&nbsp;</th>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>45335456</td>
										<td>电机备件包</td>
										<td>AK47</td>
										<td>个</td>
										<td>10</td>
										<td>20</td>
										<td>变压器专用备件</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>45335456</td>
										<td>电机备件包</td>
										<td>AK47</td>
										<td>个</td>
										<td>10</td>
										<td>20</td>
										<td>变压器专用备件</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>45335456</td>
										<td>电机备件包</td>
										<td>AK47</td>
										<td>个</td>
										<td>10</td>
										<td>20</td>
										<td>变压器专用备件</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>45335456</td>
										<td>电机备件包</td>
										<td>AK47</td>
										<td>个</td>
										<td>10</td>
										<td>20</td>
										<td>变压器专用备件</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>45335456</td>
										<td>电机备件包</td>
										<td>AK47</td>
										<td>个</td>
										<td>10</td>
										<td>20</td>
										<td>变压器专用备件</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>45335456</td>
										<td>电机备件包</td>
										<td>AK47</td>
										<td>个</td>
										<td>10</td>
										<td>20</td>
										<td>变压器专用备件</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>45335456</td>
										<td>电机备件包</td>
										<td>AK47</td>
										<td>个</td>
										<td>10</td>
										<td>20</td>
										<td>变压器专用备件</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>45335456</td>
										<td>电机备件包</td>
										<td>AK47</td>
										<td>个</td>
										<td>10</td>
										<td>20</td>
										<td>变压器专用备件</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>45335456</td>
										<td>电机备件包</td>
										<td>AK47</td>
										<td>个</td>
										<td>10</td>
										<td>20</td>
										<td>变压器专用备件</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>45335456</td>
										<td>电机备件包</td>
										<td>AK47</td>
										<td>个</td>
										<td>10</td>
										<td>20</td>
										<td>变压器专用备件</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
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
						</div>
						<div id="a4" style="display: none;">
							<div class="right_btn">
								<span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span>
								<span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/address_book.png" alt="" /></a></span>
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
										<th>保修类型<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>保修期<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>保修期单位<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>保修故障信息<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>描述<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>&nbsp;</th>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>制造保修</td>
										<td>3</td>
										<td>年</td>
										<td>维护</td>
										<td>日常维护</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="/vix/content/scm/pm/po.jsp"></a></i> <i><a href="/vix/content/scm/pm/poModify.jsp"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>制造保修</td>
										<td>3</td>
										<td>年</td>
										<td>检查</td>
										<td>定期测试检查</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="/vix/content/scm/pm/po.jsp"></a></i> <i><a href="/vix/content/scm/pm/poModify.jsp"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>制造保修</td>
										<td>3</td>
										<td>年</td>
										<td>检查</td>
										<td>加油润滑</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="/vix/content/scm/pm/po.jsp"></a></i> <i><a href="/vix/content/scm/pm/poModify.jsp"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>制造保修</td>
										<td>3</td>
										<td>年</td>
										<td>毁坏</td>
										<td>更换部件</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="/vix/content/scm/pm/po.jsp"></a></i> <i><a href="/vix/content/scm/pm/poModify.jsp"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>制造保修</td>
										<td>3</td>
										<td>年</td>
										<td>毁坏</td>
										<td>返厂修理</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="/vix/content/scm/pm/po.jsp"></a></i> <i><a href="/vix/content/scm/pm/poModify.jsp"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>制造保修</td>
										<td>3</td>
										<td>年</td>
										<td>毁坏</td>
										<td>现场维修</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="/vix/content/scm/pm/po.jsp"></a></i> <i><a href="/vix/content/scm/pm/poModify.jsp"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>附加保修</td>
										<td>3</td>
										<td>年</td>
										<td>毁坏</td>
										<td>定期检测</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="/vix/content/scm/pm/po.jsp"></a></i> <i><a href="/vix/content/scm/pm/poModify.jsp"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>附加保修</td>
										<td>3</td>
										<td>年</td>
										<td>毁坏</td>
										<td>定期检测</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="/vix/content/scm/pm/po.jsp"></a></i> <i><a href="/vix/content/scm/pm/poModify.jsp"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>外延的损坏保修</td>
										<td>3</td>
										<td>年</td>
										<td>毁坏</td>
										<td>退货</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="/vix/content/scm/pm/po.jsp"></a></i> <i><a href="/vix/content/scm/pm/poModify.jsp"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>外延的损坏保修</td>
										<td>3</td>
										<td>年</td>
										<td>毁坏</td>
										<td>退货</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i class="close"><a href="#"></a></i> <i><a href="/vix/content/scm/pm/po.jsp"></a></i> <i><a href="/vix/content/scm/pm/poModify.jsp"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
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
						</div>
						<div id="a5" style="display: none;">
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
										<th>操作人<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>操作内容<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>序号<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>操作时间<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
										<th>&nbsp;</th>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>李五</td>
										<td>负责人变更</td>
										<td>8899870</td>
										<td>2000-1-1</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>李五</td>
										<td>负责人变更</td>
										<td>8899870</td>
										<td>2000-1-1</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>李五</td>
										<td>负责人变更</td>
										<td>8899870</td>
										<td>2000-1-1</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>李五</td>
										<td>负责人变更</td>
										<td>8899870</td>
										<td>2000-1-1</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>李五</td>
										<td>负责人变更</td>
										<td>8899870</td>
										<td>2000-1-1</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>李五</td>
										<td>负责人变更</td>
										<td>8899870</td>
										<td>2000-1-1</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>李五</td>
										<td>负责人变更</td>
										<td>8899870</td>
										<td>2000-1-1</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>李五</td>
										<td>负责人变更</td>
										<td>8899870</td>
										<td>2000-1-1</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>李五</td>
										<td>负责人变更</td>
										<td>8899870</td>
										<td>2000-1-1</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><input name="aaaaa" type="checkbox" value="" /></td>
										<td><img src="${vix}/common/img/icon_edit.png" alt="" title="编辑" class="hand" /></td>
										<td>李五</td>
										<td>负责人变更</td>
										<td>8899870</td>
										<td>2000-1-1</td>
										<td>
											<div class="untitled">
												<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
												<div class="popup">
													<strong> <i><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
													</strong>
													<p>帮助</p>
												</div>
											</div>
										</td>
									</tr>
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
						</div>
					</dd>
				</dl>
			</div>
			<!--oder-->
			<div class="sub_menu sub_menu_bot">
				<div class="drop">
					<p>
						<a href="#"><span>编辑</span></a> <a href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并新建</span></a> <a href="#"><span>关闭</span></a> <a href="#" id="text"><span>弹出窗口测试</span></a>
					</p>
					<ul>
						<li><a href="#"><span>帮助</span></a>
							<ul>
								<li><a href="#">帮助</a></li>
								<li><a href="#">帮助</a></li>
								<li><a href="#">帮助</a></li>
								<li><a href="#">帮助</a></li>
							</ul></li>
					</ul>
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