--------------------------------------------------------------------------------
-- author: Hunter Matthews
-- Unlicense (see http://unlicense.org/)
--------------------------------------------------------------------------------
import Primes
import System.Environment
import System.IO
import System.Random

findPairs :: Integer -> IO ()
findPairs n = do
  if n > 1
  then do
    let p = (3 * 2^(n-1)) - 1
    let q = (3 * 2^n) - 1
    let r = (9 * (2^(2 * n - 1)) - 1)
    seed <- newStdGen
    if isPrime seed p == True && isPrime seed q == True && isPrime seed r == True 
    then do
      print(n, (2^n) * p * q, (2^n) * r)
      findPairs(n-1)
    else
      findPairs(n-1)
  else
    return ()
