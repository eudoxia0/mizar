(ns mizar.parser-test
  (:require [clojure.test :refer :all]
            [mizar.parser :refer [grammar]]))

(defmacro is-constant [code type value]
  (let [cst (gensym)]
    `(let [~cst (grammar ~code :start :constant)]
       (is (= (first ~cst)
              :constant))
       (is (= (first (second ~cst))
              ~type))
       (is (= (second (second ~cst))
              ~value)))))

(deftest parsing
  (testing "Constants"
    (is-constant "true" :bool "true")
    (is-constant "false" :bool "false")
    (is-constant "123" :integer "123")
    (is-constant "1.23" :float "1.23")
    (is-constant "\"test\"" :string "\"test\""))
  (testing "Toplevel"
    (let [code "int add(a:int b:int) begin
  return a+b
end"]
      (is (grammar code))))
  (testing "Function Calls"
    (let [code "int hello() begin
  puts(\"hello\")
  return 0
end"]
      (is (grammar code)))))
