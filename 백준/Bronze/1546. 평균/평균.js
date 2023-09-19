const fs = require("fs");
const [n, input] = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const inputArr = input.trim().split(" ");

let maxScore = 0;
let sum = 0;

for (let i = 0; i < inputArr.length; i++) {
  if (Number(inputArr[i]) > maxScore) {
    maxScore = inputArr[i];
  }
}
for (let j = 0; j < inputArr.length; j++) {
  inputArr[j] = (inputArr[j] / maxScore) * 100;
  sum += inputArr[j];
}

console.log(sum / inputArr.length);
