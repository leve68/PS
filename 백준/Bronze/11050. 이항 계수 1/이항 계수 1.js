const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ");

const n = Number(input[0]);
const k = Number(input[1]);
//이항 계수
let binomial = [];
for (let i = 0; i < n + 1; i++) {
  binomial[i] = [1];
}
binomial[1][1] = 1;

for (let i = 2; i < n + 1; i++) {
  for (let j = 1; j <= i; j++) {
    if (i === j) {
      binomial[i][j] = 1;
    } else binomial[i][j] = binomial[i - 1][j - 1] + binomial[i - 1][j];
  }
}

console.log(binomial[n][k]);