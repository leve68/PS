const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n")
  .map((x) => Number(x));

const num = input[0];

let myHeap = new MaxHeap();
let answer = [];
for (let i = 1; i <= num; i++) {
  if (input[i] === 0) {
    //pop
    answer.push(myHeap.MyPop());
  } else {
    //push
    myHeap.MyPush(input[i]);
  }
}
console.log(answer.join("\n"));
//최대 힙 구현하는 문제
function MaxHeap() {
  this.size = 0;
  this.heap = [0];

  this.GetHeap = function () {
    return this.heap;
  };

  this.MyPush = function (value) {
    this.size++;
    this.heap.push(value);
    let current = this.size;
    while (value > this.heap[Math.floor(current / 2)] && current !== 1) {
      this.heap[current] = this.heap[Math.floor(current / 2)];
      current = Math.floor(current / 2);
    }
    this.heap[current] = value;
  };

  this.MyPop = function () {
    if (this.size === 0) {
      return 0;
    }
    let max = this.heap[1];
    this.heap[1] = this.heap[this.size];
    this.heap.pop();
    this.size--;

    let value = this.heap[1];
    let current = 1;
    while (current <= this.size) {
      let temp = value;
      let index;
      if (temp < this.heap[current * 2]) {
        temp = this.heap[current * 2];
        index = current * 2;
      }
      if (temp < this.heap[current * 2 + 1]) {
        temp = this.heap[current * 2 + 1];
        index = current * 2 + 1;
      }
      this.heap[index] = this.heap[current];
      this.heap[current] = temp;
      current = index;

      if (temp <= current) {
        break;
      }
    }

    return max;
  };
}
