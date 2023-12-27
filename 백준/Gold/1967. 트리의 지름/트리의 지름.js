const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

let n = input[0][0];
let edgeMap = new Map();
for (let i = 1; i < input.length; i++) {
  if (!edgeMap.get(input[i][0])) {
    edgeMap.set(input[i][0], [[input[i][1], input[i][2]]]);
  } else {
    let temp = edgeMap.get(input[i][0]);
    temp.push([input[i][1], input[i][2]]);
    edgeMap.set(input[i][0], temp);
  }
  if (!edgeMap.get(input[i][1])) {
    edgeMap.set(input[i][1], [[input[i][0], input[i][2]]]);
  } else {
    let temp = edgeMap.get(input[i][1]);
    temp.push([input[i][0], input[i][2]]);
    edgeMap.set(input[i][1], temp);
  }
}

//DFS or BFS 두번사용
let distance = Array(n + 1).fill(0);
let visited = Array(n + 1).fill(false);
//1에서 출발
DFS(1);
let maxIndex = 0;
let max = 0;
for (let i = 0; i < n + 1; i++) {
  if (distance[i] > max) {
    max = distance[i];
    maxIndex = i;
  }
}
distance.fill(0);
visited.fill(false);
DFS(maxIndex);
console.log(Math.max(...distance));

function DFS(root) {
  visited[root] = true;
  let nextArr = edgeMap.get(root);
  if (!nextArr) return;
  for (let i = 0; i < nextArr.length; i++) {
    let nextVertex = nextArr[i][0];
    let nextWeight = nextArr[i][1];
    if (!visited[nextVertex]) {
      distance[nextVertex] = distance[root] + nextWeight;
      DFS(nextVertex);
    }
  }
}
