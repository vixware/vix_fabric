<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript"> 
<!--
	//面包屑
	if($('.sub_menu').length)
	{
		$("#breadCrumb").jBreadCrumb();
	}
	
	/* a-z字母排序 */
	$(function(){
		if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
	});

//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_order.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="pur_purchase_manage" /></a></li>
				<li><a href="#"><s:text name="pur_purchase_order" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goList.action');">订单查看</a></li>
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
					相关摘要(自动合计) <a title="注意：若有退货发生时，相应的产品数量/金额等是由发货明细/交付记录-退货明细自动计算得出。" style="cursor: help;"><img src="img/infild.png" width="16" height="16" style="vertical-align: middle;" />提示</a>
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
					<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br /> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br /> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
				</p>
			</div>
		</div>
		<div class="np_left clearfix" id="common_table">
			<div class="np_left_title">
				<h2 style="float: none;">订单${purchaseOrder.name }信息</h2>
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
						<th colspan="4">客户名称：<b>张理</b></th>
					</tr>
					<tr>
						<td class="ar">热点客户：</td>
						<td>是</td>
						<td class="ar">热度：</td>
						<td>高热</td>
					</tr>
					<tr>
						<td class="ar">热点分类：</td>
						<td>分类三</td>
						<td class="ar">热点说明：</td>
						<td>热点说明</td>
					</tr>
					<tr>
						<td colspan="4"><strong>基本信息</strong></td>
					</tr>
					<tr>
						<td class="ar bggray">助记简称：</td>
						<td>助记简称</td>
						<td class="ar bggray">价值评估：</td>
						<td>高</td>
					</tr>
					<tr>
						<td class="ar bggray">编号：</td>
						<td>123456</td>
						<td class="ar bggray">信用等级：</td>
						<td>高</td>
					</tr>
					<tr>
						<td class="ar bggray">种类：</td>
						<td>VIP客户</td>
						<td class="ar bggray">行业：</td>
						<td>工业</td>
					</tr>
					<tr>
						<td class="ar bggray">关系等级：</td>
						<td>密切</td>
						<td class="ar bggray">人员规模：</td>
						<td>200人以上</td>
					</tr>
					<tr>
						<td class="ar bggray">来源：</td>
						<td>电话来访</td>
						<td class="ar bggray">阶段：</td>
						<td>1.售前跟踪</td>
					</tr>
					<tr>
						<td class="ar bggray">标签：</td>
						<td colspan="3">已沟通,已报价,已做方案,已签合同,已成交</td>
					</tr>
					<tr>
						<td class="ar bggray">上级客户：</td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="ar bggray">公司简介：</td>
						<td colspan="3">公司简介</td>
					</tr>
					<tr>
						<td colspan="4"><strong>联系方式</strong></td>
					</tr>
					<tr>
						<td class="ar bggray">国家或地区：</td>
						<td colspan="3">CN China中国 亚洲</td>
					</tr>
					<tr>
						<td class="ar bggray">邮编：</td>
						<td>100000</td>
						<td class="ar bggray">电话：</td>
						<td>13888888888</td>
					</tr>
					<tr>
						<td class="ar bggray">省份：</td>
						<td>北京</td>
						<td class="ar bggray">传真：</td>
						<td>010-00000000-01</td>
					</tr>
					<tr>
						<td class="ar bggray">城市：</td>
						<td>市辖区</td>
						<td class="ar bggray">网址：</td>
						<td><a href="http://www.baidu.com/" target="_blank">http://www.baidu.com</a></td>
					</tr>
					<tr>
						<td class="ar bggray">区县：</td>
						<td>朝阳区</td>
						<td class="ar bggray"></td>
						<td></td>
					</tr>
					<tr>
						<td class="ar bggray">地址：</td>
						<td colspan="3">已沟通,已报价,已做方案,已签合同,已成交</td>
					</tr>
					<tr>
						<td colspan="4"><strong>备注</strong></td>
					</tr>
					<tr>
						<td class="ar bggray">备注：</td>
						<td colspan="3">备注</td>
					</tr>
					<tr>
						<td class="ar bggray">创建日期：</td>
						<td>昨天</td>
						<td class="ar bggray">更新日期：</td>
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
							<div class="table_oy">
								<table width="100%">
									<tr>
										<td>138</td>
										<td>日程</td>
										<td>拜访了解服务情况</td>
										<td>2007-12-25</td>
										<td>杜洪博→【赵隆】</td>
									</tr>
									<tr>
										<td>138</td>
										<td>日程</td>
										<td>拜访了解服务情况</td>
										<td>2007-12-25</td>
										<td>杜洪博→【赵隆】</td>
									</tr>
									<tr>
										<td>138</td>
										<td>日程</td>
										<td>拜访了解服务情况</td>
										<td>2007-12-25</td>
										<td>杜洪博→【赵隆】</td>
									</tr>
									<tr>
										<td>138</td>
										<td>日程</td>
										<td>拜访了解服务情况</td>
										<td>2007-12-25</td>
										<td>杜洪博→【赵隆】</td>
									</tr>
									<tr>
										<td>138</td>
										<td>日程</td>
										<td>拜访了解服务情况</td>
										<td>2007-12-25</td>
										<td>杜洪博→【赵隆】</td>
									</tr>
								</table>
							</div>
						</td>
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
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
								</tr>
								<tr>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
										</p>
									</td>
									<td style="text-align: center;" align="center">
										<p align="center">
											<img border="0" src="img/n2.png"><br> <a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br> <b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br> <img border="0" align="AbsMiddle" src="img/day.gif">纪念日 <br>我
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
<!-- content -->
<div id="footpanel">
	<ul id="mainpanel">
		<li><a href="http://www.designbombs.com" class="home">Inspiration <small>Design </small></a></li>
		<li><a href="http://www.designbombs.com" class="profile">View Profile <small>View Profile</small></a></li>
		<li><a href="http://www.designbombs.com" class="editprofile">Edit Profile <small>Edit Profile</small></a></li>
		<li><a href="http://www.designbombs.com" class="contacts">Contacts <small>Contacts</small></a></li>
		<li><a href="http://www.designbombs.com" class="messages">Messages (10) <small>Messages</small></a></li>
		<li><a href="http://www.designbombs.com" class="playlist">Play List <small>Play List</small></a></li>
		<li><a href="http://www.designbombs.com" class="videos">Videos <small>Videos</small></a></li>
		<li id="alertpanel"><a class="alerts" id="alerts_off">Alerts</a></li>
		<li id="chatpanel"><a href="#" class="chat">Friends (<strong>18</strong>)
		</a>
			<div class="subpanel">
				<h3>
					<span> &ndash; </span>Friends Online
				</h3>
				<ul>
					<li><span>Family Members</span></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><span>Other Friends</span></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
				</ul>
			</div></li>
	</ul>
