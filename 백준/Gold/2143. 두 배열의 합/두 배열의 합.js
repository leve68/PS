const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

const target = input[0][0];
const aLength = input[1][0];
const aArray = [...input[2]];
const bLength = input[3][0];
const bArray = [...input[4]];

let aDp = [aArray[0]];
let aMap = new Map();

for (let i = 1; i < aLength; i++) {
  aDp[i] = aDp[i - 1] + aArray[i];
}
for (let i = 0; i < aLength; i++) {
  if (aMap.has(aDp[i])) {
    aMap.set(aDp[i], aMap.get(aDp[i]) + 1);
  } else {
    aMap.set(aDp[i], 1);
  }
}
for (let i = 0; i < aLength; i++) {
  for (let j = i + 1; j < aLength; j++) {
    //i시작 - j끝
    if (aMap.has(aDp[j] - aDp[i])) {
      aMap.set(aDp[j] - aDp[i], aMap.get(aDp[j] - aDp[i]) + 1);
    } else {
      aMap.set(aDp[j] - aDp[i], 1);
    }
  }
}

let bDp = [bArray[0]];
let bMap = new Map();

for (let i = 1; i < bLength; i++) {
  bDp[i] = bDp[i - 1] + bArray[i];
}

for (let i = 0; i < bLength; i++) {
  if (bMap.has(bDp[i])) {
    bMap.set(bDp[i], bMap.get(bDp[i]) + 1);
  } else {
    bMap.set(bDp[i], 1);
  }
}
for (let i = 0; i < bLength; i++) {
  for (let j = i + 1; j < bLength; j++) {
    //i시작 - j끝
    if (bMap.has(bDp[j] - bDp[i])) {
      bMap.set(bDp[j] - bDp[i], bMap.get(bDp[j] - bDp[i]) + 1);
    } else {
      bMap.set(bDp[j] - bDp[i], 1);
    }
  }
}

let answer = 0;
let sumA = [...aMap.keys()];
sumA.forEach((aKey) => {
  let bKey = target - aKey;
  if (bMap.has(bKey)) {
    answer += aMap.get(aKey) * bMap.get(bKey);
  }
});
console.log(answer);
