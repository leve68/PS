const [size, start, ...input] = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = size[0];
const m = size[1];

const r = start[0];
const c = start[1];
const d = start[2];
//0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽

//current 칸 청소

//case1: 주변에 빈칸 없는 경우
//한칸 후진 => 불가능하면 중지

//case2: 주변에 빈칸 있는 경우
//반시계 회전 => 빈칸이면 전진

let count = 0;
let current = [r, c, d];

const dCol = [-1, 0, 1, 0];
const dRow = [0, 1, 0, -1];

while (true) {
  let curCol = current[0];
  let curRow = current[1];
  let curDir = current[2];

  // 현재 위치 청소
  if (input[curCol][curRow] === 0) {
    count++;
    input[curCol][curRow] = 2;
  }

  let isBlank = false;
  // 주변 검사
  for (let i = 0; i < 4; i++) {
    if (input[curCol + dCol[i]][curRow + dRow[i]] === 0) {
      isBlank = true;
      break;
    }
  }

  if (!isBlank) {
    //case1
    let backDir = (curDir + 2) % 4; // 후진 방향
    let backCol = curCol + dCol[backDir];
    let backRow = curRow + dRow[backDir];
    if (input[backCol][backRow] === 1) break; // 뒤가 벽인 경우 멈춤
    current = [backCol, backRow, curDir];
  } else {
    //case2
    let newDir = (curDir + 3) % 4; // 반시계 방향으로 회전
    let nextCol = curCol + dCol[newDir];
    let nextRow = curRow + dRow[newDir];
    if (input[nextCol][nextRow] === 0) {
      current = [nextCol, nextRow, newDir];
    } else {
      current = [curCol, curRow, newDir];
    }
  }
}

console.log(count);
