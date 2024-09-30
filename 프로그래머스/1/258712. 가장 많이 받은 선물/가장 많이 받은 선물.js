function solution(friends, gifts) {
    var answer = 0;
    
    let friendMap = new Map();
    friends.map((name, index) => {
       friendMap.set(name, index)
    });
    
    const gift = gifts.map((gift) => gift.split(" ").map((name)=>friendMap.get(name)));
    const giftMap = Array.from({length: friends.length}, ()=>Array(friends.length).fill(0));
    gift.forEach((e) => {giftMap[e[0]][e[1]] = giftMap[e[0]][e[1]]+1;});
    
    let weightArr = Array(friends.length);
    for(let i = 0;i<giftMap.length;i++){
        let weight = 0;
        for(let j = 0;j<friends.length;j++){
            weight += giftMap[i][j];
        }
        for(let j = 0;j<friends.length;j++){
            weight -= giftMap[j][i];
        }
        weightArr[i] = weight;
    }
    
    //두 사람 사이에 선물 기록이 있다면 더 많이 준 사람이 받음
    //기록이 같다면 weight 더 큰 사람이 받음
    
    let answerArr = Array(friends.length).fill(0);
    for(let i = 0; i<friends.length; i++){
        //i가 받는 선물
        for(let j = 0;j<friends.length; j++){
            //i 와 j 사이의 기록
            if(giftMap[i][j] === giftMap[j][i]){
                //기록 없음
                if(weightArr[i]>weightArr[j]) answerArr[i]++;
            } else {
                //기록 있음
                if(giftMap[i][j] > giftMap[j][i]) answerArr[i]++;
            }
        }
    }
    console.log(answerArr);
    answer = Math.max(...answerArr);
    return answer;
}