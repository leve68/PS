const [n, input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

function GetNum(value) {
  let temp = "a".charCodeAt() - 1;
  let num = value.charCodeAt() - temp;
  return num;
}

let mulValue = [];
for (let i = 0; i < Number(n); i++) {
  mulValue.push(GetNum(input[i]));
}

let answer = 0;
for (let i = 0; i < Number(n); i++) {
  answer += mulValue[i] * 31 ** i;
}

console.log(answer);
