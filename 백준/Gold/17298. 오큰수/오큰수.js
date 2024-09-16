const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (line) => {
  input.push(line.split(" ").map((v) => Number(v)));
  if (input.length === 2) {
    rl.close();
  }
});

rl.on("close", () => {
  const n = input[0][0];
  const arr = input[1];

  //가장 먼저 닿는 전봇대? 문제와 같은듯
  //bottom 값을 기억하고 있음
  //스택에 오는 값이 top보다 크면 top이 오는 값보다 작아질 때 까지 pop
  //answer의 index를 정해줄 값이 stack에 같이 저장되어야 할듯
  //stack.length === 0 조건 없어서 에러났음

  let stack = [[0, arr[0]]];
  let answer = [];
  for (let i = 1; i < n; i++) {
    while (arr[i] > stack[stack.length - 1][1]) {
      const temp = stack.pop();
      answer[temp[0]] = arr[i];
      if (stack.length === 0) break;
    }
    stack.push([i, arr[i]]);
  }

  stack.forEach((e) => {
    answer[e[0]] = -1;
  });

  console.log(answer.join(" "));
  process.exit();
});
