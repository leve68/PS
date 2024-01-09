let [input, ...map] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

let myQueue = new Queue();

const maxCol = input[0];
const maxRow = input[1];
let allDeleted = false;

let count = -1;

AirToNum([0, 0]);
while (!allDeleted) {
  allDeleted = true;
  count++;
  DeleteCheese();
}
console.log(count);

function DeleteCheese() {
  const dCol = [0, -1, 0, 1];
  const dRow = [1, 0, -1, 0];

  let deletedChesse = [];
  for (let i = 0; i < maxCol; i++) {
    for (let j = 0; j < maxRow; j++) {
      let count = 0;
      if (map[i][j] === 1) {
        allDeleted = false;
        for (let k = 0; k < 4; k++) {
          let nextCol = i + dCol[k];
          let nextRow = j + dRow[k];
          if (
            nextCol >= 0 &&
            nextCol < maxCol &&
            nextRow >= 0 &&
            nextRow < maxRow
          ) {
            if (map[nextCol][nextRow] === -1) {
              count++;
            }
          }
        }
        if (count >= 2) {
          deletedChesse.push([i, j]);
        }
      }
    }
  }
  for (let i = 0; i < deletedChesse.length; i++) {
    AirToNum([deletedChesse[i][0], deletedChesse[i][1]]);
  }
}

//두변이상 실온과 접촉하면 1시간후 사라짐
//내부 공간은 실온이 아님
//모두 사라지는데 걸리는 시간

function AirToNum(root) {
  const dCol = [0, -1, 0, 1];
  const dRow = [1, 0, -1, 0];

  myQueue.myPush(root);
  map[root[0]][root[1]] = -1;

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
          map[nextCol][nextRow] = -1;
          myQueue.myPush([nextCol, nextRow]);
        }
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
