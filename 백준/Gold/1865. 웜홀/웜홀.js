const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

//다익스트라는 음수 간선을 고려하지 못한다.
//벨만 포드 알고리즘을 통해 모든 간선을 확인해 음수 간선도 고려 가능
//시간이 감소하는지 판단하면 되는 문제이므로 최단거리만 저장한다.
//음수 간선 순환의 존재여부 찾는 문제
let answer = [];
for (let i = 1; i < input.length; ) {
  let vertexCount = input[i][0];
  let edgeCount = input[i][1];
  let holeCount = input[i][2];

  let edgeMap = Array(vertexCount + 1);
  for (let j = 0; j < edgeMap.length; j++) {
    edgeMap[j] = Array(vertexCount + 1);
  }

  //도로
  let count = i + 1;
  while (count <= edgeCount + i) {
    let start = input[count][0];
    let end = input[count][1];
    let time = input[count][2];
    let isHole = false;

    edgeMap = AddEdge(start, end, time, isHole, edgeMap);
    count++;
  }

  //웜홀
  while (count <= holeCount + edgeCount + i) {
    let start = input[count][0];
    let end = input[count][1];
    let time = -input[count][2];
    let isHole = true;

    edgeMap = AddEdge(start, end, time, isHole, edgeMap);
    count++;
  }

  if (bellmanFord(vertexCount, edgeMap, 1)) answer.push("YES");
  else answer.push("NO");
  i = count;
}
console.log(answer.join("\n"));

function AddEdge(start, end, time, isHole, edgeMap) {
  if (edgeMap[start][end] === undefined || edgeMap[start][end] > time) {
    edgeMap[start][end] = time;
  }
  if (
    !isHole &&
    (edgeMap[end][start] === undefined || edgeMap[end][start] > time)
  ) {
    edgeMap[end][start] = time;
  }
  return edgeMap;
}

function bellmanFord(vertexCount, edgeMap, start) {
  let distance = Array(vertexCount + 1).fill(10000000);
  distance[start] = 0;
  for (let i = 1; i <= vertexCount; i++) {
    //간선 확인
    for (let current = 1; current <= vertexCount; current++) {
      for (let next = 1; next <= vertexCount; next++) {
        if (
          edgeMap[current][next] !== undefined &&
          edgeMap[current][next] + distance[current] < distance[next]
        ) {
          distance[next] = edgeMap[current][next] + distance[current];

          //마지막까지 값이 갱신되면 음수 간선 순환에 빠진 것임
          if (i === vertexCount) return true;
        }
      }
    }
  }
  return false;
}
