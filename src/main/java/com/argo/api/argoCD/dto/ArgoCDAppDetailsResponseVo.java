package com.argo.api.argoCD.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ArgoCDAppDetailsResponseVo {
    private Source source;
    private String appName;
    private String appProject;
    private HelmConfig helm;

    // Source 클래스
    @Getter
    @Setter
    public static class Source {
        private String repoURL;
        private String path;
        private String targetRevision;
    }

    // HelmConfig 클래스
    @Getter
    @Setter
    public static class HelmConfig {
        private String[] valueFiles;
        private List<Parameter> parameters;
        private String values;
    }

    // Parameter 클래스
    @Getter
    @Setter
    public static class Parameter {
        private String name;
        private String value;
    }
}
