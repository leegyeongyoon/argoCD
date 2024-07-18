package com.argo.api.argoCD.service;

import com.argo.common.config.argoCD.ArgoCDFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ArgoCDService {

    @Autowired
    private  ArgoCDFeignClient argoCDFeignClient;
    public String getReposotiryList(){
        ResponseEntity<Map<String, Object>> response = argoCDFeignClient.selectListRepo();
        if(response == null) {
            return "false";
        }
        System.out.println(response.getBody().toString());
        return response.getBody().toString();
    }

    public String getApplicationList(){
        ResponseEntity<Map<String, Object>> response = argoCDFeignClient.selectListApplication();
        if(response == null) {
            return "false";
        }
        System.out.println(response.getBody().toString());
        return response.getBody().toString();
    }
    public String getApplicationResourceTree(String applicationName){
        ResponseEntity<Map<String, Object>> response = argoCDFeignClient.selectApplicationResourceTree(applicationName);
        if(response == null) {
            return "false";
        }
        System.out.println(response.getBody().toString());
        return response.getBody().toString();
    }
}
