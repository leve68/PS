const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n")
  .map((x) => Number(x));

const testCount = input[0];
let answer = [];
let Dp = [1, 1, 1, 2, 2];
for (let i = 1; i <= testCount; i++) {
  answer.push(GetP(input[i], Dp));
}
console.log(answer.join("\n"));

// 1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12
// N = (N-1) + (N-5)
function GetP(n, Dp) {
  for (let i = 5; i < n; i++) {
    if (!Dp[i]) {
      Dp[i] = Dp[i - 1] + Dp[i - 5];
    }
  }
  return Dp[n - 1];
}
