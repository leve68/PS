const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const numbers = input.map((v) => v.split(" ").map((x) => Number(x)));

let answer = [];
for (let i = 0; i < numbers.length - 1; i++) {
  numbers[i].sort((a, b) => a - b);
  if (numbers[i][0] ** 2 + numbers[i][1] ** 2 === numbers[i][2] ** 2) {
    answer[i] = "right";
  } else {
    answer[i] = "wrong";
  }
}

console.log(answer.join("\n"));
