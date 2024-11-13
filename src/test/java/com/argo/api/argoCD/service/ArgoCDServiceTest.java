package com.argo.api.argoCD.service;

import com.argo.api.argoCD.dto.*;
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
        ArgoCdApplicationResponseVo reposotiryList = argoCDService.getApplication("baton-ao-collector-dev");
        System.out.println(reposotiryList);
    }


    @Test
    void getApplicationResourceTree() {
        List<ArgoCDTreeVo> applicationResourceTree = argoCDService.getApplicationResourceTree("baton-ao-collector-dev");
        System.out.println(applicationResourceTree);
    }

    @Test
    void getApplicationManagedResource() {
        ArgoCdManagedResourceVo applicationResourceTree = argoCDService.getApplicationManagedResource("dev-plot-admin-api","Deploy");
        System.out.println(applicationResourceTree);
    }

    @Test
    void getAppdetails() {
        ArgoCDAppDetailsRequestBodyVo argoCDAppDetailsRequestBodyVo = new ArgoCDAppDetailsRequestBodyVo(
                new ArgoCDAppDetailsRequestBodyVo.Source(
                        "https://github.com/leegyeongyoon/plotting-gitops.git",
                        "dev/charts/plot-admin-api", "main"),
                "dev-plot-admin-api", "default","");
        ArgoCDAppDetailsResponseVo applicationResourceTree = argoCDService.getAppdetails(argoCDAppDetailsRequestBodyVo);
        System.out.println(applicationResourceTree);
    }

    @Test
    void getEvents(){
        ArgoCDEventResponseVo eventList = argoCDService.getEvents("baton-ao-collector-dev", ArgoCDEventRequestBodyVo.builder()
                .resourceUID("e4194d1d-905d-4174-a930-38003200c727")
                .resourceNamespace("")
                .resourceName("baton-ao-collector-7ccc6cbf5b")
                .appNamespace("argo").build());
        System.out.println(eventList);
    }
}
