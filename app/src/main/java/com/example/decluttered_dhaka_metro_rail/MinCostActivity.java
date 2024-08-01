package com.example.decluttered_dhaka_metro_rail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public class MinCostActivity extends AppCompatActivity {

    Resources resources;

    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *     being shut down then this Bundle contains the data it most recently supplied in
     *     onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    String sourceStation,destinationStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_cost);

        /**
         * when the button pressed on the interface
         * then this module work
         * 1. Find the selected spinner item
         * 2. calculate min cost using dijkstra algo
         * 3. show the cost and staions
         */

        Button btnMinCost = findViewById(R.id.btnViewMinCost);
        Spinner sourceStationSelector = findViewById(R.id.spinner1);
        Spinner destStationSelector = findViewById(R.id.spinner2);

        /**
         * Sets up the item selection listener for the source station spinner.
         * The selected source station is assigned to the sourceStation variable.
         *
         * @param sourceStationSelector The source station spinner
         */
        sourceStationSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceStation = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sourceStation = "";
            }
        });
        /**
         * Sets up the item selection listener for the destination station spinner.
         * The selected destination station is assigned to the destinationStation variable.
         *
         * @param destStationSelector The destination station spinner
         */
        destStationSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destinationStation = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                destinationStation = "";
            }
        });

        /**
         * Sets up the click listener for the "Minimum Cost" button.
         * This method defines the behavior when the button is clicked.
         *
         * @param btnMinCost The "Minimum Cost" button
         */
        btnMinCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //create map for keep track of stationName with station
                Map<String,Station>stationDetector = new HashMap<>();
                // to store stations
                Set<Station>stations = new HashSet<>();
                // retrive stations from txt file using model class
                StationExtractor stationExtractor = new StationExtractor(resources);
                Set<String>stationsName = stationExtractor.getStations();
                // initialize stationdetector and stations
                for(String stationname : stationsName){
                    Station station = new Station();
                    stationDetector.put(stationname,station);
                    stations.add(station);
                }
                Toast.makeText(MinCostActivity.this, "Btn clicked!!", Toast.LENGTH_SHORT).show();
                // create adjacency list
                AdjacencyListCreator adjacencyListCreator = new AdjacencyListCreator();
                Map<Station, Vector<Station>>adjacencyList = adjacencyListCreator.getAdjacencyList(stationDetector);
                for(Station startStation : stations){
                    for(Station endStation : adjacencyList.get(startStation)){
                        startStation.addDestination(endStation,getRandomTime());
                    }
                }
                // create a graph
                Graph graph = new Graph();
                // add station to graph
                for(Station station : stations){
                    graph.addNode(station);
                }
                // calculate shortest path
                graph = calculateShortestPathFromSource(graph,stationDetector.get(sourceStation));
//
                StringBuilder stationList = new StringBuilder();
                for(Station station : graph.getNodes()){
                    stationDetector.put(station.getName(),station);
                }

                for(Station station : stationDetector.get(destinationStation).getShortestPath()){
                    stationList.append(station.getName() + "\n");
                }

                String minDist = "Min distance = ";
                minDist += stationDetector.get(destinationStation).getDistance().toString();
                stationList.append(minDist);

                TextView textView = findViewById(R.id.belowCardTextView);
                textView.setText(minDist);

            }

        });
    }

    /**
     * Calculates the shortest path from a given source station in the graph.
     *
     * @param graph  The graph containing stations and their connections.
     * @param source The starting station for calculating the shortest path.
     * @return The graph with updated shortest paths from the source station.
     */

    public static Graph calculateShortestPathFromSource(Graph graph, Station source) {
        source.setDistance(0);

        Set<Station> settledNodes = new HashSet<>();
        Set<Station> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Station currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< Station, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Station adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }
    /**
     * Returns the node with the lowest distance from a set of unsettled nodes.
     *
     * @param unsettledNodes The set of nodes whose distances are to be evaluated.
     * @return The node with the lowest distance among the unsettled nodes.
     */
    public static Station getLowestDistanceNode(Set < Station > unsettledNodes) {
        Station lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Station node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    /**
     * Calculates the minimum distance for an evaluation node from the source node.
     *
     * @param evaluationNode The node whose distance is being evaluated.
     * @param edgeWeight     The weight of the edge connecting the evaluation node with the source node.
     * @param sourceNode     The source node from which the evaluation node is being evaluated.
     */
    public static void CalculateMinimumDistance(Station evaluationNode,
                                                Integer edgeWeigh, Station sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Station> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    /**
     * for getting random time
     * @return
     */

    public  int getRandomTime(){
        int min = 10;
        int max = 50;
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        return randomNumber;
    }
}