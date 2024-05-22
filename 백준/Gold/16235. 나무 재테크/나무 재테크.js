const [num, ...input] = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = num[0];
const m = num[1];
const k = num[2];

//현재 양분
let foodMap = Array.from({ length: n }, () => Array(n).fill(5));
//현재 나무
let treeMap = Array.from({ length: n }, () =>
  Array.from({ length: n }, () => Array())
);
for (let i = n; i < input.length; i++) {
  treeMap[input[i][0] - 1][input[i][1] - 1].push(input[i][2]);
}
//추가할 양분
let supplyMap = Array(n);
for (let i = 0; i < n; i++) {
  supplyMap[i] = [...input[i]];
}

//4계절
//한칸에 여러 나무가 있다면 어린 나무가 우선순위
//먹을 수 있는 양분이 없다면 즉시 사망

//봄: 나이만큼의 양분을 먹고 나이+1
//여름: 죽은 나무가 양분으로 변환 양분+죽은나무나이/2
function springSummer() {
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (treeMap[i][j].length > 0) {
        for (let k = treeMap[i][j].length - 1; k >= 0; k--) {
          if (foodMap[i][j] >= treeMap[i][j][k]) {
            foodMap[i][j] -= treeMap[i][j][k];
            treeMap[i][j][k]++;
          } else {
            while (k >= 0) {
              foodMap[i][j] += Math.floor(treeMap[i][j][k] / 2);
              treeMap[i][j][k] = 0;
              k--;
            }
            break;
          }
        }
      }
    }
  }
}

const dCol = [-1, -1, 0, 1, 1, 1, 0, -1];
const dRow = [0, 1, 1, 1, 0, -1, -1, -1];

//가을: 나이가 5의 배수이면 주위에 나이1의 나무를 생성
function fall() {
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (treeMap[i][j].length > 0) {
        for (let k = treeMap[i][j].length - 1; k >= 0; k--) {
          if (treeMap[i][j][k] === 0) {
            treeMap[i][j].splice(0, k + 1);
            break;
          }
          if (treeMap[i][j][k] % 5 === 0) {
            for (let l = 0; l < 8; l++) {
              if (
                i + dCol[l] >= 0 &&
                i + dCol[l] < n &&
                j + dRow[l] >= 0 &&
                j + dRow[l] < n
              ) {
                treeMap[i + dCol[l]][j + dRow[l]].push(1);
              }
            }
          }
        }
      }
    }
  }
}
//겨울: 양분추가
function winter() {
  foodMap = foodMap.map((x, xIndex) =>
    x.map((y, yIndex) => y + supplyMap[xIndex][yIndex])
  );
}

//k년 후
for (let i = 0; i < k; i++) {
  springSummer();
  fall();
  winter();
}

let answer = 0;
for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    answer += treeMap[i][j].length;
  }
}
console.log(answer);
