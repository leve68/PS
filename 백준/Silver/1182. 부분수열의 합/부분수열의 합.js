const [num, input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

const n = num[0];
const target = num[1];
let count = 0;
dfs(0, 0);
console.log(count);

function dfs(idx, sum) {
  if (idx >= n) {
    return;
  }

  //현재 값을 더함
  sum += input[idx];

  if (sum === target) {
    count += 1;
  }

  // 더한 값을 없애고
  dfs(idx + 1, sum - input[idx]);
  // 유지 하고
  dfs(idx + 1, sum);
}
