<div class="page__bd" id="latestOperate">
	<div class="weui-panel weui-panel_access">
		<s:if test="evaluationReviewList.size > 0">
			<div class="weui-panel__hd">回复（${evaluationReviewNum }）</div>
			<div class="weui-panel__bd">
				<s:iterator value="evaluationReviewList" var="entity" status="s">
					<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
						<div class="weui-media-box__hd">
							<img class="weui-media-box__thumb" src="${entity.employee.headImgUrl }" alt="" style="width: 50px; height: 50px;">
						</div>
						<div class="weui-media-box__bd">
							<h4 class="weui-media-box__title">${entity.employee.name }</h4>
							<p class="weui-media-box__desc">${entity.evaluationContent }</p>
						</div>
						<div class="weui-media-box__ft">${entity.evaluationTimeTimeStr}</div>
					</a>
				</s:iterator>
			</div>
		</s:if>
		<s:else>
			<div class="weui-panel__hd">暂无回复</div>
		</s:else>
	</div>
</div>
