package com.vix.common.org.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vix.common.org.dao.IChannelDistributorShopDao;
import com.vix.core.constant.BizConstant;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.system.base.compOperation.vo.OrganizationTenantShopVO;

@Repository("channelDistributorShopDao")
public class ChannelDistributorShopDaoImpl extends BaseHibernateDaoImpl implements IChannelDistributorShopDao {

	private static final long serialVersionUID = 2319529080725263626L;

	@Override
	public void save4OrgShop(String tenantId,String companyInnerCode, List<OrganizationTenantShopVO> fromShopList)throws Exception{
		//private List<OrganizationTenantShopVO> shopList = new ArrayList<OrganizationTenantShopVO>(0);
		StringBuffer sql = new StringBuffer();
		List<Object> param = new LinkedList<Object>();
    	
		List<OrganizationTenantShopVO> arraySaveList = new LinkedList<OrganizationTenantShopVO>();
		List<Object[]> arrayUpdateParam = new LinkedList<Object[]>();
		
    	if(fromShopList!=null && !fromShopList.isEmpty()){
    		sql.append("select count(shop.ID) from DRP_CHANNELDISTRIBUTOR shop where shop.TENANTID = ? and shop.CODE = ? ");

    		for(OrganizationTenantShopVO shopVo:fromShopList){
    			param.add(tenantId);
        		param.add(shopVo.getCode());
    	
        		Integer count = queryForObject(sql.toString(),Integer.class, param.toArray());
        		
        		if(count == 0 ){
        			//需要insert
        			arraySaveList.add(shopVo);
        		}else if(count == 1 ){
        			//需要update      dc.STATUS=?  where shop.TENANTID = ? and shop.CODE = ?
        			arrayUpdateParam.add(new Object[]{
        					shopVo.getShopStatus().equals(BizConstant.COMMON_BOOLEAN_YES)?"1":"2",
        					tenantId,
        					shopVo.getShopCode()
        			});
        		}else{
        			throw new BizException("存在编号重复的门店信息！");
        		}
        		
        		param.clear();
    		}
    		
    		sql.setLength(0);
    	}

    	//设置
    	if(arraySaveList!=null && !arraySaveList.isEmpty()){
    		//新建 ChannelDistributor
    		List<ChannelDistributor> batchSaveList = new LinkedList<ChannelDistributor>();
    		
    		ChannelDistributor  cb =null;
    		for(OrganizationTenantShopVO shop:arraySaveList){
    			cb = new ChannelDistributor();
    			cb.setCode(shop.getShopCode());
    			cb.setName(shop.getShopName());
    			cb.setTenantId(tenantId);
    			cb.setTenantId(tenantId);
    			cb.setCompanyInnerCode(companyInnerCode);
    			cb.setType("4");
    			cb.setStatus(shop.getShopStatus().equals(BizConstant.COMMON_BOOLEAN_YES)?"1":"2");
    			batchSaveList.add(cb);
    			
    			cb = null;
        	}
    		saveOrUpdateOriginalBatch(batchSaveList);
    	}
    	
    	if(arrayUpdateParam!=null && !arrayUpdateParam.isEmpty()){
    		//更新
    		//sql.append("update DRP_CHANNELDISTRIBUTOR dc set dc.STATUS=? , dc.metaDataName=? , meta.metaDataDisplayName=?, meta.BaseMetaDataCategory_ID=? where meta.id=? ");
    		sql.append("update DRP_CHANNELDISTRIBUTOR dc set dc.STATUS=?  where shop.TENANTID = ? and shop.CODE = ? ");

        	batchUpdateBySql(sql.toString(), arrayUpdateParam);
        	
        	sql.setLength(0);
        	arrayUpdateParam.clear();
    	}
		
	}
	
}
