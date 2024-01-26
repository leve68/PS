const [num, ...map] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = num[0];
//파이프 최초 위치: [0,0], [0,1]
//파이프 도착 위치: 1) [n-1,n-1], [n-2,n-1]
//               : 2) [n-1,n-1], [n-1,n-2]
//               : 3) [n-1,n-1], [n-2,n-2]
//빈칸은 0, 벽면은 1

let answer = 0;
if (map[n - 1][n - 1] !== 1) moveRow([0, 1]);
console.log(answer);

function moveRow([curCol, curRow]) {
  if (curCol === n - 1 && curRow === n - 1) {
    answer++;
    return;
  }

  if (curRow + 1 < n && map[curCol][curRow + 1] === 0) {
    moveRow([curCol, curRow + 1]);
    if (curCol + 1 < n) {
      if (map[curCol + 1][curRow] === 0 && map[curCol + 1][curRow + 1] === 0)
        moveDig([curCol + 1, curRow + 1]);
    }
  }
}

function moveCol([curCol, curRow]) {
  if (curCol === n - 1 && curRow === n - 1) {
    answer++;
    return;
  }

  if (curCol + 1 < n && map[curCol + 1][curRow] === 0) {
    moveCol([curCol + 1, curRow]);
    if (curRow + 1 < n) {
      if (map[curCol][curRow + 1] === 0 && map[curCol + 1][curRow + 1] === 0)
        moveDig([curCol + 1, curRow + 1]);
    }
  }
}

function moveDig([curCol, curRow]) {
  if (curCol === n - 1 && curRow === n - 1) {
    answer++;
    return;
  }

  if (curCol + 1 < n && map[curCol + 1][curRow] === 0) {
    moveCol([curCol + 1, curRow]);
  }
  if (curRow + 1 < n && map[curCol][curRow + 1] === 0) {
    moveRow([curCol, curRow + 1]);
    if (curCol + 1 < n) {
      if (map[curCol + 1][curRow] === 0 && map[curCol + 1][curRow + 1] === 0)
        moveDig([curCol + 1, curRow + 1]);
    }
  }
}
