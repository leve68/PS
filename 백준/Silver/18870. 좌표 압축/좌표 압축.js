const [n, inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const numCount = Number(n);
const input = inputString.split(" ").map((x) => Number(x));

let newNum = new Map();
//가장 작은 좌표가 0이 됨
input.sort((a, b) => a - b);
newNum.set(input[0], 0);

let count = 1;
for (let i = 1; i < input.length; i++) {
  if (isNaN(newNum.get(input[i]))) {
    newNum.set(input[i], count);
    count++;
  }
}

const answer = inputString.split(" ").map((x) => Number(x));
for (let i = 0; i < answer.length; i++) {
  answer[i] = newNum.get(answer[i]);
}
console.log(answer.join(" "));
