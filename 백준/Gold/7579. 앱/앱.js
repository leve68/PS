const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = input[0][0]; //실행중 개수
const m = input[0][1]; //필요 메모리

//appMap[i][0] 들의 합 > m 을 만족하는 i 들 중 appMap[i][1] 들의 합이 최소
//선택하거나 선택하지 않거나 => 2^n 이므로 시간초과

//m을 1부터 dp 사용
let appMap = [];
for (let i = 0; i < n; i++) {
  appMap.push([input[1][i], input[2][i]]);
}

let sumWeight = 0;
appMap.forEach((element) => {
  sumWeight += element[1];
});

let dp = Array.from({ length: n + 1 }, () => Array(sumWeight + 1).fill(0));
if (appMap[0][1] === 0) {
  for (let i = 0; i < dp[1].length; i++) {
    dp[1][i] = appMap[0][0];
  }
} else dp[1][appMap[0][1]] = appMap[0][0];

let sum = appMap[0][1];
for (let i = 2; i < n + 1; i++) {
  let current = appMap[i - 1];
  sum += current[1];
  for (let j = 0; j < sumWeight + 1; j++) {
    if (current[1] === 0) {
      dp[i][j] = dp[i - 1][j] + current[0];
    } else if (current[1] <= j && j <= sum) {
      dp[i][j] = Math.max(dp[i - 1][j - current[1]] + current[0], dp[i - 1][j]);
    } else if (current[1] > j) {
      dp[i][j] = dp[i - 1][j];
    }
  }
}

let answer = Number.MAX_SAFE_INTEGER;
for (let i = 1; i < n + 1; i++) {
  for (let j = 0; j < sumWeight + 1; j++) {
    if (dp[i][j] >= m && j < answer) {
      answer = j;
    }
  }
}
console.log(answer);

//dp[검사한개수][cost]
//dp[1][0] = 0
//dp[1][1] = 0
//dp[1][2] = 0
//dp[1][3] = 100
//dp[1][4] = 0
//dp[1][100000] = 0
//dp[2][0] = 10
//dp[2][1] = 10
//dp[2][3] = 40 max(dp[1][3 - 3] + 30, dp[1][3])
//dp[3][6] = 6
