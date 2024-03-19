const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

//순서가 정해진 작업 => 위상정렬
const vertexCount = num[0];
const edgeCount = num[1];

let startEdgeMap = Array.from({ length: vertexCount + 1 }, () => []);
let inDegree = Array.from({ length: vertexCount + 1 }, () => []);
for (let i = 0; i < edgeCount; i++) {
  startEdgeMap[input[i][0]].push(input[i][1]);
  inDegree[input[i][1]].push(input[i][0]);
}

let minHeap = new MinHeap();
let visited = Array(vertexCount + 1).fill(false);
inDegree.forEach((element, index) => {
  inDegree[index] = element.length;
  if (inDegree[index] === 0 && index !== 0) {
    minHeap.MyPush(index);
    visited[index] = true;
  }
});

let answer = [];
while (minHeap.heap.length > 1) {
  let currentVertex = minHeap.MyPop();
  answer.push(currentVertex);
  for (let i = 0; i < startEdgeMap[currentVertex].length; i++) {
    inDegree[startEdgeMap[currentVertex][i]]--;
  }

  for (let i = 1; i <= vertexCount; i++) {
    if (!visited[i] && inDegree[i] === 0) {
      minHeap.MyPush(i);
      visited[i] = true;
    }
  }
}
console.log(answer.join(" "));

function MinHeap() {
  this.heap = [0];

  this.MyPush = function (value) {
    this.heap.push(value);
    let currentIndex = this.heap.length - 1;
    while (currentIndex > 1) {
      let parentIndex = Math.floor(currentIndex / 2);
      if (this.heap[currentIndex] < this.heap[parentIndex]) {
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
        if (this.heap[rightChildIndex] < this.heap[leftChildIndex])
          minChildIndex = rightChildIndex;
      }

      if (this.heap[currentIndex] > this.heap[minChildIndex]) {
        let temp = this.heap[currentIndex];
        this.heap[currentIndex] = this.heap[minChildIndex];
        this.heap[minChildIndex] = temp;
        currentIndex = minChildIndex;
      } else break;
    }

    return returnValue;
  };
}
