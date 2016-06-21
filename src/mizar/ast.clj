(ns mizar.ast
  "Mizar's AST, and code to turn parser output into the AST."
  (:require [mizar.parser :refer :all]))

(defn transform
  "Transform a CST (parse tree) into an AST."
  [node]
  (let [mapt (fn [nodes] (map transform nodes))]
    (case (first node)
      :program {:type :program
                :children (mapt (rest node))}
      :defn (let [[type name [_ & args] body] (rest node)]
                {:type :defn
                 :name (transform name)
                 :args (map (fn [[label name colon type]]
                              {:name (transform name)
                               :type (transform type)})
                            (remove string? args))
                 :return-type (transform type)
                 :body (transform body)})

      :statement (transform (second node))
      :if {:type :if
           :test (transform (nth node 2))
           :consequent (transform (nth node 4))
           :alternate (transform (nth node 6))}
      :block {:type :block
              :children (mapt (rest (butlast (rest node))))}
      :return {:type :return
               :value (transform (nth node 2))}

      :expression (transform (second node))
      :binop {:type :binop
              :op (nth node 2)
              :lhs (transform (nth node 1))
              :rhs (transform (nth node 3))}

      :constant {:type :constant
                 :value (transform (second node))}
      :integer (second node)


      :type {:type :type
             :def (transform (second node))}
      :ident (second node)
      nil)))
