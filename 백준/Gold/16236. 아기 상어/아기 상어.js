const [num, ...map] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = num[0];

//9: 아기상어의 위치, 아기상어의 크기: 2
//물고기크기>아기상어: 지나갈 수 없음
//물고기크기=아기상어: 지나갈 수 있음
//물고기크기<아기상어: 먹을 수 있음
//먹을 수 있는 물고기가 있다면 가장 가까운 물고기를 먹으러 감
//거리가 같으면 좌상단 우선

let start;
let fishCount = 0;
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (map[i][j] === 9) {
      start = [i, j];
      map[i][j] = 0;
    } else if (map[i][j] !== 0) {
      fishCount++;
    }
  }
}

let myQueue = new Queue();
let currentSize = 2;
let eatenFishCount = 0;
let distance = Array.from({ length: n }, () => Array(n).fill(-1));
let answerDistance = 0;

while (fishCount > 0) {
  start = BFS(start);
  if (!start) break;
  if (eatenFishCount === currentSize) {
    eatenFishCount = 0;
    currentSize++;
  }
}
console.log(answerDistance);

function BFS(root) {
  let answerArr = [];
  let minDistance = 500;
  const dCol = [-1, 0, 0, 1];
  const dRow = [0, -1, 1, 0];

  myQueue.myPush(root);
  distance[root[0]][root[1]] = 0;

  while (myQueue.size > 0) {
    let current = myQueue.myPop();
    let curCol = current[0];
    let curRow = current[1];
    for (let i = 0; i < 4; i++) {
      let nextCol = curCol + dCol[i];
      let nextRow = curRow + dRow[i];
      if (nextCol >= 0 && nextCol < n && nextRow >= 0 && nextRow < n) {
        //그냥 지나가는 경우
        if (
          (map[nextCol][nextRow] === 0 ||
            map[nextCol][nextRow] === currentSize) &&
          distance[nextCol][nextRow] < 0
        ) {
          myQueue.myPush([nextCol, nextRow]);
          distance[nextCol][nextRow] = distance[curCol][curRow] + 1;
        }

        //먹을 수 있는 경우
        if (
          map[nextCol][nextRow] < currentSize &&
          distance[nextCol][nextRow] < 0
        ) {
          distance[nextCol][nextRow] = distance[curCol][curRow] + 1;
          if (minDistance >= distance[nextCol][nextRow]) {
            minDistance = distance[nextCol][nextRow];
            answerArr.push([nextCol, nextRow]);
          }
        }
      }
    }
  }
  if (answerArr.length !== 0) {
    answerArr.sort((a, b) => {
      if (a[0] !== b[0]) {
        return a[0] - b[0];
      } else {
        return a[1] - b[1];
      }
    });
    answerDistance += distance[answerArr[0][0]][answerArr[0][1]];
    map[answerArr[0][0]][answerArr[0][1]] = 0;
    eatenFishCount++;
    fishCount--;
    //큐 초기화
    while (myQueue.size > 0) {
      myQueue.myPop();
    }
    //distance 초기화
    distance.forEach((row) => row.fill(-1));
    return [answerArr[0][0], answerArr[0][1]];
  }
}

//BFS사용을 위한 큐
function Node(value) {
  this.value = value;
  this.next = null;
}

function Queue() {
  this.front = null;
  this.back = null;
  this.size = 0;

  this.myPush = function (value) {
    const node = new Node(value);
    if (this.size === 0) {
      this.front = node;
      this.back = node;
    } else {
      this.back.next = node;
      this.back = node;
    }
    this.size++;
  };

  this.myPop = function () {
    let node;
    if (this.size === 0) {
      console.log("error");
    } else {
      node = this.front;
      this.front = this.front.next;
      this.size--;
      return node.value;
    }
  };
}
