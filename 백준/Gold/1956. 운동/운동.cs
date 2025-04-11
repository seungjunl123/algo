using System;
using System.Collections;
using System.Numerics;

namespace BOJ
{
    internal class Program
    {
        static int V, E;
        static int[,] dist;
        static int INF = 200000000;

        static void Main(string[] args)
        {
            string[] input = Console.ReadLine().Split(' ');
            V = int.Parse(input[0]);
            E = int.Parse(input[1]);

            dist = new int[V + 1, V + 1];
            for(int i=1;i<=V;i++)
            {
                for(int j=1;j<=V;j++)
                {
                    if(i==j) dist[i,j] = 0;
                    else dist[i, j] = INF;
                }
            }

            for (int i=0;i<E;i++)
            {
                string[] tmp = Console.ReadLine().Split(' ');
                int start = int.Parse(tmp[0]);
                int end = int.Parse(tmp[1]);
                int cost = int.Parse(tmp[2]);

                dist[start,end] = cost;
            }

            int answer = FindCycle();

            Console.WriteLine(answer);
        }

        static int FindCycle()
        {
            for(int k=1;k<=V;k++)
            {
                for(int i=1;i<=V;i++)
                {
                    for(int j=1;j<=V; j++)
                    {
                        //Console.WriteLine(dist[i, j] + " " + dist[i, k] + " " + dist[k, j]);
                        if (dist[i,j] > dist[i,k] +dist[k,j])
                            dist[i, j] = dist[i, k] + dist[k, j];
                    }
                }
            }

            int tmp = INF;
            for (int i = 1; i <= V; i++)
            {
                for (int j = 1; j <= V; j++)
                {
                    if (i == j) continue;
                    if (dist[i, j] != INF && dist[j,i] != INF)
                    {
                        if(tmp > dist[i,j] + dist[j,i])
                             tmp = dist[i,j] + dist[j, i]; 
                    }
                }
            }

            if (tmp == INF) return -1;
            else return tmp;
            
        }
    }
}
 