const [n, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n");

const num = Number(n);
const input = inputString.map((x) => Number(x));

// 1 : 1 =1
// 2 : 1+1, 2 =2
// 3 : 1+1+1, 2+1, 1+2, 3 =4
// 4 : 1+1+1+1, 2+1+1, 1+2+1, 1+1+2, 2+2, 3+1, 1+3 =7
// 5 : 1+1+1+1+1, (2,1)4, (3,1)3, (2,2,1)3, (2,3)2 =13
// 6 : (1)1, (2,1)5, (3,1)4, (2,2,1)6, (2,2,2)1, (3,2,1)6, (3,3)1 = 24
//개수 = 이전 개수 + 이전이전개수 + 이전이전이전개수

let count = [0, 1, 2, 4];
for (let i = 4; i <= Math.max(...input); i++) {
  count[i] = count[i - 1] + count[i - 2] + count[i - 3];
}

let answer = [];
for (let i = 0; i < num; i++) {
  answer.push(count[input[i]]);
}
console.log(answer.join("\n"));
