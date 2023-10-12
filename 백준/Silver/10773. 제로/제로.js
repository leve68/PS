const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const numInput = input.map((x) => Number(x));

let stack = [];
for (let i = 0; i < numInput.length; i++) {
  if (numInput[i] !== 0) {
    stack.push(numInput[i]);
  } else {
    stack.pop();
  }
}
if (stack.length === 0) stack[0] = 0;

let sum = 0;
stack.forEach((element) => {
  sum += element;
});

console.log(sum);
