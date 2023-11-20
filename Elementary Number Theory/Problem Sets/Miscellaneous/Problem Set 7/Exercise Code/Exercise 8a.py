"""
                    Chapter 15: Mersenne Primes and Perfect Numbers
Problem 15.8: Verify that each of the following pairs is an amicable pair of numbers. (220,284),
(1184,1210), (2620,2924), (5020,5564), (6232,6368), (10744,10856), (12285,14595).

@Author = Hunter Matthews
@Date = 10/20/22

"""


def sum_of_divisor(a):
    divisor_sum = 0  # updated line
    for i in range(1, a + 1):
        if(a % i == 0):
            divisor_sum += i
    return divisor_sum


def amicable_pair(a,b):
    # Calculate the sum of divisors of a and b (i.e. sigma(a) and sigma(b))
    sigma_a = sum_of_divisor(a)
    sigma_b = sum_of_divisor(b)

    # See if the pair is amicable
    if a + b == sigma_a and sigma_b:
        print(f"({a},{b}) is an amicable pair!")
    else:
        print(f"({a},{b}) is not amicable pair!")



if __name__ == "__main__":
    list1 = [220, 1184, 2620, 5020, 6232, 10744, 12285]
    list2 = [284, 1210, 2924, 5564, 6368, 10856, 14595]

    # Check if every pair in the list (i.e. one pair is (list1[i], list2[i])) is amicable or not.
    for i in range(0, len(list1)):
        amicable_pair(list1[i], list2[i])
