(ns mizar.backend-test
  (:require [clojure.test :refer :all]
            [mizar.parser :refer [grammar]]
            [mizar.ast :refer [transform]]
            [mizar.backend :refer :all]))

(deftest ast-to-c
  (testing "Simplest Function"
    (let [code "bool pass(b:bool) begin
  if b then
    return true
  else
    return false
end"
          c (emit (transform (grammar code)))]
      (is (string? c)))))
