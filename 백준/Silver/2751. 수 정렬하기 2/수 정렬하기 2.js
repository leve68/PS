const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [n, ...numbers] = input.map((x) => Number(x));

numbers.sort((a, b) => a - b);
console.log(numbers.join("\n"));
