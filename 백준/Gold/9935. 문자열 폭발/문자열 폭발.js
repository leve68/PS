const [input1, input2] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(""));

let newString = [];
let stack = [];
for (let i = 0; i < input1.length; i++) {
  if (input1[i] !== input2[input2.length - 1] || i < input2.length - 1) {
    newString.push(input1[i]);
  } else {
    //input[i] === input2[input2.length-1]
    stack.length = 0;
    newString.push(input1[i]);

    while (stack.length < input2.length) {
      stack.push(newString.pop());
    }

    let needDelete = true;
    for (let j = 0; j < input2.length; j++) {
      if (stack[j] !== input2[input2.length - j - 1]) {
        needDelete = false;
      }
    }

    if (!needDelete)
      while (stack.length > 0) {
        newString.push(stack.pop());
      }
  }
}
if (newString.length === 0) console.log("FRULA");
else console.log(newString.join(""));
