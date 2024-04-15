const [input1, input2] = require("fs")
  .readFileSync(0,"utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(""));

let count = input2.length - input1.length;
let frontIdx = 0;
let backIdx = input2.length - 1;
let flip = false;

while (count > 0) {
  count--;
  if (flip) {
    //뒤집힌 상태
    if (input2[frontIdx] === "A") {
      frontIdx++;
    } else if (input2[frontIdx] === "B") {
      frontIdx++;
      flip = false;
    } else break;
  } else {
    //안뒤집힌 상태
    if (input2[backIdx] === "A") {
      backIdx--;
    } else if (input2[backIdx] === "B") {
      backIdx--;
      flip = true;
    } else break;
  }
}

let answerArr = [];
if (flip) {
  answerArr = input2.slice(frontIdx, backIdx + 1).reverse();
} else {
  answerArr = input2.slice(frontIdx, backIdx + 1);
}

let answer = 1;
for (let i = 0; i < input1.length; i++) {
  if (input1[i] !== answerArr[i]) answer = 0;
}
console.log(answer);
