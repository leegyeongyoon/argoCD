package com.argo.api.argoCD.service;

import com.argo.api.argoCD.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

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
        ArgoCdApplicationResponseVo reposotiryList = argoCDService.getApplication("dev-plot-admin-api");
        System.out.println(reposotiryList);
    }


    @Test
    void getApplicationResourceTree() {
        List<ArgoCDTreeVo> applicationResourceTree = argoCDService.getApplicationResourceTree("nginx");
        System.out.println(applicationResourceTree);
    }

    @Test
    void getApplicationManagedResource() {
        ArgoCdManagedResourceVo applicationResourceTree = argoCDService.getApplicationManagedResource("dev-plot-admin-api","Deployment");
        System.out.println(applicationResourceTree);
    }

    @Test
    void getAppdetails() {
        ArgoCDAppDetailsRequestBodyVo argoCDAppDetailsRequestBodyVo = new ArgoCDAppDetailsRequestBodyVo(new ArgoCDAppDetailsRequestBodyVo.Source("https://github.com/leegyeongyoon/plotting-gitops.git", "dev/charts/plot-admin-api", "main"), "dev-plot-admin-api", "default");
        ArgoCDAppDetailsResponseVo applicationResourceTree = argoCDService.getAppdetails(argoCDAppDetailsRequestBodyVo);
        System.out.println(applicationResourceTree);
    }
}
