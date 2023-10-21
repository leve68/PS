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
const m = 1234567891;

let mulValue = [];
for (let i = 0; i < Number(n); i++) {
  mulValue.push(GetNum(input[i]));
}

let arr = [1];
for (let i = 1; i < Number(n); i++) {
  arr[i] = (arr[i - 1] * 31) % m;
}

let answer = 0;
for (let i = 0; i < Number(n); i++) {
  answer += (mulValue[i] * arr[i]) % m;
}

if (answer > m) {
  answer = answer % m;
}
console.log(answer);
