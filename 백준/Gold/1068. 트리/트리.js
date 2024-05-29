const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (line) => {
  input.push(line);
  if (input.length === 3) {
    rl.close();
  }
});

let nodeList = [];

rl.on("close", () => {
  const [[n], parent, [target]] = input.map((v) =>
    v.split(" ").map((x) => Number(x))
  );

  //부모 노드 설정
  for (let i = 0; i < n; i++) {
    let node = new Node(parent[i]);
    nodeList[i] = node;
  }
  //배열에 노드 저장
  for (let i = 0; i < n; i++) {
    if (nodeList[i].parant !== -1) {
      nodeList[nodeList[i].parant].children.push(nodeList[i]);
    }
  }

  //삭제할 노드의 부모 노드에 자식이 하나뿐이라면 이 부모가 리프노드가 되는지 체크
  let targetParent = nodeList[target].parant;
  if (targetParent !== -1)
    if (nodeList[targetParent].children.length === 1) {
      nodeList[targetParent].children.length = 0;
    }
  setDeletedNode(nodeList[target]);

  let count = 0;
  for (let i = 0; i < n; i++) {
    if (nodeList[i].parant !== null && nodeList[i].children.length === 0)
      count++;
  }
  console.log(count);

  process.exit();
});

function Node(parent) {
  this.parant = parent;
  this.children = [];
}

function setDeletedNode(node) {
  node.parant = null;
  while (node.children.length > 0) {
    let child = node.children.pop();
    setDeletedNode(child);
  }
  return;
}
