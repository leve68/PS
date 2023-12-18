const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const INF = 1000000000;
const n = input[0][0];
const e = input[0][1];
const vertex1 = input[e + 1][0];
const vertex2 = input[e + 1][1];
let edgeMap = Array(n + 1);

for (let i = 0; i <= n; i++) {
  edgeMap[i] = Array(n + 1).fill(INF);
}
for (let i = 1; i <= e; i++) {
  edgeMap[input[i][0]][input[i][1]] = input[i][2];
  edgeMap[input[i][1]][input[i][0]] = input[i][2];
}

let answer = [];
//1=>v1, v1=>v2, v2=>N
let d1 = Dijkstra(1, vertex1);
let d2 = Dijkstra(vertex1, vertex2);
let d3 = Dijkstra(vertex2, n);
if (d1 === -1 || d2 === -1 || d3 === -1) {
  answer.push(INF);
} else answer.push(d1 + d2 + d3);

//1=>v2, v2=>v1, v1=>N
d1 = Dijkstra(1, vertex2);
d2 = Dijkstra(vertex2, vertex1);
d3 = Dijkstra(vertex1, n);
if (d1 === -1 || d2 === -1 || d3 === -1) {
  answer.push(INF);
} else answer.push(d1 + d2 + d3);

//정답 출력
if (answer[0] === INF && answer[1] === INF) console.log(-1);
else console.log(Math.min(...answer));

//다익스트라 알고리즘 사용하기
function Dijkstra(start, end) {
  if (start === end) return 0;
  let minHeap = new MinHeap();
  let visited = Array(n + 1).fill(false);
  let distance = Array(n + 1).fill(INF);

  //초기값 세팅
  for (let i = 1; i <= n; i++) {
    distance[i] = edgeMap[start][i];
    if (edgeMap[start][i] <= 1000) {
      minHeap.insert([edgeMap[start][i], i]);
    }
  }
  visited[start] = true;
  distance[start] = 0;

  //heap이 빌때까지 반복
  while (minHeap.heap.length - 1 > 0) {
    //가중치가 가장 작은 vertex 선택
    let currentArr = minHeap.extract();
    let currentVertex = currentArr[1];
    if (visited[currentArr]) continue;

    visited[currentVertex] = true;
    if (distance[currentArr] > currentArr[0])
      distance[currentVertex] = currentArr[0];
    for (let j = 1; j <= n; j++) {
      if (!visited[j]) {
        if (distance[j] > distance[currentVertex] + edgeMap[currentVertex][j]) {
          distance[j] = distance[currentVertex] + edgeMap[currentVertex][j];
        }
        if (edgeMap[currentVertex][j] <= 1000)
          minHeap.insert([
            distance[currentVertex] + edgeMap[currentVertex][j],
            j,
          ]);
      }
    }
    //end까지의 거리가 확정되면 리턴
    if (visited[end]) return distance[end];
  }
  return -1;
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
