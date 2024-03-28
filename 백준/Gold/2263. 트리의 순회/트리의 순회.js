const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

const n = num[0];
let inorder = input[0];
let postorder = input[1];

// Inorder (중위 순회) : left Node -> root Node -> right Node
// preorder (전위 순회) : root Node -> left Node -> right Node
// postorder (후위 순회) : left Node -> right Node -> root Node

//그냥 재귀로 구현했더니 시간초과남
//Node.js의 call stack 최대 사이즈는 약 1만이므로 에러 발생
//반복문을 사용한 유사한 재귀 => 깊이는 1이므로 호출스택에 부하가 걸리지 않음

// preorder 구하기
let answer = [];
let callStack = [[0, n - 1, 0, n - 1]];

while (callStack.length > 0) {
  const [inLeftIndex, inRightIndex, postLeftIndex, postRightIndex] =
    callStack.pop();
  //root가 존재할 때
  if (postLeftIndex <= postRightIndex) {
    let root = postorder[postRightIndex];
    answer.push(root);

    let inRootIndex = inorder.findIndex((element) => element === root);
    //왼쪽 서브트리 노드 개수
    let leftNodeCount = inRootIndex - inLeftIndex;
    //오른쪽 서브트리 노드 개수
    let rightNodeCount = inRightIndex - inRootIndex;
    //오른쪽 서브트리
    callStack.push([
      inRootIndex + 1,
      inRightIndex,
      postRightIndex - rightNodeCount,
      postRightIndex - 1,
    ]);
    //왼쪽 서브트리
    callStack.push([
      inLeftIndex,
      inRootIndex - 1,
      postLeftIndex,
      postLeftIndex + leftNodeCount - 1,
    ]);
  }
}

console.log(answer.join(" "));
