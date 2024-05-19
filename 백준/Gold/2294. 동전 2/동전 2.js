const [num, ...input] = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n");

const k = Number(num.split(" ")[1]);
const coinSet = new Set(input.map((x) => Number(x)));
const coins = Array.from(coinSet).sort((a, b) => a - b);

let dp = Array(k + 1).fill(Infinity);
dp[0] = 0;

for (let coin of coins) {
  for (let j = coin; j <= k; j++) {
    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
  }
}

console.log(dp[k] === Infinity ? -1 : dp[k]);
