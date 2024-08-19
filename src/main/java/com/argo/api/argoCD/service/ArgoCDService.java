package com.argo.api.argoCD.service;

import com.argo.api.argoCD.dto.*;
import com.argo.common.config.argoCD.ArgoCDFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

    public ArgoCdManagedResourceVo getApplicationManagedResource(String applicationName, String kind) {
        ArgoCdManagedResourceVo argoCDTreeNodeResponseVo = argoCDFeignClient.selectApplicationManagedResource(applicationName, kind);
        System.out.println("argoCDTreeNodeResponseVo = " + argoCDTreeNodeResponseVo);
        return argoCDTreeNodeResponseVo;
    }

    public ArgoCdApplicationVo getApplicationSummary(String applicationName) {
        ArgoCdApplicationResponseVo argoCdApplicationResponseVo = argoCDFeignClient.selectApplication(applicationName);
        return ArgoCdApplicationVo.setApplicationVo(argoCdApplicationResponseVo);
    }

    public ArgoCdNodeDetailVo getNodeDetail(String applicationName, String kind) {
        return ArgoCdNodeDetailVo.setNodeDetailVo(this.getApplicationSummary(applicationName), this.getApplicationManagedResource(applicationName, kind));
    }

    public String getAppdetails()  {
        String repoUrl = "https://github.com/leegyeongyoon/plotting-gitops.git";
        String encode = URLEncoder.encode(repoUrl, StandardCharsets.UTF_8);
//        String repoUrl = "https%3A%2F%2Fgithub.com%2Fleegyeongyoon%2Fplotting-gitops.git";
//        https://3.35.223.19:32214/api/v1/repositories/https%3A%2F%2Fgithub.com%2Fleegyeongyoon%2Fplotting-gitops.git/appdetails

        System.out.println("repoUrl = " + encode);
        return argoCDFeignClient.selectApplicationRepository(encode);
    }
}
