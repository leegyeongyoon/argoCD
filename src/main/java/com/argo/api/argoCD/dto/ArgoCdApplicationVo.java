package com.argo.api.argoCD.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArgoCdApplicationVo {
    private String applicationName;
    private String projectName;
    private String labels;
    private String sync;
    private String health;
    private String repositoryUrl;
    private String targetRevision;
    private String path;
    private String destination;
    private String namespace;
    private String createdAt;
    private String lastSyncAt;

}