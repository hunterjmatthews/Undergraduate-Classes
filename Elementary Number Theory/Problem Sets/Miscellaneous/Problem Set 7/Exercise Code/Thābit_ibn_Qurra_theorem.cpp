#include<iostream>
#include<vector>
#include<bitset>
#include<cmath>
#include<chrono>

using std::vector;
using std::bitset;
using std::cout;
using std::cin;
using std::endl;

#define N 10000000
typedef  long long ll;

class SumDivisors{
private:
    bitset<N+10> flag;
    vector<ll> p;

    void eratosthenesSieve(){
        p.push_back(2);
        for(ll i = 3; i <= N; i+=2){
            if(flag[i] == 0){
                p.push_back(i);
                if(i*i <= N){
                    for(ll j = i*i; j <= N; j += i*2){
                        flag[j] = 1;
                    }
                }
            }
        }
    }
    ll sumDivisors(ll n){
        ll result = 1;
        for(ll i = 0; p[i]*p[i] <= n; i++){
            if(n % p[i] == 0){
                ll count = 1;
                while(n % p[i] == 0){
                    n = n / p[i];
                    count += 1;
                }
                result *= (pow(p[i], count) - 1) / (p[i] - 1);
            }
        }
        if(n > 1)
            result *= (pow(n, 2) - 1) / (n - 1);
        return result;
    }
    void abu_l_hasan_method(int e)
    {
        long long int p = 0, q = 0, r = 0, left = 0, right = 0;

        p = 3 * (pow(2, (e - 1))) - 1;
        q = 3 * (pow(2, e)) - 1;
        r = 9 * (pow(2, ((2 * e) - 1))) - 1;

        if (p && q && r % 2 != 0)
            left = (pow(2, e) * p * q);
            right = (pow(2,e) * r);
            check_results(left, right);
    }
    void check_results(long long int a, long long int b)
    {
        eratosthenesSieve();
        long long int sig_a = sumDivisors(a);
        long long int sig_b = sumDivisors(b);
        long long int check = a + b;
        if(check == sig_a && check == sig_b) {
            cout << "(" << a << "," << b << ") is an amicable pair!\n";
        } else {
            cout << "(" << a << "," << b << ") is not an amicable pair!\n";
        }

    }

public:
    void run(){
        for(int i = 2; i <= 20; i++) {
            auto start = std::chrono::high_resolution_clock::now();
            cout << i << " ";
            abu_l_hasan_method(i);
            auto stop = std::chrono::high_resolution_clock::now();
            auto duration = std::chrono::duration_cast<std::chrono::microseconds>(stop - start);
            cout << duration.count() << endl;
        }
    }
};

int main(){
    SumDivisors sd;
    sd.run();
}
