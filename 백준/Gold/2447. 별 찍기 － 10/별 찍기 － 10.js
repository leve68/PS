const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim();

let n = Number(input);

//힌트: 좌표를 판단해서 빈공간을 채우는 것
//제대로 이해 못함
let answer = [];
star(n);
console.log(answer.join(""));

function star(N) {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      printStar(i, j, N);
    }
    answer.push("\n");
  }
}

function printStar(i, j, N) {
  if (Math.floor(i / N) % 3 === 1 && Math.floor(j / N) % 3 === 1) {
    answer.push(" ");
  } else {
    if (N < 3) answer.push("*");
    else printStar(i, j, N / 3);
  }
  return;
}
