const [input, num] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));
const n = input[0];
const m = input[1];
num.sort((a, b) => a - b);

//중복제거를 위해 집합사용

let visited = Array(n).fill(false);
let arr = [];
let answer = new Set();
DFS(n, m, 0);
console.log(Array.from(answer).join("\n"));

function DFS(n, m, count) {
  if (count === m) {
    answer.add(arr.join(" "));
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
