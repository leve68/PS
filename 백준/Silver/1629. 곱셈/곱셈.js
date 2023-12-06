const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ")
  .map((x) => Number(x));

const a = BigInt(input[0]);
const b = BigInt(input[1]);
const c = BigInt(input[2]);

function GetNewB(b) {
  if (b === 1n) return a % c;
  if (b % 2n == 0) {
    let divided = GetNewB(b / 2n);
    let result = (divided * divided) % c;
    return result;
  } else {
    let divided = GetNewB((b - 1n) / 2n);
    let result = (((divided * divided) % c) * a) % c;
    return result;
  }
}

console.log(GetNewB(b).toString().replace("n"));
