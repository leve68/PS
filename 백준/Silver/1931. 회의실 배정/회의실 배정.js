const [n, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const count = Number(n);
const input = inputString.map((v) => v.split(" ").map((x) => Number(x)));

//끝나는시간 우선 시작시간 2순위로 정렬하면 쉬웠음
//반환 값 < 0 : a가 b보다 앞에 있어야 한다.
//반환 값 = 0 : a와 b의 순서를 바꾸지 않는다.
//반환 값 > 0 : b가 a보다 앞에 있어야 한다.
let Time = input.sort((a, b) => {
  if (a[1] === b[1]) {
    return a[0] < b[0] ? -1 : 1;
  }
  return a[1] < b[1] ? -1 : 1;
});

let temp = Time[0];
let answer = 1;
for (let i = 1; i < count; i++) {
  if (Time[i][0] >= temp[1]) {
    temp = Time[i];
    answer++;
  }
}

console.log(answer);
