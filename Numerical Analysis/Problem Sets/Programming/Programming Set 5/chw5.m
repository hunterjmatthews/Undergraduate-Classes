% Group 4:
% Joshua Jones
% Sid Kocer
% Hunter Matthews
% Jack Myrick
% Vishnu Ranganath
% Kevin Tang

% Problem 1
syms f(x);
f(x) = 1 / (1 + x^2);
value = simpsons(f(x), 0, 1);
fprintf("Using the basic Simpson's rule, the value of the integral is
%.10f\n", value);
% Problem 2
% (a)
f(x) = exp(x^2);
value = composite_simpsons(f(x), 0, 1, 16);
fprintf("Using the composite Simpson's rule with 16 subintervals, the value
of the first integral is %.10f\n", value);
value = composite_simpsons(f(x), 0, 1, 32);
fprintf("Using the composite Simpson's rule with 32 subintervals, the value
of the first integral is %.10f\n", value);
% (b)
f(x) = x^3 / (x^4 - 1) ^ (1/2);
value = composite_simpsons(f(x), 2, 3, 16);
fprintf("Using the composite Simpson's rule with 16 subintervals, the value
of the second integral is %.10f\n", value);
value = composite_simpsons(f(x), 2, 3, 32);
fprintf("Using the composite Simpson's rule with 32 subintervals, the value
of the second integral is %.10f\n", value);
% Problem 3
epsilon = 10^(-5);
max_level = 10;
% (a)
f(x) = log(x^2 + 1);
value = adaptive_simpsons(f(x), 0, 1, epsilon, 0, max_level);
fprintf("Using the adaptive Simpson's rule with epsilon 10^(-5), the value of
the first integral is %.10f\n", value);
% (b)
f(x) = x^x;
value = adaptive_simpsons(f(x), 0, 1, epsilon, 0, max_level);
fprintf("Using the adaptive Simpson's rule, the value of the second integral
is %.10f\n", value);
% Basic Simpson's rule on function f(x) on interval [a, b]
function si = simpsons(f, a, b) 
% 1/6[f(a) + 4f((a+b)/2) + f(b)]
result = 0;
 x = a;
 result = result + subs(f);
 x = (a + b) / 2;
 result = result + 4 * subs(f);
 x = b;
 result = result + subs(f);
 si = result / 6;
end
% Composite Simpson's rule on function f(x) on overall interval [a, b] using
n intervals
function csi = composite_simpsons(f, a, b, n)
 h = (b - a) / n;
 x = a;
 result = subs(f);
 x = b;
 result = result + subs(f);
 for i = 1 : n / 2
 x = a + (2 * i - 1) * h;
 result = result + 4 * subs(f);
 end
 for i = 1 : (n - 2) / 2
 x = a + 2 * i * h;
 result = result + 2 * subs(f);
 end
 csi = double((h / 3) * result);
end
% Recursive function to utilize the Adaptive Simpson's method on function
f(x) with desired error epsilon. Level is the current level of recursion and
level_max is the max number of levels before stopping,
function asi = adaptive_simpsons(f, a, b, epsilon, level, level_max)
 level = level + 1;
 h = b - a;
 c = (a + b) / 2;
 x = a;
 one_simpson = subs(x);
 x = c;
 one_simpson = one_simpson + 4 * subs(f);
 x = b;
 one_simpson = one_simpson + subs(f);
 one_simpson = (h / 6) * one_simpson;
 d = (a + c) / 2;
 e = (c + b) / 2;
 x = a;
 two_simpson = subs(f);
 x = d;
 two_simpson = two_simpson + 4 * subs(f);
 x = c;
 two_simpson = two_simpson + 2 * subs(f);
 x = e;
 two_simpson = two_simpson + 4 * subs(f);
 x = b;
 two_simpson = two_simpson + subs(f);
 two_simpson = (h / 12) * two_simpson;
 if level >= level_max
 asi = double(two_simpson);
 else
 if abs(two_simpson - one_simpson) < 15 * epsilon
 asi = two_simpson + (two_simpson - one_simpson) / 15;
 else
 left_simpson = adaptive_simpsons(f, a, c, epsilon / 2, level,
level_max);
 right_simpson = adaptive_simpsons(f, c, b, epsilon / 2, level,
level_max);
 asi = left_simpson + right_simpson;
 end
 asi = double(asi);
 end
end
