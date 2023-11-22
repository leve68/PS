const [n, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const num = n.split(" ").map((x) => Number(x));
let site = new Map();
const input = inputString.map((v) => v.split(" "));

for (let i = 0; i < num[0]; i++) {
  site.set(input[i][0], input[i][1]);
}
let answer = [];
for (let i = num[0]; i < num[0] + num[1]; i++) {
  answer.push(site.get(input[i][0]));
}
console.log(answer.join("\n"));
