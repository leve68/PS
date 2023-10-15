const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const instruction = input.map((v) => v.split(" "));

function Node(value) {
  this.value = value;
  this.next = null;
}

let answer = [];
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
      answer.push(-1);
      return;
    } else {
      answer.push(this.front.value);
      this.front = this.front.next;
      this.size--;
    }
  };

  this.myFront = function () {
    if (this.size === 0) {
      answer.push(-1);
      return;
    } else {
      answer.push(this.front.value);
    }
  };

  this.myBack = function () {
    if (this.size === 0) {
      answer.push(-1);
      return;
    } else {
      answer.push(this.back.value);
    }
  };

  this.isEmpty = function () {
    if (this.size === 0) {
      answer.push(1);
      return;
    } else {
      answer.push(0);
    }
  };

  this.getSize = function () {
    answer.push(this.size);
  };
}

let myQueue = new Queue();
for (let i = 1; i < instruction.length; i++) {
  switch (instruction[i][0]) {
    case "push":
      myQueue.myPush(instruction[i][1]);
      break;
    case "pop":
      myQueue.myPop();
      break;
    case "size":
      myQueue.getSize();
      break;
    case "empty":
      myQueue.isEmpty();
      break;
    case "front":
      myQueue.myFront();
      break;
    case "back":
      myQueue.myBack();
      break;
  }
}

console.log(answer.join("\n"));
