const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim();

let target = Number(input);
let i = 0;
target--;

while (target > 0) {
  i++;
  target = target - 6 * i;
}

console.log(i + 1);
