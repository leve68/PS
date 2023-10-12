const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let answer = [];

for (let i = 0; i < n; i++) {
  let stack = new Array();
  for (let j = 0; j < input[i].length; j++) {
    if (input[i][j] === "(") {
      stack.push(input[i][j]);
    } else if (input[i][j] === ")" && stack.length > 0) {
      stack.pop();
    } else {
      answer[i] = "NO";
      break;
    }
  }
  if (stack.length !== 0) answer[i] = "NO";
  if (answer[i] !== "NO") {
    answer[i] = "YES";
  }
}

console.log(answer.join("\n"));
