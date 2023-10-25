let [target, m, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let bugNum = [];
if (input.length !== 0) {
  bugNum = input[0].split(" ").map((x) => Number(x));
}
const bugCount = Number(m);

let numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
numbers = numbers.filter((item) => !bugNum.includes(item)); //사용 가능한 숫자 가져오기
let needFind = false;

//목표: current -> target 으로 만들기
//target과 가까운 수들 중 numbers로 이루어진 값 찾기
for (let i = 0; i < target.length; i++) {
  if (!numbers.includes(Number(target[i])) && numbers.length > 0) {
    needFind = true;
  }
}

//case1: 1씩 더해가며 찾기
let highTarget = Number(target);
let lowTarget = Number(target);

let isFoundHigh;
let isFoundLow;
while (needFind) {
  if (highTarget === 100) {
    isFoundHigh = true;
    break;
  } else if (lowTarget === 100) {
    isFoundLow = true;
    break;
  }
  highTarget++;
  if (lowTarget >= 0) {
    lowTarget--;
  }
  isFoundHigh = true;
  isFoundLow = true;
  let stringHigh = highTarget.toString();
  let stringLow = lowTarget.toString();
  for (let i = 0; i < stringHigh.length; i++) {
    if (!numbers.includes(Number(stringHigh[i]))) {
      isFoundHigh = false;
    }
  }
  for (let i = 0; i < stringLow.length; i++) {
    if (!numbers.includes(Number(stringLow[i]))) {
      isFoundLow = false;
    }
  }
  if (isFoundLow || isFoundHigh) {
    needFind = false;
  }
}

if (isFoundLow) {
  if (lowTarget === 100) {
    console.log(Math.abs(lowTarget - Number(target)));
  } else
    Math.abs(Number(target) - 100) >
    lowTarget.toString().length + Math.abs(lowTarget - Number(target))
      ? console.log(
          lowTarget.toString().length + Math.abs(lowTarget - Number(target))
        )
      : console.log(Math.abs(100 - Number(target)));
} else if (isFoundHigh) {
  if (highTarget === 100) {
    console.log(Math.abs(highTarget - Number(target)));
  } else
    Math.abs(Number(target) - 100) >
    highTarget.toString().length + Math.abs(highTarget - Number(target))
      ? console.log(
          highTarget.toString().length + Math.abs(highTarget - Number(target))
        )
      : console.log(Math.abs(100 - Number(target)));
} else if (!needFind) {
  if (numbers.length === 0) {
    console.log(Math.abs(100 - Number(target)));
  } else if (target.length > Math.abs(100 - Number(target))) {
    console.log(Math.abs(100 - Number(target)));
  } else console.log(target.length);
}
