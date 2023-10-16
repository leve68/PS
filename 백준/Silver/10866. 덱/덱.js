const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const instruction = input.map((v) => v.split(" "));

function Node(value) {
  this.before = null;
  this.value = value;
  this.next = null;
}

let answer = [];
function Deque() {
  this.size = 0;
  this.front = null;
  this.back = null;

  this.myPushFront = function (value) {
    const node = new Node(value);
    if (this.size === 0) {
      this.before = node;
      this.front = node;
      this.back = node;
    } else {
      this.front.before = node;
      node.next = this.front;
      this.front = node;
    }
    this.size++;
  };
  this.myPushBack = function (value) {
    const node = new Node(value);
    if (this.size === 0) {
      this.front = node;
      this.back = node;
    } else {
      this.back.next = node;
      node.before = this.back;
      this.back = node;
    }
    this.size++;
  };

  this.myPopFront = function () {
    if (this.size === 0) {
      answer.push(-1);
      return;
    } else {
      answer.push(this.front.value);
      this.front = this.front.next;
      this.size--;
    }
  };
  this.myPopBack = function () {
    if (this.size === 0) {
      answer.push(-1);
      return;
    } else {
      answer.push(this.back.value);
      this.back = this.back.before;
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

let myDeque = new Deque();
for (let i = 1; i < instruction.length; i++) {
  switch (instruction[i][0]) {
    case "push_front":
      myDeque.myPushFront(instruction[i][1]);
      break;
    case "push_back":
      myDeque.myPushBack(instruction[i][1]);
      break;
    case "pop_front":
      myDeque.myPopFront();
      break;
    case "pop_back":
      myDeque.myPopBack();
      break;
    case "size":
      myDeque.getSize();
      break;
    case "empty":
      myDeque.isEmpty();
      break;
    case "front":
      myDeque.myFront();
      break;
    case "back":
      myDeque.myBack();
      break;
  }
}

console.log(answer.join("\n"));
