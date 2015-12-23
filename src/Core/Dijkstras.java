/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Graph.Node;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author wookie
 */
public class Dijkstras {
    private int start;   
    private int  parent[]; // из какой вершины пришли;
                           // служит для восстановления маршрута
    //private GraphMatrix matrix;
    private int matrix[][];
    //ArrayList<Node> nodes;
    
    private boolean distance = false;
    
    private int vNum; 
    private int eNum;
    private boolean[] in_tree;
    private int distlenght;
  
    private ArrayList<Integer> distan = new ArrayList<>();
     
//    public Dijkstras(GraphMatrix matrix)
//    {
//        this.matrix = matrix;
//        distan = new ArrayList<>();                 
//    }
    
    public Dijkstras(int[][] matrix) {
        //this.nodes = nodes;
        this.matrix = matrix;
    }
    
    public void distancealg(boolean flag)
    {
        distance = flag;
    }

    public void DijkstrasAlg(int first, int last)
    {
        //this.distan.clear();
        
        start = first;
        int end = last;
//        if(end < start)
//        {
//            int re = start;
//            start = end;
//            end = re;
//        }
        // start -- вершина, от которой считаем расстояния
        // end  -- вершина до которой считаем растояние
        /**************Описание графа*****************/    
        
        //int N = matrix.size(); // количество вершин
        int N = matrix.length;
        // матрица смежности: adj_matrix[i][j] == true,
        // если между вершинами i и j существует ребро
        //boolean adj_matrix[][] = new boolean[N][N];
        
        //int  cost[][] = new int [N][N]; // веса рёбер

        /*********************************************/
        
        /*********Результаты работы алгоритма*********/
        
        int  dist[] = new int[N]; // расстояния от заданной вершины

        parent = new int[N]; // из какой вершины пришли;
                                    // служит для восстановления маршрута
        /*********************************************/
    
    
        
        // in_tree[i] == true, если для вершины i
        // уже посчитано минимальное расстояние
        in_tree = new boolean[N];
  
        for (int i = 0; i < parent.length; i++)
            parent[i] = i;
        
        for(int i = 0; i < N; i++)
            dist[i] = Integer.MAX_VALUE;// машинная бесконечность,
                                        // т. е. любое расстояние будет меньше данного

        dist[start] = 0; // понятно почему, не так ли? ;)

        int cur = start; // вершина, с которой работаем

        // пока есть необработанная вершина
        while(!in_tree[cur])
        {
            in_tree[cur] = true;

            for(int i = 0; i < N; i++)
            {
                // если между cur и i есть ребро
                //if(adj_matrix[cur][i] != 0)
                //int d = matrix.GetRelations(cur, i);
                int d = matrix[cur][i];
                
                if(d != 0)
                {
                    if (distance)
                        d = 1;
                    // считаем расстояние до вершины i:
                    // расстояние до cur + вес ребра
                    // = dist[cur] + cost[cur][i];
                    d += dist[cur];// cost[cur][i];
                    // если оно меньше, чем уже записанное
                    if(d < dist[i])
                    {
                        dist[i]   = d;   // обновляем его
                        parent[i] = cur; // и "родителя"
                    }
                }
            }
            // ищем нерассмотренную вершину
            // с минимальным расстоянием
            int min_dist = Integer.MAX_VALUE;
            for(int i = 0; i < N; i++)
            {
                if(!in_tree[i] && dist[i] < min_dist)
                {
                    cur = i;
                    min_dist = dist[i];
                }
            }
        }

        // Теперь:
        // в dist[i] минимальное расстояние от start до i
        // в parent[i] вершина, из которой лежит оптимальный путь в i     
        if (dist[end] == Integer.MAX_VALUE)
            return;
        else
            distlenght = dist[end];
        recleng(end);
        distan.add(start);
        Collections.reverse(distan);
    }
    
    public int getlength()
    {
        return distlenght;
    }
    
    public ArrayList<Integer> getPath()
    {
        return distan;
    }
    
    private void recleng(int j)
    {
        if(j == start)
            return;
        distan.add(j);
        recleng(parent[j]);
    }
}
