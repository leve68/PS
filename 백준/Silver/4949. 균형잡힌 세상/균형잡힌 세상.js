const input = require("fs").readFileSync("/dev/stdin").toString().trim();
const cleanedInput = input.replace(/\n/g, ""); //문자열에서 모든 공백 제거

let sentence = [];
let tempArr = new Array();
let j = 0;
for (let i = 0; i < cleanedInput.length; i++) {
  //sentence에 각 문장을 하나의 index안에 배열로 넣음
  if (cleanedInput[i] !== ".") {
    tempArr.push(cleanedInput[i]);
  } else {
    sentence[j] = tempArr;
    tempArr = [];
    j++;
  }
}

for (let i = 0; i < sentence.length; i++) {
  let stack = [];
  let isLog = false;
  for (let j = 0; j < sentence[i].length; j++) {
    if (sentence[i][j] === "(" || sentence[i][j] === "[") {
      stack.push(sentence[i][j]);
    }
    if (sentence[i][j] === ")") {
      if (stack[stack.length - 1] === "(") {
        stack.pop();
      } else {
        console.log("no");
        isLog = true;
        break;
      }
    }
    if (sentence[i][j] === "]") {
      if (stack[stack.length - 1] === "[") {
        stack.pop();
      } else {
        console.log("no");
        isLog = true;
        break;
      }
    }
  }
  if (sentence[i].length === 0) {
  } else if (stack.length !== 0 && !isLog) {
    console.log("no");
  } else if (!isLog) console.log("yes");
}
