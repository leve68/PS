const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const num = input.map((x) => Number(x));

let fibonacci = [];
fibonacci[0] = [1, 0]; //fibonacci[0의 개수, 1의 개수]
fibonacci[1] = [0, 1];

let answer = [];
for (let i = 0; i < Number(n); i++) {
  count(num[i]);
  answer.push(fibonacci[num[i]]);
}

function count(n) {
  for (let i = 2; i <= n; i++) {
    if (isNaN(fibonacci[i])) {
      fibonacci[i] = [
        fibonacci[i - 1][0] + fibonacci[i - 2][0],
        fibonacci[i - 1][1] + fibonacci[i - 2][1],
      ];
    }
  }
}

console.log(answer.join("\n").replace(/,/g, " "));
