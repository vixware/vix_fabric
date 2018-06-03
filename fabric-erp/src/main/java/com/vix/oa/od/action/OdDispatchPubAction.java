package com.vix.oa.od.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.web.Pager;

/**
 * 公文发布
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class OdDispatchPubAction extends BaseAction{

	
	public String goSingleList(){
        try {
        	Pager pager = getPager();
        	List<UserAccount> ll = new ArrayList<UserAccount>();
        	ll.add(new UserAccount());
        	pager.setResultList(ll);
			setPager(pager);
        }catch(Exception e){
            e.printStackTrace();
        }
        return GO_SINGLE_LIST;
    }
	
	
    public String goSaveOrUpdate() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GO_SAVE_OR_UPDATE;
    }
    
    /** 处理修改操作  */
    public String saveOrUpdate() {
        boolean isSave = true;
        try {
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
        return UPDATE;
    }
    
    /** 处理删除操作 */
    public String deleteById() {
        try {
        	renderText(DELETE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            renderText(DELETE_FAIL);
        }
        return UPDATE;
    }

}
