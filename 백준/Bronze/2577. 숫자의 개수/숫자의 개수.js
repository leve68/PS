const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => Number(x));

const num = input[0] * input[1] * input[2];
const number = num.toString();
let answer = Array(10).fill(0);
for (let i = 0; i < number.length; i++) {
  let index = number[i];
  answer[index] = answer[index] + 1;
}
console.log(answer.join("\n"));
