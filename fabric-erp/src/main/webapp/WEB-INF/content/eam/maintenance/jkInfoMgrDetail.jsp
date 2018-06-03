<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

$(function(){
});

</script>

<div class="content">
	<div class="npcontent clearfix">
		<div class="np_right">
			<div class="np_right_box">
				<div class="nprb_title">
					任务信息 <a title="提示信息" style="cursor: help;"><img src="img/infild.png" width="16" height="16" style="vertical-align: middle;" />提示</a>
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
				<div class="nprb_title">
					设备信息<a href="#">(明细)</a>
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
				<div class="nprb_title">往来对账</div>
				<p align="right">
					<a href="#" title="交付/回款 月度对帐">交付/回款 月度对帐>></a>
				</p>
			</div>
			<div class="np_right_box">
				<div class="nprb_title">
					联系人<a href="#">(明细)</a>
				</div>
				<p align="center">
					<a href="javascript:;" onclick="javascript:showBCard();">leo 阿斯顿(主)</a><br />
					<b>13718996400</b><img border="0" align="Baseline" src="img/sphone.png"><br />
					<b>abcdefghi@abc.com</b>
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
	</div>
</div>

<script type="text/javascript"> 
<!--
//-->
</script>