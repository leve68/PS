const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const vertexNum = Number(n);
const edge = input.map((v) => v.split(" ").map((x) => Number(x)));

let edgeMap = new Map();
for (let i = 0; i < vertexNum; i++) {
  let temp = [];
  for (let j = 0; j < vertexNum; j++) {
    if (edge[i][j] === 1) {
      temp.push(j + 1);
    }
  }
  edgeMap.set(i + 1, temp);
}

let answerMap = [];
for (let i = 1; i <= vertexNum; i++) {
  let tempArr = Array(vertexNum).fill(0);
  for (let j = 1; j <= vertexNum; j++) {
    if (edgeMap.get(i)) {
      //DFS사용해서 갈수있는 모든 vertex 찾기
      tempArr = GetVisited(vertexNum, edgeMap, i, tempArr);
    }
  }
  answerMap[i - 1] = tempArr.join(" ");
}
console.log(answerMap.join("\n"));

function GetVisited(vertexNum, edgeMap, root, visited) {
  let nextVertex = edgeMap.get(root);
  if (nextVertex.length !== 0) {
    for (let i = 0; i < nextVertex.length; i++) {
      if (visited[nextVertex[i] - 1] !== 1) {
        visited[nextVertex[i] - 1] = 1;
        visited = GetVisited(vertexNum, edgeMap, nextVertex[i], visited);
      }
    }
  }
  return visited;
}
