//처음 가진 동전의 개수 coin
//카드를 뽑는 순서대로 적힌 수 cards
function solution(coin, cards) {
    var answer = 0;
    //라운드 진행
    //시작 시 2장 뽑기
    //2장의 카드로 n+1 만들기
    //코인을 사용해 뽑은 2장의 카드 중 원하는 것을 가질 수 있음
    //카드를 다 뽑거나 2장 낼 수 없으면 종료

    const target = cards.length + 1;
    let currentCards = cards.splice(0, cards.length/3);
    let newCards = [...cards];
    
    
    let newCurrentCards = [];
    let currentCoins = coin;
    for(answer = 1; answer<=newCards.length/2 ; answer++){
        newCurrentCards.push(newCards[answer*2-2]);
        newCurrentCards.push(newCards[answer*2-1]);
        //가진 걸로 해결
        let found = false;
        for (let i = 0; i < currentCards.length - 1; i++) {
            for (let j = i + 1; j < currentCards.length; j++) {
                if (currentCards[i] + currentCards[j] === target) {
                    found = true;
                    currentCards.splice(j, 1);
                    currentCards.splice(i, 1); 
                    break;
                }
            }
            if (found) break;
        }
        if (found) continue;
        //1개 구매
        if(currentCoins >= 1){
            for (let i = 0; i < currentCards.length; i++) {
                for (let j = 0; j < newCurrentCards.length; j++) {
                    if (currentCards[i] + newCurrentCards[j] === target) {
                        found = true;
                        currentCoins--;
                        currentCards.splice(i, 1); 
                        newCurrentCards.splice(j, 1);
                        break;
                    }
                }
                if (found) break;
            }
        }
        if (found) continue;
        //2개 구매
        if(currentCoins >= 2){
            for (let i = 0; i < newCurrentCards.length-1; i++) {
                for (let j = i+1; j < newCurrentCards.length; j++) {
                    if (newCurrentCards[i] + newCurrentCards[j] === target) {
                        found = true;
                        currentCoins -= 2;
                        newCurrentCards.splice(j, 1); 
                        newCurrentCards.splice(i, 1);
                        break;
                    }
                }
                if (found) break;
            }
        }
        if (found) continue;
        else break;
    }

    
    return answer;
}