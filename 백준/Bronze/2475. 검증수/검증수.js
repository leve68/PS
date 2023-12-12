const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ")
  .map((x) => Number(x));

let answer = 0;
for (let i = 0; i < input.length; i++) {
  answer += input[i] * input[i];
}
console.log(answer % 10);
