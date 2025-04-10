using System;

namespace BOJ
{
    internal class Program
    {
        static int n, m;
        static int[,] Map;
        static bool[,] Checked;
        static int answer = 999999;
        static int[] dr = { 0, 1, 0, -1 };
        static int[] dc = { 1, 0, -1, 0 };
        public class Node
        {
            public int r;
            public int c;
            public int count;
            public Node(int r, int c, int count)
            {
                this.r = r;
                this.c = c;
                this.count = count;
            }
        }



        static void Main(string[] args)
        {
            string[] inputs = Console.ReadLine().Split(' ');

            m = int.Parse(inputs[0]);
            n = int.Parse(inputs[1]);
            Map = new int[n+1, m+1];
            Checked = new bool[n+1, m+1];

            for (int i=1; i<=n; i++)
            {
                string input = Console.ReadLine();
                for(int j=1; j<=m; j++)
                {
                    Map[i, j] = input[j-1] == '1' ? 1 : 0;
                    Checked[i, j] = false;
                }
            }

            PriorityQueue<Node, int> pq = new PriorityQueue<Node, int>();
            pq.Enqueue(new Node(1, 1, 0), 0);

            while (pq.Count > 0)
            {
                Node node = pq.Dequeue();

                //Console.WriteLine(node.r + " " + node.c + " " + node.count);

                if (node.r == n && node.c == m)
                {
                    answer = node.count;
                    break;
                }
                for (int i = 0; i < 4; i++)
                {
                    int nr = node.r + dr[i];
                    int nc = node.c + dc[i];

                    if (nr <= 0 || nc <= 0 || nr > n || nc > m || Checked[nr,nc]) continue;
                    int nCount = node.count + Map[nr, nc];


                    Checked[nr, nc] = true;
                    pq.Enqueue(new Node(nr,nc, nCount), nCount);

                }
            }

            Console.WriteLine(answer.ToString());
        } 
    }
}
 