"""

@Author: Hunter Matthews
@Date: 11/01/22
"""
import sympy

def get_quadratic_residues(num):
    # Main Loop:
    for p in range(3, num):
        quadratic_residues, non_residues = [], []
        sum_of_quadratic_residues, sum_of_non_residues = 0, 0
        if sympy.isprime(p):
            # 1st Loop: Generates a list of numbers from 1 up to some integer p.
            for i in range(1, p):
                non_residues.append(i)
            # 2nd Loop: Generates a list of quadratic residues modulo p and nonresidues modulo p.
            for i in range(1, p):
                check = i ** 2 % p
                for j in non_residues:
                    if j == check and j not in quadratic_residues:
                        quadratic_residues.append(j)
                        non_residues.remove(j)

            # 3rd Loop: Sums the quadratic residues list and nonresidues list
            for i in quadratic_residues:
                sum_of_quadratic_residues += i
            for i in non_residues:
                sum_of_non_residues += i

            # Print the sum of the quadratic residue list and nonresidues list.
            print(f"{p} & {sum_of_quadratic_residues} & {sum_of_non_residues}")


if __name__ == "__main__":
    get_quadratic_residues(20)
