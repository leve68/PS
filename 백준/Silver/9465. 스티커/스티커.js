const [testCount, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

let answer = [];
for (let i = 0; i < testCount * 3; i += 3) {
  let map = [input[i + 1]];
  let n = input[i][0];
  map.push(input[i + 2]);
  answer.push(GetMax(n, map));
}
console.log(answer.join("\n"));

function GetMax(n, map) {
  if (n === 1) return Math.max(map[0][0], map[1][0]);
  const dp = Array.from({ length: 2 }, () => Array(n).fill(0));
  dp[0][0] = map[0][0];
  dp[1][0] = map[1][0];
  dp[0][1] = dp[1][0] + map[0][1];
  dp[1][1] = dp[0][0] + map[1][1];

  for (let i = 2; i < n; i++) {
    dp[0][i] = Math.max(dp[1][i - 1] + map[0][i], dp[1][i - 2] + map[0][i]);
    dp[1][i] = Math.max(dp[0][i - 1] + map[1][i], dp[0][i - 2] + map[1][i]);
  }
  return Math.max(...dp.flat());
}
