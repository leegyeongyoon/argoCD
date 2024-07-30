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
        return argoCdApplicationResponseVoList.getItems().stream().map(argoCdApplicationResponseVo ->
                ArgoCdApplicationVo.builder()
                        .applicationName(argoCdApplicationResponseVo.getMetadata().getName())
                        .projectName(argoCdApplicationResponseVo.getSpec().getProject())
                        .sync(argoCdApplicationResponseVo.getStatus().getSync().getStatus())
                        .health(argoCdApplicationResponseVo.getStatus().getHealth().getStatus())
                        .namespace(argoCdApplicationResponseVo.getMetadata().getNamespace())
                        .repositoryUrl(argoCdApplicationResponseVo.getSpec().getSource().getRepoURL())
                        .path(argoCdApplicationResponseVo.getSpec().getSource().getPath())
                        .targetRevision(argoCdApplicationResponseVo.getSpec().getSource().getTargetRevision())
                        .destination(Objects.nonNull(argoCdApplicationResponseVo.getSpec().getDestination().getServer()) ? argoCdApplicationResponseVo.getSpec().getDestination().getServer() : argoCdApplicationResponseVo.getSpec().getDestination().getName())
                        .createdAt(argoCdApplicationResponseVo.getMetadata().getCreationTimestamp())
                        .lastSyncAt(argoCdApplicationResponseVo.getStatus().getReconciledAt())
                        .build()
        ).toList();
    }

    public ArgoCdApplicationResponseVo getApplication(String applicationName) {
        ArgoCdApplicationResponseVo argoCdApplicationResponseVo = argoCDFeignClient.selectApplication(applicationName);
        System.out.println(argoCdApplicationResponseVo);
        return argoCdApplicationResponseVo;
    }


    public List<ArgoCDTreeVo> getApplicationResourceTree(String applicationName) {
        ArgoCDTreeNodeResponseVo argoCDTreeNodeResponseVo = argoCDFeignClient.selectApplicationResourceTree(applicationName);
        ArgoCdApplicationResponseVo argoCdApplicationResponseVo = argoCDFeignClient.selectApplication(applicationName);
        return ArgoCDTreeVo.createTree(argoCDTreeNodeResponseVo);
    }
}
