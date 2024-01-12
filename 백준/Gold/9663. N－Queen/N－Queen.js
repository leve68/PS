const input = require("fs").readFileSync("/dev/stdin").toString().trim();

const n = Number(input);
//n*n 체스판 n개의 퀸

let map = Array.from({ length: n }, () => Array(n).fill(0));

let leftQeen;
let answer = 0;
for (let i = 0; i < n; i++) {
  leftQeen = n;
  GetCases([0, i]);
}
console.log(answer);

function GetCases(current) {
  let curCol = current[0];
  let curRow = current[1];
  let ChangedMap = [];

  leftQeen--;
  map[curCol][curRow] = 1;
  for (let i = curCol; i < n; i++) {
    if (map[i][curRow] === 0) ChangedMap.push([i, curRow]);
    map[i][curRow] = 1;
  }
  for (let i = 0; i < n; i++) {
    if (map[curCol][i] === 0) ChangedMap.push([curCol, i]);
    map[curCol][i] = 1;
  }
  for (let i = 1; i < n - curCol; i++) {
    if (curRow + i < n)
      if (map[curCol + i][curRow + i] === 0) {
        ChangedMap.push([curCol + i, curRow + i]);
        map[curCol + i][curRow + i] = 1;
      }
    if (curRow - i >= 0)
      if (map[curCol + i][curRow - i] === 0) {
        ChangedMap.push([curCol + i, curRow - i]);
        map[curCol + i][curRow - i] = 1;
      }
  }
  if (curCol === n - 1) answer++;
  else {
    for (let i = 0; i < n; i++) {
      if (map[curCol + 1][i] === 0) GetCases([curCol + 1, i]);
    }
  }

  leftQeen++;
  map[curCol][curRow] = 0;
  for (let i = 0; i < ChangedMap.length; i++) {
    map[ChangedMap[i][0]][ChangedMap[i][1]] = 0;
  }
}
