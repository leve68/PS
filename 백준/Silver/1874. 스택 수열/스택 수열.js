const fs = require("fs");
const [n, ...input] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let num = 1;
let isPossible = true;

let requestArr = input.map(Number);
let myStack = [];
let answer = [];

for (let i = 0; i < input.length; ) {
  if (myStack.length === 0) {
    myStack.push(num++);
    answer.push("+");
  } else if (requestArr[i] > myStack[myStack.length - 1]) {
    myStack.push(num++);
    answer.push("+");
  } else if (requestArr[i] === myStack[myStack.length - 1]) {
    myStack.pop();
    answer.push("-");
    i++;
  } else {
    console.log("NO");
    isPossible = false;
    break;
  }
}

let output = "";

if (isPossible) {
  for (let j = 0; j < answer.length; j++) {
    output += `${answer[j][0]}\n`;
  }
  console.log(output);
}
