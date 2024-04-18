const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim();

const n = Number(input);

//길이가 n 이면서 0-9가 모두 등장하는 계단 수 구하기
//DP
//비트마스킹 => 1111111111 : 모두 방문

// 9 -> 0
// 10 -> 1
// 11 -> 3
// 12 -> 14
// 13 -> 37

let dp = Array.from({ length: n + 1 }, () =>
  Array.from({ length: 10 }, () => Array(parseInt(1111111111, 2) + 1).fill(0))
);

//초기값
for (let i = 1; i <= 9; i++) {
  dp[1][i][Math.pow(2, i)] = 1;
}

for (let i = 2; i <= n; i++) {
  for (let j = 0; j <= 9; j++) {
    for (let k = 0; k <= parseInt(1111111111, 2); k++) {
      if (j + 1 <= 9 && j - 1 >= 0) {
        if ((k | Math.pow(2, j)) === k)
          dp[i][j][k] =
            (dp[i - 1][j + 1][k - Math.pow(2, j)] +
              dp[i - 1][j - 1][k - Math.pow(2, j)] +
              dp[i - 1][j + 1][k] +
              dp[i - 1][j - 1][k]) %
            1000000000;
      } else if (j === 0) {
        if ((k | Math.pow(2, j)) === k)
          dp[i][j][k] =
            (dp[i - 1][j + 1][k - Math.pow(2, j)] + dp[i - 1][j + 1][k]) %
            1000000000;
      } else if (j === 9) {
        if ((k | Math.pow(2, j)) === k)
          dp[i][j][k] =
            (dp[i - 1][j - 1][k - Math.pow(2, j)] + dp[i - 1][j - 1][k]) %
            1000000000;
      }
    }
  }
}

let answer = 0;
for (let i = 0; i <= 9; i++) {
  answer += dp[n][i][1023];
}
console.log(answer % 1000000000);
