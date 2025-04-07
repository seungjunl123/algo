using System;

namespace 백준
{
    internal class Program
    {
        static int[,] sawTooth = new int[4, 8]; // 확인할 거 2, 6
        static bool[] check = new bool[4];
        static int n;
        static void Main(string[] args)
        {
            for(int i=0;i<4;i++)
            {
                string line = Console.ReadLine();
                if(line == null) continue;
                for(int j=0; j<8;j++)
                {
                    if (line[j]=='1')
                    {
                        sawTooth[i,j] = 1;
                    }
                    else
                    {
                        sawTooth[i,j] = 0;
                    }
                }
            }
            n = int.Parse(Console.ReadLine());
            for (int i = 0; i < n; i++)
            {
                string[] input = Console.ReadLine().Split(' ');
                Array.Clear(check);
                int num = int.Parse(input[0]);
                int dir = int.Parse(input[1]);

                RotateSawTooth(num-1, dir);
            }

            int answer =checkResult();
            Console.WriteLine(answer);
        }

        private static int checkResult()
        {
            int sum = 0;
            for (int i = 0; i < 4; i++)
            {
                if (sawTooth[i, 0] == 1) sum += 1 << i;
            }
            return sum;
        }

        private static void RotateSawTooth(int num, int dir)
        {
            int[] rotateDirs = new int[4]; // 각 톱니바퀴의 회전 방향 저장
            rotateDirs[num] = dir;

            // 왼쪽 방향으로 전파
            for (int i = num; i > 0; i--)
            {
                if (sawTooth[i, 6] != sawTooth[i - 1, 2])
                {
                    rotateDirs[i - 1] = -rotateDirs[i];
                }
                else
                {
                    break;
                }
            }

            // 오른쪽 방향으로 전파
            for (int i = num; i < 3; i++)
            {
                if (sawTooth[i, 2] != sawTooth[i + 1, 6])
                {
                    rotateDirs[i + 1] = -rotateDirs[i];
                }
                else
                {
                    break;
                }
            }

            // 회전 실행
            for (int i = 0; i < 4; i++)
            {
                if (rotateDirs[i] == 1)
                {
                    // 시계 방향
                    int tmp = sawTooth[i, 7];
                    for (int j = 7; j > 0; j--)
                    {
                        sawTooth[i, j] = sawTooth[i, j - 1];
                    }
                    sawTooth[i, 0] = tmp;
                }
                else if (rotateDirs[i] == -1)
                {
                    // 반시계 방향
                    int tmp = sawTooth[i, 0];
                    for (int j = 0; j < 7; j++)
                    {
                        sawTooth[i, j] = sawTooth[i, j + 1];
                    }
                    sawTooth[i, 7] = tmp;
                }
            }
        }

    }
}
 