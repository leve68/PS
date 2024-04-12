const [num, ...input] = require("fs")
  .readFileSync(0,"utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = num[0];
let edgeMap = Array.from({ length: n }, () => []);

//edgeMap[시작점][끝점][거리]
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    edgeMap[i].push([
      i,
      j,
      Math.sqrt(
        Math.pow(input[i][0] - input[j][0], 2) +
          Math.pow(input[i][1] - input[j][1], 2)
      ),
    ]);
  }
}

let minHeap = new MinHeap();
let visited = Array(n).fill(false);
visited[0] = true;
//시작점 0
for (let i = 0; i < edgeMap[0].length; i++) {
  minHeap.MyPush(edgeMap[0][i]);
}

let answer = 0;
while (minHeap.heap.length > 1) {
  let current = minHeap.MyPop();
  let curStart = current[0];
  let curEnd = current[1];
  let curWeight = current[2];

  if (visited[curEnd]) continue;
  else {
    visited[curEnd] = true;
    answer += edgeMap[curStart][curEnd][2];
    for (let i = 0; i < edgeMap[curEnd].length; i++) {
      minHeap.MyPush([curEnd, i, edgeMap[curEnd][i][2]]);
    }
  }
}
console.log(answer.toFixed(2));

//Prim 사용하기 위함
//최소 힙 구현
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
