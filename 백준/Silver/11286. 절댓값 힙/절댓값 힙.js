const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n")
  .map((x) => Number(x));

const num = input[0];

//class 사용해보기
class AbsHeap {
  constructor() {
    this.size = 0;
    this.heap = [0];
  }

  MyPush(value) {
    this.size++;
    this.heap.push(value);
    let current = this.size;
    while (
      Compare(value, this.heap[Math.floor(current / 2)]) &&
      current !== 1
    ) {
      this.heap[current] = this.heap[Math.floor(current / 2)];
      current = Math.floor(current / 2);
    }
    this.heap[current] = value;
  }
  MinPop() {
    if (this.size === 0) {
      return 0;
    }
    let min = this.heap[1];
    this.heap[1] = this.heap[this.size];
    this.heap.pop();
    this.size--;

    let current = 1;
    while (current * 2 <= this.size) {
      let child = current * 2;
      if (
        child + 1 <= this.size &&
        Compare(this.heap[child + 1], this.heap[child])
      ) {
        child++;
      }

      if (Compare(this.heap[child], this.heap[current])) {
        [this.heap[current], this.heap[child]] = [
          this.heap[child],
          this.heap[current],
        ];
        current = child;
      } else {
        break;
      }
    }

    return min;
  }
}

//a<b 이면 true
function Compare(a, b) {
  if (Math.abs(a) < Math.abs(b)) return true;
  else if (Math.abs(a) === Math.abs(b)) {
    if (a < b) return true;
    else return false;
  } else return false;
}

let heap = new AbsHeap();
let answer = [];
for (let i = 1; i <= num; i++) {
  if (input[i] === 0) {
    answer.push(heap.MinPop());
  } else heap.MyPush(input[i]);
}
console.log(answer.join("\n"));
