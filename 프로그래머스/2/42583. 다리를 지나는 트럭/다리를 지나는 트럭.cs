using System;
using System.Collections.Generic;

public class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int> bridge = new Queue<int>();
        int time = 0;
        int currentWeight = 0;
        int index = 0;

        // 초기 다리 상태를 0으로 채워줌
        for (int i = 0; i < bridge_length; i++) {
            bridge.Enqueue(0);
        }

        while (index < truck_weights.Length) {
            time++;

            // 1초마다 트럭이 한 칸씩 움직이므로 가장 앞 트럭 제거
            currentWeight -= bridge.Dequeue();

            // 다음 트럭이 올라갈 수 있는지 확인
            if (currentWeight + truck_weights[index] <= weight) {
                bridge.Enqueue(truck_weights[index]);
                currentWeight += truck_weights[index];
                index++;
            } else {
                // 못 올라가면 빈 공간 유지
                bridge.Enqueue(0);
            }
        }

        // 마지막 트럭이 다리를 건너는 시간까지 더해줌
        return time + bridge_length;
    }
}
