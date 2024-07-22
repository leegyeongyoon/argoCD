package com.argo.api.argoCD.service;

import com.argo.api.argoCD.dto.ArgoCDTreeNodeResponseVo;
import com.argo.api.argoCD.dto.ArgoCDTreeVo;
import com.argo.common.config.argoCD.ArgoCDFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public String getApplicationList() {
        ResponseEntity<Map<String, Object>> response = argoCDFeignClient.selectListApplication();
        if (response == null) {
            return "false";
        }
        System.out.println(response.getBody().toString());
        return response.getBody().toString();
    }

    public String getApplication(String applicationName) {
        ResponseEntity<Map<String, Object>> response = argoCDFeignClient.selectApplication(applicationName);
        if (response == null) {
            return "false";
        }
        System.out.println(response.getBody().toString());
        return response.getBody().toString();
    }



    public List<ArgoCDTreeVo> getApplicationResourceTree(String applicationName) {
//        ArgoCDTreeNodeResponseVo argoCDTreeNodeResponseVo = argoCDFeignClient.selectApplicationResourceTree(applicationName);
        ResponseEntity<Map<String, Object>> argoCDTreeNodeResponseVo = argoCDFeignClient.selectApplicationResourceTree(applicationName);
        System.out.println("argoCDTreeNodeResponseVo = " + argoCDTreeNodeResponseVo);
        return new ArrayList<>();
//        return ArgoCDTreeVo.createTree(argoCDTreeNodeResponseVo);
    }
}
