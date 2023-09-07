% Group 4:
% Joshua Jones
% Sid Kocer
% Hunter Matthews
% Jack Myrick
% Vishnu Ranganath
% Kevin Tang

% Taylor series inputs
syms f(x)
f(x) = x^3 * exp(-5 * x);

% Nested multiplication function call
fprintf("Problem 1: P(x) evaluated at -1/2: %.5f\n", nest([1, -3, 1, -3, -1, 8], -1/2))

% Taylor series function call and evaluation
t4 = taylor(f(x), 4);

x = 1.2;
fprintf("Problem 2: 4th order Taylor approximation of f(1.2): %.5f\n", nest(t4, 1.2));
fprintf("Problem 2: Actual value of f(1.2) for comparison: %.5f\n", double(subs(f)));

% Taylor series approximation
% @param f - Symbolic function to approximate
% @param n - Degree of desired Taylor polynomial
% @returns t - A list of coefficients representing the Taylor polynomial centered at a = 0
function t = taylor(f, n)
    % Pre-allocate array of n + 1 coefficients
    coeffs = zeros(n + 1);
    
    % Substitution
    x = 0;
    coeffs(1) = subs(f);
    % Calculate Taylor coefficients according to the Taylor polynomial formula
    for i = 1:n
        syms h(x);
        % Differentiate the function f i times
        h(x) = diff(f, i);
        % Substitution
        x = 0;
        coeffs(i + 1) = subs(h) / factorial(i);
    end
    t = coeffs;
end

% Nested multiplication
% @param a - Coefficients of polynomial a(0) + a(1)x + ... + a(n)x^n
% @param x - The value to evaluate the polynomial a at
% @returns The value of the polynomial a evaluated at x
function c = nest(a, x)
    n = length(a);
    % a evaluated at x = a(0) + x(a(1) + x(a(2) + ...)))
    p = a(n);
    % iterate from the penultimate coefficient to the first coefficient
    for i = n - 1:-1:1
        p = a(i) + x * p;
    end
    % p contains the value of the polynomial a evaluated at x
    c = p;
end
