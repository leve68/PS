//node.js 메모리부족으로 풀리지 않음
#include <iostream>
#include <vector>

int main() {
    int n, k;
    std::cin >> n >> k;

    std::vector<int> coins(n);
    for (int i = 0; i < n; ++i) {
        std::cin >> coins[i];
    }

    std::vector<int> dp(k + 1, 0);
    dp[0] = 1;
    //dp[0] !== 0 임
    //dp[0] 또한 동전을 하나도 안 사용하는 경우이기 때문

    for (int i = 0; i < n; ++i) {
        for (int j = coins[i]; j <= k; ++j) {
            dp[j] += dp[j - coins[i]];
        }
    }

    std::cout << dp[k] << std::endl;
    return 0;
}

//dp[6] = dp[5]+dp[1]

//dp[k]

//dp[1] = 1  1
//dp[2] = 2  11, 2
//dp[3] = 2  111, 21
//dp[4] = 3  1111,211,22
//dp[5] = 4  11111,2111,221,5
//dp[6] = 5  111111,21111,2211,222,51
//dp[7] = 6  1111111,211111,22111,2221,511,52
//dp[8] = 7  11111111,2111111,221111,22211,2222,5111,521
//dp[9] = 8  111111111,21111111,2211111,222111,22221,51111,5211,522
//dp[10] = 10

//dp[n] = dp[n] + dp[n - c[i]]