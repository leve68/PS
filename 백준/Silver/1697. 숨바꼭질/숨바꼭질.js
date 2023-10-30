const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .split(" ")
  .map((x) => Number(x));

let start = input[0];
let dest = input[1];
//일종의 최단거리 문제
//각 노드는 -1, +1, *2 3개의 분기를 가짐
//queue에 목표 노드를 queue 넣음
//목표-1, +1 /2의 노드를 넣음
//계속하면서 찾으면 break;

const maxInput = 100000;
let current = dest;
let myQueue = new Queue();
let visited = Array(maxInput + 1);
let distance = Array(maxInput + 1);
distance.fill(0);

let edge = new Map();
edge.set(0, [1]);
edge.set(1, [0, 2]);
edge.set(maxInput, [99999]);

//찾으면 종료하기 위한 변수
let isFinding = true;
myQueue.myPush(start);
if (start === dest) isFinding = false;
while (isFinding) {
  let current = myQueue.myPop();
  visited[current] = true;
  if (!edge.has(current)) {
    if (current * 2 <= maxInput) {
      edge.set(current, [current - 1, current + 1, 2 * current]);
    } else edge.set(current, [current - 1, current + 1]);
  }
  currEdge = edge.get(current);
  for (let i = 0; i < currEdge.length; i++) {
    if (!visited[currEdge[i]]) {
      visited[currEdge[i]] = true;
      myQueue.myPush(currEdge[i]);
      //distance배열에 start로부터의 거리를 계속 갱신
      //먼저 입력되는게 빨리 찾아지는 것 이므로 가능함(BFS로 최단거리 푸는 이유)
      distance[currEdge[i]] = distance[current] + 1;
      if (dest === currEdge[i]) {
        isFinding = false;
        break;
      }
    }
  }
}
console.log(distance[dest]);

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
    } else {
      const node = this.front;
      this.front = node.next;
      this.size--;
      return node.value;
    }
  };
}
