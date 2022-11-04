-- |
-- Module      : Main
-- Copyright   : Hunter Matthews
-- License     : BSD3
-- 
-- Maintainer  : Hunter Matthews
-- Stability   : experimental
-- Portability : portable
-- 
import Data.List
import System.IO
import Primes

findPairs :: Integer -> IO ()
findPairs n = do
  if n > 1
  then do
    let p = (3 * 2^(n-1)) - 1
    let q = (3 * 2^n) - 1
    let r = (9 * (2^(2 * n - 1)) - 1)
    if isPrime(p) == True && isPrime(q) == True && isPrime(r) == True
    then do
      print(n, (2^n) * p * q, (2^n) * r)
      findPairs(n-1)
    else
      findPairs(n-1)
  else
    print("DONE")
