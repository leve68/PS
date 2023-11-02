const [n, m, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const vertexCount = Number(n);
const edgeCount = Number(m);
let edgeInput = inputString.map((v) => v.split(" ").map((x) => Number(x)));
let visited = Array(vertexCount + 1).fill(0);

let edge = new Map();
//edge[vertex] = 연결된 모든 vertex저장
for (let i = 1; i < vertexCount + 1; i++) {
  let temp = Array();
  for (let j = 0; j < edgeCount; j++) {
    if (edgeInput[j][0] === i) {
      temp.push(edgeInput[j][1]);
    } else if (edgeInput[j][1] === i) {
      temp.push(edgeInput[j][0]);
    }
  }
  edge.set(i, temp);
}

//visited가 1이 된 개수 출력
//방문한 곳의 개수를 새는 것이므로 BFS가 효율적
let myQueue = new Queue();
myQueue.myPush(1);
while (myQueue.size !== 0) {
  let current = myQueue.myPop();
  visited[current] = 1;
  let vertexArr = edge.get(current);
  for (let i = 0; i < vertexArr.length; i++) {
    let nextVertex = vertexArr[i];
    if (visited[nextVertex] === 0) {
      myQueue.myPush(nextVertex);
    }
  }
}
let answer = -1;
visited.forEach((element) => {
  if (element === 1) {
    answer++;
  }
});
console.log(answer);

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
