const [num, input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const n = Number(num);
let map = input.split(" ").map((x) => Number(x));

let dp = Array(n).fill(1);
//dp
for (let i = 1; i < n; i++) {
  for (let j = 0; j <= i; j++) {
    if (map[i] > map[j] && dp[j] + 1 > dp[i]) {
      dp[i] = dp[j] + 1;
    }
  }
}
console.log(Math.max(...dp));
