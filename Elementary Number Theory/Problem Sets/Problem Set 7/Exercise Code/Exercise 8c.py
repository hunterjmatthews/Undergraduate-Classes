def abu_l_hasan_method(e):
    p = 3 * (2 ** (e - 1)) - 1
    q = 3 * (2 ** e) - 1
    r = 9 * (2 ** ((2 * e)-1)) - 1

    if p and q and r % 2 != 0:
        left_pair = (2 ** e) * p * q
        right_pair = (2 ** e) * r
        amicable_pair(left_pair, right_pair)


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
    # Call the method "abu_l_hasan_method" with an e = <insert whatever you want>
    for i in range(2, 10):
        abu_l_hasan_method(i)
