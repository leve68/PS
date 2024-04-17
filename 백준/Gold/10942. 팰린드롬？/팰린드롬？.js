const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = input[0][0];
const array = input[1];
const qCount = input[2][0];

//dp[start][end] = 1 이면 필렌드롬 0 이면 아님
let dp = Array.from({ length: n }, () => Array(n));
//초기값
dp[0][0] = 1;

for (let i = 1; i < n; i++) {
  //1개
  dp[i][i] = 1;
  //2개
  dp[i - 1][i] = array[i - 1] === array[i] ? 1 : 0;
  //3개 이상 홀수 개
  for (let j = 1; ; j++) {
    if (i - j < 0 || i + j >= n) break;
    if (dp[i - j + 1][i + j - 1] === 1)
      dp[i - j][i + j] = array[i - j] === array[i + j] ? 1 : 0;
    else dp[i - j][i + j] = 0;
  }
  //3개 이상 짝수 개
  for (let j = 1; ; j++) {
    if (i - j - 1 < 0 || i + j >= n) break;
    if (dp[i - j][i + j - 1] === 1)
      dp[i - j - 1][i + j] = array[i - j + 1] === array[i + j] ? 1 : 0;
    else if (dp[i - j][i + j - 1] === 0) dp[i - j - 1][i + j] = 0;
  }
}

let answer = [];
for (let i = 3; i < 3 + qCount; i++) {
  let start = input[i][0];
  let end = input[i][1];

  answer.push(dp[start - 1][end - 1]);
}
console.log(answer.join("\n"));
