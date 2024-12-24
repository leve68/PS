const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];
rl.on("line", (line) => {
  input.push(Number(line));
  rl.close();
});

//dp문제
//1 : 정방향 1
//2 : 정방향 2+1 + 1, 역방향 1
//3 : 정방향 3+2+1 + 2+1 + 1, 역방향 2+1
//4 : 정방향 4+3+2+1 + 3+2+1 + 2+1 + 1, 역방향 3+2+1 + 1
//5 : 정방향, 역방향 4+3+2+1 + 2+1

rl.on("close", () => {
  let answer = 0;
  const sum = [0];
  const n = input[0];

  for (let i = 1; i <= n; i++) {
    sum[i] = sum[i - 1] + i;
    answer += sum[i];
  }
  for (let i = n - 1; i > 0; i -= 2) {
    answer += sum[i];
  }

  console.log(answer);
  process.exit();
});
