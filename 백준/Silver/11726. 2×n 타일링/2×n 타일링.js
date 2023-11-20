const n = require("fs").readFileSync("/dev/stdin").toString().trim();
const N = Number(n);

//n = 1 : 1
//n = 2 : 2
//n = 3 : 3
//n = 4 : 5
//n = 5 : 8
//n = 6 : 13
//n = 7 : 21
//n = 8 : 34
//n = 9 : 55
let answer = [0, 1, 2];
for (let i = 3; i <= N; i++) {
  answer[i] = (answer[i - 1] + answer[i - 2]) % 10007;
}

console.log(answer[N]);
