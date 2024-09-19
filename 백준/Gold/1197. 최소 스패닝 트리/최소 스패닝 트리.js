const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (line) => {
  input.push(line.split(" ").map((v) => Number(v)));
  if (input.length === input[0][1] + 1) {
    rl.close();
  }
});

rl.on("close", () => {
  const vertexCount = input[0][0];
  const edgeCount = input[0][1];
  input.shift();
  const map = getEdgeMap(vertexCount, input);
  const visited = Array(map.length).fill(false);

  //1번에서 시작해서 방문하지 않은 vertex로 연결된 edge 중 가중치가 작은것 선택
  //우선순위큐 사용

  let answer = 0;
  let minHeap = new MinHeap();
  for (let i = 0; i < map[1].length; i++) {
    minHeap.MyPush(map[1][i]);
  }
  visited[1] = true;
  while (minHeap.heap.length > 1) {
    let current = minHeap.MyPop();
    if (visited[current[0]]) continue;

    for (let i = 0; i < map[current[0]].length; i++) {
      minHeap.MyPush(map[current[0]][i]);
    }

    answer += current[1];
    visited[current[0]] = true;
  }

  console.log(answer);
  process.exit();
});

function getEdgeMap(vertexCount, edgeList) {
  let map = Array.from({ length: vertexCount + 1 }, () => []);
  edgeList.forEach(([v1, v2, weight]) => {
    map[v1].push([v2, weight]);
    map[v2].push([v1, weight]);
  });

  return map;
}

//우선순위큐
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
