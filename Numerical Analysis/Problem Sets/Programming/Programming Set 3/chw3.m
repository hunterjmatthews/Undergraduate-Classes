% Group 4:
% Joshua Jones
% Sid Kocer
% Hunter Matthews
% Jack Myrick
% Vishnu Ranganath
% Kevin Tang
warning('off');

% Problem 1
fprintf("Problem 1:\n");
fprintf("The plot of the graph is on the page below.\n");

% Plot the function and note the intervals in which the zeros occur
fplot(@(x) 2 * x^3 - 6 * x - 1, [-2 2]);
fprintf("3 intervals: [-2, -1], [-1, 0], [1, 2]\n");

syms f(x);
f(x) = 2 * x^3 - 6 * x - 1;
num_iterations = num_bisection_iterations(1, 0.5 * 10^-3);
fprintf("The zero on the interval [-2 1] is %.3f\n", bisection(f, -2, -1, num_iterations));
fprintf("The zero in the interval [-1 0] is %.3f\n", bisection(f, -1, 0, num_iterations));
fprintf("The zero in the interval [1 2] is %.3f\n", bisection(f, 1, 2, num_iterations));

% Problem 2
fprintf("\nProblem 2:\n");
syms g(x);
g(x) = x^3 - 2 * x - 2;
fprintf("The zero found by Newton's method starting at x_0 = 1 is %.8f\n", newton(g, 1, 0.5 * 10^-8))

% Problem 3: a->x0 = 1, b->x1 = 2, nmax = 100, epsilon = 1e^-8
fprintf("\nProblem 3:\n");
Secant(1,2,100,1e-8)

% Functions

% Calculates the number of iterations required to reach accuracy epsilon on an interval of length length_interval
function bi = num_bisection_iterations(length_interval, epsilon)
    bi = ceil(log2(length_interval / epsilon) - 1);
end

% Calculates a zero of the function f on the interval [a, b] with nmax iterations
function bi = bisection(f, a, b, nmax)
    x = a;
    fa = subs(f);
    x = b;
    fb = subs(f);

    error = b - a;
    for i = 0 : nmax + 1
        error = error / 2;
        c = a + error;
        x = c;
        fc = subs(f);
        if subs(sign(fa)) ~= subs(sign(fc))
            b = c;
            fb = fc;
        else
            a = c;
            fa = fc;
        end
    end
    bi = (a + b) / 2;
end

% Calculates a zero of the function f using Newton's method from initial point x_0 and accuracy epsilon
function newt = newton(f, x_0, epsilon)
    x_i = x_0;
    x = x_i;
    fx = subs(f);
    while true
        syms h(x);
        h(x) = diff(f);
        x = x_i;
        fp = subs(h);
        d = fx / fp;
        x_i = x_i - d;
        x = x_i;
        fx = subs(f);
        if abs(d) < epsilon
            break;
        end
    end
    newt = double(x_i);
end

function Secant(a, b, nmax, epsilon)
    % Function to calculate f(x). @(x) is a function handler
    f = @(x) exp(x) + x - 7;

    % Initialization of iteration counter, previous values, current values.
    iterationCount = 0;
    prevX = a;
    currentX = b;

    % While loop for Secant Method
    while iterationCount < nmax
        % Next approximation calculator
        nextX = currentX - (f(currentX) * (prevX - currentX)) / (f(prevX) - f(currentX));
    
        % Check for successful convergence
        if abs(nextX - currentX) < epsilon
            fprintf('Approximate Answer: %.8f\n', nextX);
            fprintf('Iteration Count: %d\n', iterationCount);
            break;
        end
    
        % Update the values for the next iteration
        prevX = currentX;
        currentX = nextX;
        iterationCount = iterationCount + 1;
    end

    % Check if the method did not converge and display error message if iterationCount is greater than or equal to max.
    if iterationCount >= nmax
        error('Did not converge within %d iterations.\n', nmax);
    end
end