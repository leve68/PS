const fs = require("fs");
const input = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const inputCount = input[0].trim().split(" ");

let k = inputCount[0];
let lanCount = Number(inputCount[1]);

Number(input);
input.shift();

let high = Math.max(...input);
let low = 0;

let maxMid = 0;
let sum = 0;

while (low <= high) {
  let mid = Math.floor((high + low) / 2);
  for (let i = 0; i < input.length; i++) {
    sum += Math.floor(input[i] / mid);
  }

  if (sum >= lanCount) {
    if (maxMid < mid) maxMid = mid;
    low = mid + 1;
    sum = 0;
  } else if (sum < lanCount) {
    high = mid - 1;
    sum = 0;
  }
}

console.log(maxMid);
