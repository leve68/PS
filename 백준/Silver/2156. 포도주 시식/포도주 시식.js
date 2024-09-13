const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (line) => {
  input.push(line);
  if (input.length === Number(input[0]) + 1) {
    rl.close();
  }
});

rl.on("close", () => {
  const n = Number(input[0]);
  input.shift(1);
  input = input.map((v) => Number(v));

  //3연속 선택 불가
  //최대값 구하기
  //DP로 예상
  //그냥 dp로 했더니 3연속 조건을 주기가 어려움 => 2차원 배열인가?

  let dp = Array.from({ length: n }, () => []);
  dp[0][0] = 0;
  dp[0][1] = input[0];

  for (let i = 1; i < n; i++) {
    dp[i][0] = Math.max(...dp[i - 1]);
    dp[i][1] = dp[i - 1][0] + input[i];
    dp[i][2] = dp[i - 1][1] + input[i];
  }

  console.log(Math.max(...dp[n - 1]));

  process.exit();
});
