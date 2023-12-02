const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const vertexCount = input[0][0];

let edgeMap = new Map();
for (let i = 1; i <= vertexCount; i++) {
  let edge = [];
  let j = 1;
  while (input[i][j] !== -1) {
    let temp = [input[i][j], input[i][j + 1]];
    if (edge.length === 0) edge = [temp];
    else edge.push(temp);
    j += 2;
  }
  edgeMap.set(input[i][0], edge);
}

//가장 간단한 접근 방식 : BFS/DFS 2번 사용하기
//아무 vertex에서 가장 먼 vertex A 구하기
//A 에서 가장 먼 vertex B 구하기
//A와 B사의 거리는 최대임

let myQueue = new Queue();

//첫번째 BFS
let visited = Array(vertexCount + 1).fill(false);
myQueue.myPush(1);
visited[1] = 0;
BFS();

//vertex A 구하기
let max = Math.max(...visited);
let maxIndex = 0;
for (let i = 1; i < visited.length; i++) {
  if (max === visited[i]) maxIndex = i;
}

//두번쨰 BFS
visited = Array(vertexCount + 1).fill(false);
myQueue.myPush(maxIndex);
visited[maxIndex] = 0;
BFS();

let newMax = Math.max(...visited);
console.log(Math.max(max, newMax));

//BFS 함수
function BFS() {
  while (myQueue.size > 0) {
    let root = myQueue.myPop();
    let nextVertexArr = edgeMap.get(root);
    for (let i = 0; i < nextVertexArr.length; i++) {
      let nextVertex = nextVertexArr[i][0];
      let nextWeight = nextVertexArr[i][1];

      if (visited[nextVertex] === false) {
        myQueue.myPush(nextVertex);
        visited[nextVertex] = visited[root] + nextWeight;
      }
    }
  }
}

//BFS사용을 위한 큐
function Node(value) {
  this.value = value;
  this.next = null;
}

function Queue() {
  this.front = null;
  this.back = null;
  this.size = 0;

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
    let node;
    if (this.size === 0) {
      console.log("error");
    } else {
      node = this.front;
      this.front = this.front.next;
      this.size--;
      return node.value;
    }
  };
}
