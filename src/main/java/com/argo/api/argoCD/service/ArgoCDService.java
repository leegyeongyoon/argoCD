package com.argo.api.argoCD.service;

import com.argo.api.argoCD.dto.*;
import com.argo.common.config.argoCD.ArgoCDFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ArgoCDService {

    @Autowired
    private ArgoCDFeignClient argoCDFeignClient;

    public String getReposotiryList() {
        Map<String, Object> response = argoCDFeignClient.selectListRepo();
        if (response == null) {
            return "false";
        }
        return response.toString();
    }

    public List<ArgoCdApplicationVo> getApplicationList() {
        ArgoCdApplicationResponseVo argoCdApplicationResponseVoList = argoCDFeignClient.selectListApplication();
        if (Objects.isNull(argoCdApplicationResponseVoList.getItems())) {
            return new ArrayList<>();
        }
        return argoCdApplicationResponseVoList.getItems().stream().map(ArgoCdApplicationVo::setApplicationVo).toList();
    }

    public ArgoCdApplicationResponseVo getApplication(String applicationName) {
        ArgoCdApplicationResponseVo argoCdApplicationResponseVo = argoCDFeignClient.selectApplication(applicationName);
        return argoCdApplicationResponseVo;
    }


    public List<ArgoCDTreeVo> getApplicationResourceTree(String applicationName) {
        ArgoCDTreeNodeResponseVo argoCDTreeNodeResponseVo = argoCDFeignClient.selectApplicationResourceTree(applicationName);
        ArgoCDTreeNodeResponseVo argoCDTreeNodeResponseVo1 = argoCDFeignClient.selectApplicationResourceTree(applicationName);
        System.out.println("argoCDTreeNodeResponseVo1 = " + argoCDTreeNodeResponseVo1);
        return ArgoCDTreeVo.createTree(argoCDTreeNodeResponseVo);
//        return List.of(new ArgoCDTreeVo());
    }

    public ArgoCdManagedResourceVo getApplicationManagedResource(String applicationName, String kind) {
        ArgoCdManagedResourceVo argoCDTreeNodeResponseVo = argoCDFeignClient.selectApplicationManagedResource(applicationName, kind);
        return argoCDTreeNodeResponseVo;
    }

    public ArgoCdApplicationVo getApplicationSummary(String applicationName) {
        ArgoCdApplicationResponseVo argoCdApplicationResponseVo = argoCDFeignClient.selectApplication(applicationName);
        return ArgoCdApplicationVo.setApplicationVo(argoCdApplicationResponseVo);
    }

    public ArgoCdNodeDetailVo getNodeDetail(String applicationName, String kind, ArgoCDAppDetailsRequestBodyVo argoCDAppDetailsRequestBodyVo) {
        return ArgoCdNodeDetailVo.setNodeDetailVo(this.getApplicationSummary(applicationName), this.getApplicationManagedResource(applicationName, kind), this.getAppdetails(argoCDAppDetailsRequestBodyVo));
    }

    public ArgoCDEventResponseVo getEvents(String applicationName, ArgoCDEventRequestBodyVo argoCDEventRequestBodyVo) {
        ArgoCDEventResponseVo eventList = argoCDFeignClient.selectEventList(applicationName, argoCDEventRequestBodyVo);
        System.out.println(eventList);
        return eventList;
    }

    public ArgoCDAppDetailsResponseVo getAppdetails(ArgoCDAppDetailsRequestBodyVo argoCDAppDetailsRequestBodyVo) {
        String repoUrl = URLEncoder.encode(URLEncoder.encode(argoCDAppDetailsRequestBodyVo.getSource().getRepoURL(), StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        ArgoCDAppDetailsResponseVo argoCDAppDetailsResponseVo = argoCDFeignClient.selectApplicationRepository(repoUrl, argoCDAppDetailsRequestBodyVo);
        System.out.println("argoCDAppDetailsResponseVo = " + argoCDAppDetailsResponseVo);
        return argoCDAppDetailsResponseVo;
    }

}
