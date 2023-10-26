//DFS : 깊이우선탐색 (스택 or 재귀를 통해 구현)
//BFS : 너비우선탐색 (큐를 통해 구현)
let [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const info = n.split(" ").map((x) => Number(x));
const edge = input.map((x) => x.split(" ").map((x) => Number(x)));
const vertexCount = info[0];
const edgeCount = info[1];
const startVertex = info[2];

let visited = Array(vertexCount + 1);
for (let i = 0; i < visited.length; i++) {
  visited[i] = false;
}

let graph = new Array(vertexCount + 1);
graph[0] = [];
for (let i = 1; i < graph.length; i++) {
  let tempArr = new Array();
  for (let j = 0; j < edgeCount; j++) {
    let temp = Array();
    temp[0] = edge[j][0];
    temp[1] = edge[j][1];
    if (edge[j][0] === i) {
      tempArr.push(temp);
    } else if (edge[j][1] === i) {
      tempArr.push(temp.reverse());
    }
  }
  graph[i] = tempArr;
  graph[i].sort((a, b) => a[1] - b[1]);
}
//각 vertex의 간선 정보는 graph[vertexNum] 에 정렬되어 배열로 저장됨
function FindNextVertex(graph, root, visited) {
  visited[root] = true;
  DFS.push(root);

  for (let i = 0; i < graph[root].length; i++) {
    if (!visited[graph[root][i][1]]) {
      FindNextVertex(graph, graph[root][i][1], visited);
    } else if (!visited[graph[root][i][0]]) {
      FindNextVertex(graph, graph[root][i][0], visited);
    }
  }
}

function Node(value) {
  this.value = value;
  this.next = null;
}

function Queue() {
  this.size = 0;
  this.front = null;
  this.back = null;

  this.myPush = function (value) {
    const node = new Node(value);
    if (this.size === 0) {
      this.front = node;
      this.back = node;
    } else {
      this.back.next = node;
      this.back = node;
    }
    this.size++;
  };
  this.myPop = function () {
    if (this.size === 0) {
      console.log("error");
    } else {
      const node = this.front;
      this.front = node.next;
      this.size--;
      return node.value;
    }
  };
}

//DFS
let DFS = [];
FindNextVertex(graph, startVertex, visited);
console.log(DFS.join(" "));
//BFS
let BFS = [];
for (let i = 0; i < visited.length; i++) {
  //visited 초기화
  visited[i] = false;
}
let myQueue = new Queue();
myQueue.myPush(startVertex);
while (myQueue.size !== 0) {
  let root = myQueue.myPop();
  visited[root] = true;
  BFS.push(root);

  for (let i = 0; i < graph[root].length; i++) {
    if (!visited[graph[root][i][1]]) {
      myQueue.myPush(graph[root][i][1]);
      visited[graph[root][i][1]] = true;
    } else if (!visited[graph[root][i][0]]) {
      myQueue.myPush(graph[root][i][0]);
      visited[graph[root][i][0]] = true;
    }
  }
}
console.log(BFS.join(" "));
