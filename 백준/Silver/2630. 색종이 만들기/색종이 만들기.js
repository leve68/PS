const [N, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const n = Number(N);
const map = inputString.map((v) => v.split(" ").map((x) => Number(x)));
let answerMap = new Map();
answerMap.set(0, 0);
answerMap.set(1, 0);

Test(n, 0, 0);

//n은 판별할 map의 한 변, x,y는 시작좌표
function Test(n, x, y) {
  let setBreak = false;
  let first = map[y][x];

  for (let j = 0; j < n; j++) {
    for (let k = 0; k < n; k++) {
      if (first !== map[y + j][x + k]) {
        setBreak = true;
        break;
      }
    }
    if (setBreak) {
      break;
    }
  }
  if (setBreak) {
    //만약 map내부에 시작원소와 다른원소가 있다면 4개로 나누어 실행
    Test(n / 2, x, y);
    Test(n / 2, x + n / 2, y);
    Test(n / 2, x, y + n / 2);
    Test(n / 2, x + n / 2, y + n / 2);
  } else {
    answerMap.set(first, answerMap.get(first) + 1);
  }
}

console.log(answerMap.get(0));
console.log(answerMap.get(1));
