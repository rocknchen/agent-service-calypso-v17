package com.hthk.calypso.cas.service.impl;

import com.hthk.calypso.cas.service.CASService;
import com.hthk.calypso.cas.service.ServiceManager;
import com.hthk.fintech.exception.ServiceNotSupportedException;
import com.hthk.fintech.model.web.http.HttpServiceRequest;
import com.hthk.fintech.model.web.http.RequestDateTime;
import org.springframework.stereotype.Service;

/**
 * @Author: Rock CHEN
 * @Date: 2024/1/2 17:05
 */
@Service
public class ServiceManagerImpl implements ServiceManager {

    @Override
    public <R, P, C> R process(HttpServiceRequest<P, C> request) throws ServiceNotSupportedException {

        CASService service = getService(request);

        RequestDateTime reqDateTime = request.getDateTime();
        C criteria = request.getCriteria();
        R processResult = service.process(reqDateTime, criteria);

        return processResult;
    }

    private <C, P> CASService getService(HttpServiceRequest<P, C> request) throws ServiceNotSupportedException {

        throw new ServiceNotSupportedException("not support ya");
    }

}
