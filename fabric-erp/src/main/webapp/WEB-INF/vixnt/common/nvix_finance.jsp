<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a id="" href="#"><i class="fa fa-lg fa-fw fa-user"></i><span class="menu-item-parent">财务管理</span></a>
	<ul>
	    <li><a id="" href="#">设置</a>
			<ul>
			    <li><a href="#" id="">选项</a>
			        <ul>
					    <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/money/nvixSetUpAction!goList.action');">会计科目</a></li>
						<li><a href="#" id="" >凭证类型</a></li>
						<li><a href="#" id="" >外币设置</a></li>
					</ul>
			    </li>
				<li><a href="#" id="" >期初余额</a></li>
				<li><a href="#" id="" >项目目录</a></li>
				<li><a href="#" id="" >收复结算方式</a></li>
				<li><a href="#" id="" >数据权限分配</a></li>
				<li><a href="#" id="" >金额权限分配</a></li>
				<li><a href="#" id="" >总账套打工具</a></li>
				<li><a href="#" id="" >账簿清理</a></li>
			</ul>
		</li>
	    <li><a id="" href="#">账套管理</a>
			<ul>
			    <li><a href="#" id="" >新增</a></li>
				<li><a href="#" id="" >列表</a></li>
				<li><a href="#" id="" >输出</a></li>
			</ul>
		</li>
		<li><a id="" href="#">总账</a>
			<ul>
			    <li><a href="#">凭证</a>
			        <ul>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixProofAction!goWriteProof.action');">填制凭证</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixProofAction!goProofList.action');">凭证列表</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixProofAction!goTestProof.action');">审核凭证</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixProofAction!goQueryProof.action');">查询凭证</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixProofAction!goSumSubject.action');">科目汇总</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixProofAction!goSumArticle.action');">摘要汇总表</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixProofAction!goQueryCash.action');">现金流量凭证查询</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixProofAction!goChargeList.action');">记账</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixProofAction!goCommonProof.action');">常用凭证</a></li>
			        </ul>
			    </li>
				<li><a href="#" id="" >出纳</a>
				    <ul>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixCashierAction!goCashDiary.action');">现金日记账</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixCashierAction!goBankDiary.action');">银行日记账</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixCashierAction!goFundDiary.action');">资金日报表</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixCashierAction!goPrintBook.action');">账簿打印</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixCashierAction!goCheckBook.action');">支票登记簿</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixCashierAction!goCheckBank.action');">银行对账</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixCashierAction!goLostCheck.action');">长期未达账审计</a></li>
			        </ul>
				</li>
				<li><a href="#" id="" >账表</a>
				    <ul>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixBillAction!goMyForm.action');">我的账表</a></li>
				        <li><a href="#" id="" >科目账</a>
				            <ul>
						        <li><a href="#" id="" >总账</a></li>
						        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixBillAction!goBalanceBill.action');">余额表</a></li>
						        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixBillAction!goDetailBill.action');">明细账</a></li>
						        <li><a href="#" id="" >多栏账</a></li>
			                </ul>
				        </li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixBillAction!goAssistBill.action');">客户往来辅助账</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixBillAction!goApplyBill.action');">供应商往来辅助账</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixBillAction!goPersonalBill.action');">个人往来账</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixBillAction!goDepartmentBill.action');">部门辅助账</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixBillAction!goProjectBill.action');">项目辅助账</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixBillAction!goCashBill.action');">现金流量表</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixBillAction!goPrintBill.action');">账簿打印</a></li>
			        </ul>
				</li>
				<li><a href="#" id="">综合辅助账</a>
				    <ul>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixSynthesizeAction!goAssistDetail.action');">科目辅助明细账</a></li>
				        <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/totalPay/nvixSynthesizeAction!goAssistSum.action');">科目辅助汇总表</a></li>
			        </ul>
				</li>
				<li><a href="#" id="">期末</a></li>
			</ul>
		</li>
		<li><a id="" href="#">应收款管理</a>
			<ul>
			    <li><a href="#" id="" >设置</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/money/nvixCollectionMoneyAction!goCollectionList.action');">应收单据处理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/money/nvixCollectionMoneyAction!goCollectionBill.action');">收款单据处理</a></li>
				<li><a href="#" id="" >核销处理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/money/nvixCollectionMoneyAction!goNoteList.action');">票据管理</a></li>
				<li><a href="#" id="" >转账</a></li>
				<li><a href="#" id="" >坏账处理</a></li>
				<li><a href="#" id="" >制单处理</a></li>
				<li><a href="#" id="" >单据查询</a></li>
				<li><a href="#" id="" >账表管理</a></li>
			</ul>
		</li>
		<li><a id="" href="#">应付款管理</a>
			<ul>
			    <li><a href="#" id="" >设置</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/money/nvixPayMoneyAction!goPayList.action');">应付单据处理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/money/nvixPayMoneyAction!goPayBill.action');">付款单据处理</a></li>
				<li><a href="#" id="" >核销处理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/money/nvixPayMoneyAction!goNoteList.action');">票据管理</a></li>
				<li><a href="#" id="" >转账</a></li>
				<li><a href="#" id="" >制单处理</a></li>
				<li><a href="#" id="" >单据查询</a></li>
				<li><a href="#" id="" >账表管理</a></li>
			</ul>
		</li>
		<li><a id="" href="#">固定资产</a>
			<ul>
			    <li><a href="#" id="" >设置</a></li>
				<li><a href="#" id="" >卡片</a></li>
				<li><a href="#" id="" >处理</a></li>
				<li><a href="#" id="" >账表</a></li>
				<li><a href="#" id="" >维护</a></li>
			</ul>
		</li>
		<li><a id="" href="#">网上报销</a>
			<ul>
			    <li><a href="#" id="" >基础设置</a></li>
				<li><a href="#" id="" >借款</a></li>
				<li><a href="#" id="" >报销</a></li>
				<li><a href="#" id="" >报销审核</a></li>
				<li><a href="#" id="" >网上支付</a></li>
				<li><a href="#" id="" >单据查询</a></li>
				<li><a href="#" id="" >报销查询</a></li>
			</ul>
		</li>
		<li><a id="" href="#">财务报表</a>
			<ul>
			    <li><a href="#" id="" >现金流量表</a></li>
				<li><a href="#" id="" >资产负债表</a></li>
				<li><a href="#" id="" >所有者权益表</a></li>
			</ul>
		</li>
		<li><a id="" href="#">公司对账</a>
			<ul>
			    <li><a href="#" id="" >设置</a></li>
				<li><a href="#" id="" >往来对账单</a></li>
				<li><a href="#" id="" >往来自动对账</a></li>
				<li><a href="#" id="" >账表</a></li>
			</ul>
		</li>
		<li><a id="" href="#">成本管理</a>
			<ul>
			    <li><a href="#" id="" >设置</a></li>
				<li><a href="#" id="" >数据录入</a></li>
				<li><a href="#" id="" >核算</a></li>
				<li><a href="#" id="" >计划</a></li>
				<li><a href="#" id="" >预测</a></li>
				<li><a href="#" id="" >分析</a></li>
			</ul>
		</li>
		<li><a id="" href="#">项目成本管理</a>
			<ul>
			    <li><a href="#" id="" >设置</a></li>
				<li><a href="#" id="" >项目规划</a></li>
				<li><a href="#" id="" >项目执行</a></li>
				<li><a href="#" id="" >项目终止</a></li>
				<li><a href="#" id="" >报表</a></li>
			</ul>
		</li>
		<li><a id="" href="#">预算管理</a>
			<ul>
			    <li><a href="#" id="" >设置</a></li>
				<li><a href="#" id="" >预算体系</a></li>
				<li><a href="#" id="" >日常管理</a></li>
				<li><a href="#" id="" >预算调整</a></li>
				<li><a href="#" id="" >预算控制</a></li>
				<li><a href="#" id="" >预算查询</a></li>
				<li><a href="#" id="" >分析报告</a></li>
				<li><a href="#" id="" >系统接口</a></li>
			</ul>
		</li>
		<li><a id="" href="#">筹投资计划</a>
			<ul>
			    <li><a href="#" id="" >期初单据</a></li>
				<li><a href="#" id="" >查询单据</a></li>
				<li><a href="#" id="" >凭证批处理</a></li>
				<li><a href="#" id="" >凭证查询</a></li>
				
			</ul>
		</li>
	</ul>
</li>
