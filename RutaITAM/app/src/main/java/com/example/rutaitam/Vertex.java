package com.example.rutaitam;

import java.util.ArrayList;

/**
 * Created by Lab on 15/04/2015.
 */
public class Vertex implements Comparable<Vertex>
{
    public final String name;
    public ArrayList<Edge> adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public static int count = 0;
    public int id;
    public Vertex previous;
    public Vertex(String argName)
    { name = argName; 	adjacencies = new ArrayList<Edge>(); id = count++;}
    public String toString() { return name; }
    public int compareTo(Vertex other){
        return Double.compare(minDistance, other.minDistance);
    }
    @Override
    public boolean equals(Object other){
        return ((Vertex)other).name.equals(this.name);
    }
}