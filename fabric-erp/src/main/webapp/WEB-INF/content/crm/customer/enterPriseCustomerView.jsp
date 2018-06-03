<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />Print</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />Help</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountAction!goList.action');">客户</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="npcontent clearfix">
		<div class="np_right">
			<div class="np_right_box">
				<div class="nprb_title">
					客户信息 <a style="cursor: help;"><img src="img/infild.png" width="16" height="16" style="vertical-align: middle;" />提示</a>
				</div>
				<div class="ld">
					<table border="0" width="100%;">
						<tr>
							<td>合同：</td>
							<td align="right">￥0.00</td>
						</tr>
						<tr>
							<td>回款计划：</td>
							<td align="right">￥0.00</td>
						</tr>
						<tr>
							<td>已回款：</td>
							<td align="right">￥0.00</td>
						</tr>
						<tr>
							<td>已发货/交付产品：</td>
							<td align="right">￥0.00</td>
						</tr>
						<tr>
							<td>已开票金额：</td>
							<td align="right">￥0.00</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="np_right_box">
				<div class="nprb_title">往来对账</div>
				<p align="right">
					<a href="#" title="交付/回款 月度对帐">交付/回款 月度对帐>></a>
				</p>
			</div>
			<div class="np_right_box">
				<div class="nprb_title">
					费用<a href="#">(新建/明细/审批)</a>
				</div>
				<div class="ld">
					<table border="0" width="100%;">
						<tr>
							<td>已审批费用：</td>
							<td align="right">￥0.00</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="np_right_box">
				<div class="nprb_title">
					联系人<a href="#">(新建/明细/审批)</a>
				</div>
				<p align="center">
					<img border="0" src="img/n2.png"><br>
					<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br />
					<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br />
					<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
				</p>
			</div>
		</div>
		<div class="np_left clearfix" id="common_table">
			<div class="np_left_title">
				<h2 style="float: none;">客户信息</h2>
				<div>
					<p>
						<a href="#" class="abtn"><span>编辑</span></a><a href="#" class="abtn"><span>删除</span></a>
					</p>
					数据所有人：boss <a href="#"><img src="img/address_book.png" width="16" height="16" />共享</a> <a href="#"><img src="img/address_book.png" width="16" height="16" />转移</a> <a href="#"><img src="img/address_book.png" width="16" height="16" />数据日志</a>
				</div>
			</div>
			<div class="task_list">
				<table width="100%">
					<tr>
						<th colspan="4">客户名称：<b>${customerAccount.name}</b></th>
					</tr>
					<tr>
						<td class="ar">热点客户：</td>
						<td><s:if test="customerAccount.isHotCustomer == '1'">是</s:if> <s:else>否</s:else></td>
						<td class="ar">热度：</td>
						<td>${customerAccount.hotLevel.name}</td>
					</tr>
					<tr>
						<td class="ar">客户种类：</td>
						<td>${customerAccount.customerType.name }</td>
						<td class="ar">热点说明：</td>
						<td>热点说明</td>
					</tr>
					<tr>
						<td colspan="4"><strong>基本信息</strong></td>
					</tr>
					<tr>
						<td>助记简称：</td>
						<td>${customerAccount.shorterName }</td>
						<td>价值评估：</td>
						<td><s:if test="customerAccount.valueAssessment == 1">高</s:if> <s:elseif test="customerAccount.valueAssessment == 2">中</s:elseif> <s:elseif test="customerAccount.valueAssessment == 3">低</s:elseif> <s:else>中</s:else></td>
					</tr>
					<tr>
						<td>编码：</td>
						<td>${customerAccount.code}</td>
						<td>信用等级：</td>
						<td><s:if test="customerAccount.valueAssessment == 1">高</s:if> <s:elseif test="customerAccount.valueAssessment == 2">中</s:elseif> <s:elseif test="customerAccount.valueAssessment == 3">低</s:elseif> <s:else>中</s:else></td>
					</tr>
					<tr>
						<td>种类：</td>
						<td>VIP客户</td>
						<td>行业：</td>
						<td>${customerAccount.industry.name }</td>
					</tr>
					<tr>
						<td>关系等级：</td>
						<td>${customerAccount.relationshipClass.name }</td>
						<td>人员规模：</td>
						<td>${customerAccount.staffScale.name }</td>
					</tr>
					<tr>
						<td>来源：</td>
						<td>${customerAccount.customerSource.name }</td>
						<td>阶段：</td>
						<td>${customerAccount.customerStage.name }</td>
					</tr>
					<tr>
						<td>标签：</td>
						<td colspan="3">已沟通,已报价,已做方案,已签合同,已成交</td>
					</tr>
					<tr>
						<td>上级客户：</td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td>公司简介：</td>
						<td colspan="3">公司简介</td>
					</tr>
					<tr>
						<td colspan="4"><strong>联系方式</strong></td>
					</tr>
					<tr>
						<td>国家或地区：</td>
						<td colspan="3">CN China中国 亚洲</td>
					</tr>
					<tr>
						<td>邮编：</td>
						<td>100000</td>
						<td>电话：</td>
						<td>13888888888</td>
					</tr>
					<tr>
						<td>省份：</td>
						<td>北京</td>
						<td>传真：</td>
						<td>010-00000000-01</td>
					</tr>
					<tr>
						<td>城市：</td>
						<td>市辖区</td>
						<td>网址：</td>
						<td><a href="http://www.baidu.com/" target="_blank">http://www.baidu.com</a></td>
					</tr>
					<tr>
						<td>区县：</td>
						<td>朝阳区</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>地址：</td>
						<td colspan="3">已沟通,已报价,已做方案,已签合同,已成交</td>
					</tr>
					<tr>
						<td colspan="4"><strong>备注</strong></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td colspan="3">${customerAccount.memo}</td>
					</tr>
					<tr>
						<td>创建日期：</td>
						<td>昨天</td>
						<td>更新日期：</td>
						<td>2012-10-05</td>
					</tr>
				</table>
				<div class="lineh30">
					<p>
						<img src="img/file.gif" width="16" height="16" /><a href="#">n72995.jpg</a><span class="gray">[128k](2005-06-17)</span><a href="#"><img src="img/delete.gif" width="12" height="12" /></a>
					</p>
					<form action="" method="get">
						附件上传:<input name="" type="file" /> <input class="btn" name="" type="button" value="上传文件" /> <span class="gray">(上传的单一文件不能大于<span class="red">5M</span>)
						</span>
					</form>
				</div>
			</div>
		</div>
		<div class="atable">
			<div class="task_list">
				<table width="100%" class="newtab">
					<tr>
						<th colspan="3" style="text-align: center">客户跟踪</th>
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="np_border">
							<table width="100%">
								<tr>
									<th>日程</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">阿道夫</a> 2012-10-31 6:00<br /> ■ <a href="#">阿道夫</a> 2012-10-31 6:00

									</td>
								</tr>
							</table>
						</td>
						<td></td>
						<td class="np_border">
							<table width="100%">
								<tr>
									<th>待办任务</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">阿道夫</a> 电话会议<br /> ■ <a href="#">阿道夫</a> 电话会议<br /> ■ <a href="#">阿道夫</a> 电话会议
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" class="newtab">
					<tr>
						<th><a href="#" class="abtn r" style="margin-top: 5px; display: inline;"><span>新建行动历史记录</span></a>行动历史记录</th>
					</tr>
					<tr>
						<td>
							<div style="overflow: hidden;">
								<div class="wauto">
									<table id="test"></table>
								</div>
							</div>
						</td>
					</tr>
				</table>
				<table width="100%" class="newtab">
					<tr>
						<th><a href="#" class="abtn r" style="margin-top: 5px; display: inline;"><span>新建行动历史记录</span></a>行动历史记录</th>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" class="newtab newtab_red">
					<tr>
						<th colspan="3" style="text-align: center">售 前</th>
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="np_border">
							<table width="100%">
								<tr>
									<th>销售机会</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 初期沟通跟踪<br /> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 初期沟通跟踪<br /> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 初期沟通跟踪<br /> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 初期沟通跟踪<br />
									</td>
								</tr>
							</table>
						</td>
						<td></td>
						<td class="np_border">
							<table width="100%">
								<tr>
									<th>报价单(历史报价) <span class="gray">可转成订单</span>
									</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ 2006-06-30的报价 2006-06-30 报价:￥37930.00 【KP502：30台+安控综合方案】<br /> <a href="#">&gt;&gt;编辑明细</a> <a href="#">&gt;&gt;查看明细</a> <a href="#">&gt;&gt;转成订单</a><br /> ■ 2006-06-30的报价 2006-06-30 报价:￥37930.00 【KP502：30台+安控综合方案】<br /> <a href="#">&gt;&gt;编辑明细</a> <a href="#">&gt;&gt;查看明细</a> <a href="#">&gt;&gt;转成订单</a><br />
										■ 2006-06-30的报价 2006-06-30 报价:￥37930.00 【KP502：30台+安控综合方案】<br /> <a href="#">&gt;&gt;编辑明细</a> <a href="#">&gt;&gt;查看明细</a> <a href="#">&gt;&gt;转成订单</a><br /> <a href="#">已报价产品&gt;&gt;</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" class="newtab  newtab_blue">
					<tr>
						<th colspan="3" style="text-align: center">售 中</th>
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="np_border">
							<table width="100%">
								<tr>
									<th>合同</th>
									<th style="text-align: right;"><a class="abtn" href="javascript:;" onclick="javascript:addContract();"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br /> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br /> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br />
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<th>回款</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">2006-06-30</a> [1期]投影仪3M/3M X80C[台] 1(1)<br /> ■ 2006-06-30 [1期]投影仪3M/3M X80C[台] 1(1)<br />
									</td>
								</tr>
							</table>
						</td>
						<td></td>
						<td class="np_border">
							<table width="100%">
								<tr>
									<th>合同交付计划</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">2006-06-30</a> [1期]投影仪3M/3M X80C[台] 1(1)<br /> ■ 2006-06-30 [1期]投影仪3M/3M X80C[台] 1(1)<br />
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<th>合同交付记录/明细</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">2006-06-30</a>T35型电源模块/9000[个] <b>2</b> 1,800.00 <a href="#">关联</a><br /> ■ <a href="#">2006-06-30</a>T35型电源模块/9000[个] <b>2</b> 1,800.00 <a href="#">关联</a><br />
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<th>发票</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br /> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br /> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" class="newtab  newtab_yellow">
					<tr>
						<th colspan="3" style="text-align: center">售 后</th>
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="np_border">
							<table width="100%">
								<tr>
									<th>客户服务</th>
									<th style="text-align: right;"><a class="abtn" href="javascript:;" onclick="javascript:addService();"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">2006-06-30</a> [1期]投影仪3M/3M X80C[台] 1(1)<br /> ■ 2006-06-30 [1期]投影仪3M/3M X80C[台] 1(1)<br />
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<th>回款计划</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">2006-06-30</a> [1期]投影仪3M/3M X80C[台] 1(1)<br /> ■ 2006-06-30 [1期]投影仪3M/3M X80C[台] 1(1)<br />
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<th>开票记录</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">2006-06-30</a>T35型电源模块/9000[个] <b>2</b> 1,800.00 <a href="#">关联</a><br /> ■ <a href="#">2006-06-30</a>T35型电源模块/9000[个] <b>2</b> 1,800.00 <a href="#">关联</a><br />
									</td>
								</tr>
							</table>
						</td>
						<td></td>
						<td class="np_border">
							<table width="100%">
								<tr>
									<th>客户投诉</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br /> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br /> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br />
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<th>回款记录</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br /> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br /> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" class="newtab">
					<tr>
						<th style="text-align: center">所有客户</th>
					</tr>
					<tr>
						<td class="np_border">
							<table width="100%">
								<tr>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
								</tr>
								<tr>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br>
											<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br>
											<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br>
											<img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>