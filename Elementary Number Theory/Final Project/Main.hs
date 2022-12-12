--------------------------------------------------------------------------------
-- author: Hunter Matthews
-- Unlicense (see http://unlicense.org/)
--------------------------------------------------------------------------------
{-# OPTIONS_GHC -fobject-code -O #-}

module Main where

import Math.NumberTheory.Primes.Testing
import System.IO
import Data.Time

findPairs :: Integer -> IO ()
findPairs n = do
  if n > 230000
    then do
      let p = (3 * 2^(n-1)) - 1
          q = (3 * 2^n) - 1
          r = (9 * (2^(2 * n - 1))) - 1
      if isPrime p == True && isPrime q == True && isPrime r == True
        then do
          print(n)
          findPairs(n - 1)
        else
          findPairs(n - 1)
  else
    return ()

main :: IO ()
main = do
  currentTime <- getCurrentTime
  putStrLn ("Searching started @ " ++ show currentTime)
  findPairs 240000
  currentTime <- getCurrentTime
  putStrLn ("Search completed @ " ++ show currentTime)
