<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="css/service.css" type="text/css" rel="stylesheet">
<script src="js/jquery.tree.js" type="text/javascript"></script>
<script src="js/date.js" type="text/javascript"></script>
<script src="js/page_08.js" type="text/javascript"></script>
<script src="js/asyncbox.v1.5.beta.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function () {
        $('#container').highcharts({
    
            chart: {
                type: 'column'
            },
    
            title: {
                text: 'Total fruit consumtion, grouped by gender'
            },
    
            xAxis: {
                categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
            },
    
            yAxis: {
                allowDecimals: false,
                min: 0,
                title: {
                    text: 'Number of fruits'
                }
            },
    
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.x +'</b><br/>'+
                        this.series.name +': '+ this.y +'<br/>'+
                        'Total: '+ this.point.stackTotal;
                }
            },
    
            plotOptions: {
                column: {
                    stacking: 'normal'
                }
            },
    
            series: [{
                name: 'John',
                data: [5, 3, 4, 7, 2],
                stack: 'male'
            }, {
                name: 'Joe',
                data: [3, 4, 4, 2, 5],
                stack: 'male'
            }, {
                name: 'Jane',
                data: [2, 5, 6, 2, 1],
                stack: 'female'
            }, {
                name: 'Janet',
                data: [3, 0, 4, 4, 3],
                stack: 'female'
            }]
        });
    });
		</script>
