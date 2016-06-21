(ns mizar.ast-test
  (:require [clojure.test :refer :all]
            [mizar.parser :refer :all]
            [mizar.ast :refer :all]))

(deftest cst-to-ast
  (testing "Simplest Function"
    (let [code "bool pass(b:bool) begin
  if b then
    return true
  else
    return false
end"
          cst (grammar code)
          ast (transform cst)]
      (is (= (:type ast) :program))))
  (testing "Addition"
    (let [code "int add(a:int b:int) begin
  return a+b
end"
          cst (grammar code)
          ast (transform cst)]
      (is (= (:type ast) :program))))
  (testing "Constant Addition"
    (let [code "int plusone(val:int) begin
  return val+1
end"
          cst (grammar code)
          ast (transform cst)]
      (println cst)
      (println ast)
      (is (= (:type ast) :program)))))
