const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n");

const numbers = num.split(" ").map((x) => Number(x));
let M = numbers[0];
let N = numbers[1];

//주변을 -1(빈토마토)로 감싼 map 생성
let map = input.map((v) =>
  ("-1 " + v + " -1").split(" ").map((x) => Number(x))
);
map.unshift(Array(M + 2).fill(-1));
map.push(Array(M + 2).fill(-1));

//최소일수를 구하고 싶으므로 BFS사용한다
//시작점으로 삼을 1 찾기
let startArr = [];
let count = 0;
for (let i = 1; i <= N; i++) {
  for (let j = 1; j <= M; j++) {
    if (map[i][j] === 1) {
      startArr[count] = [i, j];
      count++;
    }
  }
}

//필요한값들
const dx = [1, 0, -1, 0];
const dy = [0, -1, 0, 1];

let myQueue = new Queue();
for (let i = 0; i < count; i++) {
  myQueue.myPush(startArr[i]);
}

while (myQueue.size !== 0) {
  let current = myQueue.myPop();
  let curX = current[1];
  let curY = current[0];

  for (let i = 0; i < 4; i++) {
    nextX = curX + dx[i];
    nextY = curY + dy[i];
    if (map[nextY][nextX] === 0) {
      myQueue.myPush([nextY, nextX]);
      map[nextY][nextX] += map[curY][curX] + 1;
    } else if (map[nextY][nextX] === -1) {
      continue;
    }
  }
}
if (map.flat().includes(0)) {
  console.log(-1);
} else {
  console.log(Math.max(...map.flat()) - 1);
}

//BFS사용을 위한 Queue
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
