const [num, input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

const n = num[0];
const s = num[1];

let endDp = [];
let startDp = [];
endDp[0] = input[0];
startDp[0] = 0;
for (let i = 1; i < n; i++) {
  endDp[i] = endDp[i - 1] + input[i];
  startDp[i] = startDp[i - 1] + input[i - 1];
}

let start = 0;
let end = 0;
let shortest = n + 1;
while (start < n) {
  let sub = endDp[end] - startDp[start];

  if (end === n - 1 && sub < s) break;

  if (sub < s) end++;
  else {
    if (end - start + 1 < shortest) shortest = end - start + 1;
    start++;
  }
}

if (shortest === n + 1) shortest = 0;
console.log(shortest);
