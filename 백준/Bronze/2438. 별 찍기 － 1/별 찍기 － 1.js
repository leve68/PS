const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim();

let answer = [];
for (let i = 1; i <= Number(input); i++) {
  let temp = [];
  for (let j = 0; j < i; j++) {
    temp.push("*");
  }
  answer.push(temp);
}
console.log(answer.join("\n").replace(/,/g, ""));
