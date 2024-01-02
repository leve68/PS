const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const nodeCount = Number(n);
const inputArr = input.map((v) => v.split(" "));

let nodeArr = [];
for (let i = 65; i < nodeCount + 65; i++) {
  const node = new Node(String.fromCharCode(i));
  nodeArr.push(node);
}

let myTree = new MyTree();
myTree.root = nodeArr["A".charCodeAt() - 65];

for (let i = 0; i < nodeCount; i++) {
  let rootNode = nodeArr[inputArr[i][0].charCodeAt() - 65];
  let leftNode =
    nodeArr[inputArr[i][1].charCodeAt() - 65] === "."
      ? null
      : nodeArr[inputArr[i][1].charCodeAt() - 65];
  let rightNode =
    nodeArr[inputArr[i][2].charCodeAt() - 65] === "."
      ? null
      : nodeArr[inputArr[i][2].charCodeAt() - 65];

  myTree.myPush(rootNode, leftNode, rightNode);
}

let rootNode = nodeArr[0];
let answer = [];
VLR(rootNode);
console.log(answer.join(""));
answer = [];
LVR(rootNode);
console.log(answer.join(""));
answer = [];
LRV(rootNode);
console.log(answer.join(""));

function VLR(root) {
  answer.push(root.value);
  if (root.leftChild) {
    VLR(root.leftChild);
  }
  if (root.rightChild) {
    VLR(root.rightChild);
  }
}
function LVR(root) {
  if (root.leftChild) {
    LVR(root.leftChild);
  }
  answer.push(root.value);
  if (root.rightChild) {
    LVR(root.rightChild);
  }
}
function LRV(root) {
  if (root.leftChild) {
    LRV(root.leftChild);
  }
  if (root.rightChild) {
    LRV(root.rightChild);
  }
  answer.push(root.value);
}

function Node(curVal) {
  this.value = curVal;
  this.parent = null;
  this.leftChild = null;
  this.rightChild = null;
}

function MyTree() {
  this.root = null;

  this.myPush = function (rootNode, leftNode, rightNode) {
    rootNode.leftChild = leftNode;
    rootNode.rightChild = rightNode;
    if (leftNode) leftNode.parent = rootNode;
    if (rightNode) rightNode.parent = rootNode;
  };
}
