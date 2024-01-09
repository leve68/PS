const input = require("fs").readFileSync("/dev/stdin").toString().trim();
let num = Number(input);

triNum = num / 3;
num = Math.log2(triNum);

let triangle = Array(3);
triangle[0] = "  *   ";
triangle[1] = " * *  ";
triangle[2] = "***** ";

let Gap = "   ";

dp = [];
dp[0] = triangle;
dp[1] = [
  Gap + dp[0][0] + Gap,
  Gap + dp[0][1] + Gap,
  Gap + dp[0][2] + Gap,
  dp[0][0] + dp[0][0],
  dp[0][1] + dp[0][1],
  dp[0][2] + dp[0][2],
];
for (let i = 2; i <= 10; i++) {
  dp[i] = [];
  Gap = Gap + Gap;
  for (let j = 0; j < dp[i - 1].length; j++) {
    dp[i].push(Gap + dp[i - 1][j] + Gap);
  }
  for (let j = 0; j < dp[i - 1].length; j++) {
    dp[i].push(dp[i - 1][j] + dp[i - 1][j]);
  }

  if (i === num) break;
}
console.log(dp[num].join("\n"));

//0 : tri
//1 : gap 0 gap
//    0 0
//2 : gap gap 1 gap gap
//    gap gap 1 gap gap
//    1 1
//    1 1
//3 : gap*4 2 gap*4
//    gap*4 2 gap*4
//    gap*4 2 gap*4
//    gap*4 2 gap*4
//    2 2
//    2 2
//    2 2
//    2 2
