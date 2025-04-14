using System;
using System.Collections;
using System.Diagnostics;

namespace BOJ
{
    internal class Program
    {
        static int screenEmo = 1;
        static int clipboardEmo = 0;
        static int[,] clipCheck;
        static int S, answer;

        static void Main(string[] args)
        {
            S = int.Parse(Console.ReadLine());
            clipCheck = new int[S*2, S*2];
            for (int i = 0; i < S*2; i++)
            {
                for (int j = 0; j < S * 2; j++)
                {
                    clipCheck[i,j] = 987654321;
                }
            }
            

            answer = 987654321;
            minDFS(screenEmo, clipboardEmo);

            Console.WriteLine(answer);
        }

        class Node
        {
            public int screen;
            public int clip;
            public Node(int screen, int clip)
            {
                this.screen = screen;
                this.clip = clip;
            }
        }
        static void minDFS(int sE, int cE)
        {
            Queue<Node> q = new Queue<Node>();
            q.Enqueue(new Node(sE, cE));
            int count = 0;

            while (q.Count > 0)
            {
                int size = q.Count;
                for (int i = 0; i < size; i++)
                {

                    Node node = q.Dequeue();
                    int screen = node.screen;
                    int clip = node.clip;

                    //Console.WriteLine(screen + " " + clip);

                    if(screen == S)
                    {
                        answer = count;
                        return;
                    }
                    if (screen >= S * 2 || clip >= S * 2) continue;
                    if (clipCheck[screen,clip] <= count) continue;
                    clipCheck[screen, clip] = count;

                    q.Enqueue(new Node(screen + clip, clip));
                    q.Enqueue(new Node(screen , screen));
                    if( screen - 1>= 0 ) q.Enqueue(new Node(screen-1, clip));
                }

                count++;
            }
        }

    }
}
 