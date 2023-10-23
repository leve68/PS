const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const num = input.map((v) => v.split(" ").map((x) => Number(x)));

let testCase = [];
let j = -1;

for (let i = 1; i < num.length; i++) {
  if (num[i].length > 2) {
    testCase.push([num[i]]);
    j++;
  } else {
    testCase[j].push(num[i]);
  }
}

for (let i = 0; i < testCase.length; i++) {
  GetCount(testCase[i]);
}

function GetCount(arr) {
  let x = arr[0][0];
  let y = arr[0][1];
  let count = 0;

  let myMap = [];
  let isVisited = [];
  for (let i = 0; i < y; i++) {
    let rowArr = Array(x);
    let rowArr2 = Array(x);
    myMap.push(rowArr);
    isVisited.push(rowArr2);
  }
  for (let i = 0; i < y; i++) {
    for (let j = 0; j < x; j++) {
      myMap[i][j] = 0;
      isVisited[i][j] = 0;
    }
  }
  for (let i = 1; i < arr.length; i++) {
    myMap[arr[i][1]][arr[i][0]] = 1;
  }

  for (let i = 0; i < y; i++) {
    for (let j = 0; j < x; j++) {
      if (myMap[i][j] === 1 && isVisited[i][j] === 0) {
        //주변부 판단
        isVisited = findNear(myMap, isVisited, i, j);
        count++;
      }
    }
  }
  console.log(count);
}

function findNear(myMap, isVisited, i, j) {
  const dx = [1, 0, -1, 0];
  const dy = [0, 1, 0, -1];
  let allVisited = true;
  isVisited[i][j] = 1; //자기 자신을 방문함 표시
  for (let k = 0; k < 4; k++) {
    if (
      i + dy[k] >= 0 &&
      i + dy[k] < myMap.length &&
      j + dx[k] >= 0 &&
      j + dx[k] < myMap[0].length
    ) {
      if (isVisited[i + dy[k]][j + dx[k]] === 0) {
        allVisited = false;
      }
    }
  }
  if (allVisited === true) return isVisited; //주변 모두 방문했다면 리턴
  for (let k = 0; k < 4; k++) {
    if (
      i + dy[k] >= 0 &&
      i + dy[k] < myMap.length &&
      j + dx[k] >= 0 &&
      j + dx[k] < myMap[0].length
    ) {
      if (isVisited[i + dy[k]][j + dx[k]] === 0) {
        if (myMap[i + dy[k]][j + dx[k]] === 1)
          isVisited = findNear(myMap, isVisited, i + dy[k], j + dx[k]);
        else isVisited[i + dy[k]][j + dx[k]] = 1;
      }
    }
  }
  return isVisited;
}
