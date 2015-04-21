package com.example.rutaitam;

/**
 * Created by Lab on 15/04/2015.
 */
public class Edge{
    public final Vertex target;
    public final double weight;
    public Edge(Vertex argTarget, double argWeight){
        target = argTarget; weight = argWeight;
    }
}
