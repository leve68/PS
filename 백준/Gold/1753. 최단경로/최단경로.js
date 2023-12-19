const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

let vertexCount = input[0][0];
let edgeCount = input[0][1];
let startVertex = input[1][0];

let edgeWeight = [];
for (let i = 2; i < input.length; i++) {
  let start = input[i][0];
  let end = input[i][1];
  let weight = input[i][2];
  if (edgeWeight[start]) {
    edgeWeight[start].push([weight, end]);
  } else {
    edgeWeight[start] = [[weight, end]];
  }
}

let answer = Dijkstra(startVertex, edgeWeight);
console.log(answer.join("\n").replace(/1000000000/g, "INF"));

function Dijkstra(start, edgeMap) {
  let minHeap = new MinHeap();
  let visited = Array(vertexCount + 1).fill(false);
  let distance = Array(vertexCount + 1).fill(1000000000);

  //초기값 설정
  for (let i = 0; i < edgeMap[start].length; i++) {
    minHeap.insert([edgeMap[start][i][0], edgeMap[start][i][1]]);
  }
  visited[start] = true;
  distance[start] = 0;

  while (minHeap.heap.length - 1 > 0) {
    let curArr = minHeap.extract();
    let curVertex = curArr[1];
    let curWeight = curArr[0];

    if (visited[curVertex]) continue;
    visited[curVertex] = true;
    if (distance[curVertex] > curWeight) distance[curVertex] = curWeight;

    if (!edgeMap[curVertex]) continue;
    for (let i = 0; i < edgeMap[curVertex].length; i++) {
      let nextVertex = edgeMap[curVertex][i][1];
      let nextWeight = edgeMap[curVertex][i][0];
      if (!visited[nextVertex]) {
        if (distance[nextVertex] > distance[curVertex] + nextWeight) {
          distance[nextVertex] = distance[curVertex] + nextWeight;
        }
        minHeap.insert([distance[curVertex] + nextWeight, nextVertex]);
      }
    }
  }
  distance.shift();
  return distance;
}

//MinHeap 구현
function MinHeap() {
  this.heap = [0];

  this.insert = function (value) {
    //heap[currentIndex] = value
    let currentIndex = this.heap.length;
    this.heap.push(value);

    //부모 노드가 더 크면 자식과 자리 변경
    while (currentIndex > 1) {
      let parentIndex = Math.floor(currentIndex / 2);
      if (this.heap[parentIndex][0] > this.heap[currentIndex][0]) {
        let temp = this.heap[parentIndex];
        this.heap[parentIndex] = this.heap[currentIndex];
        this.heap[currentIndex] = temp;
      }
      currentIndex = parentIndex;
    }
  };

  this.extract = function () {
    //힙이 비어있음
    if (this.heap.length === 1) return -1;
    if (this.heap.length === 2) {
      return this.heap.pop();
    }

    //heap[0]에 출력값, heap[1]에 가장 끝에있는 리프 가져옴
    this.heap[0] = this.heap[1];
    this.heap[1] = this.heap.pop();

    //자식 노드들 중 더 작은 자식찾기
    let currentIndex = 1;
    while (currentIndex < this.heap.length) {
      let targetChildIndex = 0;
      let leftChildIndex = currentIndex * 2;
      let rightChildIndex = currentIndex * 2 + 1;
      if (
        this.heap[leftChildIndex] &&
        this.heap[currentIndex][0] > this.heap[leftChildIndex][0]
      ) {
        targetChildIndex = leftChildIndex;
      }
      if (
        this.heap[rightChildIndex] &&
        this.heap[leftChildIndex][0] > this.heap[rightChildIndex][0]
      ) {
        targetChildIndex = rightChildIndex;
      }

      //변경 가능한 자식이 있다면 자리 변경
      if (targetChildIndex === 0) break;
      else {
        let temp = this.heap[targetChildIndex];
        this.heap[targetChildIndex] = this.heap[currentIndex];
        this.heap[currentIndex] = temp;
      }
      currentIndex = targetChildIndex;
    }
    return this.heap[0];
  };
}
