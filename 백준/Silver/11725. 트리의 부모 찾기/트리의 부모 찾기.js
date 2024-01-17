const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = num[0];
//root === 1
let parent = Array(n);
parent[0] = "null";
parent[1] = "null";

let edgeMap = new Map();
for (let i = 0; i < input.length; i++) {
  if (edgeMap.has(input[i][0])) {
    let temp = edgeMap.get(input[i][0]);
    temp.push(input[i][1]);
    edgeMap.set(input[i][0], temp);
  } else {
    edgeMap.set(input[i][0], [input[i][1]]);
  }

  if (edgeMap.has(input[i][1])) {
    let temp = edgeMap.get(input[i][1]);
    temp.push(input[i][0]);
    edgeMap.set(input[i][1], temp);
  } else {
    edgeMap.set(input[i][1], [input[i][0]]);
  }
}
SetParent(1);
parent.splice(0, 2);
console.log(parent.join("\n"));

function SetParent(root) {
  let children = edgeMap.get(root);
  for (let i = 0; i < children.length; i++) {
    if (!parent[children[i]]) {
      parent[children[i]] = root;
      SetParent(children[i]);
    }
  }
}
