const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [n, ...numbers] = input.map((x) => Number(x));
let sum = 0;
let average = 0;
numbers.forEach((num) => {
  sum += num;
});
average = Math.round(sum / n);
if (average === -0) {
  average = 0;
}
numbers.sort((a, b) => a - b);

let map = new Map();
for (let i = 0; i < n; i++) {
  map.set(numbers[i], (map.get(numbers[i]) || 0) + 1);
}

let max = Math.max(...map.values());
let answer = [];
let maxCount;
for (let [i, j] of map) {
  if (max === j) answer.push(i);
}
maxCount = answer[0];
if (answer.length > 1) {
  maxCount = answer[1];
}

console.log(average);
console.log(numbers[Math.floor(n / 2)]);
console.log(maxCount);
console.log(Math.max(...numbers) - Math.min(...numbers));
