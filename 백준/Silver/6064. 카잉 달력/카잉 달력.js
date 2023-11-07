const [T, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n");

let testCase = [];
for (let i = 0; i < Number(T); i++) {
  testCase[i] = input[i].split(" ").map((x) => Number(x));
}

//마지막해 = M:N
//현재 해 = <x:y>
//if x<M x = x+1 else x = 1
//if y<N y = y+1 else y = 1

let answer = [];

// 단순반복문은 시간복잡도가 너무 큼 => 나눗셈활용
for (let i = 0; i < Number(T); i++) {
  let M = testCase[i][0];
  let N = testCase[i][1];
  let cal = [testCase[i][2], testCase[i][3]];
  let current = [1, 1];
  let count = 1;
  let lcm = GetLCM(M, N);

  if (current[0] !== cal[0]) {
    current[0] = cal[0];
    count = cal[0];
    current[1] = current[0] % N;
  }
  //(currentX + M) % N = currentY ==> count = count+M
  if (cal[1] === N) {
    if (cal[0] === M) {
      answer.push(lcm);
      continue;
    }
    cal[1] = 0;
  }
  while (current[1] !== cal[1]) {
    current[1] = (count + M) % N;
    count += M;
    if (count > lcm) {
      count = -1;
      break;
    }
  }
  //   while (current[0] !== cal[0] || current[1] !== cal[1]) {
  //     if (current[0] < M) {
  //       current[0]++;
  //     } else {
  //       current[0] = 1;
  //     }
  //     if (current[1] < N) {
  //       current[1]++;
  //     } else {
  //       current[1] = 1;
  //     }
  //     count++;
  //     if (current[0] === 1 && current[1] === 1) {
  //       count = -1;
  //       break;
  //     }
  //   }
  answer.push(count);
}
console.log(answer.join("\n"));

//유클리드 호재법
function GetLCM(num1, num2) {
  //gcd함수 이해 필요
  const gcd = (a, b) => (a % b === 0 ? b : gcd(b, a % b));
  const lcm = (a, b) => (a * b) / gcd(a, b);
  return lcm(num1, num2);
}
