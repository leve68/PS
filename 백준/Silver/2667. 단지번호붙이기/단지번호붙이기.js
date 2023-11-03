const [N, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const n = Number(N);

let map = [];
for (let i = 0; i < inputString.length; i++) {
  map[0] = Array(n + 2).fill(0);
  map[i + 1] = ("0" + inputString[i] + "0").split("").map((x) => Number(x));
  map[n + 1] = Array(n + 2).fill(0);
}

let count = 2;
for (let i = 1; i <= n; i++) {
  for (let j = 1; j <= n; j++) {
    if (map[i][j] === 1) {
      map = ChangeMap(map, i, j, count);
      count++;
    }
  }
}

//map을 변경함 : 1과 연결된 값들을 num으로 바꿈
function ChangeMap(map, y, x, num) {
  let dx = [1, 0, -1, 0];
  let dy = [0, -1, 0, 1];
  map[y][x] = num;

  for (let i = 0; i < 4; i++) {
    if (map[y + dy[i]][x + dx[i]] === 1) {
      map = ChangeMap(map, y + dy[i], x + dx[i], num);
    }
  }
  return map;
}

let temp = [];
map.forEach((element) => {
  temp = temp.concat(element);
});

if (Math.max(...temp) === 0) {
  console.log(0);
} else {
  console.log(Math.max(...temp) - 1);

  let answer = [];
  for (let i = 2; ; i++) {
    let count = 0;
    temp.forEach((element) => {
      if (i === element) count++;
    });
    if (count === 0) break;
    answer.push(count);
  }
  answer.sort((a, b) => a - b);
  console.log(answer.join("\n"));
}
