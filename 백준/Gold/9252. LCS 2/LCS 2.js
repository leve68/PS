const [input1, input2] = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(""));

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

let answerLength = dp[input1.length][input2.length];
let answer = Array(answerLength);
let idx = answerLength - 1;
let col = input1.length;
let row = input2.length;
if (answerLength === 0) console.log(0);
else {
  while (!answer[0]) {
    while (dp[col][row] === dp[col - 1][row]) {
      col--;
    }
    while (dp[col][row] === dp[col][row - 1]) {
      row--;
    }

    answer[idx] = input1[col - 1];
    idx--;
    col--;
    row--;
  }

  console.log(answerLength);
  console.log(answer.join(""));
}

//https://velog.io/@emplam27/알고리즘-그림으로-알아보는-LCS-알고리즘-Longest-Common-Substring와-Longest-Common-Subsequence
