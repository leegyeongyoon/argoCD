package com.argo.api.argoCD.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ArgoCdApplicationResponseVo {
    private Metadata metadata;
    private Spec spec;
    private Status status;
    private List<ArgoCdApplicationResponseVo> items;

    @Getter
    @Setter
    public static class Metadata {
        private String name;
        private String namespace;
        private String uid;
        private String resourceVersion;
        private int generation;
        private String creationTimestamp;
        private Map<String, String> annotations;
        private List<ManagedFields> managedFields;
    }

    @Getter
    @Setter
    public static class ManagedFields {
        private String manager;
        private String operation;
        private String apiVersion;
        private String time;
        private String fieldsType;
        private FieldsV1 fieldsV1;

        @Getter
        @Setter
        public static class FieldsV1 {
            private MetadataFields metadata;
            private SpecFields spec;
            private StatusFields status;

            @Getter
            @Setter
            public static class MetadataFields {
                private Map<String, AnnotationFields> annotations;

                @Getter
                @Setter
                public static class AnnotationFields {
                    private Map<String, Object> fields;
                }
            }

            @Getter
            @Setter
            public static class SpecFields {
                private Spec.Destination destination;
                private String project;
                private Source source;
                private SyncPolicy syncPolicy;


                @Getter
                @Setter
                public static class Source {
                    private String path;
                    private String repoURL;
                    private String targetRevision;
                }

                @Getter
                @Setter
                public static class SyncPolicy {
                    private Automated automated;

                    @Getter
                    @Setter
                    public static class Automated {
                        private boolean prune;
                    }
                }
            }

            @Getter
            @Setter
            public static class StatusFields {
                private Health health;
                private List<History> history;
                private OperationState operationState;
                private String reconciledAt;
                private List<Status.Resource> resources;
                private String sourceType;
                private Summary summary;
                private Sync sync;

                @Getter
                @Setter
                public static class Health {
                    private String status;
                }

                @Getter
                @Setter
                public static class History {
                    private String revision;
                    private String deployedAt;
                    private int id;
                    private Spec.Source source;
                    private String deployStartedAt;
                }

                @Getter
                @Setter
                public static class OperationState {
                    private Operation operation;
                    private String phase;
                    private String message;
                    private SyncResult syncResult;
                    private String startedAt;
                    private String finishedAt;

                    @Getter
                    @Setter
                    public static class Operation {
                        private Sync sync;
                        private InitiatedBy initiatedBy;

                        @Getter
                        @Setter
                        public static class Sync {
                            private String revision;
                            private SyncStrategy syncStrategy;

                            @Getter
                            @Setter
                            public static class SyncStrategy {
                                private Hook hook;

                                @Getter
                                @Setter
                                public static class Hook {
                                    private Boolean force;
                                }
                            }
                        }

                        @Getter
                        @Setter
                        public static class InitiatedBy {
                            private String username;
                        }
                    }

                    @Getter
                    @Setter
                    public static class SyncResult {
                        private List<Resource> resources;
                        private String revision;
                        private Spec.Source source;

                        @Getter
                        @Setter
                        public static class Resource {
                            private String group;
                            private String version;
                            private String kind;
                            private String namespace;
                            private String name;
                            private String status;
                            private String message;
                            private String hookPhase;
                            private String syncPhase;
                        }
                    }
                }

                @Getter
                @Setter
                public static class Summary {
                    private List<String> images;
                }

                @Getter
                @Setter
                public static class Sync {
                    private String status;
                    private ComparedTo comparedTo;
                    private String revision;

                    @Getter
                    @Setter
                    public static class ComparedTo {
                        private Spec.Source source;
                        private Spec.Destination destination;
                    }
                }
            }
        }
    }

    @Getter
    @Setter
    public static class Spec {
        private String project;
        private Source source;
        private Destination destination;
        private SyncPolicy syncPolicy;

        @Getter
        @Setter
        public static class Source {
            private String repoURL;
            private String path;
            private String targetRevision;
        }

        @Getter
        @Setter
        public static class Destination {
            private String namespace;
            private String name;
            private String server;
        }

        @Getter
        @Setter
        public static class SyncPolicy {
            private Automated automated;

            @Getter
            @Setter
            public static class Automated {
                private boolean prune;
            }
        }
    }

    @Getter
    @Setter
    public static class Status {
        private List<Resource> resources;
        private Sync sync;
        private Health health;
        private List<History> history;
        private String reconciledAt;
        private OperationState operationState;
        private String sourceType;
        private Summary summary;

        @Getter
        @Setter
        public static class Resource {
            private String version;
            private String kind;
            private String namespace;
            private String name;
            private String status;
            private Health health;

            @Getter
            @Setter
            public static class Health {
                private String status;
            }
        }

        @Getter
        @Setter
        public static class Sync {
            private String status;
            private ComparedTo comparedTo;
            private String revision;

            @Getter
            @Setter
            public static class ComparedTo {
                private Spec.Source source;
                private Spec.Destination destination;
            }
        }

        @Getter
        @Setter
        public static class Health {
            private String status;
        }

        @Getter
        @Setter
        public static class History {
            private String revision;
            private String deployedAt;
            private int id;
            private Spec.Source source;
            private String deployStartedAt;
        }

        @Getter
        @Setter
        public static class OperationState {
            private Operation operation;
            private String phase;
            private String message;
            private SyncResult syncResult;
            private String startedAt;
            private String finishedAt;

            @Getter
            @Setter
            public static class Operation {
                private Sync sync;
                private InitiatedBy initiatedBy;

                @Getter
                @Setter
                public static class Sync {
                    private String revision;
                    private SyncStrategy syncStrategy;

                    @Getter
                    @Setter
                    public static class SyncStrategy {
                        private Hook hook;

                        @Getter
                        @Setter
                        public static class Hook {
                            private Boolean force;
                        }
                    }
                }

                @Getter
                @Setter
                public static class InitiatedBy {
                    private String username;
                }


            }

            @Getter
            @Setter
            public static class SyncResult {
                private List<Resource> resources;
                private String revision;
                private Spec.Source source;

                @Getter
                @Setter
                public static class Resource {
                    private String group;
                    private String version;
                    private String kind;
                    private String namespace;
                    private String name;
                    private String status;
                    private String message;
                    private String hookPhase;
                    private String syncPhase;
                }
            }
        }

        @Getter
        @Setter
        public static class Summary {
            private List<String> images;
        }
    }
}