package com.aasys.sts.web;

import com.aasys.sts.shared.core.Cuisines;
import com.aasys.sts.shared.core.Tastes;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * Created by aasys on 2/26/2017.
 */
@RemoteServiceRelativePath("cuisines")
public interface CuisinesService extends RemoteService {
    List<Cuisines> getCuisines() throws Exception;

    List<Tastes> getTastes() throws Exception;
}