</div>
<div id="footpanel_switch">
	<ul id="mainpanel">
		<li id="alertpanel"><a class="alerts" id="alerts_on">Alerts</a></li>
	</ul>
</div>
<!-- foot bar -->
<div class="pos_menu">
	<em id="show_menu"></em>
	<div id="pos_menu">
		<strong><a id="hidd_menu" href="###"></a>SHORTCHUTS</strong>
		<ul>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
		</ul>
	</div>
</div>
<!-- pos_menu -->

<script src="js/menu.js" type="text/javascript"></script>
<div id="helpContent" title="Viewing and using brush files">
	<p>&nbsp;&nbsp;You can view brush files in the Browser just like fonts or other files. This means that you can assign ratings or organize them just like any other file in ACDSee. Because many .abr files are actually groups of images in one file, you need to open them in the Viewer to see the individual images.</p>
	<p>
		<img src="img/address_book.png">This icon indicates an .abr brush file in ACDSee.
	</p>
	<p>
		<strong>To view brush files:</strong>
	</p>
	<p>&nbsp;&nbsp;1. In the Browser, navigate to the folder containing your brush files.</p>
	<p>&nbsp;&nbsp;2. To see just the top image in any .abr file, hover over the thumbnail to activate the pop-up, or click on it to see that image in the Preview pane.</p>
	<p>&nbsp;&nbsp;3. To view the other images in the .abr file, double-click on it to open it in the Viewer.</p>
	<p>&nbsp;&nbsp;The file opens in the Viewer showing the individual images in a pane on the left-hand side.</p>
	<p>&nbsp;&nbsp;4. To see the number of images, and select them by number, click the down-arrow at the top of the sidebar, and then select the number of the image.</p>
	<p>&nbsp;&nbsp;5. To scroll through the images, click the right and left arrows at the top of the sidebar, or on each image.</p>
	<p>
		<strong>To use brush files in Adobe Photoshop:</strong>
	</p>
	<p>&nbsp;&nbsp;With both Adobe Photoshop and ACDSee open, drag the file from the File List (in the Browser) onto the Photoshop window.</p>
	<p>&nbsp;&nbsp;Even though nothing appears to happen, the brush is loaded into the Photoshop brush library. To view the new brushes, open the library and scroll to the bottom of the pane.</p>
	<p class="helpTd">To make it even easier to use brushes in Photo Shop, you can configure it to be your default editor. Then you can use Ctrl +E to open Photoshop and use the brush right away.</p>

	<script type="text/javascript">
	function addContract(){
		asyncbox.html({
			 title:"新建合同",
			 content:document.getElementById("contract"),
			 width : 660,
			 buttons : [{
				　　　　　value : '保存',
				　　　　　result : 'ok'
				　　　},{
				　　　　　value : '取消',
				　　　　　result : false
				　　　}],
			 onload : function(){

			 }
		});
	};
