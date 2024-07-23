package com.argo.api.argoCD.service;

import com.argo.api.argoCD.dto.ArgoCDTreeVo;
import com.argo.api.argoCD.dto.ArgoCdApplicationResponseVo;
import com.argo.api.argoCD.dto.ArgoCdApplicationVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ArgoCDServiceTest {
    @Autowired
    ArgoCDService argoCDService;

    @Test
    void getReposotiryList() {
        String reposotiryList = argoCDService.getReposotiryList();
        System.out.println(reposotiryList);
    }

    @Test
    void getApplicationList() {
        List<ArgoCdApplicationVo> reposotiryList = argoCDService.getApplicationList();
        System.out.println(reposotiryList);
    }

    @Test
    void getApplication() {
        ArgoCdApplicationResponseVo reposotiryList = argoCDService.getApplication("nginx");
        System.out.println(reposotiryList);
    }


    @Test
    void getApplicationResourceTree() {
        List<ArgoCDTreeVo> applicationResourceTree = argoCDService.getApplicationResourceTree("nginx");
        System.out.println(applicationResourceTree);
    }
}
