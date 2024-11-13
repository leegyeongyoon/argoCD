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
    private List<Host> hosts;
    private List<Node> nodes;
    private List<Node> orphanedNodes;


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
        private long capacity;
        private long requestedByApp;
        private long requestedByNeighbors;
        private String resourceName;
    }

    @Getter
    @Setter
    public static class SystemInfo {
        private String architecture;
        private String bootID;
        private String containerRuntimeVersion;
        private String kernelVersion;
        private String kubeProxyVersion;
        private String kubeletVersion;
        private String machineID;
        private String operatingSystem;
        private String osImage;
        private String systemUUID;
    }

    @Getter
    @Setter
    public static class Node {
        private String createdAt;
        private Health health;
        private List<String> images;
        private List<Info> info;
        private NetworkingInfo networkingInfo;
        private List<ParentRef> parentRefs;
        private String resourceVersion;
        private String group;
        private String kind;
        private String name;
        private String namespace;
        private String uid;
        private String version;
    }

    @Getter
    @Setter
    public static class Health {
        private String message;
        private String status;
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
        private List<String> externalURLs;
        private List<Ingress> ingress;
        private Map<String, String> labels;
        private Map<String, String> targetLabels;
        private List<TargetRef> targetRefs;
    }

    @Getter
    @Setter
    public static class Ingress {
        private String hostname;
        private String ip;
        private String ipMode;
        private List<Port> ports;
    }

    @Getter
    @Setter
    public static class Port {
        private String error;
        private int port;
        private String protocol;
    }

    @Getter
    @Setter
    public static class TargetRef {
        private String group;
        private String kind;
        private String name;
        private String namespace;
        private String uid;
        private String version;
    }

    @Getter
    @Setter
    public static class ParentRef {
        private String group;
        private String kind;
        private String name;
        private String namespace;
        private String uid;
        private String version;
    }
}