</script>
	<div id="contract" style="display: none">
		<div class="content" style="left: 0; top: 0;">
			<div class="c_head">
				<span class="left_bg"></span> <span class="right_bg"></span>
			</div>
			<!-- c_head -->
			<div class="box">
				<div class="task_list" style="margin: 0;">
					<table width="100%">
						<tr>
							<th colspan="4"><strong>基本信息</strong></th>
						</tr>
						<tr>
							<td class="ar"><span class="red">(*)</span>主题：</td>
							<td colspan="3"><input name="" type="text" class="ipt man" /></td>
						</tr>
						<tr>
							<td class="ar">对应客户：</td>
							<td colspan="3"><strong>Boss</strong></td>
						</tr>
						<tr>
							<td class="ar bggray">对应机会：</td>
							<td colspan="3"><select name="" class="man">
									<option value="1">销售机会</option>
							</select></td>
						</tr>
						<tr>
							<td class="ar bggray">合同/订单号：</td>
							<td><input name="" type="text" class="ipt" /></td>
							<td class="ar bggray">分类：</td>
							<td><select name="">
									<option value="1">产品销售</option>
							</select></td>
						</tr>
						<tr>
							<td class="ar bggray">总金额：</td>
							<td><input name="" type="text" class="ipt" /></td>
							<td class="ar bggray">外币备注：</td>
							<td><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td class="ar bggray">付款方式：</td>
							<td><input name="" type="text" class="ipt" /></td>
							<td class="ar bggray">交付地点：</td>
							<td><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td class="ar bggray"><span class="red">(*)</span>开始时间：</td>
							<td><input name="" id="time1" type="text" class="ipt" /><img width="16" height="22" align="absmiddle" class="pointer" onclick="showTime('time1','yyyy-MM-dd')" src="img/datePicker.gif"></td>
							<td class="ar bggray"><span class="red">(*)</span>结束时间：</td>
							<td><input name="" id="time2" type="text" class="ipt" /><img width="16" height="22" align="absmiddle" class="pointer" onclick="showTime('time2','yyyy-MM-dd')" src="img/datePicker.gif"></td>
						</tr>
						<tr>
							<td class="ar bggray">产品/服务：</td>
							<td colspan="3"><input name="" type="text" class="ipt man" /></td>
						</tr>
						<tr>
							<td class="ar bggray">客户签约人：</td>
							<td colspan="3"><input name="" type="text" class="ipt" /> <select name="">
									<option value="1">boss</option>
							</select></td>
						</tr>
						<tr>
							<td class="ar bggray">我方签约人：</td>
							<td colspan="3"><input name="" type="text" class="ipt" /> <select name="">
									<option value="1">boss</option>
							</select> <select name="">
									<option value="1">boss</option>
							</select></td>
						</tr>
						<tr>
							<td class="ar bggray"><span class="red">(*)</span>所有者：</td>
							<td><input name="" type="text" class="ipt" /> <select name="">
									<option value="1">boss</option>
							</select></td>
							<td class="ar bggray"><span class="red">(*)</span>签约时间：</td>
							<td><input name="" id="time3" type="text" class="ipt" /><img width="16" height="22" align="absmiddle" class="pointer" onclick="showTime('time3','yyyy-MM-dd')" src="img/datePicker.gif"></td>
						</tr>
						<tr>
							<td colspan="4"><strong>执行状态</strong></td>
						</tr>
						<tr>
							<td class="ar bggray_yellow">回款金额：</td>
							<td>￥0.00 <a style="cursor: help;" title="回款金额不允许填写，由该合同视图内的汇款记录自动合计得出。"><img width="16" height="16" style="vertical-align: middle;" src="img/infild.png">提示</a></td>
							<td class="ar bggray">毛利：</td>
							<td>￥0.00</td>
						</tr>
						<tr>
							<td class="ar bggray"><span class="red">(*)</span>状态：</td>
							<td colspan="3"><label><input name="zt" type="radio" value="" />执行中结</label> <label><input name="zt" type="radio" value="" />束意</label> <label><input name="zt" type="radio" value="" />外中止</label></td>
						</tr>
						<tr>
							<td colspan="4"><strong>其他</strong></td>
						</tr>
						<tr>
							<td class="ar bggray">合同正文及附件：</td>
							<td colspan="3"><textarea name="" cols="" rows="" class="man"></textarea></td>
						</tr>
						<tr>
							<td class="ar bggray">备注：</td>
							<td colspan="3"><textarea name="" cols="" rows="" class="man"></textarea></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="c_foot">
				<span class="left_bg"></span> <span class="right_bg"></span>
			</div>
			<!-- c_foot -->
		</div>
	</div>
	<script type="text/javascript">
	function addService(){
		asyncbox.html({
			 title:"客户服务",
			 content:document.getElementById("addService"),
			 width : 660,
			 buttons : [{
				　　　　　value : '保存',
				　　　　　result : 'ok'
				　　　},{
				　　　　　value : '取消',
				　　　　　result : false
				　　　}],
			 onload : function(){

			 }
		});
	};
