const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n");

let gear = [];
let gearSpin = [0, 0, 0, 0];
for (let i = 0; i < 4; i++) {
  gear[i] = input[i].split("").map((x) => Number(x));
}
const k = Number(input[4]);

//1번 2:6 2번 2:6 3번 2:6 4번

for (let i = 5; i < 5 + k; i++) {
  let spin = input[i].split(" ").map((x) => Number(x));
  let gearNum = spin[0] - 1;
  let spinCount = -spin[1];

  spinGear(gearNum, spinCount);
}

let answer = 0;
gearSpin.forEach((value, index) =>
  gear[index][getCog(value)] === 1 ? (answer += Math.pow(2, index)) : null
);
console.log(answer);

function spinGear(gearNum, spinDir) {
  let directions = [0, 0, 0, 0];
  directions[gearNum] = spinDir;

  //오른쪽
  for (let i = gearNum; i < 3; i++) {
    if (isDiffCog(i, i + 1)) {
      directions[i + 1] = -directions[i];
    } else {
      break;
    }
  }

  //왼쪽
  for (let i = gearNum; i > 0; i--) {
    if (isDiffCog(i - 1, i)) {
      directions[i - 1] = -directions[i];
    } else {
      break;
    }
  }

  //회전 적용
  for (let i = 0; i < 4; i++) {
    gearSpin[i] += directions[i];
    if (gearSpin[i] < -8) {
      gearSpin[i] += 8;
    }
  }
}

function getCog(cog) {
  return cog >= 0 ? cog % 8 : cog + 8;
}

//a가 더 왼쪽에 있는 톱니바퀴
function isDiffCog(a, b) {
  return gear[a][getCog(2 + gearSpin[a])] !== gear[b][getCog(6 + gearSpin[b])]
    ? true
    : false;
}
