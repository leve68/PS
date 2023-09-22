const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split(" ");

function FindNum(M, N) {
  let answer = [];
  let array = [];
  let k = 2;
  for (let i = 0; i < N + 1; i++) {
    array[i] = true;
  }
  array[0] = false;
  array[1] = false;
  while (k < N + 1) {
    if (array[k] === true) {
      for (let i = 2; i < (N + 1) / k; i++) {
        array[i * k] = false;
      }
    }
    k++;
  }
  for (let i = M; i < N + 1; i++) {
    if (array[i] === true) {
      answer.push(i);
    }
  }
  console.log(answer.join("\n"));
}
FindNum(Number(input[0]), Number(input[1]));
