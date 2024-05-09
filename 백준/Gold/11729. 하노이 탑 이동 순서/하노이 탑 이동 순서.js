const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim();

const n = Number(input);

let k = 0;
let answer = [];

//n-1개 옮기기 + n번째 원판 옮기기 + n-1개 옮기기

move(1, n, 3);

function move(start, n, end) {
  if (n === 0) return;
  let mid = 6 - start - end;
  move(start, n - 1, mid);
  k++;
  answer.push([start, end]);
  move(mid, n - 1, end);
}

console.log(k);
console.log(answer.map((v) => v.join(" ")).join("\n"));
