const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => Number(x));

let max = 0;
let maxIndex = 0;
for (let i = 0; i < input.length; i++) {
  if (input[i] > max) {
    max = input[i];
    maxIndex = i + 1;
  }
}
console.log(max + "\n" + maxIndex);
