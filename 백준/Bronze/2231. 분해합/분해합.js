const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim();

let num;
let sum = 0;

num = Number(input) - input.length * 9;

while (Number(input) > num) {
  sum = 0;
  num = num.toString();
  for (let i = 0; i < num.length; i++) {
    sum += Number(num[i]);
  }
  num = Number(num);
  sum += num;
  if (sum === Number(input)) {
    break;
  }
  num++;
}
if (num === Number(input)) {
  console.log(0);
} else console.log(num);
