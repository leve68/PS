const [n, ...input] = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n");

const testCount = Number(n);
const cases = input.map((v) => v.split(" ").map((x) => Number(x)));

//k광년 이동후 다음 이동은 k+1 or k or K-1
//도착 직전의 이동은 1 or -1

for (let i = 0; i < testCount; i++) {
  let start = cases[i][0];
  let dest = cases[i][1];
  let dist = dest - start;

  let temp = Math.ceil(Math.sqrt(dist));
  if (dist <= temp * temp - temp) {
    console.log(temp * 2 - 2);
  } else console.log(temp * 2 - 1);
  //temp*2-1 or temp*2-2
}

//1: 1
//2: 1 1
//3: 1 1 1
//4: 1 2 1
//5: 1 2 1 1
//6: 1 2 2 1
//7: 1 2 2 1 1
//8: 1 2 2 2 1
//9: 1 2 3 2 1
//10: 1 2 3 2 1 1
//11: 1 2 3 2 2 1
//12: 1 2 3 3 2 1
//13: 1 2 3 3 2 1 1
//14: 1 2 3 3 2 2 1
//15: 1 2 3 3 3 2 1
//16: 1 2 3 4 3 2 1
//17: 1 2 3 4 3 2 1 1
//18: 1 2 3 4 3 2 2 1
//19: 1 2 3 4 3 3 2 1
//20: 1 2 3 4 4 3 2 1
//21: 1 2 3 4 4 3 2 1 1
//22: 1 2 3 4 4 3 2 2 1
//23: 1 2 3 4 4 3 3 2 1
//24: 1 2 3 4 4 4 3 2 1
//25: 1 2 3 4 5 4 3 2 1
