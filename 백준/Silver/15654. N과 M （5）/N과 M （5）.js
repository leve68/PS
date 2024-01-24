const [input, num] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));
const n = input[0];
const m = input[1];
num.sort((a, b) => a - b);
//num[0], num[n-1] 사이의 수 m개 선택

let visited = Array(n).fill(false);
let arr = [];
let answer = [];
DFS(n, m, 0);
console.log(answer.join("\n"));

function DFS(n, m, count) {
  if (count === m) {
    answer.push(arr.join(" "));
    return;
  }

  for (let i = 0; i < n; i++) {
    if (!visited[i]) {
      visited[i] = true;
      arr[count] = num[i];

      DFS(n, m, count + 1);

      visited[i] = false;
    }
  }
}
