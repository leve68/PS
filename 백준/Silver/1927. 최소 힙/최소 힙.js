const [n, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let input = inputString.map((x) => Number(x));
//0이 아니면 input[i]넣고 0이면 힙에서 가장 작은 값 출력후 제거 => min heap
//배열로 구현: 왼쪽 자식 index = 부모 index*2, 오른쪽 자식 index = 부모 index*2+1, 부모 index = 자식index*2

let minHeap = [0];
let answer = [];
for (let i = 0; i < input.length; i++) {
  if (input[i] === 0) {
    minHeap = DeleteFromHeap(minHeap);
    answer.push(minHeap[0]);
  } else {
    minHeap = InsertToHeap(minHeap, input[i]);
  }
}
console.log(answer.join("\n"));

//가장 마지막 자리에 삽입 후 부모를 타고 계속 비교
function InsertToHeap(minHeap, item) {
  if (minHeap.length === 1) {
    minHeap[1] = item;
    return minHeap;
  }
  //마지막 원소에 item 삽입
  let i = minHeap.length;
  minHeap[i] = item;

  //부모 원소가 자신보다 크면 교환
  while (minHeap[Math.floor(i / 2)] > minHeap[i] && i !== 1) {
    let temp = minHeap[Math.floor(i / 2)];
    minHeap[Math.floor(i / 2)] = minHeap[i];
    minHeap[i] = temp;
    i = Math.floor(i / 2);
  }
  return minHeap;
}

//삭제한 후 가장 마지막 자리의 노드를 root로 가져온뒤 자식을 타고 계속 비교
function DeleteFromHeap(minHeap) {
  //힙이 비었을때
  if (minHeap.length === 1) {
    minHeap[0] = 0;
    return minHeap;
  }
  //힙에 root만 있을 때
  if (minHeap.length === 2) {
    minHeap[0] = minHeap[1];
    minHeap.pop();
    return minHeap;
  }

  //힙[0]에 삭제할값 넣고 root에 가장 마지막 원소를 집어넣음
  minHeap[0] = minHeap[1];
  minHeap[1] = minHeap.pop();

  //부모 원소가 자식보다 크면 자식중 작은 값과 교환
  for (let i = 1; i < minHeap.length; ) {
    if (
      minHeap[i] > minHeap[i * 2] &&
      (!minHeap[i * 2 + 1] || minHeap[i * 2] < minHeap[i * 2 + 1])
    ) {
      let temp = minHeap[i * 2];
      minHeap[i * 2] = minHeap[i];
      minHeap[i] = temp;
      i = i * 2;
    } else if (
      minHeap[i] > minHeap[i * 2 + 1] &&
      minHeap[i * 2] >= minHeap[i * 2 + 1]
    ) {
      let temp = minHeap[i * 2 + 1];
      minHeap[i * 2 + 1] = minHeap[i];
      minHeap[i] = temp;
      i = i * 2 + 1;
    } else break;
  }
  return minHeap;
}
