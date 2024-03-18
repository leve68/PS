const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("");

//스택이용할것같음=>dp라내요
//최소 분할 개수

//p[a][b] = true/false 로 판단

let p = Array.from({ length: input.length }, () => Array(input.length));
p[input.length - 1][input.length - 1] = true;
for (let i = 0; i < input.length - 1; i++) {
  //길이가 1
  p[i][i] = true;
  //길이가 2
  if (input[i] === input[i + 1]) p[i][i + 1] = true;
}
for (let len = 3; len <= input.length; len++) {
  for (let i = 0; i + len - 1 < input.length; i++) {
    let j = i + len - 1;
    if (input[i] == input[j] && p[i + 1][j - 1]) p[i][j] = true;
  }
}

//p[a][end] = true 일때
//f[end] = f[a-1]+1
let f = Array(input.length).fill(2501);

for (let i = 0; i < input.length; i++) {
  if (p[0][i]) f[i] = 1;
}

for (let i = 1; i < input.length; i++) {
  for (let j = i; j < input.length; j++) {
    if (p[i][j]) f[j] = Math.min(f[i - 1] + 1, f[j]);
  }
}

console.log(f[input.length - 1]);
