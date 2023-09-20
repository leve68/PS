const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim();

let sum2 = 0;
let sum5 = 0;

for (let i = 1; i <= input; i++) {
  if (i % 2 === 0) {
    let temp = i;
    for (let j = 0; ; j++) {
      if (temp % 2 === 0) temp = temp / 2;
      else {
        sum2 += j;
        break;
      }
    }
  }
  if (i % 5 === 0) {
    let temp = i;
    for (let j = 0; ; j++) {
      if (temp % 5 === 0) temp = temp / 5;
      else {
        sum5 += j;
        break;
      }
    }
  }
}

console.log(Math.min(sum2, sum5));
