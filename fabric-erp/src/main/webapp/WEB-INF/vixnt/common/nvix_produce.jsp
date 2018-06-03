<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li>
	<a id="mid_produce" href="javascript:void(0);"><i class="fa fa-lg fa-fw fa-wrench"></i> <span class="menu-item-parent">生产管理</span></a>
	<ul>
		<li>
			<a href="javascript:void(0);">设置</a>
			<ul>
				<li><a id="mid_workCenter" href="javascript:void(0);" onclick="loadContent('mid_workCenter','${nvix}/nvixnt/produce/nvixWorkCenterAction!goList.action');">工作中心</a></li>
				<%-- <li><a id="mid_processManagement" href="javascript:void(0);" onclick="loadContent('mid_processManagement','${nvix}/nvixnt/produce/nvixProcessManagementAction!goList.action');">工序管理</a></li> --%>
				<li><a id="mid_craftsRoute" href="javascript:void(0);" onclick="loadContent('mid_workCenter','${nvix}/nvixnt/produce/nvixCraftsRouteAction!goList.action');">工艺路线</a></li>
			</ul>
		</li>
		<li>
			<a href="javascript:void(0);">BOM管理</a>
			<ul>
				<li><a id="mid_bomStruct" href="javascript:void(0);" onclick="loadContent('mid_bomStruct','${nvix}/nvixnt/produce/nvixBomStructAction!goList.action');">BOM结构</a></li>
				<li><a id="mid_bomNode" href="javascript:void(0);" onclick="loadContent('mid_bomNode','${nvix}/nvixnt/produce/nvixBomNodeAction!goList.action');">BOM节点</a></li>
			</ul>
		</li>
		<li>
			<a href="javascript:void(0);">配方管理</a>
			<ul>
				<li><a id="" href="javascript:void(0);" onclick="">设置</a></li>
				<li><a id="" href="javascript:void(0);" onclick="">配方列表</a></li>
			</ul>
		</li>
		<li>
			<a id="mid_" href="javascript:void(0);" onclick="">生产规划</a>
			<ul>
				<li><a id="mid_" href="javascript:void(0);" onclick="">生产规划编制</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">生产预测计划</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">规划统计</a></li>
			</ul>
		</li>
		<li>
			<a id="mid_" href="javascript:void(0);" onclick="">MPS主生产计划</a>
			<ul>
				<li>
					<a id="mid_" href="javascript:void(0);" onclick="">设置</a>
					<ul>
						<li><a id="mid_" href="javascript:void(0);" onclick="">基本资料维护</a></li>
						<li><a id="mid_" href="javascript:void(0);" onclick="">需求来源资料维护</a></li>
						<li><a id="mid_" href="javascript:void(0);" onclick="">MPS计划参数维护</a></li>
					</ul>
				</li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">MPS计划来源</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">订单</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">预测</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">紧急插单</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">MPS计划编制与生成</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">MPS计划前稽核作业</a></li>
			</ul>
		</li>
		<li>
			<a id="mid_" href="javascript:void(0);" onclick="">能力计划</a>
			<ul>
				<li><a id="mid_" href="javascript:void(0);" onclick="">资源清单维护</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">资源需求计划</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">粗能力需求计划</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">能力需求计划</a></li>
			</ul>
		</li>
		<li>
			<a id="mid_" href="javascript:void(0);" onclick="">MRP计划</a>
			<ul>
				<li><a id="mid_" href="javascript:void(0);" onclick="">需求计划</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">需求来源资料维护</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">MRP计划前稽核作业</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">计划作业</a></li>
			</ul>
		</li>
		<li>
			<a id="mid_" href="javascript:void(0);" onclick="">生产订单</a>
			<ul>
				<li><a id="mid_" href="javascript:void(0);" onclick="">生产订单列表</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">生产订单处理</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">报表</a></li>
			</ul>
		</li>
		<li>
			<a id="mid_" href="javascript:void(0);" onclick="">车间管理</a>
			<ul>
				<li><a id="mid_" href="javascript:void(0);" onclick="">生产任务管理</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">车间作业计划</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">生产订单工序计划</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">交易处理</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">报表</a></li>
			</ul>
		</li>
		<li>
			<a id="mid_" href="javascript:void(0);" onclick="">工程变更</a>
			<ul>
				<li>
					<a id="mid_" href="javascript:void(0);" onclick="">基本资料维护</a>
					<ul>
						<li><a id="mid_" href="javascript:void(0);" onclick="">工程变更原因资料维护</a></li>
						<li><a id="mid_" href="javascript:void(0);" onclick="">工程变更等级资料维护</a></li>
					</ul>
				</li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">工程变更单处理</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">工程清单维护</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">工程工艺路线维护</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">报表</a></li>
			</ul>
		</li>
		<li>
			<a id="mid_" href="javascript:void(0);" onclick="">设备管理</a>
			<ul>
				<li>
					<a id="mid_" href="javascript:void(0);" onclick="">基础设置</a>
					<ul>
						<li><a id="mid_" href="javascript:void(0);" onclick="">供应商分类</a></li>
						<li><a id="mid_" href="javascript:void(0);" onclick="">供应商档案</a></li>
						<li><a id="mid_" href="javascript:void(0);" onclick="">计量单位</a></li>
						<li><a id="mid_" href="javascript:void(0);" onclick="">存货档案</a></li>
					</ul>
				</li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">设备台账</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">作业管理</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">运行管理</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">备件管理</a></li>
				<li><a id="mid_" href="javascript:void(0);" onclick="">报表</a></li>
			</ul>
		</li>
	</ul>
</li>