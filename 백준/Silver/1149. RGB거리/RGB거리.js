let input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const houseCount = input[0][0];

//n과 n-1의 집의 색은다름
//처음 선택한 집에 따라 값을 비교함

//초기값 설정
for (let i = 2; i <= houseCount; i++) {
  //누적된 값들이 더해진 값들을 비교하여 계산함
  input[i][0] += Math.min(input[i - 1][1], input[i - 1][2]);
  input[i][1] += Math.min(input[i - 1][0], input[i - 1][2]);
  input[i][2] += Math.min(input[i - 1][0], input[i - 1][1]);
}
console.log(Math.min(...input[houseCount]));
