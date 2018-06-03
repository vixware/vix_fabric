<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function searchForRightContent() {
		loadName();
		pager("start", "${vix}/crm/business/dispenseCouponAction!goSingleListCouponDetail.action?couponId=" + $('#couponId').val(), 'couponDetail');
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/crm/business/dispenseCouponAction!goSingleListCouponDetail.action?couponId=" + $('#couponId').val(), 'couponDetail');
	$.returnValue = "";
	function chkChange(chk, id) {
		$.returnValue = id;
	}
	function currentPagerForItemChoose(tag) {
		pager(tag, "${vix}/crm/business/dispenseCouponAction!goSingleListCouponDetail.action?couponId=" + $('#couponId').val(), 'couponDetail');
	}
</script>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box" style="height: 455px;">
		<input type="hidden" id="customerAccountId" name="customerAccountId" value="${customerAccountId}" /> <input type="hidden" id="couponId" name="couponId" value="${couponId}" />
		<div id="right">
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPagerForItemChoose('start');"></a></span> <span><a class="previous" onclick="currentPagerForItemChoose('previous');"></a></span> <em>(<b class="couponDetailInfo"></b> <s:text name='cmn_to' /> <b class="couponDetailTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPagerForItemChoose('next');"></a></span> <span><a class="end" onclick="currentPagerForItemChoose('end');"></a></span>
					</div>
				</div>
				<div id="couponDetail" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPagerForItemChoose('start');"></a></span> <span><a class="previous" onclick="currentPagerForItemChoose('previous');"></a></span> <em>(<b class="couponDetailInfo"></b> <s:text name='cmn_to' /> <b class="couponDetailTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPagerForItemChoose('next');"></a></span> <span><a class="end" onclick="currentPagerForItemChoose('end');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>