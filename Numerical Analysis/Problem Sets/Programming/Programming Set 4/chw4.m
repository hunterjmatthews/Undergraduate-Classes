% Group 4:
% Joshua Jones
% Sid Kocer
% Hunter Matthews
% Jack Myrick
% Vishnu Ranganath
% Kevin Tang

% Problem 1

% Collect the equally spaced nodes (x, y)
xi = zeros(21, 1);
yi = zeros(21, 1);
for i = 1 : 21
    xi(i) = -5 + 10 * (i - 1) / 20;
    x = xi(i);
    yi(i) = 1 / (xi(i)^2 + 1);
end

coeffs = interpolate(20, xi, yi);

% Print results for equally spaced nodes
fprintf("For Equally Spaced Nodes:\n");
for i = 0 : 40
    h = -5 + 10 * i / 40;
    fh = 1 / (h^2 + 1);
    ph = eval(20, xi, coeffs, h);
    fprintf("f(%.5f)=%.5f and p(%.5f)=%.5f\n", h, fh, h, ph);
end

% Problem 2

% Collect first set of Chebyshev nodes
xi = zeros(21, 1);
yi = zeros(21, 1);
for i = 0 : 20
    xi(i + 1) = 5 * cos(i * pi / 20);
    yi(i + 1) = 1 / (xi(i + 1)^2 + 1);
end
coeffs = interpolate(20, xi, yi);
eval(20, xi, coeffs, 5);

% Print first set of Chebyshev nodes results
fprintf("\n\nFor the first set of Chebyshev nodes:\n")
for i = 0 : 40
    h = -5 + 10 * i / 40;
    fh = 1 / (h^2 + 1);
    ph = eval(20, xi, coeffs, h);
    fprintf("f(%.5f)=%.5f and p(%.5f)=%.5f\n", h, fh, h, ph);
end

% Collect second set of Chebyshev nodes
xi = zeros(21, 1);
yi = zeros(21, 1);
for i = 0 : 20
    xi(i + 1) = 5 * cos((2*i + 1) * pi / 42);
    yi(i + 1) = 1 / (xi(i + 1)^2 + 1);
end
coeffs = interpolate(20, xi, yi);
eval(20, xi, coeffs, 5);

% Print results for second set of Chebyshev nodes
fprintf("\n\nFor the second set of Chebyshev nodes:\n");
for i = 0 : 40
    h = -5 + 10 * i / 40;
    fh = 1 / (h^2 + 1);
    ph = eval(20, xi, coeffs, h);
    fprintf("f(%.5f)=%.5f and p(%.5f)=%.5f\n", h, fh, h, ph);
end

% Conclusions
fprintf("\n\nChebyshev nodes appear to be more accurate than equally spaced nodes. The second set of Chebyshev nodes seem at first glance to be less accurate than the first set of Chebyshev nodes.\n");

function in = interpolate(n, x, y)
    a = zeros(n, 1);
    for i = 1 : n + 1
        a(i) = y(i);
    end
    for j = 2 : n + 1
        for i = n + 1 : -1 : j
            a(i) = (a(i) - a(i - 1)) / (x(i) - x(i - j + 1));
        end
    end

    in = a;
end

function ev = eval(n, x, a, t)
    temp = a(n + 1);
    for i = n : -1 : 1
        temp = temp * (t - x(i)) + a(i);
    end
    ev = temp;
end