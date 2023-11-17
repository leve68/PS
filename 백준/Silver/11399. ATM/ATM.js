const [n, input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const num = Number(n);
let time = input
  .split(" ")
  .map((x) => Number(x))
  .sort((a, b) => a - b);


let current = 0;
let sum = 0;
for (let i = 0; i < num; i++) {
  current += time[i];
  sum += current;
}
console.log(sum);
