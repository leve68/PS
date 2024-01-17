const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = num[0];
const m = num[1];

let map = Array.from({ length: n }, () => Array(n));

//초기값 설정
map[0][0] = input[0][0];
for (let i = 1; i < n; i++) {
  map[0][i] = input[0][i] + map[0][i - 1];
  map[i][0] = input[i][0] + map[i - 1][0];
}

for (let i = 1; i < n; i++) {
  for (let j = 1; j < n; j++) {
    if (i > j) {
      map[i][j] =
        map[i - 1][j] + (map[i][j - 1] - map[i - 1][j - 1]) + input[i][j];
    } else {
      map[i][j] =
        map[i][j - 1] + (map[i - 1][j] - map[i - 1][j - 1]) + input[i][j];
    }
  }
}

let answer = [];
for (let i = n; i < n + m; i++) {
  answer.push(GetSum(input[i]));
}
console.log(answer.join("\n"));

function GetSum(arr) {
  let startCol = arr[0] - 1;
  let startRow = arr[1] - 1;
  let endCol = arr[2] - 1;
  let endRow = arr[3] - 1;

  if (startCol === 0 && startRow === 0) {
    return map[endCol][endRow];
  } else if (startCol === 0) {
    return map[endCol][endRow] - map[endCol][startRow - 1];
  } else if (startRow === 0) {
    return map[endCol][endRow] - map[startCol - 1][endRow];
  } else {
    return (
      map[endCol][endRow] -
      map[endCol][startRow - 1] -
      map[startCol - 1][endRow] +
      map[startCol - 1][startRow - 1]
    );
  }
}
