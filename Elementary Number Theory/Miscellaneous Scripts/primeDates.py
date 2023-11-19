from itertools import chain, combinations

def is_prime(number):
    # if number is equal to or less than 1, return False
    if number <= 1:
        return False

    for x in range(2, number):
        # if number is divisble by x, return False
        if not number % x:
            return False
    return True


def sliceable(xs):
    '''Return a sliceable version of the iterable xs.'''
    try:
        xs[:0]
        return xs
    except TypeError:
        return tuple(xs)


def partition(iterable):
    s = sliceable(iterable)
    n = len(s)
    b, mid, e = [0], list(range(1, n)), [n]
    getslice = s.__getitem__
    splits = (d for i in range(n) for d in combinations(mid, i))
    return [[s[sl] for sl in map(slice, chain(b, d), chain(d, e))]
            for d in splits]


if __name__ == '__main__':
    date = "19112023"
    check, true, false, comb = 0, 0, 0, 0
    primePlots, nonPrimePlots = [], []
    for i in partition(date):
        comb += 1
        check = sum(list(map(int, i)))
        if is_prime(check):
            primePlots.append(check)
            true += 1
            # print(f"{i} sums to {check}. Is it prime? {is_prime(check)}")
        else:
            nonPrimePlots.append(check)
            false += 1
            # print(f"{i} sums to {check}. Is it prime? {is_prime(check)}")

    print(f"True Count: {true}")
    print(f"False Count: {false}")
    print(f"Total Count: {comb}")
