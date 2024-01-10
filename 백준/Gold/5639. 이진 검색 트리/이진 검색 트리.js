const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => Number(x));

let tree = new BST();
let answer = [];

for (let i = 0; i < input.length; i++) {
  tree.myPush(input[i]);
}
LRV(tree.root);
console.log(answer.join("\n"));
//input: 전위순회 VLR
//output: 후위순회 LRV

//1: root
//parent*2 = left child
//parent*2+1 = right child

function TreeNode(value) {
  this.value = value;
  this.left = null;
  this.right = null;
}

function BST() {
  this.root = null;

  this.myPush = function (value) {
    if (value <= 0) return;
    this.root = this._insertNode(this.root, value);
  };

  this._insertNode = function (node, value) {
    if (node === null) {
      return new TreeNode(value);
    }

    if (value < node.value) {
      node.left = this._insertNode(node.left, value);
    } else {
      node.right = this._insertNode(node.right, value);
    }

    return node;
  };
}

function LRV(node) {
  if (node.left) LRV(node.left);
  if (node.right) LRV(node.right);
  answer.push(node.value);
}
