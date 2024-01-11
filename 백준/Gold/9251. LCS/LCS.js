const [input1, input2] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(""));

//2차원 배열 dp로 해결
//한번에 크기를 설정한 2차원 배열 선언하기
const dp = Array.from({ length: input1.length + 1 }, () =>
  Array(input2.length + 1).fill(0)
);

for (let i = 1; i <= input1.length; i++) {
  for (let j = 1; j <= input2.length; j++) {
    if (input1[i - 1] === input2[j - 1]) {
      dp[i][j] = dp[i - 1][j - 1] + 1;
    } else {
      dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
    }
  }
}

console.log(dp[input1.length][input2.length]);
