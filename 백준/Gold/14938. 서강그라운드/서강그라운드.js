const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const vertexCount = input[0][0];
const maxMove = input[0][1];
const edgeCount = input[0][2];

let valueMap = [];
for (let i = 0; i < vertexCount; i++) {
  valueMap[i + 1] = input[1][i];
}

let costMap = Array.from({ length: vertexCount + 1 }, () =>
  Array(vertexCount + 1).fill(100000000)
);
for (let i = 1; i <= vertexCount; i++) {
  costMap[i][i] = 0;
}
for (let i = 2; i < input.length; i++) {
  let edge = input[i];
  if (costMap[edge[0]][edge[1]] >= edge[2]) {
    costMap[edge[0]][edge[1]] = edge[2];
  }
  if (costMap[edge[1]][edge[0]] >= edge[2]) {
    costMap[edge[1]][edge[0]] = edge[2];
  }
}

for (let k = 1; k <= vertexCount; k++) {
  for (let i = 1; i <= vertexCount; i++) {
    for (let j = 1; j <= vertexCount; j++) {
      costMap[i][j] = Math.min(costMap[i][j], costMap[i][k] + costMap[k][j]);
    }
  }
}

let answer = [0];
for (let i = 1; i <= vertexCount; i++) {
  let sum = 0;
  for (let j = 1; j <= vertexCount; j++) {
    if (costMap[i][j] <= maxMove) {
      sum += valueMap[j];
    }
  }
  answer.push(sum);
}
console.log(Math.max(...answer));
