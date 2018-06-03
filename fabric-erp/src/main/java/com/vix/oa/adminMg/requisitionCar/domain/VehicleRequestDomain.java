/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.oa.adminMg.requisitionCar.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.requisitionCar.entity.CarMaintenance;
import com.vix.oa.adminMg.requisitionCar.entity.Tpk;
import com.vix.oa.adminMg.requisitionCar.entity.VehicleRequest;
import com.vix.oa.adminMg.typeSettings.entity.DisplacementType;
import com.vix.oa.adminMg.typeSettings.entity.EngineType;
import com.vix.oa.adminMg.typeSettings.entity.MaintenanceType;
import com.vix.oa.adminMg.typeSettings.entity.TransmissionType;
import com.vix.oa.adminMg.typeSettings.entity.VehicleColor;
import com.vix.oa.adminMg.typeSettings.entity.VehicleType;

/**
 * 
 * @ClassName: VehicleRequestDomain
 * @Description: 车辆管理 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-3 下午2:00:34
 */
@Component("vehicleRequestDomain")
@Transactional
public class VehicleRequestDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, VehicleRequest.class, params);
		return p;
	}
	
	/** 获取车辆维修列表数据 */
	public Pager findPagerByHqlConditions1(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CarMaintenance.class, params);
		return p;
	}
	/** 获取车辆油耗列表数据 */
	public Pager findPagerByHqlConditions2(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Tpk.class, params);
		return p;
	}
	
	/** 获取列表数据 */
	public Pager findPagerByVehicleHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, VehicleType.class, params);
		return p;
	}
	
	public VehicleType findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(VehicleType.class, id);
	}
	
	public VehicleType merge(VehicleType vehicleType) throws Exception {
		return baseHibernateService.merge(vehicleType);
	}
	
	
	public void deleteByEntity(VehicleType vehicleType) throws Exception {
		baseHibernateService.deleteByEntity(vehicleType);
	}
	
	///////
	/** 获取列表数据 */
	public Pager findPagerByVehicleColorConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, VehicleColor.class, params);
		return p;
	}
	
	public VehicleColor findVehicleColorById(String id) throws Exception {
		return baseHibernateService.findEntityById(VehicleColor.class, id);
	}
	
	public VehicleColor merge(VehicleColor vehicleColor) throws Exception {
		return baseHibernateService.merge(vehicleColor);
	}
	
	public void deleteByEntity(VehicleColor vehicleColor) throws Exception {
		baseHibernateService.deleteByEntity(vehicleColor);
	}
	///////
	/** 获取列表数据 */
	public Pager findPagerByTransmissionType(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, TransmissionType.class, params);
		return p;
	}
	
	public TransmissionType findTransmissionTypeById(String id) throws Exception {
		return baseHibernateService.findEntityById(TransmissionType.class, id);
	}
	
	public TransmissionType merge(TransmissionType transmissionType) throws Exception {
		return baseHibernateService.merge(transmissionType);
	}
	
	public void deleteByTransmissionType(TransmissionType transmissionType) throws Exception {
		baseHibernateService.deleteByEntity(transmissionType);
	}
	
	///////
	/** 获取列表数据 */
	public Pager findPagerByEngineType(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, EngineType.class, params);
		return p;
	}
	
	public EngineType findEngineTypeById(String id) throws Exception {
		return baseHibernateService.findEntityById(EngineType.class, id);
	}
	
	public EngineType merge(EngineType engineType) throws Exception {
		return baseHibernateService.merge(engineType);
	}
	
	public void deleteByEngineType(EngineType engineType) throws Exception {
		baseHibernateService.deleteByEntity(engineType);
	}
	
	///////
	/** 获取列表数据 */
	public Pager findPagerByDisplacementType(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, DisplacementType.class, params);
		return p;
	}
	
	public DisplacementType findDisplacementTypeById(String id) throws Exception {
		return baseHibernateService.findEntityById(DisplacementType.class, id);
	}
	
	public DisplacementType merge(DisplacementType displacementType) throws Exception {
		return baseHibernateService.merge(displacementType);
	}
	
	public void deleteByDisplacementType(DisplacementType displacementType) throws Exception {
		baseHibernateService.deleteByEntity(displacementType);
	}
///////
	/** 获取列表数据 */
	public Pager findPagerByMaintenanceType(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MaintenanceType.class, params);
		return p;
	}
	
	public MaintenanceType findMaintenanceTypeById(String id) throws Exception {
		return baseHibernateService.findEntityById(MaintenanceType.class, id);
	}
	
	public MaintenanceType merge(MaintenanceType maintenanceType) throws Exception {
		return baseHibernateService.merge(maintenanceType);
	}
	
	public void deleteByMaintenanceType(MaintenanceType maintenanceType) throws Exception {
		baseHibernateService.deleteByEntity(maintenanceType);
	}
	/////
	/**车辆维护*/
	public CarMaintenance findCarMaintenanceById(String id) throws Exception {
		return baseHibernateService.findEntityById(CarMaintenance.class, id);
	}
	
	public VehicleRequest findVehicleRequest1ById(String id) throws Exception {
		return baseHibernateService.findEntityById(VehicleRequest.class, id);
	}
	
	public CarMaintenance merge(CarMaintenance carMaintenance) throws Exception {
		return baseHibernateService.merge(carMaintenance);
	}
	
	public void deleteByCarMaintenance(CarMaintenance carMaintenance) throws Exception {
		baseHibernateService.deleteByEntity(carMaintenance);
	}
	/** 获取列表数据 */
	public Pager findPagerByVehicleHqlCarMaintenance(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CarMaintenance.class, params);
		return p;
	}
	/////
	/**车辆油耗*/
	public Pager findPagerByTpkType(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Tpk.class, params);
		return p;
	}
	
	public Tpk findTpkById(String id) throws Exception {
		return baseHibernateService.findEntityById(Tpk.class, id);
	}
	
	public Tpk merge(Tpk tpk) throws Exception {
		return baseHibernateService.merge(tpk);
	}
	
	public void deleteByTpk(Tpk tpk) throws Exception {
		baseHibernateService.deleteByEntity(tpk);
	}
	/** 获取列表数据 */
	public Pager findPagerByVehicleHqlTpk(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Tpk.class, params);
		return p;
	}
	
	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, VehicleRequest.class, params);
		return p;
	}
	
	/** 获取车辆维修搜索列表数据 */
	public Pager findPagerByOrHqlConditions1(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, CarMaintenance.class, params);
		return p;
	}
	/** 获取车辆油耗搜索列表数据 */
	public Pager findPagerByOrHqlConditions2(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, Tpk.class, params);
		return p;
	}

	public VehicleRequest findVehicleRequestById(String id) throws Exception {
		return baseHibernateService.findEntityById(VehicleRequest.class, id);
	}

	public VehicleRequest merge(VehicleRequest vehicleRequest) throws Exception {
		return baseHibernateService.merge(vehicleRequest);
	}
	
	/** 索引对象列表 */
	public List<CarMaintenance> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(CarMaintenance.class, null);
	}
	
	/** 索引车辆油耗管理对象列表 */
	public List<Tpk> findTpkIndex() throws Exception {
		return baseHibernateService.findAllByConditions(Tpk.class, null);
	}


	public void deleteByVehicleRequest(VehicleRequest vehicleRequest) throws Exception {
		baseHibernateService.deleteByEntity(vehicleRequest);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(VehicleRequest.class, ids);
	}

	/** 索引对象列表 */
	public List<VehicleRequest> findVehicleRequestIndex() throws Exception {
		return baseHibernateService.findAllByConditions(VehicleRequest.class, null);
	}

}
