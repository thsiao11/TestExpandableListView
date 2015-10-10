package com.zeeh.testexpandablelistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProvider {

    public static HashMap<String,List<String>> getInfo() {
        HashMap<String,List<String>> MoviesDetails = new HashMap<String,List<String>>();

        List<String> Action_Movies = new ArrayList<String>();
        Action_Movies.add("I'll be back");
        Action_Movies.add("Star Trek: The Last Frontier");

        List<String> Romantic_Movies = new ArrayList<String>();
        Romantic_Movies.add("Time Traveler's Wife");
        Romantic_Movies.add(("Runaway Bride"));

        List<String> Horror_Movies = new ArrayList<String>();
        Horror_Movies.add("Die! Suckers");
        Horror_Movies.add("I spit on your grave");

        List<String> Comedy_Movies = new ArrayList<String>();
        Comedy_Movies.add("Trading Places");
        Comedy_Movies.add("Brewster's Millions");

        MoviesDetails.put("Action Movies", Action_Movies);
        MoviesDetails.put("Romantic Movies", Romantic_Movies);
        MoviesDetails.put("Horror Movies", Horror_Movies);
        MoviesDetails.put("Comedy Movies", Comedy_Movies);

        return MoviesDetails;
    }
}
