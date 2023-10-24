let input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ");

const num = input.map((x) => Number(x));

const n = num[0];
let target = [num[1], num[2]];

let current = 2 ** n;
let answer = [];
let count = 0;

while (current >= 4) {
  let temp = current / 2;
  if (target[0] < temp && target[1] < temp) {
    //1사분면
    target = [target[0], target[1]];
  } else if (target[0] >= temp && target[1] < temp) {
    //2사분면
    let dx = target[0] - temp;
    target = [dx, target[1]];
    count = count + temp * temp * 2;
  } else if (target[0] >= temp && target[1] >= temp) {
    //3사분면
    let dx = target[0] - temp;
    let dy = target[1] - temp;
    target = [dx, dy];
    count = count + temp * temp * 3;
  } else if (target[0] < temp && target[1] >= temp) {
    //4사분면
    let dy = target[1] - temp;
    target = [target[0], dy];
    count = count + temp * temp;
  }
  current = temp;
}

if (target[0] === 0 && target[1] === 0) console.log(count);
else if (target[0] === 0 && target[1] === 1) console.log(count + 1);
else if (target[0] === 1 && target[1] === 0) console.log(count + 2);
else if (target[0] === 1 && target[1] === 1) console.log(count + 3);
