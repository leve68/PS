const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const count = num[0];
const maxWeight = num[1];

let dp = Array.from({ length: count }, () => Array(maxWeight + 1).fill(0));

//배낭문제
for (let i = 0; i <= maxWeight; i++) {
  if (i >= input[0][0]) {
    dp[0][i] = input[0][1];
  }
}

for (let i = 1; i < count; i++) {
  for (let j = 0; j <= maxWeight; j++) {
    if (j - input[i][0] >= 0)
      dp[i][j] = Math.max(
        dp[i - 1][j],
        input[i][1] + dp[i - 1][j - input[i][0]]
      );
    else dp[i][j] = dp[i - 1][j];
  }
}
console.log(dp[count - 1][maxWeight]);
