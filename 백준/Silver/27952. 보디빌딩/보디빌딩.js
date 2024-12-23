const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];
rl.on("line", (line) => {
  input.push(line.split(" ").map((v) => Number(v)));
  if (input.length === 3) rl.close();
});

//남은일수 n
//루틴 진행 시 x감량
//i일에는 bi만큼 증량
//i일에는 최소 ai이상
//쓰러지면 -1

//쓰러지는지만 검사하고 마지막날 운동 몰아서 하면 됨

rl.on("close", () => {
  let answer = Infinity;
  const n = input[0][0];
  const x = input[0][1];

  let sum = 0;
  for (let i = 0; i < n; i++) {
    sum += input[2][i];
    if (sum < input[1][i]) {
      answer = -1;
      break;
    }
  }

  if (answer !== -1) {
    answer = Math.floor((sum - input[1][n - 1]) / x);
  }

  console.log(answer);
  process.exit();
});
