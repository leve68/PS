const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const numbers = num.split(" ").map((x) => Number(x));
let M = numbers[0];
let N = numbers[1];
let H = numbers[2];

//map[z][y][x]
let startY = Array(M + 2).fill(-1);
let lastY = Array(M + 2).fill(-1);
let endZ = Array(N + 2).fill(-1);
let map = [];

endZ[0] = startY;
for (let i = 1; i <= N; i++) {
  endZ[i] = Array(M + 2).fill(-1);
}
endZ[N + 1] = lastY;

map[0] = endZ;
for (let i = 1; i <= H; i++) {
  map[i] = Array(N + 2);
  map[i][0] = startY;
  let countY = 1;
  for (let j = (i - 1) * N; j < N * i; j++) {
    map[i][countY] = ("-1 " + input[j] + " -1")
      .split(" ")
      .map((x) => Number(x));
    countY++;
  }
  map[i][N + 1] = lastY;
}

map[H + 1] = endZ;

let startArr = [];
let count = 0;
for (let k = 1; k <= H; k++) {
  for (let i = 1; i <= N; i++) {
    for (let j = 1; j <= M; j++) {
      if (map[k][i][j] === 1) {
        startArr[count] = [k, i, j];
        count++;
      }
    }
  }
}

//필요한값들
const dx = [1, 0, -1, 0, 0, 0];
const dy = [0, -1, 0, 1, 0, 0];
const dz = [0, 0, 0, 0, 1, -1];

let myQueue = new Queue();
for (let i = 0; i < count; i++) {
  myQueue.myPush(startArr[i]);
}

while (myQueue.size !== 0) {
  let current = myQueue.myPop();
  let curZ = current[0];
  let curX = current[2];
  let curY = current[1];

  for (let i = 0; i < 6; i++) {
    nextZ = curZ + dz[i];
    nextX = curX + dx[i];
    nextY = curY + dy[i];
    if (map[nextZ][nextY][nextX] === 0) {
      myQueue.myPush([nextZ, nextY, nextX]);
      map[nextZ][nextY][nextX] += map[curZ][curY][curX] + 1;
    } else if (map[nextZ][nextY][nextX] === -1) {
      continue;
    }
  }
}
if (map.flat().flat().includes(0)) {
  console.log(-1);
} else {
  console.log(Math.max(...map.flat().flat()) - 1);
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
