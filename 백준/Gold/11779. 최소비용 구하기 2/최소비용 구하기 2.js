const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

let vertexCount = input[0][0];
let edgeCount = input[1][0];
let start = input[input.length - 1][0];
let end = input[input.length - 1][1];

let edgeMap = Array.from({ length: vertexCount + 1 }, () => []);
for (let i = 2; i < input.length - 1; i++) {
  let start = input[i][0];
  let end = input[i][1];
  let weight = input[i][2];
  edgeMap[start].push([end, weight]);
}

let minHeap = new MinHeap();
for (let i = 0; i < edgeMap[start].length; i++) {
  minHeap.MyPush([start, edgeMap[start][i][0], edgeMap[start][i][1]]);
}

let distance = Array(vertexCount + 1).fill(-1);
distance[start] = 0;
let route = Array.from({ length: vertexCount + 1 }, () => []);
route[start].push(start);

while (minHeap.heap.length > 1) {
  let current = minHeap.MyPop();
  let curStart = current[0];
  let curEnd = current[1];
  let curWeight = current[2];

  if (distance[curEnd] === -1) {
    distance[curEnd] = curWeight;
    route[curEnd] = [...route[curStart], curEnd];

    if (edgeMap[curEnd]) {
      for (let i = 0; i < edgeMap[curEnd].length; i++) {
        minHeap.MyPush([
          curEnd,
          edgeMap[curEnd][i][0],
          edgeMap[curEnd][i][1] + curWeight,
        ]);
      }
    }
  }
  if (distance[end] !== -1) break;
}

console.log(distance[end]);
console.log(route[end].length);
console.log(route[end].join(" "));

//MinHeap 구현
function MinHeap() {
  this.heap = [0];

  this.MyPush = function (value) {
    this.heap.push(value);
    let currentIndex = this.heap.length - 1;
    while (currentIndex > 1) {
      let parentIndex = Math.floor(currentIndex / 2);
      if (this.heap[currentIndex][2] < this.heap[parentIndex][2]) {
        this.heap[currentIndex] = this.heap[parentIndex];
        this.heap[parentIndex] = value;
        currentIndex = parentIndex;
      } else break;
    }
  };

  this.MyPop = function () {
    if (this.heap.length === 1) return null;
    else if (this.heap.length === 2) return this.heap.pop();

    let returnValue = this.heap[1];
    this.heap[1] = this.heap.pop();
    let currentIndex = 1;

    while (currentIndex * 2 < this.heap.length) {
      let leftChildIndex = currentIndex * 2;
      let rightChildIndex = currentIndex * 2 + 1;
      let minChildIndex = leftChildIndex;

      if (rightChildIndex < this.heap.length) {
        if (this.heap[rightChildIndex][2] < this.heap[leftChildIndex][2])
          minChildIndex = rightChildIndex;
      }

      if (this.heap[currentIndex][2] > this.heap[minChildIndex][2]) {
        let temp = this.heap[currentIndex];
        this.heap[currentIndex] = this.heap[minChildIndex];
        this.heap[minChildIndex] = temp;
        currentIndex = minChildIndex;
      } else break;
    }

    return returnValue;
  };
}
