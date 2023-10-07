const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ");
const numbers = input.map((x) => Number(x));

const hight = numbers[2];
const up = numbers[0];
const down = numbers[1];

let current = 0;
let sub = up - down;

let dayCount = Math.floor(hight / sub);
current = sub * (dayCount - Math.floor(up / sub));

dayCount -= Math.floor(up / sub);

while (hight > current) {
  dayCount++;
  current += up;
  if (hight <= current) {
    break;
  } else {
    current -= down;
  }
}

console.log(dayCount);
