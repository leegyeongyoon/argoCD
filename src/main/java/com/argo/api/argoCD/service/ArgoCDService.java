package com.argo.api.argoCD.service;

import com.argo.api.argoCD.dto.ArgoCDTreeNodeResponseVo;
import com.argo.api.argoCD.dto.ArgoCDTreeVo;
import com.argo.api.argoCD.dto.ArgoCdApplicationResponseVo;
import com.argo.api.argoCD.dto.ArgoCdApplicationVo;
import com.argo.common.config.argoCD.ArgoCDFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        System.out.println(argoCdApplicationResponseVo);
        return argoCdApplicationResponseVo;
    }


    public List<ArgoCDTreeVo> getApplicationResourceTree(String applicationName) {
        ArgoCDTreeNodeResponseVo argoCDTreeNodeResponseVo = argoCDFeignClient.selectApplicationResourceTree(applicationName);
        return ArgoCDTreeVo.createTree(argoCDTreeNodeResponseVo);
    }

    public Map<String, Object> getApplicationManagedResource(String applicationName, String kind) {
        Map<String, Object> argoCDTreeNodeResponseVo = argoCDFeignClient.selectApplicationManagedResource(applicationName, kind);
        System.out.println("argoCDTreeNodeResponseVo = " + argoCDTreeNodeResponseVo);
        return argoCDTreeNodeResponseVo;
    }

    public ArgoCdApplicationVo getApplicationDetailSummary(String applicationName) {
        ArgoCdApplicationResponseVo argoCdApplicationResponseVo = argoCDFeignClient.selectApplication(applicationName);
        System.out.println("argoCDTreeNodeResponseVo = " + argoCdApplicationResponseVo);
        return ArgoCdApplicationVo.setApplicationVo(argoCdApplicationResponseVo);
    }
}
