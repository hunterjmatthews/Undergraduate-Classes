% Group 4:
% Joshua Jones
% Sid Kocer
% Hunter Matthews
% Jack Myrick
% Vishnu Ranganath
% Kevin Tang

function chw2()
    format long
    result1 = evaluateFunction();
    result2 = evaluateRewrittenFunction();
    result3 = rightTriangle();
    T = array2table([result1;result2],'VariableNames',{'10^-1','10^-2','10^-3','10^-4','10^-5','10^-6','10^-7','10^-8','10^-9','10^-10','10^-11','10^-12', ...
        '10^-13', '10^-14'},'RowName',{'Original','Rewritten'});
    % Problem 1:
    fprintf('Problem #1:\n')
    disp(T)

    % Problem 2:
    fprintf('Problem #2:\n')
    fprintf('The hypotenuse is ')
    fprintf('%.3e', result3)
    fprintf(' longer than the longer leg.\n')
end
% Problem #1 - Function 1:
function results = evaluateFunction()
    % Define the range of x values (i.e. generates logarithmically spaced n points between decades 10^-1 and 10^-14)
    valuesOfX = logspace(-1, -14, 14);

    % Stores the results via an array up to the size of vaulesOfX with 0's.
    results = zeros(size(valuesOfX));

    % Uses a for loop to calculate the function at the value of X.
    for i = 1:length(valuesOfX)
        x = valuesOfX(i);
        result = (1 - (1 - x)^3) / x;
        results(i) = result;
    end
end

% Problem #1 - Function 2:
function results = evaluateRewrittenFunction()
    % Define the range of x values (i.e. generates logarithmically spaced values from 10^-1 to 10^-14
    valuesOfX = logspace(-1, -14, 14);

    % Stores the results via an array up to the size of vaulesOfX with 0's.
    results = zeros(size(valuesOfX));

    % Uses a for loop to calculate the function at the value of X.
    for i = 1:length(valuesOfX)
        x = valuesOfX(i);
        result = 3-3*x+x^2;
        results(i) = result;
    end
end

% Problem #2:
% @param a - The value of the 'a' side of the triangle.
% @param b - The value of the 'b' side of the triangle.
% @returns - The value of the hypotenuse value that is longer than the longer leg.
function results = rightTriangle()
    a = 3344556600;
    b = 1.2222222;
    % Rewrote the equation h-a=\sqrt{a^2+b^2-a} as h-a =
    % b^2/(sqrt(a^2+b^2)+a) since there is inherent danger in subtracting
    % nearly two equal numbers.
    results = b^2/(sqrt(a^2+b^2)+a);
end
