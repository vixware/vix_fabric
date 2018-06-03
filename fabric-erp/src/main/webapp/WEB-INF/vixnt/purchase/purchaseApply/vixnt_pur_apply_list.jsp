<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i> 采购管理 <span>&gt; 采购申请</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('purchase_purchaseorder','${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="well well-sm">
				<div class="smart-timeline">
					<ul class="smart-timeline-list">
						<s:if test="baseEntityList.size > 0">
							<s:iterator value="baseEntityList" var="entity" status="s">
								<li>
									<div class="smart-timeline-icon">
										<i class="fa fa-file-text"></i>
									</div>
									<div class="smart-timeline-time">
										<small>${entity.createTimeTimeStr }</small>
									</div>
									<div class="smart-timeline-content">
										<p>
											单据编码：<a href="javascript:void(0);"><strong>${entity.code }</strong></a>
										</p>
										<p>单据主题：${entity.name }</p>
										<p>创建人：${entity.creator }</p>
										<p>
											<s:if test="#s.First">
												<a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!show.action?id=${entity.id}');" class="btn btn-xs btn-success"><i class="fa fa-eye"></i> 查看</a>
											</s:if>
											<s:else>
												<a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/vixntSourceAction!goShowBill.action?code=${entity.code}&className='+$('#<s:property value="#s.index-1"/>sourceClassName').val());" class="btn btn-xs btn-success"><i class="fa fa-eye"></i> 查看</a>
											</s:else>
											<s:if test="#s.Last">
											</s:if>
											<s:else>
												<input type="hidden" id="<s:property value="#s.index"/>sourceClassName" value="${entity.sourceClassName}"/>
											</s:else>
										</p>
									</div>
								</li>
							</s:iterator>
						</s:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>