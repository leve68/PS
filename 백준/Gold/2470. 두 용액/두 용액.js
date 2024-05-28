const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];
rl.on("line", (line) => {
  input.push(line);
  if (input.length === 2) {
    rl.close();
  }
});

rl.on("close", () => {
  let [[solCount], solList] = input.map((v) =>
    v.split(" ").map((x) => Number(x))
  );

  //2개를 골라 0에 가장 가깝게 만들기
  solList.sort((a, b) => a - b);

  let front = 0;
  let end = solCount - 1;
  let min = Math.abs(solList[front] + solList[end]);
  let answer = [solList[front], solList[end]];

  while (front < end) {
    if (Math.abs(solList[front] + solList[end]) < min) {
      min = Math.abs(solList[front] + solList[end]);
      answer = [solList[front], solList[end]];
      if (solList[front] + solList[end] === 0) {
        break;
      }
    }

    if (solList[front] + solList[end] < 0) {
      front++;
    } else {
      end--;
    }
  }

  console.log(answer[0], answer[1]);

  process.exit();
});
