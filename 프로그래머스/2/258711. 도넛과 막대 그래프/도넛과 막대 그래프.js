function solution(edges) {
    var answer = [0,0,0,0];
    
    let edgeStartMap = Array();
    let edgeEndMap = Array();
    edges.forEach((edge) => {
        if(!edgeStartMap[edge[0]]) edgeStartMap[edge[0]] = [edge[1]];
        else edgeStartMap[edge[0]].push(edge[1]);
        
        if(!edgeEndMap[edge[1]]) edgeEndMap[edge[1]] = [edge[0]];
        else edgeEndMap[edge[1]].push(edge[0]);
    })
    
    //시작지점 찾기
    //들어오는 간선이 없고 출발 간선이2개 이상인 지점
    let n = Math.max(edgeStartMap.length, edgeEndMap.length)
    let startVertex;
    for(let i = 1;i < n; i++){
        if(edgeStartMap[i] && edgeStartMap[i].length > 1 && !edgeEndMap[i]) {
            startVertex = i; 
            break;
        }
    }
    
    answer[0] = startVertex;
    let visited = Array(n).fill(false);
    visited[startVertex] = true;
    
    for(let i = 0; i<edgeStartMap[startVertex].length; i++){
        let current = edgeStartMap[startVertex][i];
        
        let stack = [];
        stack.push(current);
        visited[current] = true;
        
        while(stack.length > 0){
            let cur = stack[stack.length - 1];
            let nextArr = edgeStartMap[cur];
            if(!nextArr) {
                answer[2]++;
                break;
            } else if(nextArr.length > 1) {
                answer[3]++;
                break;
            }
            
            let flag = false;
            for(let j = 0; j<nextArr.length; j++){
                if(!visited[nextArr[j]]) {
                    stack.push(nextArr[j]);
                    visited[nextArr[j]] = true;
                    flag = true;
                }
            }
            if(!flag) break;
        }
    }
    answer[1] = edgeStartMap[startVertex].length - answer[2] - answer[3];
    
    console.log(answer)
    return answer;
}
