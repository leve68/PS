const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const [n, ...person] = input.map((v) => v.split(" ").map((x) => Number(x)));

let answer = [];
for (let i = 0; i < n; i++) {
  let count = 0;
  for (let j = 0; j < n; j++) {
    if (person[i][0] < person[j][0] && person[i][1] < person[j][1]) {
      count++;
    }
  }
  answer[i] = count + 1;
}

console.log(answer.join(" "));
