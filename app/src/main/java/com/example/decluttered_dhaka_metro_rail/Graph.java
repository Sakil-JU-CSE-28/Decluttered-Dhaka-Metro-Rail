package com.example.decluttered_dhaka_metro_rail;

import java.util.HashSet;
import java.util.Set;

/**
 * for creating graph used in dijkstra algo
 */
public class Graph {
    private Set<Station> nodes = new HashSet<>();

    public void addNode(Station nodeA) {
        nodes.add(nodeA);
    }

    public Set<Station> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Station> nodes) {
        this.nodes = nodes;
    }
}
