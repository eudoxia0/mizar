(ns mizar.backend
  "Turn the AST to C.")

(defn emit
  "Turn an AST node into a C string."
  [node]
  (case (:type node)
    :program (join (emit (:children node)))

    :defn (join (fmt "%s %s(%s) {"
                     (emit (:return-type node))
                     (emit (:name node))
                     (join ", " (map (fn [arg]
                                       (fmt "%s %s" (:type arg) (:name arg)))
                                     (:args node))))
                "}")))
