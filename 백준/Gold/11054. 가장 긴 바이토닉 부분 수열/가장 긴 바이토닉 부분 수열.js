const [num, input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const n = Number(num);
let map = input.split(" ").map((x) => Number(x));

let increaseDp = Array(n).fill(1);
let decreaseDp = Array(n).fill(1);
//dp
for (let i = 1; i < n; i++) {
  for (let j = 0; j <= i; j++) {
    if (map[i] > map[j] && increaseDp[j] + 1 > increaseDp[i]) {
      increaseDp[i] = increaseDp[j] + 1;
    }
  }
}
for (let i = n - 1; i >= 0; i--) {
  for (let j = n - 1; j >= i; j--) {
    if (map[i] > map[j] && decreaseDp[j] + 1 > decreaseDp[i]) {
      decreaseDp[i] = decreaseDp[j] + 1;
    }
  }
}

let max = 0;
for (let i = 0; i < n; i++) {
  if (increaseDp[i] + decreaseDp[i] > max) {
    max = increaseDp[i] + decreaseDp[i];
  }
}
console.log(max - 1);
