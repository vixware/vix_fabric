<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/daily.png" alt="" />供应链</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/crmAction!goCrmHome.action');">客户关系管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box clearfix">
		<div style="width: 39%;" class="column">
			<div class="addbox">
				<div class="addtitle">
					<span class="l"> <em class="an anopen"></em> <b>热点客户</b> <strong> <a href="#">清理</a> <a href="#">分类</a>
					</strong>
					</span> <span class="r"> <a href="#"><img width="16" height="16" class="sx" src="img/sx.gif"></a>
					</span>
				</div>
				<div style="height: 400px; cursor: default;" id="main">
					<div style="position: relative; overflow: hidden; width: 564px; height: 400px;">
						<div style="position: absolute; left: 0px; top: 0px; width: 564px; height: 400px;" width="705" height="500" data-id="bg"></div>
						<canvas style="position: absolute; left: 0px; top: 0px; width: 564px; height: 400px;" width="705" height="500" data-id="0"></canvas>
						<canvas style="position: absolute; left: 0px; top: 0px; width: 564px; height: 400px;" width="705" height="500" data-id="1"></canvas>
						<canvas style="position: absolute; left: 0px; top: 0px; width: 564px; height: 400px;" width="705" height="500" data-id="2"></canvas>
						<canvas style="position: absolute; left: 0px; top: 0px; width: 564px; height: 400px;" width="705" height="500" data-id="3"></canvas>
						<canvas style="position: absolute; left: 0px; top: 0px; width: 564px; height: 400px;" width="705" height="500" data-id="4"></canvas>
						<canvas style="position: absolute; left: 0px; top: 0px; width: 564px; height: 400px;" width="705" height="500" data-id="5"></canvas>
						<canvas style="position: absolute; left: 0px; top: 0px; width: 564px; height: 400px;" width="705" height="500" data-id="6"></canvas>
						<canvas style="position: absolute; left: 0px; top: 0px; width: 564px; height: 400px;" width="705" height="500" data-id="7"></canvas>
						<canvas style="position: absolute; left: 0px; top: 0px; width: 564px; height: 400px;" width="705" height="500" data-id="hover" id="_zrender_hover_"></canvas>
						<div class="echarts-dataview" style="position: absolute; display: block; overflow: hidden; transition: height 0.8s ease 0s, background-color 1s ease 0s; z-index: 1; left: 0px; top: 0px; width: 564px; height: 0px; background-color: rgb(240, 255, 255);"></div>
					</div>
				</div>
			</div>
			<div class="addbox">
				<div class="addtitle">
					<span class="l"> <em class="an anopen"></em> <b>热点客户</b> <strong><a href="#">清理</a> <a href="#">分类</a></strong>
					</span> <span class="r"> <a href="#"><img width="16" height="16" class="sx" src="img/sx.gif"></a>
					</span
				</div>
				<div class="anbox">
					<p>
						未分类：<a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a>
					</p>
					<p>
						老客户：<a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a>
					</p>
					<p>
						新客户：<a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a>
					</p>
					<p>
						新合作：<a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a> <a href="#">·成都材料</a>
					</p>
				</div>
			</div>
			<div class="addbox">
				<div class="addtitle">
					<span class="l"> <em class="an anopen"></em> <b>应收款客户汇总</b>
					</span> <span class="r"> <a href="#"><img width="16" height="16" class="sx" src="img/sx.gif"></a>
					</span>
				</div>
				<div class="anbox">
					<p>
						共：<a href="#">3</a>家客户 应收总计：￥88.00万元
					</p>
				</div>
			</div>
			<div class="addbox">
				<div class="addtitle">
					<span class="l"><em class="an anopen"></em><b>应付款客户汇总</b></span> <span class="r"> <a onclick="javascript:saveOrUpdate();" href="javascript:;"><img width="16" height="16" class="sx" src="img/sx.gif"></a>
					</span>
				</div>
				<div class="anbox">
					<p>
						共：<a href="#">3</a>家供应商 应收总计：￥38.00万元
					</p>
				</div>
			</div>
			<div class="addbox">
				<div class="addtitle">
					<span class="l"><em class="an anopen"></em><b>回款金额阅读回比</b></span> <span class="r"><a href="#"><img width="16" height="16" class="sx" src="img/sx.gif"></a></span>
				</div>
				<div class="anbox">
					<div class="maximg">
						<img width="100%" src="img/zxt.jpg">
					</div>
				</div>
			</div>
		</div>
		<div style="width: 30%;" class="column">
			<div class="addbox">
				<div class="addtitle">
					<span class="l"><em class="an anopen"></em><b>公司回款任务和完成 <strong><a href="#">年度&amp;图形</a></strong></b></span> <span class="r"><a href="#"><img width="16" height="16" class="sx" src="img/sx.gif"></a></span>
				</div>
				<div class="anbox">
					<div class="maximg">
						<div style="min-width: 200px; height: 400px; margin: 0 auto" id="container" data-highcharts-chart="0">
							<div class="highcharts-container" id="highcharts-0"
								style="position: relative; overflow: hidden; width: 414px; height: 400px; text-align: left; line-height: normal; z-index: 0; font-family: &amp; quot; Lucida Grande&amp;quot; ,&amp; quot; Lucida Sans Unicode&amp;quot; , Verdana ,Arial,Helvetica,sans-serif; font-size: 12px; left: 0.216675px; top: 0.199997px;">
								<svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="414" height="400">
									<desc>Created with Highcharts 3.0.5</desc>
									<defs>
										<clipPath id="highcharts-1">
											<rect fill="none" x="0" y="0" width="332" height="327" />
										</clipPath>
									</defs>
									<rect rx="5" ry="5" fill="#FFFFFF" x="0" y="0" width="414" height="400" />
									<g class="highcharts-button" style="cursor:default;" title="Chart context menu" stroke-linecap="round" transform="translate(380,10)">
										<title>Chart context menu</title>
										<rect rx="2" ry="2" fill="white" x="0.5" y="0.5" width="24" height="22" stroke="none" stroke-width="1" />
										<path fill="#E0E0E0" d="M 6 6.5 L 20 6.5 M 6 11.5 L 20 11.5 M 6 16.5 L 20 16.5" stroke="#666" stroke-width="3" zIndex="1" />
										<text x="0" y="13" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:black;fill:black;" zIndex="1" />
									</g>
									<g class="highcharts-grid" zIndex="1" />
									<g class="highcharts-grid" zIndex="1">
										<path fill="none" d="M 72 302.5 L 404 302.5" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1" />
										<path fill="none" d="M 72 236.5 L 404 236.5" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1" />
										<path fill="none" d="M 72 171.5 L 404 171.5" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1" />
										<path fill="none" d="M 72 105.5 L 404 105.5" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1" />
										<path fill="none" d="M 72 39.5 L 404 39.5" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1" />
										<path fill="none" d="M 72 367.5 L 404 367.5" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1" />
									</g>
									<g class="highcharts-axis" zIndex="2">
										<path fill="none" d="M 204.5 367 L 204.5 372" stroke="#C0D0E0" stroke-width="1" opacity="1" />
										<path fill="none" d="M 270.5 367 L 270.5 372" stroke="69025143
										#C0D0E0" stroke-width="1" opacity="1" />
										<path fill="none" d="M 337.5 367 L 337.5 372" stroke="#C0D0E0" stroke-width="1" opacity="1" />
										<path fill="none" d="M 404.5 367 L 404.5 372" stroke="#C0D0E0" stroke-width="1" opacity="1" />
										<path fill="none" d="M 137.5 367 L 137.5 372" stroke="#C0D0E0" stroke-width="1" opacity="1" />
										<path fill="none" d="M 71.5 367 L 71.5 372" stroke="#C0D0E0" stroke-width="1" />
										<path fill="none" d="M 72 367.5 L 404 367.5" stroke="#C0D0E0" stroke-width="1" zIndex="7" visibility="visible" />
									</g>
									<g class="highcharts-axis" zIndex="2">
										<text x="29.66666603088379" y="203.5" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#4d759e;font-weight:bold;fill:#4d759e;" zIndex="7" text-anchor="middle" transform="translate(0,0) rotate(270 29.66666603088379 203.5)" visibility="visible">
											<tspan x="29.66666603088379">Total fruit consumption</tspan>
										</text>
									</g>
									<g class="highcharts-series-group" zIndex="3">
										<g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(72,40) scale(1 1)" style="" clip-path="url(#highcharts-1)">
									<rect fill="#2f7ed8" x="16.5" y="65.5" width="32" height="131" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#2f7ed8" x="83.5" y="92.5" width="32" height="78" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#2f7ed8" x="149.5" y="39.5" width="32" height="105" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#2f7ed8" x="215.5" y="39.5" width="32" height="183" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#2f7ed8" x="282.5" y="118.5" width="32" height="52" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
										</g>
										<g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(72,40) scale(1 1)" />
										<g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(72,40) scale(1 1)" style="" clip-path="url(#highcharts-1)">
											<rect fill="#0d233a" x="16.5" y="196.5" width="32" height="53" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#0d233a" x="83.5" y="170.5" width="32" height="52" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#0d233a" x="149.5" y="144.5" width="32" height="78" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#0d233a" x="215.5" y="222.5" width="32" height="53" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#0d233a" x="282.5" y="170.5" width="32" height="26" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
										</g>
										<g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(72,40) scale(1 1)" />
										<g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(72,40) scale(1 1)" style="" clip-path="url(#highcharts-1)">
											<rect fill="#8bbc21" x="16.5" y="249.5" width="32" height="78" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#8bbc21" x="83.5" y="222.5" width="32" height="105" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#8bbc21" x="149.5" y="222.5" width="32" height="105" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#8bbc21" x="215.5" y="275.5" width="32" height="52" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
											<rect fill="#8bbc21" x="282.5" y="196.5" width="32" height="131" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0" />
										</g>
										<g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(72,40) scale(1 1)" /></g>
										<text x="207" y="25" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:16px;color:#274b6d;fill:#274b6d;width:350px;" text-anchor="middle" class="highcharts-title" zIndex="4">
											<tspan x="207">Stacked column chart</tspan>
										</text>
										<g class="highcharts-stack-labels" visibility="visible" zIndex="6" transform="translate(72,40)">
											<text x="33" y="59" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:gray;cursor:default;line-height:14px;font-weight:bold;fill:gray;" text-anchor="middle" visibility="inherit" transform="translate(0,0)">
												<tspan x="33">10</tspan>
											</text>
											<text x="100" y="86" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:gray;cursor:default;line-height:14px;font-weight:bold;fill:gray;" text-anchor="middle" visibility="inherit" transform="translate(0,0)">
												<tspan x="100">9</tspan>
											</text>
											<text x="166" y="33" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:gray;cursor:default;line-height:14px;font-weight:bold;fill:gray;" text-anchor="middle" visibility="inherit" transform="translate(0,0)">
												<tspan x="166">11</tspan>
											</text>
											<text x="232" y="33" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:gray;cursor:default;line-height:14px;font-weight:bold;fill:gray;" text-anchor="middle" visibility="inherit" transform="translate(0,0)">
												<tspan x="232">11</tspan>
											</text>
											<text x="299" y="112" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:gray;cursor:default;line-height:14px;font-weight:bold;fill:gray;" text-anchor="middle" visibility="inherit" transform="translate(0,0)">
												<tspan x="299">8</tspan>
											</text>
										</g>
										<g class="highcharts-data-labels highcharts-tracker" visibility="visible" zIndex="6" transform="translate(72,40) scale(1 1)" style="">
											<g zIndex="1" style="cursor:default;" transform="translate(26,119)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">5</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(93,119)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">3</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(159,80)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">4</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(225,119)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">7</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(292,132)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">2</tspan>
												</text>
											</g>
										</g>
										<g class="highcharts-data-labels highcharts-tracker" visibility="visible" zIndex="6" transform="translate(72,40) scale(1 1)" style="">
											<g zIndex="1" style="cursor:default;" transform="translate(26,211)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">2</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(93,184)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">2</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(159,171)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">3</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(225,237)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">2</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(292,171)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">1</tspan>
												</text>
											</g>
										</g>
										<g class="highcharts-data-labels highcharts-tracker" visibility="visible" zIndex="6" transform="translate(72,40) scale(1 1)" style="">
											<g zIndex="1" style="cursor:default;" transform="translate(26,276)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">3</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(93,263)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">4</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(159,263)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">4</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(225,289)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">2</tspan>
												</text>
											</g>
											<g zIndex="1" style="cursor:default;" transform="translate(292,250)" visibility="inherit">
												<text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:white;line-height:14px;fill:white;" zIndex="1">
													<tspan x="3">5</tspan>
												</text>
											</g>
										</g>
										<g class="highcharts-legend" zIndex="7" transform="translate(171,30)">
											<rect rx="5" ry="5" fill="white" x="0.5" y="0.5" width="162" height="29" stroke="#CCC" stroke-width="1" visibility="visible" />
											<g zIndex="1">
												<g>
													<g class="highcharts-legend-item" zIndex="1" transform="translate(8,3)">
														<text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2">
															<tspan x="21">John</tspan>
														</text>
														<rect rx="2" ry="2" fill="#2f7ed8" x="0" y="4" width="16" height="12" zIndex="3" />
													</g>
													<g class="highcharts-legend-item" zIndex="1" transform="translate(63,3)">
														<text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2">
														<tspan x="21">Jane</tspan></text>
									<rect rx="2" ry="2" fill="#0d233a" x="0" y="4" width="16" height="12" zIndex="3" />
													</g>
													<g class="highcharts-legend-item" zIndex="1" transform="translate(116.5,3)">
														<text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2">
															<tspan x="21">Joe</tspan>
														</text>
														<rect rx="2" ry="2" fill="#8bbc21" x="0" y="4" width="16" height="12" zIndex="3" />
													</g>
												</g>
											</g>
										</g>
										<g class="highcharts-axis-labels" zIndex="7">
											<text x="105.2" y="381" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:49px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1">
													<tspan x="105.2">Apples</tspan>
											</text>
											<text x="171.60000000000002" y="381" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:49px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1">
												<tspan x="171.60000000000002">Oranges</tspan>
											</text>
											<text x="238" y="381" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:49px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1">
												<tspan x="238">Pears</tspan>
											</text>
											<text x="304.40000000000003" y="381" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:49px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1">
												<tspan x="304.40000000000003">Grapes</tspan>
											</text>
											<text x="370.8" y="381" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:49px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1">
												<tspan x="370.8">Bananas</tspan>
											</text>
										</g>
										<g class="highcharts-axis-labels" zIndex="7">
											<text x="64" y="369.80000019073486" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:117px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1">
												<tspan x="64">0</tspan>
											</text>
											<text x="64" y="108.20000019073484" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:117px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1">
												<tspan x="64">10</tspan>
											</text>
											<text x="64" y="304.4000001907349" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:117px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1">
												<tspan x="64">2.5</tspan>
											</text>
											<text x="64" y="239.00000019073485" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:117px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1">
												<tspan x="64">5</tspan>
											</text>
											<text x="64" y="173.60000019073487" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:117px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1">
												<tspan x="64">7.5</tspan>
											</text>
											<text x="64" y="42.80000019073486" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:117px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1">
												<tspan x="64">12.5</tspan>
											</text>
										</g>
										<g class="highcharts-tooltip" zIndex="8" style="cursor:default;padding:0;white-space:nowrap;" visibility="hidden" transform="translate(94,210)" opacity="0">
											<rect rx="3" ry="3" fill="none" x="0.5" y="0.5" width="66" height="67.20000457763672" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)" />
											<rect rx="3" ry="3" fill="none" x="0.5" y="0.5" width="66" height="67.20000457763672" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)" />
											<rect rx="3" ry="3" fill="none" x="0.5" y="0.5" width="66" height="67.20000457763672" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)" />
											<rect rx="3" ry="3" fill="rgb(255,255,255)" x="0.5" y="0.5" width="66" height="67.20000457763672" fill-opacity="0.85" stroke="#8bbc21" stroke-width="1" anchorX="78.5" anchorY="52" />
											<text x="8" y="21" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#333333;fill:#333333;" zIndex="1">
												<tspan style="font-weight:bold" x="8">Oranges</tspan>
												<tspan x="8" dy="16">Joe: 4</tspan>
												<tspan x="8" dy="16">Total: 9</tspan>
											</text>
										</g>
										<text x="404" y="395" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:9px;cursor:pointer;color:#909090;fill:#909090;" text-anchor="end" zIndex="8">
											<tspan x="404">Highcharts.com</tspan>
										</text>
									</g>
								</svg>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="addbox">
				<div class="addtitle">
					<span class="l"> <em class="an anopen"></em><b>销售业绩</b>
					</span> <span class="r"> <a onclick="javascript:saveOrUpdate();" href="javascript:;"> <img width="16" height="16" class="sx" src="img/sx.gif">
					</a>
					</span>
				</div>
				<div class="list anbox">
					<table width="100%">
						<tbody>
							<tr class="alt">
								<th colspan="2">公司数据-Boss</th>
							</tr>
							<tr class="">
								<td width="50%" class="ar">目标回款额：</td>
								<td width="50%" class="tc">￥0.00</td>
							</tr>
							<tr class="alt">
								<td width="50%" class="ar">实际回款额：</td>
								<td width="50%" class="tc">￥43,5700</td>
							</tr>
							<tr>
								<td width="50%" class="ar">计划回款额：</td>
								<td width="50%" class="tc">￥43,5700</td>
							</tr>
							<tr class="alt">
								<td width="50%" class="ar">回款额完成：</td>
								<td width="50%" class="tc">0%</td>
							</tr>
							<tr>
								<td width="50%" class="ar">目标合约额：</td>
								<td width="50%" class="tc">￥0.00</td>
							</tr>
							<tr class="alt">
								<td width="50%" class="ar">新增合约额：</td>
								<td width="50%" class="tc">￥43,5700</td>
							</tr>
							<tr>
								<td width="50%" class="ar">合约额完成：</td>
								<td width="50%" class="tc">0%</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div style="width: 30%;" class="column">
			<div class="addbox">
				<div class="addtitle">
					<span class="l"> <em class="an anopen"></em><b>日周月报</b>
					</span> <span class="r"> <a href="#"><img width="16" height="16" class="sx" src="img/sx.gif"></a>
					</span>
				</div>
				<div class="list anbox">
					<table width="100%">
						<tbody>
							<tr class="alt">
								<th></th>
								<th style="text-align: center;">日</th>
								<th style="text-align: center;">周</th>
								<th style="text-align: center;">月</th>
							</tr>
							<tr class="">
								<td width="25%" class="ar">写：</td>
								<td width="25%" class="tc"><a href="#"><img width="16" height="16" src="img/list_icon_01.png"></a></td>
								<td width="25%" class="tc"><a href="#"><img width="16" height="16" src="img/list_icon_01.png"></a></td>
								<td width="25%" class="tc"><a href="#"><img width="16" height="16" src="img/list_icon_01.png"></a></td>
							</tr>
							<tr class="alt">
								<td width="25%" class="ar">看下级：</td>
								<td width="25%" class="tc"><a href="#">0</a></td>
								<td width="25%" class="tc"><a href="#">0</a></td>
								<td width="25%" class="tc"><a href="#">0</a></td>
							</tr>
							<tr>
								<td width="25%" class="ar">提交情况：</td>
								<td width="25%" class="tc"><a href="#"><img width="16" height="16" src="img/testImg.gif"></a></td>
								<td width="25%" class="tc"><a href="#"><img width="16" height="16" src="img/testImg.gif"></a></td>
								<td width="25%" class="tc"><a href="#"><img width="16" height="16" src="img/testImg.gif"></a></td>
							</tr>
							<tr class="alt">
								<td width="25%" class="ar">汇总：</td>
								<td width="25%" class="tc"><a href="#"><img width="16" height="16" src="img/testImg.gif"></a></td>
								<td width="25%" class="tc"><a href="#"><img width="16" height="16" src="img/testImg.gif"></a></td>
								<td width="25%" class="tc"><a href="#"><img width="16" height="16" src="img/testImg.gif"></a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="addbox">
				<div class="addtitle">
					<span class="l"> <em class="an anopen"></em><b>日程</b> <strong><a href="#">新建</a></strong>
					</span> <span class="r"> <a href="#"><img width="16" height="16" class="sx" src="img/sx.gif"></a>
					</span>
				</div>
				<div class="anbox">
					<p>
						- 参加上半年，年中销售工作总结会议 <span class="gray">后天 9:30</span>
					</p>
				</div>
			</div>
			<div class="addbox">
				<div class="addtitle">
					<span class="l"> <em class="an anopen"></em> <b>代办任务</b> <strong><a href="#">新建</a> <a href="#">新建批量客户任务</a> </strong>
					</span> <span class="r"> <a href="#"><img width="16" height="16" class="sx" src="img/sx.gif"></a>
					</span>
				</div>
				<div class="anbox">
					<p>
						<b>批量客户任务</b>（创建人）
					</p>
					<p style="text-indent: 2em;">
						- <a href="#">软件升级通知</a> <span class="blue">未结束[6]</span> 2010-07-15
					</p>
					<p>
						<b>7日内</b>
					</p>
					<p style="text-indent: 2em;">
						- <a href="#">下午发两台投影仪</a> <span class="blue">今天</span> 【上海菲奥德<img width="16" height="16" style="vertical-align: middle;" src="img/file_box.gif">】
					</p>
					<p style="text-indent: 2em;">
						- <a href="#">下午发两台投影仪</a> <span class="blue">今天</span> 【上海菲奥德<img width="16" height="16" style="vertical-align: middle;" src="img/file_box.gif">】
					</p>
					<p>
						<b>7日后</b>
					</p>
					<p style="text-indent: 2em;">
						- <a href="#">下午发两台投影仪</a> <span class="blue">今天</span> 【上海菲奥德<img width="16" height="16" style="vertical-align: middle;" src="img/file_box.gif">】
					</p>
					<p style="text-indent: 2em;">
						- <a href="#">下午发两台投影仪</a> <span class="blue">今天</span> 【上海菲奥德<img width="16" height="16" style="vertical-align: middle;" src="img/file_box.gif">】
					</p>
				</div>
			</div>
			<div class="addbox">
				<div class="addtitle">
					<span class="l"> <em class="an anopen"></em><b>审批提醒</b>
					</span> <span class="r"> <a href="#"><img width="16" height="16" class="sx" src="img/sx.gif"></a>
					</span>
				</div>
				<div class="list anbox">
					<table width="100%">
						<tbody>
							<tr>
								<td><img width="24" height="24" style="vertical-align: middle" src="img/n2.png">：</td>
								<td><a title="今天" href="#"><img width="14" height="14" style="vertical-align: middle" src="img/icon_10.png"> 1</a></td>
								<td><a title="待审批" href="#"><img width="19" height="18" style="vertical-align: middle" src="img/icon_11.png"> 1</a></td>
								<td></td>
								<td></td>
								<td></td>
								<td><a href="#">全部</a></td>
							</tr>
							<tr class="alt">
								<td><img width="24" height="24" style="vertical-align: middle" src="img/n2.png">：</td>
								<td><a title="今天" href="#"><img width="14" height="14" style="vertical-align: middle" src="img/icon_10.png"> 9</a></td>
								<td><a href="#"><img width="19" height="18" style="vertical-align: middle" src="img/icon_21.gif"> 9</a></td>
								<td><a href="#"><img width="18" height="18" style="vertical-align: middle" src="img/icon_12.png"> 0</a></td>
								<td><a href="#"><img width="13" height="12" style="vertical-align: middle" src="img/icon_14.gif"> 0</a></td>
								<td><a title="待审批" href="#"><img width="19" height="18" style="vertical-align: middle" src="img/icon_11.png"> 0</a></td>
								<td><a href="#">全部</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>