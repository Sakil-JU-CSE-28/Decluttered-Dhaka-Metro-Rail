package com.example.decluttered_dhaka_metro_rail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * for storing information of the station
 * that is used when dijkstra algo run
 */

public class Station {
    private String name;
    private List<String>comments;
    private List<Station> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<Station, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Station destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Station(String name) {
        this.name = name;
    }

    public List<Station> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Station> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Station, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Station, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
    public void addComment(String txt){
        comments.add(txt);
    }
}
