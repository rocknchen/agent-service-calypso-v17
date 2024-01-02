package com.hthk.calypso.cas.service;

import com.hthk.fintech.model.web.http.RequestDateTime;

/**
 * @Author: Rock CHEN
 * @Date: 2024/1/2 17:12
 */
public interface CASService {

    <R, C> R process(RequestDateTime reqDateTime, C criteria);

}