</script>
	<div id="addService" style="display: none">
		<div class="content">
			<div class="c_head">
				<span class="left_bg"></span> <span class="right_bg"></span>
			</div>
			<!-- c_head -->
			<div class="box">
				<div class="task_list" style="margin: 0;">
					<table width="100%">
						<tr>
							<th colspan="4"><strong>客户服务</strong></th>
						</tr>
						<tr>
							<td class="ar"><span class="red">(*)</span>主题：</td>
							<td colspan="3"><input name="" type="text" class="ipt man" /></td>
						</tr>
						<tr>
							<td class="ar">对应客户：</td>
							<td colspan="3"><strong>Boss</strong></td>
						</tr>
						<tr>
							<td class="ar">服务类型：</td>
							<td colspan="3"><label><input type="radio" value="" name="fwlx">答疑</label> <label><input type="radio" value="" name="fwlx">故障排除</label> <label><input type="radio" value="" name="fwlx">培训</label> <label><input type="radio" value="" name="fwlx">升级</label> <label><input type="radio" value=""
									name="fwlx">其他</label></td>
						</tr>
						<tr>
							<td class="ar">服务方式：</td>
							<td colspan="3"><label><input type="radio" value="" name="fwfs">电话</label> <label><input type="radio" value="" name="fwfs">传真</label> <label><input type="radio" value="" name="fwfs">邮寄</label> <label><input type="radio" value="" name="fwfs">上门</label> <label><input type="radio" value=""
									name="fwfs">其他</label></td>
						</tr>
						<tr>
							<td class="ar bggray">开始日期：</td>
							<td><input name="" id="time4" type="text" class="ipt" /><img width="16" height="22" align="absmiddle" class="pointer" onclick="showTime('time4','yyyy-MM-dd')" src="img/datePicker.gif"></td>
							<td class="ar bggray">开始时间：</td>
							<td><input name="" id="time5" type="text" class="ipt" /><img width="16" height="22" align="absmiddle" class="pointer" onclick="showTime('time5','H:m')" src="img/datePicker.gif"></td>
						</tr>
						<tr>
							<td class="ar bggray">花费时间：</td>
							<td><select name="">
									<option value="">1小时</option>
									<option value="">2小时</option>
									<option value="">3小时</option>
									<option value="">半个工作日</option>
									<option value="">1个工作日</option>
									<option value="">2个工作日</option>
									<option value="">2个工作日以上</option>
							</select></td>
							<td class="ar bggray">客户联系人：</td>
							<td><select name="">
									<option value="">leo</option>
							</select></td>
						</tr>
						<tr>
							<td class="ar bggray">状态：</td>
							<td colspan="3"><label><input type="radio" value="" name="fwzt">无需处理</label> <label><input type="radio" value="" name="fwzt">未处理</label> <label><input type="radio" value="" name="fwzt">处理中</label> <label><input type="radio" value="" name="fwzt">处理完成</label></td>
						</tr>
						<tr>
							<td class="ar bggray">执行人</td>
							<td colspan="3"><input name="" type="text" class="ipt" /> <select name="">
									<option value="">boss</option>
							</select> <select name="">
									<option value="">boss</option>
							</select></td>
						</tr>
						<tr>
							<td class="ar bggray">服务内容：</td>
							<td colspan="3"><textarea name="" cols="" rows="" class="man"></textarea></td>
						</tr>
						<tr>
							<td class="ar bggray">客户反馈：</td>
							<td colspan="3"><textarea name="" cols="" rows="" class="man"></textarea></td>
						</tr>
						<tr>
							<td class="ar bggray">备注：</td>
							<td colspan="3"><textarea name="" cols="" rows="" class="man"></textarea></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="c_foot">
				<span class="left_bg"></span> <span class="right_bg"></span>
			</div>
			<!-- c_foot -->
		</div>
	</div>