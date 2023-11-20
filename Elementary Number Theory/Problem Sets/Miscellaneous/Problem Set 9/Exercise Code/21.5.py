"""

@Author: Hunter Matthews
@Date: 11/06/22

"""

# Accepts a,n\in\mathbb{Z} s.t. a/n is represented as (a,n)
def jacobi(a,n):
    # Step 1
    # a equals a modulo n
    a = a % n
    # initialize t equal to 1
    t = 1

    # Step 3
    # While a is not equal to 0...
    while a != 0:
        # Step 2
        # while a modulo 2 equals 0...
        while a % 2 == 0:
            # This is the same as dividing a by 2**1
            a = a >> 1
            # r equals n modulo 8
            r = n % 8
            # if r equals 3 or r equals 5...
            if r == 3 or r == 5:
                # t equals negative t (i.e. make the value of t negative)
                t = -t
        # Step 4
        # r equals n
        r = n
        # n equals a
        n = a
        # a equals r
        a = r
        if a % 4 == 3 and n % 4 == 3:
            # t equals negative t (i.e. make the value of t negative)
            t = -t
        # a equals a modulo n
        a = a % n
    # Return values 1 or -1 or 0.
    if n == 1:
        return t
    else:
        return 0


if __name__ == "__main__":
    # Call and print the function jacobi with a,n\in\mathbb{Z} s.t. a/n is represented as (a,n)
    print(jacobi(29, 541))
