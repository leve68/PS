const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const nInput = n.split(" ").map((x) => Number(x));
const count = nInput[0];
const questionCount = nInput[1];
//map을 사용해 보자! => array는 시간복잡도가 너무 큼 약 100억ns
//map을 이용한 key의 탐색은 O(1)의 사간복잡도를 가짐
let numKeyDic = new Map();
let nameKeyDic = new Map();
let question = [];

for (let i = 0; i < count; i++) {
  numKeyDic.set(i + 1, input[i]);
  nameKeyDic.set(input[i], i + 1);
}

for (let i = 0; i < questionCount; i++) {
  question[i] = input[i + count];
}

let answer = [];
for (let i = 0; i < questionCount; i++) {
  if (isNaN(question[i])) {
    //이름일때
    answer[i] = nameKeyDic.get(question[i]);
  } else {
    //숫자일때
    answer[i] = numKeyDic.get(Number(question[i]));
  }
}
console.log(answer.join("\n"));