const input = require("fs").readFileSync("/dev/stdin").toString().trim();

//수학문제
//F[n] = F[n/2]x(2F[n/2-1] + F[n/2]) : 짝수
//F[n] = F[(n+1)/2]^2 + F[(n-1)/2]^2 : 홀수

let mem = new Map();
mem.set(0n, 0n);
mem.set(1n, 1n);

console.log(GetF(BigInt(input)).toString().replace("n", ""));

function GetF(num) {
  if (mem.has(num)) return mem.get(num);
  if (num % 2n === 0n) {
    let divided1 = GetF(num / 2n);
    if (!mem.has(num / 2n)) mem.set(num / 2n, divided1);

    let divided2 = GetF(num / 2n - 1n);
    if (!mem.has(num / 2n - 1n)) mem.set(num / 2n - 1n, divided2);

    return (divided1 * (2n * divided2 + divided1)) % 1000000007n;
  } else {
    let divided1 = GetF((num + 1n) / 2n);
    if (!mem.has((num + 1n) / 2n)) mem.set((num + 1n) / 2n, divided1);

    let divided2 = GetF((num - 1n) / 2n);
    if (!mem.has((num - 1n) / 2n)) mem.set((num - 1n) / 2n, divided2);

    return (divided1 ** 2n + divided2 ** 2n) % 1000000007n;
  }
}
