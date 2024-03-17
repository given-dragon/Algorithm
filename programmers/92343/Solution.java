import java.util.*;

class Solution {
    List<Integer>[] adjList;
    int maxShpCnt;
    int[] infoArr;
    public int solution(int[] info, int[][] edges) {
        infoArr = info;
        
        // 인접리스트 초기화
        adjList = new ArrayList[info.length];
        for (int i=0; i<info.length; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adjList[edge[0]].add(edge[1]);
        }
        
        // 시작 경로 세팅 & 탐색
        List<Integer> startRouteList = new ArrayList<>();
        startRouteList.add(0);
        move(0, 0, 0, startRouteList);
        
        return maxShpCnt;
    }
    
    public void move(int currNode, int shpCnt, int wlfCnt, List<Integer> routeList) {
        if (infoArr[currNode] == 0) {   // 현재 노드가 양 -> 최댓값 갱신
            maxShpCnt = Math.max(maxShpCnt, ++shpCnt);
        }
        else if (shpCnt <= ++wlfCnt) {  // 현재 노드가 늑대 -> 분기 종료여부 판단
            return;
        }
        
        // call by ref로 인해 현재 정점 삭제 시 문제발생
        // -> 새로운 리스트에 값을 추가 후 삭제 작업 진행
        List<Integer> nextRouteList = new ArrayList<>();
        nextRouteList.addAll(routeList);    // 기존 경로 추가 & 현재 노드 삭제
        nextRouteList.remove(Integer.valueOf(currNode));
        
        nextRouteList.addAll(adjList[currNode]);    // 현재 노드에서 갈 수 있는 경로(자식노드) 추가
        
        for (int nextNode : nextRouteList) {    // 경로 순서대로 탐색
            move(nextNode, shpCnt, wlfCnt, nextRouteList);
        }
    }
}
