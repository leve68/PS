const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];
rl.on("line", (line) => {
  input.push(line.split(" ").map((v) => Number(v)));
  if (input[0][1] === input.length - 1) rl.close();
});

let map = [];
let visited = [];
let answer = 0;
rl.on("close", () => {
  const n = input[0][0];
  const m = input[0][1];

  //깊이가 5인 그래프가 있으면 1
  visited = Array(n).fill(false);
  map = Array.from({ length: n }, () => []);
  for (let i = 1; i <= m; i++) {
    map[input[i][0]].push(input[i][1]);
    map[input[i][1]].push(input[i][0]);
  }
  for (let i = 0; i < n; i++) {
    findNext(i, 0);
    if (answer === 1) break;
  }
  console.log(answer);
  process.exit();
});

function findNext(currentIndex, count) {
  visited[currentIndex] = true;
  count++;
  if (count >= 5) {
    answer = 1;
    return;
  }
  if (map[currentIndex].length > 0) {
    map[currentIndex].forEach((element) => {
      if (!visited[element]) findNext(element, count);
    });
  }
  visited[currentIndex] = false;
}
