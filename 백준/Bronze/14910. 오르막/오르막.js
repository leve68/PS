const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (line) => {
  input = line.split(" ").map((v) => Number(v));
  rl.close();
});

rl.on("close", () => {
  let answer = "Good";

  if (input.length > 1) {
    for (let i = 1; i < input.length; i++) {
      if (input[i] < input[i - 1]) {
        answer = "Bad";
        break;
      }
    }
  }

  console.log(answer);

  process.exit();
});
