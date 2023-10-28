const input = require("fs").readFileSync("/dev/stdin").toString().trim();

let num = Number(input);

//1 => 0번
//2 => 1번 /2
//3 => 1번 /3
//4 => 2번 /2 /2
//5 => 3번 -1 /2 /2
//6 => 2번 /3 /2
//7 => 3번 -1 /3 /2
//8 => 3번 /2 /2 /2
//9 => 2번 /3 /3
//10 => 3번 -1 /3 /3
//11 => 4번 -1 -1 /3 /2
//12 => 3번 /3 /2 /2

//규칙 : n이 안나누어지면 n-1의 count + 1
//       n이 나누어지면 몫의 count + 1
//       두 값 중 최소값을 선택

//초기값 설정
let count = [];
count[1] = 0;
count[2] = 1;
count[3] = 1;

for (let i = 4; i <= num; i++) {
  let temp = [];
  if (i % 3 !== 0 && i % 2 !== 0) {
    count[i] = count[i - 1] + 1;
  } else if (i % 3 === 0 && i % 2 !== 0) {
    temp[0] = count[i - 1] + 1;
    temp[1] = count[i / 3] + 1;
    count[i] = temp[0] > temp[1] ? temp[1] : temp[0];
  } else if (i % 3 !== 0 && i % 2 === 0) {
    temp[0] = count[i - 1] + 1;
    temp[1] = count[i / 2] + 1;
    count[i] = temp[0] > temp[1] ? temp[1] : temp[0];
  } else {
    let tempNum = 0;
    temp[0] = count[i - 1] + 1;
    temp[1] = count[i / 2] + 1;
    temp[2] = count[i / 3] + 1;
    tempNum = temp[0] > temp[1] ? temp[1] : temp[0];
    count[i] = temp[2] > tempNum ? tempNum : temp[2];
  }
}

console.log(count[num]);