<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a id="" href="#"><i class="fa fa-lg fa-fw fa-user"></i><span class="menu-item-parent">人力资源管理</span></a>
	<ul>
	    <li><a id="" href="#">组织管理</a>
			<ul>
			    <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixAgencyAction!goList.action');">组织架构</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixOrganizeAction!goList.action');">组织架构图</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixDepartmentAction!goList.action');">部门管理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixntPostAction!goList.action');">岗位管理</a></li>
			</ul>
		</li>
	    <li><a id="" href="#">员工管理</a>
			<ul>
				<li><a href="#" id="">员工信息管理</a>
				   <ul>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNameBookAction!goEmployeeList.action');">员工列表</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNameBookAction!goList.action');">花名册</a>
				  </ul>
				</li>
				<li><a id="" href="#">事务管理</a>
					<ul>
						<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goList.action');">转正</a></li>
						<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goUnusual.action');">异动</a></li>
						<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goBorrow.action');">借调</a></li>
						<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goAskLeave.action');">请假</a></li>
						<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goDimission.action');">离职</a></li>
						<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goRetire.action');">辞退</a></li>
						<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goPrepareRetire.action');">离退休</a></li>
						<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixHrManageAction!goRehire.action');">返聘</a></li>
					</ul>
		        </li>
			</ul>
		</li>
		<li><a href="#" id="" onclick="">招聘管理</a>
		    <ul>
		        <li><a id="" href="#" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goList.action');">招聘征集</a></li>
		        <li><a id="" href="#" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goApplyList.action');">用人申请</a></li>
		        <li><a id="" href="#" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goAllRecruitList.action');">招聘汇总</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitPlanAction!goList.action');">招聘计划</a></li>
		        <li><a id="" href="#" >招聘活动</a>
		            <ul>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goActivityList.action');">内部招聘</a>
				      <li><a href="#" id="" onclick="javascript:innerRecruit();">外部招聘</a>
				    </ul>
		        </li>
		        <li><a id="" href="#" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goSuperTalentList.action');">高级人才招聘</a></li>
		        <li><a href="#" id="" >甄选录用</a>
		           <ul>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixInterviewAction!goSelectDataList.action');">资料筛选</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixInterviewAction!goList.action');">面试管理</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixInterviewAction!goEmployList.action');">录用管理</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixInterviewAction!goResumeLibraryList.action');">后备简历库</a>
				   </ul>
		        </li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixInterviewAction!goSumRecruit.action');">招聘总结</a></li>
			</ul>
		</li>
		<li><a href="#" id="" onclick="">培训管理</a>
		    <ul>
		        <li><a id="" href="#" >培训体系</a>
				    <ul>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goList.action');">培训课程</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goDataList.action');">培训资料</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goChannelList.action');">培训渠道</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goTeacherList.action');">培训讲师</a>
				   </ul>
				</li>
		        <li><a id="" href="#" >培训需求</a>
				   <ul>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNeedAction!goList.action');">培训需求征集</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNeedAction!goNeedApply.action');">培训需求申请</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNeedAction!goAllNeedList.action');">培训需求汇总</a>
				   </ul>
				</li>
		        <li><a id="" href="#">培训计划</a>
		           <ul>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goList.action');">培训规划</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goActivityList.action');">培训活动</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goQuessionList.action');">问卷调查</a>
				   </ul>
		        </li>
				<li><a id="" href="#">培训总结</a>
				   <ul>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goCourseSummarizeList.action');">课程总结</a>
				      <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goActivitySummarizeList.action');">活动总结</a>
				   </ul>
				</li>
			</ul>
		</li>
		<!-- <li><a href="#" id="" onclick="">档案管理</a></li>
		<li><a href="#" id="" onclick="">公司信息</a></li> -->
	</ul>
</li>
<script type="text/javascript">
     function innerRecruit(){
    	 layer.alert("正在对接智联招聘  !");
     }
</script>