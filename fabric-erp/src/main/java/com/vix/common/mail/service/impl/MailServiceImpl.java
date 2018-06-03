/**
 * 
 */
package com.vix.common.mail.service.impl;

import org.springframework.stereotype.Service;

import com.vix.common.mail.service.IMailService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("mailService")
public class MailServiceImpl extends BaseHibernateServiceImpl implements IMailService {
	private static final long serialVersionUID = 1L;
}
