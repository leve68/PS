const [a, b] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ")
  .map((x) => Number(x));

//1) a*2
//2) a*10+1

let myMap = new Map();
let myQueue = new Queue();
myQueue.myPush(a);
myMap.set(a, 1);

while (myQueue.size > 0) {
  let current = myQueue.myPop();
  let currentCount = myMap.get(current);

  let next1 = current * 2;
  let next2 = current * 10 + 1;
  if (next1 <= b && !myMap.get(next1)) {
    myQueue.myPush(next1);
    myMap.set(next1, currentCount + 1);
  }
  if (next2 <= b && !myMap.get(next2)) {
    myQueue.myPush(next2);
    myMap.set(next2, currentCount + 1);
  }

  if (next1 === b || next2 === b) break;
}
if (myMap.get(b)) console.log(myMap.get(b));
else console.log(-1);

//큐
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
