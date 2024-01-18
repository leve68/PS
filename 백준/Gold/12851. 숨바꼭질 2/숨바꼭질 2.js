const [input1, input2] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ");

const start = Number(input1);
const end = Number(input2);

let cost = Array(100001).fill(-1);
let count = Array(100001).fill(0);

let myQueue = new Queue();
myQueue.myPush(start);
cost[start] = 0;

let isFound = false;
while (myQueue.size > 0) {
  let current = myQueue.myPop();

  if (cost[current + 1]) {
    if (cost[current + 1] >= cost[current] + 1 || cost[current + 1] === -1) {
      cost[current + 1] = cost[current] + 1;
      myQueue.myPush(current + 1);
      count[current + 1]++;
    }
  }
  if (cost[current - 1]) {
    if (cost[current - 1] >= cost[current] + 1 || cost[current - 1] === -1) {
      cost[current - 1] = cost[current] + 1;
      myQueue.myPush(current - 1);
      count[current - 1]++;
    }
  }
  if (cost[current * 2]) {
    if (cost[current * 2] >= cost[current] + 1 || cost[current * 2] === -1) {
      cost[current * 2] = cost[current] + 1;
      myQueue.myPush(current * 2);
      count[current * 2]++;
    }
  }
}
console.log(cost[end]);
console.log(count[end] === 0 ? 1 : count[end]);

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
