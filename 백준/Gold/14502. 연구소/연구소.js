const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const inputMap = input.map((v) =>
  v.split(" ").map((x) => {
    if (x === "1") return -1;
    else if (x === "2") return -2;
    else return Number(x);
  })
);

let map = input.map((v) =>
  v.split(" ").map((x) => {
    if (x === "1") return -1;
    else if (x === "2") return -2;
    else return Number(x);
  })
);

const maxCol = Number(num[0]);
const maxRow = Number(num[2]);

//0: 빈칸, -1: 벽, -2: 바이러스
//벽 3개를 세운 뒤 모든 경우 bfs

//바이러스 찾기
let startVertex = [];
for (let i = 0; i < maxCol; i++) {
  for (let j = 0; j < maxRow; j++) {
    if (inputMap[i][j] === -2) {
      startVertex.push([i, j]);
    }
  }
}

let count = 0;
let answer = 0;
SetWall(count);

function SetWall(count) {
  if (count === 3) {
    answer > BFS(startVertex) ? answer : (answer = BFS(startVertex));
    return;
  }

  for (let i = 0; i < maxCol; i++) {
    for (let j = 0; j < maxRow; j++) {
      if (inputMap[i][j] === 0 && count < 3 && map[i][j] >= 0) {
        map[i][j] = -1;
        count++;

        SetWall(count);

        map[i][j] = 0;
        count--;
      }
    }
  }
}
console.log(answer);

function BFS(startVertex) {
  const dCol = [0, -1, 0, 1];
  const dRow = [1, 0, -1, 0];
  let myQueue = new Queue();
  for (let i = 0; i < startVertex.length; i++) {
    myQueue.myPush(startVertex[i]);
    map[startVertex[i][0]][startVertex[i][1]] = 1;
  }

  while (myQueue.size > 0) {
    let current = myQueue.myPop();
    let curCol = current[0];
    let curRow = current[1];

    for (let i = 0; i < 4; i++) {
      let nextCol = curCol + dCol[i];
      let nextRow = curRow + dRow[i];
      if (
        nextCol >= 0 &&
        nextCol < maxCol &&
        nextRow >= 0 &&
        nextRow < maxRow
      ) {
        if (map[nextCol][nextRow] === 0) {
          map[nextCol][nextRow] = map[curCol][curRow] + 1;
          myQueue.myPush([nextCol, nextRow]);
        }
      }
    }
  }

  let count = 0;
  for (let i = 0; i < maxCol; i++) {
    for (let j = 0; j < maxRow; j++) {
      if (map[i][j] === 0) {
        count++;
      } else if (map[i][j] > 0) {
        map[i][j] = 0;
      }
    }
  }
  return count;
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
