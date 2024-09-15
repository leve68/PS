const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];
rl.on("line", (line) => {
  input = Number(line);
  rl.close();
});

rl.on("close", () => {
  const n = input;

  //계단수 DP이용
  //마지막 자리수가 뭐였는지에 따라 다음에 올 수가 결정됨
  //앞자리가 0인 경우 뒷자리는 1밖에 올 수 없음
  //앞자리가 9인 경우 뒷자리는 8밖에 올 수 없음

  //2차원 dp를 이용해 자리수와 마지막수를 저장
  //dp[총자리수][마지막수] = 가능한 경우의 수 => ...dp[n] 의 총합 구하기

  let dp = Array.from({ length: n + 1 }, () => Array(10).fill(0));
  for (let i = 1; i < 10; i++) {
    dp[1][i] = 1;
  }

  for (let i = 2; i <= n; i++) {
    for (let j = 0; j < 10; j++) {
      if (j === 0) {
        dp[i][j] = dp[i - 1][1];
      } else if (j === 9) {
        dp[i][j] = dp[i - 1][8];
      } else {
        dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
      }
    }
  }

  let sum = 0;
  for (let i = 0; i < 10; i++) {
    sum = (sum + dp[n][i]) % 1000000000;
  }
  console.log(sum);

  process.exit();
});
