const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const instruction = input.map((v) => v.split(" "));

let stack = [];
let answer = [];
for (let i = 1; i < instruction.length; i++) {
  switch (instruction[i][0]) {
    case "push":
      stack.push(instruction[i][1]);
      break;
    case "pop":
      if (stack.length > 0) {
        answer.push(stack[stack.length - 1]);
        stack.pop();
      } else answer.push(-1);
      break;
    case "size":
      answer.push(stack.length);
      break;
    case "empty":
      if (stack.length > 0) {
        answer.push(0);
      } else answer.push(1);
      break;
    case "top":
      if (stack.length > 0) {
        answer.push(stack[stack.length - 1]);
      } else answer.push(-1);
      break;
  }
}

console.log(answer.join("\n"));
