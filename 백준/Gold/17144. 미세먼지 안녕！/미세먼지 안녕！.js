const [num, ...map] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

//map 크기 : r*c
const r = num[0];
const c = num[1];
const t = num[2];
let tempMap = Array.from({ length: r }, () => Array(c).fill(0));

//1초
//1. 4방향으로 미세먼지 확산(미세먼지/5 만큼 확산)
//2. 확산 후 남은 미세먼지(미세먼지 - 미세먼지/5 * 확산된 방향 수)
//3. 공기청정기 작동 바람 방향으로 미세먼지 한칸씩 이동
//4. 공기청정기로 들어간 미세먼지는 정화된다.

//-1: 공기청정기 위치

let position = [];
let time = 0;

//전체 순회
while (time < t) {
  //0. temp = map
  for (let i = 0; i < r; i++) {
    for (let j = 0; j < c; j++) {
      if (map[i][j] === -1) {
        position.push(i);
        map[i][j] = 0;
      }
      tempMap[i][j] = map[i][j];
    }
  }
  //1. 확산된 값을 temp 에 저장
  for (let i = 0; i < r; i++) {
    for (let j = 0; j < c; j++) {
      if (map[i][j] > 0) {
        Diffusion(i, j);
      }
      map[i][j] = 0; //map 초기화
    }
  }

  //2. temp 를 공기청정기 바람에 따라 순환시킨 값을 map에 저장

  //0 < i < p[0] 일떄
  for (let i = 0; i < position[0]; i++) {
    map[i + 1][0] = tempMap[i][0];
    map[i][c - 1] = tempMap[i + 1][c - 1];
    if (i === position[0]) map[i][0] = 0;
    for (let j = 1; j < c - 1; j++) {
      map[i][j] = tempMap[i][j];
    }
  }

  //p[1] <= i < r-1 일떄
  for (let i = position[1]; i < r - 1; i++) {
    map[i][0] = tempMap[i + 1][0];
    map[i + 1][c - 1] = tempMap[i][c - 1];
    if (i === position[1]) map[i][0] = 0;
    for (let j = 1; j < c - 1; j++) {
      map[i][j] = tempMap[i][j];
    }
  }

  for (let j = 0; j < c - 1; j++) {
    map[0][j] = tempMap[0][j + 1];
    map[position[0]][j + 1] = tempMap[position[0]][j];
    map[position[1]][j + 1] = tempMap[position[1]][j];
    map[r - 1][j] = tempMap[r - 1][j + 1];
  }

  map[position[0]][0] = 0;
  map[position[1]][0] = 0;

  time++;
}

let sum = 0;
for (let i = 0; i < r; i++) {
  for (let j = 0; j < c; j++) {
    sum += map[i][j];
  }
}

console.log(sum);

function Diffusion(i, j) {
  let count = 0;
  let di = [0, -1, 0, 1];
  let dj = [1, 0, -1, 0];
  for (let k = 0; k < 4; k++) {
    if (i + di[k] >= 0 && i + di[k] < r && j + dj[k] >= 0 && j + dj[k] < c) {
      if (
        (i + di[k] === position[0] || i + di[k] === position[1]) &&
        j + dj[k] === 0
      )
        continue;
      tempMap[i + di[k]][j + dj[k]] =
        tempMap[i + di[k]][j + dj[k]] + Math.floor(map[i][j] / 5);
      count++;
    }
  }
  tempMap[i][j] = tempMap[i][j] - Math.floor(map[i][j] / 5) * count;
}
