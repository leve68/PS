const [num, ...map] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((x) => Number(x)));

const n = num[0];
const b = num[1];

console.log(Pow(b).join("\n").replace(/,/g, " "));

function Pow(n) {
  if (n === 1) {
    return map.map((v) => v.map((x) => x % 1000)); //행렬 원소는 1000미만
  }
  if (n % 2 === 0) {
    let devided = Pow(n / 2);
    let result = Mul(devided, devided);
    return result;
  } else {
    let devided = Pow((n - 1) / 2);
    let result = Mul(devided, devided);
    return Mul(result, map);
  }
}

function Mul(map1, map2) {
  let newMap = Array.from({ length: n }, () => Array(n).fill(0));
  //map*map
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      for (let k = 0; k < n; k++) {
        newMap[i][j] += (map1[i][k] * map2[k][j]) % 1000;
      }
      if (newMap[i][j] >= 1000) newMap[i][j] %= 1000;
    }
  }
  return newMap;
}
