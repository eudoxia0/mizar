(ns mizar.backend
  "Turn the AST to C."
  (:require [clojure.string :refer [join]]))

(def runtime
  "#include <stdio.h>

#define true 1
#define false 0

typedef unsigned char bool;")

(defn emit
  "Turn an AST node into a C string."
  [node]
  (case (:type node)
    :program (format "%s\n\n%s" runtime (join (map emit (:children node))))

    :defn (format "%s %s(%s) {\n%s\n}"
                  (emit (:return-type node))
                  (emit (:name node))
                  (join ", " (map (fn [arg]
                                    (format "%s %s"
                                            (emit (:type arg))
                                            (emit (:name arg))))
                                  (:args node)))
                  (emit (:body node)))

    :if (format "if (%s) {\n%s\n} else {\n%s\n}"
                (emit (:test node))
                (emit (:consequent node))
                (emit (:alternate node)))
    :block (join ";\n" (map emit (:children node)))
    :return (format "return %s;" (emit (:value node)))

    :binop (format "%s %s %s"
                   (emit (:lhs node))
                   (emit (:op node))
                   (emit (:rhs node)))
    :call (format "%s(%s)"
                  (emit (:function node))
                  (join ", " (map emit (:arguments node))))

    :constant (:value node)
    :type (emit (:def node))
    :ident (:value node)

    ""))
