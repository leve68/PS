const [num, ...input] = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

//n개의 집합, input의 길이 m
const n = num[0];
const m = num[1];

let parent = Array.from({ length: n + 1 }, (_, i) => i);

function find(x) {
  if (parent[x] !== x) {
    parent[x] = find(parent[x]); // Path compression
  }
  return parent[x];
}

function union(a, b) {
  const rootA = find(a);
  const rootB = find(b);

  if (rootA < rootB) parent[rootB] = rootA;
  else parent[rootA] = rootB;
}

let answer = [];
for (let i = 0; i < m; i++) {
  let num1 = input[i][1];
  let num2 = input[i][2];
  if (input[i][0] === 0) {
    //합집합
    union(num1, num2);
  } else {
    //출력
    if (find(num1) === find(num2)) answer.push("YES");
    else answer.push("NO");
  }
}
console.log(answer.join("\n"));
