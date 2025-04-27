using System;
using System.Collections;
using System.Diagnostics;
using System.Timers;

namespace BOJ
{
    internal class Program
    {

        static void Main(string[] args)
        {
            while(true)
            {
                string[] str = Console.ReadLine().Split();

                int A = int.Parse(str[0]);
                int B = int.Parse(str[1]);


                if (A != 0 || B != 0)
                {
                    if (A > B)
                    {
                        Console.WriteLine("Yes");
                    }
                    else
                    {
                        Console.WriteLine("No");
                    }
                }
                else break;
            }
        }



    }
}
 