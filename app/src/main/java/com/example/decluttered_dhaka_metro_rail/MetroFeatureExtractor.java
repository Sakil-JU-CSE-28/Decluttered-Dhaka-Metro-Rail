package com.example.decluttered_dhaka_metro_rail;


import android.content.Context;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Helper class for extracting metro features.
 * This class manages the list of features available in the metro rail network.
 */
public class MetroFeatureExtractor {

    private Set<String> features;

    /**
     * Reads a text file from the raw resources folder and extracts unique lines into a set of strings.
     *
     * @param context    The context of the application.
     * @param resourceId The resource ID of the text file in the raw resources folder.
     * @return A set containing unique lines from the text file.
     */

    Metro metro = new Metro();
    public Set<String> getMetros(Context context, int resourceId){
        return metro.getMetros(context,resourceId);
    }

    public List<String> getComments() {
        return metro.getComments();
    }

    public void addComments(String comment) {
        List<String>comments = metro.getComments();
        comments.add(comment);
        metro.setComments(comments);
    }


}

