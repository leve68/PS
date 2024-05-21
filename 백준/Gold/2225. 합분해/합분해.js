const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split(" ")
  .map((x) => Number(x));

const n = input[0];
const k = input[1];

//0~n 의 수 k개를 이용해서 n을 만들 수 있는 경우의수

//2차원 배열
//dp[k][n] => 0~n 을 k개 이용해서 n 만드는 경우의 수
let dp = Array.from({ length: k + 1 }, () => Array(n + 1));
dp[0].fill(0);
dp[1].fill(1);
for (let i = 2; i <= k; i++) {
  //i개의 숫자 이용
  dp[i][0] = 1;
  dp[i][1] = i;
  for (let j = 2; j <= n; j++) {
    //j까지의 숫자 이용 가능
    dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 1000000000;
  }
}
console.log(dp[k][n]);

//점화식 : DP[K][N] = DP[K-1][0] + DP[K-1][1] + ... + DP[K-1][N]
