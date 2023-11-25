const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

//이동 경로 정보를 가진 edgeMap 생성
let edgeMap = [0];
for (let i = 1; i < 100; i++) {
  edgeMap[i] = [i + 1];
  let j = i + 2;
  while (j <= i + 6 && j <= 100) {
    edgeMap[i].push(j);
    j++;
  }
}
for (let i = 1; i < input.length; i++) {
  edgeMap[input[i][0]] = [input[i][1]];
}
input.length = 0; //메모리 절감

//count[i]는 i까지 가는데 필요한 주사위 던지는 수 저장
let count = [0, 0];
let myQueue = new Queue();
myQueue.myPush(1);
while (myQueue.size > 0) {
  let root = myQueue.myPop();
  if (root === 100) break;
  let nextVertex = edgeMap[root];
  if (nextVertex.length === 1 && nextVertex[0] !== 100) {
    if (count[nextVertex[0]] && count[nextVertex[0]] < count[root]) continue;
    else {
      myQueue.myPush(nextVertex[0]);
      count[nextVertex[0]] = count[root];
    }
  } else {
    for (let i = 0; i < nextVertex.length; i++) {
      if (count[nextVertex[i]] && count[nextVertex[i]] < count[root] + 1)
        continue;
      else {
        myQueue.myPush(nextVertex[i]);
        count[nextVertex[i]] = count[root] + 1;
      }
    }
  }
}
console.log(count[100]);

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
