const inputString = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let input = inputString.map((x) => Number(x));
const count = input[0];

let current = count;
let sum = [];

//n번째 칸까지의 최대값을 구함
sum[0] = 0;
sum[1] = input[1];
sum[2] = input[1] + input[2];

for (let i = 3; i <= count; i++) {
  sum[i] =
    sum[i - 2] + input[i] > sum[i - 3] + input[i - 1] + input[i]
      ? sum[i - 2] + input[i]
      : sum[i - 3] + input[i - 1] + input[i];
}
console.log(sum[count]);
