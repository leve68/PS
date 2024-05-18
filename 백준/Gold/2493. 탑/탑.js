const [num, input] = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = num[0];

let answer = Array(n).fill(0);
let stack = [];
let max = 0;
let maxIndex = 0;

for (let i = 0; i < n; i++) {
  //자기보다 큰 왼쪽 탑
  //더 크면 스택에 넣음
  if (input[i] > max) {
    max = input[i];
    maxIndex = i + 1;
    stack = [[max, maxIndex]];
  } else {
    if (input[i] > stack[stack.length - 1][0]) {
      while (input[i] > stack[stack.length - 1][0]) {
        stack.pop();
      }
    }
    answer[i] = stack[stack.length - 1][1];
    stack.push([input[i], i + 1]);
  }
}

console.log(answer.join(" "));