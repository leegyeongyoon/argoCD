package com.argo.api.argoCD.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ArgoCDAppDetailsResponseVo {
    private Object directory;
    private Helm helm;
    private Kustomize kustomize;
    private Plugin plugin;
    private String type;


    @Getter
    @Setter
    public static class Helm {
        private List<FileParameter> fileParameters;
        private String name;
        private List<Parameter> parameters;
        private List<String> valueFiles;
        private String values;
    }

    @Getter
    @Setter
    public static class FileParameter {
        private String name;
        private String path;
    }

    @Getter
    @Setter
    public static class Parameter {
        private boolean forceString;
        private String name;
        private String value;
    }

    @Getter
    @Setter
    public static class Kustomize {
        private List<String> images;
    }

    @Getter
    @Setter
    public static class Plugin {
        private List<ParametersAnnouncement> parametersAnnouncement;
    }

    @Getter
    @Setter
    public static class ParametersAnnouncement {
        private List<String> array;
        private String collectionType;
        private String itemType;
        private Map<String, String> map;
        private String name;
        private boolean required;
        private String string;
        private String title;
        private String tooltip;
    }
}
