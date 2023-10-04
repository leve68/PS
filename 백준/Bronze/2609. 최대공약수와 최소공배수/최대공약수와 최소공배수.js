    const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split(" ");
input.map((x) => Number(x));
//유클리드 호제법
//a>b 에 대하여 a = bq + r (0 <= r < b) 일 때 b, r의 최대공약수는 a, b의 최대공약수와 같다. r=0일때 b는 최대공약수이다.
//a = b*q + r(1), b = r(1)q + r(2)

let a = Math.max(...input);
let b = Math.min(...input);
let mul = a * b;
let r;

r = a % b;
if (r === 0) {
  console.log(b);
} else {
  while (r !== 0) {
    temp = b % r;
    if (temp === 0) {
      b = r;
      break;
    } else {
      b = r;
      r = temp;
    }
  }
  console.log(b);
}

console.log(mul / b);
