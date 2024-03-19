const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

const n = num[0];
const m = num[1];

let edgeMap = Array.from({ length: n + 1 }, () => []);
for (let i = 0; i < m; i++) {
  edgeMap[input[i][0]].push([input[i][1], input[i][2]]);
  edgeMap[input[i][1]].push([input[i][0], input[i][2]]);
}

let minHeap = new MinHeap();
let vertexSet = new Set();

//시작점
vertexSet.add(1);
let edgeArr = edgeMap[1];
for (let i = 0; i < edgeArr.length; i++) {
  minHeap.MyPush(edgeArr[i]);
}

let sumWeight = 0;
let maxWeight = 0;

//메모리 초과..
while (vertexSet.size < n) {
  let selectedEdge = minHeap.MyPop();

  if (!vertexSet.has(selectedEdge[0])) {
    vertexSet.add(selectedEdge[0]);
    sumWeight += selectedEdge[1];
    if (maxWeight < selectedEdge[1]) maxWeight = selectedEdge[1];

    let selectedEdgeArr = edgeMap[selectedEdge[0]];
    for (let i = 0; i < selectedEdgeArr.length; i++) {
      if (!vertexSet.has(selectedEdgeArr[i][0])) {
        minHeap.MyPush(selectedEdgeArr[i]);
      }
    }
  }
}
console.log(sumWeight - maxWeight);

//최소 신장 트리 만들고 가중치가 가장 큰 간선 삭제
//프림 알고리즘 사용

function MinHeap() {
  this.heap = [0];

  this.MyPush = function (value) {
    this.heap.push(value);
    let currentIndex = this.heap.length - 1;
    while (currentIndex > 1) {
      let parentIndex = Math.floor(currentIndex / 2);
      if (this.heap[currentIndex][1] < this.heap[parentIndex][1]) {
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
        if (this.heap[rightChildIndex][1] < this.heap[leftChildIndex][1])
          minChildIndex = rightChildIndex;
      }

      if (this.heap[currentIndex][1] > this.heap[minChildIndex][1]) {
        let temp = this.heap[currentIndex];
        this.heap[currentIndex] = this.heap[minChildIndex];
        this.heap[minChildIndex] = temp;
        currentIndex = minChildIndex;
      } else break;
    }

    return returnValue;
  };
}