<script src="js/highcharts.js"></script>
<script src="js/modules/exporting.js"></script>
<!-- head -->
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/pm_project.png" alt="" />
					<s:text name="supplyChain" /></a></li>
				<li><a href="#"><s:text name="项目管理" /></a></li>
				<li><a href="#"><s:text name="项目一览" /></a></li>
				<li><a href="#"><s:text name="资金一览" /></a></li>
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
	<div class="production">
		<div class="production_menu drop">
			<!-- <ul>
				<li class="line_down">
					<a href="#">操作</a>
					<ul class="lfj">
						<li><a href="#">Delete</a></li>
						<li><a href="#">Email</a></li>
						<li><a href="#">Mass Update</a></li>
						<li><a href="#">Merge</a></li>
						<li><a href="#">Add to Target list</a></li>
						<li><a href="#">Export</a></li>
					</ul>
				</li>
			</ul> -->
			<ul class="bright">
				<!-- <li class="line"><a href="#"><img src="img/icon_demo.gif" alt="" /></a></li>
				<li><a href="#">|&lt;</a></li>
				<li><a href="#">&lt;&lt;</a></li>
				<li><input name="" type="text" size="3" />/1</li>
				<li><a href="#">&gt;&gt;</a></li>
				<li class="line"><a href="#">&gt;|</a></li> -->
				<li class="line">
					<!-- <input name="" type="text" size="12" /> -->
					<a href="#">项目进度</a>
				</li>
			</ul>
			<ul>
				<li class="percentage"><a href="#">100%</a>
					<ul>
						<li><a href="#">80%</a></li>
						<li><a href="#">60%</a></li>
						<li><a href="#">40%</a></li>
						<li><a href="#">20%</a></li>
						<li><a href="#">00%</a></li>
					</ul></li>
			</ul>
		</div>
		<div class="production_pic">
			<h1>软件中心生产任务与资源动态跟踪图</h1>
			<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		</div>
		<hr />
		<div class="production_table">
			<h2>重点任务进展情况</h2>
			<table>
				<tr>
					<th>单位：人天</th>
					<th>09/4</th>
					<th>09/5</th>
					<th>09/6</th>
					<th>09/7</th>
					<th>09/8</th>
					<th>09/9</th>
					<th>09/10</th>
				</tr>
				<tr>
					<td class="gray">产能</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="gray">计划工作量</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="gray">实际工作量（生产任务）</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="gray">实际工作量（全部）</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<span><a href="#">查看更多</a></span>
		</div>
		<hr />
		<div class="production_table">
			<h2>重点任务进展情况</h2>
			<table>
				<tr>
					<th>单位：人天</th>
					<th>09/4</th>
					<th>09/5</th>
					<th>09/6</th>
					<th>09/7</th>
					<th>09/8</th>
					<th>09/9</th>
					<th>09/10</th>
				</tr>
				<tr>
					<td class="gray">产能</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="gray">计划工作量</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="gray">实际工作量（生产任务）</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="gray">实际工作量（全部）</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<span><a href="#">查看更多</a></span>
		</div>
		<hr />
		<div class="production_table">
			<h2>重点任务进展情况</h2>
			<table>
				<tr>
					<th>单位：人天</th>
					<th>09/4</th>
					<th>09/5</th>
					<th>09/6</th>
					<th>09/7</th>
					<th>09/8</th>
					<th>09/9</th>
					<th>09/10</th>
				</tr>
				<tr>
					<td class="gray">产能</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="gray">计划工作量</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="gray">实际工作量（生产任务）</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="gray">实际工作量（全部）</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<span><a href="#">查看更多</a></span>
		</div>
		<hr />
		<div class="production_table">
			<h2>重点任务进展情况</h2>
			<table>
				<tr>
					<th colspan="2" rowspan="2">2004年</th>
					<th rowspan="3">凭证号数</th>
					<th rowspan="3">凭证</th>
					<th colspan="3">营业收入</th>
					<th colspan="3">营业费用</th>
					<th rowspan="3">昆明</th>
					<th rowspan="3">广东</th>
				</tr>
				<tr>
					<th colspan="3">贷方</th>
					<th colspan="3">借方</th>
				</tr>
				<tr>
					<th>月</th>
					<th>日</th>
					<th>合计</th>
					<th>团队数_北京</th>
					<th>团队数_北京</th>
					<th>合计</th>
					<th>团队数_北京</th>
					<th>团队数_北京</th>
				</tr>
				<tr>
					<td>06</td>
					<td>08</td>
					<td>记-0001</td>
					<td>当前合计</td>
					<td>4,000.00</td>
					<td>4,000.00</td>
					<td>&nbsp;</td>
					<td>500.00</td>
					<td>500.00</td>
					<td>&nbsp;</td>
					<td>0.0</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>06</td>
					<td>08</td>
					<td>记-0001</td>
					<td>当前合计</td>
					<td>4,000.00</td>
					<td>4,000.00</td>
					<td>&nbsp;</td>
					<td>500.00</td>
					<td>500.00</td>
					<td>&nbsp;</td>
					<td>0.0</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>06</td>
					<td>08</td>
					<td>记-0001</td>
					<td>当前合计</td>
					<td>4,000.00</td>
					<td>4,000.00</td>
					<td>&nbsp;</td>
					<td>500.00</td>
					<td>500.00</td>
					<td>&nbsp;</td>
					<td>0.0</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>06</td>
					<td>08</td>
					<td>记-0001</td>
					<td>当前合计</td>
					<td>4,000.00</td>
					<td>4,000.00</td>
					<td>&nbsp;</td>
					<td>500.00</td>
					<td>500.00</td>
					<td>&nbsp;</td>
					<td>0.0</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>06</td>
					<td>08</td>
					<td>记-0001</td>
					<td>当前合计</td>
					<td>4,000.00</td>
					<td>4,000.00</td>
					<td>&nbsp;</td>
					<td>500.00</td>
					<td>500.00</td>
					<td>&nbsp;</td>
					<td>0.0</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>06</td>
					<td>08</td>
					<td>记-0001</td>
					<td>当前合计</td>
					<td>4,000.00</td>
					<td>4,000.00</td>
					<td>&nbsp;</td>
					<td>500.00</td>
					<td>500.00</td>
					<td>&nbsp;</td>
					<td>0.0</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>06</td>
					<td>08</td>
					<td>记-0001</td>
					<td>当前合计</td>
					<td>4,000.00</td>
					<td>4,000.00</td>
					<td>&nbsp;</td>
					<td>500.00</td>
					<td>500.00</td>
					<td>&nbsp;</td>
					<td>0.0</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>06</td>
					<td>08</td>
					<td>记-0001</td>
					<td>当前合计</td>
					<td>4,000.00</td>
					<td>4,000.00</td>
					<td>&nbsp;</td>
					<td>500.00</td>
					<td>500.00</td>
					<td>&nbsp;</td>
					<td>0.0</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>06</td>
					<td>08</td>
					<td>记-0001</td>
					<td>当前合计</td>
					<td>4,000.00</td>
					<td>4,000.00</td>
					<td>&nbsp;</td>
					<td>500.00</td>
					<td>500.00</td>
					<td>&nbsp;</td>
					<td>0.0</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>06</td>
					<td>08</td>
					<td>记-0001</td>
					<td>当前合计</td>
					<td>4,000.00</td>
					<td>4,000.00</td>
					<td>&nbsp;</td>
					<td>500.00</td>
					<td>500.00</td>
					<td>&nbsp;</td>
					<td>0.0</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<span><a href="#">查看更多</a></span>
		</div>
		<hr />
		<div class="production_table">
			<h2>重点任务进展情况</h2>
			<table>
				<tr>
					<th colspan="10"><p class="production_page">
							<a href="#">查询</a> <a href="#">查询凭证</a> <a href="#">打印</a> <a href="#">储存格式</a> <a href="#">首页</a> <a href="#">上一页</a> <a href="#">下一页</a> <a href="#">末页</a> <a href="#">转换</a>
						</p></th>
				</tr>
				<tr>
					<td colspan="10" class="gray">
						<table>
							<tr class="alt">
								<td width="10%" style="text-align: right;">期间</td>
								<td width="40%"><input type="text" name="" class="ipt"></td>
								<td width="10%" style="text-align: right;">币种</td>
								<td><input type="text" name="" class="ipt"></td>
							</tr>
							<tr class="alt">
								<td width="10%" style="text-align: right;">辅助项</td>
								<td width="40%"><input type="text" name="" class="ipt"></td>
								<td width="10%" style="text-align: right;">账簿格式</td>
								<td><select name="">
										<option value="1">金额式</option>
								</select></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th colspan="2">2011年</th>
					<th rowspan="2">项目辅助-项目辅助计算名称</th>
					<th rowspan="2">自营非自</th>
					<th rowspan="2">自营非自营攻城项目</th>
					<th rowspan="2">凭证号</th>
					<th rowspan="2">摘要</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				<tr>
					<th>月</th>
					<th>日</th>
					<th>合计</th>
					<th>5401攻城施工</th>
					<th>540101合同成本</th>
				</tr>
				<tr>
					<td>03</td>
					<td>31</td>
					<td>YXB150-海口水泥厂二期</td>
					<td>0901</td>
					<td>自营攻城项目</td>
					<td>001</td>
					<td>收金江小区工程款结算费</td>
					<td>3000.00</td>
					<td>30000.00</td>
					<td>30000.00</td>
				</tr>
				<tr>
					<td>03</td>
					<td>31</td>
					<td>YXB150-海口水泥厂二期</td>
					<td>0901</td>
					<td>自营攻城项目</td>
					<td>001</td>
					<td>收金江小区工程款结算费</td>
					<td>3000.00</td>
					<td>30000.00</td>
					<td>30000.00</td>
				</tr>
				<tr>
					<td>03</td>
					<td>31</td>
					<td>YXB150-海口水泥厂二期</td>
					<td>0901</td>
					<td>自营攻城项目</td>
					<td>001</td>
					<td>收金江小区工程款结算费</td>
					<td>3000.00</td>
					<td>30000.00</td>
					<td>30000.00</td>
				</tr>
				<tr>
					<td>03</td>
					<td>31</td>
					<td>YXB150-海口水泥厂二期</td>
					<td>0901</td>
					<td>自营攻城项目</td>
					<td>001</td>
					<td>收金江小区工程款结算费</td>
					<td>3000.00</td>
					<td>30000.00</td>
					<td>30000.00</td>
				</tr>
				<tr>
					<td>03</td>
					<td>31</td>
					<td>YXB150-海口水泥厂二期</td>
					<td>0901</td>
					<td>自营攻城项目</td>
					<td>001</td>
					<td>收金江小区工程款结算费</td>
					<td>3000.00</td>
					<td>30000.00</td>
					<td>30000.00</td>
				</tr>
				<tr>
					<td>03</td>
					<td>31</td>
					<td>YXB150-海口水泥厂二期</td>
					<td>0901</td>
					<td>自营攻城项目</td>
					<td>001</td>
					<td>收金江小区工程款结算费</td>
					<td>3000.00</td>
					<td>30000.00</td>
					<td>30000.00</td>
				</tr>
				<tr>
					<td>03</td>
					<td>31</td>
					<td>YXB150-海口水泥厂二期</td>
					<td>0901</td>
					<td>自营攻城项目</td>
					<td>001</td>
					<td>收金江小区工程款结算费</td>
					<td>3000.00</td>
					<td>30000.00</td>
					<td>30000.00</td>
				</tr>
				<tr>
					<td>03</td>
					<td>31</td>
					<td>YXB150-海口水泥厂二期</td>
					<td>0901</td>
					<td>自营攻城项目</td>
					<td>001</td>
					<td>收金江小区工程款结算费</td>
					<td>3000.00</td>
					<td>30000.00</td>
					<td>30000.00</td>
				</tr>
				<tr>
					<td>03</td>
					<td>31</td>
					<td>YXB150-海口水泥厂二期</td>
					<td>0901</td>
					<td>自营攻城项目</td>
					<td>001</td>
					<td>收金江小区工程款结算费</td>
					<td>3000.00</td>
					<td>30000.00</td>
					<td>30000.00</td>
				</tr>
				<tr>
					<td>03</td>
					<td>31</td>
					<td>YXB150-海口水泥厂二期</td>
					<td>0901</td>
					<td>自营攻城项目</td>
					<td>001</td>
					<td>收金江小区工程款结算费</td>
					<td>3000.00</td>
					<td>30000.00</td>
					<td>30000.00</td>
				</tr>
			</table>
			<span><a href="#">查看更多</a></span>
		</div>
		<div class="production_news">
			<h2>项目快讯</h2>
			<ul class="drop_news">
				<li><a href="#">新建</a>
					<ul>
						<li><a href="#">Delete</a></li>
						<li><a href="#">Email</a></li>
						<li><a href="#">Mass Update</a></li>
						<li><a href="#">Merge</a></li>
						<li><a href="#">Add to Target list</a></li>
						<li><a href="#">Export</a></li>
					</ul></li>
				<li><a href="#">操作</a>
					<ul>
						<li><a href="#">Delete</a></li>
						<li><a href="#">Email</a></li>
						<li><a href="#">Mass Update</a></li>
						<li><a href="#">Merge</a></li>
						<li><a href="#">Add to Target list</a></li>
						<li><a href="#">Export</a></li>
					</ul></li>
				<li><a href="#">设置</a>
					<ul>
						<li><a href="#">Delete</a></li>
						<li><a href="#">Email</a></li>
						<li><a href="#">Mass Update</a></li>
						<li><a href="#">Merge</a></li>
						<li><a href="#">Add to Target list</a></li>
						<li><a href="#">Export</a></li>
					</ul></li>
			</ul>
			<table>
				<tr>
					<th width="16">&nbsp;</th>
					<th>名称</th>
					<th>近期完成开发时间</th>
					<th>近期完成投产时间</th>
					<th>类别</th>
				</tr>
				<tr class="title">
					<td><a href="#" class="hidd"></a></td>
					<td colspan="4"><b>类别</b>：维护任务(1)</td>
				</tr>
				<tbody>
					<tr>
						<td>&nbsp;</td>
						<td><a href="#">个人外汇期权系统（主机版）推广</a><i>！新</i></td>
						<td>2009/10/09</td>
						<td>2009/10/15</td>
						<td>维护任务</td>
					</tr>
				</tbody>
				<tr class="title">
					<td><a href="#" class="hidd"></a></td>
					<td colspan="4"><b>类别</b>：项目(3)</td>
				</tr>
				<tbody>
					<tr>
						<td>&nbsp;</td>
						<td><a href="#">个人外汇期权系统（主机版）推广</a><i>！新</i></td>
						<td>2009/10/09</td>
						<td>2009/10/15</td>
						<td>维护任务</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><a href="#">个人外汇期权系统（主机版）推广</a><i>！新</i></td>
						<td>2009/10/09</td>
						<td>2009/10/15</td>
						<td>维护任务</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><a href="#">个人外汇期权系统（主机版）推广</a><i>！新</i></td>
						<td>2009/10/09</td>
						<td>2009/10/15</td>
						<td>维护任务</td>
					</tr>
				</tbody>
			</table>
			<h2>软件中心重要事项汇报</h2>
			<table>
				<tr>
					<th>类型</th>
					<th>名称</th>
					<th>修改者</th>
				</tr>
				<tr>
					<td colspan="3">该“软件中心重要事项汇报”文档库视图中没有可显示的项目。若要新建文档，请单击下面的“添加新文档”。</td>
				</tr>
				<tr>
					<td><a href="#"><img src="img/icon_25.gif" alt="" />添加新文档</a></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- production -->
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
			<li><a href="#"><img src="img/icon_demo.gif" alt="" />demo</a></li>
			<li><a href="#"><img src="img/icon_demo.gif" alt="" />demo</a></li>
			<li><a href="#"><img src="img/icon_demo.gif" alt="" />demo</a></li>
			<li><a href="#"><img src="img/icon_demo.gif" alt="" />demo</a></li>
			<li><a href="#"><img src="img/icon_demo.gif" alt="" />demo</a></li>
			<li><a href="#"><img src="img/icon_demo.gif" alt="" />demo</a></li>
			<li><a href="#"><img src="img/icon_demo.gif" alt="" />demo</a></li>
			<li><a href="#"><img src="img/icon_demo.gif" alt="" />demo</a></li>
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
</div>