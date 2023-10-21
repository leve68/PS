const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const num = input.map((v) => v.split(" ").map((x) => Number(x)));

const arr = num.reduce((a, b) => a.concat(b));
const block = num[0][2];
const matrix = arr.slice(3);

let max = Math.max(...matrix);
let min = Math.min(...matrix);

let answer = [Number.MAX_SAFE_INTEGER, 0];
for (let i = min; i <= max; i++) {
  let time = 0;
  let leftBlock = block;
  for (let j = 0; j < matrix.length; j++) {
    if (matrix[j] > i) {
      time += (matrix[j] - i) * 2;
      leftBlock += matrix[j] - i;
    } else if (matrix[j] < i) {
      time += i - matrix[j];
      leftBlock -= i - matrix[j];
    }
  }
  if (answer[0] > time && leftBlock >= 0) {
    answer[0] = time;
    answer[1] = i;
  } else if (answer[0] === time && leftBlock >= 0) {
    answer[1] = i;
  }
}

console.log(answer.join(" "));
