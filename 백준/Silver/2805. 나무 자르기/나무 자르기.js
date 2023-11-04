const [N, inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const n = N.split(" ");
const count = Number(n[0]);
const request = Number(n[1]);
const input = inputString
  .split(" ")
  .map((x) => Number(x))
  .sort((a, b) => a - b);
let sum = 0;
let height = 0;
let minHeight = 0;
let maxHeight = input[count - 1];

//만약 sum===request면 hight반환
//sum>request면 hight 높이기
//sum<request면 hight 낮추기

//종료 조건 주의: 일치하는 값이 없으면 sum>request들 중 최대 height를 반환해야함
while (minHeight + 1 < maxHeight) {
  sum = 0;
  height = Math.floor((minHeight + maxHeight) / 2);
  for (let i = 0; i < count; i++) {
    if (input[i] - height > 0) sum += input[i] - height;
  }
  if (sum >= request) {
    //전부 성립하는 값
    minHeight = height;
  } else {
    maxHeight = height;
  }
}
console.log(minHeight);
