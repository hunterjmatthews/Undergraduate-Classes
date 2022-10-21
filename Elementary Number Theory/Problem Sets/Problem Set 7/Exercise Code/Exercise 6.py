"""
                Chapter 15: Mersenne Primes and Perfect Numbers
Problem #15.6(a): List all product perfect numbers between 2 and 50.

@Author = Hunter Matthews
@Date = 10/20/22

"""


def print_factors(x):
    factors = []
    result = 1
    for i in range(1, x):
        if x % i == 0:
            factors.append(i)
            result *= i
    if x == result:
        print(result)


if __name__ == "__main__":
    for i in range(2, 51):
        print_factors(i)
