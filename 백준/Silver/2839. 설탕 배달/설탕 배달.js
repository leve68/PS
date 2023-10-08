const input = require("fs").readFileSync("/dev/stdin").toString().trim();

let count = 0;
let weight = [-1, -1, -1]; // weight[무게] = count
//3n+5m = input , n+m이 최소
//최대 m =1000

//초기값
weight[3] = 1;
weight[4] = -1;
weight[5] = 1;

for (let i = 3; i <= Number(input); i++) {
  if (weight[i] !== -1) {
    if (isNaN(weight[i + 3])) weight[i + 3] = weight[i] + weight[3];
    if (isNaN(weight[i + 5])) weight[i + 5] = weight[i] + weight[5];
  } else {
    weight[i] = -1;
  }
}

for (let i = 0; i < weight.length; i++) {
  if (isNaN(weight[i])) weight[i] = -1;
}

console.log(weight[Number(input)]);
