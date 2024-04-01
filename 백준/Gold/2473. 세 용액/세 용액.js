const [num, input] = require("fs")
  .readFileSync(0,"utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = num[0];
input.sort((a, b) => a - b);
let answer = Number.MAX_SAFE_INTEGER;
let answerArr = [];

let num1;
let num2;
let num3;
for (let i = 0; i < n - 1; i++) {
  for (let j = i + 1; j < n; j++) {
    binarySearch(0, n - 1, i, j);
  }
}
answerArr.push(num1);
answerArr.push(num2);
answerArr.push(num3);
answerArr.sort((a, b) => a - b);

//3개의 용액 index = x, y, z
function binarySearch(frontIndex, backIndex, x, y) {
  while (frontIndex < backIndex) {
    let midIndex = Math.floor((frontIndex + backIndex) / 2);
    let tempSum = input[x] + input[y] + input[midIndex];
    if (Math.abs(tempSum) < Math.abs(answer)) {
      //answer보다 0에 가까우면
      if (midIndex !== x && midIndex !== y) {
        num1 = x;
        num2 = y;
        num3 = midIndex;
        answer = tempSum;
      }
    }
    if (tempSum < 0) {
      frontIndex = midIndex + 1;
    } else {
      backIndex = midIndex - 1;
    }
  }
}

console.log(input[answerArr[0]], input[answerArr[1]], input[answerArr[2]]);
