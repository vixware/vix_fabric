package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 会员等级视图
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class VixntMemberLevelManagementAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private MemberLevel memberLevel;
	/**
	 * 获取会员等级列表
	 */
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			/*if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("createTime");
				pager.setOrderBy("desc");
			}*/
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("name,"+SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, MemberLevel.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到新增页面
	 * @return
	 */
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id)){
				memberLevel = vixntBaseService.findEntityById(MemberLevel.class, id);
			}else{
				memberLevel = new MemberLevel();
				memberLevel.setCode(VixUUID.createCodeByNumber(15));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	/**
	 * 处理保存操作
	 */
	public void saveOrUpdate(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(memberLevel.getId())){
				isSave = false;
			}
			memberLevel.setCreateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(memberLevel);
			memberLevel = vixntBaseService.merge(memberLevel);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
	}
	public void deleteById(){
		try {
			MemberLevel memberLevel = vixntBaseService.findEntityById(MemberLevel.class, id);
			if(memberLevel != null){
				vixntBaseService.deleteByEntity(memberLevel);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public String goMemberStatistics(){
		return "goMemberStatistics";
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public MemberLevel getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(MemberLevel memberLevel) {
		this.memberLevel = memberLevel;
	}
}
