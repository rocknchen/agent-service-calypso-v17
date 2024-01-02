package com.hthk.calypso.cas.service.impl;

import com.hthk.calypso.cas.service.CASService;
import com.hthk.calypso.cas.service.ServiceManager;
import com.hthk.fintech.exception.ServiceNotSupportedException;
import com.hthk.fintech.model.common.ServiceKey;
import com.hthk.fintech.model.web.http.ActionTypeEnum;
import com.hthk.fintech.model.web.http.HttpServiceRequest;
import com.hthk.fintech.model.web.http.RequestDateTime;
import com.hthk.fintech.model.web.http.RequestEntity;
import com.hthk.fintech.service.basic.AbstractService;
import com.hthk.fintech.utils.ServiceKeyUtils;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static com.hthk.fintech.config.FintechStaticData.LOG_DEFAULT;
import static com.hthk.fintech.config.FintechStaticData.LOG_WRAP;

/**
 * @Author: Rock CHEN
 * @Date: 2024/1/2 17:05
 */
@Service
public class ServiceManagerImpl extends AbstractService implements ServiceManager {

    private final static Logger logger = LoggerFactory.getLogger(ServiceManagerImpl.class);

    private Map<ServiceKey, CASService> serviceMap = new HashedMap();

    @PostConstruct
    public void init() {
        Collection<? extends CASService> serviceSet = appContext.getBeansOfType(CASService.class).values();
        serviceSet.stream()
                .forEach(t -> serviceMap.put(ServiceKeyUtils.build(t.getClass().getAnnotation(com.hthk.fintech.model.common.Service.class)), t));
        logger.info(LOG_WRAP, "SERVICE_MAP", serviceMap);
    }

    @Override
    public <R, P, C> R process(HttpServiceRequest<P, C> request) throws ServiceNotSupportedException {

        CASService service = getService(request);

        RequestDateTime reqDateTime = request.getDateTime();
        C criteria = request.getCriteria();
        R processResult = (R) service.process(reqDateTime, criteria);

        return processResult;
    }

    private <C, P> CASService getService(HttpServiceRequest<P, C> request) throws ServiceNotSupportedException {

        ActionTypeEnum action = request.getAction().getName();
        RequestEntity entity = request.getEntity();
        ServiceKey key = ServiceKeyUtils.build(action, entity);
        logger.info(LOG_DEFAULT, "SERVICE_KEY", key);
        CASService service = serviceMap.get(key);
        logger.info(LOG_DEFAULT, "SERVICE", service);
        return Optional.ofNullable(service).orElseThrow(() -> new ServiceNotSupportedException());
    }

}
