const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

const n = input[0][0]; //보석개수
const k = input[0][1]; //가방개수
let infoArr = [];
for (let i = 1; i < n + 1; i++) {
  //보석정보
  infoArr.push(input[i]);
}

let maxWeightArr = [];
for (let i = n + 1; i < n + k + 1; i++) {
  //가방에 담을 수 있는 최대 무게
  maxWeightArr.push(input[i][0]);
}

//무게 작은순 정렬
infoArr.sort((a, b) => a[0] - b[0]);
maxWeightArr.sort((a, b) => a - b);

let sumWeight = 0;
let tempIndex = 0;
let currentQueue = new MaxHeap();
for (let i = 0; i < k; i++) {
  //작은 가방부터 i번째 가방에 넣을 보석 찾기
  sumWeight += FillBackpack(i);
}
console.log(sumWeight);

function FillBackpack(index) {
  while (tempIndex < n && infoArr[tempIndex][0] <= maxWeightArr[index]) {
    currentQueue.MyPush(infoArr[tempIndex]);
    tempIndex++;
  }

  if (currentQueue.heap.length === 1) return 0;
  else return currentQueue.MyPop()[1];
}

//MaxHeap 구현
function MaxHeap() {
  this.heap = [0];

  this.MyPush = function (value) {
    if (this.heap.length === 1) {
      this.heap[1] = value;
    } else {
      //마지막 자리에 push
      let currentIndex = this.heap.length;
      this.heap[currentIndex] = value;

      while (currentIndex > 1) {
        let parentIndex = Math.floor(currentIndex / 2);
        if (this.heap[parentIndex][1] < this.heap[currentIndex][1]) {
          this.heap[currentIndex] = this.heap[parentIndex];
          this.heap[parentIndex] = value;

          currentIndex = parentIndex;
        } else break;
      }
    }
  };

  this.MyPop = function () {
    if (this.heap.length === 1) return null;
    else if (this.heap.length === 2) {
      let value = this.heap.pop();
      return value;
    } else {
      //가장 마지막 원소를 root로 올림
      let currentIndex = 1;
      let value = this.heap[1];
      this.heap[1] = this.heap.pop();

      while (currentIndex * 2 < this.heap.length) {
        let leftChildIndex = currentIndex * 2;
        let rightChildIndex = currentIndex * 2 + 1;
        let largerChildIndex = leftChildIndex;

        // 오른쪽 자식이 있고, 오른쪽 자식이 왼쪽 자식보다 크다면 큰 자식을 선택
        if (
          rightChildIndex < this.heap.length &&
          this.heap[rightChildIndex][1] > this.heap[leftChildIndex][1]
        ) {
          largerChildIndex = rightChildIndex;
        }

        // 현재 노드가 자식 노드보다 작다면 위치를 교환
        if (this.heap[currentIndex][1] < this.heap[largerChildIndex][1]) {
          let temp = this.heap[currentIndex];
          this.heap[currentIndex] = this.heap[largerChildIndex];
          this.heap[largerChildIndex] = temp;

          currentIndex = largerChildIndex;
        } else {
          break;
        }
      }

      return value;
    }
  };
}
