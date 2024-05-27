const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (line) => {
  input.push(line);
  if (input[0] == input.length - 1) {
    rl.close();
  }
});

rl.on("close", () => {
  const [num, ...edges] = input.map((v) => v.split(" ").map((x) => Number(x)));
  const edgeCount = num[0];
  let edgeMap = Array(edgeCount + 1);
  let maxIndex = 0;
  for (let i = 0; i < edgeCount; i++) {
    let v1 = edges[i][0];
    let v2 = edges[i][1];

    if (Math.max(v1, v2) > maxIndex) {
      maxIndex = Math.max(v1, v2);
    }

    edgeMap[v1] = v2;
  }
  //edgeMap 에서 가장 긴 증가하는 부분 수열 찾기
  //index와의 LCS찾기
  let dp = Array.from({ length: maxIndex + 1 }, () => Array(maxIndex + 1));
  for (let i = 0; i < dp.length; i++) {
    for (let j = 0; j < dp.length; j++) {
      if (i === 0 || j === 0) dp[i][j] = 0;
      else if (i === edgeMap[j]) {
        dp[i][j] = dp[i - 1][j - 1] + 1;
      } else {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
    }
  }
  console.log(edgeCount - dp[maxIndex][maxIndex]);

  process.exit();
});
