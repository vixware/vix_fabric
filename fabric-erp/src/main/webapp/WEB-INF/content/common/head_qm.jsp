<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#">质量管理</a>
	<ul>
		<li class="fly"><a href="#">初始设置</a>
			<ul>
				<li class="fly_top" icon="img/qm_quality_management.png"><a onclick="loadContent('${vix}/qm/inspectionTypeAction!goList.action','bg_02');">检验类型</a>
				<li><a onclick="loadContent('${vix}/qm/inspectionCharacteristicsAction!goList.action','bg_02');">检验特性</a>
				<li><a onclick="loadContent('${vix}/qm/testMethodsAction!goList.action','bg_02');">检验方法</a>
				<li><a onclick="loadContent('${vix}/qm/testMethodsAction!goList.action','bg_02');">检验方法</a>
				<li><a onclick="loadContent('${vix}/qm/inspectionStatusAction!goList.action','bg_02');">检验状态</a>
				<li><a onclick="loadContent('${vix}/qm/defectLevelsAction!goList.action','bg_02');">缺陷等级设置</a>
				<li><a onclick="loadContent('${vix}/qm/checkListmanagementAction!goList.action','bg_02');">检验单管理</a>
				<li><a onclick="loadContent('${vix}/qm/checkListtemplateAction!goList.action','bg_02');">检验单模板</a>
				<li class="fly_end"><a href="#">检验流程设置</a></li>
			</ul></li>
		<li class="fly"><a href="#">样品管理</a>
			<ul>
				<li class="fly_top" icon="img/qm_quality_management.png"><a onclick="loadContent('${vix}/qm/sampleRegisterAction!goList.action','bg_02');">样品登记</a>
				<li><a onclick="loadContent('${vix}/qm/afterClassifyingAction!goList.action','bg_02');">样品分类</a>
				<li><a onclick="loadContent('${vix}/qm/sampleRegisterAction!goList.action','bg_02');">样品查询</a>
			</ul></li>
		<li class="fly"><a href="#">来料检验</a>
			<ul>
				<li class="fly_top" icon="img/qm_quality_management.png"><a onclick="loadContent('${vix}/qm/incomingInspectionsingleAction!goList.action','bg_02');">来料报检单</a>
				<li><a onclick="loadContent('${vix}/qm/incomingChecklistAction!goList.action','bg_02');">来料检验单</a>
				<li><a onclick="loadContent('${vix}/qm/defectiveProductsAction!goList.action','bg_02');">来料不良品处理单</a>
			</ul></li>
		<li class="fly"><a href="#">产品检验</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/qm/productInspectionformAction!goList.action','bg_02');">列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/productInspectionformAction!goList.action','bg_02');">产品报检单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/productTestingsingleAction!goList.action','bg_02');">产品检验单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/nonconformingProductAction!goList.action','bg_02');">产品不良品处理单</a></li>
			</ul></li>
		<li class="fly"><a href="#">工序检验</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/qm/processInspectionAction!goList.action','bg_02');">列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/processInspectionAction!goList.action','bg_02');">工序报检单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/stepCheckListAction!goList.action','bg_02');">工序检验单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/processDefectiveProductsAction!goList.action','bg_02');">工序不良品处理单</a></li>
			</ul></li>
		<li class="fly"><a href="#">在库检验</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/qm/inStorehouseinspectionformAction!goList.action','bg_02');">列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/inStorehouseinspectionformAction!goList.action','bg_02');">在库报检单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/inLibrarytestAction!goList.action','bg_02');">在库检验单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/inStorehousedefectiveproductsAction!goList.action','bg_02');">在库不良品处理单</a></li>
			</ul></li>
		<li class="fly"><a href="#">发退货检验</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/qm/hairReturnclearancesingleAction!goList.action','bg_02');">列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/hairReturnclearancesingleAction!goList.action','bg_02');">发退货报检单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/hairReturnchecklistAction!goList.action','bg_02');">发退货检验单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/hairReturncdefectiveproductsAction!goList.action','bg_02');">发退货不良品处理单</a></li>
			</ul></li>
		<li><a href="#">留样单</a></li>
		<li><a href="#" onclick="loadContent('${vix}/qm/qualityDocumentmanagementAction!goList.action','bg_02');">质量文档管理</a></li>
		<li class="fly"><a href="#">单据列表</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/qm/incomingInspectionAction!goList.action','bg_02');">来料检验</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/productTestingAction!goList.action','bg_02');">产品检验</a></li>
				<!-- <li><a href="/vix/content/scm/tqm/ipi.jsp">工序检验</a></li> -->
				<li><a href="#" onclick="loadContent('${vix}/qm/inThelibrarytestAction!goList.action','bg_02');">在库检验</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/hairReturninspectionAction!goList.action','bg_02');">发退货检验</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/otherInspectionAction!goList.action','bg_02');">其他检验</a></li>
				<li><a href="#" onclick="loadContent('${vix}/qm/retentionSamplessingleListAction!goList.action','bg_02');">留样单列表</a></li>
			</ul></li>
		<li class="fly"><a href="#">统计报表</a>
			<ul>
				<li class="fly"><a href="#">质量分析图</a>
					<ul>
						<li class="fly_top"><a href="#">直方图</a></li>
						<li><a href="#">控制图</a></li>
						<li><a href="#">排列图</a></li>
						<li class="fly_end"><a href="#">分布图</a></li>
					</ul></li>

			</ul></li>
	</ul></li>