const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (line) => {
  input.push(line);
  if (input.length === Number(input[0]) + 1) {
    rl.close();
  }
});

rl.on("close", () => {
  const n = Number(input[0]);
  input.shift(1);
  const map = input.map((v) => v.split(" ").map((x) => Number(x)));
  const max = Math.max(...map.map((v) => Math.max(...v)));
  const min = Math.min(...map.map((v) => Math.min(...v)));

  //하나도 물에 잠기지 않았을 때 1개
  let count = 1;

  for (let height = min; height <= max; height++) {
    count = Math.max(getCount(height, map), count);
  }

  console.log(count);

  process.exit();
});

function getCount(height, map) {
  const n = map.length;
  let count = 0;
  let visited = Array.from({ length: n }, () => Array(n).fill(false));

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (map[i][j] > height && !visited[i][j]) {
        count++;
        setVisited(i, j);
      } else visited[i][j] = true;
    }
  }

  return count;

  function setVisited(i, j) {
    const dx = [1, 0, -1, 0];
    const dy = [0, -1, 0, 1];

    for (let k = 0; k < 4; k++) {
      if (i + dy[k] >= 0 && i + dy[k] < n && j + dx[k] >= 0 && j + dx[k] < n) {
        if (
          map[i + dy[k]][j + dx[k]] > height &&
          !visited[i + dy[k]][j + dx[k]]
        ) {
          visited[i + dy[k]][j + dx[k]] = true;
          setVisited(i + dy[k], j + dx[k]);
        }
      }
    }
  }
}
