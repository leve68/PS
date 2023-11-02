const [n, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const count = n.split(" ").map((x) => Number(x));
const y = count[0];
const x = count[1];
let input = [];
for (let i = 0; i < inputString.length; i++) {
  input[i] = inputString[i].split("");
}

let maze = GetMaze(x, y, input);

let myQueue = new Queue();
const dx = [1, 0, -1, 0];
const dy = [0, -1, 0, 1];
let moveCount = 0;

myQueue.myPush([1, 1]);
while (myQueue.size !== 0) {
  let current = myQueue.myPop();
  moveCount = maze[current[0]][current[1]];
  for (let i = 0; i < 4; i++) {
    //1로 이동 가능할 때 count++로 값을 변경
    if (maze[current[0] + dy[i]][current[1] + dx[i]] === 1) {
      maze[current[0] + dy[i]][current[1] + dx[i]] = moveCount + 1;
      myQueue.myPush([current[0] + dy[i], current[1] + dx[i]]);
    } else if (
      //1이 없으면 1칸씩 돌아감
      maze[current[0] + dy[i]][current[1] + dx[i]] > 1 &&
      maze[current[0] + dy[i]][current[1] + dx[i]] < moveCount
    ) {
      moveCount = maze[current[0] + dy[i]][current[1] + dx[i]] + 1;
    }
  }
  if (current[0] === y && current[1] === x) {
    break;
  }
}
console.log(moveCount);

function GetMaze(x, y, input) {
  x += 2;
  y += 2;
  let maze = Array(y);
  maze[0] = Array(x).fill("0");
  maze[y - 1] = Array(x).fill("0");

  for (let i = 1; i < y - 1; i++) {
    let temp = Array(x);
    temp[0] = "0";
    for (let j = 0; j < x - 2; j++) {
      temp[j + 1] = input[i - 1][j];
    }
    temp[x - 1] = "0";
    maze[i] = temp;
  }
  let numMaze = maze.map((v) => v.map((x) => Number(x)));
  return numMaze;
}

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
