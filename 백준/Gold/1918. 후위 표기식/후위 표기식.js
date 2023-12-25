const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("");

//피연산자 그대로 출력
//연산자는 스택안의 연산자보다 우선순위가 높으면 스택에 추가
//같거나 낮으면 남은 연산자가 자신보다 낮은 우선순위가 될때까지 출력

function isAlphabet(str) {
  // 알파벳 소문자 또는 대문자인지 검사하는 정규표현식
  var alphabeticRegex = /^[a-zA-Z]+$/;
  return alphabeticRegex.test(str);
}

// +, -, *, /, (, )
let stack = [];
let answer = [];
for (let i = 0; i < input.length; i++) {
  if (isAlphabet(input[i])) {
    answer.push(input[i]);
  } else {
    if (input[i] === "-" || input[i] === "+") {
      while (stack.length > 0 && stack[stack.length - 1] !== "(") {
        answer.push(stack.pop());
      }
      stack.push(input[i]);
    } else if (input[i] === "*" || input[i] === "/") {
      while (
        (stack[stack.length - 1] === "*" || stack[stack.length - 1] === "/") &&
        stack.length > 0
      ) {
        answer.push(stack.pop());
      }
      stack.push(input[i]);
    } else if (input[i] === "(") {
      stack.push(input[i]);
    } else if (input[i] === ")") {
      while (stack[stack.length - 1] !== "(" && stack.length > 0) {
        answer.push(stack.pop());
      }
      stack.pop();
    }
  }
}
while (stack.length > 0) {
  answer.push(stack.pop());
}
console.log(answer.join(""));
