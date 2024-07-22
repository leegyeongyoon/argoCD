package com.argo.api.argoCD.service;

import com.argo.api.argoCD.dto.ArgoCDTreeNodeResponseVo;
import com.argo.api.argoCD.dto.ArgoCDTreeVo;
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
        String reposotiryList = argoCDService.getApplicationList();
        System.out.println(reposotiryList);
    }

    @Test
    void getApplication() {
        String reposotiryList = argoCDService.getApplication("nginx");
        System.out.println(reposotiryList);
    }


    @Test
    void getApplicationResourceTree() {
        List<ArgoCDTreeVo> applicationResourceTree = argoCDService.getApplicationResourceTree("nginx");
        System.out.println(applicationResourceTree);
    }
}
