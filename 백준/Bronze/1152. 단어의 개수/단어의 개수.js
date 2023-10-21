const input = require("fs").readFileSync("/dev/stdin").toString().trim();

let count = 0;
for (let i = 0; i < input.length; i++) {
  if (input[i] === " ") count++;
}

if (input.length === 0) {
  console.log(0);
} else console.log(count + 1);
