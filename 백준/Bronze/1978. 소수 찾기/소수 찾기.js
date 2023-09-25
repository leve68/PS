const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [n, numbers] = input.map((v) => v.split(" ").map((x) => Number(x)));

FindNum(numbers);

function FindNum(numbers) {
  let N = Math.max(...numbers);
  let array = [];
  let k = 2;
  for (let i = 0; i < N + 1; i++) {
    array[i] = true;
  }
  array[0] = false;
  array[1] = false;
  while (k < N + 1) {
    if (array[k] === true) {
      for (let i = 2; i < (N + 1) / k; i++) {
        array[i * k] = false;
      }
    }
    k++;
  }
  let count = 0;
  for (let i = 0; i < numbers.length; i++) {
    if (array[numbers[i]] === true) count++;
  }
  console.log(count);
}
