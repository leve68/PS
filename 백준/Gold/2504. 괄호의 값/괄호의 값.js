const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];
rl.on("line", (line) => {
  input = line.split("");
  rl.close();
});

let stack = [];
let answer = 0;
let temp = 1;
rl.on("close", () => {
  //스택으로 괄호 상태를 파악
  //(일때 2를 곱하고 [일때 3을 곱함
  //어떻게 내부 괄호를 파악하지? => 분배법칙 이용
  //() 와 [] 만 숫자로 계산해서 더하는 방식으로 변경함

  //틀린 이유: 괄호가 닫히지 않고 끝났을 때를 검사하지 않음

  for (let i = 0; i < input.length; i++) {
    let current = input[i];
    if (current === "(" || current === "[") {
      stack.push(current);

      if (current === "(") temp *= 2;
      else temp *= 3;
    } else if (current === ")" && stack[stack.length - 1] === "(") {
      stack.pop();

      if (input[i - 1] === "(") {
        answer += temp;
      }
      temp /= 2;
    } else if (current === "]" && stack[stack.length - 1] === "[") {
      stack.pop();

      if (input[i - 1] === "[") {
        answer += temp;
      }
      temp /= 3;
    } else {
      answer = 0;
      break;
    }
  }
  if (stack.length > 0) answer = 0;
  console.log(answer);

  process.exit();
});
