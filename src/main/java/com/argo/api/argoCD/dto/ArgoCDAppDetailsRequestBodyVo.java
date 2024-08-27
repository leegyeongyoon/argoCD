package com.argo.api.argoCD.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ArgoCDAppDetailsRequestBodyVo {
    private Source source;
    private String appName;
    private String appProject;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Source {
        private String repoURL;
        private String path;
        private String targetRevision;
    }
}
