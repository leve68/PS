const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

let n = input[0][0];

if (n === 1) {
  console.log(input[1][0]);
} else {
  let dp = [0];
  dp[1] = [input[1][0]];
  dp[2] = [input[2][0] + input[1][0], input[2][1] + input[1][0]];
  for (let i = 3; i <= n; i++) {
    //양 끝 점
    dp[i] = [dp[i - 1][0] + input[i][0]];
    dp[i][i - 1] = dp[i - 1][i - 2] + input[i][i - 1];
    for (let j = 1; j < i - 1; j++) {
      dp[i][j] = input[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
    }
  }

  console.log(Math.max(...dp[n]));
}
