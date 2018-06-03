package com.vix.hr.position.service;

import java.util.List;

import com.vix.common.org.entity.OrgJob;
import com.vix.common.org.entity.OrgPosition;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IOrgPositionService extends IBaseHibernateService{
	
	public Pager findOrgPositionPager(Pager pager,String orgUnitId,String posName) throws Exception;
	
	public OrgPosition findOrgPositionById(String id) throws Exception;
	
	/**
	 * 根据部门id 的到其所属公司的所有职位列表  待修改
	 * @param orgUnitId
	 * @return
	 */
	public List<OrgJob> findOrgJobListByCompId(String orgUnitId) throws Exception;
	
	/**
	 * 保存和更新岗位信息 
	 * @param orgPosition
	 * @return
	 * @throws Exception
	 */
	public OrgPosition saveOrUpdateOrgPosition(OrgPosition orgPosition) throws Exception;
	
	/**
	 * 根据公司id得到所有岗位信息   树形结构
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public List<OrgPosition> findOrgPositionListByOrgId(String orgId,String posParentId) throws Exception;
	
	/**
	 * 删除岗位
	 * @param id
	 * @throws Exception
	 */
	public void deleteOrgPosition(String id) throws Exception;
	
	/**
	 * 根据员工查询 员工的岗位列表
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public List<OrgPosition> findOrgPositionListByEmpId(String empId) throws Exception;
	
	/**
	 * 根据部门id得到岗位列表
	
	public List<OrgPosition> findOrgPositionListByOrgUnitId(String orgUnitId) throws Exception; */
}
