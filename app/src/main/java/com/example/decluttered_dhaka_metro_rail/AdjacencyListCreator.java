package com.example.decluttered_dhaka_metro_rail;

import android.content.res.Resources;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

/**
 * for creating adjacency list used in dijkstra
 */
public class AdjacencyListCreator {

    /**
     * creating a hasmap to store adjacency list
     */
    Map<Station, Vector<Station>>adjacencyList = new HashMap<>();

    /**
     * this resources is used to retrive txt file
     * to access the connection list of stations
     * stored in stations.tx file under raw directory
     */
     Resources resources;

    /**
     * this method return the adjacency list
     * @param stationDetector this is used for tracking station that are
     *                        used in the working activity(i.e store corresponding
     *                        station object for each station)
     * @return
     */
    public  Map<Station, Vector<Station>> getAdjacencyList( Map<String,Station>stationDetector){
        /**
         * retrive the txt file to get connection of the stations
         */
        InputStream inputStream = resources.openRawResource(R.raw.stations);
        Scanner scanner = new Scanner(inputStream);
        String cur1 = "",cur2 = "";

        while (scanner.hasNext()) {
            cur1 = "";
            cur2 = "";
            String token = scanner.next(); // Tokenizing by words
            for(int i = 0;i < token.length();i++){
                if(token.charAt(i) == '-'){
                    cur1 = cur2;
                    cur2 = "";
                }
                else
                    cur2 += token.charAt(i);
            }

            /**
             * creating vector if the station adjacency list is empty
             * otherwise add the destination
             */

            Station source = stationDetector.get(cur1);
            Station destination = stationDetector.get(cur2);

            if(adjacencyList.containsKey(source)){
                adjacencyList.get(source).add(destination);
            }
            else{
                Vector<Station>nextNode = new Vector<>();
                nextNode.add(destination);
                nextNode.add(destination);
                adjacencyList.put(source,nextNode);
            }
            if(adjacencyList.containsKey(destination)){
                adjacencyList.get(destination).add(source);
            }
            else{
                Vector<Station>nextNode = new Vector<>();
                nextNode.add(destination);
                nextNode.add(destination);
                adjacencyList.put(destination,nextNode);
            }
        }
        scanner.close();
        return adjacencyList;
    }
}
