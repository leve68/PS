const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

//visited도 동일하게 생성해주기위해 2개의 배열생성
const inputRead = input.map((v) =>
  ("0 " + v + " 0").split(" ").map((x) => Number(x))
);
let tempArr = input.map((v) =>
  ("0 " + v + " 0").split(" ").map((x) => Number(x))
);
const n = inputRead[0][1]; //세로크기
const m = inputRead[0][2]; //가로크기
let temp = Array(m + 2).fill(0);

//주변을 0으로 감싼 map 생성
let map = [temp];
let visited = [temp];
for (let i = 1; i < n + 1; i++) {
  map[i] = inputRead[i];
  visited[i] = tempArr[i];
}
map[n + 1] = temp;
visited[n + 1] = temp;

let answer = 0;
for (let i = 1; i < n + 1; i++) {
  for (let j = 1; j < m + 1; j++) {
    let sum = 0;
    let count = 1;
    DFS(i, j, sum, count);
    Cross(i, j);
  }
}
console.log(answer);

function DFS(curY, curX, sum, count) {
  sum += map[curY][curX];
  if (count === 4) {
    if (sum > answer) answer = sum;
    return;
  }
  visited[curY][curX] = 0;
  const dx = [1, 0, -1, 0];
  const dy = [0, -1, 0, 1];

  for (let i = 0; i < 4; i++) {
    if (visited[curY + dy[i]][curX + dx[i]] !== 0) {
      DFS(curY + dy[i], curX + dx[i], sum, count + 1);
    }
  }
  visited[curY][curX] = 1;
}

function Cross(curY, curX) {
  const dx = [1, 0, -1, 0];
  const dy = [0, -1, 0, 1];
  let nextVertex = [];
  for (let i = 0; i < 4; i++) {
    if (map[curY + dy[i]][curX + dx[i]] !== 0) {
      nextVertex.push(map[curY + dy[i]][curX + dx[i]]);
    }
  }
  if (nextVertex.length === 4) {
    nextVertex.sort((a, b) => b - a);
    nextVertex.pop();
    let tempAnswer = map[curY][curX];
    nextVertex.forEach((num) => {
      tempAnswer += num;
    });
    if (tempAnswer > answer) answer = tempAnswer;
    return;
  } else if (nextVertex.length === 3) {
    let tempAnswer = map[curY][curX];
    nextVertex.forEach((num) => {
      tempAnswer += num;
    });
    if (tempAnswer > answer) answer = tempAnswer;
    return;
  } else return;
}
