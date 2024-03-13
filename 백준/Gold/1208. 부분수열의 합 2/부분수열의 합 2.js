const [num, input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

const n = num[0];
const target = num[1];
let count = 0;
let sumMap1 = new Map();
let sumMap2 = new Map();
dfs(Math.floor(n / 2), 0, 0, sumMap1);
dfs(n, Math.floor(n / 2), 0, sumMap2);
//dfs(n, Math.floor(n / 2) + 1, 0);
const array = Array.from(sumMap1.keys());
for (let i = 0; i < array.length; i++) {
  if (sumMap2.has(target - array[i])) {
    count += sumMap2.get(target - array[i]) * sumMap1.get(array[i]);
  }
}
console.log(count);

function dfs(end, idx, sum, sumMap) {
  if (idx >= end) {
    return;
  }

  //현재 값을 더함
  sum += input[idx];
  if (sum === target) count++;

  if (sumMap.has(sum)) {
    let temp = sumMap.get(sum);
    sumMap.set(sum, temp + 1);
  } else {
    sumMap.set(sum, 1);
  }

  // 더한 값을 없애고
  dfs(end, idx + 1, sum - input[idx], sumMap);
  // 유지 하고
  dfs(end, idx + 1, sum, sumMap);
}
