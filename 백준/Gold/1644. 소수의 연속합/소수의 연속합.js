const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim();

const n = Number(input);

let prime = [];

let num = Array(n + 1).fill(false);
for (let i = 2; i <= n; i++) {
  if (!num[i]) {
    prime.push(i);
    let temp = i;
    let operand = 1;
    while (temp <= n) {
      num[temp] = true;
      temp = i * operand;
      operand++;
    }
  }
}

let sum = [];
sum[0] = 0;
for (let i = 0; i < prime.length; i++) {
  sum[i + 1] = sum[i] + prime[i];
}

//투포인터 알고리즘
let answer = 0;
let start = 0;
let end = 0;

while (end < sum.length) {
  if (start === end) end++;
  else if (sum[end] - sum[start] < n) end++;
  else if (sum[end] - sum[start] > n) start++;
  else {
    answer++;
    start++;
  }
}

console.log(answer);
