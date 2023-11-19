const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const num = n.split(" ").map((x) => Number(x));
const edgeInput = input.map((x) => x.split(" ").map((x) => Number(x)));
const vertexCount = num[0];
const edgeCount = num[1];

let edgeArray = new Array(vertexCount + 1).fill(null).map(() => []);

for (let i = 0; i < edgeCount; i++) {
  edgeArray[edgeInput[i][0]].push(edgeInput[i][1]);
  edgeArray[edgeInput[i][1]].push(edgeInput[i][0]);
}

let visited = Array(vertexCount + 1).fill(false);

//BFS는 메모리초과로 풀리지 않음
let count = 0;
for (let i = 1; i <= vertexCount; i++) {
  if (!visited[i]) {
    DFS(i);
    count++;
  }
}

console.log(count);

function DFS(vertex) {
  if (visited[vertex] === true) return;
  visited[vertex] = true;
  for (let i = 0; i < edgeArray[vertex].length; i++) {
    if (visited[edgeArray[vertex][i]] === false) {
      DFS(edgeArray[vertex][i]);
    }
  }
}
