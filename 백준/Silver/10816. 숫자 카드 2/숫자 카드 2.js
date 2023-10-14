const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let lines = [];
let count = 0;
let n,
  cards = [],
  m,
  requires = [];

rl.on("line", (line) => {
  lines.push(line);
  count++;

  if (count === 4) {
    n = parseInt(lines[0]);
    cards = lines[1].split(" ").map((item) => Number(item));
    m = parseInt(lines[2]);
    requires = lines[3].split(" ").map((item) => Number(item));

    cards.sort((a, b) => a - b);

    const LowerBound = (list, target, low, high, mid) => {
      let count = 0;
      while (low <= high) {
        mid = Math.floor((low + high) / 2);

        if (list[mid] === target) {
          high = mid - 1;
          count++;
        } else if (list[mid] < target) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
      if (count === 0 && low >= high) return 0;
      else return low;
    };
    const UpperBound = (list, target, low, high, mid) => {
      let count = 0;
      while (low <= high) {
        mid = Math.floor((low + high) / 2);
        if (list[mid] === target) {
          low = mid + 1;
          count++;
        } else if (list[mid] < target) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
      if (count === 0 && low >= high) return 0;
      else return low;
    };

    let answer = [];
    for (let i = 0; i < m; i++) {
      let target = requires[i];
      answer[i] =
        UpperBound(cards, target, 0, n - 1) -
        LowerBound(cards, target, 0, n - 1);
    }

    console.log(answer.join(" "));

    rl.close();
  }
});
