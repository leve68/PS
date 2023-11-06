const [N, M, S] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n");

let n = Number(N);
let m = Number(M);

let answer = 0;

for (let i = 0; i < m; i++) {
  let count = 0; //IOI반복횟수
  if (S[i] === "I") {
    while (S[i + 1] === "O" && S[i + 2] === "I") {
      count++;
      if (count >= n) {
        //반복횟수가 n이상이면 계속 샘
        answer++;
      }
      i += 2;
    }
  }
}
console.log(answer);
