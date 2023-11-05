const [N, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n");

const testCount = Number(N);
let testCase = Array(testCount);
let caseCount = 0;

for (let i = 0; i < inputString.length; i++) {
  if (i % 3 === 0) {
    //함수순서
    let temp = Array();
    temp = inputString[i].split("");
    testCase[caseCount] = [temp];
  } else if (i % 3 === 1) {
    //원소개수
    testCase[caseCount][1] = Number(inputString[i]);
  } else {
    let temp = Array();
    temp = inputString[i].slice(1, inputString[i].length - 1);
    if (temp.length === 0) testCase[caseCount][2] = [];
    else testCase[caseCount][2] = temp.split(",").map((x) => Number(x));
    caseCount++;
  }
}

let answer = [];
for (let i = 0; i < testCount; i++) {
  answer.push(GetAnswer(i, testCase));
}
console.log(answer.join("\n"));

function GetAnswer(num, test) {
  let functionArr = test[num][0];
  let arr = test[num][2];
  let isRevers = false;
  for (let i = 0; i < functionArr.length; i++) {
    if (functionArr[i] === "R") {
      //뒤집기
      isRevers = !isRevers;
    } else {
      if (arr.length === 0) {
        return "error";
      }
      if (isRevers) {
        //역순: 가장 뒤 출력
        arr.pop();
      } else {
        //정순: 가장 앞 출력
        arr.shift();
      }
    }
  }
  if (!isRevers) return "[" + arr + "]";
  else {
    let reverseArr = Array();
    for (let i = 0; i < arr.length; i++) {
      reverseArr[i] = arr[arr.length - 1 - i];
    }
    return "[" + reverseArr + "]";
  }
}
