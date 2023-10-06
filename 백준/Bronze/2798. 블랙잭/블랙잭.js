const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [cards, numbers] = input.map((v) => v.split(" ").map((x) => Number(x)));

const n = cards[0];
const m = cards[1];

let sum = [];

for (let i = 0; i < n - 2; i++) {
  for (let j = i + 1; j < n - 1; j++) {
    for (let k = j + 1; k < n; k++) {
      sum.push(numbers[i] + numbers[j] + numbers[k]);
    }
  }
}
let mid = 0;
sum.sort((a, b) => a - b);
for (let i = 0; i < sum.length; i++) {
  if (sum[i] > m) {
    mid = i;
    break;
  }
}
if (mid === 0) {
  console.log(sum[sum.length - 1]);
} else console.log(sum[mid - 1]);
