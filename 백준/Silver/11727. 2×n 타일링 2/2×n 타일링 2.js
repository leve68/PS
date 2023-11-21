const n = require("fs").readFileSync("/dev/stdin").toString().trim();
const N = Number(n);

//n = n-1 + 2*(n-2)

let answer = [0, 1, 3];
for (let i = 3; i <= N; i++) {
  answer[i] = (answer[i - 1] + 2 * answer[i - 2]) % 10007;
}

console.log(answer[N]);
