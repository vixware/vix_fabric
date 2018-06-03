package com.vix.common.security.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.common.security.entity.Role;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
// @Scope("prototype")
public class RoleHqlProvider extends HqlProvider {

	// @Resource(name = "userAccountHqlProvider")
	// private UserAccountHqlProvider userAccountHqlProvider;

	@Override
	public String entityAsName() {
		return "role";
	}

	public StringBuilder findRoleList(Map<String, Object> params, Pager pager) {
		/*
		 * StringBuilder hql = generatorHql(UserAccount.class, params,
		 * orderField, orderBy,extHql,""); return hql;
		 */
		StringBuilder sb = new StringBuilder();
		String ename = entityAsName();
		sb.append("select ").append(ename);
		sb.append(" from ").append(Role.class.getName()).append(" ").append(ename);
		// sb.append(" left join fetch ").append(ename).append(".roles role");
		// sb.append(" left join fetch userAccount.userGroups ug left join fetch
		// ug.roles role2 left join fetch role2.authoritys ");
		// sb.append(" left join fetch role.authoritys au");
		// sb.append(" left join fetch au.resources ");
		sb.append(" where 1=1 ");
		sb.append(" and ").append(ename).append(".roleCode != :roleCode ");
		params.put("roleCode", BizConstant.ROLE_SUPERADMIN);

		if (params != null) {
			if (params.containsKey("roleName")) {
				sb.append("  and  ").append(ename).append(".name like :roleName ");
			}
			if (params.containsKey("roleType")) {
				sb.append("  and  ").append(ename).append(".roleType = :roleType ");
			}
		}

		buildOrderQl(ename, sb, pager);
		return sb;
	}

	/**
	 * @Title: findSelectRoleForUser @Description:
	 *         查询某用户的角色选择，不能包含已经具有的角色 @param @param userId @param @param
	 *         params @param @param orderField @param @param
	 *         orderBy @param @param extHql @param @return 设定文件 @return
	 *         StringBuilder 返回类型 @throws
	 */
	public StringBuilder findSelectRoleForUser(Long userId, Map<String, Object> params, String orderField, String orderBy) {
		String entityAsName = entityAsName();
		UserAccountHqlProvider userAccountHqlProvider = new UserAccountHqlProvider();
		String userAccountAsName = userAccountHqlProvider.entityAsName();
		// StringBuilder hqlBuilder =new StringBuilder();

		StringBuilder joinHql = new StringBuilder();
		joinHql.append(" inner join ").append(entityAsName).append(".userAccounts ").append(userAccountAsName);

		StringBuilder extHql = new StringBuilder();
		extHql.append(" and ").append(userAccountAsName).append(".id <> :userId ");

		StringBuilder queryHql = generatorHql(Role.class, params, orderField, orderBy, extHql.toString(), joinHql.toString());

		// 如果需要自定义参数 必须generatorHql 后面添加
		params.put("userId," + SearchCondition.NOEQUAL, userId);
		// System.out.println("before:"+queryHql);
		return queryHql;
	}

}
