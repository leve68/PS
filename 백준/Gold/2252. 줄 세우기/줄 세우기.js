const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

const n = input[0][0];
const m = input[0][1];
//정해진 순서가 있는 정렬 => 위상정렬

let edgeMap = Array.from({ length: n + 1 }, () => []);
let inDegree = Array(n + 1).fill(0);
for (let i = 1; i <= m; i++) {
  inDegree[input[i][1]]++;
  edgeMap[input[i][0]].push(input[i][1]);
}

let myQueue = new Queue();
for (let i = 1; i < n + 1; i++) {
  if (inDegree[i] === 0) {
    myQueue.myPush(i);
  }
}

let answer = [];
while (myQueue.size > 0) {
  let current = myQueue.myPop();
  answer.push(current);

  if (edgeMap[current].length > 0) {
    let nextVertexArr = edgeMap[current];
    for (let i = 0; i < nextVertexArr.length; i++) {
      inDegree[nextVertexArr[i]]--;
      if (inDegree[nextVertexArr[i]] === 0) {
        myQueue.myPush(nextVertexArr[i]);
      }
    }
  }
}
console.log(answer.join(" "));

//큐 구현
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
