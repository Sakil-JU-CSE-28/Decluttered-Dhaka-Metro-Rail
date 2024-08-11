package com.example.decluttered_dhaka_metro_rail;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * model class for metro
 */
public class Metro {
    private String name;
    private List<String> comments = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    /**
     * Reads a text file from the raw resources folder and extracts unique lines into a set of strings.
     *
     * @param context    The context of the application.
     * @param resourceId The resource ID of the text file in the raw resources folder.
     * @return A set containing unique lines from the text file.
     */
    public Set<String> getMetros(Context context,int resourceId){
        Set<String> lines = new HashSet<>();
        InputStream inputStream = context.getResources().openRawResource(resourceId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lines;
    }
}
