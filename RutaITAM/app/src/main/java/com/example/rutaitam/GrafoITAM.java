package com.example.rutaitam;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Lab on 15/04/2015.
 */
public class GrafoITAM {
    private ArrayList<Vertex> nodes;
    private Vertex[] arr;
    public GrafoITAM(InputStream inputStream){
        nodes = new ArrayList<Vertex>();
        readFile(new InputStreamReader(inputStream));
    }
    private void readFile(InputStreamReader inputStream){
        BufferedReader bufferedReader = new BufferedReader(inputStream);
        String readString = "";
        String values[];
        int count = 0;
        try {
            while ((readString = bufferedReader.readLine()) != null) {
                values = readString.split("_");
                System.out.println("Conectar: "+values[0]+" a: "+values[1]);
                Vertex origin = new Vertex(values[0]);
                Vertex target = new Vertex(values[1]);
                int weight = Integer.parseInt(values[2]);
                if(!nodes.contains(origin)){
                    count++;
                    nodes.add(origin);
                }
                if(!nodes.contains(target)){
                    count++;
                    nodes.add(target);
                }
                int indexOrigin = nodes.indexOf(origin);
                int indexTarget = nodes.indexOf(target);
                unir(nodes.get(indexOrigin),nodes.get(indexTarget),weight);
            }
        }catch (IOException ioe){

        }catch(NumberFormatException nfe){

        }
        System.out.println("Número de nodos: "+count);
    }
    private Vertex getVertex(String name){
        if (nodes.indexOf(new Vertex(name))>=0)
            return nodes.get(nodes.indexOf(new Vertex(name)));
        return null;
    }
    public ArrayList<String> getPath(String origin, String target){
        ArrayList<String> res = new ArrayList<String>();
        //copy = new ArrayList<Vertex>(nodes);
        Vertex vOrigin = getVertex(origin), vTarget = getVertex(target);
        if(vOrigin!=null&&vTarget!=null){
            computePaths(vOrigin);
            List<Vertex> temp = getShortestPathTo(vTarget);
            for(Vertex v:temp){
                res.add(v.name);
            }
        }else{
            res.add("No se pudo encontrar un camino...");
        }
        return res;
    }
    public String[] getNames(){
        String [] places = new String[nodes.size()];
        for(int i=0;i<nodes.size();i++){
            places[i]=nodes.get(i).name;
        }
        Arrays.sort(places);
        return places;
    }
    private void unir(Vertex v0, Vertex v1, int peso) {
        v0.adjacencies.add(new Edge(v1, peso));
        v1.adjacencies.add(new Edge(v0, peso));
    }
    private void computePaths(Vertex source)
    {
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        //Create visited array
        boolean[] visited = new boolean[Vertex.count + 1];

        for (int i = 0; i <= Vertex.count; i++) {
            visited[i] = false;
        }
        for(Vertex v:nodes){
            v.minDistance=Double.POSITIVE_INFINITY;
            v.previous = null;
        }
        source.minDistance = 0;
        int c = 0;
        //visited[source.id]=true;
        while (!vertexQueue.isEmpty()) {

            Vertex u = vertexQueue.poll();
            System.out.println(u.name);
            System.out.println("Adjacencies:");
            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                System.out.println(e.target.name);
                Vertex v = e.target;
                //Check if vertex has been visited
                if (!visited[v.id]) {
                    c++;
                    System.out.println(v.name+" no visitado.");
                    double weight = e.weight;
                    double distanceThroughU = u.minDistance + weight;
                    if (distanceThroughU < v.minDistance) {
                        System.out.println(v.name+" se cambia la distancia.");
                        v.minDistance = distanceThroughU;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                }
            }
            //Mark as visited
            visited[u.id] = true;
        }
        System.out.println(c+"lolol");
    }

    private List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }
}
