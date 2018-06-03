package com.vix.nvix.common.base.hql;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
public class VixntBaseHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findVixTaskPager(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from VixTask ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".employees e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append("e.id = :employeeId ");
				}
			}
			if (params.containsKey("status")) {
				Object status = params.get("status");
				if (status == null) {
					hql.append(" and ").append(ename).append(".status is null");
					params.remove("status");
				} else {
					hql.append(" and ").append(ename).append(".status = :status ");
				}
			}
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp = :isTemp ");
				}
			}
			if (params.containsKey("isDeleted")) {
				Object isDeleted = params.get("isDeleted");
				if (isDeleted == null) {
					hql.append(" and ").append(ename).append(".isDeleted is null");
					params.remove("isDeleted");
				} else {
					hql.append(" and ").append(ename).append(".isDeleted = :isDeleted ");
				}
			}
			if (params.containsKey("complete")) {
				Object complete = params.get("complete");
				if (complete == null) {
					hql.append(" and ").append(ename).append(".complete is null");
					params.remove("complete");
				} else {
					hql.append(" and ").append(ename).append(".complete = :complete ");
				}
			}
			if (params.containsKey("questName")) {
				Object questName = params.get("questName");
				if (questName == null) {
					hql.append(" and ").append(ename).append(".questName is null");
					params.remove("questName");
				} else {
					hql.append(" and ").append(ename).append(".questName like :questName ");
				}
			}
			if (params.containsKey("taskStartTime")) {
				Object taskStartTime = params.get("taskStartTime");
				if (taskStartTime == null) {
					hql.append(" and ").append(ename).append(".taskStartTime is null");
					params.remove("taskStartTime");
				} else {
					hql.append(" and ").append(ename).append(".taskStartTime <= :taskStartTime ");
				}
			}
			if (params.containsKey("taskEndTime")) {
				Object taskEndTime = params.get("taskEndTime");
				if (taskEndTime == null) {
					hql.append(" and ").append(ename).append(".taskEndTime is null");
					params.remove("taskEndTime");
				} else {
					hql.append(" and ").append(ename).append(".taskEndTime >= :taskEndTime ");
				}
			}
			if (params.containsKey("notcomplete")) {
				Object notcomplete = params.get("notcomplete");
				if (notcomplete == null) {
					hql.append(" and ").append(ename).append(".complete is null");
					params.remove("notcomplete");
				} else {
					hql.append(" and ").append(ename).append(".complete != :notcomplete ");
				}
			}
		}
		if (pager != null && StringUtils.isEmpty(pager.getOrderField())) {
			hql.append(" order by ").append(ename).append(".createTime desc ");
		} else {
			buildOrderQl(ename, hql, pager);
		}
		return hql;
	}

	public StringBuilder findStockRecordLinesHql(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from StockRecordLines ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".stockRecords e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("stockRecordsId")) {
				Object stockRecordsId = params.get("stockRecordsId");
				if (stockRecordsId == null || "".equals(stockRecordsId)) {
				} else {
					hql.append(" and ").append(" e.id in (");
					String[] ivArray = stockRecordsId.toString().split(",");
					for (int i = 0; i < ivArray.length; i++) {
						String iv = ivArray[i];
						iv = iv.trim();
						if (null != iv && !"".equals(iv)) {
							hql.append("'");
							hql.append(iv);
							hql.append("'");
							if (i < ivArray.length - 1) {
								hql.append(",");
							}
						}
					}
					hql.append(") ");
				}
			}
		}

		hql.append(" order by ").append(ename).append(".createTime desc ");

		return hql;
	}

	public StringBuilder findUploaderHql(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Uploader ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subEmployees e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append("e.id = :employeeId ");
				}
			}
			if (params.containsKey("projectId")) {
				Object projectId = params.get("projectId");
				if (projectId == null) {
					hql.append(" and ").append(ename).append(".project.id is null");
					params.remove("projectId");
				} else {
					hql.append(" and ").append(ename).append(".project.id = :projectId ");
				}
			}
			if (params.containsKey("uploaderTypeId")) {
				Object uploaderTypeId = params.get("uploaderTypeId");
				if (uploaderTypeId == null) {
					hql.append(" and ").append(ename).append(".uploaderType.id is null");
					params.remove("uploaderTypeId");
				} else {
					hql.append(" and ").append(ename).append(".uploaderType.id = :uploaderTypeId ");
				}
			}
		}
		if (pager != null && StringUtils.isEmpty(pager.getOrderField())) {
			hql.append(" order by ").append(ename).append(".createTime desc ");
		} else {
			buildOrderQl(ename, hql, pager);
		}
		return hql;
	}

	public StringBuilder findItemPager(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Item ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subChannelDistributors  e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("channelDistributorsId")) {
				Object channelDistributorsId = params.get("channelDistributorsId");
				if (channelDistributorsId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("channelDistributorsId");
				} else {
					hql.append(" and ").append("e.id = :channelDistributorsId ");
				}
			}
			if (params.containsKey("categoryId")) {
				Object categoryId = params.get("categoryId");
				if (categoryId == null) {
					hql.append(" and ").append(ename).append(".itemCatalog.id is null");
					params.remove("categoryId");
				} else {
					hql.append(" and ").append(ename).append(".itemCatalog.id = :categoryId ");
				}
			}
			if (params.containsKey("name")) {
				Object name = params.get("name");
				if (name == null) {
					hql.append(" and ").append(ename).append(".name is null");
					params.remove("name");
				} else {
					hql.append(" and ").append(ename).append(".name like :name ");
				}
			}
			if (params.containsKey("itemIds")) {
				Object itemIds = params.get("itemIds");
				if (itemIds == null || "".equals(itemIds)) {
					params.remove("itemIds");
				} else {
					hql.append(" and ").append(ename).append(".id not in (");
					String[] ivArray = itemIds.toString().split(",");
					for (int i = 0; i < ivArray.length; i++) {
						String iv = ivArray[i];
						iv = iv.trim();
						if (null != iv && !"".equals(iv)) {
							hql.append("'");
							hql.append(iv);
							hql.append("'");
							if (i < ivArray.length - 1) {
								hql.append(",");
							}
						}
					}
					hql.append(") ");
				}
			}
		}
		if (pager != null && StringUtils.isEmpty(pager.getOrderField())) {
			hql.append(" order by ").append(ename).append(".createTime desc ");
		} else {
			buildOrderQl(ename, hql, pager);
		}
		return hql;
	}

	public StringBuilder findItemHql(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Item ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subChannelDistributors  e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("channelDistributorsId")) {
				Object channelDistributorsId = params.get("channelDistributorsId");
				if (channelDistributorsId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("channelDistributorsId");
				} else {
					hql.append(" and ").append("e.id = :channelDistributorsId ");
				}
			}
			if (params.containsKey("categoryId")) {
				Object categoryId = params.get("categoryId");
				if (categoryId == null) {
					hql.append(" and ").append(ename).append(".itemCatalog.id is null");
					params.remove("categoryId");
				} else {
					hql.append(" and ").append(ename).append(".itemCatalog.id = :categoryId ");
				}
			}
			if (params.containsKey("name")) {
				Object name = params.get("name");
				if (name == null) {
					hql.append(" and ").append(ename).append(".name is null");
					params.remove("name");
				} else {
					hql.append(" and ").append(ename).append(".name like :name ");
				}
			}
		}
		if (pager != null && StringUtils.isEmpty(pager.getOrderField())) {
			hql.append(" order by ").append(ename).append(".createTime desc ");
		} else {
			buildOrderQl(ename, hql, pager);
		}
		return hql;
	}

	public StringBuilder findStoreItemPager(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from StoreItem ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subChannelDistributors  e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("channelDistributorsId")) {
				Object channelDistributorsId = params.get("channelDistributorsId");
				if (channelDistributorsId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("channelDistributorsId");
				} else {
					hql.append(" and ").append("e.id = :channelDistributorsId ");
				}
			}
			if (params.containsKey("name")) {
				Object name = params.get("name");
				if (name == null) {
					hql.append(" and ").append(ename).append(".name is null");
					params.remove("name");
				} else {
					hql.append(" and ").append(ename).append(".name like :name ");
				}
			}
			if (params.containsKey("flag")) {
				Object flag = params.get("flag");
				if (flag == null) {
					hql.append(" and ").append(ename).append(".flag is null");
					params.remove("flag");
				} else {
					hql.append(" and ").append(ename).append(".flag = :flag ");
				}
			}
			if (params.containsKey("itemCatalogId")) {
				Object itemCatalogId = params.get("itemCatalogId");
				if (itemCatalogId == null) {
					hql.append(" and ").append(ename).append(".itemCatalog.id is null");
					params.remove("itemCatalogId");
				} else {
					hql.append(" and ").append(ename).append(".itemCatalog.id = :itemCatalogId ");
				}
			}
		}
		if (pager != null && StringUtils.isEmpty(pager.getOrderField())) {
			hql.append(" order by ").append(ename).append(".createTime desc ");
		} else {
			buildOrderQl(ename, hql, pager);
		}
		return hql;
	}

	public StringBuilder findStoreItem(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from StoreItem ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subChannelDistributors  e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("channelDistributorsId")) {
				Object channelDistributorsId = params.get("channelDistributorsId");
				if (channelDistributorsId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("channelDistributorsId");
				} else {
					hql.append(" and ").append("e.id = :channelDistributorsId ");
				}
			}
			if (params.containsKey("itemIds")) {
				Object itemIds = params.get("itemIds");
				if (itemIds == null || "".equals(itemIds)) {
					params.remove("itemIds");
				} else {
					hql.append(" and ").append(ename).append(".id in (");
					String[] ivArray = itemIds.toString().split(",");
					for (int i = 0; i < ivArray.length; i++) {
						String iv = ivArray[i];
						iv = iv.trim();
						if (null != iv && !"".equals(iv)) {
							hql.append("'");
							hql.append(iv);
							hql.append("'");
							if (i < ivArray.length - 1) {
								hql.append(",");
							}
						}
					}
					hql.append(") ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findVixTaskHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from VixTask ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".employees e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append("e.id = :employeeId ");
				}
			}
			if (params.containsKey("status")) {
				Object status = params.get("status");
				if (status == null) {
					hql.append(" and ").append(ename).append(".status is null");
					params.remove("status");
				} else {
					hql.append(" and ").append(ename).append(".status = :status ");
				}
			}
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp = :isTemp ");
				}
			}
			if (params.containsKey("isDeleted")) {
				Object isDeleted = params.get("isDeleted");
				if (isDeleted == null) {
					hql.append(" and ").append(ename).append(".isDeleted is null");
					params.remove("isDeleted");
				} else {
					hql.append(" and ").append(ename).append(".isDeleted = :isDeleted ");
				}
			}
			if (params.containsKey("complete")) {
				Object complete = params.get("complete");
				if (complete == null) {
					hql.append(" and ").append(ename).append(".complete is null");
					params.remove("complete");
				} else {
					hql.append(" and ").append(ename).append(".complete = :complete ");
				}
			}
			if (params.containsKey("questName")) {
				Object questName = params.get("questName");
				if (questName == null) {
					hql.append(" and ").append(ename).append(".questName is null");
					params.remove("questName");
				} else {
					hql.append(" and ").append(ename).append(".questName like :questName ");
				}
			}
			if (params.containsKey("taskStartTime")) {
				Object taskStartTime = params.get("taskStartTime");
				if (taskStartTime == null) {
					hql.append(" and ").append(ename).append(".taskStartTime is null");
					params.remove("taskStartTime");
				} else {
					hql.append(" and ").append(ename).append(".taskStartTime <= :taskStartTime ");
				}
			}
			if (params.containsKey("taskEndTime")) {
				Object taskEndTime = params.get("taskEndTime");
				if (taskEndTime == null) {
					hql.append(" and ").append(ename).append(".taskEndTime is null");
					params.remove("taskEndTime");
				} else {
					hql.append(" and ").append(ename).append(".taskEndTime >= :taskEndTime ");
				}
			}
			if (params.containsKey("notcomplete")) {
				Object notcomplete = params.get("notcomplete");
				if (notcomplete == null) {
					hql.append(" and ").append(ename).append(".complete is null");
					params.remove("notcomplete");
				} else {
					hql.append(" and ").append(ename).append(".complete != :notcomplete ");
				}
			}
			if (params.containsKey("taskIds")) {
				Object taskIds = params.get("taskIds");
				if (taskIds == null || "".equals(taskIds)) {
					params.remove("taskIds");
				} else {
					hql.append(" and ").append(ename).append(".id in (");
					String[] ivArray = taskIds.toString().split(",");
					for (int i = 0; i < ivArray.length; i++) {
						String iv = ivArray[i];
						iv = iv.trim();
						if (null != iv && !"".equals(iv)) {
							hql.append("'");
							hql.append(iv);
							hql.append("'");
							if (i < ivArray.length - 1) {
								hql.append(",");
							}
						}
					}
					hql.append(") ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findApplicationMgHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from ApplicationMg ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".affiliatedEmployees e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append("e.id = :employeeId ");
				}
			}
			if (params.containsKey("status")) {
				Object status = params.get("status");
				if (status == null) {
					hql.append(" and ").append(ename).append(".status is null");
					params.remove("status");
				} else {
					hql.append(" and ").append(ename).append(".status = :status ");
				}
			}
			if (params.containsKey("meetingTheme")) {
				Object meetingTheme = params.get("meetingTheme");
				if (meetingTheme == null) {
					hql.append(" and ").append(ename).append(".meetingTheme is null");
					params.remove("meetingTheme");
				} else {
					hql.append(" and ").append(ename).append(".meetingTheme like :meetingTheme ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findWorkLogPager(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from WorkLog ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subEmployees e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append("e.id = :employeeId ");
				}
			}
			if (params.containsKey("status")) {
				Object status = params.get("status");
				if (status == null) {
					hql.append(" and ").append(ename).append(".status is null");
					params.remove("status");
				} else {
					hql.append(" and ").append(ename).append(".status = :status ");
				}
			}
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp != :isTemp ");
				}
			}
			if (params.containsKey("title")) {
				Object title = params.get("title");
				if (title == null) {
					hql.append(" and ").append(ename).append(".title is null");
					params.remove("title");
				} else {
					hql.append(" and ").append(ename).append(".title like :title ");
				}
			}
			if (params.containsKey("creator")) {
				Object creator = params.get("creator");
				if (creator == null) {
					hql.append(" and ").append(ename).append(".creator is null");
					params.remove("creator");
				} else {
					hql.append(" and ").append(ename).append(".creator like :creator ");
				}
			}
		}
		if (pager != null && StringUtils.isEmpty(pager.getOrderField())) {
			hql.append(" order by ").append(ename).append(".createTime desc ");
		} else {
			buildOrderQl(ename, hql, pager);
		}
		return hql;
	}

	public StringBuilder findCommunicationPager(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Communication ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subEmployees e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append("e.id = :employeeId ");
				}
			}
			if (params.containsKey("title")) {
				Object title = params.get("title");
				if (title == null) {
					hql.append(" and ").append(ename).append(".title is null");
					params.remove("title");
				} else {
					hql.append(" and ").append(ename).append(".title like :title ");
				}
			}
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp != :isTemp ");
				}
			}
			if (params.containsKey("creator")) {
				Object creator = params.get("creator");
				if (creator == null) {
					hql.append(" and ").append(ename).append(".creator is null");
					params.remove("creator");
				} else {
					hql.append(" and ").append(ename).append(".creator like :creator ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findProjectManagementPagerHql(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from ProjectManagement ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".employees e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append("e.id = :employeeId ");
				}
			}
			if (params.containsKey("proposalTitle")) {
				Object proposalTitle = params.get("proposalTitle");
				if (proposalTitle == null) {
					hql.append(" and ").append(ename).append(".proposalTitle is null");
					params.remove("proposalTitle");
				} else {
					hql.append(" and ").append(ename).append(".proposalTitle like :proposalTitle ");
				}
			}
			if (params.containsKey("uploadPersonName")) {
				Object uploadPersonName = params.get("uploadPersonName");
				if (uploadPersonName == null) {
					hql.append(" and ").append(ename).append(".uploadPersonName is null");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".uploadPersonName like :uploadPersonName ");
				}
			}
		}
		if (pager != null && StringUtils.isEmpty(pager.getOrderField())) {
			hql.append(" order by ").append(ename).append(".createTime desc ");
		} else {
			buildOrderQl(ename, hql, pager);
		}
		return hql;
	}

	public StringBuilder findTripRecordPagerHql(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from TripRecord ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subEmployees e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append("e.id = :employeeId ");
				}
			}
		}
		if (pager != null && StringUtils.isEmpty(pager.getOrderField())) {
			hql.append(" order by ").append(ename).append(".createTime desc ");
		} else {
			buildOrderQl(ename, hql, pager);
		}
		return hql;
	}

	public StringBuilder findProjectHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Project ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subEmployees e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append("e.id = :employeeId ");
				}
			}
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp = :isTemp ");
				}
			}
			if (params.containsKey("isDeleted")) {
				Object isDeleted = params.get("isDeleted");
				if (isDeleted == null) {
					hql.append(" and ").append(ename).append(".isDeleted is null");
					params.remove("isDeleted");
				} else {
					hql.append(" and ").append(ename).append(".isDeleted = :isDeleted ");
				}
			}
			if (params.containsKey("projectName")) {
				Object projectName = params.get("projectName");
				if (projectName == null) {
					hql.append(" and ").append(ename).append(".projectName is null");
					params.remove("projectName");
				} else {
					hql.append(" and ").append(ename).append(".projectName like :projectName ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findAllOrgPager(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from VixTask ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".employees e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeIds")) {
				Object employeeIds = params.get("employeeIds");
				if (employeeIds == null || "".equals(employeeIds)) {
					params.remove("employeeIds");
				} else {
					hql.append(" and e.id in (");
					String[] ivArray = employeeIds.toString().split(",");
					for (int i = 0; i < ivArray.length; i++) {
						String iv = ivArray[i];
						iv = iv.trim();
						if (null != iv && !"".equals(iv)) {
							hql.append("'");
							hql.append(iv);
							hql.append("'");
							if (i < ivArray.length - 1) {
								hql.append(",");
							}
						}
					}
					hql.append(") ");
				}
			}
			if (params.containsKey("status")) {
				Object status = params.get("status");
				if (status == null) {
					hql.append(" and ").append(ename).append(".status is null");
					params.remove("status");
				} else {
					hql.append(" and ").append(ename).append(".status = :status ");
				}
			}
			if (params.containsKey("isTemp")) {
				Object isTemp = params.get("isTemp");
				if (isTemp == null) {
					hql.append(" and ").append(ename).append(".isTemp is null");
					params.remove("isTemp");
				} else {
					hql.append(" and ").append(ename).append(".isTemp = :isTemp ");
				}
			}
			if (params.containsKey("isDeleted")) {
				Object isDeleted = params.get("isDeleted");
				if (isDeleted == null) {
					hql.append(" and ").append(ename).append(".isDeleted is null");
					params.remove("isDeleted");
				} else {
					hql.append(" and ").append(ename).append(".isDeleted = :isDeleted ");
				}
			}
			if (params.containsKey("complete")) {
				Object complete = params.get("complete");
				if (complete == null) {
					hql.append(" and ").append(ename).append(".complete is null");
					params.remove("complete");
				} else {
					hql.append(" and ").append(ename).append(".complete = :complete ");
				}
			}
			if (params.containsKey("questName")) {
				Object questName = params.get("questName");
				if (questName == null) {
					hql.append(" and ").append(ename).append(".questName is null");
					params.remove("questName");
				} else {
					hql.append(" and ").append(ename).append(".questName like :questName ");
				}
			}
			if (params.containsKey("taskStartTime")) {
				Object taskStartTime = params.get("taskStartTime");
				if (taskStartTime == null) {
					hql.append(" and ").append(ename).append(".taskStartTime is null");
					params.remove("taskStartTime");
				} else {
					hql.append(" and ").append(ename).append(".taskStartTime <= :taskStartTime ");
				}
			}
			if (params.containsKey("taskEndTime")) {
				Object taskEndTime = params.get("taskEndTime");
				if (taskEndTime == null) {
					hql.append(" and ").append(ename).append(".taskEndTime is null");
					params.remove("taskEndTime");
				} else {
					hql.append(" and ").append(ename).append(".taskEndTime >= :taskEndTime ");
				}
			}
			if (params.containsKey("notcomplete")) {
				Object notcomplete = params.get("notcomplete");
				if (notcomplete == null) {
					hql.append(" and ").append(ename).append(".complete is null");
					params.remove("notcomplete");
				} else {
					hql.append(" and ").append(ename).append(".complete != :notcomplete ");
				}
			}
			if (params.containsKey("flag")) {
				Object flag = params.get("flag");
				if (flag == null) {
					hql.append(" and ").append(ename).append(".flag is null");
					params.remove("flag");
				} else {
					hql.append(" and ").append(ename).append(".flag = :flag ");
				}
			}
		}
		if (pager != null && StringUtils.isEmpty(pager.getOrderField())) {
			hql.append(" order by ").append(ename).append(".createTime desc ");
		} else {
			buildOrderQl(ename, hql, pager);
		}
		return hql;
	}
}
