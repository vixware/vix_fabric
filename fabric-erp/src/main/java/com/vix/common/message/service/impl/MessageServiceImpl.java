package com.vix.common.message.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.message.service.IMessageService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;

@Service("messageService")
@Transactional
public class MessageServiceImpl  extends BaseHibernateServiceImpl implements IMessageService{

}
