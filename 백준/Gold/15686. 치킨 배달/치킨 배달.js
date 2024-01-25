const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

//x1,y1 -> x2,y2 : |x1-x2|+|y1-y2|
//0: 빈칸, 1: 집, 2: 치킨집
//존재하는 치킨집을 m개 남기고 삭제 => 최대 도시 치킨 거리

const n = num[0];
const m = num[1];

let store = [];
let house = [];
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (input[i][j] === 1) {
      house.push([i, j]);
    } else if (input[i][j] === 2) {
      store.push([i, j]);
    }
  }
}

let visited = Array(store.length).fill(false);
let newStore = [];
let min = 1000000000;

DFS(n, m, 0, 0);
console.log(min);

function DFS(n, m, count, last) {
  if (count <= m) {
    let current = GetResult(house, newStore);
    if (min > current) min = current;
    if (count === m) return;
  }

  for (let i = last; i < store.length; i++) {
    if (!visited[i]) {
      visited[i] = true;
      newStore[count] = store[i];

      DFS(n, m, count + 1, i);

      visited[i] = false;
    }
  }
}

//최소 도시치킨거리 구하기
function GetResult(houseArr, storeArr) {
  let result = [];
  for (let i = 0; i < houseArr.length; i++) {
    let minDistance = 1000000000;
    for (let j = 0; j < storeArr.length; j++) {
      let distance = GetDistance(houseArr[i], storeArr[j]);
      if (minDistance > distance) {
        minDistance = distance;
      }
    }
    result.push(minDistance);
  }
  return result.reduce((sum, element) => sum + element, 0);
}

//치킨거리 구하기
function GetDistance(start, end) {
  return Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
}
