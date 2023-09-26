const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim();

function Node(value) {
  this.value = value;
  this.next = null;
}

function Queue() {
  this.front = null;
  this.back = null;
  this.size = 0;

  this.add = function (value) {
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

  this.myShift = function () {
    if (this.size === 0) {
      console.log("empty queue");
      return;
    }
    const value = this.front.value;
    this.front = this.front.next;
    this.size--;
    if (this.size === 0) this.back = null;
    return value;
  };
}

let numbers = new Queue();

for (let i = 1; i <= input; i++) {
  numbers.add(i);
}

while (numbers.size !== 1) {
  numbers.myShift();
  const item = numbers.myShift();
  numbers.add(item);
}

console.log(numbers.front.value);
