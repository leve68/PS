const [num, input] = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" "));

const L = Number(num[0]); //비번 길이
const C = Number(num[1]); //구성될 수 있는 알파벳 개수
input.sort();
const vowel = ["a", "e", "i", "o", "u"];
const answer = [];

function dfs(str, startIndex) {
  if (str.length === L) {
    let vowelCount = 0;
    for (let i = 0; i < str.length; i++) {
      if (vowel.includes(str[i])) vowelCount++;
    }
    if (vowelCount >= 1 && L - vowelCount >= 2) {
      //최소 한개의 모음과 두개의 자음
      answer.push(str);
    }
    return;
  } else {
    for (let i = startIndex; i < C; i++) {
      dfs(str + input[i], i + 1);
    }
  }
}

dfs("", 0);

console.log(answer.join("\n"));
