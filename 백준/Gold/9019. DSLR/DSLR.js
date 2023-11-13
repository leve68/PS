const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n");

const testCount = Number(n);
const test = input.map((v) => v.split(" ").map((x) => Number(x)));

let answer = [];
for (let i = 0; i < testCount; i++) {
  let start = test[i][0];
  let request = test[i][1];
  answer[i] = GetOpArr(start, request);
}
console.log(answer.join("\n"));
//BFS이용
function GetOpArr(start, request) {
  let myQueue = new Queue();
  let visited = Array();
  visited[start] = " ";
  let answerArr = [];
  myQueue.myPush(start);
  while (myQueue.size !== 0) {
    let root = myQueue.myPop();
    if (root === request) {
      answerArr.push(visited[root].trim());
      break;
    }

    //D
    let rootD = (root * 2) % 10000;
    if (!visited[rootD]) {
      myQueue.myPush(rootD);
      visited[rootD] = visited[root] + "D";
    }
    //S
    let rootS = root - 1;
    if (rootS === -1) rootS = 9999;
    if (!visited[rootS]) {
      myQueue.myPush(rootS);
      visited[rootS] = visited[root] + "S";
    }
    //L
    let rootL = Math.floor(((root * 10) % 10000) + root / 1000);
    if (!visited[rootL] && rootL < 10000) {
      myQueue.myPush(rootL);
      visited[rootL] = visited[root] + "L";
    }
    //R
    let rootR = Math.floor(((root * 1000) % 10000) + root / 10);
    if (!visited[rootR] && rootR < 10000) {
      myQueue.myPush(rootR);
      visited[rootR] = visited[root] + "R";
    }
  }
  return answerArr;
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
