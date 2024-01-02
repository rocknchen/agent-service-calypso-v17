package com.hthk.calypso.cas.service;

import com.hthk.fintech.exception.ServiceNotSupportedException;
import com.hthk.fintech.model.web.http.HttpServiceRequest;

/**
 * @Author: Rock CHEN
 * @Date: 2024/1/2 17:05
 */
public interface ServiceManager {


    <R, P, C> R process(HttpServiceRequest<P,C> request) throws ServiceNotSupportedException;

}
