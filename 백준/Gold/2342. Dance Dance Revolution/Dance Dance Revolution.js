const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split(" ")
  .map((v) => Number(v));

//0,0 시작
//0 -> 1,2,3,4 : 2
//1 -> 2 인접  : 3
//1 -> 3 반대편 : 4
//1 -> 1 같은점 : 1

let weight = Array.from({ length: 5 }, () => Array(5).fill(0));
for (let i = 0; i < 5; i++) {
  for (let j = 0; j < 5; j++) {
    //i->j 의 weight
    if (j === 0) continue;
    else if (i === 0 && j !== 0) weight[i][j] = 2;
    else if (i === j) weight[i][j] = 1;
    else if (Math.abs(i - j) === 1 || Math.abs(i - j) === 3) weight[i][j] = 3;
    else weight[i][j] = 4;
  }
}

// dp[i][j][k]: i번째 입력까지 왔을 때 왼쪽 발을 j, 오른쪽 발을 k로 두었을 때의 최소 에너지
let dp = Array.from({ length: input.length }, () =>
  Array.from({ length: 5 }, () => Array(5).fill(Number.MAX_SAFE_INTEGER))
);

dp[0][0][0] = 0;

for (let i = 0; i < input.length - 1; i++) {
  for (let j = 0; j < 5; j++) {
    for (let k = 0; k < 5; k++) {
      if (dp[i][j][k] === Number.MAX_SAFE_INTEGER) continue;
      // 왼발 다음에 올 위치로 갈 경우
      dp[i + 1][input[i]][k] = Math.min(
        dp[i + 1][input[i]][k],
        dp[i][j][k] + weight[j][input[i]]
      );
      // 오른발 다음에 올 위치로 갈 경우
      dp[i + 1][j][input[i]] = Math.min(
        dp[i + 1][j][input[i]],
        dp[i][j][k] + weight[k][input[i]]
      );
    }
  }
}

let answer = Number.MAX_SAFE_INTEGER;
for (let i = 0; i < 5; i++) {
  for (let j = 0; j < 5; j++) {
    answer = Math.min(answer, dp[input.length - 1][i][j]);
  }
}

console.log(answer);
