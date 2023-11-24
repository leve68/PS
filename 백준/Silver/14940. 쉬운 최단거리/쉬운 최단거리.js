const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = input[0][0]; //세로크기
const m = input[0][1]; //가로크기

let map = [];
for (let i = 0; i < n; i++) {
  map[i] = input[i + 1];
}

let arrival = [];
for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (map[i][j] === 2) {
      map[i][j] = 0;
      arrival = [i, j];
      break;
    }
  }
}

const dx = [1, 0, -1, 0];
const dy = [0, -1, 0, 1];

let myQueue = new Queue();
myQueue.myPush(arrival);
while (myQueue.size > 0) {
  let root = myQueue.myPop();
  let curY = root[0];
  let curX = root[1];

  for (let i = 0; i < 4; i++) {
    if (
      0 <= curY + dy[i] &&
      curY + dy[i] < n &&
      0 <= curX + dx[i] &&
      curX + dx[i] < m
    )
      if (map[curY + dy[i]][curX + dx[i]] === 1) {
        map[curY + dy[i]][curX + dx[i]] += map[curY][curX];
        let nextRoot = [curY + dy[i], curX + dx[i]];
        myQueue.myPush(nextRoot);
      }
  }
}
//도달 못하는 곳
for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (map[i][j] === 1) {
      map[i][j] = -1;
    }
  }
}

//도착지점 주변부 수정
let rootX = arrival[1];
let rootY = arrival[0];
for (let i = 0; i < 4; i++) {
  if (
    0 <= rootY + dy[i] &&
    rootY + dy[i] < n &&
    0 <= rootX + dx[i] &&
    rootX + dx[i] < m &&
    map[rootY + dy[i]][rootX + dx[i]] !== 0
  ) {
    map[rootY + dy[i]][rootX + dx[i]] = 1;
  }
}
console.log(map.join("\n").replace(/\,/g, " "));
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
