const inputString = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim();
let current = Number(inputString);

//1 = 1
//2 = 1+1
//3 = 1+1+1
//4 = 2
//5 = 2+1
//6 = 2+1+1
//7 = 2+1+1+1
//8 = 2+2
//9 = 3
//10 = 3+1
//11 = 3+1+1
//12 = 3+1+1+1 =>2+2+2
//13 = 3+2
//14 = 3+2+1
//15 = 3+2+1+1
//16 = 4
//17 = 4+1
//18 = 4+1+1 => 3+3

let answer = [0, 1, 2, 3];
//(현재 값 - 현재 값보다 작은 제곱수들)중 가장 작은 수+1

let num = 1;
while (num <= current) {
  if (Number.isInteger(Math.sqrt(num))) answer[num] = 1;
  else {
    let tempArr = [];
    let count = 1;
    let temp = 1;
    while (temp < num) {
      tempArr.push(num - temp);
      count++;
      temp = count ** 2;
    }
    let min = 4;
    if (answer[num]) min = answer[min];
    for (let i = 0; i < tempArr.length; i++) {
      temp = answer[tempArr[i]] + 1;
      if (temp <= min) {
        answer[num] = temp;
        min = temp;
      }
    }
  }
  num++;
}
console.log(answer[current]);
