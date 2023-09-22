const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [N, A, M, B] = input.map((v) => v.split(" ").map((x) => Number(x)));

A.sort((a, b) => a - b);

const BinarySearch = (list, target, low, high, mid) => {
  mid = Math.floor((low + high) / 2);

  if (low > high) {
    return list[mid] == target ? 1 : 0;
  }
  if (list[mid] > target) {
    high = mid - 1;
  } else {
    low = mid + 1;
  }

  return BinarySearch(list, target, low, high, mid);
};
let answer = [];
for (let i = 0; i < M; i++) {
  answer.push(BinarySearch(A, B[i], 0, N - 1, 0));
}

const output = answer.join("\n");
console.log(output);
