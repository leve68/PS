const [num, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const number = num.split(" ").map((x) => Number(x));

const n = number[0];
const m = number[1];

let map = [];

//처음 사용했던 주변을 X로 두루는 문자열 조작 제거(시간줄이기)
for (let i = 0; i < n; i++) {
  map[i] = inputString[i].split("");
}

let myQueue = new Queue();
for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (map[i][j] === "I") {
      myQueue.myPush([i, j]);
      break;
    }
  }
  if (myQueue.size === 1) break;
}

const dx = [1, 0, -1, 0];
const dy = [0, -1, 0, 1];
let count = 0;
while (myQueue.size > 0) {
  let current = myQueue.myPop();
  let curX = current[1];
  let curY = current[0];
  for (let i = 0; i < 4; i++) {
    if (
      0 <= curY + dy[i] &&
      curY + dy[i] < n &&
      0 <= curX + dx[i] &&
      curX + dx[i] < m
    ) {
      let nextCell = map[curY + dy[i]][curX + dx[i]];
      if (nextCell === "O" || nextCell === "P") {
        myQueue.myPush([curY + dy[i], curX + dx[i]]);
        if (nextCell === "P") count++;
        map[curY + dy[i]][curX + dx[i]] = "X";
      }
    }
  }
}
if (count === 0) console.log("TT");
else console.log(count);

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
