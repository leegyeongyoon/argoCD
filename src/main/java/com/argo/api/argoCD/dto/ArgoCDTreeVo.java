package com.argo.api.argoCD.dto;

import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArgoCDTreeVo {
    private String name;
    private ArgoCDNode value;
    private String createdDate;
    private List<ArgoCDTreeVo> children;

    public void addChild(ArgoCDTreeVo argoCDTreeVo) {
        this.children.add(argoCDTreeVo);
    }

    @Getter
    @Setter
    @Builder
    public static class ArgoCDNode {
        private String status;
        private String name;
        private String kind;
        private String revision;
    }

    private static ArgoCDTreeVo createNode(ArgoCDTreeNodeResponseVo.Node node) {
        ArgoCDNode argoCDTreeNodeValue = ArgoCDNode.builder()
                .status(Objects.nonNull(node.getHealth()) ? node.getHealth().getStatus() : "")
                .kind(node.getKind())
                .name(node.getName())
                .revision(getRevisionValue(node)).build();
        return new ArgoCDTreeVo(node.getUid(), argoCDTreeNodeValue, node.getCreatedAt(), new ArrayList<>());

    }

    private static String getRevisionValue(ArgoCDTreeNodeResponseVo.Node node) {
        return Optional.ofNullable(node.getInfo())
                .orElseGet(ArrayList::new)
                .stream()
                .filter(info -> "Revision".equals(info.getName()))
                .map(ArgoCDTreeNodeResponseVo.Info::getValue)
                .findFirst()
                .orElse("");
    }


    public static List<ArgoCDTreeVo> createTree(ArgoCDTreeNodeResponseVo argoCDTreeNodeResponseVo) {
        List<ArgoCDTreeNodeResponseVo.Node> nodeList = argoCDTreeNodeResponseVo.getNodes();
        List<String> rootNodeUidList = new ArrayList<>();

        Map<String, ArgoCDTreeVo> argoCDTreeMap = nodeList.stream()
                .filter(node -> Objects.nonNull(node.getUid()))
                .collect(Collectors.toMap(ArgoCDTreeNodeResponseVo.Node::getUid, ArgoCDTreeVo::createNode));

        if (argoCDTreeMap.isEmpty()) {
            return null;
        }
        nodeList.forEach(node -> {
            ArgoCDTreeVo treeNode = argoCDTreeMap.get(node.getUid());

            if (Objects.isNull(node.getParentRefs())) {
                rootNodeUidList.add(node.getUid());
            } else {
                node.getParentRefs().forEach(parentRef -> {
                    ArgoCDTreeVo parentTreeNode = argoCDTreeMap.get(parentRef.getUid());
                    if (Objects.nonNull(parentTreeNode)) parentTreeNode.addChild(treeNode);
                });
            }
        });
        return rootNodeUidList.stream().map(argoCDTreeMap::get).toList();
    }
}
