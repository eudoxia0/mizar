(ns mizar.parser-test
  (:require [clojure.test :refer :all]
            [mizar.parser :refer :all]))

(deftest parsing
  (testing "Simple Function"
    (let [code "int add(a:int b:int) begin
  return a+b
end"]
      (is (grammar code)))))
