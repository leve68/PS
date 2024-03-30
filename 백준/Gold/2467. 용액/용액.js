const [num, input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = num[0];
let answer = Number.MAX_SAFE_INTEGER;
let answerArr = [];

let start = 0;
let end = n - 1;
while (start < end) {
  let current;
  current = input[start] + input[end];

  if (Math.abs(current) < answer) {
    answer = Math.abs(current);
    answerArr = [start, end];
  }

  if (current < 0) {
    start++;
  } else {
    end--;
  }
}

console.log(input[answerArr[0]], input[answerArr[1]]);
