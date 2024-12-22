const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];
rl.on("line", (line) => {
  input.push(line.split(" ").map((v) => Number(v)));
  if (input.length === input[0][0] + 1) rl.close();
});

//N개의 집
//K개의 대피소
//answer 대피소와 가장 멀리 떨어진 집의 거리가 최소가 되는 경우의 거리
//완전탐색

rl.on("close", () => {
  const n = input[0][0];
  const k = input[0][1];
  let distance = Array.from({ length: n }, () => Array(n));

  let answer = Infinity;

  // 거리 배열 계산
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      distance[i][j] =
        Math.abs(input[i + 1][0] - input[j + 1][0]) +
        Math.abs(input[i + 1][1] - input[j + 1][1]);
    }
  }

  // 조합 생성 함수
  const getCombinations = (array, selectNum) => {
    if (selectNum === 1) return array.map((element) => [element]);
    const result = [];
    array.forEach((fixed, index, origin) => {
      const rest = origin.slice(index + 1);
      const combinations = getCombinations(rest, selectNum - 1);
      const attached = combinations.map((combo) => [fixed, ...combo]);
      result.push(...attached);
    });
    return result;
  };

  // k에 따른 로직
  const nodes = Array.from({ length: n }, (_, index) => index);

  if (k === 1) {
    for (let i = 0; i < n; i++) {
      let maxDistance = 0;
      for (let j = 0; j < n; j++) {
        maxDistance = Math.max(maxDistance, distance[j][i]);
      }
      answer = Math.min(answer, maxDistance);
    }
  } else {
    const combinations = getCombinations(nodes, k);

    for (const shelters of combinations) {
      let maxDistance = 0;
      for (let i = 0; i < n; i++) {
        let minDistance = Infinity;
        for (const shelter of shelters) {
          minDistance = Math.min(minDistance, distance[i][shelter]);
        }
        maxDistance = Math.max(maxDistance, minDistance);
      }

      answer = Math.min(answer, maxDistance);
    }
  }

  console.log(answer);
  process.exit();
});
