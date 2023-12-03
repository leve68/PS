const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = input[0][0];
const m = input[0][1];
const x = input[0][2];

//x번 vertex가 목적지
//m개의 단방향 edge, i번째 edge의 가중치 ti
//edgeMap[출발점] = [도착점, 가중치]
let edgeMap = new Map();
let contraryEdgeMap = new Map();
for (let i = 1; i < input.length; i++) {
  if (!edgeMap.get(input[i][0])) {
    edgeMap.set(input[i][0], [[input[i][1], input[i][2]]]);
  } else {
    let temp = edgeMap.get(input[i][0]);
    temp.push([input[i][1], input[i][2]]);
    edgeMap.set(input[i][0], temp);
  }
  if (!contraryEdgeMap.get(input[i][1])) {
    contraryEdgeMap.set(input[i][1], [[input[i][0], input[i][2]]]);
  } else {
    let temp = contraryEdgeMap.get(input[i][1]);
    temp.push([input[i][0], input[i][2]]);
    contraryEdgeMap.set(input[i][1], temp);
  }
}
let myQueue = new Queue();
//x를 도착점으로 오는 거리
let contraryVisited = Array(n + 1).fill(false);
myQueue.myPush(x);
contraryVisited[x] = 0;
contraryVisited = BFS(contraryEdgeMap, contraryVisited);

//x에서 출발점으로 가는 거리
let visited = Array(n + 1).fill(false);
myQueue.myPush(x);
visited[x] = 0;
visited = BFS(edgeMap, visited);

let answer = [0];
for (let i = 1; i < n + 1; i++) {
  answer.push(contraryVisited[i] + visited[i]);
}
console.log(Math.max(...answer));

//BFS
function BFS(myMap, visit) {
  while (myQueue.size > 0) {
    let root = myQueue.myPop();
    let nextVertexArr = myMap.get(root);
    for (let i = 0; i < nextVertexArr.length; i++) {
      let nextVertex = nextVertexArr[i][0];
      let nextWeight = nextVertexArr[i][1];

      if (
        visit[nextVertex] === false ||
        visit[nextVertex] > visit[root] + nextWeight
      ) {
        myQueue.myPush(nextVertex);
        visit[nextVertex] = visit[root] + nextWeight;
      }
    }
  }
  return visit;
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
