const [n, m, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const numCount = n.split(" ").map((x) => Number(x));
const numArr = m.split(" ").map((x) => Number(x));
const test = input.map((v) => v.split(" ").map((x) => Number(x)));

let sumArr = [numArr[0]];
for (let i = 1; i < numCount[0]; i++) {
  sumArr[i] = sumArr[i - 1] + numArr[i];
}

let answer = [];
for (let k = 0; k < numCount[1]; k++) {
  let i = test[k][0];
  let j = test[k][1];

  let sum = 0;
  if (i === 1) {
    sum = sumArr[j - 1];
  } else sum = sumArr[j - 1] - sumArr[i - 2];

  answer.push(sum);
}
console.log(answer.join("\n"));
