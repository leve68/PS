const [t, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const testCount = Number(t);
const input = inputString.map((v) =>
  v.split(" ").map((x) => {
    if (!isNaN(x)) return Number(x);
    else return x;
  })
);

//test : 각 테스트 케이스들의 int 배열
//edgeMap : int 키와 다른 성격들과의 심리적 거리
//personalityMap : 성격을 int로 변환
let personalityMap = new Map();
let personalityString =
  "ISTJ, ISFJ, INFJ, INTJ, ISTP, ISFP, INFP, INTP, ESTP, ESFP, ENFP, ENTP, ESTJ, ESFJ, ENFJ, ENTJ";
let personality = personalityString.split(", ");

let edgeMap = new Map();
for (let i = 0; i < personality.length; i++) {
  //현재 vertex personality[i]
  personalityMap.set(personality[i], i);
  let temp = [];
  for (let j = 0; j < personality.length; j++) {
    let count = 0;
    for (let k = 0; k < 4; k++) {
      if (personality[i][k] !== personality[j][k]) {
        count++;
      }
    }
    temp.push(count);
  }
  edgeMap.set(i, temp);
}

let test = [];
let indexCount = 0;
for (let i = 0; i < testCount * 2; i = i + 2) {
  let temp = [];
  for (let j = 0; j < input[i + 1].length; j++) {
    temp.push(personalityMap.get(input[i + 1][j]));
  }
  test[indexCount] = temp;
  indexCount++;
}
//메모리 반환
personalityMap.delete();

//모든 경우의 수의 심리적 거리 저장 : 16C3 으로 중복없이 560개 밖에 안됨
//0~15 중 3개의 숫자 선택
let sumMap = new Map();
for (let i = 0; i < 16; i++) {
  for (let j = i; j < 16; j++) {
    for (let k = j; k < 16; k++) {
      //선택한 수 i,j,k
      let sum = 0;
      let temp = edgeMap.get(i);
      sum = temp[j] + temp[k];
      temp = edgeMap.get(j);
      sum += temp[k];
      sumMap.set([i, j, k].toString(), sum);
    }
  }
}
//메모리 반환
edgeMap.delete();

let answer = [];
for (let t = 0; t < test.length; t++) {
  let min = 8;
  if (test[t].length > 32) {
    min = 0;
    answer.push(min);
    continue;
  }
  test[t].sort((a, b) => a - b);
  for (let i = 0; i < test[t].length - 2; i++) {
    for (let j = i + 1; j < test[t].length - 1; j++) {
      for (let k = j + 1; k < test[t].length; k++) {
        let temp = [test[t][i], test[t][j], test[t][k]].toString();
        if (sumMap.get(temp) < min) {
          min = sumMap.get(temp);
        }
        if (min === 0) break;
      }
      if (min === 0) break;
    }
    if (min === 0) break;
  }
  answer.push(min);
}
console.log(answer.join("\n"));
