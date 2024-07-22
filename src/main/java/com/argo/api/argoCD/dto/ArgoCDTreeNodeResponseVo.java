package com.argo.api.argoCD.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class ArgoCDTreeNodeResponseVo {
    private List<Node> nodes;
    private List<Host> hosts;

    @Getter
    @Setter
    public static class Node {
        private String version;
        private String kind;
        private String namespace;
        private String name;
        private String uid;
        private List<ParentRef> parentRefs;
        private String resourceVersion;
        private String createdAt;
        private List<Info> info;
        private NetworkingInfo networkingInfo;
        private List<String> images;
        private Health health;
    }

    @Getter
    @Setter
    public static class ParentRef {
        private String group;
        private String kind;
        private String namespace;
        private String name;
        private String uid;

    }

    @Getter
    @Setter
    public static class Info {
        private String name;
        private String value;
    }

    @Getter
    @Setter
    public static class NetworkingInfo {
        private Map<String, String> labels;
        private Map<String, String> targetLabels;
    }


    @Getter
    @Setter
    public static class Health {
        private String status;
    }

    @Getter
    @Setter
    public static class Host {
        private String name;
        private List<ResourceInfo> resourcesInfo;
        private SystemInfo systemInfo;

    }

    @Getter
    @Setter
    public static class ResourceInfo {
        private String resourceName;
        private long requestedByNeighbors;
        private long capacity;

    }

    @Getter
    @Setter
    public static class SystemInfo {
        private String machineID;
        private String systemUUID;
        private String bootID;
        private String kernelVersion;
        private String osImage;
        private String containerRuntimeVersion;
        private String kubeletVersion;
        private String kubeProxyVersion;
        private String operatingSystem;
        private String architecture;

    }
}
