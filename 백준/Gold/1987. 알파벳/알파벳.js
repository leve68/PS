const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let setMap = n.split(" ").map((x) => Number(x));
let map = input.map((v) => v.split(""));

let r = setMap[0];
let c = setMap[1];

let visitedSet = Array(26).fill(false);
let dRow = [1, 0, -1, 0];
let dCol = [0, -1, 0, 1];
let count = 0;
let max = 0;

let start = [0, 0];
DFS(start);
console.log(max);

function DFS(root) {
  count++;
  let rootCol = root[0];
  let rootRow = root[1];
  visitedSet[alphabetToAscii(map[rootCol][rootRow])] = true;
  if (count > max) max = count;

  for (let i = 0; i < 4; i++) {
    let nextCol = rootCol + dCol[i];
    let nextRow = rootRow + dRow[i];

    if (nextCol >= 0 && nextCol < r && nextRow >= 0 && nextRow < c)
      if (!visitedSet[alphabetToAscii(map[nextCol][nextRow])]) {
        DFS([nextCol, nextRow]);
      }
  }

  visitedSet[alphabetToAscii(map[rootCol][rootRow])] = false;
  count--;
}

function alphabetToAscii(char) {
  return char.charCodeAt(0) - 65;
}
