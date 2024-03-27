const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

const count = n[0];

//수학(기하학)
//(x1,y1), ..., (xn,yn)으로 이루어진 n각형의 면적 구하는 식
//S = 1/2 * |sigma(i=1,n): (x(i)+x(i+1))*(y(i)-y(i+1))|

let sum =
  (input[count - 1][0] + input[0][0]) * (input[count - 1][1] - input[0][1]);
for (let i = 0; i < count - 1; i++) {
  sum += (input[i][0] + input[i + 1][0]) * (input[i][1] - input[i + 1][1]);
}
console.log((Math.abs(sum) / 2).toFixed(1));
