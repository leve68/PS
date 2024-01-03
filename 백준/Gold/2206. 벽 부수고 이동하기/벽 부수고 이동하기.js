const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const inputNum = num.split(" ").map((x) => Number(x));
const n = inputNum[0];
const m = inputNum[1];

let answerMap = input.map((v) =>
  v.split("").map((x) => {
    if (x === "1") return -1;
    else return Number(x);
  })
);
let map = input.map((v) =>
  v.split("").map((x) => {
    if (x === "1") return -1;
    else return Number(x);
  })
);
let mapFromEnd = input.map((v) =>
  v.split("").map((x) => {
    if (x === "1") return -1;
    else return Number(x);
  })
);


//[0][0] => [n-1][m-1]
//출발점과 끝점에서 bfs
//벽을 모두 검사하고 인접한 두 값이 최소인 값 리턴
const dRow = [1, 0, -1, 0];
const dCol = [0, -1, 0, 1];
let minDif = 100000000;
let maxDif = -1;
let curDif = -1;
let breakThis = [];

map = BFS([0, 0], map);

if (map[n - 1][m - 1] === 0) {
  mapFromEnd = BFS([n - 1, m - 1], mapFromEnd);
  //도착 못하게 하는 벽을 부숴야 하는 경우
  //한쪽만 bfs한 맵에서 0과 숫자를 끼는 -1모두 찾기
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      if (map[i][j] === -1) {
        let aroundFromStart = [];
        let aroundFromEnd = [];
        for (let k = 0; k < 4; k++) {
          let nextCol = i + dCol[k];
          let nextRow = j + dRow[k];
          if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n) {
            if (map[nextCol][nextRow] !== -1)
              if (
                map[nextCol][nextRow] !== 0 &&
                mapFromEnd[nextCol][nextRow] === 0
              )
                aroundFromStart.push(map[nextCol][nextRow]);
            if (
              map[nextCol][nextRow] === 0 &&
              mapFromEnd[nextCol][nextRow] !== 0
            )
              aroundFromEnd.push(mapFromEnd[nextCol][nextRow]);
          }
        }
        if (aroundFromStart.length > 0 && aroundFromEnd.length > 0) {
          curDif = Math.min(...aroundFromStart) + Math.min(...aroundFromEnd);
          if (curDif < minDif) {
            minDif = curDif;
            breakThis = [i, j];
          }
        }
      }
    }
  }
} else {
  //한번에 도착가능한 경우
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      if (map[i][j] === -1) {
        let around = [];
        for (let k = 0; k < 4; k++) {
          let nextCol = i + dCol[k];
          let nextRow = j + dRow[k];
          if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n) {
            if (map[nextCol][nextRow] !== -1)
              around.push(map[nextCol][nextRow]);
          }
        }
        if (around.length >= 2) {
          curDif = Math.max(...around) - Math.min(...around);
          if (curDif > maxDif) {
            maxDif = curDif;
            breakThis = [i, j];
          }
        }
      }
    }
  }
}

if (breakThis.length === 0) {
  //부수지 않는 경우
  if (map[n - 1][m - 1] === 0) console.log(-1);
  else console.log(map[n - 1][m - 1]);
} else {
  //부수는 경우
  answerMap[breakThis[0]][breakThis[1]] = 0;
  answerMap = BFS([0, 0], answerMap);
  console.log(answerMap[n - 1][m - 1]);
}

//BFS
function BFS(root, map) {
  const dRow = [1, 0, -1, 0];
  const dCol = [0, -1, 0, 1];
  let myQueue = new Queue();
  map[root[0]][root[1]] = 1;
  myQueue.myPush(root);

  while (myQueue.size > 0) {
    let current = myQueue.myPop();
    let curRow = current[1];
    let curCol = current[0];

    for (let i = 0; i < 4; i++) {
      let nextRow = curRow + dRow[i];
      let nextCol = curCol + dCol[i];
      if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n) {
        if (map[nextCol][nextRow] === 0) {
          map[nextCol][nextRow] = map[curCol][curRow] + 1;
          myQueue.myPush([nextCol, nextRow]);
        }
      }
    }
  }
  return map;
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
