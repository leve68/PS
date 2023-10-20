const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ");

const num = input.map((x) => Number(x));

//순환 큐 사용
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
      this.back.next = this.front;
    }
    this.size++;
  };

  this.myPop = function () {
    if (this.size === 0) {
      console.log("error");
      return;
    } else {
      answer.push(this.front.value);
      this.front = this.front.next;
      this.size--;
    }
  };

  this.myPushBack = function () {
    if (this.size === 0) {
      console.log("error");
      return;
    } else {
      const node = this.front;
      this.back.next = node;
      this.back = node;
      this.front = this.front.next;
      this.back.next = this.front;
    }
  };
}

let myQueue = new Queue();
for (let i = 1; i <= num[0]; i++) {
  myQueue.myPush(i);
}

let i = 1;
while (myQueue.size !== 0) {
  if (i === num[1]) {
    myQueue.myPop();
    i = 0;
  } else {
    myQueue.myPushBack();
  }
  i++;
}

console.log(`<${answer.join(", ")}>`);