const [num, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n");

let request = num.split(" ").map((x) => Number(x))[1];
const input = inputString.map((x) => Number(x));
let current = input.length - 1;
let count = 0;
while (current !== 0) {
  while (request - input[current] >= 0) {
    request -= input[current];
    count++;
  }
  current--;
}
console.log(count + request);
