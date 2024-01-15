const [num1, num2, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const vertexCount = Number(num1);
const edgeCount = Number(num2);
const edgeInput = input.map((v) => v.split(" ").map((x) => Number(x)));

let costMap = Array.from({ length: vertexCount }, () =>
  Array(vertexCount).fill(1000000000)
);

for (let i = 0; i < edgeCount; i++) {
  let start = edgeInput[i][0] - 1;
  let end = edgeInput[i][1] - 1;
  let cost = edgeInput[i][2];
  if (costMap[start][end] > cost) costMap[start][end] = cost;
}
for (let i = 0; i < vertexCount; i++) {
  costMap[i][i] = 0;
}

//플로이드 알고리즘
//경유지를 가장 바깥쪽 for로 감싸야함
for (let k = 0; k < vertexCount; k++) {
  for (let i = 0; i < vertexCount; i++) {
    for (let j = 0; j < vertexCount; j++) {
      costMap[i][j] = Math.min(costMap[i][j], costMap[i][k] + costMap[k][j]);
    }
  }
}
console.log(
  costMap
    .join("\n")
    .replace(/,/g, " ")
    .replace(/1000000000/g, 0)
);
