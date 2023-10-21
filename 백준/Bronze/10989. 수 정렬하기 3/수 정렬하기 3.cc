#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    int arr[10001] = {0};
    for(int i = 0; i < n; i++)
    {
        int tmp;
        cin >> tmp;
        arr[tmp]++;
    }
    for(int i = 0; i < 10001; i++)
        if(arr[i] > 0)
            for(int j = 0; j < arr[i]; j++)
                cout << i << '\n';
    return 0;
}